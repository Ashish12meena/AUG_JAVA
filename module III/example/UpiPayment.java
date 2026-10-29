// 2.1 UpiPayment IS-A Payment
// WHY extend Payment? It gets the receipt for free and only writes its own pay().
public class UpiPayment extends Payment {
    private String upiId;

    public UpiPayment(String upiId) {
        super("UPI");   // 2.3 tell the parent which mode this is
        this.upiId = upiId;
    }

    // 2.5 Overriding: how UPI pays
    @Override
    public void pay(double amount) {
        System.out.println("Scan the QR code at the counter");
        System.out.println("Enter UPI PIN for " + upiId);
        System.out.println("Rs " + amount + " sent by UPI");
    }
}
