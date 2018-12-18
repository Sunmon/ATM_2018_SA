package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

import DB.database;

public class server {	// 실제 서비스를 담당하지 않음 -> 접속을 요청하는 client 마다 한번의 서비스를 수행할 수 있는 스레드를 생성해줌
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

// 실제 서비스를 수행하는 스레드
class DBThread extends Thread {
	private static final int INIT = 0, DIPOSIT = 1, WITHDRAW = 2, TRANSFER = 3; // for type
	private Socket sock;
	private database db;
	private BufferedReader br;
	private PrintWriter pw;

	public DBThread(Socket sock) {
		this.sock = sock;
		this.db = new database();
		try {
			pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {	// 서비스 수행
		String msg = null;
		int type;
		String accountFrom, accountTo, money;
		try {
			msg = br.readLine();	// 예상되는 msg = type/account1/account2/money or type/account/money
			if (msg == null) {
				return;
			}
			StringTokenizer st = new StringTokenizer(msg, "/");
			String[] ack = new String[1];
			ack[0] = "ACK";
			String[] nack = new String[1];
			nack[0] = "not enough money";
			type = Integer.parseInt(st.nextToken());
			accountFrom = st.nextToken();
			db.connect();
			db.rollback();
			if (db.isValid(accountFrom)) {	// 계좌정보가 valid하다면
				switch (type) {	// type 에 따라 서비스 수행
				case INIT:	// 기본정보 요구
					String[] info = db.getInfo(accountFrom);
					sendMsg(info);
					break;
				case DIPOSIT:	// 입금
					money = st.nextToken();
					db.putMoney(accountFrom, money);
					sendMsg(ack);
					break;
				case WITHDRAW:	// 출금
					money = st.nextToken();
					if (db.getMoney(accountFrom, money)) {
						sendMsg(ack);
					} else {
						sendMsg(nack); // 잔액부족
					}
					break;
				case TRANSFER:	// 해당계좌로부터 다른 계좌 송금
					accountTo = st.nextToken();
					if (db.isValid(accountTo)) {
						money = st.nextToken();
						if (db.getMoney(accountFrom, money)) {
							db.putMoney(accountTo, money);
							sendMsg(ack);
						} else {
							sendMsg(nack);
						}
					} else {	// 받는 계좌정보가 valid하지 않음
						String[] error = new String[0];
						error[0] = "account is invalid (2)";
						sendMsg(error);
					}
					break;
				}
			} else {	// 계좌정보가 valid 하지 않을떄 에러 메세지 발생
				String[] error = new String[0];
				error[0] = "account is invalid (1)";
				sendMsg(error);
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
		// 여기서 암호화하고 보낸다.

		pw.println(send);
		pw.flush();
	}
}
