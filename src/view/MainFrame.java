package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame
{
    static Dimension d = new Dimension(1000, 800);

    private JPanel mainPanel;
    private JPanel MenuView;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;
    private JPanel insertView;
    private JButton backButton;
    private JPanel insertMoney;
    private JButton backMbutton;


    //생성자
    public MainFrame()
    {
        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(d);
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                changeView("insert");
            }
        };

        ActionListener backListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                changeView("main");
            }
        };

        //메뉴중에 어떤 버튼을 누르든 카드 입력하는 화면으로 가기
        depositButton.addActionListener(listener);
        withdrawButton.addActionListener(listener);
        transferButton.addActionListener(listener);

        //뒤로가기 버튼 리스너
        backButton.addActionListener(backListener);
        backMbutton.addActionListener(backListener);
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