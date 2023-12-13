package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.interfaces.GetAllTroetersService;
import cc2023.teamrandom.ccservice.interfaces.TroetListService;
import cc2023.teamrandom.ccservice.model.TroeterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class TroetController {

    private TroetListService troetListService;
    private GetAllTroetersService getAllTroetersService;

    @Autowired
    private Logger logger;

    public TroetController(TroetListService troetListService, GetAllTroetersService getAllTroetersService,  Logger logger){

        this.troetListService = troetListService;
        this.logger = logger;
        this.getAllTroetersService=getAllTroetersService;
    }

    @GetMapping("api/home/troets")
    public ResponseEntity<String> list(){
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @GetMapping("api/home/troeters")

    public List<TroeterDTO> getAllTroeters() {
        return getAllTroetersService.getAllTroeters();
    }
}
