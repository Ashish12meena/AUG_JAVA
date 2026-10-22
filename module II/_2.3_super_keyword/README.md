# 2.3 The `super` Keyword

Example: [SuperKeyword.java](SuperKeyword.java) (`Person` → `Student` → `PgStudent`)

- `this` → current object.
- `super` → the **parent part** of the current object.

| Use | Syntax | Why |
| --- | --- | --- |
| Parent's field | `super.type` | Child has a field with the same name |
| Parent's method | `super.show()` | Child changed the method but also wants the parent's work |
| Parent's constructor | `super(name)` | Set the parent's fields |

## Parent Field: `super.type`

- If child and parent both have `type`, plain `type` means the child's.
- `super.type` means the parent's.

```java
System.out.println(type);         // Student (child)
System.out.println(super.type);   // Person  (parent)
```

## Parent Method: `super.show()`

- Child adds to the parent's work instead of writing it again.
- `PgStudent.show()` → `Student.show()` → `Person.show()`: each one adds its own line.

## Parent Constructor: `super(...)`

- Child constructor calls the parent constructor with `super(...)`.
- Must be the **first line** of the child constructor.

```java
Student(String name, int rollNo) {
    super(name);          // Person sets the name
    this.rollNo = rollNo; // Student sets the roll no
}
```

## Constructor Order

- Parent constructor runs **first**, then the child: `Person` → `Student` → `PgStudent`.
- If we don't write `super()`, Java adds `super()` automatically (calls the no-argument constructor).
- If the parent has **no** no-argument constructor, we **must** write `super(...)`.
  - `Person` has only `Person(String name)`, so `Teacher() { }` is an error.

## Rules

- `this()` and `super()` must be the **first** statement.
- So both **cannot** be in the same constructor.
- `super` cannot be used inside a `static` method.
