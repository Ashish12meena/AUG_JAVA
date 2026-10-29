# 3.4 Set

A **Set** stores each element only once. The topic has two ideas: what a Set is and which one to choose, and **how** a Set decides that two objects are "the same". The second idea (`equals()` and `hashCode()`) is used again in Map (3.6), so it gets its own part.

## Reading Order

| Part | Notes | Question it answers | Program section |
| --- | --- | --- | --- |
| 1 | [01_Set.md](01_Set.md) | Why a Set? `HashSet`, `LinkedHashSet` or `TreeSet`? | [Sets.java](Sets.java): sign-up sheet, scholarship cut-off |
| 2 | [02_EqualsAndHashCode.md](02_EqualsAndHashCode.md) | How does a Set find duplicates? Why must I override `equals()` and `hashCode()`? | [Sets.java](Sets.java): hostel list |

## The Big Picture

```
                Set  (interface: no duplicates)
        /                |                  \
   HashSet         LinkedHashSet           TreeSet
   (hash table)    (hash table + order)    (sorted tree)
   any order       insertion order         sorted order
```

## Run

```
javac Sets.java
java Sets
```
