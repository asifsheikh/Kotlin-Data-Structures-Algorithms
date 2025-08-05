package data_structure.maps

/**
 * MAP OPERATIONS
 * 
 * This file contains comprehensive operations on Maps including
 * access, modification, iteration, filtering, and utility operations.
 * 
 * COMMON MAP OPERATIONS:
 * - Access: Get values by key with various fallback strategies
 * - Modification: Add, update, remove key-value pairs
 * - Iteration: Traverse keys, values, and entries
 * - Filtering: Filter based on keys, values, or both
 * - Transformation: Transform keys, values, or both
 * - Utility: Size, emptiness, containment checks
 */

object MapOperations {
    
    /**
     * Demonstrates comprehensive map operations
     */
    fun demonstrateMapOperations() {
        println("=== MAP OPERATIONS DEMONSTRATION ===\n")
        
        // Create maps for demonstration
        val immutableMap = mapOf("A" to 1, "B" to 2, "C" to 3, "D" to 4, "E" to 5)
        val mutableMap = mutableMapOf("X" to 10, "Y" to 20, "Z" to 30)
        
        // 1. Basic access operations
        demonstrateAccessOperations(immutableMap)
        
        // 2. Modification operations
        demonstrateModificationOperations(mutableMap)
        
        // 3. Iteration operations
        demonstrateIterationOperations(immutableMap)
        
        // 4. Filtering operations
        demonstrateFilteringOperations(immutableMap)
        
        // 5. Transformation operations
        demonstrateTransformationOperations(immutableMap)
        
        // 6. Utility operations
        demonstrateUtilityOperations(immutableMap)
        
        // 7. Advanced operations
        demonstrateAdvancedOperations(immutableMap)
        
        println("=== MAP OPERATIONS DEMONSTRATION COMPLETE ===\n")
    }
    
    /**
     * Demonstrates access operations on maps
     */
    private fun demonstrateAccessOperations(map: Map<String, Int>) {
        println("1. ACCESS OPERATIONS")
        println("Map: $map")
        
        // Basic access
        println("   map[\"A\"]: ${map["A"]}")
        println("   map[\"Z\"]: ${map["Z"]}") // null for missing key
        
        // Safe access with defaults
        println("   getOrDefault(\"Z\", -1): ${map.getOrDefault("Z", -1)}")
        println("   getOrElse(\"Z\") { -1 }: ${map.getOrElse("Z") { -1 }}")
        
        // Multiple key access
        val keys = listOf("A", "B", "Z")
        val values = keys.map { map[it] }
        println("   Multiple keys $keys: $values")
        
        // Check containment
        println("   containsKey(\"A\"): ${map.containsKey("A")}")
        println("   containsValue(3): ${map.containsValue(3)}")
        println("   \"A\" in map: ${"A" in map}")
        println("   3 in map.values: ${3 in map.values}")
        println()
    }
    
    /**
     * Demonstrates modification operations on mutable maps
     */
    private fun demonstrateModificationOperations(map: MutableMap<String, Int>) {
        println("2. MODIFICATION OPERATIONS")
        println("Initial map: $map")
        
        // Add/Update operations
        map["X"] = 100 // Direct assignment
        println("   After map[\"X\"] = 100: $map")
        
        map.put("W", 40) // put() method
        println("   After put(\"W\", 40): $map")
        
        map.putIfAbsent("X", 500) // Only add if key doesn't exist
        println("   After putIfAbsent(\"X\", 500): $map")
        
        map.getOrPut("P") { 900 } // Get existing or put new value
        println("   After getOrPut(\"P\") { 900 }: $map")
        
        // Remove operations
        map.remove("W")
        println("   After remove(\"W\"): $map")
        
        map.remove("X", 999) // Remove only if value matches
        println("   After remove(\"X\", 999): $map")
        
        map.remove("X", 100) // Remove if value matches
        println("   After remove(\"X\", 100): $map")
        
        // Bulk operations
        map.putAll(mapOf("Q" to 50, "R" to 60))
        println("   After putAll: $map")
        
        // Clear
        map.clear()
        println("   After clear(): $map")
        println()
    }
    
    /**
     * Demonstrates iteration operations on maps
     */
    private fun demonstrateIterationOperations(map: Map<String, Int>) {
        println("3. ITERATION OPERATIONS")
        println("Map: $map")
        
        // Iterate over entries
        println("   Iterating over entries:")
        for ((key, value) in map) {
            println("     $key -> $value")
        }
        
        // Iterate over keys
        println("   Keys: ${map.keys}")
        for (key in map.keys) {
            println("     Key: $key")
        }
        
        // Iterate over values
        println("   Values: ${map.values}")
        for (value in map.values) {
            println("     Value: $value")
        }
        
        // Iterate with index
        println("   Iterating with index:")
        map.entries.forEachIndexed { index, (key, value) ->
            println("     $index: $key -> $value")
        }
        
        // Functional iteration
        println("   Functional iteration:")
        map.forEach { (key, value) ->
            println("     $key -> $value")
        }
        println()
    }
    
    /**
     * Demonstrates filtering operations on maps
     */
    private fun demonstrateFilteringOperations(map: Map<String, Int>) {
        println("4. FILTERING OPERATIONS")
        println("Map: $map")
        
        // Filter by value
        val evenValues = map.filter { it.value % 2 == 0 }
        println("   Even values: $evenValues")
        
        // Filter by key
        val keysStartingWithA = map.filterKeys { it.startsWith("A") }
        println("   Keys starting with 'A': $keysStartingWithA")
        
        // Filter by both key and value
        val filtered = map.filter { (key, value) -> key.length == 1 && value > 2 }
        println("   Single letter keys with value > 2: $filtered")
        
        // Filter values
        val valuesGreaterThan3 = map.filterValues { it > 3 }
        println("   Values > 3: $valuesGreaterThan3")
        
        // Take first N entries
        val firstTwo = map.entries.take(2).associate { it.key to it.value }
        println("   First 2 entries: $firstTwo")
        
        // Drop first N entries
        val lastThree = map.entries.drop(2).associate { it.key to it.value }
        println("   Last 3 entries: $lastThree")
        println()
    }
    
    /**
     * Demonstrates transformation operations on maps
     */
    private fun demonstrateTransformationOperations(map: Map<String, Int>) {
        println("5. TRANSFORMATION OPERATIONS")
        println("Map: $map")
        
        // Transform values
        val doubledValues = map.mapValues { it.value * 2 }
        println("   Doubled values: $doubledValues")
        
        // Transform keys
        val uppercaseKeys = map.mapKeys { it.key.uppercase() }
        println("   Uppercase keys: $uppercaseKeys")
        
        // Transform both keys and values
        val transformed = map.map { (key, value) -> key.lowercase() to value.toString() }
        println("   Lowercase keys, string values: $transformed")
        
        // Flat map transformation
        val flatMapped = map.flatMap { (key, value) -> 
            listOf(key to value, key.uppercase() to value * 10)
        }.toMap()
        println("   Flat mapped: $flatMapped")
        
        // Associate with transformation
        val associated = map.keys.associateWith { key -> map[key]?.times(3) ?: 0 }
        println("   Associated with tripled values: $associated")
        
        // Associate by transformation
        val associatedBy = map.values.associateBy { it.toString() }
        println("   Associated by string values: $associatedBy")
        println()
    }
    
    /**
     * Demonstrates utility operations on maps
     */
    private fun demonstrateUtilityOperations(map: Map<String, Int>) {
        println("6. UTILITY OPERATIONS")
        println("Map: $map")
        
        // Size and emptiness
        println("   Size: ${map.size}")
        println("   Is empty: ${map.isEmpty()}")
        println("   Is not empty: ${map.isNotEmpty()}")
        
        // Min/Max operations
        println("   Min key: ${map.keys.minOrNull()}")
        println("   Max key: ${map.keys.maxOrNull()}")
        println("   Min value: ${map.values.minOrNull()}")
        println("   Max value: ${map.values.maxOrNull()}")
        
        // Sum and average
        println("   Sum of values: ${map.values.sum()}")
        println("   Average of values: ${map.values.average()}")
        
        // Count operations
        println("   Count of even values: ${map.values.count { it % 2 == 0 }}")
        println("   Count of single letter keys: ${map.keys.count { it.length == 1 }}")
        
        // Any/All operations
        println("   Any value > 4: ${map.values.any { it > 4 }}")
        println("   All values > 0: ${map.values.all { it > 0 }}")
        println("   None value < 0: ${map.values.none { it < 0 }}")
        
        // Find operations
        println("   First entry with value > 3: ${map.entries.find { it.value > 3 }}")
        println("   Last entry with single letter key: ${map.entries.findLast { it.key.length == 1 }}")
        println()
    }
    
    /**
     * Demonstrates advanced operations on maps
     */
    private fun demonstrateAdvancedOperations(map: Map<String, Int>) {
        println("7. ADVANCED OPERATIONS")
        println("Map: $map")
        
        // Group by
        val groupedByValue = map.entries.groupBy { it.value }
        println("   Grouped by value: $groupedByValue")
        
        // Partition
        val (even, odd) = map.entries.partition { it.value % 2 == 0 }
        println("   Even entries: $even")
        println("   Odd entries: $odd")
        
        // Sort operations
        val sortedByKey = map.toSortedMap()
        println("   Sorted by key: $sortedByKey")
        
        val sortedByValue = map.entries.sortedBy { it.value }.associate { it.key to it.value }
        println("   Sorted by value: $sortedByValue")
        
        // Reverse map (values must be unique)
        val reversed = map.entries.associate { (k, v) -> v to k }
        println("   Reversed map: $reversed")
        
        // Merge maps
        val otherMap = mapOf("F" to 6, "G" to 7)
        val merged = map + otherMap
        println("   Merged with $otherMap: $merged")
        
        // Intersection
        val intersection = map.filterKeys { it in otherMap.keys }
        println("   Intersection with $otherMap: $intersection")
        
        // Union with conflict resolution
        val union = map + otherMap
        println("   Union with $otherMap: $union")
        println()
    }
    
    /**
     * Demonstrates map algorithms and patterns
     */
    fun demonstrateMapAlgorithms() {
        println("=== MAP ALGORITHMS DEMONSTRATION ===\n")
        
        // 1. Frequency counting
        println("1. FREQUENCY COUNTING")
        val words = listOf("apple", "banana", "apple", "cherry", "banana", "apple")
        val frequency = words.groupingBy { it }.eachCount()
        println("   Words: $words")
        println("   Frequency: $frequency")
        println()
        
        // 2. Grouping by property
        println("2. GROUPING BY PROPERTY")
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val groupedByParity = numbers.groupBy { if (it % 2 == 0) "even" else "odd" }
        println("   Numbers: $numbers")
        println("   Grouped by parity: $groupedByParity")
        println()
        
        // 3. Inverting map
        println("3. INVERTING MAP")
        val original = mapOf("A" to 1, "B" to 2, "C" to 3)
        val inverted = original.entries.associate { (k, v) -> v to k }
        println("   Original: $original")
        println("   Inverted: $inverted")
        println()
        
        // 4. Map merging with custom logic
        println("4. MAP MERGING WITH CUSTOM LOGIC")
        val map1 = mapOf("A" to 1, "B" to 2)
        val map2 = mapOf("B" to 3, "C" to 4)
        val merged = (map1.keys + map2.keys).associateWith { key ->
            when {
                key in map1 && key in map2 -> map1[key]!! + map2[key]!!
                key in map1 -> map1[key]!!
                else -> map2[key]!!
            }
        }
        println("   Map1: $map1")
        println("   Map2: $map2")
        println("   Merged (sum): $merged")
        println()
        
        // 5. Nested map operations
        println("5. NESTED MAP OPERATIONS")
        val nested = mapOf(
            "fruits" to mapOf("apple" to 5, "banana" to 3),
            "vegetables" to mapOf("carrot" to 2, "lettuce" to 1)
        )
        println("   Nested map: $nested")
        println("   All items: ${nested.values.flatMap { it.entries }}")
        println("   Total count: ${nested.values.sumOf { it.values.sum() }}")
        println()
        
        // 6. Map caching pattern
        println("6. MAP CACHING PATTERN")
        val cache = mutableMapOf<Int, String>()
        fun expensiveOperation(n: Int): String {
            return cache.getOrPut(n) { "Result for $n" }
        }
        println("   Cache before: $cache")
        println("   expensiveOperation(5): ${expensiveOperation(5)}")
        println("   expensiveOperation(5): ${expensiveOperation(5)}") // Uses cache
        println("   Cache after: $cache")
        println()
        
        // 7. Map-based memoization
        println("7. MAP-BASED MEMOIZATION")
        val memo = mutableMapOf<Int, Int>()
        fun fibonacci(n: Int): Int {
            return memo.getOrPut(n) {
                when (n) {
                    0, 1 -> n
                    else -> fibonacci(n - 1) + fibonacci(n - 2)
                }
            }
        }
        println("   fibonacci(10): ${fibonacci(10)}")
        println("   Memo size: ${memo.size}")
        println()
        
        println("=== MAP ALGORITHMS DEMONSTRATION COMPLETE ===\n")
    }
}
