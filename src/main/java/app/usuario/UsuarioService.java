package app.usuario;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;

public class UsuarioService {

    
    /** 
     * 
     * Autentica um usuário
     * 
     * @param email
     * @param senha (hash)
     * @return boolean
     */
    public static boolean isAutentico(String email, String senha) {                
        Usuario u = UsuarioDao.getUsuario(email);
        if(u.getEmail() == null || u.getEmail().isEmpty()) {
            return false;           
        } else {
            if(gerarHashMd5(senha).equalsIgnoreCase(u.getSenha())) 
                return true;
            else
                return false;
        }
    }

    
    /** 
     * 
     * Retorna o id de um usuário baseado em seu login
     * 
     * @param email
     * @return int
     */
    public static int getIdUsuario(String email) {
        Usuario u = UsuarioDao.getUsuario(email);
        return u.getId();
    }

    
    /** 
     * 
     * Gera o hash para a senha do usuário
     * 
     * @param password
     * @return String
     */
    private static String gerarHashMd5(String password) {
        MessageDigest md = null;
        try {
             md = MessageDigest.getInstance("MD5");
        } catch(Exception e) {
            e.printStackTrace();
        }
        
		byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
		String md5 = DatatypeConverter.printHexBinary(digest);
        return md5;
    }

}