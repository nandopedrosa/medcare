package app.consulta;

import java.util.List;
import app.util.ValidacaoUtil;
import spark.*;
import app.login.LoginService;
import app.util.JsonUtil;
import app.util.Resposta;
import app.util.StatusResposta;
import javax.servlet.http.Part;
import java.io.InputStream;
import spark.utils.IOUtils;
import com.google.gson.Gson;

public class ConsultaService {

    public static List<Consulta> getAllConsultas(Request req) {
        if (!LoginService.isUsuarioLogado(req))
            return null;

        return ConsultaDao.getAllConsultas(Integer.parseInt(req.queryParams("idPaciente")));
    }

    public static Consulta getConsulta(Request req) {
        Consulta c = new Consulta();
        c = ConsultaDao.getConsulta(Integer.parseInt(req.params("id")));
        return c;
    }

    public static Resposta deletarConsulta(Request req) {
        ConsultaDao.deletarConsulta(Integer.parseInt(req.params("id")));
        Resposta r = new Resposta(StatusResposta.SUCESSO);
        return r;
    }

    public static Resposta salvarConsulta(Request req) {
        if (!LoginService.isUsuarioLogado(req))
            return null;

        Resposta resposta = new Resposta();

        Consulta c = new Consulta();
        String id = req.queryParams("id");
        String idPaciente = req.queryParams("idPaciente");
        String dataStr = req.queryParams("data");
        String quadro = req.queryParams("quadro");
        String responsavel = req.queryParams("responsavel");
        String conduta = req.queryParams("conduta");
        // TODO: anexos

        // Validações
        if (ValidacaoUtil.blank(dataStr, quadro, responsavel, conduta)) {
            resposta.setStatus(StatusResposta.ERRO);
            resposta.setMessage("Por favor, preencha todos os campos obrigatórios");
            return resposta;
        }

        // Processamento
        c.setIdPaciente(Integer.parseInt(idPaciente));
        c.setData(dataStr);
        c.setQuadro(quadro);
        c.setResponsavel(responsavel);
        c.setConduta(conduta);

        if (id.isEmpty()) {
            // Insert
            int key = ConsultaDao.inserirConsulta(c);
            c.setId(key);
            resposta.setData(JsonUtil.getJsonElement("id", String.valueOf(c.getId())));
        } else {
            // Update
            c.setId(Integer.parseInt(id));
            ConsultaDao.atualizarConsulta(c);
        }

        resposta.setStatus(StatusResposta.SUCESSO);
        resposta.setMessage("Ação realizada com sucesso");
        return resposta;
    }

    public static Resposta deletarAnexoConsulta(Request req) {
        Resposta r = new Resposta();
        int idAnexoConsulta = Integer.parseInt(req.params("id"));
        ConsultaDao.deletarAnexoConsulta(idAnexoConsulta);
        r.setStatus(StatusResposta.SUCESSO);
        return r;
    }

    public static Resposta getAnexosConsulta(Request req) {
        Resposta r = new Resposta();
        int idConsulta = Integer.parseInt(req.params("id"));        
        List<AnexoConsulta> anexos = ConsultaDao.getAnexosConsulta(idConsulta);
        r.setStatus(StatusResposta.SUCESSO);
        r.setData(new Gson().toJsonTree(anexos));
        return r;
    }

    public static AnexoConsulta getAnexoConsulta(Request req) {        
        int idAnexoConsulta = Integer.parseInt(req.params("id"));        
        AnexoConsulta anexo = ConsultaDao.getAnexoConsulta(idAnexoConsulta);        
        return anexo;
    }    

    public static Resposta adicionarAnexo(Request req) {
        Resposta r = new Resposta();
        String filename = null;
        byte[] anexo = null;
        int idConsulta = Integer.parseInt(req.queryParams("idConsulta"));
        String nome = req.queryParams("nomeAnexoConsulta");

          // Validações
        if (ValidacaoUtil.blank(nome)) {
            r.setStatus(StatusResposta.ERRO);
            r.setMessage("Por favor, preencha todos os campos obrigatórios");
            return r;
        }

        Part arquivo = null;
        try {
            arquivo = req.raw().getPart("fileAnexoConsulta");            
            if(arquivo != null) {
                filename = req.queryParams("filenameAnexoConsulta");
                InputStream inputStream = arquivo.getInputStream();    
                anexo = IOUtils.toByteArray(inputStream);                
            }  else {
                r.setStatus(StatusResposta.ERRO);
                r.setMessage("Por favor, preencha todos os campos obrigatórios");
                return r;
            }          
        } catch (Exception e) {
            e.printStackTrace();
            return new Resposta(StatusResposta.ERRO, "Erro no envio do arquivo");
        }

        ConsultaDao.inserirAnexo(idConsulta, nome, anexo, filename);        

        r.setStatus(StatusResposta.SUCESSO);
        r.setMessage("Anexo adicionado com sucesso: " + nome);
        r.setData(JsonUtil.getJsonElement("nome", nome));
        return r;
    }
}