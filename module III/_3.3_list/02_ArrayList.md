# 3.3 List, Part 2: `ArrayList`

Build your own: [CustomArrayList.java](CustomArrayList.java)

## Why `ArrayList`?

An array has one great strength and one great weakness:

- **Strength:** reaching any element is instant. `section[3]` jumps straight to position 3, because array elements sit next to each other in memory.
- **Weakness:** the size is fixed when it is made.

`ArrayList` keeps the strength and removes the weakness: it is an array **that grows by itself**.

## What Is `ArrayList`?

**`ArrayList`:** A class that implements `List` using a **resizable (dynamic) array** inside.

Two words to separate clearly:

| Term | Meaning |
| --- | --- |
| **Size** | How many elements are **in** the list now (`size()`) |
| **Capacity** | How many elements the inside array **can hold** before it must grow |

Example: `new ArrayList<>()` gets room for 10 elements when the first element is added. After 4 `add()` calls: size 4, capacity still 10.

> ⭐ **Remember:** Size ≠ capacity. `size()` tells you how many students are in the list, not how big the array inside is.

## How Does It Work Inside?

`ArrayList` keeps two things: an array `elementData` and a counter `size`.

### Adding at the end

1. If there is a free place (`size < capacity`), put the element at `elementData[size]` and do `size++`. Very fast.
2. If the array is **full**:
   - make a **new array 1.5 times bigger** (10 → 15 → 22 → 33 ...),
   - **copy** every element into it,
   - then add.

```
capacity 10, full:  [H S S R D A B C E F]
grow:               [H S S R D A B C E F _ _ _ _ _]   <- new array, 15 places, old elements copied
```

Copying is slow, but it happens rarely, so on average `add()` at the end is still fast.

If we already know about how many elements will come, we can give the capacity at the start and avoid the copying: `new ArrayList<>(60)`.

### `get(i)`: instant

`get(i)` simply returns `elementData[i]`. No loop, no matter how big the list is.

### Insert or remove in the middle: shifting

`add(1, x)` must first move every element from index 1 onwards **one place right** to make a gap. `remove(1)` moves them **one place left** to close the gap.
`ArrayList` does this with `System.arraycopy()`, but it is still work proportional to the number of elements moved.

```
remove(1):   [Hita, Sakshi, Somya, Ritika]
                     <-----  <-----
             [Hita, Somya, Ritika]
```

The closer to the **start** we insert or remove, the more elements move.

## Speed Summary

| Operation | Speed | Why |
| --- | --- | --- |
| `get(i)`, `set(i, x)` | Fast, O(1) | Jump straight to the array position |
| `add(x)` at the end | Fast on average | Only occasionally grows and copies |
| `add(i, x)` / `remove(i)` near the start | Slow, O(n) | Shifts many elements |
| `contains(x)`, `indexOf(x)` | Slow, O(n) | Checks elements one by one |

(O(1) means "same time whatever the size"; O(n) means "time grows with the number of elements". These are useful in interviews.)

## Build Your Own: `CustomArrayList`

Read [CustomArrayList.java](CustomArrayList.java) with this note. Map each idea to the code:

| Idea | In the code |
| --- | --- |
| The array inside | `private Object[] elementData` |
| Size vs capacity | `size` vs `elementData.length` |
| Starts with 10 places | `DEFAULT_CAPACITY = 10` |
| Grow 1.5 times | `grow()`: `length + (length >> 1)`, then `Arrays.copyOf` |
| Shift on insert | `add(int index, E element)`: `System.arraycopy(... index + 1 ...)` |
| Shift on remove | `remove(int index)`: `System.arraycopy(... index ...)`, then last place set to `null` |
| Wrong index | `checkIndex()` throws `IndexOutOfBoundsException` |

## Common Misconceptions

- **"`ArrayList` has no size limit, so it costs nothing to grow."** Growing means making a new array and copying everything. It is hidden, not free.
- **"`remove()` makes the array smaller."** No: the capacity stays; only `size` goes down.

## Summary

- `ArrayList` = a List backed by an array that grows by about 1.5 times when full.
- Size (elements in it) is different from capacity (room inside).
- `get(i)` is instant; insert/remove in the middle shifts elements.
- It is the **default** List to use.

Previous: [Part 1](01_ListInterface.md) · Next: [Part 3: LinkedList](03_LinkedList.md)
