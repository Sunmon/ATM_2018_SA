package model.server.main;
import java.sql.*;


//FIXME: Bank_Connect이 클래스랑 database랑 내용 똑같이 겹침

class Bank_connect
{
    Connection con;
    PreparedStatement pstmt;
    private final String jdbc_url="jdbc:mysql://117.16.137.108:3306/"
            + "user_201411140?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8";;
    private final String db_id="user_201411140";
    private final String db_pw="201411140";
    ResultSet rs;
    static int i = -1;
    static int commission;

    Bank_connect(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(jdbc_url, db_id, db_pw);
            con.setAutoCommit(false);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
