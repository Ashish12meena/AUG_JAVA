// 2.10 Abstract class: the common part of everyone in the college.
// Nobody is "just a member", so no object of this class is allowed.
public abstract class CollegeMember {
    // 1.7 Static variable: automatic ids for every member
    private static int nextId = 101;

    // 2.8 Blank final: set once in the constructor, never changed
    private final int id;
    private String name;

    // 2.10 Abstract class can have a constructor; children call it with super()
    public CollegeMember(String name) {
        this.id = nextId;
        nextId++;
        this.name = name;
    }

    // 2.8 Final method: no child can change how the id is read
    public final int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // 2.10 Abstract methods: every child must write these
    public abstract String role();

    public abstract void showDetails();

    // 2.9 toString(): println(member) prints this
    @Override
    public String toString() {
        // 2.7 role() of the real object runs (dynamic dispatch)
        return id + "  " + name + "  [" + role() + "]";
    }

    // 2.9 equals(): two members are equal if their ids are equal
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
    @Override
    public int hashCode() {
        return id;
    }
}
