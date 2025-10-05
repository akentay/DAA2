package algorithms;

import metrics.PerformanceTracker;
import java.util.Arrays;

/**
 * Min-Heap implementation with:
 * - decreaseKey(index, newValue)
 * - merge(anotherHeap)
 * - dynamic resizing
 * Instrumented with PerformanceTracker (may be null).
 */
public class MinHeap {
    private int[] heap;
    private int size;
    private PerformanceTracker tracker;

    public MinHeap(int capacity, PerformanceTracker tracker) {
        if (capacity < 1) capacity = 1;
        this.heap = new int[capacity];
        this.size = 0;
        this.tracker = (tracker != null) ? tracker : new PerformanceTracker();
    }

    public MinHeap(int capacity) {
        this(capacity, null);
    }

    public void insert(int value) {
        ensureCapacity();
        heap[size] = value;
        tracker.incArrayAccesses(1);
        heapifyUp(size);
        size++;
    }

    public int extractMin() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        int min = heap[0];
        heap[0] = heap[size - 1];
        tracker.incArrayAccesses(1);
        size--;
        heapifyDown(0);
        return min;
    }

    /**
     * decreaseKey: уменьшает значение в позиции index до newValue.
     * newValue must be <= current value.
     */
    public void decreaseKey(int index, int newValue) {
        if (index < 0 || index >= size) throw new IllegalArgumentException("Index out of bounds");
        if (newValue > heap[index]) throw new IllegalArgumentException("newValue must be <= current value");
        heap[index] = newValue;
        tracker.incArrayAccesses(1);
        heapifyUp(index);
    }

    /**
     * Merge another MinHeap into this. Complexity O(m + n)
     * We will append other's array and build-heap (linear).
     */
    public void merge(MinHeap other) {
        if (other == null || other.size == 0) return;
        int newSize = this.size + other.size;
        if (heap.length < newSize) {
            int newCap = Math.max(heap.length * 2, newSize);
            heap = Arrays.copyOf(heap, newCap);
        }
        System.arraycopy(other.heap, 0, heap, size, other.size);
        tracker.incArrayAccesses(other.size);
        size = newSize;
        // Build heap in O(n) using sift-down from last parent
        for (int i = parent(size - 1); i >= 0; i--) {
            heapifyDown(i);
        }
    }

    private void heapifyUp(int index) {
        int current = index;
        int temp = heap[current];
        tracker.incArrayAccesses(1);
        while (current > 0) {
            int p = parent(current);
            tracker.incComparisons();
            if (heap[p] > temp) {
                tracker.incArrayAccesses(1);
                heap[current] = heap[p];
                current = p;
            } else break;
        }
        heap[current] = temp;
        tracker.incArrayAccesses(1);
    }

    private void heapifyDown(int index) {
        int current = index;
        int temp = heap[current];
        tracker.incArrayAccesses(1);
        while (leftChild(current) < size) {
            int left = leftChild(current);
            int right = rightChild(current);
            int smaller = left;
            tracker.incComparisons();
            if (right < size) {
                tracker.incComparisons();
                if (heap[right] < heap[left]) smaller = right;
            }
            tracker.incComparisons();
            if (heap[smaller] < temp) {
                tracker.incArrayAccesses(1);
                heap[current] = heap[smaller];
                current = smaller;
            } else break;
        }
        heap[current] = temp;
        tracker.incArrayAccesses(1);
    }

    private void ensureCapacity() {
        if (size >= heap.length) {
            int newCap = heap.length * 2;
            if (newCap <= heap.length) newCap = heap.length + 1;
            heap = Arrays.copyOf(heap, newCap);
        }
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int leftChild(int i) { return 2 * i + 1; }
    private int rightChild(int i) { return 2 * i + 2; }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    // Optional: get internal array copy (for diagnostics), not exposing internal ref
    public int[] toArray() { return Arrays.copyOf(heap, size); }
}
