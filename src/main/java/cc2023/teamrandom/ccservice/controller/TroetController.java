package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.services.TroetListService;
import cc2023.teamrandom.ccservice.services.TroetListServiceImpl;

import cc2023.teamrandom.ccservice.model.*;
import cc2023.teamrandom.ccservice.model.gson.StatusSerializer;
import cc2023.teamrandom.ccservice.model.gson.ZonedDateTimeTypeAdapter;
import com.google.gson.GsonBuilder;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import java.util.logging.Logger;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class TroetController {
    public TroetListService service = new TroetListServiceImpl();

	Gson gson;

    {
        gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter()).registerTypeAdapter(MastodonStatus.class, new StatusSerializer())
                .create();;
    }

    public TroetController() {
    }

    @Counted(value = "metrics.troets", description = "Number of calls to metrics/troets endpoint")
    @RequestMapping(value = "/api/home/troets", produces = { "application/json" })
    public ResponseEntity<MastodonStatus[]> troets(@RequestParam(name = "limit", required = false) Integer limit,
                                                   @RequestParam(name = "offset", required = false) Integer offset,
                                                   @RequestParam(name = "troeter", required = false) String troeter) {
        //troetsrouteAccessCounter.increment();
        MastodonStatus[] entireMastodonStatuses = gson.fromJson(service.listTroets(), MastodonStatus[].class);
        if(entireMastodonStatuses == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        int offsetcounter = 0;
        int limitcounter = 0;
        if(offset == null)offset = 0;
        if(limit == null)limit = 0;
//        request an Server
        ArrayList<MastodonStatus> resultAsArrayList = new ArrayList<>();
        if(troeter==null) {
            for (MastodonStatus mastodonStatus : entireMastodonStatuses) {
                if(offset != offsetcounter){
                    offsetcounter += 1;
                }
                else if(limit != limitcounter){
                    limitcounter += 1;
                    resultAsArrayList.add(mastodonStatus);
                }
                else if(limit == 0)
                {
                    resultAsArrayList.add(mastodonStatus);
                }
            }
        } else {
            for (MastodonStatus mastodonStatus : entireMastodonStatuses) {
                if (mastodonStatus.getUsername().equals(troeter)) {
                    if (offset != offsetcounter) {
                        offsetcounter += 1;
                    } else if (limit != limitcounter) {
                        limitcounter += 1;
                        resultAsArrayList.add(mastodonStatus);
                    } else if (limit == 0) {
                        resultAsArrayList.add(mastodonStatus);
                    }
                }
            }
        }
        MastodonStatus[] result = new MastodonStatus[resultAsArrayList.size()];
        result = resultAsArrayList.toArray(result);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @Counted(value = "metrics.troets.reblogged", description = "Number of calls to the metrics/troets/reblogged endpoint")
    @RequestMapping(value = "/api/home/troets/reblogged")
    public ResponseEntity<String> reblogged(@RequestParam(name="troeter", required = false) String troeter){
        MastodonStatus[] entireMastodonStatuses = gson.fromJson(service.listTroets(), MastodonStatus[].class);

        if(entireMastodonStatuses == null) return new ResponseEntity<>("404", HttpStatus.NOT_FOUND);

        ArrayList<MastodonStatus> resultAsArrayList = new ArrayList<>();
        if(troeter != null) {
            for (MastodonStatus mastodonStatus : entireMastodonStatuses) {
                if (mastodonStatus.isReblogged() && mastodonStatus.getUsername().equals(troeter)) {
                    resultAsArrayList.add(mastodonStatus);
                }
            }
        } else {
            for (MastodonStatus mastodonStatus : entireMastodonStatuses) {
                if (mastodonStatus.isReblogged()) {
                    resultAsArrayList.add(mastodonStatus);
                }
            }
        }
        MastodonStatus[] result = new MastodonStatus[resultAsArrayList.size()];
        result = resultAsArrayList.toArray(result);
        return new ResponseEntity<>(gson.toJson(result), HttpStatus.OK);
    }
}
