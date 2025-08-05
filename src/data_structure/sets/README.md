# Set Data Structure

## Overview

A Set is a collection of unique elements that provides efficient membership testing, insertion, and deletion operations. It automatically handles duplicates by ensuring each element appears only once, making it ideal for scenarios where uniqueness is required.

## Key Features

- **Unique Elements**: No duplicate values allowed
- **Efficient Operations**: O(1) average case for membership testing, insertion, and deletion
- **Multiple Implementations**: HashSet, LinkedHashSet, TreeSet for different use cases
- **Flexible Creation**: Various factory methods and creation patterns
- **Rich API**: Comprehensive operations for set algebra and manipulation

## Implementation Details

### Core Types

1. **Set<T>**: Immutable set interface
2. **MutableSet<T>**: Mutable set interface
3. **HashSet<T>**: Hash-based implementation for optimal performance
4. **LinkedHashSet<T>**: Hash-based implementation that preserves insertion order

### Key Operations

- **Membership**: Check if element exists in set
- **Modification**: Add, remove elements
- **Set Operations**: Union, intersection, difference
- **Iteration**: Traverse all elements
- **Filtering**: Filter based on conditions
- **Transformation**: Transform elements

## File Organization

```
sets/
├── SetCreation.kt       # Creation patterns and factory methods
├── SetOperations.kt     # Comprehensive operations and utilities
├── SetAlgorithms.kt     # Algorithms that use sets
├── SetDemo.kt           # Comprehensive demonstrations
└── README.md            # This documentation
```

## Usage Examples

### Basic Operations

```kotlin
// Create sets
val immutableSet = SetCreation.createImmutableSet(1, 2, 3, 4, 5)
val mutableSet = SetCreation.createMutableSet("A", "B", "C")

// Membership testing
val contains = immutableSet.contains(3)  // true
val isMember = 3 in immutableSet         // true

// Modification
mutableSet.add("D")
mutableSet.remove("A")

// Set operations
val union = set1 union set2
val intersection = set1 intersect set2
val difference = set1 subtract set2
```

### Creation Patterns

```kotlin
// From collections
val list = listOf(1, 2, 2, 3, 3, 4)
val set = SetCreation.createSetFromList(list)  // Removes duplicates

// From range
val rangeSet = SetCreation.createSetFromRange(1..10)

// Filtered set
val evenSet = SetCreation.createFilteredSet(numbers) { it % 2 == 0 }

// Special sets
val primeSet = SetCreation.createPrimeSet(20)
val fibonacciSet = SetCreation.createFibonacciSet(20)
```

## Common Algorithms

### 1. Duplicate Removal

Remove duplicates from a collection.

```kotlin
val uniqueList = SetAlgorithms.removeDuplicates(listWithDuplicates)
```

### 2. Common Elements

Find elements common to multiple collections.

```kotlin
val common = SetAlgorithms.findCommonElements(list1, list2)
```

### 3. Unique Elements

Find elements unique to one collection.

```kotlin
val unique = SetAlgorithms.findUniqueElements(list1, list2)
```

### 4. Subset Checking

Check if one collection is a subset of another.

```kotlin
val isSubset = SetAlgorithms.isSubset(subset, superset)
```

### 5. Power Set Generation

Generate all possible subsets of a set.

```kotlin
val powerSet = SetAlgorithms.generatePowerSet(set)
```

## Time Complexity

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Membership | O(1) average   | O(1)             |
| Insertion | O(1) average   | O(1)             |
| Deletion  | O(1) average   | O(1)             |
| Union     | O(n + m)       | O(n + m)         |
| Intersection | O(min(n, m)) | O(min(n, m))     |
| Difference | O(n)          | O(n)             |

## Space Complexity

- **HashSet**: O(n) for n elements
- **LinkedHashSet**: O(n) for n elements
- **TreeSet**: O(n) for n elements

## Real-World Applications

1. **Duplicate Removal**: Remove duplicates from data collections
2. **Membership Testing**: Check if elements exist in collections
3. **Set Operations**: Mathematical set operations
4. **Permission Systems**: Manage user permissions and roles
5. **Tag Management**: Handle unique tags and categories
6. **Inventory Tracking**: Track unique items across locations
7. **Social Networks**: Manage friend lists and connections
8. **Course Registration**: Handle prerequisites and enrollments
9. **Email Filtering**: Classify emails based on keyword sets
10. **Feature Flags**: Manage feature access and preferences

## Advanced Features

### Set Operations

```kotlin
// Union
val allElements = set1 union set2

// Intersection
val commonElements = set1 intersect set2

// Difference
val uniqueElements = set1 subtract set2

// Symmetric difference
val symmetricDiff = (set1 union set2) subtract (set1 intersect set2)
```

### Functional Operations

```kotlin
// Filtering
val filtered = set.filter { it > 5 }

// Transformation
val transformed = set.map { it * 2 }

// Partitioning
val (even, odd) = set.partition { it % 2 == 0 }
```

### Special Set Generation

```kotlin
// Prime numbers
val primes = SetCreation.createPrimeSet(100)

// Fibonacci numbers
val fibonacci = SetCreation.createFibonacciSet(100)

// Character sets
val chars = SetCreation.createCharacterSet("hello world")

// Word sets
val words = SetCreation.createWordSet("the quick brown fox")
```

## Running the Demo

To see all features in action:

```kotlin
SetDemo.runDemo()
```

This will demonstrate:
- Basic operations and membership testing
- Different creation patterns
- Advanced algorithms
- Real-world applications

## Best Practices

1. **Choose Appropriate Implementation**: Use HashSet for general purpose, LinkedHashSet for ordered access
2. **Leverage Uniqueness**: Use sets when you need to ensure uniqueness
3. **Use Set Operations**: Take advantage of union, intersection, and difference operations
4. **Consider Performance**: Sets provide O(1) average case for membership testing
5. **Handle Null Values**: Be aware of null handling in sets

## Common Pitfalls

1. **Mutable Elements**: Avoid using mutable objects as set elements
2. **Order Assumptions**: Don't assume order in HashSet (use LinkedHashSet for order)
3. **Memory Usage**: Large sets should be properly managed
4. **Hash Collisions**: Poor hashCode() implementations can degrade performance
5. **Concurrent Modification**: Don't modify sets during iteration

## Related Data Structures

- **Map**: Set keys form a set of unique elements
- **List**: Alternative for ordered data with duplicates
- **Array**: Fixed-size alternative for small datasets
- **TreeSet**: Ordered set implementation
- **LinkedHashSet**: Set that preserves insertion order 