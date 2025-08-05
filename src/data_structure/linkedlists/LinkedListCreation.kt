package data_structure.linkedlists

import kotlin.math.sqrt
import kotlin.math.pow

/**
 * LINKED LIST CREATION
 * 
 * This file contains functions for creating different types of linked lists
 * and various linked list creation patterns.
 */

object LinkedListCreation {
    
    /**
     * Basic ListNode class for singly linked list
     */
    data class ListNode(var value: Int, var next: ListNode? = null)
    
    /**
     * DoublyListNode class for doubly linked list
     */
    data class DoublyListNode(var value: Int, var prev: DoublyListNode? = null, var next: DoublyListNode? = null)
    
    /**
     * Creates a singly linked list from an array
     */
    fun createSinglyLinkedList(arr: IntArray): ListNode? {
        if (arr.isEmpty()) return null
        
        val head = ListNode(arr[0])
        var current = head
        
        for (i in 1 until arr.size) {
            current.next = ListNode(arr[i])
            current = current.next!!
        }
        
        return head
    }
    
    /**
     * Creates a singly linked list from a list
     */
    fun createSinglyLinkedListFromList(list: List<Int>): ListNode? {
        return createSinglyLinkedList(list.toIntArray())
    }
    
    /**
     * Creates a doubly linked list from an array
     */
    fun createDoublyLinkedList(arr: IntArray): DoublyListNode? {
        if (arr.isEmpty()) return null
        
        val head = DoublyListNode(arr[0])
        var current = head
        
        for (i in 1 until arr.size) {
            val newNode = DoublyListNode(arr[i])
            newNode.prev = current
            current.next = newNode
            current = newNode
        }
        
        return head
    }
    
    /**
     * Creates a singly linked list with cycle
     */
    fun createLinkedListWithCycle(arr: IntArray, cycleStartIndex: Int): ListNode? {
        if (arr.isEmpty()) return null
        
        val head = ListNode(arr[0])
        var current = head
        var cycleStartNode: ListNode? = null
        
        for (i in 1 until arr.size) {
            current.next = ListNode(arr[i])
            current = current.next!!
            
            if (i == cycleStartIndex) {
                cycleStartNode = current
            }
        }
        
        // Create cycle
        if (cycleStartNode != null) {
            current.next = cycleStartNode
        }
        
        return head
    }
    
    /**
     * Creates a palindrome linked list
     */
    fun createPalindromeLinkedList(arr: IntArray): ListNode? {
        if (arr.isEmpty()) return null
        
        val head = ListNode(arr[0])
        var current = head
        
        for (i in 1 until arr.size) {
            current.next = ListNode(arr[i])
            current = current.next!!
        }
        
        return head
    }
    
    /**
     * Creates a sorted linked list
     */
    fun createSortedLinkedList(arr: IntArray): ListNode? {
        val sortedArr = arr.sortedArray()
        return createSinglyLinkedList(sortedArr)
    }
    
    /**
     * Creates a linked list with random values
     */
    fun createRandomLinkedList(size: Int, minValue: Int = 1, maxValue: Int = 100): ListNode? {
        val arr = IntArray(size) { (minValue..maxValue).random() }
        return createSinglyLinkedList(arr)
    }
    
    /**
     * Creates a linked list with repeated values
     */
    fun createLinkedListWithRepeats(arr: IntArray): ListNode? {
        return createSinglyLinkedList(arr)
    }
    
    /**
     * Creates a linked list with specific pattern
     */
    fun createPatternLinkedList(pattern: List<Int>, repeatCount: Int): ListNode? {
        val arr = mutableListOf<Int>()
        repeat(repeatCount) {
            arr.addAll(pattern)
        }
        return createSinglyLinkedList(arr.toIntArray())
    }
    
    /**
     * Creates a linked list with alternating values
     */
    fun createAlternatingLinkedList(size: Int, value1: Int, value2: Int): ListNode? {
        val arr = IntArray(size) { if (it % 2 == 0) value1 else value2 }
        return createSinglyLinkedList(arr)
    }
    
    /**
     * Creates a linked list with arithmetic sequence
     */
    fun createArithmeticLinkedList(start: Int, difference: Int, count: Int): ListNode? {
        val arr = IntArray(count) { start + it * difference }
        return createSinglyLinkedList(arr)
    }
    
    /**
     * Creates a linked list with geometric sequence
     */
    fun createGeometricLinkedList(start: Int, ratio: Int, count: Int): ListNode? {
        val arr = IntArray(count) { start * (ratio.toDouble().pow(it)).toInt() }
        return createSinglyLinkedList(arr)
    }
    
    /**
     * Creates a linked list with Fibonacci sequence
     */
    fun createFibonacciLinkedList(count: Int): ListNode? {
        if (count <= 0) return null
        if (count == 1) return ListNode(0)
        if (count == 2) return createSinglyLinkedList(intArrayOf(0, 1))
        
        val arr = IntArray(count)
        arr[0] = 0
        arr[1] = 1
        
        for (i in 2 until count) {
            arr[i] = arr[i - 1] + arr[i - 2]
        }
        
        return createSinglyLinkedList(arr)
    }
    
    /**
     * Creates a linked list with prime numbers
     */
    fun createPrimeLinkedList(count: Int): ListNode? {
        val primes = mutableListOf<Int>()
        var num = 2
        
        while (primes.size < count) {
            if (isPrime(num)) {
                primes.add(num)
            }
            num++
        }
        
        return createSinglyLinkedList(primes.toIntArray())
    }
    
    /**
     * Helper function to check if a number is prime
     */
    private fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        if (n == 2) return true
        if (n % 2 == 0) return false
        
        for (i in 3..sqrt(n.toDouble()).toInt() step 2) {
            if (n % i == 0) return false
        }
        return true
    }
    
    /**
     * Creates a linked list with specific structure for testing
     */
    fun createTestLinkedList(): ListNode? {
        // Creates: 1 -> 2 -> 3 -> 4 -> 5
        return createSinglyLinkedList(intArrayOf(1, 2, 3, 4, 5))
    }
    
    /**
     * Creates a linked list for palindrome testing
     */
    fun createPalindromeTestLinkedList(): ListNode? {
        // Creates: 1 -> 2 -> 3 -> 2 -> 1
        return createSinglyLinkedList(intArrayOf(1, 2, 3, 2, 1))
    }
    
    /**
     * Creates two linked lists for intersection testing
     */
    fun createIntersectionTestLists(): Pair<ListNode?, ListNode?> {
        // List 1: 1 -> 2 -> 3 -> 4 -> 5
        // List 2: 6 -> 7 -> 4 -> 5
        val list1 = createSinglyLinkedList(intArrayOf(1, 2, 3, 4, 5))
        val list2 = createSinglyLinkedList(intArrayOf(6, 7))
        
        // Find node with value 4 in list1
        var intersectionNode: ListNode? = null
        var current = list1
        while (current != null) {
            if (current.value == 4) {
                intersectionNode = current
                break
            }
            current = current.next
        }
        
        // Connect list2 to intersection node
        var current2 = list2
        while (current2?.next != null) {
            current2 = current2.next
        }
        current2?.next = intersectionNode
        
        return Pair(list1, list2)
    }
    
    /**
     * Demonstrates linked list creation patterns
     */
    fun demonstrateLinkedListCreation() {
        println("=== LINKED LIST CREATION DEMONSTRATION ===\n")
        
        // 1. Basic creation
        println("1. BASIC CREATION")
        val basicList = createSinglyLinkedList(intArrayOf(1, 2, 3, 4, 5))
        println("Basic singly linked list created")
        println()
        
        // 2. Doubly linked list
        println("2. DOUBLY LINKED LIST")
        val doublyList = createDoublyLinkedList(intArrayOf(1, 2, 3, 4, 5))
        println("Doubly linked list created")
        println()
        
        // 3. Linked list with cycle
        println("3. LINKED LIST WITH CYCLE")
        val cycleList = createLinkedListWithCycle(intArrayOf(1, 2, 3, 4, 5), 2)
        println("Linked list with cycle created (cycle starts at index 2)")
        println()
        
        // 4. Palindrome linked list
        println("4. PALINDROME LINKED LIST")
        val palindromeList = createPalindromeTestLinkedList()
        println("Palindrome linked list created: 1 -> 2 -> 3 -> 2 -> 1")
        println()
        
        // 5. Sorted linked list
        println("5. SORTED LINKED LIST")
        val sortedList = createSortedLinkedList(intArrayOf(5, 2, 8, 1, 9))
        println("Sorted linked list created from [5, 2, 8, 1, 9]")
        println()
        
        // 6. Random linked list
        println("6. RANDOM LINKED LIST")
        val randomList = createRandomLinkedList(5, 1, 10)
        println("Random linked list created with 5 elements (1-10)")
        println()
        
        // 7. Pattern linked list
        println("7. PATTERN LINKED LIST")
        val patternList = createPatternLinkedList(listOf(1, 2, 3), 2)
        println("Pattern linked list created: [1,2,3] repeated 2 times")
        println()
        
        // 8. Alternating linked list
        println("8. ALTERNATING LINKED LIST")
        val alternatingList = createAlternatingLinkedList(6, 1, 0)
        println("Alternating linked list created: 1,0,1,0,1,0")
        println()
        
        // 9. Arithmetic sequence
        println("9. ARITHMETIC SEQUENCE")
        val arithmeticList = createArithmeticLinkedList(2, 3, 5)
        println("Arithmetic sequence: 2, 5, 8, 11, 14")
        println()
        
        // 10. Fibonacci sequence
        println("10. FIBONACCI SEQUENCE")
        val fibonacciList = createFibonacciLinkedList(7)
        println("Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8")
        println()
        
        // 11. Prime numbers
        println("11. PRIME NUMBERS")
        val primeList = createPrimeLinkedList(6)
        println("First 6 prime numbers: 2, 3, 5, 7, 11, 13")
        println()
        
        // 12. Test lists for intersection
        println("12. INTERSECTION TEST LISTS")
        val (list1, list2) = createIntersectionTestLists()
        println("Two linked lists created for intersection testing")
        println()
        
        println("=== LINKED LIST CREATION DEMONSTRATION COMPLETE ===\n")
    }
} 