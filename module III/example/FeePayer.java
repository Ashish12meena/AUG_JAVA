// 2.11 Interface: anything that pays a fee (students, not teachers)
// WHY interface, not abstract class?
//  - Not every CollegeMember pays a fee. Teacher is a CollegeMember but pays nothing.
//  - Student already extends CollegeMember. Java allows only ONE parent class,
//    but a class can implement MANY interfaces.
public interface FeePayer {
    // 2.11 Interface field: public static final
    // WHY constant? Fine per day is one college rule, same for all, never changed.
    double FINE_PER_DAY = 50;

    // 2.11 Abstract methods: each class writes its own.
    // WHY no body? Student fee and Research Student fee are different (fee()).
    // An interface cannot keep a variable like "paid", so the class stores it.
    double fee();
    double paidAmount();
    void addPayment(double amount);

    // 2.11 default method: a method WITH a body inside an interface.
    // WHY default here?
    //  - Balance is the SAME rule for every fee payer: fee - paid.
    //  - It works on the student's own data (fee(), paidAmount()), so it needs an object: f.balance()
    //  - Not in CollegeMember: then teachers would also get a balance. Wrong.
    //  - Not in Student: then every new fee-paying class must copy it.
    //  - Bonus: adding a default method to an old interface does not break classes that already use it.
    default double balance() {
        return fee() - paidAmount();
    }

    // WHY default? Same reason: one "cleared or not" rule for every fee payer.
    default boolean isFeeCleared() {
        return balance() <= 0;
    }

    // 2.11 default method: fee status
    // NOTE: Gradable ALSO has a default status(). See Student for the diamond problem.
    default String status() {
        return isFeeCleared() ? "FEE CLEARED" : "FEE DUE";
    }

    // 2.11 static method: belongs to the interface, not to any object.
    // WHY static here (and not default)?
    //  - The fine depends ONLY on days late, not on which student pays.
    //  - It needs no student data, so no object is needed: FeePayer.lateFine(3) -> 150
    //  - Called only with the interface name. hita.lateFine(3) is an ERROR.
    //  - No class can override it, so the fine rule stays the same for everyone.
    static double lateFine(int days) {
        return days * FINE_PER_DAY;
    }
}
