package network;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import encryption.AESEncryption;

public class client {
    private static final int INIT = 0, DIPOSIT = 1, WITHDRAW = 2, TRANSFER = 3; // for type
    private Socket sock;
    private DataInputStream dIn;
    private DataOutputStream dOut;
    private String servAddr;
    private AESEncryption Aes;
    private String encryptionKey;
    private Cipher cipher;
    private SecretKeySpec secretKeySpec;

    public client(String servAddr) {
        this.servAddr = servAddr;
        this.Aes = new AESEncryption();
        this.encryptionKey = "ThisIsSecretKey!";
        this.secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");
        try {
            this.cipher = Cipher.getInstance("AES");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean connect() {
        this.sock = null;
        this.dIn = null;
        this.dOut = null;
        try {
            if (servAddr != null) {
                sock = new Socket(servAddr, 10001);
                dOut = new DataOutputStream(sock.getOutputStream());
                dIn = new DataInputStream(sock.getInputStream());
                System.out.println("[client] connect success");
                return true;
            } else {
                System.out.println("[client] servAddr is invalid");
            }
        } catch (Exception e) {
            System.out.println("[client] connect failed");
        }
        return false;
    }

    public void disconnect() {
        try {
            this.sock.close();
            this.dIn.close();
            this.dOut.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendToServer(String msg) { // msg = type/account1/account2/money ->type = info,
        try {
            byte[] encodedByte = Aes.encrypt(msg, cipher, secretKeySpec);
            dOut.writeInt(encodedByte.length);
            dOut.write(encodedByte);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String recvFromServer() {
        String msg = null;
        try {
            int length = dIn.readInt();
            if (length > 0) {
                byte[] encodedByte = new byte[length];
                dIn.readFully(encodedByte, 0, encodedByte.length);
                msg = Aes.decrypt(encodedByte, cipher, secretKeySpec);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            msg = null;
            System.out.println("decryption failed");
        }
        return msg;
    }
}
