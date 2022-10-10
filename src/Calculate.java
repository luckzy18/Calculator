public class Calculate {  // need to change to a linked list because of the removing of spaces it is slow
    private String[] equation;
    private double answer;
    public Calculate(String eq){
        this.equation = eq.split(" ");
        this.answer=calculate(equation);
    }
    public Calculate(){}

    public void setEquation(String newEq){
        this.equation=newEq.split(" ");
        this.answer=calculate(equation);
    }
    public void setEquation(String[] newEq){
        this.equation=newEq;
        this.answer=calculate(equation);
    }

    public double getAnswer(){
        return answer;
    }

    private double calculateAS(String[] part) {// does the + and - operations in the array
        // there should be a better way to do this
        double total = Double.parseDouble(part[0]);
        double tempNo;
        String operator = "";
        for (int i = 1; i < part.length; i++) {
            if (isDouble(part[i])) {
                tempNo = Double.parseDouble(part[i]);
                if (operator.equals("+")) {
                    total += tempNo;
                } else {
                    total -= tempNo;
                }
            } else {
                operator = part[i];
            }
        }
        return total;
    }

    private String[] simpleArray(String[] part) {//removes empty spaces from array
        int empty = 0;
        for (int i = 0; i < part.length; i++) {
            if (part[i].equals("")) {
                empty++;
            }
        }// need to know amount of empty spaces in order to create array of desired space

        String[] newPart = new String[part.length - empty];
        int count = 0;
        for (int i = 0; i < part.length; i++) {
            if (!part[i].equals("")) {
                newPart[count] = part[i];
                count++;
            }
        }
        return newPart;
    }

    public  double calculate(String[] equation) {// does multiplication and division
        int count = countDM(equation);
        while (count != 0) {
            count--;
            for (int i = 0; i < equation.length; i++) {
                if (equation[i].equals("*")) {
                    equation[i] = String.valueOf(Double.parseDouble(equation[i - 1]) * Double.parseDouble(equation[i + 1]));
                    equation[i + 1] = "";
                    equation[i - 1] = "";
                } else if (equation[i].equals("/")) {
                    equation[i] = String.valueOf(Double.parseDouble(equation[i - 1]) / Double.parseDouble(equation[i + 1]));
                    equation[i + 1] = "";
                    equation[i - 1] = "";
                }
                equation = simpleArray(equation);
            }
        }
        return calculateAS(equation);
    }

    private int countDM(String[] equation) {//checks how many times and divide operators are in the equation
        int count = 0;
        for (String operator : equation) {
            if (operator.equals("*") || operator.equals("/")) {
                count += 1;
            }
        }
        return count;
    }

    public boolean isDouble(String obj) {//double checks if the string is a double
        try {
            Double.valueOf(obj);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}