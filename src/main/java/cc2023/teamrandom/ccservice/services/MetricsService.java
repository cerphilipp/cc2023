package cc2023.teamrandom.ccservice.services;

import cc2023.teamrandom.ccservice.model.MetricsResponse;
import org.springframework.stereotype.Service;

// Das Interface wird als Spring Service markiert, um von Spring als Komponente erkannt und verwaltet zu werden.
@Service
public interface MetricsService {

	// Diese Methode gibt eine Metrikenantwort zurück.
	MetricsResponse getCounterValue();
}