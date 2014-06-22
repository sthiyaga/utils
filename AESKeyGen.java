import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

/**
 * Utility class to generate an AES 128 (or 192 or 256) bit private key
 */
public class AESKeyGen {
    
    public static String generatePrivateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();
        
        return DatatypeConverter.printBase64Binary(secretKey.getEncoded()); 
    }
    
    public static void main(String[] args) throws Exception {
        String privateKey = generatePrivateKey();
        
        System.out.println("Private Key = " + privateKey);
    }
}
