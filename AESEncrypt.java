import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * Utility class to encrypt a string using an AES 128 bit private key. 
 * The encryption is compatible with Salesforce Apex Crypto classes so they can decrypt, given the private key. 
 */
public class AESEncrypt {
    
    public static String encodeAES(String privateKey, String data) throws Exception {        
        final int AES_KEYLENGTH = 128;    
        byte[] iv = new byte[AES_KEYLENGTH / 8];
        SecureRandom prng = new SecureRandom();
        prng.nextBytes(iv);
        
        Cipher aesCipherForEncryption = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        
        aesCipherForEncryption.init(Cipher.ENCRYPT_MODE, 
                                new SecretKeySpec(DatatypeConverter.parseBase64Binary(privateKey), "AES"), 
                                new IvParameterSpec(iv));
        
        byte[] byteDataToEncrypt = data.getBytes();
        byte[] byteCipherText = aesCipherForEncryption.doFinal(byteDataToEncrypt);

        byte[] ivPlusCipher = new byte[iv.length + byteCipherText.length];
        System.arraycopy(iv, 0, ivPlusCipher, 0, iv.length);
        System.arraycopy(byteCipherText, 0, ivPlusCipher, iv.length, byteCipherText.length);
        
        return DatatypeConverter.printBase64Binary(ivPlusCipher);
    }
    
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java AESEncrypt <private-key> <text>");
            System.exit(-1);
        }

        System.out.println("Cipher Text = " + encodeAES(args[0], args[1]));
    }
}
