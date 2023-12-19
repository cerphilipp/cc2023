package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.model.MetricsResponse;
import cc2023.teamrandom.ccservice.services.*;
import com.google.gson.Gson;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

// Die Klasse wird als Spring RestController markiert und hat eine Basis-RequestMapping für alle Endpunkte dieser Klasse.
@RestController
@RequestMapping("api/home/metrics")
public class MetricsController {

	// Instanzvariablen für verschiedene MetricsService-Implementierungen, Gson und MeterRegistry.
	private final RebloggedMetricsService rebloggedMetricsService;
	private final TroetMetricsService troetMetricsService;
	private final TroetersMetricsService troetersMetricsService;
	private final HomeMetricsService homeMetricsService;
	private final Gson gson;
	private final MeterRegistry meter;

	// Konstruktor, der durch Spring Dependency Injection mit einem MeterRegistry-Objekt aufgerufen wird.
	@Autowired
	public MetricsController(MeterRegistry meter) {
		// Initialisierung der verschiedenen MetricsService-Implementierungen mit dem übergebenen MeterRegistry.
		this.rebloggedMetricsService = new RebloggedMetricsService(meter);
		this.troetMetricsService = new TroetMetricsService(meter);
		this.homeMetricsService = new HomeMetricsService(meter);
		this.troetersMetricsService = new TroetersMetricsService(meter);

		// Initialisierung von Gson für die JSON-Verarbeitung und Speichern des MeterRegistry-Objekts.
		this.gson = new Gson();
		this.meter = meter;
	}

	// Die Methode wird durch die @GetMapping-Annotation für HTTP-GET-Anfragen auf den Basispfad dieser Klasse gemappt.
	@GetMapping
	public String getMetrics() {
		// Eine Liste, um MetricsResponse-Objekte für jede MetricsService-Implementierung zu speichern.
		List<MetricsResponse> result = new LinkedList<>();

		// Aufruf der getCounterValue-Methode für jede MetricsService-Implementierung und Hinzufügen zum Ergebnis.
		result.add(troetMetricsService.getCounterValue());
		result.add(rebloggedMetricsService.getCounterValue());
		result.add(homeMetricsService.getCounterValue());
		result.add(troetersMetricsService.getCounterValue());

		// Die Liste von MetricsResponse-Objekten wird in JSON-Format konvertiert und als String zurückgegeben.
		return gson.toJson(result);
	}
}

