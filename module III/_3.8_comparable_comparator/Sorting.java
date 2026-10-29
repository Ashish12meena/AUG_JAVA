import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Comparable: the NATURAL order of a Student = by roll number
class Student implements Comparable<Student> {
    int id;
    String name;
    int total;                  // marks out of 500

    Student(int id, String name, int total) {
        this.id = id;
        this.name = name;
        this.total = total;
    }

    @Override
    public int compareTo(Student other) {
        return this.id - other.id;      // negative: this comes first, 0: same, positive: other first
    }

    @Override
    public String toString() {
        return id + "  " + name + "  " + total;
    }
}

// Comparator as a separate class: higher total first
class ByTotal implements Comparator<Student> {
    @Override
    public int compare(Student a, Student b) {
        return b.total - a.total;
    }
}

public class Sorting {
    static void print(String title, List<Student> list) {
        System.out.println("--- " + title + " ---");
        for (Student s : list) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(103, "Somya", 465));
        list.add(new Student(101, "Hita", 438));
        list.add(new Student(105, "Disha", 438));
        list.add(new Student(102, "Sakshi", 244));
        list.add(new Student(104, "Ritika", 412));

        // 1. natural order: uses compareTo() written inside Student
        Collections.sort(list);
        print("By roll number (Comparable)", list);

        // 2. Comparator class
        list.sort(new ByTotal());
        print("By total (Comparator class)", list);       // Hita and Disha tie

        // 3. anonymous class: a Comparator with no class name
        list.sort(new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                return a.name.compareTo(b.name);           // String has its own compareTo()
            }
        });
        print("By name (anonymous class)", list);

        // 4. lambda: the same idea in one line. Lowest total first (extra class list)
        list.sort((a, b) -> a.total - b.total);
        print("Lowest total first (lambda)", list);

        // 5. Comparator.comparing(): Java writes compare() for us
        // merit list: total high to low, and if totals tie, name A to Z
        // (Student s): we tell Java the type, because .reversed() comes next
        list.sort(Comparator.comparing((Student s) -> s.total).reversed()
                .thenComparing(s -> s.name));
        print("Merit list (comparing + reversed + thenComparing)", list);

        // 6. Arrays.sort(): for normal arrays
        int[] totals = {465, 438, 244, 412};
        Arrays.sort(totals);
        System.out.println("Sorted totals: " + Arrays.toString(totals));

        String[] names = {"Somya", "Hita", "Sakshi"};
        Arrays.sort(names);
        System.out.println("Sorted names : " + Arrays.toString(names));
    }
}
