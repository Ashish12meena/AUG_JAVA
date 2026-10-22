// 2.13 Composition (has-a): a Section HAS members.
// A member is not a section, so Section does not extend anything.
public class Section {
    private String name;
    // 2.7 Upcasting: one parent-type array holds UG, PG and Instructor objects
    private CollegeMember[] members = new CollegeMember[60];
    private int size = 0;

    public Section(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Any child object can be passed here (upcasting)
    public void add(CollegeMember m) {
        for (int i = 0; i < size; i++) {
            // 2.9 equals(): our version compares ids
            if (members[i].equals(m)) {
                System.out.println(m.getName() + " is already in " + name);
                return;
            }
        }
        if (size == members.length) {
            System.out.println(name + " is full");
        } else {
            members[size] = m;
            size++;
        }
    }

    // 2.4 Overloading: find by id ...
    public CollegeMember find(int id) {
        for (int i = 0; i < size; i++) {
            if (members[i].getId() == id) {
                return members[i];
            }
        }
        return null;
    }

    // 2.4 ... or find by name
    public CollegeMember find(String memberName) {
        for (int i = 0; i < size; i++) {
            if (members[i].getName().equalsIgnoreCase(memberName)) {
                return members[i];
            }
        }
        return null;
    }

    public void showAll() {
        System.out.println("----- " + name + " -----");
        if (size == 0) {
            System.out.println("No members");
            return;
        }
        for (int i = 0; i < size; i++) {
            // 2.7 Dynamic method dispatch: showDetails() of the real object runs
            members[i].showDetails();
        }
    }

    public void showTopper() {
        CollegeMember topper = null;
        int best = -1;
        for (int i = 0; i < size; i++) {
            // 2.7 instanceof pattern matching: only Gradable members have marks
            if (members[i] instanceof Gradable g && g.total() > best) {
                best = g.total();
                topper = members[i];
            }
        }
        if (topper == null) {
            System.out.println("No students in " + name);
        } else {
            System.out.println("Topper of " + name + ": " + topper + "  Total: " + best);
        }
    }

    public double totalFee() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            // 2.11 Interface reference: works for UG and PG, skips instructors
            if (members[i] instanceof FeePayer f) {
                sum += f.fee();
            }
        }
        return sum;
    }
}
