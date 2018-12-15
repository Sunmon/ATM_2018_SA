package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertCardView extends JPanel
{
    private JPanel panel1;



    private JTextField creditTextField;
    private JPanel creditCardPanel;
    private JLabel creditCardNumberLabel;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton deleteButton;
    private JButton resetButton;
    private JButton okButton;
    private JButton a0Button;


    CardLayout c;


    //TODO: OK버튼 누르면 다음 화면으로 넘어가게 하기
    public InsertCardView()
    {

        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                addToText(e.getSource(), creditTextField);
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
        Object[] buttons = new Object[]{a0Button, a1Button, a2Button, a3Button, a4Button, a5Button, a6Button,
                a7Button, a8Button, a9Button, deleteButton, okButton, resetButton};
        int but = 0;
        for(Object b: buttons)
        {
            if(o.equals(b)) break;
            but++;
        }


        //기존 textField의 내용을 갱신
        String temp = textField.getText();
        switch(but)
        {
            case 10:    //delete
                if(temp.length() == 0) break;
                temp = temp.substring(0, temp.length()-1);
                break;
            case 11:    MainFrame.getInstance().changeView("money");
            return;
            case 12:    //reset
                temp = "";
                break;
            default:
                temp+= Integer.toString(but);
                break;
        }
        textField.setText(temp);
    }




    public JTextField getCreditTextField()
    {
        return creditTextField;
    }

    public void setCreditTextField(JTextField creditTextField)
    {
        this.creditTextField = creditTextField;
    }

}
