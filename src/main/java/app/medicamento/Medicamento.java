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

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String f ) {
        this.filename = f;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndicacao() {
        return this.indicacao;
    }

    public void setIndicacao(String indicacao) {
        this.indicacao = indicacao;
    }

    public String getPosologia() {
        return this.posologia;
    }

    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    public byte[] getBula() {
        return this.bula;
    }

    public void setBula(byte[] bula) {
        this.bula = bula;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, indicacao, posologia);
    }

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