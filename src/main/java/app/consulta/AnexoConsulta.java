package app.consulta;

import java.util.Objects;

public class AnexoConsulta {

    private String nome;
    private int id;
    private byte[] arquivo;
    private String filename;
    private int idConsulta;

    public AnexoConsulta() {
    }

    public AnexoConsulta(String nome, int id, byte[] arquivo, String filename, int idConsulta) {
        this.nome = nome;
        this.id = id;
        this.arquivo = arquivo;
        this.filename = filename;
        this.idConsulta = idConsulta;
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
     * 
     * @return byte[]
     */
    public byte[] getArquivo() {
        return this.arquivo;
    }

    
    /** 
     * 
     * 
     * Setter padrão
     * 
     * @param arquivo
     */
    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
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
     * 
     * Setter padrão
     * 
     * @param filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    
    /** 
     * 
     * Getter padrão
     * 
     * @return int
     */
    public int getIdConsulta() {
        return this.idConsulta;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * 
     * @param idConsulta
     */
    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    
    /** 
     * 
     * Getter padrão
     * 
     * @param nome
     * @return AnexoConsulta
     */
    public AnexoConsulta nome(String nome) {
        this.nome = nome;
        return this;
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
        if (!(o instanceof AnexoConsulta)) {
            return false;
        }
        AnexoConsulta anexoConsulta = (AnexoConsulta) o;
        return Objects.equals(nome, anexoConsulta.nome) && id == anexoConsulta.id
                && Objects.equals(arquivo, anexoConsulta.arquivo) && Objects.equals(filename, anexoConsulta.filename)
                && idConsulta == anexoConsulta.idConsulta;
    }

    
    /** 
     * 
     * Hashcode padrão
     * 
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(nome, id, arquivo, filename, idConsulta);
    }

    
    /** 
     * 
     * toString padrão
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "{" + " nome='" + getNome() + "'" + ", id='" + getId() + "'" + ", arquivo='" + getArquivo() + "'"
                + ", filename='" + getFilename() + "'" + ", idConsulta='" + getIdConsulta() + "'" + "}";
    }

}
