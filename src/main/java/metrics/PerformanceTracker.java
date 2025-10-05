package metrics;

/**
 * Простая счётчик-утилита для метрик:
 * comparisons, swaps, arrayAccesses.
 */
public class PerformanceTracker {
    private long comparisons = 0;
    private long swaps = 0;
    private long arrayAccesses = 0;

    public void incComparisons() { comparisons++; }
    public void incSwaps() { swaps++; }
    public void incArrayAccesses(long count) { arrayAccesses += count; }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public long getArrayAccesses() { return arrayAccesses; }

    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
    }

    public String report() {
        return "Comparisons=" + comparisons + ", Swaps=" + swaps + ", ArrayAccesses=" + arrayAccesses;
    }
}
