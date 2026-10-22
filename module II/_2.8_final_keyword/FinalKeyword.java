class Person {
    static final String COLLEGE = "VGU College";   // constant
    final int id;                                   // blank final
    String name;

    Person(int id, String name) {
        this.id = id;                               // set once, here
        this.name = name;
    }

    final void showId() {                           // final method
        System.out.println(COLLEGE + " | ID " + id + " | " + name);
    }
}

class Student extends Person {
    Student(int id, String name) {
        super(id, name);
    }

    // void showId() { }    // ERROR: final method cannot be overridden
}

final class PgStudent extends Student {           // final class
    PgStudent(int id, String name) {
        super(id, name);
    }
}

// class PhdStudent extends PgStudent { }   // ERROR: cannot inherit from final PgStudent
// class MyString extends String { }          // ERROR: String is final

public class FinalKeyword {
    public static void main(String[] args) {
        Student s1 = new Student(101, "Ritika");
        PgStudent s2 = new PgStudent(102, "Disha");

        s1.name = "Ritika Jain";     // allowed, not final
        // s1.id = 999;              // ERROR: cannot assign a value to final variable

        s1.showId();                 // final method: can be used
        s2.showId();

        final int MAX_MARKS = 100;
        // MAX_MARKS = 50;           // ERROR: final local variable
        System.out.println("Max marks: " + MAX_MARKS);
    }
}
