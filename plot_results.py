import pandas as pd
import matplotlib.pyplot as plt
import os

os.makedirs("docs/performance-plots", exist_ok=True)

csv = "docs/performance-plots/minheap_results.csv"
df = pd.read_csv(csv)

# Time vs N
plt.figure()
plt.plot(df["N"], df["TimeMs"], marker="o")
plt.xlabel("N")
plt.ylabel("Time (ms)")
plt.title("Time vs N")
plt.grid(True)
plt.savefig("docs/performance-plots/time_vs_n.png")
plt.close()

# Comparisons vs N
plt.figure()
plt.plot(df["N"], df["Comparisons"], marker="o")
plt.xlabel("N")
plt.ylabel("Comparisons")
plt.title("Comparisons vs N")
plt.grid(True)
plt.savefig("docs/performance-plots/comparisons_vs_n.png")
plt.close()

# Swaps vs N
plt.figure()
plt.plot(df["N"], df["Swaps"], marker="o")
plt.xlabel("N")
plt.ylabel("Swaps")
plt.title("Swaps vs N")
plt.grid(True)
plt.savefig("docs/performance-plots/swaps_vs_n.png")
plt.close()

# Combined log-scale
plt.figure()
plt.plot(df["N"], df["TimeMs"], marker="o", label="Time (ms)")
plt.plot(df["N"], df["Comparisons"], marker="s", label="Comparisons")
plt.plot(df["N"], df["Swaps"], marker="^", label="Swaps")
plt.xscale("log")
plt.yscale("log")
plt.xlabel("N (log scale)")
plt.ylabel("Metric (log scale)")
plt.title("Combined (log-log)")
plt.legend()
plt.grid(True, which="both")
plt.savefig("docs/performance-plots/combined_log.png")
plt.close()

print("Plots saved in docs/performance-plots/")
