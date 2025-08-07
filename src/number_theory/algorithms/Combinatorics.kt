package number_theory.algorithms

import number_theory.NumberTheoryBasics

/**
 * COMBINATORICS ALGORITHMS
 * 
 * Problem: Various algorithms and problems related to combinatorics.
 * 
 * Examples:
 * - Permutations and combinations
 * - Catalan numbers
 * - Stirling numbers
 * - Bell numbers
 * - Partition numbers
 * - Subset generation
 * 
 * Intuition: Use mathematical formulas and dynamic programming for efficient calculation
 * 
 * Time Complexity: Varies from O(n) to O(2^n)
 * Space Complexity: Varies from O(1) to O(n)
 */

object Combinatorics {
    
    /**
     * Permutations P(n,r) = n! / (n-r)!
     * 
     * Problem: Calculate the number of ways to arrange r objects from n distinct objects.
     * 
     * Examples:
     * Input: n = 5, r = 3 → Output: 60
     * Input: n = 4, r = 2 → Output: 12
     * Input: n = 3, r = 3 → Output: 6
     * 
     * Intuition: Use the formula P(n,r) = n! / (n-r)!
     * 
     * Time Complexity: O(r)
     * Space Complexity: O(1)
     */
    fun permutations(n: Int, r: Int): Long {
        if (r > n) return 0L
        if (r == 0) return 1L
        
        var result = 1L
        for (i in 0 until r) {
            result *= (n - i)
        }
        return result
    }
    
    /**
     * Combinations C(n,r) = n! / (r! * (n-r)!)
     * 
     * Problem: Calculate the number of ways to choose r objects from n distinct objects.
     * 
     * Examples:
     * Input: n = 5, r = 3 → Output: 10
     * Input: n = 4, r = 2 → Output: 6
     * Input: n = 3, r = 3 → Output: 1
     * 
     * Intuition: Use the formula C(n,r) = n! / (r! * (n-r)!)
     * 
     * Time Complexity: O(min(r, n-r))
     * Space Complexity: O(1)
     */
    fun combinations(n: Int, r: Int): Long {
        if (r > n) return 0L
        if (r == 0 || r == n) return 1L
        
        // Use symmetry: C(n,r) = C(n,n-r)
        val k = if (r > n - r) n - r else r
        
        var result = 1L
        for (i in 0 until k) {
            result = result * (n - i) / (i + 1)
        }
        return result
    }
    
    /**
     * Combinations with modulo
     */
    fun combinationsMod(n: Int, r: Int, mod: Int): Int {
        if (r > n) return 0
        if (r == 0 || r == n) return 1
        
        val k = if (r > n - r) n - r else r
        
        var result = 1
        for (i in 0 until k) {
            result = (result * (n - i)) % mod
            result = (result * NumberTheoryBasics.modInverseFermat((i + 1).toLong(), mod.toLong()).toInt()) % mod
        }
        return result
    }
    
    /**
     * Catalan Numbers C(n) = (1/(n+1)) * C(2n,n)
     * 
     * Problem: Calculate the nth Catalan number.
     * 
     * Examples:
     * Input: n = 0 → Output: 1
     * Input: n = 1 → Output: 1
     * Input: n = 2 → Output: 2
     * Input: n = 3 → Output: 5
     * Input: n = 4 → Output: 14
     * 
     * Intuition: Use the formula C(n) = (1/(n+1)) * C(2n,n)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun catalanNumber(n: Int): Long {
        if (n <= 1) return 1L
        return combinations(2 * n, n) / (n + 1)
    }
    
    /**
     * Generate first n Catalan numbers using dynamic programming
     */
    fun catalanNumbers(n: Int): List<Long> {
        val catalan = LongArray(n + 1)
        catalan[0] = 1L
        catalan[1] = 1L
        
        for (i in 2..n) {
            for (j in 0 until i) {
                catalan[i] += catalan[j] * catalan[i - 1 - j]
            }
        }
        
        return catalan.toList()
    }
    
    /**
     * Stirling Numbers of the Second Kind S(n,k)
     * 
     * Problem: Calculate the number of ways to partition n objects into k non-empty subsets.
     * 
     * Examples:
     * Input: n = 3, k = 2 → Output: 3
     * Input: n = 4, k = 2 → Output: 7
     * Input: n = 3, k = 3 → Output: 1
     * 
     * Intuition: Use the recurrence relation S(n,k) = k*S(n-1,k) + S(n-1,k-1)
     * 
     * Time Complexity: O(n*k)
     * Space Complexity: O(n*k)
     */
    fun stirlingSecondKind(n: Int, k: Int): Long {
        if (k > n) return 0L
        if (k == 0) return if (n == 0) 1L else 0L
        if (k == 1 || k == n) return 1L
        
        val dp = Array(n + 1) { LongArray(k + 1) { 0L } }
        
        // Base cases
        dp[0][0] = 1L
        for (i in 1..n) {
            dp[i][1] = 1L
            if (i <= k) dp[i][i] = 1L
        }
        
        // Fill dp table
        for (i in 2..n) {
            for (j in 2..minOf(i, k)) {
                dp[i][j] = j * dp[i - 1][j] + dp[i - 1][j - 1]
            }
        }
        
        return dp[n][k]
    }
    
    /**
     * Bell Numbers B(n)
     * 
     * Problem: Calculate the number of ways to partition n objects into any number of subsets.
     * 
     * Examples:
     * Input: n = 0 → Output: 1
     * Input: n = 1 → Output: 1
     * Input: n = 2 → Output: 2
     * Input: n = 3 → Output: 5
     * Input: n = 4 → Output: 15
     * 
     * Intuition: B(n) = sum of S(n,k) for k from 0 to n
     * 
     * Time Complexity: O(n²)
     * Space Complexity: O(n²)
     */
    fun bellNumber(n: Int): Long {
        if (n <= 1) return 1L
        
        val bell = LongArray(n + 1)
        bell[0] = 1L
        bell[1] = 1L
        
        for (i in 2..n) {
            bell[i] = 0L
            for (j in 0 until i) {
                bell[i] += combinations(i - 1, j) * bell[j]
            }
        }
        
        return bell[n]
    }
    
    /**
     * Generate first n Bell numbers
     */
    fun bellNumbers(n: Int): List<Long> {
        val bell = LongArray(n + 1)
        bell[0] = 1L
        
        for (i in 1..n) {
            bell[i] = 0L
            for (j in 0 until i) {
                bell[i] += combinations(i - 1, j) * bell[j]
            }
        }
        
        return bell.toList()
    }
    
    /**
     * Integer Partition P(n)
     * 
     * Problem: Calculate the number of ways to write n as a sum of positive integers.
     * 
     * Examples:
     * Input: n = 1 → Output: 1
     * Input: n = 2 → Output: 2 (1+1, 2)
     * Input: n = 3 → Output: 3 (1+1+1, 1+2, 3)
     * Input: n = 4 → Output: 5 (1+1+1+1, 1+1+2, 1+3, 2+2, 4)
     * 
     * Intuition: Use dynamic programming with pentagonal number theorem
     * 
     * Time Complexity: O(n^(3/2))
     * Space Complexity: O(n)
     */
    fun integerPartition(n: Int): Long {
        if (n <= 1) return 1L
        
        val p = LongArray(n + 1)
        p[0] = 1L
        
        for (i in 1..n) {
            var j = 1
            var k = 1
            var sum = 0L
            
            while (j <= i) {
                val sign = if (k % 2 == 1) 1L else -1L
                sum += sign * p[i - j]
                j += k
                k++
            }
            
            p[i] = sum
        }
        
        return p[n]
    }
    
    /**
     * Generate all subsets of size k from n elements
     */
    fun generateSubsets(n: Int, k: Int): List<List<Int>> {
        if (k > n) return emptyList()
        if (k == 0) return listOf(emptyList())
        if (k == n) return listOf((0 until n).toList())
        
        val subsets = mutableListOf<List<Int>>()
        val current = mutableListOf<Int>()
        
        fun backtrack(start: Int, remaining: Int) {
            if (remaining == 0) {
                subsets.add(current.toList())
                return
            }
            
            for (i in start..n - remaining) {
                current.add(i)
                backtrack(i + 1, remaining - 1)
                current.removeAt(current.size - 1)
            }
        }
        
        backtrack(0, k)
        return subsets
    }
    
    /**
     * Generate all permutations of n elements
     */
    fun generatePermutations(n: Int): List<List<Int>> {
        val permutations = mutableListOf<List<Int>>()
        val current = (0 until n).toMutableList()
        
        fun generate(k: Int) {
            if (k == 1) {
                permutations.add(current.toList())
                return
            }
            
            generate(k - 1)
            
            for (i in 0 until k - 1) {
                if (k % 2 == 0) {
                    current[i] = current[k - 1].also { current[k - 1] = current[i] }
                } else {
                    current[0] = current[k - 1].also { current[k - 1] = current[0] }
                }
                generate(k - 1)
            }
        }
        
        generate(n)
        return permutations
    }
    
    /**
     * Generate all combinations of n elements
     */
    fun generateCombinations(n: Int): List<List<Int>> {
        val combinations = mutableListOf<List<Int>>()
        
        for (k in 0..n) {
            combinations.addAll(generateSubsets(n, k))
        }
        
        return combinations
    }
    
    /**
     * Multinomial coefficient
     * 
     * Problem: Calculate the number of ways to arrange n objects where there are k1, k2, ..., km objects of each type.
     * 
     * Examples:
     * Input: n = 5, k = [2, 2, 1] → Output: 30
     * Input: n = 4, k = [1, 1, 1, 1] → Output: 24
     * 
     * Intuition: Use the formula n! / (k1! * k2! * ... * km!)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun multinomialCoefficient(n: Int, k: List<Int>): Long {
        if (k.sum() != n) return 0L
        
        var result = NumberTheoryBasics.factorial(n)
        for (ki in k) {
            result /= NumberTheoryBasics.factorial(ki)
        }
        return result
    }
    
    /**
     * Derangements D(n)
     * 
     * Problem: Calculate the number of permutations where no element appears in its original position.
     * 
     * Examples:
     * Input: n = 1 → Output: 0
     * Input: n = 2 → Output: 1
     * Input: n = 3 → Output: 2
     * Input: n = 4 → Output: 9
     * 
     * Intuition: Use the recurrence relation D(n) = (n-1) * (D(n-1) + D(n-2))
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun derangements(n: Int): Long {
        if (n <= 1) return 0L
        if (n == 2) return 1L
        
        val d = LongArray(n + 1)
        d[1] = 0L
        d[2] = 1L
        
        for (i in 3..n) {
            d[i] = (i - 1) * (d[i - 1] + d[i - 2])
        }
        
        return d[n]
    }
    
    /**
     * Demonstrate all combinatorics algorithms
     */
    fun demonstrateCombinatoricsAlgorithms() {
        println("=== COMBINATORICS ALGORITHMS DEMONSTRATION ===")
        
        // Permutations and combinations
        println("1. PERMUTATIONS AND COMBINATIONS")
        println("P(5,3) = ${permutations(5, 3)}")
        println("C(5,3) = ${combinations(5, 3)}")
        println("C(10,5) = ${combinations(10, 5)}")
        println("C(5,3) mod 1000 = ${combinationsMod(5, 3, 1000)}")
        println()
        
        // Catalan numbers
        println("2. CATALAN NUMBERS")
        for (i in 0..10) {
            println("C($i) = ${catalanNumber(i)}")
        }
        println("First 10 Catalan numbers: ${catalanNumbers(10)}")
        println()
        
        // Stirling numbers
        println("3. STIRLING NUMBERS OF THE SECOND KIND")
        println("S(3,2) = ${stirlingSecondKind(3, 2)}")
        println("S(4,2) = ${stirlingSecondKind(4, 2)}")
        println("S(5,3) = ${stirlingSecondKind(5, 3)}")
        println()
        
        // Bell numbers
        println("4. BELL NUMBERS")
        for (i in 0..8) {
            println("B($i) = ${bellNumber(i)}")
        }
        println("First 8 Bell numbers: ${bellNumbers(8)}")
        println()
        
        // Integer partitions
        println("5. INTEGER PARTITIONS")
        for (i in 1..10) {
            println("P($i) = ${integerPartition(i)}")
        }
        println()
        
        // Subset generation
        println("6. SUBSET GENERATION")
        println("Subsets of size 2 from 4 elements:")
        val subsets = generateSubsets(4, 2)
        for (subset in subsets) {
            println("  $subset")
        }
        println()
        
        // Permutation generation
        println("7. PERMUTATION GENERATION")
        println("Permutations of 3 elements:")
        val permutations = generatePermutations(3)
        for (perm in permutations) {
            println("  $perm")
        }
        println()
        
        // Combination generation
        println("8. COMBINATION GENERATION")
        println("All combinations of 3 elements:")
        val combinations = generateCombinations(3)
        for (comb in combinations) {
            println("  $comb")
        }
        println()
        
        // Multinomial coefficient
        println("9. MULTINOMIAL COEFFICIENT")
        println("Multinomial(5, [2,2,1]) = ${multinomialCoefficient(5, listOf(2, 2, 1))}")
        println("Multinomial(4, [1,1,1,1]) = ${multinomialCoefficient(4, listOf(1, 1, 1, 1))}")
        println()
        
        // Derangements
        println("10. DERANGEMENTS")
        for (i in 1..8) {
            println("D($i) = ${derangements(i)}")
        }
        println()
        
        println("=== COMBINATORICS ALGORITHMS DEMONSTRATION COMPLETE ===")
    }
}
