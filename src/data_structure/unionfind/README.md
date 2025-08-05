# Union-Find Data Structure

## Overview

The Union-Find (also known as Disjoint Set) data structure is a tree-based data structure used to efficiently manage a collection of disjoint sets. It supports two main operations:

- **Union**: Merge two sets
- **Find**: Determine which set an element belongs to

## Key Features

- **Efficient Operations**: Both union and find operations have amortized time complexity of O(α(n)) where α is the inverse Ackermann function
- **Path Compression**: Automatically compresses paths during find operations
- **Union by Rank**: Balances trees by attaching smaller trees to larger ones
- **Multiple Variants**: Supports basic Union-Find and weighted Union-Find for ratio problems

## Implementation Details

### Core Classes

1. **UnionFind**: Basic implementation with path compression and union by rank
2. **WeightedUnionFind**: Extended implementation for ratio-based problems

### Key Optimizations

- **Path Compression**: During find operations, all nodes on the path point directly to the root
- **Union by Rank**: Attaches smaller trees to larger trees to maintain balance
- **Amortized Analysis**: Combined optimizations provide near-constant time operations

## File Organization

```
unionfind/
├── UnionFindCreation.kt      # Creation patterns and factory methods
├── UnionFindOperations.kt    # Core UnionFind class and basic operations
├── UnionFindAlgorithms.kt    # Algorithms that use Union-Find
├── UnionFindDemo.kt          # Comprehensive demonstrations
└── README.md                 # This documentation
```

## Usage Examples

### Basic Operations

```kotlin
// Create a Union-Find
val uf = UnionFindCreation.createBasicUnionFind(5)

// Perform unions
uf.union(0, 1)
uf.union(1, 2)
uf.union(3, 4)

// Check connectivity
println(uf.connected(0, 2))  // true
println(uf.connected(0, 4))  // false

// Get component count
println(uf.getCount())       // 2
```

### From Connections

```kotlin
val connections = arrayOf(
    intArrayOf(0, 1),
    intArrayOf(1, 2),
    intArrayOf(3, 4)
)
val uf = UnionFindCreation.createUnionFindFromConnections(5, connections)
```

### From Graph

```kotlin
val adjacencyList = arrayOf(
    arrayOf(1, 2),    // 0 -> 1, 2
    arrayOf(0, 2),    // 1 -> 0, 2
    arrayOf(0, 1),    // 2 -> 0, 1
    arrayOf(4),       // 3 -> 4
    arrayOf(3)        // 4 -> 3
)
val uf = UnionFindCreation.createUnionFindFromGraph(adjacencyList)
```

### From Grid

```kotlin
val grid = arrayOf(
    charArrayOf('1', '1', '0'),
    charArrayOf('1', '0', '1'),
    charArrayOf('0', '1', '1')
)
val uf = UnionFindCreation.createUnionFindFromGrid(grid) { a, b -> a == '1' && b == '1' }
```

## Common Algorithms

### 1. Number of Islands

Counts connected land regions in a 2D grid.

```kotlin
val islands = UnionFindAlgorithms.numIslands(grid)
```

### 2. Cycle Detection

Detects cycles in undirected graphs.

```kotlin
val hasCycle = UnionFindAlgorithms.hasCycleUndirected(edges, vertices)
```

### 3. Redundant Connection

Finds redundant edges that create cycles.

```kotlin
val redundant = UnionFindAlgorithms.findRedundantConnection(edges, vertices)
```

### 4. Kruskal's MST

Finds minimum spanning tree using Union-Find.

```kotlin
val mst = UnionFindAlgorithms.kruskalMST(edges, vertices)
```

### 5. Connected Components

Counts and analyzes connected components.

```kotlin
val count = UnionFindAlgorithms.countConnectedComponents(edges, vertices)
val largest = UnionFindAlgorithms.largestConnectedComponent(edges, vertices)
```

## Time Complexity

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Union     | O(α(n))        | O(1)             |
| Find      | O(α(n))        | O(1)             |
| Connected | O(α(n))        | O(1)             |
| GetCount  | O(n * α(n))    | O(n)             |

Where α(n) is the inverse Ackermann function, which grows very slowly and is practically constant.

## Space Complexity

- **Basic Union-Find**: O(n) for parent and rank arrays
- **Weighted Union-Find**: O(n) for parent and weight arrays

## Real-World Applications

1. **Social Networks**: Finding friend groups and communities
2. **Network Connectivity**: Analyzing network topology and critical connections
3. **Image Processing**: Image segmentation and connected component analysis
4. **Game Development**: Managing game objects and their relationships
5. **Database Systems**: Managing equivalence classes and partitions
6. **Compiler Design**: Managing symbol tables and scopes
7. **Graph Algorithms**: Cycle detection, MST, and connected components

## Advanced Features

### Weighted Union-Find

For problems involving ratios and equations:

```kotlin
val weightedUF = UnionFindCreation.createWeightedUnionFind(26)
weightedUF.union(0, 1, 2.0)  // 0 = 2 * 1
val ratio = weightedUF.connected(0, 1)  // 2.0
```

### Optimized Variants

Specialized Union-Find for specific use cases:

```kotlin
val cycleUF = UnionFindCreation.createOptimizedUnionFind(5, "cycle_detection")
val componentUF = UnionFindCreation.createOptimizedUnionFind(5, "connected_components")
val mstUF = UnionFindCreation.createOptimizedUnionFind(5, "minimum_spanning_tree")
```

## Running the Demo

To see all features in action:

```kotlin
UnionFindDemo.runDemo()
```

This will demonstrate:
- Basic operations and state changes
- Different creation patterns
- Advanced algorithms
- Real-world applications

## Best Practices

1. **Use Appropriate Creation Method**: Choose the creation method that matches your input format
2. **Consider Use Case**: Use optimized variants for specific algorithms
3. **Handle Edge Cases**: Always validate input bounds and handle empty cases
4. **Monitor Performance**: For large datasets, consider the amortized complexity
5. **Reset When Needed**: Use reset() to reuse Union-Find instances

## Common Pitfalls

1. **Index Out of Bounds**: Always validate indices before operations
2. **Incorrect Union Order**: Union operations are commutative but order can affect performance
3. **Memory Leaks**: Large Union-Find instances should be properly managed
4. **Incorrect Weight Handling**: Weighted Union-Find requires careful ratio management

## Related Data Structures

- **Graph**: Union-Find is often used with graph algorithms
- **Tree**: Union-Find maintains a forest of trees
- **Hash Set**: Alternative for simple connectivity queries
- **Connected Components**: Union-Find naturally finds connected components 