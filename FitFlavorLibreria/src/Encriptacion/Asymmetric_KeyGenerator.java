package Encriptacion;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Asymmetric_KeyGenerator {

    public void generatePrivateKey() {

        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            KeyPair keypair = generator.generateKeyPair();
            PublicKey publicKey = keypair.getPublic();
            PrivateKey privateKey = keypair.getPrivate();

            // Obtener el directorio actual como punto de partida para las rutas relativas
            String userHomeDir = System.getProperty("user.home");

            // Public
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
            String publicFilePath = Paths.get(userHomeDir, "Public.key").toString();
            FileOutputStream fileOutputStream = new FileOutputStream(publicFilePath);
            fileOutputStream.write(x509EncodedKeySpec.getEncoded());
            fileOutputStream.close();

            // Private
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            String privateFilePath = Paths.get(userHomeDir, "Private.key").toString();
            fileOutputStream = new FileOutputStream(privateFilePath);
            fileOutputStream.write(pKCS8EncodedKeySpec.getEncoded());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Asymmetric_KeyGenerator asimetricoRSA_KeyGenerator = new Asymmetric_KeyGenerator();
        asimetricoRSA_KeyGenerator.generatePrivateKey();
        System.out.println("Ficheros de Clave Generados!");
    }
}
