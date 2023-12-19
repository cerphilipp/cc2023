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

// Die Klasse wird als Spring RestController markiert.
@RestController
public class TroeterController {

	// Instanzvariable für TroetListService, die für den Zugriff auf Troets verwendet wird.
	private final TroetListService troetListService = new TroetListServiceImpl();

	// Instanzvariable für Gson, die für die JSON-Verarbeitung verwendet wird.
	Gson gson;

	// Initialisierungsblock für die Gson-Instanz mit benutzerdefinierten Typenadaptern.
	{
		gson = new GsonBuilder()
				.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter())
				.registerTypeAdapter(MastodonStatus.class, new StatusSerializer())
				.create();
	}

	// Die Methode wird durch die @Counted- und @RequestMapping-Annotationen für HTTP-GET-Anfragen auf den spezifischen Pfad gemappt.
	@Counted(value = "metrics.troeters", description = "Number of calls to the metrics/troeters endpoint")
	@RequestMapping(value = "/api/home/troeters", produces = { "application/json" })
	public ResponseEntity<JsonNode> troeters() {
		try {
			// ObjectMapper für die JSON-Verarbeitung erstellen.
			ObjectMapper objectMapper = new ObjectMapper();

			// TroetListService verwenden, um JSON-Daten zu erhalten.
			String homeJson = troetListService.listTroets();

			// Eine Menge für eindeutige Benutzerkonten erstellen.
			Set<String> uniqueAccounts = new HashSet<>();

			// JSON-Daten in ein JsonNode-Objekt deserialisieren.
			JsonNode entireMastodonStatuses = objectMapper.readTree(homeJson);

			// Überprüfen, ob die JSON-Daten ein gültiges Array sind.
			if (entireMastodonStatuses == null || !entireMastodonStatuses.isArray()) {
				// Wenn nicht, wird ein ResponseEntity mit dem HTTP-Status NOT_FOUND zurückgegeben.
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}

			// Ein ArrayNode für das Ergebnis erstellen.
			ArrayNode resultArray = objectMapper.createArrayNode();

			// Iteration über die Mastodon-Status-Nodes.
			for (JsonNode mastodonStatus : entireMastodonStatuses) {
				// Überprüfen, ob der Status ein Konto hat.
				JsonNode account = mastodonStatus.has("account") ? mastodonStatus.get("account") : null;

				// Wenn kein Konto vorhanden ist, wird zur nächsten Iteration fortgefahren.
				if (account == null) continue;

				// Die ID des Kontos extrahieren.
				String id = account.has("id") ? account.get("id").asText() : null;

				// Überprüfen, ob die ID nicht null ist und das Konto eindeutig ist.
				if (id != null && !uniqueAccounts.contains(id)) {
					// Wenn ja, wird das Konto zum Ergebnis hinzugefügt und die ID zur eindeutigen Menge hinzugefügt.
					uniqueAccounts.add(id);
					resultArray.add(account);
				}
			}

			// Das Ergebnis-ArrayNode als ResponseEntity mit HTTP-Status OK zurückgeben.
			return new ResponseEntity<>(resultArray, HttpStatus.OK);
		} catch (JsonProcessingException e) {
			// Behandlung von Fehlern bei der JSON-Verarbeitung und Rückgabe eines Internal Server Error.
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

