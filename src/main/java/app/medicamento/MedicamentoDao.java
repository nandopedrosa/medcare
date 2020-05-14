package app.medicamento;

import app.util.DaoUtil;
import java.util.List;
import org.sql2o.Query;

public class MedicamentoDao {

    
    /** 
     * Retorna todos os medicamentos de um usuário
     * 
     * @param idUsuario
     * @return List<Medicamento>
     */
    public static List<Medicamento> getAllMedicamentos(int idUsuario) {        
        Query query = 
            DaoUtil.getConexao()
            .createQuery("select id, id_usuario, nome, indicacao, posologia from medicamento where id_usuario = :idUsuario order by nome")            
            .addParameter("idUsuario", idUsuario)
            .addColumnMapping("id_usuario", "idUsuario"); 

        List<Medicamento> medicamentos = query.executeAndFetch(Medicamento.class);                
        return medicamentos;
    }

    
    /** 
     * 
     * Verifica (count) se o medicamento está associado a algum paciente
     * 
     * @param idMedicamento
     * @return boolean
     */
    public static boolean isMedicamentoAssociadoTratamento(int idMedicamento) {
        boolean retorno = true;
        Query query =
            DaoUtil.getConexao()
            .createQuery("select count(*) from tratamento_medicamento where id_medicamento = :idMedicamento")
            .addParameter("idMedicamento", idMedicamento);

        int count = query.executeScalar(Integer.class);

        if(count == 0)
            retorno = false;

        return retorno;
    }

    
    /** 
     * 
     * Retorna um medicamento específico de um usuário
     * 
     * @param idMedicamento
     * @return Medicamento
     */
    public static Medicamento getMedicamento(int idMedicamento) {        
        Medicamento med = new Medicamento();
        Query query = 
            DaoUtil.getConexao()
            .createQuery("select * from medicamento where id = :id")            
            .addParameter("id", idMedicamento)
            .addColumnMapping("id_usuario", "idUsuario");             

        med = query.executeAndFetchFirst(Medicamento.class);        
        return med;
    }

    
    /** 
     * 
     * Deleta um medicamento específico de um usuário
     * 
     * @param idMedicamento
     */
    public static void deletarMedicamento(int idMedicamento) {
        Query query = 
            DaoUtil.getConexao()
            .createQuery("delete from medicamento where id = :id")            
            .addParameter("id", idMedicamento);
        
        query.executeUpdate();
    }

    
    /** 
     * 
     * Insere um novo medicamento
     * 
     * @param med
     * @return int
     */
    public static int inserirMedicamento(Medicamento med) {
        String sql = "insert into medicamento "
        .concat("(id_usuario, nome, indicacao, posologia, bula, filename) ")
        .concat("values (:idUsuario, :nome, :indicacao, :posologia, :bula, :filename)");

        Query query = 
            DaoUtil.getConexao()
            .createQuery(sql)
            .bind(med)
            .addColumnMapping("id_usuario", "idUsuario"); 
        
        int key = (Integer)query.executeUpdate().getKey();
        
        return key;        
    }
    
    
    /** 
     * 
     * Atualiza um medicamento existente
     * 
     * @param med
     */
    public static void atualizarMedicamento(Medicamento med) {
        String sql = "update medicamento set "
        .concat("nome = :nome, ")
        .concat("indicacao = :indicacao, ")
        .concat("posologia = :posologia, ")
        .concat("bula = :bula, ")
        .concat("filename = :filename ")
        .concat("where id = :id ");
        
        Query query = 
            DaoUtil.getConexao()
            .createQuery(sql)
            .bind(med);
                    
        query.executeUpdate();        
    }
        

}