package cc2023.teamrandom.ccservice.services;

import io.micrometer.core.annotation.Counted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

// Die Implementierung des TroetListService-Interfaces.
@Service
public class TroetListServiceImpl implements TroetListService {

    // Logger für Protokollierungszwecke.
    @Autowired
    private Logger logger;

    // Standardkonstruktor.
    public TroetListServiceImpl() {
    }

    // Implementierung der Methode aus dem TroetListService-Interface.
    @Override
    public String listTroets() {

        try {
            // Erstellung eines RestTemplate für HTTP-Anfragen.
            RestTemplate restTemplate = new RestTemplate();
            String uri = "http://troetbot:80/home"; // Zugriff auf den Troetbot, wahrscheinlich wird dies nicht lange überleben
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>("", headers);

            // Ausführung der HTTP-Anfrage und Rückgabe der JSON-Antwort.
            ResponseEntity<?> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            return Objects.requireNonNull(result.getBody()).toString();
        } catch (Exception e) {
            e.printStackTrace(); // TODO: Ersetzen Sie dies durch Logger-Ausgaben
        }
        return null;
    }
}
