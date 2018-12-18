package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ResultAlert extends JDialog
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel msgLabel;

    public enum State
    {
        DEPOSIT("입금이 완료되었습니다."),
        WITHDRAW("출금이 완료되었습니다."),
        TRANSFER("송금이 완료되었습니다."),
        ERROR_CARD("존재하지 않는 카드번호입니다."),
        ERROR_PW("비밀번호가 맞지 않습니다."),
        ERROR_BALANCE("잔액이 충분하지 않습니다."),
        ERROR_EMPTY("항목이 채워지지 않았습니다.");

        private String msg;

        State(String msg) {this.msg = msg;}

        public String getMsg() {return msg;}
    }

    State state;    //error, 완료 문구 등 메세지


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

    //알림의 OK 버튼 눌렀을 때
    private void onOK()
    {
        MainFrame.getInstance().clearTexts();
        switch (state)
        {
            case DEPOSIT:
            case TRANSFER:
            case WITHDRAW:
                MainFrame.getInstance().changeView(Mode.MAIN);
            default:
                break;
        }
        dispose();
    }

    //알림창에 뜰 메세지 설정
    private void setMsg(String str)
    {   //state 따라 알림 메세지 설정
        this.state = State.valueOf(str);
        msgLabel.setText(this.state.getMsg());
    }

    //알림
    public static void alert(JPanel parent, String str)
    {
        ResultAlert dialog = new ResultAlert();
        dialog.setMsg(str);
        dialog.setLocationRelativeTo(parent);
        dialog.setLocationByPlatform(true);
        dialog.pack();
        dialog.setVisible(true);
    }
}
