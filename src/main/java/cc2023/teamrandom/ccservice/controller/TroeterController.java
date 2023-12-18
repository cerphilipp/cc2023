package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.model.MastodonAccount;
import cc2023.teamrandom.ccservice.model.MastodonStatus;
import cc2023.teamrandom.ccservice.model.gson.StatusSerializer;
import cc2023.teamrandom.ccservice.model.gson.ZonedDateTimeTypeAdapter;
import cc2023.teamrandom.ccservice.services.TroetListService;
import cc2023.teamrandom.ccservice.services.TroetListServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.micrometer.core.annotation.Counted;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.*;

@RestController
public class TroeterController {

	private final TroetListService troetListService = new TroetListServiceImpl();

	Gson gson;

	{
		gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter()).registerTypeAdapter(MastodonStatus.class, new StatusSerializer())
				.create();;
	}

	@Counted(value = "metrics.troeters", description = "Number of calls to the metrics/troeters endpoint")
	@RequestMapping(value = "/api/home/troeters", produces = { "application/json" })
	public ResponseEntity<JsonNode> troeters(){
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String homeJson = troetListService.listTroets();

			Set<String> uniqueAccounts = new HashSet<>();

			JsonNode entireMastodonStatuses = objectMapper.readTree(homeJson);

			if (entireMastodonStatuses == null || !entireMastodonStatuses.isArray()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			ArrayNode resultArray = objectMapper.createArrayNode();

			for (JsonNode mastodonStatus : entireMastodonStatuses) {
				JsonNode account = mastodonStatus.has("account") ? mastodonStatus.get("account") : null;
				if (account == null) continue;
				String id = account.has("id") ? account.get("id").asText() : null;

				if(id != null && !uniqueAccounts.contains(id)) {
					uniqueAccounts.add(id);
					resultArray.add(account);
				}
			}

			return new ResponseEntity<>(resultArray, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
