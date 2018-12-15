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
    private String mode = null;      //입력받은 카드번호 구분해주기 위한 모드. mine(자신), other(상대)
    private static InsertCardView instance;

    public InsertCardView()
    {
        instance = this;
        System.out.println(mode);
    }

    public static InsertCardView getInstance()
    {
        if ( instance == null )
            instance = new InsertCardView();
        return instance;
    }

    public String getMode()
    {
        return mode;
    }

    public void setMode(String mode)
    {
        this.mode = mode;
    }

    public void setNumRelated()
    {
        numberPadPanel.setRelationPanel(this);
    }

    public JTextField getCreditTextField()
    {
        return creditTextField;
    }


}
