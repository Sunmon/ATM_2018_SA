package view;

public class ViewMain
{
    public static void main(String[] args)
    {
        MainFrame mf = new MainFrame();
        mf.setVisible(true);

        //answers는 parameter로 들어오는 입력값

        //원하는 화면카드 띄워주기
        mf.changeView("main");
//        mf.changeView("insert");
//        mf.changeView("money");
    }
}
