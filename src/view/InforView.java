package view;

import javax.swing.*;
import java.awt.*;

public class InforView extends JPanel
{

    private JPanel insertCardPanel;
    private JTextField cardTextField;
    private JPanel passwordPanel;
    private JLabel p1;
    private JLabel p2;
    private JLabel p3;
    private JLabel p4;


    public CardLayout getCardLayout()
    {
        return (CardLayout) backgroundPanel.getLayout();
    }



    private JPanel backgroundPanel;

    public JPanel getBackgroundPanel()
    {
        return backgroundPanel;
    }

    public void setBackgroundPanel(JPanel backgroundPanel)
    {
        this.backgroundPanel = backgroundPanel;
    }

    public JPanel getInsertCardPanel()
    {
        return insertCardPanel;
    }

    public void setInsertCardPanel(JPanel insertCardPanel)
    {
        this.insertCardPanel = insertCardPanel;
    }

    public JTextField getCardTextField()
    {
        return cardTextField;
    }

    public void setCardTextField(JTextField cardTextField)
    {
        this.cardTextField = cardTextField;
    }

    public JPanel getPasswordPanel()
    {
        return passwordPanel;
    }

    public void setPasswordPanel(JPanel passwordPanel)
    {
        this.passwordPanel = passwordPanel;
    }

    public JLabel getP1()
    {
        return p1;
    }

    public void setP1(JLabel p1)
    {
        this.p1 = p1;
    }

    public JLabel getP2()
    {
        return p2;
    }

    public void setP2(JLabel p2)
    {
        this.p2 = p2;
    }

    public JLabel getP3()
    {
        return p3;
    }

    public void setP3(JLabel p3)
    {
        this.p3 = p3;
    }

    public JLabel getP4()
    {
        return p4;
    }

    public void setP4(JLabel p4)
    {
        this.p4 = p4;
    }






}
