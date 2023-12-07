package cc2023.teamrandom.ccservice.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class MastodonApplicationSerializer implements JsonSerializer<MastodonApplication> {
    @Override
    public JsonElement serialize(MastodonApplication mastodonApplication, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", mastodonApplication.getName());
        jsonObject.add("website", jsonSerializationContext.serialize(mastodonApplication.getWebsite()));
        jsonObject.addProperty("vapidKey", mastodonApplication.getVapidKey());
        jsonObject.add("clientId", jsonSerializationContext.serialize(mastodonApplication.getClientId()));
        jsonObject.add("clientSecret", jsonSerializationContext.serialize(mastodonApplication.getClientSecret()));
        return jsonObject;
    }
}
