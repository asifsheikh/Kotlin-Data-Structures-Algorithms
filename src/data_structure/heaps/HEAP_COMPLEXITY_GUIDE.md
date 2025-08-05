# Heap Operations & Algorithms Complexity Guide

This document provides a comprehensive reference for time and space complexity of all heap operations and algorithms implemented in this project.

## 📊 **HEAP OPERATIONS COMPLEXITY**

### **Basic Operations**

| Operation | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `add()` / `offer()` | O(log n) | O(1) | Add element and bubble up |
| `peek()` | O(1) | O(1) | View top element without removal |
| `poll()` / `remove()` | O(log n) | O(1) | Remove top element and bubble down |
| `size()` | O(1) | O(1) | Get number of elements |
| `isEmpty()` | O(1) | O(1) | Check if heap is empty |
| `clear()` | O(n) | O(1) | Remove all elements |

### **Search & Removal Operations**

| Operation | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `contains()` | O(n) | O(1) | Linear search through heap |
| `remove(element)` | O(n) | O(1) | Find and remove specific element |
| `removeIf()` | O(n²) | O(1) | Remove elements matching predicate |
| `removeAll()` | O(m × n) | O(1) | Remove all elements from collection |
| `retainAll()` | O(n²) | O(1) | Keep only elements in collection |

### **Conversion Operations**

| Operation | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `toList()` | O(n) | O(n) | Convert heap to list |
| `toArray()` | O(n) | O(n) | Convert heap to array |
| `toIntArray()` | O(n) | O(n) | Convert heap to IntArray |

### **Bulk Operations**

| Operation | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `addAll()` | O(m log n) | O(1) | Add m elements to heap |
| `containsAll()` | O(m × n) | O(1) | Check if heap contains all elements |

### **Iteration Operations**

| Operation | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `iterateHeap()` | O(n) | O(n) | Get elements in arbitrary order |
| `iterateHeapSorted()` | O(n log n) | O(n) | Get elements in sorted order |
| `getTopKElements()` | O(k log n) | O(n) | Get k top elements |

---

## 🧮 **HEAP ALGORITHMS COMPLEXITY**

### **Sorting Algorithms**

| Algorithm | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `heapSort()` | O(n log n) | O(n) | Sort in descending order |
| `heapSortAscending()` | O(n log n) | O(n) | Sort in ascending order |

**Breakdown:**
- Building heap: O(n log n) - n additions, each O(log n)
- Extracting elements: O(n log n) - n polls, each O(log n)

### **Selection Algorithms**

| Algorithm | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `findKthLargest()` | O(n log k) | O(k) | Find kth largest element |
| `findKthSmallest()` | O(n log k) | O(k) | Find kth smallest element |
| `findTopKLargest()` | O(n log k + k log k) | O(k) | Find k largest elements |
| `findTopKSmallest()` | O(n log k + k log k) | O(k) | Find k smallest elements |

**Intuition:**
- Use min-heap of size k for k largest elements
- Use max-heap of size k for k smallest elements
- Heap size limited to k, so operations are O(log k)

### **Median Finding**

| Algorithm | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `MedianFinder.addNum()` | O(log n) | O(n) | Add number to median finder |
| `MedianFinder.findMedian()` | O(1) | O(n) | Get current median |

**Implementation:**
- Uses two heaps: max-heap (left half) + min-heap (right half)
- Maintains balance: max-heap.size ≥ min-heap.size

### **Advanced Algorithms**

| Algorithm | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| `maxSlidingWindow()` | O(n) | O(k) | Max in each sliding window |
| `mergeKSortedArrays()` | O(N log k) | O(k) | Merge k sorted arrays |
| `findKClosestElements()` | O(n log k + k log k) | O(k) | K closest to target |
| `leastInterval()` | O(n) | O(1) | Task scheduler with cooldown |
| `connectRopes()` | O(n log n) | O(n) | Minimum cost to connect ropes |

**Key Insights:**
- `maxSlidingWindow`: Uses monotonic deque, not heap
- `mergeKSortedArrays`: N = total elements, k = number of arrays
- `leastInterval`: O(n log 26) = O(n) since only 26 task types

---

## 🎯 **OPTIMIZATION STRATEGIES**

### **For Top-K Problems**
- Use heap of size k instead of full heap
- Reduces space complexity from O(n) to O(k)
- Reduces time complexity from O(n log n) to O(n log k)

### **For Median Finding**
- Use two heaps instead of sorting
- O(log n) insertion vs O(n log n) sorting
- O(1) median access vs O(n) sorting

### **For Multiple Arrays**
- Use heap to track smallest element from each array
- Avoid merging arrays one by one
- Efficient for large number of arrays

### **For Sliding Window**
- Use deque instead of heap for maximum
- O(n) vs O(n log k) time complexity
- Better for large window sizes

---

## 📝 **COMMON PATTERNS**

### **Min-Heap for Largest Elements**
```kotlin
val minHeap = PriorityQueue<Int>()
for (num in nums) {
    minHeap.add(num)
    if (minHeap.size > k) {
        minHeap.poll() // Remove smallest
    }
}
// Root contains kth largest
```

### **Max-Heap for Smallest Elements**
```kotlin
val maxHeap = PriorityQueue<Int>(compareByDescending { it })
for (num in nums) {
    maxHeap.add(num)
    if (maxHeap.size > k) {
        maxHeap.poll() // Remove largest
    }
}
// Root contains kth smallest
```

### **Two Heaps for Median**
```kotlin
val maxHeap = PriorityQueue<Int>(compareByDescending { it }) // left half
val minHeap = PriorityQueue<Int>() // right half
// Maintain balance: maxHeap.size >= minHeap.size
```

### **Heap for Custom Objects**
```kotlin
val heap = PriorityQueue<MyObject> { a, b -> a.priority.compareTo(b.priority) }
```

---

## ⚡ **PERFORMANCE TIPS**

1. **Choose Right Heap Type:**
   - Min-heap for largest elements
   - Max-heap for smallest elements
   - Two heaps for median

2. **Limit Heap Size:**
   - For top-k problems, limit heap to size k
   - Reduces both time and space complexity

3. **Avoid Unnecessary Operations:**
   - Don't sort heap contents unless required
   - Use peek() instead of poll() when possible

4. **Consider Alternatives:**
   - QuickSelect for single kth element (O(n) average)
   - Deque for sliding window maximum
   - Bucket sort for small integer ranges

5. **Memory Management:**
   - Clear heaps when no longer needed
   - Use primitive arrays when possible
   - Consider object pooling for frequent operations 