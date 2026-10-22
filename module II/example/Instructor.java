// 2.2 Hierarchical inheritance: UgStudent and Instructor both extend CollegeMember
// Instructor is NOT Gradable and NOT a FeePayer
public class Instructor extends CollegeMember {
    private String subject;
    private double salary;

    public Instructor(String name, String subject, double salary) {
        super(name);
        this.subject = subject;
        this.salary = salary;
    }

    @Override
    public String role() {
        return "Instructor";
    }

    @Override
    public void showDetails() {
        System.out.println(this + "  Subject: " + subject + "  Salary: " + salary);
    }
}
