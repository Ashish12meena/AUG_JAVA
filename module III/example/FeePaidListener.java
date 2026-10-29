// 3.12 Listener interface: "call me back when a fee is paid"
// WHY an interface? FeeCounter calls feePaid() without knowing who is listening:
// accounts office, parents' SMS, exam cell ... any class can listen.
// 2.11 Functional interface: only one abstract method, so a lambda can be a listener.
@FunctionalInterface
public interface FeePaidListener {
    void feePaid(FeeEvent e);
}
