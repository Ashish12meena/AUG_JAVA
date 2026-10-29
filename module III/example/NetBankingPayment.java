// 2.10 New requirement: the college adds Net Banking.
// NetBankingPayment IS-A Payment: write only its own pay().
// WHY is this the only new class? FeeCounter knows only Payment, so it does NOT change.
// Only chooseMode() in PortalApp (which turns the user's choice into an object) gets one more option.
public class NetBankingPayment extends Payment {
    private String bankName;

    public NetBankingPayment(String bankName) {
        super("Net Banking");
        this.bankName = bankName;
    }

    // 2.5 Overriding: how net banking pays
    @Override
    public void pay(double amount) {
        System.out.println("Log in to " + bankName + " net banking");
        System.out.println("Enter the OTP sent to your mobile");
        System.out.println("Rs " + amount + " paid by net banking");
    }
}
