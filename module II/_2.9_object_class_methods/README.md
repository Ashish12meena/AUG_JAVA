# 2.9 Methods of the `Object` Class

Example: [ObjectMethods.java](ObjectMethods.java) (`Person` → `Student` → `PgStudent`)

Every class gets these from `Object` (2.1). We override them to make them useful.

| Method | Default work | We override to |
| --- | --- | --- |
| `toString()` | `ClassName@hashcode` | Print the object's data |
| `equals(Object o)` | Same as `==` (same object?) | Compare the **data** |
| `hashCode()` | Number based on the object | Match `equals()` |
| `getClass()` | Gives the object's real class | (cannot override, it's `final`) |

## `toString()`

- `System.out.println(obj)` calls `obj.toString()` automatically.
- Default output `Student@1b6d3586` is useless. Override it → `101 Ritika [Student]`.

## `equals()`

- `==` with objects → are both references pointing to the **same** object?
- `equals()` → are both objects **equal in data**? (only after we override it)
- Two students with the same roll no are the same student.
- `String` already overrides `equals()`, that's why we use it for strings (1.4.9).

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Student)) return false;
    Student other = (Student) obj;
    return rollNo == other.rollNo;
}
```

## `hashCode()`

- A number for an object. Used by `HashSet`, `HashMap` (Module III) to find objects fast.
- **Contract:**
  - If `a.equals(b)` is true → `a.hashCode() == b.hashCode()` **must** be true.
  - Same hash code does **not** mean equal.
- So: **override `equals()` → also override `hashCode()`.** Here: `return rollNo;`

## `getClass()`

- Gives the **real** class of the object, even through a parent reference.
- `Student s4 = new PgStudent(...)` → `s4.getClass().getName()` gives `PgStudent`.

## `clone()` and `finalize()`

- `clone()`: makes a copy of an object. Class must implement `Cloneable` (2.11). Covered in Module IV.
- `finalize()`: was called before garbage collection. Now **deprecated**, don't use it.
