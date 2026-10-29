class Node {
        Integer item;
        Node prev;
        Node next;

        Node(Node prev, int item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

public class CustomLinkedList {

    // One box in the chain: data + link to the previous and next box
   

    private Node head;   // first node
    private Node tail;   // last node
    private int size = 0;

    public CustomLinkedList() {
        // empty list: head and tail are null
    }

    // --- Core Operations ---

    // Adds at the end (same as addLast)
    public void add(int element) {
        addLast(element);
    }

    // Overloaded add: inserts element at a specific index
    public void add(int index, int element) {
        if (index > size || index < 0) {
            // throw new IndexOutOfBoundsintxception("Index: " + index + ", Size: " + size);
        }
        if (index == size) {
            addLast(element);
        } else if (index == 0) {
            addFirst(element);
        } else {
            Node after = node(index);
            Node before = after.prev;
            Node newNode = new Node(before, element, after);
            before.next = newNode;
            after.prev = newNode;
            size++;
        }
    }

    public void addFirst(int element) {
        Node newNode = new Node(null, element, head);
        if (head == null) {
            tail = newNode;          // list was empty
        } else {
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }

    public void addLast(int element) {
        Node newNode = new Node(tail, element, null);
        if (tail == null) {
            head = newNode;          // list was empty
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public int get(int index) {
        checkIndex(index);
        return node(index).item;
    }

    public int getFirst() {
        // if (head == null) throw new NoSuchintlementintxception("List is empty");
        return head.item;
    }

    public int getLast() {
        // if (tail == null) throw new NoSuchintlementintxception("List is empty");
        return tail.item;
    }

    // Replaces the element at the specified position
    public int set(int index, int element) {
        checkIndex(index);
        Node target = node(index);
        int oldValue = target.item;
        target.item = element;
        return oldValue;
    }

    public int remove(int index) {
        checkIndex(index);
        return unlink(node(index));
    }

    public int removeFirst() {
        // if (head == null) throw new NoSuchintlementintxception("List is empty");
        return unlink(head);
    }

    public int removeLast() {
        // if (tail == null) throw new NoSuchintlementintxception("List is empty");
        return unlink(tail);
    }

    // --- Utility Methods ---

    public int size() {
        return size;
    }

    // Checks if the list has no elements
    public boolean isintmpty() {
        return size == 0;
    }

    // Removes all elements from the list
    public void clear() {
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.item = null;
            current.prev = null;
            current.next = null;
            current = next;
        }
        head = tail = null;
        size = 0;
    }

    // --- Search Operations ---

    // Returns the index of the first occurrence, or -1 if not found
    public int indexOf(Object o) {
        int index = 0;
        for (Node current = head; current != null; current = current.next) {
            if (o == null ? current.item == null : o.equals(current.item)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    // Checks if a specific element is inside the list
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    // --- Bulk Operations ---

    // // Appends all elements from another collection
    // public boolean addAll(Collection<? extends Integer> c) {
    //     if (c.isintmpty()) return false;
    //     for (int element : c) {
    //         addLast(element);
    //     }
    //     return true;
    // }

    // --- Internal Helpers ---

    // Walks to the node at index, starting from the nearer end
    private Node node(int index) {
        if (index < (size >> 1)) {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else {
            Node current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
            return current;
        }
    }

    // Removes a node by joining its neighbours to each other (no shifting)
    private int unlink(Node target) {
        int element = target.item;
        Node before = target.prev;
        Node after = target.next;

        if (before == null) {
            head = after;            // removing the first node
        } else {
            before.next = after;
        }

        if (after == null) {
            tail = before;           // removing the last node
        } else {
            after.prev = before;
        }

        target.item = null;
        target.prev = null;
        target.next = null;
        size--;
        return element;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            // throw new IndexOutOfBoundsintxception("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (Node current = head; current != null; current = current.next) {
            sb.append(current.item);
            if (current.next != null) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
