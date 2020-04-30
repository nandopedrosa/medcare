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

    
    /** 
     * 
     * Getter padrão
     * 
     * @return int
     */
    public int getId() {
        return this.id;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /** 
     * 
     * Getter padrão
     * 
     * @return int
     */
    public int getIdPaciente() {
        return this.idPaciente;
    }

    
    /** 
     * 
     * 
     * Setter padrão
     * 
     * @param idPaciente
     */
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    
    /** 
     * 
     * Getter padrão
     * 
     * @return long
     */
    public long getData() {
        return this.data;
    }

    
    /** 
     * 
     * Getter padrão
     * 
     * @param data
     */
    public void setData(long data) {
        this.data = data;
    }

    
    /** 
     * 
     * 
     * Setter para quando a data vem do navegador (dd/mm/yyyy)
     * 
     * @param dataStr
     */
    
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

    
    /** 
     * 
     * Getter padrão
     * 
     * @return String
     */
    public String getResponsavel() {
        return this.responsavel;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param responsavel
     */
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    
    /** 
     * 
     * 
     * Getter padrão
     * 
     * @return String
     */
    public String getQuadro() {
        return this.quadro;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param quadro
     */
    public void setQuadro(String quadro) {
        this.quadro = quadro;
    }

    
    /** 
     * 
     * Getter padrão
     * 
     * 
     * @return String
     */
    public String getConduta() {
        return this.conduta;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * 
     * @param conduta
     */
    public void setConduta(String conduta) {
        this.conduta = conduta;
    }
         
    /** 
     * 
     * Equals padrão
     * 
     * @param o
     * @return boolean
     */
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

    
    /** 
     * 
     * Hashcode padrão
     * 
     * 
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, idPaciente, data, responsavel, quadro, conduta);
    }

    
    /** 
     * 
     * 
     * toString padrão
     * 
     * @return String
     */
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