package app.tratamento;

import app.util.DaoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import app.medicamento.Medicamento;
import app.medicamento.MedicamentoDao;
import org.sql2o.Query;

public class TratamentoDao {

    
    /** 
     * Retorna todos os tratamentos de um paciente
     * 
     * @param idPaciente
     * @return List<Tratamento>
     */
    public static List<Tratamento> getAllTratamentos(int idPaciente) {     
        Query query = 
            DaoUtil.getConexao()
            .createQuery("select * from tratamento where id_paciente = :idPaciente order by quadro")            
            .addParameter("idPaciente", idPaciente)
            .addColumnMapping("id_paciente", "idPaciente");

        List<Tratamento> tratamentos = query.executeAndFetch(Tratamento.class);      
                
        //Agora buscamos o medicamento de cada tratamento
        List<Medicamento> medicamentos = new ArrayList<Medicamento>();
        for(Tratamento t: tratamentos) {
           medicamentos = getMedicamentosDoTratamento(t.getId());                     
           t.setMedicamentos(medicamentos);
        }
        return tratamentos;
    }

    
    /** 
     * 
     * Retorna um tratamento específico de um paciente
     * 
     * @param idTratamento
     * @return Tratamento
     */
    public static Tratamento getTratamento(int idTratamento) {
        Tratamento trat = new Tratamento();
        Query query = DaoUtil.getConexao().createQuery("select * from tratamento where id = :id")
                .addParameter("id", idTratamento)                
                .addColumnMapping("id_paciente", "idPaciente");

        trat = query.executeAndFetchFirst(Tratamento.class);
        List<Medicamento> medicamentos = new ArrayList<Medicamento>();
        medicamentos = getMedicamentosDoTratamento(trat.getId());         
        trat.setMedicamentos(medicamentos);            
        return trat;
    }

    
    /** 
     *
     * Delete um tratamento específico de um paciente. Também deleta os medicamentos associados.
     * 
     *  @param idTratamento
     */
    public static void deletarTratamento(int idTratamento) {
        //Deletar registros fracos primeiro
        Query query = 
        DaoUtil.getConexao()
        .createQuery("delete from tratamento_medicamento where id_tratamento = :id")
        .addParameter("id",idTratamento);
        query.executeUpdate();
        
        query = 
        DaoUtil.getConexao()
        .createQuery("delete from tratamento where id = :id")
        .addParameter("id",idTratamento);

        query.executeUpdate();
    }

    
    /** 
     * 
     * Insere um Tratamento específico e seus medicamentos associados
     * 
     * @param trat
     * @param idMedicamentos
     * @return int
     */
    public static int inserirTratamento(Tratamento trat, List<String> idMedicamentos) {
        String sql = "insert into tratamento "
            .concat("(id_paciente, quadro) ")
            .concat("values (:idPaciente, :quadro)");

        Query query = 
            DaoUtil.getConexao()
            .createQuery(sql)
            .bind(trat)
            .addColumnMapping("id_paciente", "idPaciente");            

        int key = (Integer) query.executeUpdate().getKey();
        inserirMedicamentosDoTratamento(key, idMedicamentos);        
        return key;
    }

    
    /** 
     * 
     * Atualiza um tratamento. 
     * Deleta todos os medicamentos associados e os insere novamente (para não ter que lidar com updates)
     * 
     * @param trat
     * @param idMedicamentos
     */
    public static void atualizarTratamento(Tratamento trat, List<String> idMedicamentos) {
        String sql = "update tratamento set "
            .concat("quadro = :quadro ")            
            .concat("where id = :id ");

        Query query = DaoUtil.getConexao().createQuery(sql).bind(trat);
        query.executeUpdate();

        //Atualizar medicamentos do tratamento. Fazemos delete para nao lidar com updates
        query = DaoUtil.getConexao().createQuery("delete from tratamento_medicamento where id_tratamento = :idTratamento")
        .addParameter("idTratamento", trat.getId());
        query.executeUpdate();
        inserirMedicamentosDoTratamento(trat.getId(), idMedicamentos);
    }

    
    /** 
     * 
     * Retorna os medicamentos associados a um tratamento
     * 
     * @param idTratamento
     * @return List<Medicamento>
     */
    private static List<Medicamento> getMedicamentosDoTratamento(int idTratamento) {
        List<Medicamento> medicamentos = new ArrayList<Medicamento>();
        Query query = 
        DaoUtil.getConexao()
        .createQuery("select id_medicamento from tratamento_medicamento where id_tratamento = :idTratamento")            
        .addParameter("idTratamento", idTratamento);

       List<Map<String,Object>> medIds =  query.executeAndFetchTable().asList();

       for(Map<String,Object> medId: medIds) {
           medicamentos.add(MedicamentoDao.getMedicamento((Integer)medId.get("id_medicamento")));
       }
       return medicamentos;
    }

    
    /** 
     * 
     * Insere os medicamentos de um tratamento. 
     * 
     * @param idTratamento
     * @param idMedicamentos, String separada por vírgula
     */
    private static void inserirMedicamentosDoTratamento(int idTratamento, List<String> idMedicamentos) {
        //Inserindo os medicamentos do tratamento
        for(String idMedicamento: idMedicamentos) {
            Query query = 
            DaoUtil.getConexao()
            .createQuery("insert into tratamento_medicamento (id_tratamento, id_medicamento) values (:idTratamento, :idMedicamento)")
            .addParameter("idTratamento",idTratamento)
            .addParameter("idMedicamento",Integer.parseInt(idMedicamento));
            query.executeUpdate();            
        }
    }

}