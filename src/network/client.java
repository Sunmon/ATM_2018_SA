package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class client {
    private static final int INIT = 0, DIPOSIT = 1, WITHDRAW = 2, TRANSFER = 3; // for type
    private Socket sock;
    private BufferedReader br;
    private PrintWriter pw;
    private String servAddr;

    public client(String servAddr) {
        this.servAddr = servAddr;
    }

    public boolean connect() {
        this.sock = null;
        this.br = null;
        this.pw = null;
        try {
            if (servAddr != null) {
                sock = new Socket(servAddr, 10001);
                pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
                br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
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
            this.br.close();
            this.pw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendToServer(String msg) { // msg = type/account1/account2/money ->type = info,

        //여기서 암호화

        pw.println(msg);
        pw.flush();
    }

    public String recvFromServer() {
        String msg = null;
        try {
            msg = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 여기서 복호화

        return msg;
    }
}
