package data_structure.lists

/**
 * LIST MODIFICATION - Quick Reference
 * All Kotlin list modification methods in one place
 */

object ListModification {
    
    /**
     * Basic List Modification
     * Standard list modification operations (mutable lists only)
     */
    fun basicModification() {
        // Create mutable list within function - standalone
        val list = mutableListOf(1, 2, 3, 4, 5)
        
        // === ADD OPERATIONS ===
        list.add(6)                                            // [1, 2, 3, 4, 5, 6]
        list.add(1, 10)                                        // [1, 10, 2, 3, 4, 5, 6]
        list.addAll(listOf(7, 8, 9))                          // [1, 10, 2, 3, 4, 5, 6, 7, 8, 9]
        
        // === SET OPERATIONS ===
        list[0] = 100                                          // [100, 10, 2, 3, 4, 5, 6, 7, 8, 9]
        list.set(1, 200)                                       // [100, 200, 2, 3, 4, 5, 6, 7, 8, 9]
        
        // === REMOVE OPERATIONS ===
        list.remove(3)                                         // Remove first occurrence of 3
        list.removeAt(2)                                       // Remove element at index 2
        list.removeAll(listOf(2, 4, 6))                       // Remove all occurrences
    }
    
    /**
     * List Reversal
     * Different ways to reverse lists
     */
    fun listReversal() {
        // Create list within function - standalone
        val list = mutableListOf(1, 2, 3, 4, 5)
        
        // === REVERSE IN-PLACE ===
        list.reverse()                                         // [5, 4, 3, 2, 1]
        
        // === REVERSE RANGE ===
        list.reverse(1, 4)                                     // [5, 2, 3, 4, 1]
        
        // === MANUAL REVERSE ===
        var left = 0; var right = list.size - 1
        while (left < right) {
            list[left] = list[right].also { list[right] = list[left] }
            left++; right--
        }
    }
    
    /**
     * List Sorting
     * Different ways to sort lists
     */
    fun listSorting() {
        // Create list within function - standalone
        val list = mutableListOf(5, 2, 8, 1, 9, 3, 7, 4, 6)
        
        // === BASIC SORTING ===
        list.sort()                                            // [1, 2, 3, 4, 5, 6, 7, 8, 9]
        list.sortDescending()                                  // [9, 8, 7, 6, 5, 4, 3, 2, 1]
        
        // === CUSTOM SORTING ===
        list.sortBy { it % 3 }                                 // Sort by remainder when divided by 3
        list.sortWith(compareBy<Int> { it % 2 }.thenBy { it }) // Sort by even/odd, then by value
    }
    
    /**
     * List Shuffling
     * Different ways to shuffle lists
     */
    fun listShuffling() {
        // Create list within function - standalone
        val list = mutableListOf(1, 2, 3, 4, 5)
        
        // === SHUFFLE IN-PLACE ===
        list.shuffle()                                         // Random permutation in-place
        
        // === FISHER-YATES SHUFFLE ===
        for (i in list.size - 1 downTo 1) {
            val j = (0..i).random()
            list[i] = list[j].also { list[j] = list[i] }
        }
        
        // === SHUFFLED (RETURNS NEW LIST) ===
        val shuffledList = list.shuffled()                     // New shuffled list
    }
    
    /**
     * List Deduplication
     * Removing duplicates from lists
     */
    fun listDeduplication() {
        // Create list within function - standalone
        val list = mutableListOf(1, 2, 2, 3, 3, 3, 4, 4, 4, 4)
        
        // === REMOVE DUPLICATES ===
        val unique = list.distinct()                           // [1, 2, 3, 4]
        
        // === REMOVE DUPLICATES IN-PLACE ===
        val seen = mutableSetOf<Int>()
        list.removeAll { !seen.add(it) }                       // Remove duplicates, keep first occurrence
    }
} 