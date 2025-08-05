package data_structure.maps

/**
 * MAP CREATION - Quick Reference
 * All Kotlin map creation methods in one place
 */

object MapCreation {
    
    /**
     * All Map Creation Methods
     * Complete reference for creating maps in Kotlin
     */
    fun allMapCreationMethods() {
        // === BASIC MAP CREATION ===
        val emptyMap = emptyMap<String, Int>()                    // {}
        val immutableMap = mapOf("A" to 1, "B" to 2, "C" to 3)   // {A=1, B=2, C=3}
        val mutableMap = mutableMapOf("X" to 10, "Y" to 20)      // {X=10, Y=20}
        val hashMap = HashMap<String, Int>()                      // {}
        val linkedHashMap = LinkedHashMap<String, Int>()          // {}
        val treeMap = sortedMapOf("Z" to 30, "A" to 1)           // {A=1, Z=30}
        
        // === FROM COLLECTIONS ===
        val keys = listOf("a", "b", "c")
        val values = listOf(1, 2, 3)
        val fromLists = keys.zip(values).toMap()                  // {a=1, b=2, c=3}
        val fromPairs = listOf("x" to 10, "y" to 20).toMap()     // {x=10, y=20}
        val fromArray = arrayOf("p" to 100, "q" to 200).toMap()  // {p=100, q=200}
        
        // === FROM OTHER MAPS ===
        val originalMap = mapOf("A" to 1, "B" to 2)
        val copyMap = originalMap.toMap()                         // {A=1, B=2}
        val mutableCopy = originalMap.toMutableMap()              // {A=1, B=2}
        val hashMapCopy = HashMap(originalMap)                    // {A=1, B=2}
        val linkedCopy = LinkedHashMap(originalMap)               // {A=1, B=2}
        
        // === WITH DEFAULT VALUES ===
        val withDefault = mapOf("A" to 1).withDefault { -1 }     // Default value -1
        val mutableWithDefault = mutableMapOf<String, Int>().withDefault { 0 } // Default value 0
        val hashWithDefault = HashMap<String, Int>().withDefault { 42 }        // Default value 42
        
        // === FROM SEQUENCES ===
        val sequenceMap = (1..5).associate { it to it * 2 }      // {1=2, 2=4, 3=6, 4=8, 5=10}
        val sequenceMapBy = (1..5).associateBy { it * 2 }        // {2=1, 4=2, 6=3, 8=4, 10=5}
        val sequenceMapWith = (1..5).associateWith { it * 2 }    // {1=2, 2=4, 3=6, 4=8, 5=10}
        
        // === FROM RANGES ===
        val rangeMap = (1..3).associate { it to "value$it" }     // {1=value1, 2=value2, 3=value3}
        val rangeMapBy = (1..3).associateBy { "key$it" }         // {key1=1, key2=2, key3=3}
        val rangeMapWith = (1..3).associateWith { "value$it" }   // {1=value1, 2=value2, 3=value3}
        
        // === SPECIAL PATTERNS ===
        val identityMap = (1..5).associateWith { it }            // {1=1, 2=2, 3=3, 4=4, 5=5}
        val reverseMap = (1..5).associate { it to (6 - it) }     // {1=5, 2=4, 3=3, 4=2, 5=1}
        val squareMap = (1..5).associateWith { it * it }         // {1=1, 2=4, 3=9, 4=16, 5=25}
        val charMap = ('a'..'e').associateWith { it.code }       // {a=97, b=98, c=99, d=100, e=101}
        
        // === WITH FUNCTIONS ===
        val functionMap = (1..5).associate { 
            "key$it" to { it * 2 } 
        }                                                        // {key1=() -> Int, key2=() -> Int, ...}
        val conditionalMap = (1..10).associateWith { 
            if (it % 2 == 0) "even" else "odd" 
        }                                                        // {1=odd, 2=even, 3=odd, ...}
        val transformedMap = (1..5).associate { 
            "K$it" to "V${it * 10}" 
        }                                                        // {K1=V10, K2=V20, K3=V30, ...}
        
        // === FROM STRINGS ===
        val charCountMap = "hello".associateWith { 1 }           // {h=1, e=1, l=1, o=1}
        val charIndexMap = "hello".withIndex().associate { 
            it.value to it.index 
        }                                                        // {h=0, e=1, l=2, o=4}
        val wordLengthMap = listOf("apple", "banana", "cherry").associateWith { it.length } // {apple=5, banana=6, cherry=6}
        
        // === FROM ARRAYS ===
        val arrayMap = intArrayOf(1, 2, 3).associateWith { it * 10 } // {1=10, 2=20, 3=30}
        val arrayMapBy = intArrayOf(10, 20, 30).associateBy { it / 10 } // {1=10, 2=20, 3=30}
        val arrayMapWith = intArrayOf(1, 2, 3).associateWith { "value$it" } // {1=value1, 2=value2, 3=value3}
        
        // === NESTED MAPS ===
        val nestedMap = mapOf(
            "group1" to mapOf("A" to 1, "B" to 2),
            "group2" to mapOf("C" to 3, "D" to 4)
        )                                                        // {group1={A=1, B=2}, group2={C=3, D=4}}
        
        // === MAP WITH CAPACITY ===
        val capacityMap = HashMap<String, Int>(16)               // Initial capacity 16
        val loadFactorMap = HashMap<String, Int>(16, 0.75f)      // Capacity 16, load factor 0.75
    }
} 