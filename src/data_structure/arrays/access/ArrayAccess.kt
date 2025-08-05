package data_structure.arrays.access

/**
 * ARRAY ACCESS ALGORITHMS
 *
 * Problem: Safely access elements from arrays at specific positions or ranges.
 *
 * Different access operations:
 * 1. Get element at specific index with bounds checking
 * 2. Get first and last elements
 * 3. Get elements within a specific range
 * 4. Handle edge cases and invalid indices
 *
 * Example:
 * Array: [1, 2, 3, 4, 5]
 * Get element at index 2: 3
 * Get first element: 1
 * Get last element: 5
 * Get elements in range [1, 3]: [2, 3, 4]
 *
 * Intuition:
 * - Always check bounds before accessing
 * - Handle edge cases (empty array, invalid indices)
 * - Use safe access methods to prevent exceptions
 * - Return null or default values for invalid access
 */

object ArrayAccess {
    
    /**
     * Get Element at Index
     *
     * Problem: Safely retrieve an element at a specific index in an array.
     *
     * Algorithm:
     * 1. Check if index is within valid bounds (0 to size-1)
     * 2. Return element if valid, null otherwise
     *
     * Time Complexity: O(1) - Direct array access
     * Space Complexity: O(1) - Constant extra space
     */
    fun getElement(arr: IntArray, index: Int): Int? {
        return if (index in arr.indices) arr[index] else null
    }
    
    /**
     * Get First Element
     *
     * Problem: Safely retrieve the first element of an array.
     *
     * Algorithm:
     * 1. Check if array is not empty
     * 2. Return first element if exists, null otherwise
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    fun getFirstElement(arr: IntArray): Int? {
        return arr.firstOrNull()
    }
    
    /**
     * Get Last Element
     *
     * Problem: Safely retrieve the last element of an array.
     *
     * Algorithm:
     * 1. Check if array is not empty
     * 2. Return last element if exists, null otherwise
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    fun getLastElement(arr: IntArray): Int? {
        return arr.lastOrNull()
    }
    
    /**
     * Get Elements in Range
     *
     * Problem: Extract a subarray containing elements from start to end index.
     *
     * Algorithm:
     * 1. Validate and adjust start and end indices
     * 2. Ensure start <= end and both are within bounds
     * 3. Extract elements from start to end (inclusive)
     *
     * Time Complexity: O(end - start + 1) - We copy elements in range
     * Space Complexity: O(end - start + 1) - New array for result
     */
    fun getElementsInRange(arr: IntArray, start: Int, end: Int): IntArray {
        val startIndex = maxOf(0, start)
        val endIndex = minOf(arr.size, end + 1)
        return if (startIndex < endIndex) arr.slice(startIndex until endIndex).toIntArray() else intArrayOf()
    }
    
    /**
     * Get Elements with Step
     *
     * Problem: Extract elements from array with a specific step size.
     *
     * Algorithm:
     * 1. Start from start index
     * 2. Take every step-th element until end index
     * 3. Handle negative step for reverse traversal
     *
     * Time Complexity: O((end - start) / step)
     * Space Complexity: O((end - start) / step)
     */
    fun getElementsWithStep(arr: IntArray, start: Int, end: Int, step: Int): IntArray {
        if (step == 0) return intArrayOf()
        
        val startIndex = maxOf(0, start)
        val endIndex = minOf(arr.size, end + 1)
        
        if (startIndex >= endIndex) return intArrayOf()
        
        val result = mutableListOf<Int>()
        var i = startIndex
        
        if (step > 0) {
            while (i < endIndex) {
                result.add(arr[i])
                i += step
            }
        } else {
            i = endIndex - 1
            while (i >= startIndex) {
                result.add(arr[i])
                i += step
            }
        }
        
        return result.toIntArray()
    }
    
    /**
     * Get Elements at Indices
     *
     * Problem: Extract elements at specific indices from an array.
     *
     * Algorithm:
     * 1. Validate each index in the indices array
     * 2. Collect valid elements at those indices
     * 3. Return array of elements found
     *
     * Time Complexity: O(indices.size)
     * Space Complexity: O(indices.size)
     */
    fun getElementsAtIndices(arr: IntArray, indices: IntArray): IntArray {
        val result = mutableListOf<Int>()
        for (index in indices) {
            if (index in arr.indices) {
                result.add(arr[index])
            }
        }
        return result.toIntArray()
    }
    
    /**
     * Get Elements by Condition
     *
     * Problem: Extract elements that satisfy a given condition.
     *
     * Algorithm:
     * 1. Traverse array and check each element
     * 2. Add element to result if condition is satisfied
     * 3. Return array of matching elements
     *
     * Time Complexity: O(n) - We check each element once
     * Space Complexity: O(k) - k elements satisfy condition
     */
    fun getElementsByCondition(arr: IntArray, condition: (Int) -> Boolean): IntArray {
        return arr.filter(condition).toIntArray()
    }
    
    /**
     * Get Elements with Default Value
     *
     * Problem: Get element at index with a default value if index is invalid.
     *
     * Algorithm:
     * 1. Check if index is valid
     * 2. Return element if valid, default value otherwise
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    fun getElementWithDefault(arr: IntArray, index: Int, defaultValue: Int): Int {
        return if (index in arr.indices) arr[index] else defaultValue
    }
    
    /**
     * Get Elements Around Index
     *
     * Problem: Get elements around a specific index (previous, current, next).
     *
     * Algorithm:
     * 1. Check bounds for previous, current, and next indices
     * 2. Return array with available elements
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1) - Maximum 3 elements
     */
    fun getElementsAroundIndex(arr: IntArray, index: Int): IntArray {
        val result = mutableListOf<Int>()
        
        // Previous element
        if (index - 1 in arr.indices) {
            result.add(arr[index - 1])
        }
        
        // Current element
        if (index in arr.indices) {
            result.add(arr[index])
        }
        
        // Next element
        if (index + 1 in arr.indices) {
            result.add(arr[index + 1])
        }
        
        return result.toIntArray()
    }
} 