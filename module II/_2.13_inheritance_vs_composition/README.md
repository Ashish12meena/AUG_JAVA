# 2.13 Inheritance vs Composition

Example: [InheritanceVsComposition.java](InheritanceVsComposition.java) (`Person` → `Student`, which has a `MarkList`)

| | Inheritance | Composition |
| --- | --- | --- |
| Relationship | **is-a** | **has-a** |
| How | `class B extends A` | `class B { A a; }` |
| Example | Student **is a** Person | Student **has a** mark list |
| Link | Fixed at compile time | Can change at run time (new semester, new mark list) |
| Coupling | Tight: parent change affects child | Loose |
| Parents | Only one class | Any number of objects |

- **Test:** say "B is a A". If it sounds wrong, don't use inheritance.
  - "Student is a Person" ✔ → inheritance.
  - "Student is a MarkList" ✘ → composition.
- The same `Student` class uses **both**.

## Why Favour Composition

- Inheritance just to reuse code gives the child methods it should not have.
  - If `Student extends MarkList`, then `student.setMark(2, 82)` makes no sense: a student is not a list of marks.
- With composition the student **uses** the list's methods: `markList.setMark(2, 82)`.
- Easy to replace the part: new semester → new `MarkList` object.
- Rule: **use inheritance only for a true "is-a"; otherwise use composition.**
