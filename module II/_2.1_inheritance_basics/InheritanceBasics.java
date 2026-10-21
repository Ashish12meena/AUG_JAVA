class Person {
    String name;
    int age;

    void showBasic() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

class Fresher extends Person {
    int rollNo;
    String branch;

    void showStudent() {
        System.out.println("Roll No: " + rollNo + ", Branch: " + branch);
    }
}

public class InheritanceBasics {
    public static void main(String[] args) {
        Fresher f1 = new Fresher();
        f1.name = "Ritika";      // from Person
        f1.age = 18;             // from Person
        f1.rollNo = 101;         // its own
        f1.branch = "CSE";       // its own

        f1.showBasic();          // parent method
        f1.showStudent();        // child method

        Person p1 = new Person();
        p1.name = "Mr. Verma";
        p1.age = 40;
        p1.showBasic();
        // p1.showStudent();     // ERROR: Person does not have showStudent()
    }
}
