# 3.3 List, Part 4: Legacy Lists and Fixed Lists

Program: [Lists.java](Lists.java) (the `List.of()` part at the end)

This part covers two smaller ideas: old list classes we still meet in interviews and old code, and lists that are not allowed to change.

## A. Legacy Classes: `Vector` and `Stack`

### Why do they exist?

`Vector` and `Stack` were in Java **before** the Collections Framework (Java 1.0). When the framework came in Java 1.2, they were changed to implement `List` so old programs kept working. Classes kept only for old code are called **legacy** classes.

### What are they?

- **`Vector`:** A resizable array, like `ArrayList`, but **synchronised**: every method is locked so that only one thread can use it at a time. When full, it **doubles** its size (`ArrayList` grows by 1.5 times).
- **`Stack`:** A **LIFO** (Last In, First Out) list. It **extends `Vector`** and adds `push()`, `pop()` and `peek()`.

### Why we avoid them now

- **Slower:** the locking on every method costs time, even when only one thread uses the list (which is almost always, until Module V).
- **`Stack` breaks its own rule:** because `Stack` extends `Vector`, it also has `add(index, x)` and `get(i)`. Anyone can insert into the **middle** of a "stack", which a real stack must never allow.

| | `ArrayList` | `Vector` |
| --- | --- | --- |
| Since | Java 1.2 | Java 1.0 (legacy) |
| Synchronised | No | Yes (slower) |
| Grows by | 1.5 times | 2 times |
| Use | Default list | Only in old code |

> ⭐ **Remember:** Use `ArrayList` instead of `Vector`, and `ArrayDeque` (3.5) instead of `Stack`. For many threads, use the tools of Module V.

## B. Fixed Lists: `List.of()`

### Why?

Some data must **never** change while the program runs: the 5 subjects of the semester, the days of the week.
If such a list is a normal `ArrayList`, any part of the program can add or remove by mistake, and the bug is hard to find.
We want the list itself to **refuse** changes.

### What is it?

**Immutable list:** A list whose elements cannot be added, removed or replaced after it is made. `List.of(...)` (Java 9) creates one.

```java
List<String> subjects = List.of("Java", "DBMS", "Maths", "English", "Networks");
subjects.get(0);            // "Java": reading is fine
// subjects.add("Python");  // ERROR at run time: UnsupportedOperationException
```

### How does it behave?

- Reading (`get`, `size`, `contains`, for-each) works normally.
- Any change (`add`, `remove`, `set`, `clear`) throws `UnsupportedOperationException` **at run time**. The compiler does not stop it, because the type is still `List`.
- `List.of()` does **not** allow `null` elements.

### `List.of()` vs `Arrays.asList()`

Both make a list from fixed values, and students often mix them up:

| | `List.of(...)` | `Arrays.asList(...)` |
| --- | --- | --- |
| Add / remove | Not allowed | Not allowed (size is fixed) |
| `set(i, x)` (replace) | Not allowed | **Allowed** |
| `null` elements | Not allowed | Allowed |
| Since | Java 9 | Java 1.2 |

To get a normal, changeable list from fixed values: `new ArrayList<>(List.of("Hita", "Somya"))`.

(A third kind, a **read-only view** of an existing list, comes in 3.9: `Collections.unmodifiableList()`.)

## Summary

- `Vector` and `Stack` are legacy: synchronised and slower. Prefer `ArrayList` and `ArrayDeque`.
- `Stack` extends `Vector`, so it allows non-stack operations: a design mistake.
- `List.of()` makes an immutable list; changes fail at run time with `UnsupportedOperationException`.

Previous: [Part 3](03_LinkedList.md) · Back to [3.3 List](README.md) · Next topic: [3.4 Set](../_3.4_set/README.md)
