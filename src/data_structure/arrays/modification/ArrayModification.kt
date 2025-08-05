package data_structure.arrays.modification

/**
 * ARRAY MODIFICATION ALGORITHMS
 *
 * Problem: Modify array elements in-place with various operations like setting, swapping, and reversing.
 *
 * Different modification operations:
 * 1. Set element at specific index
 * 2. Swap elements at two indices
 * 3. Reverse entire array or range
 * 4. Handle bounds checking and validation
 *
 * Example:
 * Array: [1, 2, 3, 4, 5]
 * Set element at index 2 to 10: [1, 2, 10, 4, 5]
 * Swap elements at indices 1 and 3: [1, 4, 10, 2, 5]
 * Reverse entire array: [5, 2, 10, 4, 1]
 *
 * Intuition:
 * - Always validate indices before modification
 * - Use in-place operations to avoid extra space
 * - Handle edge cases (empty array, invalid indices)
 * - Reverse operations can be done with two pointers
 */

object ArrayModification {
    
    /**
     * Set Element at Index
     *
     * Problem: Set a value at a specific index in an array.
     *
     * Algorithm:
     * 1. Check if index is within valid bounds
     * 2. Set value if valid, return false otherwise
     *
     * Time Complexity: O(1) - Direct array access
     * Space Complexity: O(1) - Constant extra space
     */
    fun setElement(arr: IntArray, index: Int, value: Int): Boolean {
        return if (index in arr.indices) {
            arr[index] = value
            true
        } else false
    }
    
    /**
     * Swap Elements
     *
     * Problem: Swap elements at two different indices in an array.
     *
     * Algorithm:
     * 1. Validate both indices
     * 2. Use temporary variable or XOR swap
     * 3. Exchange values at the indices
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    fun swapElements(arr: IntArray, i: Int, j: Int): Boolean {
        return if (i in arr.indices && j in arr.indices) {
            arr[i] = arr[j].also { arr[j] = arr[i] }
            true
        } else false
    }
    
    /**
     * Swap Elements (XOR Method)
     *
     * Problem: Swap elements without using temporary variable.
     *
     * Algorithm:
     * 1. Use XOR properties: a ^ a = 0, a ^ 0 = a
     * 2. a = a ^ b, b = a ^ b, a = a ^ b
     * 3. This works only for integers
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1) - No extra space
     */
    fun swapElementsXOR(arr: IntArray, i: Int, j: Int): Boolean {
        return if (i in arr.indices && j in arr.indices && i != j) {
            arr[i] = arr[i] xor arr[j]
            arr[j] = arr[i] xor arr[j]
            arr[i] = arr[i] xor arr[j]
            true
        } else false
    }
    
    /**
     * Reverse Array
     *
     * Problem: Reverse the order of elements in an array.
     *
     * Algorithm:
     * 1. Use two pointers (left and right)
     * 2. Swap elements at left and right pointers
     * 3. Move left pointer right, right pointer left
     * 4. Continue until pointers meet
     *
     * Time Complexity: O(n) - We swap n/2 pairs
     * Space Complexity: O(1) - In-place operation
     */
    fun reverseArray(arr: IntArray) {
        var left = 0
        var right = arr.size - 1
        while (left < right) {
            swapElements(arr, left, right)
            left++
            right--
        }
    }
    
    /**
     * Reverse Array in Range
     *
     * Problem: Reverse elements within a specific range of an array.
     *
     * Algorithm:
     * 1. Validate and adjust start and end indices
     * 2. Use two pointers within the range
     * 3. Swap elements until pointers meet
     *
     * Time Complexity: O(end - start) - We swap (end-start)/2 pairs
     * Space Complexity: O(1) - In-place operation
     */
    fun reverseArrayInRange(arr: IntArray, start: Int, end: Int) {
        var left = maxOf(0, start)
        var right = minOf(arr.size - 1, end)
        while (left < right) {
            swapElements(arr, left, right)
            left++
            right--
        }
    }
    
    /**
     * Reverse Array (Recursive)
     *
     * Problem: Reverse array using recursion.
     *
     * Algorithm:
     * 1. Base case: if left >= right, return
     * 2. Swap elements at left and right
     * 3. Recursively reverse remaining elements
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n) - Recursion stack
     */
    fun reverseArrayRecursive(arr: IntArray) {
        reverseArrayRecursiveHelper(arr, 0, arr.size - 1)
    }
    
    private fun reverseArrayRecursiveHelper(arr: IntArray, left: Int, right: Int) {
        if (left >= right) return
        
        swapElements(arr, left, right)
        reverseArrayRecursiveHelper(arr, left + 1, right - 1)
    }
    
    /**
     * Reverse Array by Groups
     *
     * Problem: Reverse array in groups of specified size.
     *
     * Algorithm:
     * 1. Process array in chunks of group size
     * 2. Reverse each chunk individually
     * 3. Handle last chunk if it's smaller than group size
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun reverseArrayByGroups(arr: IntArray, groupSize: Int) {
        val n = arr.size
        for (i in 0 until n step groupSize) {
            val end = minOf(i + groupSize - 1, n - 1)
            reverseArrayInRange(arr, i, end)
        }
    }
    
    /**
     * Reverse Array with Condition
     *
     * Problem: Reverse only elements that satisfy a condition.
     *
     * Algorithm:
     * 1. Find all indices that satisfy condition
     * 2. Reverse elements at those indices
     * 3. Maintain relative order of other elements
     *
     * Time Complexity: O(n)
     * Space Complexity: O(k) - k elements satisfy condition
     */
    fun reverseArrayWithCondition(arr: IntArray, condition: (Int) -> Boolean) {
        val indices = mutableListOf<Int>()
        
        // Find indices that satisfy condition
        for (i in arr.indices) {
            if (condition(arr[i])) {
                indices.add(i)
            }
        }
        
        // Reverse elements at those indices
        var left = 0
        var right = indices.size - 1
        while (left < right) {
            swapElements(arr, indices[left], indices[right])
            left++
            right--
        }
    }
    
    /**
     * Fill Array with Value
     *
     * Problem: Fill entire array with a specific value.
     *
     * Algorithm:
     * 1. Traverse array and set each element to value
     * 2. Use built-in fill method for efficiency
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun fillArray(arr: IntArray, value: Int) {
        arr.fill(value)
    }
    
    /**
     * Fill Array in Range
     *
     * Problem: Fill array elements within a specific range with a value.
     *
     * Algorithm:
     * 1. Validate and adjust start and end indices
     * 2. Set each element in range to the value
     *
     * Time Complexity: O(end - start)
     * Space Complexity: O(1)
     */
    fun fillArrayInRange(arr: IntArray, start: Int, end: Int, value: Int) {
        val startIndex = maxOf(0, start)
        val endIndex = minOf(arr.size, end + 1)
        for (i in startIndex until endIndex) {
            arr[i] = value
        }
    }
    
    /**
     * Fill Array with Function
     *
     * Problem: Fill array with values generated by a function.
     *
     * Algorithm:
     * 1. Traverse array
     * 2. Set each element to function(index)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun fillArrayWithFunction(arr: IntArray, function: (Int) -> Int) {
        for (i in arr.indices) {
            arr[i] = function(i)
        }
    }
} 