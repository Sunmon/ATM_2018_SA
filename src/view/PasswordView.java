package view;

import javax.swing.*;

public class PasswordView extends JPanel implements IView
{
    private JPasswordField passwordField;
    private NumberPadView numberPadPanel;
    private JPanel Panel1;

    String nextMode = "";
    private static PasswordView instance;

    //생성자
    public PasswordView()
    {
        instance = this;
        numberPadPanel.setRelatedPanel(this);
    }

    //외부에서 이 객체 참조하게 하는 메소드
    public static PasswordView getInstance()
    {
        if ( instance == null )
            instance = new PasswordView();
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
