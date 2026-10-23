// 2.2 Hierarchical inheritance: Student and Teacher both extend CollegeMember
// Teacher IS-A CollegeMember, but is NOT Gradable and NOT a FeePayer
// WHY? A teacher has no marks and pays no fee. Because those are interfaces,
// Teacher simply does not implement them. No fake fee() returning 0.
public class Teacher extends CollegeMember {
    private String subject;
    private double salary;

    public Teacher(String name, String subject, double salary) {
        super(name);
        this.subject = subject;
        this.salary = salary;
    }

    @Override
    public String role() {
        return "Teacher";
    }

    @Override
    public void showDetails() {
        System.out.println(this + "  Subject: " + subject + "  Salary: " + salary);
    }
}
