# 3.10 Generics, Part 5: Wildcards

Program: [Wildcards.java](Wildcards.java)

## Why Do We Need Wildcards?

### A surprise

We want a method that adds up any list of numbers. This looks correct:

```java
static double sumOnlyNumbers(List<Number> list) { ... }

List<Integer> marks = List.of(85, 92, 78, 88, 95);
sumOnlyNumbers(marks);     // ERROR: List<Integer> cannot be converted to List<Number>
```

`Integer` **is a** `Number`, so why is a `List<Integer>` **not** a `List<Number>`?

### The reason: it would break type safety

Imagine Java allowed it:

```java
List<Integer> marks = new ArrayList<>();
List<Number> nums = marks;      // suppose this were allowed
nums.add(3.5);                  // a Double is a Number, so this is legal for List<Number>
Integer m = marks.get(0);       // but marks should hold only Integers! -> crash
```

`nums` and `marks` are the **same** list. Allowing the assignment would let a `Double` sneak into a list of `Integer`s, exactly the problem generics were made to stop.

So Java follows this rule:

> ⭐ **Remember:** Even if `Integer` is a `Number`, `List<Integer>` is **not** a `List<Number>`. Generic types are **invariant**: no parent-child relation between them, whatever their type arguments are.

### Arrays behave differently (and that is a weakness)

Arrays **do** allow this: an `Integer[]` can be used as a `Number[]` (arrays are **covariant**).
The mistake is then caught only at run time with an `ArrayStoreException`. Generics chose the safer path: catch it at compile time.

### So we still have a problem

We need one `sum()` method that accepts `List<Integer>`, `List<Double>`, `List<Number>` ... without letting anyone break the list. That is what **wildcards** solve.

## What Is a Wildcard?

A **wildcard** `?` means "**some** type, I don't know which". It is used in the type of a parameter, field or variable, never to create an object.

There are three forms:

| Wildcard | Read as | Accepts |
| --- | --- | --- |
| `List<?>` | list of **some** type (unbounded) | `List<String>`, `List<Integer>`, anything |
| `List<? extends Number>` | list of **some kind of Number** (upper bound) | `List<Number>`, `List<Integer>`, `List<Double>` |
| `List<? super Integer>` | list of **Integer or a parent of it** (lower bound) | `List<Integer>`, `List<Number>`, `List<Object>` |

## How Does It Work?

The key question is: **what is safe to do with such a list?** The compiler allows only the safe operations.

### `? extends Number`: safe to READ, not to add

```java
static double sum(List<? extends Number> list) {
    for (Number n : list) { ... }     // OK: whatever it is, it is some Number
    // list.add(50);                  // ERROR: the list may really be List<Double>
}
```

- Reading is safe: every element is at least a `Number`.
- Adding is **not** safe: we do not know the exact type. If it is really a `List<Double>`, adding an `Integer` would break it.

### `? super Integer`: safe to ADD Integers, reading gives only Object

```java
static void addPassMarks(List<? super Integer> list) {
    list.add(40);    // OK: the list holds Integer or a parent, so an Integer always fits
    list.add(50);
}
```

- Adding an `Integer` is safe: a `List<Integer>`, `List<Number>` or `List<Object>` can all hold an `Integer`.
- Reading gives back only `Object`, because the exact type is unknown.

### `?`: when the type does not matter

```java
static void showCount(List<?> list) {
    System.out.println("Items: " + list.size());   // size() does not care about the type
}
```

## Summary of What Is Allowed

| Parameter type | Read elements as | Can add? | Use it when the method ... |
| --- | --- | --- | --- |
| `List<?>` | `Object` | No (only `null`) | does not care about the type |
| `List<? extends Number>` | `Number` | No (only `null`) | only **reads** from the list |
| `List<? super Integer>` | `Object` | Yes, `Integer` | only **writes** into the list |
| `List<Number>` | `Number` | Yes, any `Number` | reads and writes, and only `List<Number>` is wanted |

## The PECS Rule

A short rule to choose between `extends` and `super`:

> ⭐ **Remember: PECS = Producer `Extends`, Consumer `Super`.**
> If the list **produces** (gives) data to your method → `? extends T`.
> If the list **consumes** (takes) data from your method → `? super T`.

- `sum()` takes numbers **out** of the list → the list is a producer → `extends`.
- `addPassMarks()` puts numbers **into** the list → the list is a consumer → `super`.

## Wildcard or Type Parameter?

| Use | When |
| --- | --- |
| `<T>` (type parameter) | you need to **name** the type again, e.g. return a `T`, or say two parameters have the **same** type |
| `?` (wildcard) | you use the type only once and never need to refer to it |

```java
static <T> T lastOf(List<T> list)        // needs T again for the return type
static void showCount(List<?> list)      // never needs the type again
```

## Common Mistakes

- **Creating with a wildcard**: `new ArrayList<?>()` is not allowed. A wildcard only describes a type, it cannot be created.
- **Trying to add to `? extends`**: always a compile error (except `null`). Use `? super` when the method must add.
- **Thinking `List<Object>` accepts every list**: it does not. `List<?>` is the "any list" type.

## Summary

- Generic types are invariant: `List<Integer>` is not a `List<Number>`. This protects the list.
- Wildcards make a parameter accept a **family** of lists, while still keeping it safe.
- `? extends T` → read; `? super T` → write; `?` → type not needed. Remember PECS.

Previous: [Part 4](04_BoundedTypes.md) · Next: [Part 6: Type Erasure](06_TypeErasure.md)
