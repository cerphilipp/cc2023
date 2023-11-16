package cc2023.teamrandom.ccservice.interfaces;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


public class TroetListService {

    public synchronized String getHome(){
        try{
            RestTemplate restTemplate = new RestTemplate();
            String uri = "http://localhost:8080/home";
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>("", headers);
            ResponseEntity<?> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            System.out.println(result.getBody());
            return result.getBody().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
