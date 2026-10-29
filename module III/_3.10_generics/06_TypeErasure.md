# 3.10 Generics, Part 6: Type Erasure and Restrictions

Program: [TypeErasure.java](TypeErasure.java)

## Why Does Type Erasure Exist?

When generics arrived in Java 5, millions of lines of Java code were already running, written with raw `List` and `Object`.
Java had to add generics **without breaking any of that code**, and without changing the JVM.

The solution: generics would be a **compiler feature only**. The compiler checks all the types, then removes them, so the compiled `.class` file looks just like old, non-generic code. Old and new code could then work together.

This design choice is called **type erasure**, and it explains every "strange" restriction of generics.

## What Is Type Erasure?

**Type erasure** is the process in which the compiler, after checking the generic types, **removes** them from the compiled code:

1. Every type parameter is replaced by its **bound**: `T` becomes `Object`, and `T extends Number` becomes `Number`.
2. The type arguments are dropped: `List<String>` becomes just `List`.
3. **Casts are inserted** wherever a value is read, so the code still gets the right type.

## How Does It Work?

What we write and what the compiler effectively produces:

```java
// We write
List<String> names = new ArrayList<>();
names.add("Hita");
String first = names.get(0);

// After erasure (what runs)
List names = new ArrayList();
names.add("Hita");
String first = (String) names.get(0);    // cast added by the compiler
```

The cast is still there, but now it is **guaranteed** to succeed, because the compiler already checked that only `String`s go in.

For our own class:

| We write | After erasure |
| --- | --- |
| `class Pair<K, V> { K key; V value; }` | `class Pair { Object key; Object value; }` |
| `<T extends Number> double average(List<T> list)` | `double average(List list)` with `T` → `Number` |

So at run time, `List<String>` and `List<Integer>` are the **same class**:

```java
names.getClass() == marks.getClass()     // true: both are just ArrayList
```

> ⭐ **Remember:** Generics exist only at **compile time**. At run time there is no `<String>` or `<Integer>`, only the plain class.

## Restrictions (and Why Each One Exists)

Because the type is gone at run time, anything that needs to **know `T` while running** is not allowed:

| Not allowed | Why |
| --- | --- |
| `List<int>` | After erasure `T` becomes `Object`; a primitive is not an object. Use `Integer`. |
| `new T()` | At run time `T` is just `Object`; Java cannot know which class to create. |
| `new T[10]` | Same reason: the array's real type is unknown at run time. |
| `static T value;` | A static field is shared by **all** objects, but each object can have a different `T` (`Pair<Integer,...>`, `Pair<String,...>`). |
| `obj instanceof List<String>` | At run time a `List<String>` cannot be told apart from a `List<Integer>`. Only `instanceof List` (or `List<?>`) works. |
| `void m(List<String> a)` and `void m(List<Integer> a)` in one class | After erasure both become `m(List a)`: the same method twice. |

## Important Points

- Erasure keeps new generic code **compatible** with old non-generic code.
- The compiler adds the casts for us; that is why we never write them.
- A raw type warning ("unchecked") means the compiler cannot guarantee those inserted casts will succeed.

## Common Misconceptions

- **"`ArrayList<String>` is a different class from `ArrayList<Integer>`."** No: one class, `ArrayList`. The difference exists only for the compiler.
- **"Generics make the program slower or faster."** No: after erasure the code is the same as hand-written code with casts.

## Summary

- Type erasure: check the types, then remove them; replace `T` with its bound and insert casts.
- It exists for backward compatibility with pre-Java 5 code.
- Every generics restriction comes from the same fact: **`T` is unknown at run time**.

Previous: [Part 5](05_Wildcards.md) · Back to [3.10 Generics](README.md)
