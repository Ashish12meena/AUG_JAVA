# Module 2 Final Example: VGU College Portal — Teaching Guide

One program that uses all of Module 2 together. Teach this **after 2.13**.

Students already know each concept on its own. Here they see **why** each one is needed.
The portal grows step by step. At every step:

```text
PROBLEM
   ↓
LIMITED DESIGN
   ↓
WHAT GOES WRONG?
   ↓
NEW REQUIREMENT
   ↓
OOP CONCEPT
   ↓
BETTER DESIGN
   ↓
RUN / DEMONSTRATE
```

The question to keep asking:

> Why do we need this? What problem does it solve? What happens if we design it differently?

**Running story (same all through):**

| Id | Name | Type | Marks | Fee |
| --- | --- | --- | --- | --- |
| 101 | Hita | Student | 85, 92, 78, 88, 95 → 438, PASS | 1,20,000 — fully paid |
| 102 | Sakshi | Research Student (topic Cloud Computing, guide Verma) | 45, 52, 60, 42, 45 → 48.8%, **FAIL** (needs 50%) | 1,50,000 — 50,000 paid |
| 103 | Somya | Student | 91, 95, 89, 94, 96 → 465, topper | 1,20,000 — nothing paid |
| 104 | Mr. Verma | Teacher (Java, salary 60,000) | — | — |

---

# Session 1 — Build the Domain Model

## Step 1. Start With One Student

At first VGU College has only students. One class holds everything (like Module 1):

```java
public class Student {
    int id;
    String name;
    int[] marks = new int[5];
    double paid;

    void setMark(int subject, int mark) { ... }
    int total() { ... }
    double percentage() { ... }
    boolean isPassed() { ... }
    double balance() { return 120000 - paid; }
    void showDetails() { ... }
}
```

This works, until the next requirement comes in.

## Step 2. New Requirement: Teachers → Inheritance

> The college also has teachers.

```java
public class Teacher {
    int id;
    String name;
    String subject;
    double salary;
}
```

Ask:

- Why are `id` and `name` written twice?
- What is **common** to a student and a teacher?
- Can we write the common part once?

**Better design:** move the common part into a parent.

```java
public class CollegeMember {
    private final int id;
    private String name;
}

public class Student extends CollegeMember { ... }   // marks, fee
public class Teacher extends CollegeMember { ... }   // subject, salary
```

```text
Student IS-A CollegeMember   ✔
Teacher IS-A CollegeMember   ✔
```

- Use inheritance when the **IS-A** sentence is true.
- Code reuse is a *benefit*, not the reason. The relationship must make sense first.

Ask: *Why should `CollegeMember` not extend `Student`?*

```text
Student IS-A CollegeMember   ✔ true
CollegeMember IS-A Student   ✘ false (a teacher is a member, not a student)
```

### `super()` — the parent sets its own part

`id` and `name` belong to `CollegeMember`, so the parent sets them:

```java
public Student(String name) {
    super(name);   // calls the CollegeMember constructor
}
```

```text
new Student("Hita")
      ↓
Student constructor
      ↓
super("Hita")
      ↓
CollegeMember constructor → id = 101, name = "Hita"
```

In our code the id is given automatically by a `static` counter (`nextId`), so no two members get the same id.

## Step 3. Make `CollegeMember` Abstract

Ask: *Should this be allowed?*

```java
CollegeMember m = new CollegeMember("Hita");
```

What is Hita? A student? A teacher? `CollegeMember` is a **general idea**, not a real object. So:

```java
public abstract class CollegeMember {
    ...
    public abstract String role();
    public abstract void showDetails();
}
```

- No object of an abstract class: `new CollegeMember(...)` → **compile error** (see the commented line in `PortalApp`).
- An abstract class can still have a **constructor** and **data**; children use them through `super()`.
- Abstract methods: the parent says **what** must exist, the child says **how**.
- A child that forgets `role()` or `showDetails()` does not compile.

### Overriding — same contract, different behaviour

```java
// Student
public void showDetails() {
    System.out.println(this + "  Total: " + total() + ... );
}

// Teacher
public void showDetails() {
    System.out.println(this + "  Subject: " + subject + "  Salary: " + salary);
}
```

Same method, same parameters, different body → **overriding** (`@Override`).

## Step 4. Do NOT Make `PgStudent extends UgStudent`

A tempting design to "show" multilevel inheritance:

```text
CollegeMember
      |
  UgStudent
      |
  PgStudent        ✘ says "a PG student IS-A UG student" — false
```

A PG student is a student, but **not** a UG student. Use the correct base design:

```text
              CollegeMember
              <<abstract>>
              /          \
         Student        Teacher
```

We will add a **genuine** multilevel class later (Step 9: `ResearchStudent`).

---

# Session 2 — Capabilities and Polymorphism

## Step 5. Interfaces — Model Capabilities

Ask:

- Does **every** CollegeMember have marks? → No, a teacher does not.
- Does **every** CollegeMember pay a fee? → No.

So marks and fees must **not** go into `CollegeMember`. They are **capabilities** — something a class *can do*:

```java
public interface Gradable {
    int PASS_MARK = 40;          // public static final
    int total();
    double percentage();
    boolean isPassed();
}

public interface FeePayer {
    double FINE_PER_DAY = 50;
    double fee();
    double paidAmount();
    void addPayment(double amount);
}
```

```java
public class Student extends CollegeMember implements Gradable, FeePayer { ... }
public class Teacher extends CollegeMember { ... }   // implements neither
```

- Java allows only **one** parent class: `class Student extends A, B` → error.
- Java allows **many** interfaces: `implements Gradable, FeePayer` ✔.
- Teacher needs no fake `fee()` returning 0. It just does not implement `FeePayer`.
- Bonus: the **compiler** now stops a fee being taken from a teacher:

```java
FeeCounter.collect(verma, new CashPayment(), 1000, 0);   // ERROR: Teacher is not a FeePayer
```

### Abstract class vs interface (design rule of thumb)

```text
WHAT AM I?        → CollegeMember        → abstract class
WHAT CAN I DO?    → Gradable, FeePayer   → interfaces
```

```text
Student IS-A CollegeMember
Student CAN BE GRADED
Student CAN PAY FEES
```

| Abstract class | Interface |
| --- | --- |
| Common identity / type | Capability / contract |
| `Student IS-A CollegeMember` | `Student CAN BE GRADED` |
| Can hold common data (`id`, `name`) | Only constants, no object data |
| A class extends only **one** | A class implements **many** |

This is a useful heuristic, not an absolute rule for every Java program.

## Step 6. `default` and `static` Methods in an Interface

### `default balance()` — why?

Every fee payer works out balance the **same** way: fee − paid.
Without a default method, every fee-paying class must copy:

```java
public double balance() { return fee() - paidAmount(); }
```

So write it **once**, with a body, in the interface:

```java
default double balance() {
    return fee() - paidAmount();
}

default boolean isFeeCleared() {
    return balance() <= 0;
}
```

- Not in `CollegeMember`: then teachers would get a fee balance too. Wrong.
- Not in `Student`: then every new fee-paying class must copy it.
- Adding a default method to an old interface does **not break** classes that already implement it.

Same idea in `Gradable`: `default char grade()` — one grade rule (90 = A, 75 = B …) for every gradable class.

### `static lateFine()` — why?

Late fine = days × 50. It depends **only on days**, not on which student pays.

```java
static double lateFine(int days) {
    return days * FINE_PER_DAY;
}
```

```java
f.balance();             // object-specific → default (instance) method
FeePayer.lateFine(3);    // no object needed → static method → 150.0
hita.lateFine(3);        // ERROR: static interface method needs the interface name
```

| | `default` method | `static` method |
| --- | --- | --- |
| Needs an object? | Yes: `f.balance()` | No: `FeePayer.lateFine(3)` |
| Uses the object's data? | Yes (`fee()`, `paidAmount()`) | No (only `days`) |
| Can a class override it? | Yes | No |

## Step 7. Two Defaults With the Same Name — the Diamond Problem

Both interfaces want to report a status:

```java
// Gradable
default String status() {
    return isPassed() ? "PASS" : "FAIL";
}

// FeePayer
default String status() {
    return isFeeCleared() ? "FEE CLEARED" : "FEE DUE";
}
```

`Student implements Gradable, FeePayer`. Which `status()` does Student get?

Java does **not** guess. Without an override the class does not compile:

```text
error: types Gradable and FeePayer are incompatible;
  class Student inherits unrelated defaults for status() from types Gradable and FeePayer
```

The class must resolve it. We choose to use **both**:

```java
@Override
public String status() {
    return Gradable.super.status() + ", " + FeePayer.super.status();
}
```

Output: `PASS, FEE DUE`

- `InterfaceName.super.method()` calls one interface's default.
- Default methods do **not** remove ambiguity. Java has rules and makes the class decide.

## Step 8. One Array for Everyone — Polymorphism

`Section` keeps all members in **one** parent-type array:

```java
private CollegeMember[] members = new CollegeMember[60];

section.add(hita);     // Student          → stored as CollegeMember (upcasting)
section.add(sakshi);   // ResearchStudent
section.add(verma);    // Teacher
```

```text
Reference type: CollegeMember
Real objects:   Student, ResearchStudent, Student, Teacher
```

Now:

```java
for (int i = 0; i < size; i++) {
    members[i].showDetails();
}
```

Ask: *`members[i]` is a `CollegeMember`. Whose `showDetails()` runs?*

```text
Student object          → Student.showDetails()
ResearchStudent object  → ResearchStudent.showDetails()
Teacher object          → Teacher.showDetails()
```

The JVM looks at the **real object at run time** → **dynamic method dispatch** → **runtime polymorphism**.

### Why is this useful?

Without polymorphism:

```java
if (m instanceof Student) {
    // student printing
} else if (m instanceof Teacher) {
    // teacher printing
} else if (m instanceof Counsellor) {
    ...
}
```

Every new member type (Counsellor, Librarian, Administrator…) makes this method grow.
With polymorphism the caller writes **one line** and never needs the real type:

```java
members[i].showDetails();
```

### `instanceof` — when it is still right

Polymorphism does **not** mean "never use `instanceof`". Use it when behaviour depends on a **capability** that not everyone has:

```java
// Section.showTopper(): only Gradable members have total()
if (members[i] instanceof Gradable g && g.total() > best) { ... }

// Section.feeReport(): only FeePayers have a fee
if (members[i] instanceof FeePayer f) { ... }
```

- Common behaviour (`showDetails()`) → polymorphism.
- Capability-specific behaviour (`total()`, `fee()`) → `instanceof`.
- Do not use `instanceof` everywhere as a replacement for polymorphism.

### Downcasting

Menu **4. Enter marks**: `find()` returns a `CollegeMember`, but `setMark()` exists only in `Student`.

```java
CollegeMember m = section.find(101);
if (m instanceof Student s) {     // narrow CollegeMember → Student
    s.setMark(0, 85);
}
```

```text
CollegeMember reference ──→ Student object
```

Narrowing a general reference to a specific type = **downcasting**. Check with `instanceof` first.

## Step 9. Genuine Multilevel Inheritance — `ResearchStudent`

> New requirement: VGU College admits research students. They have a topic and a guide.

```text
CollegeMember
      |
   Student
      |
ResearchStudent      ✔ "a research student IS-A student" — true
```

```java
public final class ResearchStudent extends Student {
    private String researchTopic;
    private String guide;

    public ResearchStudent(String name, String researchTopic, String guide) {
        super(name);   // Student() → CollegeMember()
        ...
    }

    @Override
    public double fee() { return 150000; }                 // different fee

    @Override
    public boolean isPassed() {
        return super.isPassed() && percentage() >= 50;     // parent rule + 50%
    }

    @Override
    public String role() { return "Research Student"; }

    @Override
    public void showDetails() {
        super.showDetails();                               // reuse parent printing
        System.out.println("      Topic: " + researchTopic + "  Guide: " + guide);
    }
}
```

- Marks, `total()`, `grade()`, `balance()`, `status()` are all **inherited** — nothing copied.
- `balance()` (in `FeePayer`) calls `fee()` → for Sakshi it automatically uses 1,50,000.
- Sakshi: every subject ≥ 40 (passes the Student rule) but 48.8% → **FAIL** as a research student.
- Now multilevel inheritance has a **real** domain reason.

---

# Session 3 — Abstraction in a Real System

## Step 10. Payments — Abstract `Payment`

> New requirement: students pay fees by UPI, Card or Cash.

**Poor design:**

```java
void pay(String mode, double amount) {
    if (mode.equals("UPI")) {
        // UPI
    } else if (mode.equals("CARD")) {
        // Card
    } else if (mode.equals("CASH")) {
        // Cash
    }
}
```

Ask: *What happens when we add Net Banking? Wallet?* → Modify this method again and again.

**Better design:** every mode IS-A `Payment`.

```java
public abstract class Payment {
    private static int nextReceipt = 1;
    private String modeName;

    public abstract void pay(double amount);                       // HOW: each mode decides

    public final void printReceipt(FeePayer payer, double amount, double fine) {
        // same receipt for every mode
    }
}
```

| Class | `pay()` does |
| --- | --- |
| `UpiPayment` | Scan QR, enter UPI PIN |
| `CardPayment` | Swipe, PIN, 1% card charge |
| `CashPayment` | Cashier counts the cash |

- Why abstract class (not interface)? All modes share **data** (`modeName`, receipt counter) and the **same receipt code**.
- `printReceipt()` is `final`: no mode can change the college receipt format.

```java
Payment mode = new CardPayment("1234567812345678");
mode.pay(10000);    // reference Payment, object CardPayment → CardPayment.pay()
```

## Step 11. `FeeCounter` — the Most Important Part

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

Ask:

- *Does `FeeCounter` know whether the payer is a Student or a Research Student?* → No. It knows only `FeePayer`.
- *Does it know whether payment is UPI, Card or Cash?* → No. It knows only `Payment`.

**WHAT vs HOW:**

```text
FeeCounter knows WHAT:        It does NOT know HOW:
  payer.addPayment()            UPI  → QR / PIN
  payer.balance()               Card → swipe / bank charge
  mode.pay()                    Cash → cashier counts
  mode.printReceipt()
```

> Good abstraction hides details from code that does not need them.
> `FeeCounter` depends on **abstractions** (`FeePayer`, `Payment`), not concrete classes.

## Step 12. Add Net Banking — Live in Class

> New requirement: the college adds Net Banking.

Write **one new class**:

```java
public class NetBankingPayment extends Payment {
    private String bankName;

    public NetBankingPayment(String bankName) {
        super("Net Banking");
        this.bankName = bankName;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Log in to " + bankName + " net banking");
        System.out.println("Enter the OTP sent to your mobile");
        System.out.println("Rs " + amount + " paid by net banking");
    }
}
```

Then add one option in `chooseMode()` in `PortalApp`:

```java
} else if (mode == 4) {
    System.out.print("Bank name: ");
    return new NetBankingPayment(sc.next());
}
```

Ask: *Did `FeeCounter` change?* → **No.**

Be accurate:

- Some code **does** change: `chooseMode()`, which turns the user's choice into an object.
- The **core fee logic** (`FeeCounter`) does not depend on the concrete payment type, so it does not change.

## Step 13. `Section` — Composition (HAS-A)

```java
public class Section {
    private String name;
    private CollegeMember[] members = new CollegeMember[60];
}
```

Ask: *Is a Section a CollegeMember?* → No. A section **contains** members.

```text
Section HAS-A CollegeMember[]      ✔
class Section extends CollegeMember   ✘ says "Section IS-A CollegeMember"
```

**IS-A vs HAS-A in this program:**

| IS-A (inheritance) | HAS-A (composition) |
| --- | --- |
| Student IS-A CollegeMember | Section HAS-A CollegeMember[] |
| Teacher IS-A CollegeMember | Student HAS-A marks[] |
| ResearchStudent IS-A Student | CardPayment HAS-A card number |
| CardPayment IS-A Payment | |

- Inheritance → genuine specialisation.
- Composition → one object contains or uses another.

## Step 14. Method Overloading

Find a member **by id or by name** — same job, different input:

```java
public CollegeMember find(int id) { ... }
public CollegeMember find(String memberName) { ... }

section.find(101);
section.find("Hita");
```

Also in `Student`:

```java
public void setMark(int subject, int mark) { ... }   // one mark
public void setMark(int[] allMarks) { ... }          // all 5 marks
```

```text
Overloading → same name, different parameters → decided at COMPILE time
Overriding  → same name, same parameters, child class → decided at RUN time
```

## Step 15. `toString()`, `equals()`, `hashCode()`

### `toString()` — solve a real problem

```java
System.out.println(hita);   // without toString(): Student@6d311334   — useless
```

```java
@Override
public String toString() {
    return id + "  " + name + "  [" + role() + "]";
}
```

Now: `101  Hita  [Student]`. `role()` inside it is dynamic dispatch — Sakshi prints `[Research Student]`.

### `equals()` — business equality

The college must not add the same member twice. `==` compares **references** (memory address).
Our rule: **same id = same member.**

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof CollegeMember)) return false;
    CollegeMember other = (CollegeMember) obj;
    return id == other.id;
}
```

Used in `Section.add()`: `section.add(hita)` a second time → `Hita is already in CSE-A`.

### `hashCode()`

Rule: if `a.equals(b)` is true, then `a.hashCode() == b.hashCode()` must be true.
So when you override `equals()`, override `hashCode()` too (needed by `HashSet`, `HashMap`).

```java
@Override
public int hashCode() {
    return id;
}
```

## Step 16. `final` — Prevent Change on Purpose

| Where | Code | Why |
| --- | --- | --- |
| Field | `private final int id;` | A college id must never change after admission |
| Method | `public final int getId()` | No child may change how the id is read |
| Method | `public final void printReceipt(...)` | Every mode prints the same college receipt |
| Class | `public final class ResearchStudent` | Last level; nothing extends it |

`final` is not automatically "better". Use it when the design **needs** to stop reassignment or extension.

---

# The Complete Design

```text
                CollegeMember  <<abstract>>
                /            \
           Student          Teacher
     implements Gradable,   (no marks, no fee)
                FeePayer
               |
        ResearchStudent  <<final>>


                  Payment  <<abstract>>
          /         |          |          \
   UpiPayment  CardPayment  CashPayment  NetBankingPayment


Section ◇──── CollegeMember[]          (HAS-A)

FeeCounter.collect(FeePayer payer, Payment mode, amount, daysLate)
```

| File | Job |
| --- | --- |
| `CollegeMember` | Abstract parent: auto id, name, `role()`, `showDetails()`, `toString()`, `equals()`, `hashCode()` |
| `Gradable` | Interface: `total()`, `percentage()`, `isPassed()`, `default grade()`, `default status()` |
| `FeePayer` | Interface: `fee()`, `paidAmount()`, `addPayment()`, `default balance()`, `default isFeeCleared()`, `default status()`, `static lateFine()` |
| `Student` | 5 marks, fee 1,20,000, fee paid; resolves the two `status()` defaults |
| `ResearchStudent` | Topic, guide, fee 1,50,000, must also get 50% |
| `Teacher` | Subject, salary; no marks, no fee |
| `Payment` | Abstract parent of all modes: `abstract pay()`, `final printReceipt()` |
| `UpiPayment`, `CardPayment`, `CashPayment` | Each mode's own `pay()` |
| `NetBankingPayment` | Mode added later — `FeeCounter` unchanged |
| `FeeCounter` | Fine + pay + receipt. Knows only `FeePayer` and `Payment` |
| `Section` | Holds all members: add, find, show all, topper, fee report |
| `PortalApp` | `main` with the menu; `chooseMode()` |

---

# Complete Use Case — Sakshi Pays Her Fee

> Sakshi (102) pays ₹10,000. She is 3 days late. She chooses Card.

| Step | Code | Concept |
| --- | --- | --- |
| 1. Find her | `CollegeMember m = section.find(102);` | Reference `CollegeMember`, object `ResearchStudent` |
| 2. Can she pay? | `if (m instanceof FeePayer f)` | Capability check |
| 3. Fee due | `f.balance()` → 100000.0 | Default method; `fee()` of ResearchStudent = 150000 |
| 4. Choose mode | `Payment mode = chooseMode(sc);` → `CardPayment` | Upcasting |
| 5. Collect | `FeeCounter.collect(f, mode, 10000, 3);` | Abstraction |
| 6. Fine | `FeePayer.lateFine(3)` → 150.0 | Static interface method |
| 7. Pay | `mode.pay(10150)` → `CardPayment.pay()` | Dynamic dispatch |
| 8. Update | `payer.addPayment(10000)` | `FeeCounter` does not know it is Sakshi |
| 9. Receipt | `mode.printReceipt(...)` | Final method, `toString()` |

Real output:

```text
Swipe card ending 5678
Enter card PIN
Rs 10251.5 paid (Rs 101.5 card charge)
------ VGU College Fee Receipt ------
Receipt No : 1
Student    : 102  Sakshi  [Research Student]
Fee paid   : 10000.0
Late fine  : 150.0
Mode       : Card
Balance    : 90000.0
-------------------------------------
```

---

# How to Run

```
javac *.java
java PortalApp
```

Names, topics, UPI id and bank name are read with `sc.next()`, so type them as **one word**.

Menu: 1 Add student · 2 Add research student · 3 Add teacher · 4 Enter marks · 5 Show all · 6 Find by name · 7 Topper · 8 Pay fee · 9 Fee report · 0 Exit

Option **5** at the start shows:

```text
101  Hita  [Student]  Total: 438  %: 87.6  Grade: B  Balance: 0.0  PASS, FEE CLEARED
102  Sakshi  [Research Student]  Total: 244  %: 48.8  Grade: D  Balance: 100000.0  FAIL, FEE DUE
      Topic: Cloud_Computing  Guide: Verma
103  Somya  [Student]  Total: 465  %: 93.0  Grade: A  Balance: 120000.0  PASS, FEE DUE
104  Verma  [Teacher]  Subject: Java  Salary: 60000.0
```

### Try in class

1. **5** Show all → one loop, four different `showDetails()` (dynamic dispatch).
2. **9** Fee report → Verma is skipped (`instanceof FeePayer`).
3. **8**, id **102** (Sakshi), 10000, 3 days late, **2** Card → fine 150 + card charge.
4. **8**, id **104** (Verma) → "does not pay a fee".
5. **8**, id **103** (Somya), pay by **4** Net Banking → the new class works, `FeeCounter` untouched.
6. **9** again → balances changed.
7. **6**, name `sakshi` → `Class: ResearchStudent` (`getClass()`).

### Show the errors (uncomment in `PortalApp`)

```java
CollegeMember m = new CollegeMember("X");               // abstract class
FeeCounter.collect(verma, new CashPayment(), 1000, 0);  // Teacher is not a FeePayer
System.out.println(hita.lateFine(2));                   // static: use FeePayer.lateFine(2)
```

And delete `status()` from `Student` → the diamond-problem error from Step 7.

---

# Concepts Used — Where to Find Them

| Concept | Where |
| --- | --- |
| 2.1 `extends`, IS-A | `Student extends CollegeMember`, `UpiPayment extends Payment` |
| 2.1 private not inherited | `ResearchStudent` uses `total()`, not `marks[]` |
| 2.2 Multilevel | `CollegeMember` → `Student` → `ResearchStudent` |
| 2.2 Hierarchical | `Student`, `Teacher` extend `CollegeMember`; 4 modes extend `Payment` |
| 2.3 `super()` | Every child constructor |
| 2.3 `super.method()` | `ResearchStudent.showDetails()`, `ResearchStudent.isPassed()` |
| 2.4 Overloading | `setMark(int, int)` / `setMark(int[])`, `find(int)` / `find(String)` |
| 2.5 Overriding, `@Override` | `role()`, `showDetails()`, `fee()`, `isPassed()`, `pay()` |
| 2.7 Upcasting | `CollegeMember[] members`, `Payment mode = chooseMode(sc)` |
| 2.7 Dynamic dispatch | `members[i].showDetails()`, `mode.pay()`, `f.fee()` |
| 2.7 `instanceof`, downcasting | `showTopper()`, `feeReport()`, menu options 4 and 8 |
| 2.8 Blank `final` | `id` in `CollegeMember` |
| 2.8 `final` method / class | `getId()`, `printReceipt()`, `ResearchStudent` |
| 2.9 `toString()` | `CollegeMember`; used by `println()` and the receipt |
| 2.9 `equals()`, `hashCode()` | `CollegeMember`; `Section.add()` stops duplicates |
| 2.9 `getClass()` | Menu option 6 |
| 2.10 Abstract class and methods | `CollegeMember` (`role()`, `showDetails()`), `Payment` (`pay()`) |
| 2.10 Abstraction | `FeeCounter.collect()` |
| 2.11 Interface, constant | `Gradable` (`PASS_MARK`), `FeePayer` (`FINE_PER_DAY`) |
| 2.11 Multiple interfaces | `Student implements Gradable, FeePayer` |
| 2.11 `default` methods | `grade()`, `balance()`, `isFeeCleared()`, `status()` |
| 2.11 Diamond problem | `Student.status()` with `Gradable.super` / `FeePayer.super` |
| 2.11 `static` method | `FeePayer.lateFine()` |
| 2.12 Abstract class + interface together | `CollegeMember`, `Payment` (identity, shared code) + `Gradable`, `FeePayer` (capabilities) |
| 2.13 Composition (HAS-A) | `Section` has members, does not extend them |

Every concept is also marked in the code with its section number and a `// WHY` line, e.g. `// 2.7 Upcasting`.

---

# Classroom Questions — Situations, Not Definitions

| # | Situation | Answer |
| --- | --- | --- |
| 1 | Student and Teacher both have id and name. | Inheritance (common parent) |
| 2 | Nobody is "just a CollegeMember". | Abstract class |
| 3 | Only students have marks. Where does grading go? | `Gradable` interface |
| 4 | Only some members pay fees. | `FeePayer` interface |
| 5 | Every fee payer works out balance the same way. | Interface `default` method |
| 6 | Late fine does not depend on any student. | `static` method |
| 7 | Two interfaces give the same default `status()`. | Override it; use `X.super.status()` |
| 8 | The same `showDetails()` call behaves differently per object. | Overriding + dynamic dispatch |
| 9 | Only Gradable members can be the topper. | `instanceof` capability check |
| 10 | `find()` gives a CollegeMember but we need `setMark()`. | Downcasting |
| 11 | A research student is a special student. | Multilevel inheritance |
| 12 | UPI, Card, Cash all pay differently. | Abstract `Payment` + overriding |
| 13 | `FeeCounter` must not know the payment type. | Abstraction + polymorphism |
| 14 | A Section contains students and teachers. | HAS-A / composition |
| 15 | Find a member by id or by name. | Overloading |
| 16 | `println(hita)` prints `Student@6d311334`. | Override `toString()` |
| 17 | Two objects with id 101 are the same person. | Override `equals()` + `hashCode()` |
| 18 | A college id must never change. | `final` field |

---

# Final Mental Model

Students should not leave thinking *"Java has many OOP keywords"*. They should leave thinking:

```text
I have a problem
      ↓
What objects exist?
      ↓
What is common? What is different?
      ↓
IS-A or HAS-A?
      ↓
Identity or capability?
      ↓
Who should own this behaviour?
      ↓
Does the caller need the concrete type?
      ↓
Can polymorphism remove type-specific if/else?
      ↓
Can abstraction hide the details?
```

| Need | Concept |
| --- | --- |
| IS-A | Inheritance |
| HAS-A | Composition |
| Common identity | Abstract class |
| Common capability | Interface |
| Same rule for every implementer | `default` method |
| No object needed | `static` method |
| Different implementation | Overriding |
| Same reference, different real object | Polymorphism |
| Hide details from the caller | Abstraction |
| Prevent change / extension | `final` |

> **Do not choose an OOP feature to show the feature. Choose it because the problem needs it.**

---

# Exercises for Students

1. Add `WalletPayment extends Payment` (wallet name). Change only `chooseMode()`. Did `FeeCounter` change?
2. Add `Counsellor extends CollegeMember` (room number). Which files need a change? Did `Section.showAll()` change?
3. Add an interface `Rewardable` with `double scholarship()`. A student gets 10% of the fee back if percentage ≥ 90.
4. Add a `default` method `showFeeStatus()` in `FeePayer` that prints fee, paid and balance.
5. Try `hita.lateFine(2)`. What error do you get and why?
6. Try `class PhdStudent extends ResearchStudent`. What error do you get and why?
7. Delete `status()` from `Student` and compile. Read the error. Then change it to use only `Gradable.super.status()`.
8. Why is `PgStudent extends UgStudent` a bad design? Write the IS-A sentence and check it.
