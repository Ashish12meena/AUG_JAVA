# Module II: Inheritance, Polymorphism and Abstraction

How classes reuse and extend each other, how one reference can behave in many ways, and how abstract classes and interfaces define contracts.

## One Example for the Whole Module

Every topic grows the **same** example: people in VGU College.

```
            Person
           /            Student    Teacher
         |
     PgStudent
```

| Person | Who |
| --- | --- |
| Ritika | UG student, roll no 101, marks 85 92 78 88 95 (438), fee 1,20,000 |
| Disha | PG student, roll no 102, thesis "Cloud Computing", fee 1,50,000 |
| Mr. Verma | Teacher, Java |

Question to keep asking: **"The bell rings. What does each person do?"** → `work()`

- Every folder uses the **same class names**: `Person`, `Student`, `PgStudent`, `Teacher`.
  Only 2.11 adds `TeachingAssistant` and the interfaces `FeePayer`, `Learning`, `Teaching`; 2.13 adds `MarkList`.
- Run each program from its own folder (`javac` + `java`), so the same names don't clash.
  VS Code sees all folders as one project, so it may underline a class as "already defined". The program still runs.

| Topic | Folder |
| --- | --- |
| 2.1 Inheritance basics | [_2.1_inheritance_basics](_2.1_inheritance_basics/README.md) |
| 2.2 Types of inheritance | [_2.2_types_of_inheritance](_2.2_types_of_inheritance/README.md) |
| 2.3 The `super` keyword | [_2.3_super_keyword](_2.3_super_keyword/README.md) |
| 2.4 Method overloading | [_2.4_method_overloading](_2.4_method_overloading/README.md) |
| 2.5 Method overriding | [_2.5_method_overriding](_2.5_method_overriding/README.md) |
| 2.6 Overloading vs overriding | [_2.6_overloading_vs_overriding](_2.6_overloading_vs_overriding/README.md) |
| 2.7 Polymorphism and dynamic dispatch | [_2.7_polymorphism](_2.7_polymorphism/README.md) |
| 2.8 The `final` keyword | [_2.8_final_keyword](_2.8_final_keyword/README.md) |
| 2.9 Methods of the `Object` class | [_2.9_object_class_methods](_2.9_object_class_methods/README.md) |
| 2.10 Abstract classes | [_2.10_abstract_class](_2.10_abstract_class/README.md) |
| 2.11 Interfaces | [_2.11_interfaces](_2.11_interfaces/README.md) |
| 2.12 Abstract class vs interface | [_2.12_abstract_vs_interface](_2.12_abstract_vs_interface/README.md) |
| 2.13 Inheritance vs composition | [_2.13_inheritance_vs_composition](_2.13_inheritance_vs_composition/README.md) |
| **Final example** (all topics together) | [example](example/README.md) |

Run any program from its folder:

```
javac FileName.java
java FileName
```
