package utilities;

import java.util.Arrays;

/**
 *
 * @author ahommy
 */
public class SuperList<E> {
    
    private static final int maxCap = 10;
    private int size = 0;
    private Object entries[];
    
    public SuperList() {
        entries = new Object[maxCap];
    }
    
    public void add(E e) {
        if (size == entries.length) {
            doubleCapacity();
        }
        
        entries[size++] = e;
    }
    
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
    
    public int size() {
        return size;
    }
    
    public Object get(int i) {
        if (i > size) {
            throw new IndexOutOfBoundsException("Out of range");
        }
        Object obj = entries[i];
        return obj;
    }
    
    
    public Object poll() {
        Object toreturn = entries[0];
        Object newEntries[] = new Object[size - 1];
        for (int i = 0; i < size - 1; i++) {
            newEntries[i] = entries[i+1];
        }
        
        entries = newEntries;
        return toreturn;
    }
    
    public boolean contains(Object e) {
        for (int i = 0; i<size; i++) {
            if (entries[i].equals(e)) {
                return true;
            }
        }
        
        return false;
    }
}
