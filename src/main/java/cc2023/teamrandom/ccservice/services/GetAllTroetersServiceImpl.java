package cc2023.teamrandom.ccservice.services;

import cc2023.teamrandom.ccservice.interfaces.GetAllTroetersService;
import cc2023.teamrandom.ccservice.model.TroeterDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetAllTroetersServiceImpl implements GetAllTroetersService {

    @Override
    public List<TroeterDTO> getAllTroeters() {
        // Hier rufen Sie die Methode auf, um die Daten von der Mastodon-API abzurufen und in TroeterDTOs zu konvertieren
        // Zum Beispiel: List<TroeterDTO> troeters = mastodonApiService.fetchAndConvertTroeters();
        // return troeters;
        return null; // Hier sollte die tatsächliche Implementierung stehen
    }
}
