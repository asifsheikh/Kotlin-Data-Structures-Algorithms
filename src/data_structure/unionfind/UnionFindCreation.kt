package data_structure.unionfind

/**
 * UNION-FIND CREATION
 * 
 * This file contains functions for creating different types of Union-Find
 * data structures and various creation patterns.
 * 
 * UNION-FIND PROPERTIES:
 * - Disjoint Set data structure
 * - Efficient union and find operations
 * - Path compression and union by rank optimizations
 * - Used for connected components, cycle detection, etc.
 * 
 * IMPLEMENTATION:
 * - Uses arrays for parent and rank tracking
 * - Supports different optimization strategies
 * - Provides both basic and weighted variants
 */

object UnionFindCreation {
    
    /**
     * Creates a basic Union-Find with path compression and union by rank
     * 
     * ALGORITHM:
     * 1. Initialize parent array where each element is its own parent
     * 2. Initialize rank array with zeros
     * 3. Use path compression in find operations
     * 4. Use union by rank for balanced trees
     * 
     * TIME COMPLEXITY: O(1) - Constant time initialization
     * SPACE COMPLEXITY: O(n) - Two arrays of size n
     * 
     * @param size Number of elements in the Union-Find
     * @return Basic Union-Find with optimizations
     */
    fun createBasicUnionFind(size: Int): UnionFind {
        return UnionFind(size)
    }
    
    /**
     * Creates a Union-Find with custom initial parent assignments
     * 
     * ALGORITHM:
     * 1. Initialize parent array with custom values
     * 2. Initialize rank array with zeros
     * 3. Validate that all parent indices are within bounds
     * 
     * TIME COMPLEXITY: O(n) - n elements to initialize
     * SPACE COMPLEXITY: O(n) - Two arrays of size n
     * 
     * @param size Number of elements
     * @param initialParents Custom initial parent assignments
     * @return Union-Find with custom initial state
     */
    fun createUnionFindWithInitialParents(size: Int, initialParents: IntArray): UnionFind {
        require(initialParents.size == size) { "Initial parents array size must match size parameter" }
        require(initialParents.all { it in 0 until size }) { "All parent indices must be within bounds" }
        
        val uf = UnionFind(size)
        for (i in 0 until size) {
            uf.setParent(i, initialParents[i])
        }
        return uf
    }
    
    /**
     * Creates a Union-Find from existing connections
     * 
     * ALGORITHM:
     * 1. Initialize basic Union-Find
     * 2. Apply all given connections using union operations
     * 3. Result represents the connected components
     * 
     * TIME COMPLEXITY: O(m * α(n)) - m connections, α(n) for each union
     * SPACE COMPLEXITY: O(n) - Union-Find storage
     * 
     * @param size Number of elements
     * @param connections Array of pairs representing connections
     * @return Union-Find with applied connections
     */
    fun createUnionFindFromConnections(size: Int, connections: Array<IntArray>): UnionFind {
        val uf = UnionFind(size)
        for ((u, v) in connections) {
            require(u in 0 until size && v in 0 until size) { "Connection indices must be within bounds" }
            uf.union(u, v)
        }
        return uf
    }
    
    /**
     * Creates a Union-Find from a graph adjacency list
     * 
     * ALGORITHM:
     * 1. Initialize basic Union-Find
     * 2. Convert adjacency list to connections
     * 3. Apply all connections using union operations
     * 
     * TIME COMPLEXITY: O(V + E * α(V)) - V vertices, E edges
     * SPACE COMPLEXITY: O(V) - Union-Find storage
     * 
     * @param adjacencyList Graph represented as adjacency list
     * @return Union-Find representing connected components
     */
    fun createUnionFindFromGraph(adjacencyList: Array<Array<Int>>): UnionFind {
        val size = adjacencyList.size
        val uf = UnionFind(size)
        
        for (u in adjacencyList.indices) {
            for (v in adjacencyList[u]) {
                if (u < v) { // Avoid duplicate unions
                    uf.union(u, v)
                }
            }
        }
        return uf
    }
    
    /**
     * Creates a Union-Find from a 2D grid
     * 
     * ALGORITHM:
     * 1. Initialize Union-Find with grid size
     * 2. Connect adjacent cells based on condition
     * 3. Use row-major indexing for grid positions
     * 
     * TIME COMPLEXITY: O(rows * cols * α(rows * cols)) - Grid traversal
     * SPACE COMPLEXITY: O(rows * cols) - Union-Find storage
     * 
     * @param grid 2D grid to process
     * @param condition Function to determine if cells should be connected
     * @return Union-Find representing connected regions
     */
    fun createUnionFindFromGrid(
        grid: Array<CharArray>,
        condition: (Char, Char) -> Boolean
    ): UnionFind {
        val rows = grid.size
        val cols = grid[0].size
        val uf = UnionFind(rows * cols)
        
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                val current = i * cols + j
                
                // Check right neighbor
                if (j + 1 < cols && condition(grid[i][j], grid[i][j + 1])) {
                    uf.union(current, i * cols + (j + 1))
                }
                
                // Check bottom neighbor
                if (i + 1 < rows && condition(grid[i][j], grid[i + 1][j])) {
                    uf.union(current, (i + 1) * cols + j)
                }
            }
        }
        return uf
    }
    
    /**
     * Creates a weighted Union-Find for ratio problems
     * 
     * ALGORITHM:
     * 1. Initialize parent array where each element is its own parent
     * 2. Initialize weight array with 1.0 (identity ratio)
     * 3. Supports ratio-based unions and queries
     * 
     * TIME COMPLEXITY: O(1) - Constant time initialization
     * SPACE COMPLEXITY: O(n) - Two arrays of size n
     * 
     * @param size Number of elements
     * @return Weighted Union-Find for ratio problems
     */
    fun createWeightedUnionFind(size: Int): WeightedUnionFind {
        return WeightedUnionFind(size)
    }
    
    /**
     * Creates a Union-Find optimized for specific use case
     * 
     * ALGORITHM:
     * 1. Choose appropriate optimization based on use case
     * 2. Initialize with optimal parameters
     * 3. Configure for expected operation patterns
     * 
     * TIME COMPLEXITY: O(1) - Constant time initialization
     * SPACE COMPLEXITY: O(n) - Union-Find storage
     * 
     * @param size Number of elements
     * @param useCase Specific use case (e.g., "cycle_detection", "connected_components")
     * @return Optimized Union-Find for the use case
     */
    fun createOptimizedUnionFind(size: Int, useCase: String): UnionFind {
        return when (useCase.lowercase()) {
            "cycle_detection" -> {
                // For cycle detection, we want fast union operations
                UnionFind(size).apply {
                    // Could add cycle detection specific optimizations
                }
            }
            "connected_components" -> {
                // For connected components, we want balanced trees
                UnionFind(size).apply {
                    // Union by rank is already implemented
                }
            }
            "minimum_spanning_tree" -> {
                // For MST, we want efficient find operations
                UnionFind(size).apply {
                    // Path compression is already implemented
                }
            }
            else -> UnionFind(size)
        }
    }
    
    /**
     * Creates a Union-Find with custom rank initialization
     * 
     * ALGORITHM:
     * 1. Initialize parent array normally
     * 2. Set custom initial ranks
     * 3. Useful for specific algorithms that need custom tree heights
     * 
     * TIME COMPLEXITY: O(n) - n elements to initialize
     * SPACE COMPLEXITY: O(n) - Two arrays of size n
     * 
     * @param size Number of elements
     * @param initialRanks Custom initial rank values
     * @return Union-Find with custom ranks
     */
    fun createUnionFindWithCustomRanks(size: Int, initialRanks: IntArray): UnionFind {
        require(initialRanks.size == size) { "Initial ranks array size must match size parameter" }
        require(initialRanks.all { it >= 0 }) { "All ranks must be non-negative" }
        
        val uf = UnionFind(size)
        for (i in 0 until size) {
            uf.setRank(i, initialRanks[i])
        }
        return uf
    }
    
    /**
     * Creates a Union-Find for sparse graphs
     * 
     * ALGORITHM:
     * 1. Initialize Union-Find with size
     * 2. Apply optimizations suitable for sparse graphs
     * 3. Focus on efficient find operations
     * 
     * TIME COMPLEXITY: O(1) - Constant time initialization
     * SPACE COMPLEXITY: O(n) - Union-Find storage
     * 
     * @param size Number of elements
     * @return Union-Find optimized for sparse graphs
     */
    fun createSparseUnionFind(size: Int): UnionFind {
        return UnionFind(size).apply {
            // For sparse graphs, path compression is most important
            // Union by rank is already implemented
        }
    }
    
    /**
     * Creates a Union-Find for dense graphs
     * 
     * ALGORITHM:
     * 1. Initialize Union-Find with size
     * 2. Apply optimizations suitable for dense graphs
     * 3. Focus on balanced union operations
     * 
     * TIME COMPLEXITY: O(1) - Constant time initialization
     * SPACE COMPLEXITY: O(n) - Union-Find storage
     * 
     * @param size Number of elements
     * @return Union-Find optimized for dense graphs
     */
    fun createDenseUnionFind(size: Int): UnionFind {
        return UnionFind(size).apply {
            // For dense graphs, both path compression and union by rank are important
            // Both are already implemented
        }
    }
    
    /**
     * Demonstrates Union-Find creation patterns
     */
    fun demonstrateUnionFindCreation() {
        println("=== UNION-FIND CREATION DEMONSTRATION ===\n")
        
        // 1. Basic Union-Find creation
        println("1. BASIC UNION-FIND CREATION")
        val basicUF = createBasicUnionFind(5)
        println("Basic Union-Find created with size 5")
        println("Initial count: ${basicUF.getCount()}")
        println()
        
        // 2. Union-Find from connections
        println("2. UNION-FIND FROM CONNECTIONS")
        val connections = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        )
        val connectionUF = createUnionFindFromConnections(5, connections)
        println("Union-Find from connections: ${connections.contentDeepToString()}")
        println("Count after connections: ${connectionUF.getCount()}")
        println()
        
        // 3. Union-Find from graph
        println("3. UNION-FIND FROM GRAPH")
        val adjacencyList = arrayOf(
            arrayOf(1, 2),    // 0 -> 1, 2
            arrayOf(0, 2),    // 1 -> 0, 2
            arrayOf(0, 1),    // 2 -> 0, 1
            arrayOf(4),       // 3 -> 4
            arrayOf(3)        // 4 -> 3
        )
        val graphUF = createUnionFindFromGraph(adjacencyList)
        println("Union-Find from graph adjacency list")
        println("Count: ${graphUF.getCount()}")
        println()
        
        // 4. Union-Find from grid
        println("4. UNION-FIND FROM GRID")
        val grid = arrayOf(
            charArrayOf('1', '1', '0'),
            charArrayOf('1', '0', '1'),
            charArrayOf('0', '1', '1')
        )
        val gridUF = createUnionFindFromGrid(grid) { a, b -> a == '1' && b == '1' }
        println("Union-Find from grid (connecting '1's)")
        println("Count: ${gridUF.getCount()}")
        println()
        
        // 5. Weighted Union-Find
        println("5. WEIGHTED UNION-FIND")
        val weightedUF = createWeightedUnionFind(4)
        println("Weighted Union-Find created with size 4")
        println()
        
        // 6. Optimized Union-Find
        println("6. OPTIMIZED UNION-FIND")
        val cycleUF = createOptimizedUnionFind(5, "cycle_detection")
        val componentUF = createOptimizedUnionFind(5, "connected_components")
        val mstUF = createOptimizedUnionFind(5, "minimum_spanning_tree")
        println("Optimized Union-Finds created for different use cases")
        println()
        
        // 7. Custom ranks Union-Find
        println("7. CUSTOM RANKS UNION-FIND")
        val customRanks = intArrayOf(0, 1, 0, 2, 0)
        val customRankUF = createUnionFindWithCustomRanks(5, customRanks)
        println("Union-Find with custom ranks: ${customRanks.contentToString()}")
        println()
        
        // 8. Sparse and Dense Union-Find
        println("8. SPARSE AND DENSE UNION-FIND")
        val sparseUF = createSparseUnionFind(10)
        val denseUF = createDenseUnionFind(10)
        println("Sparse and Dense Union-Finds created")
        println()
        
        println("=== UNION-FIND CREATION DEMONSTRATION COMPLETE ===\n")
    }
} 