package bit_manipulation.algorithms

/**
 * POWER OF TWO ALGORITHMS
 * 
 * Problem: Check if a number is a power of 2 and related power operations.
 * 
 * Examples:
 * Input: 16 → Output: true (2^4 = 16)
 * Input: 15 → Output: false (not a power of 2)
 * Input: 1 → Output: true (2^0 = 1)
 * Input: 0 → Output: false (not a power of 2)
 * 
 * Intuition: Power of 2 has exactly one set bit, use bitwise operations for efficient checking
 * 
 * Time Complexity: O(1) - constant time operations
 * Space Complexity: O(1) - constant extra space
 */

object PowerOfTwo {
    
    /**
     * Check if number is power of 2
     * 
     * Problem: Given an integer n, return true if it is a power of two, and false otherwise.
     * 
     * Examples:
     * Input: 1 → Output: true (2^0 = 1)
     * Input: 16 → Output: true (2^4 = 16)
     * Input: 3 → Output: false
     * Input: 0 → Output: false
     * 
     * Intuition: Power of 2 has exactly one set bit, so n & (n-1) == 0
     * 
     * Time Complexity: O(1) - constant time operations
     * Space Complexity: O(1) - constant extra space
     */
    fun isPowerOfTwo(n: Int): Boolean {
        return n > 0 && (n and (n - 1)) == 0
    }
    
    /**
     * Check if number is power of 2 using bit count
     */
    fun isPowerOfTwoBitCount(n: Int): Boolean {
        return n > 0 && Integer.bitCount(n) == 1
    }
    
    /**
     * Check if number is power of 2 using logarithm
     */
    fun isPowerOfTwoLog(n: Int): Boolean {
        if (n <= 0) return false
        val log2 = kotlin.math.log2(n.toDouble())
        return log2 == log2.toInt().toDouble()
    }
    
    /**
     * Check if number is power of 4
     * 
     * Problem: Given an integer n, return true if it is a power of four, and false otherwise.
     * 
     * Examples:
     * Input: 16 → Output: true (4^2 = 16)
     * Input: 5 → Output: false
     * Input: 1 → Output: true (4^0 = 1)
     * 
     * Intuition: Power of 4 is power of 2 and has set bit at even positions
     * 
     * Time Complexity: O(1) - constant time operations
     * Space Complexity: O(1) - constant extra space
     */
    fun isPowerOfFour(n: Int): Boolean {
        return n > 0 && (n and (n - 1)) == 0 && (n and 0xAAAAAAAA.toInt()) == 0
    }
    
    /**
     * Check if number is power of 8
     * 
     * Problem: Given an integer n, return true if it is a power of eight, and false otherwise.
     * 
     * Examples:
     * Input: 8 → Output: true (8^1 = 8)
     * Input: 64 → Output: true (8^2 = 64)
     * Input: 16 → Output: false
     * 
     * Intuition: Power of 8 is power of 2 and has set bit at positions divisible by 3
     * 
     * Time Complexity: O(1) - constant time operations
     * Space Complexity: O(1) - constant extra space
     */
    fun isPowerOfEight(n: Int): Boolean {
        return n > 0 && (n and (n - 1)) == 0 && (n and 0x49249249) == 0
    }
    
    /**
     * Check if number is power of 16
     * 
     * Problem: Given an integer n, return true if it is a power of sixteen, and false otherwise.
     * 
     * Examples:
     * Input: 16 → Output: true (16^1 = 16)
     * Input: 256 → Output: true (16^2 = 256)
     * Input: 32 → Output: false
     * 
     * Intuition: Power of 16 is power of 2 and has set bit at positions divisible by 4
     * 
     * Time Complexity: O(1) - constant time operations
     * Space Complexity: O(1) - constant extra space
     */
    fun isPowerOfSixteen(n: Int): Boolean {
        return n > 0 && (n and (n - 1)) == 0 && (n and 0x11111111) == 0
    }
    
    /**
     * Get next power of 2
     * 
     * Problem: Given a number n, find the next power of 2 greater than or equal to n.
     * 
     * Examples:
     * Input: 5 → Output: 8 (next power of 2 >= 5)
     * Input: 8 → Output: 8 (already a power of 2)
     * Input: 17 → Output: 32 (next power of 2 >= 17)
     * 
     * Intuition: Use leading zeros to find the next power of 2
     * 
     * Time Complexity: O(1) - constant time operations
     * Space Complexity: O(1) - constant extra space
     */
    fun getNextPowerOfTwo(n: Int): Int {
        if (n <= 0) return 1
        return 1 shl (32 - Integer.numberOfLeadingZeros(n - 1))
    }
    
    /**
     * Get previous power of 2
     * 
     * Problem: Given a number n, find the previous power of 2 less than or equal to n.
     * 
     * Examples:
     * Input: 5 → Output: 4 (previous power of 2 <= 5)
     * Input: 8 → Output: 8 (already a power of 2)
     * Input: 17 → Output: 16 (previous power of 2 <= 17)
     * 
     * Intuition: Use highest one bit to find the previous power of 2
     * 
     * Time Complexity: O(1) - constant time operations
     * Space Complexity: O(1) - constant extra space
     */
    fun getPreviousPowerOfTwo(n: Int): Int {
        if (n <= 0) return 0
        return Integer.highestOneBit(n)
    }
    
    /**
     * Get power of 2 at given exponent
     * 
     * Problem: Given an exponent e, return 2^e.
     * 
     * Examples:
     * Input: 0 → Output: 1 (2^0 = 1)
     * Input: 3 → Output: 8 (2^3 = 8)
     * Input: 10 → Output: 1024 (2^10 = 1024)
     * 
     * Intuition: Use left shift for efficient power calculation
     * 
     * Time Complexity: O(1) - constant time operations
     * Space Complexity: O(1) - constant extra space
     */
    fun getPowerOfTwo(exponent: Int): Int {
        return if (exponent >= 0 && exponent < 31) 1 shl exponent else 0
    }
    
    /**
     * Get exponent of power of 2
     * 
     * Problem: Given a power of 2, find its exponent.
     * 
     * Examples:
     * Input: 1 → Output: 0 (2^0 = 1)
     * Input: 8 → Output: 3 (2^3 = 8)
     * Input: 1024 → Output: 10 (2^10 = 1024)
     * 
     * Intuition: Use trailing zeros to find the exponent
     * 
     * Time Complexity: O(1) - constant time operations
     * Space Complexity: O(1) - constant extra space
     */
    fun getExponentOfPowerOfTwo(n: Int): Int {
        if (!isPowerOfTwo(n)) return -1
        return Integer.numberOfTrailingZeros(n)
    }
    
    /**
     * Check if number is power of 2 using loop
     */
    fun isPowerOfTwoLoop(n: Int): Boolean {
        if (n <= 0) return false
        var num = n
        while (num > 1) {
            if (num % 2 != 0) return false
            num /= 2
        }
        return true
    }
    
    /**
     * Check if number is power of 2 using recursion
     */
    fun isPowerOfTwoRecursive(n: Int): Boolean {
        if (n <= 0) return false
        if (n == 1) return true
        if (n % 2 != 0) return false
        return isPowerOfTwoRecursive(n / 2)
    }
    
    /**
     * Get all powers of 2 up to n
     * 
     * Problem: Given a number n, return all powers of 2 less than or equal to n.
     * 
     * Examples:
     * Input: 10 → Output: [1, 2, 4, 8]
     * Input: 16 → Output: [1, 2, 4, 8, 16]
     * Input: 5 → Output: [1, 2, 4]
     * 
     * Time Complexity: O(log n) - number of powers of 2 up to n
     * Space Complexity: O(log n) - storage for result
     */
    fun getAllPowersOfTwoUpTo(n: Int): List<Int> {
        val result = mutableListOf<Int>()
        var power = 1
        while (power <= n) {
            result.add(power)
            power *= 2
        }
        return result
    }
    
    /**
     * Get powers of 2 with given count
     * 
     * Problem: Given a count, return the first n powers of 2.
     * 
     * Examples:
     * Input: 4 → Output: [1, 2, 4, 8]
     * Input: 6 → Output: [1, 2, 4, 8, 16, 32]
     * Input: 0 → Output: []
     * 
     * Time Complexity: O(n) - generate n powers
     * Space Complexity: O(n) - storage for result
     */
    fun getPowersOfTwo(count: Int): List<Int> {
        return (0 until count).map { 1 shl it }
    }
    
    /**
     * Check if sum of two numbers is power of 2
     * 
     * Problem: Given two numbers a and b, check if their sum is a power of 2.
     * 
     * Examples:
     * Input: a = 3, b = 5 → Output: true (3 + 5 = 8, which is 2^3)
     * Input: a = 2, b = 3 → Output: false (2 + 3 = 5, not a power of 2)
     * Input: a = 1, b = 1 → Output: true (1 + 1 = 2, which is 2^1)
     * 
     * Time Complexity: O(1) - constant time operations
     * Space Complexity: O(1) - constant extra space
     */
    fun isSumPowerOfTwo(a: Int, b: Int): Boolean {
        return isPowerOfTwo(a + b)
    }
    
    /**
     * Check if difference of two numbers is power of 2
     * 
     * Problem: Given two numbers a and b, check if their absolute difference is a power of 2.
     * 
     * Examples:
     * Input: a = 5, b = 3 → Output: true (|5 - 3| = 2, which is 2^1)
     * Input: a = 10, b = 6 → Output: true (|10 - 6| = 4, which is 2^2)
     * Input: a = 7, b = 3 → Output: false (|7 - 3| = 4, which is 2^2, but let's check)
     * 
     * Time Complexity: O(1) - constant time operations
     * Space Complexity: O(1) - constant extra space
     */
    fun isDifferencePowerOfTwo(a: Int, b: Int): Boolean {
        return isPowerOfTwo(kotlin.math.abs(a - b))
    }
    
    /**
     * Demonstrate all power of two algorithms
     */
    fun demonstratePowerOfTwoAlgorithms() {
        println("=== POWER OF TWO ALGORITHMS DEMONSTRATION ===")
        
        // Basic power of 2 checks
        println("1. BASIC POWER OF 2 CHECKS")
        val testNumbers = intArrayOf(0, 1, 2, 3, 4, 8, 15, 16, 32, 64, 128)
        for (num in testNumbers) {
            println("$num is power of 2: ${isPowerOfTwo(num)}")
        }
        println()
        
        // Different approaches for power of 2
        println("2. DIFFERENT APPROACHES FOR POWER OF 2")
        val testNum = 16
        println("Number: $testNum")
        println("Bit manipulation: ${isPowerOfTwo(testNum)}")
        println("Bit count: ${isPowerOfTwoBitCount(testNum)}")
        println("Logarithm: ${isPowerOfTwoLog(testNum)}")
        println("Loop: ${isPowerOfTwoLoop(testNum)}")
        println("Recursive: ${isPowerOfTwoRecursive(testNum)}")
        println()
        
        // Power of 4, 8, 16
        println("3. POWER OF 4, 8, 16")
        val testNumbers2 = intArrayOf(1, 2, 4, 8, 16, 32, 64, 128, 256)
        for (num in testNumbers2) {
            println("$num: Power of 2: ${isPowerOfTwo(num)}, Power of 4: ${isPowerOfFour(num)}, Power of 8: ${isPowerOfEight(num)}, Power of 16: ${isPowerOfSixteen(num)}")
        }
        println()
        
        // Next and previous power of 2
        println("4. NEXT AND PREVIOUS POWER OF 2")
        val testNumbers3 = intArrayOf(5, 8, 17, 31, 64)
        for (num in testNumbers3) {
            println("$num: Previous: ${getPreviousPowerOfTwo(num)}, Next: ${getNextPowerOfTwo(num)}")
        }
        println()
        
        // Power of 2 operations
        println("5. POWER OF 2 OPERATIONS")
        println("2^0 = ${getPowerOfTwo(0)}")
        println("2^3 = ${getPowerOfTwo(3)}")
        println("2^10 = ${getPowerOfTwo(10)}")
        println("Exponent of 8: ${getExponentOfPowerOfTwo(8)}")
        println("Exponent of 1024: ${getExponentOfPowerOfTwo(1024)}")
        println()
        
        // Get powers of 2 up to n
        println("6. POWERS OF 2 UP TO N")
        println("Powers up to 10: ${getAllPowersOfTwoUpTo(10)}")
        println("Powers up to 16: ${getAllPowersOfTwoUpTo(16)}")
        println("First 6 powers: ${getPowersOfTwo(6)}")
        println()
        
        // Sum and difference checks
        println("7. SUM AND DIFFERENCE CHECKS")
        println("3 + 5 = 8 is power of 2: ${isSumPowerOfTwo(3, 5)}")
        println("2 + 3 = 5 is power of 2: ${isSumPowerOfTwo(2, 3)}")
        println("5 - 3 = 2 is power of 2: ${isDifferencePowerOfTwo(5, 3)}")
        println("10 - 6 = 4 is power of 2: ${isDifferencePowerOfTwo(10, 6)}")
        println()
        
        println("=== POWER OF TWO ALGORITHMS DEMONSTRATION COMPLETE ===")
    }
}
