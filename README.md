# DAA — Min-Heap Implementation (decrease-key & merge)

## Структура
- `src/main/java/algorithms/MinHeap.java` — реализация
- `src/main/java/metrics` — PerformanceTracker + CsvExporter
- `src/main/java/cli/BenchmarkRunner.java` — CLI для бенчмарка
- `src/test/java/algorithms/MinHeapTest.java` — JUnit тесты
- `docs/performance-plots/` — CSV и график
- `docs/analysis-report.md` — отчёт (экспорт в PDF для сдачи)

## Быстрый старт
1. Сборка:
```bash
mvn clean package
