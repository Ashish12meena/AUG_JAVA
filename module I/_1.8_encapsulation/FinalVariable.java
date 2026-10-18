class Exam {
    static final int PASS_MARK = 40;   // constant
    final int rollNo;                  // set once in constructor
    int marks;

    Exam(int rollNo, int marks) {
        this.rollNo = rollNo;
        this.marks = marks;
    }
}

public class FinalVariable {
    public static void main(String[] args) {
        Exam e1 = new Exam(101, 35);
        Exam e2 = new Exam(102, 35);

        // e1.rollNo = 200;          // ERROR: cannot assign a value to final variable
        // Exam.PASS_MARK = 33;      // ERROR

        e1.marks = 45;               // not final, so it can change

        if (e1.marks >= Exam.PASS_MARK) {
            System.out.println(e1.rollNo + " PASS");
        } else {
            System.out.println(e1.rollNo + " FAIL");
        }
    }
}
