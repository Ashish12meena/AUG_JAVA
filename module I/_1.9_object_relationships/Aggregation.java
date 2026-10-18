class Professor {
    String name;

    Professor(String name) {
        this.name = name;
    }
}

class Department {
    String name;
    Professor[] professors = new Professor[5];
    int count = 0;

    Department(String name) {
        this.name = name;
    }

    void addProfessor(Professor p) {    // professor made outside, passed in
        professors[count] = p;
        count++;
    }

    void display() {
        System.out.println("Department: " + name);
        for (int i = 0; i < count; i++) {
            System.out.println("  " + professors[i].name);
        }
    }
}

public class Aggregation {
    public static void main(String[] args) {
        Professor p1 = new Professor("Dr. Sharma");
        Professor p2 = new Professor("Dr. Gupta");

        Department cse = new Department("CSE");
        cse.addProfessor(p1);
        cse.addProfessor(p2);
        cse.display();

        cse = null;                    // department closed
        System.out.println(p1.name);   // professor still exists
    }
}
