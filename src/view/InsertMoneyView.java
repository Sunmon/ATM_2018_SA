package view;

import javax.swing.*;

public class InsertMoneyView extends JPanel
{
    private JPanel panel1;
    private JLabel wonLabel;


    private JTextField moneyTextField;
    private NumberPadView numberPadPanel;

    private static InsertMoneyView instance;
    //TODO: moneyView도 testView처럼 numPad 이용해서 만들기!

    // 생성자. 다른 클래스에서 instance 로 접근할 수 있게 설정.
    public InsertMoneyView()
    {
        instance = this;
        numberPadPanel.setRelationPanel(this);
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

}
