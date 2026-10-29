import java.util.LinkedHashSet;
import java.util.Set;

// 2.13 Composition: a Workshop HAS registered members
// 3.4 Set: a person can register only ONCE
public class Workshop {
    private String title;

    // WHY Set? No duplicates, and the Set checks it for us (no loop).
    // WHY LinkedHashSet? Seats are first come, first served, so registration order must be kept.
    // HOW does it know "same person"? hashCode() and equals() in CollegeMember (same id).
    private Set<CollegeMember> registered = new LinkedHashSet<>();

    public Workshop(String title) {
        this.title = title;
    }

    public void register(CollegeMember m) {
        if (registered.add(m)) {            // add() gives false if already there
            System.out.println(m.getName() + " registered for " + title);
        } else {
            System.out.println(m.getName() + " is already registered for " + title);
        }
    }

    public void show() {
        System.out.println("----- " + title + " (" + registered.size() + " registered) -----");
        for (CollegeMember m : registered) {
            System.out.println(m);
        }
    }
}
