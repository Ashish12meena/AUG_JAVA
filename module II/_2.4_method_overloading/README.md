# 2.4 Method Overloading (Compile-time Polymorphism)

Example: [MethodOverloading.java](MethodOverloading.java) (`Student`)

## Overloading

- **Same method name**, **different parameters** in the same class.
- Parameters must differ in:
  1. **number:** `show()` and `show(String, int)`
  2. **type:** `setMark(int, int)` and `setMark(int[])`
  3. **order:** `show(String, int)` and `show(int, String)`
- Constructor overloading (1.6) is the same idea.

## Return Type Alone Is Not Enough

```java
void setMark(int subject, int mark) { ... }
int setMark(int subject, int mark) { ... }   // ERROR: already defined
```

- The call `setMark(2, 80)` looks the same for both, so Java can't choose.

## Type Promotion

- If no exact match, Java **widens** the argument: `byte → short → int → long → float → double`.
- `addBonus(5)` with only `addBonus(long)` and `addBonus(double)` → `addBonus(long)` runs (closest).
- If two methods match equally well → **ambiguity error**.
  - `payFee(int, long)` and `payFee(long, int)`, then `payFee(1, 120000)` → error.

## Overloading `main`

- `main` can be overloaded: `main(int rollNo)`.
- JVM calls **only** `main(String[] args)`. Other versions run only if we call them.

## Static (Early) Binding

- The compiler decides **which** overloaded method to call by looking at the arguments.
- Decided at **compile time** → compile-time polymorphism / early binding.
