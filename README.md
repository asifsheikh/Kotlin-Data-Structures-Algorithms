# Kotlin Data Structures & Algorithms

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-blue.svg)](https://kotlinlang.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Contributions Welcome](https://img.shields.io/badge/Contributions-Welcome-brightgreen.svg)](CONTRIBUTING.md)

🎯 **A comprehensive collection of data structures and algorithms implemented in Kotlin**

This repository serves as a complete reference for learning and implementing fundamental data structures and algorithms. Each algorithm includes detailed problem descriptions, multiple solution approaches, complexity analysis, and production-ready implementations.

## 📚 Table of Contents

- [Features](#-features)
- [Quick Start](#-quick-start)
- [Complexity Reference](#-complexity-reference)
- [Learning Path](#-learning-path)
- [Contributing](#-contributing)
- [License](#-license)

## ✨ Features

- **📖 Comprehensive Documentation** - Each algorithm includes problem description, intuition, solution, and complexity analysis
- **🔄 Multiple Approaches** - Different solutions for the same problem with various trade-offs
- **⚡ Optimized Implementations** - Production-ready code with best practices
- **🎯 Well-Organized Structure** - Easy navigation by algorithm category
- **📊 Complexity Analysis** - Detailed time and space complexity for each solution
- **🧪 Edge Case Handling** - Proper handling of edge cases and error conditions
- **📝 Consistent Patterns** - Same documentation structure across all algorithms

## 🚀 Quick Start

### Prerequisites

- Kotlin 1.9+ 
- JDK 8 or higher

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/Kotlin-Data-Structures-Algorithms.git
   cd Kotlin-Data-Structures-Algorithms
   ```

2. **Open in your IDE**
   - IntelliJ IDEA (recommended)
   - Android Studio
   - VS Code with Kotlin extension

3. **Run examples**
   ```bash
   ./gradlew build
   ./gradlew run
   ```

## 📈 Complexity Reference

| Algorithm/Structure | Time Complexity | Space Complexity |
|---------------------|-----------------|------------------|
| **Hash Map (get/put/remove)** | O(1) avg, O(n) worst | O(n) |
| **Set (contains/add/remove)** | O(1) avg, O(n) worst | O(n) |
| **Stack (push/pop/peek)** | O(1) | O(1) |
| **Queue (enqueue/dequeue)** | O(1) | O(1) |
| **Binary Heap (insert/extract-min,max)** | O(log n) | O(n) |
| **Binary Heap (find-min,max)** | O(1) | O(1) |
| **Heapify** | O(n) | O(1) |
| **Union-Find (DSU, amortized)** | ~O(1) (α(n)) | O(n) |
| **Trie (insert/search/prefix)** | O(L) | O(total chars) |
| **Fenwick Tree (update/prefix sum)** | O(log n) | O(n) |
| **Fenwick Tree (build)** | O(n) | O(n) |
| **BST (avg ops; worst in parentheses)** | O(log n) avg (O(n) worst) | O(n) |
| **AVL/Red-Black Tree (ops)** | O(log n) | O(n) |
| **Tree traversals (DFS/BFS on tree)** | O(n) | O(h) |
| **Graph BFS/DFS** | O(V + E) | O(V) |
| **Topological Sort** | O(V + E) | O(V) |
| **Dijkstra (binary heap)** | O((V + E) log V) | O(V + E) |
| **Bellman–Ford** | O(VE) | O(V) |
| **Floyd–Warshall** | O(V^3) | O(V^2) |
| **Kruskal (with DSU)** | O(E log V) | O(V) |
| **Prim (binary heap)** | O(E log V) | O(V) |
| **Shortest Path in DAG** | O(V + E) | O(V) |
| **Edit Distance (Levenshtein)** | O(nm) | O(nm) |
| **0/1 Knapsack** | O(nW) | O(nW) |
| **LIS (n log n approach)** | O(n log n) | O(n) |
| **Sieve of Eratosthenes** | O(n log log n) | O(n) |
| **Euclidean GCD** | O(log min(a, b)) | O(1) |

## 🏗️ Project Structure

Removed for brevity.

## 🎯 Learning Path

1. **Start with Basics** - Linear and Binary search
2. **Learn Sorting** - QuickSort and MergeSort fundamentals
3. **Master Operations** - Array manipulation and transformation
4. **Solve Problems** - Apply algorithms to real problems
5. **Explore Advanced** - Complex algorithms and optimizations

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guidelines](CONTRIBUTING.md) for details.

### How to Contribute

1. **Fork the repository**
2. **Create a feature branch** (`git checkout -b feature/amazing-algorithm`)
3. **Add your implementation** following the existing patterns
4. **Add comprehensive documentation** including problem description, intuition, and complexity analysis
5. **Test your code** thoroughly
6. **Commit your changes** (`git commit -m 'Add amazing algorithm'`)
7. **Push to the branch** (`git push origin feature/amazing-algorithm`)
8. **Open a Pull Request**

### What We're Looking For

- **New algorithms** with comprehensive documentation
- **Optimizations** to existing algorithms
- **Bug fixes** and improvements
- **Better examples** and use cases
- **Additional data structures** (Linked Lists, Trees, Graphs, etc.)

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

⭐ **If this repository helps you, please give it a star!** ⭐

*Happy coding! 🚀* 