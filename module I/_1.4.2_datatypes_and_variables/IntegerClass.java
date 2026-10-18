import java.util.Scanner;

public class IntegerClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer a = scanner.nextInt();
        Integer b = scanner.nextInt();


        boolean isEqual = (a == b); // Comparison
        System.out.println("Are the integers equal? " + isEqual);

        boolean isEquals = a.equals(b); // Comparison
        System.out.println("Are the integers equal? " + isEquals);

        scanner.close();
    }
}
