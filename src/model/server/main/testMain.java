package model.server.main;

import model.server.DB.database;

public class testMain {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        database db = new database();
        db.connect();
        db.isValid("111111111");
        db.getMoney("111111111", "30000");
    }
}
