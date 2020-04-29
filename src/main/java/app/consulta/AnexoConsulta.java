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

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getArquivo() {
        return this.arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getIdConsulta() {
        return this.idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public AnexoConsulta nome(String nome) {
        this.nome = nome;
        return this;
    }

    public AnexoConsulta id(int id) {
        this.id = id;
        return this;
    }

    public AnexoConsulta arquivo(byte[] arquivo) {
        this.arquivo = arquivo;
        return this;
    }

    public AnexoConsulta filename(String filename) {
        this.filename = filename;
        return this;
    }

    public AnexoConsulta idConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
        return this;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(nome, id, arquivo, filename, idConsulta);
    }

    @Override
    public String toString() {
        return "{" + " nome='" + getNome() + "'" + ", id='" + getId() + "'" + ", arquivo='" + getArquivo() + "'"
                + ", filename='" + getFilename() + "'" + ", idConsulta='" + getIdConsulta() + "'" + "}";
    }

}
