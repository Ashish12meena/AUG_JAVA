interface FeePayer {
    double FINE_PER_DAY = 50;               // public static final

    double fee();                           // public abstract

    default void showFee() {                // default: has a body
        line();
        System.out.println("Fee: " + fee());
        line();
    }

    static double lateFine(int days) {      // static: call with FeePayer.lateFine()
        return days * FINE_PER_DAY;
    }

    private void line() {                   // private: helper for default methods
        System.out.println("-------------");
    }
}

interface Learning extends FeePayer {   // interface extends interface
    void work();
    void attendClass();
}

interface Teaching {
    void work();
    void takeClass();
}

class Person {
    String name;

    Person(String name) {
        this.name = name;
    }
}

class Student extends Person implements Learning {
    Student(String name) {
        super(name);
    }

    @Override
    public void work() {                    // must be public
        System.out.println(name + " attends the Java class");
    }

    @Override
    public void attendClass() {
        System.out.println(name + " is in the Java class");
    }

    @Override
    public double fee() {
        return 120000;
    }
}

class Teacher extends Person implements Teaching {
    Teacher(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println(name + " teaches Java");
    }

    @Override
    public void takeClass() {
        System.out.println(name + " is taking the Java class");
    }
}

// PG student who also teaches the lab: one class, two parent types
class TeachingAssistant extends Person implements Learning, Teaching {
    TeachingAssistant(String name) {
        super(name);
    }

    @Override
    public void work() {                    // one body for both: no diamond problem
        System.out.println(name + " studies and also teaches the Java lab");
    }

    @Override
    public void attendClass() {
        System.out.println(name + " attends the Cloud Computing class");
    }

    @Override
    public void takeClass() {
        System.out.println(name + " is taking the Java lab");
    }

    @Override
    public double fee() {
        return 150000;
    }

    @Override
    public void showFee() {                 // default method can be overridden
        System.out.println(name + " PG fee: " + fee());
    }
}

public class Interfaces {
    public static void main(String[] args) {
        Student s1 = new Student("Ritika");
        Teacher t1 = new Teacher("Mr. Verma");
        TeachingAssistant ta = new TeachingAssistant("Disha");

        // the bell rings
        s1.work();
        t1.work();
        ta.work();
        System.out.println();

        // interface references
        Learning l = ta;          // seen as a learner
        l.attendClass();
        Teaching t = ta;          // seen as a teacher
        t.takeClass();
        System.out.println();

        // default and static methods
        s1.showFee();
        ta.showFee();
        System.out.println("Late fine for 4 days: " + FeePayer.lateFine(4));

        // FeePayer.FINE_PER_DAY = 100;   // ERROR: final
        // new FeePayer();                // ERROR: interface object not allowed
    }
}
