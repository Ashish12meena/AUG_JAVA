# 1.4 Control Statements

- Control statements decide **which** statements run and **how many times**.

## Decision Statements

**`if`**
- Runs the block only when the condition is true.
- Example: [IfStatement.java](IfStatement.java) (positive number)

**`if-else`**
- Condition true → `if` block; false → `else` block.
- Example: [IfElse.java](IfElse.java) (even / odd)

**`else-if` ladder**
- Checks conditions from top to bottom; only the first true block runs.
- Used for ranges, like grades.
- Example: [ElseIf.java](ElseIf.java)

**Nested `if`**
- An `if` inside another `if`.
- Inner condition is checked only when the outer one is true.
- Example: [NestedIf.java](NestedIf.java) (greatest of three)

**`switch`**
- Picks one `case` by matching a value.
- Works with `int`, `char`, `String`.
- `break` ends the switch. Without `break`, the next case also runs (fall-through).
- `default` runs when no case matches.
- Example: [SwitchCase.java](SwitchCase.java)

## Loops

- A loop repeats a block while a condition is true.

**`for`**
- Used when the number of repetitions is known.
- `for (initialization; condition; update) { }`
- Example: [ForLoop.java](ForLoop.java)

**`while`**
- Checks the condition first, then runs.
- Used when the number of repetitions is not known.
- Forgetting to update the variable → infinite loop.
- Example: [WhileLoop.java](WhileLoop.java)

**`do-while`**
- Runs first, then checks the condition → runs **at least once**.
- Ends with `;` after `while (...)`.
- Used for menus.
- Example: [DoWhileLoop.java](DoWhileLoop.java)

| Loop | Checks condition | Runs at least once? |
| --- | --- | --- |
| `for` | Before | No |
| `while` | Before | No |
| `do-while` | After | Yes |

## Jump Statements

- `break` → exits the loop or switch.
- `continue` → skips to the next round of the loop.
