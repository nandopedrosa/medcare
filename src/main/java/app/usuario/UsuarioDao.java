package app.usuario;

import app.util.DaoUtil;
import org.sql2o.Query;

public class UsuarioDao {

    public static Usuario getUsuario(String email) {
        Query query = 
            DaoUtil.getConexao()
            .createQuery("select * from usuario where email = :email")            
            .addParameter("email", email);            

        Usuario retorno = query.executeAndFetchFirst(Usuario.class);
        return retorno;
    }

}