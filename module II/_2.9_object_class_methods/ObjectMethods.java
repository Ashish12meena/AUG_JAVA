class Person {
    String name;

    Person(String name) {
        this.name = name;
    }
}

class Student extends Person {
    int rollNo;

    Student(int rollNo, String name) {
        super(name);
        this.rollNo = rollNo;
    }

    @Override
    public String toString() {
        return rollNo + " " + name + " [Student]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;                       // same object
        }
        if (!(obj instanceof Student)) {
            return false;                      // null or another class
        }
        Student other = (Student) obj;       // downcast
        return rollNo == other.rollNo;         // same roll no = same student
    }

    @Override
    public int hashCode() {
        return rollNo;                         // equal students -> same hash code
    }
}

class PgStudent extends Student {
    PgStudent(int rollNo, String name) {
        super(rollNo, name);
    }
}

public class ObjectMethods {
    public static void main(String[] args) {
        Student s1 = new Student(101, "Ritika");
        Student s2 = new Student(101, "Ritika");
        Student s3 = s1;

        // toString()
        System.out.println(s1);               // calls s1.toString()
        System.out.println("Topper -> " + s1);
        // without toString() it prints something like Student@1b6d3586

        // == and equals()
        System.out.println(s1 == s2);         // false: two different objects
        System.out.println(s1 == s3);         // true: same object
        System.out.println(s1.equals(s2));    // true: same roll no

        // hashCode()
        System.out.println(s1.hashCode() + " " + s2.hashCode());   // same numbers
        // remove hashCode() and run again: equals is true but hash codes differ

        // getClass()
        Student s4 = new PgStudent(102, "Disha");      // parent reference
        System.out.println(s1.getClass().getName());     // Student
        System.out.println(s4.getClass().getName());     // PgStudent: real object
        System.out.println(s1.getClass() == s4.getClass());   // false
    }
}
