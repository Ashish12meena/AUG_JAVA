# Module 2 Final Example: VGU College Portal

The Module 1 example (`Student`, `Classroom`) grown into a class hierarchy.
One program with 8 files that uses most of Module 2 together.
Teach this **after 2.13**.

## What Changed from Module 1

| Module 1 | Module 2 | Why |
| --- | --- | --- |
| `Student` (everything in one class) | `CollegeMember` + `UgStudent` | Common part (id, name) moved up to a parent |
| — | `PgStudent extends UgStudent` | PG is a student with a thesis and its own rules |
| — | `Instructor extends CollegeMember` | Staff share id and name, but have no marks |
| `grade()` in `Student` | `default grade()` in `Gradable` | One grade rule for every class that has marks |
| `Classroom` with `Student[]` | `Section` with `CollegeMember[]` | One array holds students **and** instructors |
| `findStudent(int)` | `find(int)` and `find(String)` | Overloading |
| `display()` | `showDetails()` + `toString()` | Overriding |

## Classes

```
              CollegeMember  (abstract)
               /          \
        UgStudent        Instructor
  implements Gradable,
             FeePayer
            |
        PgStudent  (final)

Section ◇──── CollegeMember[]   (has-a)
```

| File | Job |
| --- | --- |
| `CollegeMember` | Abstract parent: auto id, name, `toString()`, `equals()`, `hashCode()` |
| `Gradable` | Interface: marks, total, pass/fail, `default grade()` |
| `FeePayer` | Interface: `fee()`, `static lateFine()` |
| `UgStudent` | 5 marks, UG fee 1,20,000 |
| `PgStudent` | Thesis, PG fee 1,50,000, must also get 50% overall |
| `Instructor` | Subject and salary; no marks, no fee |
| `Section` | Holds all members. Add, find, show all, topper, total fee |
| `PortalApp` | `main` method with a menu |

## Concepts Used

| Concept | Where |
| --- | --- |
| 2.1 `extends`, is-a | `UgStudent extends CollegeMember` |
| 2.1 private not inherited | `PgStudent` uses `total()`, not `marks[]` |
| 2.2 Multilevel | `CollegeMember` → `UgStudent` → `PgStudent` |
| 2.2 Hierarchical | `UgStudent` and `Instructor` both extend `CollegeMember` |
| 2.3 `super()` | Every child constructor |
| 2.3 `super.method()` | `PgStudent.showDetails()`, `PgStudent.isPassed()` |
| 2.4 Overloading | `setMark(int, int)` / `setMark(int[])`, `find(int)` / `find(String)` |
| 2.5 Overriding, `@Override` | `role()`, `showDetails()`, `fee()`, `isPassed()` |
| 2.7 Upcasting | `CollegeMember[] members` in `Section` |
| 2.7 Dynamic dispatch | `members[i].showDetails()` in `Section.showAll()` |
| 2.7 `instanceof`, downcasting | `showTopper()`, `totalFee()`, menu option 4 |
| 2.8 Blank `final` | `id` in `CollegeMember` |
| 2.8 `final` method / class | `getId()`, `PgStudent` |
| 2.9 `toString()` | `CollegeMember`; used by `println(s)` |
| 2.9 `equals()`, `hashCode()` | `CollegeMember`; `Section.add()` stops duplicates |
| 2.9 `getClass()` | Menu option 6 |
| 2.10 Abstract class and methods | `CollegeMember`, `role()`, `showDetails()` |
| 2.11 Interface, constant | `Gradable`, `PASS_MARK` |
| 2.11 Multiple interfaces | `UgStudent implements Gradable, FeePayer` |
| 2.11 `default` / `static` methods | `Gradable.grade()`, `FeePayer.lateFine()` |
| 2.12 Abstract class + interface together | `CollegeMember` (shared code) + `Gradable`, `FeePayer` (abilities) |
| 2.13 Composition (has-a) | `Section` has members, does not extend them |

Every concept is also marked with a comment in the code, with its section number, e.g. `// 2.7 Upcasting`.

## How to Run

```
javac *.java
java PortalApp
```

Sample data already added in `CSE-A`:

| Id | Name | Type | Result |
| --- | --- | --- | --- |
| 101 | Ritika | UG | 438, 87.6%, PASS |
| 102 | Disha | PG | 244, 48.8%, **FAIL** (every subject ≥ 40, but PG needs 50%) |
| 103 | Verma | Instructor | no marks |

Names and thesis are read with `sc.next()`, so type them as one word.

## Teaching Order (build it step by step)

1. **`CollegeMember` + `UgStudent`**: move id and name into the parent. Show `super(name)`. Make the parent `abstract`.
2. **`Instructor`**: second child (hierarchical). Show that it has no marks.
3. **`toString()`, `equals()`, `hashCode()`**: print a member with `println`.
4. **`Gradable`, `FeePayer`**: move `grade()` into the interface as a `default` method.
5. **`PgStudent`**: multilevel, override `fee()`, `isPassed()`, `showDetails()` with `super`. Make it `final`.
6. **`Section`**: parent-type array, dynamic dispatch, `instanceof`, overloaded `find`.
7. **`PortalApp`**: menu last.

Run the program after every step.

## Exercises for Students

1. Add `NonTeachingStaff extends CollegeMember` (role, salary). No other file should need a change except `PortalApp`.
2. Add an interface `Rewardable` with `double scholarship()`. UG gets 10% of fee back if percentage ≥ 90.
3. Show the average percentage of all `Gradable` members in a section.
4. Add a menu option to remove a member by id.
5. Try to write `class PhdStudent extends PgStudent`. What error do you get and why?
