package app.consulta;

import java.util.Objects;
import app.util.ValidacaoUtil;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class Consulta {
    
    private int id;
    private int idPaciente;
    private long data;
    private String responsavel;
    private String quadro;
    private String conduta;
    
    public Consulta() {
    }

    public Consulta(int id, int idPaciente, long data, String responsavel, String quadro, String conduta) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.data = data;
        this.responsavel = responsavel;
        this.quadro = quadro;
        this.conduta = conduta;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPaciente() {
        return this.idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public long getData() {
        return this.data;
    }

    public void setData(long data) {
        this.data = data;
    }

    //Esse método é utilizado quando a data vem do browser (dd/mm/yyyy)
    public void setData(String dataStr) {
        if (ValidacaoUtil.isNumeric(dataStr)) {
            // Veio do banco de dados (UNIX time)
            this.setData(Long.parseLong(dataStr));
        } else {
            // Veio do browser (dd/mm/yyyy)
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date data = null;
            try {
                data = formato.parse(dataStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.setData(data.getTime() / 1000L);
        }
    }

    public String getResponsavel() {
        return this.responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getQuadro() {
        return this.quadro;
    }

    public void setQuadro(String quadro) {
        this.quadro = quadro;
    }

    public String getConduta() {
        return this.conduta;
    }

    public void setConduta(String conduta) {
        this.conduta = conduta;
    }

    public Consulta id(int id) {
        this.id = id;
        return this;
    }

    public Consulta idPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
        return this;
    }

    public Consulta data(long data) {
        this.data = data;
        return this;
    }

    public Consulta responsavel(String responsavel) {
        this.responsavel = responsavel;
        return this;
    }

    public Consulta quadro(String quadro) {
        this.quadro = quadro;
        return this;
    }

    public Consulta conduta(String conduta) {
        this.conduta = conduta;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Consulta)) {
            return false;
        }
        Consulta consulta = (Consulta) o;
        return id == consulta.id && idPaciente == consulta.idPaciente && data == consulta.data && Objects.equals(responsavel, consulta.responsavel) && Objects.equals(quadro, consulta.quadro) && Objects.equals(conduta, consulta.conduta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPaciente, data, responsavel, quadro, conduta);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", idPaciente='" + getIdPaciente() + "'" +
            ", data='" + getData() + "'" +
            ", responsavel='" + getResponsavel() + "'" +
            ", quadro='" + getQuadro() + "'" +
            ", conduta='" + getConduta() + "'" +
            "}";
    }



}