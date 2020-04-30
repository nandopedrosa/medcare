package app.usuario;

import app.util.DaoUtil;
import org.sql2o.Query;

public class UsuarioDao {

    
    /** 
     * Retorna um usuário a partir do seu email (que deve ser único)
     * 
     * @param email
     * @return Usuario
     */
    public static Usuario getUsuario(String email) {
        Query query = 
            DaoUtil.getConexao()
            .createQuery("select * from usuario where email = :email")            
            .addParameter("email", email);            

        Usuario retorno = query.executeAndFetchFirst(Usuario.class);
        return retorno;
    }

}