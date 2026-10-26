# 3.1 Introduction to the Collections Framework

Example: [WhyCollections.java](WhyCollections.java) (Section CSE-A gets more students)

## The Story

- In Module II the portal kept everyone in `CollegeMember[60]`.
- The 61st admission → "Section is full".
- Sakshi leaves → shift the array by hand. Find Somya → write a loop. Merit list → write our own sort.
- Java already has ready-made classes for all of this: the **Collections Framework**.

## Definitions

- **Collection:** An object that stores a group of objects (called **elements**) as one unit.
- **Framework:** A ready-made set of interfaces and classes that gives a standard way to solve a problem.
- **Collections Framework:** A set of interfaces and classes in `java.util` used to store and work on a group of objects (add, remove, search, sort).

## Example

```java
List<Student> section = new ArrayList<>();
section.add(new Student(101, "Hita", 438));
section.add(new Student(104, "Ritika", 412));   // no size limit
section.remove(1);                                // no shifting by hand
System.out.println(section);                      // prints every student
```

## Array vs Collection

| | Array | Collection |
| --- | --- | --- |
| Size | Fixed when created | Grows and shrinks by itself |
| Holds | Primitives and objects | Objects only (`Integer`, not `int`) |
| Ready-made methods | None (only `length`) | `add`, `remove`, `contains`, `size` ... |
| Insert in the middle | Shift by hand | `add(index, element)` |
| Print all | Loop needed | `println(list)` |

## The Hierarchy

```
                  Iterable
                     |
                 Collection
          /          |           \
       List         Set          Queue
        |            |             |
   ArrayList     HashSet      PriorityQueue
   LinkedList    LinkedHashSet    Deque
   Vector        TreeSet           |
     |                         ArrayDeque
   Stack                       (LinkedList also)


   Map  (separate, not a Collection)
    |
   HashMap, LinkedHashMap, TreeMap, Hashtable
```

- `Map` stores **key → value** pairs, so it is **not** a `Collection`.
- Interfaces (`List`, `Set`, `Queue`, `Map`) say **what**. Classes (`ArrayList`, `HashSet` ...) say **how**.

## Interface vs Implementation Class

```java
List<Student> section = new ArrayList<>();
// interface (WHAT)       class (HOW)
```

- Write the **interface** on the left. To change `ArrayList` to `LinkedList` later, change one word.
- `<Student>` says what the list holds (full detail in 3.10).

## `Collection` vs `Collections`

| `Collection` | `Collections` |
| --- | --- |
| Interface | Utility class |
| Parent of `List`, `Set`, `Queue` | Has only `static` helper methods |
| `list.add(x)` | `Collections.sort(list)` |

## Important Points

- All collection interfaces and classes are in `java.util` → `import java.util.ArrayList;` etc.
- Collections store **objects only**: `int` is stored as `Integer` (autoboxing, 3.2).
- `Iterable` is the root: anything `Iterable` can be used in a for-each loop.
- Choose by need:
  - Order + duplicates allowed → **List**
  - No duplicates → **Set**
  - First come, first served → **Queue**
  - Find by a key (roll no) → **Map**
