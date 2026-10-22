class Person {
    String name;
    String type = "Person";

    Person(String name) {
        this.name = name;
    }

    void work() {
        System.out.println(name + " is in the college");
    }

    String getType() {
        return type;
    }
}

class Student extends Person {
    String type = "Student";
    int total;

    Student(String name, int total) {
        super(name);
        this.total = total;
    }

    @Override
    void work() {
        System.out.println(name + " attends the Java class");
    }

    @Override
    String getType() {
        return type;
    }

    void showMarks() {                         // only Student has this
        System.out.println(name + " total marks: " + total);
    }
}

class Teacher extends Person {
    Teacher(String name) {
        super(name);
    }

    @Override
    void work() {
        System.out.println(name + " teaches Java");
    }
}

public class Polymorphism {
    public static void main(String[] args) {
        // upcasting]
        Person p = new Student("Sakshi", 438);
        p.work();                              // Student's work() runs
        // p.showMarks();                      // ERROR: Person has no showMarks()

        p = new Teacher("Mr. Verma");
        p.work();                              // Teacher's work() runs
        System.out.println();

        // // the bell rings: one array, one loop, different work
        Person[] people = {new Student("Ritika", 438), new Teacher("Mr. Verma"), new Person("Guest")};
        for (int i = 0; i < people.length; i++) {
            people[i].work();
        }
        System.out.println();

        // // variables are not polymorphic
        Person p2 = new Student("Disha", 244);
        System.out.println(p2.type);           // Person  -> field: reference type
        System.out.println(p2.getType());      // Student -> method: object type
        System.out.println();

        // // downcasting
        Student s1 = (Student) people[0];
        s1.showMarks();
        // Student s2 = (Student) people[1]; // runtime ERROR: ClassCastException

        if (people[1] instanceof Student) {   // safe way: check first
            Student s2 = (Student) people[1];
            s2.showMarks();
        } else {
            System.out.println(people[1].name + " is not a student");
        }

        // // pattern matching: check and cast in one step
        for (int i = 0; i < people.length; i++) {
            if (people[i] instanceof Student s) {
                s.showMarks();
            }
        }

    }
}
