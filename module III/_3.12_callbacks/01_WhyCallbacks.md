# 3.12 Callbacks, Part 1: Why Callbacks?

## Why Do We Need Callbacks?

Hita pays her fee at the fee counter. Now three things must happen:

- the **accounts office** makes an entry,
- her **parents** get an SMS,
- the **exam cell** releases her hall ticket.

### Attempt 1: the fee counter calls everyone itself

```java
void collect(String name, double amount) {
    accounts.makeEntry(name, amount);
    sms.sendToParent(name);
    examCell.releaseHallTicket(name);
}
```

Problems:

- **Tightly coupled:** `FeeCounter` must know every office class and its method names.
- **Must change for every new office:** next month the library wants to know too (to clear dues). We must edit `FeeCounter` again. And again for the hostel. A class that collects fees should not change because a new office was opened.
- **Cannot be reused:** another college with different offices cannot use this `FeeCounter`.

### Attempt 2: every office keeps asking

Each office keeps checking: "Has anyone paid yet? ... Has anyone paid yet?" This is called **polling**. It wastes time, and an office that checks late gets the news late.

### The better idea: "call me when it happens"

Each office **registers** with the fee counter once: "here is what to do when a fee is paid". The counter keeps a list. When a fee is paid, it simply goes down the list and **calls back** each one.

- The counter does not know or care **who** is on the list.
- A new office just registers; `FeeCounter` does **not** change.

This idea is often summed up as the **Hollywood principle**: *"Don't call us, we'll call you."*

## What Is a Callback?

- **Event:** Something that happens: a fee is paid, a button is clicked, a key is pressed, a timer ends.
- **Event-driven programming:** A style where the program **waits** for events and runs code **when** they happen, instead of running in a fixed top-to-bottom order. Every GUI, game and web server works like this.
- **Callback:** A piece of behaviour (code) passed to another object, so that object can **call it later**, at the right moment.

### Normal call vs callback

| | Normal method call | Callback |
| --- | --- | --- |
| Who decides **what** runs? | The caller | The caller (it supplies the behaviour) |
| Who decides **when** it runs? | The caller, right now | The **other** object, later |
| Example | `Collections.sort(list)` | "When the button is clicked, show the result" |

> ⭐ **Remember:** In a callback, **we** decide **what** to do, but **someone else** decides **when** to do it.

## How Does Java Pass "Behaviour"?

In Java we cannot pass a method by itself as an argument. We can only pass **objects**.
So we pass an **object whose method contains the behaviour**, and an **interface** guarantees that the method exists:

```java
interface FeePaidListener {
    void feePaid(FeeEvent e);      // "I promise to have this method"
}
```

The fee counter only knows the interface. Anyone who implements it can register, and the counter calls `feePaid()` on them. This is **polymorphism** from Module II, used for a new purpose.

There are four ways to create that object, from oldest to newest (Part 2 and Part 3):

1. a normal class that implements the interface,
2. an anonymous class (3.11),
3. a lambda (Java 8),
4. a method reference (Java 8).

## Two Kinds of Callback

| Kind | Called when | Example |
| --- | --- | --- |
| **Synchronous** | **During** the method we called, before it returns | The `Comparator` in `list.sort(comp)`: `sort()` calls `compare()` many times, then returns |
| **Asynchronous** | **Later**, when an event happens | `actionPerformed()` when a button is clicked; `feePaid()` when a fee is paid |

We have already used callbacks without the name: a `Comparator` (3.8), `forEach(s -> ...)` and `removeIf(...)` (3.7) all call our code back.

## Common Misconceptions

- **"A callback is a special Java keyword."** No: it is a **design idea**, built from ordinary interfaces and objects.
- **"We call `feePaid()` / `actionPerformed()` ourselves."** No: we only **give** the object. The event source calls it.

## Summary

- Problem: an object that calls everyone directly is tightly coupled and must change for every new receiver.
- Callback: give behaviour to an object, and let it call that behaviour later.
- Java passes behaviour as an object implementing an interface.
- Synchronous callbacks run during the call (`Comparator`); asynchronous ones run on an event (button click).

Back to [3.12](README.md) · Next: [Part 2: The Listener Pattern](02_ListenerPattern.md)
