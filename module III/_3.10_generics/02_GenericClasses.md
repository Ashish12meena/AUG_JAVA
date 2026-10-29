# 3.10 Generics, Part 2: Generic Classes

Programs: [Pair.java](Pair.java) (the generic class), [GenericClasses.java](GenericClasses.java) (uses it)

## Why Write a Generic Class?

The college portal keeps needing "two values that belong together":

- roll number and name: `101` and `"Hita"`
- name and total: `"Somya"` and `465`
- name and fee: `"Sakshi"` and `150000.0`

The **logic** is the same each time: store two values, give them back. Only the **types** change.
Part 1 showed the two bad options (one class per combination, or `Object` with casts).
A generic class gives a third option: write the class **once**, and let the user of the class choose the types.

## What Is a Generic Class?

A **generic class** is a class that declares one or more **type parameters** after its name.
Inside the class, a type parameter can be used anywhere a normal type is used:

- as the type of a field,
- as the type of a constructor or method parameter,
- as a return type.

```java
public class Pair<K, V> {      // K and V are declared here
    private K key;             // field of type K
    private V value;           // field of type V

    public Pair(K key, V value) { ... }

    public K getKey()   { return key; }     // returns a K
    public V getValue() { return value; }   // returns a V
}
```

`Pair` does not know, and does not care, what `K` and `V` will be. That is decided by whoever creates a `Pair`.

## How Does It Work?

When we write `Pair<Integer, String>`, the compiler treats the class **as if** every `K` were `Integer` and every `V` were `String`:

| We write | Compiler checks it as |
| --- | --- |
| `Pair<Integer, String> roll = new Pair<>(101, "Hita");` | constructor takes `(Integer, String)` |
| `roll.getKey()` | returns `Integer` |
| `roll.getValue()` | returns `String` |

So the same class is checked **separately for each use**. A `Pair<Integer, String>` can never be given a `String` key.

> ⭐ **Remember:** The class is written once, but the checking happens for every use. One generic class replaces a whole family of copied classes.

## How Do We Use It?

### Creating objects

```java
Pair<Integer, String> roll   = new Pair<>(101, "Hita");
Pair<String, Integer> result = new Pair<>("Somya", 465);
Pair<String, Double>  fee    = new Pair<>("Sakshi", 150000.0);
```

- The `<>` on the right is the **diamond**. Java copies the type arguments from the left side.
- `101`, `465` and `150000.0` are autoboxed to `Integer` and `Double` (3.2).

### Reading values: no cast

```java
int marks = result.getValue();    // the compiler knows V is Integer
```

### Generic interfaces

Interfaces can be generic in exactly the same way. We have already used several:

| Interface | Type parameter | Meaning |
| --- | --- | --- |
| `List<E>` | `E` = element type | a list of `E` |
| `Map<K, V>` | key type, value type | `K` → `V` |
| `Comparable<T>` | the type to compare with | 3.8 |
| `Comparator<T>` | the type being compared | 3.8 |

When a class implements a generic interface, it fills in the type: `class Student implements Comparable<Student>`.

## Important Rules

- Type parameters are declared **once**, right after the class name: `class Pair<K, V>`.
- A class can have **any number** of type parameters, separated by commas.
- Type arguments must be **reference types**: `Pair<int, String>` is an error, use `Integer`.
- The type parameter belongs to **each object**, so it cannot be used in a `static` field (Part 6 explains why).

## Common Mistakes

- **Using `<>` on the left**: `Pair<> p = ...` is not allowed. The diamond is only for the right side after `new`.
- **Expecting different types to mix**: `roll = new Pair<>("Hita", 101);` fails, because `roll` is a `Pair<Integer, String>`, not a `Pair<String, Integer>`.

## Summary

- A generic class has type parameters after its name: `class Pair<K, V>`.
- The user of the class picks the real types: `Pair<Integer, String>`.
- The compiler checks every use separately, and no casts are needed when reading.
- `List`, `Map`, `Comparable` and `Comparator` are generic types we already use.

Previous: [Part 1](01_WhyGenerics.md) · Next: [Part 3: Generic Methods](03_GenericMethods.md)
