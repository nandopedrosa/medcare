package app.util;

import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

public class JsonUtil {
    public static String getRepresentacaoJson(String key, String value) {
        String retorno = "{"
            .concat("'")
            .concat(key)
            .concat("'")
            .concat(":")
            .concat(value)
            .concat("}");
        return retorno;
    }

    public static JsonElement getJsonElement(String key, String value) {
        return new JsonParser().parse(getRepresentacaoJson(key,value));
    }

}

