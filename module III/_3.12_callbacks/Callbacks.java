import java.util.ArrayList;
import java.util.List;

// event object: the details of WHAT happened
class FeeEvent {
    String studentName;
    double amount;

    FeeEvent(String studentName, double amount) {
        this.studentName = studentName;
        this.amount = amount;
    }
}

// listener interface: "call me back when a fee is paid"
interface FeePaidListener {
    void feePaid(FeeEvent e);
}

// event source: does the work, then calls back every listener
class FeeCounter {
    private List<FeePaidListener> listeners = new ArrayList<>();

    void addListener(FeePaidListener listener) {
        listeners.add(listener);
    }

    void collect(String name, double amount) {
        System.out.println(name + " paid Rs " + amount + " at the fee counter");
        FeeEvent e = new FeeEvent(name, amount);
        for (FeePaidListener listener : listeners) {
            listener.feePaid(e);             // callback: FeeCounter does not know WHO is listening
        }
        System.out.println();
    }
}

// 1. listener as a normal class
class AccountsOffice implements FeePaidListener {
    @Override
    public void feePaid(FeeEvent e) {
        System.out.println("  Accounts office: Rs " + e.amount + " entered for " + e.studentName);
    }
}

public class Callbacks {
    static void printReceipt(FeeEvent e) {
        System.out.println("  Printer: receipt for " + e.studentName);
    }

    public static void main(String[] args) {
        FeeCounter counter = new FeeCounter();

        counter.addListener(new AccountsOffice());              // 1. normal class

        counter.addListener(new FeePaidListener() {             // 2. anonymous class
            @Override
            public void feePaid(FeeEvent e) {
                System.out.println("  SMS to parent: fee of " + e.studentName + " received");
            }
        });

        // 3. lambda
        counter.addListener(e -> System.out.println("  Exam cell: hall ticket ready for " + e.studentName));

        // 4. method reference: same as  e -> Callbacks.printReceipt(e)
        counter.addListener(Callbacks::printReceipt);

        counter.collect("Hita", 120000);
        counter.collect("Sakshi", 150000);
    }
}
