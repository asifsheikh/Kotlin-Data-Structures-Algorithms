package data_structure.stacks

/**
 * STACK CREATION
 * 
 * This file contains functions for creating different types of stacks
 * and various stack creation patterns.
 * 
 * STACK PROPERTIES:
 * - LIFO (Last In, First Out) data structure
 * - Elements are added and removed from the same end (top)
 * - Efficient for operations at the top of the stack
 * - Used for function call management, undo operations, etc.
 * 
 * IMPLEMENTATION:
 * - Uses ArrayDeque for efficient operations
 * - Supports generic types
 * - Provides both mutable and immutable variants
 */

object StackCreation {
    
    /**
     * Creates a basic stack using ArrayDeque
     * 
     * ALGORITHM:
     * 1. Initialize an empty ArrayDeque
     * 2. Use addLast() for push operations
     * 3. Use removeLast() for pop operations
     * 
     * TIME COMPLEXITY: O(1) - Constant time initialization
     * SPACE COMPLEXITY: O(1) - Only stores the reference
     * 
     * @return Empty stack ready for operations
     */
    fun <T> createBasicStack(): ArrayDeque<T> {
        return ArrayDeque()
    }
    
    /**
     * Creates a stack with initial capacity
     * 
     * ALGORITHM:
     * 1. Initialize ArrayDeque with specified capacity
     * 2. Pre-allocates memory for better performance
     * 
     * TIME COMPLEXITY: O(1) - Constant time initialization
     * SPACE COMPLEXITY: O(capacity) - Pre-allocates memory
     * 
     * @param capacity Initial capacity of the stack
     * @return Empty stack with specified capacity
     */
    fun <T> createStackWithCapacity(capacity: Int): ArrayDeque<T> {
        return ArrayDeque(capacity)
    }
    
    /**
     * Creates a stack from a list of elements
     * 
     * ALGORITHM:
     * 1. Initialize empty ArrayDeque
     * 2. Add each element from the list to the stack
     * 3. Elements are added in order (first element becomes bottom)
     * 
     * TIME COMPLEXITY: O(n) - n elements to add
     * SPACE COMPLEXITY: O(n) - stores all n elements
     * 
     * @param elements List of elements to add to stack
     * @return Stack containing all elements from the list
     */
    fun <T> createStackFromList(elements: List<T>): ArrayDeque<T> {
        val stack = ArrayDeque<T>()
        for (element in elements) {
            stack.addLast(element)
        }
        return stack
    }
    
    /**
     * Creates a stack from an array of elements
     * 
     * ALGORITHM:
     * 1. Initialize empty ArrayDeque
     * 2. Add each element from the array to the stack
     * 3. Elements are added in order (first element becomes bottom)
     * 
     * TIME COMPLEXITY: O(n) - n elements to add
     * SPACE COMPLEXITY: O(n) - stores all n elements
     * 
     * @param elements Array of elements to add to stack
     * @return Stack containing all elements from the array
     */
    fun <T> createStackFromArray(elements: Array<T>): ArrayDeque<T> {
        val stack = ArrayDeque<T>()
        for (element in elements) {
            stack.addLast(element)
        }
        return stack
    }
    
    /**
     * Creates a stack with elements in reverse order
     * 
     * ALGORITHM:
     * 1. Initialize empty ArrayDeque
     * 2. Add elements in reverse order
     * 3. Last element from input becomes top of stack
     * 
     * TIME COMPLEXITY: O(n) - n elements to add
     * SPACE COMPLEXITY: O(n) - stores all n elements
     * 
     * @param elements List of elements to add to stack in reverse
     * @return Stack with elements in reverse order
     */
    fun <T> createReversedStack(elements: List<T>): ArrayDeque<T> {
        val stack = ArrayDeque<T>()
        for (element in elements.reversed()) {
            stack.addLast(element)
        }
        return stack
    }
    
    /**
     * Creates a stack for specific data types with validation
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
     * @return Stack containing only valid elements
     */
    fun <T> createValidatedStack(elements: List<T>, validator: (T) -> Boolean): ArrayDeque<T> {
        val stack = ArrayDeque<T>()
        for (element in elements) {
            if (validator(element)) {
                stack.addLast(element)
            }
        }
        return stack
    }
    
    /**
     * Creates a stack with limited size (bounded stack)
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
     * @param limit Maximum number of elements in stack
     * @return Bounded stack with at most 'limit' elements
     */
    fun <T> createBoundedStack(elements: List<T>, limit: Int): ArrayDeque<T> {
        val stack = ArrayDeque<T>(limit)
        for (element in elements.take(limit)) {
            stack.addLast(element)
        }
        return stack
    }
    
    /**
     * Creates a stack for number operations
     * 
     * ALGORITHM:
     * 1. Initialize empty ArrayDeque for numbers
     * 2. Add numeric elements
     * 3. Useful for mathematical operations
     * 
     * TIME COMPLEXITY: O(n) - n numbers to add
     * SPACE COMPLEXITY: O(n) - stores all n numbers
     * 
     * @param numbers List of numbers to add to stack
     * @return Stack containing numeric elements
     */
    fun createNumberStack(numbers: List<Number>): ArrayDeque<Number> {
        return createStackFromList(numbers)
    }
    
    /**
     * Creates a stack for character operations
     * 
     * ALGORITHM:
     * 1. Initialize empty ArrayDeque for characters
     * 2. Add character elements
     * 3. Useful for string processing
     * 
     * TIME COMPLEXITY: O(n) - n characters to add
     * SPACE COMPLEXITY: O(n) - stores all n characters
     * 
     * @param chars List of characters to add to stack
     * @return Stack containing character elements
     */
    fun createCharStack(chars: List<Char>): ArrayDeque<Char> {
        return createStackFromList(chars)
    }
    
    /**
     * Creates a stack from a string (character by character)
     * 
     * ALGORITHM:
     * 1. Initialize empty ArrayDeque for characters
     * 2. Add each character from the string
     * 3. First character becomes bottom of stack
     * 
     * TIME COMPLEXITY: O(n) - n characters in string
     * SPACE COMPLEXITY: O(n) - stores all n characters
     * 
     * @param str String to convert to character stack
     * @return Stack containing all characters from the string
     */
    fun createStackFromString(str: String): ArrayDeque<Char> {
        val stack = ArrayDeque<Char>()
        for (char in str) {
            stack.addLast(char)
        }
        return stack
    }
    
    /**
     * Creates a stack with custom comparator for ordering
     * 
     * ALGORITHM:
     * 1. Initialize empty ArrayDeque
     * 2. Add elements in custom order based on comparator
     * 3. Maintains specified ordering
     * 
     * TIME COMPLEXITY: O(n log n) - sorting n elements
     * SPACE COMPLEXITY: O(n) - stores all n elements
     * 
     * @param elements List of elements to add
     * @param comparator Function to compare elements
     * @return Stack with elements ordered by comparator
     */
    fun <T> createOrderedStack(elements: List<T>, comparator: Comparator<T>): ArrayDeque<T> {
        val sortedElements = elements.sortedWith(comparator)
        return createStackFromList(sortedElements)
    }
    
    /**
     * Demonstrates stack creation patterns
     */
    fun demonstrateStackCreation() {
        println("=== STACK CREATION DEMONSTRATION ===\n")
        
        // 1. Basic stack creation
        println("1. BASIC STACK CREATION")
        val basicStack = createBasicStack<Int>()
        println("Basic stack created: $basicStack")
        println()
        
        // 2. Stack with capacity
        println("2. STACK WITH CAPACITY")
        val capacityStack = createStackWithCapacity<Int>(10)
        println("Stack with capacity 10 created: $capacityStack")
        println()
        
        // 3. Stack from list
        println("3. STACK FROM LIST")
        val listStack = createStackFromList(listOf(1, 2, 3, 4, 5))
        println("Stack from list [1,2,3,4,5]: $listStack")
        println()
        
        // 4. Reversed stack
        println("4. REVERSED STACK")
        val reversedStack = createReversedStack(listOf(1, 2, 3, 4, 5))
        println("Reversed stack from [1,2,3,4,5]: $reversedStack")
        println()
        
        // 5. Validated stack
        println("5. VALIDATED STACK")
        val validatedStack = createValidatedStack(listOf(1, -2, 3, -4, 5)) { it > 0 }
        println("Validated stack (positive numbers only): $validatedStack")
        println()
        
        // 6. Bounded stack
        println("6. BOUNDED STACK")
        val boundedStack = createBoundedStack(listOf(1, 2, 3, 4, 5, 6, 7), 3)
        println("Bounded stack (limit 3): $boundedStack")
        println()
        
        // 7. Number stack
        println("7. NUMBER STACK")
        val numberStack = createNumberStack(listOf(1, 2.5, 3, 4.7, 5))
        println("Number stack: $numberStack")
        println()
        
        // 8. Character stack
        println("8. CHARACTER STACK")
        val charStack = createCharStack(listOf('a', 'b', 'c', 'd'))
        println("Character stack: $charStack")
        println()
        
        // 9. String stack
        println("9. STRING STACK")
        val stringStack = createStackFromString("hello")
        println("String stack from 'hello': $stringStack")
        println()
        
        // 10. Ordered stack
        println("10. ORDERED STACK")
        val orderedStack = createOrderedStack(listOf(5, 2, 8, 1, 9)) { a, b -> b.compareTo(a) }
        println("Ordered stack (descending): $orderedStack")
        println()
        
        println("=== STACK CREATION DEMONSTRATION COMPLETE ===\n")
    }
} 