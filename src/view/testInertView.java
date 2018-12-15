package view;

import javax.swing.*;

public class testInertView extends JPanel
{
    private JPanel panel1;
    private NumberPadView numberPadPanel;
    private JButton button1;



    private JTextField textField1;


    //싱글톤 zsss 이용한 생성자.
    private static testInertView instance;
    public testInertView()
    {
        instance = this;
    }

    public static testInertView getInstance()
    {
        if ( instance == null )
            instance = new testInertView();
        return instance;
    }

    public JTextField getTextField1()
    {
        return textField1;
    }

    public void setTextField1(JTextField textField1)
    {
        this.textField1 = textField1;
    }


}
