class Individual {
    String name;

    void showName() {
        System.out.println("Name: " + name);
    }
}

class Undergrad extends Individual {
    int rollNo;

    void showRollNo() {
        System.out.println("Roll No: " + rollNo);
    }
}

class Postgrad extends Undergrad {
    String thesis;

    void showThesis() {
        System.out.println("Thesis: " + thesis);
    }
}

public class MultilevelInheritance {
    public static void main(String[] args) {
        Postgrad p1 = new Postgrad();
        p1.name = "Disha";           // from Individual (grandparent)
        p1.rollNo = 201;             // from Undergrad (parent)
        p1.thesis = "Cloud Computing";

        p1.showName();
        p1.showRollNo();
        p1.showThesis();
    }
}
