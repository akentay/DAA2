# Min-Heap Implementation (with Decrease-Key and Merge Operations)

### 👩‍💻 Author
**Aida Kentay**  
Course: *Design and Analysis of Algorithms (DAA)*  
Project: *Heap Data Structures — Individual Implementation*

---

## 🧩 Project Overview
This project implements a **Min-Heap** data structure in Java with additional operations such as:
- `decreaseKey()` — efficiently decreases the value of a given key.
- `merge()` — merges two heaps into one.
- `extractMin()` — removes and returns the minimum element.
- `insert()` — adds a new element while maintaining the heap property.

The implementation also includes:
- **Performance metrics tracking** (comparisons, swaps, array accesses)
- **CLI benchmark runner** for empirical testing
- **JUnit test suite** for edge case validation

---

## ⚙️ Features
✅ Clean, readable, well-documented Java code  
✅ Input validation and error handling  
✅ Comprehensive unit tests (empty, single-element, duplicate cases)  
✅ Performance tracking and CSV export  
✅ Command-line interface for benchmarking  
✅ Optimization for memory efficiency  

---

| N       | Comparisons | Swaps   | Array Accesses | Time (ms) |
| ------- | ----------- | ------- | -------------- | --------- |
| 100     | 1478        | 102     | 1528           | 1         |
| 1,000   | 24,712      | 1,204   | 22,540         | 2         |
| 10,000  | 347,652     | 12,788  | 294,742        | 5         |
| 100,000 | 4,475,868   | 128,614 | 3,615,588      | 13        |

| Operation   | Time Complexity | Space Complexity |
| ----------- | --------------- | ---------------- |
| Insert      | O(log n)        | O(1)             |
| ExtractMin  | O(log n)        | O(1)             |
| DecreaseKey | O(log n)        | O(1)             |
| Merge       | O(n + m)        | O(n + m)         |


🏁 Conclusion

This project demonstrates a robust and optimized implementation of the Min-Heap with advanced features.
The combination of theoretical analysis, empirical results, and clean code ensures both correctness and efficiency.

📎 Version Control Workflow

Branching Strategy:

feature/algorithm — base Min-Heap implementation

feature/metrics — performance tracking and CSV export

feature/testing — JUnit tests

feature/cli — command-line benchmark runner

feature/optimization — performance tuning and final release

Commit Example:

- init: maven project setup
- feat(algorithm): implemented Min-Heap with decrease-key and merge
- feat(metrics): added performance tracker and CSV export
- test(algorithm): unit tests for edge cases
- feat(cli): benchmark runner with variable input sizes
- feat(optimization): improved heapifyDown performance
- docs(readme): added usage and complexity analysis
- release: v1.0 finalized
