public class LogicalOperators {
    public static void main(String[] args) {
        int marks = 75;

        System.out.println(marks >= 40 && marks <= 100);   // both true
        System.out.println(marks < 40 || marks > 100);     // both false
        System.out.println(!(marks >= 40));
    }
}
