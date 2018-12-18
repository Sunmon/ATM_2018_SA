package model.server.main;
import java.util.*;

public class ATM_cf
{
    public static void main(String[] args) throws Exception // 메인 메뉴 메소드
    {
        Bank bn = new Bank();
        System.out.println("==============================");
        System.out.println(" ○○은행 ");
        System.out.println("==============================");
        System.out.println(" 처리하실 번호를 입력하십시오. ");
        System.out.println("==============================");
        System.out.print
                (" 1.고객출금관리 \n 2.고객입금관리\n 3.고객송금관리\n" );
        System.out.print("==============================\n☞");
        String menu = readString();

        if (menu.equals("1")){ // 고객출금관리
            System.out.print(" 출금하실 계좌번호 9자리를 입력하시오.\n ☞ " );
            String banknum = readString();
            System.out.print(" 비밀번호 4자리를 입력하시오.\n ☞ ");
            String pwd = readString();
            System.out.print(" 출금하실 금액을 입력하시오.\n ☞ ");
            int money = readInt();
            bn.defrayment(banknum,money,pwd);
        }
        else if (menu.equals("2")){ // 고객입금관리
            System.out.print(" 입금하실 계좌번호 9자리를 입력하시오.\n ☞ ");
            String banknum = readString();
            System.out.print(" 비밀번호 4자리를 입력하시오.\n ☞ ");
            String pwd = readString();
            System.out.print(" 입금하실 금액을 입력하시오.\n ☞ ");
            int money = readInt();
            bn.receipt(banknum,money,pwd);
        }
        else if (menu.equals("3")){ // 고객송금관리
            System.out.print(" 보내실 분의 계좌번호 9자리를 입력하시오.\n ☞ ");
            String banknum = readString();
            System.out.print(" 받는 분의 계좌번호 9자리를 입력하시오.\n ☞ ");
            String Tobanknum = readString();
            System.out.print(" 송금하실 금액을 입력하시오. \n ☞ ");
            int money = readInt();
            System.out.print(" 비밀번호 4자리를 입력하시오.\n ☞ ");
            String pwd = readString();
            bn.remittance(banknum,Tobanknum,pwd,money);
        }
        else
            System.out.println(" 잘못 입력하셨습니다. " );
    }

    public static String readString() { // 문자열 입력
        Scanner input = new Scanner(System.in);
        return input.nextLine().trim();
    }
    public static int readInt() { // 정수 입력
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
}
