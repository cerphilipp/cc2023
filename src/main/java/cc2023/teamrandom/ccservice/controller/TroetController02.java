package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.interfaces.GetHomeService;
import cc2023.teamrandom.ccservice.model.MastodonStatus;
import cc2023.teamrandom.ccservice.model.gson.StatusSerializer;
import cc2023.teamrandom.ccservice.model.gson.ZonedDateTimeTypeAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.ArrayList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@JsonFormat(shape = JsonFormat.Shape.STRING)
public class TroetController02 {

    public GetHomeService service = new GetHomeService();



    Gson gson;

    {
        gson = new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter()).registerTypeAdapter(MastodonStatus.class, new StatusSerializer())
                .create();;
    }
    public ResponseEntity<MastodonStatus[]> getHome () {
        //getHomeAccessCounter.increment();
        MastodonStatus[] response = gson.fromJson(service.getHome(), MastodonStatus[].class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/home/troets", produces = { "application/json" })
    public ResponseEntity<MastodonStatus[]> troets(@RequestParam(name = "limit", required = false) Integer limit,
                                              @RequestParam(name = "offset", required = false) Integer offset,
                                              @RequestParam(name = "troeter", required = false) String troeter) {
        //troetsrouteAccessCounter.increment();
        MastodonStatus[] entireMastodonStatuses = getHome().getBody();
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
}
