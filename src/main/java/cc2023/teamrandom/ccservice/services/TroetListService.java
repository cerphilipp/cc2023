package cc2023.teamrandom.ccservice.services;

import io.micrometer.core.annotation.Counted;
import org.springframework.stereotype.Service;

// Das Interface wird als Spring Service markiert, um von Spring als Komponente erkannt und verwaltet zu werden.
@Service
public interface TroetListService {

	// Diese Methode listet Troets und wird mit dem @Counted-Annotation für Metriken versehen.
	@Counted(value = "metrics.apicalls", description = "Total Api Calls")
	String listTroets();
}
