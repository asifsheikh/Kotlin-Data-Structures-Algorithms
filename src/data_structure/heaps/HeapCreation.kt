package data_structure.heaps

import java.util.*
import java.util.Collections.addAll

/**
 * HEAP CREATION
 * 
 * This file contains functions for creating different types of heaps
 * and various heap creation patterns.
 */

object HeapCreation {
    
    /**
     * Creates a min-heap (default PriorityQueue)
     */
    fun createMinHeap(): PriorityQueue<Int> {
        return PriorityQueue<Int>()
    }
    
    /**
     * Creates a max-heap using comparator
     */
    fun createMaxHeap(): PriorityQueue<Int> {
        return PriorityQueue<Int>(compareByDescending { it })
    }
    
    /**
     * Creates a min-heap from a list of elements
     */
    fun createMinHeapFromList(elements: List<Int>): PriorityQueue<Int> {
        return PriorityQueue(elements)
    }
    
    /**
     * Creates a max-heap from a list of elements
     */
    fun createMaxHeapFromList(elements: List<Int>): PriorityQueue<Int> {
        return PriorityQueue<Int>(compareByDescending { it }).apply { addAll(elements) }
    }
    
    /**
     * Creates a heap with custom comparator
     */
    fun <T> createHeapWithComparator(comparator: Comparator<T>): PriorityQueue<T> {
        return PriorityQueue(comparator)
    }
    
    /**
     * Creates a heap from an array
     */
    fun createMinHeapFromArray(arr: IntArray): PriorityQueue<Int> {
        return PriorityQueue<Int>().apply { 
            for (element in arr) {
                add(element)
            }
        }
    }
    
    /**
     * Creates a max-heap from an array
     */
    fun createMaxHeapFromArray(arr: IntArray): PriorityQueue<Int> {
        return PriorityQueue<Int>(compareByDescending { it }).apply { 
            for (element in arr) {
                add(element)
            }
        }
    }
    
    /**
     * Creates a heap with initial capacity
     */
    fun createMinHeapWithCapacity(initialCapacity: Int): PriorityQueue<Int> {
        return PriorityQueue<Int>(initialCapacity)
    }
    
    /**
     * Creates a max-heap with initial capacity
     */
    fun createMaxHeapWithCapacity(initialCapacity: Int): PriorityQueue<Int> {
        return PriorityQueue<Int>(initialCapacity, compareByDescending { it })
    }
    
    /**
     * Creates a heap for custom objects
     */
    data class HeapObj(val key: Int, val name: String)
    
    fun createObjectHeap(): PriorityQueue<HeapObj> {
        return PriorityQueue<HeapObj>(compareBy { it.key })
    }
    
    fun createObjectMaxHeap(): PriorityQueue<HeapObj> {
        return PriorityQueue<HeapObj>(compareByDescending { it.key })
    }
    
    /**
     * Creates a heap from a map (key-value pairs)
     */
    fun createHeapFromMap(map: Map<String, Int>): PriorityQueue<Map.Entry<String, Int>> {
        return PriorityQueue<Map.Entry<String, Int>>(compareBy { it.value })
    }
    
    /**
     * Creates a heap from a map with max-heap ordering
     */
    fun createMaxHeapFromMap(map: Map<String, Int>): PriorityQueue<Map.Entry<String, Int>> {
        return PriorityQueue<Map.Entry<String, Int>>(compareByDescending { it.value })
    }
    
    /**
     * Creates a heap with custom ordering for pairs
     */
    fun createPairHeap(): PriorityQueue<Pair<Int, String>> {
        return PriorityQueue<Pair<Int, String>>(compareBy { it.first })
    }
    
    /**
     * Creates a max-heap for pairs
     */
    fun createPairMaxHeap(): PriorityQueue<Pair<Int, String>> {
        return PriorityQueue<Pair<Int, String>>(compareByDescending { it.first })
    }
    
    /**
     * Demonstrates heap creation patterns
     */
    fun demonstrateHeapCreation() {
        println("=== HEAP CREATION DEMONSTRATION ===\n")
        
        // 1. Basic heap creation
        println("1. BASIC HEAP CREATION")
        val minHeap = createMinHeap()
        val maxHeap = createMaxHeap()
        println("Min-heap created: $minHeap")
        println("Max-heap created: $maxHeap")
        println()
        
        // 2. Creating heaps from collections
        println("2. CREATING HEAPS FROM COLLECTIONS")
        val list = listOf(5, 2, 8, 1, 9)
        val minHeapFromList = createMinHeapFromList(list)
        val maxHeapFromList = createMaxHeapFromList(list)
        println("Min-heap from list: $minHeapFromList")
        println("Max-heap from list: $maxHeapFromList")
        println()
        
        // 3. Creating heaps from arrays
        println("3. CREATING HEAPS FROM ARRAYS")
        val array = intArrayOf(3, 7, 1, 9, 4)
        val minHeapFromArray = createMinHeapFromArray(array)
        val maxHeapFromArray = createMaxHeapFromArray(array)
        println("Min-heap from array: $minHeapFromArray")
        println("Max-heap from array: $maxHeapFromArray")
        println()
        
        // 4. Creating heaps with capacity
        println("4. CREATING HEAPS WITH CAPACITY")
        val minHeapWithCap = createMinHeapWithCapacity(10)
        val maxHeapWithCap = createMaxHeapWithCapacity(10)
        println("Min-heap with capacity 10: $minHeapWithCap")
        println("Max-heap with capacity 10: $maxHeapWithCap")
        println()
        
        // 5. Creating object heaps
        println("5. CREATING OBJECT HEAPS")
        val objHeap = createObjectHeap()
        val objMaxHeap = createObjectMaxHeap()
        objHeap.add(HeapObj(30, "Alice"))
        objHeap.add(HeapObj(10, "Bob"))
        objMaxHeap.add(HeapObj(30, "Alice"))
        objMaxHeap.add(HeapObj(10, "Bob"))
        println("Object min-heap: $objHeap")
        println("Object max-heap: $objMaxHeap")
        println()
        
        // 6. Creating heaps from maps
        println("6. CREATING HEAPS FROM MAPS")
        val map = mapOf("A" to 3, "B" to 7, "C" to 1, "D" to 9)
        val mapHeap = createHeapFromMap(map)
        val mapMaxHeap = createMaxHeapFromMap(map)
        println("Map min-heap: $mapHeap")
        println("Map max-heap: $mapMaxHeap")
        println()
        
        // 7. Creating pair heaps
        println("7. CREATING PAIR HEAPS")
        val pairHeap = createPairHeap()
        val pairMaxHeap = createPairMaxHeap()
        pairHeap.add(Pair(30, "Alice"))
        pairHeap.add(Pair(10, "Bob"))
        pairMaxHeap.add(Pair(30, "Alice"))
        pairMaxHeap.add(Pair(10, "Bob"))
        println("Pair min-heap: $pairHeap")
        println("Pair max-heap: $pairMaxHeap")
        println()
        
        println("=== HEAP CREATION DEMONSTRATION COMPLETE ===\n")
    }
} 