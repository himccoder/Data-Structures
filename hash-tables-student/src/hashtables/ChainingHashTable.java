package hashtables;

import java.util.Iterator;
import java.util.ArrayList;
/**
 * An implementation of HashTable.
 * 
 * This implementation uses chaining to resolve collisions. Chaining means 
 * the underlying array stores references to growable structures (like LinkedLists
 * or ArrayLists) that we expect to remain small in size. When there is a 
 * collision, the element is added to the end of the growable structure. It
 * must search the entire growable structure whenever checking membership
 * or removing elements.
 * 
 * This implementation maintains a capacity equal to 2^n - 1 for some positive
 * integer n. When the load factor exceeds 0.75, the next add() triggers a
 * resize by incrementing n (by one). For example, when n=3, then capacity=7.
 * When size=6, then load factor ~=0.86. The addition of the seventh item would
 * trigger a resize, increasing the capacity of the array to 15.
 */
public class ChainingHashTable<E> implements HashTable<E> {
    private ArrayList<E>[] table;
    private int size;
    private int capacity;
    private final double loadFactorThreshold = 0.75;
    private int resizeCounter;
    private int n=0;
    
    /**
     * Instantiate a new hash table. The initial capacity should be 7.
     */
    public ChainingHashTable() {
        this.n = 3;
        this.capacity = (int) Math.pow(2, this.n) - 1;
        this.table = (ArrayList<E>[]) new ArrayList[this.capacity];
    }

    /**
     * Instantiate a new hash table. The initial capacity should be 
     * at least sufficient to hold n elements, but must be one less
     * than a power of two.
     */
    public ChainingHashTable(int n) {
        this.capacity = (int) Math.pow(2, this.n) - 1;
        while(this.capacity<n){
            this.n++;
            this.capacity = (int) Math.pow(2, this.n) - 1;
        }
        
        this.table = (ArrayList<E>[]) new ArrayList[this.capacity];
        this.size = 0;
        this.resizeCounter = 0;
        
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public double loadFactor() {
        return (double) this.size/this.capacity;
    }

    @Override
    public boolean add(E e) {
        if(loadFactor()>loadFactorThreshold){
            resize();
        } 
        if(e==null){ 
            return false;
        }
        if (contains(e)) {
            return false;
        }
        int index = hash(e);
        if (this.table[index] == null) {
            this.table[index] = new ArrayList<>();
        }
        this.table[index].add(e);
        this.size++;
        return true;
        
        
    }
    

    @Override
    public boolean remove(E e) {
        if(!this.contains(e)){
            return false;
        }
        int index = hash(e);
        ArrayList<E> list = this.table[index];
        if (list == null) {
            return false;
        }

        boolean removed = list.remove(e);
        if (removed) {
            this.size--;
        }
    
        return removed;
    }

    @Override
    public boolean contains(E e) {
        int index = hash(e);
        ArrayList<E> list = this.table[index];
        if (list == null) {
            return false;
        }
        return list.contains(e);

    }

    @Override
    public E get(E e) {
        int index = hash(e);
        ArrayList<E> list = this.table[index];
        if (list == null) {
            return null;
        }
        for (E element : list) {
            if (element.equals(e)) {
                return element;
            }
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {  
        ArrayList<E> list = new ArrayList<>();
        for (ArrayList<E> chain : this.table) {
            if (chain != null) {
                list.addAll(chain);
            }
        }
        return list.iterator();
    }
    
    
    


    /**
     * Resize the hash table to double its current capacity, plus one.
     */
    public void resize() {
        this.n++;
        this.capacity = (int) Math.pow(2, this.n) - 1;
        ArrayList<E>[] newTable = (ArrayList<E>[]) new ArrayList[this.capacity];
        for (E element : this) {
            int index = hash(element);
            if (newTable[index] == null) {
                newTable[index] = new ArrayList<>();
            }
            newTable[index].add(element);
        }
        this.table = newTable;
        this.resizeCounter++;
    }
    
        

    /**
     * Returns the index for the given element.
     * The hash code is calculated as the result of the element's
     * hashCode() method, modulo the capacity of the hash table.
     *
     * @param e the element to hash
     * @return the index for the element
     */

    public int hash(E e){
        int hashCode = Math.abs(e.hashCode());
        int index = hashCode % this.capacity;
        return index;
    }

    public ArrayList[] getTable(){
        return this.table;
    }


}



