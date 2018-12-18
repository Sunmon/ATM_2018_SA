package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import DB.database;
import encryption.proxyAESEncryption;

public class server {
    private ServerSocket serverSock;

    public server() {
        try {
            this.serverSock = new ServerSocket(10001);
            while (true) {
                System.out.println("-------------waitting client-------------");
                Socket sock = serverSock.accept();
                DBThread care = new DBThread(sock);
                care.start();
                System.out.println("-------------client accepted-------------");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class DBThread extends Thread {
    private static final int INIT = 0, DIPOSIT = 1, WITHDRAW = 2, TRANSFER = 3; // for type
    private Socket sock;
    private database db;
    private DataInputStream dIn;
    private DataOutputStream dOut;
    private proxyAESEncryption Aes;
    private String encryptionKey;
    private Cipher cipher;
    private SecretKeySpec secretKeySpec;

    public DBThread(Socket sock) {
        this.sock = sock;
        this.db = new database();
        this.Aes = new proxyAESEncryption();
        this.encryptionKey = "ThisIsSecretKey!";
        this.secretKeySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");
        try {
            dOut = new DataOutputStream(sock.getOutputStream());
            dIn = new DataInputStream(sock.getInputStream());
            this.cipher = Cipher.getInstance("AES");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void run() {
        String msg = null;
        int type;
        String accountFrom, accountTo, money;
        try {
            // msg = type/account1/account2/money
            int length = dIn.readInt();
            byte[] message = null;
            if(length > 0) {
                message = new byte[length];
                dIn.readFully(message, 0, message.length);
            } else {
                System.out.println("byte length = 0");
                return;
            }
            try {
                msg = Aes.decrypt(message, cipher, secretKeySpec);
                System.out.println("[after decode] : " + msg);
            } catch (Exception e) {
                msg = null;
                System.out.println("decryption failed");
                return;
            }
            StringTokenizer st = new StringTokenizer(msg, "/");
            String[] ack = new String[1];
            ack[0] = "ACK";
            String[] nack = new String[1];
            nack[0] = "NACK";
            type = Integer.parseInt(st.nextToken());
            accountFrom = st.nextToken();
            db.connect();
            db.rollback();
            if (db.isValid(accountFrom)) {
                switch (type) {
                    case INIT:
                        String[] info = db.getInfo(accountFrom);
                        if (info != null) {
                            sendMsg(info);
                        } else {
                            sendMsg(nack);
                        }
                        break;
                    case DIPOSIT:
                        money = st.nextToken();
                        if (db.putMoney(accountFrom, money)) {
                            sendMsg(ack);
                        } else {
                            sendMsg(nack);
                        }
                        break;
                    case WITHDRAW:
                        money = st.nextToken();
                        if (db.getMoney(accountFrom, money)) {
                            sendMsg(ack);
                        } else {
                            sendMsg(nack);
                        }
                        break;
                    case TRANSFER:
                        accountTo = st.nextToken();
                        if (db.isValid(accountTo)) {
                            money = st.nextToken();
                            if (db.getMoney(accountFrom, money)) {
                                if (db.putMoney(accountTo, money)) {
                                    sendMsg(ack);
                                } else {
                                    sendMsg(nack);
                                }
                            } else {
                                sendMsg(nack);
                            }
                        } else {
                            sendMsg(nack);
                        }
                        break;
                }
            } else {
                sendMsg(nack);
            }
            db.commit();
            db.disconnect();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendMsg(String[] msg) {
        int size = msg.length;
        String send = msg[0];
        for (int i = 1; i < size; i++) {
            send = send + "/" + msg[i];
        }
        System.out.println("[before encode] : " + send);
        byte[] encodedByte = null;
        try {
            encodedByte = Aes.encrypt(send, cipher, secretKeySpec);
            dOut.writeInt(encodedByte.length);
            dOut.write(encodedByte);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
