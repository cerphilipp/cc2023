package cc2023.teamrandom.ccservice.model;

import java.time.ZonedDateTime;
import java.util.List;

public record Filter(String id,
                     String title,
                     List<String> context, //one of:  home, notifications, public, thread, account
                     @Optional ZonedDateTime expires_at,
                     String filter_action, //one of: warn, hide
                     List<FilterKeyword> keywords,
                     List<FilterStatus> statuses) {
}