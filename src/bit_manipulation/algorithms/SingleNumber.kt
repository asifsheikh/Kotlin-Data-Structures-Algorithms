package bit_manipulation.algorithms

/**
 * SINGLE NUMBER ALGORITHMS
 * 
 * Problem: Find the single number in an array where every other number appears a specific number of times.
 * 
 * Examples:
 * Input: [2, 2, 1] → Output: 1 (every number appears twice except 1)
 * Input: [4, 1, 2, 1, 2] → Output: 4 (every number appears twice except 4)
 * Input: [2, 2, 3, 2] → Output: 3 (every number appears three times except 3)
 * 
 * Intuition: Use XOR for twice, bit counting for thrice, and advanced techniques for other frequencies
 * 
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(1) - constant extra space
 */

object SingleNumber {
    
    /**
     * Single Number I - Every number appears twice except one
     * 
     * Problem: Given a non-empty array of integers, every element appears twice except for one. Find that single one.
     * 
     * Examples:
     * Input: [2, 2, 1] → Output: 1
     * Input: [4, 1, 2, 1, 2] → Output: 4
     * Input: [1] → Output: 1
     * 
     * Intuition: XOR all numbers - same numbers XOR to 0, single number remains
     * 
     * Time Complexity: O(n) - single pass through array
     * Space Complexity: O(1) - constant extra space
     */
    fun singleNumberI(nums: IntArray): Int {
        return nums.reduce { acc, num -> acc xor num }
    }
    
    /**
     * Single Number II - Every number appears three times except one
     * 
     * Problem: Given a non-empty array of integers, every element appears three times except for one. Find that single one.
     * 
     * Examples:
     * Input: [2, 2, 3, 2] → Output: 3
     * Input: [0, 1, 0, 1, 0, 1, 99] → Output: 99
     * Input: [1, 1, 1, 2] → Output: 2
     * 
     * Intuition: Count bits at each position modulo 3, reconstruct the single number
     * 
     * Time Complexity: O(n) - single pass through array
     * Space Complexity: O(1) - constant extra space
     */
    fun singleNumberII(nums: IntArray): Int {
        var result = 0
        
        // Check each bit position
        for (i in 0 until 32) {
            var sum = 0
            
            // Count set bits at position i
            for (num in nums) {
                sum += (num shr i) and 1
            }
            
            // If count is not divisible by 3, set the bit in result
            if (sum % 3 != 0) {
                result = result or (1 shl i)
            }
        }
        
        return result
    }
    
    /**
     * Single Number II - Optimized version using bit manipulation
     */
    fun singleNumberIIOptimized(nums: IntArray): Int {
        var ones = 0  // Bits that have appeared once
        var twos = 0  // Bits that have appeared twice
        
        for (num in nums) {
            // Update twos: bits that have appeared twice
            twos = twos or (ones and num)
            
            // Update ones: bits that have appeared once
            ones = ones xor num
            
            // Clear bits that have appeared three times
            val threes = ones and twos
            ones = ones and threes.inv()
            twos = twos and threes.inv()
        }
        
        return ones
    }
    
    /**
     * Single Number III - Two numbers appear once, others twice
     * 
     * Problem: Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
     * 
     * Examples:
     * Input: [1, 2, 1, 3, 2, 5] → Output: [3, 5]
     * Input: [2, 4, 6, 8, 10, 2, 6, 10] → Output: [4, 8]
     * Input: [1, 1, 2, 2, 3, 4] → Output: [3, 4]
     * 
     * Intuition: XOR all numbers to get XOR of two single numbers, find a set bit, divide array into two groups
     * 
     * Time Complexity: O(n) - two passes through array
     * Space Complexity: O(1) - constant extra space
     */
    fun singleNumberIII(nums: IntArray): IntArray {
        // XOR all numbers to get XOR of two single numbers
        val xorResult = nums.reduce { acc, num -> acc xor num }
        
        // Find the rightmost set bit in XOR result
        val rightmostSetBit = xorResult and -xorResult
        
        // Divide numbers into two groups based on this bit
        var group1 = 0
        var group2 = 0
        
        for (num in nums) {
            if ((num and rightmostSetBit) != 0) {
                group1 = group1 xor num
            } else {
                group2 = group2 xor num
            }
        }
        
        return intArrayOf(group1, group2)
    }
    
    /**
     * Single Number IV - Every number appears k times except one
     * 
     * Problem: Given an array where every element appears k times except one element that appears m times (m < k), find that element.
     * 
     * Examples:
     * Input: [2, 2, 2, 4, 4, 4, 5, 5, 5, 3], k = 3, m = 1 → Output: 3
     * Input: [1, 1, 1, 1, 2, 2, 2, 2, 3], k = 4, m = 1 → Output: 3
     * 
     * Intuition: Count bits at each position modulo k, reconstruct the number
     * 
     * Time Complexity: O(n) - single pass through array
     * Space Complexity: O(1) - constant extra space
     */
    fun singleNumberIV(nums: IntArray, k: Int, m: Int): Int {
        var result = 0
        
        // Check each bit position
        for (i in 0 until 32) {
            var sum = 0
            
            // Count set bits at position i
            for (num in nums) {
                sum += (num shr i) and 1
            }
            
            // If count is not divisible by k, set the bit in result
            if (sum % k != 0) {
                result = result or (1 shl i)
            }
        }
        
        return result
    }
    
    /**
     * Single Number V - Using hash map (alternative approach)
     * 
     * Problem: Find single number using hash map approach
     * 
     * Time Complexity: O(n) - single pass through array
     * Space Complexity: O(n) - hash map storage
     */
    fun singleNumberV(nums: IntArray): Int {
        val frequency = mutableMapOf<Int, Int>()
        
        for (num in nums) {
            frequency[num] = frequency.getOrDefault(num, 0) + 1
        }
        
        for ((num, count) in frequency) {
            if (count == 1) {
                return num
            }
        }
        
        return -1 // No single number found
    }
    
    /**
     * Single Number VI - Using set (alternative approach)
     * 
     * Problem: Find single number using set approach
     * 
     * Time Complexity: O(n) - single pass through array
     * Space Complexity: O(n) - set storage
     */
    fun singleNumberVI(nums: IntArray): Int {
        val seen = mutableSetOf<Int>()
        
        for (num in nums) {
            if (num in seen) {
                seen.remove(num)
            } else {
                seen.add(num)
            }
        }
        
        return seen.first()
    }
    
    /**
     * Single Number VII - Using sorting (alternative approach)
     * 
     * Problem: Find single number using sorting approach
     * 
     * Time Complexity: O(n log n) - sorting
     * Space Complexity: O(1) - in-place sorting
     */
    fun singleNumberVII(nums: IntArray): Int {
        nums.sort()
        
        for (i in 0 until nums.size - 1 step 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i]
            }
        }
        
        return nums.last()
    }
    
    /**
     * Demonstrate all single number algorithms
     */
    fun demonstrateSingleNumberAlgorithms() {
        println("=== SINGLE NUMBER ALGORITHMS DEMONSTRATION ===")
        
        // Single Number I
        println("1. SINGLE NUMBER I (Every number appears twice except one)")
        val nums1 = intArrayOf(2, 2, 1)
        val nums2 = intArrayOf(4, 1, 2, 1, 2)
        println("Input: ${nums1.contentToString()}")
        println("Output: ${singleNumberI(nums1)}")
        println("Input: ${nums2.contentToString()}")
        println("Output: ${singleNumberI(nums2)}")
        println()
        
        // Single Number II
        println("2. SINGLE NUMBER II (Every number appears three times except one)")
        val nums3 = intArrayOf(2, 2, 3, 2)
        val nums4 = intArrayOf(0, 1, 0, 1, 0, 1, 99)
        println("Input: ${nums3.contentToString()}")
        println("Output: ${singleNumberII(nums3)}")
        println("Optimized Output: ${singleNumberIIOptimized(nums3)}")
        println("Input: ${nums4.contentToString()}")
        println("Output: ${singleNumberII(nums4)}")
        println("Optimized Output: ${singleNumberIIOptimized(nums4)}")
        println()
        
        // Single Number III
        println("3. SINGLE NUMBER III (Two numbers appear once, others twice)")
        val nums5 = intArrayOf(1, 2, 1, 3, 2, 5)
        val nums6 = intArrayOf(2, 4, 6, 8, 10, 2, 6, 10)
        println("Input: ${nums5.contentToString()}")
        println("Output: ${singleNumberIII(nums5).contentToString()}")
        println("Input: ${nums6.contentToString()}")
        println("Output: ${singleNumberIII(nums6).contentToString()}")
        println()
        
        // Single Number IV
        println("4. SINGLE NUMBER IV (Every number appears k times except one)")
        val nums7 = intArrayOf(2, 2, 2, 4, 4, 4, 5, 5, 5, 3)
        println("Input: ${nums7.contentToString()}, k = 3, m = 1")
        println("Output: ${singleNumberIV(nums7, 3, 1)}")
        println()
        
        // Alternative approaches
        println("5. ALTERNATIVE APPROACHES")
        println("Hash Map Approach:")
        println("Input: ${nums1.contentToString()}")
        println("Output: ${singleNumberV(nums1)}")
        
        println("Set Approach:")
        println("Input: ${nums1.contentToString()}")
        println("Output: ${singleNumberVI(nums1)}")
        
        println("Sorting Approach:")
        println("Input: ${nums1.contentToString()}")
        println("Output: ${singleNumberVII(nums1)}")
        println()
        
        println("=== SINGLE NUMBER ALGORITHMS DEMONSTRATION COMPLETE ===")
    }
}
