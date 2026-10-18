// 1.9 Composition: College creates its own classrooms.
// Classrooms cannot exist without the college.
public class College {
    // 1.8 Encapsulation: private fields
    private String name;
    // 1.5.7 Array of objects
    private Classroom[] classrooms = new Classroom[2];

    // 1.6 Constructor
    public College(String name) {
        this.name = name;
        // 1.9 Composition: classrooms are created INSIDE the college
        classrooms[0] = new Classroom("CSE-A");
        classrooms[1] = new Classroom("CSE-B");
    }

    // 1.8 Getter
    public String getName() {
        return name;
    }

    public Classroom getClassroom(String section) {
        for (int i = 0; i < classrooms.length; i++) {
            // 1.4.9 Strings are compared with equals(), not ==
            if (classrooms[i].getSection().equals(section)) {
                return classrooms[i];
            }
        }
        return null;   // no such section
    }

    // College asks each Classroom to search (objects working together)
    public Student findStudent(int rollNo) {
        for (int i = 0; i < classrooms.length; i++) {
            Student s = classrooms[i].findStudent(rollNo);
            if (s != null) {   // 1.4.5 Relational operator !=
                return s;
            }
        }
        return null;
    }

    public void showAll() {
        System.out.println("===== " + name + " =====");
        for (int i = 0; i < classrooms.length; i++) {
            classrooms[i].showResult();
        }
    }
}
