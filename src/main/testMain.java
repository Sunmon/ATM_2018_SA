package main;

import controller.mainController;

public class testMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        mainController controll = new mainController();
        if(controll.isValid("111111111")) {
            System.out.println("good");
            if(controll.diposit("5000")) {
                System.out.println("diposit good");
            }
            if(controll.withdraw("50000")) {
                System.out.println("withdraw good");
            }
            if(controll.transfer("222222222", "50000")) {
                System.out.println("transfer good");
            }
        } else {
            System.out.println("bad");
        }
    }

}
