# 1.8 Encapsulation

## Problem

- If fields are open, any code can store wrong values: `s1.marks = -50;`
- The class has no control over its own data.

Example: [WithoutEncapsulation.java](WithoutEncapsulation.java)

## Encapsulation

- **Encapsulation:** keeping data (fields) and the methods that work on it together in one class, and stopping direct access to the data.
- **Data hiding:** make fields `private`. Only code inside the class can use them.
- Outside code uses `public` methods to read or change the data.
- Like a capsule: the medicine is inside, you only see the cover.

## Getters and Setters

| | Getter | Setter |
| --- | --- | --- |
| Job | Read a private field | Change a private field |
| Name | `getName()` (`isPassed()` for boolean) | `setName(String name)` |
| Returns | The value | Usually `void` |

- A setter should **check** the value before storing it.
- A setter with no checking gives no real protection.

Example: [GetterSetter.java](GetterSetter.java)

## Read-only Fields

- Give only a getter, no setter. Outside code can read but not change it.
- Example: roll number can be seen but never changed.

Example: [FeeAccount.java](FeeAccount.java)

## Access Modifiers

| Modifier | Same class | Same package | Subclass (other package) | Everywhere |
| --- | --- | --- | --- | --- |
| `private` | ✔ | ✘ | ✘ | ✘ |
| default (no keyword) | ✔ | ✔ | ✘ | ✘ |
| `protected` | ✔ | ✔ | ✔ | ✘ |
| `public` | ✔ | ✔ | ✔ | ✔ |

- Rule: **fields `private`, methods `public`**.

Example: [AccessModifiers.java](AccessModifiers.java)

## `final` Variables

- A `final` variable can get a value only **once**.
- `static final` → constant, one copy, name in `UPPER_CASE`: `PASS_MARK`.
- `final` field without a value → must be set in the constructor.

Example: [FinalVariable.java](FinalVariable.java)

## Immutable Class

- Object cannot change after it is created. Example: `String`.
- **Steps:**
  1. Class is `final`.
  2. All fields `private final`.
  3. Values set in the constructor.
  4. Only getters, no setters.

Example: [ImmutableClass.java](ImmutableClass.java)

## Benefits

- **Control:** only valid values are stored.
- **Security:** important data cannot be changed from outside.
- **Easy to change:** inside code can change without breaking code that uses the class.
