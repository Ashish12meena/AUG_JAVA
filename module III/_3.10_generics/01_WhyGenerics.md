# 3.10 Generics, Part 1: Why Generics?

Program: [WhyGenerics.java](WhyGenerics.java)

## Why Do We Need Generics?

### The problem before Java 5

Collections were added in Java 1.2, but generics only came in Java 5. In between, every collection stored its elements as `Object`.
Since every class is a child of `Object`, a list could hold **anything**.

That sounds flexible, but it caused three real problems:

1. **No checking when adding.** We meant to keep only names in the list, but `list.add(438)` was also accepted. The compiler had no way to know our intention.
2. **A cast on every read.** `get()` returned `Object`, so we had to write `(String) list.get(0)` every time we took something out.
3. **Errors at the wrong time.** If a wrong object was inside, the cast failed **while the program was running** with a `ClassCastException`. The crash happens far away from the line that made the mistake, often in front of the user.

```java
List raw = new ArrayList();
raw.add("Hita");
raw.add(438);                          // wrong, but accepted
String s = (String) raw.get(1);        // crashes at run time: ClassCastException
```

### A second problem: repeated code

Suppose we want a class that holds two related values, like roll number and name.
Without generics we have two bad choices:

- Write a new class for every combination: `IntStringPair`, `StringIntPair`, `StringDoublePair` ... (same code, copied again and again).
- Write one class using `Object` fields. It works for everything, but we are back to casts and run-time errors.

We wanted **both**: one piece of code that works for many types (**reuse**) **and** full checking by the compiler (**type safety**).

## What Are Generics?

**Generics** let us write classes, interfaces and methods with a **type parameter**, a placeholder for a type that is filled in when the code is used.

The idea is the same as a method parameter:

| | Method parameter | Type parameter |
| --- | --- | --- |
| Placeholder for | a **value** | a **type** |
| Written in | `( )` | `< >` |
| Declared as | `void greet(String name)` | `class Pair<K, V>` |
| Filled in when | the method is called: `greet("Hita")` | the type is used: `Pair<Integer, String>` |

Once we write `List<String>`, the compiler knows the list holds only `String`. It can now:

- **reject** a wrong `add()` at compile time,
- **insert the cast for us** when we `get()`.

> ⭐ **Remember:** Generics move type errors from **run time** to **compile time**. A compile error is cheap (we fix it before running). A run-time error is expensive (the program crashes).

## Terms to Know

| Term | Meaning | Example |
| --- | --- | --- |
| Generic type | A class or interface with type parameters | `List<E>`, `Pair<K, V>` |
| Type parameter | The placeholder in the declaration | `E`, `K`, `V`, `T` |
| Type argument | The real type given when using it | `String` in `List<String>` |
| Parameterized type | A generic type with its arguments filled in | `List<String>` |
| Raw type | A generic type used **without** `< >` | `List` |
| Diamond `<>` | Lets Java fill the type on the right from the left | `new ArrayList<>()` |

### Naming convention

Type parameters are single capital letters, so they never look like class names:

| Letter | Stands for | Seen in |
| --- | --- | --- |
| `T` | Type (any type) | `Pair<T>`, generic methods |
| `E` | Element | `List<E>`, `Set<E>` |
| `K`, `V` | Key, Value | `Map<K, V>` |
| `N` | Number | bounded types |
| `R` | Return type | `Function<T, R>` (3.12) |

## How It Changes Our Code

| Without generics (raw type) | With generics |
| --- | --- |
| `List list = new ArrayList();` | `List<String> list = new ArrayList<>();` |
| `list.add(438);` is accepted | `list.add(438);` is a **compile error** |
| `(String) list.get(0)` – cast needed | `list.get(0)` – no cast |
| Mistake found at **run time** | Mistake found at **compile time** |

## Raw Types

A **raw type** is a generic class used without its type argument: `List list`.

- It exists only so that old (pre-Java 5) code still compiles.
- The compiler warns: *"uses unchecked or unsafe operations"*.
- We lose all the checking that generics give.

> ⭐ **Remember:** Never write raw types in new code. Always give the type: `List<String>`, `Map<Integer, Student>`.

## Common Mistakes

- **`List<int>`**: type arguments must be **classes**, not primitives. Use the wrapper: `List<Integer>`.
- **Leaving out the type on the left**: `List list = new ArrayList<String>();` is still a raw `List`. The type on the **left** is what the compiler checks.
- **Thinking generics change the program at run time.** They do not; they are a compile-time check (Part 6).

## Summary

- Before generics, collections stored `Object`: no checking, casts everywhere, run-time crashes.
- Generics add a **type parameter** `<T>`, filled in with a **type argument** when used.
- Benefits: compile-time type safety, no casts, one piece of code for many types.

Next: [Part 2: Generic Classes](02_GenericClasses.md)
