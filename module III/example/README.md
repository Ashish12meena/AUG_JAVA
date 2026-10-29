# Module 3 Final Example: VGU College Portal 2.0 — Teaching Guide

The **same** portal from the Module II example, grown with Module III. Teach this **after 3.12**.

Module II gave us the right **classes** (CollegeMember, Student, Teacher, Payment ...).
Module III gives us the right **containers** for them, and a way for objects to **react** to events.

At every step:

```text
PROBLEM
   ↓
LIMITED DESIGN (Module II code)
   ↓
WHAT GOES WRONG?
   ↓
MODULE III CONCEPT
   ↓
BETTER DESIGN
   ↓
RUN / DEMONSTRATE
```

The question to keep asking:

> Which container fits this job? Order? Duplicates? Find by key? First come, first served?

**Running story (same as Module II):**

| Id | Name | Type | Marks | Fee |
| --- | --- | --- | --- | --- |
| 101 | Hita | Student | 438, PASS | 1,20,000 — fully paid |
| 102 | Sakshi | Research Student (Cloud Computing, guide Verma) | 244 (48.8%), **FAIL** | 1,50,000 — 50,000 paid |
| 103 | Somya | Student | 465, topper | 1,20,000 — nothing paid |
| 104 | Mr. Verma | Teacher (Java, salary 60,000) | — | — |

**What changed from Module II:**

| File | Change |
| --- | --- |
| `Section` | Array → `ArrayList` + `HashMap`; sorted lists, topper, grade report, promote |
| `FeeCounter` | Static methods → object with a fee **line** (`Queue`) and **listeners** |
| `Workshop` | **New**: `LinkedHashSet` of registered members |
| `FeeEvent`, `FeePaidListener`, `AccountsOffice` | **New**: callbacks when a fee is paid |
| `PortalApp` | New menu options 7–13; listeners added |
| `CollegeMember` | Only comments: `equals()`/`hashCode()` are now really used |
| All other classes | Same as Module II |

---

# Session 1 — Store the Members

## Step 1. The Problem With `CollegeMember[60]`

Module II `Section`:

```java
private CollegeMember[] members = new CollegeMember[60];
private int size = 0;
```

Ask:

- The 61st student takes admission. What happens? → `"CSE-A is full"`
- Sakshi leaves. How do we remove her? → shift every member after her by hand.
- Find member 103? → loop over everyone.
- Merit list? → write our own sort.

We are writing **storage code** again and again, not college code.
Java already has it: the **Collections Framework** (3.1).

## Step 2. `ArrayList` for Members (3.3)

```java
private List<CollegeMember> members = new ArrayList<>();

members.add(m);          // no size, no counter, never full
```

- `List` on the left, `ArrayList` on the right → **interface vs class** (3.1).
- Why a List? Admission **order** matters when we show everyone.
- The `size` variable is gone: `members.size()`.

Ask: *Why is `marks` in `Student` still an array?* → Always exactly 5 subjects. A **fixed size is correct** there.

```text
Size changes?   → collection
Size fixed?     → array is fine
```

## Step 3. Why `<CollegeMember>`? — Generics (3.10)

Without generics:

```java
List members = new ArrayList();
members.add("Hita");                          // a String! compiles
CollegeMember m = (CollegeMember) members.get(0);   // ClassCastException at RUN time
```

With generics:

```java
List<CollegeMember> members = new ArrayList<>();
members.add("Hita");          // COMPILE error, caught before class even starts
```

- No casts when reading: `CollegeMember m = members.get(0);`
- Every collection in this portal is generic.

## Step 4. Find by Id Without a Loop — `HashMap` (3.6)

Module II:

```java
public CollegeMember find(int id) {
    for (int i = 0; i < size; i++) {
        if (members[i].getId() == id) return members[i];
    }
    return null;
}
```

Now:

```java
private Map<Integer, CollegeMember> byId = new HashMap<>();

public CollegeMember find(int id) {
    return byId.get(id);     // straight to the member
}
```

And the duplicate check in `add()`:

```java
if (byId.containsKey(m.getId())) {
    System.out.println(m.getName() + " is already in " + name);
} else {
    members.add(m);
    byId.put(m.getId(), m);
}
```

Ask: *Why keep the List too?* → A HashMap has **no order**. The list keeps admission order for showing.
Rule: the list and the map must **always match** (see Step 10).

Ask: *Why is `id` `final` in `CollegeMember`?* → It is the **key** of the map. A key that changes gets lost in the map.

`find(String name)` still loops: the key is the id, not the name.

## Step 5. Others May Read, Not Change — `unmodifiableList` (3.9)

```java
public List<CollegeMember> getMembers() {
    return Collections.unmodifiableList(members);
}
```

Ask: *What if we returned `members` itself?*

```java
section.getMembers().add(verma);   // skips the duplicate check, the map never hears about it
```

With the read-only view → `UnsupportedOperationException`. Only `Section.add()` can add.

---

# Session 2 — Sort, Search, Report

## Step 6. Sorted Lists — `Comparator` (3.8)

> The office wants: members by name, a merit list, and a fee-due list.

```java
// by name
copy.sort(Comparator.comparing(CollegeMember::getName));

// merit list: total high to low, tie → name
list.sort(Comparator.comparing(Student::total).reversed()
        .thenComparing(Student::getName));

// fee due: biggest balance first
list.sort(Comparator.comparing(Student::balance).reversed());
```

- We sort a **copy**, so `members` keeps admission order.
- `Student::total`, `Student::balance` are **method references** (3.12). `balance()` is the `default` method from `FeePayer`.

Ask: *Why not `Comparable` in `CollegeMember`?*

- `Comparable` gives **one** natural order. We need **three** orders → `Comparator`.
- The natural order would be by id, but `members` is already in id order. We don't need it.

Fee-due list also uses:

```java
list.removeIf(Student::isFeeCleared);    // remove while looping, done for us (3.7)
```

## Step 7. One `showList()` for Every List — Wildcard (3.10)

```java
public void showList(String title, List<CollegeMember> list)   // first try
```

```java
section.showList("Merit list", section.meritList());   // meritList() is a List<Student>
// ERROR: List<Student> cannot be converted to List<CollegeMember>
```

Ask: *A Student IS-A CollegeMember. Why is `List<Student>` not a `List<CollegeMember>`?*
→ If it were, the method could put a `Teacher` into a list of students.

Fix:

```java
public void showList(String title, List<? extends CollegeMember> list)
```

- `? extends CollegeMember` = "a list of CollegeMember **or any child**".
- We only **read** from it → `extends` (PECS: Producer Extends).
- Now one method shows all members, the merit list, the fee-due list and the promoted list.

## Step 8. Topper and Grade Report — `Collections.max` (3.9) and `TreeMap` (3.6)

Module II `showTopper()` had a loop, a `best` variable and a `topper` variable. Now:

```java
Student topper = Collections.max(list, Comparator.comparing(Student::total));
```

Grade report: how many A, B, C ...?

```java
Map<Character, Integer> count = new TreeMap<>();
for (Student s : students()) {
    char g = s.grade();
    count.put(g, count.getOrDefault(g, 0) + 1);
}
// {A=1, B=1, D=1}
```

- `TreeMap` → grades print in A, B, C order.
- `getOrDefault(g, 0)` → 0 the first time a grade is seen.

## Step 9. Java Workshop — `Set` (3.4)

> New requirement: members register for a Java workshop. One person, one seat. First come, first served.

**Poor design:** a List + a loop to check "already registered?" before every add.

**Better design:**

```java
private Set<CollegeMember> registered = new LinkedHashSet<>();

if (registered.add(m)) {       // false if already there
    ...
}
```

- **Set** → no duplicates, checked for us.
- **Linked**HashSet → keeps registration order (first come, first served).

Ask: *How does the Set know two objects are the "same person"?*
→ `hashCode()` and `equals()` from Module II Step 15. **This is where they pay off.**

```text
registered.add(somya)
      ↓
hashCode() = 103 → bucket
      ↓
equals() → same id already there? → return false
```

- In this program the same object is registered again, so even Object's `hashCode()` would catch it.
  `equals()`/`hashCode()` matter when a **different object** with the same id arrives (e.g. loaded again from a file in Module IV). The 3.4 hostel example shows exactly this.

## Step 10. Year End: Promote Students — `Iterator` (3.7)

> At year end, students who **passed** and **cleared the fee** move to 2nd year. Remove them from CSE-A.

**Wrong:**

```java
for (CollegeMember m : members) {
    if (m instanceof Student s && s.isPassed() && s.isFeeCleared()) {
        members.remove(m);          // ConcurrentModificationException
    }
}
```

**Right:**

```java
Iterator<CollegeMember> it = members.iterator();
while (it.hasNext()) {
    CollegeMember m = it.next();
    if (m instanceof Student s && s.isPassed() && s.isFeeCleared()) {
        it.remove();                // safe remove from the list
        byId.remove(s.getId());     // and from the map: both must match
        promoted.add(s);
    }
}
```

- At the start only **Hita** is promoted (Somya has not paid, Sakshi failed).
- After Somya pays (Step 11), promote again → Somya too.

---

# Session 3 — Fee Day: a Line and Listeners

## Step 11. The Fee Line — `Queue` (3.5)

> Fee day. Students stand in a line at the counter. First come, first served.

```java
private Queue<FeePayer> line = new LinkedList<>();

line.offer(payer);        // join at the back
line.poll();              // serve the front (null if nobody is there)
```

Ask: *Why `Queue` and not `List`?* → We only add at the back and take from the front. `Queue` says exactly that, and nobody can jump the line with `add(0, ...)`.

`join()` also uses `line.contains(payer)` → `equals()` again: Sakshi cannot stand in the line twice.

### `FeeCounter` becomes an object

Module II:

```java
FeeCounter.collect(f, mode, amount, days);     // static: the counter kept no data
```

Now:

```java
FeeCounter counter = new FeeCounter();
counter.join(sakshi);
FeePayer f = counter.serveNext();
counter.collect(f, mode, amount, days);
```

Ask: *Why not static any more?* → The counter now **keeps data** (the line, the listeners). Data needs an object.

## Step 12. Who Needs to Know a Fee Was Paid? — Callbacks (3.12)

> New requirement: when a fee is paid
> – the accounts office records the money,
> – parents get an SMS,
> – the exam cell releases the hall ticket if the fee is cleared.

**Poor design:**

```java
public void collect(...) {
    ...
    accounts.record(amount);
    sms.send(payer);
    if (payer.isFeeCleared()) examCell.release(payer);
    // library? hostel? transport? → change this method every time
}
```

`FeeCounter` now knows every office in the college. Every new office = change `FeeCounter`.

**Better design: the listener pattern**

```text
FeeCounter (event source)  ──calls──▶  FeePaidListener.feePaid(FeeEvent e)
                                              ▲         ▲         ▲
                                       Accounts     SMS      Exam cell
```

```java
@FunctionalInterface
public interface FeePaidListener {
    void feePaid(FeeEvent e);
}
```

```java
// in FeeCounter.collect(), after the receipt
FeeEvent event = new FeeEvent(payer, amount, fine);
for (FeePaidListener listener : listeners) {
    listener.feePaid(event);
}
```

- `FeeCounter` knows only the **interface**. It never knows who is listening.
- `FeeEvent` carries the details. A new detail later (date, mode) = a new field in `FeeEvent`; the interface does not change.

Compare with Module II Step 11:

```text
Module II:  FeeCounter does not know HOW the student pays   (Payment)
Module III: FeeCounter does not know WHO cares about it      (FeePaidListener)
```

## Step 13. Three Kinds of Listeners

| Listener | Written as | WHY this form |
| --- | --- | --- |
| Accounts office | Full class `AccountsOffice` | It **keeps data** (collected today). A lambda cannot have fields. |
| SMS to parent | Anonymous class (3.11) | Used once, in one place. A named class is not worth it. |
| Exam cell | Lambda | Short, no data, interface has one method. |

```java
counter.addListener(new AccountsOffice());

counter.addListener(new FeePaidListener() {
    @Override
    public void feePaid(FeeEvent e) { ... SMS ... }
});

counter.addListener(e -> {
    if (e.getPayer().isFeeCleared()) {
        System.out.println("Exam cell: hall ticket released for " + e.getPayer());
    }
});
```

## Step 14. Add a Listener — Live in Class

> New requirement: the library must renew the card when the fee is cleared.

Add **one line** in `PortalApp`:

```java
counter.addListener(e -> {
    if (e.getPayer().isFeeCleared()) {
        System.out.println("Library  : card renewed for " + e.getPayer());
    }
});
```

Ask: *Did `FeeCounter` change?* → **No.**

---

# The Complete Design

```text
                CollegeMember  <<abstract>>
                /            \
           Student          Teacher
     implements Gradable,
                FeePayer
               |
        ResearchStudent  <<final>>


Section
  ├── List<CollegeMember> members        (ArrayList: admission order)
  └── Map<Integer, CollegeMember> byId   (HashMap: find by id)

Workshop
  └── Set<CollegeMember> registered      (LinkedHashSet: once each, in order)

FeeCounter
  ├── Queue<FeePayer> line               (LinkedList: first come, first served)
  └── List<FeePaidListener> listeners    (callbacks)
          ▲
   AccountsOffice · anonymous SMS · lambda exam cell

Payment <<abstract>> → UpiPayment, CardPayment, CashPayment, NetBankingPayment
```

| File | Job |
| --- | --- |
| `CollegeMember`, `Gradable`, `FeePayer`, `Student`, `ResearchStudent`, `Teacher` | Same as Module II |
| `Payment`, `UpiPayment`, `CardPayment`, `CashPayment`, `NetBankingPayment` | Same as Module II |
| `Section` | List + Map of members; sorted lists, topper, grade report, fee report, promote |
| `Workshop` | Set of registered members |
| `FeeCounter` | Fee line (Queue), collect fee, call listeners |
| `FeeEvent` | Details of one payment |
| `FeePaidListener` | Listener interface |
| `AccountsOffice` | Listener that keeps the day's total |
| `PortalApp` | `main` with the menu; adds the listeners |

## Which Container and Why

| Job | Container | Why |
| --- | --- | --- |
| All members, in admission order | `ArrayList` | Order, grows by itself |
| Find a member by id | `HashMap` | Jump to the key, no loop |
| Workshop registrations | `LinkedHashSet` | No duplicates + keep order |
| Fee counter line | `Queue` (`LinkedList`) | First in, first out |
| Grade count | `TreeMap` | Key → count, keys sorted |
| Members list for others | `unmodifiableList` | Read, not change |
| 5 marks of a student | `int[]` | Fixed size: array is right |

---

# Complete Use Case — Fee Day

> Sakshi and Somya are already in the fee line. Sakshi pays ₹10,000 by card, 3 days late. Then Somya pays the full ₹1,20,000 by Net Banking.

| Step | Code | Concept |
| --- | --- | --- |
| 1. Serve next | `FeePayer f = counter.serveNext();` → Sakshi | `Queue.poll()` (3.5) |
| 2. Fine | `FeePayer.lateFine(3)` → 150.0 | Static interface method (2.11) |
| 3. Pay | `mode.pay(10150)` → `CardPayment.pay()` | Dynamic dispatch (2.7) |
| 4. Receipt | `mode.printReceipt(...)` | Final method (2.8) |
| 5. Event | `new FeeEvent(payer, 10000, 150)` | Event object (3.12) |
| 6. Callbacks | Accounts, SMS run; exam cell checks → fee still due, stays quiet | Listeners (3.12) |
| 7. Serve next | Somya pays 1,20,000 → fee cleared → exam cell **releases hall ticket** | Same code, different result |
| 8. Promote | Menu 13 → Hita **and** Somya promoted | Iterator (3.7) |

Real output for Sakshi:

```text
Now serving: 102  Sakshi  [Research Student]  Fee due: 100000.0
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
Accounts : Rs 10150.0 entered. Collected today: Rs 10150.0
SMS      : Dear parent, Rs 10000.0 received for 102  Sakshi  [Research Student]. Balance: Rs 90000.0
Fee line: [103  Somya  [Student]]
```

Then for Somya the last lines are:

```text
Accounts : Rs 120000.0 entered. Collected today: Rs 130150.0
SMS      : Dear parent, Rs 120000.0 received for 103  Somya  [Student]. Balance: Rs 0.0
Exam cell: hall ticket released for 103  Somya  [Student]
Fee line: []
```

---

# How to Run

```
javac *.java
java PortalApp
```

Names, topics, UPI id and bank name are read with `sc.next()`, so type them as **one word**.

Menu: 1 Add student · 2 Add research student · 3 Add teacher · 4 Enter marks · 5 Show all · 6 Find by name · 7 Sorted lists · 8 Topper and grade report · 9 Join fee line · 10 Serve next · 11 Fee report · 12 Register for workshop · 13 Promote · 0 Exit

At start the program prints:

```text
Hita is already in CSE-A
Somya registered for Java Workshop
Verma registered for Java Workshop
102  Sakshi  [Research Student] joined the fee line. Position: 1
103  Somya  [Student] joined the fee line. Position: 2
```

### Try in class

1. **5** Show all → `showList()` with the read-only list.
2. **7** → **2** Merit list: Somya, Hita, Sakshi. **7** → **3** Fee due: Somya 1,20,000, Sakshi 1,00,000.
3. **8** → `Topper of CSE-A: 103 Somya` and `Grade count: {A=1, B=1, D=1}`.
4. **9**, id **104** (Verma) → "does not pay a fee".
5. **10** → Sakshi is served first (she joined first). Pay 10000, 3 days late, Card.
6. **10** → Somya. Pay 120000, 0 days, Net Banking → watch the exam cell listener.
7. **10** again → "Nobody is in the fee line" (`poll()` gave `null`).
8. **12**, id **101** → Hita registered. **12**, id **103** → "already registered" (Set).
9. **13** Promote → Hita and Somya. **5** → only Sakshi and Verma left.

### Show the errors (uncomment in `PortalApp`)

```java
section.getMembers().add(verma);   // UnsupportedOperationException (read-only view)
counter.join(verma);               // compile error: Teacher is not a FeePayer
```

And in `Section`, change `showList(String, List<? extends CollegeMember>)` to `List<CollegeMember>` → menu 7 → 2 no longer compiles (Step 7).

---

# Concepts Used — Where to Find Them

| Concept | Where |
| --- | --- |
| 3.1 Interface vs class | `List<...> = new ArrayList<>()`, `Queue<...> = new LinkedList<>()` |
| 3.2 `contains()`, `size()`, `isEmpty()` | `FeeCounter.join()`, `Section.showList()` |
| 3.3 `ArrayList` | `Section.members` |
| 3.4 `LinkedHashSet`, `equals()`/`hashCode()` | `Workshop.registered` |
| 3.5 `Queue`: `offer`, `poll` | `FeeCounter.line` |
| 3.6 `HashMap`: `put`, `get`, `containsKey`, `remove` | `Section.byId` |
| 3.6 `TreeMap`, `getOrDefault` | `Section.gradeReport()` |
| 3.7 `Iterator.remove()` | `Section.promote()` |
| 3.7 `removeIf` | `Section.feeDueList()` |
| 3.8 `Comparator.comparing`, `reversed`, `thenComparing` | `sortedByName()`, `meritList()`, `feeDueList()` |
| 3.9 `Collections.max`, `unmodifiableList` | `showTopper()`, `getMembers()` |
| 3.10 Generic collections | Every collection |
| 3.10 Wildcard `? extends` | `Section.showList()` |
| 3.11 Anonymous class | SMS listener in `PortalApp` |
| 3.12 Listener pattern | `FeeCounter`, `FeeEvent`, `FeePaidListener` |
| 3.12 Listener as class / lambda | `AccountsOffice` / exam cell in `PortalApp` |
| 3.12 Method reference | `CollegeMember::getName`, `Student::total`, `Student::isFeeCleared` |

Every concept is also marked in the code with its section number and a `// WHY` line, e.g. `// 3.6 HashMap`.

---

# Classroom Questions — Situations, Not Definitions

| # | Situation | Answer |
| --- | --- | --- |
| 1 | The 61st student cannot join the section. | `ArrayList` instead of an array |
| 2 | A student always has exactly 5 marks. | An array is fine |
| 3 | A String got into the members list by mistake. | Generics `List<CollegeMember>` |
| 4 | Find member 103 without a loop. | `HashMap<Integer, CollegeMember>` |
| 5 | Others should see members but not add to the list directly. | `Collections.unmodifiableList` |
| 6 | Members by name, by marks, by fee due. | Many orders → `Comparator` |
| 7 | Two students have the same total. | `thenComparing()` |
| 8 | `showList(List<CollegeMember>)` rejects the merit list. | `List<? extends CollegeMember>` |
| 9 | Find the topper in one line. | `Collections.max(list, comparator)` |
| 10 | Count how many students got each grade. | `Map` + `getOrDefault` |
| 11 | One person, one workshop seat, in order. | `LinkedHashSet` |
| 12 | How does the Set know it is the same person? | `hashCode()` + `equals()` |
| 13 | Remove members while looping over them. | `Iterator.remove()` |
| 14 | Fee counter: first come, first served. | `Queue` |
| 15 | Many offices must react when a fee is paid. | Listener / callback |
| 16 | A listener must keep a running total. | Full class, not a lambda |
| 17 | A listener used once in one place. | Anonymous class or lambda |
| 18 | Add a library listener. Does `FeeCounter` change? | No |

---

# Final Mental Model

```text
I have many objects
      ↓
Fixed number?             → array
Growing, in order?        → List
No duplicates?            → Set (Linked- for order, Tree- for sorted)
First come, first served? → Queue
Find by a key?            → Map
      ↓
Need many orders?         → Comparator
Removing while looping?   → Iterator
Must others only read it? → unmodifiable view
      ↓
Something happens and others must react?
      ↓
Listener interface + event object → the source never changes
```

> **Choose the container by the job, not by habit. Choose a callback when the source should not know who reacts.**

---

# Exercises for Students

1. Add the **Library** listener from Step 14. Did `FeeCounter` change?
2. Add `Hostel` listener as a full class that counts how many students cleared their fee today.
3. Can **one** `LinkedHashMap<Integer, CollegeMember>` replace both `members` and `byId` in `Section`? Try it.
4. Make `CollegeMember implements Comparable<CollegeMember>` (by id). Change the workshop to a `TreeSet`. What order is printed now?
5. Add a menu option "Search by grade": show all students with grade A, using `removeIf` or a stream `filter`.
6. Add a `PriorityQueue` for scholarship interviews: highest total first. Who is interviewed first?
7. Write the merit-list `Comparator` as a separate class `MeritOrder` and as an anonymous class. Which is easiest to read?
8. Write `for (CollegeMember m : members) members.remove(m);` in `promote()`. Run menu 13. Read the error.
