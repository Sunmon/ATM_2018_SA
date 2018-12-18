package model.server.main;
import java.sql.SQLException;
import java.util.Date;

class Bank extends Bank_connect // 은행 업무 클래스
{
    public void receipt(String banknum,int money,String pwd){ // 입금
        try{
            pstmt = con.prepareStatement(
                    "update bank set money=money+? where banknum=? and pass=?");
            pstmt.setInt(1,money);
            pstmt.setString(2,banknum);
            pstmt.setString(3,pwd);
            i = pstmt.executeUpdate();

            if (i == 0)
                System.out.println(" 비밀번호가 틀렸습니다. ");

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        balance(banknum,pwd);
    }

    public void defrayment(String banknum,int money,String pwd){ // 출금
        try{
            pstmt = con.prepareStatement(
                    "update bank set money=money-?-? where banknum=? and pass=?");
            pstmt.setInt(1,money);
            pstmt.setString(2,banknum);
            pstmt.setString(3,pwd);
            i = pstmt.executeUpdate();

            if (i == 0)
                System.out.println(" 비밀번호가 틀렸습니다. ");

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        balance(banknum,pwd);
    }

    public void remittance(String banknum,String Tobanknum,String pwd,int money){ // 송금
        try{
            pstmt = con.prepareStatement(
                    "select banknum,name,money from bank where banknum=? and pass=?");
            pstmt.setString(1,banknum);
            pstmt.setString(2,pwd);
            rs = pstmt.executeQuery();

            if(rs.next()){
                pstmt = con.prepareStatement(
                        "Update bank set money=money+? where banknum=?");
                pstmt.setInt(1,money);
                pstmt.setString(2,Tobanknum);
                i = pstmt.executeUpdate();
                if (i==0)
                    System.out.println("받으실분의 계좌번호를 잘못 입력하셨습니다.");
                else{
                    defrayment(banknum,money,pwd);
                }
            }
            else
                System.out.println(" 잘못 입력하셨습니다." );
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void balance(String banknum,String pwd){ // db
        try{
            Date dt = new Date();
            pstmt = con.prepareStatement(
                    "select banknum,name,money from bank where banknum=? and pass=?");
            pstmt.setString(1,banknum);
            pstmt.setString(2,pwd);
            rs = pstmt.executeQuery();
            int commit =0;

            while(rs.next()){
                commit = rs.getInt(3);
                if (commit>=0){
                    System.out.println("정상 처리되었습니다.");
                    con.commit();
                    System.out.println(" ─────────────────────");
                    System.out.println("│               잔 액 조 회              │");
                    System.out.println(" ─────────────────────");
                    System.out.println("│      "+dt+"      │");
                    System.out.println(" ─────────────────────");
                    System.out.println(" 계좌번호 => "+rs.getString(1));
                    System.out.println(" 성    명 => "+rs.getString(2)+" 님");
                    System.out.println(" 잔    액 => "+rs.getInt(3) + " 원");
                }

                else{
                    con.rollback();
                    System.out.println(" 잔액이 없습니다. " );
                }
            }
            con.setAutoCommit(true);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            if(rs != null) {
                try{ rs.close();} catch(SQLException ex){};
            }
            if(pstmt != null) {
                try{ pstmt.close();} catch(SQLException ex){};
            }
            if(con != null) {
                try{ con.close();} catch(SQLException ex){};
            }
        }
    }
}