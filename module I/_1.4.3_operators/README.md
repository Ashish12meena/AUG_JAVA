# 1.4 Operators

- An operator is a symbol that performs an operation on values.

## Arithmetic: `+ - * / %`

- `10 / 3` → `3` (int / int gives int)
- `10 % 3` → `1` (remainder)
- `+` also joins strings: `"Age: " + 22`

Example: [ArithmeticOperators.java](ArithmeticOperators.java)

## Relational: `== != > < >= <=`

- Compare two values. Result is `true` or `false`.
- `=` assigns, `==` compares.

Example: [RelationalOperators.java](RelationalOperators.java)

## Logical: `&& || !`

- `&&` → true only when **both** conditions are true.
- `||` → true when **at least one** is true.
- `!` → reverses the result.
- `&&` stops if the first condition is false; `||` stops if the first is true.

Example: [LogicalOperators.java](LogicalOperators.java)

## Assignment: `= += -= *= /= %=`

- `x += 5` means `x = x + 5`.

Example: [AssignmentOperators.java](AssignmentOperators.java)

## Unary: `+ - ++ -- !`

- Work on one value.
- `++x` → increase first, then use.
- `x++` → use first, then increase.

```java
int x = 5;
int y = x++;   // y = 5, x = 6
```

Example: [UnaryOperators.java](UnaryOperators.java)

## Ternary: `condition ? value1 : value2`

- Short form of `if-else`.

```java
String result = (marks >= 40) ? "PASS" : "FAIL";
```

Example: [TernaryOperator.java](TernaryOperator.java)

## Precedence (high → low)

`++ --` → `* / %` → `+ -` → `< > <= >=` → `== !=` → `&&` → `||` → `?:` → `=`

- `2 + 3 * 4` → `14`. Use brackets when in doubt.
