class ExamCell {
    void work() {
        System.out.println("Prepare exam timetable");
    }
}

class SportsCell {
    void work() {
        System.out.println("Organise sports day");
    }
}

// class Coordinator extends ExamCell, SportsCell { }   // ERROR: only one class can be extended
// If allowed, which work() would Coordinator get? Java avoids this confusion.

public class DiamondProblem {
    public static void main(String[] args) {
        new ExamCell().work();
        new SportsCell().work();
    }
}
