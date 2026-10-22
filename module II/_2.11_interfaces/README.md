# 2.11 Interfaces

Example: [Interfaces.java](Interfaces.java) (`Person` → `Student`, `Teacher`, `TeachingAssistant`)

## Interface

- A **contract**: a list of methods a class promises to write.
- Keyword `interface`. A class uses `implements`.
- **Cannot** create its object. Its reference can point to an implementing object.

```java
interface FeePayer {
    double FINE_PER_DAY = 50;      // public static final
    double fee();                  // public abstract
}

class Student extends Person implements FeePayer { ... }
```

| Inside an interface | Automatically |
| --- | --- |
| Methods (no body) | `public abstract` |
| Fields | `public static final` (constants, must have a value) |
| Constructor | Not allowed |

- Implementing method must be `public` (can't be weaker than the interface).
- Students pay a fee, teachers don't → `FeePayer` is an **ability**, not a parent.

## Multiple Interfaces

- Disha is a PG student **and** teaches the Java lab: a teaching assistant.
- `class TeachingAssistant extends Person implements Learning, Teaching`
- This is how Java does **multiple inheritance**.
- No diamond problem: both have `work()`, the class writes one `work()` body itself (see 2.2).
- A class can `extends` one class **and** `implements` many.

## Interface Extending Interface

- An interface `extends` another interface (can extend many).
- `interface Learning extends FeePayer` → every learner pays a fee.
- Implementing class writes methods of **both**: `work()`, `attendClass()` **and** `fee()`.

| | Keyword |
| --- | --- |
| class → class | `extends` (one) |
| class → interface | `implements` (many) |
| interface → interface | `extends` (many) |

## `default`, `static` and `private` Methods (Java 8 / 9)

| Method | Has body? | Use |
| --- | --- | --- |
| `default` | Yes | Common code: `showFee()`; class may override it. New methods can be added without breaking old classes. |
| `static` | Yes | Helper; call with interface name: `FeePayer.lateFine(4)` |
| `private` | Yes | Helper used only by `default` methods inside the interface: `line()` |

## Functional Interface and Lambda (intro)

Example: [LambdaIntro.java](LambdaIntro.java)

- Functional interface: interface with **exactly one** abstract method.
- `@FunctionalInterface` makes the compiler check this.
- **Lambda:** short way to write that one method: `marks -> marks >= 40`
- Used a lot in Module III.

## Marker Interface

- Interface with **no methods**. Just "marks" a class as special for Java.
- `Serializable`: object can be saved to a file / sent over network.
- `Cloneable`: object can be copied with `clone()`.
