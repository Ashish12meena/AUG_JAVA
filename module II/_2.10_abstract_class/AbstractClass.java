abstract class Person {
    String name;

    Person(String name) {                // abstract class can have a constructor
        this.name = name;
    }

   abstract void work();             // no body: each person decides

    void enter() {                         // normal method: same for all
        System.out.println(name + " entered VGU College");
    }
}

class Student extends Person {
    Student(String name) {
        super(name);
    }

    @Override
    void work() {
       System.out.println("" + name + " studies Java");
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

// class Visitor extends Person { }    // ERROR: must write work(), or be declared abstract

public class AbstractClass {
    public static void main(String[] args) {
        // Person p = new Person("X");   // ERROR: Person is abstract

        Person p1 = new Student("Ritika");   // upcasting
        Person p2 = new Teacher("Mr. Verma");

        // the bell rings: who decides what work() does? The real object.
        p1.enter();
        p1.work();
        p2.enter();
        p2.work();


        // Person p3 = new Person("Sakshi");
    }
}
