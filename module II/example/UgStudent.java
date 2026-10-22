// 2.1 Inheritance: UgStudent IS-A CollegeMember
// 2.11 Multiple interfaces: it is also Gradable and a FeePayer
public class UgStudent extends CollegeMember implements Gradable, FeePayer {
    // 2.1 private: PgStudent cannot touch this array directly, it uses the methods
    private int[] marks = new int[5];

    public UgStudent(String name) {
        super(name);   // 2.3 super(): parent constructor gives id and name
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
    public void setMark(int[] allMarks) {
        for (int i = 0; i < marks.length; i++) {
            setMark(i, allMarks[i]);
        }
    }

    // 2.11 Implementing interface methods: must be public
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

    @Override
    public double fee() {
        return 120000;
    }

    // 2.10 Writing the abstract methods of CollegeMember
    @Override
    public String role() {
        return "UG Student";
    }

    @Override
    public void showDetails() {
        String result = isPassed() ? "PASS" : "FAIL";
        // 2.9 "this" in a String calls toString()
        System.out.println(this + "  Total: " + total() + "  %: " + percentage()
                + "  Grade: " + grade() + "  " + result + "  Fee: " + fee());
    }
}
