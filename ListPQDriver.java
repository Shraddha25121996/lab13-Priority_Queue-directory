/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author shraddhapatel
 */
import java.util.LinkedList;

// --- 1. Entry ADT ---
class MyEntry<K extends Comparable<K>, V> implements Comparable<MyEntry<K, V>> {
    private K key;
    private V value;

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public K getKey() { return key; }
    public V getValue() { return value; }

    @Override
    public int compareTo(MyEntry<K, V> other) {
        return this.key.compareTo(other.key);
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}

// --- 2. Common Interface ---
interface PriorityQueue<K extends Comparable<K>, V> {
    void insert(K key, V value);
    MyEntry<K, V> removeMin();
    MyEntry<K, V> min();
    boolean isEmpty();
}

// --- 3. Unsorted List PQ ---
class UnsortedListPQ<K extends Comparable<K>, V> implements PriorityQueue<K, V> {
    private LinkedList<MyEntry<K, V>> list = new LinkedList<>();

    public boolean isEmpty() { return list.isEmpty(); }

    public void insert(K key, V value) {
        list.addLast(new MyEntry<>(key, value));
    }

    public MyEntry<K, V> min() {
        if (isEmpty()) return null;
        MyEntry<K, V> minEntry = list.getFirst();
        for (MyEntry<K, V> entry : list) {
            if (entry.compareTo(minEntry) < 0) {
                minEntry = entry;
            }
        }
        return minEntry;
    }

    public MyEntry<K, V> removeMin() {
        if (isEmpty()) return null;

        // 1. Find minimum entry
        MyEntry<K, V> minEntry = min();

        // 2. Remove it from the LinkedList (O(n))
        list.remove(minEntry);

        return minEntry;
    }
}

// --- 4. Sorted List PQ ---
class SortedListPQ<K extends Comparable<K>, V> implements PriorityQueue<K, V> {
    private LinkedList<MyEntry<K, V>> list = new LinkedList<>();

    public boolean isEmpty() { return list.isEmpty(); }

    public void insert(K key, V value) {
        MyEntry<K, V> newEntry = new MyEntry<>(key, value);

        // 1. Find correct position to keep list sorted
        int index = 0;
        while (index < list.size() && list.get(index).compareTo(newEntry) <= 0) {
            index++;
        }

        // 2. Insert at the found index
        list.add(index, newEntry);
    }

    public MyEntry<K, V> min() {
        return isEmpty() ? null : list.getFirst();
    }

    public MyEntry<K, V> removeMin() {
        return isEmpty() ? null : list.removeFirst();
    }
}

// --- 5. Driver Class ---
public class ListPQDriver {
    public static void main(String[] args) {

        System.out.println("--- UnsortedListPQ (O(n) removal) ---");
        PriorityQueue<Integer, String> pq1 = new UnsortedListPQ<>();
        pq1.insert(5, "Task E"); 
        pq1.insert(1, "Task A"); 
        pq1.insert(10, "Task G"); 
        pq1.insert(3, "Task C");

        while(!pq1.isEmpty()) 
            System.out.println("Removed: " + pq1.removeMin());

        System.out.println("\n--- SortedListPQ (O(n) insertion) ---");
        PriorityQueue<Integer, String> pq2 = new SortedListPQ<>();
        pq2.insert(5, "Task E"); 
        pq2.insert(1, "Task A"); 
        pq2.insert(10, "Task G"); 
        pq2.insert(3, "Task C");

        while(!pq2.isEmpty()) 
            System.out.println("Removed: " + pq2.removeMin());
    }
}