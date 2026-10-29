// 2.1 Inheritance: Student IS-A CollegeMember
// 2.11 Multiple interfaces: Student CAN BE GRADED and CAN PAY FEES
// WHY extends + implements? Identity (id, name) comes from ONE parent class,
// and two capabilities (marks, fee) come from interfaces. Java allows this, not two parent classes.
public class Student extends CollegeMember implements Gradable, FeePayer {
    // 2.1 private: ResearchStudent cannot touch this array directly, it uses the methods
    // WHY private? Marks can only be changed through setMark(), which checks 0 to 100.
    // WHY still an array (not a List)? Always exactly 5 subjects: fixed size is right here.
    private int[] marks = new int[5];

    // WHY here? An interface cannot keep variables, so the fee paid is stored in the class.
    private double paid;

    public Student(String name) {
        super(name);   // 2.3 super(): the parent sets its own part (id and name)
    }

    // 1.8 Setter with validation (same as Module I Student)
    public void setMark(int subject, int mark) {
        if (mark < 0 || mark > 100) {
            System.out.println("Invalid mark " + mark + ". Mark must be 0 to 100.");
        } else {
            marks[subject] = mark;
        }
    }

    // 2.4 Overloading: same name, different parameter -> sets all 5 marks
    // WHY? Set one mark OR all marks with the same easy name, setMark().
    public void setMark(int[] allMarks) {
        for (int i = 0; i < marks.length; i++) {
            setMark(i, allMarks[i]);   // reuses the check above
        }
    }

    // 2.11 Implementing interface methods: must be public
    // WHY public? Interface methods are public, and an override cannot be weaker.
    @Override
    public int total() {
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
        return sum;
    }

    @Override
    public double percentage() {
        return total() / 5.0;
    }

    @Override
    public boolean isPassed() {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < PASS_MARK) {   // 2.11 constant from Gradable
                return false;
            }
        }
        return true;
    }
    // grade() is NOT written here: it comes from Gradable (default method)

    @Override
    public double fee() {
        return 120000;
    }

    @Override
    public double paidAmount() {
        return paid;
    }

    @Override
    public void addPayment(double amount) {
        paid += amount;
    }
    // balance() and isFeeCleared() are NOT written here: they come from FeePayer (default)
    // lateFine() is NOT inherited: it is static, call it as FeePayer.lateFine(days)

    // 2.11 Diamond problem: Gradable AND FeePayer both give a default status().
    // WHY must we write it? Java cannot guess which one we want, so without this the class
    // will not compile. We decide: use BOTH, with InterfaceName.super.status().
    @Override
    public String status() {
        return Gradable.super.status() + ", " + FeePayer.super.status();
    }

    // 2.10 Writing the abstract methods of CollegeMember
    // WHY? CollegeMember forces every child to write them, or the code will not compile.
    @Override
    public String role() {
        return "Student";
    }

    @Override
    public void showDetails() {
        // 2.9 "this" in a String calls toString()
        System.out.println(this + "  Total: " + total() + "  %: " + percentage()
                + "  Grade: " + grade() + "  Balance: " + balance() + "  " + status());
    }
}
