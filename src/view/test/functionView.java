//TODO: 안씀!!!!!!!!


package view.test;

import view.IView;
import view.Mode;
import view.NumberPadView;

import javax.swing.*;
import java.awt.*;

public class functionView extends JPanel
{
    private NumberPadView numberPadPanel;
    private InforView inforPanel;


    public void changeCard(String cardName)
    {
        CardLayout c = (CardLayout)inforPanel.getBackgroundPanel().getLayout();
        inforPanel.setCurrentCardPanel(cardName);
        JPanel cur = inforPanel.getCurrentCardPanel();
        c.show(inforPanel.getBackgroundPanel(), cardName);
    }

}
