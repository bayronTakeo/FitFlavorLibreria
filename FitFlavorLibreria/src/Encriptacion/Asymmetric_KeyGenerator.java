package Encriptacion;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Asymmetric_KeyGenerator {

    public void generatePrivateKey(String directoryPath) {

        KeyPairGenerator generator;
        try {
            generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(1024);
            KeyPair keypair = generator.generateKeyPair();
            PublicKey publicKey = keypair.getPublic();
            PrivateKey privateKey = keypair.getPrivate();

            // Crear la carpeta si no existe
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Public
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
            FileOutputStream fileOutputStream = new FileOutputStream(directoryPath + File.separator + "Public.key");
            fileOutputStream.write(x509EncodedKeySpec.getEncoded());
            fileOutputStream.close();

            // Private
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            fileOutputStream = new FileOutputStream(directoryPath + File.separator + "Private.key");
            fileOutputStream.write(pKCS8EncodedKeySpec.getEncoded());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Asymmetric_KeyGenerator asimetricoRSA_KeyGenerator = new Asymmetric_KeyGenerator();
        // Especifica la ruta del directorio donde quieres crear la carpeta y los archivos
        String directoryPath = "C:\\files";
        asimetricoRSA_KeyGenerator.generatePrivateKey(directoryPath);
        System.out.println("Ficheros de Clave Generados!");
    }
}
