package data_structure.queues

import java.util.PriorityQueue

/**
 * QUEUE OPERATIONS
 * 
 * This file contains standard operations on queues including
 * enqueuing, dequeuing, peeking, and other basic queue operations.
 * 
 * COMMON QUEUE OPERATIONS:
 * - Enqueue: Add element to rear of queue
 * - Dequeue: Remove and return front element
 * - Peek: View front element without removing
 * - Size: Get number of elements
 * - Empty: Check if queue is empty
 * - Clear: Remove all elements
 */

object QueueOperations {
    
    /**
     * Enqueues an element to the rear of the queue
     * 
     * ALGORITHM:
     * 1. Add element to the end of the ArrayDeque
     * 2. Element becomes the new rear of the queue
     * 
     * TIME COMPLEXITY: O(1) - Constant time addition
     * SPACE COMPLEXITY: O(1) - Only stores one additional element
     * 
     * @param queue The queue to enqueue element into
     * @param element The element to enqueue
     */
    fun <T> enqueue(queue: kotlin.collections.ArrayDeque<T>, element: T) {
        queue.addLast(element)
    }
    
    /**
     * Enqueues an element to the rear of the priority queue
     * 
     * ALGORITHM:
     * 1. Add element to the PriorityQueue
     * 2. Element is automatically positioned based on priority
     * 
     * TIME COMPLEXITY: O(log n) - Heap insertion
     * SPACE COMPLEXITY: O(1) - Only stores one additional element
     * 
     * @param queue The priority queue to enqueue element into
     * @param element The element to enqueue
     */
    fun <T> enqueue(queue: java.util.PriorityQueue<T>, element: T) {
        queue.add(element)
    }
    
    /**
     * Dequeues (removes and returns) the front element from the queue
     * 
     * ALGORITHM:
     * 1. Remove and return the first element from ArrayDeque
     * 2. Next element becomes the new front
     * 
     * TIME COMPLEXITY: O(1) - Constant time removal
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param queue The queue to dequeue from
     * @return The front element, or null if queue is empty
     */
    fun <T> dequeue(queue: kotlin.collections.ArrayDeque<T>): T? {
        return if (queue.isNotEmpty()) queue.removeFirst() else null
    }
    
    /**
     * Dequeues (removes and returns) the highest priority element
     * 
     * ALGORITHM:
     * 1. Remove and return the root element from PriorityQueue
     * 2. Heap is automatically rebalanced
     * 
     * TIME COMPLEXITY: O(log n) - Heap extraction
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param queue The priority queue to dequeue from
     * @return The highest priority element, or null if queue is empty
     */
    fun <T> dequeue(queue: java.util.PriorityQueue<T>): T? {
        return if (queue.isNotEmpty()) queue.poll() else null
    }
    
    /**
     * Peeks at the front element without removing it
     * 
     * ALGORITHM:
     * 1. Return the first element from ArrayDeque without removing
     * 2. Queue remains unchanged
     * 
     * TIME COMPLEXITY: O(1) - Constant time access
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param queue The queue to peek at
     * @return The front element, or null if queue is empty
     */
    fun <T> peek(queue: kotlin.collections.ArrayDeque<T>): T? {
        return queue.firstOrNull()
    }
    
    /**
     * Peeks at the highest priority element without removing it
     * 
     * ALGORITHM:
     * 1. Return the root element from PriorityQueue without removing
     * 2. Queue remains unchanged
     * 
     * TIME COMPLEXITY: O(1) - Constant time access
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param queue The priority queue to peek at
     * @return The highest priority element, or null if queue is empty
     */
    fun <T> peek(queue: java.util.PriorityQueue<T>): T? {
        return queue.peek()
    }
    
    /**
     * Gets the size of the queue
     * 
     * ALGORITHM:
     * 1. Return the size property of the queue
     * 
     * TIME COMPLEXITY: O(1) - Direct access to size property
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param queue The queue to get size of
     * @return Number of elements in the queue
     */
    fun <T> size(queue: kotlin.collections.ArrayDeque<T>): Int {
        return queue.size
    }
    
    /**
     * Gets the size of the priority queue
     * 
     * ALGORITHM:
     * 1. Return the size property of the PriorityQueue
     * 
     * TIME COMPLEXITY: O(1) - Direct access to size property
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param queue The priority queue to get size of
     * @return Number of elements in the priority queue
     */
    fun <T> size(queue: java.util.PriorityQueue<T>): Int {
        return queue.size
    }
    
    /**
     * Checks if the queue is empty
     * 
     * ALGORITHM:
     * 1. Check if size is zero
     * 
     * TIME COMPLEXITY: O(1) - Direct access to size property
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param queue The queue to check
     * @return true if queue is empty, false otherwise
     */
    fun <T> isEmpty(queue: kotlin.collections.ArrayDeque<T>): Boolean {
        return queue.isEmpty()
    }
    
    /**
     * Checks if the priority queue is empty
     * 
     * ALGORITHM:
     * 1. Check if size is zero
     * 
     * TIME COMPLEXITY: O(1) - Direct access to size property
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param queue The priority queue to check
     * @return true if priority queue is empty, false otherwise
     */
    fun <T> isEmpty(queue: java.util.PriorityQueue<T>): Boolean {
        return queue.isEmpty()
    }
    
    /**
     * Checks if the queue is not empty
     * 
     * ALGORITHM:
     * 1. Check if size is greater than zero
     * 
     * TIME COMPLEXITY: O(1) - Direct access to size property
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param queue The queue to check
     * @return true if queue is not empty, false otherwise
     */
    fun <T> isNotEmpty(queue: kotlin.collections.ArrayDeque<T>): Boolean {
        return queue.isNotEmpty()
    }
    
    /**
     * Checks if the priority queue is not empty
     * 
     * ALGORITHM:
     * 1. Check if size is greater than zero
     * 
     * TIME COMPLEXITY: O(1) - Direct access to size property
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param queue The priority queue to check
     * @return true if priority queue is not empty, false otherwise
     */
    fun <T> isNotEmpty(queue: java.util.PriorityQueue<T>): Boolean {
        return queue.isNotEmpty()
    }
    
    /**
     * Clears all elements from the queue
     * 
     * ALGORITHM:
     * 1. Remove all elements from ArrayDeque
     * 
     * TIME COMPLEXITY: O(n) - Need to remove all n elements
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param queue The queue to clear
     */
    fun <T> clear(queue: kotlin.collections.ArrayDeque<T>) {
        queue.clear()
    }
    
    /**
     * Clears all elements from the priority queue
     * 
     * ALGORITHM:
     * 1. Remove all elements from PriorityQueue
     * 
     * TIME COMPLEXITY: O(n) - Need to remove all n elements
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param queue The priority queue to clear
     */
    fun <T> clear(queue: java.util.PriorityQueue<T>) {
        queue.clear()
    }
    
    /**
     * Checks if the queue contains a specific element
     * 
     * ALGORITHM:
     * 1. Search through all elements in the queue
     * 2. Return true if element is found, false otherwise
     * 
     * TIME COMPLEXITY: O(n) - Linear search through all elements
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param queue The queue to search in
     * @param element The element to search for
     * @return true if element is found, false otherwise
     */
    fun <T> contains(queue: kotlin.collections.ArrayDeque<T>, element: T): Boolean {
        return queue.contains(element)
    }
    
    /**
     * Checks if the priority queue contains a specific element
     * 
     * ALGORITHM:
     * 1. Search through all elements in the priority queue
     * 2. Return true if element is found, false otherwise
     * 
     * TIME COMPLEXITY: O(n) - Linear search through all elements
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param queue The priority queue to search in
     * @param element The element to search for
     * @return true if element is found, false otherwise
     */
    fun <T> contains(queue: java.util.PriorityQueue<T>, element: T): Boolean {
        return queue.contains(element)
    }
    
    /**
     * Removes a specific element from the queue
     * 
     * ALGORITHM:
     * 1. Find the element in the queue
     * 2. Remove the first occurrence found
     * 
     * TIME COMPLEXITY: O(n) - Linear search to find element
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param queue The queue to remove element from
     * @param element The element to remove
     * @return true if element was found and removed, false otherwise
     */
    fun <T> remove(queue: kotlin.collections.ArrayDeque<T>, element: T): Boolean {
        return queue.remove(element)
    }
    
    /**
     * Removes a specific element from the priority queue
     * 
     * ALGORITHM:
     * 1. Find the element in the priority queue
     * 2. Remove the element and rebalance heap
     * 
     * TIME COMPLEXITY: O(n) - Linear search to find element
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param queue The priority queue to remove element from
     * @param element The element to remove
     * @return true if element was found and removed, false otherwise
     */
    fun <T> remove(queue: java.util.PriorityQueue<T>, element: T): Boolean {
        return queue.remove(element)
    }
    
    /**
     * Converts the queue to a list
     * 
     * ALGORITHM:
     * 1. Create a new list containing all elements from the queue
     * 2. Order is maintained (front to rear)
     * 
     * TIME COMPLEXITY: O(n) - Need to copy all n elements
     * SPACE COMPLEXITY: O(n) - Creates a new list with n elements
     * 
     * @param queue The queue to convert
     * @return List containing all queue elements (front to rear)
     */
    fun <T> toList(queue: kotlin.collections.ArrayDeque<T>): List<T> {
        return queue.toList()
    }
    
    /**
     * Converts the priority queue to a list
     * 
     * ALGORITHM:
     * 1. Create a new list containing all elements from the priority queue
     * 2. Order is based on priority (highest priority first)
     * 
     * TIME COMPLEXITY: O(n log n) - Need to extract all n elements
     * SPACE COMPLEXITY: O(n) - Creates a new list with n elements
     * 
     * @param queue The priority queue to convert
     * @return List containing all priority queue elements (sorted by priority)
     */
    fun <T> toList(queue: java.util.PriorityQueue<T>): List<T> {
        val result = mutableListOf<T>()
        while (queue.isNotEmpty()) {
            result.add(queue.poll()!!)
        }
        return result
    }
    
    /**
     * Converts the queue to an array
     * 
     * ALGORITHM:
     * 1. Create a new array containing all elements from the queue
     * 2. Order is maintained (front to rear)
     * 
     * TIME COMPLEXITY: O(n) - Need to copy all n elements
     * SPACE COMPLEXITY: O(n) - Creates a new array with n elements
     * 
     * @param queue The queue to convert
     * @return Array containing all queue elements (front to rear)
     */
    fun <T> toArray(queue: kotlin.collections.ArrayDeque<T>): Array<Any?> {
        return queue.toArray()
    }
    
    /**
     * Adds multiple elements to the queue
     * 
     * ALGORITHM:
     * 1. Add each element from the collection to the queue
     * 2. Elements are added in order (first element becomes front)
     * 
     * TIME COMPLEXITY: O(m) - m elements to add
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param queue The queue to add elements to
     * @param elements Collection of elements to add
     */
    fun <T> addAll(queue: kotlin.collections.ArrayDeque<T>, elements: Collection<T>) {
        queue.addAll(elements)
    }
    
    /**
     * Adds multiple elements to the priority queue
     * 
     * ALGORITHM:
     * 1. Add each element from the collection to the priority queue
     * 2. Elements are automatically positioned based on priority
     * 
     * TIME COMPLEXITY: O(m log n) - m elements to add, each takes O(log n)
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param queue The priority queue to add elements to
     * @param elements Collection of elements to add
     */
    fun <T> addAll(queue: java.util.PriorityQueue<T>, elements: Collection<T>) {
        queue.addAll(elements)
    }
    
    /**
     * Gets the element at a specific position from the front
     * 
     * ALGORITHM:
     * 1. Return element at the specified position from the beginning
     * 
     * TIME COMPLEXITY: O(1) - Direct access to element
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param queue The queue to get element from
     * @param position Position from front (0 = front, 1 = second from front, etc.)
     * @return Element at the specified position, or null if position is invalid
     */
    fun <T> getFromFront(queue: kotlin.collections.ArrayDeque<T>, position: Int): T? {
        return if (position >= 0 && position < queue.size) {
            queue.elementAt(position)
        } else null
    }
    
    /**
     * Gets the element at a specific position from the rear
     * 
     * ALGORITHM:
     * 1. Calculate position from the end of ArrayDeque
     * 2. Return element at that position
     * 
     * TIME COMPLEXITY: O(1) - Direct access to element
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param queue The queue to get element from
     * @param position Position from rear (0 = rear, 1 = second from rear, etc.)
     * @return Element at the specified position, or null if position is invalid
     */
    fun <T> getFromRear(queue: kotlin.collections.ArrayDeque<T>, position: Int): T? {
        return if (position >= 0 && position < queue.size) {
            queue.elementAt(queue.size - 1 - position)
        } else null
    }
    
    /**
     * Iterates over the queue from front to rear
     * 
     * ALGORITHM:
     * 1. Iterate through elements in natural order
     * 
     * TIME COMPLEXITY: O(n) - Visit all n elements
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param queue The queue to iterate over
     * @param action Function to perform on each element
     */
    fun <T> iterateFrontToRear(queue: kotlin.collections.ArrayDeque<T>, action: (T) -> Unit) {
        for (element in queue) {
            action(element)
        }
    }
    
    /**
     * Iterates over the queue from rear to front
     * 
     * ALGORITHM:
     * 1. Iterate through elements in reverse order
     * 
     * TIME COMPLEXITY: O(n) - Visit all n elements
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param queue The queue to iterate over
     * @param action Function to perform on each element
     */
    fun <T> iterateRearToFront(queue: kotlin.collections.ArrayDeque<T>, action: (T) -> Unit) {
        for (element in queue.reversed()) {
            action(element)
        }
    }
    
    /**
     * Creates a copy of the queue
     * 
     * ALGORITHM:
     * 1. Create a new ArrayDeque with all elements from the original
     * 
     * TIME COMPLEXITY: O(n) - Need to copy all n elements
     * SPACE COMPLEXITY: O(n) - Creates a new queue with n elements
     * 
     * @param queue The queue to copy
     * @return A new queue with the same elements
     */
    fun <T> copy(queue: kotlin.collections.ArrayDeque<T>): kotlin.collections.ArrayDeque<T> {
        return kotlin.collections.ArrayDeque(queue)
    }
    
    /**
     * Creates a copy of the priority queue
     * 
     * ALGORITHM:
     * 1. Create a new PriorityQueue with all elements from the original
     * 
     * TIME COMPLEXITY: O(n) - Need to copy all n elements
     * SPACE COMPLEXITY: O(n) - Creates a new priority queue with n elements
     * 
     * @param queue The priority queue to copy
     * @return A new priority queue with the same elements
     */
    fun <T> copy(queue: java.util.PriorityQueue<T>): java.util.PriorityQueue<T> {
        return java.util.PriorityQueue(queue)
    }
    
    /**
     * Demonstrates standard queue operations
     */
    fun demonstrateQueueOperations() {
        println("=== QUEUE OPERATIONS DEMONSTRATION ===\n")
        
        // Create queues for demonstration
        val queue = QueueCreation.createQueueFromList(listOf(1, 2, 3, 4, 5))
        val priorityQueue = QueueCreation.createPriorityQueue<Int>()
        priorityQueue.addAll(listOf(5, 2, 8, 1, 9))
        
        // 1. Basic operations
        println("1. BASIC OPERATIONS")
        println("Queue: $queue")
        println("Priority Queue: $priorityQueue")
        println("Queue size: ${size(queue)}")
        println("Priority queue size: ${size(priorityQueue)}")
        println("Queue is empty: ${isEmpty(queue)}")
        println("Priority queue is empty: ${isEmpty(priorityQueue)}")
        println()
        
        // 2. Enqueue and dequeue operations
        println("2. ENQUEUE AND DEQUEUE OPERATIONS")
        enqueue(queue, 6)
        println("After enqueuing 6: $queue")
        val dequeued = dequeue(queue)
        println("Dequeued element: $dequeued")
        println("Queue after dequeue: $queue")
        
        enqueue(priorityQueue, 3)
        println("After enqueuing 3 to priority queue: $priorityQueue")
        val priorityDequeued = dequeue(priorityQueue)
        println("Dequeued from priority queue: $priorityDequeued")
        println()
        
        // 3. Peek operations
        println("3. PEEK OPERATIONS")
        println("Front of queue: ${peek(queue)}")
        println("Highest priority: ${peek(priorityQueue)}")
        println()
        
        // 4. Search operations
        println("4. SEARCH OPERATIONS")
        println("Queue contains 3: ${contains(queue, 3)}")
        println("Queue contains 10: ${contains(queue, 10)}")
        val removed = remove(queue, 3)
        println("Removed 3: $removed")
        println("Queue after removal: $queue")
        println()
        
        // 5. Access operations
        println("5. ACCESS OPERATIONS")
        println("Element at position 0 from front: ${getFromFront(queue, 0)}")
        println("Element at position 1 from front: ${getFromFront(queue, 1)}")
        println("Element at position 0 from rear: ${getFromRear(queue, 0)}")
        println("Element at position 1 from rear: ${getFromRear(queue, 1)}")
        println()
        
        // 6. Conversion operations
        println("6. CONVERSION OPERATIONS")
        val asList = toList(queue)
        println("Queue as list: $asList")
        val priorityAsList = toList(priorityQueue)
        println("Priority queue as list: $priorityAsList")
        println()
        
        // 7. Iteration operations
        println("7. ITERATION OPERATIONS")
        print("Iterating front to rear: ")
        iterateFrontToRear(queue) { print("$it ") }
        println()
        print("Iterating rear to front: ")
        iterateRearToFront(queue) { print("$it ") }
        println()
        println()
        
        // 8. Copy operations
        println("8. COPY OPERATIONS")
        val copiedQueue = copy(queue)
        println("Copied queue: $copiedQueue")
        val copiedPriorityQueue = copy(priorityQueue)
        println("Copied priority queue: $copiedPriorityQueue")
    println()

        // 9. Bulk operations
        println("9. BULK OPERATIONS")
        addAll(queue, listOf(7, 8, 9))
        println("After adding [7,8,9]: $queue")
        clear(queue)
        println("After clearing: $queue")
        println("Is empty: ${isEmpty(queue)}")
        println()
        
        println("=== QUEUE OPERATIONS DEMONSTRATION COMPLETE ===\n")
    }
}
