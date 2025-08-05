# Heap Data Structure

This directory contains the heap data structure implementation broken down into multiple files for better organization and navigation.

## File Structure

### 1. `HeapCreation.kt`
Contains functions for creating different types of heaps:
- **Basic Creation**: `createMinHeap()`, `createMaxHeap()`
- **From Collections**: `createMinHeapFromList()`, `createMaxHeapFromList()`
- **From Arrays**: `createMinHeapFromArray()`, `createMaxHeapFromArray()`
- **With Capacity**: `createMinHeapWithCapacity()`, `createMaxHeapWithCapacity()`
- **Custom Objects**: `createObjectHeap()`, `createObjectMaxHeap()`
- **From Maps**: `createHeapFromMap()`, `createMaxHeapFromMap()`
- **Pair Heaps**: `createPairHeap()`, `createPairMaxHeap()`

### 2. `HeapOperations.kt`
Contains standard operations on heaps:
- **Basic Operations**: `addToMinHeap()`, `addToMaxHeap()`, `offerToHeap()`
- **Access Operations**: `peekHeap()`, `pollHeap()`
- **Modification**: `removeFromHeap()`, `clearHeap()`
- **Query Operations**: `containsInHeap()`, `getHeapSize()`, `isHeapEmpty()`
- **Conversion**: `heapToList()`, `heapToArray()`, `heapToIntArray()`
- **Iteration**: `iterateHeap()`, `iterateHeapSorted()`
- **Bulk Operations**: `addAllToHeap()`, `removeAllFromHeap()`, `removeIfFromHeap()`

### 3. `HeapAlgorithms.kt`
Contains algorithms that use heaps:
- **Sorting**: `heapSort()`, `heapSortAscending()`
- **Selection**: `findKthLargest()`, `findKthSmallest()`
- **Top K**: `findTopKLargest()`, `findTopKSmallest()`
- **Median**: `MedianFinder` class
- **Sliding Window**: `maxSlidingWindow()`
- **Merging**: `mergeKSortedArrays()`
- **Closest Elements**: `findKClosestElements()`
- **Task Scheduling**: `leastInterval()`
- **Rope Connection**: `connectRopes()`

### 4. `HeapDemo.kt`
Coordinates all heap functionality for demonstration:
- `demonstrateHeapAlgorithms()` - Shows all heap capabilities

## Quick Reference

### Heap Types
- **Min-Heap**: Smallest element at the top
- **Max-Heap**: Largest element at the top
- **Custom Heap**: Using custom comparators

### Common Operations
```kotlin
// Creation
val minHeap = PriorityQueue<Int>()
val maxHeap = PriorityQueue<Int>(compareByDescending { it })

// Basic Operations
heap.add(element)      // Add element
heap.offer(element)    // Add element (alternative)
heap.peek()           // View top element
heap.poll()           // Remove and return top element
heap.remove(element)  // Remove specific element
heap.clear()          // Remove all elements

// Query Operations
heap.size             // Number of elements
heap.isEmpty()        // Check if empty
heap.contains(element) // Check if contains element
```

### Time Complexity
- **Insertion**: O(log n)
- **Deletion**: O(log n)
- **Peek**: O(1)
- **Heapify**: O(n)
- **Heap Sort**: O(n log n)

### Space Complexity
- **Storage**: O(n)
- **Auxiliary Space**: O(1) for most operations

## Usage Example

```kotlin
// Create and use a min-heap
val minHeap = HeapCreation.createMinHeap()
HeapOperations.addToMinHeap(minHeap, 5)
HeapOperations.addToMinHeap(minHeap, 2)
HeapOperations.addToMinHeap(minHeap, 8)

val smallest = HeapOperations.peekHeap(minHeap) // 2
val sorted = HeapOperations.iterateHeapSorted(minHeap) // [2, 5, 8]

// Use heap algorithms
val arr = intArrayOf(3, 1, 4, 1, 5, 9, 2, 6)
val kthLargest = HeapAlgorithms.findKthLargest(arr, 3) // 5
val sortedArray = HeapAlgorithms.heapSort(arr)
``` 