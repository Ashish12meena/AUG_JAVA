# 3.6 Map

A **Map** finds a value directly by its **key**, like finding a student by roll number. It is one of the most used structures in real programs and a favourite interview topic (especially "how does `HashMap` work?"), so it is taught in three parts.

## Reading Order

| Part | Notes | Question it answers | Program section |
| --- | --- | --- | --- |
| 1 | [01_Map.md](01_Map.md) | Why a Map? How do I put, get and visit pairs? | [Maps.java](Maps.java): sections 1, 2, 3 |
| 2 | [02_HowHashMapWorks.md](02_HowHashMapWorks.md) | How does `get(103)` find the student without a loop? | (theory; builds on 3.4 Part 2) |
| 3 | [03_MapImplementations.md](03_MapImplementations.md) | `HashMap`, `LinkedHashMap`, `TreeMap` or `Hashtable`? | [Maps.java](Maps.java): sections 4, 5 |

## The Big Picture

```
                     Map<K, V>  (interface: key -> value, keys unique)
          /             |              |              \
     HashMap      LinkedHashMap     TreeMap        Hashtable
    (no order)   (insertion order) (sorted keys)   (legacy)
```

> Map is part of the Collections Framework but is **not** a `Collection`.

## Run

```
javac Maps.java
java Maps
```
