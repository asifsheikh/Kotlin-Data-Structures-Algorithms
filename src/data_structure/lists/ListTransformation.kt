package data_structure.lists

/**
 * LIST TRANSFORMATION - Quick Reference
 * All Kotlin list transformation methods in one place
 */

object ListTransformation {
    
    /**
     * All List Transformation Methods
     * Complete reference for transforming lists in Kotlin
     */
    fun allListTransformationMethods() {
        // Create list within function - standalone
        val list = listOf(1, 2, 3, 4, 5)
        
        // === MAPPING OPERATIONS ===
        val doubled = list.map { it * 2 }                      // [2, 4, 6, 8, 10]
        val squared = list.map { it * it }                     // [1, 4, 9, 16, 25]
        val toString = list.map { it.toString() }              // ["1", "2", "3", "4", "5"]
        val withIndex = list.mapIndexed { index, value -> "$index:$value" } // ["0:1", "1:2", "2:3", "3:4", "4:5"]
        val indexPlusValue = list.mapIndexed { index, value -> index + value } // [1, 3, 5, 7, 9]
        val toBoolean = list.map { it > 3 }                    // [false, false, false, true, true]
        val toChar = list.map { ('a' + it - 1).toChar() }      // ['a', 'b', 'c', 'd', 'e']
        
        // === FILTERING OPERATIONS ===
        val evenNumbers = list.filter { it % 2 == 0 }          // [2, 4]
        val oddNumbers = list.filter { it % 2 == 1 }           // [1, 3, 5]
        val greaterThan3 = list.filter { it > 3 }              // [4, 5]
        val between2and4 = list.filter { it in 2..4 }          // [2, 3, 4]
        val evenIndices = list.filterIndexed { index, _ -> index % 2 == 0 } // [1, 3, 5]
        val oddIndices = list.filterIndexed { index, _ -> index % 2 == 1 }   // [2, 4]
        val notEven = list.filterNot { it % 2 == 0 }           // [1, 3, 5]
        
        // === FLATTENING OPERATIONS ===
        val nestedList = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9)
        )
        val flattened = nestedList.flatten()                   // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        val flatMapped = nestedList.flatMap { it.map { num -> num * 2 } } // [2, 4, 6, 8, 10, 12, 14, 16, 18]
        val flatMappedEven = nestedList.flatMap { it.filter { num -> num % 2 == 0 } } // [2, 4, 6, 8]
        
        // === CONCATENATION OPERATIONS ===
        val list1 = listOf(1, 2, 3)
        val list2 = listOf(4, 5, 6)
        val list3 = listOf(7, 8, 9)
        val combined = list1 + list2 + list3                   // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        val result = list1.plus(list2).plus(list3)             // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        val mutableCombined = mutableListOf<Int>()
        mutableCombined.addAll(list1)
        mutableCombined.addAll(list2)
        mutableCombined.addAll(list3)                          // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        
        // === SPLITTING OPERATIONS ===
        val longList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val left = longList.take(5)                            // [1,2,3,4,5]
        val right = longList.drop(5)                           // [6,7,8,9,10]
        val parts = longList.chunked(3)                        // [[1,2,3], [4,5,6], [7,8,9], [10]]
        val chunks = longList.chunked(3)                       // [[1,2,3], [4,5,6], [7,8,9], [10]]
        val chunksWithTransform = longList.chunked(3) { it.sum() } // [6, 15, 24, 10]
        val windows = longList.windowed(3)                     // [[1,2,3], [2,3,4], [3,4,5], ...]
        val windowsWithStep = longList.windowed(3, step = 2)   // [[1,2,3], [3,4,5], [5,6,7], ...]
        
        // === ZIP OPERATIONS ===
        val numbers = listOf(1, 2, 3, 4, 5)
        val letters = listOf("a", "b", "c", "d", "e")
        val symbols = listOf("!", "@", "#", "$", "%")
        val zipped = numbers.zip(letters)                      // [(1, "a"), (2, "b"), (3, "c"), (4, "d"), (5, "e")]
        val zippedWithTransform = numbers.zip(letters) { num, letter -> "$num$letter" } // ["1a", "2b", "3c", "4d", "5e"]
        val zippedThree = numbers.zip(letters).zip(symbols) { pair, symbol -> "${pair.first}${pair.second}$symbol" } // ["1a!", "2b@", "3c#", "4d$", "5e%"]
        val (unzippedNumbers, unzippedLetters) = zipped.unzip() // ([1,2,3,4,5], ["a","b","c","d","e"])
    }
} 