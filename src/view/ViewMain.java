package view;

public class ViewMain
{
    public static void main(String[] args)
    {

        MainFrame mf = MainFrame.getInstance();
        mf.setVisible(true);

        //메인메뉴 띄우기
        mf.changeView(Mode.MAIN);
    }
}
