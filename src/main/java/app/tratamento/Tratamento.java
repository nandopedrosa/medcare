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

    
    /** 
     * 
     * Retorna a lista de medicamentos do tratamento por extenso, separados por vírgula
     * 
     * @return String (tylenol, dipirona, etc.)
     */
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
     * Setter padrão
     * 
     * @param idPaciente
     */
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    
    /** 
     * 
     * Seta os medicamentos associados ao tratamento. 
     * Também atualiza os medicamentos por extenso
     * 
     * @param medicamentos
     */
    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
        atualizarMedicamentosPorExtenso();
    }

    
    /** 
     * 
     * Getter padrão
     * 
     * @return List<Medicamento>
     */
    public List<Medicamento> getMedicamentos() {
        return this.medicamentos;
    }

    
    /** 
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
     * Equals padrão
     * 
     * @param o
     * @return boolean
     */
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

    
    /** 
     * 
     * Hashcode padrão
     * 
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, idPaciente, medicamentos, quadro);

    }
}
