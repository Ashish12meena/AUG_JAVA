

class OfficeStaff {
    String name;
    double salary;

    void showSalary() {
        System.out.println(name + " salary: " + salary);
    }
}

class Accountant extends OfficeStaff {
    void collectFee(int rollNo) {
        System.out.println(name + " collected fee of roll no " + rollNo);
    }
}

class LabAssistant extends OfficeStaff {
    void setupLab(String lab) {
        System.out.println(name + " set up " + lab);
    }
}

public class HierarchicalInheritance {
    public static void main(String[] args) {
        Accountant a1 = new Accountant();
        a1.name = "Mr. Gupta";
        a1.salary = 30000;
        a1.showSalary();
        a1.collectFee(101);

        LabAssistant l1 = new LabAssistant();
        l1.name = "Mr. Khan";
        l1.salary = 25000;
        l1.showSalary();
        l1.setupLab("Java Lab");
    }
}
