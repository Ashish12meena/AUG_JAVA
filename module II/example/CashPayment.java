// 2.1 CashPayment IS-A Payment
// WHY extend Payment? It gets the receipt for free and only writes its own pay().
public class CashPayment extends Payment {
    public CashPayment() {
        super("Cash");   // 2.3 tell the parent which mode this is
    }

    // 2.5 Overriding: how cash is paid
    @Override
    public void pay(double amount) {
        System.out.println("Give cash at the fee counter");
        System.out.println("Cashier counts Rs " + amount);
    }
}
