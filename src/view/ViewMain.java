package view;

public class ViewMain
{
    public static void main(String[] args)
    {
        MainFrame mf = new MainFrame();
        mf.setVisible(true);

        //원하는 화면카드 띄워주기
        mf.changeView("money");
    }
}
