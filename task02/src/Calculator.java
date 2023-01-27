package assessment1.task02.src;

public class Calculator {
    //Constructor
    public Calculator (){}
    //Perform calculation
    public Float calculate(Float num1, Float num2, String operator){
        switch (operator){
            case "+":
                return num1+num2;
            case "-":
                return num1-num2;
            case "*":
                return num1*num2;
            case "/":
                return num1/num2;
            default:
                 return 0.0f;
        }
    }
}
