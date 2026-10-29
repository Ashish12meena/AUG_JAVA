import java.util.TreeSet;

class Student {
    int id;
    String name;
    int total;                  // marks out of 500

    Student(int id, String name, int total) {
        this.id = id;
        this.name = name;
        this.total = total;
    }

    // same id = same student (HashSet uses this to find duplicates)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Student)) {
            return false;
        }
        Student other = (Student) obj;
        return id == other.id;
    }

    // equal students must give the same hash code
    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return id + "  " + name + "  " + total;
    }
}

public class Sets {
    public static void main(String[] args) {
        
        // // workshop sign-up sheet: Hita and Somya wrote their names twice
        // String[] signUps = {"Somya", "Hita", "Ritika", "Hita", "Disha", "Somya"};

        // Set<String> hash = new HashSet<>();
        // Set<String> linked = new LinkedHashSet<>();
        // Set<String> tree = new TreeSet<>();
        // for (int i = 0; i < signUps.length; i++) {
        //    hash.add(signUps[i]);
        //     linked.add(signUps[i]);
        //     tree.add(signUps[i]);
        // }
        // System.out.println("HashSet      : " + hash);      // no duplicates, no fixed order
        // System.out.println("LinkedHashSet: " + linked);    // no duplicates, sign-up order
        // System.out.println("TreeSet      : " + tree);      // no duplicates, A to Z
        // System.out.println("Add Hita again? " + hash.add("Hita"));   // false: already there
        // System.out.println();

        // // our own objects: the Set calls hashCode() and equals() to find duplicates
        // Set<Student> hostel = new HashSet<>();
        // hostel.add(new Student(101, "Hita", 438));
        // hostel.add(new Student(103, "Somya", 465));
        // hostel.add(new Student(101, "Hita", 438));        // new object, but same id
        // System.out.println("Hostel students: " + hostel.size());   // 2 (would be 3 without equals/hashCode)
        // System.out.println();

        // // TreeSet keeps numbers sorted: scholarship cut-off is 400
        TreeSet<Integer> totals = new TreeSet<>();
        totals.add(438);
        totals.add(244);
        totals.add(465);
        totals.add(412);
        totals.add(438);                                  // Disha: same total as Hita, kept once
        System.out.println("Totals       : " + totals);
        System.out.println("first()      : " + totals.first());
        System.out.println("last()       : " + totals.last());
        System.out.println("floor(450)   : " + totals.floor(450));      // biggest that is <= 450
        System.out.println("ceiling(400) : " + totals.ceiling(400));    // smallest that is >= 400
        System.out.println("headSet(400) : " + totals.headSet(400));    // below 400
        System.out.println("tailSet(400) : " + totals.tailSet(400));    // 400 and above
    }
}
