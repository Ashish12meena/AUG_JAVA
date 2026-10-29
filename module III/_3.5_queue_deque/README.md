# 3.5 Queue and Deque

Program: [QueueAndDeque.java](QueueAndDeque.java) (fee counter line, scholarship interview, answer sheets)

## Why Do We Need Queues?

Some problems are not about **storing** a group but about **the order in which we handle it**. Three situations from the college:

- **Fee counter:** students are served in the order they arrived. First come, first served.
- **Scholarship interview:** the student with the **highest total** goes first, whoever came first.
- **Mr. Verma's answer sheets:** sheets are piled up; he checks the **top** one, the last one put down.

We could use a List for all three, but then:

- "Serve the first student" means `remove(0)` on an `ArrayList`, which shifts everyone (slow).
- The rule ("first come first served", "highest first", "last in first out") exists only in our heads. Nothing stops code from serving someone in the middle of the line.

Queues build the **processing rule** into the collection. We just say "give me the next one".

## What Is It?

- **Queue:** A collection that holds elements waiting to be processed. The usual rule is **FIFO** (First In, First Out): add at the **back (tail)**, remove from the **front (head)**.
- **PriorityQueue:** A queue where the element with the **highest priority** comes out first, decided by natural order or a `Comparator`. Arrival order does not matter.
- **Deque** (say "deck", **d**ouble-**e**nded **que**ue): A queue where elements can be added and removed at **both** ends. So it can work as a queue **and** as a stack.
- **Stack:** **LIFO** (Last In, First Out): the last element added is the first one removed.

| Rule | Meaning | College example | Java |
| --- | --- | --- | --- |
| FIFO | First in, first out | Fee counter line | `Queue` (`ArrayDeque`, `LinkedList`) |
| Priority | Most important first | Scholarship interview | `PriorityQueue` |
| LIFO | Last in, first out | Pile of answer sheets | `Deque` used as a stack (`ArrayDeque`) |

## How Does a Queue Work?

```
          tail (add here)                head (remove here)
offer ->  [ Somya | Sakshi | Hita ]  -> poll
```

`Queue` is an interface. Its main jobs are **add at the back**, **remove from the front**, and **look at the front**.

### Two sets of methods for the same jobs

| Job | Throws an exception if it fails | Gives a special value |
| --- | --- | --- |
| Add at the back | `add(x)` | `offer(x)` → `false` |
| Remove from the front | `remove()` → `NoSuchElementException` | `poll()` → `null` |
| See the front | `element()` → `NoSuchElementException` | `peek()` → `null` |

**Why two sets?** An empty queue is a **normal** situation (the fee line is empty at lunch time), not a program error. `poll()` and `peek()` let us check calmly; `remove()` and `element()` are for when an empty queue really would be a bug.

> ⭐ **Remember:** Prefer `offer`, `poll`, `peek`. An empty line gives `null` instead of crashing the program.

```java
Queue<String> feeLine = new LinkedList<>();
feeLine.offer("Hita");
feeLine.offer("Sakshi");
feeLine.peek();     // Hita (only looks)
feeLine.poll();     // Hita is served and removed
```

## How Does a PriorityQueue Work?

A `PriorityQueue` always keeps the **"smallest"** element at the head, according to:

- **natural order** (`Integer`: smaller number first, `String`: A first), or
- a **`Comparator`** we give it (3.8).

```java
PriorityQueue<Integer> rolls = new PriorityQueue<>();               // 101, 102, 103 come out in order
PriorityQueue<Student> interview =
        new PriorityQueue<>((a, b) -> b.total - a.total);           // highest total first
```

Output of the interview queue: `Somya 465, Hita 438, Ritika 412, Sakshi 244`

Inside, it uses a structure called a **binary heap**. A heap only guarantees that the **head** is the smallest; the rest are only partly arranged. That is enough to give the next element quickly, and cheaper than keeping everything fully sorted.

> ⭐ **Remember:** Printing a `PriorityQueue` with `println()` may look **unsorted**. Only `poll()` gives elements in priority order, one by one.

## How Does a Deque Work?

A Deque has a front and a back, and every operation exists for **both** ends: `offerFirst/offerLast`, `pollFirst/pollLast`, `peekFirst/peekLast`.

Using only one end gives a **stack**; using opposite ends gives a **queue**:

| As a stack (LIFO) – one end | As a queue (FIFO) – both ends |
| --- | --- |
| `push(x)` – add on top | `offerLast(x)` – add at the back |
| `pop()` – remove from the top | `pollFirst()` – remove from the front |
| `peek()` – see the top | `peekFirst()` – see the front |

```java
Deque<String> sheets = new ArrayDeque<>();
sheets.push("Hita"); sheets.push("Sakshi"); sheets.push("Somya");
sheets.pop();       // Somya (last in, first out)
```

## Implementation Classes

| Class | Implements | Inside | `null` allowed? | Use for |
| --- | --- | --- | --- | --- |
| `ArrayDeque` | `Deque` (and `Queue`) | Resizable circular array | No | Stacks and normal queues (best choice) |
| `LinkedList` | `Deque` and `List` | Linked nodes | Yes | When you also need List methods |
| `PriorityQueue` | `Queue` | Binary heap | No | "Most important first" |

> ⭐ **Remember:** Use `ArrayDeque` for both stacks and queues. It is faster than the legacy `Stack` class and than `LinkedList`.

## Common Mistakes

- Using the legacy `Stack` class (3.3, Part 4) instead of `ArrayDeque`.
- Expecting a `PriorityQueue` to be fully sorted when printed.
- Calling `remove()` on an empty queue → `NoSuchElementException`.
- Adding `null` to an `ArrayDeque` or `PriorityQueue` → `NullPointerException` (because `null` is used as the "empty" answer of `poll()`).

## Summary

- Queues control **processing order**: FIFO (Queue), priority (PriorityQueue), LIFO (Deque as a stack).
- Two method sets: exception (`add/remove/element`) or special value (`offer/poll/peek`).
- `PriorityQueue` only guarantees the head; `poll()` gives priority order.
- `ArrayDeque` is the default for stacks and queues.

Previous: [3.4 Set](../_3.4_set/README.md) · Next: [3.6 Map](../_3.6_map/README.md)
