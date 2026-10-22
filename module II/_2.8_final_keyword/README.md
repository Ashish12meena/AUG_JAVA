# 2.8 The `final` Keyword

Example: [FinalKeyword.java](FinalKeyword.java) (`Person` → `Student` → `PgStudent`)

| Used on | Meaning |
| --- | --- |
| Variable | Value set **once**, cannot change |
| Method | Cannot be **overridden** |
| Class | Cannot be **extended** |

## `final` Variables

- `final int MAX_MARKS = 100;` → changing it later is an error.
- `static final` → constant, name in `UPPER_CASE`: `COLLEGE = "VGU College"` (seen in 1.8).
- **Blank `final`:** `final` field with no value. Must be set in **every** constructor.
  - Each person gets their own `id`, which then never changes.

## `final` Methods

- Child can **use** it but cannot **override** it.
- Use when the logic must stay the same for all children: `showId()`.

## `final` Classes

- No class can `extends` it.
- `final class PgStudent` → `class PhdStudent extends PgStudent` is an error.
- Examples from Java: `String`, `Integer`, `Math`.
- All methods of a `final` class are automatically final.
