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



    private JTextField textField;           //다른 패널에 있는 textField(금액, 카드번호 등)
    private JPasswordField passwordField;   //비밀번호 입력창.

    IView relatedPanel;
//    JPanel relatedPanel;
/*
    public void setRelatedPanel(JPanel pan, JTextField jtf)
    {
//        if(pan.equals(testInertView.getInstance())) textField = testInertView.getInstance().getTextField1();
//        if(pan.equals(InsertCardView.getInstance())) textField = InsertCardView.getInstance().getCreditTextField();
        relatedPanel = pan;
        textField = jtf;

        if(pan.equals(InsertMoneyView.getInstance())) textField = InsertMoneyView.getInstance().getMoneyTextField();
        System.out.println("textFiled");
    }*/

public void setRelatedPanel(IView pan, JTextField jtf)
{
    relatedPanel = pan;
    textField = jtf;

}


    public NumberPadView()
    {
        init();
    }

    public void showManButton(boolean b)
    {
        manButton.setEnabled(b);
        manButton.setVisible(b);
    }


    private void init()
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addToText(e.getSource(), textField);
            }
        };

        //add button listener
        a1Button.addActionListener(listener);
        a2Button.addActionListener(listener);
        a3Button.addActionListener(listener);
        a4Button.addActionListener(listener);
        a5Button.addActionListener(listener);
        a6Button.addActionListener(listener);
        a7Button.addActionListener(listener);
        a8Button.addActionListener(listener);
        a9Button.addActionListener(listener);
        a0Button.addActionListener(listener);
        deleteButton.addActionListener(listener);
        resetButton.addActionListener(listener);
        okButton.addActionListener(listener);
    }




    //버튼 눌렀을때 textField에 누른 숫자대로 뜨게 하는 메소드
    public void addToText(Object o, JTextField textField)
    {

        //누른 버튼 정보를 but에 숫자로 저장
        Object[] buttons = new Object[]
                {a0Button, a1Button, a2Button, a3Button, a4Button, a5Button, a6Button,
                        a7Button, a8Button, a9Button, deleteButton, okButton, resetButton};

        int but = 0;
        for(Object b: buttons)
        {
            if(o.equals(b)) break;
            but++;
        }


        //누른 버튼에 맞게 textField 내용을 갱신
        String temp = textField.getText();
        switch(but)
        {
            case 10:    //delete button
                if(temp.length() == 0) break;
                temp = temp.substring(0, temp.length()-1);
                break;
            case 11:    //OK button
                //NOTE: 임의로 cardNum에 저장해둠. 나중에 model과 합치면 수정할 부분!
//                System.out.println("okbutton pressed: " +InsertCardView.getInstance().getNextMode());
                System.out.println("okbutton pressed: " + relatedPanel.getNextMode());

                MainFrame.getInstance().setCardNum(textField.getText());
                MainFrame.getInstance().changeView("money");
                return;
            case 12:    //reset button
                temp = "";
                break;
            default:    //number buttons
                temp+= Integer.toString(but);
                break;
        }
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