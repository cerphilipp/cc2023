package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.interfaces.GetHomeService;
import cc2023.teamrandom.ccservice.interfaces.TroetListService;

import cc2023.teamrandom.ccservice.model.*;
import cc2023.teamrandom.ccservice.model.gson.StatusSerializer;
import cc2023.teamrandom.ccservice.model.gson.ZonedDateTimeTypeAdapter;
import com.google.gson.GsonBuilder;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;


import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class TroetController {
    public GetHomeService service = new GetHomeService();


	Gson gson;

    {
        gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter()).registerTypeAdapter(MastodonStatus.class, new StatusSerializer())
                .create();;
    }

    private Logger logger;
    private final Counter troetsrouteAccessCounter;
    private final Counter rebloggedAccesCounter;
    private final Counter getHomeAccessCounter;
    @Autowired
    public TroetController(MeterRegistry meterRegistry, Logger logger) {
        this.troetsrouteAccessCounter = Counter.builder("custom.route.troetaccess")
                .description("Counts the number of times a route is accessed")
                .register(meterRegistry);
        this.rebloggedAccesCounter = Counter.builder("custom.route.rebloggedaccess")
                .description("Counts the number of times a route is accessed")
                .register(meterRegistry);
        this.getHomeAccessCounter = Counter.builder("custom.route.gethomeaccess")
                .description("Counts the number of times a route is accessed")
                .register(meterRegistry);
		this.logger = logger;
    }


    @RequestMapping("/api/home/troets")
    public ResponseEntity<String> troets(@RequestParam(name = "limit", required = false) Integer limit,
                                         @RequestParam(name = "offset", required = false) Integer offset,
                                         @RequestParam(name = "troeter", required = false) String troeter) {
        troetsrouteAccessCounter.increment();
        MastodonStatus[] entireMastodonStatuses = getHome().getBody();
        if(entireMastodonStatuses == null) return new ResponseEntity<>("500", HttpStatus.INTERNAL_SERVER_ERROR);

        try{
//        request an Server


        return new ResponseEntity<>("Hello World", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("505", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/gethome", method = GET)
    public ResponseEntity<MastodonStatus[]> getHome () {
        getHomeAccessCounter.increment();
        MastodonStatus[] response = gson.fromJson(service.getHome(), MastodonStatus[].class);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @RequestMapping(value = "/api/home/troets/reblogged")
    public ResponseEntity<String> reblogged(@RequestParam(name="troeter", required = false) String troeter){
        rebloggedAccesCounter.increment();
        MastodonStatus[] entireMastodonStatuses = getHome().getBody();
        if(entireMastodonStatuses == null) return new ResponseEntity<>("404", HttpStatus.NOT_FOUND);
        ArrayList<MastodonStatus> resultAsArrayList = new ArrayList<>();

            for (MastodonStatus mastodonStatus : entireMastodonStatuses) {
                if (mastodonStatus.isReblogged()) resultAsArrayList.add(mastodonStatus);
            }
        MastodonStatus[] result = new MastodonStatus[resultAsArrayList.size()];
        result = resultAsArrayList.toArray(result);
        return new ResponseEntity<>(gson.toJson(result), HttpStatus.OK);
    }

    @RequestMapping(value="/metrics")
    public ResponseEntity<String> metrics(){
        String json = gson.toJson(
                new MetricsResponse(
                        getHomeAccessCounter.count(),
                        rebloggedAccesCounter.count(),
                        troetsrouteAccessCounter.count()
                ));
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
}
