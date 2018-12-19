package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import encryption.proxyAESEncryption;

public class client {
    private static final int INIT = 0, DIPOSIT = 1, WITHDRAW = 2, TRANSFER = 3; // for type
    private Socket sock;
    private DataInputStream dIn;
    private DataOutputStream dOut;
    private String servAddr;
    private proxyAESEncryption Aes;
    private String encryptionKey;
    private Cipher cipher;
    private SecretKeySpec secretKeySpec;

    private client(String servAddr) {
        this.servAddr = servAddr;
        this.Aes = new proxyAESEncryption();
        this.encryptionKey = "ThisIsSecretKey!";
        this.secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");
        try {
            this.cipher = Cipher.getInstance("AES");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static client getInstance(String servAddr){
        if(instance == null) instance = new client(servAddr);
        return instance;
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
