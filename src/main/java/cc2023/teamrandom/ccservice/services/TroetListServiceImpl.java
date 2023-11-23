package cc2023.teamrandom.ccservice.services;

import cc2023.teamrandom.ccservice.interfaces.TroetListService;
import cc2023.teamrandom.ccservice.model.TroetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TroetListServiceImpl implements TroetListService {

    @Autowired
    private Logger logger;

    public TroetListServiceImpl(Logger logger){
        this.logger = logger;
    }

    public List<TroetDto> listTroets(){

        return new ArrayList<>();
    }
}
