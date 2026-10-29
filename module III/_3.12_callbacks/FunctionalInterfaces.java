import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Student {
    int id;
    String name;
    int total;                  // marks out of 500

    Student(int id, String name, int total) {
        this.id = id;
        this.name = name;
        this.total = total;
    }

    @Override
    public String toString() {
        return id + "  " + name + "  " + total;
    }
}

public class FunctionalInterfaces {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(101, "Hita", 438));
        list.add(new Student(102, "Sakshi", 244));
        list.add(new Student(103, "Somya", 465));
        list.add(new Student(104, "Ritika", 412));
        list.add(new Student(105, "Disha", 438));

        // Runnable: no input, no output. Just a job to run.
        Runnable bell = () -> System.out.println("Bell rings: result day!");
        bell.run();

        // Predicate<T>: takes T, gives true or false
        Predicate<Student> passed = s -> s.total >= 250;          // 50%

        // Function<T, R>: takes T, gives R
        Function<Student, Double> percent = s -> s.total / 5.0;

        // Consumer<T>: takes T, gives nothing back
        Consumer<Student> show = s -> System.out.println(s.name + "  " + percent.apply(s) + "%");

        // Comparator<T>: takes two T, gives an int
        Comparator<Student> byTotal = (a, b) -> b.total - a.total;

        list.sort(byTotal);
        System.out.println("Passed students:");
        for (Student s : list) {
            if (passed.test(s)) {
                show.accept(s);
            }
        }
        System.out.println();

        // method reference: short form of a lambda that only calls one method
        List<String> names = List.of("Hita", "Somya", "Disha");
        names.forEach(System.out::println);                       // same as n -> System.out.println(n)
        System.out.println();

        // Stream basics (optional): filter -> map -> collect
        List<String> scholarship = list.stream()
                .filter(s -> s.total >= 400)                      // keep 400 and above
                .map(s -> s.name)                                 // Student -> name
                .collect(Collectors.toList());                    // back into a List
        System.out.println("Scholarship: " + scholarship);
    }
}
