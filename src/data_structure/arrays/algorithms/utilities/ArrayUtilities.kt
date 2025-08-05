package data_structure.arrays.algorithms.utilities

/**
 * ARRAY UTILITIES ALGORITHMS
 *
 * Problem: Provide utility functions for common array operations like insertion, removal, and replacement.
 *
 * Different utility operations:
 * 1. Remove elements by value or index
 * 2. Insert elements at specific positions
 * 3. Replace elements with new values
 * 4. Handle edge cases and validation
 *
 * Example:
 * Array: [1, 2, 3, 4, 5]
 * Remove element 3: [1, 2, 4, 5]
 * Remove element at index 2: [1, 2, 4, 5]
 * Insert 10 at index 2: [1, 2, 10, 3, 4, 5]
 * Replace 3 with 30: [1, 2, 30, 4, 5]
 *
 * Intuition:
 * - Always validate indices before operations
 * - Create new arrays for immutable operations
 * - Handle edge cases (empty array, invalid indices)
 * - Consider efficiency for large arrays
 */

object ArrayUtilities {
    
    /**
     * Remove Element by Value
     *
     * Problem: Remove all occurrences of a specific value from an array.
     *
     * Algorithm:
     * 1. Filter array to exclude the specified value
     * 2. Return new array without the value
     *
     * Time Complexity: O(n) - We check each element once
     * Space Complexity: O(n) - New array for result
     */
    fun removeElement(arr: IntArray, value: Int): IntArray {
        return arr.filter { it != value }.toIntArray()
    }
    
    /**
     * Remove Element at Index
     *
     * Problem: Remove element at a specific index from an array.
     *
     * Algorithm:
     * 1. Validate index bounds
     * 2. Create new array excluding the element at index
     * 3. Return new array
     *
     * Time Complexity: O(n) - We copy elements
     * Space Complexity: O(n) - New array for result
     */
    fun removeElementAt(arr: IntArray, index: Int): IntArray {
        return if (index in arr.indices) {
            arr.take(index).plus(arr.drop(index + 1)).toIntArray()
        } else {
            arr.copyOf()
        }
    }
    
    /**
     * Remove Elements in Range
     *
     * Problem: Remove elements within a specific range from an array.
     *
     * Algorithm:
     * 1. Validate and adjust start and end indices
     * 2. Create new array excluding the range
     * 3. Return new array
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun removeElementsInRange(arr: IntArray, start: Int, end: Int): IntArray {
        val startIndex = maxOf(0, start)
        val endIndex = minOf(arr.size, end + 1)
        
        return if (startIndex < endIndex) {
            arr.take(startIndex).plus(arr.drop(endIndex)).toIntArray()
        } else {
            arr.copyOf()
        }
    }
    
    /**
     * Remove Elements by Condition
     *
     * Problem: Remove all elements that satisfy a given condition.
     *
     * Algorithm:
     * 1. Filter array to keep elements that don't satisfy condition
     * 2. Return new array
     *
     * Time Complexity: O(n)
     * Space Complexity: O(k) - k elements don't satisfy condition
     */
    fun removeElementsByCondition(arr: IntArray, condition: (Int) -> Boolean): IntArray {
        return arr.filter { !condition(it) }.toIntArray()
    }
    
    /**
     * Remove Duplicates
     *
     * Problem: Remove duplicate elements from an array while preserving order.
     *
     * Algorithm:
     * 1. Use set to track seen elements
     * 2. Keep only first occurrence of each element
     * 3. Return new array without duplicates
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n) - Set to track seen elements
     */
    fun removeDuplicates(arr: IntArray): IntArray {
        val seen = mutableSetOf<Int>()
        val result = mutableListOf<Int>()
        
        for (element in arr) {
            if (seen.add(element)) {
                result.add(element)
            }
        }
        
        return result.toIntArray()
    }
    
    /**
     * Insert Element at Index
     *
     * Problem: Insert an element at a specific index in an array.
     *
     * Algorithm:
     * 1. Validate index (0 to size)
     * 2. Create new array with element inserted at index
     * 3. Return new array
     *
     * Time Complexity: O(n) - We copy elements
     * Space Complexity: O(n) - New array for result
     */
    fun insertElement(arr: IntArray, index: Int, value: Int): IntArray {
        return if (index in 0..arr.size) {
            arr.take(index).plus(value).plus(arr.drop(index)).toIntArray()
        } else {
            arr.copyOf()
        }
    }
    
    /**
     * Insert Multiple Elements
     *
     * Problem: Insert multiple elements at a specific index in an array.
     *
     * Algorithm:
     * 1. Validate index
     * 2. Create new array with elements inserted at index
     * 3. Return new array
     *
     * Time Complexity: O(n + elements.size)
     * Space Complexity: O(n + elements.size)
     */
    fun insertElements(arr: IntArray, index: Int, elements: IntArray): IntArray {
        return if (index in 0..arr.size) {
            arr.take(index).plus(elements.toList()).plus(arr.drop(index)).toIntArray()
        } else {
            arr.copyOf()
        }
    }
    
    /**
     * Insert Element at Beginning
     *
     * Problem: Insert an element at the beginning of an array.
     *
     * Algorithm:
     * 1. Create new array with element at index 0
     * 2. Copy original array elements after the new element
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun insertAtBeginning(arr: IntArray, value: Int): IntArray {
        return intArrayOf(value).plus(arr.toList()).toIntArray()
    }
    
    /**
     * Insert Element at End
     *
     * Problem: Insert an element at the end of an array.
     *
     * Algorithm:
     * 1. Create new array with original elements plus new element
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun insertAtEnd(arr: IntArray, value: Int): IntArray {
        return arr.plus(value).toIntArray()
    }
    
    /**
     * Replace Element by Value
     *
     * Problem: Replace all occurrences of a value with a new value.
     *
     * Algorithm:
     * 1. Map each element, replacing old value with new value
     * 2. Return new array
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun replaceElement(arr: IntArray, oldValue: Int, newValue: Int): IntArray {
        return arr.map { if (it == oldValue) newValue else it }.toIntArray()
    }
    
    /**
     * Replace Element at Index
     *
     * Problem: Replace element at a specific index with a new value.
     *
     * Algorithm:
     * 1. Validate index
     * 2. Create new array with element replaced at index
     * 3. Return new array
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun replaceElementAt(arr: IntArray, index: Int, newValue: Int): IntArray {
        return if (index in arr.indices) {
            arr.copyOf().apply { this[index] = newValue }
        } else {
            arr.copyOf()
        }
    }
    
    /**
     * Replace Elements by Condition
     *
     * Problem: Replace elements that satisfy a condition with a new value.
     *
     * Algorithm:
     * 1. Map each element, replacing those that satisfy condition
     * 2. Return new array
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun replaceElementsByCondition(arr: IntArray, condition: (Int) -> Boolean, newValue: Int): IntArray {
        return arr.map { if (condition(it)) newValue else it }.toIntArray()
    }
    
    /**
     * Replace Elements in Range
     *
     * Problem: Replace elements within a specific range with a new value.
     *
     * Algorithm:
     * 1. Validate and adjust start and end indices
     * 2. Create new array with elements replaced in range
     * 3. Return new array
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun replaceElementsInRange(arr: IntArray, start: Int, end: Int, newValue: Int): IntArray {
        val startIndex = maxOf(0, start)
        val endIndex = minOf(arr.size, end + 1)
        
        return arr.mapIndexed { index, value ->
            if (index in startIndex until endIndex) newValue else value
        }.toIntArray()
    }
    
    /**
     * Clear Array
     *
     * Problem: Remove all elements from an array.
     *
     * Algorithm:
     * 1. Return empty array
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    fun clearArray(arr: IntArray): IntArray {
        return intArrayOf()
    }
    
    /**
     * Resize Array
     *
     * Problem: Change the size of an array, either expanding or shrinking.
     *
     * Algorithm:
     * 1. Create new array of specified size
     * 2. Copy elements up to minimum of old and new size
     * 3. Fill remaining positions with default value if expanding
     *
     * Time Complexity: O(min(oldSize, newSize))
     * Space Complexity: O(newSize)
     */
    fun resizeArray(arr: IntArray, newSize: Int, defaultValue: Int = 0): IntArray {
        val result = IntArray(newSize) { defaultValue }
        val copySize = minOf(arr.size, newSize)
        arr.copyInto(result, 0, 0, copySize)
        return result
    }
    
    /**
     * Clone Array
     *
     * Problem: Create a deep copy of an array.
     *
     * Algorithm:
     * 1. Create new array of same size
     * 2. Copy all elements to new array
     * 3. Return new array
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun cloneArray(arr: IntArray): IntArray {
        return arr.copyOf()
    }
    
    /**
     * Clone Array Range
     *
     * Problem: Create a copy of a specific range of an array.
     *
     * Algorithm:
     * 1. Validate and adjust start and end indices
     * 2. Create new array for the range
     * 3. Copy elements from start to end
     *
     * Time Complexity: O(end - start)
     * Space Complexity: O(end - start)
     */
    fun cloneArrayRange(arr: IntArray, start: Int, end: Int): IntArray {
        val startIndex = maxOf(0, start)
        val endIndex = minOf(arr.size, end + 1)
        return if (startIndex < endIndex) arr.slice(startIndex until endIndex).toIntArray() else intArrayOf()
    }
} 