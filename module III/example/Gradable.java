// 2.11 Interface: anything that gets marks and a grade
// WHY interface? Not every CollegeMember has marks (student yes, teacher no).
// So it is a capability ("CAN BE GRADED"), not a parent ("IS-A").
public interface Gradable {
    // 2.11 Interface field: public static final
    // WHY constant here? One pass mark for every student, and nobody can change it by mistake.
    int PASS_MARK = 40;

    // 2.11 Interface methods: public abstract
    // WHY no body? Each class decides how to calculate these (Research Student has a stricter pass rule).
    int total();
    double percentage();
    boolean isPassed();

    // 2.11 default method: a method WITH a body inside an interface.
    // WHY default here?
    //  - The grade rule (90 = A, 75 = B ...) is the SAME for every Gradable class.
    //  - It only needs percentage(), which every Gradable class already has.
    //  - Without default, every Gradable class must copy this code.
    //  - A class can still override it if it needs a different rule.
    default char grade() {
        double p = percentage();   // percentage() of the real object
        if (p >= 90) {
            return 'A';
        } else if (p >= 75) {
            return 'B';
        } else if (p >= 60) {
            return 'C';
        } else if (p >= 40) {
            return 'D';
        } else {
            return 'F';
        }
    }

    // 2.11 default method: result status
    // NOTE: FeePayer ALSO has a default status(). See Student for the diamond problem.
    default String status() {
        return isPassed() ? "PASS" : "FAIL";
    }
}
