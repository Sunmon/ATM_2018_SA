package view;

import javax.swing.*;

public interface IView
{
    public JTextField getTextField();     //text field 리턴
    public String getNextMode();      //다음에 어떤 화면이 와야 할 지 리턴
}
