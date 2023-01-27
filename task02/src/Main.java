package assessment1.task02.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        //Start calculator
        System.out.println("Welcome.");
        String input;
        Boolean exit = false;
        Float last = 0.0f;
        while (!exit){
            input = scanner.nextLine();
            String[] inputArray = input.split(" ");
            //Exit calculator
            if (input.equals("exit")){
                exit = true;
                continue;
            }
            //Request for new input if input incorrect format
            if (inputArray.length<3){
                System.out.println("Please enter valid format > (number [space] operator [space] number)");
                continue;
            }
            //Take in the number input
            Float num1 = 0.0f;
            Float num2 = 0.0f;
            if (inputArray[0].equals("$last")){
                num1 = last;
            } else {
                num1 = Float.parseFloat(inputArray[0]);
            }
            if (inputArray[2].equals("$last")){
                num2 = last;
            } else {
                num2 = Float.parseFloat(inputArray[2]);
            }
            //Perform calculation
            Float result = 0.00f;
            switch (inputArray[1]){
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }
            //Print out result
            if (result*10%10==0){
                System.out.println(Math.round(result));
            } else {
                System.out.println(result);
            }
            //Keep the result
            last = result; 
        }
        System.out.println("Bye bye");
    }
}
