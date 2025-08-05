package data_structure.heaps

import java.util.*

/**
 * HEAP OPERATIONS
 * 
 * This file contains standard operations on heaps including
 * adding, removing, peeking, and other basic heap operations.
 * 
 * HEAP PROPERTIES:
 * - Min-Heap: Parent node is always smaller than or equal to its children
 * - Max-Heap: Parent node is always larger than or equal to its children
 * - Complete Binary Tree: All levels are filled except possibly the last level
 * - Heapify: Process of converting a binary tree into a heap
 * 
 * IMPLEMENTATION:
 * - Uses Java's PriorityQueue which is implemented as a binary heap
 * - Default PriorityQueue is a min-heap
 * - Max-heap is created using a custom comparator
 */

object HeapOperations {
    
    /**
     * Adds an element to a min-heap
     * 
     * ALGORITHM:
     * 1. Add the new element to the end of the heap
     * 2. Bubble up the element to maintain heap property
     *    - Compare with parent and swap if necessary
     *    - Continue until heap property is satisfied
     * 
     * TIME COMPLEXITY: O(log n) - Height of the heap
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param heap The min-heap to add element to
     * @param element The element to add
     * @return true if element was added successfully
     */
    fun addToMinHeap(heap: PriorityQueue<Int>, element: Int): Boolean {
        return heap.add(element)
    }
    
    /**
     * Adds an element to a max-heap
     * 
     * ALGORITHM:
     * 1. Add the new element to the end of the heap
     * 2. Bubble up the element to maintain heap property
     *    - Compare with parent and swap if necessary
     *    - Continue until heap property is satisfied
     * 
     * TIME COMPLEXITY: O(log n) - Height of the heap
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param heap The max-heap to add element to
     * @param element The element to add
     * @return true if element was added successfully
     */
    fun addToMaxHeap(heap: PriorityQueue<Int>, element: Int): Boolean {
        return heap.add(element)
    }
    
    /**
     * Offers an element to a heap (alternative to add)
     * 
     * ALGORITHM:
     * Same as add() but returns false if capacity is exceeded
     * 1. Add the new element to the end of the heap
     * 2. Bubble up the element to maintain heap property
     * 
     * TIME COMPLEXITY: O(log n) - Height of the heap
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param heap The heap to offer element to
     * @param element The element to offer
     * @return true if element was offered successfully, false if capacity exceeded
     */
    fun offerToHeap(heap: PriorityQueue<Int>, element: Int): Boolean {
        return heap.offer(element)
    }
    
    /**
     * Peeks at the top element of a heap without removing it
     * 
     * ALGORITHM:
     * Simply returns the root element (top of heap)
     * - For min-heap: returns the smallest element
     * - For max-heap: returns the largest element
     * 
     * TIME COMPLEXITY: O(1) - Direct access to root
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param heap The heap to peek at
     * @return The top element or null if heap is empty
     */
    fun peekHeap(heap: PriorityQueue<Int>): Int? {
        return heap.peek()
    }
    
    /**
     * Polls (removes and returns) the top element from a heap
     * 
     * ALGORITHM:
     * 1. Remove the root element (top of heap)
     * 2. Replace root with the last element in the heap
     * 3. Bubble down the new root to maintain heap property
     *    - Compare with children and swap with smaller/larger child
     *    - Continue until heap property is satisfied
     * 
     * TIME COMPLEXITY: O(log n) - Height of the heap
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param heap The heap to poll from
     * @return The top element or null if heap is empty
     */
    fun pollHeap(heap: PriorityQueue<Int>): Int? {
        return heap.poll()
    }
    
    /**
     * Removes a specific element from a heap
     * 
     * ALGORITHM:
     * 1. Find the element in the heap (linear search)
     * 2. Replace it with the last element
     * 3. Remove the last element
     * 4. Bubble down the replacement element to maintain heap property
     * 
     * TIME COMPLEXITY: O(n) - Linear search to find element
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param heap The heap to remove element from
     * @param element The element to remove
     * @return true if element was found and removed, false otherwise
     */
    fun removeFromHeap(heap: PriorityQueue<Int>, element: Int): Boolean {
        return heap.remove(element)
    }
    
    /**
     * Checks if a heap contains a specific element
     * 
     * ALGORITHM:
     * Linear search through all elements in the heap
     * - Check each element until the target is found
     * - Return true if found, false if not found
     * 
     * TIME COMPLEXITY: O(n) - Linear search through all elements
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param heap The heap to search in
     * @param element The element to search for
     * @return true if element is found, false otherwise
     */
    fun containsInHeap(heap: PriorityQueue<Int>, element: Int): Boolean {
        return heap.contains(element)
    }
    
    /**
     * Gets the size of a heap
     * 
     * ALGORITHM:
     * Simply returns the number of elements in the heap
     * 
     * TIME COMPLEXITY: O(1) - Direct access to size property
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param heap The heap to get size of
     * @return Number of elements in the heap
     */
    fun getHeapSize(heap: PriorityQueue<Int>): Int {
        return heap.size
    }
    
    /**
     * Checks if a heap is empty
     * 
     * ALGORITHM:
     * Simply checks if the heap has zero elements
     * 
     * TIME COMPLEXITY: O(1) - Direct access to size property
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param heap The heap to check
     * @return true if heap is empty, false otherwise
     */
    fun isHeapEmpty(heap: PriorityQueue<Int>): Boolean {
        return heap.isEmpty()
    }
    
    /**
     * Clears all elements from a heap
     * 
     * ALGORITHM:
     * Removes all elements from the heap, making it empty
     * 
     * TIME COMPLEXITY: O(n) - Need to remove all n elements
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param heap The heap to clear
     */
    fun clearHeap(heap: PriorityQueue<Int>) {
        heap.clear()
    }
    
    /**
     * Converts a heap to a list
     * 
     * ALGORITHM:
     * Creates a new list containing all elements from the heap
     * Note: The list order is not guaranteed to be sorted
     * 
     * TIME COMPLEXITY: O(n) - Need to copy all n elements
     * SPACE COMPLEXITY: O(n) - Creates a new list with n elements
     * 
     * @param heap The heap to convert
     * @return List containing all heap elements
     */
    fun heapToList(heap: PriorityQueue<Int>): List<Int> {
        return heap.toList()
    }
    
    /**
     * Converts a heap to an array
     * 
     * ALGORITHM:
     * Creates a new array containing all elements from the heap
     * Note: The array order is not guaranteed to be sorted
     * 
     * TIME COMPLEXITY: O(n) - Need to copy all n elements
     * SPACE COMPLEXITY: O(n) - Creates a new array with n elements
     * 
     * @param heap The heap to convert
     * @return Array containing all heap elements
     */
    fun heapToArray(heap: PriorityQueue<Int>): Array<Int> {
        return heap.toTypedArray()
    }
    
    /**
     * Converts a heap to an IntArray
     * 
     * ALGORITHM:
     * Creates a new IntArray containing all elements from the heap
     * Note: The array order is not guaranteed to be sorted
     * 
     * TIME COMPLEXITY: O(n) - Need to copy all n elements
     * SPACE COMPLEXITY: O(n) - Creates a new IntArray with n elements
     * 
     * @param heap The heap to convert
     * @return IntArray containing all heap elements
     */
    fun heapToIntArray(heap: PriorityQueue<Int>): IntArray {
        return heap.toIntArray()
    }
    
    /**
     * Iterates over heap elements (order not guaranteed)
     * 
     * ALGORITHM:
     * Creates a list of all elements in the heap
     * Note: The order is not guaranteed to be sorted
     * 
     * TIME COMPLEXITY: O(n) - Need to copy all n elements
     * SPACE COMPLEXITY: O(n) - Creates a new list with n elements
     * 
     * @param heap The heap to iterate over
     * @return List of heap elements in arbitrary order
     */
    fun iterateHeap(heap: PriorityQueue<Int>): List<Int> {
        return heap.toList()
    }
    
    /**
     * Iterates over heap elements in sorted order (by polling)
     * 
     * ALGORITHM:
     * 1. Create a copy of the heap to avoid modifying the original
     * 2. Repeatedly poll elements from the heap
     * 3. Add each polled element to the result list
     * 4. Continue until heap is empty
     * 
     * TIME COMPLEXITY: O(n log n) - n poll operations, each taking O(log n)
     * SPACE COMPLEXITY: O(n) - Creates a copy of the heap and result list
     * 
     * @param heap The heap to iterate over
     * @return List of heap elements in sorted order
     */
    fun iterateHeapSorted(heap: PriorityQueue<Int>): List<Int> {
        val result = mutableListOf<Int>()
        val tempHeap = PriorityQueue(heap) // Create a copy
        
        while (tempHeap.isNotEmpty()) {
            result.add(tempHeap.poll())
        }
        return result
    }
    
    /**
     * Gets the top k elements from a heap
     * 
     * ALGORITHM:
     * 1. Create a copy of the heap to avoid modifying the original
     * 2. Poll k elements from the heap
     * 3. Add each polled element to the result list
     * 4. Return the k smallest elements (for min-heap) or k largest (for max-heap)
     * 
     * TIME COMPLEXITY: O(k log n) - k poll operations, each taking O(log n)
     * SPACE COMPLEXITY: O(n) - Creates a copy of the heap and result list
     * 
     * @param heap The heap to get top k elements from
     * @param k Number of top elements to retrieve
     * @return List of k top elements in sorted order
     */
    fun getTopKElements(heap: PriorityQueue<Int>, k: Int): List<Int> {
        val result = mutableListOf<Int>()
        val tempHeap = PriorityQueue(heap) // Create a copy
        
        repeat(k) {
            if (tempHeap.isNotEmpty()) {
                result.add(tempHeap.poll())
            }
        }
        return result
    }
    
    /**
     * Adds multiple elements to a heap
     * 
     * ALGORITHM:
     * 1. Add each element from the collection to the heap
     * 2. Each addition triggers heapify to maintain heap property
     * 
     * TIME COMPLEXITY: O(m log n) - m additions, each taking O(log n)
     *                  where m is the number of elements to add
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param heap The heap to add elements to
     * @param elements Collection of elements to add
     * @return true if all elements were added successfully
     */
    fun addAllToHeap(heap: PriorityQueue<Int>, elements: Collection<Int>): Boolean {
        return heap.addAll(elements)
    }
    
    /**
     * Removes all elements that match a condition
     * 
     * ALGORITHM:
     * 1. Iterate through all elements in the heap
     * 2. Remove elements that satisfy the predicate
     * 3. Re-heapify after each removal to maintain heap property
     * 
     * TIME COMPLEXITY: O(n²) - n elements, each removal takes O(n)
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param heap The heap to remove elements from
     * @param predicate Function that returns true for elements to remove
     * @return true if any elements were removed
     */
    fun removeIfFromHeap(heap: PriorityQueue<Int>, predicate: (Int) -> Boolean): Boolean {
        return heap.removeIf(predicate)
    }
    
    /**
     * Retains only elements that match a condition
     * 
     * ALGORITHM:
     * 1. Remove all elements that are not in the specified collection
     * 2. Re-heapify after each removal to maintain heap property
     * 
     * TIME COMPLEXITY: O(n²) - n elements, each removal takes O(n)
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param heap The heap to modify
     * @param elements Collection of elements to retain
     * @return true if the heap was modified
     */
    fun retainAllInHeap(heap: PriorityQueue<Int>, elements: Collection<Int>): Boolean {
        return heap.retainAll(elements)
    }
    
    /**
     * Checks if heap contains all elements from a collection
     * 
     * ALGORITHM:
     * 1. Check each element in the collection
     * 2. Use linear search to find each element in the heap
     * 3. Return true only if all elements are found
     * 
     * TIME COMPLEXITY: O(m * n) - m elements in collection, each search takes O(n)
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param heap The heap to check
     * @param elements Collection of elements to check for
     * @return true if all elements are found in the heap
     */
    fun containsAllInHeap(heap: PriorityQueue<Int>, elements: Collection<Int>): Boolean {
        return heap.containsAll(elements)
    }
    
    /**
     * Removes all elements from a collection from the heap
     * 
     * ALGORITHM:
     * 1. Remove each element from the collection from the heap
     * 2. Re-heapify after each removal to maintain heap property
     * 
     * TIME COMPLEXITY: O(m * n) - m elements in collection, each removal takes O(n)
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param heap The heap to remove elements from
     * @param elements Collection of elements to remove
     * @return true if any elements were removed
     */
    fun removeAllFromHeap(heap: PriorityQueue<Int>, elements: Collection<Int>): Boolean {
        return heap.removeAll(elements)
    }
    
    /**
     * Demonstrates standard heap operations
     */
    fun demonstrateHeapOperations() {
        println("=== HEAP OPERATIONS DEMONSTRATION ===\n")
        
        // 1. Basic operations
        println("1. BASIC HEAP OPERATIONS")
        val minHeap = PriorityQueue<Int>()
        val maxHeap = PriorityQueue<Int>(compareByDescending { it })
        
        // Adding elements
        addToMinHeap(minHeap, 4)
        addToMinHeap(minHeap, 2)
        addToMinHeap(minHeap, 7)
        addToMaxHeap(maxHeap, 4)
        addToMaxHeap(maxHeap, 2)
        addToMaxHeap(maxHeap, 7)
        
        println("Min-heap after adding elements: $minHeap")
        println("Max-heap after adding elements: $maxHeap")
        println()
        
        // 2. Peeking and polling
        println("2. PEEKING AND POLLING")
        val minTop = peekHeap(minHeap)
        val maxTop = peekHeap(maxHeap)
        println("Min-heap top element: $minTop")
        println("Max-heap top element: $maxTop")
        
        val minPolled = pollHeap(minHeap)
        val maxPolled = pollHeap(maxHeap)
        println("Polled from min-heap: $minPolled")
        println("Polled from max-heap: $maxPolled")
        println("Min-heap after polling: $minHeap")
        println("Max-heap after polling: $maxHeap")
        println()
        
        // 3. Size and emptiness checks
        println("3. SIZE AND EMPTINESS CHECKS")
        println("Min-heap size: ${getHeapSize(minHeap)}")
        println("Max-heap size: ${getHeapSize(maxHeap)}")
        println("Is min-heap empty: ${isHeapEmpty(minHeap)}")
        println("Is max-heap empty: ${isHeapEmpty(maxHeap)}")
        println()
        
        // 4. Contains and remove operations
        println("4. CONTAINS AND REMOVE OPERATIONS")
        println("Min-heap contains 4: ${containsInHeap(minHeap, 4)}")
        println("Min-heap contains 10: ${containsInHeap(minHeap, 10)}")
        
        val removedSeven = removeFromHeap(minHeap, 7)
        println("Removed 7 from min-heap: $removedSeven")
        println("Min-heap after removing 7: $minHeap")
        println()
        
        // 5. Conversion operations
        println("5. CONVERSION OPERATIONS")
        val list = heapToList(minHeap)
        val array = heapToArray(minHeap)
        val intArray = heapToIntArray(minHeap)
        println("Heap as list: $list")
        println("Heap as array: ${array.contentToString()}")
        println("Heap as IntArray: ${intArray.contentToString()}")
        println()
        
        // 6. Iteration operations
        println("6. ITERATION OPERATIONS")
        addToMinHeap(minHeap, 1)
        addToMinHeap(minHeap, 9)
        addToMinHeap(minHeap, 3)
        
        val heapElements = iterateHeap(minHeap)
        val sortedElements = iterateHeapSorted(minHeap)
        println("Heap elements (unordered): $heapElements")
        println("Heap elements (sorted): $sortedElements")
        println()
        
        // 7. Top K elements
        println("7. TOP K ELEMENTS")
        val top3Elements = getTopKElements(minHeap, 3)
        println("Top 3 elements from min-heap: $top3Elements")
        println()
        
        // 8. Bulk operations
        println("8. BULK OPERATIONS")
        val newElements = listOf(10, 20, 30)
        addAllToHeap(minHeap, newElements)
        println("Min-heap after adding bulk elements: $minHeap")
        
        val elementsToRemove = listOf(10, 20)
        removeAllFromHeap(minHeap, elementsToRemove)
        println("Min-heap after removing bulk elements: $minHeap")
        println()
        
        // 9. Clear operation
        println("9. CLEAR OPERATION")
        clearHeap(minHeap)
        println("Min-heap after clearing: $minHeap")
        println("Is min-heap empty after clearing: ${isHeapEmpty(minHeap)}")
        println()
        
        println("=== HEAP OPERATIONS DEMONSTRATION COMPLETE ===\n")
    }
}
