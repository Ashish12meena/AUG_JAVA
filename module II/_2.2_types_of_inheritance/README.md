# 2.2 Types of Inheritance

## With Classes (allowed)

| Type | Shape | Example |
| --- | --- | --- |
| Single | A → B | `Staff` → `Librarian` |
| Multilevel | A → B → C | `Individual` → `Undergrad` → `Postgrad` |
| Hierarchical | A → B, A → C | `OfficeStaff` → `Accountant`, `LabAssistant` |

```
Single         Multilevel       Hierarchical
 Staff         Individual        OfficeStaff
   |               |               /     \
Librarian      Undergrad    Accountant  LabAssistant
                   |
                Postgrad
```

Examples:
[SingleInheritance.java](SingleInheritance.java),
[MultilevelInheritance.java](MultilevelInheritance.java),
[HierarchicalInheritance.java](HierarchicalInheritance.java)

## Only With Interfaces

| Type | Meaning |
| --- | --- |
| Multiple | One class, **two or more parents** |
| Hybrid | Mix of two or more types (includes multiple) |

- A class can `extends` only **one** class.
- A class can `implements` **many** interfaces (2.11).

## Diamond Problem

```
  ExamCell   SportsCell     both have work()
      \         /
     Coordinator            which work()?
```

- If both parents have `work()`, Java cannot decide which one the child gets.
- So `class Coordinator extends ExamCell, SportsCell` is a **compile error**.
- Interfaces solve it: the class writes its own `work()` (see 2.11 `Interfaces.java`, a teaching assistant who studies and teaches).

Example: [DiamondProblem.java](DiamondProblem.java)
