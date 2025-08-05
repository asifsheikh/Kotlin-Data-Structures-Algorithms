# Array Algorithms - Organized Structure

This directory contains a comprehensive collection of array algorithms organized by category for easy navigation and learning.

## 📁 Directory Structure

```
algorithms/
├── searching/          # Search algorithms
├── sorting/           # Sorting algorithms  
├── rotation/          # Array rotation algorithms
├── transformation/    # Array transformation algorithms
├── validation/        # Array validation algorithms
├── statistics/        # Statistical algorithms
├── manipulation/      # Array manipulation algorithms
└── problems/          # Popular array problems
```

## 🔍 Searching Algorithms

### LinearSearch.kt
**Problem**: Find elements in unsorted arrays

**Algorithms**:
- `search()` - Basic linear search
- `searchSorted()` - Linear search with early exit for sorted arrays
- `searchLastOccurrence()` - Find last occurrence
- `searchAllOccurrences()` - Find all occurrences
- `searchWithSentinel()` - Optimized with sentinel
- `searchRecursive()` - Recursive implementation
- `findMinimum()` - Find minimum element
- `findMaximum()` - Find maximum element
- `findSecondLargest()` - Find second largest element

**Complexity**: O(n) time, O(1) space

### BinarySearch.kt
**Problem**: Find elements in sorted arrays efficiently

**Algorithms**:
- `search()` - Basic binary search
- `searchRecursive()` - Recursive implementation
- `findFirstOccurrence()` - First occurrence in sorted array
- `findLastOccurrence()` - Last occurrence in sorted array
- `findInsertionPosition()` - Find insertion point
- `findFloor()` - Find floor value
- `findCeiling()` - Find ceiling value
- `findPeakElement()` - Find peak element
- `searchInRotatedArray()` - Search in rotated sorted array
- `findMinimumInRotatedArray()` - Find minimum in rotated array
- `countOccurrences()` - Count occurrences

**Complexity**: O(log n) time, O(1) space

## 🔄 Sorting Algorithms

### QuickSort.kt
**Problem**: Sort arrays efficiently using divide-and-conquer

**Algorithms**:
- `sort()` - Basic QuickSort with Lomuto partition
- `sortWithHoarePartition()` - QuickSort with Hoare partition
- `sortRandomized()` - QuickSort with randomized pivot
- `sortWithMedianOfThree()` - QuickSort with median-of-three pivot
- `sortOptimized()` - QuickSort with tail call optimization
- `sortStrings()` - QuickSort for string arrays
- `quickSelect()` - Find k-th smallest element

**Complexity**: O(n log n) average, O(n²) worst case

## 🔄 Rotation Algorithms

### ArrayRotation.kt
**Problem**: Rotate arrays by k positions

**Algorithms**:
- `rotateLeft()` - Left rotation using reversal algorithm
- `rotateRight()` - Right rotation using reversal algorithm
- `rotateLeftJuggling()` - Left rotation using juggling algorithm
- `rotateLeftWithExtraSpace()` - Using extra array
- `rotateRightWithExtraSpace()` - Using extra array

**Complexity**: O(n) time, O(1) space (in-place)

## 🔄 Transformation Algorithms

### ArrayTransformation.kt
**Problem**: Transform arrays in various ways

**Algorithms**:
- `mapArray()` - Apply function to each element
- `filterArray()` - Keep elements satisfying condition
- `flatten2DArray()` - Convert 2D to 1D array
- `reshapeArray()` - Change array dimensions
- `concatenateArrays()` - Join multiple arrays
- `splitArray()` - Divide array into parts
- `transposeMatrix()` - Transpose 2D array
- `rotateMatrix90Clockwise()` - Rotate matrix 90°

**Complexity**: O(n) to O(n²) depending on operation

## ✅ Validation Algorithms

### ArrayValidation.kt
**Problem**: Validate array properties

**Algorithms**:
- `isArraySorted()` - Check if sorted (ascending/descending)
- `hasDuplicates()` - Check for duplicates
- `isArrayPalindrome()` - Check if palindrome
- `isArrayMonotonic()` - Check if monotonic
- `isArrayConsecutive()` - Check if consecutive
- `isMountainArray()` - Check if mountain array
- `isArithmeticSequence()` - Check if arithmetic sequence
- `isGeometricSequence()` - Check if geometric sequence

**Complexity**: O(n) time, O(1) space

## 📊 Statistics Algorithms

### ArrayStatistics.kt
**Problem**: Calculate statistical measures

**Algorithms**:
- `countOccurrences()` - Count element frequency
- `findMostFrequent()` - Find most frequent element
- `findUniqueElements()` - Find unique elements
- `findMissingNumbers()` - Find missing numbers
- `calculateMean()` - Calculate arithmetic mean
- `calculateMedian()` - Calculate median
- `calculateMode()` - Calculate mode
- `calculateVariance()` - Calculate variance
- `findQuartiles()` - Find quartiles

**Complexity**: O(n) to O(n log n) depending on operation

## 🔧 Manipulation Algorithms

### ArrayManipulation.kt
**Problem**: Manipulate array elements

**Algorithms**:
- `insertElement()` - Insert at specific position
- `removeElement()` - Remove by value
- `removeElementAt()` - Remove by index
- `replaceElement()` - Replace elements
- `shiftArrayLeft()` - Shift left by k positions
- `shiftArrayRight()` - Shift right by k positions
- `fillArray()` - Fill with value
- `copyArrayRange()` - Copy specific range
- `swapElements()` - Swap two elements
- `shuffleArray()` - Randomly shuffle

**Complexity**: O(n) time, O(n) space

## 🎯 Popular Problems

### TwoSum.kt
**Problem**: Find two numbers that sum to target

**Algorithms**:
- `findIndices()` - Return indices using hash map
- `findValues()` - Return values using hash map
- `findIndicesSorted()` - Two pointers for sorted array
- `findAllPairs()` - Find all pairs
- `findAllIndexPairs()` - Find all index pairs
- `findClosestSum()` - Find closest sum to target
- `findIndicesBST()` - Using BST approach
- `findIndicesWithDuplicates()` - Handle duplicates
- `threeSum()` - Three sum variant

**Complexity**: O(n) to O(n²) depending on variant

## 🚀 Usage Examples

```kotlin
// Searching
val arr = intArrayOf(4, 2, 7, 1, 9, 3, 6)
val index = LinearSearch.search(arr, 7) // 2
val sortedArr = intArrayOf(1, 2, 3, 4, 5, 6, 7)
val binIndex = BinarySearch.search(sortedArr, 5) // 4

// Sorting
val unsorted = intArrayOf(64, 34, 25, 12, 22, 11, 90)
QuickSort.sort(unsorted) // [11, 12, 22, 25, 34, 64, 90]

// Rotation
val rotateArr = intArrayOf(1, 2, 3, 4, 5, 6, 7)
ArrayRotation.rotateLeft(rotateArr, 2) // [3, 4, 5, 6, 7, 1, 2]

// Problems
val nums = intArrayOf(2, 7, 11, 15)
val result = TwoSum.findIndices(nums, 9) // [0, 1]
```

## 📈 Complexity Summary

| Category | Time Complexity | Space Complexity |
|----------|----------------|------------------|
| Linear Search | O(n) | O(1) |
| Binary Search | O(log n) | O(1) |
| QuickSort | O(n log n) avg | O(log n) |
| Array Rotation | O(n) | O(1) |
| Array Transformation | O(n) - O(n²) | O(n) |
| Array Validation | O(n) | O(1) |
| Array Statistics | O(n) - O(n log n) | O(k) |
| Array Manipulation | O(n) | O(n) |
| Two Sum | O(n) | O(n) |

## 🎯 Key Features

✅ **Comprehensive Coverage** - All major array algorithms included
✅ **Multiple Approaches** - Different solutions for same problem
✅ **Detailed Documentation** - Problem, intuition, solution, complexity
✅ **Optimized Implementations** - Best practices and optimizations
✅ **Edge Case Handling** - Proper handling of edge cases
✅ **Consistent Structure** - Same pattern across all files
✅ **Easy Navigation** - Organized by category
✅ **Production Ready** - Well-tested and optimized code

## 🔄 Migration from Old Structure

The old `ArrayOperations.kt` file can be gradually migrated to use these new organized files:

```kotlin
// Old way
ArrayOperations.linearSearch(arr, target)

// New way
LinearSearch.search(arr, target)
```

## 📚 Learning Path

1. **Start with Searching** - Linear and Binary search fundamentals
2. **Learn Sorting** - QuickSort and other sorting algorithms
3. **Master Manipulation** - Array operations and transformations
4. **Practice Problems** - Apply algorithms to real problems
5. **Explore Advanced** - Complex algorithms and optimizations

## 🎯 Next Steps

This structure can be extended to other data structures:
- **Linked Lists** - Node-based algorithms
- **Trees** - Tree traversal and manipulation
- **Graphs** - Graph algorithms and pathfinding
- **Stacks & Queues** - Stack and queue operations
- **Hash Tables** - Hash table implementations
- **Heaps** - Heap operations and algorithms

Each following the same organized pattern for consistency and ease of learning. 