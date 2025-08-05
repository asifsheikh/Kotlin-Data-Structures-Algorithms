package data_structure.lists.algorithms

/**
 * REMOVE DUPLICATES FROM SORTED LIST
 * 
 * Problem: Given a sorted list, remove all duplicates such that each element appears only once.
 * 
 * Examples:
 * Input: [1, 1, 2] → Output: [1, 2]
 * Input: [1, 1, 2, 3, 3] → Output: [1, 2, 3]
 * Input: [1, 2, 3, 4, 5] → Output: [1, 2, 3, 4, 5] (no duplicates)
 * Input: [1, 1, 1, 1] → Output: [1]
 * 
 * Intuition: Use two pointers approach - one for reading, one for writing
 * 
 * Time Complexity: O(n) - Single pass through list
 * Space Complexity: O(1) - In-place modification
 */

object RemoveDuplicates {
    
    /**
     * Remove duplicates from sorted list using two pointers
     */
    fun removeDuplicates(nums: MutableList<Int>): Int {
        if (nums.isEmpty()) return 0
        
        var writeIndex = 1
        
        for (i in 1 until nums.size) {
            if (nums[i] != nums[i - 1]) {
                nums[writeIndex] = nums[i]
                writeIndex++
            }
        }
        
        return writeIndex
    }
    
    /**
     * Remove duplicates using built-in distinct function
     */
    fun removeDuplicatesBuiltin(nums: List<Int>): List<Int> {
        return nums.distinct()
    }
    
    /**
     * Remove duplicates using set
     */
    fun removeDuplicatesWithSet(nums: List<Int>): List<Int> {
        val seen = mutableSetOf<Int>()
        return nums.filter { seen.add(it) }
    }
    
    /**
     * Remove duplicates from unsorted list
     */
    fun removeDuplicatesUnsorted(nums: List<Int>): List<Int> {
        val seen = mutableSetOf<Int>()
        return nums.filter { seen.add(it) }
    }
} 