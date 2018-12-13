package view;

import javax.swing.*;

public class NumberPadView extends JPanel
{
    private JPanel panel1;
    private JButton a1Button;   //숫자 키패드들
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton deleteButton;
    private JButton okButton;
    private JButton a0Button;
    private JButton manButton;
    private JButton resetButton;

    public NumberPadView()
    {
        //처음 생성시 만원버튼 안 보이게
        showManButton(false);
    }

    public void showManButton(boolean b)
    {
        manButton.setEnabled(b);
        manButton.setVisible(b);
    }
}