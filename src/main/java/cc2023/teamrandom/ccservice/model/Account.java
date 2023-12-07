package cc2023.teamrandom.ccservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public record Account(String id,
                      String username,
                      String acct,
                      String url,
                      String display_name,
                      String note,
                      String avatar,
                      String avatar_static,
                      String header,
                      String header_static,
                      Boolean locked,
                      List<Field> fields,
                      List<CustomEmoji> emojis,
                      Boolean bot,
                      Boolean group,
                      Boolean discoverable,
                      @Optional Boolean noindex,
                      @Optional Account moved,
                      @Optional Boolean suspended,
                      @Optional Boolean limited,
                      ZonedDateTime created_at,
                      String last_status_at,
                      Integer statuses_count,
                      Integer followers_count,
                      Integer following_count) {
}