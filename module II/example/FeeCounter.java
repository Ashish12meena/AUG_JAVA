// 2.10 + 2.11 Abstraction: the counter only knows FeePayer and Payment.
// It never asks "Student or Research Student?" or "UPI, Card or Cash?".
// WHY? If it checked every type, we would have to change it for every new mode.
// Now a new mode (Net Banking) needs a new class, but NO change here.
public class FeeCounter {
    // WHY static? The counter keeps no data of its own, so no FeeCounter object is needed.
    // WHY FeePayer and Payment as parameter types? Any fee payer and any mode can be passed in.
    public static void collect(FeePayer payer, Payment mode, double amount, int daysLate) {
        double fine = FeePayer.lateFine(daysLate);   // 2.11 static method: called with interface name
        mode.pay(amount + fine);                     // 2.7 which pay()? decided at run time by the object
        payer.addPayment(amount);
        mode.printReceipt(payer, amount, fine);
    }
}
