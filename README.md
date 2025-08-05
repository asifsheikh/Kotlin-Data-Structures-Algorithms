# Kotlin Data Structures & Algorithms

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-blue.svg)](https://kotlinlang.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Contributions Welcome](https://img.shields.io/badge/Contributions-Welcome-brightgreen.svg)](CONTRIBUTING.md)

🎯 **A comprehensive collection of data structures and algorithms implemented in Kotlin**

This repository serves as a complete reference for learning and implementing fundamental data structures and algorithms. Each algorithm includes detailed problem descriptions, multiple solution approaches, complexity analysis, and production-ready implementations.

## 📚 Table of Contents

- [Features](#-features)
- [Quick Start](#-quick-start)
- [Data Structures](#-data-structures)
- [Algorithms](#-algorithms)
- [Usage Examples](#-usage-examples)
- [Complexity Reference](#-complexity-reference)
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

## 📊 Data Structures

### Arrays
- **Searching Algorithms**
  - Linear Search (basic, sentinel, recursive)
  - Binary Search (basic, recursive, first/last occurrence)
  - Peak Finding
  - Missing Number Detection

- **Sorting Algorithms**
  - QuickSort (Lomuto, Hoare, randomized, median-of-three)
  - MergeSort (basic, in-place, bottom-up, linked list)
  - More coming soon...

- **Array Operations**
  - Rotation (left, right, juggling algorithm)
  - Transformation (map, filter, reshape, concatenate)
  - Validation (sorted, duplicates, palindrome, monotonic)
  - Statistics (mean, median, mode, variance)
  - Manipulation (insert, remove, replace, shift)

### Popular Problems
- **Two Sum** - Multiple approaches (hash map, two pointers, BST)
- **Maximum Subarray Sum** - Kadane's algorithm with variations
- More coming soon...

## 🔍 Algorithms

### Searching
```kotlin
// Linear Search
val arr = intArrayOf(4, 2, 7, 1, 9, 3, 6)
val index = LinearSearch.search(arr, 7) // Returns 2

// Binary Search
val sortedArr = intArrayOf(1, 2, 3, 4, 5, 6, 7)
val binIndex = BinarySearch.search(sortedArr, 5) // Returns 4
```

### Sorting
```kotlin
// QuickSort
val unsorted = intArrayOf(64, 34, 25, 12, 22, 11, 90)
QuickSort.sort(unsorted) // Sorts in-place

// MergeSort
val arr = intArrayOf(38, 27, 43, 3, 9, 82, 10)
MergeSort.sort(arr) // Sorts in-place
```

### Array Operations
```kotlin
// Rotation
val rotateArr = intArrayOf(1, 2, 3, 4, 5, 6, 7)
ArrayRotation.rotateLeft(rotateArr, 2) // [3, 4, 5, 6, 7, 1, 2]

// Validation
val palindrome = intArrayOf(1, 2, 2, 1)
val isPal = ArrayValidation.isArrayPalindrome(palindrome) // true
```

### Popular Problems
```kotlin
// Two Sum
val nums = intArrayOf(2, 7, 11, 15)
val result = TwoSum.findIndices(nums, 9) // [0, 1]

// Maximum Subarray Sum
val arr = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
val maxSum = MaxSubarraySum.kadaneAlgorithm(arr) // 6
```

## 📈 Complexity Reference

| Algorithm Category | Time Complexity | Space Complexity |
|-------------------|----------------|------------------|
| **Linear Search** | O(n) | O(1) |
| **Binary Search** | O(log n) | O(1) |
| **QuickSort** | O(n log n) avg, O(n²) worst | O(log n) |
| **MergeSort** | O(n log n) | O(n) |
| **Array Rotation** | O(n) | O(1) |
| **Array Validation** | O(n) | O(1) |
| **Two Sum** | O(n) | O(n) |
| **Kadane's Algorithm** | O(n) | O(1) |

## 🏗️ Project Structure

```
src/data_structure/arrays/algorithms/
├── searching/              # Search algorithms
│   ├── LinearSearch.kt     # Linear search variations
│   └── BinarySearch.kt     # Binary search variations
├── sorting/               # Sorting algorithms
│   ├── QuickSort.kt       # QuickSort implementations
│   └── MergeSort.kt       # MergeSort implementations
├── rotation/              # Array rotation
├── transformation/        # Array transformations
├── validation/           # Array validation
├── statistics/           # Statistical algorithms
├── manipulation/         # Array manipulation
├── problems/             # Popular problems
│   ├── TwoSum.kt         # Two Sum problem
│   └── MaxSubarraySum.kt # Kadane's algorithm
└── README.md             # Detailed documentation
```

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

## 🙏 Acknowledgments

- Inspired by various algorithm textbooks and online resources
- Built with ❤️ for the Kotlin community
- Special thanks to all contributors and the open-source community

## 📞 Contact

- **Repository**: [Kotlin-Data-Structures-Algorithms](https://github.com/yourusername/Kotlin-Data-Structures-Algorithms)
- **Issues**: [GitHub Issues](https://github.com/yourusername/Kotlin-Data-Structures-Algorithms/issues)
- **Discussions**: [GitHub Discussions](https://github.com/yourusername/Kotlin-Data-Structures-Algorithms/discussions)

---

⭐ **If this repository helps you, please give it a star!** ⭐

*Happy coding! 🚀* 