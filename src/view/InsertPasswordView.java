package view;

import javax.swing.*;

public class InsertPasswordView extends JPanel implements IView
{
    private JPasswordField passwordField;
    private NumberPadView numberPadPanel;
    private JPanel Panel1;
    String nextMode = "";                           //다음 띄워야 하는 화면 설정. money, transfer, password 등등
    private static InsertPasswordView instance;     //자기 자신 객체 리턴용

    //생성자
    public InsertPasswordView()
    {
        instance = this;
        numberPadPanel.setRelatedPanel(this);
    }

    //외부에서 이 객체 참조하게 하는 메소드
    public static InsertPasswordView getInstance()
    {
        if ( instance == null )
            instance = new InsertPasswordView();
        return instance;
    }


    @Override
    public JTextField getTextField()
    {
        return passwordField;
    }
    public void setNextMode(String nextMode)
    {
        this.nextMode = nextMode;
    }
    @Override
    public String getNextMode()
    {
        return nextMode;
    }
}
