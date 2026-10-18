// 1.5 Class: blueprint for every student object
public class Student {
    // 1.7 Static variable: one copy shared by all students
    private static int count = 0;
    // 1.7 Static variable: gives automatic roll numbers
    private static int nextRollNo = 101;

    // 1.8 Encapsulation: fields are private (data hiding)
    private int rollNo;
    private String name;
    // 1.4.8 Array: 5 marks stored in one variable
    private int[] marks = new int[5];

    // 1.6 Parameterised constructor: runs when "new Student(name)" is used
    public Student(String name) {
        this.rollNo = nextRollNo;   // 1.5.6 this: field of the current object
        nextRollNo++;               // 1.4.5 Unary operator ++
        this.name = name;
        count++;                    // count goes up for every new object
    }

    // 1.8 Getter: read-only, there is no setRollNo()
    public int getRollNo() {
        return rollNo;
    }

    // 1.8 Getter
    public String getName() {
        return name;
    }

    // 1.7 Static method: called with class name, Student.getCount()
    public static int getCount() {
        return count;
    }

    // 1.8 Setter with validation: only 0 to 100 is stored
    public void setMark(int subject, int mark) {
        // 1.4.5 Logical operator ||
        if (mark < 0 || mark > 100) {
            System.out.println("Invalid mark " + mark + ". Mark must be 0 to 100.");
        } else {
            marks[subject] = mark;
        }
    }

    // 1.5.4 Method with return value
    public int total() {
        int sum = 0;                 // local variable
        // 1.4.6 for loop over an array
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];         // 1.4.5 Assignment operator +=
        }
        return sum;
    }

    public double percentage() {
        return total() / 5.0;        // 1.4.4 Type conversion: int / double gives double
    }

    // 1.4.6 else-if ladder
    public char grade() {
        double p = percentage();
        if (p >= 90) {
            return 'A';
        } else if (p >= 75) {
            return 'B';
        } else if (p >= 60) {
            return 'C';
        } else if (p >= 40) {
            return 'D';
        } else {
            return 'F';
        }
    }

    // 1.8 Getter for a boolean starts with "is"
    public boolean isPassed() {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < 40) {
                return false;   // fail in one subject = fail
            }
        }
        return true;
    }

    public void display() {
        // 1.4.5 Ternary operator
        String result = isPassed() ? "PASS" : "FAIL";
        // 1.4.9 String joining with +
        System.out.println(rollNo + "  " + name + "  Total: " + total()
                + "  %: " + percentage() + "  Grade: " + grade() + "  " + result);
    }
} 
