import java.util.ArrayList;
import java.util.LinkedList;
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

public class Lists {
    public static void main(String[] args) {
        // ArrayList: the section register, in roll number order
        List<Student> section = new ArrayList<>();
        Student somya = new Student(103, "Somya", 465);
        section.add(new Student(101, "Hita", 438));
        section.add(new Student(102, "Sakshi", 244));
        section.add(somya);
        section.add(new Student(105, "Disha", 438));

        System.out.println("First student: " + section.get(0));     // index starts at 0

        // Ritika (104) joins late: put her at index 3, before Disha
        section.add(3, new Student(104, "Ritika", 412));
        for (int i = 0; i < section.size(); i++) {
            System.out.println(i + " -> " + section.get(i));
        }

        // Sakshi's paper is re-checked: replace her record at index 1
        section.set(1, new Student(102, "Sakshi", 310));
        System.out.println("After re-check: " + section.get(1));

        System.out.println("Somya is at index " + section.indexOf(somya));

        // subList(from, to): "from" is included, "to" is not
        System.out.println("Front bench: " + section.subList(0, 2));

        section.remove(4);                                          // Disha moves to section B
        System.out.println("Students now: " + section.size());
        System.out.println();

        // a List allows duplicates: Hita came to both lectures
        List<String> attendance = new ArrayList<>();
        attendance.add("Hita");
        attendance.add("Somya");
        attendance.add("Hita");
        System.out.println("Attendance: " + attendance);

        // LinkedList: notice board, a new notice goes on top
        LinkedList<String> notices = new LinkedList<>();
        notices.add("Fee last date 30 Oct");
        notices.addFirst("Java test on Monday");
        notices.addLast("Library closes at 6 pm");
        System.out.println("Notices: " + notices);
        notices.removeFirst();                                      // the test is over
        System.out.println("After the test: " + notices);

        // List.of(): a fixed list, it cannot be changed
        List<String> subjects = List.of("Java", "DBMS", "Maths", "English", "Networks");
        System.out.println("Subjects: " + subjects);
        // subjects.add("Python");     // ERROR at run time: UnsupportedOperationException
    }
}
