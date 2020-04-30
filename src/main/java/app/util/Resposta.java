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

    
    /** 
     * Retorna um status possível de resposta (sucesso/erro)
     * 
     * @return StatusResposta
     */
    public StatusResposta getStatus() {
        return status;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param status
     */
    public void setStatus(StatusResposta status) {
        this.status = status;
    }

    
    /** 
     * 
     * Getter padrão
     * 
     * @return String
     */
    public String getMessage() {
        return msg;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param message
     */
    public void setMessage(String message) {
        this.msg = message;
    }

    
    /** 
     * 
     * Getter padrão
     * 
     * @return JsonElement
     */
    public JsonElement getData() {
        return data;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param data
     */
    public void setData(JsonElement data) {
        this.data = data;
    }
}