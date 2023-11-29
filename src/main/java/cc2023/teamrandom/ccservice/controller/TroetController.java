package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.interfaces.GetHomeService;
import cc2023.teamrandom.ccservice.interfaces.TroetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.web.bind.annotation.RequestMapping;
import social.bigbone.api.entity.Announcement.Status;

import java.io.StringReader;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
@RestController
public class TroetController {

    private TroetListService troetListService;

    public GetHomeService service = new GetHomeService();

    Gson gson = new Gson();

    @Autowired
    private Logger logger;

    public TroetController(TroetListService troetListService, Logger logger) {

        this.troetListService = troetListService;
        this.logger = logger;
    }

    @GetMapping("api/home/troets")
    public ResponseEntity<String> list() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @RequestMapping(value = "/gethome", method = GET)
    public ResponseEntity<String> getHome () {
        String response = service.getHome();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @RequestMapping(value = "/api/home/troets/reblogged")
    public ResponseEntity<String> reblogged(@RequestParam(name="troeter") String troeter){
        String entireStatus = getHome().getBody();
        return new ResponseEntity<>("just4test: "+troeter, HttpStatus.OK);
    }
}
