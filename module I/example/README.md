# Module 1 Final Example: College Record System

One program with 4 classes that uses most of Module 1 together.
Teach this **after 1.9 (Object Relationships)**.

## Classes

| Class | Job |
| --- | --- |
| `Student` | Roll no, name, 5 marks. Calculates total, percentage, grade, pass/fail |
| `Classroom` | Holds students of one section. Add, find, show result, find topper |
| `College` | Creates its classrooms. Finds a student in any section |
| `CollegeApp` | `main` method with a menu |

```
College ◆──── Classroom ◇──── Student
(creates its           (students are created
 classrooms)            outside and added)
```

- **College → Classroom:** composition (college creates and owns the classrooms).
- **Classroom → Student:** aggregation (student is created outside, then added).

## Concepts Used

| Concept | Where |
| --- | --- |
| Class, object, `new` | All classes |
| Constructor, `this` | `Student(String name)`, `Classroom(String section)` |
| Encapsulation (`private` + getters) | Fields of every class |
| Validation in a setter | `Student.setMark()` accepts only 0 to 100 |
| `static` variable and method | `count`, `nextRollNo`, `Student.getCount()` |
| Arrays, array of objects | `marks`, `Student[] students`, `Classroom[] classrooms` |
| `else-if` ladder | `Student.grade()` |
| Ternary operator | `isPassed() ? "PASS" : "FAIL"` |
| Loops | Total, find student, find topper |
| `Scanner`, `do-while`, `switch` | Menu in `CollegeApp` |
| Composition, aggregation | `College`, `Classroom` |

Every concept is also marked with a comment in the code, with its section number, e.g. `// 1.8 Setter with validation`.

## How to Run

```
javac *.java
java CollegeApp
```

Ritika and Disha are already added as sample data in `CSE-A`.

## Teaching Order (build it step by step)

1. **`Student`**: write the class, create 2 students in a small `main`, call `display()`.
2. **Add `static`**: `count` and automatic roll numbers. Show that every student gets the next roll number.
3. **`Classroom`**: add students, show result, find topper. Point out that students are created outside (aggregation).
4. **`College`**: creates `CSE-A` and `CSE-B` itself (composition).
5. **`CollegeApp`**: add the menu last.

Run the program after every step.

## Exercises for Students

1. Add a third section `CSE-C`.
2. Add a menu option to show only failed students of a section.
3. Show the class average of a section.
4. Do not allow adding a student with an empty name.
