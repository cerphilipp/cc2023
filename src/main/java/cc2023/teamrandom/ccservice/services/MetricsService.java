package cc2023.teamrandom.ccservice.services;

import cc2023.teamrandom.ccservice.model.MetricsResponse;
import org.springframework.stereotype.Service;

@Service
public interface MetricsService {
	MetricsResponse getCounterValue();
}
