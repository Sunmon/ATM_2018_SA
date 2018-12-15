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
    private InsertCardView insertCardPanel;
    private JLabel whatToDoLabel;
    private InsertMoneyView insertMoneyPanel;
    private JPanel insertTransferView;
    private InsertCardView insertTransferPanel;
    private JButton backTransferButton;

    private String cardNum;     //model에 연결하기 전 GUI에서 임시로 카드번호 저장

    //싱글톤 이용한 생성자.
    private static MainFrame instance = new MainFrame();
    private MainFrame()
    {
        init();
    }
    public static MainFrame getInstance()
    {
        if ( instance == null )
            instance = new MainFrame();
        return instance;
    }


    public void init()
    {
        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(d);


        //Action listener 설정
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource().equals(depositButton))
                {
                    insertCardPanel.setNextMode("money");
//                    insertCardPanel.getNumberPadPanel().setRelatedPanel(insertCardPanel, insertCardPanel.getCreditTextField());
                }
                if(e.getSource().equals(withdrawButton)) insertCardPanel.setNextMode("password");
                if(e.getSource().equals(transferButton)) insertCardPanel.setNextMode("transfer");
                System.out.println("insertCardPanel: + "+ insertCardPanel.getNextMode());
                changeView("insert");
            }
        };

        //뒤로가기 버튼
        ActionListener backListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                insertCardPanel.getCreditTextField().setText("");
                insertTransferPanel.getCreditTextField().setText("");
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
    //cardName : main, insert, money, transfer 중 하나
    public void changeView(String cardName)
    {
        CardLayout c = (CardLayout)mainPanel.getLayout();
        c.show(mainPanel, cardName);


    }


    //모델에 연결 전. GUI상으로 임시로 저장하는 카드 번호.
    public String getCardNum()
    {
        return cardNum;
    }
    public void setCardNum(String cardNum)
    {
        this.cardNum = cardNum;
    }
    public JPanel getMainPanel()
    {
        return mainPanel;
    }
}
