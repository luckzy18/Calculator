import java.awt.*;
import java.awt.event.*;


import javax.swing.*;
public class Main extends JFrame implements ActionListener {

    private JButton dot,plus, minus, times, divide, equals,
                            backspace,clear;
    private JButton[] buttons=new JButton[10];
    JPanel numberPanel,symbolPanel;
    private JTextField result, answer;


    public static void main(String[] args) {
	Main frame=new Main();
    frame.setSize(400,400);
    frame.createGUI();
    frame.setTitle("Calculator");
    frame.setLocationRelativeTo(null);// opens window in the middle of the screen
    frame.setVisible(true);
    }

    private void createGUI(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window=getContentPane();
        window.setLayout(new FlowLayout());

        numberPanel=new JPanel();
        numberPanel.setBackground(Color.blue);
        numberPanel.setBounds(50,50,300,300);
        numberPanel.setLayout(new GridLayout(4,4,10,10));
        numberPanel.setBackground(Color.gray);


        for (int i=0;i<buttons.length;i++){
            buttons[i]=new JButton(Integer.toString(i));
            numberPanel.add(buttons[i]);
            buttons[i].addActionListener(this);
        }

        dot=new JButton(".");
        numberPanel.add(dot);
        dot.addActionListener(this);

        equals=new JButton("=");
        numberPanel.add(equals);
        equals.addActionListener(this);

        window.add(numberPanel);

        symbolPanel=new JPanel();
        symbolPanel.setBackground(Color.blue);
        numberPanel.setBounds(50,50,300,300);
        symbolPanel.setLayout(new GridLayout(4,1,10,10));
        symbolPanel.setBackground(Color.gray);

        plus=new JButton("+");
        symbolPanel.add(plus);
        plus.addActionListener(this);

        minus=new JButton("-");
        symbolPanel.add(minus);
        minus.addActionListener(this);

        times=new JButton("*");
        symbolPanel.add(times);
        times.addActionListener(this);

        divide=new JButton("/");
        symbolPanel.add(divide);
        divide.addActionListener(this);

        window.add(symbolPanel);

        backspace=new JButton("backspace");
        window.add(backspace);
        backspace.addActionListener(this);

        clear=new JButton("clear");
        window.add(clear);
        clear.addActionListener(this);

        result=new JTextField(20);
        result.setBackground(Color.LIGHT_GRAY);
        result.setEditable(false);
        window.add(result);
        result.setVisible(true);

        answer=new JTextField(20);
        answer.setBackground(Color.CYAN);
        answer.setEditable(false);
        window.add(answer);
        answer.setVisible(true);


    }
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        for (int i=0; i<10;i++){
            if (source.equals(buttons[i])){
                result.setText(result.getText()+String.valueOf(i));
            }

        }
        if(source.equals(dot)) {
            result.setText(result.getText() + ".");
        }

        if (source.equals(times)) {
            result.setText(result.getText() + " * ");
        }
        if (source.equals(divide)) {
            result.setText(result.getText() + " / ");
        }
        if (source.equals(plus)) {
            result.setText(result.getText()+" + ");
    }
        if (source.equals(minus)){
            result.setText(result.getText()+" - ");
        }
        if (source.equals(clear)){
            result.setText("");
            answer.setText("");
        }
        if (source.equals(backspace)){
            backSpace();
        }
        if (source.equals(equals)){
            Calculate cal=new Calculate(result.getText());
            answer.setText(String.valueOf(cal.getAnswer()));
        }
}
    private void backSpace(){     //deletes last character of result
        String a=result.getText();
        if (a.length()==0){
            return;
        }else if(a.charAt(a.length()-1) ==' '){
            a=a.substring(0,a.length()-3);
        }else{
            a=a.substring(0,a.length()-1);
        }
        answer.setText("");
        result.setText(a);
 }
}
