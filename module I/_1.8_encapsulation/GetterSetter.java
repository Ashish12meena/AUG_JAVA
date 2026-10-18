class StudentRecord {
    private String name;
    private int marks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks: " + marks);
        } else {
            this.marks = marks;
        }
    }
}

public class GetterSetter {
    public static void main(String[] args) {
        StudentRecord s1 = new StudentRecord();
        s1.setName("Disha");

        // s1.marks = -50;   // ERROR: marks has private access

        s1.setMarks(-50);    // Invalid marks: -50
        s1.setMarks(85);     // accepted

        System.out.println(s1.getName() + " got " + s1.getMarks());
    }
}
