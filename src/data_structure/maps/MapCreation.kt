package data_structure.maps

/**
 * MAP CREATION
 * 
 * This file contains functions for creating different types of Maps
 * and various creation patterns.
 * 
 * MAP PROPERTIES:
 * - Key-value pair data structure
 * - Unique keys with associated values
 * - Efficient lookup, insertion, and deletion
 * - Supports both mutable and immutable variants
 * 
 * IMPLEMENTATION:
 * - Uses Kotlin's built-in Map implementations
 * - Supports different map types (HashMap, TreeMap, LinkedHashMap)
 * - Provides utility functions for common creation patterns
 */

object MapCreation {
    
    /**
     * Creates a basic immutable map
     * 
     * ALGORITHM:
     * 1. Use mapOf() to create immutable map
     * 2. Returns read-only map with provided key-value pairs
     * 
     * TIME COMPLEXITY: O(1) - Constant time creation
     * SPACE COMPLEXITY: O(n) - n key-value pairs
     * 
     * @param pairs Key-value pairs to initialize the map
     * @return Immutable map with provided pairs
     */
    fun <K, V> createImmutableMap(vararg pairs: Pair<K, V>): Map<K, V> {
        return mapOf(*pairs)
    }
    
    /**
     * Creates a basic mutable map
     * 
     * ALGORITHM:
     * 1. Use mutableMapOf() to create mutable map
     * 2. Returns mutable map with provided key-value pairs
     * 
     * TIME COMPLEXITY: O(1) - Constant time creation
     * SPACE COMPLEXITY: O(n) - n key-value pairs
     * 
     * @param pairs Key-value pairs to initialize the map
     * @return Mutable map with provided pairs
     */
    fun <K, V> createMutableMap(vararg pairs: Pair<K, V>): MutableMap<K, V> {
        return mutableMapOf(*pairs)
    }
    
    /**
     * Creates a HashMap for optimal performance
     * 
     * ALGORITHM:
     * 1. Use HashMap constructor for hash-based implementation
     * 2. Provides O(1) average case for operations
     * 
     * TIME COMPLEXITY: O(1) - Constant time creation
     * SPACE COMPLEXITY: O(n) - n key-value pairs
     * 
     * @param pairs Key-value pairs to initialize the map
     * @return HashMap with provided pairs
     */
    fun <K, V> createHashMap(vararg pairs: Pair<K, V>): HashMap<K, V> {
        return HashMap<K, V>().apply {
            putAll(pairs.toMap())
        }
    }
    
    /**
     * Creates a LinkedHashMap to preserve insertion order
     * 
     * ALGORITHM:
     * 1. Use LinkedHashMap constructor for ordered implementation
     * 2. Maintains insertion order of elements
     * 
     * TIME COMPLEXITY: O(1) - Constant time creation
     * SPACE COMPLEXITY: O(n) - n key-value pairs
     * 
     * @param pairs Key-value pairs to initialize the map
     * @return LinkedHashMap with provided pairs
     */
    fun <K, V> createLinkedHashMap(vararg pairs: Pair<K, V>): LinkedHashMap<K, V> {
        return LinkedHashMap<K, V>().apply {
            putAll(pairs.toMap())
        }
    }
    
    /**
     * Creates a map from two lists (keys and values)
     * 
     * ALGORITHM:
     * 1. Zip keys and values lists together
     * 2. Convert to map using toMap()
     * 
     * TIME COMPLEXITY: O(n) - n elements to zip
     * SPACE COMPLEXITY: O(n) - n key-value pairs
     * 
     * @param keys List of keys
     * @param values List of values
     * @return Map created from keys and values
     */
    fun <K, V> createMapFromLists(keys: List<K>, values: List<V>): Map<K, V> {
        require(keys.size == values.size) { "Keys and values lists must have the same size" }
        return keys.zip(values).toMap()
    }
    
    /**
     * Creates a map from array of key-value pairs
     * 
     * ALGORITHM:
     * 1. Convert array to list of pairs
     * 2. Use toMap() to create map
     * 
     * TIME COMPLEXITY: O(n) - n pairs to process
     * SPACE COMPLEXITY: O(n) - n key-value pairs
     * 
     * @param pairs Array of key-value pairs
     * @return Map created from array
     */
    fun <K, V> createMapFromArray(pairs: Array<Pair<K, V>>): Map<K, V> {
        return pairs.toMap()
    }
    
    /**
     * Creates a map with default values for missing keys
     * 
     * ALGORITHM:
     * 1. Create mutable map with provided pairs
     * 2. Use withDefault() to provide default value
     * 
     * TIME COMPLEXITY: O(1) - Constant time creation
     * SPACE COMPLEXITY: O(n) - n key-value pairs
     * 
     * @param defaultValue Default value for missing keys
     * @param pairs Key-value pairs to initialize the map
     * @return Map with default values
     */
    fun <K, V> createMapWithDefault(defaultValue: V, vararg pairs: Pair<K, V>): Map<K, V> {
        return mapOf(*pairs).withDefault { defaultValue }
    }
    
    /**
     * Creates a map from a sequence of key-value pairs
     * 
     * ALGORITHM:
     * 1. Convert sequence to map using toMap()
     * 2. Efficient for large datasets
     * 
     * TIME COMPLEXITY: O(n) - n pairs to process
     * SPACE COMPLEXITY: O(n) - n key-value pairs
     * 
     * @param pairs Sequence of key-value pairs
     * @return Map created from sequence
     */
    fun <K, V> createMapFromSequence(pairs: Sequence<Pair<K, V>>): Map<K, V> {
        return pairs.toMap()
    }
    
    /**
     * Creates a map by grouping elements by a key function
     * 
     * ALGORITHM:
     * 1. Use groupBy() to group elements by key function
     * 2. Each group becomes a list of values
     * 
     * TIME COMPLEXITY: O(n) - n elements to group
     * SPACE COMPLEXITY: O(n) - n elements
     * 
     * @param elements List of elements to group
     * @param keySelector Function to extract key from element
     * @return Map with grouped elements
     */
    fun <T, K> createGroupedMap(elements: List<T>, keySelector: (T) -> K): Map<K, List<T>> {
        return elements.groupBy(keySelector)
    }
    
    /**
     * Creates a map by associating elements with values
     * 
     * ALGORITHM:
     * 1. Use associate() to create key-value pairs
     * 2. Each element becomes a key-value pair
     * 
     * TIME COMPLEXITY: O(n) - n elements to associate
     * SPACE COMPLEXITY: O(n) - n key-value pairs
     * 
     * @param elements List of elements
     * @param transform Function to transform element to key-value pair
     * @return Map created from elements
     */
    fun <T, K, V> createAssociatedMap(elements: List<T>, transform: (T) -> Pair<K, V>): Map<K, V> {
        return elements.associate(transform)
    }
    
    /**
     * Creates a map with keys mapped to transformed values
     * 
     * ALGORITHM:
     * 1. Use associateWith() to create key-value pairs
     * 2. Each element becomes a key with transformed value
     * 
     * TIME COMPLEXITY: O(n) - n elements to process
     * SPACE COMPLEXITY: O(n) - n key-value pairs
     * 
     * @param keys List of keys
     * @param valueSelector Function to transform key to value
     * @return Map with keys and transformed values
     */
    fun <K, V> createMapWithTransformedValues(keys: List<K>, valueSelector: (K) -> V): Map<K, V> {
        return keys.associateWith(valueSelector)
    }
    
    /**
     * Creates a map with values mapped to transformed keys
     * 
     * ALGORITHM:
     * 1. Use associateBy() to create key-value pairs
     * 2. Each element becomes a value with transformed key
     * 
     * TIME COMPLEXITY: O(n) - n elements to process
     * SPACE COMPLEXITY: O(n) - n key-value pairs
     * 
     * @param values List of values
     * @param keySelector Function to transform value to key
     * @return Map with transformed keys and values
     */
    fun <K, V> createMapWithTransformedKeys(values: List<V>, keySelector: (V) -> K): Map<K, V> {
        return values.associateBy(keySelector)
    }
    
    /**
     * Creates a frequency map (count of occurrences)
     * 
     * ALGORITHM:
     * 1. Use groupingBy() and eachCount() to count occurrences
     * 2. Each element becomes a key with its count as value
     * 
     * TIME COMPLEXITY: O(n) - n elements to count
     * SPACE COMPLEXITY: O(k) - k unique elements
     * 
     * @param elements List of elements to count
     * @return Map with element counts
     */
    fun <T> createFrequencyMap(elements: List<T>): Map<T, Int> {
        return elements.groupingBy { it }.eachCount()
    }
    
    /**
     * Creates a map from a range of integers
     * 
     * ALGORITHM:
     * 1. Convert range to list
     * 2. Use associateWith() to create key-value pairs
     * 
     * TIME COMPLEXITY: O(n) - n elements in range
     * SPACE COMPLEXITY: O(n) - n key-value pairs
     * 
     * @param range Range of integers
     * @param valueSelector Function to transform integer to value
     * @return Map with integer keys and transformed values
     */
    fun <V> createMapFromRange(range: IntRange, valueSelector: (Int) -> V): Map<Int, V> {
        return range.associateWith(valueSelector)
    }
    
    /**
     * Creates a map with initial capacity for performance
     * 
     * ALGORITHM:
     * 1. Create HashMap with specified initial capacity
     * 2. Add provided pairs
     * 
     * TIME COMPLEXITY: O(1) - Constant time creation
     * SPACE COMPLEXITY: O(capacity) - Initial capacity
     * 
     * @param initialCapacity Initial capacity of the map
     * @param pairs Key-value pairs to initialize the map
     * @return HashMap with specified capacity
     */
    fun <K, V> createMapWithCapacity(initialCapacity: Int, vararg pairs: Pair<K, V>): HashMap<K, V> {
        return HashMap<K, V>(initialCapacity).apply {
            putAll(pairs.toMap())
        }
    }
    
    /**
     * Demonstrates map creation patterns
     */
    fun demonstrateMapCreation() {
        println("=== MAP CREATION DEMONSTRATION ===\n")
        
        // 1. Basic map creation
        println("1. BASIC MAP CREATION")
        val basicMap = createImmutableMap("A" to 1, "B" to 2, "C" to 3)
        val mutableMap = createMutableMap("X" to 10, "Y" to 20)
        println("Immutable map: $basicMap")
        println("Mutable map: $mutableMap")
        println()
        
        // 2. Different map types
        println("2. DIFFERENT MAP TYPES")
        val hashMap = createHashMap("A" to 1, "B" to 2)
        val linkedHashMap = createLinkedHashMap("A" to 1, "B" to 2, "C" to 3)
        println("HashMap: $hashMap")
        println("LinkedHashMap: $linkedHashMap")
        println()
        
        // 3. From lists
        println("3. FROM LISTS")
        val keys = listOf("A", "B", "C")
        val values = listOf(1, 2, 3)
        val listMap = createMapFromLists(keys, values)
        println("Keys: $keys")
        println("Values: $values")
        println("Map from lists: $listMap")
        println()
        
        // 4. With default values
        println("4. WITH DEFAULT VALUES")
        val defaultMap = createMapWithDefault(-1, "A" to 1, "B" to 2)
        println("Map with default -1: $defaultMap")
        println("Accessing missing key: ${defaultMap["C"]}")
        println()
        
        // 5. Grouped map
        println("5. GROUPED MAP")
        val words = listOf("apple", "banana", "apricot", "blueberry", "cherry")
        val groupedMap = createGroupedMap(words) { it.first() }
        println("Words: $words")
        println("Grouped by first letter: $groupedMap")
        println()
        
        // 6. Associated map
        println("6. ASSOCIATED MAP")
        val numbers = listOf(1, 2, 3, 4, 5)
        val associatedMap = createAssociatedMap(numbers) { it to (it * it) }
        println("Numbers: $numbers")
        println("Squares: $associatedMap")
        println()
        
        // 7. Frequency map
        println("7. FREQUENCY MAP")
        val letters = listOf("A", "B", "A", "C", "B", "A")
        val frequencyMap = createFrequencyMap(letters)
        println("Letters: $letters")
        println("Frequency: $frequencyMap")
        println()
        
        // 8. Range map
        println("8. RANGE MAP")
        val rangeMap = createMapFromRange(1..5) { it * 10 }
        println("Range 1..5 mapped to multiples of 10: $rangeMap")
        println()
        
        // 9. Map with capacity
        println("9. MAP WITH CAPACITY")
        val capacityMap = createMapWithCapacity(10, "A" to 1, "B" to 2)
        println("Map with initial capacity 10: $capacityMap")
        println()
        
        println("=== MAP CREATION DEMONSTRATION COMPLETE ===\n")
    }
} 