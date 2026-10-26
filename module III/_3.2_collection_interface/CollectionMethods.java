import java.util.ArrayList;
import java.util.Collection;

public class CollectionMethods {
    public static void main(String[] args) {
        // students who joined the Java workshop
        Collection<String> java = new ArrayList<>();
        java.add("Hita");
        java.add("Sakshi");
        java.add("Somya");
        System.out.println("Java workshop: " + java + "  size: " + java.size());
        System.out.println("Is Somya in? " + java.contains("Somya"));

        java.remove("Sakshi");                       // remove by value
        System.out.println("After Sakshi leaves: " + java);

        // students who joined the Python workshop
        Collection<String> python = new ArrayList<>();
        python.add("Somya");
        python.add("Ritika");
        python.add("Disha");
        System.out.println("Python workshop: " + python);
        System.out.println();

        // bulk methods change the collection they are called on, so work on a copy
        Collection<String> all = new ArrayList<>(java);
        all.addAll(python);
        System.out.println("addAll    (everyone)     : " + all);       // Somya twice

        Collection<String> both = new ArrayList<>(java);
        both.retainAll(python);
        System.out.println("retainAll (in both)      : " + both);

        Collection<String> onlyJava = new ArrayList<>(java);
        onlyJava.removeAll(python);
        System.out.println("removeAll (only Java)    : " + onlyJava);

        System.out.println("containsAll (all has every Python student?) " + all.containsAll(python));
        System.out.println();

        // toArray(): back to a normal array
        Object[] arr = java.toArray();
        System.out.println("Array length: " + arr.length + ", first: " + arr[0]);

        // autoboxing: a collection holds only objects, so int becomes Integer
        Collection<Integer> marks = new ArrayList<>();
        marks.add(85);                               // int -> Integer (autoboxing)
        marks.add(92);
        marks.add(78);
        int sum = 0;
        for (int m : marks) {                        // Integer -> int (unboxing)
            sum += m;
        }
        System.out.println("Hita's first 3 marks: " + marks + "  sum: " + sum);
        // Collection<int> x;                        // ERROR: only objects, use Integer

        python.clear();
        System.out.println("Python workshop cancelled. Empty? " + python.isEmpty());
    }
}
