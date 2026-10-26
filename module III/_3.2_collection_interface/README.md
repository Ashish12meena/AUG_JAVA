# 3.2 The `Collection` Interface

Example: [CollectionMethods.java](CollectionMethods.java) (Java and Python workshops)

## The Story

- Hita, Sakshi, Somya join the **Java workshop**. Somya, Ritika, Disha join the **Python workshop**.
- Who joined both? Who joined only Java? Everyone together?
- `Collection` methods answer these without writing loops.

## Definitions

- **`Collection` interface:** The root interface of `List`, `Set` and `Queue`. It has the methods that every collection has.
- **Autoboxing:** Java automatically changes a primitive into its wrapper object (`int` → `Integer`) when it goes into a collection.
- **Unboxing:** The opposite: wrapper object → primitive (`Integer` → `int`).

## Example

```java
Collection<String> java = new ArrayList<>();
java.add("Hita");
java.add("Somya");
java.contains("Somya");     // true
```

## Common Methods

| Method | Does | Example result |
| --- | --- | --- |
| `add(x)` | Adds x (gives `true` if added) | `[Hita, Sakshi, Somya]` |
| `remove(x)` | Removes x | `remove("Sakshi")` → `[Hita, Somya]` |
| `contains(x)` | Is x there? | `true` |
| `size()` | How many | `3` |
| `isEmpty()` | Is it empty? | `true` after `clear()` |
| `clear()` | Removes everything | `[]` |

## Bulk Methods (whole collection at once)

`java = [Hita, Somya]`, `python = [Somya, Ritika, Disha]`

| Method | Meaning | Result |
| --- | --- | --- |
| `addAll(python)` | Add all of them | `[Hita, Somya, Somya, Ritika, Disha]` |
| `retainAll(python)` | Keep only common ones | `[Somya]` (in both) |
| `removeAll(python)` | Remove all of them | `[Hita]` (only Java) |
| `containsAll(python)` | Are all of them there? | `true` / `false` |

- Bulk methods **change** the collection they are called on. Work on a copy: `new ArrayList<>(java)`.

## `toArray()`

```java
Object[] arr = java.toArray();     // collection → normal array
```

## Autoboxing in Collections

```java
Collection<Integer> marks = new ArrayList<>();
marks.add(85);                  // int → Integer (autoboxing)
for (int m : marks) { ... }     // Integer → int (unboxing)
// Collection<int> x;           // ERROR: only objects
```

## Important Points

- `Collection` is an interface: `Collection<String> c = new ArrayList<>();`
- `Collection` has **no** `get(index)`. Index is only in `List`. Use for-each to visit.
- `remove(x)` and `contains(x)` use `equals()` to compare.
- A `List` keeps duplicates (Somya twice after `addAll`). For no duplicates → `Set` (3.4).
