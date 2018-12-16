package view;
import javax.swing.*;
//NumberPad에서 연관된 패널들에 있는 텍스트필드 접근하기 위한 인터페이스
//InsertCardView, InsertMoneyView, InsertPasswordView 가 이용
public interface IView
{
    JTextField getTextField();     //text field 리턴
    Mode getNextMode();          //다음에 어떤 화면이 와야 할 지 리턴
    Mode getCurrentMode();
}
