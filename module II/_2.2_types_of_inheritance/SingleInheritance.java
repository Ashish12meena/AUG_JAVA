class Staff {
    String name;

    void markAttendance() {
        System.out.println(name + " marked attendance");
    }
}

class Librarian extends Staff {
    void issueBook(String title) {
        System.out.println(name + " issued " + title);
    }
}

public class SingleInheritance {
    public static void main(String[] args) {
        Librarian l1 = new Librarian();
        l1.name = "Mrs. Sharma";
        l1.markAttendance();
        l1.issueBook("Java Basics");
    }
}
