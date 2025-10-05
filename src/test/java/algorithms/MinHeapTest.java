package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {

    @Test
    void testInsertAndExtract() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap heap = new MinHeap(10, tracker);

        heap.insert(5);
        heap.insert(1);
        heap.insert(9);

        assertEquals(1, heap.extractMin());
        assertEquals(5, heap.extractMin());
        assertEquals(9, heap.extractMin());
    }

    @Test
    void testExtractFromEmptyHeap() {
        MinHeap heap = new MinHeap(5);
        assertThrows(IllegalStateException.class, heap::extractMin);
    }

    @Test
    void testSingleElement() {
        MinHeap heap = new MinHeap(5);
        heap.insert(42);
        assertEquals(42, heap.extractMin());
        assertTrue(heap.isEmpty());
    }

    @Test
    void testDecreaseKey() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap heap = new MinHeap(5, tracker);

        heap.insert(10);
        heap.insert(20);
        heap.insert(5);

        // decrease the value at index 1 (originally 20) to 2 -> should become new min
        heap.decreaseKey(1, 2);
        assertEquals(2, heap.extractMin());
    }

    @Test
    void testDuplicates() {
        MinHeap heap = new MinHeap(5);
        heap.insert(7);
        heap.insert(7);
        heap.insert(7);

        assertEquals(7, heap.extractMin());
        assertEquals(7, heap.extractMin());
        assertEquals(7, heap.extractMin());
    }

    @Test
    void testMerge() {
        PerformanceTracker tracker1 = new PerformanceTracker();
        PerformanceTracker tracker2 = new PerformanceTracker();

        MinHeap a = new MinHeap(3, tracker1);
        MinHeap b = new MinHeap(3, tracker2);

        a.insert(1); a.insert(4); a.insert(9);
        b.insert(2); b.insert(3); b.insert(5);

        a.merge(b);
        // now extracting should give 1,2,3,4,5,9 (sorted)
        int[] expected = {1,2,3,4,5,9};
        for (int e : expected) assertEquals(e, a.extractMin());
    }

    @Test
    void testDynamicResizing() {
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap heap = new MinHeap(1, tracker);
        for (int i = 0; i < 1000; i++) heap.insert(i);
        assertEquals(0, heap.extractMin());
    }
}
