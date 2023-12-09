package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.interfaces.GetHomeService;
import cc2023.teamrandom.ccservice.interfaces.TroetListService;
import cc2023.teamrandom.ccservice.model.*;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.StringReader;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
public class TroetController {
    public GetHomeService service = new GetHomeService();
    private TroetListService troetListService;



    Gson gson;

    {
        gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter()).registerTypeAdapter(Status.class, new StatusSerializer())
                .create();;
    }

    @Autowired
    private Logger logger;

    public TroetController(TroetListService troetListService, Logger logger) {

        this.troetListService = troetListService;
        this.logger = logger;
    }


    @GetMapping("api/home/troets")
    public ResponseEntity<String> list() {

//    @GetMapping("api/home/troets")
//    public ResponseEntity<String> list(){
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
   }

    @RequestMapping(value = "/gethome", method = GET)
    public ResponseEntity<Status[]> getHome() {
        Status[] response = gson.fromJson(service.getHome(), Status[].class);
        return new ResponseEntity<>(response, HttpStatus.OK);


    }

    @RequestMapping("/api/home/troets")
    public ResponseEntity<Status[]> troets(@RequestParam(name = "limit", required = false) Integer limit,
                                           @RequestParam(name = "offset", required = false) Integer offset,
                                           @RequestParam(name = "troeter", required = false) String troeter) {
        Status[] entireStatus = getHome().getBody();
        if(entireStatus == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        try{
            int offsetcounter = 0;
            int limitcounter = 0;
            if(offset == null)offset = 0;
            if(limit == null)limit = 0;
//        request an Server
            ArrayList<Status> resultAsArrayList = new ArrayList<>();
            if(troeter==null) {
                for (Status status : entireStatus) {
                    if(offset != offsetcounter){
                        offsetcounter += 1;
                    }
                    else if(limit != limitcounter){
                        limitcounter += 1;
                        resultAsArrayList.add(status);
                    }
                    else if(limit == 0)
                    {
                        resultAsArrayList.add(status);
                    }
                }
            } else {
                for (Status status : entireStatus) {
                    if (status.getUsername().equals(troeter)) {
                        if (offset != offsetcounter) {
                            offsetcounter += 1;
                        } else if (limit != limitcounter) {
                            limitcounter += 1;
                            resultAsArrayList.add(status);
                        } else if (limit == 0) {
                            resultAsArrayList.add(status);
                        }
                    }
                }
            }
            Status[] result = new Status[resultAsArrayList.size()];
            result = resultAsArrayList.toArray(result);
            return new ResponseEntity<>(result, HttpStatus.OK);



        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
/*
    @RequestMapping(value = "/gethome", method = GET)
    public ResponseEntity<Status[]> getHome () {
        Status[] response = gson.fromJson(service.getHome(), Status[].class);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    */


    @RequestMapping(value = "/api/home/troets/reblogged")
    public ResponseEntity<String> reblogged(@RequestParam(name="troeter", required = false) String troeter){

        Status[] entireStatus = getHome().getBody();
        if(entireStatus == null) return new ResponseEntity<>("500", HttpStatus.INTERNAL_SERVER_ERROR);

        ArrayList<Status> resultAsArrayList = new ArrayList<>();
        if(troeter==null) {
            for (Status status : entireStatus) {

                if (status.isReblogged()) resultAsArrayList.add(status);
            }
        } else {
            for (Status status : entireStatus) {
                if (status.isReblogged() && status.getUsername().equals(troeter)) resultAsArrayList.add(status);
            }
        }
        Status[] result = new Status[resultAsArrayList.size()];
        result = resultAsArrayList.toArray(result);
        return new ResponseEntity<>(gson.toJson(result), HttpStatus.OK);
    }
}
