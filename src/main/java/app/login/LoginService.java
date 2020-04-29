package app.login;

import spark.*;
import app.util.Resposta;
import app.usuario.UsuarioService;
import app.util.StatusResposta;

public class LoginService {

    public static Resposta login(Request req) {
        String email = req.queryParams("email");
        String senha = req.queryParams("senha");
        Resposta resposta = new Resposta();

        if (email.isEmpty() || senha.isEmpty()) {
            resposta.setStatus(StatusResposta.ERRO);
            resposta.setMessage("Por favor, digite usuário e senha");            
        } else {
            if(UsuarioService.isAutentico(email, senha)) {
                resposta.setStatus(StatusResposta.SUCESSO);
                req.session().attribute("usuarioAtual",email);

            } else {
                resposta.setStatus(StatusResposta.ERRO);
                resposta.setMessage("Email ou Senha incorretos");            
            }
        }
        return resposta;
    }

    public static boolean isUsuarioLogado(Request req) {
        return req.session().attribute("usuarioAtual") != null;                    
    }

    public static int getIdUsuarioLogado(Request req) {
        return UsuarioService.getIdUsuario(req.session().attribute("usuarioAtual"));
    }

}