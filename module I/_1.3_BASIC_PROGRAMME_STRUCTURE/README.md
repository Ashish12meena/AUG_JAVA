# 1.3 Basic Program Structure

## Structure of a Java Program

```java
package vgu.college;          // 1. package (optional, must be first)

import java.util.Scanner;      // 2. import (optional)

public class HelloCollege {     // 3. class
    public static void main(String[] args) {   // 4. main method
        // statements
    }
}
```

- **Order:** package → import → class → main
- A file can have many classes but only **one `public` class**.
- File name must be the same as the public class name.
- Every statement ends with `;`.
- Java is case-sensitive.

## The `main` Method

- Starting point of the program. The JVM calls `main` first.
- `public static void main(String[] args)`

| Keyword | Meaning |
| --- | --- |
| `public` | JVM can call it from outside the class |
| `static` | JVM calls it without creating an object |
| `void` | Returns nothing |
| `main` | Fixed name the JVM looks for |
| `String[] args` | Command-line arguments |

## Method Signature

- Method signature = **method name + parameter list**.
- Return type is **not** part of the signature.
- Two methods with the same signature in one class → error.

Example: [MethodSignature.java](MethodSignature.java)

## Command-Line Arguments

- Values given after the class name when running the program.
- Stored in `args` as strings.

```
> java CommandLineArgument Riya CSE 2
```

- `args[0]` → `"Riya"`, `args.length` → `3`
- Convert to number: `Integer.parseInt(args[2])`

Example: [CommandLineArgument.java](CommandLineArgument.java)

## Naming Conventions

| Element | Style | Example |
| --- | --- | --- |
| Class | PascalCase | `Student` |
| Method | camelCase | `getName()` |
| Variable | camelCase | `rollNo` |
| Constant | UPPER_CASE | `PASS_MARK` |
| Package | lowercase | `vgu.college` |

Example: [Student.java](Student.java)

## Comments

- `//` single-line comment
- `/* ... */` multi-line comment
- `/** ... */` documentation comment
- The compiler ignores comments.
