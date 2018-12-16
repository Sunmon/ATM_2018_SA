package view.test;

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



    private JTextField currentTextField;
    private JPanel currentCardPanel;

    public CardLayout getCardLayout()
    {
        return (CardLayout) backgroundPanel.getLayout();
    }

    //카드네임에 따라 어떤 패널을 꺼내는지 리턴.
    public JPanel getCurrentCardPanel()
    {
        return currentCardPanel;

    }


    public void setCurrentTextField(JTextField currentTextField)
    {
        this.currentTextField = currentTextField;
    }

    private JPanel backgroundPanel;
    private JPanel moneyPanel;
    private JTextField moneyField;
    private JLabel wonLabel;

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


    public void setCurrentCardPanel(String cardName)
    {
        if(cardName.equals("insert"))
        {
            currentCardPanel = insertCardPanel;
            currentTextField = cardTextField;
        }
        else if(cardName.equals("password"))
        {
            currentCardPanel = passwordPanel;
            currentTextField = null;
        }
        else if(cardName.equals("money"))
        {
            currentCardPanel = moneyPanel;
            currentTextField = moneyField;
        }
    }

    public JTextField getCurrentTextField()
    {
        return currentTextField;
    }

}
