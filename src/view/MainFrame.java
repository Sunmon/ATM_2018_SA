package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 메인프레임. 각 패널들이 올라갈 곳. 상황에 맞는 화면을 띄워준다.
public class MainFrame extends JFrame
{
    static Dimension d = new Dimension(1000, 800);
    private JPanel mainPanel;

    //menu 선택 화면
    private JPanel menuView;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;
    //카드 입력 화면
    private JPanel insertView;
    private JButton backButton;
    private InsertCardView insertCardPanel;
    //금액 입력 화면
    private JPanel insertMoney;
    private JButton backMbutton;
    private JLabel msgLabel;
    private InsertMoneyView insertMoneyPanel;
    //송금받을 카드번호 입력 화면
    private JPanel insertTransferView;
    private InsertCardView insertTransferPanel;
    private JButton backTransferButton;
    //패스워드 입력 화면
    private JPanel insertPassword;
    private JButton backPButton;
    private InsertPasswordView insertPasswordPanel;
    //각 화면에 쓰이는 숫자패드 패널
    private NumberPadView numberPadPanel;

    private String function;    //deposit, withdraw, transfer 중 어떤 작업을 하는지 저장
    private String cardNum;     //model에 연결하기 전 GUI에서 임시로 카드번호 저장

    //싱글톤 이용한 생성자.
    private static MainFrame instance = new MainFrame();
    private MainFrame()
    {
        init();
    }
    public static MainFrame getInstance()
    {
        if ( instance == null ) instance = new MainFrame();
        return instance;
    }


    //MainFrame 생성시 초기화하는 메소드
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
            {   //메뉴 버튼들 눌렀을때 행동 설정
                if(e.getSource().equals(depositButton))
                {
                    function = "deposit";
                    insertCardPanel.setNextMode("money");
                    insertMoneyPanel.setNextMode("alert");
                }
                if(e.getSource().equals(withdrawButton))
                {
                    function = "withdraw";
                    insertCardPanel.setNextMode("money");
                    insertMoneyPanel.setNextMode("password");
                    insertPasswordPanel.setNextMode("alert");
                }
                if(e.getSource().equals(transferButton))
                {
                    function = "transfer";
                    insertCardPanel.setNextMode("transfer");
                    insertTransferPanel.setNextMode("money");
                    insertMoneyPanel.setNextMode("alert");
                }
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
                clearTexts();
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
        backTransferButton.addActionListener(backListener);
        backPButton.addActionListener(backListener);

    }

    //상황에 맞는 패널로 띄워준다.
    //cardName : main, insert, money, transfer 중 하나
    public void changeView(String cardName)
    {
        CardLayout c = (CardLayout)mainPanel.getLayout();
        c.show(mainPanel, cardName);


    }

    public void clearTexts()
    {
        insertCardPanel.getCreditTextField().setText("");
        insertTransferPanel.getCreditTextField().setText("");
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
    public String getFunction()
    {
        return function;
    }

}
