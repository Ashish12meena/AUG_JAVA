class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    void work() {
        System.out.println(name + " is in the college");
    }

    protected void show() {
        System.out.println("Name: " + name);
    }

    Person copy() {
        return new Person(name);
    }

    static void rules() {
        System.out.println("College rules");
    }

    private void password() {
        System.out.println("Person password");
    }

    final void college() {
        System.out.println("VGU College");
    }
}

class Student extends Person {
    Student(String name) {
        super(name);
    }

    @Override
    void work() {
        System.out.println(name + " attends the Java class");
    }

    @Override
    public void show() {               // protected -> public: wider access is OK
        super.show();                  // parent's version first
        System.out.println("Role: Student");
    }

    @Override
    Student copy() {                  // covariant return: child type is OK
        return new Student(name);
    }

    static void rules() {              // hides Person.rules(), does NOT override
        System.out.println("Student rules");
    }

    void password() {                  // new method, parent's password() is private
        System.out.println("Student password");
    }

    // void college() { }              // ERROR: final method cannot be overridden
    // private void show() { }         // ERROR: weaker access not allowed
    // @Override
    // void wrok() { }                 // ERROR: @Override catches the spelling mistake
}

class Teacher extends Person {
    Teacher(String name) {
        super(name);
    }

    @Override
    void work() {
        System.out.println(name + " teaches Java");
    }
    // show() not overridden, so parent's version is used
}

public class MethodOverriding {
    public static void main(String[] args) {
        Student s1 = new Student("Ritika");
        Teacher t1 = new Teacher("Mr. Verma");

        // the bell rings: each class does its own work()
        s1.work();
        t1.work();

        s1.show();                     // Student's show()
        t1.show();                     // Person's show()

        Student s2 = s1.copy();       // gives Student, no cast needed
        s2.work();

        Person.rules();
        Student.rules();
        s1.college();
    }
}
