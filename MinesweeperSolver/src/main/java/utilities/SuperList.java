package utilities;

import java.util.Arrays;

/**
 *
 * @author ahommy
 */

// Alternative version of an ArrayList
public class SuperList<E> {

    private static final int maxCap = 10;
    private int size = 0;
    private Object entries[];

    public SuperList() {
        entries = new Object[maxCap];
    }

    // Add an object to the list
    public void add(E e) {
        if (size == entries.length) {
            doubleCapacity();
        }

        entries[size++] = e;
    }

    // Double the size of the list
    public void doubleCapacity() {
        int newCap = entries.length * 2;
        Object newEntries[] = new Object[newCap];
        for (int i = 0; i < size; i++) {
            newEntries[i] = entries[i];
        }

        entries = newEntries;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the size of the list
    public int size() {
        return size;
    }

    // Return the object within the particular index. 
    public E get(int i) {
        if (i > size) {
            throw new IndexOutOfBoundsException("Out of range");
        }
        return (E) entries[i];
    }

    // Remove the first element of the list and return it
    public E poll() {
        return (E) remove(0);
    }

    // Remove an object from the list. After removing, move every object up by one to fill in the gap.
    public Object remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Object removedElement = entries[index];
        for (int i = index; i < size - 1; i++) {
            entries[i] = entries[i + 1];
        }
        size--;
        return removedElement;
    }

    // See if the list contains a particular object
    public boolean contains(Object e) {
        for (int i = 0; i < size; i++) {
            if (entries[i].equals(e)) {
                return true;
            }
        }

        return false;
    }
}
