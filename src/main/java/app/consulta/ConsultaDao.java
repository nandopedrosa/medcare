package app.consulta;

import app.util.DaoUtil;
import java.util.List;
import org.sql2o.Query;

public class ConsultaDao {

    public static List<Consulta> getAllConsultas(int idPaciente) {
        Query query = DaoUtil.getConexao()
                .createQuery("select * from consulta where id_paciente = :idPaciente order by data desc")
                .addParameter("idPaciente", idPaciente)
                .addColumnMapping("id_paciente", "idPaciente");

        List<Consulta> pacientes = query.executeAndFetch(Consulta.class);
        return pacientes;
    }

    public static List<AnexoConsulta> getAnexosConsulta(int idConsulta) {
        Query query = DaoUtil.getConexao()
        .createQuery("select * from anexo_consulta where id_consulta = :idConsulta order by nome")
        .addParameter("idConsulta", idConsulta)
        .addColumnMapping("id_consulta", "idConsulta");
        List<AnexoConsulta> anexos = query.executeAndFetch(AnexoConsulta.class);
        return anexos;
    }

    public static AnexoConsulta getAnexoConsulta(int idAnexoConsulta) {
        Query query = DaoUtil.getConexao()
            .createQuery("select * from anexo_consulta where id = :idAnexoConsulta")
            .addParameter("idAnexoConsulta", idAnexoConsulta)
            .addColumnMapping("id_consulta", "idConsulta");
            AnexoConsulta anexo = query.executeAndFetchFirst(AnexoConsulta.class);
            return anexo;
    }

    public static Consulta getConsulta(int idConsulta) {
        Consulta c = new Consulta();
        Query query = DaoUtil.getConexao()
        .createQuery("select * from consulta where id = :id")
        .addParameter("id", idConsulta)
        .addColumnMapping("id_paciente", "idPaciente");

        c = query.executeAndFetchFirst(Consulta.class);
        return c;
    }

    public static void deletarConsulta(int idConsulta) {
        Query query = DaoUtil.getConexao()
        .createQuery("delete from consulta where id = :id")
        .addParameter("id",idConsulta);

        query.executeUpdate();
    }

    public static void deletarAnexoConsulta(int idAnexoConsulta) {
        Query query = DaoUtil.getConexao()
        .createQuery("delete from anexo_consulta where id = :id")
        .addParameter("id",idAnexoConsulta);

        query.executeUpdate();
    }

    public static int inserirConsulta(Consulta c) {
        String sql = "insert into consulta "
        .concat("(id_paciente, data, responsavel, quadro, conduta) ")
        .concat("values (:idPaciente, :data, :responsavel, :quadro, :conduta)");

        Query query = DaoUtil.getConexao()
        .createQuery(sql)
        .bind(c)
        .addColumnMapping("id_paciente", "idPaciente");

        int key = (Integer) query.executeUpdate().getKey();

        return key;
    }

    public static void inserirAnexo(int idConsulta, String nome, byte[] anexo, String filename) {
        String sql = "insert into anexo_consulta "
        .concat("(id_consulta, nome, arquivo, filename) ")
        .concat("values (:idConsulta, :nome, :arquivo, :filename)");

        Query query = DaoUtil.getConexao()
        .createQuery(sql)
        .addParameter("idConsulta", idConsulta)
        .addParameter("nome", nome)
        .addParameter("arquivo", anexo)
        .addParameter("filename", filename);

        query.executeUpdate();
    }

    public static void atualizarConsulta(Consulta c) {
        String sql = "update consulta set "
        .concat("data = :data, ")
        .concat("responsavel = :responsavel, ")
        .concat("quadro = :quadro, ")
        .concat("conduta = :conduta ")
        .concat("where id = :id ");

        Query query = DaoUtil.getConexao().createQuery(sql).bind(c);

        query.executeUpdate();
    }

}