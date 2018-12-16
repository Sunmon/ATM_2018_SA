package view;
import javax.swing.*;

//카드번호 입력받는 클래스
public class InsertCardView extends JPanel implements IView
{
    private JPanel panel1;
    private JTextField creditTextField;
    private JPanel creditCardPanel;
    private JLabel creditCardNumberLabel;
    private NumberPadView numberPadPanel;   //number Pad
    private String nextMode = "";           //다음 띄워야 하는 화면 설정. money, transfer, password 등등
    private static InsertCardView instance; // 자기자신(객체)

    //생성자
    public InsertCardView()
    {
        instance = this;
        numberPadPanel.setRelatedPanel(this);   //연관된 numberPad 에 해당 객체가 연결되어있다고 설정
    }

    //외부에서 이 객체 참조하게 하는 메소드
    public static InsertCardView getInstance()
    {
        if ( instance == null )
            instance = new InsertCardView();
        return instance;
    }


    //Getter & Setter
    public JTextField getCreditTextField()
    {
        return creditTextField;
    }

    public void setNextMode(String nextMode)
    {
        this.nextMode = nextMode;
    }

    public NumberPadView getNumberPadPanel()
    {
        return numberPadPanel;
    }

    public void setNumberPadPanel(NumberPadView numberPadPanel)
    {
        this.numberPadPanel = numberPadPanel;
    }

    @Override
    public JTextField getTextField()
    {
        return creditTextField;
    }

    @Override
    public String getNextMode()
    {
        return nextMode;
    }
}
