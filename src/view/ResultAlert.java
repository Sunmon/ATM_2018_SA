package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ResultAlert extends JDialog
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel msgLabel;

    String msg = "";    //error, 완료 문구 등 메세지


    public ResultAlert()
    {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {onOK();}
        });
    }

    private void onOK()
    {
        //알림 OK 버튼 눌렀을 때
        MainFrame.getInstance().clearTexts();
        MainFrame.getInstance().changeView(Mode.MAIN);
        dispose();
    }

    private void setMsg(String state)
    {   //state 따라 알림 메세지 설정
        if(state.equals("error"))
        {
            msg = "error";
            return;
        }

        String func = MainFrame.getInstance().getFunction();
        if(func.equals("deposit")) msg = "예금이 완료되었습니다.";
        else if(func.equals("withdraw")) msg = "출금이 완료되었습니다.";
        else if(func.equals("transfer")) msg = "송금이 완료되었습니다.";
        msgLabel.setText(msg);
    }



    public static void alert(JPanel parent, String state)
    {
        ResultAlert dialog = new ResultAlert();
        dialog.setMsg(state);
        dialog.setLocationRelativeTo(parent);
        dialog.setLocationByPlatform(true);
        dialog.pack();
        dialog.setVisible(true);
    }
}
