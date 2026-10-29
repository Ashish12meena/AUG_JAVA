// 3.12 Event object: carries the details of WHAT happened to every listener
// WHY an object, not 3 parameters? If we add a detail later (date, mode), the
// listener interface does not change. Only this class gets a new field.
public class FeeEvent {
    private FeePayer payer;
    private double amount;
    private double fine;

    public FeeEvent(FeePayer payer, double amount, double fine) {
        this.payer = payer;
        this.amount = amount;
        this.fine = fine;
    }

    public FeePayer getPayer() {
        return payer;
    }

    public double getAmount() {
        return amount;
    }

    public double getFine() {
        return fine;
    }
}
