package number_theory

/**
 * NUMBER THEORY BASICS - Quick Reference
 * All Kotlin number theory basic concepts and operations in one place
 */

object NumberTheoryBasics {
    
    /**
     * All Number Theory Basic Methods
     * Complete reference for basic number theory in Kotlin
     */
    fun allNumberTheoryBasics() {
        // === PRIME NUMBER OPERATIONS ===
        val num = 17
        val isPrime = isPrime(num)                    // true
        val isPrimeOptimized = isPrimeOptimized(num)  // true
        val isPrimeMillerRabin = isPrimeMillerRabin(num.toLong()) // true
        
        val factors = getPrimeFactors(84L)            // [2, 2, 3, 7]
        val primeFactors = getPrimeFactorization(84L) // {2=2, 3=1, 7=1}
        val primes = generatePrimes(20)               // [2, 3, 5, 7, 11, 13, 17, 19]
        
        // === GCD AND LCM OPERATIONS ===
        val a = 48L
        val b = 18L
        val gcd = gcd(a, b)                           // 6
        val lcm = lcm(a, b)                           // 144
        val binaryGCD = binaryGCD(a, b)               // 6
        val extendedGCD = extendedGCD(a, b)           // BezoutCoefficients(x=1, y=-2, gcd=6)
        
        // === MODULAR ARITHMETIC ===
        val base = 2L
        val exp = 10L
        val mod = 1000L
        val modPow = modPow(base, exp, mod)           // 24
        val modAdd = modAdd(5L, 3L, 7L)               // 1
        val modSub = modSub(3L, 5L, 7L)               // 5
        val modMul = modMul(4L, 5L, 7L)               // 6
        val modInverse = modInverse(3L, 7L)           // 5
        val modInverseFermat = modInverseFermat(3L, 7L) // 5
        
        // === COMBINATORICS ===
        val n = 5
        val r = 3
        val factorial = factorial(n)                  // 120
        val factorialMod = factorialMod(n, 1000)      // 120
        val permutations = permutations(n, r)         // 60
        val combinations = combinations(n, r)         // 10
        val catalan = catalanNumber(4)                // 14
        val catalanNumbers = catalanNumbers(5)        // [1, 1, 2, 5, 14, 42]
        
        // === DIVISORS AND MULTIPLES ===
        val num2 = 12L
        val divisors = getDivisors(num2)              // [1, 2, 3, 4, 6, 12]
        val countDivisors = countDivisors(num2)       // 6
        val sumDivisors = sumOfDivisors(num2)         // 28
        val isPerfect = isPerfectNumber(num2)         // false
        val isAbundant = isAbundantNumber(num2)       // true
        val isDeficient = isDeficientNumber(num2)     // false
        
        // === MATHEMATICAL SEQUENCES ===
        val fib = fibonacci(10)                       // 55
        val fibMod = fibonacciMod(10, 1000)           // 55
        val sumFirstN = sumFirstN(10)                 // 55
        val sumSquares = sumSquares(5)                // 55
        val sumCubes = sumCubes(4)                    // 100
        
        // === NUMBER PROPERTIES ===
        val num3 = 16L
        val isPowerOf2 = isPowerOf2(num3)             // true
        val isPowerOf3 = isPowerOf3(num3)             // false
        val isPowerOf4 = isPowerOf4(num3)             // true
        val isPowerOf5 = isPowerOf5(num3)             // false
        
        // === ADVANCED OPERATIONS ===
        val phi = eulerTotient(10)                    // 4
        val mu = mobiusFunction(12)                   // 0
        val sigma = sumOfDivisors(12)                 // 28
        val tau = countDivisors(12)                   // 6
    }
    
    // === PRIME NUMBER OPERATIONS ===
    
    /**
     * Check if number is prime using trial division
     */
    fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        if (n == 2) return true
        if (n % 2 == 0) return false
        
        for (i in 3..kotlin.math.sqrt(n.toDouble()).toInt() step 2) {
            if (n % i == 0) return false
        }
        return true
    }
    
    /**
     * Optimized prime check
     */
    fun isPrimeOptimized(n: Int): Boolean {
        if (n < 2) return false
        if (n == 2 || n == 3) return true
        if (n % 2 == 0 || n % 3 == 0) return false
        
        for (i in 5..kotlin.math.sqrt(n.toDouble()).toInt() step 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false
        }
        return true
    }
    
    /**
     * Miller-Rabin probabilistic primality test
     */
    fun isPrimeMillerRabin(n: Long, k: Int = 5): Boolean {
        if (n < 2) return false
        if (n == 2L || n == 3L) return true
        if (n % 2 == 0L) return false
        
        var d = n - 1
        var r = 0
        while (d % 2 == 0L) {
            d /= 2
            r++
        }
        
        repeat(k) {
            val a = (2..n-2).random().toLong()
            var x = modPow(a, d, n)
            if (x == 1L || x == n - 1) return@repeat
            
            repeat(r - 1) {
                x = (x * x) % n
                if (x == n - 1L) return@repeat
            }
            return false
        }
        return true
    }
    
    /**
     * Get prime factors of a number
     */
    fun getPrimeFactors(n: Long): List<Long> {
        val factors = mutableListOf<Long>()
        var num = n
        
        // Check for 2
        while (num % 2 == 0L) {
            factors.add(2)
            num /= 2
        }
        
        // Check odd numbers
        for (i in 3..kotlin.math.sqrt(num.toDouble()).toLong() step 2) {
            while (num % i == 0L) {
                factors.add(i)
                num /= i
            }
        }
        
        if (num > 2) factors.add(num)
        return factors
    }
    
    /**
     * Get prime factorization with exponents
     */
    fun getPrimeFactorization(n: Long): Map<Long, Int> {
        val factors = getPrimeFactors(n)
        return factors.groupingBy { it }.eachCount()
    }
    
    /**
     * Generate primes up to n using Sieve of Eratosthenes
     */
    fun generatePrimes(n: Int): List<Int> {
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
    
    // === GCD AND LCM OPERATIONS ===
    
    /**
     * Greatest Common Divisor using Euclidean algorithm
     */
    fun gcd(a: Long, b: Long): Long {
        return if (b == 0L) a else gcd(b, a % b)
    }
    
    /**
     * Least Common Multiple
     */
    fun lcm(a: Long, b: Long): Long {
        return (a / gcd(a, b)) * b
    }
    
    /**
     * Binary GCD (Stein's algorithm)
     */
    fun binaryGCD(a: Long, b: Long): Long {
        if (a == 0L) return b
        if (b == 0L) return a
        
        var u = a
        var v = b
        var shift = 0
        
        while (((u or v) and 1L) == 0L) {
            u = u shr 1
            v = v shr 1
            shift++
        }
        
        while ((u and 1L) == 0L) u = u shr 1
        while ((v and 1L) == 0L) v = v shr 1
        
        while (u != v) {
            if (u > v) {
                u -= v
                while ((u and 1L) == 0L) u = u shr 1
            } else {
                v -= u
                while ((v and 1L) == 0L) v = v shr 1
            }
        }
        
        return u shl shift
    }
    
    /**
     * Extended Euclidean algorithm
     */
    data class BezoutCoefficients(val x: Long, val y: Long, val gcd: Long)
    
    fun extendedGCD(a: Long, b: Long): BezoutCoefficients {
        if (b == 0L) return BezoutCoefficients(1, 0, a)
        
        val result = extendedGCD(b, a % b)
        return BezoutCoefficients(
            result.y,
            result.x - (a / b) * result.y,
            result.gcd
        )
    }
    
    // === MODULAR ARITHMETIC ===
    
    /**
     * Fast modular exponentiation
     */
    fun modPow(base: Long, exp: Long, mod: Long): Long {
        if (mod == 1L) return 0L
        
        var result = 1L
        var b = base % mod
        var e = exp
        
        while (e > 0) {
            if (e and 1L == 1L) {
                result = (result * b) % mod
            }
            b = (b * b) % mod
            e = e shr 1
        }
        
        return result
    }
    
    /**
     * Modular addition
     */
    fun modAdd(a: Long, b: Long, m: Long): Long {
        return ((a % m) + (b % m)) % m
    }
    
    /**
     * Modular subtraction
     */
    fun modSub(a: Long, b: Long, m: Long): Long {
        return ((a % m) - (b % m) + m) % m
    }
    
    /**
     * Modular multiplication
     */
    fun modMul(a: Long, b: Long, m: Long): Long {
        return ((a % m) * (b % m)) % m
    }
    
    /**
     * Modular inverse using extended Euclidean
     */
    fun modInverse(a: Long, m: Long): Long {
        val bezout = extendedGCD(a, m)
        if (bezout.gcd != 1L) return -1L // No inverse exists
        
        return (bezout.x % m + m) % m
    }
    
    /**
     * Modular inverse using Fermat's Little Theorem
     */
    fun modInverseFermat(a: Long, m: Long): Long {
        return modPow(a, m - 2, m) // Only when m is prime
    }
    
    // === COMBINATORICS ===
    
    /**
     * Factorial
     */
    fun factorial(n: Int): Long {
        if (n < 0) return 0L
        if (n <= 1) return 1L
        
        var result = 1L
        for (i in 2..n) {
            result *= i
        }
        return result
    }
    
    /**
     * Factorial with modulo
     */
    fun factorialMod(n: Int, mod: Int): Int {
        if (n < 0) return 0
        if (n <= 1) return 1
        
        var result = 1
        for (i in 2..n) {
            result = (result * i) % mod
        }
        return result
    }
    
    /**
     * Permutations P(n,r)
     */
    fun permutations(n: Int, r: Int): Long {
        if (r > n) return 0L
        var result = 1L
        for (i in 0 until r) {
            result *= (n - i)
        }
        return result
    }
    
    /**
     * Combinations C(n,r)
     */
    fun combinations(n: Int, r: Int): Long {
        if (r > n) return 0L
        if (r > n - r) return combinations(n, n - r)
        
        var result = 1L
        for (i in 0 until r) {
            result = result * (n - i) / (i + 1)
        }
        return result
    }
    
    /**
     * Catalan number
     */
    fun catalanNumber(n: Int): Long {
        if (n <= 1) return 1L
        return combinations(2 * n, n) / (n + 1)
    }
    
    /**
     * Generate first n Catalan numbers
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
    
    // === DIVISORS AND MULTIPLES ===
    
    /**
     * Get all divisors of a number
     */
    fun getDivisors(n: Long): List<Long> {
        val divisors = mutableListOf<Long>()
        for (i in 1..kotlin.math.sqrt(n.toDouble()).toLong()) {
            if (n % i == 0L) {
                divisors.add(i)
                if (i * i != n) {
                    divisors.add(n / i)
                }
            }
        }
        return divisors.sorted()
    }
    
    /**
     * Count number of divisors
     */
    fun countDivisors(n: Long): Int {
        var count = 0
        for (i in 1..kotlin.math.sqrt(n.toDouble()).toLong()) {
            if (n % i == 0L) {
                count += if (i * i == n) 1 else 2
            }
        }
        return count
    }
    
    /**
     * Sum of all divisors
     */
    fun sumOfDivisors(n: Long): Long {
        var sum = 0L
        for (i in 1..kotlin.math.sqrt(n.toDouble()).toLong()) {
            if (n % i == 0L) {
                sum += i
                if (i * i != n) {
                    sum += n / i
                }
            }
        }
        return sum
    }
    
    /**
     * Check if number is perfect (sum of proper divisors equals number)
     */
    fun isPerfectNumber(n: Long): Boolean {
        return sumOfDivisors(n) - n == n
    }
    
    /**
     * Check if number is abundant (sum of proper divisors greater than number)
     */
    fun isAbundantNumber(n: Long): Boolean {
        return sumOfDivisors(n) - n > n
    }
    
    /**
     * Check if number is deficient (sum of proper divisors less than number)
     */
    fun isDeficientNumber(n: Long): Boolean {
        return sumOfDivisors(n) - n < n
    }
    
    // === MATHEMATICAL SEQUENCES ===
    
    /**
     * Fibonacci number using matrix exponentiation
     */
    fun fibonacci(n: Long): Long {
        if (n <= 1) return n
        
        val matrix = arrayOf(longArrayOf(1, 1), longArrayOf(1, 0))
        val result = matrixPower(matrix, n - 1)
        return result[0][0]
    }
    
    /**
     * Fibonacci number with modulo
     */
    fun fibonacciMod(n: Long, mod: Long): Long {
        if (n <= 1) return n
        
        var a = 0L
        var b = 1L
        for (i in 2..n) {
            val temp = (a + b) % mod
            a = b
            b = temp
        }
        return b
    }
    
    /**
     * Sum of first n natural numbers
     */
    fun sumFirstN(n: Long): Long {
        return n * (n + 1) / 2
    }
    
    /**
     * Sum of squares of first n natural numbers
     */
    fun sumSquares(n: Long): Long {
        return n * (n + 1) * (2 * n + 1) / 6
    }
    
    /**
     * Sum of cubes of first n natural numbers
     */
    fun sumCubes(n: Long): Long {
        val sum = n * (n + 1) / 2
        return sum * sum
    }
    
    // === NUMBER PROPERTIES ===
    
    /**
     * Check if number is power of 2
     */
    fun isPowerOf2(n: Long): Boolean {
        return n > 0 && (n and (n - 1)) == 0L
    }
    
    /**
     * Check if number is power of 3
     */
    fun isPowerOf3(n: Long): Boolean {
        if (n <= 0) return false
        var num = n
        while (num % 3 == 0L) {
            num /= 3
        }
        return num == 1L
    }
    
    /**
     * Check if number is power of 4
     */
    fun isPowerOf4(n: Long): Boolean {
        return n > 0 && (n and (n - 1)) == 0L && (n and 0xAAAAAAAA.toLong()) == 0L
    }
    
    /**
     * Check if number is power of 5
     */
    fun isPowerOf5(n: Long): Boolean {
        if (n <= 0) return false
        var num = n
        while (num % 5 == 0L) {
            num /= 5
        }
        return num == 1L
    }
    
    // === ADVANCED OPERATIONS ===
    
    /**
     * Euler's totient function
     */
    fun eulerTotient(n: Int): Int {
        var result = n
        var num = n
        
        for (i in 2..kotlin.math.sqrt(n.toDouble()).toInt()) {
            if (num % i == 0) {
                while (num % i == 0) {
                    num /= i
                }
                result -= result / i
            }
        }
        
        if (num > 1) {
            result -= result / num
        }
        
        return result
    }
    
    /**
     * Möbius function
     */
    fun mobiusFunction(n: Int): Int {
        if (n == 1) return 1
        
        val factors = getPrimeFactors(n.toLong())
        val distinctFactors = factors.toSet()
        
        if (factors.size != distinctFactors.size) return 0
        return if (distinctFactors.size % 2 == 0) 1 else -1
    }
    
    // === HELPER FUNCTIONS ===
    
    /**
     * Matrix power for Fibonacci calculation
     */
    private fun matrixPower(matrix: Array<LongArray>, n: Long): Array<LongArray> {
        if (n == 0L) return arrayOf(longArrayOf(1, 0), longArrayOf(0, 1))
        if (n == 1L) return matrix
        
        val half = matrixPower(matrix, n / 2)
        val squared = matrixMultiply(half, half)
        
        return if (n % 2 == 0L) squared else matrixMultiply(squared, matrix)
    }
    
    /**
     * Matrix multiplication
     */
    private fun matrixMultiply(a: Array<LongArray>, b: Array<LongArray>): Array<LongArray> {
        val result = Array(2) { LongArray(2) }
        for (i in 0..1) {
            for (j in 0..1) {
                for (k in 0..1) {
                    result[i][j] += a[i][k] * b[k][j]
                }
            }
        }
        return result
    }
}
