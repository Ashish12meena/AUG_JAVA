class Person {
    String name;
    String type = "Person";

    Person(String name) {
        this.name = name;
        System.out.println("1. Person constructor");
    }

    void show() {
        System.out.println("Name: " + name);
    }
}

class Student extends Person {
    int rollNo;
    String type = "Student";            // same field name as the parent

    Student(String name, int rollNo) {
        super(name);                    // parent constructor: must be the first line
        this.rollNo = rollNo;
        System.out.println("2. Student constructor");
    }

    void showType() {
        System.out.println(type);       // Student (child)
        System.out.println(this.type);  // Student (child)
        System.out.println(super.type); // Person  (parent)
    }

    void show() {
        super.show();                   // parent's show() first
        System.out.println("Roll No: " + rollNo);
    }
}

class PgStudent extends Student {
    String thesis;

    PgStudent(String name, int rollNo, String thesis) {
        super(name, rollNo);
        this.thesis = thesis;
        System.out.println("3. PgStudent constructor");
    }

    void show() {
        super.show();                   // Student's show(), which calls Person's show()
        System.out.println("Thesis: " + thesis);
    }
}

// class Teacher extends Person {
//     Teacher() { }    // ERROR: Java adds super(), but Person has no Person() constructor
// }

public class SuperKeyword {
    public static void main(String[] args) {
        Student s1 = new Student("Ritika", 101);
        System.out.println();

        s1.showType();                  // super.field
        System.out.println();

        s1.show();                      // super.method()
        System.out.println();

        // constructor order: Person -> Student -> PgStudent
        PgStudent s2 = new PgStudent("Disha", 102, "Cloud Computing");
        System.out.println();
        s2.show();
    }
}
