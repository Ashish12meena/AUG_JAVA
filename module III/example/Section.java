import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// 2.13 Composition (HAS-A): a Section HAS members.
// A section is not a member, so Section does not extend CollegeMember.
public class Section {
    private String name;

    // 3.3 ArrayList instead of CollegeMember[60]
    // WHY? The array was full at 60, and removing meant shifting by hand. A list grows and shrinks by itself.
    // 3.1 Interface on the left (List), class on the right (ArrayList).
    // 3.10 <CollegeMember>: the compiler stops anything else (a String, a Payment) going in.
    private List<CollegeMember> members = new ArrayList<>();

    // 3.6 HashMap: id -> member
    // WHY? find(101) goes straight to the member. No loop over every member.
    // WHY keep the List too? The map has no order; the list keeps the admission order for showing.
    private Map<Integer, CollegeMember> byId = new HashMap<>();

    public Section(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(CollegeMember m) {
        // 3.6 containsKey(): duplicate check without a loop (Module II used a loop with equals())
        if (byId.containsKey(m.getId())) {
            System.out.println(m.getName() + " is already in " + name);
        } else {
            members.add(m);                 // no "section is full" any more
            byId.put(m.getId(), m);         // WHY both? The list and the map must always match.
        }
    }

    // 2.4 Overloading: find by id ...
    public CollegeMember find(int id) {
        return byId.get(id);                // 3.6 null if there is no such id
    }

    // 2.4 ... or find by name
    // WHY a loop here? The map's key is the id, not the name.
    public CollegeMember find(String memberName) {
        for (CollegeMember m : members) {
            if (m.getName().equalsIgnoreCase(memberName)) {
                return m;
            }
        }
        return null;
    }

    // 3.9 unmodifiableList(): others can READ the members but not change the list
    // WHY? getMembers().add(x) would skip the duplicate check and the map would not know about x.
    public List<CollegeMember> getMembers() {
        return Collections.unmodifiableList(members);
    }

    // only students (they have marks and a fee). Teachers are skipped.
    private List<Student> students() {
        List<Student> list = new ArrayList<>();
        for (CollegeMember m : members) {
            if (m instanceof Student s) {   // 2.7 instanceof: a capability check
                list.add(s);
            }
        }
        return list;
    }

    // 3.10 Wildcard: accepts a List<CollegeMember> AND a List<Student>
    // WHY "? extends"? A List<Student> is NOT a List<CollegeMember>, so the merit list
    // could not be passed. We only READ from the list here, so "extends" is right.
    public void showList(String title, List<? extends CollegeMember> list) {
        System.out.println("----- " + title + " -----");
        if (list.isEmpty()) {
            System.out.println("Nobody");
            return;
        }
        for (CollegeMember m : list) {
            m.showDetails();                // 2.7 dynamic dispatch: each object shows itself
        }
    }

    // 3.8 Comparator: sort a COPY, so members keeps the admission order
    // WHY Comparator, not Comparable? We need many orders (name, merit, fee due), not one.
    public List<CollegeMember> sortedByName() {
        List<CollegeMember> copy = new ArrayList<>(members);
        copy.sort(Comparator.comparing(CollegeMember::getName));     // 3.12 method reference
        return copy;
    }

    // 3.8 Merit list: total high to low; if totals tie, name A to Z
    public List<Student> meritList() {
        List<Student> list = students();
        list.sort(Comparator.comparing(Student::total).reversed()
                .thenComparing(Student::getName));
        return list;
    }

    // 3.8 Fee due list: biggest balance first, for the accounts office
    public List<Student> feeDueList() {
        List<Student> list = students();
        list.removeIf(Student::isFeeCleared);    // 3.7 remove while looping, done for us
        list.sort(Comparator.comparing(Student::balance).reversed());
        return list;
    }

    // 3.9 Collections.max() with a Comparator: no loop, no "best" variable
    public void showTopper() {
        List<Student> list = students();
        if (list.isEmpty()) {
            System.out.println("No students in " + name);
            return;
        }
        Student topper = Collections.max(list, Comparator.comparing(Student::total));
        System.out.println("Topper of " + name + ": " + topper + "  Total: " + topper.total());
    }

    // 3.6 Counting with a map: grade -> how many students
    // WHY TreeMap? Grades come out in A, B, C order.
    public void gradeReport() {
        Map<Character, Integer> count = new TreeMap<>();
        for (Student s : students()) {
            char g = s.grade();
            count.put(g, count.getOrDefault(g, 0) + 1);   // 0 the first time
        }
        System.out.println("Grade count: " + count);
    }

    public void feeReport() {
        double totalFee = 0;
        double totalPaid = 0;
        System.out.println("----- Fee report: " + name + " -----");
        for (CollegeMember m : members) {
            // 2.11 Interface reference: works for every fee payer, skips teachers
            if (m instanceof FeePayer f) {
                String state = f.isFeeCleared() ? "CLEARED" : "DUE";
                System.out.println(m.getName() + "  Fee: " + f.fee() + "  Paid: "
                        + f.paidAmount() + "  Balance: " + f.balance() + "  " + state);
                totalFee += f.fee();
                totalPaid += f.paidAmount();
            }
        }
        System.out.println("Total: " + totalFee + "  Collected: " + totalPaid
                + "  Pending: " + (totalFee - totalPaid));
    }

    // 3.7 Iterator: year end, students who PASSED and CLEARED the fee move to 2nd year
    // WHY an Iterator? We remove members WHILE looping over them.
    // members.remove(m) inside a for-each loop -> ConcurrentModificationException.
    public List<Student> promote() {
        List<Student> promoted = new ArrayList<>();
        Iterator<CollegeMember> it = members.iterator();
        while (it.hasNext()) {
            CollegeMember m = it.next();
            if (m instanceof Student s && s.isPassed() && s.isFeeCleared()) {
                it.remove();                // safe remove from the list
                byId.remove(s.getId());     // and from the map, so both still match
                promoted.add(s);
            }
        }
        return promoted;
    }
}
