import java.util.ArrayList;
import java.util.List;

public class GenericMethods {
    // generic method: <T> before the return type. Works for a list of ANY type T
    static <T> void printAll(List<T> list) {
        for (T item : list) {
            System.out.println("  " + item);
        }
    }

    // T is also the return type: a String list gives back a String, a Pair list gives back a Pair
    static <T> T lastOf(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Hita");
        names.add("Somya");

        List<Pair<String, Integer>> results = new ArrayList<>();
        results.add(new Pair<>("Hita", 438));
        results.add(new Pair<>("Somya", 465));

        System.out.println("Names:");
        printAll(names);                                 // T = String (Java works it out)
        System.out.println("Results:");
        printAll(results);                               // T = Pair<String, Integer>

        String lastName = lastOf(names);                 // no cast needed
        Pair<String, Integer> lastResult = lastOf(results);
        System.out.println("Last name  : " + lastName);
        System.out.println("Last result: " + lastResult);

        System.out.println("Names again:");
        GenericMethods.<String>printAll(names);          // T written by hand (rarely needed)
    }
}
