package data_structure.lists

/**
 * LIST ACCESS - Quick Reference
 * All Kotlin list access methods in one place
 */

object ListAccess {
    
    /**
     * All List Access Methods
     * Complete reference for accessing lists in Kotlin
     */
    fun allListAccessMethods() {
        // Create list within function - standalone
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // === DIRECT ACCESS ===
        val firstElement = list[0]                            // 1
        val lastElement = list[list.size - 1]                 // 10
        val elementAtIndex = list[2]                          // 3
        
        // === SAFE ACCESS ===
        val safeElement = list.getOrNull(10)                  // null
        val elementWithDefault = list.getOrElse(10) { -1 }    // -1
        val firstOrNull = list.firstOrNull()                  // 1
        val lastOrNull = list.lastOrNull()                    // 10
        
        // === BOUNDS CHECKING ===
        val isValidIndex = 2 in list.indices                  // true
        val isOutOfBounds = 10 in list.indices                // false
        
        // === RANGE ACCESS ===
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
        
        // === CONDITIONAL ACCESS ===
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
        
        // === MULTIPLE ELEMENT ACCESS ===
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
        
        // === 2D LIST ACCESS ===
        val matrix = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9)
        )
        val element = matrix[1][2]                            // 6
        val row = matrix[1]                                   // [4, 5, 6]
        val column = matrix.map { it[1] }                     // [2, 5, 8]
        val flattened = matrix.flatten()                      // [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
} 