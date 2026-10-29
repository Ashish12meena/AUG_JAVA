# 3.6 Map, Part 1: What Is a Map?

Program: [Maps.java](Maps.java) (roll number register, grade count)

## Why Do We Need a Map?

The office asks: "Show me roll number **103**."

With a List, we must loop through the students and check each one's roll number until we find 103.
That has two problems:

- **It gets slower as the section grows.** For 5 students it does not matter; for 50,000 students in the whole university it does.
- **It repeats the same search code** everywhere we need a student by roll number.

What we really have is a **lookup** problem: we know the **key** (roll number) and want the matching **value** (the student). Real life is full of these:

| Key | Value |
| --- | --- |
| Roll number | Student |
| Word | Its meaning (a dictionary) |
| Subject code | Subject name |
| Grade | How many students got it |

A **Map** is made for exactly this: give it the key, it gives back the value directly, without us writing a loop.

## What Is a Map?

- **Map:** An object that stores **key → value** pairs. Each key is **unique** and maps to exactly one value. Values can repeat.
- **Key:** the thing we search with (roll number).
- **Value:** the thing we want back (the student).
- **Entry:** one key-value pair, of type `Map.Entry<K, V>`.

```java
Map<Integer, Student> register = new HashMap<>();
//  key type  value type
```

Characteristics:

- **Keys are unique.** Putting the same key again **replaces** the old value.
- **Values can repeat.** Hita and Disha can both have the value 438 in a "name → total" map.
- **Not a `Collection`.** A Collection holds single elements; a Map holds **pairs**. Methods like `add(x)` do not make sense for pairs, so `Map` has its own hierarchy.

> ⭐ **Remember:** In a Map, keys are unique but values are not. A second `put()` with the same key **replaces** the value. It does not add a second entry.

## How Do We Use It?

### Main methods

| Method | Does | In our program |
| --- | --- | --- |
| `put(k, v)` | Add the pair; if k exists, replace its value (returns the old value) | Sakshi re-checked: 244 → 310 |
| `get(k)` | Value for key k, or `null` if not there | `get(999)` → `null` |
| `getOrDefault(k, d)` | Value, or `d` if k is not there | Counting grades |
| `containsKey(k)` | Is the key there? | `102` → `true` |
| `containsValue(v)` | Is the value there? (slow: checks all values) | Anyone got 465? |
| `remove(k)` | Remove the pair | Disha moves to section B |
| `putIfAbsent(k, v)` | Put only if the key is free | 101 is taken → nothing changes |
| `size()` | Number of pairs | `4` |

```java
Map<Integer, Student> register = new HashMap<>();
register.put(101, new Student(101, "Hita", 438));
register.put(103, new Student(103, "Somya", 465));
register.get(103);          // Somya, no loop
```

### The three views: keys, values, pairs

A Map is not `Iterable`, so we cannot for-each over it directly. Instead it gives three **views**:

| View | Type | Gives |
| --- | --- | --- |
| `keySet()` | `Set<K>` | All keys (a Set, because keys are unique) |
| `values()` | `Collection<V>` | All values (not a Set, because values can repeat) |
| `entrySet()` | `Set<Map.Entry<K, V>>` | All pairs |

Notice how the types follow from the rules: keys are unique → `Set`; values may repeat → `Collection`.

To visit every pair, use `entrySet()`:

```java
for (Map.Entry<Integer, Student> e : register.entrySet()) {
    System.out.println(e.getKey() + " -> " + e.getValue().name);
}
```

`entrySet()` is better than looping over `keySet()` and calling `get()` each time, because it does not search again for every key.

### A common pattern: counting

How many students got each grade? The grade is the key, the count is the value:

```java
for (String g : grades) {
    gradeCount.put(g, gradeCount.getOrDefault(g, 0) + 1);   // 0 the first time
}
// {A=1, B=3, D=1}
```

## Common Mistakes

- **Treating `get()` returning `null` as "key not there".** It can also mean the key is there with a `null` value. Use `containsKey()` when it matters.
- **Unboxing a missing value:** `int c = gradeCount.get("C");` → `NullPointerException`, because there is no "C" and `null` cannot become an `int`. Use `getOrDefault()`.
- **Changing a key object after putting it in the map.** The map can no longer find it (Part 2).

## Summary

- A Map stores key → value pairs; keys are unique, values can repeat.
- `put` adds or replaces, `get` finds by key, `remove` deletes.
- Visit with `keySet()`, `values()` or (best) `entrySet()`.
- Map is not a Collection.

Back to [3.6 Map](README.md) · Next: [Part 2: How HashMap Works](02_HowHashMapWorks.md)
