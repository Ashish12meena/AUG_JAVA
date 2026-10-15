# Programming Concepts Using Java: Detailed Curriculum

Every topic in the 5-module curriculum, broken into the specific subtopics to teach, so nothing is left out. Use each numbered topic as a lecture heading and each sub-point as something students should be able to explain or code.

## Module I: OOP and Class Hierarchy

Students learn how Java runs, the language basics they need, and how to design classes and objects and connect them to each other.

### 1.1 Introduction to OOP and Java

- Procedural vs object-oriented programming: problems with the procedural approach at scale
- The four pillars of OOP: encapsulation, inheritance, polymorphism, abstraction
- History of Java: Sun Microsystems, James Gosling, 1995, Oak to Java, Oracle today
- Features (buzzwords) of Java: simple, object-oriented, platform-independent, portable, secure, robust, multithreaded, high performance, distributed, dynamic
- Java vs C and C++: pointers, memory management, multiple inheritance, operator overloading, platform dependence
- Where Java is used: enterprise servers, Android, web services, big data tools

### 1.2 Java architecture: JDK, JRE and JVM

- Roles of JDK, JRE and JVM, and how they contain each other
- Compilation and execution flow: `.java` → `javac` → `.class` (bytecode) → JVM
- Bytecode and platform independence: "write once, run anywhere"
- Java as both compiled and interpreted; the JIT (Just-In-Time) compiler
- JVM components: class loader, bytecode verifier, execution engine (interpreter + JIT), garbage collector
- JVM memory areas: method area, heap, stack, PC register, native method stack
- Tools: `javac`, `java`, `javap`, `jar`, `javadoc`

### 1.3 Basic program structure

- Structure of a Java program: package statement, import statements, class, `main` method
- The `main` method signature: meaning of `public`, `static`, `void`, `String[] args`
- Command-line arguments
- Naming conventions for classes, methods, variables and constants
- Comments: single-line, multi-line, documentation comments (`/** */`)
- Compiling and running from the command line and from an IDE

### 1.4 Language fundamentals

- Tokens: keywords, identifiers, literals, operators, separators
- Data types: the 8 primitive types, their sizes, ranges and default values; reference types
- Variables: local, instance and static; scope and lifetime
- Type conversion: widening (automatic) and narrowing (casting); type promotion in expressions
- Operators: arithmetic, relational, logical, bitwise, shift, assignment, ternary, `instanceof`; precedence and associativity
- Control statements: `if-else`, `switch` (including the enhanced `switch`), `for`, `while`, `do-while`, enhanced `for`; `break`, `continue`, labelled `break`
- Input and output: `Scanner`, `System.out.print`, `println`, `printf`
- Arrays: one-dimensional, two-dimensional and jagged arrays; the `length` property; the `Arrays` utility class
- Strings: `String` immutability, the string pool, common methods, `==` vs `equals()`, `StringBuilder` and `StringBuffer`
- Wrapper classes, autoboxing and unboxing

### 1.5 Classes and objects

- Defining a class: fields (instance variables) and methods
- Creating objects with `new`; reference variables
- Memory: references on the stack, objects on the heap
- Methods: parameters, return types, calling methods
- Java is always pass-by-value: passing primitives vs passing object references
- The `this` keyword: referring to fields, calling another constructor with `this()`, passing the current object
- Array of objects
- Anonymous objects
- Garbage collection: when an object becomes eligible, `null` references, `System.gc()`

### 1.6 Constructors

- Purpose and rules of constructors (same name as the class, no return type)
- Default constructor provided by the compiler
- No-argument and parameterised constructors
- Constructor overloading
- Constructor chaining with `this()`
- Copy constructor (user-defined)
- Instance initialiser blocks and the order of initialisation

### 1.7 Static members

- Static (class) variables vs instance variables
- Static methods and their restrictions: no `this`, no direct access to instance members
- Static blocks: when they run and what they are used for
- Why `main` is static
- Example: counting the number of objects created
- Static vs instance: memory and lifetime

### 1.8 Encapsulation

- Data hiding with `private` fields
- Getters and setters, with validation inside setters
- Access modifiers overview: `private`, default, `protected`, `public` (full detail in Module IV)
- The `final` keyword with variables: constants
- Immutable classes: `final` fields, no setters
- Benefits: control, security, easier maintenance

### 1.9 Object relationships

- Association: one-to-one, one-to-many, many-to-many; unidirectional and bidirectional
- Aggregation: a weak "has-a" relationship where the part can exist alone (Department and Professor)
- Composition: a strong "part-of" relationship where the part dies with the whole (House and Room)
- Comparing association, aggregation and composition, and when to use each
- Basics of UML class diagrams: class boxes, association lines, hollow and filled diamonds

**Practice programs:** bank account with validation, student record with array of objects, object counter using `static`, Library with Books (aggregation), Car with Engine (composition).

## Module II: Inheritance, Polymorphism and Abstraction

Students learn how classes reuse and extend each other, how one reference can behave in many ways, and how abstract classes and interfaces define contracts.

### 2.1 Inheritance basics

- The "is-a" relationship; the `extends` keyword
- Superclass and subclass terminology
- What is inherited and what is not: private members, constructors
- The `Object` class as the root of every class
- Benefits: code reuse, extensibility, method overriding

### 2.2 Types of inheritance

- Single, multilevel and hierarchical inheritance (supported with classes)
- Multiple and hybrid inheritance (supported only through interfaces)
- The diamond problem and why Java does not allow multiple inheritance of classes

### 2.3 The `super` keyword

- Accessing a parent's fields when they are hidden by the child
- Calling a parent's methods with `super.method()`
- Calling a parent's constructor with `super()`
- Constructor execution order in an inheritance chain; the implicit `super()` call
- Rules: `this()` and `super()` must be the first statement and cannot be used together

### 2.4 Method overloading (compile-time polymorphism)

- Same method name, different parameter lists (number, type or order)
- Return type alone cannot overload a method
- Overloading with automatic type promotion and its ambiguity errors
- Overloading the `main` method
- Static (early) binding

### 2.5 Method overriding (runtime polymorphism)

- Rules: same signature, same or covariant return type, access cannot be more restrictive, checked exceptions cannot be broader
- The `@Override` annotation
- What cannot be overridden: `static` methods (method hiding), `private` methods, `final` methods
- Calling the overridden parent method with `super.method()`

### 2.6 Overloading vs overriding

- Side-by-side comparison: where it happens, signature, return type, binding time, polymorphism type
- Common exam and interview traps

### 2.7 Polymorphism and dynamic method dispatch

- Upcasting: a parent reference pointing to a child object
- Dynamic method dispatch: the method is chosen at runtime by the object's actual type
- Dynamic (late) binding vs static (early) binding
- Variables are not polymorphic: field access uses the reference type
- Downcasting, the `instanceof` check, pattern matching for `instanceof`, and `ClassCastException`

### 2.8 The `final` keyword

- `final` variables, including blank `final` variables set in a constructor
- `final` methods: cannot be overridden
- `final` classes: cannot be extended (example: `String`)

### 2.9 Methods of the `Object` class

- `toString()`: overriding it to print objects meaningfully
- `equals()`: comparing objects by content instead of reference
- `hashCode()`: the contract between `equals()` and `hashCode()`
- `getClass()`; `clone()` and `finalize()` (introduced here, covered in Module IV)

### 2.10 Abstraction and abstract classes

- Abstraction: showing what an object does, hiding how
- Abstract methods: declared without a body
- Abstract class rules: cannot be instantiated, can have constructors, fields and concrete methods
- A subclass must implement every abstract method or be declared abstract itself
- Abstract classes as partial abstraction

### 2.11 Interfaces

- Declaring and implementing an interface
- Interface methods are `public abstract` by default; fields are `public static final`
- Implementing multiple interfaces (Java's form of multiple inheritance)
- An interface extending other interfaces
- `default` methods and `static` methods in interfaces; `private` interface methods
- Functional interfaces and an introduction to lambda expressions (used in Module III)
- Marker interfaces: `Serializable`, `Cloneable`

### 2.12 Abstract class vs interface

- Comparison: methods, variables, constructors, inheritance, access modifiers
- When to choose an abstract class and when to choose an interface

### 2.13 Inheritance vs composition

- "Is-a" vs "has-a" design
- Why to favour composition when inheritance does not truly fit

**Practice programs:** `Shape` hierarchy computing area, `Employee` and `Manager` salary with overriding, `Payment` interface with UPI and card implementations, multilevel `Vehicle` hierarchy, overridden `toString()` and `equals()` in a `Student` class.

## Module III: Collections, Iterators, Generics and Callbacks

Students learn to store, search, sort and process groups of objects with the Collections Framework, write type-safe code with generics, and respond to events with callbacks.

### 3.1 Introduction to the Collections Framework

- Limitations of arrays: fixed size, no built-in search, sort or insert
- What a collection and a framework are; the `java.util` package
- The hierarchy: `Iterable` → `Collection` → `List`, `Set`, `Queue`; `Map` as a separate hierarchy
- Interfaces vs implementation classes
- `Collection` (interface) vs `Collections` (utility class)

### 3.2 The `Collection` interface

- Common methods: `add`, `remove`, `contains`, `size`, `isEmpty`, `clear`
- Bulk methods: `addAll`, `removeAll`, `retainAll`, `containsAll`
- `toArray()`
- Wrapper classes and autoboxing inside collections

### 3.3 List

- Features: ordered, index-based, duplicates allowed
- `ArrayList`: dynamic array, how it grows, fast random access
- `LinkedList`: doubly linked list, fast insertion and deletion, also a `Deque`
- `Vector` and `Stack`: legacy, synchronised classes
- List methods: `get`, `set`, `add(index, element)`, `remove(index)`, `indexOf`, `subList`
- `ArrayList` vs `LinkedList`: when to use which
- Immutable lists with `List.of()`

### 3.4 Set

- Features: no duplicates
- `HashSet`: hashing, no order; why `equals()` and `hashCode()` matter for custom objects
- `LinkedHashSet`: keeps insertion order
- `TreeSet`: sorted order; `SortedSet` and `NavigableSet` methods (`first`, `last`, `floor`, `ceiling`, `headSet`, `tailSet`)
- Comparing `HashSet`, `LinkedHashSet` and `TreeSet`

### 3.5 Queue and Deque

- `Queue` (FIFO): `offer`, `poll`, `peek` vs `add`, `remove`, `element`
- `PriorityQueue`: ordering by natural order or a `Comparator`
- `Deque` and `ArrayDeque`: using one class as both a stack and a queue

### 3.6 Map

- Key-value pairs; unique keys
- `HashMap`: internal working (hashing, buckets, collisions, load factor, rehashing)
- `LinkedHashMap`: insertion order
- `TreeMap`: sorted keys; `SortedMap` and `NavigableMap` methods
- `Hashtable`: legacy and synchronised
- Map methods: `put`, `get`, `getOrDefault`, `containsKey`, `containsValue`, `remove`, `putIfAbsent`, `keySet`, `values`, `entrySet`
- Iterating over a map with `Map.Entry`
- `HashMap` vs `Hashtable` vs `TreeMap`

### 3.7 Iterators

- `Iterator`: `hasNext`, `next`, `remove`
- `ListIterator`: moving both ways, `add` and `set`
- Enhanced `for` loop and `forEach()` with a lambda
- `ConcurrentModificationException`: why it happens and how to avoid it
- Fail-fast vs fail-safe iterators
- `Enumeration` (legacy)

### 3.8 Sorting with `Comparable` and `Comparator`

- `Comparable` and `compareTo()`: the natural order of a class
- `Comparator` and `compare()`: any number of custom orders
- Writing comparators as separate classes, anonymous classes and lambdas
- `Comparator.comparing()`, `thenComparing()`, `reversed()`
- `Collections.sort()`, `List.sort()`, `Arrays.sort()`
- `Comparable` vs `Comparator`

### 3.9 The `Collections` utility class

- `sort`, `reverse`, `shuffle`, `max`, `min`, `frequency`, `binarySearch`
- `unmodifiableList` and other read-only views
- `synchronizedList` and other thread-safe wrappers (linked to Module V)

### 3.10 Generics

- Why generics: type safety, no casting, errors caught at compile time
- Generic classes (for example, `Box<T>`) and multiple type parameters (`Pair<K, V>`)
- Generic methods
- Bounded type parameters: `<T extends Number>`
- Wildcards: unbounded `<?>`, upper-bounded `<? extends T>`, lower-bounded `<? super T>`
- The PECS rule: Producer Extends, Consumer Super
- Type erasure
- Restrictions: no primitive types, no `new T()`, no static fields of type `T`
- Raw types and why to avoid them

### 3.11 Nested and inner classes (needed for callbacks)

- Static nested classes
- Inner (member) classes
- Local classes
- Anonymous classes

### 3.12 Event-driven programming and callbacks

- What a callback is: passing behaviour to be called later
- Implementing callbacks with interfaces
- The listener pattern: event source, event object, listener interface
- Callbacks with anonymous inner classes
- Lambda expressions and functional interfaces: `Runnable`, `Comparator`, `Consumer`, `Predicate`, `Function`
- Method references
- Real-world example: `ActionListener` for a button click in AWT or Swing
- Optional extension: Stream API basics (`filter`, `map`, `collect`)

**Practice programs:** student records in an `ArrayList` sorted by marks and by name, word-frequency counter with `HashMap`, removing duplicates with `HashSet`, a generic `Pair<K, V>` class, library catalogue with `TreeMap`, a custom `BookReturnedListener` callback.

## Module IV: Cloning, I/O, Serialization and Exception Handling

Students learn to organise code into packages, handle errors safely, copy objects correctly, and save data to files. Topics are ordered so exceptions come before file I/O, because every file operation needs `try-catch`.

### 4.1 Packages

- Why packages: organisation, avoiding name clashes, access control
- Built-in packages: `java.lang`, `java.util`, `java.io`, `java.nio`
- Creating user-defined packages; package naming conventions
- Compiling and running classes in packages (`javac -d`)
- `import`: single-class import, wildcard import, static import; fully qualified names
- `CLASSPATH`
- Creating and using JAR files

### 4.2 Access control

- The four access levels: `private`, default (package-private), `protected`, `public`
- Visibility table: same class, same package, subclass in another package, everywhere
- `protected` access across packages
- Class-level access: `public` vs default classes
- Access modifiers and method overriding

### 4.3 Exception handling basics

- What an exception is; error vs exception
- The hierarchy: `Throwable` → `Error` and `Exception` → `RuntimeException`
- Checked vs unchecked exceptions
- Common exceptions: `ArithmeticException`, `NullPointerException`, `ArrayIndexOutOfBoundsException`, `NumberFormatException`, `ClassCastException`, `IOException`, `FileNotFoundException`
- The default exception handler and reading a stack trace

### 4.4 `try`, `catch` and `finally`

- `try-catch` blocks
- Multiple `catch` blocks and why their order matters
- Multi-catch with `|`
- Nested `try` blocks
- `finally`: when it always runs and the rare cases it does not
- `try`-with-resources and the `AutoCloseable` interface
- Pitfalls: `return` inside `finally`, empty `catch` blocks

### 4.5 `throw`, `throws` and custom exceptions

- Throwing an exception with `throw`
- Declaring exceptions with `throws`
- `throw` vs `throws`
- Rethrowing and exception chaining (the cause of an exception)
- Creating custom checked and unchecked exceptions
- Exception rules with method overriding
- Best practices: catch specific exceptions, never silently swallow them
- Exam trap: `final` vs `finally` vs `finalize`

### 4.6 Cloning

- The copying problem: assigning a reference copies the reference, not the object
- The `Cloneable` marker interface and `Object.clone()`
- Overriding `clone()`; `CloneNotSupportedException`
- Shallow copy: nested objects are shared
- Deep copy: cloning nested objects too
- Shallow vs deep copy comparison
- Copy constructors as an alternative to `clone()`

### 4.7 File handling with streams

- The idea of a stream; the `java.io` package
- Byte streams: `InputStream`, `OutputStream`, `FileInputStream`, `FileOutputStream`
- Character streams: `Reader`, `Writer`, `FileReader`, `FileWriter`
- Byte streams vs character streams: when to use which
- Buffered streams: `BufferedReader`, `BufferedWriter`, `BufferedInputStream`, `BufferedOutputStream`
- Reading a file line by line; writing and appending to a file
- `PrintWriter`
- `DataInputStream` and `DataOutputStream` for primitive values
- Closing streams correctly with `try`-with-resources

### 4.8 The `File` class and NIO

- The `File` class: `exists`, `createNewFile`, `delete`, `length`, `list`, `mkdir`
- Introduction to `java.nio.file`: `Path`, `Path.of()`, `Files.readAllLines()`, `Files.write()`

### 4.9 Serialization

- What serialization and deserialization are, and why they are needed
- The `Serializable` marker interface
- `ObjectOutputStream.writeObject()` and `ObjectInputStream.readObject()`
- The `transient` keyword
- `serialVersionUID` and version compatibility
- `static` fields are not serialised
- Serialization with inheritance: a non-serializable parent needs a no-argument constructor
- Serialising object graphs (objects that refer to other objects)
- `Externalizable` (introduction)
- Security note: never deserialise data from untrusted sources

**Practice programs:** custom `InsufficientBalanceException` for a bank account, reading marks from a file with bad-input handling, copying a file, saving and loading a library's book list with serialization, a shallow vs deep clone demo, a small project split into packages.

## Module V: Concurrent Programming in Java

Students learn to run several tasks at once safely: creating and controlling threads, protecting shared data, coordinating threads, and using the modern `java.util.concurrent` tools that real servers rely on.

### 5.1 Introduction to multithreading

- Process vs thread
- Multitasking vs multithreading
- Concurrency vs parallelism
- Benefits and real uses: servers, user interfaces, background tasks
- The main thread; `Thread.currentThread()`

### 5.2 Creating threads

- Extending the `Thread` class
- Implementing the `Runnable` interface
- Writing a `Runnable` as a lambda
- `start()` vs `run()`: why calling `run()` directly does not create a thread
- `Thread` vs `Runnable`: which to prefer and why

### 5.3 Thread life cycle

- Thread states: `NEW`, `RUNNABLE`, `BLOCKED`, `WAITING`, `TIMED_WAITING`, `TERMINATED`
- Transitions between states and which methods cause them
- Checking a state with `getState()`

### 5.4 Thread methods and properties

- `sleep()`, `join()`, `yield()`
- `interrupt()`, `isInterrupted()` and `InterruptedException`
- `setName()`, `getName()`, `isAlive()`
- Thread priorities: `MIN_PRIORITY`, `NORM_PRIORITY`, `MAX_PRIORITY`
- Daemon threads vs user threads

### 5.5 Race conditions and thread safety

- Shared mutable data and why it is dangerous
- Race conditions: the lost-update problem with `count++`
- Atomicity and the critical section
- What "thread-safe" means

### 5.6 Synchronization

- `synchronized` methods
- `synchronized` blocks and choosing the lock object
- Object locks (monitors) and class-level locks (`static synchronized`)
- Reentrant locking
- The `volatile` keyword and memory visibility

### 5.7 Inter-thread communication

- `wait()`, `notify()` and `notifyAll()`
- Why they must be called inside `synchronized` code
- The producer-consumer problem
- `wait()` vs `sleep()`
- Checking the condition in a loop (spurious wake-ups)

### 5.8 Concurrency problems

- Deadlock: the four necessary conditions, an example, and prevention by lock ordering
- Livelock and starvation
- Detecting a deadlock with a thread dump (`jstack`)

### 5.9 Locks and atomic variables

- `ReentrantLock`: `lock()` and `unlock()` in `finally`, `tryLock()` with a timeout
- `ReadWriteLock`
- `Condition` objects as a replacement for `wait` and `notify`
- Atomic variables: `AtomicInteger`, `AtomicLong`
- `synchronized` vs `ReentrantLock`

### 5.10 The Executor framework and thread pools

- Problems with creating threads by hand
- `Executor` and `ExecutorService`
- Thread pools: `newFixedThreadPool`, `newCachedThreadPool`, `newSingleThreadExecutor`, `newScheduledThreadPool`
- `execute()` vs `submit()`
- `Callable` and `Future`: getting a result back from a thread
- `invokeAll()`
- `shutdown()`, `shutdownNow()`, `awaitTermination()`
- Introduction to `CompletableFuture`

### 5.11 Concurrent collections

- Limits of synchronised wrappers such as `Collections.synchronizedList`
- `ConcurrentHashMap`
- `CopyOnWriteArrayList`
- `BlockingQueue`: `ArrayBlockingQueue`, `LinkedBlockingQueue`; producer-consumer without `wait` and `notify`
- `ConcurrentLinkedQueue`

### 5.12 Synchronizers

- `CountDownLatch`
- `CyclicBarrier`
- `Semaphore`

### 5.13 Modern Java concurrency

- Virtual threads (Java 21): what they are and why they matter for servers

**Practice programs:** counter race-condition demo fixed with `synchronized` and then `AtomicInteger`, producer-consumer with `wait`/`notify` and then with `BlockingQueue`, a deadlock demo and its fix, a thread pool processing a list of tasks, concurrent withdrawals from a bank account, two library members borrowing the last copy of a book.
