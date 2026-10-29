# 3.11 Nested Classes, Part 3: Local and Anonymous Classes

Program: [NestedClasses.java](NestedClasses.java) (`Invigilator` inside `conductExam()`, and the `Greeting` objects in `main()`)

These two kinds are written **inside code** (a method or an expression), not in the class body. They are the most "temporary" kinds of class.

## A. Local Class

### Why?

An **Invigilator** is needed only while `conductExam()` runs. No other method, and no other class, should ever see it.
Putting it in the class body (even as an inner class) would make it visible to the whole `College` class. We want its scope to be as small as its use.

### What is it?

**Local class:** A class written **inside a method** (or any block). It can be used only inside that method, just like a local variable.

```java
void conductExam(String subject) {
    class Invigilator {
        String teacher;
        Invigilator(String teacher) { this.teacher = teacher; }

        void check() {
            System.out.println(teacher + " checks hall tickets for the " + subject + " exam at " + name);
        }
    }

    Invigilator inv = new Invigilator("Mr. Verma");
    inv.check();
}
```

### How does it work?

- It is visible only from its declaration to the end of the method.
- It can use the outer class's members (`name`), like an inner class.
- It can use the method's **local variables and parameters** (`subject`), but only if they are **effectively final**.

### Why "effectively final"?

**Effectively final:** a variable whose value is **never changed** after it is first given a value (even without the word `final`).

The reason: local variables live only while the method runs. An object of the local class may live **longer** (it could be stored somewhere and used later). So Java gives the object its **own copy** of the variable's value.
If the variable could still change, the copy and the original could disagree, and the program would be confusing. Java avoids this by allowing only variables that never change.

> ⭐ **Remember:** Local and anonymous classes (and lambdas) can use a method's local variables only if they are **effectively final**: assigned once, never changed.

## B. Anonymous Class

### Why?

To welcome Hita, we need **one** object that implements `Greeting`, used **once**, right here.
Writing a full named class (`class WelcomeGreeting implements Greeting { ... }`) somewhere else, just to create one object, is a lot of ceremony.
An anonymous class lets us **declare the class and create its object in one step**, exactly where it is needed.

### What is it?

**Anonymous class:** A class with **no name**, declared and instantiated in a single expression. It either **extends a class** or **implements an interface** (exactly one).

### How do we read the syntax?

```java
Greeting welcome = new Greeting() {          // "make an object of a new class that implements Greeting"
    @Override
    public void greet(String name) {         // the class body
        System.out.println("Welcome to VGU College, " + name);
    }
};                                           // note the semicolon: this whole thing is one expression

welcome.greet("Hita");
```

| Part | Meaning |
| --- | --- |
| `new Greeting()` | create an object of a class that implements `Greeting` (or extends it, if it is a class) |
| `{ ... }` | the body of that nameless class |
| `;` | end of the statement that assigns the object |

### Rules

- No name → it cannot have a **constructor** (a constructor needs the class name).
- It can implement **one** interface **or** extend **one** class, not both.
- Only **one object** is made from it, at that place.
- It can have fields and extra methods, and override several methods.
- It follows the same "effectively final" rule as a local class.

### Where do we use it?

Whenever we need a **one-time implementation of an interface**:

- a `Comparator` for one sort (3.8),
- a listener: "do this **when** the button is clicked" (3.12).

This is why anonymous classes are the classic way to write **callbacks**.

## Anonymous Class vs Lambda

Java 8 added **lambdas**, a shorter way to write the same thing when the interface has only **one** abstract method:

```java
Greeting luck = name -> System.out.println("Best of luck for the exam, " + name);
```

| | Anonymous class | Lambda |
| --- | --- | --- |
| Works with | Any interface or class | Only a **functional** interface (one abstract method) |
| Can override | Several methods | Exactly one |
| Can have fields / state | Yes | No |
| `this` means | The anonymous object itself | The surrounding object |
| Separate `.class` file | Yes (`NestedClasses$1.class`) | No |
| Code length | Longer | Very short |

> ⭐ **Remember:** A lambda can replace an anonymous class **only** when the interface has exactly one abstract method. Otherwise, use the anonymous class.

## Local vs Anonymous

| | Local class | Anonymous class |
| --- | --- | --- |
| Has a name? | Yes | No |
| Objects | Can create many (`new Invigilator(...)` again) | Exactly one, where it is written |
| Constructor | Yes | No |
| Implements | Any number of interfaces | One interface **or** one class |

## Summary

- Local class: a named class inside a method; visible only there.
- Anonymous class: a nameless class, declared and created at once; one object.
- Both can use effectively final local variables.
- A lambda is a shorter form of an anonymous class for one-method interfaces; both lead into callbacks (3.12).

Previous: [Part 2](02_StaticAndInnerClasses.md) · Back to [3.11](README.md) · Next topic: [3.12 Callbacks](../_3.12_callbacks/README.md)
