// 2.13 Composition (HAS-A): a Section HAS members.
// A section is not a member, so Section does not extend CollegeMember.
// WHY composition? A section is a group of people, not a kind of person.
public class Section {
    private String name;
    // 2.7 Upcasting: one parent-type array holds Student, ResearchStudent and Teacher objects
    // WHY CollegeMember[]? One array for everyone, instead of one array per type.
    private CollegeMember[] members = new CollegeMember[60];
    private int size = 0;

    public Section(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Any child object can be passed here (upcasting)
    // WHY? One add() works for every member type, even ones added later.
    public void add(CollegeMember m) {
        for (int i = 0; i < size; i++) {
            // 2.9 equals(): our version compares ids
            // WHY not ==? == checks memory address; equals() checks "same person (same id)?"
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
    // WHY same name? Both do the same job (find a member), only the input is different.
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
            // WHY no if-else on type? Each object already knows how to show itself.
            members[i].showDetails();
        }
    }

    public void showTopper() {
        CollegeMember topper = null;
        int best = -1;
        for (int i = 0; i < size; i++) {
            // 2.7 instanceof pattern matching: only Gradable members have marks
            // WHY check here? A teacher has no total(). This depends on a capability,
            // so instanceof is the right tool (not a replacement for polymorphism).
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

    public void feeReport() {
        double totalFee = 0;
        double totalPaid = 0;
        System.out.println("----- Fee report: " + name + " -----");
        for (int i = 0; i < size; i++) {
            // 2.11 Interface reference: works for every fee payer, skips teachers
            // WHY FeePayer, not Student? Any future fee-paying class also works here.
            if (members[i] instanceof FeePayer f) {
                String state = f.isFeeCleared() ? "CLEARED" : "DUE";   // 2.11 default method
                // 2.7 f.fee(): Student gives 120000, ResearchStudent gives 150000
                System.out.println(members[i].getName() + "  Fee: " + f.fee() + "  Paid: "
                        + f.paidAmount() + "  Balance: " + f.balance() + "  " + state);
                totalFee += f.fee();
                totalPaid += f.paidAmount();
            }
        }
        System.out.println("Total: " + totalFee + "  Collected: " + totalPaid
                + "  Pending: " + (totalFee - totalPaid));
    }
}
