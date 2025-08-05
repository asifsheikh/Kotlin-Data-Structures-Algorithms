package data_structure.arrays

/**
 * ARRAY REVERSAL ALGORITHMS
 * 
 * Problem: Given an array, reverse its elements in-place.
 * 
 * There are different variations:
 * 1. Reverse entire array
 * 2. Reverse array in a specific range
 * 3. Reverse array in groups
 * 
 * Example:
 * Array: [1, 2, 3, 4, 5, 6, 7]
 * Full reversal: [7, 6, 5, 4, 3, 2, 1]
 * Range reversal (2-5): [1, 2, 6, 5, 4, 3, 7]
 * Group reversal (k=3): [3, 2, 1, 6, 5, 4, 7]
 * 
 * Intuition:
 * - Use two-pointer technique: one at start, one at end
 * - Swap elements at both pointers and move them towards center
 * - For range reversal, apply same technique within the specified range
 * - For group reversal, reverse each group of k elements
 */

object ArrayReversal {
    
    /**
     * Reverse entire array in-place
     * 
     * Algorithm:
     * 1. Use two pointers: left (start) and right (end)
     * 2. Swap elements at left and right pointers
     * 3. Move left pointer right, right pointer left
     * 4. Continue until left >= right
     * 
     * Time Complexity: O(n) - We traverse half the array
     * Space Complexity: O(1) - We only use constant extra space
     */
    fun reverseArray(arr: IntArray) {
        var left = 0
        var right = arr.size - 1
        
        while (left < right) {
            // Swap elements
            arr[left] = arr[right].also { arr[right] = arr[left] }
            left++
            right--
        }
    }
    
    /**
     * Reverse array elements in a specific range
     * 
     * Algorithm:
     * 1. Validate the range indices
     * 2. Use two pointers within the specified range
     * 3. Apply same swapping technique as full reversal
     * 
     * Time Complexity: O(end - start) - We traverse the range once
     * Space Complexity: O(1) - Constant extra space
     */
    fun reverseArrayInRange(arr: IntArray, start: Int, end: Int) {
        var left = maxOf(0, start)
        var right = minOf(arr.size - 1, end)
        
        while (left < right) {
            // Swap elements
            arr[left] = arr[right].also { arr[right] = arr[left] }
            left++
            right--
        }
    }
    
    /**
     * Reverse array in groups of size k
     * 
     * Problem: Given an array and a number k, reverse the array in groups of k.
     * If the last group has less than k elements, reverse them as well.
     * 
     * Example:
     * Array: [1, 2, 3, 4, 5, 6, 7, 8], k = 3
     * Result: [3, 2, 1, 6, 5, 4, 8, 7]
     * 
     * Algorithm:
     * 1. Process array in chunks of size k
     * 2. For each chunk, reverse elements in that range
     * 3. Handle the last chunk which might be smaller than k
     * 
     * Time Complexity: O(n) - Each element is processed once
     * Space Complexity: O(1) - Constant extra space
     */
    fun reverseArrayInGroups(arr: IntArray, k: Int) {
        val n = arr.size
        
        for (i in 0 until n step k) {
            val left = i
            val right = minOf(i + k - 1, n - 1)
            reverseArrayInRange(arr, left, right)
        }
    }
    
    /**
     * Reverse array in groups of size k (alternative approach)
     * 
     * This approach uses a stack-like behavior but in-place
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun reverseArrayInGroupsAlternative(arr: IntArray, k: Int) {
        val n = arr.size
        
        for (i in 0 until n step k) {
            var left = i
            var right = minOf(i + k - 1, n - 1)
            
            while (left < right) {
                arr[left] = arr[right].also { arr[right] = arr[left] }
                left++
                right--
            }
        }
    }
    
    /**
     * Reverse array in groups of size k (recursive approach)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n/k) - Recursion stack space
     */
    fun reverseArrayInGroupsRecursive(arr: IntArray, k: Int) {
        reverseArrayInGroupsRecursiveHelper(arr, 0, arr.size, k)
    }
    
    private fun reverseArrayInGroupsRecursiveHelper(arr: IntArray, start: Int, n: Int, k: Int) {
        if (start >= n) return
        
        val end = minOf(start + k, n)
        reverseArrayInRange(arr, start, end - 1)
        reverseArrayInGroupsRecursiveHelper(arr, end, n, k)
    }
    
    /**
     * Reverse array in groups of size k (iterative with extra space)
     * 
     * This approach uses extra space but is more intuitive
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(k) - Extra space for temporary storage
     */
    fun reverseArrayInGroupsWithExtraSpace(arr: IntArray, k: Int): IntArray {
        val n = arr.size
        val result = IntArray(n)
        
        for (i in 0 until n step k) {
            val groupSize = minOf(k, n - i)
            for (j in 0 until groupSize) {
                result[i + j] = arr[i + groupSize - 1 - j]
            }
        }
        
        return result
    }
    
    /**
     * Check if array is palindrome (reads same forward and backward)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun isArrayPalindrome(arr: IntArray): Boolean {
        var left = 0
        var right = arr.size - 1
        
        while (left < right) {
            if (arr[left] != arr[right]) return false
            left++
            right--
        }
        return true
    }
    
    /**
     * Reverse array using recursion
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) - Recursion stack space
     */
    fun reverseArrayRecursive(arr: IntArray) {
        reverseArrayRecursiveHelper(arr, 0, arr.size - 1)
    }
    
    private fun reverseArrayRecursiveHelper(arr: IntArray, left: Int, right: Int) {
        if (left >= right) return
        
        arr[left] = arr[right].also { arr[right] = arr[left] }
        reverseArrayRecursiveHelper(arr, left + 1, right - 1)
    }
} 