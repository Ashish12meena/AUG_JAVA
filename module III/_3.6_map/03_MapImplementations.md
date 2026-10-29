# 3.6 Map, Part 3: Choosing a Map

Program: [Maps.java](Maps.java) (sections 4 and 5: same data in three maps, `TreeMap` methods)

## Why Are There Several Maps?

All maps keep the same rule (unique keys → values). They differ in **how they store the pairs**, which changes two things we care about:

- **Order:** when we print or loop, in what order do the pairs come out?
- **Extra abilities:** for example, "which roll numbers are below 103?"

Same idea as the three Sets in 3.4. In fact each Set is built on the matching Map.

## What Are the Map Classes?

- **`HashMap`:** Uses hashing (Part 2). Fastest, **no fixed order**. Allows one `null` key.
- **`LinkedHashMap`:** A `HashMap` that also keeps a linked list of entries, so it remembers the **insertion order**.
- **`TreeMap`:** Stores entries in a balanced (red-black) tree, so the **keys are always sorted**. Keys must be comparable (3.8).
- **`Hashtable`:** Old (**legacy**, Java 1.0) hash map. **Synchronised** (slower) and allows **no** `null` key or value.

### Same data, three orders

```java
// put in this order: Somya 465, Hita 438, Ritika 412
```

| Map | Output |
| --- | --- |
| `HashMap` | `{Ritika=412, Somya=465, Hita=438}` (any order) |
| `LinkedHashMap` | `{Somya=465, Hita=438, Ritika=412}` (order of `put()`) |
| `TreeMap` | `{Hita=438, Ritika=412, Somya=465}` (keys A to Z) |

## How Does `TreeMap` Help?

Because the keys are always sorted, a `TreeMap` can answer range questions (from the interfaces `SortedMap` and `NavigableMap`):

| Method | Meaning | Result for roll numbers 101–104 |
| --- | --- | --- |
| `firstKey()` / `lastKey()` | Smallest / biggest key | `101` / `104` |
| `headMap(103)` | Pairs with keys **below** 103 | keys `[101, 102]` |
| `tailMap(103)` | Pairs with keys 103 **and above** | keys `[103, 104]` |
| `floorKey(k)` / `ceilingKey(k)` | Nearest key ≤ k / ≥ k | |

In [Maps.java](Maps.java) the grade count also uses a `TreeMap`, so grades print in A, B, C ... order.

## Comparison

| | `HashMap` | `LinkedHashMap` | `TreeMap` | `Hashtable` |
| --- | --- | --- | --- | --- |
| Order | None | Insertion | Sorted keys | None |
| Speed of `get`/`put` | Fastest, O(1) | Fast, O(1) | Slower, O(log n) | Slow (synchronised) |
| `null` key | One allowed | One allowed | Not allowed | Not allowed |
| `null` values | Allowed | Allowed | Allowed | Not allowed |
| Keys need | `equals` + `hashCode` | `equals` + `hashCode` | `compareTo` / `Comparator` | `equals` + `hashCode` |
| Synchronised | No | No | No | Yes |
| Use | Default choice | Need insertion order | Need sorted keys / ranges | Old code only |

> ⭐ **Remember:** `HashMap` allows one `null` key; `Hashtable` allows **no** `null` key or value. `Hashtable` is legacy: for many threads use `ConcurrentHashMap` (Module V).

## Choosing a Map

| Need | Use |
| --- | --- |
| Fast lookup, order does not matter | `HashMap` |
| Remember the order pairs were added | `LinkedHashMap` |
| Keys in sorted order, or "keys below/above" questions | `TreeMap` |
| Many threads (Module V) | `ConcurrentHashMap` |

## Common Mistakes

- Expecting `HashMap` to print in the order of `put()`.
- Using a key class without `compareTo()` in a `TreeMap` → `ClassCastException` at run time.
- Choosing `Hashtable` because it "sounds like HashMap". It is legacy.

## Summary

- All maps: unique keys → values. They differ in order and extra methods.
- `HashMap` (no order), `LinkedHashMap` (insertion order), `TreeMap` (sorted keys + range methods), `Hashtable` (legacy).
- Default: `HashMap`.

Previous: [Part 2](02_HowHashMapWorks.md) · Back to [3.6 Map](README.md) · Next topic: [3.7 Iterators](../_3.7_iterators/README.md)
