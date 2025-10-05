package metrics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CsvExporter {
    private final String path;

    public CsvExporter(String path) {
        this.path = path;
        ensureDirectory();
    }

    private void ensureDirectory() {
        File f = new File(path);
        File parent = f.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();
    }

    public void writeHeader(String... cols) {
        try (FileWriter w = new FileWriter(path, false)) {
            w.write(String.join(",", cols));
            w.write("\n");
        } catch (IOException e) {
            System.err.println("Error writing CSV header: " + e.getMessage());
        }
    }

    public void appendRow(String... values) {
        try (FileWriter w = new FileWriter(path, true)) {
            w.write(String.join(",", values));
            w.write("\n");
        } catch (IOException e) {
            System.err.println("Error writing CSV row: " + e.getMessage());
        }
    }
}
