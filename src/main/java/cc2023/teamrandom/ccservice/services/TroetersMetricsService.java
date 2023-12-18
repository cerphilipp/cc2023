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

@Service
public class TroetersMetricsService implements MetricsService{
	public final Counter troetersCounter;

	Gson gson = new Gson();

	@Autowired
	public TroetersMetricsService(MeterRegistry meter){
		this.troetersCounter = meter.counter("metrics.troeters");
	}
	@Override
	public MetricsResponse getCounterValue() {
		String jsonResponse = "{...}"; // Replace with your actual JSON response

		try {
			RestTemplate restTemplate = new RestTemplate();
			String uri = "http://localhost:9000/actuator/metrics/metrics.troeters"; //Access Troetbot here, probably won't survive for long
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<String> entity = new HttpEntity<>("", headers);
			ResponseEntity<?> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
			jsonResponse = Objects.requireNonNull(result.getBody()).toString();
		} catch (Exception e) {
			e.printStackTrace(); //TODO Replace with logger
			jsonResponse = "{\"Error\":\"Error while trying to access metrics route\"}";
		}


		return gson.fromJson(jsonResponse, MetricsResponse.class);
	}
}