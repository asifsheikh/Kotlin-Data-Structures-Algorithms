package number_theory.algorithms

import number_theory.NumberTheoryBasics

/**
 * PRIME NUMBER ALGORITHMS
 * 
 * Problem: Various algorithms and problems related to prime numbers.
 * 
 * Examples:
 * - Check if number is prime
 * - Generate prime numbers up to n
 * - Find prime factors
 * - Count prime numbers in range
 * - Find next/previous prime
 * 
 * Intuition: Use efficient algorithms like Sieve of Eratosthenes, Miller-Rabin test
 * 
 * Time Complexity: Varies from O(√n) to O(n log log n)
 * Space Complexity: Varies from O(1) to O(n)
 */

object PrimeNumbers {
    
    /**
     * Count Primes - Count number of prime numbers less than n
     * 
     * Problem: Given an integer n, return the number of prime numbers that are strictly less than n.
     * 
     * Examples:
     * Input: 10 → Output: 4 (primes: 2, 3, 5, 7)
     * Input: 0 → Output: 0
     * Input: 1 → Output: 0
     * Input: 2 → Output: 0
     * 
     * Intuition: Use Sieve of Eratosthenes to mark non-primes
     * 
     * Time Complexity: O(n log log n) - Sieve of Eratosthenes
     * Space Complexity: O(n) - Boolean array
     */
    fun countPrimes(n: Int): Int {
        if (n <= 2) return 0
        
        val isPrime = BooleanArray(n) { true }
        isPrime[0] = false
        isPrime[1] = false
        
        for (i in 2..kotlin.math.sqrt(n.toDouble()).toInt()) {
            if (isPrime[i]) {
                for (j in i * i until n step i) {
                    isPrime[j] = false
                }
            }
        }
        
        return isPrime.count { it }
    }
    
    /**
     * Generate all prime numbers up to n
     */
    fun generatePrimesUpTo(n: Int): List<Int> {
        if (n < 2) return emptyList()
        
        val isPrime = BooleanArray(n + 1) { true }
        isPrime[0] = false
        isPrime[1] = false
        
        for (i in 2..kotlin.math.sqrt(n.toDouble()).toInt()) {
            if (isPrime[i]) {
                for (j in i * i..n step i) {
                    isPrime[j] = false
                }
            }
        }
        
        return (2..n).filter { isPrime[it] }
    }
    
    /**
     * Segmented Sieve - Generate primes in range [L, R]
     * 
     * Problem: Generate all prime numbers in a given range [L, R] where R can be very large.
     * 
     * Examples:
     * Input: L = 10, R = 20 → Output: [11, 13, 17, 19]
     * Input: L = 1, R = 10 → Output: [2, 3, 5, 7]
     * 
     * Intuition: Use segmented sieve to handle large ranges efficiently
     * 
     * Time Complexity: O((R-L+1) log log R + √R log log √R)
     * Space Complexity: O(√R + R-L+1)
     */
    fun segmentedSieve(L: Long, R: Long): List<Long> {
        val primes = mutableListOf<Long>()
        
        // Generate primes up to √R
        val limit = kotlin.math.sqrt(R.toDouble()).toLong()
        val isPrime = BooleanArray(limit.toInt() + 1) { true }
        isPrime[0] = false
        isPrime[1] = false
        
        for (i in 2..limit) {
            if (isPrime[i.toInt()]) {
                for (j in i * i..limit step i) {
                    isPrime[j.toInt()] = false
                }
            }
        }
        
        // Segmented sieve
        val segmentSize = R - L + 1
        val segment = BooleanArray(segmentSize.toInt()) { true }
        
        for (i in 2..limit) {
            if (isPrime[i.toInt()]) {
                val start = kotlin.math.max(i * i, (L + i - 1) / i * i)
                for (j in start..R step i) {
                    segment[(j - L).toInt()] = false
                }
            }
        }
        
        for (i in 0 until segmentSize.toInt()) {
            if (segment[i] && L + i >= 2) {
                primes.add(L + i)
            }
        }
        
        return primes
    }
    
    /**
     * Find next prime number greater than n
     */
    fun nextPrime(n: Long): Long {
        var num = n + 1
        while (!NumberTheoryBasics.isPrimeMillerRabin(num)) {
            num++
        }
        return num
    }
    
    /**
     * Find previous prime number less than n
     */
    fun previousPrime(n: Long): Long {
        if (n <= 2) return -1
        var num = n - 1
        while (num >= 2 && !NumberTheoryBasics.isPrimeMillerRabin(num)) {
            num--
        }
        return if (num >= 2) num else -1
    }
    
    /**
     * Check if number is prime using trial division
     */
    fun isPrimeTrial(n: Long): Boolean {
        if (n < 2) return false
        if (n == 2L) return true
        if (n % 2 == 0L) return false
        
        for (i in 3..kotlin.math.sqrt(n.toDouble()).toLong() step 2) {
            if (n % i == 0L) return false
        }
        return true
    }
    
    /**
     * Optimized trial division for prime checking
     */
    fun isPrimeOptimized(n: Long): Boolean {
        if (n < 2) return false
        if (n == 2L || n == 3L) return true
        if (n % 2 == 0L || n % 3 == 0L) return false
        
        for (i in 5..kotlin.math.sqrt(n.toDouble()).toLong() step 6) {
            if (n % i == 0L || n % (i + 2) == 0L) return false
        }
        return true
    }
    
    /**
     * Prime factorization with exponents
     */
    fun primeFactorization(n: Long): Map<Long, Int> {
        val factors = mutableMapOf<Long, Int>()
        var num = n
        
        // Check for 2
        var count = 0
        while (num % 2 == 0L) {
            count++
            num /= 2
        }
        if (count > 0) factors[2] = count
        
        // Check odd numbers
        for (i in 3..kotlin.math.sqrt(num.toDouble()).toLong() step 2) {
            count = 0
            while (num % i == 0L) {
                count++
                num /= i
            }
            if (count > 0) factors[i] = count
        }
        
        if (num > 2) factors[num] = 1
        return factors
    }
    
    /**
     * Count distinct prime factors
     */
    fun countDistinctPrimeFactors(n: Long): Int {
        return primeFactorization(n).size
    }
    
    /**
     * Sum of prime factors
     */
    fun sumOfPrimeFactors(n: Long): Long {
        return primeFactorization(n).entries.sumOf { (prime, exp) -> prime * exp }
    }
    
    /**
     * Largest prime factor
     */
    fun largestPrimeFactor(n: Long): Long {
        val factors = primeFactorization(n)
        return factors.keys.maxOrNull() ?: 1
    }
    
    /**
     * Smallest prime factor
     */
    fun smallestPrimeFactor(n: Long): Long {
        val factors = primeFactorization(n)
        return factors.keys.minOrNull() ?: 1
    }
    
    /**
     * Twin primes - Find pairs of primes with difference 2
     */
    fun findTwinPrimes(limit: Int): List<Pair<Int, Int>> {
        val primes = generatePrimesUpTo(limit)
        val twinPrimes = mutableListOf<Pair<Int, Int>>()
        
        for (i in 0 until primes.size - 1) {
            if (primes[i + 1] - primes[i] == 2) {
                twinPrimes.add(primes[i] to primes[i + 1])
            }
        }
        
        return twinPrimes
    }
    
    /**
     * Cousin primes - Find pairs of primes with difference 4
     */
    fun findCousinPrimes(limit: Int): List<Pair<Int, Int>> {
        val primes = generatePrimesUpTo(limit)
        val cousinPrimes = mutableListOf<Pair<Int, Int>>()
        
        for (i in 0 until primes.size - 1) {
            if (primes[i + 1] - primes[i] == 4) {
                cousinPrimes.add(primes[i] to primes[i + 1])
            }
        }
        
        return cousinPrimes
    }
    
    /**
     * Sexy primes - Find pairs of primes with difference 6
     */
    fun findSexyPrimes(limit: Int): List<Pair<Int, Int>> {
        val primes = generatePrimesUpTo(limit)
        val sexyPrimes = mutableListOf<Pair<Int, Int>>()
        
        for (i in 0 until primes.size - 1) {
            if (primes[i + 1] - primes[i] == 6) {
                sexyPrimes.add(primes[i] to primes[i + 1])
            }
        }
        
        return sexyPrimes
    }
    
    /**
     * Prime gap - Difference between consecutive primes
     */
    fun findPrimeGaps(limit: Int): List<Int> {
        val primes = generatePrimesUpTo(limit)
        val gaps = mutableListOf<Int>()
        
        for (i in 0 until primes.size - 1) {
            gaps.add(primes[i + 1] - primes[i])
        }
        
        return gaps
    }
    
    /**
     * Largest prime gap up to n
     */
    fun largestPrimeGap(n: Int): Int {
        val gaps = findPrimeGaps(n)
        return gaps.maxOrNull() ?: 0
    }
    
    /**
     * Prime counting function π(n) - number of primes ≤ n
     */
    fun primeCountingFunction(n: Int): Int {
        return countPrimes(n + 1)
    }
    
    /**
     * Nth prime number
     */
    fun nthPrime(n: Int): Int {
        if (n <= 0) return -1
        if (n == 1) return 2
        
        var count = 1
        var num = 3
        
        while (count < n) {
            if (NumberTheoryBasics.isPrime(num)) {
                count++
            }
            num += 2
        }
        
        return num - 2
    }
    
    /**
     * Prime number theorem approximation
     * π(n) ≈ n / ln(n)
     */
    fun primeCountingApproximation(n: Int): Int {
        if (n < 2) return 0
        return (n / kotlin.math.ln(n.toDouble())).toInt()
    }
    
    /**
     * Demonstrate all prime number algorithms
     */
    fun demonstratePrimeNumberAlgorithms() {
        println("=== PRIME NUMBER ALGORITHMS DEMONSTRATION ===")
        
        // Basic prime operations
        println("1. BASIC PRIME OPERATIONS")
        val testNumbers = longArrayOf(2, 3, 4, 17, 25, 29, 100)
        for (num in testNumbers) {
            println("$num is prime (trial): ${isPrimeTrial(num)}")
            println("$num is prime (optimized): ${isPrimeOptimized(num)}")
            println("$num is prime (Miller-Rabin): ${NumberTheoryBasics.isPrimeMillerRabin(num)}")
        }
        println()
        
        // Prime counting
        println("2. PRIME COUNTING")
        val limits = intArrayOf(10, 20, 50, 100)
        for (limit in limits) {
            println("Primes less than $limit: ${countPrimes(limit)}")
            println("Prime counting function π($limit): ${primeCountingFunction(limit)}")
            println("Approximation: ${primeCountingApproximation(limit)}")
        }
        println()
        
        // Prime generation
        println("3. PRIME GENERATION")
        println("Primes up to 20: ${generatePrimesUpTo(20)}")
        println("Primes up to 50: ${generatePrimesUpTo(50)}")
        println()
        
        // Segmented sieve
        println("4. SEGMENTED SIEVE")
        println("Primes in range [10, 20]: ${segmentedSieve(10, 20)}")
        println("Primes in range [100, 120]: ${segmentedSieve(100, 120)}")
        println()
        
        // Next and previous primes
        println("5. NEXT AND PREVIOUS PRIMES")
        val testNums = longArrayOf(10, 17, 25, 30)
        for (num in testNums) {
            println("Next prime after $num: ${nextPrime(num)}")
            println("Previous prime before $num: ${previousPrime(num)}")
        }
        println()
        
        // Prime factorization
        println("6. PRIME FACTORIZATION")
        val factorizeNums = longArrayOf(12, 84, 100, 1000)
        for (num in factorizeNums) {
            println("Prime factorization of $num: ${primeFactorization(num)}")
            println("Distinct prime factors: ${countDistinctPrimeFactors(num)}")
            println("Sum of prime factors: ${sumOfPrimeFactors(num)}")
            println("Largest prime factor: ${largestPrimeFactor(num)}")
            println("Smallest prime factor: ${smallestPrimeFactor(num)}")
        }
        println()
        
        // Special prime pairs
        println("7. SPECIAL PRIME PAIRS")
        println("Twin primes up to 50: ${findTwinPrimes(50)}")
        println("Cousin primes up to 50: ${findCousinPrimes(50)}")
        println("Sexy primes up to 50: ${findSexyPrimes(50)}")
        println()
        
        // Prime gaps
        println("8. PRIME GAPS")
        println("Prime gaps up to 50: ${findPrimeGaps(50)}")
        println("Largest prime gap up to 100: ${largestPrimeGap(100)}")
        println()
        
        // Nth prime
        println("9. NTH PRIME")
        for (i in 1..10) {
            println("${i}th prime: ${nthPrime(i)}")
        }
        println()
        
        println("=== PRIME NUMBER ALGORITHMS DEMONSTRATION COMPLETE ===")
    }
}
