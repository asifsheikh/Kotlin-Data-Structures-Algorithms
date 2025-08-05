package data_structure.arrays

/**
 * ARRAY TRANSFORMATION - Quick Reference
 * All Kotlin array transformation methods in one place
 */

object ArrayTransformation {
    
    /**
     * Array Mapping
     * Transform each element using functions
     */
    fun arrayMapping() {
        // Create array within function - standalone
        val arr = intArrayOf(1, 2, 3, 4, 5)
        
        // === BASIC MAP ===
        val doubled = arr.map { it * 2 }                     // [2, 4, 6, 8, 10]
        val squared = arr.map { it * it }                    // [1, 4, 9, 16, 25]
        val toString = arr.map { it.toString() }             // ["1", "2", "3", "4", "5"]
        
        // === MAP WITH INDEX ===
        val withIndex = arr.mapIndexed { index, value -> "$index:$value" } // ["0:1", "1:2", "2:3", "3:4", "4:5"]
        val indexPlusValue = arr.mapIndexed { index, value -> index + value } // [1, 3, 5, 7, 9]
        
        // === MAP TO DIFFERENT TYPE ===
        val toBoolean = arr.map { it > 3 }                   // [false, false, false, true, true]
        val toChar = arr.map { ('a' + it - 1).toChar() }     // ['a', 'b', 'c', 'd', 'e']
    }
    
    /**
     * Array Filtering
     * Select elements based on conditions
     */
    fun arrayFiltering() {
        // Create array within function - standalone
        val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // === BASIC FILTER ===
        val evenNumbers = arr.filter { it % 2 == 0 }         // [2, 4, 6, 8, 10]
        val oddNumbers = arr.filter { it % 2 == 1 }          // [1, 3, 5, 7, 9]
        val greaterThan5 = arr.filter { it > 5 }             // [6, 7, 8, 9, 10]
        val between3and7 = arr.filter { it in 3..7 }         // [3, 4, 5, 6, 7]
        
        // === FILTER WITH INDEX ===
        val evenIndices = arr.filterIndexed { index, _ -> index % 2 == 0 } // [1, 3, 5, 7, 9]
        val oddIndices = arr.filterIndexed { index, _ -> index % 2 == 1 }   // [2, 4, 6, 8, 10]
        
        // === FILTER NOT ===
        val notEven = arr.filterNot { it % 2 == 0 }          // [1, 3, 5, 7, 9]
        val notGreaterThan5 = arr.filterNot { it > 5 }       // [1, 2, 3, 4, 5]
    }
    
    /**
     * Array Flattening
     * Convert nested arrays to flat arrays
     */
    fun arrayFlattening() {
        // Create matrix within function - standalone
        val matrix = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        )
        
        // === FLATTEN 2D ARRAY ===
        val flattened = matrix.flatMap { it.toList() }       // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        val flattenedToInt = matrix.flatMap { it.toList() }.toIntArray() // IntArray
        
        // === FLATTEN WITH TRANSFORMATION ===
        val flattenedDoubled = matrix.flatMap { row -> row.map { it * 2 } } // [2, 4, 6, 8, 10, 12, 14, 16, 18]
        
        // === FLATTEN NESTED LISTS ===
        val nestedList = listOf(listOf(1, 2), listOf(3, 4), listOf(5, 6))
        val flatList = nestedList.flatten()                  // [1, 2, 3, 4, 5, 6]
    }
    
    /**
     * Array Reshaping
     * Change array dimensions
     */
    fun arrayReshaping() {
        // Create array within function - standalone
        val arr = intArrayOf(1, 2, 3, 4, 5, 6)
        
        // === 1D TO 2D (ROW-WISE) ===
        val matrix2x3 = Array(2) { row -> IntArray(3) { col -> arr[row * 3 + col] } }
        // [[1, 2, 3], [4, 5, 6]]
        
        // === 1D TO 2D (COLUMN-WISE) ===
        val matrix3x2 = Array(3) { row -> IntArray(2) { col -> arr[row + col * 3] } }
        // [[1, 4], [2, 5], [3, 6]]
        
        // === 2D TO 1D ===
        val matrix = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6))
        val flattened = matrix.flatMap { it.toList() }.toIntArray() // [1, 2, 3, 4, 5, 6]
    }
    
    /**
     * Array Concatenation
     * Combine multiple arrays
     */
    fun arrayConcatenation() {
        // Create arrays within function - standalone
        val arr1 = intArrayOf(1, 2, 3)
        val arr2 = intArrayOf(4, 5, 6)
        val arr3 = intArrayOf(7, 8, 9)
        
        // === CONCATENATE ARRAYS ===
        val combined = arr1 + arr2 + arr3                    // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        val combinedList = arr1.toList() + arr2.toList() + arr3.toList() // List<Int>
        
        // === CONCATENATE WITH PLUS ===
        val result = arr1.plus(arr2).plus(arr3)              // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        
        // === CONCATENATE 2D ARRAYS ===
        val matrix1 = arrayOf(intArrayOf(1, 2), intArrayOf(3, 4))
        val matrix2 = arrayOf(intArrayOf(5, 6), intArrayOf(7, 8))
        val combinedMatrix = matrix1 + matrix2               // [[1, 2], [3, 4], [5, 6], [7, 8]]
    }
    
    /**
     * Array Splitting
     * Divide arrays into parts
     */
    fun arraySplitting() {
        // Create array within function - standalone
        val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // === SPLIT AT INDEX ===
        val (left, right) = arr.splitAt(5)                   // ([1,2,3,4,5], [6,7,8,9,10])
        
        // === SPLIT BY CONDITION ===
        val parts = arr.split { it % 3 == 0 }                // [[1,2], [4,5], [7,8], [10]]
        
        // === CHUNK SPLITTING ===
        val chunks = arr.chunked(3)                          // [[1,2,3], [4,5,6], [7,8,9], [10]]
        val chunksWithTransform = arr.chunked(3) { it.sum() } // [6, 15, 24, 10]
        
        // === WINDOW SPLITTING ===
        val windows = arr.windowed(3)                        // [[1,2,3], [2,3,4], [3,4,5], ...]
        val windowsWithStep = arr.windowed(3, step = 2)      // [[1,2,3], [3,4,5], [5,6,7], ...]
    }
    
    /**
     * Array Copying
     * Create copies with modifications
     */
    fun arrayCopying() {
        // Create array within function - standalone
        val original = intArrayOf(1, 2, 3, 4, 5)
        
        // === BASIC COPY ===
        val copy = original.copyOf()                         // [1, 2, 3, 4, 5]
        val largerCopy = original.copyOf(10)                 // [1, 2, 3, 4, 5, 0, 0, 0, 0, 0]
        val smallerCopy = original.copyOf(3)                 // [1, 2, 3]
        
        // === COPY RANGE ===
        val rangeCopy = original.copyOfRange(1, 4)           // [2, 3, 4]
        
        // === COPY WITH PADDING ===
        val paddedCopy = IntArray(10) { if (it < original.size) original[it] else -1 }
        // [1, 2, 3, 4, 5, -1, -1, -1, -1, -1]
    }
} 