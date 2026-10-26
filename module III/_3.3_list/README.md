# 3.3 List

Example: [Lists.java](Lists.java) (section register, notice board, subjects)

## Definitions

- **List:** An **ordered** collection that allows **duplicates** and gives access by **index** (0, 1, 2 ...).
- **`ArrayList`:** A List that uses a **resizable array** inside. Fast to read by index.
- **`LinkedList`:** A List made of **nodes**; each node knows the one before and after it (doubly linked). Fast to add/remove at the ends. It is also a `Deque` (3.5).
- **`Vector` and `Stack`:** Old (**legacy**) classes. `Vector` is a synchronised ArrayList; `Stack` extends `Vector` (LIFO).

## Example

```java
List<Student> section = new ArrayList<>();
section.add(new Student(101, "Hita", 438));
section.add(3, new Student(104, "Ritika", 412));   // insert at index 3
section.get(0);                                      // Hita
```

## List Methods

| Method | Does | In our program |
| --- | --- | --- |
| `get(i)` | Element at index i | `get(0)` → Hita |
| `set(i, x)` | Replace element at i | Sakshi's re-checked result |
| `add(i, x)` | Insert at i, others move right | Ritika joins at index 3 |
| `remove(i)` | Remove at i, others move left | Disha moves to section B |
| `indexOf(x)` | Index of x (or -1) | Somya → 2 |
| `subList(a, b)` | Part from a to b-1 | Front bench: index 0, 1 |

## How `ArrayList` Grows

- Starts with space for **10** elements.
- When full, it makes a **new array 1.5 times bigger** and copies everything.
- We never see this: we just call `add()`.

## `ArrayList` vs `LinkedList`

| | `ArrayList` | `LinkedList` |
| --- | --- | --- |
| Inside | Resizable array | Doubly linked nodes |
| `get(i)` | Fast (jumps to index) | Slow (walks node by node) |
| Add/remove in the middle | Slow (shifts elements) | Fast once the place is found |
| Add/remove at the start | Slow | Fast (`addFirst`, `removeFirst`) |
| Use when | Mostly reading (register, lists) | Mostly adding/removing at the ends |

- In practice `ArrayList` is the default choice.

## Immutable List: `List.of()`

```java
List<String> subjects = List.of("Java", "DBMS", "Maths", "English", "Networks");
// subjects.add("Python");   // ERROR at run time: UnsupportedOperationException
```

- Use for data that must never change (the 5 subjects).

## Important Points

- Index starts at **0**. A wrong index → `IndexOutOfBoundsException`.
- Duplicates allowed: `[Hita, Somya, Hita]`.
- Insertion order is kept.
- `println(list)` prints every element using `toString()`.
- `Vector`/`Stack` are slow (every method is synchronised). Use `ArrayList` and `ArrayDeque` instead.
