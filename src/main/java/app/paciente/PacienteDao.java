package app.paciente;

import app.util.DaoUtil;
import java.util.List;
import org.sql2o.Query;

public class PacienteDao {

    
    /** 
     * 
     * Retorna todos os pacientes de um determinado usuário
     * 
     * @param idUsuario
     * @return List<Paciente>
     */
    public static List<Paciente> getAllPacientes(int idUsuario) {        
        Query query = 
            DaoUtil.getConexao()
            .createQuery("select * from paciente where id_usuario = :idUsuario order by nome")            
            .addParameter("idUsuario", idUsuario)
            .addColumnMapping("id_usuario", "idUsuario")
            .addColumnMapping("data_de_nascimento", "dataNascimento");
            
        List<Paciente> pacientes = query.executeAndFetch(Paciente.class); 
        return pacientes;
    }

    
    /** 
     * 
     * Retorna um paciente específico de um usuário
     * 
     * @param idPaciente
     * @return Paciente
     */
    public static Paciente getPaciente(int idPaciente) {        
        Paciente p = new Paciente();
        Query query = 
            DaoUtil.getConexao()
            .createQuery("select * from paciente where id = :id")            
            .addParameter("id", idPaciente)
            .addColumnMapping("id_usuario", "idUsuario")
            .addColumnMapping("data_de_nascimento", "dataNascimento");              

        p = query.executeAndFetchFirst(Paciente.class);        
        return p;
    }

    
    /** 
     * 
     * Delete um paciente - cuidado
     * 
     * @param idPaciente
     */
    public static void deletarPaciente(int idPaciente) {
        Query query = 
            DaoUtil.getConexao()
            .createQuery("delete from paciente where id = :id")            
            .addParameter("id", idPaciente);
        
        query.executeUpdate();
    }

    
    /** 
     * 
     * Insere um novo paciente
     * 
     * @param p
     * @return int
     */
    public static int inserirPaciente(Paciente p) {
        String sql = "insert into paciente "
        .concat("(id_usuario, nome, sexo, data_de_nascimento, peso) ")
        .concat("values (:idUsuario, :nome, :sexo, :dataNascimento, :peso)");

        Query query = 
            DaoUtil.getConexao()
            .createQuery(sql)
            .bind(p)
            .addColumnMapping("id_usuario", "idUsuario")
            .addColumnMapping("data_de_nascimento", "dataNascimento");
        
        int key = (Integer) query.executeUpdate().getKey();
        
        return key;        
    }
    
    
    /** 
     * 
     * Atualiza um paciente existente
     * 
     * @param p
     */
    public static void atualizarPaciente(Paciente p) {
        String sql = "update paciente set "
        .concat("nome = :nome, ")
        .concat("sexo = :sexo, ")
        .concat("data_de_nascimento = :dataNascimento, ")
        .concat("peso = :peso ")
        .concat("where id = :id ");
        
        Query query = 
            DaoUtil.getConexao()
            .createQuery(sql)
            .bind(p);
                    
        query.executeUpdate();        
    }

}