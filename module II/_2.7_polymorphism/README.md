# 2.7 Polymorphism and Dynamic Method Dispatch

Example: [Polymorphism.java](Polymorphism.java) (`Person` → `Student`, `Teacher`)

- **Polymorphism:** one thing, many forms. (**Poly** = many, **morph** = forms.)
- One parent reference can point to different child objects and behave differently.

| Type | How | Decided at |
| --- | --- | --- |
| Compile-time | Overloading (2.4) | Compile time |
| Runtime | Overriding + upcasting | Run time |

## Upcasting

- Parent reference → child object.
- Done automatically.

```java
Person p = new Student("Ritika");   // upcasting
```

- Using `p` we can call **only** methods that `Person` has (`p.showMarks()` is an error).

## Dynamic Method Dispatch

- Which overridden method runs is decided at **run time** by the **object**, not the reference.

```java
Person p = new Student("Ritika");
p.work();                  // Ritika attends the Java class

p = new Teacher("Mr. Verma");
p.work();                  // Mr. Verma teaches Java
```

- Same call `p.work()`, different work. Ask: **"Who decides what `work()` does?"** → the real object.
- Very useful with an array of parent type: the bell rings, one loop, each person does their own work.

## Static vs Dynamic Binding

| Static (early) binding | Dynamic (late) binding |
| --- | --- |
| Compile time | Run time |
| Overloaded, `static`, `private`, `final` methods | Overridden methods |
| Uses reference type | Uses object type |

## Variables Are Not Polymorphic

- Fields are chosen by the **reference type**, not the object.
- Only **methods** are overridden. Fields are hidden.

```java
Person p2 = new Student("Disha");
p2.type        // Person  (field: reference type)
p2.getType()   // Student (method: object type)
```

## Downcasting

- Parent reference → back to child type, so we can call child-only methods.
- Must be written: `Student s = (Student) p;`
- Wrong cast (a teacher as a student) → `ClassCastException` at run time.
- Check first with `instanceof`.

```java
if (p instanceof Student) {
    Student s = (Student) p;
    s.showMarks();
}

// pattern matching (Java 16+): check and cast in one step
if (p instanceof Student s) {
    s.showMarks();
}
```
