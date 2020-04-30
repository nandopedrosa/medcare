package app.paciente;

import java.util.List;
import app.util.ValidacaoUtil;
import spark.*;
import app.login.LoginService;
import app.util.JsonUtil;
import app.util.Resposta;
import app.util.StatusResposta;

public class PacienteService {

    
    /** 
     * 
     * Retorna todos os pacientes de um usuário
     * 
     * @param req
     * @return List<Paciente>
     */
    public static List<Paciente> getAllPacientes(Request req) {
        if (!LoginService.isUsuarioLogado(req))
            return null;

        List<Paciente> pacientes = PacienteDao.getAllPacientes(LoginService.getIdUsuarioLogado(req));

        for (Paciente p : pacientes) {
            p.atualizarIdade();
        }

        return pacientes;
    }

    
    /** 
     * 
     * Retorna um paciente específico de um usuário
     * 
     * @param req
     * @return Paciente
     */
    public static Paciente getPaciente(Request req) {
        if (!LoginService.isUsuarioLogado(req))
            return null;
        
        Paciente p = new Paciente();
        p = PacienteDao.getPaciente(Integer.parseInt(req.params("id")));        
        p.atualizarIdade();
        return p;
    }

    
    /** 
     * 
     * Deleta um paciente - cuidado
     * 
     * @param req
     * @return Resposta
     */
    public static Resposta deletarPaciente(Request req) {
        if (!LoginService.isUsuarioLogado(req))
            return null;
        
        PacienteDao.deletarPaciente(Integer.parseInt(req.params("id")));
        Resposta r = new Resposta(StatusResposta.SUCESSO);
        return r;
    }

    
    /** 
     * 
     * Insere ou atualiza um paciente
     * 
     * @param req
     * @return Resposta
     */
    public static Resposta salvarPaciente(Request req) {
        if (!LoginService.isUsuarioLogado(req))
            return null;

        Resposta resposta = new Resposta();

        Paciente p = new Paciente();
        String id = req.queryParams("id");
        String nome = req.queryParams("nome");
        String sexo = req.queryParams("sexo");
        String dataStr = req.queryParams("dataNascimento");
        String pesoStr = req.queryParams("peso");

        // Validações
        if (ValidacaoUtil.blank(nome, sexo, dataStr)) {
            resposta.setStatus(StatusResposta.ERRO);
            resposta.setMessage("Por favor, preencha todos os campos obrigatórios");
            return resposta;
        }

        // Processamento
        p.setIdUsuario(LoginService.getIdUsuarioLogado(req));
        p.setNome(nome);
        p.setSexo(sexo);
        p.setDataNascimento(dataStr);
        p.setPeso(pesoStr);

        if (id.isEmpty()) {
            // Insert
            int key = PacienteDao.inserirPaciente(p);
            p.setId(key);
            resposta.setData(JsonUtil.getJsonElement("id", String.valueOf(p.getId())));
        } else {
            // Update
            p.setId(Integer.parseInt(id));
            PacienteDao.atualizarPaciente(p);
        }

        resposta.setStatus(StatusResposta.SUCESSO);
        resposta.setMessage("Ação realizada com sucesso");
        return resposta;
    }

}