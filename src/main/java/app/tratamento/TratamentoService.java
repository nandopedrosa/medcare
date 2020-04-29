package app.tratamento;

import java.util.Arrays;
import java.util.List;
import app.util.ValidacaoUtil;
import spark.*;
import app.login.LoginService;
import app.medicamento.Medicamento;
import app.medicamento.MedicamentoService;
import app.util.JsonUtil;
import app.util.Resposta;
import app.util.StatusResposta;

public class TratamentoService {

    public static List<Tratamento> getAllTratamentos(Request req) {
        if (!LoginService.isUsuarioLogado(req))
            return null;

        return TratamentoDao.getAllTratamentos(Integer.parseInt(req.queryParams("idPaciente")));
    }

    public static Tratamento getTratamento(Request req) {
        if (!LoginService.isUsuarioLogado(req))
            return null;

        Tratamento trat = new Tratamento();
        trat = TratamentoDao.getTratamento(Integer.parseInt(req.params("id")));
        return trat;
    }

    public static Resposta deletarTratamento(Request req) {
        if (!LoginService.isUsuarioLogado(req))
            return null;

        TratamentoDao.deletarTratamento(Integer.parseInt(req.params("id")));
        Resposta r = new Resposta(StatusResposta.SUCESSO);
        return r;
    }

    public static Resposta salvarTratamento(Request req) {
        if (!LoginService.isUsuarioLogado(req))
            return null;

        Resposta resposta = new Resposta();

        Tratamento trat = new Tratamento();
        String id = req.queryParams("id");
        String idPaciente = req.queryParams("idPaciente");
        String quadro = req.queryParams("quadro");
        //Comma separated ids
        String idMedicamentosStr = req.queryParams("idMedicamentos");
        
         // Validações
         if (ValidacaoUtil.blank(quadro, idPaciente, idMedicamentosStr)) {
            resposta.setStatus(StatusResposta.ERRO);
            resposta.setMessage("Por favor, preencha todos os campos obrigatórios");
            return resposta;
        }

        // Processamento
        trat.setQuadro(quadro);
        trat.setIdPaciente(Integer.parseInt(idPaciente));        
        List<String> idMedicamentos = Arrays.asList(idMedicamentosStr.split("\\s*,\\s*"));
                

        if (id.isEmpty()) {
            // Insert
            int key = TratamentoDao.inserirTratamento(trat, idMedicamentos);
            trat.setId(key);
            resposta.setData(JsonUtil.getJsonElement("id", String.valueOf(trat.getId())));
        } else {
            // Update
            trat.setId(Integer.parseInt(id)); 
            TratamentoDao.atualizarTratamento(trat, idMedicamentos);
        }

        resposta.setStatus(StatusResposta.SUCESSO);
        resposta.setMessage("Ação realizada com sucesso");
        return resposta;
    }

    public static List<Medicamento> getOpcoesMedicamentos(Request req) {     
        Tratamento trat = getTratamento(req);
        List<Medicamento> medicamentosEscolhidos = trat.getMedicamentos() ;
        List<Medicamento> opcoesMedicamentos = MedicamentoService.getAllMedicamentos(req);
        opcoesMedicamentos.removeAll(medicamentosEscolhidos);
        

        return opcoesMedicamentos;

    }

}