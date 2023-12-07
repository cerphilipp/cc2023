package cc2023.teamrandom.ccservice.model;

import java.time.ZonedDateTime;

public record Field(String name,
                    String value,
                    ZonedDateTime verified_at) {
}