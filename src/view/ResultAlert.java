package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ResultAlert extends JDialog
{
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel msgLabel;

    //TODO: 에러나 상태관련 메세지 enum만들기
    public enum State
    {
        DEPOSIT("입금이 완료되었습니다."),
        WITHDRAW("출금이 완료되었습니다."),
        TRANSFER("송금이 완료되었습니다."),
        ERROR_CARD("존재하지 않는 카드번호입니다."),
        ERROR_PW("비밀번호가 맞지 않습니다."),
        ERROR_BALENCE("잔액이 충분하지 않습니다.");

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

    private void onOK()
    {
        //알림 OK 버튼 눌렀을 때
        MainFrame.getInstance().clearTexts();
        MainFrame.getInstance().changeView(Mode.MAIN);
        dispose();
    }

    //알림창에 뜰 메세지 설정
    private void setMsg(String str)
    {   //state 따라 알림 메세지 설정
        this.state = State.valueOf(str);


        //이 func어쩌구 메소드들 없앨것
    /*    String func = MainFrame.getInstance().getFunction();
        if(func.equals("deposit")) this.state = State.DEPOSIT;
        else if(func.equals("withdraw")) this.state = State.WITHDRAW;
        else if(func.equals("transfer")) this.state = State.TRANSFER;*/

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
