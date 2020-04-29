package app.util;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DaoUtil {
        
    private static Connection conexao;
    
    public static Connection getConexao() {                       
        final boolean isProducao = false; 
        if (conexao == null) {
            if(!isProducao) {
                Sql2o sql2o = new Sql2o("jdbc:sqlite:/Users/fernando/Desktop/repos/medcare/data.db", null, null);                
                conexao = sql2o.open(); 
            }            
        }
        
        return conexao;        
    }
    

}