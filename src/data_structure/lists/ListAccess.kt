package data_structure.lists

/**
 * LIST ACCESS - Quick Reference
 * All Kotlin list access methods in one place
 */

object ListAccess {
    
    /**
     * Basic List Access
     * Standard list access operations
     */
    fun basicAccess() {
        // Create list within function - standalone
        val list = listOf(1, 2, 3, 4, 5)
        
        // === DIRECT ACCESS ===
        val firstElement = list[0]                            // 1
        val lastElement = list[list.size - 1]                 // 5
        val elementAtIndex = list[2]                          // 3
        
        // === SAFE ACCESS ===
        val safeElement = list.getOrNull(10)                  // null
        val elementWithDefault = list.getOrElse(10) { -1 }    // -1
        val firstOrNull = list.firstOrNull()                  // 1
        val lastOrNull = list.lastOrNull()                    // 5
        
        // === BOUNDS CHECKING ===
        val isValidIndex = 2 in list.indices                  // true
        val isOutOfBounds = 10 in list.indices                // false
    }
    
    /**
     * Range Access
     * Accessing list elements in ranges
     */
    fun rangeAccess() {
        // Create list within function - standalone
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // === SLICE OPERATIONS ===
        val slice1to5 = list.slice(1..5)                      // [2, 3, 4, 5, 6]
        val sliceUntil5 = list.slice(0 until 5)               // [1, 2, 3, 4, 5]
        val sliceFrom3 = list.slice(3..list.lastIndex)        // [4, 5, 6, 7, 8, 9, 10]
        val sliceWithStep = list.slice(0..list.lastIndex step 2) // [1, 3, 5, 7, 9]
        
        // === TAKE/DROP OPERATIONS ===
        val first3 = list.take(3)                             // [1, 2, 3]
        val last3 = list.takeLast(3)                          // [8, 9, 10]
        val dropFirst3 = list.drop(3)                         // [4, 5, 6, 7, 8, 9, 10]
        val dropLast3 = list.dropLast(3)                      // [1, 2, 3, 4, 5, 6, 7]
        
        // === SUBLIST OPERATIONS ===
        val subList = list.subList(1, 4)                      // [2, 3, 4]
        val subListToEnd = list.subList(3, list.size)         // [4, 5, 6, 7, 8, 9, 10]
    }
    
    /**
     * Conditional Access
     * Accessing elements based on conditions
     */
    fun conditionalAccess() {
        // Create list within function - standalone
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // === FILTER OPERATIONS ===
        val evenNumbers = list.filter { it % 2 == 0 }         // [2, 4, 6, 8, 10]
        val oddNumbers = list.filter { it % 2 == 1 }          // [1, 3, 5, 7, 9]
        val greaterThan5 = list.filter { it > 5 }             // [6, 7, 8, 9, 10]
        val between3and7 = list.filter { it in 3..7 }         // [3, 4, 5, 6, 7]
        
        // === FIND OPERATIONS ===
        val firstEven = list.find { it % 2 == 0 }             // 2
        val lastEven = list.findLast { it % 2 == 0 }          // 10
        val firstGreaterThan5 = list.find { it > 5 }          // 6
        val indexOfFirstEven = list.indexOfFirst { it % 2 == 0 } // 1
        val indexOfLastEven = list.indexOfLast { it % 2 == 0 }   // 9
        
        // === CONTAINS OPERATIONS ===
        val contains3 = 3 in list                             // true
        val containsAll = list.containsAll(listOf(1, 3, 5))   // true
        val containsNone = list.none { it > 20 }              // true
        val containsAny = list.any { it > 8 }                 // true
    }
    
    /**
     * Multiple Element Access
     * Accessing multiple elements at once
     */
    fun multipleElementAccess() {
        // Create list within function - standalone
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // === MULTIPLE INDICES ===
        val indices = listOf(0, 2, 4, 6, 8)
        val elementsAtIndices = indices.map { list[it] }      // [1, 3, 5, 7, 9]
        
        // === AROUND INDEX ===
        val centerIndex = 4
        val aroundCenter = listOfNotNull(
            if (centerIndex > 0) list[centerIndex - 1] else null,
            list[centerIndex],
            if (centerIndex < list.lastIndex) list[centerIndex + 1] else null
        ) // [4, 5, 6]
        
        // === WINDOW ACCESS ===
        val windowSize = 3
        val windows = list.windowed(windowSize)               // [[1,2,3], [2,3,4], [3,4,5], ...]
        val windowsWithStep = list.windowed(windowSize, step = 2) // [[1,2,3], [3,4,5], [5,6,7], ...]
        
        // === CHUNK ACCESS ===
        val chunks = list.chunked(3)                          // [[1,2,3], [4,5,6], [7,8,9], [10]]
        val chunksWithTransform = list.chunked(3) { it.sum() } // [6, 15, 24, 10]
    }
    
    /**
     * 2D List Access
     * Accessing elements in 2D lists (list of lists)
     */
    fun twoDimensionalAccess() {
        // Create matrix within function - standalone
        val matrix = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9)
        )
        
        // === DIRECT ACCESS ===
        val element = matrix[1][2]                            // 6
        val row = matrix[1]                                   // [4, 5, 6]
        val column = matrix.map { it[1] }                     // [2, 5, 8]
        
        // === SAFE ACCESS ===
        val safeElement = matrix.getOrNull(1)?.getOrNull(2)   // 6
        val outOfBounds = matrix.getOrNull(5)?.getOrNull(5)   // null
        
        // === ROW/COLUMN ACCESS ===
        val firstRow = matrix.first()                         // [1, 2, 3]
        val lastRow = matrix.last()                           // [7, 8, 9]
        val firstColumn = matrix.map { it.first() }           // [1, 4, 7]
        val lastColumn = matrix.map { it.last() }             // [3, 6, 9]
        
        // === FLATTEN ACCESS ===
        val flattened = matrix.flatten()                      // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        val flattenedWithTransform = matrix.flatMap { it.map { num -> num * 2 } }
        // [2, 4, 6, 8, 10, 12, 14, 16, 18]
    }
} 