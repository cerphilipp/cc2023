package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.model.MastodonStatus;
import cc2023.teamrandom.ccservice.model.gson.StatusSerializer;
import cc2023.teamrandom.ccservice.model.gson.ZonedDateTimeTypeAdapter;
import cc2023.teamrandom.ccservice.services.TroetListService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cc2023.teamrandom.ccservice.services.TroetListServiceImpl;

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

	@RequestMapping(value = "/gethome", method = GET)
	public ResponseEntity<MastodonStatus[]> getHome() {
		MastodonStatus[] response = gson.fromJson(service.listTroets(), MastodonStatus[].class);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}