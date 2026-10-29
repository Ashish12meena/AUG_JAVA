// 2.1 CardPayment IS-A Payment
// WHY extend Payment? It gets the receipt for free and only writes its own pay().
public class CardPayment extends Payment {
    private String cardNumber;

    public CardPayment(String cardNumber) {
        super("Card");   // 2.3 tell the parent which mode this is
        this.cardNumber = cardNumber;
    }

    // 2.5 Overriding: how a card pays
    // WHY different? The bank takes 1% extra, only for card.
    @Override
    public void pay(double amount) {
        double charge = amount / 100;
        String lastFour = cardNumber.substring(cardNumber.length() - 4);   // never show the full number
        System.out.println("Swipe card ending " + lastFour);
        System.out.println("Enter card PIN");
        System.out.println("Rs " + (amount + charge) + " paid (Rs " + charge + " card charge)");
    }
}
