# 3.1 Introduction to the Collections Framework

Program: [WhyCollections.java](WhyCollections.java) (Section CSE-A gets more students)

## Why Do We Need Collections?

In Module II the college portal kept every member in an array: `CollegeMember[60]`.
An array is fine when we know **exactly** how many items there will be and that number never changes. A real section is not like that.

Problems we hit with the array:

- **Fixed size.** The array is made with 60 places. The 61st admission has nowhere to go. Making it `[1000]` "just in case" wastes memory and is still a guess.
- **No ready-made operations.** To find Somya we write a loop. To remove Sakshi we shift every later student one place left, by hand. To make a merit list we write our own sort.
- **The same code in every project.** Every programmer writes searching, sorting and resizing again, and every copy can have its own bugs.
- **No rules about the data.** An array cannot say "no duplicates", "keep sorted" or "first come, first served". We must enforce such rules ourselves, everywhere.

Before Java 1.2, Java had only arrays and a few separate classes (`Vector`, `Stack`, `Hashtable`). Each had its **own** method names (`addElement()`, `elementAt()`, `put()` ...) and no common parent, so code written for one could not be used with another.

**What we want:** containers that grow and shrink by themselves, already know how to add, remove, search and sort, can follow rules (no duplicates, sorted order), and all share **one** common set of method names.

That is the **Collections Framework** (Java 1.2, package `java.util`).

## What Is It?

- **Collection:** An object that stores a group of objects (called **elements**) as one unit.
- **Framework:** A ready-made set of interfaces and classes that gives a standard way to solve a problem.
- **Collections Framework:** A set of interfaces, classes and algorithms in `java.util` used to store and work on a group of objects (add, remove, search, sort).

### The three parts of the framework

| Part | What it does | Examples |
| --- | --- | --- |
| **Interfaces** | Say **what** a collection can do (its rules) | `List`, `Set`, `Queue`, `Map` |
| **Implementation classes** | Say **how** the data is stored inside | `ArrayList`, `HashSet`, `HashMap` |
| **Algorithms** | Ready-made methods that work on any collection | `Collections.sort()`, `Collections.max()` |

### Four kinds of collection, four kinds of problem

The framework does not give one container for everything. It gives a different kind for each **kind of rule**:

| Kind | Rule | College example |
| --- | --- | --- |
| **List** | Keeps order, allows duplicates, has an index | Section register in roll order |
| **Set** | No duplicates | Names on a workshop sign-up sheet |
| **Queue** | Decides who is processed next | Line at the fee counter |
| **Map** | Finds a value by its key | Roll number → student |

## How Is It Organised?

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

- **`Iterable`** is the root. Anything `Iterable` can be used in a for-each loop (3.7).
- **`Collection`** holds the methods every group of single elements has (`add`, `remove`, `size` ...) (3.2).
- **`List`, `Set`, `Queue`** each add their own rule.
- **`Map`** stores **key → value pairs**, not single elements, so it has its own separate hierarchy.

> ⭐ **Remember:** `Map` is part of the Collections Framework, but it is **not** a `Collection`. It does not extend the `Collection` interface.

## How Do We Use It?

### Interface on the left, class on the right

```java
List<Student> section = new ArrayList<>();
// interface (WHAT)       class (HOW)
```

This is called **programming to an interface**. Why it matters:

- The rest of the program only knows "it is a `List`". To switch from `ArrayList` to `LinkedList` later, we change **one word**.
- A method written as `void print(List<Student> list)` accepts **any** kind of list.

### `< >` says what the collection holds

`List<Student>` is a list of `Student` objects only. The compiler stops anything else from going in. (Full detail in 3.10, Generics.)

### Only objects go in

A collection stores **objects**, not primitives. An `int` is stored as an `Integer`; Java converts it automatically (**autoboxing**, 3.2).

### A small example

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
| Rules (no duplicates, sorted ...) | None | Built in (`Set`, `TreeSet` ...) |
| Print all | Loop needed | `println(list)` |

Arrays are still useful: they are slightly faster and can hold primitives. Use an array when the size is truly fixed (the 7 days of a week); use a collection otherwise.

## `Collection` vs `Collections`

| `Collection` | `Collections` |
| --- | --- |
| Interface | Utility class |
| Parent of `List`, `Set`, `Queue` | Has only `static` helper methods |
| `list.add(x)` | `Collections.sort(list)` |

> ⭐ **Remember:** `Collection` (no s) is an **interface**. `Collections` (with s) is a **helper class** (3.9). A favourite interview question.

## Choosing a Collection

| If you need ... | Use |
| --- | --- |
| Order + duplicates allowed + index | **List** (`ArrayList`) |
| No duplicates | **Set** (`HashSet`) |
| First come, first served | **Queue** (`ArrayDeque`, `LinkedList`) |
| Find by a key (roll number) | **Map** (`HashMap`) |

## Common Mistakes

- **Forgetting the import.** All collection types are in `java.util`: `import java.util.ArrayList;`.
- **`List<int>`.** Not allowed; use `List<Integer>`.
- **Calling `Map` a `Collection`.** It is in the framework, but not a `Collection`.

## Summary

- Arrays are fixed in size and have no ready-made operations or rules.
- The Collections Framework gives interfaces (what), classes (how) and algorithms (ready-made methods).
- Four kinds: List (order), Set (unique), Queue (processing order), Map (key → value).
- Write the interface on the left and the class on the right.

Next: [3.2 The `Collection` Interface](../_3.2_collection_interface/README.md)
