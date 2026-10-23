# Teaching Script — Build the VGU College Portal from Scratch

This is a **word-by-word classroom script**. We start with an **empty folder** and build the program live, one requirement at a time.

Students already know each OOP concept on its own. Today they see **why** each concept is needed.
Every concept enters **only when a problem forces it**.

## How to read this script

| Label | What you do |
| --- | --- |
| **Say:** | Speak this to the class |
| **Ask:** | Ask, then **wait**. Let students answer before you continue |
| **Expected:** | The answer you want. If nobody says it, give hints, then say it yourself |
| **Board:** | Write this on the board |
| **Type → File.java** | Type this file live (full file shown every time it changes) |
| **Run:** | Compile and run: `javac *.java` then `java PortalApp` |
| **Show error:** | Type the wrong line, compile, show the error, then remove it |

## The story (same all through)

| Person | Who |
| --- | --- |
| Hita (101) | Student, all marks good, fee fully paid |
| Sakshi (102) | Research student (comes later), Cloud Computing |
| Somya (103) | Student, the topper, no fee paid yet |
| Mr. Verma (104) | Java teacher |

The question you keep asking all through:

> **Why do we need this? What problem does it solve? What goes wrong if we do it differently?**

---

# SESSION 1 — Build the Domain Model

## Part 1. The College Has Only Students

### Opening

- **Say:** Today we will not learn any new keyword.
- **Say:** You already know inheritance, abstract classes, interfaces, polymorphism. You learned them one by one.
- **Say:** Today we build **one real program** from an empty folder.
- **Say:** And we will use a concept **only when the problem asks for it**. Not before.
- **Say:** Our client is VGU College. They want a small college portal.
- **Say:** Every few minutes the college will send us a **new requirement**. Just like a real job.

### Requirement 1

- **Say:** First requirement: *"The college has students. Each student has an id, a name, marks in 5 subjects, and pays a fee of 1,20,000."*
- **Ask:** What data does one student have?
- **Expected:** id, name, 5 marks, amount paid.
- **Ask:** What do we want to **know** about a student?
- **Expected:** total, percentage, pass or fail, how much fee is left.

**Board:**

```text
Student
  id, name, marks[5], paid
  setMark()  total()  percentage()  isPassed()
  addPayment()  balance()  showDetails()
```

- **Say:** This is Module 1 work. One class. Let us type it fast.

**Type → Student.java**

```java
public class Student {
    private int id;
    private String name;
    private int[] marks = new int[5];
    private double paid;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setMark(int subject, int mark) {
        if (mark < 0 || mark > 100) {
            System.out.println("Invalid mark: " + mark);
        } else {
            marks[subject] = mark;
        }
    }

    public void setMark(int[] allMarks) {
        for (int i = 0; i < marks.length; i++) {
            setMark(i, allMarks[i]);
        }
    }

    public int total() {
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
        return sum;
    }

    public double percentage() {
        return total() / 5.0;
    }

    public boolean isPassed() {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < 40) {
                return false;
            }
        }
        return true;
    }

    public void addPayment(double amount) {
        paid += amount;
    }

    public double balance() {
        return 120000 - paid;
    }

    public void showDetails() {
        String result = isPassed() ? "PASS" : "FAIL";
        System.out.println(id + "  " + name + "  Total: " + total() + "  %: " + percentage()
                + "  " + result + "  Balance: " + balance());
    }
}
```

- **Say:** Look, I wrote `setMark` two times. One sets one mark, one sets all five. Remember this. We will give it a name later.
- **Say:** `setMark` checks 0 to 100. That is why `marks` is private: nobody can put 150 directly.

**Type → PortalApp.java**

```java
public class PortalApp {
    public static void main(String[] args) {
        Student hita = new Student(101, "Hita");
        int[] hitaMarks = {85, 92, 78, 88, 95};
        hita.setMark(hitaMarks);
        hita.addPayment(120000);

        hita.showDetails();
    }
}
```

**Run:**

```text
101  Hita  Total: 438  %: 87.6  PASS  Balance: 0.0
```

- **Say:** It works. The college is happy. Now the next requirement comes.

---

## Part 2. The College Also Has Teachers → Inheritance

### Requirement 2

- **Say:** New requirement: *"The college also has teachers. Mr. Verma teaches Java."*
- **Ask:** What does a teacher have?
- **Expected:** id, name, subject, salary.
- **Say:** Let me write it the simple way first.

**Board:**

```java
public class Teacher {
    private int id;
    private String name;
    private String subject;
    private double salary;
    // constructor, getId(), getName() ...
}
```

### What goes wrong?

- **Ask:** Look at `Student` and `Teacher`. What is written two times?
- **Expected:** `id` and `name`, and their constructor code and getters.
- **Ask:** Next month the college adds Counsellor, Librarian, Accountant. What happens?
- **Expected:** `id` and `name` are written 5 times.
- **Ask:** And if the college changes the rule for id? How many places do we change?
- **Expected:** Every class.
- **Say:** So the problem is: **the common part is copied**.

### The concept

- **Ask:** Student and Teacher. What is one word for both of them?
- **Expected:** A member of the college. A college member.
- **Say:** Good. Let us make a class for only the common part: `CollegeMember`.
- **Say:** Now read these sentences aloud with me.

**Board:**

```text
Student IS-A CollegeMember     ✔
Teacher IS-A CollegeMember     ✔
```

- **Say:** When the IS-A sentence is **true**, we use inheritance.
- **Say:** Saving code is a bonus. It is **not** the reason. The sentence must make sense first.
- **Ask:** Can I write `CollegeMember extends Student`?
- **Expected:** No. "A CollegeMember IS-A Student" is false. Mr. Verma is a member but not a student.

**Type → CollegeMember.java**

```java
public class CollegeMember {
    private int id;
    private String name;

    public CollegeMember(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
```

- **Say:** Now `Student` loses `id` and `name`. It gets them from the parent.

**Type → Student.java**

```java
public class Student extends CollegeMember {
    private int[] marks = new int[5];
    private double paid;

    public Student(int id, String name) {
        super(id, name);
    }

    public void setMark(int subject, int mark) {
        if (mark < 0 || mark > 100) {
            System.out.println("Invalid mark: " + mark);
        } else {
            marks[subject] = mark;
        }
    }

    public void setMark(int[] allMarks) {
        for (int i = 0; i < marks.length; i++) {
            setMark(i, allMarks[i]);
        }
    }

    public int total() {
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
        return sum;
    }

    public double percentage() {
        return total() / 5.0;
    }

    public boolean isPassed() {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < 40) {
                return false;
            }
        }
        return true;
    }

    public void addPayment(double amount) {
        paid += amount;
    }

    public double balance() {
        return 120000 - paid;
    }

    public void showDetails() {
        String result = isPassed() ? "PASS" : "FAIL";
        System.out.println(getId() + "  " + getName() + "  Total: " + total() + "  %: " + percentage()
                + "  " + result + "  Balance: " + balance());
    }
}
```

### `super()` — point at the constructor

- **Say:** Look at `super(id, name)`.
- **Ask:** `id` and `name` live in which class?
- **Expected:** `CollegeMember`.
- **Say:** So who should set them? The parent. `super(...)` calls the parent's constructor.

**Board:**

```text
new Student(101, "Hita")
        ↓
Student constructor
        ↓
super(101, "Hita")
        ↓
CollegeMember constructor → id = 101, name = "Hita"
```

- **Ask:** In `showDetails()` I wrote `getId()`, not `id`. Why?
- **Expected:** `id` is private in `CollegeMember`. The child cannot touch it directly.

**Type → Teacher.java**

```java
public class Teacher extends CollegeMember {
    private String subject;
    private double salary;

    public Teacher(int id, String name, String subject, double salary) {
        super(id, name);
        this.subject = subject;
        this.salary = salary;
    }

    public void showDetails() {
        System.out.println(getId() + "  " + getName() + "  Subject: " + subject + "  Salary: " + salary);
    }
}
```

**Type → PortalApp.java**

```java
public class PortalApp {
    public static void main(String[] args) {
        Student hita = new Student(101, "Hita");
        int[] hitaMarks = {85, 92, 78, 88, 95};
        hita.setMark(hitaMarks);
        hita.addPayment(120000);

        Teacher verma = new Teacher(104, "Verma", "Java", 60000);

        hita.showDetails();
        verma.showDetails();
    }
}
```

**Run:**

```text
101  Hita  Total: 438  %: 87.6  PASS  Balance: 0.0
104  Verma  Subject: Java  Salary: 60000.0
```

- **Say:** Same output as before for Hita. But now the common part is written **once**.

---

## Part 3. Nobody Is "Just a Member" → Abstract Class

### Problem 1: an object that means nothing

- **Ask:** Right now, can I write this?

**Board:**

```java
CollegeMember m = new CollegeMember(105, "Riya");
```

- **Expected:** Yes, it compiles.
- **Ask:** So what is Riya? A student? A teacher? Does she have marks? A salary?
- **Expected:** We don't know.
- **Say:** In our college, nobody is "just a member". Everyone is a student or a teacher.
- **Say:** `CollegeMember` is a **general idea**, not a real thing.
- **Say:** When a class is only an idea and must never have objects, we make it **abstract**.

### Problem 2: a method the parent cannot write

- **Say:** New requirement: *"Every member must be able to show details and say their role."*
- **Ask:** Can I write `showDetails()` inside `CollegeMember`? What will it print?
- **Expected:** It can't. The parent doesn't know marks or subject.
- **Say:** So the parent can only say: *"every member MUST have showDetails()"*.
- **Say:** The child says **how**.

**Board:**

```text
Parent (abstract method) → WHAT must exist
Child  (override)        → HOW it works
```

**Type → CollegeMember.java**

```java
public abstract class CollegeMember {
    private int id;
    private String name;

    public CollegeMember(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract String role();

    public abstract void showDetails();
}
```

**Show error:** add this line in `main`, compile:

```java
CollegeMember m = new CollegeMember(105, "Riya");
```

```text
error: CollegeMember is abstract; cannot be instantiated
```

- **Say:** The compiler now protects the idea. No object of "just a member".
- **Ask:** An abstract class still has a constructor. Why? Nobody can call `new` on it.
- **Expected:** The children call it with `super(id, name)`.

**Show error:** compile now, before changing `Student` and `Teacher`:

```text
error: Student is not abstract and does not override abstract method role() in CollegeMember
error: Teacher is not abstract and does not override abstract method role() in CollegeMember
```

- **Say:** The parent made a rule. Every child **must** follow it, or it does not compile.

**Type → Student.java** (add `role()` and `@Override`)

```java
public class Student extends CollegeMember {
    private int[] marks = new int[5];
    private double paid;

    public Student(int id, String name) {
        super(id, name);
    }

    public void setMark(int subject, int mark) {
        if (mark < 0 || mark > 100) {
            System.out.println("Invalid mark: " + mark);
        } else {
            marks[subject] = mark;
        }
    }

    public void setMark(int[] allMarks) {
        for (int i = 0; i < marks.length; i++) {
            setMark(i, allMarks[i]);
        }
    }

    public int total() {
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
        return sum;
    }

    public double percentage() {
        return total() / 5.0;
    }

    public boolean isPassed() {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < 40) {
                return false;
            }
        }
        return true;
    }

    public void addPayment(double amount) {
        paid += amount;
    }

    public double balance() {
        return 120000 - paid;
    }

    @Override
    public String role() {
        return "Student";
    }

    @Override
    public void showDetails() {
        String result = isPassed() ? "PASS" : "FAIL";
        System.out.println(getId() + "  " + getName() + "  [" + role() + "]  Total: " + total()
                + "  %: " + percentage() + "  " + result + "  Balance: " + balance());
    }
}
```

**Type → Teacher.java**

```java
public class Teacher extends CollegeMember {
    private String subject;
    private double salary;

    public Teacher(int id, String name, String subject, double salary) {
        super(id, name);
        this.subject = subject;
        this.salary = salary;
    }

    @Override
    public String role() {
        return "Teacher";
    }

    @Override
    public void showDetails() {
        System.out.println(getId() + "  " + getName() + "  [" + role() + "]  Subject: " + subject
                + "  Salary: " + salary);
    }
}
```

- **Say:** Same method name, same parameters, different body in each child. That is **overriding**.
- **Say:** `@Override` asks the compiler: "please check I really am overriding". Spell it `showDetail` by mistake and it will catch you.

**Run:** (`PortalApp` is unchanged)

```text
101  Hita  [Student]  Total: 438  %: 87.6  PASS  Balance: 0.0
104  Verma  [Teacher]  Subject: Java  Salary: 60000.0
```

---

## Part 4. The UG / PG Trap (board only, no code)

- **Say:** Someone in the team says: *"The college has UG and PG students. Let us write `PgStudent extends UgStudent`. Then we also get multilevel inheritance!"*

**Board:**

```text
CollegeMember
      |
  UgStudent
      |
  PgStudent
```

- **Ask:** Read the sentence: *"A PG student IS-A UG student."* Is it true?
- **Expected:** No. A PG student is a student, but not a UG student.
- **Say:** Then this design is wrong, even if it compiles.
- **Say:** Never build a hierarchy **just to show a feature**.
- **Say:** Our correct base design is this:

**Board:**

```text
            CollegeMember   <<abstract>>
             /          \
        Student        Teacher
```

- **Say:** We **will** get real multilevel inheritance. But only when the college gives us a real reason. Wait for Part 9.

### End of Session 1 — recap on the board

```text
Common part copied           → inheritance (IS-A must be true)
Parent sets its own part     → super()
Class is only an idea        → abstract class
Parent knows WHAT, not HOW   → abstract method
Child writes HOW             → overriding (@Override)
```

---

# SESSION 2 — Capabilities and Polymorphism

## Part 5. Not Every Member Has Marks → Interfaces

- **Say:** Look at `Student` again. Marks, total, pass, fee, balance.
- **Ask:** Does Mr. Verma have marks?
- **Expected:** No.
- **Ask:** Does Mr. Verma pay a student fee?
- **Expected:** No.
- **Ask:** So can we move `total()` or `balance()` up into `CollegeMember`?
- **Expected:** No. Then Mr. Verma also gets marks and a fee. Wrong.
- **Say:** Read these two sentences.

**Board:**

```text
Student IS-A CollegeMember       ← what am I?    (identity)
Student CAN BE GRADED            ← what can I do? (capability)
Student CAN PAY FEES             ← what can I do? (capability)
```

- **Say:** "Is-a" is identity. Identity goes in a **class**.
- **Say:** "Can do" is a capability. A capability goes in an **interface**.
- **Ask:** Can a class extend two classes? `class Student extends CollegeMember, Gradable`?
- **Expected:** No. Java allows only one parent class.
- **Say:** But a class can implement **many** interfaces. That is exactly what we need.

**Board:**

```text
WHAT AM I?       → CollegeMember        → abstract class
WHAT CAN I DO?   → Gradable, FeePayer   → interfaces
```

**Type → Gradable.java**

```java
public interface Gradable {
    int PASS_MARK = 40;

    int total();
    double percentage();
    boolean isPassed();
}
```

- **Say:** `PASS_MARK` is automatically `public static final`. One pass mark for every student. Nobody can change it.

**Type → FeePayer.java**

```java
public interface FeePayer {
    double fee();
    double paidAmount();
    void addPayment(double amount);
    double balance();
}
```

- **Ask:** Why is `paid` not inside the interface?
- **Expected:** An interface cannot keep an object's data. The class keeps it.

**Type → Student.java**

```java
public class Student extends CollegeMember implements Gradable, FeePayer {
    private int[] marks = new int[5];
    private double paid;

    public Student(int id, String name) {
        super(id, name);
    }

    public void setMark(int subject, int mark) {
        if (mark < 0 || mark > 100) {
            System.out.println("Invalid mark: " + mark);
        } else {
            marks[subject] = mark;
        }
    }

    public void setMark(int[] allMarks) {
        for (int i = 0; i < marks.length; i++) {
            setMark(i, allMarks[i]);
        }
    }

    @Override
    public int total() {
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
        return sum;
    }

    @Override
    public double percentage() {
        return total() / 5.0;
    }

    @Override
    public boolean isPassed() {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < PASS_MARK) {
                return false;
            }
        }
        return true;
    }

    @Override
    public double fee() {
        return 120000;
    }

    @Override
    public double paidAmount() {
        return paid;
    }

    @Override
    public void addPayment(double amount) {
        paid += amount;
    }

    @Override
    public double balance() {
        return fee() - paidAmount();
    }

    @Override
    public String role() {
        return "Student";
    }

    @Override
    public void showDetails() {
        String result = isPassed() ? "PASS" : "FAIL";
        System.out.println(getId() + "  " + getName() + "  [" + role() + "]  Total: " + total()
                + "  %: " + percentage() + "  " + result + "  Balance: " + balance());
    }
}
```

- **Say:** `extends` one class, `implements` two interfaces. Both together.
- **Ask:** Why are all these methods `public`?
- **Expected:** Interface methods are public. An override cannot make them weaker.
- **Ask:** Did we change `Teacher`?
- **Expected:** No. Teacher implements neither. No fake `fee()` that returns 0.

**Show error:** add in `main`:

```java
FeePayer f = verma;
```

```text
error: incompatible types: Teacher cannot be converted to FeePayer
```

- **Say:** Good! The compiler itself knows a teacher does not pay fees.

**Run:** (`PortalApp` is unchanged)

```text
101  Hita  [Student]  Total: 438  %: 87.6  PASS  Balance: 0.0
104  Verma  [Teacher]  Subject: Java  Salary: 60000.0
```

---

## Part 6. Same Rule Everywhere → `default` and `static` Methods

### `default balance()`

**Board:**

```text
balance = fee − paid
```

- **Say:** Today only `Student` pays fees. But the college will add more fee payers: research students, hostel students, short-course students.
- **Ask:** What will each of them write for `balance()`?
- **Expected:** The same line: `fee() - paidAmount()`.
- **Say:** Same rule, copied in every class. That is the problem.
- **Ask:** Where can we write it **once**? In `CollegeMember`?
- **Expected:** No, then teachers get a balance.
- **Say:** In the interface itself, **with a body**. That is a **default method**.

### `static lateFine()`

- **Say:** New requirement: *"Late fee payment: fine of Rs 50 per day."*
- **Ask:** Does the fine depend on **which** student pays?
- **Expected:** No. Only on the number of days.
- **Say:** It needs no student data. So it needs no object. That is a **static** method.

**Board:**

```text
Needs this object's data   → instance / default method   f.balance()
Needs no object at all     → static method                FeePayer.lateFine(3)
```

**Type → FeePayer.java**

```java
public interface FeePayer {
    double FINE_PER_DAY = 50;

    double fee();
    double paidAmount();
    void addPayment(double amount);

    default double balance() {
        return fee() - paidAmount();
    }

    static double lateFine(int days) {
        return days * FINE_PER_DAY;
    }
}
```

- **Say:** Now **delete** `balance()` from `Student`.

**Type → Student.java** (`balance()` removed)

```java
public class Student extends CollegeMember implements Gradable, FeePayer {
    private int[] marks = new int[5];
    private double paid;

    public Student(int id, String name) {
        super(id, name);
    }

    public void setMark(int subject, int mark) {
        if (mark < 0 || mark > 100) {
            System.out.println("Invalid mark: " + mark);
        } else {
            marks[subject] = mark;
        }
    }

    public void setMark(int[] allMarks) {
        for (int i = 0; i < marks.length; i++) {
            setMark(i, allMarks[i]);
        }
    }

    @Override
    public int total() {
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
        return sum;
    }

    @Override
    public double percentage() {
        return total() / 5.0;
    }

    @Override
    public boolean isPassed() {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < PASS_MARK) {
                return false;
            }
        }
        return true;
    }

    @Override
    public double fee() {
        return 120000;
    }

    @Override
    public double paidAmount() {
        return paid;
    }

    @Override
    public void addPayment(double amount) {
        paid += amount;
    }

    @Override
    public String role() {
        return "Student";
    }

    @Override
    public void showDetails() {
        String result = isPassed() ? "PASS" : "FAIL";
        System.out.println(getId() + "  " + getName() + "  [" + role() + "]  Total: " + total()
                + "  %: " + percentage() + "  " + result + "  Balance: " + balance());
    }
}
```

- **Ask:** `Student` has no `balance()` now. Will `showDetails()` still work?
- **Expected:** Yes, it comes from `FeePayer` as a default method.

**Type → PortalApp.java**

```java
public class PortalApp {
    public static void main(String[] args) {
        Student hita = new Student(101, "Hita");
        int[] hitaMarks = {85, 92, 78, 88, 95};
        hita.setMark(hitaMarks);
        hita.addPayment(120000);

        Teacher verma = new Teacher(104, "Verma", "Java", 60000);

        hita.showDetails();
        verma.showDetails();

        System.out.println("Fine for 3 days late: " + FeePayer.lateFine(3));
    }
}
```

**Show error:** change the last line to `hita.lateFine(3)`:

```text
error: cannot find symbol
  symbol:   method lateFine(int)
```

- **Ask:** `Student` implements `FeePayer`. Why can't Hita see `lateFine`?
- **Expected:** A static method in an interface is **not inherited** by the class.
- **Say:** A static interface method is called **only** with the interface name: `FeePayer.lateFine(3)`.

**Run:**

```text
101  Hita  [Student]  Total: 438  %: 87.6  PASS  Balance: 0.0
104  Verma  [Teacher]  Subject: Java  Salary: 60000.0
Fine for 3 days late: 150.0
```

---

## Part 7. Two Interfaces, Same Default → the Diamond Problem

- **Say:** New requirement: *"Show one status word for each student."*
- **Say:** The exam cell wants `PASS` or `FAIL`.
- **Say:** The accounts office wants `FEE CLEARED` or `FEE DUE`.
- **Say:** Both are good default methods. Let us add `status()` to both interfaces.

**Type → Gradable.java**

```java
public interface Gradable {
    int PASS_MARK = 40;

    int total();
    double percentage();
    boolean isPassed();

    default String status() {
        return isPassed() ? "PASS" : "FAIL";
    }
}
```

**Type → FeePayer.java**

```java
public interface FeePayer {
    double FINE_PER_DAY = 50;

    double fee();
    double paidAmount();
    void addPayment(double amount);

    default double balance() {
        return fee() - paidAmount();
    }

    default String status() {
        return balance() <= 0 ? "FEE CLEARED" : "FEE DUE";
    }

    static double lateFine(int days) {
        return days * FINE_PER_DAY;
    }
}
```

**Show error:** compile now:

```text
error: types Gradable and FeePayer are incompatible;
  class Student inherits unrelated defaults for status() from types Gradable and FeePayer
```

- **Ask:** `Student` implements both. `hita.status()` — which one should Java run?
- **Expected:** We can't tell. Both have a body.
- **Say:** Java does not guess. This is the **diamond problem** with default methods.
- **Say:** Default methods do not remove the confusion. Java makes the **class** decide.
- **Say:** We decide: show **both**. `InterfaceName.super.method()` calls one interface's version.

**Type → Student.java** (add `status()`, use it in `showDetails()`)

```java
public class Student extends CollegeMember implements Gradable, FeePayer {
    private int[] marks = new int[5];
    private double paid;

    public Student(int id, String name) {
        super(id, name);
    }

    public void setMark(int subject, int mark) {
        if (mark < 0 || mark > 100) {
            System.out.println("Invalid mark: " + mark);
        } else {
            marks[subject] = mark;
        }
    }

    public void setMark(int[] allMarks) {
        for (int i = 0; i < marks.length; i++) {
            setMark(i, allMarks[i]);
        }
    }

    @Override
    public int total() {
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
        return sum;
    }

    @Override
    public double percentage() {
        return total() / 5.0;
    }

    @Override
    public boolean isPassed() {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < PASS_MARK) {
                return false;
            }
        }
        return true;
    }

    @Override
    public double fee() {
        return 120000;
    }

    @Override
    public double paidAmount() {
        return paid;
    }

    @Override
    public void addPayment(double amount) {
        paid += amount;
    }

    @Override
    public String status() {
        return Gradable.super.status() + ", " + FeePayer.super.status();
    }

    @Override
    public String role() {
        return "Student";
    }

    @Override
    public void showDetails() {
        System.out.println(getId() + "  " + getName() + "  [" + role() + "]  Total: " + total()
                + "  %: " + percentage() + "  Balance: " + balance() + "  " + status());
    }
}
```

**Run:** (`PortalApp` is unchanged)

```text
101  Hita  [Student]  Total: 438  %: 87.6  Balance: 0.0  PASS, FEE CLEARED
104  Verma  [Teacher]  Subject: Java  Salary: 60000.0
Fine for 3 days late: 150.0
```

---

## Part 8. One List for Everyone → Polymorphism

### Requirement

- **Say:** New requirement: *"Show all members in one list."* Somya joins as a student.
- **Ask:** Hita and Somya are `Student`. Mr. Verma is `Teacher`. Do we need two arrays?
- **Ask:** What is true for all three?
- **Expected:** All of them IS-A `CollegeMember`.
- **Say:** So **one** `CollegeMember` array can hold all of them. Child object in a parent reference: **upcasting**.

### Which `showDetails()` runs?

- **Ask:** `members[i]` is declared as `CollegeMember`. When I call `members[i].showDetails()`, whose method runs? Parent's? Student's? Teacher's?
- Let them guess. Write the guesses on the board. Then run.

### Somya's marks come later → downcasting

- **Say:** The exam cell sends Somya's marks later. We only have the array.

**Show error:**

```java
CollegeMember m = members[1];
m.setMark(somyaMarks);
```

```text
error: cannot find symbol
  symbol:   method setMark(int[])
  location: variable m of type CollegeMember
```

- **Ask:** Somya **is** a Student. Why can't we call `setMark`?
- **Expected:** The reference type is `CollegeMember`. `CollegeMember` has no `setMark`.
- **Say:** The compiler checks the **reference type**. We must say: "this is really a Student". First check, then narrow: `instanceof` + **downcasting**.

### Show the topper → `instanceof` for a capability

- **Say:** Requirement: *"Show the topper."*
- **Ask:** Can I call `members[i].total()` in a loop?
- **Expected:** No. Mr. Verma has no `total()`.
- **Say:** Only `Gradable` members have marks. So here we check: `instanceof Gradable`.

**Type → PortalApp.java**

```java
public class PortalApp {
    public static void main(String[] args) {
        Student hita = new Student(101, "Hita");
        int[] hitaMarks = {85, 92, 78, 88, 95};
        hita.setMark(hitaMarks);
        hita.addPayment(120000);

        Student somya = new Student(103, "Somya");
        Teacher verma = new Teacher(104, "Verma", "Java", 60000);

        CollegeMember[] members = {hita, somya, verma};

        // Somya's marks come later
        int[] somyaMarks = {91, 95, 89, 94, 96};
        CollegeMember m = members[1];
        if (m instanceof Student s) {
            s.setMark(somyaMarks);
        }

        for (int i = 0; i < members.length; i++) {
            members[i].showDetails();
        }

        CollegeMember topper = null;
        int best = -1;
        for (int i = 0; i < members.length; i++) {
            if (members[i] instanceof Gradable g && g.total() > best) {
                best = g.total();
                topper = members[i];
            }
        }
        System.out.println("Topper: " + topper.getName() + "  Total: " + best);
    }
}
```

**Run:**

```text
101  Hita  [Student]  Total: 438  %: 87.6  Balance: 0.0  PASS, FEE CLEARED
103  Somya  [Student]  Total: 465  %: 93.0  Balance: 120000.0  PASS, FEE DUE
104  Verma  [Teacher]  Subject: Java  Salary: 60000.0
Topper: Somya  Total: 465
```

- **Say:** The same line `members[i].showDetails()` ran **Student's** code two times and **Teacher's** code once.
- **Say:** Java looked at the **real object at run time**, not the reference type.
- **Say:** That is **dynamic method dispatch**. It is how **runtime polymorphism** works.

**Board:**

```text
Reference type: CollegeMember        (compiler checks this)
Real object:    Student / Teacher    (JVM runs this one's method)
```

### Why is this so useful? Show the bad version

**Board:**

```java
// WITHOUT polymorphism
if (m instanceof Student) {
    // print student
} else if (m instanceof Teacher) {
    // print teacher
} else if (m instanceof Counsellor) {
    // print counsellor
} ...
```

- **Ask:** The college adds Librarian. What happens to this code?
- **Expected:** One more `else if`. Every time.
- **Say:** With polymorphism, the loop **never changes**. Each object knows how to show itself.

### When is `instanceof` right?

- **Say:** So is `instanceof` bad? No.

**Board:**

```text
Everyone has it (showDetails)            → polymorphism, no instanceof
Only some have it (total, setMark, fee)  → instanceof is correct
```

- **Say:** Don't use `instanceof` as a replacement for overriding.

---

## Part 9. Research Students → Real Multilevel Inheritance

### Requirement

- **Say:** New requirement: *"The college admits research students. Sakshi researches Cloud Computing, her guide is Mr. Verma. Research fee is 1,50,000. To pass, a research student must pass every subject AND get at least 50%."*
- **Ask:** Read the sentence: *"A research student IS-A student."* True?
- **Expected:** Yes.
- **Say:** Now multilevel inheritance has a **real** reason. Compare with the UG/PG trap from Part 4.

**Board:**

```text
CollegeMember
      |
   Student
      |
ResearchStudent      ✔ IS-A Student
```

- **Ask:** What is the **same** as a normal student?
- **Expected:** 5 marks, total, percentage, paying fees, balance.
- **Ask:** What is **different**?
- **Expected:** fee, pass rule, role, topic and guide.
- **Say:** We override only what is different. Everything else comes free.

**Type → ResearchStudent.java**

```java
public class ResearchStudent extends Student {
    private String researchTopic;
    private String guide;

    public ResearchStudent(int id, String name, String researchTopic, String guide) {
        super(id, name);
        this.researchTopic = researchTopic;
        this.guide = guide;
    }

    @Override
    public double fee() {
        return 150000;
    }

    @Override
    public boolean isPassed() {
        return super.isPassed() && percentage() >= 50;
    }

    @Override
    public String role() {
        return "Research Student";
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("      Topic: " + researchTopic + "  Guide: " + guide);
    }
}
```

- **Say:** `super(id, name)` runs `Student()`, which runs `CollegeMember()`. Three levels.
- **Say:** `super.isPassed()` reuses the Student rule. We don't write that loop again.
- **Say:** `super.showDetails()` reuses the Student printing. We only add one line.
- **Ask:** We did not write `balance()`. Will Sakshi's balance use 1,20,000 or 1,50,000?
- **Expected:** 1,50,000. `balance()` calls `fee()`, and `fee()` of the real object runs.

**Type → PortalApp.java** (Sakshi added; Somya is now at index 2)

```java
public class PortalApp {
    public static void main(String[] args) {
        Student hita = new Student(101, "Hita");
        int[] hitaMarks = {85, 92, 78, 88, 95};
        hita.setMark(hitaMarks);
        hita.addPayment(120000);

        ResearchStudent sakshi = new ResearchStudent(102, "Sakshi", "Cloud Computing", "Mr. Verma");
        int[] sakshiMarks = {45, 52, 60, 42, 45};
        sakshi.setMark(sakshiMarks);
        sakshi.addPayment(50000);

        Student somya = new Student(103, "Somya");
        Teacher verma = new Teacher(104, "Verma", "Java", 60000);

        CollegeMember[] members = {hita, sakshi, somya, verma};

        // Somya's marks come later
        int[] somyaMarks = {91, 95, 89, 94, 96};
        CollegeMember m = members[2];
        if (m instanceof Student s) {
            s.setMark(somyaMarks);
        }

        for (int i = 0; i < members.length; i++) {
            members[i].showDetails();
        }

        CollegeMember topper = null;
        int best = -1;
        for (int i = 0; i < members.length; i++) {
            if (members[i] instanceof Gradable g && g.total() > best) {
                best = g.total();
                topper = members[i];
            }
        }
        System.out.println("Topper: " + topper.getName() + "  Total: " + best);
    }
}
```

**Run:**

```text
101  Hita  [Student]  Total: 438  %: 87.6  Balance: 0.0  PASS, FEE CLEARED
102  Sakshi  [Research Student]  Total: 244  %: 48.8  Balance: 100000.0  FAIL, FEE DUE
      Topic: Cloud Computing  Guide: Mr. Verma
103  Somya  [Student]  Total: 465  %: 93.0  Balance: 120000.0  PASS, FEE DUE
104  Verma  [Teacher]  Subject: Java  Salary: 60000.0
Topper: Somya  Total: 465
```

- **Ask:** Sakshi has every subject above 40. Why FAIL?
- **Expected:** 48.8% — research students need 50%.
- **Ask:** Most important question: did we change the `showDetails()` loop?
- **Expected:** No!
- **Say:** A new type came in. The old loop handled it with zero change. That is the power of polymorphism.

### End of Session 2 — recap on the board

```text
Only some members can do it      → interface (capability)
Same rule for every implementer  → default method
Needs no object                  → static method
Two defaults with same name      → class must override (X.super.m())
Parent reference, child object   → upcasting
Real object's method runs        → dynamic dispatch / runtime polymorphism
Only some objects have it        → instanceof + downcasting
Real IS-A specialisation         → multilevel inheritance
```

---

# SESSION 3 — Abstraction in a Real System

## Part 10. Pay by UPI, Card or Cash → Abstract `Payment`

### Requirement

- **Say:** New requirement: *"Students pay fees by UPI, Card or Cash."*
- **Say:** Let me show you how most people write it first.

**Board:**

```java
void pay(String mode, double amount) {
    if (mode.equals("UPI")) {
        // scan QR, enter PIN
    } else if (mode.equals("CARD")) {
        // swipe, enter PIN
    } else if (mode.equals("CASH")) {
        // cashier counts
    }
}
```

### What goes wrong?

- **Ask:** Next month the college adds Net Banking. What happens to this method?
- **Expected:** We open it and add another `else if`.
- **Ask:** Then Wallet?
- **Expected:** Open it again.
- **Say:** Every new mode changes old, tested code. And someone types `"Upi"` instead of `"UPI"` — no error, it just silently does nothing.
- **Say:** It is the same if-else problem from Part 8. So the same cure.

### The concept

- **Ask:** UPI, Card, Cash. One word for all three?
- **Expected:** Payment.
- **Ask:** *"CardPayment IS-A Payment"* — true?
- **Expected:** Yes.
- **Ask:** Can anyone pay by "just Payment"?
- **Expected:** No. It is always some real mode.
- **Say:** So `Payment` is **abstract**.
- **Ask:** What is the same for every mode?
- **Expected:** The receipt.
- **Ask:** What is different?
- **Expected:** **How** the money is paid.
- **Say:** Same part → written in the parent. Different part → abstract method, each child writes it.
- **Ask:** Why abstract class here, not an interface?
- **Expected:** All modes share data (`modeName`) and the same receipt code.

**Type → Payment.java**

```java
public abstract class Payment {
    private String modeName;

    public Payment(String modeName) {
        this.modeName = modeName;
    }

    public abstract void pay(double amount);

    public void printReceipt(FeePayer payer, double amount, double fine) {
        System.out.println("------ VGU College Fee Receipt ------");
        System.out.println("Fee paid  : " + amount);
        System.out.println("Late fine : " + fine);
        System.out.println("Mode      : " + modeName);
        System.out.println("Balance   : " + payer.balance());
        System.out.println("-------------------------------------");
    }
}
```

**Type → UpiPayment.java**

```java
public class UpiPayment extends Payment {
    public UpiPayment() {
        super("UPI");
    }

    @Override
    public void pay(double amount) {
        System.out.println("Scan the QR code and enter UPI PIN");
        System.out.println("Rs " + amount + " paid by UPI");
    }
}
```

**Type → CardPayment.java**

```java
public class CardPayment extends Payment {
    public CardPayment() {
        super("Card");
    }

    @Override
    public void pay(double amount) {
        System.out.println("Swipe the card and enter card PIN");
        System.out.println("Rs " + amount + " paid by card");
    }
}
```

**Type → CashPayment.java**

```java
public class CashPayment extends Payment {
    public CashPayment() {
        super("Cash");
    }

    @Override
    public void pay(double amount) {
        System.out.println("Give cash at the counter");
        System.out.println("Cashier counts Rs " + amount);
    }
}
```

**Type → PortalApp.java** (two lines added at the end)

```java
public class PortalApp {
    public static void main(String[] args) {
        Student hita = new Student(101, "Hita");
        int[] hitaMarks = {85, 92, 78, 88, 95};
        hita.setMark(hitaMarks);
        hita.addPayment(120000);

        ResearchStudent sakshi = new ResearchStudent(102, "Sakshi", "Cloud Computing", "Mr. Verma");
        int[] sakshiMarks = {45, 52, 60, 42, 45};
        sakshi.setMark(sakshiMarks);
        sakshi.addPayment(50000);

        Student somya = new Student(103, "Somya");
        Teacher verma = new Teacher(104, "Verma", "Java", 60000);

        CollegeMember[] members = {hita, sakshi, somya, verma};

        // Somya's marks come later
        int[] somyaMarks = {91, 95, 89, 94, 96};
        CollegeMember m = members[2];
        if (m instanceof Student s) {
            s.setMark(somyaMarks);
        }

        for (int i = 0; i < members.length; i++) {
            members[i].showDetails();
        }

        CollegeMember topper = null;
        int best = -1;
        for (int i = 0; i < members.length; i++) {
            if (members[i] instanceof Gradable g && g.total() > best) {
                best = g.total();
                topper = members[i];
            }
        }
        System.out.println("Topper: " + topper.getName() + "  Total: " + best);

        Payment mode = new CardPayment();
        mode.pay(10000);
    }
}
```

- **Ask:** Reference type `Payment`, real object `CardPayment`. Whose `pay()` runs?
- **Expected:** `CardPayment`'s. Same as Part 8.

**Run:**

```text
101  Hita  [Student]  Total: 438  %: 87.6  Balance: 0.0  PASS, FEE CLEARED
102  Sakshi  [Research Student]  Total: 244  %: 48.8  Balance: 100000.0  FAIL, FEE DUE
      Topic: Cloud Computing  Guide: Mr. Verma
103  Somya  [Student]  Total: 465  %: 93.0  Balance: 120000.0  PASS, FEE DUE
104  Verma  [Teacher]  Subject: Java  Salary: 60000.0
Topper: Somya  Total: 465
Swipe the card and enter card PIN
Rs 10000.0 paid by card
```

---

## Part 11. The Fee Counter → Abstraction

- **Say:** Now the real fee counter. Four steps: work out the fine, take the money, update the student, print the receipt.

**Type → FeeCounter.java**

```java
public class FeeCounter {
    public static void collect(FeePayer payer, Payment mode, double amount, int daysLate) {
        double fine = FeePayer.lateFine(daysLate);
        mode.pay(amount + fine);
        payer.addPayment(amount);
        mode.printReceipt(payer, amount, fine);
    }
}
```

- **Say:** Read the parameter types slowly. `FeePayer`. `Payment`.
- **Ask:** Does `FeeCounter` know that Sakshi is a `ResearchStudent`?
- **Expected:** No. It only knows `FeePayer`.
- **Ask:** Does it know she pays by card?
- **Expected:** No. It only knows `Payment`.
- **Say:** `FeeCounter` knows **what** to do. It does not know **how** each one does it.

**Board:**

```text
FeeCounter knows WHAT:            It does NOT know HOW:
  FeePayer.lateFine()               UPI  → QR code, PIN
  mode.pay()                        Card → swipe, PIN
  payer.addPayment()                Cash → cashier counts
  mode.printReceipt()
```

- **Say:** Hiding details from code that does not need them — that is **abstraction**.
- **Say:** `FeeCounter` depends on **abstractions** (`FeePayer`, `Payment`), not on concrete classes.
- **Ask:** Why is `collect()` static?
- **Expected:** The counter keeps no data of its own. No object needed.

**Type → PortalApp.java** (replace the last two lines)

```java
public class PortalApp {
    public static void main(String[] args) {
        Student hita = new Student(101, "Hita");
        int[] hitaMarks = {85, 92, 78, 88, 95};
        hita.setMark(hitaMarks);
        hita.addPayment(120000);

        ResearchStudent sakshi = new ResearchStudent(102, "Sakshi", "Cloud Computing", "Mr. Verma");
        int[] sakshiMarks = {45, 52, 60, 42, 45};
        sakshi.setMark(sakshiMarks);
        sakshi.addPayment(50000);

        Student somya = new Student(103, "Somya");
        Teacher verma = new Teacher(104, "Verma", "Java", 60000);

        CollegeMember[] members = {hita, sakshi, somya, verma};

        // Somya's marks come later
        int[] somyaMarks = {91, 95, 89, 94, 96};
        CollegeMember m = members[2];
        if (m instanceof Student s) {
            s.setMark(somyaMarks);
        }

        for (int i = 0; i < members.length; i++) {
            members[i].showDetails();
        }

        CollegeMember topper = null;
        int best = -1;
        for (int i = 0; i < members.length; i++) {
            if (members[i] instanceof Gradable g && g.total() > best) {
                best = g.total();
                topper = members[i];
            }
        }
        System.out.println("Topper: " + topper.getName() + "  Total: " + best);

        // Sakshi pays 10000 by card, 3 days late
        FeeCounter.collect(sakshi, new CardPayment(), 10000, 3);
    }
}
```

**Show error:**

```java
FeeCounter.collect(verma, new CashPayment(), 1000, 0);
```

```text
error: incompatible types: Teacher cannot be converted to FeePayer
```

- **Say:** Nobody can take a fee from Mr. Verma by mistake. The interface protects us.

**Run:**

```text
101  Hita  [Student]  Total: 438  %: 87.6  Balance: 0.0  PASS, FEE CLEARED
102  Sakshi  [Research Student]  Total: 244  %: 48.8  Balance: 100000.0  FAIL, FEE DUE
      Topic: Cloud Computing  Guide: Mr. Verma
103  Somya  [Student]  Total: 465  %: 93.0  Balance: 120000.0  PASS, FEE DUE
104  Verma  [Teacher]  Subject: Java  Salary: 60000.0
Topper: Somya  Total: 465
Swipe the card and enter card PIN
Rs 10150.0 paid by card
------ VGU College Fee Receipt ------
Fee paid  : 10000.0
Late fine : 150.0
Mode      : Card
Balance   : 90000.0
-------------------------------------
```

- **Ask:** Fine is 150. Paid by card: 10150. Balance: 1,50,000 − 50,000 − 10,000 = ?
- **Expected:** 90,000.

---

## Part 12. Add Net Banking — Live

- **Say:** One month later: *"The college adds Net Banking."*
- **Say:** Now we test our design. Let us count how many files change.

**Type → NetBankingPayment.java**

```java
public class NetBankingPayment extends Payment {
    public NetBankingPayment() {
        super("Net Banking");
    }

    @Override
    public void pay(double amount) {
        System.out.println("Log in to net banking and enter the OTP");
        System.out.println("Rs " + amount + " paid by net banking");
    }
}
```

**Type → PortalApp.java** (one line added at the end)

```java
public class PortalApp {
    public static void main(String[] args) {
        Student hita = new Student(101, "Hita");
        int[] hitaMarks = {85, 92, 78, 88, 95};
        hita.setMark(hitaMarks);
        hita.addPayment(120000);

        ResearchStudent sakshi = new ResearchStudent(102, "Sakshi", "Cloud Computing", "Mr. Verma");
        int[] sakshiMarks = {45, 52, 60, 42, 45};
        sakshi.setMark(sakshiMarks);
        sakshi.addPayment(50000);

        Student somya = new Student(103, "Somya");
        Teacher verma = new Teacher(104, "Verma", "Java", 60000);

        CollegeMember[] members = {hita, sakshi, somya, verma};

        // Somya's marks come later
        int[] somyaMarks = {91, 95, 89, 94, 96};
        CollegeMember m = members[2];
        if (m instanceof Student s) {
            s.setMark(somyaMarks);
        }

        for (int i = 0; i < members.length; i++) {
            members[i].showDetails();
        }

        CollegeMember topper = null;
        int best = -1;
        for (int i = 0; i < members.length; i++) {
            if (members[i] instanceof Gradable g && g.total() > best) {
                best = g.total();
                topper = members[i];
            }
        }
        System.out.println("Topper: " + topper.getName() + "  Total: " + best);

        // Sakshi pays 10000 by card, 3 days late
        FeeCounter.collect(sakshi, new CardPayment(), 10000, 3);

        // Somya pays 5000 by net banking, on time
        FeeCounter.collect(somya, new NetBankingPayment(), 5000, 0);
    }
}
```

**Run:**

```text
101  Hita  [Student]  Total: 438  %: 87.6  Balance: 0.0  PASS, FEE CLEARED
102  Sakshi  [Research Student]  Total: 244  %: 48.8  Balance: 100000.0  FAIL, FEE DUE
      Topic: Cloud Computing  Guide: Mr. Verma
103  Somya  [Student]  Total: 465  %: 93.0  Balance: 120000.0  PASS, FEE DUE
104  Verma  [Teacher]  Subject: Java  Salary: 60000.0
Topper: Somya  Total: 465
Swipe the card and enter card PIN
Rs 10150.0 paid by card
------ VGU College Fee Receipt ------
Fee paid  : 10000.0
Late fine : 150.0
Mode      : Card
Balance   : 90000.0
-------------------------------------
Log in to net banking and enter the OTP
Rs 5000.0 paid by net banking
------ VGU College Fee Receipt ------
Fee paid  : 5000.0
Late fine : 0.0
Mode      : Net Banking
Balance   : 115000.0
-------------------------------------
```

- **Ask:** Did `FeeCounter` change?
- **Expected:** No.
- **Ask:** Did `Payment`, `Student`, `FeePayer` change?
- **Expected:** No.
- **Say:** Be honest though: `main` changed. Someone must **create** the new object.
- **Say:** So the correct sentence is: *"The core fee logic does not depend on the payment type, so it did not change."*
- **Say:** Not *"nothing changed anywhere"*. That would be a false claim.

---

## Part 13. Sections → Composition (HAS-A)

- **Say:** Look at `main`. It holds the array, it loops, it finds the topper. That is not `main`'s job.
- **Say:** New requirement: *"The college has sections, like CSE-A. A section keeps its members, shows them, and shows its topper."*
- **Ask:** Is a Section a CollegeMember? Can we write `Section extends CollegeMember`?
- **Expected:** No. A section is not a person.
- **Ask:** Then what is the relation?
- **Expected:** A section **has** members.

**Board:**

```text
Section IS-A CollegeMember     ✘  (false sentence → no extends)
Section HAS-A CollegeMember[]  ✔  (composition: a field)
```

**Board:**

| IS-A → inheritance | HAS-A → composition |
| --- | --- |
| Student IS-A CollegeMember | Section HAS-A CollegeMember[] |
| ResearchStudent IS-A Student | Student HAS-A marks[] |
| CardPayment IS-A Payment | |

**Type → Section.java**

```java
public class Section {
    private String name;
    private CollegeMember[] members = new CollegeMember[60];
    private int size = 0;

    public Section(String name) {
        this.name = name;
    }

    public void add(CollegeMember m) {
        members[size] = m;
        size++;
    }

    public void showAll() {
        System.out.println("----- " + name + " -----");
        for (int i = 0; i < size; i++) {
            members[i].showDetails();
        }
    }

    public void showTopper() {
        CollegeMember topper = null;
        int best = -1;
        for (int i = 0; i < size; i++) {
            if (members[i] instanceof Gradable g && g.total() > best) {
                best = g.total();
                topper = members[i];
            }
        }
        System.out.println("Topper of " + name + ": " + topper.getName() + "  Total: " + best);
    }
}
```

- **Ask:** `add(CollegeMember m)` — what can we pass?
- **Expected:** Any member: Student, ResearchStudent, Teacher, and any type added later.

**Type → PortalApp.java** (`main` is now short)

```java
public class PortalApp {
    public static void main(String[] args) {
        Section section = new Section("CSE-A");

        Student hita = new Student(101, "Hita");
        int[] hitaMarks = {85, 92, 78, 88, 95};
        hita.setMark(hitaMarks);
        hita.addPayment(120000);

        ResearchStudent sakshi = new ResearchStudent(102, "Sakshi", "Cloud Computing", "Mr. Verma");
        int[] sakshiMarks = {45, 52, 60, 42, 45};
        sakshi.setMark(sakshiMarks);
        sakshi.addPayment(50000);

        Student somya = new Student(103, "Somya");
        int[] somyaMarks = {91, 95, 89, 94, 96};
        somya.setMark(somyaMarks);

        Teacher verma = new Teacher(104, "Verma", "Java", 60000);

        section.add(hita);
        section.add(sakshi);
        section.add(somya);
        section.add(verma);

        section.showAll();
        section.showTopper();

        FeeCounter.collect(sakshi, new CardPayment(), 10000, 3);
        FeeCounter.collect(somya, new NetBankingPayment(), 5000, 0);
    }
}
```

**Run:**

```text
----- CSE-A -----
101  Hita  [Student]  Total: 438  %: 87.6  Balance: 0.0  PASS, FEE CLEARED
102  Sakshi  [Research Student]  Total: 244  %: 48.8  Balance: 100000.0  FAIL, FEE DUE
      Topic: Cloud Computing  Guide: Mr. Verma
103  Somya  [Student]  Total: 465  %: 93.0  Balance: 120000.0  PASS, FEE DUE
104  Verma  [Teacher]  Subject: Java  Salary: 60000.0
Topper of CSE-A: Somya  Total: 465
Swipe the card and enter card PIN
Rs 10150.0 paid by card
------ VGU College Fee Receipt ------
Fee paid  : 10000.0
Late fine : 150.0
Mode      : Card
Balance   : 90000.0
-------------------------------------
Log in to net banking and enter the OTP
Rs 5000.0 paid by net banking
------ VGU College Fee Receipt ------
Fee paid  : 5000.0
Late fine : 0.0
Mode      : Net Banking
Balance   : 115000.0
-------------------------------------
```

---

## Part 14. Find a Member by Id or by Name → Overloading

- **Say:** At the fee counter, Sakshi gives her id: 102.
- **Say:** Mr. Verma walks in and only says his name.
- **Ask:** Should we write `findById()` and `findByName()`?
- **Expected:** We can, but it is the same job: find a member.
- **Say:** Java lets us use the **same name** with **different parameters**. That is **overloading**.
- **Say:** Remember Part 1? `setMark(int, int)` and `setMark(int[])`. We already used overloading!

**Board:**

```text
Overloading → same name, different parameters → chosen at COMPILE time
Overriding  → same name, same parameters, in child → chosen at RUN time
```

**Type → Section.java** (two `find` methods added)

```java
public class Section {
    private String name;
    private CollegeMember[] members = new CollegeMember[60];
    private int size = 0;

    public Section(String name) {
        this.name = name;
    }

    public void add(CollegeMember m) {
        members[size] = m;
        size++;
    }

    public CollegeMember find(int id) {
        for (int i = 0; i < size; i++) {
            if (members[i].getId() == id) {
                return members[i];
            }
        }
        return null;
    }

    public CollegeMember find(String memberName) {
        for (int i = 0; i < size; i++) {
            if (members[i].getName().equalsIgnoreCase(memberName)) {
                return members[i];
            }
        }
        return null;
    }

    public void showAll() {
        System.out.println("----- " + name + " -----");
        for (int i = 0; i < size; i++) {
            members[i].showDetails();
        }
    }

    public void showTopper() {
        CollegeMember topper = null;
        int best = -1;
        for (int i = 0; i < size; i++) {
            if (members[i] instanceof Gradable g && g.total() > best) {
                best = g.total();
                topper = members[i];
            }
        }
        System.out.println("Topper of " + name + ": " + topper.getName() + "  Total: " + best);
    }
}
```

- **Say:** Now the full real-life flow. Everything comes together here. Go slowly.

**Type → PortalApp.java**

```java
public class PortalApp {
    public static void main(String[] args) {
        Section section = new Section("CSE-A");

        Student hita = new Student(101, "Hita");
        int[] hitaMarks = {85, 92, 78, 88, 95};
        hita.setMark(hitaMarks);
        hita.addPayment(120000);

        ResearchStudent sakshi = new ResearchStudent(102, "Sakshi", "Cloud Computing", "Mr. Verma");
        int[] sakshiMarks = {45, 52, 60, 42, 45};
        sakshi.setMark(sakshiMarks);
        sakshi.addPayment(50000);

        Student somya = new Student(103, "Somya");
        Teacher verma = new Teacher(104, "Verma", "Java", 60000);

        section.add(hita);
        section.add(sakshi);
        section.add(somya);
        section.add(verma);

        // exam cell sends Somya's marks with her id
        int[] somyaMarks = {91, 95, 89, 94, 96};
        CollegeMember m = section.find(103);
        if (m instanceof Student s) {
            s.setMark(somyaMarks);
        }

        section.showAll();
        section.showTopper();

        // Sakshi comes to the fee counter and gives her id
        CollegeMember payer = section.find(102);
        if (payer instanceof FeePayer f) {
            FeeCounter.collect(f, new CardPayment(), 10000, 3);
        }

        // Mr. Verma comes and gives only his name
        CollegeMember visitor = section.find("Verma");
        if (visitor instanceof FeePayer f) {
            FeeCounter.collect(f, new CashPayment(), 1000, 0);
        } else {
            System.out.println(visitor.getName() + " is a " + visitor.role() + ", no fee to pay");
        }
    }
}
```

**Board** — trace Sakshi's payment with the class, line by line:

```text
section.find(102)            → overloading picks find(int)
CollegeMember payer          → reference type CollegeMember, real object ResearchStudent
payer instanceof FeePayer f  → capability check + downcast
FeePayer.lateFine(3)         → static interface method → 150
mode.pay(10150)              → dynamic dispatch → CardPayment.pay()
payer.addPayment(10000)      → FeeCounter never knew it was Sakshi
payer.balance()              → default method → uses ResearchStudent.fee() = 150000
```

**Run:**

```text
----- CSE-A -----
101  Hita  [Student]  Total: 438  %: 87.6  Balance: 0.0  PASS, FEE CLEARED
102  Sakshi  [Research Student]  Total: 244  %: 48.8  Balance: 100000.0  FAIL, FEE DUE
      Topic: Cloud Computing  Guide: Mr. Verma
103  Somya  [Student]  Total: 465  %: 93.0  Balance: 120000.0  PASS, FEE DUE
104  Verma  [Teacher]  Subject: Java  Salary: 60000.0
Topper of CSE-A: Somya  Total: 465
Swipe the card and enter card PIN
Rs 10150.0 paid by card
------ VGU College Fee Receipt ------
Fee paid  : 10000.0
Late fine : 150.0
Mode      : Card
Balance   : 90000.0
-------------------------------------
Verma is a Teacher, no fee to pay
```

---

## Part 15. Printing and Comparing Objects → `toString()`, `equals()`, `hashCode()`

### `toString()`

- **Say:** Let me just print Hita.

**Board:**

```java
System.out.println(hita);
```

```text
Student@1b6d3586        ← the number is different on every computer
```

- **Ask:** Is this useful to the college?
- **Expected:** No. Class name and a hash number.
- **Say:** `println` calls `toString()`. The default one comes from `Object`. We override it once, in `CollegeMember`, for everyone.
- **Ask:** Inside `toString()` I call `role()`. For Sakshi, which `role()` runs?
- **Expected:** ResearchStudent's. Dynamic dispatch again.

### `equals()`

- **Say:** New requirement: *"Don't add the same member twice."*

**Board:**

```java
Student copy = new Student(101, "Hita");
copy == hita        → ?
```

- **Ask:** Is `copy == hita` true?
- **Expected:** False. Two different objects in memory.
- **Ask:** But for the college, is it the same person?
- **Expected:** Yes. Same id 101.
- **Say:** `==` compares **references** — memory addresses.
- **Say:** `equals()` is **business equality** — we decide the rule. Our rule: **same id = same member**.

### `hashCode()`

- **Say:** Java has a rule: if `a.equals(b)` is true, then `a.hashCode()` must equal `b.hashCode()`.
- **Say:** `HashSet` and `HashMap` (Module 4) depend on this. So when you override `equals()`, always override `hashCode()`.

**Type → CollegeMember.java**

```java
public abstract class CollegeMember {
    private int id;
    private String name;

    public CollegeMember(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract String role();

    public abstract void showDetails();

    @Override
    public String toString() {
        return id + "  " + name + "  [" + role() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CollegeMember)) {
            return false;
        }
        CollegeMember other = (CollegeMember) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
```

- **Say:** Now `showDetails()` can use `this` instead of `getId() + getName() + role()`.

**Type → Student.java** (only `showDetails()` changed)

```java
public class Student extends CollegeMember implements Gradable, FeePayer {
    private int[] marks = new int[5];
    private double paid;

    public Student(int id, String name) {
        super(id, name);
    }

    public void setMark(int subject, int mark) {
        if (mark < 0 || mark > 100) {
            System.out.println("Invalid mark: " + mark);
        } else {
            marks[subject] = mark;
        }
    }

    public void setMark(int[] allMarks) {
        for (int i = 0; i < marks.length; i++) {
            setMark(i, allMarks[i]);
        }
    }

    @Override
    public int total() {
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
        return sum;
    }

    @Override
    public double percentage() {
        return total() / 5.0;
    }

    @Override
    public boolean isPassed() {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < PASS_MARK) {
                return false;
            }
        }
        return true;
    }

    @Override
    public double fee() {
        return 120000;
    }

    @Override
    public double paidAmount() {
        return paid;
    }

    @Override
    public void addPayment(double amount) {
        paid += amount;
    }

    @Override
    public String status() {
        return Gradable.super.status() + ", " + FeePayer.super.status();
    }

    @Override
    public String role() {
        return "Student";
    }

    @Override
    public void showDetails() {
        System.out.println(this + "  Total: " + total() + "  %: " + percentage()
                + "  Balance: " + balance() + "  " + status());
    }
}
```

**Type → Teacher.java** (only `showDetails()` changed)

```java
public class Teacher extends CollegeMember {
    private String subject;
    private double salary;

    public Teacher(int id, String name, String subject, double salary) {
        super(id, name);
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
```

- **Say:** The receipt can now print who paid.

**Type → Payment.java** (one line added in the receipt)

```java
public abstract class Payment {
    private String modeName;

    public Payment(String modeName) {
        this.modeName = modeName;
    }

    public abstract void pay(double amount);

    public void printReceipt(FeePayer payer, double amount, double fine) {
        System.out.println("------ VGU College Fee Receipt ------");
        System.out.println("Student   : " + payer);
        System.out.println("Fee paid  : " + amount);
        System.out.println("Late fine : " + fine);
        System.out.println("Mode      : " + modeName);
        System.out.println("Balance   : " + payer.balance());
        System.out.println("-------------------------------------");
    }
}
```

- **Say:** And the section uses `equals()` to stop duplicates.

**Type → Section.java** (`add()` changed)

```java
public class Section {
    private String name;
    private CollegeMember[] members = new CollegeMember[60];
    private int size = 0;

    public Section(String name) {
        this.name = name;
    }

    public void add(CollegeMember m) {
        for (int i = 0; i < size; i++) {
            if (members[i].equals(m)) {
                System.out.println(m.getName() + " is already in " + name);
                return;
            }
        }
        members[size] = m;
        size++;
    }

    public CollegeMember find(int id) {
        for (int i = 0; i < size; i++) {
            if (members[i].getId() == id) {
                return members[i];
            }
        }
        return null;
    }

    public CollegeMember find(String memberName) {
        for (int i = 0; i < size; i++) {
            if (members[i].getName().equalsIgnoreCase(memberName)) {
                return members[i];
            }
        }
        return null;
    }

    public void showAll() {
        System.out.println("----- " + name + " -----");
        for (int i = 0; i < size; i++) {
            members[i].showDetails();
        }
    }

    public void showTopper() {
        CollegeMember topper = null;
        int best = -1;
        for (int i = 0; i < size; i++) {
            if (members[i] instanceof Gradable g && g.total() > best) {
                best = g.total();
                topper = members[i];
            }
        }
        System.out.println("Topper of " + name + ": " + topper + "  Total: " + best);
    }
}
```

**Type → PortalApp.java** (new lines after the four `add` calls)

```java
public class PortalApp {
    public static void main(String[] args) {
        Section section = new Section("CSE-A");

        Student hita = new Student(101, "Hita");
        int[] hitaMarks = {85, 92, 78, 88, 95};
        hita.setMark(hitaMarks);
        hita.addPayment(120000);

        ResearchStudent sakshi = new ResearchStudent(102, "Sakshi", "Cloud Computing", "Mr. Verma");
        int[] sakshiMarks = {45, 52, 60, 42, 45};
        sakshi.setMark(sakshiMarks);
        sakshi.addPayment(50000);

        Student somya = new Student(103, "Somya");
        Teacher verma = new Teacher(104, "Verma", "Java", 60000);

        section.add(hita);
        section.add(sakshi);
        section.add(somya);
        section.add(verma);

        // toString(), == and equals()
        System.out.println(hita);
        Student copy = new Student(101, "Hita");
        System.out.println("copy == hita      : " + (copy == hita));
        System.out.println("copy.equals(hita) : " + copy.equals(hita));
        section.add(copy);

        // exam cell sends Somya's marks with her id
        int[] somyaMarks = {91, 95, 89, 94, 96};
        CollegeMember m = section.find(103);
        if (m instanceof Student s) {
            s.setMark(somyaMarks);
        }

        section.showAll();
        section.showTopper();

        // Sakshi comes to the fee counter and gives her id
        CollegeMember payer = section.find(102);
        if (payer instanceof FeePayer f) {
            FeeCounter.collect(f, new CardPayment(), 10000, 3);
        }

        // Mr. Verma comes and gives only his name
        CollegeMember visitor = section.find("Verma");
        if (visitor instanceof FeePayer f) {
            FeeCounter.collect(f, new CashPayment(), 1000, 0);
        } else {
            System.out.println(visitor.getName() + " is a " + visitor.role() + ", no fee to pay");
        }
    }
}
```

**Run:**

```text
101  Hita  [Student]
copy == hita      : false
copy.equals(hita) : true
Hita is already in CSE-A
----- CSE-A -----
101  Hita  [Student]  Total: 438  %: 87.6  Balance: 0.0  PASS, FEE CLEARED
102  Sakshi  [Research Student]  Total: 244  %: 48.8  Balance: 100000.0  FAIL, FEE DUE
      Topic: Cloud Computing  Guide: Mr. Verma
103  Somya  [Student]  Total: 465  %: 93.0  Balance: 120000.0  PASS, FEE DUE
104  Verma  [Teacher]  Subject: Java  Salary: 60000.0
Topper of CSE-A: 103  Somya  [Student]  Total: 465
Swipe the card and enter card PIN
Rs 10150.0 paid by card
------ VGU College Fee Receipt ------
Student   : 102  Sakshi  [Research Student]
Fee paid  : 10000.0
Late fine : 150.0
Mode      : Card
Balance   : 90000.0
-------------------------------------
Verma is a Teacher, no fee to pay
```

- **Say:** `==` said false. `equals()` said true. And the section refused the duplicate.

---

## Part 16. Stop Changes on Purpose → `final`

- **Ask:** After admission, should Hita's id 101 ever change?
- **Expected:** Never.
- **Say:** Then let the compiler guarantee it: **`final` field**.
- **Ask:** Should any child class change how the id is read?
- **Expected:** No → **`final` method** `getId()`.
- **Ask:** The college receipt format. Should Card or UPI print a different receipt?
- **Expected:** No → `printReceipt()` is **final**.
- **Ask:** Should anyone write `class PhdStudent extends ResearchStudent`?
- **Say:** In our college, research is the last level. → **`final` class**.

**Type → CollegeMember.java** (`final` id and `final getId()`)

```java
public abstract class CollegeMember {
    private final int id;
    private String name;

    public CollegeMember(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public final int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract String role();

    public abstract void showDetails();

    @Override
    public String toString() {
        return id + "  " + name + "  [" + role() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CollegeMember)) {
            return false;
        }
        CollegeMember other = (CollegeMember) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
```

**Type → Payment.java** (`final printReceipt()`)

```java
public abstract class Payment {
    private String modeName;

    public Payment(String modeName) {
        this.modeName = modeName;
    }

    public abstract void pay(double amount);

    public final void printReceipt(FeePayer payer, double amount, double fine) {
        System.out.println("------ VGU College Fee Receipt ------");
        System.out.println("Student   : " + payer);
        System.out.println("Fee paid  : " + amount);
        System.out.println("Late fine : " + fine);
        System.out.println("Mode      : " + modeName);
        System.out.println("Balance   : " + payer.balance());
        System.out.println("-------------------------------------");
    }
}
```

**Type → ResearchStudent.java** (`final class`)

```java
public final class ResearchStudent extends Student {
    private String researchTopic;
    private String guide;

    public ResearchStudent(int id, String name, String researchTopic, String guide) {
        super(id, name);
        this.researchTopic = researchTopic;
        this.guide = guide;
    }

    @Override
    public double fee() {
        return 150000;
    }

    @Override
    public boolean isPassed() {
        return super.isPassed() && percentage() >= 50;
    }

    @Override
    public String role() {
        return "Research Student";
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("      Topic: " + researchTopic + "  Guide: " + guide);
    }
}
```

**Show errors** (one at a time):

```java
// in CollegeMember
public void setId(int id) {
    this.id = id;
}
```

```text
error: cannot assign a value to final variable id
```

```java
// new file PhdStudent.java
public class PhdStudent extends ResearchStudent { ... }
```

```text
error: cannot inherit from final ResearchStudent
```

- **Say:** `final` is not automatically "better". Use it only when the design **needs** to stop a change.

**Run:** (`PortalApp` is unchanged — same output as Part 15)

```text
101  Hita  [Student]
copy == hita      : false
copy.equals(hita) : true
Hita is already in CSE-A
----- CSE-A -----
101  Hita  [Student]  Total: 438  %: 87.6  Balance: 0.0  PASS, FEE CLEARED
102  Sakshi  [Research Student]  Total: 244  %: 48.8  Balance: 100000.0  FAIL, FEE DUE
      Topic: Cloud Computing  Guide: Mr. Verma
103  Somya  [Student]  Total: 465  %: 93.0  Balance: 120000.0  PASS, FEE DUE
104  Verma  [Teacher]  Subject: Java  Salary: 60000.0
Topper of CSE-A: 103  Somya  [Student]  Total: 465
Swipe the card and enter card PIN
Rs 10150.0 paid by card
------ VGU College Fee Receipt ------
Student   : 102  Sakshi  [Research Student]
Fee paid  : 10000.0
Late fine : 150.0
Mode      : Card
Balance   : 90000.0
-------------------------------------
Verma is a Teacher, no fee to pay
```

---

# Wrap-Up (last 15 minutes)

## 1. Draw the final design together

- **Say:** Close your laptops. Let us draw what we built — from memory.

**Board:**

```text
                CollegeMember  <<abstract>>
                /            \
           Student          Teacher
     implements Gradable,
                FeePayer
               |
        ResearchStudent  <<final>>


                    Payment  <<abstract>>
        /         |          |            \
  UpiPayment  CardPayment  CashPayment  NetBankingPayment


Section ◇──── CollegeMember[]            (HAS-A)

FeeCounter.collect(FeePayer, Payment, amount, daysLate)
```

## 2. The thinking steps

- **Say:** You should not leave today thinking *"Java has many OOP keywords."*
- **Say:** Leave with these questions. Ask them every time you design a program.

**Board:**

```text
What objects exist?
      ↓
What is common? What is different?
      ↓
IS-A or HAS-A?
      ↓
Identity (class) or capability (interface)?
      ↓
Who should own this behaviour?
      ↓
Does the caller need the real type?
      ↓
Can polymorphism remove this if-else?
      ↓
Can abstraction hide the details?
```

## 3. Rapid-fire situations

- **Say:** I give a situation. You shout the concept.

| I say | They answer |
| --- | --- |
| Student and Teacher both have id and name | Inheritance |
| Nobody is "just a member" | Abstract class |
| Only students have marks | Interface `Gradable` |
| Only some members pay fees | Interface `FeePayer` |
| Every fee payer works out balance the same way | `default` method |
| Late fine does not depend on any student | `static` method |
| Two interfaces give the same default `status()` | Override, `X.super.status()` |
| Same `showDetails()` call, different output | Overriding + dynamic dispatch |
| Only Gradable members can be topper | `instanceof` |
| `find()` returns CollegeMember but we need `setMark()` | Downcasting |
| A research student is a special student | Multilevel inheritance |
| UPI, Card, Cash pay differently | Abstract class + overriding |
| FeeCounter must not know the payment type | Abstraction |
| A section contains members | Composition (HAS-A) |
| Find by id or by name | Overloading |
| `println(hita)` prints `Student@1b6d3586` | `toString()` |
| Two objects with id 101 are the same person | `equals()` + `hashCode()` |
| An id must never change | `final` field |

## 4. The one sentence to remember

- **Say:** Write this in your notebook:

> **Don't choose an OOP feature to show the feature. Choose it because the problem needs it.**

## 5. Homework

1. Add `WalletPayment extends Payment`. Count which files you changed. Did `FeeCounter` change?
2. Add `Counsellor extends CollegeMember` (room number). Did `Section.showAll()` change?
3. Add a `default` method `grade()` in `Gradable` (90 = A, 75 = B, 60 = C, 40 = D, else F). Which classes get it?
4. Remove `status()` from `Student`. Read the error. Fix it using only `FeePayer.super.status()`.
5. Why is `PgStudent extends UgStudent` a bad design? Write the IS-A sentence and check it.
6. Try `hita.lateFine(2)` and `class PhdStudent extends ResearchStudent`. Explain both errors.

---

# Appendix — Final Code of All Files

For your own preparation. This is the program at the end of Part 16.

### CollegeMember.java

```java
public abstract class CollegeMember {
    private final int id;
    private String name;

    public CollegeMember(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public final int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract String role();

    public abstract void showDetails();

    @Override
    public String toString() {
        return id + "  " + name + "  [" + role() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CollegeMember)) {
            return false;
        }
        CollegeMember other = (CollegeMember) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
```

### Student.java

```java
public class Student extends CollegeMember implements Gradable, FeePayer {
    private int[] marks = new int[5];
    private double paid;

    public Student(int id, String name) {
        super(id, name);
    }

    public void setMark(int subject, int mark) {
        if (mark < 0 || mark > 100) {
            System.out.println("Invalid mark: " + mark);
        } else {
            marks[subject] = mark;
        }
    }

    public void setMark(int[] allMarks) {
        for (int i = 0; i < marks.length; i++) {
            setMark(i, allMarks[i]);
        }
    }

    @Override
    public int total() {
        int sum = 0;
        for (int i = 0; i < marks.length; i++) {
            sum += marks[i];
        }
        return sum;
    }

    @Override
    public double percentage() {
        return total() / 5.0;
    }

    @Override
    public boolean isPassed() {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < PASS_MARK) {
                return false;
            }
        }
        return true;
    }

    @Override
    public double fee() {
        return 120000;
    }

    @Override
    public double paidAmount() {
        return paid;
    }

    @Override
    public void addPayment(double amount) {
        paid += amount;
    }

    @Override
    public String status() {
        return Gradable.super.status() + ", " + FeePayer.super.status();
    }

    @Override
    public String role() {
        return "Student";
    }

    @Override
    public void showDetails() {
        System.out.println(this + "  Total: " + total() + "  %: " + percentage()
                + "  Balance: " + balance() + "  " + status());
    }
}
```

### ResearchStudent.java

```java
public final class ResearchStudent extends Student {
    private String researchTopic;
    private String guide;

    public ResearchStudent(int id, String name, String researchTopic, String guide) {
        super(id, name);
        this.researchTopic = researchTopic;
        this.guide = guide;
    }

    @Override
    public double fee() {
        return 150000;
    }

    @Override
    public boolean isPassed() {
        return super.isPassed() && percentage() >= 50;
    }

    @Override
    public String role() {
        return "Research Student";
    }

    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("      Topic: " + researchTopic + "  Guide: " + guide);
    }
}
```

### Teacher.java

```java
public class Teacher extends CollegeMember {
    private String subject;
    private double salary;

    public Teacher(int id, String name, String subject, double salary) {
        super(id, name);
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
```

### Gradable.java

```java
public interface Gradable {
    int PASS_MARK = 40;

    int total();
    double percentage();
    boolean isPassed();

    default String status() {
        return isPassed() ? "PASS" : "FAIL";
    }
}
```

### FeePayer.java

```java
public interface FeePayer {
    double FINE_PER_DAY = 50;

    double fee();
    double paidAmount();
    void addPayment(double amount);

    default double balance() {
        return fee() - paidAmount();
    }

    default String status() {
        return balance() <= 0 ? "FEE CLEARED" : "FEE DUE";
    }

    static double lateFine(int days) {
        return days * FINE_PER_DAY;
    }
}
```

### Payment.java

```java
public abstract class Payment {
    private String modeName;

    public Payment(String modeName) {
        this.modeName = modeName;
    }

    public abstract void pay(double amount);

    public final void printReceipt(FeePayer payer, double amount, double fine) {
        System.out.println("------ VGU College Fee Receipt ------");
        System.out.println("Student   : " + payer);
        System.out.println("Fee paid  : " + amount);
        System.out.println("Late fine : " + fine);
        System.out.println("Mode      : " + modeName);
        System.out.println("Balance   : " + payer.balance());
        System.out.println("-------------------------------------");
    }
}
```

### UpiPayment.java

```java
public class UpiPayment extends Payment {
    public UpiPayment() {
        super("UPI");
    }

    @Override
    public void pay(double amount) {
        System.out.println("Scan the QR code and enter UPI PIN");
        System.out.println("Rs " + amount + " paid by UPI");
    }
}
```

### CardPayment.java

```java
public class CardPayment extends Payment {
    public CardPayment() {
        super("Card");
    }

    @Override
    public void pay(double amount) {
        System.out.println("Swipe the card and enter card PIN");
        System.out.println("Rs " + amount + " paid by card");
    }
}
```

### CashPayment.java

```java
public class CashPayment extends Payment {
    public CashPayment() {
        super("Cash");
    }

    @Override
    public void pay(double amount) {
        System.out.println("Give cash at the counter");
        System.out.println("Cashier counts Rs " + amount);
    }
}
```

### NetBankingPayment.java

```java
public class NetBankingPayment extends Payment {
    public NetBankingPayment() {
        super("Net Banking");
    }

    @Override
    public void pay(double amount) {
        System.out.println("Log in to net banking and enter the OTP");
        System.out.println("Rs " + amount + " paid by net banking");
    }
}
```

### FeeCounter.java

```java
public class FeeCounter {
    public static void collect(FeePayer payer, Payment mode, double amount, int daysLate) {
        double fine = FeePayer.lateFine(daysLate);
        mode.pay(amount + fine);
        payer.addPayment(amount);
        mode.printReceipt(payer, amount, fine);
    }
}
```

### Section.java

```java
public class Section {
    private String name;
    private CollegeMember[] members = new CollegeMember[60];
    private int size = 0;

    public Section(String name) {
        this.name = name;
    }

    public void add(CollegeMember m) {
        for (int i = 0; i < size; i++) {
            if (members[i].equals(m)) {
                System.out.println(m.getName() + " is already in " + name);
                return;
            }
        }
        members[size] = m;
        size++;
    }

    public CollegeMember find(int id) {
        for (int i = 0; i < size; i++) {
            if (members[i].getId() == id) {
                return members[i];
            }
        }
        return null;
    }

    public CollegeMember find(String memberName) {
        for (int i = 0; i < size; i++) {
            if (members[i].getName().equalsIgnoreCase(memberName)) {
                return members[i];
            }
        }
        return null;
    }

    public void showAll() {
        System.out.println("----- " + name + " -----");
        for (int i = 0; i < size; i++) {
            members[i].showDetails();
        }
    }

    public void showTopper() {
        CollegeMember topper = null;
        int best = -1;
        for (int i = 0; i < size; i++) {
            if (members[i] instanceof Gradable g && g.total() > best) {
                best = g.total();
                topper = members[i];
            }
        }
        System.out.println("Topper of " + name + ": " + topper + "  Total: " + best);
    }
}
```

### PortalApp.java

```java
public class PortalApp {
    public static void main(String[] args) {
        Section section = new Section("CSE-A");

        Student hita = new Student(101, "Hita");
        int[] hitaMarks = {85, 92, 78, 88, 95};
        hita.setMark(hitaMarks);
        hita.addPayment(120000);

        ResearchStudent sakshi = new ResearchStudent(102, "Sakshi", "Cloud Computing", "Mr. Verma");
        int[] sakshiMarks = {45, 52, 60, 42, 45};
        sakshi.setMark(sakshiMarks);
        sakshi.addPayment(50000);

        Student somya = new Student(103, "Somya");
        Teacher verma = new Teacher(104, "Verma", "Java", 60000);

        section.add(hita);
        section.add(sakshi);
        section.add(somya);
        section.add(verma);

        // toString(), == and equals()
        System.out.println(hita);
        Student copy = new Student(101, "Hita");
        System.out.println("copy == hita      : " + (copy == hita));
        System.out.println("copy.equals(hita) : " + copy.equals(hita));
        section.add(copy);

        // exam cell sends Somya's marks with her id
        int[] somyaMarks = {91, 95, 89, 94, 96};
        CollegeMember m = section.find(103);
        if (m instanceof Student s) {
            s.setMark(somyaMarks);
        }

        section.showAll();
        section.showTopper();

        // Sakshi comes to the fee counter and gives her id
        CollegeMember payer = section.find(102);
        if (payer instanceof FeePayer f) {
            FeeCounter.collect(f, new CardPayment(), 10000, 3);
        }

        // Mr. Verma comes and gives only his name
        CollegeMember visitor = section.find("Verma");
        if (visitor instanceof FeePayer f) {
            FeeCounter.collect(f, new CashPayment(), 1000, 0);
        } else {
            System.out.println(visitor.getName() + " is a " + visitor.role() + ", no fee to pay");
        }
    }
}
```

