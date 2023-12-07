package cc2023.teamrandom.ccservice.model.gson;

import cc2023.teamrandom.ccservice.model.MastodonStatus;
import com.google.gson.*;

import java.lang.reflect.Type;

public class StatusSerializer implements JsonSerializer<MastodonStatus> {
    @Override
    public JsonElement serialize(MastodonStatus mastodonStatus, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", mastodonStatus.getId());
        jsonObject.addProperty("uri", mastodonStatus.getUri());
        jsonObject.add("createdAt", jsonSerializationContext.serialize(mastodonStatus.getCreatedAt()));
        jsonObject.add("account", jsonSerializationContext.serialize(mastodonStatus.getAccount()));
        jsonObject.addProperty("content", mastodonStatus.getContent());
        jsonObject.addProperty("visibility", mastodonStatus.getVisibility());
        jsonObject.addProperty("spoilerText", mastodonStatus.getSpoilerText());
        jsonObject.add("mediaAttachments", jsonSerializationContext.serialize(mastodonStatus.getMediaAttachments()));
        jsonObject.add("application", jsonSerializationContext.serialize(mastodonStatus.getApplication()));
        jsonObject.add("mentions", jsonSerializationContext.serialize(mastodonStatus.getMentions()));
        jsonObject.add("tags", jsonSerializationContext.serialize(mastodonStatus.getTags()));
        jsonObject.addProperty("reblogsCount", mastodonStatus.getReblogsCount());
        jsonObject.addProperty("favouritesCount", mastodonStatus.getFavouritesCount());
        jsonObject.addProperty("repliesCount", mastodonStatus.getRepliesCount());
        jsonObject.addProperty("url", mastodonStatus.getUrl());
        jsonObject.addProperty("inReplyToId", mastodonStatus.getInReplyToId());
        jsonObject.addProperty("inReplyToAccountId", mastodonStatus.getInReplyToAccountId());
        jsonObject.add("reblog", jsonSerializationContext.serialize(mastodonStatus.getReblog()));
        jsonObject.add("poll", jsonSerializationContext.serialize(mastodonStatus.getPoll()));
        jsonObject.add("card", jsonSerializationContext.serialize(mastodonStatus.getCard()));
        jsonObject.addProperty("language", mastodonStatus.getLanguage());
        jsonObject.add("text", jsonSerializationContext.serialize(mastodonStatus.getText()));
        jsonObject.add("editedAt", jsonSerializationContext.serialize(mastodonStatus.getEditedAt()));
        jsonObject.addProperty("pinned", mastodonStatus.isPinned());
        jsonObject.addProperty("muted", mastodonStatus.isMuted());
        jsonObject.addProperty("reblogged", mastodonStatus.isReblogged());
        jsonObject.addProperty("sensitive", mastodonStatus.isSensitive());
        jsonObject.addProperty("bookmarked", mastodonStatus.isBookmarked());
        jsonObject.addProperty("favourited", mastodonStatus.isFavourited());
        return jsonObject;
    }
}

