package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.model.MastodonStatus;
import cc2023.teamrandom.ccservice.model.gson.StatusSerializer;
import cc2023.teamrandom.ccservice.model.gson.ZonedDateTimeTypeAdapter;
import cc2023.teamrandom.ccservice.services.TroetListService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import io.micrometer.core.annotation.Counted;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cc2023.teamrandom.ccservice.services.TroetListServiceImpl;
import org.springframework.web.client.HttpServerErrorException;

import java.time.ZonedDateTime;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HomeController {

	public TroetListService service = new TroetListServiceImpl();
	Gson gson;

	{
		gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter()).registerTypeAdapter(MastodonStatus.class, new StatusSerializer())
				.create();
	}

	@Counted(value = "metrics.gethome", description = "Number of calls to /gethome endpoint")
	@RequestMapping(value = "/gethome", method = GET)
	public ResponseEntity<JsonNode> getHome() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String homeJson = service.listTroets();
			JsonNode entireMastodonStatuses = objectMapper.readTree(homeJson);
			return  new ResponseEntity<>(entireMastodonStatuses, HttpStatus.OK);
		}
		catch (JsonProcessingException ex){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}