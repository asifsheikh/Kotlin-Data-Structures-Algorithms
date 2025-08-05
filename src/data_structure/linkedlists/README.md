# Linked List Data Structure

This directory contains the linked list data structure implementation broken down into multiple files for better organization and navigation.

## File Structure

### 1. `LinkedListCreation.kt`
Contains functions for creating different types of linked lists:
- **Basic Creation**: `createSinglyLinkedList()`, `createDoublyLinkedList()`
- **Special Patterns**: `createLinkedListWithCycle()`, `createPalindromeLinkedList()`
- **Sequences**: `createArithmeticLinkedList()`, `createFibonacciLinkedList()`, `createPrimeLinkedList()`
- **Patterns**: `createPatternLinkedList()`, `createAlternatingLinkedList()`
- **Random**: `createRandomLinkedList()`, `createSortedLinkedList()`
- **Test Lists**: `createTestLinkedList()`, `createIntersectionTestLists()`

### 2. `LinkedListOperations.kt`
Contains standard operations on linked lists:
- **Printing**: `printList()`, `printDoublyList()`
- **Insertion**: `insertAtBeginning()`, `insertAtEnd()`, `insertAtPosition()`
- **Deletion**: `deleteNode()`, `deleteAtPosition()`
- **Search & Access**: `search()`, `getLength()`, `getNthNode()`, `getMiddleNode()`, `getLastNode()`
- **Conversion**: `linkedListToArray()`, `linkedListToList()`
- **Reversal**: `reverseList()`, `reverseListRecursive()`, `reverseKGroup()`
- **Advanced**: `swapPairs()`, `rotateList()`

### 3. `LinkedListAlgorithms.kt`
Contains algorithms that use linked lists:
- **Cycle Detection**: `hasCycle()`, `detectCycleStart()`, `removeCycle()`
- **Merge Operations**: `mergeTwoLists()`, `mergeKLists()`
- **Palindrome**: `isPalindrome()`
- **Node Removal**: `removeNthFromEnd()`
- **Intersection**: `getIntersectionNode()`
- **Sorting**: `sortList()` (merge sort)
- **Reordering**: `reorderList()`
- **Partitioning**: `partitionList()`
- **Duplicate Removal**: `removeDuplicatesFromSorted()`, `removeDuplicatesFromUnsorted()`
- **Analysis**: `findMiddleNode()`, `hasEvenNodes()`

### 4. `LinkedListDemo.kt`
Coordinates all linked list functionality for demonstration:
- `demonstrateLinkedListAlgorithms()` - Shows all linked list capabilities

## Quick Reference

### Linked List Types
- **Singly Linked List**: Each node has a value and a reference to the next node
- **Doubly Linked List**: Each node has a value and references to both next and previous nodes
- **Circular Linked List**: Last node points back to the first node

### Common Operations
```kotlin
// Creation
val list = LinkedListCreation.createSinglyLinkedList(intArrayOf(1, 2, 3, 4, 5))

// Basic Operations
LinkedListOperations.insertAtBeginning(list, 0)  // Add at start
LinkedListOperations.insertAtEnd(list, 6)       // Add at end
LinkedListOperations.deleteNode(list, 3)        // Remove by value
LinkedListOperations.search(list, 4)            // Search for value
LinkedListOperations.getLength(list)            // Get size

// Reversal
val reversed = LinkedListOperations.reverseList(list)

// Algorithms
LinkedListAlgorithms.hasCycle(list)             // Check for cycles
LinkedListAlgorithms.isPalindrome(list)         // Check if palindrome
```

### Time Complexity
- **Access**: O(n) - Need to traverse from head
- **Search**: O(n) - Linear search
- **Insertion**: O(1) at beginning, O(n) at end/position
- **Deletion**: O(1) at beginning, O(n) at end/position
- **Reverse**: O(n)
- **Cycle Detection**: O(n) using Floyd's algorithm

### Space Complexity
- **Storage**: O(n) for n nodes
- **Auxiliary Space**: O(1) for most operations, O(n) for recursive operations

## Common Patterns

### Two Pointers (Slow/Fast)
```kotlin
var slow = head
var fast = head
while (fast?.next != null) {
    slow = slow?.next
    fast = fast.next?.next
}
// slow points to middle
```

### Dummy Node Technique
```kotlin
val dummy = LinkedListCreation.ListNode(0)
dummy.next = head
var current = dummy
// ... operations
return dummy.next
```

### Floyd's Cycle Detection
```kotlin
var slow = head
var fast = head
while (fast?.next != null) {
    slow = slow?.next
    fast = fast.next?.next
    if (slow == fast) return true // cycle detected
}
```

## Usage Example

```kotlin
// Create and manipulate a linked list
val list = LinkedListCreation.createTestLinkedList()
LinkedListOperations.printList(list) // 1 -> 2 -> 3 -> 4 -> 5 -> null

// Insert operations
val modified = LinkedListOperations.insertAtBeginning(list, 0)
LinkedListOperations.printList(modified) // 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> null

// Algorithm operations
val hasCycle = LinkedListAlgorithms.hasCycle(modified) // false
val isPalindrome = LinkedListAlgorithms.isPalindrome(modified) // false

// Reverse the list
val reversed = LinkedListOperations.reverseList(modified)
LinkedListOperations.printList(reversed) // 5 -> 4 -> 3 -> 2 -> 1 -> 0 -> null
``` 