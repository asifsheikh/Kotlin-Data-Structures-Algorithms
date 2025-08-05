package data_structure.arrays

/**
 * ARRAY CREATION - Quick Reference
 * All Kotlin array creation methods in one place
 */

object ArrayCreation {
    
    /**
     * Primitive Array Creation
     * All ways to create primitive arrays in Kotlin
     */
    fun primitiveArrays() {
        // === INT ARRAYS ===
        val emptyIntArray = IntArray(5)                    // [0, 0, 0, 0, 0]
        val intArrayWithValues = intArrayOf(1, 2, 3, 4, 5) // [1, 2, 3, 4, 5]
        val intArrayWithDefault = IntArray(5) { 10 }       // [10, 10, 10, 10, 10]
        val intArrayWithFunction = IntArray(5) { it * 2 }  // [0, 2, 4, 6, 8]
        val intArrayFromRange = (1..5).toList().toIntArray() // [1, 2, 3, 4, 5]
        
        // === OTHER PRIMITIVE ARRAYS ===
        val booleanArray = BooleanArray(3) { true }        // [true, true, true]
        val charArray = CharArray(3) { 'a' + it }          // ['a', 'b', 'c']
        val longArray = LongArray(3) { it.toLong() }       // [0, 1, 2]
        val floatArray = FloatArray(3) { it.toFloat() }    // [0.0, 1.0, 2.0]
        val doubleArray = DoubleArray(3) { it.toDouble() } // [0.0, 1.0, 2.0]
        val byteArray = ByteArray(3) { it.toByte() }       // [0, 1, 2]
        val shortArray = ShortArray(3) { it.toShort() }    // [0, 1, 2]
    }
    
    /**
     * Generic Array Creation
     * All ways to create generic arrays in Kotlin
     */
    fun genericArrays() {
        // === GENERIC ARRAYS ===
        val stringArray = Array(3) { "item$it" }           // ["item0", "item1", "item2"]
        val anyArray = arrayOf(1, "hello", true)           // [1, "hello", true]
        val nullableArray = arrayOfNulls<String>(3)        // [null, null, null]
        val typedArray = Array<String>(3) { "default" }    // ["default", "default", "default"]
        
        // === LIST TO ARRAY ===
        val list = listOf(1, 2, 3, 4, 5)
        val arrayFromList = list.toTypedArray()            // [1, 2, 3, 4, 5]
        val intArrayFromList = list.toIntArray()           // [1, 2, 3, 4, 5]
    }
    
    /**
     * 2D Array Creation
     * All ways to create 2D arrays and matrices
     */
    fun twoDimensionalArrays() {
        // === 2D INT ARRAYS ===
        val matrix2x3 = Array(2) { IntArray(3) }           // [[0, 0, 0], [0, 0, 0]]
        val matrixWithDefault = Array(2) { IntArray(3) { 5 } } // [[5, 5, 5], [5, 5, 5]]
        val matrixWithValues = arrayOf(intArrayOf(1, 2), intArrayOf(3, 4)) // [[1, 2], [3, 4]]
        
        // === 2D GENERIC ARRAYS ===
        val stringMatrix = Array(2) { Array(3) { "x" } }   // [["x", "x", "x"], ["x", "x", "x"]]
        val anyMatrix = arrayOf(arrayOf(1, "a"), arrayOf(2, "b")) // [[1, "a"], [2, "b"]]
        
        // === SPECIAL MATRICES ===
        val identityMatrix = Array(3) { row -> IntArray(3) { col -> if (row == col) 1 else 0 } }
        val spiralMatrix = createSpiralMatrix(3, 3)
    }
    
    /**
     * Special Array Patterns
     * Common array creation patterns
     */
    fun specialPatterns() {
        // === RANGE ARRAYS ===
        val rangeArray = (1..10).toList().toIntArray()     // [1, 2, 3, ..., 10]
        val stepArray = (0..10 step 2).toList().toIntArray() // [0, 2, 4, 6, 8, 10]
        val reverseArray = (10 downTo 1).toList().toIntArray() // [10, 9, 8, ..., 1]
        
        // === RANDOM ARRAYS ===
        val randomArray = IntArray(5) { (1..100).random() }
        val shuffledArray = (1..10).toList().shuffled().toIntArray()
        
        // === REPEATED ARRAYS ===
        val repeatedArray = IntArray(5) { 42 }             // [42, 42, 42, 42, 42]
        val alternatingArray = IntArray(6) { if (it % 2 == 0) 0 else 1 } // [0, 1, 0, 1, 0, 1]
    }
    
    // Helper function for spiral matrix - standalone within this file
    private fun createSpiralMatrix(rows: Int, cols: Int): Array<IntArray> {
        val matrix = Array(rows) { IntArray(cols) }
        var value = 1
        var top = 0; var bottom = rows - 1; var left = 0; var right = cols - 1
        
        while (top <= bottom && left <= right) {
            for (i in left..right) matrix[top][i] = value++
            top++
            for (i in top..bottom) matrix[i][right] = value++
            right--
            if (top <= bottom) {
                for (i in right downTo left) matrix[bottom][i] = value++
                bottom--
            }
            if (left <= right) {
                for (i in bottom downTo top) matrix[i][left] = value++
                left++
            }
        }
        return matrix
    }
} 