
package controller;
import java.util.StringTokenizer;
import network.client;
public class mainController {
        private static final int INIT = 0, DIPOSIT = 1, WITHDRAW = 2, TRANSFER = 3; // for type
        private client client;
        private String accountId;
        private String balance;
        private String pw;
        private String name;


        public mainController(String servAddr) {
            this.client = new client(servAddr);
            this.accountId = null;
            this.balance = null;
            this.pw = null;
            this.name = null;
        }

        public boolean isValid(String accountId) {
            this.accountId = accountId;
            if(client.connect()) {
                String msg = "0/" + this.accountId;
                client.sendToServer(msg);
                String info = client.recvFromServer();
                client.disconnect();
                if(info.equals("NACK")) {
                    return false;
                }
                StringTokenizer st = new StringTokenizer(info, "/");
                this.pw = st.nextToken();
                this.name = st.nextToken();
                this.balance = st.nextToken();
            } else {
                System.out.println("connect failed");
                return false;
            }
            return true;
        }

        //비밀번호 일치하면 true, 틀리면 false
        public boolean matchingPw(String pw) {
            if(this.pw.equals(pw)) {
                return true;
            }
            return false;
        }

        public String getBalance() {
            return this.balance;
        }

        public boolean diposit(String money) {
            if(client.connect()) {
                String msg = "1/" + this.accountId + "/" + money;
                client.sendToServer(msg);
                String info = client.recvFromServer();
                client.disconnect();
                if(info.equals("ACK")) {
                    return true;
                }
            } else {
                System.out.println("connect failed");
            }
            return false;
        }

        public boolean withdraw(String money) {
            if(client.connect()) {
                String msg = "2/" + this.accountId + "/" + money;
                client.sendToServer(msg);
                String info = client.recvFromServer();
                client.disconnect();
                if(info.equals("ACK")) {
                    return true;
                }
            } else {
                System.out.println("connect failed");
            }
            return false;
        }

        public boolean transfer(String accountTo, String money) {
            if(client.connect()) {
                String msg = "3/" + this.accountId + "/" + accountTo + "/" + money;
                client.sendToServer(msg);
                String info = client.recvFromServer();
                client.disconnect();
                if(info.equals("ACK")) {
                    return true;
                }
            } else {
                System.out.println("connect failed");
            }
            return false;
        }
    }

