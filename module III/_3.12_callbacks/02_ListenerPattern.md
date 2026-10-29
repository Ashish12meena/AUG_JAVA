# 3.12 Callbacks, Part 2: The Listener Pattern

Programs: [Callbacks.java](Callbacks.java) (fee counter), [ButtonClick.java](ButtonClick.java) (Swing button)

## Why a Pattern?

Part 1 gave the idea: "register, and get called back". Java programs use one standard structure for this, so that every event (fee paid, button clicked, key pressed) is built the same way. Once you know the structure, you can read any event code in Java.
This structure is the **listener pattern** (also known as the **Observer pattern**).

## What Are the Parts?

| Part | Role | In our fee counter |
| --- | --- | --- |
| **Event source** | The object where the event happens; keeps the list of listeners and calls them | `FeeCounter` |
| **Event object** | Carries the **details** of what happened | `FeeEvent` (student name, amount) |
| **Listener interface** | The method the source promises to call | `FeePaidListener.feePaid(FeeEvent e)` |
| **Listener** | An object that implements the interface; the code to run | `AccountsOffice`, SMS, exam cell, printer |

```
                    addListener(...)
  AccountsOffice  ------------------>  +------------+
  SMS to parent   ------------------>  | FeeCounter |  keeps List<FeePaidListener>
  Exam cell       ------------------>  +------------+
                                             |
                          collect("Hita", 120000) -> new FeeEvent(...)
                                             |
                       for each listener: listener.feePaid(event)
```

## How Does It Work?

Four steps:

**1. Define the listener interface:** what will be called.

```java
interface FeePaidListener {
    void feePaid(FeeEvent e);
}
```

**2. The source keeps a list of listeners and lets others register.**

```java
class FeeCounter {
    private List<FeePaidListener> listeners = new ArrayList<>();

    void addListener(FeePaidListener l) {
        listeners.add(l);
    }
```

**3. When the event happens, the source creates an event object and calls every listener.**

```java
    void collect(String name, double amount) {
        FeeEvent e = new FeeEvent(name, amount);
        for (FeePaidListener l : listeners) {
            l.feePaid(e);                    // call back
        }
    }
}
```

This loop is called **firing** the event.

**4. Each receiver registers once.**

```java
counter.addListener(new AccountsOffice());
```

Why an **event object** and not just `feePaid(String name, double amount)`? If later we also need the receipt number or the payment mode, we add a field to `FeeEvent`. The listener interface, and every listener, stays the same.

> ⭐ **Remember:** The event source knows only the **listener interface**, never the real listener classes. A new listener means one more `addListener(...)`. The source does **not** change.

## Four Ways to Write a Listener

All four give the counter an object that has `feePaid()`. They differ only in how much we write.

| Way | Code | Use when |
| --- | --- | --- |
| Normal class | `counter.addListener(new AccountsOffice());` | The listener is big or reused |
| Anonymous class | `counter.addListener(new FeePaidListener() { public void feePaid(FeeEvent e) { ... } });` | One-time listener (before Java 8, or needs fields) |
| Lambda | `counter.addListener(e -> System.out.println(...));` | Short, one-time behaviour |
| Method reference | `counter.addListener(Callbacks::printReceipt);` | The behaviour already exists as a method |

Output for Hita's payment: the accounts office, SMS, exam cell and printer each print their line, in the order they registered.

## Real World: Button Click (Swing)

Every Java GUI uses this exact pattern. In [ButtonClick.java](ButtonClick.java):

```java
button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        label.setText("Hita: 438 / 500, PASS");
    }
});
```

| Pattern part | Fee counter | Swing button |
| --- | --- | --- |
| Event source | `FeeCounter` | `JButton` |
| Event object | `FeeEvent` | `ActionEvent` |
| Listener interface | `FeePaidListener` | `ActionListener` |
| Callback method | `feePaid()` | `actionPerformed()` |
| Register | `addListener()` | `addActionListener()` |

We **never** call `actionPerformed()`. Swing calls it **when** the user clicks: an asynchronous callback. The lambda form is also possible: `button.addActionListener(e -> label.setText(...));`

## Benefits

- **Loose coupling:** source and listeners know only the interface.
- **Open for extension:** new listeners can be added without touching the source.
- **Many listeners, one event:** any number of objects can react to the same event.

## Common Mistakes

- Forgetting to register: the listener class exists but `addListener()` is never called, so nothing happens.
- Calling the callback method yourself instead of letting the source fire it.

## Summary

- Listener pattern = event source + event object + listener interface + listeners.
- The source keeps a list, and on an event creates an event object and calls every listener.
- Listeners can be written as a class, anonymous class, lambda or method reference.
- Java GUIs (`JButton`, `ActionListener`) use exactly this pattern.

Previous: [Part 1](01_WhyCallbacks.md) · Next: [Part 3: Functional Interfaces and Lambdas](03_FunctionalInterfaces.md)
