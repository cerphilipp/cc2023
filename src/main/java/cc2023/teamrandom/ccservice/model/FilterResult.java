package cc2023.teamrandom.ccservice.model;

import java.util.List;

public record FilterResult(Filter filter,
                           @Optional List<String> keyword_matches,
                           @Optional String status_matches) {
}