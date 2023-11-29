package cc2023.teamrandom.ccservice.model;

import java.time.ZonedDateTime;
import java.util.List;

public record Status(String id,
        String uri,
        ZonedDateTime created_at,
        Account account,
        String content,
        String visibility,
        Boolean sensitive,
        String spoiler_text,
        List<MediaAttachment> media_attachments,
@Optional Application application,
        List<Mention> mentions,
        List<Tag> tags,
        List<CustomEmoji> emojis,
        Integer reblogs_count,
        Integer favourites_count,
        Integer replies_count,
        String url,
@Optional String in_reply_to_id,
@Optional String in_reply_to_account_id,
@Optional Status reblog,
        Poll poll,
@Optional PreviewCard card,
@Optional String language,
@Optional String text,
@Optional ZonedDateTime edited_at,
@Optional Boolean favourited,
@Optional Boolean reblogged,
@Optional Boolean muted,
@Optional Boolean bookmarked,
@Optional Boolean pinned,
@Optional List<FilterResult> filtered) {

public record Application(String name,
        String website) {
        }

public record Mention(String id,
        String username,
        String url,
        String acct) {
        }

public record Tag(String name,
        String url) {
        }
        }