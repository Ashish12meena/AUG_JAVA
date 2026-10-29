# 3.3 List, Part 1: The `List` Interface

Program: [Lists.java](Lists.java) (section register)

## Why Do We Need a List?

The section register of CSE-A has to answer questions like these:

- "Who is **first** in the register?" → we need **order**.
- "Ritika joined late; put her **at position 3**." → we need to reach a **position**.
- "Hita came to both lectures, write her name both times." → we need **duplicates**.

`Collection` (3.2) has no positions: there is no `get(0)` and no "insert at 3". A `Set` would even throw away Hita's second entry.
We need a collection that behaves like an **array that grows**: ordered, position-based, and allowing repeats. That is a **List**.

## What Is a List?

**List:** An **ordered** collection that allows **duplicates** and gives access to elements by their **index** (0, 1, 2 ...).

Its three characteristics:

| Characteristic | Meaning | Register example |
| --- | --- | --- |
| **Ordered** | Elements stay in the order they were added (or placed) | Roll order is kept |
| **Indexed** | Every element has a position number, starting at 0 | `get(0)` → Hita |
| **Duplicates allowed** | The same element can appear more than once | `[Hita, Somya, Hita]` |

`List` is an **interface**. It extends `Collection`, so it has every `Collection` method, and it **adds** the index-based methods.
The classes that implement it are `ArrayList`, `LinkedList`, `Vector` and `Stack`.

> ⭐ **Remember:** Index starts at **0**. In a list of 5 students the valid indexes are 0 to 4. Index 5 → `IndexOutOfBoundsException`.

## How Does It Work?

### Index-based methods (added by `List`)

| Method | Does | In our program |
| --- | --- | --- |
| `get(i)` | Element at index i | `get(0)` → Hita |
| `set(i, x)` | Replace the element at i | Sakshi's re-checked result |
| `add(i, x)` | Insert at i; the rest move **right** | Ritika joins at index 3 |
| `remove(i)` | Remove at i; the rest move **left** | Disha moves to section B |
| `indexOf(x)` | First index of x, or -1 | Somya → 2 |
| `lastIndexOf(x)` | Last index of x, or -1 | useful with duplicates |
| `subList(a, b)` | Part from index a to b-1 | Front bench: index 0, 1 |

### What "insert" and "remove" mean in a List

A list has no gaps. So:

```
Before add(3, Ritika):  [Hita, Sakshi, Somya, Disha]
                           0      1      2      3
After:                  [Hita, Sakshi, Somya, Ritika, Disha]
                           0      1      2       3      4     <- Disha moved from 3 to 4
```

Every element after the position gets a **new index**. After `remove(i)` they all move one place back.

### `subList(from, to)`: "from" included, "to" not

`subList(0, 2)` gives index 0 and 1 only. This **half-open** style (start included, end excluded) is used all over Java (`substring()` too).

### `indexOf()` and `contains()` use `equals()`

Searching compares elements with `equals()`, not `==` (same rule as 3.2).

## Example

```java
List<Student> section = new ArrayList<>();
section.add(new Student(101, "Hita", 438));
section.add(3, new Student(104, "Ritika", 412));   // insert at index 3
section.get(0);                                      // Hita
section.set(1, new Student(102, "Sakshi", 310));   // replace index 1
```

## Common Mistakes

- **`remove(1)` on a `List<Integer>`.** There are two `remove` methods: `remove(int index)` and `remove(Object o)`. With `List<Integer> totals`, `totals.remove(1)` removes **index 1**, not the number 1. To remove the value, write `totals.remove(Integer.valueOf(1))`.
- **Saving an index, then inserting or removing.** Indexes shift; the saved index may now point to a different student.
- **Looping with an index while removing.** After `remove(i)` the next element slides into position `i` and the loop skips it. Use an iterator (3.7).

## Summary

- A List is ordered, indexed (from 0) and allows duplicates.
- `List` adds index methods: `get`, `set`, `add(i, x)`, `remove(i)`, `indexOf`, `subList`.
- Insert and remove shift the indexes of the elements after them.
- `List` is an interface; the next parts show the classes behind it.

Back to [3.3 List](README.md) · Next: [Part 2: ArrayList](02_ArrayList.md)
