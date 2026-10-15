# 1.2 Java Architecture: JDK, JRE and JVM

## JDK, JRE and JVM

- **JVM (Java Virtual Machine):** runs the bytecode. Each OS has its own JVM.
- **JRE (Java Runtime Environment):** JVM + libraries. Needed to **run** Java programs.
- **JDK (Java Development Kit):** JRE + tools (`javac`, `java`, ...). Needed to **write and compile** Java programs.

```
+-----------------------------------+
| JDK  = JRE + tools (javac, java)  |
|   +-----------------------------+ |
|   | JRE = JVM + libraries       | |
|   |   +---------------------+   | |
|   |   | JVM                 |   | |
|   |   +---------------------+   | |
|   +-----------------------------+ |
+-----------------------------------+
```

## How a Java Program Runs

```
HelloWorld.java  --javac-->  HelloWorld.class  --java-->  JVM  -->  Output
 (source code)                  (bytecode)
```

1. Write code in `HelloWorld.java`.
2. `javac HelloWorld.java` compiles it into `HelloWorld.class`.
3. `java HelloWorld` starts the JVM and runs the bytecode.

Try it with [HelloWorld.java](HelloWorld.java).

## Bytecode and Platform Independence

- **Bytecode:** instructions for the JVM, not for a real processor.
- The same `.class` file runs on Windows, Linux and macOS. Only the JVM is different.
- **Write Once, Run Anywhere (WORA).**
- Bytecode is platform-independent; the JVM is platform-dependent.

## Compiled and Interpreted

- **Compiled:** `javac` converts source code to bytecode.
- **Interpreted:** the JVM runs bytecode line by line.
- **JIT (Just-In-Time) compiler:** converts code that runs many times (like loops) into machine code, so it runs faster.

## Parts of the JVM

- **Class loader:** loads `.class` files into memory.
- **Bytecode verifier:** checks the bytecode is safe.
- **Execution engine:** runs the bytecode (interpreter + JIT).
- **Garbage collector:** frees memory of objects that are no longer used.

## JVM Memory (main areas)

- **Method area:** class information and static variables.
- **Heap:** objects.
- **Stack:** method calls and local variables.

## JDK Tools

| Tool | Use |
| --- | --- |
| `javac` | Compile `.java` → `.class` |
| `java` | Run a program |
| `javap` | Show what is inside a `.class` file |
| `jar` | Pack many `.class` files into one file |
| `javadoc` | Make documentation from `/** */` comments |
