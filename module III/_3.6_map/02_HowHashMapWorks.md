# 3.6 Map, Part 2: How `HashMap` Works Inside

Builds on: [3.4 Set, Part 2: `equals()` and `hashCode()`](../_3.4_set/02_EqualsAndHashCode.md)

## Why Learn the Inside?

`register.get(103)` finds Somya **without looping** through all students. How is that possible?
Knowing the answer explains three rules that otherwise look random:

- why keys need `equals()` **and** `hashCode()`,
- why a key should never change,
- why `HashMap` has no fixed order.

It is also one of the most asked Java interview questions.

## What Is Inside a `HashMap`?

A `HashMap` is an **array of buckets** (also called a hash table).

- **Bucket:** one slot of the array. It holds the entries whose keys "belong" there.
- **Capacity:** the number of buckets. Default **16**.
- **Hashing:** using the key's `hashCode()` to calculate **which bucket** the entry goes into.

The idea: instead of searching **every** entry, calculate **where** the entry must be and look only there.

## How Does `put()` Work?

`register.put(101, hita)`:

1. **Hash the key:** `Integer.valueOf(101).hashCode()` is `101`.
2. **Pick the bucket:** the hash is turned into an index from 0 to 15. For 16 buckets this is like `101 % 16` = **5**.
3. **Look in bucket 5:**
   - Empty → store the entry (101 → Hita) there.
   - Not empty → compare the key with each key there using **`equals()`**.
     - Found an equal key → **replace** its value (this is why a second `put` replaces).
     - No equal key → add the new entry to the bucket.

```
index   bucket
  0     -
  ...
  5     [101 -> Hita]
  6     [102 -> Sakshi]
  7     [103 -> Somya]
  8     [104 -> Ritika]
  9     [105 -> Disha]
  ...
 15     -
```

## How Does `get()` Work?

`register.get(103)`: hash 103 → bucket **7** → compare keys in bucket 7 with `equals()` → found → return Somya.

Only **one bucket** is checked, however many students there are. That is why `get()` and `put()` are **O(1) on average**: about the same speed for 5 students or 50,000.

> ⭐ **Remember:** `hashCode()` finds the **bucket**; `equals()` finds the **key inside the bucket**. A key class needs both, and they must agree (equal keys → equal hash codes).

## Collisions

A **collision** happens when two different keys land in the **same bucket**. If roll number 117 joins: `117 % 16` = 5, the same bucket as 101.

- Both entries are kept in bucket 5, as a small **linked list**.
- `equals()` tells them apart on `get()`.
- If one bucket's list grows long (more than 8 entries, in a big enough map), Java 8+ turns it into a **balanced tree**, so searching that bucket stays fast.

Collisions are normal. A **good `hashCode()`** spreads keys over many buckets, so each bucket stays short.

## Load Factor and Rehashing

If we keep adding entries to 16 buckets, the buckets get crowded and slow. So `HashMap` grows:

- **Load factor = 0.75.** When the number of entries goes above **75%** of the capacity (12 entries for 16 buckets) ...
- **Rehashing:** ... it makes a new array with **double** the buckets (32) and moves every entry to its new bucket (the bucket index depends on the capacity, so it must be recalculated).

Rehashing is costly but rare, similar to `ArrayList` growing (3.3).

## The Rules This Explains

| Rule | Reason from the inside |
| --- | --- |
| Key classes must override `equals()` and `hashCode()` | `hashCode()` picks the bucket, `equals()` finds the key |
| A key must not change after `put()` | Its hash would change; `get()` would search the **wrong bucket** and return `null` |
| `HashMap` has no fixed order | Entries sit in bucket order, which depends on hash codes and on capacity (and changes after rehashing) |
| Good keys: `String`, `Integer`, ids | They are **immutable** and already have correct `equals()`/`hashCode()` |
| `HashSet` behaves the same way | A `HashSet` is a `HashMap` that stores elements as keys |

## Common Misconceptions

- **"Same hash code means same key."** No: that is a collision. `equals()` decides.
- **"HashMap keeps the order I put things in."** No: use `LinkedHashMap` for that (Part 3).

## Summary

- `HashMap` = an array of buckets (default 16).
- `put`/`get`: `hashCode()` → bucket → `equals()` inside the bucket.
- Collisions share a bucket (list, or tree when long).
- Above 75% full, the table doubles and entries are rehashed.

Previous: [Part 1](01_Map.md) · Next: [Part 3: Map Implementations](03_MapImplementations.md)
