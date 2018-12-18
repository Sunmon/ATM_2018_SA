package model.server.main;

import model.server.DB.database;

public class testMain {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        // 이런식으로 사용해야 됩니다. 기본적으로 서버에 있는 함수들이 이런구조에요
        database db = new database();
		db.connect();
		if(db.isValid("111111111")) {
			db.getMoney("111111111", "50000");
			db.putMoney("111111111", "50000");
		}
		db.disconnect();
		db.commit();
    }
}
