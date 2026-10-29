# 3.11 Nested and Inner Classes

Java lets us write a class **inside** another class. There are four kinds, each with its own rules. They are taught in three parts: why and what (overview), the two kinds that are **members** of a class, and the two kinds that live **inside code**.

## Reading Order

| Part | Notes | Question it answers | Program section |
| --- | --- | --- | --- |
| 1 | [01_WhyNestedClasses.md](01_WhyNestedClasses.md) | Why put a class inside a class? What are the four kinds? | – |
| 2 | [02_StaticAndInnerClasses.md](02_StaticAndInnerClasses.md) | Static nested vs inner: when does a class need an outer object? | [NestedClasses.java](NestedClasses.java): `Rules`, `Department` |
| 3 | [03_LocalAndAnonymousClasses.md](03_LocalAndAnonymousClasses.md) | Classes inside a method, and classes with no name | [NestedClasses.java](NestedClasses.java): `Invigilator`, `Greeting` |

## The Big Picture

```
Nested class (a class inside a class)
├── Static nested class         static class Rules { }        needs NO outer object
└── Inner class (not static)                                  belongs to an outer object
    ├── Member inner class      class Department { }
    ├── Local class             class Invigilator { }  (inside a method)
    └── Anonymous class         new Greeting() { ... }  (no name)
```

## Run

```
javac NestedClasses.java
java NestedClasses
```
