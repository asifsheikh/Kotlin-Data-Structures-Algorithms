package data_structure.arrays

/**
 * ARRAY MANIPULATION ALGORITHMS
 * 
 * Problem: Perform various manipulation operations on arrays like insertion, deletion, shifting, etc.
 * 
 * Different manipulations:
 * 1. Insert elements at specific positions
 * 2. Remove elements by value or index
 * 3. Replace elements
 * 4. Shift elements left/right
 * 5. Fill arrays with values
 * 6. Copy and pad arrays
 * 
 * Example:
 * Array: [1, 2, 3, 4, 5]
 * Insert 10 at index 2: [1, 2, 10, 3, 4, 5]
 * Remove element 3: [1, 2, 4, 5]
 * Replace 2 with 20: [1, 20, 3, 4, 5]
 * Shift left by 2: [3, 4, 5, 1, 2]
 * 
 * Intuition:
 * - For insertion: Create new array and copy elements
 * - For deletion: Filter out unwanted elements
 * - For shifting: Use modular arithmetic for circular shifts
 * - For filling: Use loops or built-in functions
 * - Consider in-place vs new array approaches
 */

object ArrayManipulation {
    
    /**
     * Insert Element at Index
     * 
     * Problem: Insert an element at a specific position in array
     * 
     * Algorithm:
     * 1. Create new array with size + 1
     * 2. Copy elements before insertion point
     * 3. Insert new element
     * 4. Copy remaining elements
     * 
     * Time Complexity: O(n) - We copy all elements
     * Space Complexity: O(n) - New array
     */
    fun insertElement(arr: IntArray, index: Int, value: Int): IntArray {
        return if (index in 0..arr.size) {
            arr.take(index).plus(value).plus(arr.drop(index)).toIntArray()
        } else {
            arr.copyOf()
        }
    }
    
    /**
     * Insert Element at Index (Manual)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun insertElementManual(arr: IntArray, index: Int, value: Int): IntArray {
        if (index !in 0..arr.size) return arr.copyOf()
        
        val result = IntArray(arr.size + 1)
        
        // Copy elements before insertion point
        for (i in 0 until index) {
            result[i] = arr[i]
        }
        
        // Insert new element
        result[index] = value
        
        // Copy remaining elements
        for (i in index until arr.size) {
            result[i + 1] = arr[i]
        }
        
        return result
    }
    
    /**
     * Remove Element by Value
     * 
     * Problem: Remove all occurrences of a specific value from array
     * 
     * Algorithm:
     * 1. Filter out elements equal to target value
     * 2. Convert filtered list to array
     * 
     * Time Complexity: O(n) - We check each element once
     * Space Complexity: O(n) - New filtered array
     */
    fun removeElement(arr: IntArray, value: Int): IntArray {
        return arr.filter { it != value }.toIntArray()
    }
    
    /**
     * Remove Element at Index
     * 
     * Problem: Remove element at specific position
     * 
     * Algorithm:
     * 1. Check if index is valid
     * 2. Create new array without element at index
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun removeElementAt(arr: IntArray, index: Int): IntArray {
        return if (index in arr.indices) {
            arr.take(index).plus(arr.drop(index + 1)).toIntArray()
        } else {
            arr.copyOf()
        }
    }
    
    /**
     * Remove Element at Index (Manual)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun removeElementAtManual(arr: IntArray, index: Int): IntArray {
        if (index !in arr.indices) return arr.copyOf()
        
        val result = IntArray(arr.size - 1)
        
        // Copy elements before removal point
        for (i in 0 until index) {
            result[i] = arr[i]
        }
        
        // Copy elements after removal point
        for (i in index + 1 until arr.size) {
            result[i - 1] = arr[i]
        }
        
        return result
    }
    
    /**
     * Replace Element
     * 
     * Problem: Replace all occurrences of old value with new value
     * 
     * Algorithm:
     * 1. Create new array
     * 2. Replace matching elements with new value
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
     * Problem: Replace element at specific position
     * 
     * Algorithm:
     * 1. Create copy of array
     * 2. Replace element at index if valid
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun replaceElementAt(arr: IntArray, index: Int, newValue: Int): IntArray {
        return if (index in arr.indices) {
            arr.copyOf().also { it[index] = newValue }
        } else {
            arr.copyOf()
        }
    }
    
    /**
     * Shift Array Left
     * 
     * Problem: Shift array elements to the left by k positions
     * 
     * Algorithm:
     * 1. Calculate effective shift (k % n)
     * 2. Create new array
     * 3. Copy elements with new indices using modular arithmetic
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun shiftArrayLeft(arr: IntArray, k: Int): IntArray {
        val n = arr.size
        if (n == 0) return arr
        
        val shift = k % n
        if (shift == 0) return arr.copyOf()
        
        val result = IntArray(n)
        for (i in 0 until n) {
            val newIndex = (i - shift + n) % n
            result[newIndex] = arr[i]
        }
        return result
    }
    
    /**
     * Shift Array Right
     * 
     * Problem: Shift array elements to the right by k positions
     * 
     * Algorithm:
     * 1. Calculate effective shift (k % n)
     * 2. Create new array
     * 3. Copy elements with new indices using modular arithmetic
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun shiftArrayRight(arr: IntArray, k: Int): IntArray {
        val n = arr.size
        if (n == 0) return arr
        
        val shift = k % n
        if (shift == 0) return arr.copyOf()
        
        val result = IntArray(n)
        for (i in 0 until n) {
            val newIndex = (i + shift) % n
            result[newIndex] = arr[i]
        }
        return result
    }
    
    /**
     * Fill Array with Value
     * 
     * Problem: Fill entire array with a specific value
     * 
     * Algorithm:
     * 1. Use built-in fill function or loop
     * 2. Set each element to target value
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1) - In-place modification
     */
    fun fillArray(arr: IntArray, value: Int) {
        arr.fill(value)
    }
    
    /**
     * Fill Array in Range
     * 
     * Problem: Fill array elements in specific range with value
     * 
     * Algorithm:
     * 1. Validate range indices
     * 2. Fill elements in range with target value
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
     * Problem: Fill array using a function that takes index as parameter
     * 
     * Algorithm:
     * 1. Apply function to each index
     * 2. Store result in array
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun fillArrayWithFunction(arr: IntArray, function: (Int) -> Int) {
        for (i in arr.indices) {
            arr[i] = function(i)
        }
    }
    
    /**
     * Copy Array Range
     * 
     * Problem: Create new array containing elements from specific range
     * 
     * Algorithm:
     * 1. Validate range indices
     * 2. Create new array of appropriate size
     * 3. Copy elements from range
     * 
     * Time Complexity: O(end - start)
     * Space Complexity: O(end - start)
     */
    fun copyArrayRange(arr: IntArray, start: Int, end: Int): IntArray {
        val startIndex = maxOf(0, start)
        val endIndex = minOf(arr.size, end + 1)
        return if (startIndex < endIndex) {
            arr.slice(startIndex until endIndex).toIntArray()
        } else {
            intArrayOf()
        }
    }
    
    /**
     * Copy Array with Padding
     * 
     * Problem: Create new array of specified size with padding
     * 
     * Algorithm:
     * 1. Create new array of target size
     * 2. Fill with padding value
     * 3. Copy original array elements
     * 
     * Time Complexity: O(newSize)
     * Space Complexity: O(newSize)
     */
    fun copyArrayWithPadding(arr: IntArray, newSize: Int, paddingValue: Int = 0): IntArray {
        val result = IntArray(newSize) { paddingValue }
        arr.copyInto(result, 0, 0, minOf(arr.size, newSize))
        return result
    }
    
    /**
     * Swap Elements
     * 
     * Problem: Swap two elements in array
     * 
     * Algorithm:
     * 1. Validate indices
     * 2. Use temporary variable or Kotlin's also function
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
     * Move Element to Front
     * 
     * Problem: Move element at specific index to front of array
     * 
     * Algorithm:
     * 1. Store element at index
     * 2. Shift elements from start to index left by 1
     * 3. Place element at front
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun moveElementToFront(arr: IntArray, index: Int): Boolean {
        if (index !in arr.indices || index == 0) return false
        
        val element = arr[index]
        
        // Shift elements left
        for (i in index downTo 1) {
            arr[i] = arr[i - 1]
        }
        
        arr[0] = element
        return true
    }
    
    /**
     * Move Element to End
     * 
     * Problem: Move element at specific index to end of array
     * 
     * Algorithm:
     * 1. Store element at index
     * 2. Shift elements from index to end right by 1
     * 3. Place element at end
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun moveElementToEnd(arr: IntArray, index: Int): Boolean {
        if (index !in arr.indices || index == arr.size - 1) return false
        
        val element = arr[index]
        
        // Shift elements right
        for (i in index until arr.size - 1) {
            arr[i] = arr[i + 1]
        }
        
        arr[arr.size - 1] = element
        return true
    }
    
    /**
     * Reverse Array Range
     * 
     * Problem: Reverse elements in specific range of array
     * 
     * Algorithm:
     * 1. Use two pointers within range
     * 2. Swap elements and move pointers towards center
     * 
     * Time Complexity: O(end - start)
     * Space Complexity: O(1)
     */
    fun reverseArrayRange(arr: IntArray, start: Int, end: Int) {
        var left = maxOf(0, start)
        var right = minOf(arr.size - 1, end)
        
        while (left < right) {
            arr[left] = arr[right].also { arr[right] = arr[left] }
            left++
            right--
        }
    }
    
    /**
     * Shuffle Array
     * 
     * Problem: Randomly shuffle array elements
     * 
     * Algorithm:
     * 1. Use Fisher-Yates shuffle
     * 2. For each position, swap with random position from current to end
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun shuffleArray(arr: IntArray) {
        for (i in arr.size - 1 downTo 1) {
            val j = (0..i).random()
            arr[i] = arr[j].also { arr[j] = arr[i] }
        }
    }
} 