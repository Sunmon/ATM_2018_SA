//TODO: NumberPadView 클래스 안 쓴다!!!!!!!!!!!!!!!!!



package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberPadView extends JPanel
{
    private JPanel panel1;
    private JButton a1Button;   //숫자 키패드들
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton deleteButton;
    private JButton okButton;
    private JButton a0Button;
    private JButton manButton;
    private JButton resetButton;



    private static JTextField textField;           //다른 패널에 있는 textField(금액, 카드번호 등)
    private JPasswordField passwordField;   //비밀번호 입력창.


    public NumberPadView()
    {
        //처음 생성시 만원버튼 안 보이게
        showManButton(false);
        ActionListener numListener = new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                //누른 버튼 번호에 맞는 숫자(string) 리턴
                Object temp = e.getSource();
                if(temp.equals(a0Button)) setNumber(buttonValue("0"));
                else if(temp.equals(a1Button)) setNumber(buttonValue("1"));
                else if(temp.equals(a2Button)) setNumber(buttonValue("2"));
                else if(temp.equals(a3Button)) setNumber(buttonValue("3"));
                else if(temp.equals(a4Button)) setNumber(buttonValue("4"));
                else if(temp.equals(a5Button)) setNumber(buttonValue("5"));
                else if(temp.equals(a6Button)) setNumber(buttonValue("6"));
                else if(temp.equals(a7Button)) setNumber(buttonValue("7"));
                else if(temp.equals(a8Button)) setNumber(buttonValue("8"));
                else if(temp.equals(a9Button)) setNumber(buttonValue("9"));
            }
        };
        a1Button.addActionListener(numListener);
        a2Button.addActionListener(numListener);
        a3Button.addActionListener(numListener);
        a4Button.addActionListener(numListener);
        a5Button.addActionListener(numListener);
        a6Button.addActionListener(numListener);
        a7Button.addActionListener(numListener);
        a8Button.addActionListener(numListener);
        a9Button.addActionListener(numListener);
        a0Button.addActionListener(numListener);

    }

    public void showManButton(boolean b)
    {
        manButton.setEnabled(b);
        manButton.setVisible(b);
    }


    //버튼 누른 숫자 리턴 (string으로)
    public String buttonValue(String bn)
    {
        return bn;
    }

    //textField에 입력한 숫자 더해서 표시해주기
    public void setNumber(String val)
    {
        String temp = textField.getText();
        temp += val;
        textField.setText(temp);
    }


    public JTextField getTextField()
    {
        return textField;
    }

    public static void setTextField(JTextField textField)
    {
        textField = textField;
    }
}