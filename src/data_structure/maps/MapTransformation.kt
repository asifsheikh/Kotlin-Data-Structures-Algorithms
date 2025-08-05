package data_structure.maps

/**
 * MAP TRANSFORMATION - Quick Reference
 * All Kotlin map transformation methods in one place
 */

object MapTransformation {
    
    /**
     * All Map Transformation Methods
     * Complete reference for transforming maps in Kotlin
     */
    fun allMapTransformationMethods() {
        // Create map within function - standalone
        val map = mapOf("A" to 1, "B" to 2, "C" to 3, "D" to 4, "E" to 5)
        
        // === KEY TRANSFORMATIONS ===
        val uppercaseKeys = map.mapKeys { it.key.uppercase() }   // {A=1, B=2, C=3, D=4, E=5}
        val lowercaseKeys = map.mapKeys { it.key.lowercase() }   // {a=1, b=2, c=3, d=4, e=5}
        val prefixedKeys = map.mapKeys { "key_${it.key}" }       // {key_A=1, key_B=2, key_C=3, key_D=4, key_E=5}
        val suffixedKeys = map.mapKeys { "${it.key}_value" }     // {A_value=1, B_value=2, C_value=3, D_value=4, E_value=5}
        val numberedKeys = map.mapKeys { "key${it.value}" }      // {key1=1, key2=2, key3=3, key4=4, key5=5}
        
        // === VALUE TRANSFORMATIONS ===
        val doubledValues = map.mapValues { it.value * 2 }       // {A=2, B=4, C=6, D=8, E=10}
        val squaredValues = map.mapValues { it.value * it.value } // {A=1, B=4, C=9, D=16, E=25}
        val stringValues = map.mapValues { "value_${it.value}" }  // {A=value_1, B=value_2, C=value_3, D=value_4, E=value_5}
        val conditionalValues = map.mapValues { 
            if (it.value % 2 == 0) "even" else "odd" 
        }                                                        // {A=odd, B=even, C=odd, D=even, E=odd}
        
        // === BOTH KEY AND VALUE TRANSFORMATIONS ===
        val transformedMap = map.map { (key, value) -> 
            "${key}_${value}" to value * 10 
        }.toMap()                                                // {A_1=10, B_2=20, C_3=30, D_4=40, E_5=50}
        val swappedMap = map.map { (key, value) -> 
            value to key 
        }.toMap()                                                // {1=A, 2=B, 3=C, 4=D, 5=E}
        val complexTransform = map.map { (key, value) -> 
            "new_${key}_${value}" to "transformed_${value * 2}" 
        }.toMap()                                                // {new_A_1=transformed_2, new_B_2=transformed_4, ...}
        
        // === FILTERING TRANSFORMATIONS ===
        val evenValues = map.filterValues { it % 2 == 0 }        // {B=2, D=4}
        val oddValues = map.filterValues { it % 2 == 1 }         // {A=1, C=3, E=5}
        val vowelKeys = map.filterKeys { it in "AEIOU" }         // {A=1, E=5}
        val consonantKeys = map.filterKeys { it !in "AEIOU" }    // {B=2, C=3, D=4}
        val greaterThan3 = map.filterValues { it > 3 }           // {D=4, E=5}
        val lessThan3 = map.filterValues { it < 3 }              // {A=1, B=2}
        val combinedFilter = map.filter { (key, value) -> 
            key in "ABC" && value > 2 
        }                                                        // {C=3}
        
        // === FLATTENING TRANSFORMATIONS ===
        val nestedMap = mapOf(
            "group1" to mapOf("A" to 1, "B" to 2),
            "group2" to mapOf("C" to 3, "D" to 4)
        )
        val flattenedMap = nestedMap.flatMap { (groupKey, innerMap) ->
            innerMap.map { (key, value) -> "${groupKey}_$key" to value }
        }.toMap()                                                // {group1_A=1, group1_B=2, group2_C=3, group2_D=4}
        
        val flattenedKeys = nestedMap.flatMap { (groupKey, innerMap) ->
            innerMap.keys.map { "${groupKey}_$it" }
        }                                                        // [group1_A, group1_B, group2_C, group2_D]
        
        val flattenedValues = nestedMap.flatMap { (_, innerMap) ->
            innerMap.values
        }                                                        // [1, 2, 3, 4]
        
        // === GROUPING TRANSFORMATIONS ===
        val words = listOf("apple", "banana", "apricot", "blueberry", "cherry")
        val groupedByLength = words.groupBy { it.length }        // {5=[apple, cherry], 6=[banana], 7=[apricot], 9=[blueberry]}
        val groupedByFirstLetter = words.groupBy { it.first() }  // {a=[apple, apricot], b=[banana, blueberry], c=[cherry]}
        
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val groupedByEvenOdd = numbers.groupBy { it % 2 == 0 }   // {false=[1, 3, 5, 7, 9], true=[2, 4, 6, 8, 10]}
        val groupedByRange = numbers.groupBy { 
            when {
                it <= 3 -> "small"
                it <= 7 -> "medium"
                else -> "large"
            }
        }                                                        // {small=[1, 2, 3], medium=[4, 5, 6, 7], large=[8, 9, 10]}
        
        // === ASSOCIATION TRANSFORMATIONS ===
        val associateMap = words.associate { it to it.length }   // {apple=5, banana=6, apricot=7, blueberry=9, cherry=6}
        val associateByMap = words.associateBy { it.first() }    // {a=apple, b=banana, c=cherry} (last wins for duplicates)
        val associateWithMap = words.associateWith { it.length } // {apple=5, banana=6, apricot=7, blueberry=9, cherry=6}
        
        // === MERGING TRANSFORMATIONS ===
        val map1 = mapOf("A" to 1, "B" to 2)
        val map2 = mapOf("B" to 20, "C" to 3)
        val mergedMap = map1 + map2                              // {A=1, B=20, C=3} (map2 values override)
        val mergedWithFunction = map1.merge(map2) { oldValue, newValue -> 
            oldValue + newValue 
        }                                                        // {A=1, B=22, C=3}
        
        // === SORTING TRANSFORMATIONS ===
        val sortedByKey = map.toSortedMap()                      // {A=1, B=2, C=3, D=4, E=5}
        val sortedByValue = map.toList().sortedBy { it.second }.toMap() // {A=1, B=2, C=3, D=4, E=5}
        val sortedByKeyDesc = map.toList().sortedByDescending { it.first }.toMap() // {E=5, D=4, C=3, B=2, A=1}
        val sortedByValueDesc = map.toList().sortedByDescending { it.second }.toMap() // {E=5, D=4, C=3, B=2, A=1}
        
        // === CONVERSION TRANSFORMATIONS ===
        val toList = map.toList()                                // [(A,1), (B,2), (C,3), (D,4), (E,5)]
        val toSet = map.toSet()                                  // {(A,1), (B,2), (C,3), (D,4), (E,5)}
        val keysList = map.keys.toList()                         // [A, B, C, D, E]
        val valuesList = map.values.toList()                     // [1, 2, 3, 4, 5]
        val entriesList = map.entries.toList()                   // [A=1, B=2, C=3, D=4, E=5]
        
        // === INVERTING TRANSFORMATIONS ===
        val invertedMap = map.entries.associate { it.value to it.key } // {1=A, 2=B, 3=C, 4=D, 5=E}
        val invertedWithDuplicates = mapOf("A" to 1, "B" to 1, "C" to 2).entries
            .groupBy { it.value }
            .mapValues { it.value.map { entry -> entry.key } }   // {1=[A, B], 2=[C]}
        
        // === CHUNKING TRANSFORMATIONS ===
        val largeMap = (1..10).associateWith { it * 2 }          // {1=2, 2=4, 3=6, 4=8, 5=10, 6=12, 7=14, 8=16, 9=18, 10=20}
        val chunkedMap = largeMap.toList().chunked(3)            // [[(1,2), (2,4), (3,6)], [(4,8), (5,10), (6,12)], [(7,14), (8,16), (9,18)], [(10,20)]]
        val chunkedMapOfMaps = chunkedMap.map { it.toMap() }     // [{1=2, 2=4, 3=6}, {4=8, 5=10, 6=12}, {7=14, 8=16, 9=18}, {10=20}]
    }
} 