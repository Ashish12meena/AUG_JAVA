# 3.8 Sorting, Part 1: `Comparable` (Natural Order)

Program: [Sorting.java](Sorting.java) (the `Student` class and section 1)

## Why Do We Need `Comparable`?

In 3.1, `Collections.sort(names)` sorted a list of Strings without any help. Try the same with students:

```java
List<Student> list = ...;
Collections.sort(list);     // ERROR at compile time
```

**Why?** To sort, Java must repeatedly ask "should student A come before student B?".
For Strings the answer is obvious (alphabetical). For a `Student`, it is not: by roll number? by name? by marks? Java cannot guess; only the person who **designs** the class knows what the normal order of students is.

So the class itself must answer the question "how do I compare with another object of my type?". That is the job of `Comparable`.

## What Is `Comparable`?

**`Comparable<T>`:** An interface (in `java.lang`) that a class implements to define its **natural order**: the default way its objects are sorted. It has one method:

```java
int compareTo(T other)
```

- **Natural order:** the one order that feels "normal" for the class. For a register, students are naturally in **roll number** order.
- Because it is written **inside** the class, a class can have only **one** natural order.

Java classes that already implement `Comparable`:

| Class | Natural order |
| --- | --- |
| `Integer`, `Double` ... | Small to big |
| `String` | Alphabetical (dictionary) order, by character codes |
| `Character` | By character code |

That is why Strings and numbers "just sort".

## How Does It Work?

### What the number means

`a.compareTo(b)` answers "where does `a` go compared to `b`?":

| Result | Meaning |
| --- | --- |
| Negative | `a` comes **before** `b` |
| 0 | Same place (equal for sorting) |
| Positive | `a` comes **after** `b` |

Only the **sign** matters, not the size: -1 and -73 mean the same thing.

### Implementing it

```java
class Student implements Comparable<Student> {
    int id;
    ...
    @Override
    public int compareTo(Student other) {
        return this.id - other.id;      // smaller roll number comes first
    }
}

Collections.sort(list);                 // now works: by roll number
```

`this.id - other.id` is negative when `this` has the smaller roll number, so `this` comes first. Exactly what we want.

### Who uses `compareTo()`?

Once a class is `Comparable`, every part of Java that needs a "natural" order can use it:

- `Collections.sort(list)` and `list.sort(null)`
- `TreeSet` and `TreeMap` keys (3.4, 3.6)
- `PriorityQueue` without a comparator (3.5)
- `Collections.max()` / `min()` (3.9)

## Important Rules

> ⭐ **Remember:** A `TreeSet` (or `TreeMap`) treats `compareTo() == 0` as a **duplicate**. If `Student.compareTo()` compared **totals**, Hita and Disha (both 438) would count as the same element, and one of them would silently disappear from the set.

So `compareTo()` should return 0 only when the objects are really "equal" (ideally when `equals()` is also true). Comparing by `id` is safe because ids are unique.

- **Subtraction can overflow.** `this.id - other.id` is fine for roll numbers and marks. For very large or negative numbers it can give the wrong sign; then use `Integer.compare(this.id, other.id)`.
- **String compares by character codes:** all capital letters come before small letters, so `"Zara"` sorts before `"anita"`.

## Limitation of `Comparable`

The office now wants:

- the **merit list** (by total, high to low),
- the **name list** (A to Z),
- and still the register (by roll number).

`Comparable` gives only **one** order, and changing it means changing the `Student` class every time. Also, we cannot add `compareTo()` to classes we did not write (like `String`, to sort names by length).
This limitation leads to `Comparator` → Part 2.

## Summary

- `Comparable` gives a class its one **natural order**, written **inside** the class with `compareTo()`.
- Negative = before, 0 = same, positive = after.
- Used by `Collections.sort(list)`, `TreeSet`, `TreeMap`, `PriorityQueue`.
- Only one order is possible → for more orders, use `Comparator`.

Back to [3.8 Sorting](README.md) · Next: [Part 2: Comparator](02_Comparator.md)
