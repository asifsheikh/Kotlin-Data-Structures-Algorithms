package data_structure.stacks

/**
 * STACK OPERATIONS
 * 
 * This file contains standard operations on stacks including
 * pushing, popping, peeking, and other basic stack operations.
 * 
 * COMMON STACK OPERATIONS:
 * - Push: Add element to top of stack
 * - Pop: Remove and return top element
 * - Peek: View top element without removing
 * - Size: Get number of elements
 * - Empty: Check if stack is empty
 * - Clear: Remove all elements
 */

object StackOperations {
    
    /**
     * Pushes an element onto the top of the stack
     * 
     * ALGORITHM:
     * 1. Add element to the end of the ArrayDeque
     * 2. Element becomes the new top of the stack
     * 
     * TIME COMPLEXITY: O(1) - Constant time addition
     * SPACE COMPLEXITY: O(1) - Only stores one additional element
     * 
     * @param stack The stack to push element onto
     * @param element The element to push
     */
    fun <T> push(stack: ArrayDeque<T>, element: T) {
        stack.addLast(element)
    }
    
    /**
     * Pops (removes and returns) the top element from the stack
     * 
     * ALGORITHM:
     * 1. Remove and return the last element from ArrayDeque
     * 2. Previous element becomes the new top
     * 
     * TIME COMPLEXITY: O(1) - Constant time removal
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param stack The stack to pop from
     * @return The top element, or null if stack is empty
     */
    fun <T> pop(stack: ArrayDeque<T>): T? {
        return if (stack.isNotEmpty()) stack.removeLast() else null
    }
    
    /**
     * Peeks at the top element without removing it
     * 
     * ALGORITHM:
     * 1. Return the last element from ArrayDeque without removing
     * 2. Stack remains unchanged
     * 
     * TIME COMPLEXITY: O(1) - Constant time access
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param stack The stack to peek at
     * @return The top element, or null if stack is empty
     */
    fun <T> peek(stack: ArrayDeque<T>): T? {
        return stack.lastOrNull()
    }
    
    /**
     * Gets the size of the stack
     * 
     * ALGORITHM:
     * 1. Return the size property of ArrayDeque
     * 
     * TIME COMPLEXITY: O(1) - Direct access to size property
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param stack The stack to get size of
     * @return Number of elements in the stack
     */
    fun <T> size(stack: ArrayDeque<T>): Int {
        return stack.size
    }
    
    /**
     * Checks if the stack is empty
     * 
     * ALGORITHM:
     * 1. Check if size is zero
     * 
     * TIME COMPLEXITY: O(1) - Direct access to size property
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param stack The stack to check
     * @return true if stack is empty, false otherwise
     */
    fun <T> isEmpty(stack: ArrayDeque<T>): Boolean {
        return stack.isEmpty()
    }
    
    /**
     * Checks if the stack is not empty
     * 
     * ALGORITHM:
     * 1. Check if size is greater than zero
     * 
     * TIME COMPLEXITY: O(1) - Direct access to size property
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param stack The stack to check
     * @return true if stack is not empty, false otherwise
     */
    fun <T> isNotEmpty(stack: ArrayDeque<T>): Boolean {
        return stack.isNotEmpty()
    }
    
    /**
     * Clears all elements from the stack
     * 
     * ALGORITHM:
     * 1. Remove all elements from ArrayDeque
     * 
     * TIME COMPLEXITY: O(n) - Need to remove all n elements
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param stack The stack to clear
     */
    fun <T> clear(stack: ArrayDeque<T>) {
    stack.clear()
    }
    
    /**
     * Checks if the stack contains a specific element
     * 
     * ALGORITHM:
     * 1. Search through all elements in the stack
     * 2. Return true if element is found, false otherwise
     * 
     * TIME COMPLEXITY: O(n) - Linear search through all elements
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param stack The stack to search in
     * @param element The element to search for
     * @return true if element is found, false otherwise
     */
    fun <T> contains(stack: ArrayDeque<T>, element: T): Boolean {
        return stack.contains(element)
    }
    
    /**
     * Removes a specific element from the stack
     * 
     * ALGORITHM:
     * 1. Find the element in the stack
     * 2. Remove the first occurrence found
     * 
     * TIME COMPLEXITY: O(n) - Linear search to find element
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param stack The stack to remove element from
     * @param element The element to remove
     * @return true if element was found and removed, false otherwise
     */
    fun <T> remove(stack: ArrayDeque<T>, element: T): Boolean {
        return stack.remove(element)
    }
    
    /**
     * Converts the stack to a list
     * 
     * ALGORITHM:
     * 1. Create a new list containing all elements from the stack
     * 2. Order is maintained (bottom to top)
     * 
     * TIME COMPLEXITY: O(n) - Need to copy all n elements
     * SPACE COMPLEXITY: O(n) - Creates a new list with n elements
     * 
     * @param stack The stack to convert
     * @return List containing all stack elements (bottom to top)
     */
    fun <T> toList(stack: ArrayDeque<T>): List<T> {
        return stack.toList()
    }
    
    /**
     * Converts the stack to an array
     * 
     * ALGORITHM:
     * 1. Create a new array containing all elements from the stack
     * 2. Order is maintained (bottom to top)
     * 
     * TIME COMPLEXITY: O(n) - Need to copy all n elements
     * SPACE COMPLEXITY: O(n) - Creates a new array with n elements
     * 
     * @param stack The stack to convert
     * @return Array containing all stack elements (bottom to top)
     */
    inline fun <reified T> toArray(stack: ArrayDeque<T>): Array<T> {
        return stack.toTypedArray()
    }
    
    /**
     * Gets all elements from the stack as a list (top to bottom)
     * 
     * ALGORITHM:
     * 1. Create a new list with elements in reverse order
     * 2. Top element becomes first in the list
     * 
     * TIME COMPLEXITY: O(n) - Need to copy all n elements
     * SPACE COMPLEXITY: O(n) - Creates a new list with n elements
     * 
     * @param stack The stack to get elements from
     * @return List containing all stack elements (top to bottom)
     */
    fun <T> toListTopToBottom(stack: ArrayDeque<T>): List<T> {
        return stack.reversed().toList()
    }
    
    /**
     * Adds multiple elements to the stack
     * 
     * ALGORITHM:
     * 1. Add each element from the collection to the stack
     * 2. Elements are added in order (first element becomes bottom)
     * 
     * TIME COMPLEXITY: O(m) - m elements to add
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param stack The stack to add elements to
     * @param elements Collection of elements to add
     */
    fun <T> addAll(stack: ArrayDeque<T>, elements: Collection<T>) {
        stack.addAll(elements)
    }
    
    /**
     * Pushes multiple elements onto the stack in reverse order
     * 
     * ALGORITHM:
     * 1. Add elements in reverse order
     * 2. Last element from collection becomes top of stack
     * 
     * TIME COMPLEXITY: O(m) - m elements to add
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param stack The stack to push elements onto
     * @param elements Collection of elements to push
     */
    fun <T> pushAll(stack: ArrayDeque<T>, elements: Collection<T>) {
        for (element in elements.reversed()) {
            stack.addLast(element)
        }
    }
    
    /**
     * Gets the element at a specific position from the top
     * 
     * ALGORITHM:
     * 1. Calculate position from the end of ArrayDeque
     * 2. Return element at that position
     * 
     * TIME COMPLEXITY: O(1) - Direct access to element
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param stack The stack to get element from
     * @param position Position from top (0 = top, 1 = second from top, etc.)
     * @return Element at the specified position, or null if position is invalid
     */
    fun <T> getFromTop(stack: ArrayDeque<T>, position: Int): T? {
        return if (position >= 0 && position < stack.size) {
            stack.elementAt(stack.size - 1 - position)
        } else null
    }
    
    /**
     * Gets the element at a specific position from the bottom
     * 
     * ALGORITHM:
     * 1. Return element at the specified position from the beginning
     * 
     * TIME COMPLEXITY: O(1) - Direct access to element
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param stack The stack to get element from
     * @param position Position from bottom (0 = bottom, 1 = second from bottom, etc.)
     * @return Element at the specified position, or null if position is invalid
     */
    fun <T> getFromBottom(stack: ArrayDeque<T>, position: Int): T? {
        return if (position >= 0 && position < stack.size) {
            stack.elementAt(position)
        } else null
    }
    
    /**
     * Iterates over the stack from bottom to top
     * 
     * ALGORITHM:
     * 1. Iterate through elements in natural order
     * 
     * TIME COMPLEXITY: O(n) - Visit all n elements
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param stack The stack to iterate over
     * @param action Function to perform on each element
     */
    fun <T> iterateBottomToTop(stack: ArrayDeque<T>, action: (T) -> Unit) {
        for (element in stack) {
            action(element)
        }
    }
    
    /**
     * Iterates over the stack from top to bottom
     * 
     * ALGORITHM:
     * 1. Iterate through elements in reverse order
     * 
     * TIME COMPLEXITY: O(n) - Visit all n elements
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param stack The stack to iterate over
     * @param action Function to perform on each element
     */
    fun <T> iterateTopToBottom(stack: ArrayDeque<T>, action: (T) -> Unit) {
        for (element in stack.reversed()) {
            action(element)
        }
    }
    
    /**
     * Creates a copy of the stack
     * 
     * ALGORITHM:
     * 1. Create a new ArrayDeque with all elements from the original
     * 
     * TIME COMPLEXITY: O(n) - Need to copy all n elements
     * SPACE COMPLEXITY: O(n) - Creates a new stack with n elements
     * 
     * @param stack The stack to copy
     * @return A new stack with the same elements
     */
    fun <T> copy(stack: ArrayDeque<T>): ArrayDeque<T> {
        return ArrayDeque(stack)
    }
    
    /**
     * Reverses the order of elements in the stack
     * 
     * ALGORITHM:
     * 1. Create a new stack with elements in reverse order
     * 2. Top becomes bottom and vice versa
     * 
     * TIME COMPLEXITY: O(n) - Need to process all n elements
     * SPACE COMPLEXITY: O(n) - Creates a new stack with n elements
     * 
     * @param stack The stack to reverse
     * @return A new stack with reversed order
     */
    fun <T> reverse(stack: ArrayDeque<T>): ArrayDeque<T> {
        return ArrayDeque(stack.reversed())
    }
    
    /**
     * Demonstrates standard stack operations
     */
    fun demonstrateStackOperations() {
        println("=== STACK OPERATIONS DEMONSTRATION ===\n")
        
        // Create a stack for demonstration
        val stack = StackCreation.createStackFromList(listOf(1, 2, 3, 4, 5))
        
        // 1. Basic operations
        println("1. BASIC OPERATIONS")
        println("Initial stack: $stack")
        println("Size: ${size<Int>(stack)}")
        println("Is empty: ${isEmpty<Int>(stack)}")
        println("Top element: ${peek<Int>(stack)}")
        println()
        
        // 2. Push and pop operations
        println("2. PUSH AND POP OPERATIONS")
        push<Int>(stack, 6)
        println("After pushing 6: $stack")
        val popped = pop<Int>(stack)
        println("Popped element: $popped")
        println("Stack after pop: $stack")
        println()
        
        // 3. Search operations
        println("3. SEARCH OPERATIONS")
        println("Contains 3: ${contains<Int>(stack, 3)}")
        println("Contains 10: ${contains<Int>(stack, 10)}")
        val removed = remove<Int>(stack, 3)
        println("Removed 3: $removed")
        println("Stack after removal: $stack")
        println()
        
        // 4. Access operations
        println("4. ACCESS OPERATIONS")
        println("Element at position 0 from top: ${getFromTop<Int>(stack, 0)}")
        println("Element at position 1 from top: ${getFromTop<Int>(stack, 1)}")
        println("Element at position 0 from bottom: ${getFromBottom<Int>(stack, 0)}")
        println("Element at position 1 from bottom: ${getFromBottom<Int>(stack, 1)}")
        println()
        
        // 5. Conversion operations
        println("5. CONVERSION OPERATIONS")
        val asList = toList<Int>(stack)
        println("Stack as list (bottom to top): $asList")
        val asListTopToBottom = toListTopToBottom<Int>(stack)
        println("Stack as list (top to bottom): $asListTopToBottom")
        println()
        
        // 6. Iteration operations
        println("6. ITERATION OPERATIONS")
        print("Iterating bottom to top: ")
        iterateBottomToTop(stack) { print("$it ") }
        println()
        print("Iterating top to bottom: ")
        iterateTopToBottom(stack) { print("$it ") }
        println()
        println()
        
        // 7. Copy and reverse operations
        println("7. COPY AND REVERSE OPERATIONS")
        val copiedStack = copy(stack)
        println("Copied stack: $copiedStack")
        val reversedStack = reverse(stack)
        println("Reversed stack: $reversedStack")
        println()
        
        // 8. Bulk operations
        println("8. BULK OPERATIONS")
        addAll(stack, listOf(7, 8, 9))
        println("After adding [7,8,9]: $stack")
        clear(stack)
        println("After clearing: $stack")
        println("Is empty: ${isEmpty(stack)}")
        println()
        
        println("=== STACK OPERATIONS DEMONSTRATION COMPLETE ===\n")
    }
}