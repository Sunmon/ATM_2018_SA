package view;

import javax.swing.*;

public class InsertCardView extends JPanel implements IView
{
    private JPanel panel1;



    private JTextField creditTextField;
    private JPanel creditCardPanel;
    private JLabel creditCardNumberLabel;



    private NumberPadView numberPadPanel;
    private static InsertCardView instance;



    private String nextMode = "";    //다음 띄워야 하는 화면 설정. money, transfer

    //TODO: OK버튼 누르면 다음 화면으로 넘어가게 하기
    public InsertCardView()
    {
        instance = this;
        numberPadPanel.setRelatedPanel(this, creditTextField);
    }

    public static InsertCardView getInstance()
    {
        if ( instance == null )
            instance = new InsertCardView();
        return instance;
    }

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
