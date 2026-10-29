// 3.12 Listener written as a full class
// WHY a class, not a lambda? It KEEPS data: the money collected today.
// A lambda cannot have its own fields.
public class AccountsOffice implements FeePaidListener {
    private double collectedToday = 0;

    @Override
    public void feePaid(FeeEvent e) {
        double money = e.getAmount() + e.getFine();
        collectedToday += money;
        System.out.println("Accounts : Rs " + money + " entered. Collected today: Rs " + collectedToday);
    }
}
