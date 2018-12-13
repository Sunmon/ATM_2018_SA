package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JPanel
{
    private JPanel buttonPanel;
    private JButton withdrawButton;
    private JButton transferButton;
    private JButton depositButton;

    public MenuView()
    {
        depositButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });
    }
}
