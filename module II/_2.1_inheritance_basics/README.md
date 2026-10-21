# 2.1 Inheritance Basics

## Inheritance

- One class gets the fields and methods of another class.
- Keyword: `extends`.
- **"is-a" relationship:** a Fresher **is a** Person.
- Write common code once in the parent, reuse it in every child.

```java
class Person { }
class Fresher extends Person { }   // Fresher is a Person
```

| Word | Meaning | Example |
| --- | --- | --- |
| Superclass / parent / base | Class that is extended | `Person` |
| Subclass / child / derived | Class that extends | `Fresher` |

- Child object can use parent methods **and** its own methods.
- Parent object **cannot** use child methods.

Example: [InheritanceBasics.java](InheritanceBasics.java)

## What Is Inherited, What Is Not

| Member | Inherited? |
| --- | --- |
| `public` / `protected` fields and methods | Yes |
| default (no keyword) members | Yes, if child is in the same package |
| `private` fields and methods | **No** direct access. Use a public getter. |
| Constructors | **No**. Child calls them with `super()` (2.3) |

Example: [WhatIsInherited.java](WhatIsInherited.java)

## The `Object` Class

- `Object` (in `java.lang`) is the parent of **every** class.
- `class Visitor { }` really means `class Visitor extends Object { }`.
- So every object already has `toString()`, `equals()`, `hashCode()`, `getClass()` (more in 2.9).

Example: [ObjectClassRoot.java](ObjectClassRoot.java)

## Benefits

- **Code reuse:** common code written once.
- **Extensibility:** add a new child without changing the parent.
- **Overriding:** child can change a parent method (2.5).
