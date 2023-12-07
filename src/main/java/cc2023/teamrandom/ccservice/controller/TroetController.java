package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.interfaces.GetHomeService;
import cc2023.teamrandom.ccservice.interfaces.TroetListService;
import ch.qos.logback.core.status.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class TroetController {
    public GetHomeService service = new GetHomeService();
    private TroetListService troetListService;
    @Autowired
    private Logger logger;

    public TroetController(TroetListService troetListService, Logger logger){

        this.troetListService = troetListService;
        this.logger = logger;
    }

//    @GetMapping("api/home/troets")
//    public ResponseEntity<String> list(){
//        return new ResponseEntity<>("Hello World", HttpStatus.OK);
//    }

    @RequestMapping(value = "/gethome", method = GET)
    public ResponseEntity<Status[]> getHome () {
        Status[] response = new Status[0];
        try {
            response = new ObjectMapper().reader().forType(Status[].class)
                    .readValue(service.getHome());;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @RequestMapping("/api/home/troets")
    public ResponseEntity<String> troets(@RequestParam(name = "limit", required = false) Integer limit,
                                         @RequestParam(name = "offset", required = false) Integer offset,
                                         @RequestParam(name = "troeter", required = false) String troeter) {
        Status[] entireStatus = getHome().getBody();
        if(entireStatus == null) return new ResponseEntity<>("500", HttpStatus.INTERNAL_SERVER_ERROR);

        try{
//        request an Server

        return new ResponseEntity<>("Hello World", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("505", HttpStatus.BAD_REQUEST);
        }
    }
}
