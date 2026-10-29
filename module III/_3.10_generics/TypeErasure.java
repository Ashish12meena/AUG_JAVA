import java.util.ArrayList;
import java.util.List;

public class TypeErasure {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        List<Integer> marks = new ArrayList<>();
        names.add("Hita");
        marks.add(438);

        // at run time both are just ArrayList: <String> and <Integer> are gone
        System.out.println("names is a " + names.getClass().getName());
        System.out.println("marks is a " + marks.getClass().getName());
        System.out.println("Same class at run time? " + (names.getClass() == marks.getClass()));   // true

        String first = names.get(0);     // the compiler secretly writes: (String) names.get(0)
        System.out.println("First name: " + first);

        // things erasure does not allow
        // Object obj = names;
        // if (obj instanceof List<String>) { }   // ERROR: run time cannot see <String>
        // List<int> x;                            // ERROR: no primitive types, use Integer
        // inside Pair: new K()                    // ERROR: Java does not know what K is at run time
        // inside Pair: new K[10]                  // ERROR: same reason
        // inside Pair: static K k;                // ERROR: static belongs to the class, K belongs to each object
    }
}
