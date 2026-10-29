# 3.3 List, Part 3: `LinkedList`

Build your own: [CustomLinkedList.java](CustomLinkedList.java)

## Why Another List?

Think of the **notice board**: a new notice is always pinned **on top**, and the oldest notice is taken off when it is over.

With an `ArrayList`, "add at the start" means shifting **every** notice one place right, each time. For a long board, that is a lot of moving for a tiny change.

The real problem is that array elements must sit **side by side** in memory. If elements did not need to sit together, adding at the front would just be "attach one more". That idea is the **linked list**.

## What Is `LinkedList`?

**`LinkedList`:** A class that implements `List` (and `Deque`) as a **doubly linked list**: a chain of **nodes**, where each node knows the node before it and the node after it.

Terms:

| Term | Meaning |
| --- | --- |
| **Node** | One box in the chain: the data + a link to the previous node + a link to the next node |
| **head** | The first node |
| **tail** | The last node |
| **Doubly linked** | Each node has **two** links (`prev` and `next`), so we can walk both ways |

```
 head                                              tail
  |                                                  |
 [null | Hita | o]<-->[o | Somya | o]<-->[o | Ritika | null]
         data   next   prev        next   prev
```

The nodes can be **anywhere** in memory. Only the links keep them in order.

## How Does It Work?

### Add at the start or end: just change links

To pin a new notice on top (`addFirst`):

1. Make a new node.
2. Its `next` points to the old head; the old head's `prev` points back to it.
3. `head` now points to the new node.

No other node moves. Same idea at the end with `tail` (`addLast`).

### Remove: join the neighbours

To remove a node, link its previous node directly to its next node. The removed node is now outside the chain. Nothing shifts.

### `get(i)`: walk the chain

There is no array, so there is no jumping to position `i`. `LinkedList` must **walk** node by node from the head (or from the tail, if `i` is in the second half). For a long list, that is slow.

> ⭐ **Remember:** `LinkedList` is fast at **changing links** but slow at **finding a position**. Inserting in the middle is fast only **after** you have walked there.

### It is also a Deque

`LinkedList` also implements `Deque` (3.5), so it has `addFirst`, `addLast`, `removeFirst`, `removeLast`, `getFirst`, `getLast`.

```java
LinkedList<String> notices = new LinkedList<>();
notices.add("Fee last date 30 Oct");
notices.addFirst("Java test on Monday");     // new notice on top
notices.addLast("Library closes at 6 pm");
notices.removeFirst();                        // the test is over
```

## `ArrayList` vs `LinkedList`

| | `ArrayList` | `LinkedList` |
| --- | --- | --- |
| Inside | Resizable array | Doubly linked nodes |
| `get(i)` | Fast, O(1) (jumps to index) | Slow, O(n) (walks node by node) |
| Add/remove at the **end** | Fast | Fast |
| Add/remove at the **start** | Slow, O(n) (shifts all) | Fast, O(1) (`addFirst`, `removeFirst`) |
| Add/remove in the **middle** | Slow (shifts) | Walk is slow, the link change is fast |
| Memory | Only the elements (+ some empty places) | Each element + 2 links per node |
| Also a `Deque`? | No | Yes |
| Use when | Mostly reading by position | Mostly adding/removing at the ends |

> ⭐ **Remember:** In practice `ArrayList` is the default choice. Use `LinkedList` only when you mostly add/remove at the **ends**, and even then `ArrayDeque` (3.5) is usually better.

## Build Your Own: `CustomLinkedList`

Read [CustomLinkedList.java](CustomLinkedList.java) with this note:

| Idea | In the code |
| --- | --- |
| A node | `class Node { item; prev; next; }` |
| First and last | `head`, `tail` |
| Add at the start / end | `addFirst()`, `addLast()`: create a node, fix two links |
| Walk to an index | `node(index)`: starts from the head **or** the tail, whichever is nearer |
| Remove without shifting | `unlink()`: `before.next = after; after.prev = before;` |

## Common Misconceptions

- **"LinkedList is faster for inserting, so always use it."** Only if you are already at the position. Finding the position costs a walk.
- **"LinkedList uses less memory."** It uses more: every element carries two extra links.

## Summary

- `LinkedList` = a chain of nodes, each linked to the previous and next node.
- Adding/removing at the ends only changes links: very fast.
- `get(i)` walks the chain: slow.
- It is both a `List` and a `Deque`.

Previous: [Part 2](02_ArrayList.md) · Next: [Part 4: Legacy and Fixed Lists](04_LegacyAndFixedLists.md)
