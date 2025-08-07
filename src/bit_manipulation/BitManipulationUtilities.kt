package bit_manipulation

/**
 * BIT MANIPULATION UTILITIES - Quick Reference
 * All Kotlin bit manipulation utility functions and helpers in one place
 */

object BitManipulationUtilities {
    
    /**
     * All Bit Manipulation Utility Methods
     * Complete reference for bit manipulation utilities in Kotlin
     */
    fun allBitManipulationUtilities() {
        // === CONVERSION UTILITIES ===
        val num = 42
        val binaryString = toBinaryString(num)     // "00000000000000000000000000101010"
        val binaryStringShort = toBinaryStringShort(num) // "101010"
        val hexString = toHexString(num)           // "2a"
        val octalString = toOctalString(num)       // "52"
        
        // === DISPLAY UTILITIES ===
        printBinary(num)                           // Print binary representation
        printBinaryWithSpaces(num)                 // Print binary with spaces
        printBinaryGrouped(num, 4)                 // Print binary grouped by 4
        printBinaryGrouped(num, 8)                 // Print binary grouped by 8
        
        // === COMPARISON UTILITIES ===
        val num1 = 5                               // 0101
        val num2 = 3                               // 0011
        val bitwiseComparison = compareBitwise(num1, num2) // Compare bit by bit
        val hammingDistance = calculateHammingDistance(num1, num2) // 2
        
        // === VALIDATION UTILITIES ===
        val isValidBitPosition = isValidBitPosition(5) // true
        val isValidBitPosition2 = isValidBitPosition(32) // false
        val isValidBitRange = isValidBitRange(2, 6) // true
        val isValidBitRange2 = isValidBitRange(6, 2) // false
        
        // === CONSTANT UTILITIES ===
        val maxInt = getMaxInt()                   // 2147483647
        val minInt = getMinInt()                   // -2147483648
        val maxLong = getMaxLong()                 // 9223372036854775807
        val minLong = getMinLong()                 // -9223372036854775808
        
        // === MASK UTILITIES ===
        val allOnes = getAllOnes()                 // -1 (all bits set)
        val allZeros = getAllZeros()               // 0 (all bits clear)
        val alternatingOnes = getAlternatingOnes() // 0xAAAAAAAA
        val alternatingZeros = getAlternatingZeros() // 0x55555555
        
        // === POWER UTILITIES ===
        val powerOf2Array = getPowerOf2Array(10)   // [1, 2, 4, 8, 16, 32, 64, 128, 256, 512]
        val powerOf2Set = getPowerOf2Set(10)       // Set of powers of 2
        val isPowerOf2Fast = isPowerOf2Fast(16)    // true
        val nextPowerOf2Fast = getNextPowerOf2Fast(7) // 8
        
        // === BIT COUNTING UTILITIES ===
        val bitCountArray = getBitCountArray(10)   // [0, 1, 1, 2, 1, 2, 2, 3, 1, 2]
        val bitCountMap = getBitCountMap(10)       // Map of number to bit count
        val numbersWithBitCount = getNumbersWithBitCount(3, 5) // Numbers with 3 set bits up to 5
        
        // === PATTERN UTILITIES ===
        val palindromeBits = isPalindromeBits(9)   // true (1001 is palindrome)
        val palindromeBits2 = isPalindromeBits(10) // false (1010 is not palindrome)
        val alternatingBits = hasAlternatingBits(5) // true (0101 has alternating bits)
        val alternatingBits2 = hasAlternatingBits(7) // false (0111 doesn't have alternating bits)
        
        // === RANGE UTILITIES ===
        val rangeSum = getBitRangeSum(num, 1, 4)   // Sum of bits in range
        val rangeCount = getBitRangeCount(num, 1, 4) // Count of set bits in range
        val rangeMax = getBitRangeMax(num, 1, 4)   // Maximum value in range
        val rangeMin = getBitRangeMin(num, 1, 4)   // Minimum value in range
        
        // === TRANSFORMATION UTILITIES ===
        val mirrored = mirrorBits(num)             // Mirror bits
        val reversed = reverseBits(num)            // Reverse bits
        val rotated = rotateBits(num, 2)           // Rotate bits by 2
        val swapped = swapHalves(num)              // Swap upper and lower halves
        
        // === COMBINATION UTILITIES ===
        val combinations = getBitCombinations(3)   // All combinations of 3 bits
        val permutations = getBitPermutations(3)   // All permutations of 3 bits
        val subsets = getBitSubsets(3)             // All subsets of 3 bits
        
        // === ANALYSIS UTILITIES ===
        val bitAnalysis = analyzeBits(num)         // Detailed bit analysis
        val patternAnalysis = analyzeBitPattern(num) // Pattern analysis
        val complexityAnalysis = analyzeBitComplexity(num) // Complexity analysis
    }
    
    /**
     * Convert number to binary string with leading zeros
     */
    fun toBinaryString(num: Int): String {
        return String.format("%32s", Integer.toBinaryString(num)).replace(' ', '0')
    }
    
    /**
     * Convert number to binary string without leading zeros
     */
    fun toBinaryStringShort(num: Int): String {
        return Integer.toBinaryString(num)
    }
    
    /**
     * Convert number to hexadecimal string
     */
    fun toHexString(num: Int): String {
        return Integer.toHexString(num)
    }
    
    /**
     * Convert number to octal string
     */
    fun toOctalString(num: Int): String {
        return Integer.toOctalString(num)
    }
    
    /**
     * Print binary representation
     */
    fun printBinary(num: Int) {
        println("Binary: ${toBinaryString(num)}")
    }
    
    /**
     * Print binary with spaces every 4 bits
     */
    fun printBinaryWithSpaces(num: Int) {
        val binary = toBinaryString(num)
        val spaced = binary.chunked(4).joinToString(" ")
        println("Binary with spaces: $spaced")
    }
    
    /**
     * Print binary grouped by specified size
     */
    fun printBinaryGrouped(num: Int, groupSize: Int) {
        val binary = toBinaryString(num)
        val grouped = binary.chunked(groupSize).joinToString(" ")
        println("Binary grouped by $groupSize: $grouped")
    }
    
    /**
     * Compare two numbers bit by bit
     */
    fun compareBitwise(num1: Int, num2: Int): String {
        val binary1 = toBinaryString(num1)
        val binary2 = toBinaryString(num2)
        val comparison = binary1.zip(binary2).map { (b1, b2) ->
            when {
                b1 == b2 -> "="
                b1 == '1' -> ">"
                else -> "<"
            }
        }.joinToString("")
        return comparison
    }
    
    /**
     * Calculate Hamming distance between two numbers
     */
    fun calculateHammingDistance(num1: Int, num2: Int): Int {
        return Integer.bitCount(num1 xor num2)
    }
    
    /**
     * Check if bit position is valid
     */
    fun isValidBitPosition(position: Int): Boolean {
        return position >= 0 && position < 32
    }
    
    /**
     * Check if bit range is valid
     */
    fun isValidBitRange(start: Int, end: Int): Boolean {
        return start >= 0 && end < 32 && start <= end
    }
    
    /**
     * Get maximum integer value
     */
    fun getMaxInt(): Int {
        return Int.MAX_VALUE
    }
    
    /**
     * Get minimum integer value
     */
    fun getMinInt(): Int {
        return Int.MIN_VALUE
    }
    
    /**
     * Get maximum long value
     */
    fun getMaxLong(): Long {
        return Long.MAX_VALUE
    }
    
    /**
     * Get minimum long value
     */
    fun getMinLong(): Long {
        return Long.MIN_VALUE
    }
    
    /**
     * Get all ones (all bits set)
     */
    fun getAllOnes(): Int {
        return -1
    }
    
    /**
     * Get all zeros (all bits clear)
     */
    fun getAllZeros(): Int {
        return 0
    }
    
    /**
     * Get alternating ones (1010...)
     */
    fun getAlternatingOnes(): Int {
        return 0xAAAAAAAA.toInt()
    }
    
    /**
     * Get alternating zeros (0101...)
     */
    fun getAlternatingZeros(): Int {
        return 0x55555555
    }
    
    /**
     * Get array of powers of 2 up to n
     */
    fun getPowerOf2Array(n: Int): IntArray {
        return IntArray(n) { 1 shl it }
    }
    
    /**
     * Get set of powers of 2 up to n
     */
    fun getPowerOf2Set(n: Int): Set<Int> {
        return (0 until n).map { 1 shl it }.toSet()
    }
    
    /**
     * Fast power of 2 check
     */
    fun isPowerOf2Fast(num: Int): Boolean {
        return num > 0 && (num and (num - 1)) == 0
    }
    
    /**
     * Fast next power of 2
     */
    fun getNextPowerOf2Fast(num: Int): Int {
        if (num <= 0) return 1
        return 1 shl (32 - Integer.numberOfLeadingZeros(num - 1))
    }
    
    /**
     * Get array of bit counts for numbers 0 to n-1
     */
    fun getBitCountArray(n: Int): IntArray {
        return IntArray(n) { Integer.bitCount(it) }
    }
    
    /**
     * Get map of number to bit count for numbers 0 to n-1
     */
    fun getBitCountMap(n: Int): Map<Int, Int> {
        return (0 until n).associateWith { Integer.bitCount(it) }
    }
    
    /**
     * Get numbers with specified bit count up to max
     */
    fun getNumbersWithBitCount(bitCount: Int, max: Int): List<Int> {
        return (0..max).filter { Integer.bitCount(it) == bitCount }
    }
    
    /**
     * Check if binary representation is palindrome
     */
    fun isPalindromeBits(num: Int): Boolean {
        val binary = toBinaryStringShort(num)
        return binary == binary.reversed()
    }
    
    /**
     * Check if number has alternating bits
     */
    fun hasAlternatingBits(num: Int): Boolean {
        val shifted = num shr 1
        return (num xor shifted) == ((1 shl Integer.toBinaryString(num).length) - 1)
    }
    
    /**
     * Get sum of bits in range
     */
    fun getBitRangeSum(num: Int, start: Int, end: Int): Int {
        if (!isValidBitRange(start, end)) return 0
        var sum = 0
        for (i in start..end) {
            sum += (num shr i) and 1
        }
        return sum
    }
    
    /**
     * Get count of set bits in range
     */
    fun getBitRangeCount(num: Int, start: Int, end: Int): Int {
        if (!isValidBitRange(start, end)) return 0
        val mask = ((1 shl (end - start + 1)) - 1) shl start
        return Integer.bitCount(num and mask)
    }
    
    /**
     * Get maximum value in bit range
     */
    fun getBitRangeMax(num: Int, start: Int, end: Int): Int {
        if (!isValidBitRange(start, end)) return 0
        val length = end - start + 1
        return (1 shl length) - 1
    }
    
    /**
     * Get minimum value in bit range
     */
    fun getBitRangeMin(num: Int, start: Int, end: Int): Int {
        if (!isValidBitRange(start, end)) return 0
        return 0
    }
    
    /**
     * Mirror bits (reverse order)
     */
    fun mirrorBits(num: Int): Int {
        var result = 0
        var n = num
        for (i in 0 until 32) {
            result = (result shl 1) or (n and 1)
            n = n shr 1
        }
        return result
    }
    
    /**
     * Reverse bits
     */
    fun reverseBits(num: Int): Int {
        var result = 0
        var n = num
        for (i in 0 until 32) {
            result = (result shl 1) or (n and 1)
            n = n shr 1
        }
        return result
    }
    
    /**
     * Rotate bits by given positions
     */
    fun rotateBits(num: Int, positions: Int): Int {
        val n = positions and 31
        return (num shl n) or (num ushr (32 - n))
    }
    
    /**
     * Swap upper and lower halves
     */
    fun swapHalves(num: Int): Int {
        return ((num and 0xFFFF) shl 16) or ((num ushr 16) and 0xFFFF)
    }
    
    /**
     * Get all combinations of n bits
     */
    fun getBitCombinations(n: Int): List<Int> {
        return (0 until (1 shl n)).toList()
    }
    
    /**
     * Get all permutations of n bits (same as combinations for bits)
     */
    fun getBitPermutations(n: Int): List<Int> {
        return getBitCombinations(n)
    }
    
    /**
     * Get all subsets of n bits
     */
    fun getBitSubsets(n: Int): List<Int> {
        return getBitCombinations(n)
    }
    
    /**
     * Analyze bits of a number
     */
    fun analyzeBits(num: Int): Map<String, Any> {
        return mapOf(
            "number" to num,
            "binary" to toBinaryStringShort(num),
            "hex" to toHexString(num),
            "octal" to toOctalString(num),
            "bitCount" to Integer.bitCount(num),
            "leadingZeros" to Integer.numberOfLeadingZeros(num),
            "trailingZeros" to Integer.numberOfTrailingZeros(num),
            "isPowerOf2" to isPowerOf2Fast(num),
            "isEven" to ((num and 1) == 0),
            "isOdd" to ((num and 1) == 1)
        )
    }
    
    /**
     * Analyze bit pattern
     */
    fun analyzeBitPattern(num: Int): Map<String, Any> {
        val binary = toBinaryStringShort(num)
        return mapOf(
            "pattern" to binary,
            "length" to binary.length,
            "isPalindrome" to isPalindromeBits(num),
            "hasAlternatingBits" to hasAlternatingBits(num),
            "consecutiveOnes" to getConsecutiveOnes(num),
            "consecutiveZeros" to getConsecutiveZeros(num)
        )
    }
    
    /**
     * Analyze bit complexity
     */
    fun analyzeBitComplexity(num: Int): Map<String, Any> {
        return mapOf(
            "bitCount" to Integer.bitCount(num),
            "bitDensity" to Integer.bitCount(num).toDouble() / 32,
            "sparsity" to (32 - Integer.bitCount(num)).toDouble() / 32,
            "entropy" to calculateBitEntropy(num)
        )
    }
    
    /**
     * Get consecutive ones count
     */
    private fun getConsecutiveOnes(num: Int): Int {
        var maxCount = 0
        var currentCount = 0
        var n = num
        for (i in 0 until 32) {
            if ((n and 1) == 1) {
                currentCount++
                maxCount = maxOf(maxCount, currentCount)
            } else {
                currentCount = 0
            }
            n = n shr 1
        }
        return maxCount
    }
    
    /**
     * Get consecutive zeros count
     */
    private fun getConsecutiveZeros(num: Int): Int {
        var maxCount = 0
        var currentCount = 0
        var n = num
        for (i in 0 until 32) {
            if ((n and 1) == 0) {
                currentCount++
                maxCount = maxOf(maxCount, currentCount)
            } else {
                currentCount = 0
            }
            n = n shr 1
        }
        return maxCount
    }
    
    /**
     * Calculate bit entropy
     */
    private fun calculateBitEntropy(num: Int): Double {
        val bitCount = Integer.bitCount(num)
        val zeroCount = 32 - bitCount
        val p1 = bitCount.toDouble() / 32
        val p0 = zeroCount.toDouble() / 32
        
        var entropy = 0.0
        if (p1 > 0) entropy -= p1 * kotlin.math.log2(p1)
        if (p0 > 0) entropy -= p0 * kotlin.math.log2(p0)
        
        return entropy
    }
}
