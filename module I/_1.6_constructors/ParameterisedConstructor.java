class Lecturer {
    String name;
    String subject;

    Lecturer(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    void display() {
        System.out.println(name + " teaches " + subject);
    }
}

public class ParameterisedConstructor {
    public static void main(String[] args) {
        Lecturer t1 = new Lecturer("Mr. Verma", "Java");
        Lecturer t2 = new Lecturer("Ms. Jain", "DBMS");

        t1.display();
        t2.display();

        // Lecturer t3 = new Lecturer();   // ERROR: no default constructor now
    }
}
