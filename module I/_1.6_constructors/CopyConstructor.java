class Marksheet {
    String name;
    int marks;

    Marksheet(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    Marksheet(Marksheet other) {      // copy constructor
        this.name = other.name;
        this.marks = other.marks;
    }

    void display() {
        System.out.println(name + " " + marks);
    }
}

public class CopyConstructor {
    public static void main(String[] args) {
        Marksheet m1 = new Marksheet("Ritika", 85);
        Marksheet m2 = new Marksheet(m1);

        m2.marks = 90;   // change only the copy

        m1.display();   // Ritika 85
        m2.display();   // Ritika 90
    }
}
