class Student {
    String name;
    int[] marks = new int[5];

    Student(String name) {
        this.name = name;
    }

    void setMark(int subject, int mark) {
        marks[subject] = mark;
    }

    void setMark(int[] allMarks) {                    // different number and type
        for (int i = 0; i < marks.length; i++) {
            marks[i] = allMarks[i];
        }
    }

    // int setMark(int subject, int mark) { }        // ERROR: only return type is different

    int total() {
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
        return sum;
    }

    void show() {
        System.out.println(name + " Total: " + total());
    }

    void show(String subject, int mark) {             // different number
        System.out.println(name + " | " + subject + ": " + mark);
    }

    void show(int mark, String subject) {             // different order
        System.out.println(name + " | " + mark + " in " + subject);
    }

    // type promotion
    void addBonus(long bonus) {
        System.out.println("long version: " + bonus);
    }

    void addBonus(double bonus) {
        System.out.println("double version: " + bonus);
    }

    // ambiguity
    void payFee(int year, long fee) {
        System.out.println("int, long: year " + year + ", fee " + fee);
    }

    void payFee(long year, int fee) {
        System.out.println("long, int: year " + year + ", fee " + fee);
    }
}

public class MethodOverloading {
    public static void main(String[] args) {
        Student s1 = new Student("Ritika");
        int[] marks = {85, 92, 78, 88, 95};
        s1.setMark(0, 85);             // setMark(int, int): one subject
        s1.setMark(marks);             // setMark(int[]): all five

        s1.show();
        s1.show("Java", 92);
        s1.show(92, "Java");

        s1.addBonus(5);                // no addBonus(int), so int -> long
        s1.addBonus(2.5f);             // float -> double

        s1.payFee(1, 120000L);         // int, long
        // s1.payFee(1, 120000);       // ERROR: ambiguous, both methods match equally

        main(101);                     // overloaded main runs only when we call it
    }

    public static void main(int rollNo) {
        System.out.println("main(int): " + rollNo);
    }
}
