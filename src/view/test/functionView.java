//TODO: 안씀!!!!!!!!


package view.test;

import view.IView;
import view.NumberPadView;

import javax.swing.*;
import java.awt.*;

public class functionView extends JPanel implements IView
{
    private NumberPadView numberPadPanel;
    private InforView inforPanel;


    public void changeCard(String cardName)
    {
        CardLayout c = (CardLayout)inforPanel.getBackgroundPanel().getLayout();
        inforPanel.setCurrentCardPanel(cardName);
        JPanel cur = inforPanel.getCurrentCardPanel();
//        numberPadPanel.setRelatedPan(cur, inforPanel.getCurrentTextField());
        c.show(inforPanel.getBackgroundPanel(), cardName);

    }


    @Override
    public JTextField getTextField()
    {
        return null;
    }

    @Override
    public String getNextMode()
    {
        return null;
    }
}
