package model.server.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class database {
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;

    public void connect() {	// DB서버에 접속하기 위한 메서드
        String dbUrl = "jdbc:mysql://117.16.137.108:3306/"
                + "user_201411140?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8";
        String dbId = "user_201411140";
        String dbPw = "201411140";
        try {
            conn = DriverManager.getConnection(dbUrl, dbId, dbPw);
            System.out.println("Connected to db");
        } catch (Exception e) {
            System.out.println("Connected failed");
            System.out.println(e.getMessage());
        }
    }

    public void disconnect() {	// 연결되어 있던 stmt 와 pstmt 를 초기화해줌
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void commit() {	// 현재까지 진행된 쿼리를 커밋함
        String sql = "commit;";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rollback() {	// 커밋되지 않은 쿼리를 롤백
        String sql = "rollback;";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getInfo(String accountId) {		// 초기 카드 입력시 정보조회 목적으로 호출함 (but 실제로는 비밀번호만 사용될듯)
        String sql = "select pw, name, balance from account where id = ?;";
        ResultSet rs;
        String[] info = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, accountId);
            rs = pstmt.executeQuery();
            info = new String[3];
            rs.next();
            info[0] = rs.getString(1);	//pw
            info[1] = rs.getString(2);	//name
            info[2] = rs.getString(3);	//balance
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    public boolean isValid(String accountId) {		// 계좌정보가 유효한지 확인함. 유효하면 true
		String sql = "select id from account where id = ?;";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, accountId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println(accountId + " is valid account");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(accountId + " is invalid account");
		return false;
	}

    public void putMoney(String accountId, String money) {		// 계좌에 돈을 넣음
        String sqlUpdate = "update account set balance = ? where id = ?;";
        String sqlRead = "select balance from account where id = ?;";
        try {
            pstmt = conn.prepareStatement(sqlRead);
            pstmt.setString(1, accountId);


            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String balance = rs.getString(1);
            balance = plus(balance, money);
            pstmt = conn.prepareStatement(sqlUpdate);
            pstmt.setString(1, balance);
            pstmt.setString(2, accountId);
            pstmt.executeUpdate();
            System.out.println("put into " + accountId + " money ( " + money + " )\tbalance = " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getMoney(String accountId, String money) {	// 계좌에서 돈을 뺌
        String sqlUpdate = "update account set balance = ? where id = ?;";
        String sqlRead = "select balance from account where id = ?;";
        try {
            pstmt = conn.prepareStatement(sqlRead);
            pstmt.setString(1, accountId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            String balance = rs.getString(1);
            if (isBig(money, balance)) {
                System.out.println("balance is not enough\tbalance = " + balance);
                return false; // 잔액부족
            }
            balance = minus(balance, money);
            pstmt = conn.prepareStatement(sqlUpdate);
            pstmt.setString(1, balance);
            pstmt.setString(2, accountId);
            pstmt.executeUpdate();
            System.out.println("get money ( " + money + " ) from " + accountId + "\tbalance = " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
	
    // 이하함수는 balance 를 계산할 때 overflow를 막기 위해 String으로 연산하기 위한 함수들

    private boolean isBig(String n1, String n2) { // n1 > n2면 true 리턴.
        if (n1.length() > n2.length())
            return true;
        else if (n1.length() == n2.length()) {
            for (int i = 0; i < n1.length(); i++) {
                if (n1.charAt(i) == n2.charAt(i))
                    continue;
                if (n1.charAt(i) < n2.charAt(i))
                    return false;
                return true;
            }
        }
        return false;
    }

    private String minus(String n1, String n2) { // n1 >= n2 인 상황을 가정하고 있음
        if (n1.equals(n2))
            return "0";
        String sum = n1.substring(0, n1.length() - n2.length());
        for (int i = 0; i < n2.length(); i++) {
            char a = n1.charAt(n1.length() - n2.length() + i);
            char b = n2.charAt(i);
            if (a == b) {
                sum = sum + '0';
            } else if (isBig(a + "", b + "")) {
                sum = sum + String.valueOf(Integer.parseInt(a + "") - Integer.parseInt(b + ""));
            } else {
                int j, c;
                for (j = 0; sum.charAt(sum.length() - 1 - j) == '0'; j++)
                    ;
                if (sum.length() - 1 - j == 0) {
                    if (sum.charAt(0) == '1') {
                        sum = "";
                        for (int k = 0; k < j; k++)
                            sum = sum + '9';
                    } else {
                        c = Integer.parseInt(sum.charAt(0) + "") - 1;
                        sum = String.valueOf(c);
                        for (int k = 0; k < j; k++) {
                            sum = sum + '9';
                        }
                    }
                }
                c = Integer.parseInt("1" + a) - Integer.parseInt("" + b);
                sum = sum + String.valueOf(c);
            }
        }
        if (sum.length() > 0) {
            while (sum.substring(0, 1).equals("0"))
                sum = sum.substring(1, sum.length());
        }
        return sum;
    }

    private String plus(String n1, String n2) {
        String sum = "";
        boolean up = false;
        if (n1.length() > n2.length()) {
            for (int i = 1; i <= n2.length(); i++) {
                char a = n1.charAt(n1.length() - i);
                char b = n2.charAt(n2.length() - i);
                if (!up) {
                    if (Integer.parseInt(a + "") + Integer.parseInt(b + "") > 9) {
                        sum = String.valueOf(Integer.parseInt(a + "") + Integer.parseInt(b + "")).charAt(1) + sum;
                        up = true;
                    } else {
                        sum = String.valueOf(Integer.parseInt(a + "") + Integer.parseInt(b + "")) + sum;
                        up = false;
                    }
                } else {
                    if (Integer.parseInt(a + "") + Integer.parseInt(b + "") > 8) {
                        sum = String.valueOf(Integer.parseInt(a + "") + Integer.parseInt(b + "") + 1).charAt(1) + sum;
                        up = true;
                    } else {
                        sum = String.valueOf(Integer.parseInt(a + "") + Integer.parseInt(b + "") + 1) + sum;
                        up = false;
                    }
                }
            }
            if (up) {
                for (int i = n1.length() - n2.length() - 1; i >= 0; i--) {
                    char a = n1.charAt(i);
                    if (a == '9') {
                        sum = '0' + sum;
                    } else {
                        sum = n1.substring(0, i + 1) + sum;
                        break;
                    }
                }
                if (sum.charAt(0) == '0')
                    sum = '1' + sum;
            } else {
                sum = n1.substring(0, n1.length() - n2.length()) + sum;
            }

        } else {
            for (int i = 1; i <= n1.length(); i++) {
                char a = n1.charAt(n1.length() - i);
                char b = n2.charAt(n2.length() - i);
                if (!up) {
                    if (Integer.parseInt(a + "") + Integer.parseInt(b + "") > 9) {
                        sum = String.valueOf(Integer.parseInt(a + "") + Integer.parseInt(b + "")).charAt(1) + sum;
                        up = true;
                    } else {
                        sum = String.valueOf(Integer.parseInt(a + "") + Integer.parseInt(b + "")) + sum;
                        up = false;
                    }
                } else {
                    if (Integer.parseInt(a + "") + Integer.parseInt(b + "") > 8) {
                        sum = String.valueOf(Integer.parseInt(a + "") + Integer.parseInt(b + "") + 1).charAt(1) + sum;
                        up = true;
                    } else {
                        sum = String.valueOf(Integer.parseInt(a + "") + Integer.parseInt(b + "") + 1) + sum;
                        up = false;
                    }
                }
            }
            if (up) {
                if (n2.length() == n1.length())
                    sum = '1' + sum;
                else {
                    for (int i = n2.length() - n1.length() - 1; i >= 0; i--) {
                        char a = n2.charAt(i);
                        if (a == '9') {
                            sum = '0' + sum;
                        } else {
                            sum = n2.substring(0, i + 1) + sum;
                            break;
                        }
                    }
                    if (sum.charAt(0) == '0')
                        sum = '1' + sum;
                }
            } else {
                sum = n2.substring(0, n2.length() - n1.length()) + sum;
            }
        }
        return sum;
    }
}
