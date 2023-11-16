package cc2023.teamrandom.ccservice.services;

import cc2023.teamrandom.ccservice.model.TroetDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class TroetListServiceImplTest {

    @Test
    void listTroets() {

        TroetListServiceImpl service = new TroetListServiceImpl(Logger.getAnonymousLogger());
        List<TroetDto> list = service.listTroets();
        assertTrue(list.isEmpty());
    }
}