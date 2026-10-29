# 3.8 Sorting with `Comparable` and `Comparator`

Java can sort Strings and numbers by itself, but not our `Student` objects. There are two ways to teach Java how to order objects, and they solve different problems, so the topic has two parts.

## Reading Order

| Part | Notes | Question it answers | Program section |
| --- | --- | --- | --- |
| 1 | [01_Comparable.md](01_Comparable.md) | How do I give a class its **one** natural order? | [Sorting.java](Sorting.java): the `Student` class, section 1 |
| 2 | [02_Comparator.md](02_Comparator.md) | How do I sort the same objects in **many** different ways? | [Sorting.java](Sorting.java): `ByTotal`, sections 2–6 |

## The Big Picture

```
"How do I compare two students?"
   |
   |-- ONE default order, written INSIDE the class   -> Comparable  (compareTo)
   |
   |-- ANY number of orders, written OUTSIDE         -> Comparator  (compare)
```

Both give the same kind of answer: a **negative** number, **0**, or a **positive** number.

## Run

```
javac Sorting.java
java Sorting
```
