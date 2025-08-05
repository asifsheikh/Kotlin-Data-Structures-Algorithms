package data_structure.arrays

/**
 * ARRAY ACCESS - Quick Reference
 * All Kotlin array access methods in one place
 */

object ArrayAccess {
    
    /**
     * Basic Array Access
     * Standard array access operations
     */
    fun basicAccess() {
        // Create array within function - standalone
        val arr = intArrayOf(1, 2, 3, 4, 5)
        
        // === DIRECT ACCESS ===
        val firstElement = arr[0]                           // 1
        val lastElement = arr[arr.size - 1]                 // 5
        val elementAtIndex = arr[2]                         // 3
        
        // === SAFE ACCESS ===
        val safeElement = arr.getOrNull(10)                 // null
        val elementWithDefault = arr.getOrElse(10) { -1 }   // -1
        val firstOrNull = arr.firstOrNull()                 // 1
        val lastOrNull = arr.lastOrNull()                   // 5
        
        // === BOUNDS CHECKING ===
        val isValidIndex = 2 in arr.indices                 // true
        val isOutOfBounds = 10 in arr.indices               // false
    }
    
    /**
     * Range Access
     * Accessing array elements in ranges
     */
    fun rangeAccess() {
        // Create array within function - standalone
        val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // === SLICE OPERATIONS ===
        val slice1to5 = arr.slice(1..5)                     // [2, 3, 4, 5, 6]
        val sliceUntil5 = arr.slice(0 until 5)              // [1, 2, 3, 4, 5]
        val sliceFrom3 = arr.slice(3..arr.lastIndex)        // [4, 5, 6, 7, 8, 9, 10]
        val sliceWithStep = arr.slice(0..arr.lastIndex step 2) // [1, 3, 5, 7, 9]
        
        // === TAKE/DROP OPERATIONS ===
        val first3 = arr.take(3)                            // [1, 2, 3]
        val last3 = arr.takeLast(3)                         // [8, 9, 10]
        val dropFirst3 = arr.drop(3)                        // [4, 5, 6, 7, 8, 9, 10]
        val dropLast3 = arr.dropLast(3)                     // [1, 2, 3, 4, 5, 6, 7]
        
        // === RANGE WITH CONDITIONS ===
        val evenIndices = arr.filterIndexed { index, _ -> index % 2 == 0 } // [1, 3, 5, 7, 9]
        val oddIndices = arr.filterIndexed { index, _ -> index % 2 == 1 }   // [2, 4, 6, 8, 10]
    }
    
    /**
     * Conditional Access
     * Accessing elements based on conditions
     */
    fun conditionalAccess() {
        // Create array within function - standalone
        val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // === FILTER OPERATIONS ===
        val evenNumbers = arr.filter { it % 2 == 0 }        // [2, 4, 6, 8, 10]
        val oddNumbers = arr.filter { it % 2 == 1 }         // [1, 3, 5, 7, 9]
        val greaterThan5 = arr.filter { it > 5 }            // [6, 7, 8, 9, 10]
        val between3and7 = arr.filter { it in 3..7 }        // [3, 4, 5, 6, 7]
        
        // === FIND OPERATIONS ===
        val firstEven = arr.find { it % 2 == 0 }            // 2
        val lastEven = arr.findLast { it % 2 == 0 }         // 10
        val firstGreaterThan5 = arr.find { it > 5 }         // 6
        val indexOfFirstEven = arr.indexOfFirst { it % 2 == 0 } // 1
        val indexOfLastEven = arr.indexOfLast { it % 2 == 0 }   // 9
    }
    
    /**
     * Multiple Element Access
     * Accessing multiple elements at once
     */
    fun multipleElementAccess() {
        // Create array within function - standalone
        val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // === MULTIPLE INDICES ===
        val indices = intArrayOf(0, 2, 4, 6, 8)
        val elementsAtIndices = indices.map { arr[it] }     // [1, 3, 5, 7, 9]
        
        // === AROUND INDEX ===
        val centerIndex = 4
        val aroundCenter = listOfNotNull(
            if (centerIndex > 0) arr[centerIndex - 1] else null,
            arr[centerIndex],
            if (centerIndex < arr.lastIndex) arr[centerIndex + 1] else null
        ) // [4, 5, 6]
        
        // === WINDOW ACCESS ===
        val windowSize = 3
        val windows = (0..arr.size - windowSize).map { start ->
            arr.slice(start until start + windowSize)
        } // [[1,2,3], [2,3,4], [3,4,5], ...]
    }
    
    /**
     * 2D Array Access
     * Accessing elements in 2D arrays
     */
    fun twoDimensionalAccess() {
        // Create matrix within function - standalone
        val matrix = arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        )
        
        // === DIRECT ACCESS ===
        val element = matrix[1][2]                          // 6
        val row = matrix[1]                                 // [4, 5, 6]
        val column = matrix.map { it[1] }                   // [2, 5, 8]
        
        // === SAFE ACCESS ===
        val safeElement = matrix.getOrNull(1)?.getOrNull(2) // 6
        val outOfBounds = matrix.getOrNull(5)?.getOrNull(5) // null
        
        // === ROW/COLUMN ACCESS ===
        val firstRow = matrix.first()                       // [1, 2, 3]
        val lastRow = matrix.last()                         // [7, 8, 9]
        val firstColumn = matrix.map { it.first() }         // [1, 4, 7]
        val lastColumn = matrix.map { it.last() }           // [3, 6, 9]
    }
} 