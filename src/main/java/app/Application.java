package app;

import static spark.Spark.*;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletResponse;
import app.util.*;
import app.consulta.AnexoConsulta;
import app.consulta.ConsultaService;
import app.login.LoginService;
import app.medicamento.*;
import app.paciente.PacienteService;
import app.tratamento.TratamentoService;
import com.google.gson.Gson;

public class Application {

    public static boolean isProducao = false;

    public static void main(String[] args) {
        if (System.getenv("DATABASE_URL") != null)
            isProducao = true;

        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        // This tells our app that if Heroku sets a port for us, we need to use that
        // port.
        // Otherwise, if they do not, continue using port 4567.
        if (process.environment().get("PORT") != null) {            
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);

        // Raiz dos arquivos estáticos 
        staticFiles.location("/public");

        // dez minutos
        staticFiles.expireTime(600L);

        // Configuração para SSL
        if (isProducao)
            secure("keystore.jks", "minhapomba", null, null);            

        /**
         * -------------- Login --------------
         */
        post("/login", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(LoginService.login(req));
        });

        get("/logado", (req, res) -> {
            res.type("application/json");
            Resposta resposta = new Resposta();
            if (LoginService.isUsuarioLogado(req)) {
                resposta.setStatus(StatusResposta.SUCESSO);
            } else {
                resposta.setStatus(StatusResposta.ERRO);
            }
            return new Gson().toJson(resposta);
        });

        /**
         * -------------- Medicamentos --------------
         */
        get("/medicamentos", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new Resposta(StatusResposta.SUCESSO,
                    new Gson().toJsonTree(MedicamentoService.getAllMedicamentos(req))));
        });

        get("/medicamentos/:id", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new Resposta(StatusResposta.SUCESSO,
                    new Gson().toJsonTree(MedicamentoService.getMedicamento(req))));
        });

        delete("/medicamentos/:id", (req, res) -> {
            res.type("application/json");
            Resposta r = MedicamentoService.deletarMedicamento(req);
            return new Gson().toJson(r);
        });

        post("/medicamentos", (req, res) -> {
            res.type("application/json");
            MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/tmp");
            req.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
            Resposta r = MedicamentoService.salvarMedicamento(req);
            return new Gson().toJson(r);
        });

        get("/baixar-bula/:id", (req, res) -> {
            Medicamento m = MedicamentoService.getMedicamento(req);
            byte[] bula = m.getBula();

            HttpServletResponse raw = res.raw();
            raw.getOutputStream().write(bula);
            raw.getOutputStream().flush();
            raw.getOutputStream().close();
            res.header("Content-Type", "application/download");
            res.header("Content-Disposition", "attachment; filename=" + m.getFilename());

            return res.raw();
        });

        /**
         * -------------- Pacientes --------------
         */
        get("/pacientes", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(
                    new Resposta(StatusResposta.SUCESSO, new Gson().toJsonTree(PacienteService.getAllPacientes(req))));
        });

        get("/pacientes/:id", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(
                    new Resposta(StatusResposta.SUCESSO, new Gson().toJsonTree(PacienteService.getPaciente(req))));
        });

        delete("/pacientes/:id", (req, res) -> {
            res.type("application/json");
            Resposta r = PacienteService.deletarPaciente(req);
            return new Gson().toJson(r);
        });

        post("/pacientes", (req, res) -> {
            res.type("application/json");
            Resposta r = PacienteService.salvarPaciente(req);
            return new Gson().toJson(r);
        });

        /**
         * -------------- Tratamentos --------------
         */
        get("/tratamentos", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new Resposta(StatusResposta.SUCESSO,
                    new Gson().toJsonTree(TratamentoService.getAllTratamentos(req))));
        });

        get("/tratamentos/:id", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(
                    new Resposta(StatusResposta.SUCESSO, new Gson().toJsonTree(TratamentoService.getTratamento(req))));
        });

        get("/tratamento-opcoes-medicamentos/:id", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(new Resposta(StatusResposta.SUCESSO,
                    new Gson().toJsonTree(TratamentoService.getOpcoesMedicamentos(req))));
        });

        post("/tratamentos", (req, res) -> {
            res.type("application/json");
            Resposta r = TratamentoService.salvarTratamento(req);
            return new Gson().toJson(r);
        });

        delete("/tratamentos/:id", (req, res) -> {
            res.type("application/json");
            Resposta r = TratamentoService.deletarTratamento(req);
            return new Gson().toJson(r);
        });

        /**
         * -------------- Consultas --------------
         */
        get("/consultas", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(
                    new Resposta(StatusResposta.SUCESSO, new Gson().toJsonTree(ConsultaService.getAllConsultas(req))));
        });

        get("/consultas/:id", (req, res) -> {
            res.type("application/json");
            return new Gson().toJson(
                    new Resposta(StatusResposta.SUCESSO, new Gson().toJsonTree(ConsultaService.getConsulta(req))));
        });

        post("/consultas", (req, res) -> {
            res.type("application/json");
            Resposta r = ConsultaService.salvarConsulta(req);
            return new Gson().toJson(r);
        });

        delete("/consultas/:id", (req, res) -> {
            res.type("application/json");
            Resposta r = ConsultaService.deletarConsulta(req);
            return new Gson().toJson(r);
        });

        post("/adicionar-anexo-consulta", (req, res) -> {
            res.type("application/json");
            MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/tmp");
            req.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
            Resposta r = ConsultaService.adicionarAnexo(req);
            return new Gson().toJson(r);
        });

        get("/anexos-consultas/:id", (req, res) -> {
            res.type("application/json");
            Resposta r = ConsultaService.getAnexosConsulta(req);
            return new Gson().toJson(r);
        });

        delete("/anexo-consulta/:id", (req, res) -> {
            res.type("application/json");
            Resposta r = ConsultaService.deletarAnexoConsulta(req);
            return new Gson().toJson(r);
        });

        get("/baixar-anexo-consulta/:id", (req, res) -> {
            AnexoConsulta anexo = ConsultaService.getAnexoConsulta(req);

            HttpServletResponse raw = res.raw();
            raw.getOutputStream().write(anexo.getArquivo());
            raw.getOutputStream().flush();
            raw.getOutputStream().close();
            res.header("Content-Type", "application/download");
            res.header("Content-Disposition", "attachment; filename=" + anexo.getFilename());

            return res.raw();
        });

    }

}