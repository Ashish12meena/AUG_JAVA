import java.util.ArrayList;
import java.util.List;

public class WhyGenerics {
    public static void main(String[] args) {
        // 1. without generics (raw type): anything goes in, a cast is needed
        List raw = new ArrayList();
        raw.add("Hita");
        raw.add(438);                                    // a number slipped in: no error
        String first = (String) raw.get(0);
        System.out.println("Raw list: " + raw + ", first: " + first);
        // String second = (String) raw.get(1);          // ERROR at RUN time: ClassCastException

        // 2. with generics: the compiler checks, no cast needed
        List<String> names = new ArrayList<>();
        names.add("Hita");
        names.add("Somya");
        // names.add(438);                               // ERROR at COMPILE time
        String name = names.get(0);                      // no cast
        System.out.println("Generic list: " + names + ", first: " + name);
    }
}
