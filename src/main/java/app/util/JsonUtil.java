package app.util;

import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

public class JsonUtil {
    
    /** 
     * 
     * 
     * Cria uma String JSON com um unico campo {key:value}
     * 
     * @param key
     * @param value
     * @return String
     */
    public static String getRepresentacaoJson(String key, String value) {
        String retorno = "{"
            .concat("'")
            .concat(key)
            .concat("'")
            .concat(":")
            .concat("'" + value + "'")
            .concat("}");
        return retorno;
    }

    
    /** 
     * 
     * Retorna um JSON com um unico campo
     * 
     * @param key
     * @param value
     * @return JsonElement
     */
    public static JsonElement getJsonElement(String key, String value) {
        return new JsonParser().parse(getRepresentacaoJson(key,value));
    }

}

