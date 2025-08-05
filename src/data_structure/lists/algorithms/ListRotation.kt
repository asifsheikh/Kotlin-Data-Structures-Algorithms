package data_structure.lists.algorithms

/**
 * LIST ROTATION
 * 
 * Problem: Given a list, rotate it to the right by k places.
 * 
 * Examples:
 * Input: [1, 2, 3, 4, 5], k = 2 → Output: [4, 5, 1, 2, 3]
 * Input: [0, 1, 2], k = 4 → Output: [2, 0, 1] (k % n = 1, so rotate by 1)
 * Input: [1, 2, 3], k = 0 → Output: [1, 2, 3] (no rotation)
 * 
 * Intuition: Handle case where k > list size using modulo, then use reverse algorithm
 * 
 * Time Complexity: O(n) - Three passes through list
 * Space Complexity: O(1) - In-place modification
 */

object ListRotation {
    
    /**
     * Rotate list to the right by k places using reverse algorithm
     */
    fun rotateRight(nums: MutableList<Int>, k: Int) {
        val n = nums.size
        if (n == 0) return
        
        val effectiveK = k % n
        if (effectiveK == 0) return
        
        // Reverse entire list
        reverse(nums, 0, n - 1)
        // Reverse first k elements
        reverse(nums, 0, effectiveK - 1)
        // Reverse remaining elements
        reverse(nums, effectiveK, n - 1)
    }
    
    /**
     * Rotate list to the left by k places
     */
    fun rotateLeft(nums: MutableList<Int>, k: Int) {
        val n = nums.size
        if (n == 0) return
        
        val effectiveK = k % n
        if (effectiveK == 0) return
        
        // Reverse entire list
        reverse(nums, 0, n - 1)
        // Reverse first n-k elements
        reverse(nums, 0, n - effectiveK - 1)
        // Reverse remaining elements
        reverse(nums, n - effectiveK, n - 1)
    }
    
    /**
     * Rotate using extra space
     */
    fun rotateWithExtraSpace(nums: List<Int>, k: Int): List<Int> {
        val n = nums.size
        if (n == 0) return nums
        
        val effectiveK = k % n
        if (effectiveK == 0) return nums
        
        return nums.takeLast(effectiveK) + nums.take(n - effectiveK)
    }
    
    /**
     * Rotate using modular arithmetic
     */
    fun rotateModular(nums: List<Int>, k: Int): List<Int> {
        val n = nums.size
        if (n == 0) return nums
        
        val effectiveK = k % n
        if (effectiveK == 0) return nums
        
        return List(n) { i ->
            val newIndex = (i - effectiveK + n) % n
            nums[newIndex]
        }
    }
    
    /**
     * Helper function to reverse list in range
     */
    private fun reverse(nums: MutableList<Int>, start: Int, end: Int) {
        var left = start
        var right = end
        
        while (left < right) {
            nums[left] = nums[right].also { nums[right] = nums[left] }
            left++
            right--
        }
    }
} 