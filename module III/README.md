# Module III: Collections, Iterators, Generics and Callbacks

How to store, find, sort and process a group of objects, write type-safe code with generics, and react to events with callbacks.

**Start here:** [Collections_Presentation.md](Collections_Presentation.md), the overview to present before 3.1.

**Full deck:** [Collections_Complete_Presentation.md](Collections_Complete_Presentation.md) ([PDF](Collections_Complete_Presentation.pdf)), every collection in order (List incl. Vector and Stack, Set, Queue/Deque, Map, Iterator, sorting, `Collections`) with tables, maps and \*\*\* remember points.

## One Example for the Whole Module

Every topic grows the **same** example: the students of section **CSE-A** in VGU College.

| Roll no | Name | Total (out of 500) |
| --- | --- | --- |
| 101 | Hita | 438 |
| 102 | Sakshi | 244 |
| 103 | Somya | 465 |
| 104 | Ritika | 412 |
| 105 | Disha | 438 (same as Hita) |

Mr. Verma is the Java teacher.

Question to keep asking: **"Where do we keep the students so we can find, sort and count them easily?"**

- In Module II the portal kept everyone in `CollegeMember[60]`. Fixed size, no search, no sort. Module III fixes this.
- Every folder uses the **same class name** `Student` (roll no, name, total).
  3.4 adds `equals()`/`hashCode()`, 3.8 adds `compareTo()`.
- Run each program from its own folder (`javac` + `java`), so the same names don't clash.
  VS Code sees all folders as one project, so it may underline a class as "already defined". The program still runs.

## How the Notes Are Written

Every topic answers the student's questions in this order:

**Why do I need this? → What problem does it solve? → What is it? → How does it work? → How do I use it? → What should I remember?**

- **Theory first.** The notes can be read and understood before opening the program. Code snippets only support the explanation.
- **⭐ Remember** boxes mark the few points worth memorising (rules, traps, interview facts).
- **Small topics** are one `README.md`. **Large topics** have a `README.md` with the reading order, plus numbered parts (`01_...md`, `02_...md`), each with one teaching purpose.

## Topics

| Topic | Folder | Notes |
| --- | --- | --- |
| 3.1 Introduction to the Collections Framework | [_3.1_collections_intro](_3.1_collections_intro/README.md) | 1 file |
| 3.2 The `Collection` interface | [_3.2_collection_interface](_3.2_collection_interface/README.md) | 1 file |
| 3.3 List | [_3.3_list](_3.3_list/README.md) | 4 parts: List interface, ArrayList, LinkedList, legacy and fixed lists |
| 3.4 Set | [_3.4_set](_3.4_set/README.md) | 2 parts: Set, `equals()` and `hashCode()` |
| 3.5 Queue and Deque | [_3.5_queue_deque](_3.5_queue_deque/README.md) | 1 file |
| 3.6 Map | [_3.6_map](_3.6_map/README.md) | 3 parts: Map, how HashMap works, choosing a Map |
| 3.7 Iterators | [_3.7_iterators](_3.7_iterators/README.md) | 1 file |
| 3.8 Sorting with `Comparable` and `Comparator` | [_3.8_comparable_comparator](_3.8_comparable_comparator/README.md) | 2 parts: Comparable, Comparator |
| 3.9 The `Collections` utility class | [_3.9_collections_class](_3.9_collections_class/README.md) | 1 file |
| 3.10 Generics | [_3.10_generics](_3.10_generics/README.md) | 6 parts, one program each |
| 3.11 Nested and inner classes | [_3.11_nested_classes](_3.11_nested_classes/README.md) | 3 parts: why, static/inner, local/anonymous |
| 3.12 Event-driven programming and callbacks | [_3.12_callbacks](_3.12_callbacks/README.md) | 3 parts: why callbacks, listener pattern, functional interfaces |
| **Final example** (Module II portal + all of Module III) | [example](example/README.md) | step-by-step guide |

Run any program from its folder:

```
javac FileName.java
java FileName
```

In [_3.10_generics](_3.10_generics/README.md) several programs share `Pair.java`, so use `javac *.java` there.
