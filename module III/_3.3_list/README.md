# 3.3 List

A **List** keeps elements in order and lets us reach any position by its index, like a register.
The topic has one interface and several classes that store the data in very different ways, so it is taught in four parts.

## Reading Order

| Part | Notes | Question it answers | Program |
| --- | --- | --- | --- |
| 1 | [01_ListInterface.md](01_ListInterface.md) | What makes a List different from other collections? | [Lists.java](Lists.java) |
| 2 | [02_ArrayList.md](02_ArrayList.md) | How can an array grow? What happens inside `ArrayList`? | [CustomArrayList.java](CustomArrayList.java) |
| 3 | [03_LinkedList.md](03_LinkedList.md) | Why another List? When is `LinkedList` better? | [CustomLinkedList.java](CustomLinkedList.java) |
| 4 | [04_LegacyAndFixedLists.md](04_LegacyAndFixedLists.md) | What are `Vector` and `Stack`? How do I make a list that cannot change? | [Lists.java](Lists.java) (last part) |

## The Big Picture

```
              List  (interface: order + index + duplicates)
        /         |           \
  ArrayList   LinkedList     Vector  (legacy)
  (array)     (nodes)          |
                             Stack   (legacy)
```

- **One interface, many classes.** All of them have `get(i)`, `add(i, x)` ... but the **speed** of each method depends on how the data is stored inside.
- Default choice: **`ArrayList`**.

## Run

```
javac Lists.java
java Lists
```

`CustomArrayList` and `CustomLinkedList` have no `main()`; they are "build your own" classes to read with Part 2 and Part 3.
