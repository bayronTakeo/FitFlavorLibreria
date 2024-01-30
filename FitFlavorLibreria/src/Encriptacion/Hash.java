package Encriptacion;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Hash {

    public static String hashText(String texto) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(texto.getBytes(StandardCharsets.UTF_8));
            byte hashedText[] = null;

            messageDigest.update(hashedBytes);
            hashedText = messageDigest.digest();

            return new String(hashedText);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejo de errores
        }
    }

}
