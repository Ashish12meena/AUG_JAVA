# 2.12 Abstract Class vs Interface

Example: [AbstractVsInterface.java](AbstractVsInterface.java) (`Person` + `FeePayer`)

| | Abstract class | Interface |
| --- | --- | --- |
| Keyword | `abstract class` | `interface` |
| Child uses | `extends` (only one) | `implements` (many) |
| Methods | Abstract + normal | Abstract + `default` + `static` + `private` |
| Variables | Any kind (instance, static, final...) | Only `public static final` |
| Constructor | Yes | No |
| Access of methods | Any | `public` (except `private` helpers) |
| Object can be created? | No | No |
| Abstraction | Partial (0 to 100%) | Full (traditionally) |
| Relationship | "is-a" (a student **is a** Person) | "can-do" (a student **can pay** a fee) |

- Teach the example first, **then** this table.

## When to Choose

**Abstract class** when:
- Children are closely related (same family): Student and Teacher are both Persons.
- They share **fields** and some **common code**: `name`, `enter()`.
- You need a constructor.

**Interface** when:
- Only some classes need the ability: students pay a fee, teachers don't.
- A class needs more than one parent type: a teaching assistant learns **and** teaches.
- You only want to say **what** to do, not how.

> Common design: abstract class for shared code (`Person`) + interface for the ability (`FeePayer`) + normal classes (`Student`, `Teacher`).
