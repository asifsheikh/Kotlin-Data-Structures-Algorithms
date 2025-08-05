package data_structure.lists.algorithms

/**
 * MAXIMUM SUBARRAY SUM (KADANE'S ALGORITHM)
 * 
 * Problem: Find the contiguous subarray with the largest sum.
 * 
 * Examples:
 * Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4] → Output: 6 (subarray [4, -1, 2, 1])
 * Input: [1, 2, 3, 4, 5] → Output: 15 (entire array)
 * Input: [-1, -2, -3, -4] → Output: -1 (single element)
 * Input: [5, 4, -1, 7, 8] → Output: 23 (entire array)
 * 
 * Intuition: Keep track of current sum and maximum sum seen so far
 * 
 * Time Complexity: O(n) - Single pass through list
 * Space Complexity: O(1) - Constant extra space
 */

object MaxSubarraySum {
    
    /**
     * Kadane's Algorithm - Find maximum subarray sum
     */
    fun kadaneAlgorithm(nums: List<Int>): Int {
        if (nums.isEmpty()) return 0
        
        var maxSoFar = nums[0]
        var maxEndingHere = nums[0]
        
        for (i in 1 until nums.size) {
            maxEndingHere = maxOf(nums[i], maxEndingHere + nums[i])
            maxSoFar = maxOf(maxSoFar, maxEndingHere)
        }
        
        return maxSoFar
    }
    
    /**
     * Kadane's Algorithm with indices - Return subarray with max sum
     */
    fun kadaneWithIndices(nums: List<Int>): Triple<Int, Int, Int> {
        if (nums.isEmpty()) return Triple(0, -1, -1)
        
        var maxSoFar = nums[0]
        var maxEndingHere = nums[0]
        var start = 0
        var end = 0
        var tempStart = 0
        
        for (i in 1 until nums.size) {
            if (nums[i] > maxEndingHere + nums[i]) {
                maxEndingHere = nums[i]
                tempStart = i
            } else {
                maxEndingHere += nums[i]
            }
            
            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere
                start = tempStart
                end = i
            }
        }
        
        return Triple(maxSoFar, start, end)
    }
    
    /**
     * Find maximum subarray sum with at least k elements
     */
    fun maxSubarraySumAtLeastK(nums: List<Int>, k: Int): Int {
        if (nums.size < k) return 0
        
        var maxSum = 0
        for (i in 0 until k) {
            maxSum += nums[i]
        }
        
        var currentSum = maxSum
        for (i in k until nums.size) {
            currentSum += nums[i] - nums[i - k]
            maxSum = maxOf(maxSum, currentSum)
        }
        
        return maxSum
    }
    
    /**
     * Find maximum subarray sum with circular array
     */
    fun maxSubarraySumCircular(nums: List<Int>): Int {
        if (nums.isEmpty()) return 0
        
        // Case 1: Maximum subarray sum (normal Kadane)
        val maxNormal = kadaneAlgorithm(nums)
        
        // Case 2: Maximum subarray sum with circular wrap
        val totalSum = nums.sum()
        val minSubarraySum = kadaneAlgorithm(nums.map { -it })
        val maxCircular = totalSum + minSubarraySum
        
        return if (maxCircular == 0) maxNormal else maxOf(maxNormal, maxCircular)
    }
    
    /**
     * Find maximum subarray sum with size constraint
     */
    fun maxSubarraySumSizeK(nums: List<Int>, k: Int): Int {
        if (nums.size < k) return 0
        
        var currentSum = 0
        for (i in 0 until k) {
            currentSum += nums[i]
        }
        
        var maxSum = currentSum
        for (i in k until nums.size) {
            currentSum += nums[i] - nums[i - k]
            maxSum = maxOf(maxSum, currentSum)
        }
        
        return maxSum
    }
    
    /**
     * Find maximum subarray sum with alternating signs
     */
    fun maxSubarraySumAlternating(nums: List<Int>): Int {
        if (nums.isEmpty()) return 0
        
        var maxEven = 0 // Max sum ending at even index
        var maxOdd = 0  // Max sum ending at odd index
        var result = 0
        
        for (i in nums.indices) {
            if (i % 2 == 0) {
                maxEven = maxOf(nums[i], maxEven + nums[i])
                result = maxOf(result, maxEven)
            } else {
                maxOdd = maxOf(nums[i], maxOdd + nums[i])
                result = maxOf(result, maxOdd)
            }
        }
        
        return result
    }
    
    /**
     * Find maximum subarray sum with non-negative constraint
     */
    fun maxSubarraySumNonNegative(nums: List<Int>): Int {
        if (nums.isEmpty()) return 0
        
        var currentSum = 0
        var maxSum = 0
        
        for (num in nums) {
            currentSum = maxOf(0, currentSum + num)
            maxSum = maxOf(maxSum, currentSum)
        }
        
        return maxSum
    }
} 