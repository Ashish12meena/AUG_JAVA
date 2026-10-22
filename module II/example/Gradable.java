// 2.11 Interface: anything that gets marks and a grade
public interface Gradable {
    int PASS_MARK = 40;          // 2.11 Interface field: public static final

    int total();                 // 2.11 Interface methods: public abstract
    double percentage();
    boolean isPassed();

    // 2.11 default method: one grade rule for every Gradable class
    default char grade() {
        double p = percentage();
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
}
