package cc2023.teamrandom.ccservice.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class MastodonCreatedAtSerializer implements JsonSerializer<MastodonCreatedAt> {
    @Override
    public JsonElement serialize(MastodonCreatedAt mastodonCreatedAt, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("instant", mastodonCreatedAt.getInstant());
        jsonObject.addProperty("valid", mastodonCreatedAt.isValid());
        return jsonObject;
    }
}
