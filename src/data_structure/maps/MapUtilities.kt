package data_structure.maps

/**
 * MAP UTILITIES - Quick Reference
 * All Kotlin map utility methods in one place
 */

object MapUtilities {
    
    /**
     * All Map Utility Methods
     * Complete reference for map utilities in Kotlin
     */
    fun allMapUtilityMethods() {
        // Create map within function - standalone
        val map = mapOf("A" to 1, "B" to 2, "C" to 3, "D" to 4, "E" to 5)
        
        // === STATISTICS OPERATIONS ===
        val size = map.size                                       // 5
        val isEmpty = map.isEmpty()                              // false
        val isNotEmpty = map.isNotEmpty()                        // true
        val keyCount = map.keys.size                             // 5
        val valueCount = map.values.size                         // 5
        val entryCount = map.entries.size                        // 5
        
        val sumOfValues = map.values.sum()                       // 15
        val averageOfValues = map.values.average()               // 3.0
        val minValue = map.values.minOrNull()                    // 1
        val maxValue = map.values.maxOrNull()                    // 5
        val minKey = map.keys.minOrNull()                        // A
        val maxKey = map.keys.maxOrNull()                        // E
        
        val evenValueCount = map.values.count { it % 2 == 0 }    // 2
        val oddValueCount = map.values.count { it % 2 == 1 }     // 3
        val vowelKeyCount = map.keys.count { it in "AEIOU" }     // 2
        val consonantKeyCount = map.keys.count { it !in "AEIOU" } // 3
        
        // === VALIDATION OPERATIONS ===
        val hasKeyA = map.containsKey("A")                       // true
        val hasKeyZ = map.containsKey("Z")                       // false
        val hasValue3 = map.containsValue(3)                     // true
        val hasValue10 = map.containsValue(10)                   // false
        val keyInMap = "A" in map                                // true
        val keyNotInMap = "Z" in map                             // false
        val valueInMap = 3 in map.values                         // true
        val valueNotInMap = 10 in map.values                     // false
        
        val allValuesPositive = map.values.all { it > 0 }        // true
        val anyValueEven = map.values.any { it % 2 == 0 }        // true
        val allKeysVowels = map.keys.all { it in "AEIOU" }       // false
        val anyKeyVowel = map.keys.any { it in "AEIOU" }         // true
        val noneValueNegative = map.values.none { it < 0 }       // true
        val noneKeyDigit = map.keys.none { it.toIntOrNull() != null } // true
        
        // === COMPARISON OPERATIONS ===
        val map1 = mapOf("A" to 1, "B" to 2)
        val map2 = mapOf("A" to 1, "B" to 2)
        val map3 = mapOf("B" to 2, "A" to 1)
        val map4 = mapOf("A" to 1, "B" to 3)
        
        val areEqual = map1 == map2                              // true
        val areNotEqual = map1 == map3                           // false (order matters for equals)
        val areNotEqualValues = map1 == map4                     // false
        val sameSize = map1.size == map2.size                    // true
        val differentSize = map1.size != map3.size               // false
        
        val sameKeys = map1.keys == map2.keys                    // true
        val sameValues = map1.values.toSet() == map2.values.toSet() // true
        val differentValues = map1.values.toSet() == map4.values.toSet() // false
        
        // === CONVERSION OPERATIONS ===
        val toList = map.toList()                                // [(A,1), (B,2), (C,3), (D,4), (E,5)]
        val toSet = map.toSet()                                  // {(A,1), (B,2), (C,3), (D,4), (E,5)}
        val toMutableMap = map.toMutableMap()                    // MutableMap
        val toHashMap = HashMap(map)                             // HashMap
        val toLinkedHashMap = LinkedHashMap(map)                 // LinkedHashMap
        val toSortedMap = map.toSortedMap()                      // SortedMap
        
        val keysList = map.keys.toList()                         // [A, B, C, D, E]
        val valuesList = map.values.toList()                     // [1, 2, 3, 4, 5]
        val entriesList = map.entries.toList()                   // [A=1, B=2, C=3, D=4, E=5]
        val keysSet = map.keys.toSet()                           // {A, B, C, D, E}
        val valuesSet = map.values.toSet()                       // {1, 2, 3, 4, 5}
        
        val toString = map.toString()                            // "{A=1, B=2, C=3, D=4, E=5}"
        val joinToString = map.entries.joinToString { "${it.key}:${it.value}" } // "A:1, B:2, C:3, D:4, E:5"
        val joinWithSeparator = map.entries.joinToString(" | ") { "${it.key}=${it.value}" } // "A=1 | B=2 | C=3 | D=4 | E=5"
        
        // === ITERATION OPERATIONS ===
        for ((key, value) in map) {
            // Process each key-value pair
        }
        
        map.forEach { (key, value) ->
            // Process each key-value pair
        }
        
        map.forEach { entry ->
            // Process each entry
        }
        
        map.entries.forEach { entry ->
            // Process each entry
        }
        
        map.keys.forEach { key ->
            val value = map[key]
            // Process key-value
        }
        
        map.values.forEach { value ->
            // Process value
        }
        
        // === ITERATOR OPERATIONS ===
        val iterator = map.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            // Process entry
        }
        
        val keysIterator = map.keys.iterator()
        while (keysIterator.hasNext()) {
            val key = keysIterator.next()
            val value = map[key]
            // Process key-value
        }
        
        val valuesIterator = map.values.iterator()
        while (valuesIterator.hasNext()) {
            val value = valuesIterator.next()
            // Process value
        }
        
        val entriesIterator = map.entries.iterator()
        while (entriesIterator.hasNext()) {
            val entry = entriesIterator.next()
            // Process entry
        }
        
        // === GROUPING OPERATIONS ===
        val groupedByValueEvenOdd = map.groupBy { (_, value) -> value % 2 == 0 } // {false=[A=1, C=3, E=5], true=[B=2, D=4]}
        val groupedByKeyVowel = map.groupBy { (key, _) -> key in "AEIOU" } // {true=[A=1, E=5], false=[B=2, C=3, D=4]}
        val groupedByValueRange = map.groupBy { (_, value) -> 
            when {
                value <= 2 -> "small"
                value <= 4 -> "medium"
                else -> "large"
            }
        }                                                        // {small=[A=1, B=2], medium=[C=3, D=4], large=[E=5]}
        
        val groupedByKeyLength = map.groupBy { (key, _) -> key.length } // {1=[A=1, B=2, C=3, D=4, E=5]}
        val groupedByValueType = map.groupBy { (_, value) -> 
            when {
                value % 2 == 0 -> "even"
                else -> "odd"
            }
        }                                                        // {odd=[A=1, C=3, E=5], even=[B=2, D=4]}
        
        // === FINDING OPERATIONS ===
        val firstEntry = map.entries.first()                     // A=1
        val lastEntry = map.entries.last()                       // E=5
        val firstKey = map.keys.first()                          // A
        val lastKey = map.keys.last()                            // E
        val firstValue = map.values.first()                      // 1
        val lastValue = map.values.last()                        // 5
        
        val firstEvenValue = map.entries.find { it.value % 2 == 0 } // B=2
        val firstVowelKey = map.keys.find { it in "AEIOU" }     // A
        val firstValueGreaterThan3 = map.entries.find { it.value > 3 } // D=4
        val lastEvenValue = map.entries.findLast { it.value % 2 == 0 } // D=4
        val lastVowelKey = map.keys.findLast { it in "AEIOU" }  // E
        
        // === SORTING OPERATIONS ===
        val sortedByKey = map.toSortedMap()                      // {A=1, B=2, C=3, D=4, E=5}
        val sortedByValue = map.toList().sortedBy { it.second }.toMap() // {A=1, B=2, C=3, D=4, E=5}
        val sortedByKeyDesc = map.toList().sortedByDescending { it.first }.toMap() // {E=5, D=4, C=3, B=2, A=1}
        val sortedByValueDesc = map.toList().sortedByDescending { it.second }.toMap() // {E=5, D=4, C=3, B=2, A=1}
        
        val sortedKeys = map.keys.sorted()                       // [A, B, C, D, E]
        val sortedValues = map.values.sorted()                   // [1, 2, 3, 4, 5]
        val sortedKeysDesc = map.keys.sortedDescending()         // [E, D, C, B, A]
        val sortedValuesDesc = map.values.sortedDescending()     // [5, 4, 3, 2, 1]
        
        // === AGGREGATION OPERATIONS ===
        val sumOfValues2 = map.values.sum()                      // 15
        val productOfValues = map.values.reduce { acc, value -> acc * value } // 120
        val maxValue2 = map.values.maxOrNull()                   // 5
        val minValue2 = map.values.minOrNull()                   // 1
        val averageValue = map.values.average()                  // 3.0
        
        val concatenatedKeys = map.keys.joinToString("")         // "ABCDE"
        val concatenatedValues = map.values.joinToString("")     // "12345"
        val concatenatedEntries = map.entries.joinToString("") { "${it.key}${it.value}" } // "A1B2C3D4E5"
    }
} 