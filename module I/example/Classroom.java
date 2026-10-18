// 1.9 Aggregation: Classroom HAS students, but students are created outside
// and passed in. A student can exist without a classroom.
public class Classroom {
    // 1.8 Encapsulation: private fields
    private String section;
    // 1.5.7 Array of objects: holds up to 60 Student objects
    private Student[] students = new Student[60];
    private int size = 0;   // how many students are added

    // 1.6 Parameterised constructor + 1.5.6 this
    public Classroom(String section) {
        this.section = section;
    }

    // 1.8 Getter
    public String getSection() {
        return section;
    }

    // 1.9 Aggregation: the Student object comes from outside
    public void addStudent(Student s) {
        if (size == students.length) {
            System.out.println("Classroom " + section + " is full");
        } else {
            students[size] = s;
            size++;
        }
    }

    // 1.4.6 Linear search with a for loop; returns an object
    public Student findStudent(int rollNo) {
        for (int i = 0; i < size; i++) {
            // 1.8 private rollNo is read through its getter
            if (students[i].getRollNo() == rollNo) {
                return students[i];
            }
        }
        return null;   // 1.4.2 null: reference that points to no object
    }

    public void showResult() {
        System.out.println("----- " + section + " -----");
        if (size == 0) {
            System.out.println("No students");
            return;
        }
        for (int i = 0; i < size; i++) {
            students[i].display();   // 1.5 calling a method on each object
        }
    }

    // Finding the maximum (topper) in an array of objects
    public void showTopper() {
        if (size == 0) {
            System.out.println("No students in " + section);
            return;
        }
        Student topper = students[0];   // 1.5.3 reference variable, no new object
        for (int i = 1; i < size; i++) {
            if (students[i].total() > topper.total()) {
                topper = students[i];
            }
        }
        System.out.println("Topper of " + section + ": " + topper.getName()
                + " (" + topper.total() + ")");
    }
}
