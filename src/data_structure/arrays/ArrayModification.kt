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
    
    /**
     * Algorithmic Problems - Insert/Remove Operations
     * Advanced insertion and removal algorithms
     */
    fun algorithmicInsertRemove() {
        // Create array within function - standalone
        val arr = intArrayOf(1, 2, 3, 4, 5)
        
        // === INSERT ELEMENT AT INDEX ===
        val index = 2; val value = 10
        val inserted = arr.take(index) + value + arr.drop(index) // [1, 2, 10, 3, 4, 5]
        
        // === REMOVE ELEMENT BY VALUE ===
        val removeValue = 3
        val removed = arr.filter { it != removeValue }       // [1, 2, 4, 5]
        
        // === REMOVE ELEMENT AT INDEX ===
        val removeIndex = 2
        val removedAtIndex = arr.take(removeIndex) + arr.drop(removeIndex + 1) // [1, 2, 4, 5]
        
        // === REPLACE ELEMENT ===
        val oldValue = 2; val newValue = 20
        val replaced = arr.map { if (it == oldValue) newValue else it } // [1, 20, 3, 4, 5]
    }
    
    /**
     * Algorithmic Problems - Array Shifting
     * Advanced shifting algorithms with modular arithmetic
     */
    fun algorithmicShifting() {
        // Create array within function - standalone
        val arr = intArrayOf(1, 2, 3, 4, 5)
        val n = arr.size
        
        // === SHIFT LEFT WITH MODULAR ARITHMETIC ===
        val k = 2
        val shiftLeft = IntArray(n) { i ->
            val newIndex = (i - k + n) % n
            arr[newIndex]
        } // [3, 4, 5, 1, 2]
        
        // === SHIFT RIGHT WITH MODULAR ARITHMETIC ===
        val shiftRight = IntArray(n) { i ->
            val newIndex = (i + k) % n
            arr[newIndex]
        } // [4, 5, 1, 2, 3]
        
        // === CIRCULAR SHIFT (HANDLES NEGATIVE K) ===
        val circularShift = { k: Int ->
            val effectiveShift = ((k % n) + n) % n
            IntArray(n) { i ->
                val newIndex = (i - effectiveShift + n) % n
                arr[newIndex]
            }
        }
        val circularLeft = circularShift(2)                  // [3, 4, 5, 1, 2]
        val circularRight = circularShift(-2)                // [4, 5, 1, 2, 3]
    }
    
    /**
     * Algorithmic Problems - Element Movement
     * Moving elements to specific positions
     */
    fun algorithmicMovement() {
        // Create array within function - standalone
        val arr = intArrayOf(1, 2, 3, 4, 5)
        
        // === MOVE ELEMENT TO FRONT ===
        val moveIndex = 2
        val element = arr[moveIndex]
        val movedToFront = intArrayOf(element) + arr.take(moveIndex) + arr.drop(moveIndex + 1) // [3, 1, 2, 4, 5]
        
        // === MOVE ELEMENT TO END ===
        val movedToEnd = arr.take(moveIndex) + arr.drop(moveIndex + 1) + intArrayOf(element) // [1, 2, 4, 5, 3]
        
        // === SWAP ADJACENT ELEMENTS ===
        val swapIndex = 1
        val swapped = arr.copyOf().apply {
            this[swapIndex] = this[swapIndex + 1].also { this[swapIndex + 1] = this[swapIndex] }
        } // [1, 3, 2, 4, 5]
    }
    
    /**
     * Algorithmic Problems - Array Shuffling
     * Random shuffling algorithms
     */
    fun algorithmicShuffling() {
        // Create array within function - standalone
        val arr = intArrayOf(1, 2, 3, 4, 5)
        
        // === FISHER-YATES SHUFFLE ===
        val shuffled = arr.copyOf()
        for (i in shuffled.size - 1 downTo 1) {
            val j = (0..i).random()
            shuffled[i] = shuffled[j].also { shuffled[j] = shuffled[i] }
        }
        // Result: Random permutation of [1, 2, 3, 4, 5]
        
        // === SHUFFLE WITH BUILT-IN ===
        val shuffledBuiltin = arr.toList().shuffled().toIntArray() // Random permutation
        
        // === PARTIAL SHUFFLE (FIRST K ELEMENTS) ===
        val k = 3
        val partialShuffled = arr.copyOf()
        for (i in 0 until minOf(k, partialShuffled.size - 1)) {
            val j = (i until partialShuffled.size).random()
            partialShuffled[i] = partialShuffled[j].also { partialShuffled[j] = partialShuffled[i] }
        }
        // First k elements are shuffled, rest remain in order
    }
    
    /**
     * Algorithmic Problems - Range Operations
     * Operations on specific ranges of arrays
     */
    fun algorithmicRangeOperations() {
        // Create array within function - standalone
        val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // === REVERSE RANGE ===
        val start = 2; val end = 6
        val rangeReversed = arr.copyOf().apply {
            var left = start; var right = end
            while (left < right) {
                this[left] = this[right].also { this[right] = this[left] }
                left++; right--
            }
        } // [1, 2, 7, 6, 5, 4, 3, 8, 9, 10]
        
        // === FILL RANGE ===
        val fillValue = 99
        val rangeFilled = arr.copyOf().apply {
            for (i in start..end) this[i] = fillValue
        } // [1, 2, 99, 99, 99, 99, 99, 8, 9, 10]
        
        // === ROTATE RANGE ===
        val rotateK = 2
        val rangeRotated = arr.copyOf().apply {
            // Reverse entire range
            var left = start; var right = end
            while (left < right) {
                this[left] = this[right].also { this[right] = this[left] }
                left++; right--
            }
            // Reverse first k elements of range
            left = start; right = start + rotateK - 1
            while (left < right) {
                this[left] = this[right].also { this[right] = this[left] }
                left++; right--
            }
            // Reverse remaining elements of range
            left = start + rotateK; right = end
            while (left < right) {
                this[left] = this[right].also { this[right] = this[left] }
                left++; right--
            }
        } // [1, 2, 5, 6, 7, 3, 4, 8, 9, 10]
    }
} 