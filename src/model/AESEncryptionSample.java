package model;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryptionSample {
    public static void main(String[] args) throws NoSuchAlgorithmException
            , NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException
            , BadPaddingException, UnsupportedEncodingException {


        byte[] IncodingBytes;
        String DecodingStr;
        AESEncryption Aes = new AESEncryption();
        // 암호화에 사용할 키, 디폴트로 128bit(16Byte)
        String encryptionKey = "1234567890123456";
        // 암호화할 문자열
        String target = "Name: 김선정";
        // AES로 암호화 =================================================
        // AES Cipher 객체 생성
        Cipher cipher = Cipher.getInstance("AES");
        // 암호화 Chipher 초기화
        SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(),"AES");


        IncodingBytes=Aes.encrypt(target, cipher, secretKeySpec); //IncodingBytes에 인코딩된 것 저장
        System.out.println(new String(IncodingBytes));

        DecodingStr=Aes.decrypt(IncodingBytes, cipher, secretKeySpec); //DecodingStr에 디코딩된것 저장
        System.out.println(DecodingStr);
    }
}

