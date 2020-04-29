package app.medicamento;

import java.util.List;
import javax.servlet.http.Part;
import java.io.InputStream;
import spark.utils.IOUtils;
import app.util.ValidacaoUtil;
import spark.*;
import app.login.LoginService;
import app.util.JsonUtil;
import app.util.Resposta;
import app.util.StatusResposta;

public class MedicamentoService {

    public static List<Medicamento> getAllMedicamentos(Request req) {
        if (!LoginService.isUsuarioLogado(req))
            return null;

        return MedicamentoDao.getAllMedicamentos(LoginService.getIdUsuarioLogado(req));
    }

    public static Medicamento getMedicamento(Request req) {
        Medicamento med = new Medicamento();
        med = MedicamentoDao.getMedicamento(Integer.parseInt(req.params("id")));
        return med;
    }

    public static Resposta deletarMedicamento(Request req) {
        MedicamentoDao.deletarMedicamento(Integer.parseInt(req.params("id")));
        Resposta r = new Resposta(StatusResposta.SUCESSO);
        return r;
    }

    public static Resposta salvarMedicamento(Request req) {
        if (!LoginService.isUsuarioLogado(req))
            return null;

        Resposta resposta = new Resposta();

        Medicamento med = new Medicamento();
        String id = req.queryParams("id");
        String nome = req.queryParams("nome");
        String indicacao = req.queryParams("indicacao");
        String posologia = req.queryParams("posologia");

        // Validações
        if (ValidacaoUtil.blank(nome, indicacao, posologia)) {
            resposta.setStatus(StatusResposta.ERRO);
            resposta.setMessage("Por favor, preencha todos os campos obrigatórios");
            return resposta;
        }

        // Processamento
        med.setIdUsuario(LoginService.getIdUsuarioLogado(req));
        med.setNome(nome);
        med.setIndicacao(indicacao);
        med.setPosologia(posologia);
        // Blob (bula - pode ser null)        
        Part arquivo = null;
        try {
            arquivo = req.raw().getPart("file");            
            if(arquivo != null) {
                med.setFilename(req.queryParams("filename"));
                InputStream inputStream = arquivo.getInputStream();    
                med.setBula(IOUtils.toByteArray(inputStream));
            }            
        } catch (Exception e) {            
            e.printStackTrace();
            return new Resposta(StatusResposta.ERRO, "Erro no envio do arquivo");
        }

        if (id.isEmpty()) {
            // Insert
            int key = MedicamentoDao.inserirMedicamento(med);
            med.setId(key);
            resposta.setData(JsonUtil.getJsonElement("id", String.valueOf(med.getId())));
        } else {
            // Update
            med.setId(Integer.parseInt(id));
            MedicamentoDao.atualizarMedicamento(med);
        }

        resposta.setStatus(StatusResposta.SUCESSO);
        resposta.setMessage("Ação realizada com sucesso");
        return resposta;
    }

}