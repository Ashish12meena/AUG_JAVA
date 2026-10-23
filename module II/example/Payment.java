// 2.10 Abstract class: every payment mode (UPI, Card, Cash) IS-A Payment.
// WHY abstract? Nobody pays by "just Payment", it is always UPI, Card or Cash.
// WHY a class, not an interface? All modes share data (modeName, receipt number)
// and the same receipt code. An interface cannot hold that data.
public abstract class Payment {
    // 1.7 Static variable: one receipt counter shared by all modes
    // WHY static? Receipt numbers must go 1, 2, 3... across UPI, Card AND Cash together.
    private static int nextReceipt = 1;
    private String modeName;

    public Payment(String modeName) {
        this.modeName = modeName;
    }

    // 2.10 Abstract method: WHAT = pay the amount. HOW = each mode decides
    // WHY abstract? Every mode MUST pay, but each pays differently (QR, swipe, cash).
    public abstract void pay(double amount);

    // 2.8 final method: every mode prints the same receipt, no mode can change it
    // WHY final? The college receipt format must be the same for all modes.
    public final void printReceipt(FeePayer payer, double amount, double fine) {
        System.out.println("------ VGU College Fee Receipt ------");
        System.out.println("Receipt No : " + nextReceipt);
        System.out.println("Student    : " + payer);              // 2.9 toString() of the real object
        System.out.println("Fee paid   : " + amount);
        System.out.println("Late fine  : " + fine);
        System.out.println("Mode       : " + modeName);
        System.out.println("Balance    : " + payer.balance());    // 2.11 default method
        System.out.println("-------------------------------------");
        nextReceipt++;
    }
}
