# 1.5 Classes and Objects

## Class

- A class is a blueprint that defines the data (fields) and behaviour (methods) of objects.

```java
class Student {
    String name;     // field
    int age;         // field

    void display() { // method
        System.out.println(name + " " + age);
    }
}
```

## Object

- An object is an instance of a class.
- Each object has its own copy of the fields.
- Created with `new`.

```java
Student s1 = new Student();
s1.name = "Riya";
s1.display();
```

- `Student s1` → reference variable
- `new Student()` → creates the object in heap memory
- Use the dot `.` to access fields and methods: `s1.name`, `s1.display()`

## Memory

- **Stack:** local variables and references (`s1`).
- **Heap:** objects.
- `Student s2 = s1;` copies the reference, not the object. Both point to the same object.

## Methods

- `returnType methodName(parameters) { }`
- `void` → returns nothing.
- Called using the object: `s1.display()`.

## `this` Keyword

- `this` refers to the current object.
- Used when the field and parameter have the same name: `this.name = name;`

## Garbage Collection

- Objects that are no longer used are removed from memory automatically.
- Java has no `free()` or `delete`.
