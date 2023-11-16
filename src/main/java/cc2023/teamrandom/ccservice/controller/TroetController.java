package cc2023.teamrandom.ccservice.controller;

import cc2023.teamrandom.ccservice.interfaces.TroetListService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class TroetController {
    TroetListService service = new TroetListService();
    @RequestMapping(value = "/gethome", method = GET)
    public String getHome(){
        String response = service.getHome();

        return response;
    }
}
