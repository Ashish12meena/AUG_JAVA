class Course {
    String name;
    int seats;

    Course() {
        name = "Not set";
        seats = 0;
    }

    Course(String name) {
        this.name = name;
        seats = 60;
    }

    Course(String name, int seats) {
        this.name = name;
        this.seats = seats;
    }

    void display() {
        System.out.println(name + " : " + seats + " seats");
    }
}

public class ConstructorOverloading {
    public static void main(String[] args) {
        Course c1 = new Course();
        Course c2 = new Course("BCA");
        Course c3 = new Course("B.Tech CSE", 120);

        c1.display();
        c2.display();
        c3.display();
    }
}
