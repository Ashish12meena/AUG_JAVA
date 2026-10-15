# 1.1 Introduction to OOP and Java

## Procedural vs Object-Oriented Programming

- **Procedural:** program = functions + shared data (example: C).
- **Object-oriented (OOP):** program = objects. Each object keeps its own data and the methods that work on it (example: Java).

| Point | Procedural | OOP |
| --- | --- | --- |
| Built from | Functions | Objects (data + methods) |
| Data safety | Any function can change any data | Each object protects its data |
| Reuse | Difficult | Easy |
| Large programs | Hard to manage | Easy to change and extend |

## Four Pillars of OOP

- **Encapsulation:** keep data and methods together in a class; hide the data.
- **Inheritance:** a new class reuses an existing class.
- **Polymorphism:** one action, many forms.
- **Abstraction:** show *what* an object does, hide *how*.

## History of Java

- 1991: James Gosling and team at **Sun Microsystems** started a language called **Oak**.
- 1995: Renamed **Java** and released.
- 2010: **Oracle** bought Sun. Oracle maintains Java now.
- New version every 6 months. LTS versions: 8, 11, 17, 21, 25.

## Features of Java

- **Simple:** easy syntax, no pointers.
- **Object-oriented:** all code is inside classes.
- **Platform-independent:** compiled once to bytecode, runs on any OS with a JVM.
- **Secure:** no pointers; bytecode is checked before running.
- **Robust:** strong type checking, exception handling, automatic memory cleanup.
- **Multithreaded:** can do many tasks at the same time.
- **High performance:** JIT compiler makes frequently used code fast.

## Java vs C / C++

| Feature | C | C++ | Java |
| --- | --- | --- | --- |
| Type | Procedural | Procedural + OOP | OOP |
| Pointers | Yes | Yes | No |
| Memory cleanup | Manual | Manual | Automatic (garbage collector) |
| Runs on | One OS | One OS | Any OS with a JVM |

## Where Java Is Used

- Banking and enterprise software
- Android apps
- Web applications
- Big-data tools (Hadoop, Kafka, Spark)
