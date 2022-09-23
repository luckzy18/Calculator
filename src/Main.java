import java.awt.*;
import java.awt.event.*;


import javax.swing.*;
public class Main extends JFrame implements ActionListener {

    private JButton no0,no1,no2,no3,no4,no5,no6,no7,no8,no9,dot,
                            plus, minus, times, divide, equals,
                            backspace,clear;
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

        no0=new JButton("0");
        window.add(no0);
        no0.addActionListener(this);

        no1=new JButton("1");
        window.add(no1);
        no1.addActionListener(this);

        no2=new JButton("2");
        window.add(no2);
        no2.addActionListener(this);

        no3=new JButton("3");
        window.add(no3);
        no3.addActionListener(this);

        no4=new JButton("4");
        window.add(no4);
        no4.addActionListener(this);

        no5=new JButton("5");
        window.add(no5);
        no5.addActionListener(this);

        no6=new JButton("6");
        window.add(no6);
        no6.addActionListener(this);

        no7=new JButton("7");
        window.add(no7);
        no7.addActionListener(this);

        no8=new JButton("8");
        window.add(no8);
        no8.addActionListener(this);

        no9=new JButton("9");
        window.add(no9);
        no9.addActionListener(this);

        dot=new JButton(".");
        window.add(dot);
        dot.addActionListener(this);

        plus=new JButton("+");
        window.add(plus);
        plus.addActionListener(this);

        minus=new JButton("-");
        window.add(minus);
        minus.addActionListener(this);

        times=new JButton("*");
        window.add(times);
        times.addActionListener(this);

        divide=new JButton("/");
        window.add(divide);
        divide.addActionListener(this);

        equals=new JButton("=");
        window.add(equals);
        equals.addActionListener(this);

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

        if (source.equals(no1)) {
            result.setText(result.getText() + "1");
        }
        if (source.equals(no2)) {
            result.setText(result.getText() + "2");
        }
        if (source.equals(no3)) {
            result.setText(result.getText() + "3");
        }
        if (source.equals(no4)) {
            result.setText(result.getText() + "4");
        }
        if (source.equals(no5)) {
            result.setText(result.getText() + "5");
        }
        if (source.equals(no6)) {
            result.setText(result.getText() + "6");
        }
        if (source.equals(no7)) {
            result.setText(result.getText() + "7");
        }
        if (source.equals(no8)) {
            result.setText(result.getText() + "8");
        }
        if (source.equals(no9)) {
            result.setText(result.getText() + "9");
        }
        if (source.equals(no0)) {
            result.setText(result.getText() + "0");
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
        }
        if (source.equals(backspace)){
            backSpaceM();
        }
        if (source.equals(equals)){
            answer.setText(String.valueOf(calculateDM()));
        }
}
    private void backSpaceM(){     //deletes last character of result
        String a=result.getText();
        if (a.length()==0){
            return;
        }else if(a.charAt(a.length()-1) ==' '){
            a=a.substring(0,a.length()-3);
        }else{
            a=a.substring(0,a.length()-1);
        }
        result.setText(a);
 }
    private double calculateDM() {// does multiplication and division
        String equation = result.getText();
        String[] part=equation.split(" ");
        int count=countDM(part);
        while (count!=0){
            count--;
            for (int i=0; i<part.length;i++){
                if (part[i].equals("*")){
                    part[i]= String.valueOf(Double.parseDouble(part[i-1]) * Double.parseDouble(part[i+1]));
                    part[i+1]="";
                    part[i-1]="";
                }else if (part[i].equals("/")){
                    part[i]= String.valueOf(Double.parseDouble(part[i-1]) / Double.parseDouble(part[i+1]));
                    part[i+1]="";
                    part[i-1]="";
                }
            part=simpleArray(part);
            }
        }
        return calculateAS(part);
    }
    private int countDM(String[] equation){//checks how many times and divide operators are in the equation
        int count=0;
        for (String operator:equation){
            if (operator.equals("*") || operator.equals("/")){
                count+=1;
            }
        }
        return count;
    }

    private String[] simpleArray(String[] part){//removes empty spaces from array
        int empty=0;
         for (int i=0;i<part.length;i++){
            if (part[i].equals("")){
                empty++;
            }
        }// need to know amount of empty spaces in order to create array of desired space

        String[] newPart=new String[part.length-empty];
        int count=0;
        for (int i=0;i<part.length;i++){
            if (!part[i].equals("")){
                newPart[count]=part[i];
                count++;
            }
        }
        return newPart;
    }
    private double calculateAS(String[] part){// does the + and - operations in the array
        double total=Double.parseDouble(part[0]);
        double tempNo;
        String operator="";
        for(int i=1;i<part.length;i++){
            if(isDouble(part[i])){
                tempNo=Double.parseDouble(part[i]);
                if (operator.equals("+")){total+=tempNo;}
                else{
                    total-=tempNo;
                }
            }else{
                operator=part[i];
            }
        }
        return total;
    }
    public boolean isDouble(String obj){//double checks if the string is a double
        try{
            Double.valueOf(obj);
        }catch(Exception ex){
            return false;
        }
        return true;
    }
}
