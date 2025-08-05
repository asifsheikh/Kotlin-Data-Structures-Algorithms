package data_structure.arrays.algorithms

/**
 * TWO SUM PROBLEM
 * 
 * Problem: Given an array of integers and a target sum, find two numbers
 * in the array that add up to the target.
 * 
 * Given an array of integers nums and an integer target, return indices
 * of the two numbers such that they add up to target.
 * 
 * You may assume that each input would have exactly one solution, and
 * you may not use the same element twice.
 * 
 * Example:
 * Input: nums = [2, 7, 11, 15], target = 9
 * Output: [0, 1] (because nums[0] + nums[1] = 2 + 7 = 9)
 * 
 * Input: nums = [3, 2, 4], target = 6
 * Output: [1, 2] (because nums[1] + nums[2] = 2 + 4 = 6)
 * 
 * Intuition:
 * - We need to find two numbers that sum to target
 * - For each number, we can look for its complement (target - number)
 * - Use a hash map to store numbers we've seen and their indices
 * - For each current number, check if its complement exists in the map
 * - If found, we have our pair
 * 
 * Variations:
 * - Return indices vs return values
 * - Handle duplicates
 * - Find all pairs vs find one pair
 * - Array sorted vs unsorted
 */

object TwoSum {
    
    /**
     * Two Sum - Return Indices (Hash Map Solution)
     * 
     * Algorithm:
     * 1. Create a hash map to store number -> index mapping
     * 2. For each number in array:
     *    - Calculate complement = target - current number
     *    - If complement exists in map, return [complement_index, current_index]
     *    - Add current number and its index to map
     * 3. Return empty array if no solution found
     * 
     * Time Complexity: O(n) - Single pass through array
     * Space Complexity: O(n) - Hash map to store numbers
     * 
     * This is the most efficient solution for unsorted arrays.
     */
    fun findIndices(nums: IntArray, target: Int): IntArray {
        val numMap = mutableMapOf<Int, Int>()
        
        for (i in nums.indices) {
            val complement = target - nums[i]
            
            if (complement in numMap) {
                return intArrayOf(numMap[complement]!!, i)
            }
            
            numMap[nums[i]] = i
        }
        
        return intArrayOf() // No solution found
    }
    
    /**
     * Two Sum - Return Values (Hash Map Solution)
     * 
     * Same algorithm but returns the actual values instead of indices.
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun findValues(nums: IntArray, target: Int): IntArray {
        val numSet = mutableSetOf<Int>()
        
        for (num in nums) {
            val complement = target - num
            
            if (complement in numSet) {
                return intArrayOf(complement, num)
            }
            
            numSet.add(num)
        }
        
        return intArrayOf() // No solution found
    }
    
    /**
     * Two Sum - Sorted Array (Two Pointers Solution)
     * 
     * If the array is sorted, we can use two pointers for better space efficiency.
     * 
     * Algorithm:
     * 1. Use two pointers: left (start) and right (end)
     * 2. Calculate sum = nums[left] + nums[right]
     * 3. If sum == target, return [left, right]
     * 4. If sum < target, move left pointer right
     * 5. If sum > target, move right pointer left
     * 6. Continue until pointers meet
     * 
     * Time Complexity: O(n) - Single pass with two pointers
     * Space Complexity: O(1) - Only two pointers used
     */
    fun findIndicesSorted(nums: IntArray, target: Int): IntArray {
        var left = 0
        var right = nums.size - 1
        
        while (left < right) {
            val sum = nums[left] + nums[right]
            
            when {
                sum == target -> return intArrayOf(left, right)
                sum < target -> left++
                else -> right--
            }
        }
        
        return intArrayOf() // No solution found
    }
    
    /**
     * Two Sum - Find All Pairs
     * 
     * Find all pairs that sum to target (may have duplicates).
     * 
     * Algorithm:
     * 1. Use hash map to count frequency of each number
     * 2. For each number, check if complement exists
     * 3. Handle duplicates carefully
     * 4. Return all valid pairs
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun findAllPairs(nums: IntArray, target: Int): List<Pair<Int, Int>> {
        val frequency = mutableMapOf<Int, Int>()
        val result = mutableListOf<Pair<Int, Int>>()
        
        // Count frequency of each number
        for (num in nums) {
            frequency[num] = frequency.getOrDefault(num, 0) + 1
        }
        
        // Find pairs
        for (num in nums) {
            val complement = target - num
            
            if (complement in frequency) {
                // Handle duplicates
                if (num == complement) {
                    if (frequency[num]!! >= 2) {
                        result.add(Pair(num, complement))
                        frequency[num] = frequency[num]!! - 2
                    }
                } else {
                    if (frequency[num]!! > 0 && frequency[complement]!! > 0) {
                        result.add(Pair(num, complement))
                        frequency[num] = frequency[num]!! - 1
                        frequency[complement] = frequency[complement]!! - 1
                    }
                }
            }
        }
        
        return result
    }
    
    /**
     * Two Sum - Return Indices for All Pairs
     * 
     * Find all pairs of indices that sum to target.
     * 
     * Algorithm:
     * 1. Use hash map to store number -> list of indices
     * 2. For each number, find all valid pairs with its complement
     * 3. Handle duplicates by using different indices
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun findAllIndexPairs(nums: IntArray, target: Int): List<Pair<Int, Int>> {
        val indexMap = mutableMapOf<Int, MutableList<Int>>()
        val result = mutableListOf<Pair<Int, Int>>()
        
        // Build index map
        for (i in nums.indices) {
            indexMap.getOrPut(nums[i]) { mutableListOf() }.add(i)
        }
        
        // Find pairs
        for (i in nums.indices) {
            val complement = target - nums[i]
            
            if (complement in indexMap) {
                for (j in indexMap[complement]!!) {
                    if (i < j) { // Avoid duplicates and same element
                        result.add(Pair(i, j))
                    }
                }
            }
        }
        
        return result
    }
    
    /**
     * Two Sum - Closest to Target
     * 
     * Find two numbers whose sum is closest to target.
     * 
     * Algorithm:
     * 1. Sort the array
     * 2. Use two pointers approach
     * 3. Keep track of closest sum found
     * 4. Update closest sum when better pair found
     * 
     * Time Complexity: O(n log n) - Due to sorting
     * Space Complexity: O(1)
     */
    fun findClosestSum(nums: IntArray, target: Int): IntArray {
        nums.sort()
        
        var left = 0
        var right = nums.size - 1
        var closestSum = nums[0] + nums[1]
        var closestPair = intArrayOf(0, 1)
        
        while (left < right) {
            val sum = nums[left] + nums[right]
            
            if (abs(sum - target) < abs(closestSum - target)) {
                closestSum = sum
                closestPair = intArrayOf(nums[left], nums[right])
            }
            
            when {
                sum == target -> return intArrayOf(nums[left], nums[right])
                sum < target -> left++
                else -> right--
            }
        }
        
        return closestPair
    }
    
    /**
     * Two Sum - BST Solution
     * 
     * Use Binary Search Tree for sorted array.
     * 
     * Algorithm:
     * 1. For each element, search for complement in BST
     * 2. If found, return the pair
     * 3. Insert current element into BST
     * 
     * Time Complexity: O(n log n) - Each search is O(log n)
     * Space Complexity: O(n) - BST storage
     */
    fun findIndicesBST(nums: IntArray, target: Int): IntArray {
        val bst = mutableSetOf<Int>()
        
        for (i in nums.indices) {
            val complement = target - nums[i]
            
            if (complement in bst) {
                // Find index of complement
                for (j in 0 until i) {
                    if (nums[j] == complement) {
                        return intArrayOf(j, i)
                    }
                }
            }
            
            bst.add(nums[i])
        }
        
        return intArrayOf()
    }
    
    /**
     * Two Sum - Handle Duplicates
     * 
     * Handle case where array may contain duplicates and we need
     * to find pairs with different indices.
     * 
     * Algorithm:
     * 1. Use hash map to store number -> list of indices
     * 2. For each number, find complement
     * 3. Ensure we use different indices for the pair
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun findIndicesWithDuplicates(nums: IntArray, target: Int): IntArray {
        val indexMap = mutableMapOf<Int, MutableList<Int>>()
        
        for (i in nums.indices) {
            val complement = target - nums[i]
            
            if (complement in indexMap) {
                // Find an index different from current
                for (j in indexMap[complement]!!) {
                    if (j != i) {
                        return intArrayOf(j, i)
                    }
                }
            }
            
            indexMap.getOrPut(nums[i]) { mutableListOf() }.add(i)
        }
        
        return intArrayOf()
    }
    
    /**
     * Two Sum - Three Sum Variant
     * 
     * Find three numbers that sum to target.
     * 
     * Algorithm:
     * 1. Sort the array
     * 2. For each element, use two sum approach on remaining elements
     * 3. Skip duplicates to avoid duplicate triplets
     * 
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     */
    fun threeSum(nums: IntArray, target: Int): List<List<Int>> {
        nums.sort()
        val result = mutableListOf<List<Int>>()
        
        for (i in 0 until nums.size - 2) {
            // Skip duplicates
            if (i > 0 && nums[i] == nums[i - 1]) continue
            
            var left = i + 1
            var right = nums.size - 1
            
            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]
                
                when {
                    sum == target -> {
                        result.add(listOf(nums[i], nums[left], nums[right]))
                        
                        // Skip duplicates
                        while (left < right && nums[left] == nums[left + 1]) left++
                        while (left < right && nums[right] == nums[right - 1]) right--
                        
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