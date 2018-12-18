package encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryption {


    public byte[] encrypt(String target,Cipher cipher, SecretKeySpec secretKeySpec) throws NoSuchAlgorithmException
            , NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException
            , BadPaddingException, UnsupportedEncodingException
    {
        // 암호화 Chipher 초기화
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        // 암호화 완료
        byte[] encryptBytes = cipher.doFinal(target.getBytes("UTF-8"));
        return encryptBytes;
    }

    public String decrypt(byte[] encryptBytes,Cipher cipher, SecretKeySpec secretKeySpec) throws NoSuchAlgorithmException
            , NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException
            , BadPaddingException, UnsupportedEncodingException
    {
        String decodingStr;
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        decodingStr=new String(decryptBytes,"UTF-8");

        return decodingStr;
    }

}

