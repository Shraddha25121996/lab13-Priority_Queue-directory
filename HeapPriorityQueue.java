/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author shraddhapatel
 */
import java.util.ArrayList;

class HeapPriorityQueue<K extends Comparable<K>> {
    private ArrayList<K> heap = new ArrayList<>();

    protected int parent(int j) { return (j - 1) / 2; }
    protected int left(int j) { return 2 * j + 1; }
    protected int right(int j) { return 2 * j + 2; }

    public int size() { return heap.size(); }
    public boolean isEmpty() { return heap.isEmpty(); }
    
    private void swap(int i, int j) {
        K temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void insert(K key) {
        heap.add(key);
        upheap(heap.size() - 1);
    }

    public K removeMin() {
        if (isEmpty()) return null;
        K answer = heap.get(0);
        K last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            downheap(0);
        }
        return answer;
    }

    public K min() {
        return isEmpty() ? null : heap.get(0);
    }

    // ------------------------------
    //   🎯 COMPLETE UPHEAP METHOD
    // ------------------------------
    private void upheap(int j) {
        while (j > 0) {
            int p = parent(j);
            if (heap.get(j).compareTo(heap.get(p)) >= 0)
                break;  // heap property satisfied
            swap(j, p);
            j = p;
        }
    }

    // ------------------------------
    //   🎯 COMPLETE DOWNHEAP METHOD
    // ------------------------------
    private void downheap(int j) {
        while (left(j) < heap.size()) {  // while left child exists
            int leftIndex = left(j);
            int smallChild = leftIndex;

            int rightIndex = right(j);
            if (rightIndex < heap.size() &&
                heap.get(rightIndex).compareTo(heap.get(leftIndex)) < 0) {
                smallChild = rightIndex;  // right child is smaller
            }

            if (heap.get(smallChild).compareTo(heap.get(j)) >= 0)
                break; // heap property satisfied

            swap(j, smallChild);
            j = smallChild;
        }
    }
}