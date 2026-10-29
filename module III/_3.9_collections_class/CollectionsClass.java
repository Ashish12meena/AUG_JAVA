import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

public class CollectionsClass {
    public static void main(String[] args) {
        List<Integer> totals = new ArrayList<>();
        totals.add(438);
        totals.add(244);
        totals.add(465);
        totals.add(412);
        totals.add(438);
        System.out.println("Totals   : " + totals);
        System.out.println("max      : " + Collections.max(totals));
        System.out.println("min      : " + Collections.min(totals));
        System.out.println("frequency of 438: " + Collections.frequency(totals, 438));

        Collections.sort(totals);
        System.out.println("sort     : " + totals);
        // binarySearch() works only on a SORTED list
        System.out.println("binarySearch(412): index " + Collections.binarySearch(totals, 412));

        Collections.reverse(totals);
        System.out.println("reverse  : " + totals);
        System.out.println();

        // max() with a Comparator: the topper
        List<Student> section = new ArrayList<>();
        section.add(new Student(101, "Hita", 438));
        section.add(new Student(102, "Sakshi", 244));
        section.add(new Student(103, "Somya", 465));
        Student topper = Collections.max(section, (a, b) -> a.total - b.total);
        System.out.println("Topper: " + topper);
        System.out.println();

        // shuffle(): random viva order, different every run
        List<String> viva = new ArrayList<>();
        viva.add("Hita");
        viva.add("Sakshi");
        viva.add("Somya");
        viva.add("Ritika");
        viva.add("Disha");
        Collections.shuffle(viva);
        System.out.println("Viva order: " + viva);

        // unmodifiableList(): put the merit list on the notice board, nobody can change it
        List<String> merit = new ArrayList<>();
        merit.add("Somya");
        merit.add("Disha");
        merit.add("Hita");
        List<String> noticeBoard = Collections.unmodifiableList(merit);
        System.out.println("Notice board: " + noticeBoard);
        // noticeBoard.add("Sakshi");    // ERROR at run time: UnsupportedOperationException

        // synchronizedList(): safe when many threads use one list (Module V)
        List<String> attendance = Collections.synchronizedList(new ArrayList<>());
        attendance.add("Hita");
        System.out.println("Attendance: " + attendance);
    }
}
