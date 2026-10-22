import java.util.Scanner;

public class PortalApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Section section = new Section("CSE-A");

        // CollegeMember m = new CollegeMember("X");   // 2.10 ERROR: abstract class

        // sample data (ids 101, 102, 103 given automatically)
        UgStudent ritika = new UgStudent("Ritika");
        int[] ritikaMarks = {85, 92, 78, 88, 95};
        ritika.setMark(ritikaMarks);                 // 2.4 overloaded setMark(int[])

        PgStudent disha = new PgStudent("Disha", "Cloud_Computing");
        int[] dishaMarks = {45, 52, 60, 42, 45};    // 48.8%: passes UG rule, fails PG rule
        disha.setMark(dishaMarks);                   // inherited from UgStudent

        Instructor verma = new Instructor("Verma", "Java", 60000);

        // 2.7 Upcasting: all three go into a CollegeMember array
        section.add(ritika);
        section.add(disha);
        section.add(verma);
        section.add(ritika);                         // 2.9 equals() stops a duplicate

        int choice;
        do {
            System.out.println();
            System.out.println("===== VGU College Portal =====");
            System.out.println("1. Add UG student");
            System.out.println("2. Add PG student");
            System.out.println("3. Add instructor");
            System.out.println("4. Enter marks");
            System.out.println("5. Show all members");
            System.out.println("6. Find member by name");
            System.out.println("7. Show topper");
            System.out.println("8. Fee report");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("Name: ");
                    UgStudent s = new UgStudent(sc.next());
                    section.add(s);
                    System.out.println(s + " added");   // 2.9 toString()
                    break;
                }
                case 2: {
                    System.out.print("Name: ");
                    String name = sc.next();
                    System.out.print("Thesis (one word): ");
                    PgStudent s = new PgStudent(name, sc.next());
                    section.add(s);
                    System.out.println(s + " added");
                    break;
                }
                case 3: {
                    System.out.print("Name: ");
                    String name = sc.next();
                    System.out.print("Subject: ");
                    String subject = sc.next();
                    System.out.print("Salary: ");
                    Instructor t = new Instructor(name, subject, sc.nextDouble());
                    section.add(t);
                    System.out.println(t + " added");
                    break;
                }
                case 4: {
                    System.out.print("Id: ");
                    CollegeMember m = section.find(sc.nextInt());   // 2.4 find(int)
                    if (m == null) {
                        System.out.println("Member not found");
                    } else if (m instanceof UgStudent s) {          // 2.7 Downcasting
                        for (int i = 0; i < 5; i++) {
                            System.out.print("Subject " + (i + 1) + " mark: ");
                            s.setMark(i, sc.nextInt());
                        }
                        s.showDetails();
                    } else {
                        System.out.println(m.getName() + " (" + m.role() + ") has no marks");
                    }
                    break;
                }
                case 5:
                    section.showAll();
                    break;
                case 6: {
                    System.out.print("Name: ");
                    CollegeMember m = section.find(sc.next());      // 2.4 find(String)
                    if (m == null) {
                        System.out.println("Member not found");
                    } else {
                        m.showDetails();                            // 2.7 dynamic dispatch
                        // 2.9 getClass(): real class of the object
                        System.out.println("Class: " + m.getClass().getSimpleName());
                    }
                    break;
                }
                case 7:
                    section.showTopper();
                    break;
                case 8: {
                    System.out.println("Total fee of " + section.getName() + ": " + section.totalFee());
                    System.out.print("Days late: ");
                    int days = sc.nextInt();
                    // 2.11 static interface method: called with the interface name
                    System.out.println("Late fine: " + FeePayer.lateFine(days));
                    break;
                }
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
