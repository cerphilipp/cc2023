package cc2023.teamrandom.ccservice.services;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TroetMetricsService implements MetricsService{
	public final Counter troetsCounter;

	@Autowired
	public TroetMetricsService(MeterRegistry meter){
		this.troetsCounter = meter.counter("metrics.troets");
	}
	@Override
	public void handleAPIRequest() {

	}
}
