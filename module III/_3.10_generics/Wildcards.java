import java.util.ArrayList;
import java.util.List;

public class Wildcards {
    // NOT flexible: accepts only List<Number>, not List<Integer> or List<Double>
    static double sumOnlyNumbers(List<Number> list) {
        double sum = 0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }

    // upper bounded wildcard: we only READ numbers from the list (producer -> extends)
    static double sum(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        // list.add(50);                     // ERROR: the list may really be a List<Double>
        return sum;
    }

    // lower bounded wildcard: we only PUT Integers into the list (consumer -> super)
    static void addPassMarks(List<? super Integer> list) {
        list.add(40);
        list.add(50);
    }

    // unbounded wildcard: type does not matter, we only need size()
    static void showCount(List<?> list) {
        System.out.println("Items: " + list.size());
    }

    public static void main(String[] args) {
        List<Integer> marks = List.of(85, 92, 78, 88, 95);
        List<Double> fees = List.of(120000.0, 150000.0);

        // sumOnlyNumbers(marks);            // ERROR: List<Integer> is not a List<Number>
        System.out.println("Sum of marks: " + sum(marks));    // List<Integer> works
        System.out.println("Sum of fees : " + sum(fees));     // List<Double> works

        List<Number> cutOffs = new ArrayList<>();
        addPassMarks(cutOffs);                                // List<Number> can take Integers
        System.out.println("Cut-offs: " + cutOffs);

        List<String> names = List.of("Hita", "Somya", "Disha");
        showCount(names);
        showCount(marks);
    }
}
