# 3.4 Set, Part 2: `equals()` and `hashCode()`

Program: [Sets.java](Sets.java) (hostel list) and the `Student` class at its top

## Why Do We Need Them?

The hostel list is a `HashSet<Student>`. By mistake, Hita is added twice:

```java
hostel.add(new Student(101, "Hita", 438));
hostel.add(new Student(101, "Hita", 438));   // same roll number
```

For Strings the Set removed duplicates by itself. Here, without any extra code, the hostel set has **2** Hitas.

**Why?** The two `new Student(...)` calls create **two different objects** in memory. By default, Java's `equals()` (from `Object`) only checks "is it the **same object**?" (like `==`). It knows nothing about roll numbers.

Only **we** know the rule of our college: **same roll number = same student**. We must tell Java this rule by overriding `equals()` and `hashCode()`.

## What Are They?

- **`equals(Object o)`:** decides whether two objects are **logically equal** (same meaning), not just the same object.
- **`hashCode()`:** returns an `int` "summary" of the object. Equal objects must give the same number. Hash-based collections use it to decide **where** to store the object.
- **Bucket:** one slot of the hash table inside `HashSet`/`HashMap`. The hash code picks the bucket.
- **Collision:** two **different** objects that land in the same bucket. Allowed and normal; `equals()` tells them apart.

## How Does a `HashSet` Find a Duplicate?

When we call `hostel.add(s)`:

1. **`hashCode()`** of `s` is calculated → it picks a **bucket**.
2. If the bucket is empty → `s` is added. Done. (No `equals()` call at all.)
3. If the bucket already has objects → **`equals()`** compares `s` with each of them.
   - Any of them equal → duplicate → not added, `add()` returns `false`.
   - None equal → `s` is added to that bucket (a collision).

```
add(Hita 101)  -> hashCode 101 -> bucket 5 (empty)        -> added
add(Hita 101)  -> hashCode 101 -> bucket 5 (has Hita 101) -> equals()? true -> NOT added
```

So `hashCode()` finds the **place** quickly, and `equals()` gives the **final answer**. This is why a `HashSet` is so fast: it only compares with the few objects in one bucket, not with everyone.

(Inside, a `HashSet` is actually a `HashMap` that uses the elements as keys. That is why the same rules come back in 3.6.)

## The Contract Between Them

These rules are called the **equals–hashCode contract**:

1. If `a.equals(b)` is `true`, then `a.hashCode() == b.hashCode()` **must** be true.
2. If the hash codes are equal, the objects may or may not be equal (a collision is allowed).
3. Calling `hashCode()` again on an unchanged object must give the same number.

> ⭐ **Remember:** Equal objects **must** have equal hash codes. Equal hash codes do **not** mean equal objects. Always override `equals()` and `hashCode()` **together**, using the **same** fields.

## What Goes Wrong If We Break the Contract?

Two `Student(101, "Hita", 438)` objects added to a `HashSet`:

| `equals()` overridden? | `hashCode()` overridden? | Set size | Why |
| --- | --- | --- | --- |
| No | No | 2 | Different objects, `equals()` says "not equal" |
| **Yes** | No | 2 (usually) | Different hash codes → different buckets → `equals()` is never even asked |
| No | **Yes** | 2 | Same bucket, but `equals()` still says "not equal" |
| **Yes** | **Yes** | **1** ✔ | Same bucket, and `equals()` says "equal" |

The second row is the classic trap: `equals()` alone is **not enough** for hash-based collections.

## How We Write Them

From the `Student` class in [Sets.java](Sets.java):

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;                    // same object
    if (!(obj instanceof Student)) return false;     // not a Student at all
    Student other = (Student) obj;
    return id == other.id;                           // our rule: same roll number
}

@Override
public int hashCode() {
    return id;                                       // same field as equals()
}
```

- The parameter type is `Object`, not `Student`; otherwise we **overload** instead of override, and the Set never calls it. `@Override` catches this mistake.
- Both methods use the **same field** (`id`), so the contract holds.
- For more than one field, `Objects.hash(id, name)` builds a good hash code.

## Important Rules

- Do not change a field used in `hashCode()` **after** the object is inside a `HashSet` or used as a `HashMap` key. Its bucket is now wrong and the set cannot find it any more. (That is why ids are good choices: they never change.)
- `String`, `Integer` and other Java classes already override both methods correctly.
- `TreeSet` does **not** use these two methods. It uses `compareTo()` / `Comparator`: if `compareTo()` returns 0, it is a duplicate (3.8).

## Common Mistakes

- Overriding only `equals()`.
- Writing `equals(Student other)` instead of `equals(Object obj)`.
- Using different fields in `equals()` and `hashCode()`.

## Summary

- Default `equals()` checks "same object", not "same meaning".
- `HashSet`: `hashCode()` picks the bucket, `equals()` decides inside the bucket.
- Equal objects must have equal hash codes; override both, with the same fields.
- The same rules apply to `HashMap` keys (3.6).

Previous: [Part 1](01_Set.md) · Back to [3.4 Set](README.md) · Next topic: [3.5 Queue and Deque](../_3.5_queue_deque/README.md)
