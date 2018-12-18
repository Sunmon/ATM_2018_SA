package main;

import controller.mainController;
import view.MainFrame;
import view.Mode;

public class testMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        mainController controll = new mainController("127.0.0.1");
        MainFrame mf = MainFrame.getInstance();
        mf.setController(controll);
        mf.setVisible(true);

        //메인메뉴 띄우기
        mf.changeView(Mode.MAIN);
    }
}
