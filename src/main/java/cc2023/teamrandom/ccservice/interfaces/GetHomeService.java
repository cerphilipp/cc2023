package cc2023.teamrandom.ccservice.interfaces;


import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;



public class GetHomeService implements TroetListService{

    public String getHome() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String uri = "http://localhost:8080/home"; //Access Troetbot here, probably won't survive for long
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            ResponseEntity<?> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            return Objects.requireNonNull(result.getBody()).toString();
        } catch (Exception e) {
            e.printStackTrace(); //TODO Replace with logger
        }
        return null;
    }
}
