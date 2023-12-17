package cc2023.teamrandom.ccservice.services;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RebloggedMetricsService implements MetricsService{
	private final Counter rebloggedCounter;

	@Autowired
	public RebloggedMetricsService(MeterRegistry meter){
		this.rebloggedCounter = meter.counter("metrics.troets.reblogged");

	}
	@Override
	public void handleAPIRequest() {
		rebloggedCounter.increment();
	}
}
