package cc2023.teamrandom.ccservice.services;

import cc2023.teamrandom.ccservice.model.MetricsResponse;
import com.google.gson.Gson;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

// Die Klasse wird als Spring Service markiert, um von Spring als Komponente erkannt und verwaltet zu werden.
@Service
public class ApiMetricsService implements MetricsService {

	// Zähler für die Metrik "metrics.apicalls".
	public final Counter apiCallCounter;

	// Gson-Objekt für die JSON-Verarbeitung.
	Gson gson = new Gson();

	// Konstruktor, der den Zähler initialisiert und einen MeterRegistry injiziert.
	@Autowired
	public ApiMetricsService(MeterRegistry meter) {
		this.apiCallCounter = meter.counter("metrics.apicalls");
	}

	// Implementierung der Methode aus dem MetricsService-Interface.
	@Override
	public MetricsResponse getCounterValue() {
		String jsonResponse = "{...}"; // Ersetzen Sie dies durch Ihre tatsächliche JSON-Antwort

		try {
			// Erstellung eines RestTemplate für HTTP-Anfragen.
			RestTemplate restTemplate = new RestTemplate();
			String uri = "http://localhost:9000/actuator/metrics/metrics.apicalls";
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<>("", headers);

			// Ausführung der HTTP-Anfrage und Extrahieren der JSON-Antwort.
			ResponseEntity<?> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
			jsonResponse = Objects.requireNonNull(result.getBody()).toString();
		} catch (Exception e) {
			e.printStackTrace(); // TODO: Ersetzen Sie dies durch Logger-Ausgaben
			jsonResponse = "{\"Error\":\"Dunno\"}";
		}

		// Deserialisierung der JSON-Antwort in ein MetricsResponse-Objekt und Rückgabe.
		return gson.fromJson(jsonResponse, MetricsResponse.class);
	}
}