public class ElseIf {
    public static void main(String[] args) {
        int marks = 82;

        if (marks >= 90) {
            System.out.println("Grade A");
        } else if (marks >= 75) {
            System.out.println("Grade B");
        } else if (marks >= 40) {
            System.out.println("Grade C");
        } else {
            System.out.println("Fail");
        }
    }
}
