import java.util.List;

public class BoundedTypes {
    // bounded type: T must be a Number (Integer, Double ...), so doubleValue() is allowed
    static <T extends Number> double average(List<T> list) {
        double sum = 0;
        for (T n : list) {
            sum += n.doubleValue();
        }
        return sum / list.size();
    }

    public static void main(String[] args) {
        List<Integer> marks = List.of(85, 92, 78, 88, 95);      // Hita's five subjects
        List<Double> fees = List.of(120000.0, 150000.0);
        List<String> names = List.of("Hita", "Sakshi");

        System.out.println("Hita's average mark: " + average(marks));
        System.out.println("Average fee        : " + average(fees));
        // average(names);                                      // ERROR: String is not a Number
    }
}
