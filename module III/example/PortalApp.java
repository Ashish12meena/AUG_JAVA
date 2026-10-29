import java.util.List;
import java.util.Scanner;

public class PortalApp {
    // 2.7 Returns the parent type Payment.
    // The real object (UPI, Card, Cash or Net Banking) is decided only when the user types.
    static Payment chooseMode(Scanner sc) {
        System.out.print("Pay by 1. UPI  2. Card  3. Cash  4. Net Banking: ");
        int mode = sc.nextInt();
        if (mode == 1) {
            System.out.print("UPI id: ");
            return new UpiPayment(sc.next());
        } else if (mode == 2) {
            System.out.print("Card number: ");
            return new CardPayment(sc.next());
        } else if (mode == 4) {
            System.out.print("Bank name: ");
            return new NetBankingPayment(sc.next());
        } else {
            return new CashPayment();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Section section = new Section("CSE-A");
        Workshop workshop = new Workshop("Java Workshop");
        FeeCounter counter = new FeeCounter();

        // 3.12 Who wants to know when a fee is paid? Each one registers a listener.
        // WHY? FeeCounter only says "a fee was paid". Each office decides what to do.
        counter.addListener(new AccountsOffice());       // full class: keeps its own total

        // 3.11 Anonymous class: a listener with no class name, made and used once
        // WHY? We need it in one place only, so a separate named class is not worth it.
        counter.addListener(new FeePaidListener() {
            @Override
            public void feePaid(FeeEvent e) {
                System.out.println("SMS      : Dear parent, Rs " + e.getAmount() + " received for "
                        + e.getPayer() + ". Balance: Rs " + e.getPayer().balance());
            }
        });

        // 3.12 Lambda: the shortest listener (FeePaidListener has only one method)
        counter.addListener(e -> {
            if (e.getPayer().isFeeCleared()) {
                System.out.println("Exam cell: hall ticket released for " + e.getPayer());
            }
        });

        // sample data (ids 101 to 104 given automatically)
        Student hita = new Student("Hita");
        int[] hitaMarks = {85, 92, 78, 88, 95};
        hita.setMark(hitaMarks);
        hita.addPayment(120000);                     // full fee already paid

        ResearchStudent sakshi = new ResearchStudent("Sakshi", "Cloud_Computing", "Verma");
        int[] sakshiMarks = {45, 52, 60, 42, 45};   // 48.8%: fails the research rule
        sakshi.setMark(sakshiMarks);
        sakshi.addPayment(50000);                    // part fee paid

        Student somya = new Student("Somya");
        int[] somyaMarks = {91, 95, 89, 94, 96};
        somya.setMark(somyaMarks);                   // no fee paid yet

        Teacher verma = new Teacher("Verma", "Java", 60000);

        section.add(hita);
        section.add(sakshi);
        section.add(somya);
        section.add(verma);
        section.add(hita);                           // 3.6 containsKey() stops a duplicate

        workshop.register(somya);
        workshop.register(verma);

        counter.join(sakshi);                        // 3.5 two students already wait in the fee line
        counter.join(somya);

        // section.getMembers().add(verma);          // 3.9 ERROR at run time: UnsupportedOperationException
        // counter.join(verma);                      // ERROR: Teacher is not a FeePayer

        int choice;
        do {
            System.out.println();
            System.out.println("===== VGU College Portal =====");
            System.out.println(" 1. Add student");
            System.out.println(" 2. Add research student");
            System.out.println(" 3. Add teacher");
            System.out.println(" 4. Enter marks");
            System.out.println(" 5. Show all members");
            System.out.println(" 6. Find member by name");
            System.out.println(" 7. Sorted lists");
            System.out.println(" 8. Topper and grade report");
            System.out.println(" 9. Join fee line");
            System.out.println("10. Serve next in fee line");
            System.out.println("11. Fee report");
            System.out.println("12. Register for Java workshop");
            System.out.println("13. Year end: promote students");
            System.out.println(" 0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("Name: ");
                    Student s = new Student(sc.next());
                    section.add(s);
                    System.out.println(s + " added");
                    break;
                }
                case 2: {
                    System.out.print("Name: ");
                    String name = sc.next();
                    System.out.print("Research topic (one word): ");
                    String topic = sc.next();
                    System.out.print("Guide: ");
                    ResearchStudent s = new ResearchStudent(name, topic, sc.next());
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
                    Teacher t = new Teacher(name, subject, sc.nextDouble());
                    section.add(t);
                    System.out.println(t + " added");
                    break;
                }
                case 4: {
                    System.out.print("Id: ");
                    CollegeMember m = section.find(sc.nextInt());   // 3.6 map lookup
                    if (m == null) {
                        System.out.println("Member not found");
                    } else if (m instanceof Student s) {            // 2.7 Downcasting
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
                    section.showList("All members of " + section.getName(), section.getMembers());
                    break;
                case 6: {
                    System.out.print("Name: ");
                    CollegeMember m = section.find(sc.next());
                    if (m == null) {
                        System.out.println("Member not found");
                    } else {
                        m.showDetails();
                        System.out.println("Class: " + m.getClass().getSimpleName());
                    }
                    break;
                }
                case 7: {
                    System.out.print("Sort by 1. Name  2. Marks (merit list)  3. Fee due: ");
                    int by = sc.nextInt();
                    if (by == 1) {
                        section.showList("Sorted by name", section.sortedByName());
                    } else if (by == 2) {
                        section.showList("Merit list", section.meritList());     // a List<Student>
                    } else {
                        section.showList("Fee due (highest first)", section.feeDueList());
                    }
                    break;
                }
                case 8:
                    section.showTopper();
                    section.gradeReport();
                    break;
                case 9: {
                    System.out.print("Id: ");
                    CollegeMember m = section.find(sc.nextInt());
                    if (m == null) {
                        System.out.println("Member not found");
                    } else if (!(m instanceof FeePayer f)) {
                        System.out.println(m.getName() + " (" + m.role() + ") does not pay a fee");
                    } else if (f.isFeeCleared()) {
                        System.out.println(m.getName() + " has no fee due");
                    } else {
                        counter.join(f);
                    }
                    counter.showLine();
                    break;
                }
                case 10: {
                    FeePayer f = counter.serveNext();                // 3.5 first in line
                    if (f == null) {
                        System.out.println("Nobody is in the fee line");
                        break;
                    }
                    System.out.println("Now serving: " + f + "  Fee due: " + f.balance());
                    System.out.print("Amount to pay: ");
                    double amount = sc.nextDouble();
                    if (amount <= 0 || amount > f.balance()) {
                        System.out.println("Amount must be between 1 and " + f.balance()
                                + ". Please join the line again.");
                    } else {
                        System.out.print("Days late (0 if on time): ");
                        int days = sc.nextInt();
                        Payment mode = chooseMode(sc);
                        counter.collect(f, mode, amount, days);     // listeners are called inside
                    }
                    counter.showLine();
                    break;
                }
                case 11:
                    section.feeReport();
                    break;
                case 12: {
                    System.out.print("Id: ");
                    CollegeMember m = section.find(sc.nextInt());
                    if (m == null) {
                        System.out.println("Member not found");
                    } else {
                        workshop.register(m);                       // 3.4 Set stops a second entry
                    }
                    workshop.show();
                    break;
                }
                case 13: {
                    List<Student> promoted = section.promote();     // 3.7 Iterator.remove() inside
                    section.showList("Promoted to 2nd year (passed and fee cleared)", promoted);
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
