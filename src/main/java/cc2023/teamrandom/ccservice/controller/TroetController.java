package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.model.MastodonStatus;
import cc2023.teamrandom.ccservice.model.*;
import cc2023.teamrandom.ccservice.model.gson.StatusSerializer;
import cc2023.teamrandom.ccservice.model.gson.ZonedDateTimeTypeAdapter;
import cc2023.teamrandom.ccservice.services.TroetListService;
import cc2023.teamrandom.ccservice.services.TroetListServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.micrometer.core.annotation.Counted;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import java.util.logging.Logger;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;


import static org.springframework.web.bind.annotation.RequestMethod.GET;


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
    public ResponseEntity<JsonNode> troets(@RequestParam(name = "limit", required = false) Integer limit,
                                                   @RequestParam(name = "offset", required = false) Integer offset,
                                                   @RequestParam(name = "troeter", required = false) String troeter) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode entireMastodonStatuses = objectMapper.readTree(service.listTroets());
            if (entireMastodonStatuses == null || !entireMastodonStatuses.isArray()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            int offsetcounter = 0;
            int limitcounter = 0;
            if (offset == null) offset = 0;
            if (limit == null) limit = 0;

            ArrayNode resultArray = objectMapper.createArrayNode();

            for (JsonNode mastodonStatus : entireMastodonStatuses) {
                if (troeter == null || mastodonStatus.has("username") && mastodonStatus.get("username").asText().equals(troeter)) {
                    if (offset != offsetcounter) {
                        offsetcounter += 1;
                    } else if (limit != limitcounter) {
                        limitcounter += 1;
                        resultArray.add(mastodonStatus);
                    } else if (limit == 0) {
                        resultArray.add(mastodonStatus);
                    }
                }
            }

            return new ResponseEntity<>(resultArray, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
  
    @Counted(value = "metrics.troets.reblogged", description = "Number of calls to the metrics/troets/reblogged endpoint")
    @RequestMapping(value = "/api/home/troets/reblogged", produces = { "application/json" })
    public ResponseEntity<JsonNode> reblogged(@RequestParam(name="troeter", required = false) String troeter){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String homeJson = service.listTroets();

            JsonNode entireMastodonStatuses = objectMapper.readTree(homeJson);

            if (entireMastodonStatuses == null || !entireMastodonStatuses.isArray()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            ArrayNode resultArray = objectMapper.createArrayNode();

            for (JsonNode mastodonStatus : entireMastodonStatuses) {
                JsonNode reblog = mastodonStatus.has("reblog") ? mastodonStatus.get("reblog") : null;
                boolean isRebloggedInner = reblog != null && reblog.has("reblogged") && reblog.get("reblogged").asBoolean();
                boolean isReblogged = isRebloggedInner || mastodonStatus.has("reblogged") && mastodonStatus.get("reblogged").asBoolean();


                String username = mastodonStatus.has("username") ? mastodonStatus.get("username").asText() : null;


                if (troeter != null && isReblogged && troeter.equals(username)) {
                    resultArray.add(mastodonStatus);
                } else if (troeter == null && isReblogged) {
                    resultArray.add(mastodonStatus);
                }
            }

            return new ResponseEntity<>(resultArray, HttpStatus.OK);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

