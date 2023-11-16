package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.interfaces.TroetListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Logger;

@RestController
public class TroetController {

    private TroetListService troetListService;
    @Autowired
    private Logger logger;

    public TroetController(TroetListService troetListService, Logger logger){

        this.troetListService = troetListService;
        this.logger = logger;
    }

    @GetMapping("api/home/troets")
    public ResponseEntity<String> list(){
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }
}
