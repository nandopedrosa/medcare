package app.tratamento;

import java.util.List;
import app.medicamento.Medicamento;
import java.util.Objects;

public class Tratamento {
    private int id;
    private int idPaciente;
    private List<Medicamento> medicamentos;
    private String medicamentosPorExtenso = "";
    private String quadro;

    public Tratamento() {
    }

    public Tratamento(int id, int idPaciente, String quadro) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.quadro = quadro;
    }

    public String getMedicamentosPorExtenso() {
        return this.medicamentosPorExtenso;
    }

    public void atualizarMedicamentosPorExtenso() {
        this.medicamentosPorExtenso = "";
        for(Medicamento m: this.medicamentos) {
            this.medicamentosPorExtenso += m.getNome() + ", ";
        }
        this.medicamentosPorExtenso = this.medicamentosPorExtenso.substring(0, this.medicamentosPorExtenso.length() - 2);        
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

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
        atualizarMedicamentosPorExtenso();
    }

    public List<Medicamento> getMedicamentos() {
        return this.medicamentos;
    }

    public String getQuadro() {
        return this.quadro;
    }

    public void setQuadro(String quadro) {
        this.quadro = quadro;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Tratamento)) {
            return false;
        }
        Tratamento tratamento = (Tratamento) o;
        return id == tratamento.id && idPaciente == tratamento.idPaciente && medicamentos == tratamento.medicamentos
                && Objects.equals(quadro, tratamento.quadro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idPaciente, medicamentos, quadro);

    }
}
