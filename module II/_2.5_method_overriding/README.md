# 2.5 Method Overriding (Runtime Polymorphism)

Example: [MethodOverriding.java](MethodOverriding.java) (`Person` → `Student`, `Teacher`)

## Overriding

- Child class writes its **own version** of a parent method.
- Same name, same parameters.
- Child object calls the **child's** version.

> **The bell rings. What does each person do?**

```java
class Person {
    void work() { System.out.println(name + " is in the college"); }
}
class Student extends Person {
    @Override
    void work() { System.out.println(name + " attends the Java class"); }
}
class Teacher extends Person {
    @Override
    void work() { System.out.println(name + " teaches Java"); }
}
```

- If a child does not override a method, the parent's version is used (`Teacher` uses `Person`'s `show()`).

## `@Override`

- Tells the compiler: "this method overrides a parent method".
- If the name or parameters are wrong (`wrok()`), the compiler gives an **error**.
- Not compulsory, but always write it.

## Rules

| Rule | Example |
| --- | --- |
| Same name and same parameters | `work()` → `work()` |
| Same return type, or a **child** type (covariant) | `Person copy()` → `Student copy()` |
| Access same or **wider**, never narrower | `protected` → `public` ✔, `protected` → `private` ✘ |
| Cannot throw new or broader checked exceptions | (Module IV) |

## Cannot Be Overridden

| Method | Why |
| --- | --- |
| `static` | Belongs to the class. Same method in child **hides** it (method hiding): `rules()` |
| `private` | Not visible in child. Same method in child is a new method: `password()` |
| `final` | Locked (2.8): `college()` |
| Constructor | Not inherited. |

## Calling the Parent Version

- Use `super.method()` inside the overriding method: `Student.show()` calls `super.show()` (see 2.3).
