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

@RestController
@RequestMapping("api/home/metrics")
public class MetricsController {

	//private final ApiMetricsService apiMetricsService;
	private final RebloggedMetricsService rebloggedMetricsService;
	private final TroetMetricsService troetMetricsService;

	private final TroetersMetricsService troetersMetricsService;

	private final HomeMetricsService homeMetricsService;
	private final Gson gson;
	private final MeterRegistry meter;

	@Autowired
	public MetricsController(MeterRegistry meter){
		//this.apiMetricsService = new ApiMetricsService(meter);
		this.rebloggedMetricsService = new RebloggedMetricsService(meter);
		this.troetMetricsService = new TroetMetricsService(meter);
		this.homeMetricsService = new HomeMetricsService(meter);
		this.troetersMetricsService = new TroetersMetricsService(meter);

		this.gson = new Gson();
		this.meter = meter;
	}

	@GetMapping
	public String getMetrics(){

		List<MetricsResponse> result = new LinkedList<>();
		//result.add(apiMetricsService.getCounterValue());
		result.add(troetMetricsService.getCounterValue());
		result.add(rebloggedMetricsService.getCounterValue());
		result.add(homeMetricsService.getCounterValue());
		result.add(troetersMetricsService.getCounterValue());

		return gson.toJson(result);
	}

}
