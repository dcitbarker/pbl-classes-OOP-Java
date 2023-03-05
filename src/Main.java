import java.util.Scanner;

public class Main {
    public static void main (String[] args){

        int operation;

        double results = 0;

        Calculator calculator = new Calculator();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Which of the following operations would you like to perform. 1.Addition 2.Subtraction 3.Multiplication 4.Division");

        operation = scanner.nextInt();

        System.out.println("Enter first Number: ");

        calculator.setLeftOperand(scanner.nextInt());

        System.out.println("Enter Second number: ");

        calculator.setRightOperand(scanner.nextInt());

        switch (operation){
            case (1):
                results = calculator.add();
                break;
            case 2:
                results = calculator.subtract();
                break;
            case 3:
                results = calculator.multiplication();
                break;
            case 4:
                results = calculator.division();
                break;
            default:
                System.out.println("Invalid Operation");
        }



        System.out.println("result is " + results);
    }
}