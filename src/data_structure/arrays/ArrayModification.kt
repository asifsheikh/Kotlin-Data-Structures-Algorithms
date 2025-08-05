package data_structure.arrays

/**
 * ARRAY MODIFICATION - Quick Reference
 * All Kotlin array modification methods in one place
 */

object ArrayModification {
    
    /**
     * Basic Element Modification
     * Standard array modification operations
     */
    fun basicModification() {
        // Create array within function - standalone
        val arr = intArrayOf(1, 2, 3, 4, 5)
        
        // === SET ELEMENTS ===
        arr[0] = 10                                          // [10, 2, 3, 4, 5]
        arr.set(1, 20)                                       // [10, 20, 3, 4, 5]
        arr.fill(0)                                          // [0, 0, 0, 0, 0]
        arr.fill(1, 1, 4)                                    // [0, 1, 1, 1, 0]
        
        // === SWAP ELEMENTS ===
        arr[0] = arr[1].also { arr[1] = arr[0] }            // Swap arr[0] and arr[1]
        val temp = arr[2]; arr[2] = arr[3]; arr[3] = temp   // Traditional swap
        arr[4] = arr[4] xor arr[0].also { arr[0] = arr[4] xor arr[0] }.also { arr[4] = arr[4] xor arr[0] } // XOR swap
    }
    
    /**
     * Array Reversal
     * Different ways to reverse arrays
     */
    fun arrayReversal() {
        // Create array within function - standalone
        val arr = intArrayOf(1, 2, 3, 4, 5)
        
        // === FULL REVERSE ===
        arr.reverse()                                        // [5, 4, 3, 2, 1]
        
        // === RANGE REVERSE ===
        arr.reverse(1, 4)                                    // [5, 2, 3, 4, 1]
        
        // === MANUAL REVERSE ===
        var left = 0; var right = arr.size - 1
        while (left < right) {
            arr[left] = arr[right].also { arr[right] = arr[left] }
            left++; right--
        }
        
        // === REVERSE BY GROUPS ===
        val groupSize = 2
        for (i in 0 until arr.size step groupSize) {
            val end = minOf(i + groupSize - 1, arr.size - 1)
            arr.reverse(i, end + 1)
        }
    }
    
    /**
     * Array Rotation
     * Rotating array elements
     */
    fun arrayRotation() {
        // Create array within function - standalone
        val arr = intArrayOf(1, 2, 3, 4, 5)
        
        // === LEFT ROTATION ===
        val k = 2
        arr.reverse(0, k)                                    // Reverse first k elements
        arr.reverse(k, arr.size)                             // Reverse remaining elements
        arr.reverse()                                        // Reverse entire array
        
        // === RIGHT ROTATION ===
        arr.reverse()                                        // Reverse entire array
        arr.reverse(0, k)                                    // Reverse first k elements
        arr.reverse(k, arr.size)                             // Reverse remaining elements
        
        // === SHIFT OPERATIONS ===
        val shiftedLeft = arr.drop(k) + arr.take(k)          // Left shift by k
        val shiftedRight = arr.takeLast(k) + arr.dropLast(k) // Right shift by k
    }
    
    /**
     * Array Filling
     * Different ways to fill arrays
     */
    fun arrayFilling() {
        // Create array within function - standalone
        val arr = IntArray(5)
        
        // === BASIC FILL ===
        arr.fill(42)                                         // [42, 42, 42, 42, 42]
        arr.fill(0, 1, 4)                                    // [42, 0, 0, 0, 42]
        
        // === FILL WITH FUNCTION ===
        for (i in arr.indices) arr[i] = i * 2                // [0, 2, 4, 6, 8]
        
        // === FILL WITH PATTERN ===
        for (i in arr.indices) arr[i] = if (i % 2 == 0) 0 else 1 // [0, 1, 0, 1, 0]
        
        // === FILL WITH SEQUENCE ===
        var value = 1
        for (i in arr.indices) arr[i] = value++              // [1, 2, 3, 4, 5]
    }
    
    /**
     * Array Copying
     * Different ways to copy arrays
     */
    fun arrayCopying() {
        // Create array within function - standalone
        val original = intArrayOf(1, 2, 3, 4, 5)
        
        // === BASIC COPY ===
        val copy1 = original.copyOf()                        // [1, 2, 3, 4, 5]
        val copy2 = original.clone()                         // [1, 2, 3, 4, 5]
        
        // === COPY WITH SIZE ===
        val largerCopy = original.copyOf(10)                 // [1, 2, 3, 4, 5, 0, 0, 0, 0, 0]
        val smallerCopy = original.copyOf(3)                 // [1, 2, 3]
        
        // === COPY RANGE ===
        val rangeCopy = original.copyOfRange(1, 4)           // [2, 3, 4]
        
        // === COPY INTO ===
        val target = IntArray(5)
        original.copyInto(target)                            // target = [1, 2, 3, 4, 5]
        original.copyInto(target, 1, 0, 3)                  // target = [0, 1, 2, 3, 0]
    }
    
    /**
     * 2D Array Modification
     * Modifying 2D arrays
     */
    fun twoDimensionalModification() {
        // Create matrix within function - standalone
        val matrix = Array(3) { IntArray(3) { 0 } }
        
        // === SET ELEMENTS ===
        matrix[0][0] = 1                                     // Set specific element
        matrix[1].fill(5)                                    // Fill entire row
        matrix.forEach { row -> row.fill(3) }                // Fill entire matrix
        
        // === ROW/COLUMN OPERATIONS ===
        matrix[0] = intArrayOf(1, 2, 3)                     // Replace entire row
        for (i in matrix.indices) matrix[i][1] = 10          // Set entire column
        
        // === MATRIX REVERSE ===
        matrix.reverse()                                     // Reverse rows
        matrix.forEach { it.reverse() }                      // Reverse each row
    }
} 