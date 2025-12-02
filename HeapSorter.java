/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Arrays;
import java.util.Random;
/**
 *
 * @author shraddhapatel
 */
public class HeapSorter {
    
    public static void heapSort(Integer[] arr) {
        HeapPriorityQueue<Integer> pq = new HeapPriorityQueue<>();

        // Insert Phase
        for (Integer x : arr) pq.insert(x);

        // Remove Phase (sorted output)
        for (int i = 0; i < arr.length; i++)
            arr[i] = pq.removeMin();
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[10];
        Random rand = new Random();
        for (int i = 0; i < data.length; i++)
            data[i] = rand.nextInt(100);

        System.out.println("Before Sorting: " + Arrays.toString(data));
        heapSort(data);
        System.out.println("After Sorting:  " + Arrays.toString(data));
    }
} 

