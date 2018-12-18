package model.server.main;

import model.server.DB.database;
import view.MainFrame;
import view.Mode;

public class testMain
{
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub

        // 이런식으로 사용해야 됩니다. 기본적으로 서버에 있는 함수들이 이런구조에요
/*
        database db = new database();
        db.connect();
        db.rollback();
        if (db.isValid("111111111"))
        {
//        db.isValid("111111111");
            db.putMoney("111111111", "60");
//		db.getMoney("111111111", "1000000000");

        }
        db.disconnect();
        db.commit();
    }
*/


        //TODO: GUI 연결 되나 테스트
        database db = new database();
        db.connect();
        db.rollback();

        //메인프레임 (싱글톤 이용)
        MainFrame mf = MainFrame.getInstance();
        mf.setVisible(true);
        mf.setDB(db);

        //메인메뉴 띄우기
        mf.changeView(Mode.MAIN);

}

    }

