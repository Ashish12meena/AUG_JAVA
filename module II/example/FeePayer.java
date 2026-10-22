// 2.11 Interface: anything that pays a fee
public interface FeePayer {
    double fee();

    // 2.11 static method: called with the interface name, FeePayer.lateFine(5)
    static double lateFine(int days) {
        return days * 50;
    }
}
