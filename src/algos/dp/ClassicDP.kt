package algos.dp

/**
 * CLASSIC DYNAMIC PROGRAMMING PROBLEMS
 * 
 * This file contains classic DP problems that form the foundation
 * of dynamic programming understanding including Fibonacci, climbing stairs,
 * and house robber problems.
 * 
 * COMMON CLASSIC DP PROBLEMS:
 * - Fibonacci Sequence: Basic recurrence relation
 * - Climbing Stairs: Counting ways to reach top
 * - House Robber: Maximum sum with constraints
 * - Min Cost Climbing Stairs: Optimization with costs
 */

object ClassicDP {
    
    /**
     * PROBLEM: Fibonacci Number
     * 
     * Calculate the nth Fibonacci number where F(n) = F(n-1) + F(n-2)
     * with base cases F(0) = 0 and F(1) = 1.
     * 
     * INPUT: Integer n (0 ≤ n ≤ 30)
     * OUTPUT: The nth Fibonacci number
     * 
     * EXAMPLES:
     * Input: n = 2
     * Output: 1 (F(2) = F(1) + F(0) = 1 + 0 = 1)
     * 
     * Input: n = 3
     * Output: 2 (F(3) = F(2) + F(1) = 1 + 1 = 2)
     * 
     * Input: n = 4
     * Output: 3 (F(4) = F(3) + F(2) = 2 + 1 = 3)
     * 
     * Input: n = 10
     * Output: 55
     * 
     * INTUITION:
     * - Use bottom-up DP to avoid recursion overhead
     * - Build solution iteratively using previous two values
     * - Can optimize space to O(1) by only storing last two values
     * - Classic example of overlapping subproblems
     * 
     * TIME COMPLEXITY: O(n) - Single pass through numbers
     * SPACE COMPLEXITY: O(1) - Only need last two values
     * 
     * @param n The position in Fibonacci sequence
     * @return The nth Fibonacci number
     */
    fun fibonacci(n: Int): Int {
        if (n <= 1) return n
        
        var prev = 0
        var curr = 1
        
        for (i in 2..n) {
            val next = prev + curr
            prev = curr
            curr = next
        }
        
        return curr
    }
    
    /**
     * PROBLEM: Climbing Stairs
     * 
     * You are climbing a staircase. It takes n steps to reach the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways
     * can you climb to the top?
     * 
     * INPUT: Integer n (1 ≤ n ≤ 45)
     * OUTPUT: Number of distinct ways to climb n stairs
     * 
     * EXAMPLES:
     * Input: n = 2
     * Output: 2 (ways: [1,1], [2])
     * 
     * Input: n = 3
     * Output: 3 (ways: [1,1,1], [1,2], [2,1])
     * 
     * Input: n = 4
     * Output: 5 (ways: [1,1,1,1], [1,1,2], [1,2,1], [2,1,1], [2,2])
     * 
     * Input: n = 5
     * Output: 8
     * 
     * INTUITION:
     * - Similar to Fibonacci: ways(n) = ways(n-1) + ways(n-2)
     * - To reach step n, you can come from step n-1 (1 step) or n-2 (2 steps)
     * - Base cases: ways(1) = 1, ways(2) = 2
     * - Can use same space optimization as Fibonacci
     * 
     * TIME COMPLEXITY: O(n) - Single pass through stairs
     * SPACE COMPLEXITY: O(1) - Only need last two values
     * 
     * @param n Number of stairs to climb
     * @return Number of distinct ways to climb
     */
    fun climbStairs(n: Int): Int {
        if (n <= 2) return n
        
        var prev = 1
        var curr = 2
        
        for (i in 3..n) {
            val next = prev + curr
            prev = curr
            curr = next
        }
        
        return curr
    }
    
    /**
     * PROBLEM: House Robber
     * 
     * You are a professional robber planning to rob houses along a street.
     * Each house has a certain amount of money stashed, the only constraint
     * stopping you from robbing each of them is that adjacent houses have
     * security systems connected and it will automatically contact the police
     * if two adjacent houses were broken into on the same night.
     * 
     * Given an integer array nums representing the amount of money of each house,
     * return the maximum amount of money you can rob tonight without alerting the police.
     * 
     * INPUT: Array of integers representing money in each house
     * OUTPUT: Maximum amount that can be robbed
     * 
     * EXAMPLES:
     * Input: [1,2,3,1]
     * Output: 4 (rob house 1 and 3: 1 + 3 = 4)
     * 
     * Input: [2,7,9,3,1]
     * Output: 12 (rob house 1, 3, and 5: 2 + 9 + 1 = 12)
     * 
     * Input: [2,1,1,2]
     * Output: 4 (rob house 1 and 4: 2 + 2 = 4)
     * 
     * Input: [1]
     * Output: 1 (rob the only house)
     * 
     * INTUITION:
     * - At each house, choose to rob or skip
     * - If rob current house: can't rob previous house
     * - If skip current house: can rob previous house
     * - DP[i] = max(DP[i-1], DP[i-2] + nums[i])
     * - Similar to climbing stairs but with values
     * 
     * TIME COMPLEXITY: O(n) - Single pass through houses
     * SPACE COMPLEXITY: O(1) - Only need last two values
     * 
     * @param nums Array representing money in each house
     * @return Maximum amount that can be robbed
     */
    fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]
        
        var prev = nums[0]
        var curr = maxOf(nums[0], nums[1])
        
        for (i in 2 until nums.size) {
            val next = maxOf(curr, prev + nums[i])
            prev = curr
            curr = next
        }
        
        return curr
    }
    
    /**
     * PROBLEM: House Robber II
     * 
     * You are a professional robber planning to rob houses along a street.
     * All houses at this place are arranged in a circle. That means the first
     * house is the neighbor of the last one. Meanwhile, adjacent houses have
     * a security system connected, and it will automatically contact the police
     * if two adjacent houses were broken into on the same night.
     * 
     * Given an integer array nums representing the amount of money of each house,
     * return the maximum amount of money you can rob tonight without alerting the police.
     * 
     * INPUT: Array of integers representing money in each house (circular)
     * OUTPUT: Maximum amount that can be robbed
     * 
     * EXAMPLES:
     * Input: [2,3,2]
     * Output: 3 (rob house 2: 3)
     * 
     * Input: [1,2,3,1]
     * Output: 4 (rob house 2 and 4: 2 + 2 = 4)
     * 
     * Input: [1,2,3]
     * Output: 3 (rob house 2: 3)
     * 
     * INTUITION:
     * - Houses are in a circle, so first and last are adjacent
     * - Can't rob both first and last house
     * - Solve two subproblems:
     *   1. Rob houses 0 to n-2 (exclude last)
     *   2. Rob houses 1 to n-1 (exclude first)
     * - Take maximum of the two results
     * 
     * TIME COMPLEXITY: O(n) - Two passes through houses
     * SPACE COMPLEXITY: O(1) - Only need last two values for each subproblem
     * 
     * @param nums Array representing money in each house (circular)
     * @return Maximum amount that can be robbed
     */
    fun robII(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]
        if (nums.size == 2) return maxOf(nums[0], nums[1])
        
        // Rob houses 0 to n-2 (exclude last)
        val robFirst = robRange(nums, 0, nums.size - 2)
        // Rob houses 1 to n-1 (exclude first)
        val robLast = robRange(nums, 1, nums.size - 1)
        
        return maxOf(robFirst, robLast)
    }
    
    private fun robRange(nums: IntArray, start: Int, end: Int): Int {
        var prev = nums[start]
        var curr = maxOf(nums[start], nums[start + 1])
        
        for (i in start + 2..end) {
            val next = maxOf(curr, prev + nums[i])
            prev = curr
            curr = next
        }
        
        return curr
    }
    
    /**
     * PROBLEM: Min Cost Climbing Stairs
     * 
     * You are given an integer array cost where cost[i] is the cost of ith step
     * on a staircase. Once you pay the cost, you can either climb one or two steps.
     * You can either start from the step with index 0, or the step with index 1.
     * 
     * Return the minimum cost to reach the top of the floor.
     * 
     * INPUT: Array of integers representing cost of each step
     * OUTPUT: Minimum cost to reach the top
     * 
     * EXAMPLES:
     * Input: [10,15,20]
     * Output: 15 (start at index 1, pay 15, climb two steps)
     * 
     * Input: [1,100,1,1,1,100,1,1,100,1]
     * Output: 6 (start at index 0, pay 1, climb two steps, pay 1, climb two steps, etc.)
     * 
     * Input: [0,0,0,0]
     * Output: 0 (no cost to reach top)
     * 
     * INTUITION:
     * - Similar to climbing stairs but with costs
     * - At each step, choose minimum cost path
     * - Can start from step 0 or step 1
     * - DP[i] = min(DP[i-1], DP[i-2]) + cost[i]
     * - Final answer is min(DP[n-1], DP[n-2])
     * 
     * TIME COMPLEXITY: O(n) - Single pass through steps
     * SPACE COMPLEXITY: O(1) - Only need last two values
     * 
     * @param cost Array representing cost of each step
     * @return Minimum cost to reach the top
     */
    fun minCostClimbingStairs(cost: IntArray): Int {
        if (cost.size <= 1) return 0
        
        var prev = cost[0]
        var curr = cost[1]
        
        for (i in 2 until cost.size) {
            val next = minOf(prev, curr) + cost[i]
            prev = curr
            curr = next
        }
        
        return minOf(prev, curr)
    }
    
    /**
     * Demonstrates classic DP problems
     */
    fun demonstrateClassicDP() {
        println("=== CLASSIC DYNAMIC PROGRAMMING PROBLEMS ===\n")
        
        // 1. Fibonacci
        println("1. FIBONACCI SEQUENCE")
        val fibTests = listOf(0, 1, 2, 3, 4, 10)
        for (n in fibTests) {
            println("Fibonacci($n) = ${fibonacci(n)}")
        }
        println()
        
        // 2. Climbing Stairs
        println("2. CLIMBING STAIRS")
        val stairTests = listOf(1, 2, 3, 4, 5)
        for (n in stairTests) {
            println("Ways to climb $n stairs = ${climbStairs(n)}")
        }
        println()
        
        // 3. House Robber
        println("3. HOUSE ROBBER")
        val houseTests = listOf(
            intArrayOf(1, 2, 3, 1),
            intArrayOf(2, 7, 9, 3, 1),
            intArrayOf(2, 1, 1, 2),
            intArrayOf(1)
        )
        for (houses in houseTests) {
            println("Houses: ${houses.contentToString()}")
            println("Maximum money: ${rob(houses)}")
        }
        println()
        
        // 4. House Robber II
        println("4. HOUSE ROBBER II (CIRCULAR)")
        val circularHouseTests = listOf(
            intArrayOf(2, 3, 2),
            intArrayOf(1, 2, 3, 1),
            intArrayOf(1, 2, 3)
        )
        for (houses in circularHouseTests) {
            println("Circular houses: ${houses.contentToString()}")
            println("Maximum money: ${robII(houses)}")
        }
        println()
        
        // 5. Min Cost Climbing Stairs
        println("5. MIN COST CLIMBING STAIRS")
        val costTests = listOf(
            intArrayOf(10, 15, 20),
            intArrayOf(1, 100, 1, 1, 1, 100, 1, 1, 100, 1),
            intArrayOf(0, 0, 0, 0)
        )
        for (costs in costTests) {
            println("Costs: ${costs.contentToString()}")
            println("Minimum cost: ${minCostClimbingStairs(costs)}")
        }
        println()
        
        println("=== CLASSIC DP PROBLEMS DEMONSTRATION COMPLETE ===\n")
    }
} 