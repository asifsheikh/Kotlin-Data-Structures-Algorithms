package data_structure.lists

/**
 * LIST TRANSFORMATION - Quick Reference
 * All Kotlin list transformation methods in one place
 */

object ListTransformation {
    
    /**
     * List Mapping
     * Transform each element using functions
     */
    fun listMapping() {
        // Create list within function - standalone
        val list = listOf(1, 2, 3, 4, 5)
        
        // === BASIC MAP ===
        val doubled = list.map { it * 2 }                      // [2, 4, 6, 8, 10]
        val squared = list.map { it * it }                     // [1, 4, 9, 16, 25]
        val toString = list.map { it.toString() }              // ["1", "2", "3", "4", "5"]
        
        // === MAP WITH INDEX ===
        val withIndex = list.mapIndexed { index, value -> "$index:$value" } // ["0:1", "1:2", "2:3", "3:4", "4:5"]
        val indexPlusValue = list.mapIndexed { index, value -> index + value } // [1, 3, 5, 7, 9]
        
        // === MAP TO DIFFERENT TYPE ===
        val toBoolean = list.map { it > 3 }                    // [false, false, false, true, true]
        val toChar = list.map { ('a' + it - 1).toChar() }      // ['a', 'b', 'c', 'd', 'e']
    }
    
    /**
     * List Filtering
     * Select elements based on conditions
     */
    fun listFiltering() {
        // Create list within function - standalone
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // === BASIC FILTER ===
        val evenNumbers = list.filter { it % 2 == 0 }          // [2, 4, 6, 8, 10]
        val oddNumbers = list.filter { it % 2 == 1 }           // [1, 3, 5, 7, 9]
        val greaterThan5 = list.filter { it > 5 }              // [6, 7, 8, 9, 10]
        val between3and7 = list.filter { it in 3..7 }          // [3, 4, 5, 6, 7]
        
        // === FILTER WITH INDEX ===
        val evenIndices = list.filterIndexed { index, _ -> index % 2 == 0 } // [1, 3, 5, 7, 9]
        val oddIndices = list.filterIndexed { index, _ -> index % 2 == 1 }   // [2, 4, 6, 8, 10]
        
        // === FILTER NOT ===
        val notEven = list.filterNot { it % 2 == 0 }           // [1, 3, 5, 7, 9]
        val notGreaterThan5 = list.filterNot { it > 5 }        // [1, 2, 3, 4, 5]
    }
    
    /**
     * List Flattening
     * Convert nested lists to flat lists
     */
    fun listFlattening() {
        // Create nested list within function - standalone
        val nestedList = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9)
        )
        
        // === FLATTEN NESTED LIST ===
        val flattened = nestedList.flatten()                   // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        
        // === FLATMAP WITH TRANSFORMATION ===
        val flatMapped = nestedList.flatMap { it.map { num -> num * 2 } } // [2, 4, 6, 8, 10, 12, 14, 16, 18]
        
        // === FLATMAP WITH CONDITION ===
        val flatMappedEven = nestedList.flatMap { it.filter { num -> num % 2 == 0 } } // [2, 4, 6, 8]
    }
    
    /**
     * List Concatenation
     * Combine multiple lists
     */
    fun listConcatenation() {
        // Create lists within function - standalone
        val list1 = listOf(1, 2, 3)
        val list2 = listOf(4, 5, 6)
        val list3 = listOf(7, 8, 9)
        
        // === CONCATENATE LISTS ===
        val combined = list1 + list2 + list3                   // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        
        // === CONCATENATE WITH PLUS ===
        val result = list1.plus(list2).plus(list3)             // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        
        // === CONCATENATE WITH ADDALL ===
        val mutableCombined = mutableListOf<Int>()
        mutableCombined.addAll(list1)
        mutableCombined.addAll(list2)
        mutableCombined.addAll(list3)                          // [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
    
    /**
     * List Splitting
     * Divide lists into parts
     */
    fun listSplitting() {
        // Create list within function - standalone
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // === SPLIT AT INDEX ===
        val (left, right) = list.splitAt(5)                    // ([1,2,3,4,5], [6,7,8,9,10])
        
        // === SPLIT BY CONDITION ===
        val parts = list.split { it % 3 == 0 }                 // [[1,2], [4,5], [7,8], [10]]
        
        // === CHUNK SPLITTING ===
        val chunks = list.chunked(3)                           // [[1,2,3], [4,5,6], [7,8,9], [10]]
        val chunksWithTransform = list.chunked(3) { it.sum() } // [6, 15, 24, 10]
        
        // === WINDOW SPLITTING ===
        val windows = list.windowed(3)                         // [[1,2,3], [2,3,4], [3,4,5], ...]
        val windowsWithStep = list.windowed(3, step = 2)       // [[1,2,3], [3,4,5], [5,6,7], ...]
    }
    
    /**
     * List Zipping
     * Combine lists element by element
     */
    fun listZipping() {
        // Create lists within function - standalone
        val numbers = listOf(1, 2, 3, 4, 5)
        val letters = listOf("a", "b", "c", "d", "e")
        val symbols = listOf("!", "@", "#", "$", "%")
        
        // === BASIC ZIP ===
        val zipped = numbers.zip(letters)                      // [(1, "a"), (2, "b"), (3, "c"), (4, "d"), (5, "e")]
        
        // === ZIP WITH TRANSFORM ===
        val zippedWithTransform = numbers.zip(letters) { num, letter -> "$num$letter" } // ["1a", "2b", "3c", "4d", "5e"]
        
        // === ZIP WITH THREE LISTS ===
        val zippedThree = numbers.zip(letters).zip(symbols) { pair, symbol -> "${pair.first}${pair.second}$symbol" }
        // ["1a!", "2b@", "3c#", "4d$", "5e%"]
        
        // === UNZIP ===
        val (unzippedNumbers, unzippedLetters) = zipped.unzip() // ([1,2,3,4,5], ["a","b","c","d","e"])
    }
} 