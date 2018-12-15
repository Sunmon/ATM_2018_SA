package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InsertCardView extends JPanel
{
    private JPanel panel1;



    private JTextField creditTextField;
    private JPanel creditCardPanel;
    private JLabel creditCardNumberLabel;
    private NumberPadView numberPadPanel;

    private static InsertCardView instance;

    //TODO: OK버튼 누르면 다음 화면으로 넘어가게 하기
    public InsertCardView()
    {
        instance = this;
        numberPadPanel.setRelationPanel(this);
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


}
