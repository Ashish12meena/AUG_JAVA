# 3.11 Nested Classes, Part 2: Static Nested and Inner Classes

Program: [NestedClasses.java](NestedClasses.java) (`College.Rules` and `College.Department`)

Both kinds are written directly in the body of the outer class, like fields and methods (they are **members**). The only difference is the word `static`, but that one word changes a lot.

## A. Static Nested Class

### Why?

The college **rules** (pass mark 40%, attendance 75%) belong to VGU College, so they should be written inside `College`.
But reading the rules does not need any **particular** college object: no name, no data of one college. So the rules class should not be tied to an object.

### What is it?

**Static nested class:** A `static` class inside a class. It belongs to the **outer class itself**, not to any object of it, just like a static method.

```java
class College {
    private String name;

    static class Rules {
        void show() {
            System.out.println("Rules: pass mark 40%, attendance 75%");
        }
    }
}
```

### How does it work?

- It is created **without** an outer object, using the outer class name:

  ```java
  College.Rules rules = new College.Rules();
  ```

- It behaves like a normal top-level class that is simply **placed inside** another for grouping.
- It can use the outer class's **static** members (even private ones).
- It **cannot** directly use instance fields like `name`, because there is no `College` object to take `name` from.

## B. Inner (Member) Class

### Why?

A **Department** is different from the rules: "CSE department" has no meaning without saying **which college** it belongs to. A department must be able to say "I am the CSE department **of VGU College**", so it needs that college's `name`.

### What is it?

**Inner class:** A non-static class inside a class. Each inner object is **connected to one outer object** and can use all of that object's members, including private ones.

```java
class College {
    private String name;

    class Department {
        String deptName;

        void show() {
            System.out.println(deptName + " department of " + name);   // outer's private field
        }
    }
}
```

### How does it work?

- Every `Department` object secretly holds a **reference to its `College` object**. That is how it can read `name`.
- So it can **only** be created through an outer object:

  ```java
  College vgu = new College("VGU College");
  College.Department cse = vgu.new Department("CSE");   // cse belongs to vgu
  ```

  The unusual `vgu.new` syntax says "make a Department **for this college**".
- If an inner field has the same name as an outer field, the outer one is reached with `College.this.name`.

> ⭐ **Remember:** A static nested class needs **no** outer object: `new College.Rules()`. An inner class **must** have one: `vgu.new Department("CSE")`.

## Comparison

| | Static nested class | Inner (member) class |
| --- | --- | --- |
| Keyword | `static class Rules` | `class Department` |
| Belongs to | The outer **class** | One outer **object** |
| Needs an outer object to be created? | No | Yes |
| Create with | `new College.Rules()` | `vgu.new Department("CSE")` |
| Can use outer **instance** fields? | No (no object to read from) | Yes, all, even private |
| Can use outer **static** fields? | Yes | Yes |
| Hidden reference to outer object | No | Yes (`College.this`) |
| Like a ... | static method | instance method |

## Which One to Choose?

Ask: **does the nested class need the fields of an outer object?**

- No → make it `static`. This is the safer default: it is simpler, and it does not keep the outer object alive in memory.
- Yes → inner class.

## Common Mistakes

- `new College.Department("CSE")` → error: an inner class needs an outer object (`vgu.new ...`).
- Using `name` inside a static nested class → error: "non-static variable cannot be referenced from a static context".
- Making a class inner "just because", when it never uses outer fields. Make it `static`.

## Summary

- Static nested class: belongs to the outer class; created without an outer object; only outer static members.
- Inner class: belongs to one outer object; created with `outer.new`; uses all outer members.
- Prefer static unless the outer object's data is needed.

Previous: [Part 1](01_WhyNestedClasses.md) · Next: [Part 3: Local and Anonymous Classes](03_LocalAndAnonymousClasses.md)
