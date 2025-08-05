package algos.dp

/**
 * TWO-DIMENSIONAL DYNAMIC PROGRAMMING PROBLEMS
 * 
 * This file contains 2D DP problems that use two dimensions
 * for state representation including unique paths, longest common
 * subsequence, edit distance, and other 2D optimization problems.
 * 
 * COMMON 2D DP PROBLEMS:
 * - Unique Paths: Count paths in grid
 * - Longest Common Subsequence: Find LCS between strings
 * - Edit Distance: Minimum operations to transform string
 * - Minimum Path Sum: Find minimum cost path in grid
 * - Regular Expression Matching: Pattern matching with DP
 */

object TwoDimensionalDP {
    
    /**
     * PROBLEM: Unique Paths
     * 
     * There is a robot on an m x n grid. The robot is initially located at the
     * top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right
     * corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right
     * at any point in time.
     * 
     * Given the two integers m and n, return the number of possible unique paths
     * that the robot can take to reach the bottom-right corner.
     * 
     * INPUT: Two integers m and n representing grid dimensions
     * OUTPUT: Number of unique paths from top-left to bottom-right
     * 
     * EXAMPLES:
     * Input: m = 3, n = 7
     * Output: 28
     * 
     * Input: m = 3, n = 2
     * Output: 3 (paths: [Right,Down,Down], [Down,Right,Down], [Down,Down,Right])
     * 
     * Input: m = 7, n = 3
     * Output: 28
     * 
     * Input: m = 3, n = 3
     * Output: 6
     * 
     * INTUITION:
     * - Use 2D DP where dp[i][j] = paths to reach cell (i,j)
     * - Can reach (i,j) from (i-1,j) or (i,j-1)
     * - dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * - Base case: dp[0][j] = 1, dp[i][0] = 1 (only one path along edges)
     * - Can optimize space to O(n) using rolling array
     * 
     * TIME COMPLEXITY: O(m * n) - Fill entire DP table
     * SPACE COMPLEXITY: O(m * n) - 2D DP table
     * 
     * @param m Number of rows
     * @param n Number of columns
     * @return Number of unique paths
     */
    fun uniquePaths(m: Int, n: Int): Int {
        val dp = Array(m) { IntArray(n) { 1 } }
        
        for (i in 1 until m) {
            for (j in 1 until n) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
            }
        }
        
        return dp[m - 1][n - 1]
    }
    
    /**
     * PROBLEM: Unique Paths II (With Obstacles)
     * 
     * You are given an m x n integer array obstacleGrid. There is a robot initially
     * located at the top-left corner (i.e., obstacleGrid[0][0]). The robot tries to
     * move to the bottom-right corner (i.e., obstacleGrid[m - 1][n - 1]). The robot
     * can only move either down or right at any point in time.
     * 
     * An obstacle and space are marked as 1 or 0 respectively in obstacleGrid. A path
     * that the robot takes cannot include any square that is an obstacle.
     * 
     * INPUT: 2D array representing grid with obstacles (1 = obstacle, 0 = space)
     * OUTPUT: Number of unique paths avoiding obstacles
     * 
     * EXAMPLES:
     * Input: [[0,0,0],[0,1,0],[0,0,0]]
     * Output: 2 (two paths avoiding the obstacle at center)
     * 
     * Input: [[0,1],[0,0]]
     * Output: 1 (one path avoiding the obstacle)
     * 
     * Input: [[1,0]]
     * Output: 0 (start position is blocked)
     * 
     * INTUITION:
     * - Similar to unique paths but handle obstacles
     * - If cell is obstacle, dp[i][j] = 0
     * - Otherwise, dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * - Handle edge cases: first row/column with obstacles
     * - If start or end is obstacle, return 0
     * 
     * TIME COMPLEXITY: O(m * n) - Fill entire DP table
     * SPACE COMPLEXITY: O(m * n) - 2D DP table
     * 
     * @param obstacleGrid 2D array with obstacles
     * @return Number of unique paths avoiding obstacles
     */
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        val m = obstacleGrid.size
        val n = obstacleGrid[0].size
        
        // If start or end is obstacle, no path possible
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0
        }
        
        val dp = Array(m) { IntArray(n) }
        dp[0][0] = 1
        
        // Fill first row
        for (j in 1 until n) {
            dp[0][j] = if (obstacleGrid[0][j] == 1) 0 else dp[0][j - 1]
        }
        
        // Fill first column
        for (i in 1 until m) {
            dp[i][0] = if (obstacleGrid[i][0] == 1) 0 else dp[i - 1][0]
        }
        
        // Fill rest of the table
        for (i in 1 until m) {
            for (j in 1 until n) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
                }
            }
        }
        
        return dp[m - 1][n - 1]
    }
    
    /**
     * PROBLEM: Longest Common Subsequence
     * 
     * Given two strings text1 and text2, return the length of their longest common
     * subsequence. If there is no common subsequence, return 0.
     * 
     * A subsequence of a string is a new string generated from the original string
     * with some characters (can be none) deleted without changing the relative order
     * of the remaining characters.
     * 
     * INPUT: Two strings text1 and text2
     * OUTPUT: Length of longest common subsequence
     * 
     * EXAMPLES:
     * Input: text1 = "abcde", text2 = "ace"
     * Output: 3 (LCS is "ace")
     * 
     * Input: text1 = "abc", text2 = "abc"
     * Output: 3 (LCS is "abc")
     * 
     * Input: text1 = "abc", text2 = "def"
     * Output: 0 (no common subsequence)
     * 
     * Input: text1 = "bsbininm", text2 = "jmjkbkjkv"
     * Output: 1 (LCS is "b")
     * 
     * INTUITION:
     * - Use 2D DP where dp[i][j] = LCS length for text1[0...i-1] and text2[0...j-1]
     * - If characters match: dp[i][j] = dp[i-1][j-1] + 1
     * - If characters don't match: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     * - Base case: dp[0][j] = 0, dp[i][0] = 0
     * - Return dp[m][n] where m, n are string lengths
     * 
     * TIME COMPLEXITY: O(m * n) - Fill entire DP table
     * SPACE COMPLEXITY: O(m * n) - 2D DP table
     * 
     * @param text1 First string
     * @param text2 Second string
     * @return Length of longest common subsequence
     */
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val m = text1.length
        val n = text2.length
        val dp = Array(m + 1) { IntArray(n + 1) }
        
        for (i in 1..m) {
            for (j in 1..n) {
                if (text1[i - 1] == text2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }
        
        return dp[m][n]
    }
    
    /**
     * PROBLEM: Edit Distance
     * 
     * Given two strings word1 and word2, return the minimum number of operations
     * required to convert word1 to word2. You have the following three operations
     * permitted on a word: Insert a character, Delete a character, Replace a character.
     * 
     * INPUT: Two strings word1 and word2
     * OUTPUT: Minimum number of operations to convert word1 to word2
     * 
     * EXAMPLES:
     * Input: word1 = "horse", word2 = "ros"
     * Output: 3 (horse -> rorse -> rose -> ros)
     * 
     * Input: word1 = "intention", word2 = "execution"
     * Output: 5 (intention -> inention -> exention -> exection -> execution)
     * 
     * Input: word1 = "abc", word2 = "abc"
     * Output: 0 (no operations needed)
     * 
     * Input: word1 = "", word2 = "abc"
     * Output: 3 (insert 3 characters)
     * 
     * INTUITION:
     * - Use 2D DP where dp[i][j] = min operations to convert word1[0...i-1] to word2[0...j-1]
     * - If characters match: dp[i][j] = dp[i-1][j-1]
     * - If characters don't match: dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])
     * - Base case: dp[0][j] = j (insert j characters), dp[i][0] = i (delete i characters)
     * - Three operations: insert, delete, replace
     * 
     * TIME COMPLEXITY: O(m * n) - Fill entire DP table
     * SPACE COMPLEXITY: O(m * n) - 2D DP table
     * 
     * @param word1 First string
     * @param word2 Second string
     * @return Minimum number of operations
     */
    fun minDistance(word1: String, word2: String): Int {
        val m = word1.length
        val n = word2.length
        val dp = Array(m + 1) { IntArray(n + 1) }
        
        // Base cases
        for (i in 0..m) dp[i][0] = i
        for (j in 0..n) dp[0][j] = j
        
        for (i in 1..m) {
            for (j in 1..n) {
                if (word1[i - 1] == word2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1]
                } else {
                    dp[i][j] = 1 + minOf(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
                }
            }
        }
        
        return dp[m][n]
    }
    
    /**
     * PROBLEM: Minimum Path Sum
     * 
     * Given a m x n grid filled with non-negative numbers, find a path from top left
     * to bottom right, which minimizes the sum of all numbers along its path.
     * 
     * Note: You can only move either down or right at any point in time.
     * 
     * INPUT: 2D array representing grid with costs
     * OUTPUT: Minimum sum of path from top-left to bottom-right
     * 
     * EXAMPLES:
     * Input: [[1,3,1],[1,5,1],[4,2,1]]
     * Output: 7 (path: 1 -> 3 -> 1 -> 1 -> 1 = 7)
     * 
     * Input: [[1,2,3],[4,5,6]]
     * Output: 12 (path: 1 -> 2 -> 3 -> 6 = 12)
     * 
     * Input: [[1]]
     * Output: 1 (single cell)
     * 
     * INTUITION:
     * - Use 2D DP where dp[i][j] = min sum to reach cell (i,j)
     * - Can reach (i,j) from (i-1,j) or (i,j-1)
     * - dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1])
     * - Base case: dp[0][0] = grid[0][0]
     * - Fill first row and column separately
     * 
     * TIME COMPLEXITY: O(m * n) - Fill entire DP table
     * SPACE COMPLEXITY: O(m * n) - 2D DP table
     * 
     * @param grid 2D array with costs
     * @return Minimum path sum
     */
    fun minPathSum(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val dp = Array(m) { IntArray(n) }
        
        dp[0][0] = grid[0][0]
        
        // Fill first row
        for (j in 1 until n) {
            dp[0][j] = dp[0][j - 1] + grid[0][j]
        }
        
        // Fill first column
        for (i in 1 until m) {
            dp[i][0] = dp[i - 1][0] + grid[i][0]
        }
        
        // Fill rest of the table
        for (i in 1 until m) {
            for (j in 1 until n) {
                dp[i][j] = grid[i][j] + minOf(dp[i - 1][j], dp[i][j - 1])
            }
        }
        
        return dp[m - 1][n - 1]
    }
    
    /**
     * PROBLEM: Regular Expression Matching
     * 
     * Given an input string s and a pattern p, implement regular expression matching
     * with support for '.' and '*' where:
     * - '.' Matches any single character
     * - '*' Matches zero or more of the preceding element
     * 
     * INPUT: String s and pattern p
     * OUTPUT: true if s matches pattern p, false otherwise
     * 
     * EXAMPLES:
     * Input: s = "aa", p = "a"
     * Output: false (no '*' to match multiple 'a's)
     * 
     * Input: s = "aa", p = "a*"
     * Output: true ('a*' can match two 'a's)
     * 
     * Input: s = "ab", p = ".*"
     * Output: true (".*" can match any string)
     * 
     * Input: s = "aab", p = "c*a*b"
     * Output: true ('c*' matches 0 'c's, 'a*' matches 2 'a's, 'b' matches 'b')
     * 
     * INTUITION:
     * - Use 2D DP where dp[i][j] = does s[0...i-1] match p[0...j-1]
     * - Handle '*' by considering 0 or more repetitions of preceding character
     * - Handle '.' by matching any single character
     * - Base case: dp[0][0] = true (empty string matches empty pattern)
     * - Handle patterns like a*, a*b*, etc. at the beginning
     * 
     * TIME COMPLEXITY: O(m * n) - Fill entire DP table
     * SPACE COMPLEXITY: O(m * n) - 2D DP table
     * 
     * @param s Input string
     * @param p Pattern string
     * @return true if s matches p
     */
    fun isMatch(s: String, p: String): Boolean {
        val m = s.length
        val n = p.length
        val dp = Array(m + 1) { BooleanArray(n + 1) }
        dp[0][0] = true
        
        // Handle patterns like a*, a*b*, etc.
        for (j in 1..n) {
            if (p[j - 1] == '*') {
                dp[0][j] = dp[0][j - 2]
            }
        }
        
        for (i in 1..m) {
            for (j in 1..n) {
                if (p[j - 1] == '.' || p[j - 1] == s[i - 1]) {
                    dp[i][j] = dp[i - 1][j - 1]
                } else if (p[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 2] // 0 occurrence
                    if (p[j - 2] == '.' || p[j - 2] == s[i - 1]) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j]
                    }
                }
            }
        }
        
        return dp[m][n]
    }
    
    /**
     * Demonstrates 2D DP problems
     */
    fun demonstrateTwoDimensionalDP() {
        println("=== TWO-DIMENSIONAL DYNAMIC PROGRAMMING PROBLEMS ===\n")
        
        // 1. Unique Paths
        println("1. UNIQUE PATHS")
        val pathTests = listOf(
            Pair(3, 7),
            Pair(3, 2),
            Pair(7, 3),
            Pair(3, 3)
        )
        for ((m, n) in pathTests) {
            println("Grid: ${m}x${n}")
            println("Unique paths: ${uniquePaths(m, n)}")
        }
        println()
        
        // 2. Unique Paths with Obstacles
        println("2. UNIQUE PATHS WITH OBSTACLES")
        val obstacleTests = listOf(
            arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 1, 0), intArrayOf(0, 0, 0)),
            arrayOf(intArrayOf(0, 1), intArrayOf(0, 0)),
            arrayOf(intArrayOf(1, 0))
        )
        for (grid in obstacleTests) {
            println("Grid:")
            for (row in grid) {
                println("  ${row.contentToString()}")
            }
            println("Unique paths: ${uniquePathsWithObstacles(grid)}")
        }
        println()
        
        // 3. Longest Common Subsequence
        println("3. LONGEST COMMON SUBSEQUENCE")
        val lcsTests = listOf(
            Pair("abcde", "ace"),
            Pair("abc", "abc"),
            Pair("abc", "def"),
            Pair("bsbininm", "jmjkbkjkv")
        )
        for ((text1, text2) in lcsTests) {
            println("Text1: '$text1', Text2: '$text2'")
            println("LCS length: ${longestCommonSubsequence(text1, text2)}")
        }
        println()
        
        // 4. Edit Distance
        println("4. EDIT DISTANCE")
        val editTests = listOf(
            Pair("horse", "ros"),
            Pair("intention", "execution"),
            Pair("abc", "abc"),
            Pair("", "abc")
        )
        for ((word1, word2) in editTests) {
            println("Word1: '$word1', Word2: '$word2'")
            println("Edit distance: ${minDistance(word1, word2)}")
        }
        println()
        
        // 5. Minimum Path Sum
        println("5. MINIMUM PATH SUM")
        val pathSumTests = listOf(
            arrayOf(intArrayOf(1, 3, 1), intArrayOf(1, 5, 1), intArrayOf(4, 2, 1)),
            arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6)),
            arrayOf(intArrayOf(1))
        )
        for (grid in pathSumTests) {
            println("Grid:")
            for (row in grid) {
                println("  ${row.contentToString()}")
            }
            println("Minimum path sum: ${minPathSum(grid)}")
        }
        println()
        
        // 6. Regular Expression Matching
        println("6. REGULAR EXPRESSION MATCHING")
        val regexTests = listOf(
            Pair("aa", "a"),
            Pair("aa", "a*"),
            Pair("ab", ".*"),
            Pair("aab", "c*a*b"),
            Pair("mississippi", "mis*is*p*.")
        )
        for ((s, p) in regexTests) {
            println("String: '$s', Pattern: '$p'")
            println("Matches: ${isMatch(s, p)}")
        }
        println()
        
        println("=== TWO-DIMENSIONAL DP PROBLEMS DEMONSTRATION COMPLETE ===\n")
    }
} 