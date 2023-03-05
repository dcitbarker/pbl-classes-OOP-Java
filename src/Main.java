import java.util.Scanner;

public class Main {
    public static void main (String[] args){

        int a, b,c ;

        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter first Number: ");

        a = scanner.nextInt();

        System.out.println("Enter Second number: ");

        b= scanner.nextInt();

        c = a + b;

        System.out.println(c);
    }
}