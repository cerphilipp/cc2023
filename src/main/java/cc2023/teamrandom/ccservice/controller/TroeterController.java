package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.model.MastodonAccount;
import cc2023.teamrandom.ccservice.model.MastodonStatus;
import cc2023.teamrandom.ccservice.model.gson.StatusSerializer;
import cc2023.teamrandom.ccservice.model.gson.ZonedDateTimeTypeAdapter;
import cc2023.teamrandom.ccservice.services.TroetListService;
import cc2023.teamrandom.ccservice.services.TroetListServiceImpl;
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
	public ResponseEntity<String> troeters(){
		MastodonStatus[] entireMastodonStatuses = gson.fromJson(troetListService.listTroets(), MastodonStatus[].class);

		Set<MastodonAccount> uniqueAccounts = new HashSet<>();

		for(MastodonStatus status : entireMastodonStatuses) {
			MastodonAccount account = status.getAccount();
			uniqueAccounts.add(account);
		}

		List<MastodonAccount> result = new ArrayList<>(uniqueAccounts);

		return new ResponseEntity<>(gson.toJson(result), HttpStatus.OK);
	}
}
