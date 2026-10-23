import java.util.Scanner;

public class PortalApp {
    // 2.7 Returns the parent type Payment.
    // The real object (UPI, Card, Cash or Net Banking) is decided only when the user types.
    // WHY return Payment? The caller does not need to know which mode was chosen.
    // This is the ONLY place that knows all modes: a new mode is added here, not in FeeCounter.
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

        // CollegeMember m = new CollegeMember("X");   // 2.10 ERROR: abstract class
        // Payment p = new Payment("Cheque");          // 2.10 ERROR: abstract class

        // sample data (ids 101 to 104 given automatically)
        Student hita = new Student("Hita");
        int[] hitaMarks = {85, 92, 78, 88, 95};
        hita.setMark(hitaMarks);                     // 2.4 overloaded setMark(int[])
        hita.addPayment(120000);                     // full fee already paid

        ResearchStudent sakshi = new ResearchStudent("Sakshi", "Cloud_Computing", "Verma");
        int[] sakshiMarks = {45, 52, 60, 42, 45};   // 48.8%: passes Student rule, fails research rule
        sakshi.setMark(sakshiMarks);                 // inherited from Student
        sakshi.addPayment(50000);                    // part fee paid

        Student somya = new Student("Somya");
        int[] somyaMarks = {91, 95, 89, 94, 96};
        somya.setMark(somyaMarks);                   // no fee paid yet

        Teacher verma = new Teacher("Verma", "Java", 60000);

        // 2.7 Upcasting: all of them go into a CollegeMember array
        section.add(hita);
        section.add(sakshi);
        section.add(somya);
        section.add(verma);
        section.add(hita);                           // 2.9 equals() stops a duplicate

        // FeeCounter.collect(verma, new CashPayment(), 1000, 0);   // ERROR: Teacher is not a FeePayer
        // WHY is this good? The compiler itself stops a fee being taken from a teacher.
        // System.out.println(hita.lateFine(2));                    // ERROR: static interface method needs FeePayer.lateFine(2)

        int choice;
        do {
            System.out.println();
            System.out.println("===== VGU College Portal =====");
            System.out.println("1. Add student");
            System.out.println("2. Add research student");
            System.out.println("3. Add teacher");
            System.out.println("4. Enter marks");
            System.out.println("5. Show all members");
            System.out.println("6. Find member by name");
            System.out.println("7. Show topper");
            System.out.println("8. Pay fee");
            System.out.println("9. Fee report");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("Name: ");
                    Student s = new Student(sc.next());
                    section.add(s);
                    System.out.println(s + " added");   // 2.9 toString()
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
                    CollegeMember m = section.find(sc.nextInt());   // 2.4 find(int)
                    if (m == null) {
                        System.out.println("Member not found");
                    } else if (m instanceof Student s) {            // 2.7 Downcasting
                        // WHY downcast? setMark() is only in Student, not in CollegeMember.
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
                    System.out.print("Id: ");
                    CollegeMember m = section.find(sc.nextInt());
                    if (m == null) {
                        System.out.println("Member not found");
                    } else if (!(m instanceof FeePayer f)) {        // 2.11 only fee payers can pay
                        // WHY check? find() gives a CollegeMember; it may be a teacher.
                        System.out.println(m.getName() + " (" + m.role() + ") does not pay a fee");
                    } else if (f.isFeeCleared()) {                  // 2.11 default method
                        System.out.println(m.getName() + " has no fee due");
                    } else {
                        // 2.11 default method: called on an object (f), uses that student's fee
                        System.out.println("Fee due: " + f.balance());
                        System.out.print("Amount to pay: ");
                        double amount = sc.nextDouble();
                        if (amount <= 0 || amount > f.balance()) {
                            System.out.println("Amount must be between 1 and " + f.balance());
                        } else {
                            System.out.print("Days late (0 if on time): ");
                            int days = sc.nextInt();
                            Payment mode = chooseMode(sc);          // 2.7 parent reference
                            // WHY pass f and mode? FeeCounter works with ANY fee payer and ANY mode.
                            FeeCounter.collect(f, mode, amount, days);
                        }
                    }
                    break;
                }
                case 9:
                    section.feeReport();
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
