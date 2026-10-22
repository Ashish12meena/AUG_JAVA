# 2.10 Abstraction and Abstract Classes

Example: [AbstractClass.java](AbstractClass.java) (`Person` → `Student`, `Teacher`)

## Abstraction

- Show **what** an object does, hide **how** it does it.
- The bell rings → everyone must **work**. Each person decides **how**.
- In Java: **abstract classes** (partial) and **interfaces** (2.11).

## Abstract Method

- Method with **no body**, only a declaration. Ends with `;`.
- Says: "every child **must** write this method".

```java
abstract void work();
```

## Abstract Class

- Nobody in the college is "just a Person". Everyone is a student, a teacher...
- So `Person` is a general idea → `abstract class Person`.
- **Rules:**
  - **Cannot** create its object: `new Person("X")` is an error.
  - **Can** have constructors, fields, normal (concrete) methods and abstract methods.
  - A class with even one abstract method must be `abstract`.
  - Its reference **can** point to a child object (upcasting).
- Constructor runs when a child object is created (`super(name)`).

## Child Class Rules

- Child must write **every** abstract method,
- **or** the child must also be declared `abstract`.
- `class Visitor extends Person { }` with no `work()` → error.

## Partial Abstraction

- Abstract class = some methods with body (`enter()`, same for all) + some without (`work()`, child decides).
- So it gives **0 to 100%** abstraction.
- Interface (before Java 8) gave 100%.

| Can an abstract class... | |
| --- | --- |
| have a constructor? | Yes |
| have a `main` method? | Yes |
| be `final`? | No (`final` stops extending, `abstract` needs it) |
| have zero abstract methods? | Yes, still can't create its object |

## Teaching Trick

Keep asking: **"Who decides what `work()` actually does?"**

```
Person            → says WHAT must be done   (abstract work())
Student / Teacher → say HOW it is done        (override work())
Person reference  → picks which one runs, at run time
```

- **Abstraction → What should be done?**
- **Polymorphism → Which version should run?**
