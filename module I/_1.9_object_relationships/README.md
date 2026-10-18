# 1.9 Object Relationships

- In real programs, objects work together. One class uses or holds objects of another class.
- Three kinds: **association**, **aggregation**, **composition**.

## Association ("uses" / "knows")

- Two independent classes work together.
- Nobody owns anybody. Both can live alone.
- Example: Teacher teaches Student.

Example: [Association.java](Association.java)

## Aggregation ("has-a", weak)

- The whole **has** parts, but the parts are made **outside** and passed in.
- If the whole is removed, the parts still exist.
- Example: Department has Professors. If the department closes, professors still exist.

Example: [Aggregation.java](Aggregation.java)

## Composition ("part-of", strong)

- The whole **creates** its own parts inside it.
- If the whole is removed, the parts are removed too.
- Example: Hostel has Rooms. No hostel means no hostel rooms.

Example: [Composition.java](Composition.java)

## Comparison

| Point | Association | Aggregation | Composition |
| --- | --- | --- | --- |
| Relation | Uses | Has-a (weak) | Part-of (strong) |
| Part made | Separately | Outside, passed in | Inside the whole |
| Part lives without whole? | Yes | Yes | No |
| Example | Teacher–Student | Department–Professor | Hostel–Room |
| UML | Line `───` | Hollow diamond `◇───` | Filled diamond `◆───` |

> **Quick test:** If the whole is deleted, should the part be deleted too?
> Yes → composition. No → aggregation. They only work together → association.
