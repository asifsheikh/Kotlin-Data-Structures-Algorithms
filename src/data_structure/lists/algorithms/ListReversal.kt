package data_structure.lists.algorithms

/**
 * LIST REVERSAL ALGORITHMS
 * 
 * Problem: Reverse a list or parts of a list efficiently.
 * 
 * Examples:
 * Input: [1, 2, 3, 4, 5] → Output: [5, 4, 3, 2, 1]
 * Input: [1, 2, 3, 4, 5], start=1, end=3 → Output: [1, 4, 3, 2, 5]
 * Input: ["a", "b", "c"] → Output: ["c", "b", "a"]
 * 
 * Intuition: Use two pointers to swap elements from both ends
 * 
 * Time Complexity: O(n) - Single pass through list
 * Space Complexity: O(1) - In-place modification
 */

object ListReversal {
    
    /**
     * Reverse entire list in-place
     */
    fun reverseList(nums: MutableList<Int>) {
        var left = 0
        var right = nums.size - 1
        
        while (left < right) {
            nums[left] = nums[right].also { nums[right] = nums[left] }
            left++
            right--
        }
    }
    
    /**
     * Reverse list in range [start, end]
     */
    fun reverseRange(nums: MutableList<Int>, start: Int, end: Int) {
        var left = start
        var right = end
        
        while (left < right) {
            nums[left] = nums[right].also { nums[right] = nums[left] }
            left++
            right--
        }
    }
    
    /**
     * Reverse list using built-in function
     */
    fun reverseBuiltin(nums: List<Int>): List<Int> {
        return nums.reversed()
    }
    
    /**
     * Reverse list using stack
     */
    fun reverseWithStack(nums: List<Int>): List<Int> {
        val stack = mutableListOf<Int>()
        for (num in nums) {
            stack.add(num)
        }
        
        val result = mutableListOf<Int>()
        while (stack.isNotEmpty()) {
            result.add(stack.removeAt(stack.size - 1))
        }
        
        return result
    }
    
    /**
     * Reverse list using recursion
     */
    fun reverseRecursive(nums: List<Int>): List<Int> {
        if (nums.size <= 1) return nums
        
        return listOf(nums.last()) + reverseRecursive(nums.dropLast(1))
    }
    
    /**
     * Reverse list in groups of k
     */
    fun reverseInGroups(nums: MutableList<Int>, k: Int) {
        val n = nums.size
        
        for (i in 0 until n step k) {
            val end = minOf(i + k - 1, n - 1)
            reverseRange(nums, i, end)
        }
    }
    
    /**
     * Reverse alternate groups of k
     */
    fun reverseAlternateGroups(nums: MutableList<Int>, k: Int) {
        val n = nums.size
        
        for (i in 0 until n step 2 * k) {
            val end = minOf(i + k - 1, n - 1)
            reverseRange(nums, i, end)
        }
    }
    
    /**
     * Reverse words in a list of strings
     */
    fun reverseWords(words: List<String>): List<String> {
        return words.reversed()
    }
    
    /**
     * Reverse list while preserving special characters
     */
    fun reversePreservingSpecial(nums: List<Char>): List<Char> {
        val result = nums.toMutableList()
        var left = 0
        var right = result.size - 1
        
        while (left < right) {
            // Skip non-alphanumeric characters from left
            while (left < right && !result[left].isLetterOrDigit()) {
                left++
            }
            
            // Skip non-alphanumeric characters from right
            while (left < right && !result[right].isLetterOrDigit()) {
                right--
            }
            
            // Swap alphanumeric characters
            if (left < right) {
                result[left] = result[right].also { result[right] = result[left] }
                left++
                right--
            }
        }
        
        return result
    }
    
    /**
     * Reverse list with custom condition
     */
    fun reverseWithCondition(nums: List<Int>, condition: (Int) -> Boolean): List<Int> {
        val result = mutableListOf<Int>()
        val stack = mutableListOf<Int>()
        
        for (num in nums) {
            if (condition(num)) {
                stack.add(num)
            } else {
                // Add reversed stack elements
                while (stack.isNotEmpty()) {
                    result.add(stack.removeAt(stack.size - 1))
                }
                result.add(num)
            }
        }
        
        // Add remaining stack elements
        while (stack.isNotEmpty()) {
            result.add(stack.removeAt(stack.size - 1))
        }
        
        return result
    }
    
    /**
     * Reverse list in pairs
     */
    fun reverseInPairs(nums: MutableList<Int>) {
        for (i in 0 until nums.size - 1 step 2) {
            nums[i] = nums[i + 1].also { nums[i + 1] = nums[i] }
        }
    }
    
    /**
     * Reverse list in triplets
     */
    fun reverseInTriplets(nums: MutableList<Int>) {
        for (i in 0 until nums.size - 2 step 3) {
            if (i + 2 < nums.size) {
                nums[i] = nums[i + 2].also { nums[i + 2] = nums[i] }
            }
        }
    }
} 