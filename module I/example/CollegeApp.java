// 1.4.7 Input: Scanner reads from the keyboard
import java.util.Scanner;

public class CollegeApp {
    // 1.3.2 main method: program starts here
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 1.5.2 Creating an object with new (constructor runs)
        College college = new College("VGU College");

        // sample data, so the menu has something to show
        Student ritika = new Student("Ritika");   // roll no 101 given automatically (1.7 static)
        // 1.4.8 Array initialised with values
        int[] ritikaMarks = {85, 92, 78, 88, 95};
        for (int i = 0; i < 5; i++) {
            ritika.setMark(i, ritikaMarks[i]);    // 1.8 change data only through the setter
        }
        // 1.9 Aggregation: student created outside, then added to the classroom
        college.getClassroom("CSE-A").addStudent(ritika);

        Student disha = new Student("Disha");
        int[] dishaMarks = {45, 38, 60, 52, 49};
        for (int i = 0; i < 5; i++) {
            disha.setMark(i, dishaMarks[i]);
        }
        college.getClassroom("CSE-A").addStudent(disha);

        int choice;
        // 1.4.6 do-while: menu is shown at least once
        do {
            System.out.println();
            System.out.println("1. Add student");
            System.out.println("2. Enter marks");
            System.out.println("3. Show result of a section");
            System.out.println("4. Show topper of a section");
            System.out.println("5. Show all students");
            System.out.println("6. Total students");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            // 1.4.6 switch-case for the menu
            switch (choice) {
                case 1: {   // { } keeps variables like 'room' local to this case
                    System.out.print("Name: ");
                    String name = sc.next();
                    System.out.print("Section (CSE-A / CSE-B): ");
                    String section = sc.next();

                    Classroom room = college.getClassroom(section);
                    if (room == null) {
                        System.out.println("No section " + section);
                    } else {
                        Student s = new Student(name);
                        room.addStudent(s);
                        System.out.println(name + " added with roll no " + s.getRollNo());
                    }
                    break;
                }
                case 2: {
                    System.out.print("Roll no: ");
                    int rollNo = sc.nextInt();
                    Student s = college.findStudent(rollNo);
                    if (s == null) {
                        System.out.println("Student not found");
                    } else {
                        for (int i = 0; i < 5; i++) {
                            System.out.print("Subject " + (i + 1) + " mark: ");
                            s.setMark(i, sc.nextInt());   // 1.8 setter rejects marks outside 0-100
                        }
                        s.display();
                    }
                    break;
                }
                case 3: {
                    System.out.print("Section: ");
                    Classroom room = college.getClassroom(sc.next());
                    if (room == null) {
                        System.out.println("No such section");
                    } else {
                        room.showResult();
                    }
                    break;
                }
                case 4: {
                    System.out.print("Section: ");
                    Classroom room = college.getClassroom(sc.next());
                    if (room == null) {
                        System.out.println("No such section");
                    } else {
                        room.showTopper();
                    }
                    break;
                }
                case 5:
                    college.showAll();
                    break;
                case 6:
                    // 1.7 Static method: called with the class name, no object needed
                    System.out.println("Total students: " + Student.getCount());
                    break;
                case 0:
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 0);

        sc.close();
    }
}
