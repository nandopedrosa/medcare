package app.usuario;

import java.util.Objects;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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
     * Getter padrão
     * 
     * @return String
     */
    public String getEmail() {
        return this.email;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    /** 
     * 
     * Getter padrão
     * 
     * @return String
     */
    public String getSenha() {
        return this.senha;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
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
        if (!(o instanceof Usuario)) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        return id == usuario.id && Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha);
    }

    
    /** 
     * 
     * Hashcode padrão
     * 
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, senha);
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
            ", email='" + getEmail() + "'" +
            ", senha='" + getSenha() + "'" +
            "}";
    }

}