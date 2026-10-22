@FunctionalInterface
interface MarksRule {
    boolean check(int marks);      // exactly one abstract method
}

class PassRule implements MarksRule {
    @Override
    public boolean check(int marks) {
        return marks >= 40;
    }
}

public class LambdaIntro {
    public static void main(String[] args) {
        // old way: a full class
        MarksRule pass = new PassRule();

        // lambda: same work in one line
        MarksRule distinction = marks -> marks >= 75;

        int[] marks = {85, 38, 76, 52};
        for (int i = 0; i < marks.length; i++) {
            System.out.println(marks[i] + "  pass: " + pass.check(marks[i])
                    + "  distinction: " + distinction.check(marks[i]));
        }
    }
}
