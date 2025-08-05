package data_structure.maps

/**
 * MAP ACCESS - Quick Reference
 * All Kotlin map access methods in one place
 */

object MapAccess {
    
    /**
     * All Map Access Methods
     * Complete reference for accessing maps in Kotlin
     */
    fun allMapAccessMethods() {
        // Create map within function - standalone
        val map = mapOf("A" to 1, "B" to 2, "C" to 3, "D" to 4, "E" to 5)
        
        // === DIRECT ACCESS ===
        val valueA = map["A"]                                    // 1
        val valueZ = map["Z"]                                    // null
        val valueB = map.get("B")                                // 2
        val valueX = map.get("X")                                // null
        
        // === SAFE ACCESS ===
        val safeValue = map.getOrDefault("Z", -1)                // -1
        val safeValueLambda = map.getOrElse("Z") { -1 }          // -1
        val safeValueNull = map.getOrElse("Z") { null }          // null
        val safeValueComputed = map.getOrElse("Z") { 
            "computed_${map.size}" 
        }                                                        // "computed_5"
        
        // === MULTIPLE KEY ACCESS ===
        val keys = listOf("A", "B", "Z")
        val values = keys.map { map[it] }                        // [1, 2, null]
        val valuesWithDefault = keys.map { map.getOrDefault(it, -1) } // [1, 2, -1]
        val filteredValues = keys.mapNotNull { map[it] }         // [1, 2]
        
        // === CONTAINMENT CHECKS ===
        val hasKeyA = map.containsKey("A")                       // true
        val hasKeyZ = map.containsKey("Z")                       // false
        val hasValue3 = map.containsValue(3)                     // true
        val hasValue10 = map.containsValue(10)                   // false
        val keyInMap = "A" in map                                // true
        val keyNotInMap = "Z" in map                             // false
        val valueInMap = 3 in map.values                         // true
        val valueNotInMap = 10 in map.values                     // false
        
        // === RANGE ACCESS ===
        val firstEntry = map.entries.first()                     // A=1
        val lastEntry = map.entries.last()                       // E=5
        val firstKey = map.keys.first()                          // A
        val lastKey = map.keys.last()                            // E
        val firstValue = map.values.first()                      // 1
        val lastValue = map.values.last()                        // 5
        
        // === CONDITIONAL ACCESS ===
        val evenValues = map.filterValues { it % 2 == 0 }        // {B=2, D=4}
        val oddValues = map.filterValues { it % 2 == 1 }         // {A=1, C=3, E=5}
        val vowelKeys = map.filterKeys { it in "AEIOU" }         // {A=1, E=5}
        val consonantKeys = map.filterKeys { it !in "AEIOU" }    // {B=2, C=3, D=4}
        val greaterThan3 = map.filterValues { it > 3 }           // {D=4, E=5}
        val lessThan3 = map.filterValues { it < 3 }              // {A=1, B=2}
        
        // === FIND OPERATIONS ===
        val firstEvenValue = map.entries.find { it.value % 2 == 0 } // B=2
        val firstEvenKey = map.keys.find { map[it]!! % 2 == 0 }  // B
        val firstEvenValueKey = map.entries.find { it.value % 2 == 0 }?.key // B
        val firstEvenValueValue = map.entries.find { it.value % 2 == 0 }?.value // 2
        
        // === ITERATION ACCESS ===
        for ((key, value) in map) {
            // Process each key-value pair
        }
        
        map.forEach { (key, value) ->
            // Process each key-value pair
        }
        
        map.forEach { entry ->
            // Process each entry
        }
        
        // === ITERATOR ACCESS ===
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
        
        // === COLLECTION ACCESS ===
        val allKeys = map.keys.toList()                          // [A, B, C, D, E]
        val allValues = map.values.toList()                      // [1, 2, 3, 4, 5]
        val allEntries = map.entries.toList()                    // [A=1, B=2, C=3, D=4, E=5]
        val allPairs = map.toList()                              // [(A,1), (B,2), (C,3), (D,4), (E,5)]
        
        // === SIZE AND EMPTINESS ===
        val size = map.size                                       // 5
        val isEmpty = map.isEmpty()                              // false
        val isNotEmpty = map.isNotEmpty()                        // true
        val keyCount = map.keys.size                             // 5
        val valueCount = map.values.size                         // 5
        val entryCount = map.entries.size                        // 5
        
        // === NESTED MAP ACCESS ===
        val nestedMap = mapOf(
            "group1" to mapOf("A" to 1, "B" to 2),
            "group2" to mapOf("C" to 3, "D" to 4)
        )
        val group1 = nestedMap["group1"]                         // {A=1, B=2}
        val valueAInGroup1 = nestedMap["group1"]?["A"]           // 1
        val valueXInGroup1 = nestedMap["group1"]?.get("X")       // null
        val safeNestedAccess = nestedMap["group1"]?.get("X") ?: -1 // -1
        
        // === TRANSFORMED ACCESS ===
        val doubledValues = map.mapValues { it.value * 2 }       // {A=2, B=4, C=6, D=8, E=10}
        val lowercaseKeys = map.mapKeys { it.key.lowercase() }   // {a=1, b=2, c=3, d=4, e=5}
        val transformedMap = map.map { (key, value) -> 
            "${key}_${value}" to value * 10 
        }.toMap()                                                // {A_1=10, B_2=20, C_3=30, D_4=40, E_5=50}
    }
} 