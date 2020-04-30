package app.paciente;

import java.util.Date;
import java.util.Objects;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import app.util.ValidacaoUtil;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Period;

public class Paciente {
    public int id;
    public int idUsuario;
    public String nome;
    public String sexo;
    public long dataNascimento; // unix
    public double peso;

    // Dado transiente
    public long idade;

    public Paciente() {
    }

    public Paciente(int id, int idUsuario, String nome, String sexo, long dataNascimento, double peso) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.peso = peso;
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
     * Getter padrão
     * 
     * @return String
     */
    public String getSexo() {
        return this.sexo;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param sexo
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    
    /** 
     * 
     * Getter padrão
     * 
     * @return long
     */
    public long getDataNascimento() {
        return this.dataNascimento;
    }

    
    /** 
     * 
     * Getter padrão
     * 
     * @return long
     */
    public long getIdade() {
        return this.idade;
    }

    
    /** 
     * 
     * Atualiza a idade com base na data de nascimento
     * 
     * @return long
     */
    public void atualizarIdade() {
        if (this.dataNascimento != 0L) {
            Instant instant = Instant.ofEpochSecond(this.dataNascimento);
            LocalDate nasc = LocalDate.ofInstant(instant, ZoneId.systemDefault());
            LocalDate hoje = LocalDate.now();
            this.idade = Period.between(nasc, hoje).getYears();
        }
    }

    
    /** 
     * 
     * Getter padrão
     * Esse método é utilizado quando a data da nascimento vem do BD (unix epoch) 
     * @param dataNascimento
     */
    
    public void setDataNascimento(long dataNascimento) {
        this.dataNascimento = dataNascimento;
        this.atualizarIdade();
    }

    
    /** 
     * 
     * Seta a data de nascimento baseado na máscara recebida do navegador (dd/mm/yyyy)
     * 
     * @param dataStr
     */    
    public void setDataNascimento(String dataStr) {
        if (ValidacaoUtil.isNumeric(dataStr)) {
            // Veio do banco de dados (UNIX time)
            this.setDataNascimento(Long.parseLong(dataStr));
        } else {
            // Veio do browser (dd/mm/yyyy)
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date data = null;
            try {
                data = formato.parse(dataStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.setDataNascimento(data.getTime() / 1000L);
        }
    }

    
    /** 
     * 
     * Getter padrão
     * 
     * @return double
     */
    public double getPeso() {
        return this.peso;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param peso
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    
    /** 
     * 
     * Setter padrão
     * 
     * @param pesoStr
     */
    public void setPeso(String pesoStr) {
        this.peso = pesoStr.isEmpty() ? 0 : Double.parseDouble(pesoStr);
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
        if (!(o instanceof Paciente)) {
            return false;
        }
        Paciente paciente = (Paciente) o;
        return id == paciente.id && idUsuario == paciente.idUsuario && Objects.equals(nome, paciente.nome)
                && sexo == paciente.sexo && Objects.equals(dataNascimento, paciente.dataNascimento)
                && peso == paciente.peso;
    }

    
    /** 
     * 
     * Hashcode padrão
     * 
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, idUsuario, nome, sexo, dataNascimento, peso);
    }

    
    /** 
     * 
     * toString padrão
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", idUsuario='" + getIdUsuario() + "'" + ", nome='" + getNome() + "'"
                + ", sexo='" + getSexo() + "'" + ", dataNascimento='" + getDataNascimento() + "'" + ", peso='"
                + getPeso() + "'" + "}";
    }

}