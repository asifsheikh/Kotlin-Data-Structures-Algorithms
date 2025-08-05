package data_structure.arrays

/**
 * ARRAY ALGORITHMS
 * 
 * This file contains algorithms that use arrays including
 * two pointers, sliding window, prefix sum, and other array-based algorithms.
 * 
 * COMMON ARRAY ALGORITHMS:
 * - Two Pointers: Efficient array traversal and manipulation
 * - Sliding Window: Process subarrays with optimal complexity
 * - Prefix Sum: Precompute sums for range queries
 * - Kadane's Algorithm: Maximum subarray sum
 * - Dutch National Flag: Three-way partitioning
 * - Array Rotation: Rotate arrays efficiently
 */

object ArrayAlgorithms {
    
    /**
     * PROBLEM: Two Sum
     * 
     * Given an array of integers and a target sum, find two numbers that add up to the target.
     * 
     * INPUT: Array of integers and target sum
     * OUTPUT: Indices of two numbers that sum to target, or null if not found
     * 
     * EXAMPLES:
     * Input: nums = [2, 7, 11, 15], target = 9
     * Output: [0, 1] (2 + 7 = 9)
     * 
     * Input: nums = [3, 2, 4], target = 6
     * Output: [1, 2] (2 + 4 = 6)
     * 
     * Input: nums = [3, 3], target = 6
     * Output: [0, 1] (3 + 3 = 6)
     * 
     * INTUITION:
     * - Use a hash map to store complements (target - current number)
     * - For each number, check if its complement exists in the map
     * - If found, return current index and complement's index
     * - If not found, store current number and its index
     * 
     * TIME COMPLEXITY: O(n) - Single pass through array
     * SPACE COMPLEXITY: O(n) - Hash map storage
     * 
     * @param nums Array of integers
     * @param target Target sum
     * @return Indices of two numbers that sum to target, or null
     */
    fun twoSum(nums: IntArray, target: Int): IntArray? {
        val map = mutableMapOf<Int, Int>()
        
        for (i in nums.indices) {
            val complement = target - nums[i]
            if (complement in map) {
                return intArrayOf(map[complement]!!, i)
            }
            map[nums[i]] = i
        }
        
        return null
    }
    
    /**
     * PROBLEM: Maximum Subarray Sum (Kadane's Algorithm)
     * 
     * Given an array of integers, find the contiguous subarray with the largest sum.
     * 
     * INPUT: Array of integers (can contain negative numbers)
     * OUTPUT: Maximum sum of any contiguous subarray
     * 
     * EXAMPLES:
     * Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
     * Output: 6 (subarray [4, -1, 2, 1] has sum 6)
     * 
     * Input: [1, 2, 3, 4, 5]
     * Output: 15 (entire array has sum 15)
     * 
     * Input: [-1, -2, -3, -4]
     * Output: -1 (single element -1)
     * 
     * INTUITION:
     * - Keep track of current sum and maximum sum seen so far
     * - For each element, decide whether to extend current subarray or start new one
     * - If current sum becomes negative, start fresh (negative sum won't help)
     * - Update maximum sum whenever current sum exceeds it
     * 
     * TIME COMPLEXITY: O(n) - Single pass through array
     * SPACE COMPLEXITY: O(1) - Only constant extra space
     * 
     * @param nums Array of integers
     * @return Maximum subarray sum
     */
    fun maxSubArray(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        
        var currentSum = nums[0]
        var maxSum = nums[0]
        
        for (i in 1 until nums.size) {
            currentSum = maxOf(nums[i], currentSum + nums[i])
            maxSum = maxOf(maxSum, currentSum)
        }
        
        return maxSum
    }
    
    /**
     * PROBLEM: Container With Most Water
     * 
     * Given an array representing heights of bars, find two bars that form a container
     * with the maximum area.
     * 
     * INPUT: Array of integers representing heights
     * OUTPUT: Maximum area of water that can be contained
     * 
     * EXAMPLES:
     * Input: [1, 8, 6, 2, 5, 4, 8, 3, 7]
     * Output: 49 (bars at indices 1 and 8, height 8 and 7, width 7)
     * 
     * Input: [1, 1]
     * Output: 1 (bars at indices 0 and 1, height 1, width 1)
     * 
     * Input: [4, 3, 2, 1, 4]
     * Output: 16 (bars at indices 0 and 4, height 4, width 4)
     * 
     * INTUITION:
     * - Use two pointers starting from both ends
     * - Calculate area = min(height[left], height[right]) * width
     * - Move the pointer with smaller height inward
     * - This is optimal because we can't get a larger area by keeping the smaller height
     * 
     * TIME COMPLEXITY: O(n) - Single pass with two pointers
     * SPACE COMPLEXITY: O(1) - Only constant extra space
     * 
     * @param height Array of heights
     * @return Maximum area
     */
    fun maxArea(height: IntArray): Int {
        var left = 0
        var right = height.size - 1
        var maxArea = 0
        
        while (left < right) {
            val area = minOf(height[left], height[right]) * (right - left)
            maxArea = maxOf(maxArea, area)
            
            if (height[left] < height[right]) {
                left++
            } else {
                right--
            }
        }
        
        return maxArea
    }
    
    /**
     * PROBLEM: Trapping Rain Water
     * 
     * Given an array representing heights of bars, calculate how much water can be trapped.
     * 
     * INPUT: Array of integers representing heights
     * OUTPUT: Total amount of water that can be trapped
     * 
     * EXAMPLES:
     * Input: [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
     * Output: 6 (water trapped between bars)
     * 
     * Input: [4, 2, 0, 3, 2, 5]
     * Output: 9
     * 
     * Input: [1, 2, 3, 4, 5]
     * Output: 0 (no water can be trapped)
     * 
     * INTUITION:
     * - For each position, water trapped = min(leftMax, rightMax) - currentHeight
     * - Use two pointers to track left and right maximums
     * - Move pointer with smaller maximum (can't trap more water on that side)
     * - Accumulate water when current height is less than minimum of left/right max
     * 
     * TIME COMPLEXITY: O(n) - Single pass with two pointers
     * SPACE COMPLEXITY: O(1) - Only constant extra space
     * 
     * @param height Array of heights
     * @return Total trapped water
     */
    fun trap(height: IntArray): Int {
        if (height.size < 3) return 0
        
        var left = 0
        var right = height.size - 1
        var leftMax = 0
        var rightMax = 0
        var water = 0
        
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left]
                } else {
                    water += leftMax - height[left]
                }
                left++
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right]
                } else {
                    water += rightMax - height[right]
                }
                right--
            }
        }
        
        return water
    }
    
    /**
     * PROBLEM: Move Zeroes
     * 
     * Given an array, move all zeroes to the end while maintaining the relative order
     * of non-zero elements.
     * 
     * INPUT: Array of integers
     * OUTPUT: Array with zeroes moved to the end (modify in-place)
     * 
     * EXAMPLES:
     * Input: [0, 1, 0, 3, 12]
     * Output: [1, 3, 12, 0, 0]
     * 
     * Input: [1, 2, 3, 4, 5]
     * Output: [1, 2, 3, 4, 5] (no zeroes)
     * 
     * Input: [0, 0, 0, 1, 2]
     * Output: [1, 2, 0, 0, 0]
     * 
     * INTUITION:
     * - Use two pointers: one for writing position, one for reading
     * - Write pointer points to next position for non-zero element
     * - Read pointer scans through array
     * - When non-zero element found, swap with write position and increment write pointer
     * - Fill remaining positions with zeroes
     * 
     * TIME COMPLEXITY: O(n) - Single pass through array
     * SPACE COMPLEXITY: O(1) - In-place modification
     * 
     * @param nums Array to modify
     */
    fun moveZeroes(nums: IntArray) {
        var writeIndex = 0
        
        // Move all non-zero elements to front
        for (i in nums.indices) {
            if (nums[i] != 0) {
                nums[writeIndex] = nums[i]
                writeIndex++
            }
        }
        
        // Fill remaining positions with zeroes
        while (writeIndex < nums.size) {
            nums[writeIndex] = 0
            writeIndex++
        }
    }
    
    /**
     * PROBLEM: Dutch National Flag (Sort Colors)
     * 
     * Given an array containing only 0s, 1s, and 2s, sort it in-place.
     * 
     * INPUT: Array containing only 0, 1, 2
     * OUTPUT: Sorted array (modify in-place)
     * 
     * EXAMPLES:
     * Input: [2, 0, 2, 1, 1, 0]
     * Output: [0, 0, 1, 1, 2, 2]
     * 
     * Input: [2, 0, 1]
     * Output: [0, 1, 2]
     * 
     * Input: [1, 1, 1, 1]
     * Output: [1, 1, 1, 1]
     * 
     * INTUITION:
     * - Use three pointers: low (for 0s), mid (for 1s), high (for 2s)
     * - low starts at beginning, high at end, mid scans through
     * - When mid encounters 0, swap with low and increment both
     * - When mid encounters 1, just increment mid
     * - When mid encounters 2, swap with high and decrement high
     * - Continue until mid > high
     * 
     * TIME COMPLEXITY: O(n) - Single pass through array
     * SPACE COMPLEXITY: O(1) - In-place modification
     * 
     * @param nums Array to sort
     */
    fun sortColors(nums: IntArray) {
        var low = 0
        var mid = 0
        var high = nums.size - 1
        
        while (mid <= high) {
            when (nums[mid]) {
                0 -> {
                    nums[low] = nums[mid].also { nums[mid] = nums[low] }
                    low++
                    mid++
                }
                1 -> mid++
                2 -> {
                    nums[mid] = nums[high].also { nums[high] = nums[mid] }
                    high--
                }
            }
        }
    }
    
    /**
     * PROBLEM: Sliding Window Maximum
     * 
     * Given an array and a window size k, find the maximum element in each sliding window.
     * 
     * INPUT: Array of integers and window size k
     * OUTPUT: Array of maximum values for each window
     * 
     * EXAMPLES:
     * Input: nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
     * Output: [3, 3, 5, 5, 6, 7]
     * 
     * Input: nums = [1], k = 1
     * Output: [1]
     * 
     * Input: nums = [1, -1], k = 1
     * Output: [1, -1]
     * 
     * INTUITION:
     * - Use a deque (double-ended queue) to maintain indices of potential maximums
     * - For each element, remove smaller elements from back of deque
     * - Add current element to back of deque
     * - Remove elements from front that are outside current window
     * - Front of deque always contains maximum for current window
     * 
     * TIME COMPLEXITY: O(n) - Each element pushed/popped at most once
     * SPACE COMPLEXITY: O(k) - Deque size at most k
     * 
     * @param nums Array of integers
     * @param k Window size
     * @return Array of maximum values for each window
     */
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        if (nums.isEmpty() || k <= 0) return intArrayOf()
        
        val result = IntArray(nums.size - k + 1)
        val deque = ArrayDeque<Int>()
        
        for (i in nums.indices) {
            // Remove elements outside current window
            while (deque.isNotEmpty() && deque.first() <= i - k) {
                deque.removeFirst()
            }
            
            // Remove smaller elements from back
            while (deque.isNotEmpty() && nums[deque.last()] < nums[i]) {
                deque.removeLast()
            }
            
            deque.addLast(i)
            
            // Add maximum to result
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.first()]
            }
        }
        
        return result
    }
    
    /**
     * PROBLEM: Product of Array Except Self
     * 
     * Given an array, return an array where each element is the product of all elements
     * except the element at that index.
     * 
     * INPUT: Array of integers
     * OUTPUT: Array where each element is product of all others
     * 
     * EXAMPLES:
     * Input: [1, 2, 3, 4]
     * Output: [24, 12, 8, 6] (24=2*3*4, 12=1*3*4, 8=1*2*4, 6=1*2*3)
     * 
     * Input: [-1, 1, 0, -3, 3]
     * Output: [0, 0, 9, 0, 0]
     * 
     * INTUITION:
     * - Use two passes: left to right and right to left
     * - First pass: calculate products of all elements to the left
     * - Second pass: multiply with products of all elements to the right
     * - This avoids division and handles zero cases
     * 
     * TIME COMPLEXITY: O(n) - Two passes through array
     * SPACE COMPLEXITY: O(1) - Only output array (not counting input)
     * 
     * @param nums Input array
     * @return Array of products
     */
    fun productExceptSelf(nums: IntArray): IntArray {
        val result = IntArray(nums.size)
        
        // Calculate products of elements to the left
        var leftProduct = 1
        for (i in nums.indices) {
            result[i] = leftProduct
            leftProduct *= nums[i]
        }
        
        // Multiply with products of elements to the right
        var rightProduct = 1
        for (i in nums.size - 1 downTo 0) {
            result[i] *= rightProduct
            rightProduct *= nums[i]
        }
        
        return result
    }
    
    /**
     * PROBLEM: Longest Consecutive Sequence
     * 
     * Given an unsorted array, find the length of the longest consecutive sequence.
     * 
     * INPUT: Array of integers
     * OUTPUT: Length of longest consecutive sequence
     * 
     * EXAMPLES:
     * Input: [100, 4, 200, 1, 3, 2]
     * Output: 4 (sequence [1, 2, 3, 4])
     * 
     * Input: [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]
     * Output: 9 (sequence [0, 1, 2, 3, 4, 5, 6, 7, 8])
     * 
     * Input: [1, 2, 0, 1]
     * Output: 3 (sequence [0, 1, 2])
     * 
     * INTUITION:
     * - Convert array to set for O(1) lookup
     * - For each number, check if it's the start of a sequence (no number-1 exists)
     * - If it's a start, count consecutive numbers forward
     * - Track maximum length found
     * 
     * TIME COMPLEXITY: O(n) - Each number visited at most twice
     * SPACE COMPLEXITY: O(n) - Set storage
     * 
     * @param nums Array of integers
     * @return Length of longest consecutive sequence
     */
    fun longestConsecutive(nums: IntArray): Int {
        val numSet = nums.toSet()
        var maxLength = 0
        
        for (num in numSet) {
            // Check if this is the start of a sequence
            if (num - 1 !in numSet) {
                var currentNum = num
                var currentLength = 1
                
                // Count consecutive numbers
                while (currentNum + 1 in numSet) {
                    currentNum++
                    currentLength++
                }
                
                maxLength = maxOf(maxLength, currentLength)
            }
        }
        
        return maxLength
    }
    
    /**
     * PROBLEM: Find Missing Number
     * 
     * Given an array containing n distinct numbers from 0 to n, find the missing number.
     * 
     * INPUT: Array of n distinct numbers from 0 to n (missing one)
     * OUTPUT: The missing number
     * 
     * EXAMPLES:
     * Input: [3, 0, 1]
     * Output: 2 (numbers are 0,1,2,3, missing 2)
     * 
     * Input: [0, 1]
     * Output: 2 (numbers are 0,1,2, missing 2)
     * 
     * Input: [9, 6, 4, 2, 3, 5, 7, 0, 1]
     * Output: 8 (numbers 0-9, missing 8)
     * 
     * INTUITION:
     * - Use XOR operation: a ^ a = 0, a ^ 0 = a
     * - XOR all numbers from 0 to n with all numbers in array
     * - Result will be the missing number
     * - Alternative: use sum formula n*(n+1)/2 - array sum
     * 
     * TIME COMPLEXITY: O(n) - Single pass through array
     * SPACE COMPLEXITY: O(1) - Only constant extra space
     * 
     * @param nums Array with missing number
     * @return Missing number
     */
    fun missingNumber(nums: IntArray): Int {
        var result = nums.size
        
        for (i in nums.indices) {
            result = result xor i xor nums[i]
        }
        
        return result
    }
    
    /**
     * PROBLEM: Find Duplicate Number
     * 
     * Given an array containing n+1 integers where each integer is between 1 and n,
     * find the duplicate number.
     * 
     * INPUT: Array of n+1 integers from 1 to n (one duplicate)
     * OUTPUT: The duplicate number
     * 
     * EXAMPLES:
     * Input: [1, 3, 4, 2, 2]
     * Output: 2 (duplicate)
     * 
     * Input: [3, 1, 3, 4, 2]
     * Output: 3 (duplicate)
     * 
     * Input: [1, 1]
     * Output: 1 (duplicate)
     * 
     * INTUITION:
     * - Use Floyd's cycle detection algorithm
     * - Treat array as linked list where nums[i] points to nums[nums[i]]
     * - Find intersection point of fast and slow pointers
     * - Reset slow pointer and find cycle start
     * 
     * TIME COMPLEXITY: O(n) - Linear time
     * SPACE COMPLEXITY: O(1) - Constant space
     * 
     * @param nums Array with duplicate
     * @return Duplicate number
     */
    fun findDuplicate(nums: IntArray): Int {
        var slow = nums[0]
        var fast = nums[0]
        
        // Find intersection point
        do {
            slow = nums[slow]
            fast = nums[nums[fast]]
        } while (slow != fast)
        
        // Find cycle start
        slow = nums[0]
        while (slow != fast) {
            slow = nums[slow]
            fast = nums[fast]
        }
        
        return slow
    }
    
    /**
     * Demonstrates array algorithms
     */
    fun demonstrateArrayAlgorithms() {
        println("=== ARRAY ALGORITHMS DEMONSTRATION ===\n")
        
        // 1. Two Sum
        println("1. TWO SUM")
        val twoSumNums = intArrayOf(2, 7, 11, 15)
        val twoSumTarget = 9
        val twoSumResult = twoSum(twoSumNums, twoSumTarget)
        println("Array: ${twoSumNums.contentToString()}")
        println("Target: $twoSumTarget")
        println("Result: ${twoSumResult?.contentToString()}")
        println()
        
        // 2. Maximum Subarray Sum
        println("2. MAXIMUM SUBARRAY SUM")
        val maxSubArrayNums = intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
        val maxSubArrayResult = maxSubArray(maxSubArrayNums)
        println("Array: ${maxSubArrayNums.contentToString()}")
        println("Maximum subarray sum: $maxSubArrayResult")
        println()
        
        // 3. Container With Most Water
        println("3. CONTAINER WITH MOST WATER")
        val containerHeight = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)
        val containerResult = maxArea(containerHeight)
        println("Heights: ${containerHeight.contentToString()}")
        println("Maximum area: $containerResult")
        println()
        
        // 4. Trapping Rain Water
        println("4. TRAPPING RAIN WATER")
        val trapHeight = intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)
        val trapResult = trap(trapHeight)
        println("Heights: ${trapHeight.contentToString()}")
        println("Trapped water: $trapResult")
        println()
        
        // 5. Move Zeroes
        println("5. MOVE ZEROES")
        val zeroesNums = intArrayOf(0, 1, 0, 3, 12)
        println("Before: ${zeroesNums.contentToString()}")
        moveZeroes(zeroesNums)
        println("After: ${zeroesNums.contentToString()}")
        println()
        
        // 6. Sort Colors
        println("6. SORT COLORS")
        val colorsNums = intArrayOf(2, 0, 2, 1, 1, 0)
        println("Before: ${colorsNums.contentToString()}")
        sortColors(colorsNums)
        println("After: ${colorsNums.contentToString()}")
        println()
        
        // 7. Sliding Window Maximum
        println("7. SLIDING WINDOW MAXIMUM")
        val slidingNums = intArrayOf(1, 3, -1, -3, 5, 3, 6, 7)
        val slidingK = 3
        val slidingResult = maxSlidingWindow(slidingNums, slidingK)
        println("Array: ${slidingNums.contentToString()}")
        println("Window size: $slidingK")
        println("Maximums: ${slidingResult.contentToString()}")
        println()
        
        // 8. Product Except Self
        println("8. PRODUCT EXCEPT SELF")
        val productNums = intArrayOf(1, 2, 3, 4)
        val productResult = productExceptSelf(productNums)
        println("Array: ${productNums.contentToString()}")
        println("Products: ${productResult.contentToString()}")
        println()
        
        // 9. Longest Consecutive Sequence
        println("9. LONGEST CONSECUTIVE SEQUENCE")
        val consecutiveNums = intArrayOf(100, 4, 200, 1, 3, 2)
        val consecutiveResult = longestConsecutive(consecutiveNums)
        println("Array: ${consecutiveNums.contentToString()}")
        println("Longest consecutive length: $consecutiveResult")
        println()
        
        // 10. Missing Number
        println("10. MISSING NUMBER")
        val missingNums = intArrayOf(3, 0, 1)
        val missingResult = missingNumber(missingNums)
        println("Array: ${missingNums.contentToString()}")
        println("Missing number: $missingResult")
        println()
        
        // 11. Find Duplicate
        println("11. FIND DUPLICATE")
        val duplicateNums = intArrayOf(1, 3, 4, 2, 2)
        val duplicateResult = findDuplicate(duplicateNums)
        println("Array: ${duplicateNums.contentToString()}")
        println("Duplicate number: $duplicateResult")
        println()
        
        println("=== ARRAY ALGORITHMS DEMONSTRATION COMPLETE ===\n")
    }
} 