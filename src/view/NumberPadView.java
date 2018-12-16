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
    IView relatedPanel; //연관된 패널.

//TODO: setRelatedPanel 수정.. moneyView관련해서 도 만들기!
    // TODO: 파라미터 하나 없애기.


    public NumberPadView()
    {
        init();
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


    //number pad 와 연관된 panel 설정
    public void setRelatedPanel(IView pan)
    {
        relatedPanel = pan;
        textField = pan.getTextField();

    }


    //만원버튼 보이기 안보이기
    public void showManButton(boolean b)
    {
        manButton.setEnabled(b);
        manButton.setVisible(b);
    }



    //버튼 눌렀을때 textField에 누른 숫자대로 뜨게 하는 메소드
    public void addToText(Object o, JTextField textField)
    {

        //TODO: enumㅇ로 바꿔보기?
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
                System.out.println("okbutton pressed: " + relatedPanel.getNextMode());
                MainFrame.getInstance().setCardNum(textField.getText());
                MainFrame.getInstance().changeView(relatedPanel.getNextMode());
                if(relatedPanel.getNextMode().equals("alert")) ResultAlert.alert((JPanel)relatedPanel, "");
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