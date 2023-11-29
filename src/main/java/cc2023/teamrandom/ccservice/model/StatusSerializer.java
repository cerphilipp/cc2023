package cc2023.teamrandom.ccservice.model;

import com.google.gson.*;

import java.lang.reflect.Type;

public class StatusSerializer implements JsonSerializer<Status> {
    @Override
    public JsonElement serialize(Status status, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", status.getId());
        jsonObject.addProperty("uri", status.getUri());
        jsonObject.add("createdAt", jsonSerializationContext.serialize(status.getCreatedAt()));
        jsonObject.add("account", jsonSerializationContext.serialize(status.getAccount()));
        jsonObject.addProperty("content", status.getContent());
        jsonObject.addProperty("visibility", status.getVisibility());
        jsonObject.addProperty("spoilerText", status.getSpoilerText());
        jsonObject.add("mediaAttachments", jsonSerializationContext.serialize(status.getMediaAttachments()));
        jsonObject.add("application", jsonSerializationContext.serialize(status.getApplication()));
        jsonObject.add("mentions", jsonSerializationContext.serialize(status.getMentions()));
        jsonObject.add("tags", jsonSerializationContext.serialize(status.getTags()));
        jsonObject.addProperty("reblogsCount", status.getReblogsCount());
        jsonObject.addProperty("favouritesCount", status.getFavouritesCount());
        jsonObject.addProperty("repliesCount", status.getRepliesCount());
        jsonObject.addProperty("url", status.getUrl());
        jsonObject.addProperty("inReplyToId", status.getInReplyToId());
        jsonObject.addProperty("inReplyToAccountId", status.getInReplyToAccountId());
        jsonObject.add("reblog", jsonSerializationContext.serialize(status.getReblog()));
        jsonObject.add("poll", jsonSerializationContext.serialize(status.getPoll()));
        jsonObject.add("card", jsonSerializationContext.serialize(status.getCard()));
        jsonObject.addProperty("language", status.getLanguage());
        jsonObject.add("text", jsonSerializationContext.serialize(status.getText()));
        jsonObject.add("editedAt", jsonSerializationContext.serialize(status.getEditedAt()));
        jsonObject.addProperty("pinned", status.isPinned());
        jsonObject.addProperty("muted", status.isMuted());
        jsonObject.addProperty("reblogged", status.isReblogged());
        jsonObject.addProperty("sensitive", status.isSensitive());
        jsonObject.addProperty("bookmarked", status.isBookmarked());
        jsonObject.addProperty("favourited", status.isFavourited());
        return jsonObject;
    }
}

