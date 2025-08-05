package algos.dp

/**
 * ONE-DIMENSIONAL DYNAMIC PROGRAMMING PROBLEMS
 * 
 * This file contains 1D DP problems that use a single dimension
 * for state representation including coin change, longest increasing
 * subsequence, and other 1D optimization problems.
 * 
 * COMMON 1D DP PROBLEMS:
 * - Coin Change: Minimum coins needed for amount
 * - Longest Increasing Subsequence: Find longest increasing subsequence
 * - Maximum Subarray Sum: Kadane's algorithm
 * - Decode Ways: Count ways to decode string
 * - Word Break: Check if string can be segmented
 */

object OneDimensionalDP {
    
    /**
     * PROBLEM: Coin Change (Minimum Coins)
     * 
     * You are given an integer array coins representing coins of different
     * denominations and an integer amount representing a total amount of money.
     * Return the fewest number of coins that you need to make up that amount.
     * If that amount of money cannot be made up by any combination of the coins,
     * return -1.
     * 
     * INPUT: Array of coin denominations and target amount
     * OUTPUT: Minimum number of coins needed, or -1 if impossible
     * 
     * EXAMPLES:
     * Input: coins = [1,2,5], amount = 11
     * Output: 3 (11 = 5 + 5 + 1)
     * 
     * Input: coins = [2], amount = 3
     * Output: -1 (impossible to make 3 with only coin of value 2)
     * 
     * Input: coins = [1], amount = 0
     * Output: 0 (no coins needed)
     * 
     * Input: coins = [1,3,4], amount = 6
     * Output: 2 (6 = 3 + 3)
     * 
     * INTUITION:
     * - Use bottom-up DP with array dp[amount + 1]
     * - For each amount, try all coin denominations
     * - dp[i] = min(dp[i], dp[i - coin] + 1) for all coins
     * - Initialize dp[0] = 0, others to amount + 1
     * - Return dp[amount] if it's not amount + 1, else -1
     * 
     * TIME COMPLEXITY: O(amount * number of coins)
     * SPACE COMPLEXITY: O(amount) - DP array
     * 
     * @param coins Array of coin denominations
     * @param amount Target amount to make
     * @return Minimum number of coins needed
     */
    fun coinChange(coins: IntArray, amount: Int): Int {
        val dp = IntArray(amount + 1) { amount + 1 }
        dp[0] = 0
        
        for (i in 1..amount) {
            for (coin in coins) {
                if (coin <= i) {
                    dp[i] = minOf(dp[i], dp[i - coin] + 1)
                }
            }
        }
        
        return if (dp[amount] > amount) -1 else dp[amount]
    }
    
    /**
     * PROBLEM: Coin Change II (Number of Ways)
     * 
     * You are given an integer array coins representing coins of different
     * denominations and an integer amount representing a total amount of money.
     * Return the number of combinations that make up that amount. If that amount
     * of money cannot be made up by any combination of the coins, return 0.
     * 
     * INPUT: Array of coin denominations and target amount
     * OUTPUT: Number of different combinations to make the amount
     * 
     * EXAMPLES:
     * Input: amount = 5, coins = [1,2,5]
     * Output: 4 (combinations: [5], [2,2,1], [2,1,1,1], [1,1,1,1,1])
     * 
     * Input: amount = 3, coins = [2]
     * Output: 0 (impossible to make 3 with only coin of value 2)
     * 
     * Input: amount = 10, coins = [10]
     * Output: 1 (only one way: use coin of value 10)
     * 
     * INTUITION:
     * - Use unbounded knapsack approach
     * - For each coin, iterate through all amounts
     * - dp[i] += dp[i - coin] for each coin
     * - Initialize dp[0] = 1 (one way to make amount 0)
     * - Order matters: iterate coins first, then amounts
     * 
     * TIME COMPLEXITY: O(amount * number of coins)
     * SPACE COMPLEXITY: O(amount) - DP array
     * 
     * @param amount Target amount to make
     * @param coins Array of coin denominations
     * @return Number of combinations
     */
    fun change(amount: Int, coins: IntArray): Int {
        val dp = IntArray(amount + 1)
        dp[0] = 1
        
        for (coin in coins) {
            for (i in coin..amount) {
                dp[i] += dp[i - coin]
            }
        }
        
        return dp[amount]
    }
    
    /**
     * PROBLEM: Longest Increasing Subsequence
     * 
     * Given an integer array nums, return the length of the longest strictly
     * increasing subsequence.
     * 
     * INPUT: Array of integers
     * OUTPUT: Length of longest increasing subsequence
     * 
     * EXAMPLES:
     * Input: [10,9,2,5,3,7,101,18]
     * Output: 4 (LIS: [2,5,7,101] or [2,3,7,101])
     * 
     * Input: [0,1,0,3,2,3]
     * Output: 4 (LIS: [0,1,2,3])
     * 
     * Input: [7,7,7,7,7,7,7]
     * Output: 1 (LIS: [7])
     * 
     * Input: [1,2,3,4,5]
     * Output: 5 (LIS: [1,2,3,4,5])
     * 
     * INTUITION:
     * - Use DP array where dp[i] = length of LIS ending at index i
     * - For each element, check all previous elements
     * - If current > previous, extend the subsequence
     * - dp[i] = max(dp[j] + 1) for all j < i where nums[j] < nums[i]
     * - Return maximum value in dp array
     * 
     * TIME COMPLEXITY: O(n²) - For each element, check all previous
     * SPACE COMPLEXITY: O(n) - DP array
     * 
     * @param nums Array of integers
     * @return Length of longest increasing subsequence
     */
    fun lengthOfLIS(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        
        val dp = IntArray(nums.size) { 1 }
        var maxLength = 1
        
        for (i in 1 until nums.size) {
            for (j in 0 until i) {
                if (nums[i] > nums[j]) {
                    dp[i] = maxOf(dp[i], dp[j] + 1)
                }
            }
            maxLength = maxOf(maxLength, dp[i])
        }
        
        return maxLength
    }
    
    /**
     * PROBLEM: Maximum Subarray Sum (Kadane's Algorithm)
     * 
     * Given an integer array nums, find the contiguous subarray (containing
     * at least one number) which has the largest sum and return its sum.
     * 
     * INPUT: Array of integers (can contain negative numbers)
     * OUTPUT: Maximum sum of any contiguous subarray
     * 
     * EXAMPLES:
     * Input: [-2,1,-3,4,-1,2,1,-5,4]
     * Output: 6 (subarray [4,-1,2,1] has sum 6)
     * 
     * Input: [1]
     * Output: 1 (single element)
     * 
     * Input: [5,4,-1,7,8]
     * Output: 23 (entire array)
     * 
     * Input: [-1,-2,-3,-4]
     * Output: -1 (single element -1)
     * 
     * INTUITION:
     * - Use Kadane's algorithm with two variables
     * - maxEndingHere: maximum sum ending at current position
     * - maxSoFar: maximum sum seen so far
     * - At each step: maxEndingHere = max(nums[i], maxEndingHere + nums[i])
     * - Update maxSoFar if maxEndingHere is larger
     * 
     * TIME COMPLEXITY: O(n) - Single pass through array
     * SPACE COMPLEXITY: O(1) - Only two variables
     * 
     * @param nums Array of integers
     * @return Maximum subarray sum
     */
    fun maxSubArray(nums: IntArray): Int {
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
     * PROBLEM: Decode Ways
     * 
     * A message containing letters from A-Z can be encoded into numbers using
     * the following mapping: 'A' -> "1", 'B' -> "2", ..., 'Z' -> "26"
     * 
     * Given a string s containing only digits, return the number of ways to decode it.
     * 
     * INPUT: String containing only digits
     * OUTPUT: Number of ways to decode the string
     * 
     * EXAMPLES:
     * Input: "12"
     * Output: 2 ("AB" (1 2) or "L" (12))
     * 
     * Input: "226"
     * Output: 3 ("BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6))
     * 
     * Input: "06"
     * Output: 0 (no valid decoding)
     * 
     * Input: "1"
     * Output: 1 ("A")
     * 
     * INTUITION:
     * - Use DP where dp[i] = ways to decode substring s[0...i]
     * - For each position, check if current digit is valid (1-9)
     * - Also check if current and previous digit form valid number (10-26)
     * - dp[i] = dp[i-1] (if current digit valid) + dp[i-2] (if two digits valid)
     * - Handle edge cases: leading zeros, single digits
     * 
     * TIME COMPLEXITY: O(n) - Single pass through string
     * SPACE COMPLEXITY: O(n) - DP array
     * 
     * @param s String containing only digits
     * @return Number of ways to decode
     */
    fun numDecodings(s: String): Int {
        if (s.isEmpty() || s[0] == '0') return 0
        
        val n = s.length
        val dp = IntArray(n + 1)
        dp[0] = 1
        dp[1] = 1
        
        for (i in 2..n) {
            // Check single digit
            if (s[i - 1] != '0') {
                dp[i] += dp[i - 1]
            }
            
            // Check two digits
            val twoDigits = s.substring(i - 2, i).toInt()
            if (twoDigits in 10..26) {
                dp[i] += dp[i - 2]
            }
        }
        
        return dp[n]
    }
    
    /**
     * PROBLEM: Word Break
     * 
     * Given a string s and a dictionary of strings wordDict, return true if s
     * can be segmented into a space-separated sequence of one or more dictionary words.
     * 
     * INPUT: String s and list of dictionary words
     * OUTPUT: true if string can be segmented, false otherwise
     * 
     * EXAMPLES:
     * Input: s = "leetcode", wordDict = ["leet","code"]
     * Output: true ("leetcode" can be segmented as "leet code")
     * 
     * Input: s = "applepenapple", wordDict = ["apple","pen"]
     * Output: true ("applepenapple" can be segmented as "apple pen apple")
     * 
     * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
     * Output: false (no valid segmentation)
     * 
     * Input: s = "a", wordDict = ["a"]
     * Output: true
     * 
     * INTUITION:
     * - Use DP where dp[i] = can segment substring s[0...i-1]
     * - For each position, check all possible word endings
     * - If substring ending at current position is in dictionary and
     *   previous position is true, then current position is true
     * - dp[i] = true if s[j...i-1] in wordDict and dp[j] = true for some j < i
     * 
     * TIME COMPLEXITY: O(n³) - n positions, n word lengths, n string comparison
     * SPACE COMPLEXITY: O(n) - DP array
     * 
     * @param s String to check
     * @param wordDict List of dictionary words
     * @return true if string can be segmented
     */
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val wordSet = wordDict.toSet()
        val n = s.length
        val dp = BooleanArray(n + 1)
        dp[0] = true
        
        for (i in 1..n) {
            for (j in 0 until i) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true
                    break
                }
            }
        }
        
        return dp[n]
    }
    
    /**
     * Demonstrates 1D DP problems
     */
    fun demonstrateOneDimensionalDP() {
        println("=== ONE-DIMENSIONAL DYNAMIC PROGRAMMING PROBLEMS ===\n")
        
        // 1. Coin Change
        println("1. COIN CHANGE (MINIMUM COINS)")
        val coinTests = listOf(
            Pair(intArrayOf(1, 2, 5), 11),
            Pair(intArrayOf(2), 3),
            Pair(intArrayOf(1), 0),
            Pair(intArrayOf(1, 3, 4), 6)
        )
        for ((coins, amount) in coinTests) {
            println("Coins: ${coins.contentToString()}, Amount: $amount")
            println("Minimum coins needed: ${coinChange(coins, amount)}")
        }
        println()
        
        // 2. Coin Change II
        println("2. COIN CHANGE II (NUMBER OF WAYS)")
        val changeTests = listOf(
            Pair(5, intArrayOf(1, 2, 5)),
            Pair(3, intArrayOf(2)),
            Pair(10, intArrayOf(10)),
            Pair(4, intArrayOf(1, 2, 3))
        )
        for ((amount, coins) in changeTests) {
            println("Amount: $amount, Coins: ${coins.contentToString()}")
            println("Number of ways: ${change(amount, coins)}")
        }
        println()
        
        // 3. Longest Increasing Subsequence
        println("3. LONGEST INCREASING SUBSEQUENCE")
        val lisTests = listOf(
            intArrayOf(10, 9, 2, 5, 3, 7, 101, 18),
            intArrayOf(0, 1, 0, 3, 2, 3),
            intArrayOf(7, 7, 7, 7, 7, 7, 7),
            intArrayOf(1, 2, 3, 4, 5)
        )
        for (nums in lisTests) {
            println("Array: ${nums.contentToString()}")
            println("LIS length: ${lengthOfLIS(nums)}")
        }
        println()
        
        // 4. Maximum Subarray Sum
        println("4. MAXIMUM SUBARRAY SUM (KADANE'S)")
        val kadaneTests = listOf(
            intArrayOf(-2, 1, -3, 4, -1, 2, 1, -5, 4),
            intArrayOf(1),
            intArrayOf(5, 4, -1, 7, 8),
            intArrayOf(-1, -2, -3, -4)
        )
        for (nums in kadaneTests) {
            println("Array: ${nums.contentToString()}")
            println("Maximum subarray sum: ${maxSubArray(nums)}")
        }
        println()
        
        // 5. Decode Ways
        println("5. DECODE WAYS")
        val decodeTests = listOf("12", "226", "06", "1", "27")
        for (s in decodeTests) {
            println("String: '$s'")
            println("Number of ways to decode: ${numDecodings(s)}")
        }
        println()
        
        // 6. Word Break
        println("6. WORD BREAK")
        val wordBreakTests = listOf(
            Triple("leetcode", listOf("leet", "code"), "leetcode"),
            Triple("applepenapple", listOf("apple", "pen"), "applepenapple"),
            Triple("catsandog", listOf("cats", "dog", "sand", "and", "cat"), "catsandog"),
            Triple("a", listOf("a"), "a")
        )
        for ((s, wordDict, description) in wordBreakTests) {
            println("String: '$s', Dictionary: $wordDict")
            println("Can be segmented: ${wordBreak(s, wordDict)}")
        }
        println()
        
        println("=== ONE-DIMENSIONAL DP PROBLEMS DEMONSTRATION COMPLETE ===\n")
    }
} 