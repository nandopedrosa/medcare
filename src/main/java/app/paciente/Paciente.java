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

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public long getDataNascimento() {
        return this.dataNascimento;
    }

    public long getIdade() {
        return this.idade;
    }

    public void atualizarIdade() {
        if (this.dataNascimento != 0L) {
            Instant instant = Instant.ofEpochSecond(this.dataNascimento);
            LocalDate nasc = LocalDate.ofInstant(instant, ZoneId.systemDefault());
            LocalDate hoje = LocalDate.now();
            this.idade = Period.between(nasc, hoje).getYears();
        }
    }

    //Esse método é utilizado quando a data da nascimento vem do BD (unix epoch)
    public void setDataNascimento(long dataNascimento) {
        this.dataNascimento = dataNascimento;
        this.atualizarIdade();
    }

    //Esse método é utilizado quando a data da nascimento vem do browser (dd/mm/yyyy)
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

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setPeso(String pesoStr) {
        this.peso = pesoStr.isEmpty() ? 0 : Double.parseDouble(pesoStr);
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(id, idUsuario, nome, sexo, dataNascimento, peso);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", idUsuario='" + getIdUsuario() + "'" + ", nome='" + getNome() + "'"
                + ", sexo='" + getSexo() + "'" + ", dataNascimento='" + getDataNascimento() + "'" + ", peso='"
                + getPeso() + "'" + "}";
    }

}