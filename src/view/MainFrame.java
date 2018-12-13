package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    static Dimension d = new Dimension(1000, 800);



    private JPanel mainPanel;
    private JPanel insertCard;
    private JTextField textField1;
    private JLabel cardNumberLabel;
    private JPanel cardPanel;
    private JPanel insertMoney;
    private JTextField textField2;
    private JLabel wonLabel;


    //생성자
    public MainFrame()
    {
        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(d);
    }

    //상황에 맞는 패널로 띄워준다.
    //cardName : main, num, insert, money
    public void changeView(String cardName)
    {
        CardLayout c = (CardLayout)mainPanel.getLayout();
        c.show(mainPanel, cardName);
    }


    public JPanel getMainPanel()
    {
        return mainPanel;
    }
}
