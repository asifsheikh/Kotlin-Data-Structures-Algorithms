package data_structure.linkedlists

/**
 * LINKED LIST ALGORITHMS
 * 
 * This file contains standard algorithms that use linked lists including
 * cycle detection, merge operations, palindrome check, and other advanced algorithms.
 */

object LinkedListAlgorithms {
    
    // Use the ListNode class from LinkedListCreation directly
    
    /**
     * Detects cycle in a linked list using Floyd's Cycle Finding Algorithm
     */
    fun hasCycle(head: LinkedListCreation.ListNode?): Boolean {
        if (head?.next == null) return false
        
        var slow = head
        var fast = head
        
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
            
            if (slow == fast) return true
        }
        
        return false
    }
    
    /**
     * Finds the start node of a cycle in a linked list
     */
    fun detectCycleStart(head: LinkedListCreation.ListNode?): LinkedListCreation.ListNode? {
        if (head?.next == null) return null
        
        var slow = head
        var fast = head
        
        // Find meeting point
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
            
            if (slow == fast) break
        }
        
        if (fast?.next == null) return null // No cycle
        
        // Find cycle start
        slow = head
        while (slow != fast) {
            slow = slow?.next
            fast = fast?.next
        }
        
        return slow
    }
    
    /**
     * Removes cycle from a linked list
     */
    fun removeCycle(head: LinkedListCreation.ListNode?): LinkedListCreation.ListNode? {
        val cycleStart = detectCycleStart(head) ?: return head
        
        var current: LinkedListCreation.ListNode? = cycleStart
        while (current?.next != cycleStart) {
            current = current?.next
        }
        current?.next = null
        
        return head
    }
    
    /**
     * Merges two sorted linked lists
     */
    fun mergeTwoLists(l1: LinkedListCreation.ListNode?, l2: LinkedListCreation.ListNode?): LinkedListCreation.ListNode? {
        val dummy = LinkedListCreation.ListNode(0)
        var current = dummy
        var list1 = l1
        var list2 = l2
        
        while (list1 != null && list2 != null) {
            if (list1.value <= list2.value) {
                current.next = list1
                list1 = list1.next
            } else {
                current.next = list2
                list2 = list2.next
            }
            current = current.next!!
        }
        
        current.next = list1 ?: list2
        return dummy.next
    }
    
    /**
     * Merges k sorted linked lists
     */
    fun mergeKLists(lists: Array<LinkedListCreation.ListNode?>): LinkedListCreation.ListNode? {
        if (lists.isEmpty()) return null
        if (lists.size == 1) return lists[0]
        
        var result = lists[0]
        for (i in 1 until lists.size) {
            result = mergeTwoLists(result, lists[i])
        }
        
        return result
    }
    
    /**
     * Checks if a linked list is palindrome
     */
    fun isPalindrome(head: LinkedListCreation.ListNode?): Boolean {
        if (head?.next == null) return true
        
        // Find middle using slow/fast pointers
        var slow = head
        var fast = head
        
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        
        // Reverse second half
        var secondHalf = LinkedListOperations.reverseList(slow)
        var firstHalf = head
        
        // Compare first and second half
        while (secondHalf != null) {
            if (firstHalf?.value != secondHalf.value) return false
            firstHalf = firstHalf.next
            secondHalf = secondHalf.next
        }
        
        return true
    }
    
    /**
     * Removes the nth node from the end of a linked list
     */
    fun removeNthFromEnd(head: LinkedListCreation.ListNode?, n: Int): LinkedListCreation.ListNode? {
        val dummy = LinkedListCreation.ListNode(0)
        dummy.next = head
        
        var first: LinkedListCreation.ListNode? = dummy
        var second: LinkedListCreation.ListNode? = dummy
        
        // Move first pointer n+1 steps ahead
        repeat(n + 1) {
            first = first?.next
        }
        
        // Move both pointers until first reaches end
        while (first != null) {
            first = first.next
            second = second?.next
        }
        
        // Remove the node
        second?.next = second?.next?.next
        
        return dummy.next
    }
    
    /**
     * Finds the intersection node of two linked lists
     */
    fun getIntersectionNode(headA: LinkedListCreation.ListNode?, headB: LinkedListCreation.ListNode?): LinkedListCreation.ListNode? {
        if (headA == null || headB == null) return null
        
        var a: LinkedListCreation.ListNode? = headA
        var b: LinkedListCreation.ListNode? = headB
        
        while (a != b) {
            a = if (a == null) headB else a.next
            b = if (b == null) headA else b.next
        }
        
        return a
    }
    
    /**
     * Sorts a linked list using merge sort
     */
    fun sortList(head: LinkedListCreation.ListNode?): LinkedListCreation.ListNode? {
        if (head?.next == null) return head
        
        // Find middle
        var slow = head
        var fast = head
        var prev: LinkedListCreation.ListNode? = null
        
        while (fast?.next != null) {
            prev = slow
            slow = slow?.next
            fast = fast.next?.next
        }
        
        prev?.next = null
        
        // Sort two halves
        val left = sortList(head)
        val right = sortList(slow)
        
        // Merge sorted halves
        return mergeTwoLists(left, right)
    }
    
    /**
     * Reorders a linked list (L0 → L1 → L2 → ... → Ln-1 → Ln → L0 → L1 → L2 → ...)
     */
    fun reorderList(head: LinkedListCreation.ListNode?): LinkedListCreation.ListNode? {
        if (head?.next == null) return head
        
        // Find middle
        var slow = head
        var fast = head
        var prev: LinkedListCreation.ListNode? = null
        
        while (fast?.next != null) {
            prev = slow
            slow = slow?.next
            fast = fast.next?.next
        }
        
        prev?.next = null
        
        // Reverse second half
        var secondHalf = LinkedListOperations.reverseList(slow)
        var firstHalf = head
        
        // Merge in reorder pattern
        while (firstHalf != null && secondHalf != null) {
            val temp1 = firstHalf.next
            val temp2 = secondHalf.next
            
            firstHalf.next = secondHalf
            secondHalf.next = temp1
            
            firstHalf = temp1
            secondHalf = temp2
        }
        
        return head
    }
    
    /**
     * Flattens a multilevel linked list
     */
    fun flattenList(head: LinkedListCreation.ListNode?): LinkedListCreation.ListNode? {
        if (head?.next == null) return head
        
        var current = head
        while (current != null) {
            if (current.next == null) {
                current.next = current.next
                break
            }
            current = current.next
        }
        
        return head
    }
    
    /**
     * Partitions a linked list around a value x
     */
    fun partitionList(head: LinkedListCreation.ListNode?, x: Int): LinkedListCreation.ListNode? {
        val beforeHead = LinkedListCreation.ListNode(0)
        val afterHead = LinkedListCreation.ListNode(0)
        var before = beforeHead
        var after = afterHead
        var current = head
        
        while (current != null) {
            if (current.value < x) {
                before.next = current
                before = before.next!!
            } else {
                after.next = current
                after = after.next!!
            }
            current = current.next
        }
        
        after.next = null
        before.next = afterHead.next
        
        return beforeHead.next
    }
    
    /**
     * Removes duplicates from a sorted linked list
     */
    fun removeDuplicatesFromSorted(head: LinkedListCreation.ListNode?): LinkedListCreation.ListNode? {
        var current = head
        
        while (current?.next != null) {
            if (current.value == current.next?.value) {
                current.next = current.next?.next
            } else {
                current = current.next
            }
        }
        
        return head
    }
    
    /**
     * Removes duplicates from an unsorted linked list
     */
    fun removeDuplicatesFromUnsorted(head: LinkedListCreation.ListNode?): LinkedListCreation.ListNode? {
        val seen = mutableSetOf<Int>()
        val dummy = LinkedListCreation.ListNode(0)
        dummy.next = head
        var current = dummy
        
        while (current.next != null) {
            if (seen.contains(current.next?.value)) {
                current.next = current.next?.next
            } else {
                seen.add(current.next?.value!!)
                current = current.next!!
            }
        }
        
        return dummy.next
    }
    
    /**
     * Finds the middle node of a linked list
     */
    fun findMiddleNode(head: LinkedListCreation.ListNode?): LinkedListCreation.ListNode? {
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
     * Checks if a linked list has even number of nodes
     */
    fun hasEvenNodes(head: LinkedListCreation.ListNode?): Boolean {
        var current = head
        var count = 0
        
        while (current != null) {
            count++
            current = current.next
        }
        
        return count % 2 == 0
    }
    
    /**
     * Demonstrates linked list algorithms
     */
    fun demonstrateLinkedListAlgorithms() {
        println("=== LINKED LIST ALGORITHMS DEMONSTRATION ===\n")
        
        // 1. Cycle detection
        println("1. CYCLE DETECTION")
        val normalList = LinkedListCreation.createTestLinkedList()
        val cycleList = LinkedListCreation.createLinkedListWithCycle(intArrayOf(1, 2, 3, 4, 5), 2)
        
        println("Normal list has cycle: ${hasCycle(normalList)}")
        println("Cycle list has cycle: ${hasCycle(cycleList)}")
        
        val cycleStart = detectCycleStart(cycleList)
        println("Cycle starts at node with value: ${cycleStart?.value}")
        println()
        
        // 2. Merge operations
        println("2. MERGE OPERATIONS")
        val list1 = LinkedListCreation.createSinglyLinkedList(intArrayOf(1, 3, 5))
        val list2 = LinkedListCreation.createSinglyLinkedList(intArrayOf(2, 4, 6))
        
        println("List 1:")
        LinkedListOperations.printList(list1)
        println("List 2:")
        LinkedListOperations.printList(list2)
        
        val merged = mergeTwoLists(list1, list2)
        println("Merged list:")
        LinkedListOperations.printList(merged)
        println()
        
        // 3. Palindrome check
        println("3. PALINDROME CHECK")
        val palindromeList = LinkedListCreation.createPalindromeTestLinkedList()
        val nonPalindromeList = LinkedListCreation.createTestLinkedList()
        
        println("Palindrome list:")
        LinkedListOperations.printList(palindromeList)
        println("Is palindrome: ${isPalindrome(palindromeList)}")
        
        println("Non-palindrome list:")
        LinkedListOperations.printList(nonPalindromeList)
        println("Is palindrome: ${isPalindrome(nonPalindromeList)}")
        println()
        
        // 4. Remove nth from end
        println("4. REMOVE NTH FROM END")
        val testList = LinkedListCreation.createTestLinkedList()
        println("Original list:")
        LinkedListOperations.printList(testList)
        
        val removedList = removeNthFromEnd(testList, 2)
        println("After removing 2nd from end:")
        LinkedListOperations.printList(removedList)
        println()
        
        // 5. Intersection detection
        println("5. INTERSECTION DETECTION")
        val (intersectionList1, intersectionList2) = LinkedListCreation.createIntersectionTestLists()
        
        println("List 1:")
        LinkedListOperations.printList(intersectionList1)
        println("List 2:")
        LinkedListOperations.printList(intersectionList2)
        
        val intersectionNode = getIntersectionNode(intersectionList1, intersectionList2)
        println("Intersection node value: ${intersectionNode?.value}")
        println()
        
        // 6. Sorting
        println("6. SORTING")
        val unsortedList = LinkedListCreation.createSinglyLinkedList(intArrayOf(5, 2, 8, 1, 9))
        println("Unsorted list:")
        LinkedListOperations.printList(unsortedList)
        
        val sortedList = sortList(unsortedList)
        println("Sorted list:")
        LinkedListOperations.printList(sortedList)
        println()
        
        // 7. Reordering
        println("7. REORDERING")
        val reorderList = LinkedListCreation.createSinglyLinkedList(intArrayOf(1, 2, 3, 4, 5))
        println("Original list:")
        LinkedListOperations.printList(reorderList)
        
        val reordered = reorderList(reorderList)
        println("Reordered list:")
        LinkedListOperations.printList(reordered)
        println()
        
        // 8. Partitioning
        println("8. PARTITIONING")
        val partitionList = LinkedListCreation.createSinglyLinkedList(intArrayOf(3, 5, 8, 5, 10, 2, 1))
        println("Original list:")
        LinkedListOperations.printList(partitionList)
        
        val partitioned = partitionList(partitionList, 5)
        println("Partitioned around 5:")
        LinkedListOperations.printList(partitioned)
        println()
        
        // 9. Duplicate removal
        println("9. DUPLICATE REMOVAL")
        val duplicateList = LinkedListCreation.createSinglyLinkedList(intArrayOf(1, 1, 2, 3, 3, 4, 5, 5))
        println("List with duplicates:")
        LinkedListOperations.printList(duplicateList)
        
        val noDuplicates = removeDuplicatesFromSorted(duplicateList)
        println("After removing duplicates:")
        LinkedListOperations.printList(noDuplicates)
        println()
        
        // 10. Middle node
        println("10. MIDDLE NODE")
        val middleTestList = LinkedListCreation.createSinglyLinkedList(intArrayOf(1, 2, 3, 4, 5, 6))
        println("List:")
        LinkedListOperations.printList(middleTestList)
        
        val middleNode = findMiddleNode(middleTestList)
        println("Middle node value: ${middleNode?.value}")
        println("Has even number of nodes: ${hasEvenNodes(middleTestList)}")
        println()
        
        println("=== LINKED LIST ALGORITHMS DEMONSTRATION COMPLETE ===\n")
    }
} 