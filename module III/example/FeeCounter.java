import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 2.10 + 2.11 Abstraction: the counter only knows FeePayer and Payment.
// It never asks "Student or Research Student?" or "UPI, Card or Cash?".
// WHY an object now (Module II used static methods)? The counter now KEEPS data:
// the line of students and the list of listeners. Data needs an object.
public class FeeCounter {
    // 3.5 Queue (FIFO): first to join the line is served first
    // WHY Queue, not List? We only add at the back and take from the front. Queue says exactly that.
    private Queue<FeePayer> line = new LinkedList<>();

    // 3.12 Listeners: everyone who wants to know when a fee is paid
    // WHY a list of FeePaidListener? FeeCounter does not need to know WHO they are.
    private List<FeePaidListener> listeners = new ArrayList<>();

    public void addListener(FeePaidListener listener) {
        listeners.add(listener);
    }

    public void join(FeePayer payer) {
        if (line.contains(payer)) {         // 3.2 contains() uses equals(): same id
            System.out.println(payer + " is already in the fee line");
        } else {
            line.offer(payer);
            System.out.println(payer + " joined the fee line. Position: " + line.size());
        }
    }

    // 3.5 poll(): take the first in line. null if the line is empty (no error)
    public FeePayer serveNext() {
        return line.poll();
    }

    public void showLine() {
        System.out.println("Fee line: " + line);     // 3.3 println(collection) uses toString()
    }

    public void collect(FeePayer payer, Payment mode, double amount, int daysLate) {
        double fine = FeePayer.lateFine(daysLate);   // 2.11 static method
        mode.pay(amount + fine);                     // 2.7 which pay()? decided at run time
        payer.addPayment(amount);
        mode.printReceipt(payer, amount, fine);

        // 3.12 Callback: tell every listener what happened
        // WHY? A new office (library, hostel) = one more listener. This method does NOT change.
        FeeEvent event = new FeeEvent(payer, amount, fine);
        for (FeePaidListener listener : listeners) {
            listener.feePaid(event);
        }
    }
}
