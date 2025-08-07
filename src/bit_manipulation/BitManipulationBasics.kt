package bit_manipulation

/**
 * BIT MANIPULATION BASICS - Quick Reference
 * All Kotlin bit manipulation basic operators and concepts in one place
 */

object BitManipulationBasics {
    
    /**
     * All Bit Manipulation Basic Methods
     * Complete reference for basic bit manipulation in Kotlin
     */
    fun allBitManipulationBasics() {
        // === BASIC BITWISE OPERATORS ===
        val a = 5  // 0101 in binary
        val b = 3  // 0011 in binary
        
        val andResult = a and b                    // 0001 = 1
        val orResult = a or b                      // 0111 = 7
        val xorResult = a xor b                    // 0110 = 6
        val notResult = a.inv()                    // 11111111111111111111111111111010 = -6
        val leftShift = a shl 1                    // 1010 = 10
        val rightShift = a shr 1                   // 0010 = 2
        val unsignedRightShift = a ushr 1          // 0010 = 2
        
        // === BIT MANIPULATION WITH NEGATIVE NUMBERS ===
        val negativeA = -5                         // 11111111111111111111111111111011
        val negativeAnd = negativeA and b          // 0011 = 3
        val negativeOr = negativeA or b            // 11111111111111111111111111111011 = -5
        val negativeXor = negativeA xor b          // 11111111111111111111111111111000 = -8
        val negativeLeftShift = negativeA shl 1    // 11111111111111111111111111110110 = -10
        val negativeRightShift = negativeA shr 1   // 11111111111111111111111111111101 = -3
        val negativeUnsignedRightShift = negativeA ushr 1 // 01111111111111111111111111111101 = 2147483645
        
        // === SINGLE BIT OPERATIONS ===
        val num = 42                              // 00101010 in binary
        val setBit = setBit(num, 0)               // Set bit at position 0
        val clearBit = clearBit(num, 1)           // Clear bit at position 1
        val toggleBit = toggleBit(num, 2)         // Toggle bit at position 2
        val getBit = getBit(num, 3)               // Get bit at position 3
        val isBitSet = isBitSet(num, 4)           // Check if bit at position 4 is set
        
        // === COMMON PATTERNS ===
        val isPowerOf2 = isPowerOf2(16)           // true
        val isPowerOf2False = isPowerOf2(15)      // false
        val lowestSetBit = getLowestSetBit(12)    // 4 (position of lowest set bit)
        val clearLowestSetBit = clearLowestSetBit(12) // 8 (clear lowest set bit)
        val highestSetBit = getHighestSetBit(12)  // 8 (position of highest set bit)
        
        // === PERFORMANCE OPTIMIZATION EXAMPLES ===
        val multiplyBy2 = num shl 1               // num * 2
        val divideBy2 = num shr 1                 // num / 2
        val multiplyBy4 = num shl 2               // num * 4
        val divideBy4 = num shr 2                 // num / 4
        val modulo2 = num and 1                   // num % 2
        val modulo4 = num and 3                   // num % 4
        val modulo8 = num and 7                   // num % 8
        
        // === FLAG MANAGEMENT ===
        val READ = 1 shl 0                        // 0001
        val WRITE = 1 shl 1                       // 0010
        val EXECUTE = 1 shl 2                     // 0100
        val DELETE = 1 shl 3                      // 1000
        
        val permissions = READ or WRITE           // 0011 (read and write)
        val hasRead = (permissions and READ) != 0 // true
        val hasWrite = (permissions and WRITE) != 0 // true
        val hasExecute = (permissions and EXECUTE) != 0 // false
        
        // Add execute permission
        val newPermissions = permissions or EXECUTE // 0111
        // Remove write permission
        val finalPermissions = newPermissions and WRITE.inv() // 0101
        
        // === SET OPERATIONS ===
        val set1 = 0b1010                         // {1, 3}
        val set2 = 0b1100                         // {2, 3}
        val union = set1 or set2                  // {1, 2, 3}
        val intersection = set1 and set2          // {3}
        val difference = set1 and set2.inv()      // {1}
        val symmetricDifference = set1 xor set2   // {1, 2}
        
        // === BUILT-IN FUNCTIONS ===
        val bitCount = Integer.bitCount(num)      // Count set bits
        val leadingZeros = Integer.numberOfLeadingZeros(num) // Count leading zeros
        val trailingZeros = Integer.numberOfTrailingZeros(num) // Count trailing zeros
        val highestOneBit = Integer.highestOneBit(num) // Get highest set bit
        val lowestOneBit = Integer.lowestOneBit(num) // Get lowest set bit
        
        // === BIT MANIPULATION WITH LONGS ===
        val longNum = 42L
        val longBitCount = java.lang.Long.bitCount(longNum)
        val longLeadingZeros = java.lang.Long.numberOfLeadingZeros(longNum)
        val longTrailingZeros = java.lang.Long.numberOfTrailingZeros(longNum)
        val longHighestOneBit = java.lang.Long.highestOneBit(longNum)
        val longLowestOneBit = java.lang.Long.lowestOneBit(longNum)
        
        // === ADVANCED PATTERNS ===
        val isPowerOf4 = isPowerOf4(16)           // true
        val isPowerOf4False = isPowerOf4(8)       // false
        val nextPowerOf2 = getNextPowerOf2(7)     // 8
        val previousPowerOf2 = getPreviousPowerOf2(7) // 4
        
        // === BIT REVERSAL ===
        val reversed = reverseBits(8)             // Reverse bits of 8
        val reversedLong = reverseBitsLong(8L)    // Reverse bits of 8L
        
        // === PARITY CHECK ===
        val parity = getParity(7)                 // Check if odd number of set bits
        val parityOptimized = getParityOptimized(7) // Optimized parity check
        
        // === ABSOLUTE VALUE WITHOUT BRANCHING ===
        val absValue = getAbsoluteValue(-42)      // Get absolute value
        val absValueLong = getAbsoluteValueLong(-42L) // Get absolute value for Long
        
        // === MIN/MAX WITHOUT BRANCHING ===
        val minValue = getMin(5, 3)               // Get minimum without branching
        val maxValue = getMax(5, 3)               // Get maximum without branching
    }
    
    /**
     * Set bit at given position
     */
    fun setBit(num: Int, position: Int): Int {
        return num or (1 shl position)
    }
    
    /**
     * Clear bit at given position
     */
    fun clearBit(num: Int, position: Int): Int {
        return num and (1 shl position).inv()
    }
    
    /**
     * Toggle bit at given position
     */
    fun toggleBit(num: Int, position: Int): Int {
        return num xor (1 shl position)
    }
    
    /**
     * Get bit at given position
     */
    fun getBit(num: Int, position: Int): Int {
        return (num shr position) and 1
    }
    
    /**
     * Check if bit is set at given position
     */
    fun isBitSet(num: Int, position: Int): Boolean {
        return (num and (1 shl position)) != 0
    }
    
    /**
     * Check if number is power of 2
     */
    fun isPowerOf2(num: Int): Boolean {
        return num > 0 && (num and (num - 1)) == 0
    }
    
    /**
     * Check if number is power of 4
     */
    fun isPowerOf4(num: Int): Boolean {
        return num > 0 && (num and (num - 1)) == 0 && (num and 0xAAAAAAAA.toInt()) == 0
    }
    
    /**
     * Get lowest set bit
     */
    fun getLowestSetBit(num: Int): Int {
        return num and -num
    }
    
    /**
     * Clear lowest set bit
     */
    fun clearLowestSetBit(num: Int): Int {
        return num and (num - 1)
    }
    
    /**
     * Get highest set bit
     */
    fun getHighestSetBit(num: Int): Int {
        return Integer.highestOneBit(num)
    }
    
    /**
     * Get next power of 2
     */
    fun getNextPowerOf2(num: Int): Int {
        if (num <= 0) return 1
        return 1 shl (32 - Integer.numberOfLeadingZeros(num - 1))
    }
    
    /**
     * Get previous power of 2
     */
    fun getPreviousPowerOf2(num: Int): Int {
        if (num <= 0) return 0
        return Integer.highestOneBit(num)
    }
    
    /**
     * Reverse bits of an integer
     */
    fun reverseBits(num: Int): Int {
        var result = 0
        var n = num
        for (i in 0 until 32) {
            result = result shl 1
            result = result or (n and 1)
            n = n shr 1
        }
        return result
    }
    
    /**
     * Reverse bits of a long
     */
    fun reverseBitsLong(num: Long): Long {
        var result = 0L
        var n = num
        for (i in 0 until 64) {
            result = result shl 1
            result = result or (n and 1L)
            n = n shr 1
        }
        return result
    }
    
    /**
     * Get parity (odd/even number of set bits)
     */
    fun getParity(num: Int): Boolean {
        var parity = false
        var n = num
        while (n > 0) {
            parity = !parity
            n = n and (n - 1)
        }
        return parity
    }
    
    /**
     * Get parity using lookup table (optimized)
     */
    fun getParityOptimized(num: Int): Boolean {
        // Precomputed parity table for 8-bit numbers
        val parityTable = intArrayOf(
            0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0,
            1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1,
            1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1,
            0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0,
            1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1,
            0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0,
            0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0,
            1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1,
            1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1,
            0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0,
            0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0,
            1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1,
            0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0,
            1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1,
            1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1,
            0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0
        )
        
        var result = 0
        var n = num
        for (i in 0 until 4) {
            result = result xor parityTable[n and 0xFF]
            n = n shr 8
        }
        return result == 1
    }
    
    /**
     * Get absolute value without branching
     */
    fun getAbsoluteValue(num: Int): Int {
        val mask = num shr 31
        return (num + mask) xor mask
    }
    
    /**
     * Get absolute value for Long without branching
     */
    fun getAbsoluteValueLong(num: Long): Long {
        val mask = num shr 63
        return (num + mask) xor mask
    }
    
    /**
     * Get minimum of two numbers without branching
     */
    fun getMin(a: Int, b: Int): Int {
        return b xor ((a xor b) and -(if (a < b) 1 else 0))
    }
    
    /**
     * Get maximum of two numbers without branching
     */
    fun getMax(a: Int, b: Int): Int {
        return a xor ((a xor b) and -(if (a < b) 1 else 0))
    }
}
