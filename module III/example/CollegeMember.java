// 2.10 Abstract class: the common part of everyone in the college.
// WHY abstract? Nobody is "just a member": everyone is a student or a teacher.
// new CollegeMember("Hita") -> what is Hita? Student? Teacher? So no object of this class is allowed.
// WHY a class, not an interface? All members share data (id, name) and code (toString()).
// An interface cannot hold that data.
public abstract class CollegeMember {
    // 1.7 Static variable: automatic ids for every member
    // WHY static? One counter shared by ALL members, so no two members get the same id.
    private static int nextId = 101;

    // 2.8 Blank final: set once in the constructor, never changed
    // WHY final? A college id must never change after admission.
    // 3.6 It is also the KEY in Section's HashMap. A key that changes would be lost in the map.
    private final int id;
    private String name;

    // 2.10 Abstract class can have a constructor; children call it with super()
    // WHY? id and name belong to CollegeMember, so the parent sets them, in ONE place.
    public CollegeMember(String name) {
        this.id = nextId;
        nextId++;
        this.name = name;
    }

    // 2.8 Final method
    // WHY final? No child should change how the id is read.
    public final int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // 2.10 Abstract methods: every child must write these
    // WHY abstract? Every member HAS a role and details, but each type shows them differently.
    // Parent says WHAT, child says HOW. A child that forgets them will not compile.
    public abstract String role();

    public abstract void showDetails();

    // 2.9 toString(): println(member) prints this
    // WHY? Without it, println prints something like Student@1b6d3586.
    // 3.3 println(list) also calls toString() of every element.
    @Override
    public String toString() {
        // 2.7 role() of the real object runs (dynamic dispatch)
        return id + "  " + name + "  [" + role() + "]";
    }

    // 2.9 equals(): two members are equal if their ids are equal
    // WHY? Default equals() works like ==, it only checks the memory address.
    // Our college rule: same id = same person.
    // 3.2 / 3.4 USED BY: line.contains() in FeeCounter and the Set in Workshop.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CollegeMember)) {
            return false;
        }
        CollegeMember other = (CollegeMember) obj;   // 2.7 Downcasting
        return id == other.id;
    }

    // 2.9 hashCode(): equal members must give the same hash code
    // 3.4 NOW we see WHY: a HashSet first uses hashCode() to find the bucket, then equals().
    // Without this, the same person could register twice for the workshop.
    @Override
    public int hashCode() {
        return id;
    }
}
