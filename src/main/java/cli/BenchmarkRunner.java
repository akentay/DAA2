package cli;

import algorithms.MinHeap;
import metrics.PerformanceTracker;
import metrics.CsvExporter;

import java.util.Random;

public class BenchmarkRunner {
    public static void main(String[] args) {
        int[] sizes;
        if (args.length > 0) {
            sizes = new int[args.length];
            for (int i = 0; i < args.length; i++) sizes[i] = Integer.parseInt(args[i]);
        } else {
            sizes = new int[]{100, 1_000, 10_000, 100_000};
        }

        CsvExporter exporter = new CsvExporter("docs/performance-plots/minheap_results.csv");
        exporter.writeHeader("N","Comparisons","Swaps","ArrayAccesses","TimeMs");

        for (int n : sizes) {
            runBenchmark(n, exporter);
        }
        System.out.println("Benchmark finished. CSV -> docs/performance-plots/minheap_results.csv");
    }

    private static void runBenchmark(int n, CsvExporter exporter) {
        Random rand = new Random(12345);
        PerformanceTracker tracker = new PerformanceTracker();
        MinHeap heap = new MinHeap(Math.max(1, n/10), tracker);

        int[] data = new int[n];
        for (int i = 0; i < n; i++) data[i] = rand.nextInt(n * 10);

        long start = System.currentTimeMillis();
        for (int v : data) heap.insert(v);
        // also try decreaseKey on some elements (to exercise decrease-key)
        for (int i = 0; i < Math.min(10, heap.size()); i++) {
            if (!heap.isEmpty()) {
                int idx = i;
                // decrease value at idx if possible (safer with toArray)
                // We'll just call decreaseKey on an index if valid:
                try {
                    heap.decreaseKey(idx, -rand.nextInt(1000)); // decrease to negative value
                } catch (Exception ignored) {}
            }
        }
        while (!heap.isEmpty()) heap.extractMin();
        long end = System.currentTimeMillis();
        long elapsed = end - start;

        exporter.appendRow(
            String.valueOf(n),
            String.valueOf(tracker.getComparisons()),
            String.valueOf(tracker.getSwaps()),
            String.valueOf(tracker.getArrayAccesses()),
            String.valueOf(elapsed)
        );
    }
}
