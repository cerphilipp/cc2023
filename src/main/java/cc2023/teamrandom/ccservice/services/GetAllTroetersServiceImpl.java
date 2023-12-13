package cc2023.teamrandom.ccservice.services;

import cc2023.teamrandom.ccservice.interfaces.GetAllTroetersService;
import cc2023.teamrandom.ccservice.model.TroeterDTO;
import java.util.Collections;

import java.util.List;

public class GetAllTroetersServiceImpl implements GetAllTroetersService {

    @Override
    public List<TroeterDTO> getAllTroeters() {
        // Hier rufen Sie die Methode auf, um die Daten von der Mastodon-API abzurufen und in TroeterDTOs zu konvertieren
        return Collections.emptyList(); // todo: Hier sollte die tatsächliche Implementierung stehen
    }
}
