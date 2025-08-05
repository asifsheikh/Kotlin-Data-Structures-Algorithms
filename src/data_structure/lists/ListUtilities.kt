package data_structure.lists

/**
 * LIST UTILITIES - Quick Reference
 * All Kotlin list utility methods in one place
 */

object ListUtilities {
    
    /**
     * All List Utility Methods
     * Complete reference for list utilities in Kotlin
     */
    fun allListUtilityMethods() {
        // Create list within function - standalone
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // === STATISTICS OPERATIONS ===
        val sum = list.sum()                                   // 55
        val average = list.average()                           // 5.5
        val min = list.minOrNull()                             // 1
        val max = list.maxOrNull()                             // 10
        val count = list.count()                               // 10
        val evenCount = list.count { it % 2 == 0 }            // 5
        val oddSum = list.filter { it % 2 == 1 }.sum()        // 25
        val greaterThan5Count = list.count { it > 5 }         // 5
        val frequency = list.groupBy { it }.mapValues { it.value.size } // {1=1, 2=1, 3=1, 4=1, 5=1, 6=1, 7=1, 8=1, 9=1, 10=1}
        val mostFrequent = frequency.maxByOrNull { it.value }?.key // Any element (all have frequency 1)
        
        // === VALIDATION OPERATIONS ===
        val isEmpty = list.isEmpty()                           // false
        val isNotEmpty = list.isNotEmpty()                     // true
        val hasSize = list.size == 10                          // true
        val hasDuplicates = list.size != list.distinct().size  // false
        val isSorted = list.zipWithNext().all { (a, b) -> a <= b } // true
        val allPositive = list.all { it > 0 }                 // true
        val anyNegative = list.any { it < 0 }                 // false
        val allInRange = list.all { it in 1..10 }             // true
        val hasEven = list.any { it % 2 == 0 }                // true
        val allEven = list.all { it % 2 == 0 }                // false
        
        // === COMPARISON OPERATIONS ===
        val list1 = listOf(1, 2, 3, 4, 5)
        val list2 = listOf(1, 2, 3, 4, 5)
        val list3 = listOf(5, 4, 3, 2, 1)
        val areEqual = list1 == list2                          // true
        val areNotEqual = list1 == list3                       // false
        val elementWise = list1.zip(list2).map { (a, b) -> a == b } // [true, true, true, true, true]
        val sameSize = list1.size == list2.size               // true
        val differentSize = list1.size != list3.size          // false
        val sameContents = list1.toSet() == list2.toSet()     // true (ignores order)
        val differentContents = list1.toSet() != list3.toSet() // false
        
        // === CONVERSION OPERATIONS ===
        val toArray = list.toTypedArray()                      // Array<Int>
        val toIntArray = list.toIntArray()                     // IntArray
        val toSet = list.toSet()                               // Set<Int>
        val toMutableSet = list.toMutableSet()                 // MutableSet<Int>
        val toMap = list.mapIndexed { index, value -> index to value }.toMap() // Map<Int, Int>
        val toMapWithTransform = list.associateWith { it * 2 } // Map<Int, Int>
        val toString = list.toString()                         // "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"
        val joinToString = list.joinToString()                 // "1, 2, 3, 4, 5, 6, 7, 8, 9, 10"
        val joinWithSeparator = list.joinToString("-")         // "1-2-3-4-5-6-7-8-9-10"
        val joinWithPrefixSuffix = list.joinToString(prefix = "[", postfix = "]") // "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]"
        
        // === ITERATION OPERATIONS ===
        for (item in list) {
            // Process each item
        }
        list.forEach { item ->
            // Process each item
        }
        list.forEachIndexed { index, item ->
            // Process item with its index
        }
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            // Process item
        }
        val listIterator = list.listIterator()
        while (listIterator.hasNext()) {
            val item = listIterator.next()
            // Process item
        }
        for (i in list.indices.reversed()) {
            val item = list[i]
            // Process item in reverse order
        }
        
        // === GROUPING OPERATIONS ===
        val groupedByEvenOdd = list.groupBy { it % 2 == 0 }    // {true=[2,4,6,8,10], false=[1,3,5,7,9]}
        val groupedByRange = list.groupBy { 
            when {
                it <= 3 -> "small"
                it <= 7 -> "medium"
                else -> "large"
            }
        } // {"small"=[1,2,3], "medium"=[4,5,6,7], "large"=[8,9,10]}
        val stringList = listOf("apple", "banana", "cherry", "date", "elderberry")
        val groupedByFirstLetter = stringList.groupBy { it[0] } // {'a'=["apple"], 'b'=["banana"], 'c'=["cherry"], 'd'=["date"], 'e'=["elderberry"]}
        val groupedWithTransform = list.groupBy({ it % 3 }, { it * 2 }) // {1=[2,8,14], 2=[4,10], 0=[6,12]}
    }
} 