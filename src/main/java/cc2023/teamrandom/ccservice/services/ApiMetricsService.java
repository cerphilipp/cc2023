package cc2023.teamrandom.ccservice.services;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class ApiMetricsService implements MetricsService {
	public final Counter apiCallCounter;

	public ApiMetricsService(MeterRegistry meter){
		this.apiCallCounter = meter.counter("metrics.apicalls");
	}
	@Override
	public void handleAPIRequest() {
		this.apiCallCounter.increment();
	}
}
