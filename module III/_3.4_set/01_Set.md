# 3.4 Set, Part 1: What Is a Set?

Program: [Sets.java](Sets.java) (workshop sign-up sheet, scholarship cut-off)

## Why Do We Need a Set?

The workshop sign-up sheet reads: `Somya, Hita, Ritika, Hita, Disha, Somya`.
Hita and Somya wrote their names twice. We want each name **once**.

With a List we would have to check before every add:

```java
if (!names.contains(name)) {
    names.add(name);
}
```

This works, but the "no duplicates" rule now lives in **our code**. Every place that adds a name must remember to check. Forget it once, and duplicates are back. `contains()` on a list also checks elements one by one, which gets slow for big lists.

A Set moves the rule **into the data structure itself**: it simply refuses duplicates, everywhere, every time.

## What Is a Set?

**Set:** A collection that does **not** allow duplicate elements. It is modelled on a set in mathematics.

Characteristics:

- **No duplicates:** adding an element that is already there does nothing; `add()` returns `false`. No error.
- **No index:** a Set has no `get(i)`. Elements are visited with for-each or an iterator.
- **Order depends on the class:** none, insertion order, or sorted.

Interesting point: the `Set` interface adds **no new methods** to `Collection`. It only changes the **promise** of `add()`: "I will never hold two equal elements".

> ⭐ **Remember:** Adding a duplicate to a Set is **not** an error. `add()` just returns `false` and the set stays the same.

## How Do the Three Sets Store Data?

The three classes keep the same rule (no duplicates) but store the data differently, which gives them different **orders** and **speeds**.

| Class | Stored as | Order | Speed |
| --- | --- | --- | --- |
| **`HashSet`** | A hash table (buckets chosen by `hashCode()`) | No fixed order | Fastest |
| **`LinkedHashSet`** | A hash table **+** a linked list that remembers the adding order | Insertion order | Fast |
| **`TreeSet`** | A balanced (red-black) tree | Sorted (A to Z, small to big) | Slower |

- **Hashing:** turning an object into a number (`hashCode()`) that decides which **bucket** it goes into, so it can be found again without searching everything (Part 2).

### Same sign-up sheet, three sets

| Set | Output |
| --- | --- |
| `HashSet` | `[Disha, Ritika, Somya, Hita]` (any order) |
| `LinkedHashSet` | `[Somya, Hita, Ritika, Disha]` (sign-up order) |
| `TreeSet` | `[Disha, Hita, Ritika, Somya]` (A to Z) |

The `HashSet` order comes from the bucket numbers, not from us. It may even change as the set grows. Never write code that depends on it.

## `TreeSet`: A Set That Knows Order

Because a `TreeSet` is always sorted, it can answer "nearest" questions that other sets cannot. These methods come from the interfaces `SortedSet` and `NavigableSet`.

Scholarship cut-off is 400. Totals: `[244, 412, 438, 465]`

| Method | Meaning | Result |
| --- | --- | --- |
| `first()` / `last()` | Smallest / biggest | `244` / `465` |
| `floor(450)` | Biggest that is ≤ 450 | `438` |
| `ceiling(400)` | Smallest that is ≥ 400 | `412` |
| `headSet(400)` | All below 400 | `[244]` |
| `tailSet(400)` | 400 and above | `[412, 438, 465]` |

A `TreeSet` decides order (and duplicates!) with `compareTo()` or a `Comparator` (3.8), not with `equals()`.

## Comparison

| | `HashSet` | `LinkedHashSet` | `TreeSet` |
| --- | --- | --- | --- |
| Order | None | Insertion order | Sorted |
| Speed | Fastest | Fast | Slower |
| Duplicates checked by | `hashCode()` + `equals()` | `hashCode()` + `equals()` | `compareTo()` / `Comparator` |
| `null` allowed? | One | One | No |
| Extra methods | – | – | `first`, `floor`, `headSet` ... |

## Choosing a Set

| Need | Use |
| --- | --- |
| Unique + fastest, order does not matter | `HashSet` |
| Unique + keep the order they came in | `LinkedHashSet` |
| Unique + always sorted, or "nearest value" questions | `TreeSet` |

## Set Operations

The bulk methods of 3.2 work like real set algebra on Sets, and since a Set has no duplicates, `addAll` gives a true **union**:

`addAll` (union), `retainAll` (intersection), `removeAll` (difference), `containsAll` (subset).

## Common Mistakes

- **Expecting `HashSet` to keep order.** Use `LinkedHashSet` for that.
- **Calling `get(0)` on a Set.** There is no index.
- **Adding `null` to a `TreeSet`.** It cannot compare `null` → `NullPointerException`.

## Summary

- A Set holds each element only once; a duplicate `add()` returns `false`.
- `HashSet` (no order, fastest), `LinkedHashSet` (insertion order), `TreeSet` (sorted, with navigation methods).
- For our own classes, the Set needs `equals()` and `hashCode()` → Part 2.

Back to [3.4 Set](README.md) · Next: [Part 2: `equals()` and `hashCode()`](02_EqualsAndHashCode.md)
