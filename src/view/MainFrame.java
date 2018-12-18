package view;
import controller.mainController;

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
    private String menu;


    //데이터베이스 연결과 관련
    private mainController controller;        //TODO: DB말고 server에 연결되게 바꿀것

    private String cardNum = "";     //자신의 카드 번호
    private String otherCardNum = "";   //다른사람 카드 번호
    private String pw = "";  //자신의 비밀번호


    //싱글톤 이용한 생성자.
    private static MainFrame instance = new MainFrame();

    private MainFrame()
    {
        init();
    }

    public static MainFrame getInstance()
    {
        if (instance == null) instance = new MainFrame();
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
            {
                onMenuButton(e);
            }
        };

        //뒤로가기 버튼 리스너
        ActionListener backListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                clearTexts();
                changeView(Mode.MAIN);
            }
        };

        //메뉴버튼 리스너 추가
        depositButton.addActionListener(listener);
        withdrawButton.addActionListener(listener);
        transferButton.addActionListener(listener);

        //뒤로가기 버튼들에 리스너 추가
        backButton.addActionListener(backListener);
        backMbutton.addActionListener(backListener);
        backTransferButton.addActionListener(backListener);
        backPButton.addActionListener(backListener);

    }

    private void onMenuButton(ActionEvent e)
    {
        //메뉴 버튼들 눌렀을때 행동 설정
        //현재 화면의 정보. 다음에 나와야 할 화면 정보 설정
        if (e.getSource().equals(depositButton))
        {
            menu = "DEPOSIT";
            insertCardPanel.setNextMode(Mode.MONEY);
            insertMoneyPanel.setNextMode(Mode.ALERT);

        } else if (e.getSource().equals(withdrawButton))
        {
            menu = "WITHDRAW";
            insertCardPanel.setNextMode(Mode.PASSWORD);
            insertPasswordPanel.setNextMode(Mode.MONEY);
            insertMoneyPanel.setNextMode(Mode.ALERT);
        } else if (e.getSource().equals(transferButton))
        {
            menu = "TRANSFER";
            insertCardPanel.setNextMode(Mode.TRANSFER);
            insertTransferPanel.setCurrentMode(Mode.TRANSFER);
            insertTransferPanel.setNextMode(Mode.PASSWORD);
            insertPasswordPanel.setNextMode(Mode.MONEY);
            insertTransferPanel.getCreditCardNumberLabel().setText("INSERT RECEIVER CARD NU");
            insertMoneyPanel.setNextMode(Mode.ALERT);
        }
        changeView(Mode.CARD);
    }

    //상황에 맞는 패널로 띄워준다.
    //mode : MAIN, CARD, TRANSFER, PASSWORD, MONEY, ALERT 중 하나
    public void changeView(Mode mode)
    {
        CardLayout c = (CardLayout) mainPanel.getLayout();
        c.show(mainPanel, mode.name());
    }


    //메인화면으로 돌아가면 기존 text에 썼던 내용들 다 지우기
    public void clearTexts()
    {
        insertCardPanel.getTextField().setText("");
        insertTransferPanel.getTextField().setText("");
        insertMoneyPanel.getTextField().setText("");
        for (JLabel lab : insertPasswordPanel.getJLabelArr())
        {
            lab.setText(" ");
        }
        insertPasswordPanel.getNumberPadPanel().initPointer();

        //TODO: controller rollback
//        controller.rollback();
    }


    //TODO: DB에서 비밀번호 정보 가져옴 => 서버 이용하게 변경할것
   /* public void setPWInfo()
    {
        String info[] = controller.getInfo(cardNum);
        setPw(info[0]); //pw저장
    }
*/

    public mainController getController()
    {
        return controller;
    }

    public void setController(mainController controller)
    {
        this.controller = controller;
    }

    public String getMenu()
    {
        return menu;
    }

    public String getCardNum()
    {
        return cardNum;
    }

    public void setCardNum(String cardNum)
    {
        this.cardNum = cardNum;
    }


    public String getOtherCardNum()
    {
        return otherCardNum;
    }

    public void setOtherCardNum(String otherCardNum)
    {
        this.otherCardNum = otherCardNum;
    }

    public String getPw()
    {
        return pw;
    }

    public void setPw(String pw)
    {
        this.pw = pw;
    }

}
