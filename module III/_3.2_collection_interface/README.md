# 3.2 The `Collection` Interface

Program: [CollectionMethods.java](CollectionMethods.java) (Java and Python workshops)

## Why Do We Need a Common Interface?

There are many kinds of collections: `ArrayList`, `LinkedList`, `HashSet`, `TreeSet`, `ArrayDeque` ...
Each stores data in its own way. But many jobs are the **same** for all of them:

- add an element, remove an element,
- ask "is this element there?",
- ask "how many are there?".

If each class had its own names for these, we would need one `printAll()` for `ArrayList`, another for `HashSet`, and so on. We would also have to learn every class separately.

So Java puts these common operations into **one** interface, `Collection`, at the top of the hierarchy.
Now a method written for a `Collection` works with **every** list, set and queue, and we learn the basic methods only once.

## What Is It?

- **`Collection` interface:** The root interface of `List`, `Set` and `Queue`. It declares the methods that every collection of single elements has.
- **Autoboxing:** Java automatically changes a primitive into its wrapper object (`int` → `Integer`) when it goes into a collection.
- **Unboxing:** The opposite: wrapper object → primitive (`Integer` → `int`).

Important characteristics:

- It is generic: `Collection<E>`, where `E` is the element type.
- It extends `Iterable<E>`, which is why every collection works in a for-each loop.
- We never create a "`Collection` object" directly. We create an `ArrayList`, `HashSet` ... and can refer to it as a `Collection`:

```java
Collection<String> java = new ArrayList<>();
```

### What is NOT in `Collection`, and why

A parent interface can only contain what is true for **all** its children.

- `get(index)` is **not** in `Collection`, because a `Set` and a `Queue` have no index. Index methods live in `List`.
- There is no "sorted" method either, because a `Queue` or a `HashSet` has no sorted order.

> ⭐ **Remember:** `Collection` has **no** `get(index)`. To visit every element of any collection, use a for-each loop or an iterator (3.7).

## How Does It Work?

The methods fall into a few groups.

### 1. Basic methods (one element at a time)

| Method | Does | Example result |
| --- | --- | --- |
| `add(x)` | Adds x (gives `true` if it was added) | `[Hita, Sakshi, Somya]` |
| `remove(x)` | Removes x | `remove("Sakshi")` → `[Hita, Somya]` |
| `contains(x)` | Is x there? | `true` |
| `size()` | How many | `3` |
| `isEmpty()` | Is it empty? | `true` after `clear()` |
| `clear()` | Removes everything | `[]` |

`add()` returns a `boolean` because some collections may **refuse** an element. A `Set` refuses a duplicate and returns `false` (3.4).

### 2. Bulk methods (a whole collection at once)

Story: Hita, Sakshi and Somya join the **Java workshop**; Somya, Ritika and Disha join the **Python workshop**. Questions: who joined both? Who joined only Java? Everyone together?

These are the same operations as **sets in mathematics**:

| Method | Maths idea | Meaning | Result (`java = [Hita, Somya]`, `python = [Somya, Ritika, Disha]`) |
| --- | --- | --- | --- |
| `addAll(python)` | Union (A ∪ B) | Add all of them | `[Hita, Somya, Somya, Ritika, Disha]` |
| `retainAll(python)` | Intersection (A ∩ B) | Keep only the common ones | `[Somya]` |
| `removeAll(python)` | Difference (A − B) | Remove all of them | `[Hita]` |
| `containsAll(python)` | Subset (B ⊆ A) | Are all of them there? | `true` / `false` |

> ⭐ **Remember:** Bulk methods **change** the collection they are called on. To keep the original, work on a copy: `new ArrayList<>(java)`.

### 3. Conversion

`toArray()` turns a collection back into a normal array: `Object[] arr = java.toArray();`

### 4. Visiting elements

`iterator()` and the for-each loop (3.7).

## How Does `contains()` Decide "Same"?

`contains(x)` and `remove(x)` compare using **`equals()`**, not `==`.

- For `String` and `Integer` this works naturally: two `"Hita"` strings are equal.
- For our own `Student` class, `equals()` must be overridden, otherwise two objects with the same roll number are "different" (3.4).

## Autoboxing in Collections

**Why it is needed:** a collection stores **objects**, but marks are `int`s. Java fixes this by wrapping each primitive in its **wrapper class**:

| Primitive | Wrapper class |
| --- | --- |
| `int` | `Integer` |
| `double` | `Double` |
| `char` | `Character` |
| `boolean` | `Boolean` |

```java
Collection<Integer> marks = new ArrayList<>();
marks.add(85);                  // int -> Integer (autoboxing)
for (int m : marks) { ... }     // Integer -> int (unboxing)
// Collection<int> x;           // ERROR: only objects
```

The conversion is automatic, so the code reads as if the collection held `int`s.

## Common Mistakes

- **Calling `get(0)` on a `Collection` or a `Set`.** There is no index; use for-each.
- **Forgetting that a List keeps duplicates.** After `addAll`, Somya appears twice. For no duplicates use a `Set` (3.4).
- **Using bulk methods on the original** and then wondering why the data changed.

## Summary

- `Collection` is the root interface of `List`, `Set` and `Queue`; it holds the common methods.
- Basic methods (`add`, `remove`, `contains`, `size` ...) and bulk methods (union, intersection, difference, subset).
- `contains()` and `remove()` use `equals()`.
- Only objects go in; primitives are autoboxed.

Previous: [3.1](../_3.1_collections_intro/README.md) · Next: [3.3 List](../_3.3_list/README.md)
