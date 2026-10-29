# 3.10 Generics, Part 4: Bounded Types

Program: [BoundedTypes.java](BoundedTypes.java)

## Why Do We Need Bounded Types?

Mr. Verma wants an `average()` method that works for:

- Hita's marks: `List<Integer>` → 87.6
- fees: `List<Double>` → 135000.0

A plain generic method looks right, but it does not compile:

```java
static <T> double average(List<T> list) {
    double sum = 0;
    for (T n : list) {
        sum += n.doubleValue();     // ERROR: cannot find symbol doubleValue()
    }
    return sum / list.size();
}
```

**Why the error?** `T` could be **any** type, even `String` or `Student`. The compiler only allows methods that **every** possible `T` has, and that means only the methods of `Object`. `doubleValue()` is not one of them.

So we have a new problem: we want the method to be general, but **not too general**. It should accept every kind of number and nothing else.

## What Is a Bounded Type?

A **bounded type parameter** limits a type parameter to one family of types: a class and its children, or the classes that implement an interface.

```java
<T extends Number>
```

means "`T` can be `Number` or any subclass of `Number`": `Integer`, `Double`, `Long`, `Float` ...
`Number` is called the **upper bound** of `T`.

## How Does It Work?

Because the compiler now knows that every `T` **is a** `Number`, it allows all of `Number`'s methods on `T`:

```java
static <T extends Number> double average(List<T> list) {
    double sum = 0;
    for (T n : list) {
        sum += n.doubleValue();     // OK: every Number has doubleValue()
    }
    return sum / list.size();
}

average(marks);    // T = Integer -> 87.6
average(fees);     // T = Double  -> 135000.0
// average(names); // ERROR at compile time: String is not a Number
```

The bound works in **two directions**:

| Inside the method | At the call |
| --- | --- |
| We may use the bound's methods (`doubleValue()`) | Only types inside the bound are accepted (`String` is rejected) |

> ⭐ **Remember:** In a bound, `extends` is used for **both** classes and interfaces. There is no `implements` inside `< >`. `<T extends Comparable<T>>` means "T implements `Comparable`".

## More Than One Bound

A type parameter can have several bounds joined with `&`:

```java
<T extends Number & Comparable<T>>
```

means "T must be a `Number` **and** must be `Comparable`".

Rule: if one of the bounds is a **class**, it must come **first**; the rest must be interfaces (just like `extends` and `implements` in a class header).

## A Common Real Use

Finding the biggest element needs `compareTo()`, so the type must be comparable:

```java
static <T extends Comparable<T>> T biggest(List<T> list)
```

This works for `Integer`, `String`, and our `Student` once it implements `Comparable<Student>` (3.8). It is exactly how `Collections.max()` is declared (in a slightly longer form).

## Summary Table

| Declaration | `T` can be | Inside, we can call |
| --- | --- | --- |
| `<T>` | any class | only `Object` methods |
| `<T extends Number>` | `Number`, `Integer`, `Double` ... | `Number` methods (`doubleValue()` ...) |
| `<T extends Comparable<T>>` | any class that implements `Comparable` | `compareTo()` |
| `<T extends Number & Comparable<T>>` | numbers that are also comparable | both |

## Common Mistakes

- **Writing `implements`**: `<T implements Comparable<T>>` is a syntax error. Always `extends`.
- **Putting an interface before the class** in multiple bounds: `<T extends Comparable<T> & Number>` is an error.

## Summary

- Without a bound, `T` only has `Object`'s methods.
- `<T extends Number>` limits `T` to `Number` and its children, and unlocks `Number`'s methods.
- `extends` in generics means "is a", for classes and interfaces both.

Previous: [Part 3](03_GenericMethods.md) · Next: [Part 5: Wildcards](05_Wildcards.md)
