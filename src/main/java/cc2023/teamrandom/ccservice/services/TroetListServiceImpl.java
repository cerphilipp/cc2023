package cc2023.teamrandom.ccservice.services;

import io.micrometer.core.annotation.Counted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TroetListServiceImpl implements TroetListService {

    @Autowired
    private Logger logger;

    public TroetListServiceImpl(){

    }
    @Override
    public String listTroets(){

        try {
            RestTemplate restTemplate = new RestTemplate();
            String uri = "http://troetbot:80/home"; //Access Troetbot here, probably won't survive for long
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
