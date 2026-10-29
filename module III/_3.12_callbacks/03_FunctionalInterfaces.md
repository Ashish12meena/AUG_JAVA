# 3.12 Callbacks, Part 3: Functional Interfaces, Lambdas and Method References

Program: [FunctionalInterfaces.java](FunctionalInterfaces.java) (`Runnable`, `Predicate`, `Function`, `Consumer`, method references, streams)

## Why Do We Need Them?

Look at how much code an anonymous class needs to say one thing, "print a line when a fee is paid":

```java
counter.addListener(new FeePaidListener() {
    @Override
    public void feePaid(FeeEvent e) {
        System.out.println("SMS to parent: fee of " + e.studentName + " received");
    }
});
```

Five lines of ceremony around **one** line of behaviour. Callbacks are used everywhere (sorting, filtering, buttons, threads), so this noise adds up fast.

Java 8 added **lambdas**: a way to write just the behaviour. And to know **which** method a lambda is for, Java needs an interface with exactly one abstract method: a **functional interface**.

## What Are They?

- **Functional interface:** An interface with exactly **one abstract method**. A lambda or method reference can be used wherever one is expected. (`default` and `static` methods do not count.)
- **`@FunctionalInterface`:** An optional annotation. The compiler then gives an error if someone adds a second abstract method.
- **Lambda expression:** A short way to write the body of that one method: `parameters -> body`.
- **Method reference:** An even shorter form of a lambda that only calls one existing method: `Callbacks::printReceipt`.

> ⭐ **Remember:** A lambda has no type of its own. It takes the type of the functional interface it is assigned to. That is why it only works where **one** abstract method is expected: Java must know which method the lambda is.

## How Does a Lambda Work?

The lambda is matched to the one abstract method of the interface:

```java
interface FeePaidListener { void feePaid(FeeEvent e); }

FeePaidListener examCell = e -> System.out.println("Hall ticket ready for " + e.studentName);
//                         ^ parameter of feePaid()   ^ body of feePaid()
```

Java creates an object that implements `FeePaidListener`, with this body as its `feePaid()`.

### Lambda syntax forms

| Form | Example | When |
| --- | --- | --- |
| No parameters | `() -> System.out.println("Bell rings")` | `Runnable` |
| One parameter | `s -> s.total >= 250` | Brackets optional |
| Several parameters | `(a, b) -> b.total - a.total` | Brackets needed |
| With types | `(Student s) -> s.total` | When Java cannot guess the type |
| Block body | `s -> { double p = s.total / 5.0; return p; }` | More than one statement; needs `return` |

Rules: parameter types are usually **inferred**; a one-expression body returns its value automatically; local variables used inside must be **effectively final** (3.11).

## Built-in Functional Interfaces (`java.util.function`)

We do not need to write a new interface for every common shape of behaviour. Java gives ready-made ones:

| Interface | Method | Takes → Gives | Our example |
| --- | --- | --- | --- |
| `Runnable` | `run()` | nothing → nothing | Bell rings |
| `Supplier<T>` | `get()` | nothing → T | Make a new object (not in our program) |
| `Consumer<T>` | `accept(t)` | T → nothing | Print a student |
| `Predicate<T>` | `test(t)` | T → boolean | Passed? `s.total >= 250` |
| `Function<T, R>` | `apply(t)` | T → R | Student → percentage |
| `Comparator<T>` | `compare(a, b)` | two T → int | Sort by total |

How to remember them, by the question each answers:

- **Predicate:** "Is it true for this one?" (filter)
- **Function:** "Change this one into something else." (convert)
- **Consumer:** "Do something with this one." (use)
- **Supplier:** "Give me one." (create)

```java
Predicate<Student> passed = s -> s.total >= 250;
Function<Student, Double> percent = s -> s.total / 5.0;
Consumer<Student> show = s -> System.out.println(s.name + "  " + percent.apply(s) + "%");

for (Student s : list) {
    if (passed.test(s)) show.accept(s);
}
```

The behaviour (pass rule, percentage, printing) is now in **variables** that can be passed around, which is what callbacks need.

## Method References

When a lambda does nothing except call one existing method, write a **method reference** instead. `::` means "use this method as the behaviour".

| Kind | Lambda | Method reference |
| --- | --- | --- |
| Static method | `e -> Callbacks.printReceipt(e)` | `Callbacks::printReceipt` |
| Method of a particular object | `n -> System.out.println(n)` | `System.out::println` |
| Method of the parameter itself | `s -> s.getName()` | `Student::getName` |
| Constructor | `() -> new ArrayList<>()` | `ArrayList::new` |

```java
names.forEach(System.out::println);     // same as  n -> System.out.println(n)
```

## Anonymous Class vs Lambda vs Method Reference

| | Anonymous class | Lambda | Method reference |
| --- | --- | --- | --- |
| Needs a functional interface? | No | Yes | Yes |
| Length | Longest | Short | Shortest |
| Can hold fields? | Yes | No | No |
| Best when | Several methods or state | New short behaviour | Behaviour already exists as a method |

## Optional: Streams (Callbacks in a Pipeline)

Streams (Java 8) process a collection in steps, and each step takes a callback:

```java
List<String> scholarship = list.stream()
        .filter(s -> s.total >= 400)       // Predicate: keep some
        .map(s -> s.name)                  // Function: change each
        .collect(Collectors.toList());     // back into a List
// [Somya, Hita, Disha, Ritika]   (the list was already sorted by total)
```

## Callbacks Are Everywhere

| Where | Interface | Called by |
| --- | --- | --- |
| `list.sort(comp)` | `Comparator` | `sort()` |
| `list.forEach(...)` | `Consumer` | `forEach()` |
| `list.removeIf(...)` | `Predicate` | `removeIf()` |
| Button click | `ActionListener` | Swing |
| Threads (Module V) | `Runnable` | the thread |

## Common Mistakes

- Using a lambda for an interface with two abstract methods → compile error. Use an anonymous class.
- Changing a local variable that a lambda uses → "variable used in lambda should be final or effectively final".
- Forgetting `return` in a block-body lambda: `s -> { s.total / 5.0; }` is an error.

## Summary

- A functional interface has exactly one abstract method; lambdas and method references implement it.
- Lambda = just the behaviour: `parameters -> body`.
- Built-ins: `Runnable`, `Supplier`, `Consumer`, `Predicate`, `Function`, `Comparator`.
- Method reference `Class::method` replaces a lambda that only calls one method.

Previous: [Part 2](02_ListenerPattern.md) · Back to [3.12](README.md) · Final example: [Module III example](../example/README.md)
