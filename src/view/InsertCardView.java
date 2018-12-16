package view;
import javax.swing.*;
import javax.swing.text.DocumentFilter;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;

//카드번호 입력받는 클래스
public class InsertCardView extends JPanel implements IView
{
    private JPanel panel1;
    private JTextField creditTextField;
    private JPanel creditCardPanel;


    private JLabel creditCardNumberLabel;
    private NumberPadView numberPadPanel;   //number Pad
    private static InsertCardView instance; // 자기자신(객체)
    private Mode nextMode;                  //다음에 띄워줄 화면
    private Mode currentMode;               //자기 자신 화면 설명

    //생성자
    public InsertCardView()
    {
        instance = this;
        this.currentMode = Mode.CARD;
        numberPadPanel.setRelatedPanel(this);   //연관된 numberPad 에 해당 객체가 연결되어있다고 설정
        numberPadPanel.showManButton(false);
        getTextField().setCaretColor(getBackground());
    }

    //외부에서 이 객체 참조하게 하는 메소드
    public static InsertCardView getInstance()
    {
        if ( instance == null )
            instance = new InsertCardView();
        return instance;
    }

    //Getter & Setter
    public void setNextMode(Mode nextMode)
    {
        this.nextMode = nextMode;
    }
    @Override
    public JTextField getTextField()
    {
        return creditTextField;
    }
    @Override
    public Mode getNextMode()
    {
        return nextMode;
    }
    @Override
    public Mode getCurrentMode()
    {
        return currentMode;
    }
    public void setCurrentMode(Mode currentMode)
    {
        this.currentMode = currentMode;
    }
    public JLabel getCreditCardNumberLabel()
    {
        return creditCardNumberLabel;
    }



}
