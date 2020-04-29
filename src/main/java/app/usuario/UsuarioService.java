package app.usuario;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;

public class UsuarioService {

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

    public static int getIdUsuario(String email) {
        Usuario u = UsuarioDao.getUsuario(email);
        return u.getId();
    }

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