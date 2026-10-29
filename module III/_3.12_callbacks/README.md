# 3.12 Event-Driven Programming and Callbacks

Until now, our programs ran top to bottom and **we** decided when every method was called. In event-driven programs, things happen **when an event occurs** (a fee is paid, a button is clicked), and our code is **called back**. The topic has three parts: the idea, the standard design (listener pattern), and the short Java syntax for it (functional interfaces and lambdas).

## Reading Order

| Part | Notes | Question it answers | Program |
| --- | --- | --- | --- |
| 1 | [01_WhyCallbacks.md](01_WhyCallbacks.md) | What is a callback? Why "don't call us, we'll call you"? | – |
| 2 | [02_ListenerPattern.md](02_ListenerPattern.md) | How do we build event source, event and listener? | [Callbacks.java](Callbacks.java), [ButtonClick.java](ButtonClick.java) |
| 3 | [03_FunctionalInterfaces.md](03_FunctionalInterfaces.md) | How do lambdas and method references make callbacks short? | [FunctionalInterfaces.java](FunctionalInterfaces.java) |

## The Big Picture

```
Normal call:   my code  ---- calls ---->  method            (I decide WHEN)

Callback:      my code  -- gives behaviour -->  other object
               other object  -- calls it back LATER -->  my behaviour   (IT decides WHEN)
```

Built on: interfaces (Module II), anonymous classes and lambdas (3.11).

## Run

```
javac Callbacks.java
java Callbacks
```

`ButtonClick` opens a small window; close it with the X button.
