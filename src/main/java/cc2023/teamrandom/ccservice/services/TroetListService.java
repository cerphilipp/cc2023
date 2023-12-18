package cc2023.teamrandom.ccservice.services;

import io.micrometer.core.annotation.Counted;
import org.springframework.stereotype.Service;

@Service
public interface TroetListService {

	@Counted(value = "metrics.apicalls", description = "Total Api Calls")
	String listTroets();
}
