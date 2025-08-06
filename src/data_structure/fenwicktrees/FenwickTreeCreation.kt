package data_structure.fenwicktrees

/**
 * FENWICK TREE CREATION - Quick Reference
 * All Kotlin Fenwick Tree creation methods in one place
 */

object FenwickTreeCreation {
    
    /**
     * Basic Fenwick Tree class for efficient range queries and point updates
     */
    class FenwickTree(private val size: Int) {
        private val tree = LongArray(size + 1) { 0L }
        
        /**
         * Update value at index i by adding delta
         */
        fun update(i: Int, delta: Long) {
            var index = i + 1 // 1-indexed
            while (index <= size) {
                tree[index] += delta
                index += index and -index // Add least significant bit
            }
        }
        
        /**
         * Get prefix sum from index 0 to i (inclusive)
         */
        fun query(i: Int): Long {
            var sum = 0L
            var index = i + 1 // 1-indexed
            while (index > 0) {
                sum += tree[index]
                index -= index and -index // Remove least significant bit
            }
            return sum
        }
        
        /**
         * Get range sum from index left to right (inclusive)
         */
        fun rangeQuery(left: Int, right: Int): Long {
            return query(right) - query(left - 1)
        }
        
        /**
         * Get current size of the tree
         */
        fun getSize(): Int = size
    }
    
    /**
     * Enhanced Fenwick Tree with additional features
     */
    class EnhancedFenwickTree(private val size: Int) {
        private val tree = LongArray(size + 1) { 0L }
        private val original = LongArray(size) { 0L }
        
        /**
         * Set value at index i
         */
        fun set(i: Int, value: Long) {
            val delta = value - original[i]
            update(i, delta)
        }
        
        /**
         * Update value at index i by adding delta
         */
        fun update(i: Int, delta: Long) {
            original[i] += delta
            var index = i + 1
            while (index <= size) {
                tree[index] += delta
                index += index and -index
            }
        }
        
        /**
         * Get prefix sum from index 0 to i (inclusive)
         */
        fun query(i: Int): Long {
            var sum = 0L
            var index = i + 1
            while (index > 0) {
                sum += tree[index]
                index -= index and -index
            }
            return sum
        }
        
        /**
         * Get range sum from index left to right (inclusive)
         */
        fun rangeQuery(left: Int, right: Int): Long {
            return query(right) - query(left - 1)
        }
        
        /**
         * Get value at specific index
         */
        fun get(i: Int): Long = original[i]
        
        /**
         * Get current size of the tree
         */
        fun getSize(): Int = size
    }
    
    /**
     * All Fenwick Tree Creation Methods
     * Complete reference for creating Fenwick Trees in Kotlin
     */
    fun allFenwickTreeCreationMethods() {
        // === BASIC FENWICK TREE CREATION ===
        val emptyFenwick = FenwickTree(10)                        // Empty Fenwick tree with size 10
        val largeFenwick = FenwickTree(1000000)                   // Large Fenwick tree for big datasets
        val smallFenwick = FenwickTree(1)                         // Smallest possible Fenwick tree
        
        // === ENHANCED FENWICK TREE CREATION ===
        val enhancedFenwick = EnhancedFenwickTree(50)             // Enhanced Fenwick tree with set operations
        val enhancedLarge = EnhancedFenwickTree(100000)           // Large enhanced Fenwick tree
        
        // === FROM ARRAYS ===
        val array = longArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val fenwickFromArray = createFromArray(array)             // Fenwick tree from array
        
        // === FROM LISTS ===
        val list = listOf(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L)
        val fenwickFromList = createFromList(list)                // Fenwick tree from list
        
        // === FROM SEQUENCES ===
        val sequence = (1..10).asSequence().map { it.toLong() }
        val fenwickFromSequence = createFromSequence(sequence)    // Fenwick tree from sequence
        
        // === FROM RANGES ===
        val fenwickFromRange = createFromRange(1..10)             // Fenwick tree from range
        val fenwickFromRangeStep = createFromRange(1..20 step 2)  // Fenwick tree from range with step
        
        // === FROM FUNCTIONS ===
        val fenwickFromFunction = createFromFunction(10) { it * it.toLong() } // Fenwick tree from function
        val fenwickFromIndexFunction = createFromIndexFunction(10) { index -> index * 2L } // Fenwick tree from index function
        
        // === WITH INITIAL VALUES ===
        val fenwickWithZeros = createWithInitialValue(10, 0L)     // Fenwick tree filled with zeros
        val fenwickWithOnes = createWithInitialValue(10, 1L)      // Fenwick tree filled with ones
        val fenwickWithValue = createWithInitialValue(10, 42L)    // Fenwick tree filled with specific value
        
        // === WITH RANDOM VALUES ===
        val fenwickWithRandom = createWithRandomValues(10, 1L..100L) // Fenwick tree with random values
        val fenwickWithRandomInt = createWithRandomValues(10, 1..100) // Fenwick tree with random int values
        
        // === SPECIAL PATTERNS ===
        val fenwickAlternating = createAlternating(10)            // Fenwick tree with alternating 1, -1, 1, -1...
        val fenwickFibonacci = createFibonacci(10)                // Fenwick tree with Fibonacci sequence
        val fenwickPowers = createPowers(10, 2)                  // Fenwick tree with powers of 2
        val fenwickFactorial = createFactorial(10)                // Fenwick tree with factorial sequence
        
        // === 2D FENWICK TREE ===
        val fenwick2D = create2DFenwickTree(5, 5)                // 2D Fenwick tree 5x5
        val fenwick2DLarge = create2DFenwickTree(100, 100)       // Large 2D Fenwick tree
        
        // === FROM MATRIX ===
        val matrix = Array(3) { row -> LongArray(3) { col -> (row + col).toLong() } }
        val fenwickFromMatrix = createFromMatrix(matrix)         // Fenwick tree from 2D matrix
    }
    
    /**
     * Creates Fenwick tree from array
     */
    private fun createFromArray(arr: LongArray): FenwickTree {
        val fenwick = FenwickTree(arr.size)
        for (i in arr.indices) {
            fenwick.update(i, arr[i])
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree from list
     */
    private fun createFromList(list: List<Long>): FenwickTree {
        val fenwick = FenwickTree(list.size)
        for (i in list.indices) {
            fenwick.update(i, list[i])
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree from sequence
     */
    private fun createFromSequence(sequence: Sequence<Long>): FenwickTree {
        val list = sequence.toList()
        return createFromList(list)
    }
    
    /**
     * Creates Fenwick tree from range
     */
    private fun createFromRange(range: IntRange): FenwickTree {
        val fenwick = FenwickTree(range.count())
        for ((index, value) in range.withIndex()) {
            fenwick.update(index, value.toLong())
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree from function
     */
    private fun createFromFunction(size: Int, function: (Int) -> Long): FenwickTree {
        val fenwick = FenwickTree(size)
        for (i in 0 until size) {
            fenwick.update(i, function(i))
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree from index function
     */
    private fun createFromIndexFunction(size: Int, function: (Int) -> Long): FenwickTree {
        return createFromFunction(size, function)
    }
    
    /**
     * Creates Fenwick tree with initial value
     */
    private fun createWithInitialValue(size: Int, value: Long): FenwickTree {
        val fenwick = FenwickTree(size)
        for (i in 0 until size) {
            fenwick.update(i, value)
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree with random values
     */
    private fun createWithRandomValues(size: Int, range: LongRange): FenwickTree {
        val fenwick = FenwickTree(size)
        for (i in 0 until size) {
            fenwick.update(i, range.random())
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree with random int values
     */
    private fun createWithRandomValues(size: Int, range: IntRange): FenwickTree {
        val fenwick = FenwickTree(size)
        for (i in 0 until size) {
            fenwick.update(i, range.random().toLong())
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree with alternating values
     */
    private fun createAlternating(size: Int): FenwickTree {
        val fenwick = FenwickTree(size)
        for (i in 0 until size) {
            fenwick.update(i, if (i % 2 == 0) 1L else -1L)
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree with Fibonacci sequence
     */
    private fun createFibonacci(size: Int): FenwickTree {
        val fenwick = FenwickTree(size)
        if (size > 0) fenwick.update(0, 1L)
        if (size > 1) fenwick.update(1, 1L)
        for (i in 2 until size) {
            val fib = fenwick.get(i - 1) + fenwick.get(i - 2)
            fenwick.update(i, fib)
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree with powers
     */
    private fun createPowers(size: Int, base: Int): FenwickTree {
        val fenwick = FenwickTree(size)
        for (i in 0 until size) {
            fenwick.update(i, base.toLong().pow(i))
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree with factorial sequence
     */
    private fun createFactorial(size: Int): FenwickTree {
        val fenwick = FenwickTree(size)
        var factorial = 1L
        for (i in 0 until size) {
            if (i > 0) factorial *= i
            fenwick.update(i, factorial)
        }
        return fenwick
    }
    
    /**
     * Creates 2D Fenwick tree
     */
    private fun create2DFenwickTree(rows: Int, cols: Int): Array<LongArray> {
        return Array(rows + 1) { LongArray(cols + 1) { 0L } }
    }
    
    /**
     * Creates Fenwick tree from 2D matrix
     */
    private fun createFromMatrix(matrix: Array<LongArray>): Array<LongArray> {
        val rows = matrix.size
        val cols = matrix[0].size
        val fenwick = Array(rows + 1) { LongArray(cols + 1) { 0L } }
        
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                update2D(fenwick, i, j, matrix[i][j])
            }
        }
        
        return fenwick
    }
    
    /**
     * Update 2D Fenwick tree
     */
    private fun update2D(fenwick: Array<LongArray>, row: Int, col: Int, delta: Long) {
        var i = row + 1
        while (i < fenwick.size) {
            var j = col + 1
            while (j < fenwick[i].size) {
                fenwick[i][j] += delta
                j += j and -j
            }
            i += i and -i
        }
    }
    
    /**
     * Extension function for Long power
     */
    private fun Long.pow(exponent: Int): Long {
        var result = 1L
        repeat(exponent) { result *= this }
        return result
    }
} 