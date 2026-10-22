# 2.6 Overloading vs Overriding

| | Overloading | Overriding |
| --- | --- | --- |
| Where | Same class (or child) | Parent and child |
| Method name | Same | Same |
| Parameters | **Must be different** | **Must be same** |
| Return type | Anything | Same or child type (covariant) |
| Access | Anything | Same or wider |
| `static` / `private` / `final` methods | Can be overloaded | Cannot be overridden |
| Decided at | Compile time | Run time |
| Binding | Static (early) | Dynamic (late) |
| Polymorphism | Compile-time | Runtime |
| `@Override` | Not used | Used |

Example: [OverloadVsOverride.java](OverloadVsOverride.java)

## Common Traps

- Only return type changed → **not** overloading, it's an error.
- Parameters changed in child → **overloading**, not overriding. `@Override` catches this.
- `static` method in child with same signature → **method hiding**, not overriding.
- `private` method can't be overridden; child's method with same name is a new method.
- `main` can be overloaded, but not overridden (it is `static`).
