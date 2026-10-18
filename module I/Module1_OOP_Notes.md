# Module I: OOP and Class Hierarchy

**Programming Concepts Using Java — Notes**

---

## Running Example: Student Result System

One example is used through the whole module. It starts as a single line of output and grows, topic by topic, into a small college system.

| Version | Topic | What the program does |
| --- | --- | --- |
| v1 | 1.3 Program structure | `HelloCollege`: prints a welcome message |
| v2 | 1.4 Fundamentals | `ReportCard`: total, percentage and grade of **one** student |
| v3 | 1.4 Arrays and strings | `ClassResult`: result of the **whole class** |
| v4 | 1.5 Classes and objects | `Student` class: each student becomes an object |
| v5 | 1.6 Constructors | `Student` is created complete in one line |
| v6 | 1.7 Static members | Shared college name, automatic roll numbers and a student count |
| v7 | 1.8 Encapsulation | Marks protected; only valid marks accepted |
| v8 | 1.9 Relationships | `Classroom`, `Teacher` and `College` added |

Same data everywhere:

| Roll No | Name | Marks (5 subjects) | Total |
| --- | --- | --- | --- |
| 101 | Riya | 85, 92, 78, 88, 95 | 438 |
| 102 | Aman | 45, 38, 60, 52, 49 | 244 |
| 103 | Sneha | 91, 89, 94, 90, 96 | 460 |

---

## Contents

- 1.1 Introduction to OOP and Java
- 1.2 Java Architecture: JDK, JRE and JVM
- 1.3 Basic Program Structure
- 1.4 Language Fundamentals
- 1.5 Classes and Objects
- 1.6 Constructors
- 1.7 Static Members
- 1.8 Encapsulation
- 1.9 Object Relationships
- Practice Programs
- Quick Revision

**Topic folders** (each has a short `README.md` + example programs)

| Section | Folder |
| --- | --- |
| 1.1 Introduction to OOP and Java | `_1.1_introduction_to_java/` |
| 1.2 JDK, JRE and JVM | `_1.2_jdk_jre_jvm/` |
| 1.3 Basic Program Structure | `_1.3_BASIC_PROGRAMME_STRUCTURE/` |
| 1.4 Control Statements | `_1.4.1_control_statements/` |
| 1.4 Data Types, Variables (+ Static Members) | `_1.4.2_datatypes_and_variables/` |
| 1.4 Operators | `_1.4.3_operators/` |
| 1.5 Classes and Objects | `_1.5_class_and_objects/` |
| 1.6 Constructors | `_1.6_constructors/` |
| 1.7 Static Members | in `_1.4.2_datatypes_and_variables/` |
| 1.8 Encapsulation | `_1.8_encapsulation/` |
| 1.9 Object Relationships | `_1.9_object_relationships/` |
| Final example (end of module) | `example/` |

---
---

## 1.1 Introduction to OOP and Java

### 1.1.1 Procedural vs Object-Oriented Programming

**Procedural programming:** a program is a set of functions that work on shared data.

**Object-oriented programming (OOP):** a program is a set of objects; each object holds its own data and the functions (methods) that work on it.

Procedural version of student records (C style):

```c
char  names[500][30];
int   rollNos[500];
int   marks[500][5];

void updateMarks(int i, int subject, int m) {
    marks[i][subject] = m;      // any function can change any student's marks
}
```

OOP version (Java):

```java
class Student {
    int rollNo;
    String name;
    int[] marks;

    void setMarks(int subject, int m) {
        if (m >= 0 && m <= 100) marks[subject] = m;   // data and its rules together
    }
}
```

| Point | Procedural | Object-oriented |
| --- | --- | --- |
| Built from | Functions + shared data | Objects (data + methods) |
| Example call | `updateMarks(i, 2, 85)` | `riya.setMarks(2, 85)` |
| Data safety | Any function can change any data | Each object protects its own data |
| Reuse | Difficult | Easy (classes, inheritance) |
| Large programs | Hard to change and debug | Easier to change and extend |

**Problems with procedural code at scale**

- Global data can be changed from anywhere, so bugs are hard to trace.
- Functions become long and tangled.
- One change ripples through many functions.
- Code is difficult to reuse.

### 1.1.2 The Four Pillars of OOP

| Pillar | Meaning | Example |
| --- | --- | --- |
| Encapsulation | Keep data and methods together; hide the data | Marks change only through `setMarks()`, which checks them |
| Inheritance | A new class reuses and extends an existing class | `HostelStudent` is a `Student` with a room number |
| Polymorphism | One action, many forms | `calculateFee()` differs for a day scholar and a hostel student |
| Abstraction | Show what an object does, hide how | "Print result" works without knowing how percentage is calculated |

> Encapsulation: section 1.8. Inheritance, polymorphism, abstraction: Module II.

### 1.1.3 History of Java

| Year | Event |
| --- | --- |
| 1991 | James Gosling and the "Green Team" at **Sun Microsystems** start a language named **Oak** |
| 1995 | Renamed **Java** and released publicly |
| 2010 | **Oracle** acquires Sun; Java is now maintained by Oracle and the OpenJDK community |
| Today | New version every 6 months; LTS (long-term support) versions: 8, 11, 17, 21, 25 |

### 1.1.4 Features (Buzzwords) of Java

| Feature | Meaning |
| --- | --- |
| Simple | C-like syntax; no pointers, no manual memory freeing |
| Object-oriented | All code is written inside classes |
| Platform-independent | Compiled to bytecode, which runs on any JVM |
| Portable | Fixed data sizes (`int` is always 32 bits) |
| Secure | No pointers; bytecode is verified before running |
| Robust | Strong type checking, exception handling, garbage collection |
| Multithreaded | Can run many tasks at the same time |
| High performance | JIT compiler converts frequently used bytecode to machine code |
| Distributed | Built-in networking support |
| Dynamic | Classes are loaded at runtime, when needed |

### 1.1.5 Java vs C and C++

| Feature | C | C++ | Java |
| --- | --- | --- | --- |
| Paradigm | Procedural | Procedural + OOP | OOP |
| Pointers | Yes | Yes | No (references) |
| Memory management | Manual (`malloc`/`free`) | Manual (`new`/`delete`) | Automatic (garbage collector) |
| Multiple inheritance of classes | — | Yes | No (interfaces instead) |
| Operator overloading | No | Yes | No (except `+` for strings) |
| Platform dependence | Compiled for one OS | Compiled for one OS | Bytecode runs on any OS |

### 1.1.6 Where Java Is Used

- Enterprise and banking systems, college ERP systems
- Android applications
- Web applications and web services
- Big-data tools (Hadoop, Kafka, Spark)

---
---

## 1.2 Java Architecture: JDK, JRE and JVM

### 1.2.1 JDK, JRE and JVM

```
+------------------------------------------------------------+
| JDK (Java Development Kit)                                 |
|   development tools: javac, java, javap, jar, javadoc      |
|                                                            |
|   +------------------------------------------------------+ |
|   | JRE (Java Runtime Environment)                       | |
|   |   class libraries: java.lang, java.util, java.io ... | |
|   |                                                      | |
|   |   +-----------------------------------------+        | |
|   |   | JVM (Java Virtual Machine)              |        | |
|   |   |   class loader, bytecode verifier,      |        | |
|   |   |   execution engine (interpreter + JIT), |        | |
|   |   |   garbage collector                     |        | |
|   |   +-----------------------------------------+        | |
|   +------------------------------------------------------+ |
+------------------------------------------------------------+
```

| Part | Full form | Contains | Needed for |
| --- | --- | --- | --- |
| JVM | Java Virtual Machine | Class loader, verifier, execution engine, garbage collector | Running bytecode (one JVM per OS) |
| JRE | Java Runtime Environment | JVM + class libraries | Running Java programs |
| JDK | Java Development Kit | JRE + development tools | Writing and compiling Java programs |

> **JDK = JRE + development tools**
> **JRE = JVM + class libraries**
> Since Java 11, Oracle distributes only the JDK (no separate JRE download).

### 1.2.2 Compilation and Execution Flow

```
HelloCollege.java  --javac-->  HelloCollege.class  --java-->  JVM  -->  Output
  (source code)                   (bytecode)
```

1. Write source code in `HelloCollege.java`.
2. `javac HelloCollege.java` checks the code and creates `HelloCollege.class`.
3. The `.class` file contains **bytecode**.
4. `java HelloCollege` starts the JVM, which loads, verifies and runs the bytecode.

### 1.2.3 Bytecode and Platform Independence

- **Bytecode:** instructions for the JVM, not for any real processor.
- The same `.class` file runs on every operating system; only the JVM differs.

```
                          +--> JVM on Windows  --> runs
HelloCollege.class  ------+--> JVM on Linux    --> runs
   (one file)             +--> JVM on macOS    --> runs
```

> **Write Once, Run Anywhere (WORA).**
> Bytecode is platform-independent. The JVM is platform-dependent.

### 1.2.4 Compiled and Interpreted: the JIT Compiler

Java is **both compiled and interpreted**:

- **Compiled:** `javac` converts source code to bytecode.
- **Interpreted:** the JVM executes bytecode instruction by instruction.
- **JIT (Just-In-Time) compiler:** part of the JVM; finds "hot" code (code that runs many times, such as a loop over all students) and converts it into native machine code for faster execution.

### 1.2.5 JVM Components

| Component | Job |
| --- | --- |
| Class loader | Loads `.class` files into memory when first needed |
| Bytecode verifier | Checks bytecode is valid and safe |
| Execution engine | Runs bytecode: interpreter + JIT compiler |
| Garbage collector | Frees memory of objects no longer in use |

### 1.2.6 JVM Memory Areas

| Area | Stores | Shared by threads? |
| --- | --- | --- |
| Method area | Class information, method code, static variables | Yes |
| Heap | All objects and arrays | Yes |
| Stack | Method calls, local variables, references | No (one per thread) |
| PC register | Address of the next instruction | No (one per thread) |
| Native method stack | Calls to native (C/C++) code | No (one per thread) |

### 1.2.7 JDK Tools

| Tool | Purpose | Example |
| --- | --- | --- |
| `javac` | Compile source to bytecode | `javac HelloCollege.java` |
| `java` | Run a class on the JVM | `java HelloCollege` |
| `javap` | Show the contents/bytecode of a class file | `javap -c HelloCollege` |
| `jar` | Package class files into one archive | `jar cf college.jar *.class` |
| `javadoc` | Create HTML documentation from `/** */` comments | `javadoc Student.java` |

---
---

## 1.3 Basic Program Structure

> **Running example — v1: `HelloCollege`**

```java
import java.time.LocalDate;

/**
 * v1: welcomes a student to the college.
 */
public class HelloCollege {

    public static void main(String[] args) {
        // name from the command line, or a default
        String name = (args.length > 0) ? args[0] : "Student";

        System.out.println("Welcome to VGU College, " + name + "!");
        System.out.println("Today is " + LocalDate.now());
    }
}
```

```
> javac HelloCollege.java
> java HelloCollege Riya
Welcome to VGU College, Riya!
Today is 2026-10-14
```

### 1.3.1 Structure of a Java Program

```java
package vgu.college;               // 1. package (optional, must be first)

import java.util.Scanner;           // 2. import (optional)

public class HelloCollege {         // 3. class

    public static void main(String[] args) {   // 4. main method
        // statements
    }
}
```

- **Order:** package → import → class → main
- A file can have many classes but only **one `public` class**.
- File name must match the public class name: `HelloCollege.java`.
- Every statement ends with `;`.
- Java is case-sensitive (`Main` and `main` are different).

### 1.3.2 The `main` Method

- Starting point of the program. The JVM calls `main` first.

```java
public static void main(String[] args)
```

| Keyword | Meaning |
| --- | --- |
| `public` | JVM (outside the class) can call it |
| `static` | Called without creating an object |
| `void` | Returns nothing |
| `main` | Fixed name the JVM looks for |
| `String[] args` | Command-line arguments |

- Also valid: `String args[]`, `String... args`.

### 1.3.3 Method Signature

- Method signature = **method name + parameter list**.
- Return type is **not** part of the signature.
- Two methods in one class cannot have the same signature.

```java
public static void main(String[] args) { }              // OK
public static int main(String[] args, int age) { }      // OK: different parameters
// public static char main(String[] args, int age) { }  // ERROR: only return type is different
```

### 1.3.4 Command-Line Arguments

- Values typed after the class name when running the program.
- Stored in `args` as **strings**.

```
> java HelloCollege Riya CSE 2
```

- `args[0]` → `"Riya"`, `args[2]` → `"2"` (a string), `args.length` → `3`
- Convert to a number: `int year = Integer.parseInt(args[2]);`
- Using `args[0]` when no argument is passed → `ArrayIndexOutOfBoundsException`.

### 1.3.5 Naming Conventions

| Element | Convention | Example |
| --- | --- | --- |
| Class / interface | PascalCase, noun | `Student`, `HelloCollege` |
| Method | camelCase, verb | `printResult()`, `getName()` |
| Variable | camelCase | `rollNo`, `totalMarks` |
| Constant | UPPER_SNAKE_CASE | `PASS_MARK` |
| Package | lowercase | `vgu.college` |

### 1.3.6 Comments

- Ignored by the compiler; used to explain code.

```java
// single-line comment

/* multi-line
   comment */

/** documentation comment (read by javadoc) */
```

### 1.3.7 Compiling and Running

- `javac HelloCollege.java` → creates `HelloCollege.class` (bytecode)
- `java HelloCollege` → runs the program
- Java 11+: `java HelloCollege.java` compiles and runs in one step.
- With a package: `javac -d . HelloCollege.java` then `java vgu.college.HelloCollege`
- IDE (IntelliJ / Eclipse / VS Code): create project → add class → Run

---
---

## 1.4 Language Fundamentals

> **Running example — v2: `ReportCard` (one student)**

```java
import java.util.Scanner;

public class ReportCard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Roll number: ");
        int rollNo = sc.nextInt();
        sc.nextLine();                          // clear leftover newline
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Marks in 5 subjects: ");
        int m1 = sc.nextInt(), m2 = sc.nextInt(), m3 = sc.nextInt(),
            m4 = sc.nextInt(), m5 = sc.nextInt();

        int total = m1 + m2 + m3 + m4 + m5;
        double percentage = total / 5.0;

        char grade;
        if (percentage >= 90)      grade = 'A';
        else if (percentage >= 75) grade = 'B';
        else if (percentage >= 60) grade = 'C';
        else if (percentage >= 40) grade = 'D';
        else                       grade = 'F';

        boolean passedAll = m1 >= 40 && m2 >= 40 && m3 >= 40 && m4 >= 40 && m5 >= 40;
        String result = passedAll ? "PASS" : "FAIL";

        String remark;
        switch (grade) {
            case 'A': remark = "Outstanding";    break;
            case 'B': remark = "Very good";      break;
            case 'C':
            case 'D': remark = "Keep improving"; break;
            default:  remark = "Needs support";
        }

        System.out.println("---------- REPORT CARD ----------");
        System.out.printf("%-10s : %d%n", "Roll No", rollNo);
        System.out.printf("%-10s : %s%n", "Name", name);
        System.out.printf("%-10s : %d / 500%n", "Total", total);
        System.out.printf("%-10s : %.2f%%%n", "Percentage", percentage);
        System.out.printf("%-10s : %c (%s)%n", "Grade", grade, remark);
        System.out.printf("%-10s : %s%n", "Result", result);
    }
}
```

```
Roll number: 101
Name: Riya
Marks in 5 subjects: 85 92 78 88 95
---------- REPORT CARD ----------
Roll No    : 101
Name       : Riya
Total      : 438 / 500
Percentage : 87.60%
Grade      : B (Very good)
Result     : PASS
```

### 1.4.1 Tokens

**Token:** the smallest unit of a program that the compiler understands.

| Token type | Examples from v2 |
| --- | --- |
| Keywords | `public`, `class`, `int`, `if`, `else`, `switch` |
| Identifiers | `ReportCard`, `rollNo`, `total`, `percentage` |
| Literals | `101`, `5.0`, `'A'`, `"PASS"`, `true`, `null` |
| Operators | `+`, `/`, `>=`, `&&`, `?:` |
| Separators | `;` `,` `.` `( )` `{ }` `[ ]` |

**Identifier rules**

- Starts with a letter, `_` or `$` (not a digit)
- No spaces or special symbols
- Cannot be a keyword
- Case-sensitive (`total` ≠ `Total`)

### 1.4.2 Data Types

- A data type tells what kind of value a variable can store and how much memory it uses.
- Java is **strongly typed**: every variable must have a type, and the type is known at compile time.
- Two kinds: **primitive** (8 types) and **non-primitive** (reference) types.

**Primitive types (8)**

| Type | Size | Range | Default | Example |
| --- | --- | --- | --- | --- |
| `byte` | 1 byte | −128 to 127 | `0` | `byte semester = 3;` |
| `short` | 2 bytes | −32,768 to 32,767 | `0` | `short students = 1200;` |
| `int` | 4 bytes | about ±2.1 billion | `0` | `int rollNo = 101;` |
| `long` | 8 bytes | very large whole numbers | `0L` | `long phone = 9876543210L;` |
| `float` | 4 bytes | about 7 decimal digits | `0.0f` | `float cgpa = 8.5f;` |
| `double` | 8 bytes | about 15 decimal digits | `0.0` | `double percentage = 87.6;` |
| `char` | 2 bytes | 0 to 65,535 (Unicode) | `'\u0000'` | `char grade = 'B';` |
| `boolean` | JVM-dependent | `true` / `false` | `false` | `boolean passed = true;` |

- Whole numbers are `int` by default; decimal numbers are `double` by default.
- `long` value ends with `L`, `float` value ends with `f`.
- `char` uses single quotes `'A'`; `String` uses double quotes `"A"`.

**Non-primitive (reference) types:** `String`, arrays, classes (`Student`, `Scanner`), interfaces.

- A reference variable stores the **address** of an object.
- Default value: `null`.

### 1.4.3 Variables

- A variable is a named memory location used to store a value.
- The value of a variable can change during program execution.
- Syntax:
  `dataType variableName = value;`

```java
int age;              // declaration
age = 22;             // initialisation
age = 23;             // value changed
int a = 10, b = 20;   // many variables in one line
```

- Variable names follow the identifier rules (1.4.1) and use camelCase.

**Types of variables**

| Type | Declared | Default value | Seen in |
| --- | --- | --- | --- |
| Local | Inside a method/block | None (must be given a value before use) | v2: `total`, `grade` |
| Instance | Inside class, outside methods, no `static` | Yes | v4: `name`, `marks` |
| Static | Inside class with `static` | Yes | v6: `count` |

- **Scope:** a local variable can be used only inside the `{ }` block where it is declared.

### 1.4.4 Type Conversion

**Widening (automatic)** — smaller type to larger type, no data loss:

```
byte → short → int → long → float → double
               char → int
```

**Narrowing (casting)** — larger type to smaller type, may lose data:

```java
double percentage = 87.6;
int rounded = (int) percentage;     // 87 (decimal part lost)
```

**Type promotion in expressions**

- `byte`, `short` and `char` are promoted to `int`.
- If any operand is `long`, `float` or `double`, the result is that type.

```java
int total = 438;
System.out.println(total / 5);            // 87     (int / int = int)
System.out.println(total / 5.0);          // 87.6   (int / double = double)
System.out.println((double) total / 5);   // 87.6

byte a = 10, b = 20;
// byte c = a + b;                        // ERROR: a + b is int
int c = a + b;                            // OK
```

### 1.4.5 Operators

- An operator is a symbol that performs an operation on values (operands).

**Arithmetic: `+ - * / %`**

```java
int a = 10, b = 3;
a + b      // 13
a - b      // 7
a * b      // 30
a / b      // 3     (int / int gives int)
a % b      // 1     (remainder)
a / 3.0    // 3.333...
```

- `+` also joins strings: `"Total: " + 438` → `"Total: 438"`.
- `%` is used to check even/odd: `num % 2 == 0`.

**Relational: `== != > < >= <=`**

- Compare two values. Result is always `true` or `false`.

```java
int a = 10, b = 3;
a == b     // false
a != b     // true
a > b      // true
a <= b     // false
```

- `=` assigns a value; `==` compares two values.

**Logical: `&& || !`**

| Operator | Result is `true` when |
| --- | --- |
| `&&` (AND) | Both conditions are true |
| `\|\|` (OR) | At least one condition is true |
| `!` (NOT) | Condition is false (reverses it) |

```java
int marks = 75;
marks >= 40 && marks <= 100    // true
marks < 40 || marks > 100      // false
!(marks >= 40)                 // false
```

- **Short-circuit:** in `a && b`, if `a` is false, `b` is not checked. In `a || b`, if `a` is true, `b` is not checked.

**Assignment: `= += -= *= /= %=`**

```java
int x = 10;
x += 5;    // x = x + 5  → 15
x -= 3;    // x = x - 3  → 12
x *= 2;    // x = x * 2  → 24
x /= 4;    // x = x / 4  → 6
x %= 4;    // x = x % 4  → 2
```

**Unary: `+ - ++ -- !`**

- Work on a single operand.
- `++` increases by 1, `--` decreases by 1.

| Form | Meaning | Example (`x = 5`) |
| --- | --- | --- |
| `++x` (pre-increment) | Increase first, then use | `y = ++x;` → `x = 6`, `y = 6` |
| `x++` (post-increment) | Use first, then increase | `y = x++;` → `y = 5`, `x = 6` |

- `--x` and `x--` work the same way.
- `!` reverses a boolean: `!true` → `false`.

**Ternary: `condition ? value1 : value2`**

- Short form of `if-else` that gives a value.
- If the condition is true → `value1`, else → `value2`.

```java
int marks = 75;
String result = (marks >= 40) ? "PASS" : "FAIL";   // PASS
```

**Precedence (high → low)**

| Level | Operators |
| --- | --- |
| 1 | `++ --` (postfix) |
| 2 | `++ -- + - !` (prefix/unary) |
| 3 | `* / %` |
| 4 | `+ -` |
| 5 | `< > <= >=` |
| 6 | `== !=` |
| 7 | `&&` |
| 8 | `\|\|` |
| 9 | `?:` |
| 10 | `= += -=` … |

- Same level: left → right (unary, ternary and assignment: right → left).
- `2 + 3 * 4` → `14`. Use brackets when in doubt: `(2 + 3) * 4` → `20`.

### 1.4.6 Control Statements

- Control statements decide **which** statements run and **how many times**.
- Decision: `if`, `if-else`, `else-if`, nested `if`, `switch`
- Loops: `for`, `while`, `do-while`
- Jump: `break`, `continue`

**`if`**

- Runs the block only when the condition is true.

```java
int num = 5;
if (num > 0) {
    System.out.println("Positive");
}
```

**`if-else`**

- If the condition is true, the `if` block runs; otherwise the `else` block runs.

```java
if (num % 2 == 0) {
    System.out.println("Even");
} else {
    System.out.println("Odd");
}
```

**`else-if` ladder**

- Checks conditions from top to bottom; only the first true block runs.
- `else` at the end runs when no condition is true.
- Used for ranges, like the grade in v2.

```java
if (marks >= 90) {
    System.out.println("Grade A");
} else if (marks >= 75) {
    System.out.println("Grade B");
} else if (marks >= 40) {
    System.out.println("Grade C");
} else {
    System.out.println("Fail");
}
```

**Nested `if`**

- An `if` inside another `if`.
- The inner condition is checked only when the outer condition is true.

```java
if (a > b) {
    if (a > c) {
        System.out.println("a is greatest");
    } else {
        System.out.println("c is greatest");
    }
} else {
    if (b > c) {
        System.out.println("b is greatest");
    } else {
        System.out.println("c is greatest");
    }
}
```

**`switch`**

- Selects one block by matching a value with `case` labels.
- Works with `int`, `char`, `String` (also `byte`, `short`).
- `break` ends the switch. Without `break`, the next case also runs (**fall-through**).
- `default` runs when no case matches (optional).

```java
int day = 3;
switch (day) {
    case 1:
        System.out.println("Monday");
        break;
    case 2:
        System.out.println("Tuesday");
        break;
    case 3:
        System.out.println("Wednesday");
        break;
    default:
        System.out.println("Invalid day");
}
```

- Many cases can share one block:

```java
switch (semester) {
    case 1: case 2: System.out.println("First year");  break;
    case 3: case 4: System.out.println("Second year"); break;
    default:        System.out.println("Senior");
}
```

**`for` loop**

- Used when the number of repetitions is known.
- Syntax: `for (initialisation; condition; update) { }`

```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);        // 1 2 3 4 5
}
```

**`while` loop**

- Checks the condition first, then runs the block.
- Used when the number of repetitions is not known in advance.

```java
int i = 1;
while (i <= 5) {
    System.out.println(i);
    i++;
}
```

- Forgetting `i++` makes an infinite loop.

**`do-while` loop**

- Runs the block first, then checks the condition → runs **at least once**.
- Ends with `;` after `while (...)`.
- Used for menus.

```java
int i = 1;
do {
    System.out.println(i);
    i++;
} while (i <= 5);
```

**Loop comparison**

| Loop | Checks condition | Runs at least once? | Use |
| --- | --- | --- | --- |
| `for` | Before each round | No | Known number of repetitions |
| `while` | Before each round | No | Repeat while a condition holds |
| `do-while` | After each round | Yes | Menus |
| Enhanced `for` | — | No | Read every element of an array (1.4.8) |

**Jump statements**

| Statement | Effect |
| --- | --- |
| `break` | Exits the current loop or switch |
| `continue` | Skips to the next iteration |
| labelled `break` | Exits an outer loop |

(Examples in 1.4.8 v3.)

### 1.4.7 Input and Output

**Input with `Scanner`** (`java.util.Scanner`)

| Method | Reads |
| --- | --- |
| `nextInt()` | An `int` |
| `nextDouble()` | A `double` |
| `next()` | One word |
| `nextLine()` | A whole line |
| `nextBoolean()` | A `boolean` |

> After `nextInt()`, the newline stays in the input. A following `nextLine()` returns `""`. Add an extra `sc.nextLine();` (as in v2).

**Output**

| Method | Behaviour |
| --- | --- |
| `System.out.print()` | Prints, stays on the same line |
| `System.out.println()` | Prints, then moves to a new line |
| `System.out.printf()` | Formatted output |

**`printf` format specifiers**

| Specifier | Meaning | Example output |
| --- | --- | --- |
| `%d` | Integer | `438` |
| `%f`, `%.2f` | Decimal, 2 places | `87.60` |
| `%s` | String | `Riya` |
| `%c` | Character | `B` |
| `%n` | New line | |
| `%%` | Percent sign | `%` |
| `%-10s` | Left-aligned, width 10 | `Name      ` |

### 1.4.8 Arrays

> **Running example — v3: `ClassResult` (whole class)**

```java
import java.util.Arrays;

public class ClassResult {
    public static void main(String[] args) {
        int[] rollNos  = {101, 102, 103};
        String[] names = {"Riya", "Aman", "Sneha"};
        int[][] marks  = {
            {85, 92, 78, 88, 95},     // Riya
            {45, 38, 60, 52, 49},     // Aman
            {91, 89, 94, 90, 96}      // Sneha
        };

        int[] totals = new int[names.length];
        int topper = 0;

        for (int i = 0; i < names.length; i++) {             // each student
            for (int j = 0; j < marks[i].length; j++) {      // each subject
                totals[i] += marks[i][j];
            }
            if (totals[i] > totals[topper]) topper = i;
        }

        System.out.println("Failed in a subject:");
        for (int i = 0; i < names.length; i++) {
            for (int m : marks[i]) {
                if (m < 40) {
                    System.out.println("  " + rollNos[i] + " " + names[i]);
                    break;                                    // one fail is enough
                }
            }
        }

        System.out.println("Topper: " + names[topper] + " (" + totals[topper] + ")");

        int[] sorted = Arrays.copyOf(totals, totals.length);
        Arrays.sort(sorted);
        System.out.println("Totals: " + Arrays.toString(sorted));
    }
}
```

```
Failed in a subject:
  102 Aman
Topper: Sneha (460)
Totals: [244, 438, 460]
```

**Array:** a fixed-size collection of elements of the same type, stored in contiguous memory, accessed by index.

**One-dimensional array**

```java
int[] rollNos = new int[3];            // {0, 0, 0}
int[] rollNos = {101, 102, 103};       // declare + initialise
String[] names = new String[3];        // {null, null, null}
```

**Two-dimensional array** — table of rows and columns:

```java
int[][] marks = new int[3][5];         // 3 students × 5 subjects
marks[0][2] = 78;                      // Riya, subject 3
```

```
             sub0  sub1  sub2  sub3  sub4
marks[0] →    85    92    78    88    95     Riya
marks[1] →    45    38    60    52    49     Aman
marks[2] →    91    89    94    90    96     Sneha
```

**Jagged array** — rows of different lengths:

```java
int[][] electives = new int[3][];
electives[0] = new int[2];     // Riya: 2 electives
electives[1] = new int[1];     // Aman: 1 elective
electives[2] = new int[3];     // Sneha: 3 electives
```

**Key points**

- Index starts at `0`, last index is `length - 1`.
- `length` is a **property** of arrays (`names.length`), a **method** of strings (`name.length()`).
- Invalid index → `ArrayIndexOutOfBoundsException`.
- Size is fixed after creation (solved by `ArrayList` in Module III).

**`java.util.Arrays` class**

| Method | Use |
| --- | --- |
| `Arrays.sort(a)` | Sort ascending |
| `Arrays.toString(a)` | `"[244, 438, 460]"` |
| `Arrays.copyOf(a, n)` | Copy first `n` elements |
| `Arrays.fill(a, v)` | Set every element to `v` |
| `Arrays.binarySearch(a, key)` | Search (array must be sorted) |
| `Arrays.equals(a, b)` | Compare contents |

**`continue` and labelled `break` on the same data**

```java
// continue: skip absent entries (-1) while totalling
for (int m : marks[i]) {
    if (m == -1) continue;
    total += m;
}

// labelled break: stop BOTH loops at the first mark below 40
search:
for (int i = 0; i < names.length; i++) {
    for (int m : marks[i]) {
        if (m < 40) {
            System.out.println("First low score: " + names[i]);
            break search;
        }
    }
}
```

### 1.4.9 Strings

**`String`:** an object representing a sequence of characters (`java.lang.String`).

**Creating strings and the string pool**

```java
String a = "Riya";               // stored in the string pool
String b = "Riya";               // reuses the same pool object
String c = new String("Riya");   // new object on the heap

a == b          // true   (same object)
a == c          // false  (different objects)
a.equals(c)     // true   (same characters)
```

> `==` compares **references**. `equals()` compares **content**. Always use `equals()` for strings.

**Immutability:** a `String` object cannot be changed. Methods return a **new** string.

```java
String name = "riya";
name.toUpperCase();              // result discarded; name is still "riya"
name = name.toUpperCase();       // name is now "RIYA"
```

**Common methods**

| Method | Example | Result |
| --- | --- | --- |
| `length()` | `"Riya".length()` | `4` |
| `charAt(i)` | `"Riya".charAt(0)` | `'R'` |
| `substring(a, b)` | `"Sneha".substring(0, 3)` | `"Sne"` |
| `indexOf(s)` | `"CSE-A".indexOf("-")` | `3` |
| `contains(s)` | `"CSE-A".contains("CSE")` | `true` |
| `equals(s)` | `"Riya".equals("riya")` | `false` |
| `equalsIgnoreCase(s)` | `"Riya".equalsIgnoreCase("riya")` | `true` |
| `compareTo(s)` | `"Aman".compareTo("Riya")` | negative |
| `toUpperCase()` / `toLowerCase()` | `"Riya".toUpperCase()` | `"RIYA"` |
| `trim()` / `strip()` | `"  Riya ".trim()` | `"Riya"` |
| `replace(a, b)` | `"CSE-A".replace("A", "B")` | `"CSE-B"` |
| `split(s)` | `"101,Riya".split(",")` | `["101", "Riya"]` |
| `startsWith(s)` | `"CSE-A".startsWith("CSE")` | `true` |
| `isEmpty()` / `isBlank()` | `"  ".isBlank()` | `true` |
| `String.join(d, ...)` | `String.join(", ", names)` | `"Riya, Aman, Sneha"` |
| `String.valueOf(x)` | `String.valueOf(438)` | `"438"` |

**`StringBuilder` and `StringBuffer`** — mutable strings, used when a string changes many times:

```java
StringBuilder list = new StringBuilder("CSE-A: ");
for (String n : names) {
    list.append(n).append(", ");
}
list.setLength(list.length() - 2);       // remove last ", "
System.out.println(list);                // CSE-A: Riya, Aman, Sneha
```

| | `String` | `StringBuilder` | `StringBuffer` |
| --- | --- | --- | --- |
| Mutable | No | Yes | Yes |
| Thread-safe | Yes (immutable) | No | Yes (synchronised) |
| Speed for repeated changes | Slow | Fastest | Slower than `StringBuilder` |
| Methods | — | `append`, `insert`, `delete`, `reverse`, `setLength` | same as `StringBuilder` |

### 1.4.10 Wrapper Classes, Autoboxing and Unboxing

**Wrapper class:** a class that wraps a primitive value in an object.

| Primitive | Wrapper |
| --- | --- |
| `byte` | `Byte` |
| `short` | `Short` |
| `int` | `Integer` |
| `long` | `Long` |
| `float` | `Float` |
| `double` | `Double` |
| `char` | `Character` |
| `boolean` | `Boolean` |

```java
String[] parts = "101,Riya,85".split(",");    // a line from a file
int rollNo = Integer.parseInt(parts[0]);      // String → int
int mark   = Integer.parseInt(parts[2]);
String s   = Integer.toString(rollNo);        // int → String

Integer boxed = mark;      // autoboxing:   int → Integer (automatic)
int plain = boxed;         // unboxing:     Integer → int (automatic)
```

**Uses:** converting strings to numbers; storing numbers in collections (Module III), which accept only objects.

> `Integer x = 127, y = 127;` → `x == y` is `true` (values −128 to 127 are cached).
> `Integer p = 128, q = 128;` → `p == q` is `false`. Use `equals()`.

---
---

## 1.5 Classes and Objects

> **Running example — v4: `Student` class**
> Problem with v3: name, roll number and marks are in separate arrays. Sorting one array and not the others mixes up students. Solution: keep each student's data together in one object.

```java
public class Student {
    // fields (instance variables)
    int rollNo;
    String name;
    int[] marks = new int[5];

    // methods
    int total() {
        int sum = 0;
        for (int m : marks) sum += m;
        return sum;
    }

    double percentage() {
        return total() / 5.0;
    }

    void setName(String name) {
        this.name = name;
    }

    Student better(Student other) {
        return this.total() >= other.total() ? this : other;
    }

    void display() {
        System.out.printf("%d  %-6s  %3d  %.1f%%%n", rollNo, name, total(), percentage());
    }
}
```

```java
public class StudentDemo {
    public static void main(String[] args) {
        Student riya = new Student();
        riya.rollNo = 101;
        riya.setName("Riya");
        riya.marks = new int[]{85, 92, 78, 88, 95};

        Student aman = new Student();
        aman.rollNo = 102;
        aman.setName("Aman");
        aman.marks = new int[]{45, 38, 60, 52, 49};

        riya.display();                              // 101  Riya    438  87.6%
        aman.display();                              // 102  Aman    244  48.8%
        System.out.println(riya.better(aman).name);  // Riya
    }
}
```

### 1.5.1 Defining a Class

**Class:** a blueprint that defines the fields (data) and methods (behaviour) of objects.

```java
class ClassName {
    // fields
    // methods
}
```

| Part | In `Student` |
| --- | --- |
| Fields (instance variables) | `rollNo`, `name`, `marks` |
| Methods | `total()`, `percentage()`, `setName()`, `better()`, `display()` |

### 1.5.2 Creating Objects

**Object:** an instance of a class, with its own copy of the fields.

```java
Student riya = new Student();
```

| Part | Meaning |
| --- | --- |
| `Student riya` | Declares a reference variable |
| `new Student()` | Creates the object on the heap |
| `=` | Stores the object's address in `riya` |

Accessing members: `riya.rollNo`, `riya.display()` (dot operator).

### 1.5.3 Memory: Stack and Heap

```java
Student riya = new Student();
riya.rollNo = 101;
Student s2 = riya;
s2.name = "Riya S.";
```

```
      STACK (main)                        HEAP
   +---------------+            +--------------------------+
   | riya  | addr ------------->|  Student object          |
   +---------------+     +----->|  rollNo = 101            |
   | s2    | addr -------+      |  name   = "Riya S."      |
   +---------------+            |  marks  = [..]           |
                                +--------------------------+
```

- **Stack:** local variables and references.
- **Heap:** objects.
- `Student s2 = riya;` copies the **reference**, not the object. Both point to one student, so `riya.name` is now `"Riya S."`.

### 1.5.4 Methods

```java
returnType methodName(parameterList) {
    // body
    return value;      // if returnType is not void
}
```

| Method in v4 | Return type | Parameters |
| --- | --- | --- |
| `total()` | `int` | none |
| `percentage()` | `double` | none |
| `setName(String name)` | `void` | one `String` |
| `better(Student other)` | `Student` | one `Student` |

- Called with `object.method(arguments)`.
- Inside the class, a method can call another method directly: `percentage()` calls `total()`.

### 1.5.5 Pass-by-Value

**Java is always pass-by-value:** a copy of the argument's value is passed.

- Primitive → copy of the value.
- Object → copy of the reference (both point to the same object).

```java
static void addGrace(int mark)   { mark = mark + 5; }
static void addGrace(Student s)  { s.marks[1] += 5; }
static void replace(Student s)   { s = new Student(); s.name = "Ghost"; }

int m = 38;
addGrace(m);       System.out.println(m);              // 38   (copy changed)
addGrace(aman);    System.out.println(aman.marks[1]);  // 43   (same object changed)
replace(aman);     System.out.println(aman.name);      // Aman (caller's reference unchanged)
```

| Passed | What the method can do | What it cannot do |
| --- | --- | --- |
| Primitive | Change its own copy | Change the caller's variable |
| Object reference | Change the object's fields | Make the caller's variable point to another object |

### 1.5.6 The `this` Keyword

**`this`:** a reference to the current object (the object on which the method was called).

| Use | Example |
| --- | --- |
| Refer to a field hidden by a parameter | `this.name = name;` |
| Pass/return the current object | `return this.total() >= other.total() ? this : other;` |
| Call another constructor | `this(rollNo, name, "CSE-A");` (see 1.6.5) |

### 1.5.7 Array of Objects

```java
Student[] cseA = new Student[3];     // 3 references, all null
cseA[0] = riya;
cseA[1] = aman;
cseA[2] = new Student();
cseA[2].rollNo = 103;
cseA[2].setName("Sneha");
cseA[2].marks = new int[]{91, 89, 94, 90, 96};

Student topper = cseA[0];
for (Student s : cseA) {
    topper = topper.better(s);
}
System.out.println("Topper: " + topper.name);   // Sneha
```

```
cseA ──► [ ref | ref | ref ]
            │     │     │
            ▼     ▼     ▼
          Riya  Aman  Sneha     (objects on the heap)
```

- `new Student[3]` creates the array of references only, not the students.
- Calling a method on a `null` element throws `NullPointerException`.

### 1.5.8 Anonymous Objects

**Anonymous object:** an object created without a reference variable; used once.

```java
new Student().display();         // 0  null      0  0.0%
```

### 1.5.9 Garbage Collection

**Garbage collection:** automatic freeing of heap memory used by objects that are no longer reachable.

**When an object becomes eligible**

```java
Student t = new Student();
t = null;                         // 1. reference set to null

Student x = new Student();
Student y = new Student();
x = y;                            // 2. reference reassigned

new Student();                    // 3. anonymous object

void temp() {
    Student s = new Student();    // 4. local reference ends with the method
}
```

- `System.gc();` only **requests** garbage collection; the JVM decides when.
- Java has no `free()` or `delete`.

---
---

## 1.6 Constructors

> **Running example — v5: `Student` with constructors**
> Problem with v4: fields are filled one by one; a forgotten field leaves a student with name `null`. Solution: create the student complete in one statement.

```java
import java.util.Arrays;

public class Student {
    int rollNo;
    String name;
    String section;
    int[] marks;

    {   // instance initialiser block
        marks = new int[5];
        System.out.println("[init] admission form opened");
    }

    Student() {                                         // no-argument
        this(0, "Not set", "Unassigned");
    }

    Student(int rollNo, String name) {                  // parameterised (2)
        this(rollNo, name, "Unassigned");
    }

    Student(int rollNo, String name, String section) {  // parameterised (3)
        this.rollNo = rollNo;
        this.name = name;
        this.section = section;
        System.out.println("[constructor] admitted " + name);
    }

    Student(Student other) {                            // copy constructor
        this(other.rollNo, other.name, other.section);
        this.marks = Arrays.copyOf(other.marks, other.marks.length);
    }

    // total(), percentage(), better(), display() as in v4
}
```

```java
Student riya  = new Student(101, "Riya", "CSE-A");
Student aman  = new Student(102, "Aman");
Student blank = new Student();
Student moved = new Student(riya);      // copy of Riya's record
moved.section = "CSE-B";                // riya.section is still "CSE-A"
```

### 1.6.1 Purpose and Rules

**Constructor:** a special block that initialises a new object; runs automatically when `new` is used.

**Rules**

- Same name as the class.
- No return type, not even `void`.
- Called once per object, at creation.
- Can be overloaded.
- Can have access modifiers (`public`, `private`, …).
- Not inherited; cannot be `static`, `final` or `abstract`.

> `void Student() { }` is a **method**, not a constructor.

| | Constructor | Method |
| --- | --- | --- |
| Name | Same as class | Any name |
| Return type | None | Required (`void` or a type) |
| Called | Automatically by `new` | Explicitly |
| Purpose | Initialise object | Perform an action |
| Inherited | No | Yes |

### 1.6.2 Default Constructor

- Added by the compiler **only if the class has no constructor**.
- Takes no arguments; fields get default values.

```java
class Student { int rollNo; String name; }
// compiler adds:  Student() { super(); }
```

> Once any constructor is written, the compiler does not add the default one. `new Student()` then needs an explicitly written no-argument constructor.

### 1.6.3 No-Argument and Parameterised Constructors

| Type | Example | Call |
| --- | --- | --- |
| No-argument | `Student() { ... }` | `new Student()` |
| Parameterised | `Student(int rollNo, String name) { ... }` | `new Student(102, "Aman")` |

### 1.6.4 Constructor Overloading

**Constructor overloading:** more than one constructor in a class, with different parameter lists (number, type or order).

v5 has four constructors: `()`, `(int, String)`, `(int, String, String)`, `(Student)`. The compiler chooses one based on the arguments.

### 1.6.5 Constructor Chaining with `this()`

**Constructor chaining:** one constructor calling another constructor of the same class using `this(...)`.

```
new Student(102, "Aman")
      │
      └─► Student(int, String)  ──this(...)──►  Student(int, String, String)
                                                  (assigns the fields)
```

**Rules**

- `this(...)` must be the **first statement** in the constructor.
- Recursive chaining (A calls B, B calls A) is a compile error.
- Benefit: field assignment code is written once.

### 1.6.6 Copy Constructor

**Copy constructor:** a constructor that creates a new object with the same values as an existing object of the same class.

- Java does not provide one automatically; it is user-defined.
- Creates a separate object: changing the copy does not change the original.
- `Arrays.copyOf` copies the marks array too; `this.marks = other.marks` would make both students share one array (shallow vs deep copy: Module IV).

### 1.6.7 Instance Initialiser Blocks and Order of Initialisation

**Instance initialiser block:** a `{ }` block inside a class (outside methods) that runs every time an object is created, before the constructor body.

**Order of initialisation for `new Student(102, "Aman")`**

| Step | What happens |
| --- | --- |
| 1 | Memory allocated on the heap; fields get default values |
| 2 | `Student(int, String)` starts and calls `this(...)` |
| 3 | `Student(int, String, String)` calls `super()` (parent constructor, `Object`) |
| 4 | Field initialisers and instance blocks run, top to bottom |
| 5 | Body of `Student(int, String, String)` runs |
| 6 | Rest of `Student(int, String)` runs |

```
[init] admission form opened
[constructor] admitted Aman
```

- The instance block runs **once per object**, even when constructors are chained.
- Static blocks run before all of these, once per class (1.7.3).

---
---

## 1.7 Static Members

> **Running example — v6: college name, automatic roll numbers and student count**
> Problem: the college name is the same for every student, but each object stores its own copy. Also, no single student object knows how many students were admitted. Solution: `static` data that belongs to the whole class, not to one object.

```java
public class Student {
    static String college = "VGU College";   // one copy for all students
    static int count = 0;
    static int nextRollNo = 101;

    int rollNo;                                // one copy per student
    String name;
    int[] marks = new int[5];

    Student(String name) {
        this.rollNo = nextRollNo;              // automatic roll number
        nextRollNo++;
        this.name = name;
        count++;
    }

    static void printCount() {                 // static method
        System.out.println(college + " has " + count + " students");
    }

    void display() {                           // instance method
        System.out.println(rollNo + " | " + name + " | " + college);
    }
}
```

```java
public class AdmissionDesk {
    public static void main(String[] args) {
        Student riya  = new Student("Riya");
        Student aman  = new Student("Aman");
        Student sneha = new Student("Sneha");

        sneha.display();           // 103 | Sneha | VGU College
        Student.printCount();      // VGU College has 3 students

        Student.college = "ABC College";
        riya.display();            // 101 | Riya | ABC College
        aman.display();            // 102 | Aman | ABC College
    }
}
```

### 1.7.1 Static Variables vs Instance Variables

- **Static variable:** declared with `static`; **one copy shared by all objects**.
- **Instance variable:** no `static`; **each object has its own copy**.
- Access static members with the class name: `Student.count`.
- **Problem static solves:** common data (college name, count) is stored once, not in every object. Change it once → every object sees the new value.

```
      Class Student (one copy)            Objects (heap)
   +------------------------------+     +----------------+
   | college    = "VGU College"   |     | rollNo = 101   |  riya
   | count      = 3               |     | name = "Riya"  |
   | nextRollNo = 104             |     +----------------+
   +------------------------------+     | rollNo = 102   |  aman
                                        | name = "Aman"  |
                                        +----------------+
```

### 1.7.2 Static Methods

- Belong to the class; called without an object: `Student.printCount()`.
- Examples from Java: `Math.max(a, b)`, `Integer.parseInt("101")`.
- Can use static members directly.
- Cannot use `this` or instance variables directly (no object exists).

```java
static void printCount() {
    System.out.println(count);         // OK: static
    // System.out.println(name);       // ERROR: instance variable
}
```

### 1.7.3 Static Blocks

- `static { }` runs **once**, when the class is loaded.
- Runs before `main` (if in the main class) and before any object is created.
- Used to give values to static variables.

```java
static {
    college = "VGU College";
    System.out.println("Static block runs once");
}
```

- **Order:** static block (once) → instance block (every object) → constructor (every object)

### 1.7.4 Why `main` Is Static

- When the program starts, no object exists.
- The JVM must call `main` without creating an object.
- Only a static method can be called without an object.

### 1.7.5 Example: Counting Objects Created

- `count++` in the constructor increases the shared counter for every new object.
- `Student.printCount()` shows the total.
- `nextRollNo++` gives each student a unique roll number in the same way.

### 1.7.6 Static vs Instance

| | Static member | Instance member |
| --- | --- | --- |
| Belongs to | Class | Object |
| Number of copies | One | One per object |
| Memory | Method area | Heap (inside the object) |
| Created | When the class is loaded | When the object is created (`new`) |
| Accessed by | `ClassName.member` | `objectReference.member` |
| Example (v6) | `college`, `count`, `printCount()` | `rollNo`, `name`, `display()` |

---
---

## 1.8 Encapsulation

> **Running example — v7: protected `Student`**
> Problem with v6: any code can write `riya.marks[2] = 450;` or `riya.rollNo = 0;`. Solution: hide the fields; allow changes only through methods that check them.

```java
public class Student {
    public static final int PASS_MARK = 40;    // constant
    public static final String COLLEGE = "VGU College";
    private static int count = 0;
    private static int nextRollNo = 101;

    private final int rollNo;                  // cannot change after admission
    private String name;
    private final int[] marks = new int[5];

    public Student(String name) {
        this.rollNo = nextRollNo++;
        setName(name);
        count++;
    }

    // getters
    public int getRollNo()          { return rollNo; }
    public String getName()         { return name; }
    public int getMark(int subject) { return marks[subject]; }
    public static int getCount()    { return count; }

    // setters with validation
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            System.out.println("Name cannot be empty");
            return;
        }
        this.name = name.trim();
    }

    public void setMark(int subject, int mark) {
        if (subject < 0 || subject >= marks.length) {
            System.out.println("Invalid subject: " + subject);
        } else if (mark < 0 || mark > 100) {
            System.out.println("Invalid mark " + mark + " for " + name);
        } else {
            marks[subject] = mark;
        }
    }

    public int total() {
        int sum = 0;
        for (int m : marks) sum += m;
        return sum;
    }

    public double percentage() { return total() / 5.0; }

    public boolean isPassed() {
        for (int m : marks) {
            if (m < PASS_MARK) return false;
        }
        return true;
    }
    // no setRollNo(): roll number is fixed
}
```

```java
Student riya = new Student("Riya");
// riya.marks[2] = 450;          // ERROR: marks has private access
// riya.rollNo = 0;              // ERROR: private and final
riya.setMark(2, 450);            // Invalid mark 450 for Riya
riya.setMark(2, 78);             // accepted
System.out.println(riya.getMark(2));     // 78
```

### 1.8.1 Data Hiding with `private`

**Encapsulation:** wrapping data (fields) and the methods that operate on it into one unit (class), and restricting direct access to the data.

**Data hiding:** declaring fields `private` so they can be accessed only inside the class.

### 1.8.2 Getters and Setters

| | Getter (accessor) | Setter (mutator) |
| --- | --- | --- |
| Purpose | Read a private field | Change a private field |
| Naming | `getName()`, `isPassed()` for boolean | `setName(String name)` |
| Returns | The field's value | Usually `void` |
| Validation | — | Checks the value before storing |

- Not every field needs a setter (`rollNo` has only a getter).
- A setter without validation gives no real protection.

### 1.8.3 Access Modifiers (Overview)

| Modifier | Same class | Same package | Subclass (other package) | Everywhere |
| --- | --- | --- | --- | --- |
| `private` | ✔ | ✘ | ✘ | ✘ |
| default (no keyword) | ✔ | ✔ | ✘ | ✘ |
| `protected` | ✔ | ✔ | ✔ | ✘ |
| `public` | ✔ | ✔ | ✔ | ✔ |

General rule: **fields `private`, methods `public`** (unless only used inside the class).
Full detail with packages: Module IV.

### 1.8.4 The `final` Keyword with Variables

**`final` variable:** can be assigned only once.

| Declaration | Meaning |
| --- | --- |
| `public static final int PASS_MARK = 40;` | Constant: one copy, never changes |
| `private final int rollNo;` | Blank final: assigned once, in the constructor |
| `private final int[] marks = new int[5];` | Reference fixed; array contents can still change |

- Constants are named in `UPPER_SNAKE_CASE`.
- `final` on a reference does not make the object unchangeable.

### 1.8.5 Immutable Classes

**Immutable class:** a class whose objects cannot be changed after creation.

```java
public final class IdCard {
    private final int rollNo;
    private final String name;
    private final String college;

    public IdCard(int rollNo, String name, String college) {
        this.rollNo = rollNo;
        this.name = name;
        this.college = college;
    }

    public int getRollNo()     { return rollNo; }
    public String getName()    { return name; }
    public String getCollege() { return college; }
}
```

```java
IdCard card = new IdCard(riya.getRollNo(), riya.getName(), Student.COLLEGE);
```

**Steps to make a class immutable**

1. Declare the class `final` (cannot be extended).
2. Make all fields `private final`.
3. Set all fields in the constructor.
4. Provide getters only, no setters.
5. Do not return references to mutable internal objects (e.g. return a copy of an array).

Example of an immutable class in Java: `String`.

### 1.8.6 Benefits of Encapsulation

| Benefit | In v7 |
| --- | --- |
| Control | Only marks between 0 and 100 are stored |
| Security | Roll number cannot be changed from outside |
| Maintainability | Internal storage of marks can change without affecting code that uses `setMark()` |
| Read-only / write-only fields | `rollNo` is read-only (getter only) |

---
---

## 1.9 Object Relationships

> **Running example — v8: `Classroom`, `Teacher` and `College`**
> The `Student` class from v7 is now used inside bigger classes.

```java
// AGGREGATION: Classroom has Students created outside it
class Classroom {
    private final String section;
    private final Student[] students = new Student[60];
    private int size = 0;

    Classroom(String section) { this.section = section; }

    String getSection() { return section; }

    void addStudent(Student s) {                  // student passed in
        if (size < students.length) students[size++] = s;
    }

    void printList() {
        System.out.print(section + ": ");
        for (int i = 0; i < size; i++) System.out.print(students[i].getName() + " ");
        System.out.println();
    }
}

// COMPOSITION: College creates and owns its Classrooms
class College {
    private final String name;
    private final Classroom[] rooms;

    College(String name, String[] sections) {
        this.name = name;
        rooms = new Classroom[sections.length];
        for (int i = 0; i < sections.length; i++) {
            rooms[i] = new Classroom(sections[i]);   // created inside
        }
    }

    void admit(Student s, String section) {
        for (Classroom c : rooms) {
            if (c.getSection().equals(section)) { c.addStudent(s); return; }
        }
        System.out.println("No section " + section);
    }

    void printAll() {
        System.out.println(name);
        for (Classroom c : rooms) c.printList();
    }
}

// ASSOCIATION: Teacher knows (mentors) some Students
class Teacher {
    private final String name;
    private final Student[] mentees = new Student[10];
    private int size = 0;

    Teacher(String name) { this.name = name; }

    void addMentee(Student s) { mentees[size++] = s; }

    void printMentees() {
        System.out.print(name + " mentors: ");
        for (int i = 0; i < size; i++) System.out.print(mentees[i].getName() + " ");
        System.out.println();
    }
}
```

```java
public class CollegeApp {
    public static void main(String[] args) {
        College vgu = new College("VGU College", new String[]{"CSE-A", "CSE-B"});

        Student riya  = new Student("Riya");
        Student aman  = new Student("Aman");
        Student sneha = new Student("Sneha");

        vgu.admit(riya, "CSE-A");
        vgu.admit(aman, "CSE-A");
        vgu.admit(sneha, "CSE-B");

        Teacher verma = new Teacher("Mr. Verma");
        verma.addMentee(riya);
        verma.addMentee(sneha);

        vgu.printAll();
        verma.printMentees();

        vgu = null;                           // College and its Classrooms gone
        System.out.println(riya.getName());   // Riya still exists
    }
}
```

```
VGU College
CSE-A: Riya Aman
CSE-B: Sneha
Mr. Verma mentors: Riya Sneha
Riya
```

### 1.9.1 Association

**Association:** a relationship between two independent classes where one uses or knows the other. Neither owns the other.

**By number**

| Type | Example |
| --- | --- |
| One-to-one | One `Student` — one `IdCard` |
| One-to-many | One `Teacher` — many `Student`s (mentees) |
| Many-to-many | Many `Teacher`s — many `Student`s (subjects) |

**By direction**

| Type | Meaning | Example |
| --- | --- | --- |
| Unidirectional | Only one class has a reference to the other | `Teacher` knows its students; `Student` does not know the teacher |
| Bidirectional | Both classes refer to each other | Add `Teacher mentor` field to `Student`; both sides must be kept in sync |

### 1.9.2 Aggregation

**Aggregation:** a weak "has-a" relationship. The whole contains parts, but the parts are created outside and can exist without the whole.

- `Classroom` has `Student`s.
- Students are created first, then passed in with `addStudent()`.
- A student can move to another classroom; if a classroom is removed, the students still exist.
- Classic example: Department and Professor.

### 1.9.3 Composition

**Composition:** a strong "part-of" relationship. The whole creates and owns its parts; the parts cannot exist without the whole.

- `College` creates its `Classroom`s inside its constructor.
- Classrooms are never handed out or shared.
- When the `College` object is destroyed, its classrooms are destroyed too.
- Classic example: House and Room.

### 1.9.4 Association vs Aggregation vs Composition

| Point | Association | Aggregation | Composition |
| --- | --- | --- | --- |
| Relationship | Uses / knows | Has-a (weak) | Part-of (strong) |
| Ownership | None | Whole holds parts | Whole owns parts |
| Part created | Independently | Outside, passed in | Inside the whole |
| Part shared? | Yes | Yes | No |
| Part survives whole? | Yes | Yes | No |
| v8 example | `Teacher` → `Student` | `Classroom` → `Student` | `College` → `Classroom` |
| Classic example | Teacher–Student | Department–Professor | House–Room |
| UML symbol | Line / arrow | Hollow diamond ◇ at whole | Filled diamond ◆ at whole |

> **Test:** If the whole is deleted, should the part be deleted too?
> Yes → composition. No, but the whole holds it → aggregation. They only work together → association.

### 1.9.5 UML Class Diagram Basics

**Class box**

```
+-------------------------------+
|           Student             |   ← class name
+-------------------------------+
| - rollNo : int                |   ← fields
| - name : String               |
| - marks : int[]               |
+-------------------------------+
| + getName() : String          |   ← methods
| + setMark(s : int, m : int)   |
| + total() : int               |
+-------------------------------+
```

**Visibility symbols**

| Symbol | Access |
| --- | --- |
| `-` | private |
| `+` | public |
| `#` | protected |
| `~` | default (package) |

**Relationship symbols**

| Relationship | Symbol |
| --- | --- |
| Association | `──────` or `──────>` (direction) |
| Aggregation | `◇──────` (hollow diamond at the whole) |
| Composition | `◆──────` (filled diamond at the whole) |

**Multiplicity:** `1` (exactly one), `0..1` (zero or one), `*` (many), `1..*` (one or more)

**v8 class diagram**

```
+-------------+ 1     1..* +-------------+ 1        * +-------------+
|   College   |◆───────────|  Classroom  |◇───────────|   Student   |
+-------------+            +-------------+            +-------------+
| - name      |            | - section   |            | - rollNo    |
| - rooms     |            | - students  |            | - name      |
+-------------+            +-------------+            | - marks     |
                                                      +-------------+
                                                             ▲ *
                                                             │
                                                             │ 1
                                                      +-------------+
                                                      |   Teacher   |
                                                      +-------------+
                                                      | - name      |
                                                      | - mentees   |
                                                      +-------------+
```

- `College ◆── Classroom`: composition (classrooms die with the college)
- `Classroom ◇── Student`: aggregation (students live on)
- `Teacher ──> Student`: association (teacher knows students)

---
---

## Practice Programs

| No. | Program | Topics |
| --- | --- | --- |
| 1 | **Student fee account** — pay fees, add money, show balance; refuse overpayment and negative amounts | Encapsulation, validation, `final` |
| 2 | **Class register** — read N students with `Scanner` into an array of `Student` objects; list, search by roll number, find topper | Classes, constructors, array of objects, loops |
| 3 | **Admission counter** — automatic roll numbers and total count of students | `static` variables, static block, static method |
| 4 | **Department with teachers** — one teacher in two departments; deleting a department keeps the teacher | Aggregation |
| 5 | **Student with report card** — `ReportCard` created inside `Student`, never returned | Composition |
| 6 | **College record system** — menu: add student, enter marks, class result, student count, exit (full program in `example/`) | All of Module I |

---
---

## Quick Revision

| Topic | Key points |
| --- | --- |
| 1.1 | OOP = data + methods in objects. Pillars: encapsulation, inheritance, polymorphism, abstraction. Java: Sun, Gosling, 1995 |
| 1.2 | JDK = JRE + tools; JRE = JVM + libraries. `.java` → `javac` → `.class` (bytecode) → JVM. JIT compiles hot code |
| 1.3 | package → import → class → `main`. `public static void main(String[] args)`. File name = public class name. Signature = name + parameters (not return type) |
| 1.4 | 8 primitives. Variable = named memory location. `int / int` = int. `=` assigns, `==` compares. `x++` use then increase, `++x` increase then use. `switch` needs `break`. `do-while` runs at least once. Arrays start at 0. Strings: use `equals()` |
| 1.5 | Class = blueprint, object = instance. References on stack, objects on heap. Java is always pass-by-value. `this` = current object |
| 1.6 | No return type. Default constructor only if none written. `this()` must be first. Copy constructor is user-defined |
| 1.7 | Static = one copy per class, shared by all objects. Called with class name. Static methods: no `this`, no direct instance access. Static block runs once at class load |
| 1.8 | `private` fields + validated getters/setters. `final` = assign once. Immutable: final class, private final fields, no setters |
| 1.9 | Association: knows. Aggregation: has-a, part lives on (◇). Composition: part-of, part dies with whole (◆) |
