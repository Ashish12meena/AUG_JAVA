import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

class Student {
    int id;
    String name;
    int total; // marks out of 500

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

public class Iterators {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(101, "Hita", 438));
        list.add(new Student(102, "Sakshi", 244));
        list.add(new Student(103, "Somya", 465));
        list.add(new Student(104, "Ritika", 412));
        list.add(new Student(105, "Disha", 438));

        // // 1. three ways to visit every student
        for (int i = 0; i < list.size(); i++) { // index loop (List only)
            System.out.print(list.get(i).name + " ");
        }
        System.out.println();
        for (Student s : list) { // for-each (any collection)
            System.out.print(s.name + " ");
        }
        System.out.println();
        list.forEach(s -> System.out.print(s.name + " ")); // forEach() + lambda
        System.out.println();
        System.out.println();

        // 2. scholarship list: remove everyone below 400
        // WRONG: removing inside for-each
        // for (Student s : list) {
        // if (s.total < 400) {
        // // list.remove(s); // ERROR at run time: ConcurrentModificationException
        // }
        // }

        // // RIGHT: let the Iterator remove it
        Iterator<Student> it = list.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.total < 400) {
                it.remove(); // safe
            }
        }
        System.out.println("Scholarship list: " + list);
        System.out.println();

        // // 3. ListIterator: change while moving, and go both ways
        List<String> seats = new ArrayList<>();
        seats.add("Hita");
        seats.add("Somya");
        seats.add("Ritika");
        ListIterator<String> li = seats.listIterator();
        while (li.hasNext()) {
            String name = li.next();
            li.set(name.toUpperCase()); // name cards in capitals
            if (name.equals("Hita")) {
                li.add("DISHA"); // late admission sits next to Hita
            }
        }
        System.out.println("Seats: " + seats);
        System.out.print("Back row first: ");
        while (li.hasPrevious()) {
            System.out.print(li.previous() + " ");
        }
        System.out.println();
        System.out.println();

        // // 4. fail-safe: CopyOnWriteArrayList loops over a copy, so adding inside the
        // loop is allowed
        List<String> club = new CopyOnWriteArrayList<>();
        club.add("Hita");
        club.add("Somya");
        for (String name : club) {
            if (name.equals("Hita")) {
                club.add("Sakshi"); // Hita brings a friend
            }
        }
        System.out.println("Coding club: " + club);

        // // 5. Enumeration: the old (legacy) way, used with Vector
        Vector<String> oldList = new Vector<>();
        oldList.add("Hita");
        oldList.add("Somya");
        Enumeration<String> en = oldList.elements();
        while (en.hasMoreElements()) {
            System.out.print(en.nextElement() + " ");
        }
        System.out.println();
    }
}
