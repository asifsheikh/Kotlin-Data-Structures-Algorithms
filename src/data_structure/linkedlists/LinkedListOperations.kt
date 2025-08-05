package data_structure.linkedlists

/**
 * LINKED LIST OPERATIONS
 * 
 * This file contains standard operations on linked lists including
 * inserting, deleting, searching, and other basic linked list operations.
 */

object LinkedListOperations {
    
    // Use the ListNode class from LinkedListCreation directly
    
    /**
     * Prints a singly linked list
     */
    fun printList(head: LinkedListCreation.ListNode?) {
        var current = head
        while (current != null) {
            print("${current.value} -> ")
            current = current.next
        }
        println("null")
    }
    
    /**
     * Prints a doubly linked list
     */
    fun printDoublyList(head: LinkedListCreation.DoublyListNode?) {
        var current = head
        print("Forward: ")
        while (current != null) {
            print("${current.value} <-> ")
            current = current.next
        }
        println("null")
        
        // Print backward
        current = head
        while (current?.next != null) {
            current = current.next
        }
        
        print("Backward: null <-> ")
        while (current != null) {
            print("${current.value} <-> ")
            current = current.prev
        }
        println("null")
    }
    
    /**
     * Inserts a node at the beginning of a singly linked list
     */
    fun insertAtBeginning(head: LinkedListCreation.ListNode?, value: Int): LinkedListCreation.ListNode {
        val newNode = LinkedListCreation.ListNode(value)
        newNode.next = head
        return newNode
    }
    
    /**
     * Inserts a node at the end of a singly linked list
     */
    fun insertAtEnd(head: LinkedListCreation.ListNode?, value: Int): LinkedListCreation.ListNode? {
        val newNode = LinkedListCreation.ListNode(value)
        if (head == null) return newNode
        
        var current = head
        while (current?.next != null) {
            current = current.next
        }
        current?.next = newNode
        return head
    }
    
    /**
     * Inserts a node at a specific position in a singly linked list
     */
    fun insertAtPosition(head: LinkedListCreation.ListNode?, value: Int, position: Int): LinkedListCreation.ListNode? {
        if (position == 0) return insertAtBeginning(head, value)
        
        val newNode = LinkedListCreation.ListNode(value)
        var current = head
        var count = 0
        
        while (current != null && count < position - 1) {
            current = current.next
            count++
        }
        
        if (current != null) {
            newNode.next = current.next
            current.next = newNode
        }
        
        return head
    }
    
    /**
     * Deletes a node by value from a singly linked list
     */
    fun deleteNode(head: LinkedListCreation.ListNode?, value: Int): LinkedListCreation.ListNode? {
        if (head == null) return null
        if (head.value == value) return head.next
        
        var current = head
        while (current?.next != null) {
            if (current.next?.value == value) {
                current.next = current.next?.next
                break
            }
            current = current.next
        }
        return head
    }
    
    /**
     * Deletes a node at a specific position in a singly linked list
     */
    fun deleteAtPosition(head: LinkedListCreation.ListNode?, position: Int): LinkedListCreation.ListNode? {
        if (head == null) return null
        if (position == 0) return head.next
        
        var current = head
        var count = 0
        
        while (current?.next != null && count < position - 1) {
            current = current.next
            count++
        }
        
        if (current?.next != null) {
            current.next = current.next?.next
        }
        
        return head
    }
    
    /**
     * Searches for a value in a singly linked list
     */
    fun search(head: LinkedListCreation.ListNode?, value: Int): Boolean {
        var current = head
        while (current != null) {
            if (current.value == value) return true
            current = current.next
        }
        return false
    }
    
    /**
     * Gets the length of a singly linked list
     */
    fun getLength(head: LinkedListCreation.ListNode?): Int {
        var count = 0
        var current = head
        while (current != null) {
            count++
            current = current.next
        }
        return count
    }
    
    /**
     * Gets the nth node from the beginning
     */
    fun getNthNode(head: LinkedListCreation.ListNode?, n: Int): LinkedListCreation.ListNode? {
        var current = head
        var count = 0
        
        while (current != null && count < n) {
            current = current.next
            count++
        }
        
        return current
    }
    
    /**
     * Gets the middle node of a singly linked list
     */
    fun getMiddleNode(head: LinkedListCreation.ListNode?): LinkedListCreation.ListNode? {
        if (head?.next == null) return head
        
        var slow = head
        var fast = head
        
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        
        return slow
    }
    
    /**
     * Checks if a singly linked list is empty
     */
    fun isEmpty(head: LinkedListCreation.ListNode?): Boolean {
        return head == null
    }
    
    /**
     * Gets the last node of a singly linked list
     */
    fun getLastNode(head: LinkedListCreation.ListNode?): LinkedListCreation.ListNode? {
        if (head == null) return null
        
        var current = head
        while (current?.next != null) {
            current = current.next
        }
        return current
    }
    
    /**
     * Converts a singly linked list to an array
     */
    fun linkedListToArray(head: LinkedListCreation.ListNode?): IntArray {
        val list = mutableListOf<Int>()
        var current = head
        
        while (current != null) {
            list.add(current.value)
            current = current.next
        }
        
        return list.toIntArray()
    }
    
    /**
     * Converts a singly linked list to a list
     */
    fun linkedListToList(head: LinkedListCreation.ListNode?): List<Int> {
        val list = mutableListOf<Int>()
        var current = head
        
        while (current != null) {
            list.add(current.value)
            current = current.next
        }
        
        return list
    }
    
    /**
     * Reverses a singly linked list iteratively
     */
    fun reverseList(head: LinkedListCreation.ListNode?): LinkedListCreation.ListNode? {
        var prev: LinkedListCreation.ListNode? = null
        var current = head
        
        while (current != null) {
            val next = current.next
            current.next = prev
            prev = current
            current = next
        }
        
        return prev
    }
    
    /**
     * Reverses a singly linked list recursively
     */
    fun reverseListRecursive(head: LinkedListCreation.ListNode?): LinkedListCreation.ListNode? {
        if (head?.next == null) return head
        
        val newHead = reverseListRecursive(head.next)
        head.next?.next = head
        head.next = null
        return newHead
    }
    
    /**
     * Reverses a singly linked list in groups of k
     */
    fun reverseKGroup(head: LinkedListCreation.ListNode?, k: Int): LinkedListCreation.ListNode? {
        if (head == null || k == 1) return head
        
        var current = head
        var count = 0
        
        // Count nodes in current group
        while (current != null && count < k) {
            current = current.next
            count++
        }
        
        if (count == k) {
            current = reverseKGroup(current, k)
            
            // Reverse current group
            var prev: LinkedListCreation.ListNode? = null
            var curr = head
            repeat(k) {
                val next = curr?.next
                curr?.next = prev
                prev = curr
                curr = next
            }
            
            head.next = current
            return prev
        }
        
        return head
    }
    
    /**
     * Swaps adjacent nodes in a singly linked list
     */
    fun swapPairs(head: LinkedListCreation.ListNode?): LinkedListCreation.ListNode? {
        if (head?.next == null) return head
        
        val dummy = LinkedListCreation.ListNode(0)
        dummy.next = head
        var current: LinkedListCreation.ListNode? = dummy
        
        while (current?.next != null && current.next?.next != null) {
            val first = current.next
            val second = current.next?.next
            
            first?.next = second?.next
            second?.next = first
            current.next = second
            
            current = first
        }
        
        return dummy.next
    }
    
    /**
     * Rotates a singly linked list by k positions
     */
    fun rotateList(head: LinkedListCreation.ListNode?, k: Int): LinkedListCreation.ListNode? {
        if (head?.next == null || k == 0) return head
        
        val length = getLength(head)
        val actualK = k % length
        
        if (actualK == 0) return head
        
        var current = head
        repeat(length - actualK - 1) {
            current = current?.next
        }
        
        val newHead = current?.next
        current?.next = null
        
        var last = newHead
        while (last?.next != null) {
            last = last.next
        }
        last?.next = head
        
        return newHead
    }
    
    /**
     * Demonstrates standard linked list operations
     */
    fun demonstrateLinkedListOperations() {
        println("=== LINKED LIST OPERATIONS DEMONSTRATION ===\n")
        
        // Create a test linked list
        val head = LinkedListCreation.createTestLinkedList()
        println("Original list:")
        printList(head)
        println()
        
        // 1. Basic operations
        println("1. BASIC OPERATIONS")
        println("Length: ${getLength(head)}")
        println("Is empty: ${isEmpty(head)}")
        println("Search for 3: ${search(head, 3)}")
        println("Search for 10: ${search(head, 10)}")
        println()
        
        // 2. Insert operations
        println("2. INSERT OPERATIONS")
        var modifiedHead: LinkedListCreation.ListNode? = insertAtBeginning(head, 0)
        println("After inserting 0 at beginning:")
        printList(modifiedHead)
        
        modifiedHead = insertAtEnd(modifiedHead, 6)
        println("After inserting 6 at end:")
        printList(modifiedHead)
        
        modifiedHead = insertAtPosition(modifiedHead, 100, 3)
        println("After inserting 100 at position 3:")
        printList(modifiedHead)
        println()
        
        // 3. Delete operations
        println("3. DELETE OPERATIONS")
        modifiedHead = deleteNode(modifiedHead, 100)
        println("After deleting 100:")
        printList(modifiedHead)
        
        modifiedHead = deleteAtPosition(modifiedHead, 2)
        println("After deleting at position 2:")
        printList(modifiedHead)
        println()
        
        // 4. Node access operations
        println("4. NODE ACCESS OPERATIONS")
        val nthNode = getNthNode(modifiedHead, 2)
        println("3rd node (index 2): ${nthNode?.value}")
        
        val middleNode = getMiddleNode(modifiedHead)
        println("Middle node: ${middleNode?.value}")
        
        val lastNode = getLastNode(modifiedHead)
        println("Last node: ${lastNode?.value}")
        println()
        
        // 5. Conversion operations
        println("5. CONVERSION OPERATIONS")
        val array = linkedListToArray(modifiedHead)
        println("Linked list as array: ${array.contentToString()}")
        
        val list = linkedListToList(modifiedHead)
        println("Linked list as list: $list")
        println()
        
        // 6. Reverse operations
        println("6. REVERSE OPERATIONS")
        val reversedHead = reverseList(modifiedHead)
        println("Reversed list (iterative):")
        printList(reversedHead)
        
        val reversedRecursive = reverseListRecursive(reversedHead)
        println("Reversed back (recursive):")
        printList(reversedRecursive)
        
        val kReversed = reverseKGroup(reversedRecursive, 2)
        println("Reversed in groups of 2:")
        printList(kReversed)
        println()
        
        // 7. Advanced operations
        println("7. ADVANCED OPERATIONS")
        val swappedPairs = swapPairs(kReversed)
        println("Swapped pairs:")
        printList(swappedPairs)
        
        val rotated = rotateList(swappedPairs, 2)
        println("Rotated by 2 positions:")
        printList(rotated)
        println()
        
        // 8. Doubly linked list operations
        println("8. DOUBLY LINKED LIST OPERATIONS")
        val doublyHead = LinkedListCreation.createDoublyLinkedList(intArrayOf(1, 2, 3, 4, 5))
        println("Doubly linked list:")
        printDoublyList(doublyHead)
        println()
        
        println("=== LINKED LIST OPERATIONS DEMONSTRATION COMPLETE ===\n")
    }
} 