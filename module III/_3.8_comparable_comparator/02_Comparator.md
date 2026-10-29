# 3.8 Sorting, Part 2: `Comparator` (Any Order)

Program: [Sorting.java](Sorting.java) (`ByTotal` class and sections 2–6)

## Why Do We Need `Comparator`?

Part 1 ended with three problems that `Comparable` cannot solve:

1. We need **many** orders for the same students: by roll number, by name, by total.
2. Each new order should **not** require changing the `Student` class.
3. Sometimes we cannot change the class at all (`String` is written by Java).

The fix is to take the "how to compare" rule **out of the class** and put it in a **separate object**. We can make as many such objects as we like, one per order, and hand the right one to `sort()`.

## What Is a `Comparator`?

**`Comparator<T>`:** An interface (in `java.util`) for an object whose only job is to compare two objects of type `T`. Its main method:

```java
int compare(T a, T b)
```

The result means the same as in `compareTo()`: negative → `a` first, 0 → same, positive → `b` first.

Key idea: the **object being sorted** (Student) and the **rule for sorting** (Comparator) are now **separate**. The same list can be sorted by any rule we pass in.

> ⭐ **Remember:** `Comparable` = "I know how to compare **myself**" (inside the class). `Comparator` = "I know how to compare **two others**" (outside the class).

## How Do We Write One?

There are four ways, from longest to shortest. They all create the same thing: an object with a `compare()` method.

| Way | Code |
| --- | --- |
| Separate class | `class ByTotal implements Comparator<Student> { public int compare(Student a, Student b) { return b.total - a.total; } }` |
| Anonymous class (3.11) | `new Comparator<Student>() { public int compare(Student a, Student b) { return a.name.compareTo(b.name); } }` |
| Lambda (3.12) | `(a, b) -> a.total - b.total` |
| `Comparator.comparing()` | `Comparator.comparing((Student s) -> s.total)` |

A lambda works because `Comparator` is a **functional interface**: it has only one abstract method, `compare()`.

### Using it

```java
list.sort(new ByTotal());                          // by total, high to low
list.sort((a, b) -> a.name.compareTo(b.name));     // by name
Collections.sort(list, new ByTotal());             // same as list.sort(...)
```

### Reading the sign: ascending or descending?

- `a.total - b.total` → small total first (**ascending**).
- `b.total - a.total` → big total first (**descending**). Swapping `a` and `b` reverses the order.

## Building a Comparator Step by Step

`Comparator` has ready-made methods to build complex orders from simple ones, instead of writing `if`-chains:

| Method | Does |
| --- | --- |
| `Comparator.comparing(s -> s.total)` | Compare by the value this function returns |
| `.reversed()` | Reverse the order |
| `.thenComparing(s -> s.name)` | If equal so far, compare by this next |
| `Comparator.naturalOrder()` / `reverseOrder()` | Natural order (uses `compareTo`) / its reverse |

**Merit list:** total high to low; if totals tie, name A to Z.

```java
list.sort(Comparator.comparing((Student s) -> s.total)   // by total
        .reversed()                                     // high to low
        .thenComparing(s -> s.name));                   // tie -> by name
```

Result: `Somya 465, Disha 438, Hita 438, Ritika 412, Sakshi 244`

Why write `(Student s)` and not just `s`? When `.reversed()` is chained after `comparing()`, Java cannot work out the type of `s` by itself, so we tell it.

### Ties and stable sorting

Without `thenComparing`, Hita and Disha (both 438) stay in whatever order they were **before** sorting. That is because Java's list sort is **stable**: equal elements keep their old relative order. `thenComparing` makes the tie-break explicit, so the result does not depend on the old order.

## Sorting Methods

| Method | Sorts | Order used |
| --- | --- | --- |
| `Collections.sort(list)` | A List | Natural (`Comparable`) |
| `Collections.sort(list, comp)` | A List | The `Comparator` |
| `list.sort(comp)` | A List | The `Comparator` (`null` → natural) |
| `Arrays.sort(array)` | An array | Natural |
| `Arrays.sort(array, comp)` | An array of objects | The `Comparator` |
| `new TreeSet<>(comp)`, `new PriorityQueue<>(comp)` | Kept in order all the time | The `Comparator` |

## `Comparable` vs `Comparator`

| | `Comparable` | `Comparator` |
| --- | --- | --- |
| Package | `java.lang` | `java.util` |
| Method | `compareTo(T other)` | `compare(T a, T b)` |
| Written | Inside the class being sorted | Outside, as a separate object |
| Number of orders | One (natural) | Any number |
| Must change the class? | Yes | No |
| Works for classes we cannot edit? | No | Yes |
| Used by | `Collections.sort(list)`, `TreeSet` | `list.sort(comp)`, `new TreeSet<>(comp)` |
| Lambda possible? | No (implemented by the class) | Yes (functional interface) |

> ⭐ **Remember:** Use `Comparable` for the **one obvious** order of a class (roll number). Use `Comparator` for **every other** order (merit list, name list).

## Common Mistakes

- Expecting `Collections.sort(list)` to work on a class that is not `Comparable` → compile error. Pass a comparator.
- Mixing up ascending and descending: check which object comes first in the subtraction.
- Forgetting `(Student s)` before `.reversed()` → "cannot find symbol" on `s.total`.

## Summary

- A `Comparator` is a separate object holding one sorting rule: `compare(a, b)`.
- Many comparators → many orders, with no change to the class.
- Write it as a class, an anonymous class, a lambda, or with `comparing()`, `reversed()`, `thenComparing()`.
- List sorting is stable; use `thenComparing` for explicit tie-breaks.

Previous: [Part 1](01_Comparable.md) · Back to [3.8 Sorting](README.md) · Next topic: [3.9 The `Collections` Class](../_3.9_collections_class/README.md)
