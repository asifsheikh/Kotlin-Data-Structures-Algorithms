package algos.dp

/**
 * KNAPSACK DYNAMIC PROGRAMMING PROBLEMS
 * 
 * This file contains knapsack DP problems including 0/1 knapsack,
 * unbounded knapsack, and their variations. These are fundamental
 * optimization problems with wide applications.
 * 
 * COMMON KNAPSACK PROBLEMS:
 * - 0/1 Knapsack: Each item can be used at most once
 * - Unbounded Knapsack: Each item can be used multiple times
 * - Subset Sum: Find subset with given sum
 * - Partition Equal Subset Sum: Divide array into two equal parts
 * - Target Sum: Assign +/- to reach target
 */

object KnapsackDP {
    
    /**
     * PROBLEM: 0/1 Knapsack
     * 
     * Given weights and values of n items, put these items in a knapsack
     * of capacity W to get the maximum total value in the knapsack.
     * Each item can be used at most once (0/1 property).
     * 
     * INPUT: Arrays of weights, values, and knapsack capacity
     * OUTPUT: Maximum value that can be achieved
     * 
     * EXAMPLES:
     * Input: weights = [1,3,4,5], values = [1,4,5,7], capacity = 7
     * Output: 9 (items with weights 3 and 4: 4 + 5 = 9)
     * 
     * Input: weights = [2,3,4,5], values = [3,4,5,6], capacity = 5
     * Output: 7 (items with weights 2 and 3: 3 + 4 = 7)
     * 
     * Input: weights = [1,2,3], values = [10,15,40], capacity = 6
     * Output: 65 (all items: 10 + 15 + 40 = 65)
     * 
     * INTUITION:
     * - Use 2D DP where dp[i][w] = max value using first i items with capacity w
     * - For each item, choose to include or exclude it
     * - If include: dp[i][w] = dp[i-1][w-weight[i-1]] + value[i-1]
     * - If exclude: dp[i][w] = dp[i-1][w]
     * - Take maximum of include and exclude
     * - Can optimize space to O(capacity) using rolling array
     * 
     * TIME COMPLEXITY: O(n * capacity) - Fill entire DP table
     * SPACE COMPLEXITY: O(n * capacity) - 2D DP table
     * 
     * @param weights Array of item weights
     * @param values Array of item values
     * @param capacity Knapsack capacity
     * @return Maximum value achievable
     */
    fun knapsack01(weights: IntArray, values: IntArray, capacity: Int): Int {
        val n = weights.size
        val dp = Array(n + 1) { IntArray(capacity + 1) }
        
        for (i in 1..n) {
            for (w in 0..capacity) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = maxOf(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1])
                } else {
                    dp[i][w] = dp[i - 1][w]
                }
            }
        }
        
        return dp[n][capacity]
    }
    
    /**
     * PROBLEM: Unbounded Knapsack (Coin Change II)
     * 
     * Given an array of coins and a target amount, return the number of
     * combinations that make up that amount. Each coin can be used unlimited times.
     * 
     * INPUT: Target amount and array of coin denominations
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
     * Input: amount = 4, coins = [1,2,3]
     * Output: 4 (combinations: [1,1,1,1], [1,1,2], [2,2], [1,3])
     * 
     * INTUITION:
     * - Use 1D DP where dp[i] = ways to make amount i
     * - For each coin, iterate through all amounts
     * - dp[i] += dp[i - coin] for each coin
     * - Initialize dp[0] = 1 (one way to make amount 0)
     * - Order matters: iterate coins first, then amounts
     * 
     * TIME COMPLEXITY: O(amount * number of coins)
     * SPACE COMPLEXITY: O(amount) - 1D DP array
     * 
     * @param amount Target amount to make
     * @param coins Array of coin denominations
     * @return Number of combinations
     */
    fun unboundedKnapsack(amount: Int, coins: IntArray): Int {
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
     * PROBLEM: Subset Sum
     * 
     * Given a set of non-negative integers and a value sum, determine if there
     * is a subset of the given set with sum equal to given sum.
     * 
     * INPUT: Array of integers and target sum
     * OUTPUT: true if subset with given sum exists, false otherwise
     * 
     * EXAMPLES:
     * Input: set = [3, 34, 4, 12, 5, 2], sum = 9
     * Output: true (subset [4, 5] has sum 9)
     * 
     * Input: set = [3, 34, 4, 12, 5, 2], sum = 30
     * Output: false (no subset has sum 30)
     * 
     * Input: set = [1, 2, 3], sum = 0
     * Output: true (empty subset has sum 0)
     * 
     * Input: set = [1, 2, 3], sum = 6
     * Output: true (subset [1, 2, 3] has sum 6)
     * 
     * INTUITION:
     * - Use 2D DP where dp[i][j] = can make sum j using first i elements
     * - For each element, choose to include or exclude it
     * - If include: dp[i][j] = dp[i-1][j-arr[i-1]]
     * - If exclude: dp[i][j] = dp[i-1][j]
     * - Base case: dp[0][0] = true, dp[0][j] = false for j > 0
     * 
     * TIME COMPLEXITY: O(n * sum) - Fill entire DP table
     * SPACE COMPLEXITY: O(n * sum) - 2D DP table
     * 
     * @param nums Array of integers
     * @param sum Target sum
     * @return true if subset with given sum exists
     */
    fun subsetSum(nums: IntArray, sum: Int): Boolean {
        val n = nums.size
        val dp = Array(n + 1) { BooleanArray(sum + 1) }
        
        // Base case: empty subset can make sum 0
        dp[0][0] = true
        
        for (i in 1..n) {
            for (j in 0..sum) {
                if (j == 0) {
                    dp[i][j] = true // empty subset
                } else if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]]
                } else {
                    dp[i][j] = dp[i - 1][j]
                }
            }
        }
        
        return dp[n][sum]
    }
    
    /**
     * PROBLEM: Partition Equal Subset Sum
     * 
     * Given a non-empty array nums containing only positive integers, find if
     * the array can be partitioned into two subsets such that the sum of elements
     * in both subsets is equal.
     * 
     * INPUT: Array of positive integers
     * OUTPUT: true if array can be partitioned into two equal subsets
     * 
     * EXAMPLES:
     * Input: [1,5,11,5]
     * Output: true (subsets [1,5,5] and [11] both sum to 11)
     * 
     * Input: [1,2,3,5]
     * Output: false (no valid partition)
     * 
     * Input: [1,2,3,4,5,6,7]
     * Output: true (subsets [1,2,4,7] and [3,5,6] both sum to 14)
     * 
     * Input: [1,1]
     * Output: true (subsets [1] and [1] both sum to 1)
     * 
     * INTUITION:
     * - If total sum is odd, impossible to partition equally
     * - If total sum is even, find subset with sum = total/2
     * - Use subset sum algorithm with target = total/2
     * - This reduces to finding subset with half the total sum
     * 
     * TIME COMPLEXITY: O(n * sum) - Subset sum complexity
     * SPACE COMPLEXITY: O(n * sum) - DP table
     * 
     * @param nums Array of positive integers
     * @return true if can be partitioned equally
     */
    fun canPartition(nums: IntArray): Boolean {
        val totalSum = nums.sum()
        
        // If total sum is odd, impossible to partition equally
        if (totalSum % 2 != 0) return false
        
        val target = totalSum / 2
        return subsetSum(nums, target)
    }
    
    /**
     * PROBLEM: Target Sum
     * 
     * You are given an integer array nums and an integer target. You want to build
     * an expression out of nums by adding one of the symbols '+' and '-' before each
     * integer in nums and then concatenate all the integers.
     * 
     * Return the number of different expressions that you can build, which evaluate to target.
     * 
     * INPUT: Array of integers and target sum
     * OUTPUT: Number of ways to assign +/- to reach target
     * 
     * EXAMPLES:
     * Input: nums = [1,1,1,1,1], target = 3
     * Output: 5 (ways: -1+1+1+1+1, +1-1+1+1+1, +1+1-1+1+1, +1+1+1-1+1, +1+1+1+1-1)
     * 
     * Input: nums = [1], target = 1
     * Output: 1 (only way: +1)
     * 
     * Input: nums = [1,2,3], target = 0
     * Output: 2 (ways: +1+2-3, -1-2+3)
     * 
     * INTUITION:
     * - Let S be sum of all numbers, P be sum of positive numbers, N be sum of negative
     * - P - N = target, P + N = S
     * - Solving: P = (S + target) / 2, N = (S - target) / 2
     * - Problem reduces to finding subset with sum = (S + target) / 2
     * - Use subset sum with target = (S + target) / 2
     * 
     * TIME COMPLEXITY: O(n * sum) - Subset sum complexity
     * SPACE COMPLEXITY: O(n * sum) - DP table
     * 
     * @param nums Array of integers
     * @param target Target sum
     * @return Number of ways to reach target
     */
    fun findTargetSumWays(nums: IntArray, target: Int): Int {
        val totalSum = nums.sum()
        
        // If target is greater than total sum or less than -total sum, impossible
        if (target > totalSum || target < -totalSum) return 0
        
        // If (totalSum + target) is odd, impossible
        if ((totalSum + target) % 2 != 0) return 0
        
        val subsetTarget = (totalSum + target) / 2
        
        // Use subset sum to count ways
        return countSubsetSum(nums, subsetTarget)
    }
    
    private fun countSubsetSum(nums: IntArray, target: Int): Int {
        val n = nums.size
        val dp = Array(n + 1) { IntArray(target + 1) }
        
        // Base case: empty subset can make sum 0 in 1 way
        dp[0][0] = 1
        
        for (i in 1..n) {
            for (j in 0..target) {
                if (j == 0) {
                    dp[i][j] = 1 // empty subset
                } else if (nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]]
                } else {
                    dp[i][j] = dp[i - 1][j]
                }
            }
        }
        
        return dp[n][target]
    }
    
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
     * INTUITION:
     * - Use unbounded knapsack approach (coins can be used multiple times)
     * - Use 1D DP where dp[i] = min coins needed for amount i
     * - For each amount, try all coin denominations
     * - dp[i] = min(dp[i], dp[i - coin] + 1) for all coins
     * - Initialize dp[0] = 0, others to amount + 1
     * 
     * TIME COMPLEXITY: O(amount * number of coins)
     * SPACE COMPLEXITY: O(amount) - 1D DP array
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
     * Demonstrates knapsack DP problems
     */
    fun demonstrateKnapsackDP() {
        println("=== KNAPSACK DYNAMIC PROGRAMMING PROBLEMS ===\n")
        
        // 1. 0/1 Knapsack
        println("1. 0/1 KNAPSACK")
        val knapsackTests = listOf(
            Triple(intArrayOf(1, 3, 4, 5), intArrayOf(1, 4, 5, 7), 7),
            Triple(intArrayOf(2, 3, 4, 5), intArrayOf(3, 4, 5, 6), 5),
            Triple(intArrayOf(1, 2, 3), intArrayOf(10, 15, 40), 6)
        )
        for ((weights, values, capacity) in knapsackTests) {
            println("Weights: ${weights.contentToString()}")
            println("Values: ${values.contentToString()}")
            println("Capacity: $capacity")
            println("Maximum value: ${knapsack01(weights, values, capacity)}")
        }
        println()
        
        // 2. Unbounded Knapsack
        println("2. UNBOUNDED KNAPSACK (COIN CHANGE II)")
        val unboundedTests = listOf(
            Pair(5, intArrayOf(1, 2, 5)),
            Pair(3, intArrayOf(2)),
            Pair(10, intArrayOf(10)),
            Pair(4, intArrayOf(1, 2, 3))
        )
        for ((amount, coins) in unboundedTests) {
            println("Amount: $amount, Coins: ${coins.contentToString()}")
            println("Number of ways: ${unboundedKnapsack(amount, coins)}")
        }
        println()
        
        // 3. Subset Sum
        println("3. SUBSET SUM")
        val subsetTests = listOf(
            Pair(intArrayOf(3, 34, 4, 12, 5, 2), 9),
            Pair(intArrayOf(3, 34, 4, 12, 5, 2), 30),
            Pair(intArrayOf(1, 2, 3), 0),
            Pair(intArrayOf(1, 2, 3), 6)
        )
        for ((nums, sum) in subsetTests) {
            println("Array: ${nums.contentToString()}, Target: $sum")
            println("Subset exists: ${subsetSum(nums, sum)}")
        }
        println()
        
        // 4. Partition Equal Subset Sum
        println("4. PARTITION EQUAL SUBSET SUM")
        val partitionTests = listOf(
            intArrayOf(1, 5, 11, 5),
            intArrayOf(1, 2, 3, 5),
            intArrayOf(1, 2, 3, 4, 5, 6, 7),
            intArrayOf(1, 1)
        )
        for (nums in partitionTests) {
            println("Array: ${nums.contentToString()}")
            println("Can partition equally: ${canPartition(nums)}")
        }
        println()
        
        // 5. Target Sum
        println("5. TARGET SUM")
        val targetTests = listOf(
            Pair(intArrayOf(1, 1, 1, 1, 1), 3),
            Pair(intArrayOf(1), 1),
            Pair(intArrayOf(1, 2, 3), 0)
        )
        for ((nums, target) in targetTests) {
            println("Array: ${nums.contentToString()}, Target: $target")
            println("Number of ways: ${findTargetSumWays(nums, target)}")
        }
        println()
        
        // 6. Coin Change (Minimum)
        println("6. COIN CHANGE (MINIMUM COINS)")
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
        
        println("=== KNAPSACK DP PROBLEMS DEMONSTRATION COMPLETE ===\n")
    }
} 