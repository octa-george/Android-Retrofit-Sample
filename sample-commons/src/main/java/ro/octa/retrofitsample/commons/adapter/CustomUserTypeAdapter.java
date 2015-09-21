package ro.octa.retrofitsample.commons.adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ro.octa.retrofitsample.commons.model.User;

/**
 * @author Octa on 9/21/2015.
 */
public class CustomUserTypeAdapter extends CustomizedTypeAdapterFactory<User> {

    public CustomUserTypeAdapter() {
        super(User.class);
    }

    @Override
    protected void beforeWrite(User source, JsonElement toSerialize) {
        JsonObject data = toSerialize.getAsJsonObject();
        data.remove("id");
        data.remove("userHash");
    }

    @Override
    protected void afterRead(JsonElement deserialized) {

    }
}
