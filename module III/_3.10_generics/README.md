# 3.10 Generics

Generics let us write **one** class or method that works for many types, while the compiler still checks every type.
It is a large topic, so it is taught in six short parts. Read them in order: each part answers a question raised by the one before.

## Reading Order

| Part | Notes | Question it answers | Program |
| --- | --- | --- | --- |
| 1 | [01_WhyGenerics.md](01_WhyGenerics.md) | What went wrong before generics? What is a type parameter? | [WhyGenerics.java](WhyGenerics.java) |
| 2 | [02_GenericClasses.md](02_GenericClasses.md) | How do I write my own generic class? | [Pair.java](Pair.java), [GenericClasses.java](GenericClasses.java) |
| 3 | [03_GenericMethods.md](03_GenericMethods.md) | What if only one method needs to be generic? | [GenericMethods.java](GenericMethods.java) |
| 4 | [04_BoundedTypes.md](04_BoundedTypes.md) | How do I allow only some types (only numbers)? | [BoundedTypes.java](BoundedTypes.java) |
| 5 | [05_Wildcards.md](05_Wildcards.md) | Why is `List<Integer>` not a `List<Number>`? What is `?` | [Wildcards.java](Wildcards.java) |
| 6 | [06_TypeErasure.md](06_TypeErasure.md) | What happens to generics at run time? What is not allowed? | [TypeErasure.java](TypeErasure.java) |

## The Big Picture

```
Problem (01)          ->  one list held anything; mistakes crashed at run time
Generic class (02)    ->  class Pair<K, V>        : the TYPE becomes a parameter
Generic method (03)   ->  static <T> void m(...)  : only one method is generic
Bounded type (04)     ->  <T extends Number>      : limit T to a family
Wildcard (05)         ->  List<? extends Number>  : accept a family of lists
Type erasure (06)     ->  types are checked, then removed before run time
```

## Run

`Pair.java` is shared by two programs, so compile everything together:

```
javac *.java
java GenericClasses
```

(`WhyGenerics.java` prints a note "uses unchecked or unsafe operations". That is expected: it shows a raw list on purpose.)
