class Faculty {
    private double salary = 50000;
    String name = "Mr. Verma";          // default
    protected String subject = "Java";
    public String college = "VGU College";

    void showSalary() {

        System.out.println("Salary inside class = " + salary);   // private is OK inside its own class
    }
}

public class AccessModifiers {
    public static void main(String[] args) {
        Faculty t1 = new Faculty();


        // System.out.println(t1.salary);   // ERROR: salary has private access
        System.out.println("name = " + t1.name);
        System.out.println("subject = " + t1.subject);
        System.out.println("college = " + t1.college);

        t1.showSalary();
    }
}
