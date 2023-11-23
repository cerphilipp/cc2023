package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.interfaces.TroetListService;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import social.bigbone.api.entity.Announcement.Status;

import java.io.StringReader;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class TroetController {
    TroetListService service = new TroetListService();
    Gson gson = new Gson();
    @RequestMapping(value = "/gethome", method = GET)
    public Status[] getHome(){
        String response = service.getHome();
        JsonReader jsonReader = new JsonReader(new StringReader(response));
        jsonReader.setLenient(true);
        Status[] status = gson.fromJson(jsonReader, Status[].class);
        System.out.println(status.length);
        System.out.println(status[0]);
        return status;
    }
}
