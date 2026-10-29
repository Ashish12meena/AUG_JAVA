import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Student {
    int id;
    String name;
    int total;                  // marks out of 500

    Student(int id, String name, int total) {
        this.id = id;
        this.name = name;
        this.total = total;
    }

    @Override
    public String toString() {
        return id + "  " + name + "  " + total;
    }
}

public class QueueAndDeque {
    public static void main(String[] args) {
        // 1. Queue (FIFO): line at the fee counter, first come first served
        Deque<String> feeLine = new LinkedList<>();
        feeLine.offer("Hita");
        feeLine.offer("Sakshi");
        feeLine.offer("Somya");
        System.out.println("Fee line : " + feeLine);
        System.out.println("Next     : " + feeLine.peek());     // only look, do not remove
        System.out.println("Served   : " + feeLine.poll());     // remove from the front
        System.out.println("Served   : " + feeLine.poll());
        System.out.println("Line now : " + feeLine);
        feeLine.poll();
        System.out.println("Empty line, poll(): " + feeLine.poll());   // null, no error
        // feeLine.remove();       // ERROR at run time: NoSuchElementException (line is empty)
        System.out.println();

        // 2. PriorityQueue, natural order: smallest roll number is called first
        PriorityQueue<Integer> rolls = new PriorityQueue<>();
        rolls.offer(103);
        rolls.offer(101);
        rolls.offer(102);
        System.out.print("Roll call: ");
        while (!rolls.isEmpty()) {
            System.out.print(rolls.poll() + " ");
        }
        System.out.println();

        // 3. PriorityQueue with a Comparator: scholarship interview, highest total first
        // (a, b) -> b.total - a.total  means "bigger total comes first" (more in 3.8)
        PriorityQueue<Student> interview = new PriorityQueue<>((a, b) -> b.total - a.total);
        interview.offer(new Student(101, "Hita", 438));
        interview.offer(new Student(102, "Sakshi", 244));
        interview.offer(new Student(103, "Somya", 465));
        interview.offer(new Student(104, "Ritika", 412));
        while (!interview.isEmpty()) {
            System.out.println("Interview: " + interview.poll());
        }
        System.out.println();

        // 4. ArrayDeque as a STACK (LIFO): Mr. Verma's pile of answer sheets
        Deque<String> sheets = new ArrayDeque<>();
        sheets.push("Hita");
        sheets.push("Sakshi");
        sheets.push("Somya");                       // last one is on top
        System.out.println("Checking : " + sheets.pop());   // Somya first
        System.out.println("Top now  : " + sheets.peek());

        // 5. the same class as a QUEUE: add at the back, take from the front
        Deque<String> lab = new ArrayDeque<>();
        lab.offerLast("Ritika");
        lab.offerLast("Disha");
        lab.offerFirst("Mr. Verma");                // teacher enters the lab first
        System.out.println("Lab entry: " + lab.pollFirst() + ", " + lab.pollFirst() + ", " + lab.pollFirst());
    }
}
