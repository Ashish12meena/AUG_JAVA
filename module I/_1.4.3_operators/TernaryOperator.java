public class TernaryOperator {
    public static void main(String[] args) {
        int marks = 75;
        String result = (marks >= 40) ? "PASS" : "FAIL";
        System.out.println(result);

        int a = 10;
        int b = 20;
        int max = (a > b) ? a : b;
        System.out.println("Greater number is " + max);
    }
}
