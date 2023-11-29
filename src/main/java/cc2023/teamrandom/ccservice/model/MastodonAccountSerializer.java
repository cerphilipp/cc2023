package cc2023.teamrandom.ccservice.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class MastodonAccountSerializer implements JsonSerializer<MastodonAccount> {
    @Override
    public JsonElement serialize(MastodonAccount mastodonAccount, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", mastodonAccount.getId());
        jsonObject.addProperty("username", mastodonAccount.getUsername());
        jsonObject.addProperty("acct", mastodonAccount.getAcct());
        jsonObject.addProperty("url", mastodonAccount.getUrl());
        jsonObject.addProperty("displayName", mastodonAccount.getDisplayName());
        jsonObject.addProperty("note", mastodonAccount.getNote());
        jsonObject.addProperty("avatar", mastodonAccount.getAvatar());
        jsonObject.addProperty("avatarStatic", mastodonAccount.getAvatarStatic());
        jsonObject.addProperty("header", mastodonAccount.getHeader());
        jsonObject.addProperty("headerStatic", mastodonAccount.getHeaderStatic());
        jsonObject.add("fields", jsonSerializationContext.serialize(mastodonAccount.getFields()));
        jsonObject.add("emojis", jsonSerializationContext.serialize(mastodonAccount.getEmojis()));
        jsonObject.add("moved", jsonSerializationContext.serialize(mastodonAccount.getMoved()));
        jsonObject.add("createdAt", jsonSerializationContext.serialize(mastodonAccount.getCreatedAt()));
        jsonObject.add("lastStatusAt", jsonSerializationContext.serialize(mastodonAccount.getLastStatusAt()));
        jsonObject.addProperty("statusesCount", mastodonAccount.getStatusesCount());
        jsonObject.addProperty("followersCount", mastodonAccount.getFollowersCount());
        jsonObject.addProperty("followingCount", mastodonAccount.getFollowingCount());
        jsonObject.add("limited", jsonSerializationContext.serialize(mastodonAccount.getLimited()));
        jsonObject.addProperty("locked", mastodonAccount.isLocked());
        jsonObject.addProperty("group", mastodonAccount.isGroup());
        jsonObject.add("suspended", jsonSerializationContext.serialize(mastodonAccount.getSuspended()));
        jsonObject.addProperty("bot", mastodonAccount.isBot());
        jsonObject.add("discoverable", jsonSerializationContext.serialize(mastodonAccount.getDiscoverable()));
        jsonObject.addProperty("notIndexed", mastodonAccount.isNotIndexed());
        return jsonObject;
    }
}
