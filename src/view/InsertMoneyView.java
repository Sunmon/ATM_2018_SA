package view;
import javax.swing.*;

// 금액 입력받는 클래스
public class InsertMoneyView extends JPanel implements IView
{
    private JPanel panel1;
    private JLabel wonLabel;
    private JTextField moneyTextField;          //금액 텍스트필드
    private NumberPadView numberPadPanel;       //숫자패드 클래스
    private String nextMode;                    //다음 띄워야 하는 화면 설정. money, transfer, password 등등
    private static InsertMoneyView instance;    //자기 자신 객체

    // 생성자
    public InsertMoneyView()
    {
        instance = this;
        numberPadPanel.setRelatedPanel(this);
    }

    //자기 자신 객체 리턴 (객체 하나만 쓰게)
    public static InsertMoneyView getInstance()
    {
        if ( instance == null )
            instance = new InsertMoneyView();
        return instance;
    }


    public JTextField getMoneyTextField()
    {
        return moneyTextField;
    }
    @Override
    public JTextField getTextField()
    {
        return moneyTextField;
    }
    public void setNextMode(String nextMode)
    {
        this.nextMode = nextMode;
    }
    @Override
    public String getNextMode()
    {
        return nextMode;
    }
}
