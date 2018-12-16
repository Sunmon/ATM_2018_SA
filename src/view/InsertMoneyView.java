package view;

import javax.swing.*;

public class InsertMoneyView extends JPanel implements IView
{
    private JPanel panel1;
    private JLabel wonLabel;
    private JTextField moneyTextField;
    private NumberPadView numberPadPanel;
    private String nextMode;
    private static InsertMoneyView instance;

    // 생성자. 다른 클래스에서 instance 로 접근할 수 있게 설정.
    public InsertMoneyView()
    {
        instance = this;
        numberPadPanel.setRelatedPanel(this);
    }

    public static InsertMoneyView getInstance()
    {
        if ( instance == null )
            instance = new InsertMoneyView();
        return instance;
    }

    public JTextField getMoneyTextField()
    {
        return moneyTextField;
    }

    @Override
    public JTextField getTextField()
    {
        return moneyTextField;
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
