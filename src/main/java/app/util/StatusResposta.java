package app.util;

public enum StatusResposta {
    SUCESSO("SUCESSO"), ERRO("ERRO");

    final private String status;

    StatusResposta(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
