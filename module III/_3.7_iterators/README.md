# 3.7 Iterators

Program: [Iterators.java](Iterators.java) (scholarship list, seating plan, coding club)

## Why Do We Need Iterators?

### Problem 1: every collection is stored differently

- `ArrayList` keeps elements in an array (positions 0, 1, 2 ...).
- `LinkedList` keeps them in a chain of nodes.
- `HashSet` keeps them in buckets.
- `TreeSet` keeps them in a tree.

An index loop (`for (int i = 0; ...) list.get(i)`) only works for Lists. A `Set` has no index at all. Without a common way, to visit the elements we would need to know **how each collection is built inside**.

### Problem 2: removing while looping

The scholarship list starts with all 5 students; everyone below 400 (Sakshi) must be removed.
Removing inside a for-each loop **crashes** with `ConcurrentModificationException`. Removing inside an index loop silently **skips** elements, because the indexes shift.

### The solution

Give every collection **one standard "visitor" object** that knows how to walk through that particular collection, and that can also remove safely. That object is the **Iterator**.
We use the same three methods for an `ArrayList`, a `HashSet` or a `TreeMap`'s keys, and never think about how they are stored.

## What Is an Iterator?

- **Iterator:** An object that visits the elements of a collection one by one, and can safely remove the element it just visited.
- **`Iterable`:** The interface that says "I can give you an iterator" (method `iterator()`). `Collection` extends it, so every list, set and queue is `Iterable`.
- **`ListIterator`:** An Iterator for Lists that can move **both ways**, and can also `add()` and `set()`.
- **Fail-fast iterator:** Throws `ConcurrentModificationException` if the collection is changed during the loop by anything other than the iterator itself. (`ArrayList`, `HashMap` ...)
- **Fail-safe iterator:** Loops over a **copy** (or a snapshot), so changes during the loop do not throw. (`CopyOnWriteArrayList`, `ConcurrentHashMap`)
- **`Enumeration`:** The old (legacy) iterator of `Vector` and `Hashtable`. Cannot remove.

> ⭐ **Remember:** `Iterable` is the thing you loop **over** (the collection). `Iterator` is the thing that does the looping (the visitor).

## How Does an Iterator Work?

Think of a **cursor** that sits **between** elements, never on one:

```
start:            ^ Hita  Sakshi  Somya
after next():       Hita ^ Sakshi  Somya     next() returned Hita
after next():       Hita  Sakshi ^ Somya     next() returned Sakshi
remove():           removes Sakshi (the one next() just returned)
```

| Method | Does |
| --- | --- |
| `hasNext()` | Is there an element after the cursor? |
| `next()` | Move the cursor past the next element and **return** it |
| `remove()` | Remove the element **last returned** by `next()` |

```java
Iterator<Student> it = list.iterator();
while (it.hasNext()) {
    Student s = it.next();
    if (s.total < 400) {
        it.remove();          // safe
    }
}
```

Rules that follow from the cursor model:

- Call `next()` **before** `remove()`: there must be a "last returned" element.
- `remove()` twice in a row → `IllegalStateException` (the last returned element is already gone).
- `next()` when `hasNext()` is false → `NoSuchElementException`.

### The for-each loop is an iterator in disguise

```java
for (Student s : list) { ... }
```

is turned by the compiler into:

```java
Iterator<Student> it = list.iterator();
while (it.hasNext()) {
    Student s = it.next();
    ...
}
```

So for-each works on anything `Iterable`, but we have no access to `it`, so we **cannot** call `it.remove()`.

## Why Does Removing in for-each Crash?

```java
for (Student s : list) {
    if (s.total < 400) {
        list.remove(s);       // ERROR: ConcurrentModificationException
    }
}
```

- Every `ArrayList` keeps a **change counter** (`modCount`), increased on every add/remove.
- When the iterator starts, it remembers the counter value.
- On each `next()`, it checks: has the counter changed **behind my back**? `list.remove(s)` changed it, so the iterator stops with an exception, instead of continuing with wrong positions.

This is **fail-fast**: stop immediately at the first sign of trouble rather than give wrong results later.
`it.remove()` is safe because the iterator itself makes the change and updates its own count.

**Fixes:** use `it.remove()`, or the short form `list.removeIf(s -> s.total < 400);`

> ⭐ **Remember:** Never change a collection with its **own** methods (`list.add`, `list.remove`) inside a for-each loop over it. Use `it.remove()` or `removeIf()`.

## `ListIterator`: Two Directions, and Changes

Only for Lists. Extra methods:

| Method | Does |
| --- | --- |
| `hasPrevious()`, `previous()` | Go backwards |
| `set(x)` | Replace the element last returned |
| `add(x)` | Insert at the cursor position |
| `nextIndex()`, `previousIndex()` | Index around the cursor |

Seats `[Hita, Somya, Ritika]` → name cards in capitals, Disha sits after Hita → `[HITA, DISHA, SOMYA, RITIKA]`, then backwards: `RITIKA SOMYA DISHA HITA`.

## Fail-Fast vs Fail-Safe

| | Fail-fast | Fail-safe |
| --- | --- | --- |
| Loops over | The real collection | A copy / snapshot |
| Change during loop | Throws `ConcurrentModificationException` | No exception |
| Sees changes made during loop? | – | No (it is looking at the copy) |
| Memory / speed | Light, fast | Extra copy, slower |
| Examples | `ArrayList`, `HashSet`, `HashMap` | `CopyOnWriteArrayList`, `ConcurrentHashMap` |

In the program, Hita brings a friend to the coding club while we loop over a `CopyOnWriteArrayList`: no exception. Fail-safe collections are meant for programs where many threads share the data (Module V).

## Ways to Loop

| Loop | Works on | Can remove while looping? |
| --- | --- | --- |
| `for (int i = 0; ...)` + `get(i)` | List only | Risky (indexes shift, elements skipped) |
| for-each `for (Student s : list)` | Any `Iterable` | **No** → exception |
| `list.forEach(s -> ...)` | Any `Iterable` | No |
| `Iterator` | Any collection | **Yes**: `it.remove()` |
| `ListIterator` | List only | Yes, and also `add`/`set`, both directions |

## `Iterator` vs `Enumeration`

| | `Iterator` | `Enumeration` |
| --- | --- | --- |
| Since | Java 1.2 | Java 1.0 (legacy) |
| Methods | `hasNext()`, `next()`, `remove()` | `hasMoreElements()`, `nextElement()` |
| Can remove? | Yes | No |
| Works with | All collections | `Vector`, `Hashtable` |

## Summary

- An Iterator is one standard way to visit **any** collection, without knowing how it is stored.
- `hasNext()`, `next()`, `remove()`; the cursor sits between elements.
- for-each uses an iterator inside, but cannot remove.
- Changing a collection behind its iterator's back → `ConcurrentModificationException` (fail-fast).

Previous: [3.6 Map](../_3.6_map/README.md) · Next: [3.8 Sorting](../_3.8_comparable_comparator/README.md)
