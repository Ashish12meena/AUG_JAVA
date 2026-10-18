class Subject {
    String code;
    String name;
    int credits;

    Subject() {
        this("NA", "Not set");          // calls Subject(String, String)
    }

    Subject(String code, String name) {
        this(code, name, 4);            // calls Subject(String, String, int)
    }

    Subject(String code, String name, int credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    void display() {
        System.out.println(code + " " + name + " " + credits + " credits");
    }
}

public class ConstructorChaining {
    public static void main(String[] args) {
        Subject s1 = new Subject();
        Subject s2 = new Subject("CS101", "Java");
        Subject s3 = new Subject("CS102", "DBMS", 3);

        s1.display();
        s2.display();
        s3.display();
    }
}
