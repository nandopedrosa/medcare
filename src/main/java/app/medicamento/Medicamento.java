package app.medicamento;

import java.util.Objects;

public class Medicamento {
    
    private int id;
    private int idUsuario;
    private String nome;
    private String indicacao;
    private String posologia;
    private byte[] bula;
    private String filename;

    public Medicamento() {
    }

    public Medicamento(int id, String nome, String indicacao, String posologia) {
        this.id = id;
        this.nome = nome;
        this.indicacao = indicacao;
        this.posologia = posologia;
    }

    
    /** 
     * 
     * Getter padrão
     * 
     * @return String
     */
    public String getFilename() {
        return this.filename;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param f
     */
    public void setFilename(String f ) {
        this.filename = f;
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
    public int getIdUsuario() {
        return this.idUsuario;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param idUsuario
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    /** 
     * 
     * Getter padrão
     * 
     * @return String
     */
    public String getNome() {
        return this.nome;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    /** 
     * 
     * 
     * Getter padrão
     * 
     * @return String
     */
    public String getIndicacao() {
        return this.indicacao;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param indicacao
     */
    public void setIndicacao(String indicacao) {
        this.indicacao = indicacao;
    }

    
    /** 
     * 
     * 
     * Getter padrão
     * 
     * @return String
     */
    public String getPosologia() {
        return this.posologia;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param posologia
     */
    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    
    /** 
     * 
     * Getter padrão
     * 
     * @return byte[]
     */
    public byte[] getBula() {
        return this.bula;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param bula
     */
    public void setBula(byte[] bula) {
        this.bula = bula;
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
        if (!(o instanceof Medicamento)) {
            return false;
        }
        Medicamento medicamento = (Medicamento) o;
        return id == medicamento.id && Objects.equals(nome, medicamento.nome) && Objects.equals(indicacao, medicamento.indicacao) && Objects.equals(posologia, medicamento.posologia);
    }

    
    /** 
     * 
     * Hashcode padrão
     * 
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, nome, indicacao, posologia);
    }

    
    /** 
     * 
     * toString padrão
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", indicacao='" + getIndicacao() + "'" +
            ", posologia='" + getPosologia() + "'" +
            "}";
    }

   

    




}