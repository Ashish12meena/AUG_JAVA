# 3.11 Nested Classes, Part 1: Why Nested Classes?

## Why Do We Need Them?

Until now, every class was a **top-level** class: written on its own, visible to the whole program.
But some classes only make sense **as part of another class**. At VGU College:

- **Rules** belong to the college, and we can read them even without a particular college object.
- A **Department** cannot exist without its college; "CSE department" means "CSE department **of VGU College**".
- An **Invigilator** is needed only while one exam is being conducted.
- A **welcome message** is written once, for one moment, and never reused.

If all of these were top-level classes:

- **Clutter:** the program fills up with small classes that nobody else should use.
- **No connection:** nothing in the code says that `Department` belongs to `College`.
- **No access:** `Department` cannot use `College`'s `private` fields; we would have to make them public or add getters just for it, which weakens encapsulation.
- **Too much ceremony:** for a one-time piece of behaviour (the welcome message), we must create a whole named class in a separate place.

Java's answer: let a class be written **inside** another class, exactly where it belongs.

## What Is a Nested Class?

- **Nested class:** A class written inside another class.
- **Outer (enclosing) class:** The class that contains it.
- **Static nested class:** A nested class marked `static`. It needs **no** object of the outer class.
- **Inner class:** A nested class that is **not** static. Each object of it is connected to **one** object of the outer class.

Inner classes come in three forms, depending on **where** they are written:

| Kind | Written | Has a name? | College example |
| --- | --- | --- | --- |
| **Static nested class** | In the class body, with `static` | Yes | `College.Rules` |
| **Member inner class** | In the class body, no `static` | Yes | `College.Department` |
| **Local class** | Inside a method | Yes | `Invigilator` inside `conductExam()` |
| **Anonymous class** | Inside an expression, created at once | **No** | `new Greeting() { ... }` |

## How Do We Decide Which One?

Two questions decide the kind:

```
Is it needed only inside ONE method?
  ├─ yes -> used once, right here?   yes -> Anonymous class
  │                                  no  -> Local class
  └─ no  -> does it need an object of the outer class (its fields)?
              yes -> Inner (member) class
              no  -> Static nested class
```

## What Do They Give Us?

| Benefit | Meaning |
| --- | --- |
| **Logical grouping** | A class used only by `College` stays inside `College` |
| **Encapsulation** | Nested classes can use the outer class's **private** members, so those members stay private |
| **Readability** | The code is next to where it is used |
| **Less code for one-time use** | Anonymous classes create and use a class in one place; this is the classic way to write **callbacks** (3.12) |

> ⭐ **Remember:** A nested class can use the **private** members of its outer class. This is the main reason nested classes exist: close cooperation without giving up encapsulation.

## Important Points

- Each nested class gets its own `.class` file, named with a `$`: `College$Rules.class`, `College$Department.class`, `College$1Invigilator.class`, and `NestedClasses$1.class` for the first anonymous class.
- Nested classes can be `private`, `protected` or `public` (top-level classes can only be `public` or package-level).
- Do not over-use them: a class that is useful on its own should stay top-level.

## Summary

- A nested class is a class inside a class; use it when a class belongs to, or is only used by, another class.
- Four kinds: static nested, inner (member), local, anonymous.
- Benefits: grouping, access to private members, readability, short one-time classes.

Back to [3.11](README.md) · Next: [Part 2: Static Nested and Inner Classes](02_StaticAndInnerClasses.md)
