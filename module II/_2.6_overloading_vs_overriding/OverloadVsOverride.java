class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    void work() {
        System.out.println(name + " is in the college");
    }
}

class Student extends Person {
    Student(String name) {
        super(name);
    }

    @Override
    void work() {                              // overriding: same parameters
        System.out.println(name + " attends the Java class");
    }

    void work(String subject) {                // overloading: different parameters
        System.out.println(name + " attends the " + subject + " class");
    }

    void work(String subject, int hours) {     // overloading again
        System.out.println(name + " attends the " + subject + " class for " + hours + " hours");
    }
}

public class OverloadVsOverride {
    public static void main(String[] args) {
        Student s1 = new Student("Ritika");
        s1.work();                 // overridden version
        s1.work("DBMS");           // overloaded version
        s1.work("Java Lab", 2);    // overloaded version
    }
}
