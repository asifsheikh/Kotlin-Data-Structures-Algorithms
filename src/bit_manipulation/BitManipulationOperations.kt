package bit_manipulation

/**
 * BIT MANIPULATION OPERATIONS - Quick Reference
 * All Kotlin bit manipulation common operations and patterns in one place
 */

object BitManipulationOperations {
    
    /**
     * All Bit Manipulation Operations
     * Complete reference for bit manipulation operations in Kotlin
     */
    fun allBitManipulationOperations() {
        // === COUNTING OPERATIONS ===
        val num = 42                              // 00101010 in binary
        val setBitsCount = countSetBits(num)      // 3
        val setBitsCountOptimized = countSetBitsOptimized(num) // 3
        val setBitsCountBuiltin = Integer.bitCount(num) // 3
        val leadingZerosCount = countLeadingZeros(num) // 26
        val trailingZerosCount = countTrailingZeros(num) // 1
        val totalBits = getTotalBits(num)         // 32
        
        // === PATTERN OPERATIONS ===
        val isEven = isEven(num)                  // true
        val isOdd = isOdd(num)                    // false
        val isPowerOf2 = isPowerOf2(num)          // false
        val isPowerOf4 = isPowerOf4(num)          // false
        val isPowerOf8 = isPowerOf8(num)          // false
        val isPowerOf16 = isPowerOf16(num)        // false
        
        // === BIT POSITION OPERATIONS ===
        val lowestSetBitPos = getLowestSetBitPosition(num) // 1
        val highestSetBitPos = getHighestSetBitPosition(num) // 5
        val nextSetBitPos = getNextSetBitPosition(num, 2) // 4
        val prevSetBitPos = getPreviousSetBitPosition(num, 4) // 1
        
        // === BIT RANGE OPERATIONS ===
        val range = getBitRange(num, 1, 4)        // Get bits from position 1 to 4
        val setBitRange = setBitRange(num, 1, 4, 0b1111) // Set bits in range
        val clearBitRange = clearBitRange(num, 1, 4) // Clear bits in range
        val toggleBitRange = toggleBitRange(num, 1, 4) // Toggle bits in range
        
        // === ROTATION OPERATIONS ===
        val leftRotated = leftRotate(num, 2)      // Left rotate by 2 positions
        val rightRotated = rightRotate(num, 2)    // Right rotate by 2 positions
        val leftRotatedLong = leftRotateLong(num.toLong(), 2) // Long version
        val rightRotatedLong = rightRotateLong(num.toLong(), 2) // Long version
        
        // === SWAP OPERATIONS ===
        val swappedBits = swapBits(num, 1, 5)     // Swap bits at positions 1 and 5
        val swappedNibbles = swapNibbles(num)     // Swap upper and lower nibbles
        val swappedBytes = swapBytes(num)         // Swap bytes (little-endian to big-endian)
        
        // === MASK OPERATIONS ===
        val mask = createMask(4)                  // Create mask with 4 set bits
        val maskRange = createMaskRange(2, 6)     // Create mask for range 2-6
        val maskComplement = createMaskComplement(4) // Create complement mask
        
        // === COMPARISON OPERATIONS ===
        val minWithoutBranching = getMinWithoutBranching(5, 3) // 3
        val maxWithoutBranching = getMaxWithoutBranching(5, 3) // 5
        val signWithoutBranching = getSignWithoutBranching(-5) // -1
        val signWithoutBranching2 = getSignWithoutBranching(5) // 1
        val signWithoutBranching3 = getSignWithoutBranching(0) // 0
        
        // === ARITHMETIC OPERATIONS ===
        val addWithoutPlus = addWithoutPlus(5, 3) // 8
        val subtractWithoutMinus = subtractWithoutMinus(5, 3) // 2
        val multiplyWithoutStar = multiplyWithoutStar(5, 3) // 15
        val divideWithoutSlash = divideWithoutSlash(15, 3) // 5
        
        // === LOGICAL OPERATIONS ===
        val logicalAnd = logicalAnd(5, 3)         // 1
        val logicalOr = logicalOr(5, 3)           // 7
        val logicalXor = logicalXor(5, 3)         // 6
        val logicalNot = logicalNot(5)            // -6
        
        // === CONDITIONAL OPERATIONS ===
        val conditionalSet = conditionalSet(num, 0, true) // Set bit 0 if condition is true
        val conditionalClear = conditionalClear(num, 1, false) // Clear bit 1 if condition is false
        val conditionalToggle = conditionalToggle(num, 2, true) // Toggle bit 2 if condition is true
        
        // === MULTIPLE BIT OPERATIONS ===
        val setMultipleBits = setMultipleBits(num, intArrayOf(0, 2, 4)) // Set multiple bits
        val clearMultipleBits = clearMultipleBits(num, intArrayOf(1, 3, 5)) // Clear multiple bits
        val toggleMultipleBits = toggleMultipleBits(num, intArrayOf(0, 1, 2)) // Toggle multiple bits
        
        // === BIT FIELD OPERATIONS ===
        val bitField = createBitField(3, 2, 6)    // Create bit field from position 2, length 6
        val extractBitField = extractBitField(num, 2, 6) // Extract bit field
        val insertBitField = insertBitField(num, 0b111111, 2, 6) // Insert bit field
        
        // === FLAG OPERATIONS ===
        val flags = createFlags(intArrayOf(0, 2, 4)) // Create flags from positions
        val hasAllFlags = hasAllFlags(num, flags) // Check if all flags are set
        val hasAnyFlags = hasAnyFlags(num, flags) // Check if any flags are set
        val addFlags = addFlags(num, flags)       // Add flags
        val removeFlags = removeFlags(num, flags) // Remove flags
        val toggleFlags = toggleFlags(num, flags) // Toggle flags
    }
    
    /**
     * Count set bits using Brian Kernighan's algorithm
     */
    fun countSetBits(num: Int): Int {
        var count = 0
        var n = num
        while (n > 0) {
            n = n and (n - 1)
            count++
        }
        return count
    }
    
    /**
     * Count set bits using lookup table (optimized)
     */
    fun countSetBitsOptimized(num: Int): Int {
        // Precomputed table for 8-bit numbers
        val bitCountTable = intArrayOf(
            0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4,
            1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
            1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
            1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
            2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
            3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
            3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
            4, 5, 5, 6, 5, 6, 6, 7, 5, 6, 6, 7, 6, 7, 7, 8
        )
        
        var result = 0
        var n = num
        for (i in 0 until 4) {
            result += bitCountTable[n and 0xFF]
            n = n shr 8
        }
        return result
    }
    
    /**
     * Count leading zeros
     */
    fun countLeadingZeros(num: Int): Int {
        return Integer.numberOfLeadingZeros(num)
    }
    
    /**
     * Count trailing zeros
     */
    fun countTrailingZeros(num: Int): Int {
        return Integer.numberOfTrailingZeros(num)
    }
    
    /**
     * Get total number of bits
     */
    fun getTotalBits(num: Int): Int {
        return 32
    }
    
    /**
     * Check if number is even
     */
    fun isEven(num: Int): Boolean {
        return (num and 1) == 0
    }
    
    /**
     * Check if number is odd
     */
    fun isOdd(num: Int): Boolean {
        return (num and 1) == 1
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
     * Check if number is power of 8
     */
    fun isPowerOf8(num: Int): Boolean {
        return num > 0 && (num and (num - 1)) == 0 && (num and 0x49249249) == 0
    }
    
    /**
     * Check if number is power of 16
     */
    fun isPowerOf16(num: Int): Boolean {
        return num > 0 && (num and (num - 1)) == 0 && (num and 0x11111111) == 0
    }
    
    /**
     * Get position of lowest set bit
     */
    fun getLowestSetBitPosition(num: Int): Int {
        return Integer.numberOfTrailingZeros(num)
    }
    
    /**
     * Get position of highest set bit
     */
    fun getHighestSetBitPosition(num: Int): Int {
        return 31 - Integer.numberOfLeadingZeros(num)
    }
    
    /**
     * Get next set bit position after given position
     */
    fun getNextSetBitPosition(num: Int, position: Int): Int {
        val mask = (1 shl (position + 1)) - 1
        val nextNum = num and mask.inv()
        return if (nextNum == 0) -1 else Integer.numberOfTrailingZeros(nextNum)
    }
    
    /**
     * Get previous set bit position before given position
     */
    fun getPreviousSetBitPosition(num: Int, position: Int): Int {
        val mask = (1 shl position) - 1
        val prevNum = num and mask
        return if (prevNum == 0) -1 else 31 - Integer.numberOfLeadingZeros(prevNum)
    }
    
    /**
     * Get bit range from start to end position
     */
    fun getBitRange(num: Int, start: Int, end: Int): Int {
        val mask = (1 shl (end - start + 1)) - 1
        return (num shr start) and mask
    }
    
    /**
     * Set bit range with given value
     */
    fun setBitRange(num: Int, start: Int, end: Int, value: Int): Int {
        val mask = (1 shl (end - start + 1)) - 1
        val cleared = num and (mask shl start).inv()
        return cleared or ((value and mask) shl start)
    }
    
    /**
     * Clear bit range
     */
    fun clearBitRange(num: Int, start: Int, end: Int): Int {
        val mask = (1 shl (end - start + 1)) - 1
        return num and (mask shl start).inv()
    }
    
    /**
     * Toggle bit range
     */
    fun toggleBitRange(num: Int, start: Int, end: Int): Int {
        val mask = (1 shl (end - start + 1)) - 1
        return num xor (mask shl start)
    }
    
    /**
     * Left rotate by given positions
     */
    fun leftRotate(num: Int, positions: Int): Int {
        val n = positions and 31 // Handle positions > 31
        return (num shl n) or (num ushr (32 - n))
    }
    
    /**
     * Right rotate by given positions
     */
    fun rightRotate(num: Int, positions: Int): Int {
        val n = positions and 31 // Handle positions > 31
        return (num ushr n) or (num shl (32 - n))
    }
    
    /**
     * Left rotate for Long
     */
    fun leftRotateLong(num: Long, positions: Int): Long {
        val n = positions and 63 // Handle positions > 63
        return (num shl n) or (num ushr (64 - n))
    }
    
    /**
     * Right rotate for Long
     */
    fun rightRotateLong(num: Long, positions: Int): Long {
        val n = positions and 63 // Handle positions > 63
        return (num ushr n) or (num shl (64 - n))
    }
    
    /**
     * Swap bits at given positions
     */
    fun swapBits(num: Int, pos1: Int, pos2: Int): Int {
        val bit1 = (num shr pos1) and 1
        val bit2 = (num shr pos2) and 1
        var result = num
        if (bit1 != bit2) {
            result = result xor (1 shl pos1)
            result = result xor (1 shl pos2)
        }
        return result
    }
    
    /**
     * Swap nibbles (4-bit groups)
     */
    fun swapNibbles(num: Int): Int {
        return ((num and 0x0F0F0F0F.toInt()) shl 4) or ((num and 0xF0F0F0F0.toInt()) shr 4)
    }
    
    /**
     * Swap bytes (little-endian to big-endian)
     */
    fun swapBytes(num: Int): Int {
        return ((num and 0xFF) shl 24) or
               (((num shr 8) and 0xFF) shl 16) or
               (((num shr 16) and 0xFF) shl 8) or
               ((num shr 24) and 0xFF)
    }
    
    /**
     * Create mask with given number of set bits
     */
    fun createMask(bits: Int): Int {
        return (1 shl bits) - 1
    }
    
    /**
     * Create mask for given range
     */
    fun createMaskRange(start: Int, end: Int): Int {
        val length = end - start + 1
        return ((1 shl length) - 1) shl start
    }
    
    /**
     * Create complement mask
     */
    fun createMaskComplement(bits: Int): Int {
        return createMask(bits).inv()
    }
    
    /**
     * Get minimum without branching
     */
    fun getMinWithoutBranching(a: Int, b: Int): Int {
        return b xor ((a xor b) and -(if (a < b) 1 else 0))
    }
    
    /**
     * Get maximum without branching
     */
    fun getMaxWithoutBranching(a: Int, b: Int): Int {
        return a xor ((a xor b) and -(if (a < b) 1 else 0))
    }
    
    /**
     * Get sign without branching
     */
    fun getSignWithoutBranching(num: Int): Int {
        return (num shr 31) or (-num ushr 31)
    }
    
    /**
     * Add without plus operator
     */
    fun addWithoutPlus(a: Int, b: Int): Int {
        var sum = a
        var carry = b
        while (carry != 0) {
            val temp = sum
            sum = sum xor carry
            carry = (temp and carry) shl 1
        }
        return sum
    }
    
    /**
     * Subtract without minus operator
     */
    fun subtractWithoutMinus(a: Int, b: Int): Int {
        return addWithoutPlus(a, addWithoutPlus(b.inv(), 1))
    }
    
    /**
     * Multiply without star operator (Russian peasant method)
     */
    fun multiplyWithoutStar(a: Int, b: Int): Int {
        var result = 0
        var multiplicand = a
        var multiplier = b
        while (multiplier > 0) {
            if ((multiplier and 1) == 1) {
                result = addWithoutPlus(result, multiplicand)
            }
            multiplicand = multiplicand shl 1
            multiplier = multiplier shr 1
        }
        return result
    }
    
    /**
     * Divide without slash operator
     */
    fun divideWithoutSlash(a: Int, b: Int): Int {
        if (b == 0) throw ArithmeticException("Division by zero")
        var dividend = a
        var divisor = b
        var quotient = 0
        while (dividend >= divisor) {
            dividend = subtractWithoutMinus(dividend, divisor)
            quotient = addWithoutPlus(quotient, 1)
        }
        return quotient
    }
    
    /**
     * Logical AND
     */
    fun logicalAnd(a: Int, b: Int): Int {
        return a and b
    }
    
    /**
     * Logical OR
     */
    fun logicalOr(a: Int, b: Int): Int {
        return a or b
    }
    
    /**
     * Logical XOR
     */
    fun logicalXor(a: Int, b: Int): Int {
        return a xor b
    }
    
    /**
     * Logical NOT
     */
    fun logicalNot(a: Int): Int {
        return a.inv()
    }
    
    /**
     * Conditionally set bit
     */
    fun conditionalSet(num: Int, position: Int, condition: Boolean): Int {
        return if (condition) num or (1 shl position) else num
    }
    
    /**
     * Conditionally clear bit
     */
    fun conditionalClear(num: Int, position: Int, condition: Boolean): Int {
        return if (condition) num and (1 shl position).inv() else num
    }
    
    /**
     * Conditionally toggle bit
     */
    fun conditionalToggle(num: Int, position: Int, condition: Boolean): Int {
        return if (condition) num xor (1 shl position) else num
    }
    
    /**
     * Set multiple bits
     */
    fun setMultipleBits(num: Int, positions: IntArray): Int {
        var result = num
        for (position in positions) {
            result = result or (1 shl position)
        }
        return result
    }
    
    /**
     * Clear multiple bits
     */
    fun clearMultipleBits(num: Int, positions: IntArray): Int {
        var result = num
        for (position in positions) {
            result = result and (1 shl position).inv()
        }
        return result
    }
    
    /**
     * Toggle multiple bits
     */
    fun toggleMultipleBits(num: Int, positions: IntArray): Int {
        var result = num
        for (position in positions) {
            result = result xor (1 shl position)
        }
        return result
    }
    
    /**
     * Create bit field
     */
    fun createBitField(value: Int, start: Int, length: Int): Int {
        val mask = (1 shl length) - 1
        return (value and mask) shl start
    }
    
    /**
     * Extract bit field
     */
    fun extractBitField(num: Int, start: Int, length: Int): Int {
        val mask = (1 shl length) - 1
        return (num shr start) and mask
    }
    
    /**
     * Insert bit field
     */
    fun insertBitField(num: Int, value: Int, start: Int, length: Int): Int {
        val mask = (1 shl length) - 1
        val cleared = num and (mask shl start).inv()
        return cleared or ((value and mask) shl start)
    }
    
    /**
     * Create flags from positions
     */
    fun createFlags(positions: IntArray): Int {
        var flags = 0
        for (position in positions) {
            flags = flags or (1 shl position)
        }
        return flags
    }
    
    /**
     * Check if all flags are set
     */
    fun hasAllFlags(num: Int, flags: Int): Boolean {
        return (num and flags) == flags
    }
    
    /**
     * Check if any flags are set
     */
    fun hasAnyFlags(num: Int, flags: Int): Boolean {
        return (num and flags) != 0
    }
    
    /**
     * Add flags
     */
    fun addFlags(num: Int, flags: Int): Int {
        return num or flags
    }
    
    /**
     * Remove flags
     */
    fun removeFlags(num: Int, flags: Int): Int {
        return num and flags.inv()
    }
    
    /**
     * Toggle flags
     */
    fun toggleFlags(num: Int, flags: Int): Int {
        return num xor flags
    }
}
