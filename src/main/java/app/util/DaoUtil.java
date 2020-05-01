package app.util;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import app.Application;
import java.net.URI;
import java.net.URISyntaxException;

public class DaoUtil {

    private static Connection conexao;
    private static URI dbUri;

    /**
     * Retorna uma conexão ao banco de dados. Garante que só existe uma única
     * conexão (Singleton).
     * 
     * @return Connection
     */
    public static Connection getConexao() {
        if (conexao == null) {
            if (!Application.isProducao) {
                //Localhost (SQLITE)
                Sql2o sql2o = new Sql2o("jdbc:sqlite:/Users/fernando/Desktop/repos/medcare/data.db", null, null);
                conexao = sql2o.open();
            } else {
                //HEROKU (POSTGRES)
                try {
                    dbUri = new URI(System.getenv("DATABASE_URL"));
                    int port = dbUri.getPort();
                    String host = dbUri.getHost();
                    String path = dbUri.getPath();
                    String username = (dbUri.getUserInfo() == null) ? null : dbUri.getUserInfo().split(":")[0];
                    String password = (dbUri.getUserInfo() == null) ? null : dbUri.getUserInfo().split(":")[1];
                    Sql2o sql2o = new Sql2o("jdbc:postgresql://" + host + ":" + port + path, username, password);
                    conexao = sql2o.open();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }
        return conexao;
    }

}