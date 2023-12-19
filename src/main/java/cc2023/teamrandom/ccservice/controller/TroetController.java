package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.model.MastodonStatus;
import cc2023.teamrandom.ccservice.model.*;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import java.util.logging.Logger;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;


import static org.springframework.web.bind.annotation.RequestMethod.GET;


// Die Klasse wird als Spring RestController markiert, um von Spring als RESTful Controller erkannt und verwaltet zu werden.
@RestController
public class TroetController {

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

    // Standardkonstruktor.
    public TroetController() {
    }

    // Die Methode wird durch die @Counted-Annotation für Metriken markiert und mappt den "/api/home/troets"-Endpunkt für HTTP-GET-Anfragen.
    @Counted(value = "metrics.troets", description = "Number of calls to metrics/troets endpoint")
    @RequestMapping(value = "/api/home/troets", produces = { "application/json" })
    public ResponseEntity<JsonNode> troets(
            @RequestParam(name = "limit", required = false) Integer limit,
            @RequestParam(name = "offset", required = false) Integer offset,
            @RequestParam(name = "troeter", required = false) String troeter
    ) {
        try {
            // Erstellung eines ObjectMapper für die JSON-Verarbeitung.
            ObjectMapper objectMapper = new ObjectMapper();

            // Aufruf der listTroets-Methode des TroetListService für die JSON-Daten.
            String homeJson = service.listTroets();

            // Deserialisierung der JSON-Daten in ein JsonNode-Objekt.
            JsonNode entireMastodonStatuses = objectMapper.readTree(homeJson);

            // Überprüfung, ob die JSON-Daten ein gültiges Array sind.
            if (entireMastodonStatuses == null || !entireMastodonStatuses.isArray()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            // Initialisierung von Zählern für Offset und Limit.
            int offsetcounter = 0;
            int limitcounter = 0;

            // Standardwerte für Offset und Limit, wenn sie nicht angegeben sind.
            if (offset == null) offset = 0;
            if (limit == null) limit = 0;

            // Erstellung eines ArrayNode für das Ergebnis.
            ArrayNode resultArray = objectMapper.createArrayNode();

            // Iteration über die Mastodon-Status-Nodes.
            for (JsonNode mastodonStatus : entireMastodonStatuses) {
                // Überprüfung, ob ein Troeter angegeben ist oder der Troet von einem bestimmten Troeter stammt.
                if (troeter == null || mastodonStatus.has("username") && mastodonStatus.get("username").asText().equals(troeter)) {
                    // Überprüfung von Offset und Limit.
                    if (offset != offsetcounter) {
                        offsetcounter += 1;
                    } else if (limit != limitcounter) {
                        limitcounter += 1;
                        resultArray.add(mastodonStatus);
                    } else if (limit == 0) {
                        resultArray.add(mastodonStatus);
                    }
                }
            }

            // Rückgabe des Ergebnis-ArrayNodes als ResponseEntity mit HTTP-Status OK.
            return new ResponseEntity<>(resultArray, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            // Behandlung von Fehlern bei der JSON-Verarbeitung und Rückgabe eines Internal Server Error.
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Die Methode wird durch die @Counted-Annotation für Metriken markiert und mappt den "/api/home/troets/reblogged"-Endpunkt für HTTP-GET-Anfragen.
    @Counted(value = "metrics.troets.reblogged", description = "Number of calls to the metrics/troets/reblogged endpoint")
    @RequestMapping(value = "/api/home/troets/reblogged", produces = { "application/json" })
    public ResponseEntity<JsonNode> reblogged(@RequestParam(name="troeter", required = false) String troeter) {
        try {
            // Erstellung eines ObjectMapper für die JSON-Verarbeitung.
            ObjectMapper objectMapper = new ObjectMapper();

            // Aufruf der listTroets-Methode des TroetListService für die JSON-Daten.
            String homeJson = service.listTroets();

            // Deserialisierung der JSON-Daten in ein JsonNode-Objekt.
            JsonNode entireMastodonStatuses = objectMapper.readTree(homeJson);

            // Überprüfung, ob die JSON-Daten ein gültiges Array sind.
            if (entireMastodonStatuses == null || !entireMastodonStatuses.isArray()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            // Erstellung eines ArrayNode für das Ergebnis.
            ArrayNode resultArray = objectMapper.createArrayNode();

            // Iteration über die Mastodon-Status-Nodes.
            for (JsonNode mastodonStatus : entireMastodonStatuses) {
                // Überprüfung, ob der Troet ein Reblog ist.
                JsonNode reblog = mastodonStatus.has("reblog") ? mastodonStatus.get("reblog") : null;
                boolean isRebloggedInner = reblog != null && reblog.has("reblogged") && reblog.get("reblogged").asBoolean();
                boolean isReblogged = isRebloggedInner || mastodonStatus.has("reblogged") && mastodonStatus.get("reblogged").asBoolean();

                // Extrahierung des Benutzernamens des Troeters.
                String username = mastodonStatus.has("username") ? mastodonStatus.get("username").asText() : null;

                // Überprüfung, ob ein Troeter angegeben ist und der Troet von diesem Troeter stammt.
                if (troeter != null && isReblogged && troeter.equals(username)) {
                    resultArray.add(mastodonStatus);
                } else if (troeter == null && isReblogged) {
                    resultArray.add(mastodonStatus);
                }
            }

            // Rückgabe des Ergebnis-ArrayNodes als ResponseEntity mit HTTP-Status OK.
            return new ResponseEntity<>(resultArray, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            // Behandlung von Fehlern bei der JSON-Verarbeitung und Rückgabe eines Internal Server Error.
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

