package data_structure.arrays

/**
 * ARRAY UTILITIES - Quick Reference
 * All Kotlin array utility methods in one place
 */

object ArrayUtilities {
    
    /**
     * Array Insertion
     * Insert elements at different positions
     */
    fun arrayInsertion() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        
        // === INSERT AT BEGINNING ===
        val withFirst = intArrayOf(0) + arr                  // [0, 1, 2, 3, 4, 5]
        val withFirstList = listOf(0) + arr.toList()         // List<Int>
        
        // === INSERT AT END ===
        val withLast = arr + 6                               // [1, 2, 3, 4, 5, 6]
        val withLastList = arr.toList() + 6                  // List<Int>
        
        // === INSERT AT INDEX ===
        val index = 2; val value = 10
        val withInsert = arr.take(index) + value + arr.drop(index) // [1, 2, 10, 3, 4, 5]
        
        // === INSERT MULTIPLE ===
        val multiple = arr.take(2) + intArrayOf(10, 20) + arr.drop(2) // [1, 2, 10, 20, 3, 4, 5]
    }
    
    /**
     * Array Removal
     * Remove elements by different criteria
     */
    fun arrayRemoval() {
        val arr = intArrayOf(1, 2, 3, 4, 5, 3, 6, 3, 7)
        
        // === REMOVE BY VALUE ===
        val without3 = arr.filter { it != 3 }                // [1, 2, 4, 5, 6, 7]
        val withoutFirst3 = arr.takeWhile { it != 3 } + arr.dropWhile { it != 3 }.drop(1) // [1, 2, 4, 5, 3, 6, 3, 7]
        
        // === REMOVE AT INDEX ===
        val index = 2
        val withoutIndex = arr.take(index) + arr.drop(index + 1) // [1, 2, 4, 5, 3, 6, 3, 7]
        
        // === REMOVE RANGE ===
        val start = 1; val end = 3
        val withoutRange = arr.take(start) + arr.drop(end + 1) // [1, 5, 3, 6, 3, 7]
        
        // === REMOVE DUPLICATES ===
        val unique = arr.distinct()                          // [1, 2, 3, 4, 5, 6, 7]
        val uniquePreserveOrder = arr.toSet().toList()       // Preserve order if needed
    }
    
    /**
     * Array Replacement
     * Replace elements in different ways
     */
    fun arrayReplacement() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        
        // === REPLACE BY VALUE ===
        val replace3with10 = arr.map { if (it == 3) 10 else it } // [1, 2, 10, 4, 5]
        
        // === REPLACE AT INDEX ===
        val index = 2; val newValue = 10
        val replacedAtIndex = arr.mapIndexed { i, value -> if (i == index) newValue else value } // [1, 2, 10, 4, 5]
        
        // === REPLACE BY CONDITION ===
        val replaceEvenWith0 = arr.map { if (it % 2 == 0) 0 else it } // [1, 0, 3, 0, 5]
        val replaceGreaterThan3 = arr.map { if (it > 3) 99 else it } // [1, 2, 3, 99, 99]
        
        // === REPLACE RANGE ===
        val start = 1; val end = 3; val replacement = 99
        val replacedRange = arr.mapIndexed { i, value -> if (i in start..end) replacement else value } // [1, 99, 99, 99, 5]
    }
    
    /**
     * Array Resizing
     * Change array size
     */
    fun arrayResizing() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        
        // === EXPAND ARRAY ===
        val expanded = arr.copyOf(10)                        // [1, 2, 3, 4, 5, 0, 0, 0, 0, 0]
        val expandedWithDefault = IntArray(10) { if (it < arr.size) arr[it] else -1 } // [1, 2, 3, 4, 5, -1, -1, -1, -1, -1]
        
        // === SHRINK ARRAY ===
        val shrunk = arr.copyOf(3)                           // [1, 2, 3]
        
        // === RESIZE WITH PADDING ===
        val padded = arr + IntArray(3) { 0 }                 // [1, 2, 3, 4, 5, 0, 0, 0]
        val paddedFront = IntArray(3) { 0 } + arr            // [0, 0, 0, 1, 2, 3, 4, 5]
    }
    
    /**
     * Array Comparison
     * Compare arrays in different ways
     */
    fun arrayComparison() {
        val arr1 = intArrayOf(1, 2, 3, 4, 5)
        val arr2 = intArrayOf(1, 2, 3, 4, 5)
        val arr3 = intArrayOf(5, 4, 3, 2, 1)
        
        // === EQUALITY CHECK ===
        val areEqual = arr1.contentEquals(arr2)              // true
        val areNotEqual = arr1.contentEquals(arr3)           // false
        
        // === ELEMENT-WISE COMPARISON ===
        val elementWise = arr1.zip(arr2).map { (a, b) -> a == b } // [true, true, true, true, true]
        
        // === COMPARE SIZES ===
        val sameSize = arr1.size == arr2.size                // true
        val differentSize = arr1.size != arr3.size           // false
        
        // === COMPARE CONTENTS ===
        val sameContents = arr1.toSet() == arr2.toSet()      // true (ignores order)
        val differentContents = arr1.toSet() != arr3.toSet() // false
    }
    
    /**
     * Array Statistics
     * Basic statistical operations
     */
    fun arrayStatistics() {
        val arr = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // === BASIC STATS ===
        val sum = arr.sum()                                  // 55
        val average = arr.average()                          // 5.5
        val min = arr.minOrNull()                            // 1
        val max = arr.maxOrNull()                            // 10
        val count = arr.count()                              // 10
        
        // === CONDITIONAL STATS ===
        val evenCount = arr.count { it % 2 == 0 }           // 5
        val oddSum = arr.filter { it % 2 == 1 }.sum()       // 25
        val greaterThan5Count = arr.count { it > 5 }        // 5
        
        // === FREQUENCY ===
        val frequency = arr.groupBy { it }.mapValues { it.value.size } // {1=1, 2=1, 3=1, 4=1, 5=1, 6=1, 7=1, 8=1, 9=1, 10=1}
        val mostFrequent = frequency.maxByOrNull { it.value }?.key // Any element (all have frequency 1)
    }
    
    /**
     * Array Validation
     * Validate array properties
     */
    fun arrayValidation() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        val emptyArr = intArrayOf()
        val duplicateArr = intArrayOf(1, 2, 2, 3, 4)
        
        // === BASIC VALIDATION ===
        val isEmpty = arr.isEmpty()                          // false
        val isNotEmpty = arr.isNotEmpty()                    // true
        val hasSize = arr.size == 5                          // true
        
        // === CONTENT VALIDATION ===
        val hasDuplicates = arr.size != arr.distinct().size  // false
        val isSorted = arr.zipWithNext().all { (a, b) -> a <= b } // true
        val allPositive = arr.all { it > 0 }                // true
        val anyNegative = arr.any { it < 0 }                // false
        
        // === RANGE VALIDATION ===
        val allInRange = arr.all { it in 1..10 }            // true
        val hasEven = arr.any { it % 2 == 0 }               // true
        val allEven = arr.all { it % 2 == 0 }               // false
    }
} 