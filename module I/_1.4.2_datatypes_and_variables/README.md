# 1.4 Data Types, Variables and Static Members

## Data Types

- A data type tells what kind of value a variable can store and how much memory it uses.
- Java is **strongly typed**: every variable must have a type.
- Two kinds: **primitive** and **non-primitive**.

| Type | Size | Example |
| --- | --- | --- |
| `byte` | 1 byte | `byte semester = 3;` |
| `short` | 2 bytes | `short students = 1200;` |
| `int` | 4 bytes | `int rollNo = 101;` |
| `long` | 8 bytes | `long phone = 9876543210L;` |
| `float` | 4 bytes | `float cgpa = 8.5f;` |
| `double` | 8 bytes | `double percentage = 87.6;` |
| `char` | 2 bytes | `char grade = 'A';` |
| `boolean` | — | `boolean passed = true;` |

- Whole numbers are `int` by default; decimal numbers are `double` by default.
- `long` ends with `L`, `float` ends with `f`.
- `char` uses `' '`, `String` uses `" "`.
- **Non-primitive:** `String`, arrays, classes. They store the address of an object. Default is `null`.

Example: [DataTypes.java](DataTypes.java)

## Variables

- A variable is a named memory location used to store a value.
- The value can change while the program runs.
- Syntax: `dataType variableName = value;`

```java
int age;      // declaration
age = 22;     // initialization
```

| Type | Where declared | Default value |
| --- | --- | --- |
| Local | Inside a method | None (must give a value) |
| Instance | Inside class, outside methods | Yes (`0`, `null`, `false`) |
| Static | Inside class, with `static` | Yes |

Example: [Variables.java](Variables.java)

## Static Members

- **Problem:** college name is the same for every student. Storing it in every object wastes memory, and changing it means changing every object.
- **Solution:** make it `static`. One copy is shared by all objects.
- Static members belong to the **class**, not to an object.
- Access with class name: `Student.collegeName`.
- Static method: called without an object; cannot use `this` or instance variables directly.
- `main` is static so the JVM can call it without creating an object.

| | Static | Instance |
| --- | --- | --- |
| Belongs to | Class | Object |
| Copies | One | One per object |
| Access | `ClassName.member` | `object.member` |

Example: [Student.java](Student.java) + [Main.java](Main.java). Changing `collegeName` once changes it for both students.
