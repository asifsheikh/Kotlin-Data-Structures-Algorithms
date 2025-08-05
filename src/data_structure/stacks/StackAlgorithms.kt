package data_structure.stacks

/**
 * STACK ALGORITHMS
 * 
 * This file contains algorithms that use stacks including
 * string reversal, parentheses matching, postfix evaluation,
 * and other stack-based algorithms.
 * 
 * COMMON STACK ALGORITHMS:
 * - String Reversal: Reverse characters using stack
 * - Parentheses Matching: Check balanced parentheses
 * - Postfix Evaluation: Evaluate postfix expressions
 * - Infix to Postfix: Convert infix to postfix notation
 * - Undo/Redo: Implement undo/redo functionality
 * - Browser History: Implement back/forward navigation
 */

object StackAlgorithms {
    
    /**
     * Reverses a string using stack
     * 
     * ALGORITHM:
     * 1. Push each character onto the stack
     * 2. Pop characters from stack to get reverse order
     * 3. Build reversed string from popped characters
     * 
     * TIME COMPLEXITY: O(n) - n characters to process
     * SPACE COMPLEXITY: O(n) - stack stores all n characters
     * 
     * @param str String to reverse
     * @return Reversed string
     */
    fun reverseString(str: String): String {
        val stack = StackCreation.createStackFromString(str)
        val result = StringBuilder()
        
        while (stack.isNotEmpty()) {
            result.append(StackOperations.pop(stack))
        }
        
        return result.toString()
    }
    
    /**
     * Checks if parentheses are balanced
     * 
     * ALGORITHM:
     * 1. Push opening parentheses onto stack
     * 2. When closing parenthesis found, pop from stack
     * 3. Check if popped parenthesis matches current one
     * 4. Stack should be empty at the end
     * 
     * TIME COMPLEXITY: O(n) - n characters to process
     * SPACE COMPLEXITY: O(n) - worst case when all are opening parentheses
     * 
     * @param str String containing parentheses
     * @return true if parentheses are balanced, false otherwise
     */
    fun isBalancedParentheses(str: String): Boolean {
        val stack = StackCreation.createBasicStack<Char>()
        
        for (char in str) {
            when (char) {
                '(', '[', '{' -> StackOperations.push(stack, char)
                ')' -> {
                    if (StackOperations.isEmpty(stack) || StackOperations.pop(stack) != '(') {
                        return false
                    }
                }
                ']' -> {
                    if (StackOperations.isEmpty(stack) || StackOperations.pop(stack) != '[') {
                        return false
                    }
                }
                '}' -> {
                    if (StackOperations.isEmpty(stack) || StackOperations.pop(stack) != '{') {
                        return false
                    }
                }
            }
        }
        
        return StackOperations.isEmpty(stack)
    }
    
    /**
     * Evaluates a postfix expression
     * 
     * ALGORITHM:
     * 1. Scan expression from left to right
     * 2. Push operands onto stack
     * 3. When operator found, pop two operands and apply operator
     * 4. Push result back onto stack
     * 5. Final result is on top of stack
     * 
     * TIME COMPLEXITY: O(n) - n tokens to process
     * SPACE COMPLEXITY: O(n) - stack stores operands
     * 
     * @param expression Postfix expression as string
     * @return Result of the expression
     */
    fun evaluatePostfix(expression: String): Double {
        val stack = StackCreation.createBasicStack<Double>()
        
        for (token in expression.split(" ")) {
            when (token) {
                "+" -> {
                    val b = StackOperations.pop(stack)!!
                    val a = StackOperations.pop(stack)!!
                    StackOperations.push(stack, a + b)
                }
                "-" -> {
                    val b = StackOperations.pop(stack)!!
                    val a = StackOperations.pop(stack)!!
                    StackOperations.push(stack, a - b)
                }
                "*" -> {
                    val b = StackOperations.pop(stack)!!
                    val a = StackOperations.pop(stack)!!
                    StackOperations.push(stack, a * b)
                }
                "/" -> {
                    val b = StackOperations.pop(stack)!!
                    val a = StackOperations.pop(stack)!!
                    StackOperations.push(stack, a / b)
                }
                else -> {
                    StackOperations.push(stack, token.toDouble())
                }
            }
        }
        
        return StackOperations.pop(stack)!!
    }
    
    /**
     * Converts infix expression to postfix
     * 
     * ALGORITHM:
     * 1. Scan infix expression from left to right
     * 2. If operand, add to output
     * 3. If operator, push to stack based on precedence
     * 4. If opening parenthesis, push to stack
     * 5. If closing parenthesis, pop operators until opening parenthesis
     * 
     * TIME COMPLEXITY: O(n) - n tokens to process
     * SPACE COMPLEXITY: O(n) - stack stores operators
     * 
     * @param infix Infix expression as string
     * @return Postfix expression as string
     */
    fun infixToPostfix(infix: String): String {
        val stack = StackCreation.createBasicStack<Char>()
        val output = StringBuilder()
        
        for (char in infix) {
            when {
                char.isLetterOrDigit() -> output.append(char)
                char == '(' -> StackOperations.push(stack, char)
                char == ')' -> {
                    while (StackOperations.isNotEmpty(stack) && StackOperations.peek(stack) != '(') {
                        output.append(StackOperations.pop(stack))
                    }
                    if (StackOperations.isNotEmpty(stack) && StackOperations.peek(stack) == '(') {
                        StackOperations.pop(stack) // Remove '('
                    }
                }
                else -> {
                    while (StackOperations.isNotEmpty(stack) && 
                           precedence(StackOperations.peek(stack)!!) >= precedence(char)) {
                        output.append(StackOperations.pop(stack))
                    }
                    StackOperations.push(stack, char)
                }
            }
        }
        
        while (StackOperations.isNotEmpty(stack)) {
            output.append(StackOperations.pop(stack))
        }
        
        return output.toString()
    }
    
    /**
     * Helper function to get operator precedence
     */
    private fun precedence(operator: Char): Int {
        return when (operator) {
            '+', '-' -> 1
            '*', '/' -> 2
            '^' -> 3
            else -> 0
        }
    }
    
    /**
     * Finds the next greater element for each element in array
     * 
     * ALGORITHM:
     * 1. Use stack to keep track of elements
     * 2. For each element, pop smaller elements from stack
     * 3. Current element is next greater for popped elements
     * 4. Push current element onto stack
     * 
     * TIME COMPLEXITY: O(n) - each element pushed/popped at most once
     * SPACE COMPLEXITY: O(n) - stack stores elements
     * 
     * @param arr Input array
     * @return Array with next greater elements
     */
    fun nextGreaterElement(arr: IntArray): IntArray {
        val result = IntArray(arr.size) { -1 }
        val stack = StackCreation.createBasicStack<Int>()
        
        for (i in arr.indices) {
            while (StackOperations.isNotEmpty(stack) && arr[StackOperations.peek(stack)!!] < arr[i]) {
                val index = StackOperations.pop(stack)!!
                result[index] = arr[i]
            }
            StackOperations.push(stack, i)
        }
        
        return result
    }
    
    /**
     * Finds the largest rectangle in histogram
     * 
     * ALGORITHM:
     * 1. Use stack to keep track of bar indices
     * 2. For each bar, pop bars with greater height
     * 3. Calculate area for each popped bar
     * 4. Keep track of maximum area
     * 
     * TIME COMPLEXITY: O(n) - each bar pushed/popped at most once
     * SPACE COMPLEXITY: O(n) - stack stores bar indices
     * 
     * @param heights Array of bar heights
     * @return Maximum area of rectangle
     */
    fun largestRectangleArea(heights: IntArray): Int {
        val stack = StackCreation.createBasicStack<Int>()
        var maxArea = 0
        var i = 0
        
        while (i < heights.size) {
            if (StackOperations.isEmpty(stack) || heights[StackOperations.peek(stack)!!] <= heights[i]) {
                StackOperations.push(stack, i++)
            } else {
                val top = StackOperations.pop(stack)!!
                val area = heights[top] * (if (StackOperations.isEmpty(stack)) i else i - StackOperations.peek(stack)!! - 1)
                maxArea = maxOf(maxArea, area)
            }
        }
        
        while (StackOperations.isNotEmpty(stack)) {
            val top = StackOperations.pop(stack)!!
            val area = heights[top] * (if (StackOperations.isEmpty(stack)) i else i - StackOperations.peek(stack)!! - 1)
            maxArea = maxOf(maxArea, area)
        }
        
        return maxArea
    }
    
    /**
     * Implements undo/redo functionality
     * 
     * ALGORITHM:
     * 1. Use two stacks: one for undo, one for redo
     * 2. Each action is pushed onto undo stack
     * 3. Undo pops from undo stack and pushes to redo stack
     * 4. Redo pops from redo stack and pushes to undo stack
     * 
     * TIME COMPLEXITY: O(1) - constant time operations
     * SPACE COMPLEXITY: O(n) - stores all actions
     */
    class UndoRedoManager<T> {
        private val undoStack = StackCreation.createBasicStack<T>()
        private val redoStack = StackCreation.createBasicStack<T>()
        
        fun performAction(action: T) {
            StackOperations.push(undoStack, action)
            StackOperations.clear(redoStack) // Clear redo when new action performed
        }
        
        fun undo(): T? {
            return if (StackOperations.isNotEmpty(undoStack)) {
                val action = StackOperations.pop(undoStack)!!
                StackOperations.push(redoStack, action)
                action
            } else null
        }
        
        fun redo(): T? {
            return if (StackOperations.isNotEmpty(redoStack)) {
                val action = StackOperations.pop(redoStack)!!
                StackOperations.push(undoStack, action)
                action
            } else null
        }
        
        fun canUndo(): Boolean = StackOperations.isNotEmpty(undoStack)
        fun canRedo(): Boolean = StackOperations.isNotEmpty(redoStack)
    }
    
    /**
     * Implements browser history functionality
     * 
     * ALGORITHM:
     * 1. Use two stacks: one for back history, one for forward history
     * 2. Navigate pushes current page to back stack
     * 3. Back pops from back stack and pushes to forward stack
     * 4. Forward pops from forward stack and pushes to back stack
     * 
     * TIME COMPLEXITY: O(1) - constant time operations
     * SPACE COMPLEXITY: O(n) - stores all visited pages
     */
    class BrowserHistory {
        private val backStack = StackCreation.createBasicStack<String>()
        private val forwardStack = StackCreation.createBasicStack<String>()
        private var currentPage: String? = null
        
        fun navigate(url: String) {
            currentPage?.let { StackOperations.push(backStack, it) }
            currentPage = url
            StackOperations.clear(forwardStack) // Clear forward when new navigation
        }
        
        fun back(): String? {
            return if (StackOperations.isNotEmpty(backStack)) {
                currentPage?.let { StackOperations.push(forwardStack, it) }
                currentPage = StackOperations.pop(backStack)!!
                currentPage
            } else null
        }
        
        fun forward(): String? {
            return if (StackOperations.isNotEmpty(forwardStack)) {
                currentPage?.let { StackOperations.push(backStack, it) }
                currentPage = StackOperations.pop(forwardStack)!!
                currentPage
            } else null
        }
        
        fun canGoBack(): Boolean = StackOperations.isNotEmpty(backStack)
        fun canGoForward(): Boolean = StackOperations.isNotEmpty(forwardStack)
        fun getCurrentPage(): String? = currentPage
    }
    
    /**
     * Sorts a stack using another stack
     * 
     * ALGORITHM:
     * 1. Use temporary stack to hold sorted elements
     * 2. Pop from original stack and insert into temp stack in sorted order
     * 3. Move all elements back to original stack
     * 
     * TIME COMPLEXITY: O(n²) - each element may need to be moved multiple times
     * SPACE COMPLEXITY: O(n) - temporary stack stores all elements
     * 
     * @param stack Stack to sort
     * @return Sorted stack
     */
    fun <T : Comparable<T>> sortStack(stack: ArrayDeque<T>): ArrayDeque<T> {
        val tempStack = StackCreation.createBasicStack<T>()
        
        while (StackOperations.isNotEmpty(stack)) {
            val temp = StackOperations.pop(stack)!!
            
            while (StackOperations.isNotEmpty(tempStack) && 
                   StackOperations.peek(tempStack)!! > temp) {
                StackOperations.push(stack, StackOperations.pop(tempStack)!!)
            }
            
            StackOperations.push(tempStack, temp)
        }
        
        // Move all elements back to original stack
        while (StackOperations.isNotEmpty(tempStack)) {
            StackOperations.push(stack, StackOperations.pop(tempStack)!!)
        }
        
        return stack
    }
    
    /**
     * Checks if a string is a palindrome using stack
     * 
     * ALGORITHM:
     * 1. Push first half of characters onto stack
     * 2. Compare second half with popped characters
     * 3. If all match, string is palindrome
     * 
     * TIME COMPLEXITY: O(n) - n characters to process
     * SPACE COMPLEXITY: O(n/2) - stack stores half the characters
     * 
     * @param str String to check
     * @return true if string is palindrome, false otherwise
     */
    fun isPalindrome(str: String): Boolean {
        val stack = StackCreation.createBasicStack<Char>()
        val length = str.length
        
        // Push first half onto stack
        for (i in 0 until length / 2) {
            StackOperations.push(stack, str[i])
        }
        
        // Compare with second half
        val startIndex = if (length % 2 == 0) length / 2 else length / 2 + 1
        for (i in startIndex until length) {
            if (StackOperations.pop(stack) != str[i]) {
                return false
            }
        }
        
        return true
    }
    
    /**
     * Demonstrates stack algorithms
     */
    fun demonstrateStackAlgorithms() {
        println("=== STACK ALGORITHMS DEMONSTRATION ===\n")
        
        // 1. String reversal
        println("1. STRING REVERSAL")
        val original = "hello world"
        val reversed = reverseString(original)
        println("Original: '$original'")
        println("Reversed: '$reversed'")
        println()
        
        // 2. Parentheses matching
        println("2. PARENTHESES MATCHING")
        val expressions = listOf("()", "([{}])", "([)]", "(((", ")))")
        for (expr in expressions) {
            println("'$expr' is balanced: ${isBalancedParentheses(expr)}")
        }
        println()
        
        // 3. Postfix evaluation
        println("3. POSTFIX EVALUATION")
        val postfix = "5 3 2 * +"
        val result = evaluatePostfix(postfix)
        println("Postfix: '$postfix'")
        println("Result: $result")
        println()
        
        // 4. Infix to postfix
        println("4. INFIX TO POSTFIX")
        val infix = "a+b*c"
        val postfixResult = infixToPostfix(infix)
        println("Infix: '$infix'")
        println("Postfix: '$postfixResult'")
        println()
        
        // 5. Next greater element
        println("5. NEXT GREATER ELEMENT")
        val array = intArrayOf(4, 5, 2, 10, 8)
        val nextGreater = nextGreaterElement(array)
        println("Array: ${array.contentToString()}")
        println("Next greater: ${nextGreater.contentToString()}")
        println()
        
        // 6. Largest rectangle in histogram
        println("6. LARGEST RECTANGLE IN HISTOGRAM")
        val heights = intArrayOf(2, 1, 5, 6, 2, 3)
        val maxArea = largestRectangleArea(heights)
        println("Heights: ${heights.contentToString()}")
        println("Maximum area: $maxArea")
        println()
        
        // 7. Undo/Redo manager
        println("7. UNDO/REDO MANAGER")
        val undoRedo = UndoRedoManager<String>()
        undoRedo.performAction("Action 1")
        undoRedo.performAction("Action 2")
        undoRedo.performAction("Action 3")
        println("Current state: ${undoRedo.canUndo()}")
        println("Undo: ${undoRedo.undo()}")
        println("Redo: ${undoRedo.redo()}")
        println()
        
        // 8. Browser history
        println("8. BROWSER HISTORY")
        val browser = BrowserHistory()
        browser.navigate("google.com")
        browser.navigate("github.com")
        browser.navigate("stackoverflow.com")
        println("Current page: ${browser.getCurrentPage()}")
        println("Back: ${browser.back()}")
        println("Forward: ${browser.forward()}")
        println()
        
        // 9. Stack sorting
        println("9. STACK SORTING")
        val unsortedStack = StackCreation.createStackFromList(listOf(5, 2, 8, 1, 9))
        println("Unsorted stack: $unsortedStack")
        val sortedStack = sortStack(unsortedStack)
        println("Sorted stack: $sortedStack")
        println()
        
        // 10. Palindrome check
        println("10. PALINDROME CHECK")
        val palindromes = listOf("racecar", "hello", "anna", "kotlin")
        for (word in palindromes) {
            println("'$word' is palindrome: ${isPalindrome(word)}")
        }
        println()
        
        println("=== STACK ALGORITHMS DEMONSTRATION COMPLETE ===\n")
    }
} 