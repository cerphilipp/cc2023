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

// Die Klasse wird als Spring RestController markiert, um von Spring als RESTful Controller erkannt und verwaltet zu werden.
@RestController
public class HomeController {

	// Instanzvariable für den TroetListService, der für den Zugriff auf Troets verwendet wird.
	public TroetListService service = new TroetListServiceImpl();

	// Gson-Objekt für die JSON-Verarbeitung.
	Gson gson;

	// Initialisierungsblock für die Konfiguration des Gson-Objekts mit speziellen Serialisierungs-/Deserialisierungs-Adapters.
	{
		gson = new GsonBuilder()
				.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter())
				.registerTypeAdapter(MastodonStatus.class, new StatusSerializer())
				.create();
	}

	// Die Methode wird durch die @Counted-Annotation für Metriken markiert und mappt den "/gethome"-Endpunkt für HTTP-GET-Anfragen.
	@Counted(value = "metrics.gethome", description = "Number of calls to /gethome endpoint")
	@RequestMapping(value = "/gethome", method = GET)
	public ResponseEntity<JsonNode> getHome() {
		try {
			// Erstellung eines ObjectMapper für die JSON-Verarbeitung.
			ObjectMapper objectMapper = new ObjectMapper();

			// Aufruf der listTroets-Methode des TroetListService für die JSON-Daten.
			String homeJson = service.listTroets();

			// Deserialisierung der JSON-Daten in ein JsonNode-Objekt.
			JsonNode entireMastodonStatuses = objectMapper.readTree(homeJson);

			// Rückgabe der JSON-Daten als ResponseEntity mit HTTP-Status OK.
			return new ResponseEntity<>(entireMastodonStatuses, HttpStatus.OK);
		} catch (JsonProcessingException ex) {
			// Behandlung von Fehlern bei der JSON-Verarbeitung und Rückgabe eines Internal Server Error.
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
