package data_structure.lists

/**
 * LIST MODIFICATION - Quick Reference
 * All Kotlin list modification methods in one place
 */

object ListModification {
    
    /**
     * All List Modification Methods
     * Complete reference for modifying lists in Kotlin
     */
    fun allListModificationMethods() {
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
        
        // === REVERSE OPERATIONS ===
        list.reverse()                                         // Reverse entire list
        list.subList(1, 4).reverse()                           // Reverse range [1, 4)
        
        // === MANUAL REVERSE ===
        var left = 0; var right = list.size - 1
        while (left < right) {
            list[left] = list[right].also { list[right] = list[left] }
            left++; right--
        }
        
        // === SORTING OPERATIONS ===
        list.sort()                                            // Sort in ascending order
        list.sortDescending()                                  // Sort in descending order
        list.sortBy { it % 3 }                                 // Sort by remainder when divided by 3
        list.sortWith(compareBy<Int> { it % 2 }.thenBy { it }) // Sort by even/odd, then by value
        
        // === SHUFFLING OPERATIONS ===
        list.shuffle()                                         // Shuffle in-place
        val shuffledList = list.shuffled()                     // New shuffled list
        
        // === FISHER-YATES SHUFFLE ===
        for (i in list.size - 1 downTo 1) {
            val j = (0..i).random()
            list[i] = list[j].also { list[j] = list[i] }
        }
        
        // === DEDUPLICATION ===
        val duplicateList = mutableListOf(1, 2, 2, 3, 3, 3, 4, 4, 4, 4)
        val unique = duplicateList.distinct()                  // [1, 2, 3, 4]
        val seen = mutableSetOf<Int>()
        duplicateList.removeAll { !seen.add(it) }              // Remove duplicates in-place
        
        // === FILLING OPERATIONS ===
        val fillList = MutableList(5) { 0 }
        fillList.fill(42)                                      // [42, 42, 42, 42, 42]
        for (i in fillList.indices) fillList[i] = i * 2       // [0, 2, 4, 6, 8]
        
        // === TRANSFORMATION OPERATIONS ===
        list.replaceAll { it * 2 }                             // Multiply all elements by 2
        list.replaceAll { if (it % 2 == 0) it * 10 else it }  // Multiply even elements by 10
        for (i in list.indices) list[i] = list[i] + i         // Add index to each element
    }
} 