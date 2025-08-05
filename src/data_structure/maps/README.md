# Map Data Structure

## Overview

A Map is a key-value pair data structure that stores unique keys with associated values. It provides efficient lookup, insertion, and deletion operations, making it ideal for scenarios where you need to associate data with identifiers.

## Key Features

- **Key-Value Pairs**: Each element consists of a unique key and an associated value
- **Efficient Operations**: O(1) average case for lookup, insertion, and deletion
- **Multiple Implementations**: HashMap, LinkedHashMap, TreeMap for different use cases
- **Flexible Creation**: Various factory methods and creation patterns
- **Rich API**: Comprehensive operations for filtering, transformation, and iteration

## Implementation Details

### Core Types

1. **Map<K, V>**: Immutable map interface
2. **MutableMap<K, V>**: Mutable map interface
3. **HashMap<K, V>**: Hash-based implementation for optimal performance
4. **LinkedHashMap<K, V>**: Hash-based implementation that preserves insertion order

### Key Operations

- **Access**: Get values by key with various fallback strategies
- **Modification**: Add, update, remove key-value pairs
- **Iteration**: Traverse keys, values, and entries
- **Filtering**: Filter based on keys, values, or both
- **Transformation**: Transform keys, values, or both

## File Organization

```
maps/
├── MapCreation.kt       # Creation patterns and factory methods
├── MapOperations.kt     # Comprehensive operations and utilities
├── MapAlgorithms.kt     # Algorithms that use maps
├── MapDemo.kt           # Comprehensive demonstrations
└── README.md            # This documentation
```

## Usage Examples

### Basic Operations

```kotlin
// Create maps
val immutableMap = MapCreation.createImmutableMap("A" to 1, "B" to 2, "C" to 3)
val mutableMap = MapCreation.createMutableMap("X" to 10, "Y" to 20)

// Access operations
val value = immutableMap["A"]                    // 1
val defaultValue = immutableMap.getOrDefault("Z", -1)  // -1

// Modification operations
mutableMap["Z"] = 30
mutableMap.put("W", 40)
mutableMap.remove("X")

// Iteration
for ((key, value) in immutableMap) {
    println("$key -> $value")
}
```

### Creation Patterns

```kotlin
// From lists
val keys = listOf("A", "B", "C")
val values = listOf(1, 2, 3)
val map = MapCreation.createMapFromLists(keys, values)

// With default values
val defaultMap = MapCreation.createMapWithDefault(-1, "A" to 1, "B" to 2)

// Frequency counting
val frequency = MapCreation.createFrequencyMap(listOf("A", "B", "A", "C"))

// Grouping
val grouped = MapCreation.createGroupedMap(words) { it.first() }
```

## Common Algorithms

### 1. Frequency Counting

Count occurrences of elements in a collection.

```kotlin
val frequency = MapAlgorithms.countFrequency(elements)
```

### 2. Two Sum

Find two numbers that sum to a target value.

```kotlin
val result = MapAlgorithms.findTwoSum(numbers, target)
```

### 3. Anagram Detection

Check if two strings are anagrams.

```kotlin
val areAnagrams = MapAlgorithms.areAnagrams(str1, str2)
```

### 4. Longest Substring Without Repeats

Find the longest substring with unique characters.

```kotlin
val length = MapAlgorithms.longestSubstringWithoutRepeats(str)
```

### 5. Top K Frequent Elements

Find the most frequent elements.

```kotlin
val topK = MapAlgorithms.topKFrequent(elements, k)
```

## Time Complexity

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Access    | O(1) average   | O(1)             |
| Insertion | O(1) average   | O(1)             |
| Deletion  | O(1) average   | O(1)             |
| Search    | O(1) average   | O(1)             |
| Iteration | O(n)           | O(1)             |

## Space Complexity

- **HashMap**: O(n) for n key-value pairs
- **LinkedHashMap**: O(n) for n key-value pairs
- **TreeMap**: O(n) for n key-value pairs

## Real-World Applications

1. **Caching**: Store computed results for reuse
2. **Configuration Management**: Store application settings
3. **Database Indexing**: Map keys to record locations
4. **Session Management**: Track user sessions
5. **Inventory Systems**: Track product quantities
6. **Frequency Analysis**: Count occurrences of elements
7. **Data Validation**: Store validation rules and results
8. **URL Parameter Parsing**: Parse query string parameters

## Advanced Features

### Default Values

```kotlin
val map = mapOf("A" to 1, "B" to 2).withDefault { -1 }
val value = map["C"]  // Returns -1 instead of null
```

### Bulk Operations

```kotlin
// Merge maps
val merged = map1 + map2

// Filter operations
val filtered = map.filter { it.value > 10 }

// Transform operations
val transformed = map.mapValues { it.value * 2 }
```

### Functional Operations

```kotlin
// Group by property
val grouped = elements.groupBy { it.property }

// Associate with transformation
val associated = keys.associateWith { transform(it) }

// Associate by transformation
val associatedBy = values.associateBy { extractKey(it) }
```

## Running the Demo

To see all features in action:

```kotlin
MapDemo.runDemo()
```

This will demonstrate:
- Basic operations and state changes
- Different creation patterns
- Advanced algorithms
- Real-world applications

## Best Practices

1. **Choose Appropriate Implementation**: Use HashMap for general purpose, LinkedHashMap for ordered access
2. **Handle Null Values**: Use getOrDefault() or getOrElse() for safe access
3. **Consider Key Types**: Ensure keys have proper equals() and hashCode() implementations
4. **Use Bulk Operations**: Leverage filter, map, and transform operations for efficiency
5. **Monitor Performance**: Be aware of hash collisions and resizing overhead

## Common Pitfalls

1. **Null Key/Value Handling**: Always check for null values when accessing maps
2. **Mutable Keys**: Avoid using mutable objects as keys
3. **Memory Leaks**: Large maps should be properly managed and cleared when no longer needed
4. **Hash Collisions**: Poor hashCode() implementations can degrade performance
5. **Concurrent Modification**: Don't modify maps during iteration

## Related Data Structures

- **Set**: Map keys form a set of unique elements
- **List**: Alternative for ordered data without keys
- **Array**: Fixed-size alternative for integer keys
- **TreeMap**: Ordered map implementation
- **LinkedHashMap**: Map that preserves insertion order 