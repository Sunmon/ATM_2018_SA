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
                //TODO: 버튼 누르는대로 view 바꿔주기
                if(e.getSource().equals(depositButton)) changeView("insert");
            }
        };
        depositButton.addActionListener(listener);
        withdrawButton.addActionListener(listener);
        transferButton.addActionListener(listener);
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
