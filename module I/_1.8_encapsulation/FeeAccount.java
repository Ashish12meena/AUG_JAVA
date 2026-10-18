class StudentFee {
    private int rollNo;
    private double feeDue;

    public StudentFee(int rollNo, double totalFee) {
        this.rollNo = rollNo;
        this.feeDue = totalFee;
    }

    public int getRollNo() {      // only getter, so read-only
        return rollNo;
    }

    public double getFeeDue() {
        return feeDue;
    }

    public void payFee(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be more than 0");
        } else if (amount > feeDue) {
            System.out.println("Amount is more than fee due");
        } else {
            feeDue = feeDue - amount;
        }
    }
}

public class FeeAccount {
    public static void main(String[] args) {
        StudentFee f1 = new StudentFee(101, 50000);

        f1.payFee(-500);     // Amount must be more than 0
        f1.payFee(80000);    // Amount is more than fee due
        f1.payFee(20000);

        // f1.feeDue = 0;    // ERROR: feeDue has private access

        System.out.println("Roll No: " + f1.getRollNo());
        System.out.println("Fee due: " + f1.getFeeDue());
    }
}
