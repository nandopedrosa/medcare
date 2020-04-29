package app.util;

import com.google.gson.JsonElement;

public class Resposta {
    private StatusResposta status;
    private String msg;
    private JsonElement data;

    public Resposta() {
        
    }

    public Resposta(StatusResposta status) {
        this.status = status;
    }

    public Resposta(StatusResposta status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Resposta(StatusResposta status, JsonElement data) {
        this.status = status;
        this.data = data;
    }

    public StatusResposta getStatus() {
        return status;
    }

    public void setStatus(StatusResposta status) {
        this.status = status;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }
}