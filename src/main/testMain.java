package main;

import controller.mainController;

public class testMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        mainController controll = new mainController("127.0.0.1");
        if(controll.isValid("111111111")) {
            System.out.println("good");
            if(controll.diposit("5000")) {
                System.out.println("diposit good");
            }

            if(!controll.withdraw("99999999999999999")) {
                System.out.println("good!!");
            }

            if(controll.withdraw("50000")) {
                System.out.println("withdraw good");
            }

            if(controll.transfer("222222222", "50000")) {
                System.out.println("transfer good");
            }

            if(!controll.transfer("22222222", "5000")) {
                System.out.println("very good!!");
            }

        } else {
            System.out.println("bad");
        }

        if(controll.isValid("11111")) {
            System.out.println("?? error");
        }else {
            System.out.println("very very good!!");
        }

    }

}
