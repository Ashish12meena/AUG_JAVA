import java.util.Arrays;
import java.util.Collection;

public class CustomArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size = 0;

    public CustomArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = new Object[0];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    // --- Core Operations ---

    public void add(E element) {
        ensureCapacity();
        elementData[size++] = element;
    }

    // Overloaded add: inserts element at a specific index
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elementData[index];
    }

    // Replaces the element at the specified position
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        checkIndex(index);
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        E removedElement = (E) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return removedElement;
    }

    // --- Utility Methods ---

    public int size() {
        return size;
    }

    // Checks if the list has no elements
    public boolean isEmpty() {
        return size == 0;
    }

    // Removes all elements from the list
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    // --- Search Operations ---

    // Returns the index of the first occurrence, or -1 if not found
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) return i;
            }
        }
        return -1;
    }

    // Checks if a specific element is inside the list
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    // --- Bulk Operations ---

    // Appends all elements from another collection
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0) return false;
        
        // Ensure capacity for current size + new elements
        while (size + numNew > elementData.length) {
            grow();
        }
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return true;
    }

    // --- Internal Helpers ---

    private void ensureCapacity() {
        if (size == elementData.length) {
            grow();
        }
    }

    private void grow() {
        int newCapacity = elementData.length + (elementData.length >> 1); // 1.5x growth
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
