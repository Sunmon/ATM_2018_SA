package view;

import javax.swing.*;

public class InsertCardView extends JPanel
{
    private JPanel panel1;
    private JTextField creditTextField;
    private JPanel creditCardPanel;
    private JLabel creditCardNumberLabel;


    public InsertCardView()
    {
        NumberPadView.setTextField(creditTextField);
        System.out.println("init InsertCard");
    }
}
