package data_structure.lists.algorithms

/**
 * TWO SUM PROBLEM
 * 
 * Problem: Given a list of integers and a target sum, find two numbers that add up to the target.
 * 
 * Examples:
 * Input: [2, 7, 11, 15], target = 9 → Output: [0, 1] (2 + 7 = 9)
 * Input: [3, 2, 4], target = 6 → Output: [1, 2] (2 + 4 = 6)
 * Input: [3, 3], target = 6 → Output: [0, 1] (3 + 3 = 6)
 * 
 * Intuition: Use hash map to store complements for O(n) time complexity
 * 
 * Time Complexity: O(n) - Single pass through list
 * Space Complexity: O(n) - Hash map storage
 */

object TwoSum {
    
    /**
     * Find indices of two numbers that sum to target using hash map
     */
    fun findIndices(nums: List<Int>, target: Int): List<Int> {
        val seen = mutableMapOf<Int, Int>()
        
        for (i in nums.indices) {
            val complement = target - nums[i]
            if (complement in seen) {
                return listOf(seen[complement]!!, i)
            }
            seen[nums[i]] = i
        }
        
        return emptyList() // No solution found
    }
    
    /**
     * Find values of two numbers that sum to target
     */
    fun findValues(nums: List<Int>, target: Int): List<Int> {
        val seen = mutableSetOf<Int>()
        
        for (num in nums) {
            val complement = target - num
            if (complement in seen) {
                return listOf(complement, num)
            }
            seen.add(num)
        }
        
        return emptyList() // No solution found
    }
    
    /**
     * Find all pairs that sum to target
     */
    fun findAllPairs(nums: List<Int>, target: Int): List<List<Int>> {
        val seen = mutableMapOf<Int, Int>()
        val result = mutableListOf<List<Int>>()
        
        for (i in nums.indices) {
            val complement = target - nums[i]
            if (complement in seen) {
                result.add(listOf(complement, nums[i]))
            }
            seen[nums[i]] = i
        }
        
        return result
    }
    
    /**
     * Find indices for sorted list using two pointers
     */
    fun findIndicesSorted(nums: List<Int>, target: Int): List<Int> {
        var left = 0
        var right = nums.size - 1
        
        while (left < right) {
            val sum = nums[left] + nums[right]
            when {
                sum == target -> return listOf(left, right)
                sum < target -> left++
                else -> right--
            }
        }
        
        return emptyList() // No solution found
    }
    
    /**
     * Find closest sum to target
     */
    fun findClosestSum(nums: List<Int>, target: Int): List<Int> {
        if (nums.size < 2) return emptyList()
        
        var closestSum = nums[0] + nums[1]
        var closestPair = listOf(0, 1)
        
        for (i in 0 until nums.size - 1) {
            for (j in i + 1 until nums.size) {
                val sum = nums[i] + nums[j]
                if (kotlin.math.abs(sum - target) < kotlin.math.abs(closestSum - target)) {
                    closestSum = sum
                    closestPair = listOf(i, j)
                }
            }
        }
        
        return closestPair
    }
    
    /**
     * Three Sum variant
     */
    fun threeSum(nums: List<Int>, target: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val sorted = nums.sorted()
        
        for (i in 0 until sorted.size - 2) {
            if (i > 0 && sorted[i] == sorted[i - 1]) continue
            
            var left = i + 1
            var right = sorted.size - 1
            
            while (left < right) {
                val sum = sorted[i] + sorted[left] + sorted[right]
                when {
                    sum == target -> {
                        result.add(listOf(sorted[i], sorted[left], sorted[right]))
                        while (left < right && sorted[left] == sorted[left + 1]) left++
                        while (left < right && sorted[right] == sorted[right - 1]) right--
                        left++
                        right--
                    }
                    sum < target -> left++
                    else -> right--
                }
            }
        }
        
        return result
    }
} 