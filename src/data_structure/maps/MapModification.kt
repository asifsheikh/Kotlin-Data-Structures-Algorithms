package data_structure.maps

/**
 * MAP MODIFICATION - Quick Reference
 * All Kotlin map modification methods in one place
 */

object MapModification {
    
    /**
     * All Map Modification Methods
     * Complete reference for modifying maps in Kotlin
     */
    fun allMapModificationMethods() {
        // Create mutable map within function - standalone
        val map = mutableMapOf("A" to 1, "B" to 2, "C" to 3, "D" to 4, "E" to 5)
        
        // === ADD OPERATIONS ===
        map["F"] = 6                                              // {A=1, B=2, C=3, D=4, E=5, F=6}
        map.put("G", 7)                                          // {A=1, B=2, C=3, D=4, E=5, F=6, G=7}
        map.putAll(mapOf("H" to 8, "I" to 9))                   // {A=1, B=2, C=3, D=4, E=5, F=6, G=7, H=8, I=9}
        map += "J" to 10                                         // {A=1, B=2, C=3, D=4, E=5, F=6, G=7, H=8, I=9, J=10}
        map += mapOf("K" to 11, "L" to 12)                      // {A=1, B=2, C=3, D=4, E=5, F=6, G=7, H=8, I=9, J=10, K=11, L=12}
        
        // === UPDATE OPERATIONS ===
        map["A"] = 100                                           // Update existing key
        map.put("B", 200)                                        // Update existing key
        map.replace("C", 300)                                    // Replace existing key
        map.replace("C", 300, 301)                               // Replace only if current value is 300
        map.merge("D", 400) { oldValue, newValue -> oldValue + newValue } // Merge values
        
        // === CONDITIONAL ADD/UPDATE ===
        map.putIfAbsent("A", 999)                                // Won't update (key exists)
        map.putIfAbsent("M", 13)                                 // Will add (key doesn't exist)
        map.getOrPut("N") { 14 }                                 // Get existing or put new value
        map.getOrPut("A") { 999 }                                // Get existing (won't update)
        map.computeIfAbsent("O") { 15 }                          // Compute and put if absent
        map.computeIfPresent("A") { key, value -> value * 2 }    // Compute if present
        
        // === REMOVE OPERATIONS ===
        map.remove("A")                                          // Remove by key
        map.remove("B", 200)                                     // Remove only if value matches
        map -= "C"                                               // Remove by key
        map -= listOf("D", "E")                                  // Remove multiple keys
        map.clear()                                              // Remove all entries
        
        // === BULK OPERATIONS ===
        val newMap = mutableMapOf("X" to 100, "Y" to 200, "Z" to 300)
        newMap.putAll(mapOf("W" to 400, "V" to 500))            // Add multiple entries
        newMap += mapOf("U" to 600, "T" to 700)                 // Add multiple entries
        newMap -= listOf("X", "Y")                              // Remove multiple keys
        newMap.retainAll { (key, value) -> value > 200 }        // Keep only entries with value > 200
        
        // === TRANSFORMATION OPERATIONS ===
        val transformMap = mutableMapOf("A" to 1, "B" to 2, "C" to 3)
        transformMap.replaceAll { key, value -> value * 10 }     // Multiply all values by 10
        transformMap.replaceAll { key, value -> 
            if (key == "A") value * 2 else value 
        }                                                        // Conditional replacement
        
        // === MERGE OPERATIONS ===
        val mergeMap1 = mutableMapOf("A" to 1, "B" to 2)
        val mergeMap2 = mapOf("B" to 20, "C" to 3)
        mergeMap1.putAll(mergeMap2)                              // Merge maps (B will be overwritten)
        
        val mergeMap3 = mutableMapOf("X" to 10, "Y" to 20)
        val mergeMap4 = mapOf("Y" to 200, "Z" to 30)
        mergeMap3.merge("Y", 200) { oldValue, newValue -> oldValue + newValue } // Merge with function
        mergeMap3.merge("Z", 30) { oldValue, newValue -> oldValue + newValue }  // Add new key
        
        // === COMPUTE OPERATIONS ===
        val computeMap = mutableMapOf("A" to 1, "B" to 2)
        computeMap.compute("A") { key, value -> (value ?: 0) * 2 } // Compute new value
        computeMap.compute("C") { key, value -> (value ?: 0) + 10 } // Compute for new key
        computeMap.computeIfAbsent("D") { 5 }                   // Compute if absent
        computeMap.computeIfPresent("B") { key, value -> value * 3 } // Compute if present
        
        // === BATCH OPERATIONS ===
        val batchMap = mutableMapOf<String, Int>()
        batchMap.putAll((1..5).associate { "key$it" to it })     // Add multiple entries
        batchMap.removeAll { (key, value) -> value % 2 == 0 }    // Remove even values
        batchMap.retainAll { (key, value) -> value > 2 }         // Keep values > 2
        
        // === SWAP OPERATIONS ===
        val swapMap = mutableMapOf("A" to 1, "B" to 2)
        val temp = swapMap["A"]
        swapMap["A"] = swapMap["B"]
        swapMap["B"] = temp                                       // Swap values
        
        // === INCREMENT/DECREMENT OPERATIONS ===
        val counterMap = mutableMapOf("A" to 0, "B" to 0, "C" to 0)
        counterMap["A"] = counterMap["A"]!! + 1                  // Increment
        counterMap["B"] = counterMap.getOrDefault("B", 0) + 1    // Safe increment
        counterMap.merge("C", 1) { oldValue, newValue -> oldValue + newValue } // Merge increment
        counterMap["D"] = counterMap.getOrDefault("D", 0) - 1    // Decrement (new key)
        
        // === CONDITIONAL MODIFICATIONS ===
        val conditionalMap = mutableMapOf("A" to 1, "B" to 2, "C" to 3)
        if (conditionalMap["A"]!! > 0) {
            conditionalMap["A"] = conditionalMap["A"]!! * 2
        }
        
        conditionalMap.entries.removeIf { it.value < 2 }         // Remove entries with value < 2
        conditionalMap.entries.removeIf { it.key == "B" }        // Remove entry with key "B"
    }
} 