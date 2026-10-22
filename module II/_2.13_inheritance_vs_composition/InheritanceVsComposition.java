class Person {
    String name;

    Person(String name) {
        this.name = name;
    }
}

class MarkList {
    int[] marks;

    MarkList(int[] marks) {
        this.marks = marks;
    }

    void setMark(int subject, int mark) {
        marks[subject] = mark;
    }

    int total() {
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
        return sum;
    }

    void show() {
        System.out.println("Total: " + total() + ", Percent: " + total() / 5.0);
    }
}

// is-a:  a student IS A person      -> inheritance
// has-a: a student HAS A mark list  -> composition
class Student extends Person {
    MarkList markList;                       // composition

    Student(String name, MarkList markList) {
        super(name);
        this.markList = markList;
    }

    void nextSemester(int[] newMarks) {
        markList = new MarkList(newMarks);   // part replaced at run time
    }

    void show() {
        System.out.print(name + " -> ");
        markList.show();
    }
}

// WRONG: class Student extends MarkList { }   // a student is NOT a mark list

public class InheritanceVsComposition {
    public static void main(String[] args) {
        int[] sem1 = {85, 92, 78, 88, 95};
        Student s1 = new Student("Ritika", new MarkList(sem1));
        s1.show();

        s1.markList.setMark(2, 82);            // use the part's method (re-check)
        s1.show();

        int[] sem2 = {90, 88, 84, 91, 87};
        s1.nextSemester(sem2);
        s1.show();
    }
}
