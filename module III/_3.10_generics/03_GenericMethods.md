# 3.10 Generics, Part 3: Generic Methods

Program: [GenericMethods.java](GenericMethods.java)

## Why Do We Need Generic Methods?

Sometimes the **class** has nothing to do with types, but **one method** does.

The office wants a helper that prints every item of a list, one per line:

- a list of names (`List<String>`),
- a list of results (`List<Pair<String, Integer>>`),
- a list of marks (`List<Integer>`).

Making the whole class generic just for this one method would be wrong: the class is not "a class of T".
Writing three methods (`printNames`, `printResults`, `printMarks`) would repeat the same loop.
What we need is a type parameter that belongs only to **this method**.

## What Is a Generic Method?

A **generic method** is a method that declares its **own** type parameter. It can be in a normal class or a generic class, and it can be `static` or not.

The type parameter is written in `< >` **just before the return type**:

```java
static <T> void printAll(List<T> list)
//     ^^^ declares T for this method only
```

Reading the line from left to right:

| Part | Meaning |
| --- | --- |
| `static` | normal modifier |
| `<T>` | "this method uses a type called T" |
| `void` | return type (could also be `T`) |
| `printAll(List<T> list)` | takes a list of that same T |

## How Does It Work?

### Java works out T by itself (type inference)

We do **not** write the type when calling the method. Java looks at the argument and decides what `T` is:

```java
printAll(names);      // names is List<String>                 -> T = String
printAll(results);    // results is List<Pair<String, Integer>> -> T = Pair<String, Integer>
```

This is called **type inference**. If needed, we can still write it by hand (the "type witness"):

```java
GenericMethods.<String>printAll(names);
```

### T can be the return type

When `T` is also the return type, the caller gets back the exact type that went in, with no cast:

```java
static <T> T lastOf(List<T> list) {
    return list.get(list.size() - 1);
}

String lastName = lastOf(names);                    // T = String
Pair<String, Integer> lastResult = lastOf(results); // T = Pair<String, Integer>
```

> ⭐ **Remember:** The `<T>` of a generic method goes **before the return type**. Without it, Java thinks `T` is the name of a class and gives "cannot find symbol".

## Generic Class vs Generic Method

| | Generic class | Generic method |
| --- | --- | --- |
| Declared as | `class Pair<K, V>` | `static <T> void printAll(...)` |
| Type parameter lives in | the whole class | that one method |
| Decided when | the object is created: `new Pair<Integer, String>(...)` | the method is called (inferred from arguments) |
| Can be static? | The type parameter cannot be used in static members | Yes, static generic methods are common |
| Use when | the class **stores** values of that type | only one method needs to work with many types |

## Real Examples in Java

Many ready-made methods are generic methods. We used them before knowing it:

- `Collections.sort(list)`, `Collections.max(list)` (3.9)
- `List.of(...)` (3.3)
- `Arrays.asList(...)`

## Common Mistakes

- **Forgetting `<T>`**: `static void printAll(List<T> list)` does not compile, `T` is not declared.
- **Putting `<T>` after the return type**: `static void <T> printAll(...)` is a syntax error.

## Summary

- A generic method declares its own type parameter before the return type.
- Java infers the type from the arguments, so the call looks like a normal call.
- Use a generic method when only one method needs to be general; use a generic class when the class stores that type.

Previous: [Part 2](02_GenericClasses.md) · Next: [Part 4: Bounded Types](04_BoundedTypes.md)
