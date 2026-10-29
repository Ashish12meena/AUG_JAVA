# 3.9 The `Collections` Utility Class

Program: [CollectionsClass.java](CollectionsClass.java) (totals, topper, viva order, notice board)

## Why Do We Need a Utility Class?

Some jobs are needed again and again, on **any** list:

- sort it, reverse it, shuffle it,
- find the biggest or the smallest,
- count how many times a value appears,
- protect it from changes.

Where should this code live?

- Writing it ourselves in every program means the same loops again and again, each with its own bugs.
- Putting it inside every collection class would repeat it in `ArrayList`, `LinkedList`, `Vector` ...
- These jobs only need the **interfaces** (`List`, `Collection`), so one copy of the code can work for every class.

Java's answer is one **utility class** that holds these algorithms as `static` methods: `Collections`.
It is the same idea as `Math` (helpers for numbers) and `Arrays` (helpers for arrays).

## What Is It?

- **`Collections` class:** A utility class in `java.util` with only **static** methods that work on collections (sort, search, max, min, read-only views ...).
- **Utility class:** A class that only groups helper methods. We never create its object (its constructor is `private`); we call `Collections.method(...)`.
- **Unmodifiable (read-only) view:** A wrapper around a collection that allows reading but throws an error on any change.

> ⭐ **Remember:** `Collections` (with s) = helper class with static methods. `Collection` (no s) = interface (3.1).

## How Do We Use It?

The methods fall into four groups.

### 1. Changing the order

| Method | Does | Result in our program |
| --- | --- | --- |
| `sort(list)` / `sort(list, comp)` | Sort (natural / by comparator) | `[244, 412, 438, 438, 465]` |
| `reverse(list)` | Reverse the current order | `[465, 438, 438, 412, 244]` |
| `shuffle(list)` | Random order | Viva order (changes every run) |
| `swap(list, i, j)` | Swap two positions | |

### 2. Searching and counting

| Method | Does | Result in our program |
| --- | --- | --- |
| `max(c)` / `min(c)` | Biggest / smallest (natural order) | `465` / `244` |
| `max(c, comp)` | Biggest by a comparator | Topper: `Somya 465` |
| `frequency(c, x)` | How many times x appears (uses `equals()`) | `438` → 2 |
| `binarySearch(list, x)` | Index of x in a **sorted** list | `412` → 1 |

**Why must the list be sorted for `binarySearch()`?** Binary search looks at the **middle** element and throws away the half where x cannot be: if x is smaller, it can only be in the left half. That logic is only true when the list is sorted. On an unsorted list it gives a **wrong answer, without any error**.

### 3. Wrappers (protecting a collection)

| Method | Does |
| --- | --- |
| `unmodifiableList(list)` (also `Set`, `Map`) | Read-only view |
| `synchronizedList(list)` (also `Set`, `Map`) | Thread-safe wrapper (Module V) |

### 4. Ready-made small collections

`emptyList()`, `singletonList(x)`, `nCopies(n, x)`, `addAll(c, x, y, z)`.

## Read-only Collections: Three Kinds

The merit list goes on the **notice board**; nobody should be able to change it there.

```java
List<String> noticeBoard = Collections.unmodifiableList(merit);
// noticeBoard.add("Sakshi");   // ERROR at run time: UnsupportedOperationException
```

An unmodifiable list is a **view**: it does not copy the data, it looks through to the original. If `merit` changes, the notice board shows the change too. Students often confuse the three ways to get a read-only list:

| | `Collections.unmodifiableList(list)` | `List.copyOf(list)` | `List.of(a, b, c)` |
| --- | --- | --- | --- |
| What it is | A read-only **view** of the original | A read-only **copy** | A new fixed list |
| Original changes later | **Shows** the change | Not affected | – |
| Change through it | `UnsupportedOperationException` | `UnsupportedOperationException` | `UnsupportedOperationException` |

> ⭐ **Remember:** "Unmodifiable" means **you** cannot change it through this reference. It does not mean the data can never change: the owner of the original list still can.

## Common Mistakes

- `binarySearch()` on an unsorted list (wrong answer, no error).
- Expecting an unmodifiable view to be a frozen copy.
- Writing `new Collections()`: not allowed, call the static methods directly.

## Summary

- `Collections` is a utility class of static algorithms that work on any collection.
- Order (`sort`, `reverse`, `shuffle`), search (`max`, `min`, `frequency`, `binarySearch`), wrappers (`unmodifiable…`, `synchronized…`).
- `binarySearch()` needs a sorted list.
- An unmodifiable list is a view, not a copy.

Previous: [3.8 Sorting](../_3.8_comparable_comparator/README.md) · Next: [3.10 Generics](../_3.10_generics/README.md)
