# Array Algorithms

This directory contains organized array algorithms following a consistent pattern for easy navigation and understanding.

## Structure

Each algorithm file follows this pattern:
1. **Problem Description** - What we're solving
2. **Intuition** - How we approach the problem
3. **Solution** - The actual implementation
4. **Complexity Analysis** - Time and space complexity

## Files

### 1. ArrayRotation.kt
**Problem**: Rotate arrays by k positions (left/right)

**Algorithms**:
- `rotateLeft()` - Left rotation using reversal algorithm
- `rotateRight()` - Right rotation using reversal algorithm
- `rotateLeftJuggling()` - Left rotation using juggling algorithm
- `rotateLeftWithExtraSpace()` - Using extra array approach
- `rotateRightWithExtraSpace()` - Using extra array approach

**Key Concepts**:
- Reversal Algorithm: Reverse parts then entire array
- Juggling Algorithm: Use GCD to move elements in sets
- Modular arithmetic for circular shifts

### 2. ArrayReversal.kt
**Problem**: Reverse arrays in various ways

**Algorithms**:
- `reverseArray()` - Reverse entire array
- `reverseArrayInRange()` - Reverse specific range
- `reverseArrayInGroups()` - Reverse in groups of k
- `isArrayPalindrome()` - Check if array is palindrome
- `reverseArrayRecursive()` - Recursive approach

**Key Concepts**:
- Two-pointer technique
- Group reversal patterns
- Recursive vs iterative approaches

### 3. ArraySearching.kt
**Problem**: Search for elements in arrays

**Algorithms**:
- `linearSearch()` - Search in unsorted array
- `binarySearch()` - Search in sorted array
- `findFirstOccurrence()` - First occurrence in sorted array
- `findLastOccurrence()` - Last occurrence in sorted array
- `findMissingNumber()` - Find missing number
- `findDuplicateNumber()` - Find duplicate using Floyd's cycle
- `findPeakElement()` - Find peak element
- `findKthLargest()` - Find k-th largest element

**Key Concepts**:
- Binary search variations
- Floyd's cycle detection
- QuickSelect algorithm
- XOR for missing numbers

### 4. ArrayTransformation.kt
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

**Key Concepts**:
- Functional programming concepts
- Index mapping formulas
- Matrix operations

### 5. ArrayValidation.kt
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

**Key Concepts**:
- Property validation patterns
- Mathematical sequence detection
- Pattern recognition

### 6. ArrayStatistics.kt
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

**Key Concepts**:
- Statistical measures
- Frequency analysis
- Mathematical formulas

### 7. ArrayManipulation.kt
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

**Key Concepts**:
- Element manipulation
- Array shifting
- In-place vs new array approaches

## Usage Examples

```kotlin
// Array Rotation
val arr = intArrayOf(1, 2, 3, 4, 5)
ArrayRotation.rotateLeft(arr, 2) // [3, 4, 5, 1, 2]

// Array Searching
val sortedArr = intArrayOf(1, 2, 3, 4, 5, 6, 7)
val index = ArraySearching.binarySearch(sortedArr, 5) // 4

// Array Validation
val palindrome = intArrayOf(1, 2, 2, 1)
val isPal = ArrayValidation.isArrayPalindrome(palindrome) // true

// Array Statistics
val statsArr = intArrayOf(1, 2, 2, 3, 3, 3, 4)
val mostFreq = ArrayStatistics.findMostFrequent(statsArr) // 3

// Array Transformation
val matrix = arrayOf(intArrayOf(1, 2), intArrayOf(3, 4))
val flattened = ArrayTransformation.flatten2DArray(matrix) // [1, 2, 3, 4]
```

## Complexity Summary

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Linear Search | O(n) | O(1) |
| Binary Search | O(log n) | O(1) |
| Array Rotation | O(n) | O(1) |
| Array Reversal | O(n) | O(1) |
| Array Transformation | O(n) | O(n) |
| Array Validation | O(n) | O(1) |
| Array Statistics | O(n) | O(k) |
| Array Manipulation | O(n) | O(n) |

## Notes

- All algorithms are implemented as object functions for easy access
- Each algorithm includes detailed comments explaining the approach
- Time and space complexity are documented for each function
- Multiple approaches are provided where applicable
- Edge cases are handled appropriately
- Functions are designed to be reusable and well-documented

## Next Steps

This structure can be extended to other data structures like:
- Linked Lists
- Trees
- Graphs
- Stacks and Queues
- Hash Tables
- Heaps

Each following the same pattern for consistency and ease of navigation. 