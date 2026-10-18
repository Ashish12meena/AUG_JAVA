# 1.6 Constructors

## Constructor

- A constructor is a special block that sets up a new object.
- It runs automatically when `new` is used.
- **Rules:**
  - Same name as the class.
  - No return type, not even `void`.
  - Runs once for each object.

```java
class Student {
    String name;

    Student(String name) {   // constructor
        this.name = name;
    }
}
```

> `void Student() { }` is a **method**, not a constructor.

| | Constructor | Method |
| --- | --- | --- |
| Name | Same as class | Any name |
| Return type | None | Required |
| Called | Automatically by `new` | By us |

## Default Constructor

- If we write **no constructor**, the compiler adds an empty one.
- Fields get default values: `0`, `null`, `false`.
- If we write any constructor, the compiler does **not** add the default one.

Example: [DefaultConstructor.java](DefaultConstructor.java)

## Parameterised Constructor

- Takes values when the object is created.
- The object is complete in one line: `new Lecturer("Mr. Verma", "Java")`.

Example: [ParameterisedConstructor.java](ParameterisedConstructor.java)

## Constructor Overloading

- More than one constructor in a class, each with different parameters.
- Java picks the constructor that matches the arguments.

Example: [ConstructorOverloading.java](ConstructorOverloading.java)

## Constructor Chaining: `this()`

- One constructor calls another constructor of the same class with `this(...)`.
- `this(...)` must be the **first line**.
- Field assignment code is written only once.

Example: [ConstructorChaining.java](ConstructorChaining.java)

## Copy Constructor

- Makes a new object with the same values as another object.
- Changing the copy does not change the original.

Example: [CopyConstructor.java](CopyConstructor.java)

## Instance Initialiser Block

- A `{ }` block inside the class, outside methods.
- Runs every time an object is created, **before** the constructor body.

Example: [InitializerBlock.java](InitializerBlock.java)
