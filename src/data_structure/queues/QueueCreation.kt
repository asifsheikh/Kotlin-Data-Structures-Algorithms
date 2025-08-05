package data_structure.queues

import java.util.PriorityQueue
import java.util.Collections

/**
 * QUEUE CREATION
 * 
 * This file contains functions for creating different types of queues
 * and various queue creation patterns.
 * 
 * QUEUE PROPERTIES:
 * - FIFO (First In, First Out) data structure
 * - Elements are added at the rear and removed from the front
 * - Efficient for operations at both ends
 * - Used for task scheduling, breadth-first search, etc.
 * 
 * IMPLEMENTATION:
 * - Uses ArrayDeque for efficient operations
 * - Supports generic types
 * - Provides both mutable and immutable variants
 */

object QueueCreation {
    
    /**
     * Creates a basic queue using ArrayDeque
     * 
     * ALGORITHM:
     * 1. Initialize an empty ArrayDeque
     * 2. Use addLast() for enqueue operations
     * 3. Use removeFirst() for dequeue operations
     * 
     * TIME COMPLEXITY: O(1) - Constant time initialization
     * SPACE COMPLEXITY: O(1) - Only stores the reference
     * 
     * @return Empty queue ready for operations
     */
    fun <T> createBasicQueue(): ArrayDeque<T> {
        return ArrayDeque()
    }
    
    /**
     * Creates a queue with initial capacity
     * 
     * ALGORITHM:
     * 1. Initialize ArrayDeque with specified capacity
     * 2. Pre-allocates memory for better performance
     * 
     * TIME COMPLEXITY: O(1) - Constant time initialization
     * SPACE COMPLEXITY: O(capacity) - Pre-allocates memory
     * 
     * @param capacity Initial capacity of the queue
     * @return Empty queue with specified capacity
     */
    fun <T> createQueueWithCapacity(capacity: Int): ArrayDeque<T> {
        return ArrayDeque(capacity)
    }
    
    /**
     * Creates a queue from a list of elements
     * 
     * ALGORITHM:
     * 1. Initialize empty ArrayDeque
     * 2. Add each element from the list to the queue
     * 3. Elements are added in order (first element becomes front)
     * 
     * TIME COMPLEXITY: O(n) - n elements to add
     * SPACE COMPLEXITY: O(n) - stores all n elements
     * 
     * @param elements List of elements to add to queue
     * @return Queue containing all elements from the list
     */
    fun <T> createQueueFromList(elements: List<T>): ArrayDeque<T> {
        val queue = ArrayDeque<T>()
        for (element in elements) {
            queue.addLast(element)
        }
        return queue
    }
    
    /**
     * Creates a queue from an array of elements
     * 
     * ALGORITHM:
     * 1. Initialize empty ArrayDeque
     * 2. Add each element from the array to the queue
     * 3. Elements are added in order (first element becomes front)
     * 
     * TIME COMPLEXITY: O(n) - n elements to add
     * SPACE COMPLEXITY: O(n) - stores all n elements
     * 
     * @param elements Array of elements to add to queue
     * @return Queue containing all elements from the array
     */
    fun <T> createQueueFromArray(elements: Array<T>): ArrayDeque<T> {
        val queue = ArrayDeque<T>()
        for (element in elements) {
            queue.addLast(element)
        }
        return queue
    }
    
    /**
     * Creates a priority queue using Java's PriorityQueue
     * 
     * ALGORITHM:
     * 1. Initialize PriorityQueue with natural ordering
     * 2. Elements are ordered based on their natural comparison
     * 
     * TIME COMPLEXITY: O(1) - Constant time initialization
     * SPACE COMPLEXITY: O(1) - Only stores the reference
     * 
     * @return Empty priority queue
     */
    fun <T : Comparable<T>> createPriorityQueue(): PriorityQueue<T> {
        return PriorityQueue()
    }
    
    /**
     * Creates a priority queue with custom comparator
     * 
     * ALGORITHM:
     * 1. Initialize PriorityQueue with custom comparator
     * 2. Elements are ordered based on the comparator
     * 
     * TIME COMPLEXITY: O(1) - Constant time initialization
     * SPACE COMPLEXITY: O(1) - Only stores the reference
     * 
     * @param comparator Custom comparator for ordering elements
     * @return Empty priority queue with custom ordering
     */
    fun <T> createPriorityQueueWithComparator(comparator: Comparator<T>): PriorityQueue<T> {
        return PriorityQueue(comparator)
    }
    
    /**
     * Creates a max priority queue (largest elements first)
     * 
     * ALGORITHM:
     * 1. Initialize PriorityQueue with reverse ordering
     * 2. Largest elements are dequeued first
     * 
     * TIME COMPLEXITY: O(1) - Constant time initialization
     * SPACE COMPLEXITY: O(1) - Only stores the reference
     * 
     * @return Empty max priority queue
     */
    fun <T : Comparable<T>> createMaxPriorityQueue(): PriorityQueue<T> {
        return PriorityQueue(Collections.reverseOrder())
    }
    
    /**
     * Creates a bounded queue with limited capacity
     * 
     * ALGORITHM:
     * 1. Initialize ArrayDeque with capacity
     * 2. Add elements up to the limit
     * 3. Ignore elements beyond the limit
     * 
     * TIME COMPLEXITY: O(min(n, limit)) - n elements, limited by capacity
     * SPACE COMPLEXITY: O(min(n, limit)) - stores up to limit elements
     * 
     * @param elements List of elements to add
     * @param limit Maximum number of elements in queue
     * @return Bounded queue with at most 'limit' elements
     */
    fun <T> createBoundedQueue(elements: List<T>, limit: Int): ArrayDeque<T> {
        val queue = ArrayDeque<T>(limit)
        for (element in elements.take(limit)) {
            queue.addLast(element)
        }
        return queue
    }
    
    /**
     * Creates a queue for specific data types with validation
     * 
     * ALGORITHM:
     * 1. Initialize empty ArrayDeque
     * 2. Add elements that pass validation
     * 3. Skip invalid elements
     * 
     * TIME COMPLEXITY: O(n) - n elements to validate and add
     * SPACE COMPLEXITY: O(n) - stores valid elements
     * 
     * @param elements List of elements to add
     * @param validator Function to validate elements
     * @return Queue containing only valid elements
     */
    fun <T> createValidatedQueue(elements: List<T>, validator: (T) -> Boolean): ArrayDeque<T> {
        val queue = ArrayDeque<T>()
        for (element in elements) {
            if (validator(element)) {
                queue.addLast(element)
            }
        }
        return queue
    }
    
    /**
     * Creates a queue for number operations
     * 
     * ALGORITHM:
     * 1. Initialize empty ArrayDeque for numbers
     * 2. Add numeric elements
     * 3. Useful for mathematical operations
     * 
     * TIME COMPLEXITY: O(n) - n numbers to add
     * SPACE COMPLEXITY: O(n) - stores all n numbers
     * 
     * @param numbers List of numbers to add to queue
     * @return Queue containing numeric elements
     */
    fun createNumberQueue(numbers: List<Number>): ArrayDeque<Number> {
        return createQueueFromList(numbers)
    }
    
    /**
     * Creates a queue for character operations
     * 
     * ALGORITHM:
     * 1. Initialize empty ArrayDeque for characters
     * 2. Add character elements
     * 3. Useful for string processing
     * 
     * TIME COMPLEXITY: O(n) - n characters to add
     * SPACE COMPLEXITY: O(n) - stores all n characters
     * 
     * @param chars List of characters to add to queue
     * @return Queue containing character elements
     */
    fun createCharQueue(chars: List<Char>): ArrayDeque<Char> {
        return createQueueFromList(chars)
    }
    
    /**
     * Creates a queue from a string (character by character)
     * 
     * ALGORITHM:
     * 1. Initialize empty ArrayDeque for characters
     * 2. Add each character from the string
     * 3. First character becomes front of queue
     * 
     * TIME COMPLEXITY: O(n) - n characters in string
     * SPACE COMPLEXITY: O(n) - stores all n characters
     * 
     * @param str String to convert to character queue
     * @return Queue containing all characters from the string
     */
    fun createQueueFromString(str: String): ArrayDeque<Char> {
        val queue = ArrayDeque<Char>()
        for (char in str) {
            queue.addLast(char)
        }
        return queue
    }
    
    /**
     * Creates a circular queue using ArrayDeque
     * 
     * ALGORITHM:
     * 1. Initialize ArrayDeque with capacity
     * 2. Use addLast() and removeFirst() for circular behavior
     * 3. ArrayDeque automatically handles circular operations
     * 
     * TIME COMPLEXITY: O(1) - Constant time initialization
     * SPACE COMPLEXITY: O(capacity) - Pre-allocates memory
     * 
     * @param capacity Maximum capacity of the circular queue
     * @return Empty circular queue
     */
    fun <T> createCircularQueue(capacity: Int): ArrayDeque<T> {
        return ArrayDeque(capacity)
    }
    
    /**
     * Creates a double-ended queue (deque)
     * 
     * ALGORITHM:
     * 1. Initialize ArrayDeque
     * 2. Supports operations at both ends
     * 3. Can be used as both stack and queue
     * 
     * TIME COMPLEXITY: O(1) - Constant time initialization
     * SPACE COMPLEXITY: O(1) - Only stores the reference
     * 
     * @return Empty double-ended queue
     */
    fun <T> createDeque(): ArrayDeque<T> {
        return ArrayDeque()
    }
    
    /**
     * Creates a queue with custom ordering
     * 
     * ALGORITHM:
     * 1. Initialize PriorityQueue with custom comparator
     * 2. Add elements in custom order based on comparator
     * 3. Maintains specified ordering
     * 
     * TIME COMPLEXITY: O(n log n) - sorting n elements
     * SPACE COMPLEXITY: O(n) - stores all n elements
     * 
     * @param elements List of elements to add
     * @param comparator Function to compare elements
     * @return Queue with elements ordered by comparator
     */
    fun <T> createOrderedQueue(elements: List<T>, comparator: Comparator<T>): PriorityQueue<T> {
        val queue = PriorityQueue(comparator)
        queue.addAll(elements)
        return queue
    }
    
    /**
     * Demonstrates queue creation patterns
     */
    fun demonstrateQueueCreation() {
        println("=== QUEUE CREATION DEMONSTRATION ===\n")
        
        // 1. Basic queue creation
        println("1. BASIC QUEUE CREATION")
        val basicQueue = createBasicQueue<Int>()
        println("Basic queue created: $basicQueue")
        println()
        
        // 2. Queue with capacity
        println("2. QUEUE WITH CAPACITY")
        val capacityQueue = createQueueWithCapacity<Int>(10)
        println("Queue with capacity 10 created: $capacityQueue")
        println()
        
        // 3. Queue from list
        println("3. QUEUE FROM LIST")
        val listQueue = createQueueFromList(listOf(1, 2, 3, 4, 5))
        println("Queue from list [1,2,3,4,5]: $listQueue")
        println()
        
        // 4. Priority queue
        println("4. PRIORITY QUEUE")
        val priorityQueue = createPriorityQueue<Int>()
        priorityQueue.addAll(listOf(5, 2, 8, 1, 9))
        println("Priority queue: $priorityQueue")
        println()
        
        // 5. Max priority queue
        println("5. MAX PRIORITY QUEUE")
        val maxPriorityQueue = createMaxPriorityQueue<Int>()
        maxPriorityQueue.addAll(listOf(5, 2, 8, 1, 9))
        println("Max priority queue: $maxPriorityQueue")
        println()
        
        // 6. Bounded queue
        println("6. BOUNDED QUEUE")
        val boundedQueue = createBoundedQueue(listOf(1, 2, 3, 4, 5, 6, 7), 3)
        println("Bounded queue (limit 3): $boundedQueue")
        println()
        
        // 7. Validated queue
        println("7. VALIDATED QUEUE")
        val validatedQueue = createValidatedQueue(listOf(1, -2, 3, -4, 5)) { it > 0 }
        println("Validated queue (positive numbers only): $validatedQueue")
        println()
        
        // 8. Number queue
        println("8. NUMBER QUEUE")
        val numberQueue = createNumberQueue(listOf(1, 2.5, 3, 4.7, 5))
        println("Number queue: $numberQueue")
        println()
        
        // 9. Character queue
        println("9. CHARACTER QUEUE")
        val charQueue = createCharQueue(listOf('a', 'b', 'c', 'd'))
        println("Character queue: $charQueue")
        println()
        
        // 10. String queue
        println("10. STRING QUEUE")
        val stringQueue = createQueueFromString("hello")
        println("String queue from 'hello': $stringQueue")
        println()
        
        // 11. Circular queue
        println("11. CIRCULAR QUEUE")
        val circularQueue = createCircularQueue<Int>(5)
        println("Circular queue with capacity 5: $circularQueue")
        println()
        
        // 12. Deque
        println("12. DOUBLE-ENDED QUEUE")
        val deque = createDeque<Int>()
        println("Double-ended queue: $deque")
        println()
        
        // 13. Ordered queue
        println("13. ORDERED QUEUE")
        val orderedQueue = createOrderedQueue(listOf(5, 2, 8, 1, 9)) { a, b -> b.compareTo(a) }
        println("Ordered queue (descending): $orderedQueue")
        println()
        
        println("=== QUEUE CREATION DEMONSTRATION COMPLETE ===\n")
    }
} 