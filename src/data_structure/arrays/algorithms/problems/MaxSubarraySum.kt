package data_structure.arrays.algorithms.problems

/**
 * MAXIMUM SUBARRAY SUM (KADANE'S ALGORITHM)
 * 
 * Problem: Find the maximum sum of a contiguous subarray in an array.
 * 
 * Given an array of integers, find the contiguous subarray with the largest sum.
 * The subarray must contain at least one element.
 * 
 * Example:
 * Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 * Output: 6 (subarray [4, -1, 2, 1] has sum 6)
 * 
 * Input: [1, 2, 3, 4, 5]
 * Output: 15 (entire array has sum 15)
 * 
 * Input: [-1, -2, -3, -4]
 * Output: -1 (single element -1)
 * 
 * Intuition:
 * - Use dynamic programming approach
 * - For each position, decide whether to extend previous subarray or start new one
 * - Keep track of current sum and maximum sum seen so far
 * - If current sum becomes negative, start fresh (no point in carrying negative sum)
 * 
 * Variations:
 * - Find maximum sum subarray
 * - Find maximum sum subarray with indices
 * - Handle all negative numbers
 * - Find maximum sum subarray of given length
 * - Find maximum sum subarray with constraints
 */

object MaxSubarraySum {
    
    /**
     * Kadane's Algorithm - Basic Implementation
     * 
     * Algorithm:
     * 1. Initialize current sum and max sum to first element
     * 2. For each element starting from second:
     *    - Add current element to current sum
     *    - If current sum > max sum, update max sum
     *    - If current sum < 0, reset to 0 (start fresh)
     * 3. Return max sum
     * 
     * Time Complexity: O(n) - Single pass through array
     * Space Complexity: O(1) - Only a few variables
     * 
     * This is the most efficient solution for this problem.
     */
    fun kadaneAlgorithm(arr: IntArray): Int {
        if (arr.isEmpty()) return 0
        
        var currentSum = arr[0]
        var maxSum = arr[0]
        
        for (i in 1 until arr.size) {
            currentSum = maxOf(arr[i], currentSum + arr[i])
            maxSum = maxOf(maxSum, currentSum)
        }
        
        return maxSum
    }
    
    /**
     * Kadane's Algorithm with Indices
     * 
     * Find the maximum sum subarray and return its start and end indices.
     * 
     * Algorithm:
     * 1. Track current sum and max sum
     * 2. Track start and end indices of current and max subarrays
     * 3. Update indices when current sum changes
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun kadaneWithIndices(arr: IntArray): Triple<Int, Int, Int> {
        if (arr.isEmpty()) return Triple(0, 0, 0)
        
        var currentSum = arr[0]
        var maxSum = arr[0]
        var currentStart = 0
        var maxStart = 0
        var maxEnd = 0
        
        for (i in 1 until arr.size) {
            if (arr[i] > currentSum + arr[i]) {
                currentSum = arr[i]
                currentStart = i
            } else {
                currentSum += arr[i]
            }
            
            if (currentSum > maxSum) {
                maxSum = currentSum
                maxStart = currentStart
                maxEnd = i
            }
        }
        
        return Triple(maxSum, maxStart, maxEnd)
    }
    
    /**
     * Kadane's Algorithm - Handle All Negative Numbers
     * 
     * Modified version that handles arrays with all negative numbers.
     * 
     * Algorithm:
     * 1. Find maximum element in array
     * 2. If all elements are negative, return maximum element
     * 3. Otherwise, use standard Kadane's algorithm
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun kadaneAllNegative(arr: IntArray): Int {
        if (arr.isEmpty()) return 0
        
        val maxElement = arr.maxOrNull()!!
        
        // If all elements are negative, return maximum element
        if (maxElement < 0) return maxElement
        
        // Use standard Kadane's algorithm
        var currentSum = 0
        var maxSum = 0
        
        for (num in arr) {
            currentSum = maxOf(0, currentSum + num)
            maxSum = maxOf(maxSum, currentSum)
        }
        
        return maxSum
    }
    
    /**
     * Maximum Subarray Sum - Divide and Conquer
     * 
     * Alternative approach using divide and conquer strategy.
     * 
     * Algorithm:
     * 1. Divide array into two halves
     * 2. Recursively find max sum in left and right halves
     * 3. Find max sum crossing the middle
     * 4. Return maximum of three values
     * 
     * Time Complexity: O(n log n)
     * Space Complexity: O(log n) - Recursion stack
     */
    fun maxSubarraySumDivideAndConquer(arr: IntArray): Int {
        return maxSubarraySumHelper(arr, 0, arr.size - 1)
    }
    
    private fun maxSubarraySumHelper(arr: IntArray, left: Int, right: Int): Int {
        // Base case: single element
        if (left == right) return arr[left]
        
        // Base case: two elements
        if (right == left + 1) {
            return maxOf(arr[left], arr[right], arr[left] + arr[right])
        }
        
        val mid = (left + right) / 2
        
        // Find max sum in left half
        val leftMax = maxSubarraySumHelper(arr, left, mid)
        
        // Find max sum in right half
        val rightMax = maxSubarraySumHelper(arr, mid + 1, right)
        
        // Find max sum crossing the middle
        val crossMax = maxCrossingSum(arr, left, mid, right)
        
        return maxOf(leftMax, rightMax, crossMax)
    }
    
    private fun maxCrossingSum(arr: IntArray, left: Int, mid: Int, right: Int): Int {
        // Find max sum in left half ending at mid
        var leftSum = Int.MIN_VALUE
        var sum = 0
        for (i in mid downTo left) {
            sum += arr[i]
            leftSum = maxOf(leftSum, sum)
        }
        
        // Find max sum in right half starting from mid+1
        var rightSum = Int.MIN_VALUE
        sum = 0
        for (i in mid + 1..right) {
            sum += arr[i]
            rightSum = maxOf(rightSum, sum)
        }
        
        return leftSum + rightSum
    }
    
    /**
     * Maximum Subarray Sum - Dynamic Programming
     * 
     * DP approach that builds solution using previous results.
     * 
     * Algorithm:
     * 1. Create DP array where dp[i] = max sum ending at index i
     * 2. dp[i] = max(arr[i], dp[i-1] + arr[i])
     * 3. Find maximum value in DP array
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun maxSubarraySumDP(arr: IntArray): Int {
        if (arr.isEmpty()) return 0
        
        val dp = IntArray(arr.size)
        dp[0] = arr[0]
        
        for (i in 1 until arr.size) {
            dp[i] = maxOf(arr[i], dp[i - 1] + arr[i])
        }
        
        return dp.maxOrNull()!!
    }
    
    /**
     * Maximum Subarray Sum - Circular Array
     * 
     * Find maximum sum in circular array (can wrap around).
     * 
     * Algorithm:
     * 1. Find max sum using Kadane's algorithm
     * 2. Find min sum using inverted Kadane's algorithm
     * 3. Find total sum of array
     * 4. Return max(maxSum, totalSum - minSum)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun maxSubarraySumCircular(arr: IntArray): Int {
        if (arr.isEmpty()) return 0
        
        // Find max sum using Kadane's algorithm
        val maxSum = kadaneAlgorithm(arr)
        
        // Find min sum using inverted Kadane's algorithm
        val minSum = kadaneAlgorithmInverted(arr)
        
        // Find total sum
        val totalSum = arr.sum()
        
        // If all elements are negative, return max element
        if (totalSum == minSum) return maxSum
        
        return maxOf(maxSum, totalSum - minSum)
    }
    
    private fun kadaneAlgorithmInverted(arr: IntArray): Int {
        if (arr.isEmpty()) return 0
        
        var currentSum = -arr[0]
        var maxSum = -arr[0]
        
        for (i in 1 until arr.size) {
            currentSum = maxOf(-arr[i], currentSum - arr[i])
            maxSum = maxOf(maxSum, currentSum)
        }
        
        return -maxSum
    }
    
    /**
     * Maximum Subarray Sum - Given Length K
     * 
     * Find maximum sum subarray of exactly length k.
     * 
     * Algorithm:
     * 1. Calculate sum of first k elements
     * 2. Use sliding window to find max sum
     * 3. For each window, update max sum
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun maxSubarraySumOfLengthK(arr: IntArray, k: Int): Int {
        if (arr.size < k) return 0
        
        // Calculate sum of first k elements
        var currentSum = 0
        for (i in 0 until k) {
            currentSum += arr[i]
        }
        
        var maxSum = currentSum
        
        // Slide window and update max sum
        for (i in k until arr.size) {
            currentSum = currentSum - arr[i - k] + arr[i]
            maxSum = maxOf(maxSum, currentSum)
        }
        
        return maxSum
    }
    
    /**
     * Maximum Subarray Sum - At Most K Elements
     * 
     * Find maximum sum subarray with at most k elements.
     * 
     * Algorithm:
     * 1. Use sliding window with variable size
     * 2. Keep track of current sum and window size
     * 3. Shrink window when size exceeds k
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun maxSubarraySumAtMostK(arr: IntArray, k: Int): Int {
        if (arr.isEmpty()) return 0
        
        var currentSum = 0
        var maxSum = Int.MIN_VALUE
        var windowStart = 0
        
        for (windowEnd in arr.indices) {
            currentSum += arr[windowEnd]
            
            // Shrink window if size exceeds k
            while (windowEnd - windowStart + 1 > k) {
                currentSum -= arr[windowStart]
                windowStart++
            }
            
            maxSum = maxOf(maxSum, currentSum)
        }
        
        return maxSum
    }
    
    /**
     * Maximum Subarray Sum - Non-Adjacent Elements
     * 
     * Find maximum sum of non-adjacent elements.
     * 
     * Algorithm:
     * 1. Use dynamic programming
     * 2. dp[i] = max(dp[i-1], dp[i-2] + arr[i])
     * 3. Can't pick adjacent elements
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun maxSubarraySumNonAdjacent(arr: IntArray): Int {
        if (arr.isEmpty()) return 0
        if (arr.size == 1) return arr[0]
        
        val dp = IntArray(arr.size)
        dp[0] = arr[0]
        dp[1] = maxOf(arr[0], arr[1])
        
        for (i in 2 until arr.size) {
            dp[i] = maxOf(dp[i - 1], dp[i - 2] + arr[i])
        }
        
        return dp[arr.size - 1]
    }
    
    /**
     * Maximum Subarray Sum - With Constraints
     * 
     * Find maximum sum subarray where no two elements are adjacent
     * and sum should be divisible by k.
     * 
     * Algorithm:
     * 1. Use dynamic programming with modulo
     * 2. Track maximum sum for each remainder
     * 3. Update dp[i][r] = max(dp[i-1][r], dp[i-2][(r-arr[i])%k] + arr[i])
     * 
     * Time Complexity: O(n * k)
     * Space Complexity: O(n * k)
     */
    fun maxSubarraySumWithConstraints(arr: IntArray, k: Int): Int {
        if (arr.isEmpty()) return 0
        
        val dp = Array(arr.size) { IntArray(k) { -1 } }
        
        // Base cases
        dp[0][arr[0] % k] = arr[0]
        if (arr.size > 1) {
            dp[1][arr[1] % k] = arr[1]
            dp[1][arr[0] % k] = maxOf(dp[1][arr[0] % k], arr[0])
        }
        
        for (i in 2 until arr.size) {
            for (r in 0 until k) {
                // Don't include current element
                dp[i][r] = dp[i - 1][r]
                
                // Include current element
                val prevRemainder = (r - arr[i] % k + k) % k
                if (dp[i - 2][prevRemainder] != -1) {
                    dp[i][r] = maxOf(dp[i][r], dp[i - 2][prevRemainder] + arr[i])
                }
            }
        }
        
        return if (dp[arr.size - 1][0] != -1) dp[arr.size - 1][0] else 0
    }
} 