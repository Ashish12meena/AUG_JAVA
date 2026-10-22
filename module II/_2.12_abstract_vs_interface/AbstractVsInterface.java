abstract class Person {               // shared fields + code: is-a
    String name;

    Person(String name) {
        this.name = name;
    }

    void enter() {
        System.out.println(name + " entered VGU College");
    }

    abstract void work();
}

interface FeePayer {                   // an ability: only some people pay a fee
    double fee();
}

class Student extends Person implements FeePayer {
    Student(String name) {
        super(name);
    }

    @Override
    void work() {
        System.out.println(name + " attends the Java class");
    }

    @Override
    public double fee() {
        return 120000;
    }
}

class Teacher extends Person {       // does not pay a fee
    Teacher(String name) {
        super(name);
    }

    @Override
    void work() {
        System.out.println(name + " teaches Java");
    }
}

public class AbstractVsInterface {
    public static void main(String[] args) {
        Person[] people = {new Student("Ritika"), new Teacher("Mr. Verma")};

        for (int i = 0; i < people.length; i++) {
            people[i].enter();
            people[i].work();
            if (people[i] instanceof FeePayer f) {
                System.out.println("Fee: " + f.fee());
            }
            System.out.println();
        }
    }
}
