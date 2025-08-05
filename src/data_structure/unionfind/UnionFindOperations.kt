package data_structure.unionfind

/**
 * UNION-FIND OPERATIONS
 * 
 * This file contains standard operations on Union-Find data structures
 * including find, union, connected, and other basic operations.
 * 
 * COMMON UNION-FIND OPERATIONS:
 * - Find: Find the root of an element's set
 * - Union: Merge two sets
 * - Connected: Check if two elements are in the same set
 * - GetCount: Get number of connected components
 * - PrintState: Display current state
 */

// ===== BASIC UNION-FIND IMPLEMENTATION =====
class UnionFind(private val size: Int) {
    private val parent = IntArray(size) { it } // Each element is its own parent initially
    private val rank = IntArray(size) { 0 }   // Rank for union by rank optimization
    
    /**
     * Find with path compression
     * 
     * ALGORITHM:
     * 1. Follow parent pointers until reaching root
     * 2. Compress path by making all nodes point directly to root
     * 3. Return the root element
     * 
     * TIME COMPLEXITY: O(α(n)) - Amortized time with path compression
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param x Element to find root for
     * @return Root of the set containing x
     */
    fun find(x: Int): Int {
        if (parent[x] != x) {
            parent[x] = find(parent[x]) // Path compression
        }
        return parent[x]
    }
    
    /**
     * Union by rank
     * 
     * ALGORITHM:
     * 1. Find roots of both elements
     * 2. If roots are same, no action needed
     * 3. Attach smaller tree to larger tree based on rank
     * 4. Update rank if trees have same rank
     * 
     * TIME COMPLEXITY: O(α(n)) - Amortized time with optimizations
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param x First element
     * @param y Second element
     */
    fun union(x: Int, y: Int) {
        val rootX = find(x)
        val rootY = find(y)
        
        if (rootX == rootY) return // Already in same set
        
        // Union by rank: attach smaller tree to larger tree
        when {
            rank[rootX] < rank[rootY] -> parent[rootX] = rootY
            rank[rootX] > rank[rootY] -> parent[rootY] = rootX
            else -> {
                parent[rootY] = rootX
                rank[rootX]++
            }
        }
    }
    
    /**
     * Check if two elements are in same set
     * 
     * ALGORITHM:
     * 1. Find roots of both elements
     * 2. Return true if roots are the same
     * 
     * TIME COMPLEXITY: O(α(n)) - Amortized time with path compression
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param x First element
     * @param y Second element
     * @return true if elements are in the same set
     */
    fun connected(x: Int, y: Int): Boolean {
        return find(x) == find(y)
    }
    
    /**
     * Get number of connected components
     * 
     * ALGORITHM:
     * 1. Find root of each element
     * 2. Count unique roots
     * 
     * TIME COMPLEXITY: O(n * α(n)) - n elements, α(n) for each find
     * SPACE COMPLEXITY: O(n) - Set to store unique roots
     * 
     * @return Number of connected components
     */
    fun getCount(): Int {
        val roots = mutableSetOf<Int>()
        for (i in 0 until size) {
            roots.add(find(i))
        }
        return roots.size
    }
    
    /**
     * Get size of the Union-Find
     * 
     * ALGORITHM:
     * 1. Return the size parameter
     * 
     * TIME COMPLEXITY: O(1) - Direct access
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @return Total number of elements
     */
    fun getSize(): Int = size
    
    /**
     * Get all connected components
     * 
     * ALGORITHM:
     * 1. Find root of each element
     * 2. Group elements by their roots
     * 
     * TIME COMPLEXITY: O(n * α(n)) - n elements, α(n) for each find
     * SPACE COMPLEXITY: O(n) - Map to store components
     * 
     * @return Map of root to list of elements in that component
     */
    fun getComponents(): Map<Int, List<Int>> {
        val components = mutableMapOf<Int, MutableList<Int>>()
        
        for (i in 0 until size) {
            val root = find(i)
            components.getOrPut(root) { mutableListOf() }.add(i)
        }
        
        return components
    }
    
    /**
     * Get size of a specific component
     * 
     * ALGORITHM:
     * 1. Find root of the element
     * 2. Count all elements with the same root
     * 
     * TIME COMPLEXITY: O(n * α(n)) - n elements, α(n) for each find
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param x Element in the component
     * @return Size of the component containing x
     */
    fun getComponentSize(x: Int): Int {
        val root = find(x)
        var count = 0
        
        for (i in 0 until size) {
            if (find(i) == root) {
                count++
            }
        }
        
        return count
    }
    
    /**
     * Check if Union-Find is connected (single component)
     * 
     * ALGORITHM:
     * 1. Check if count is 1
     * 
     * TIME COMPLEXITY: O(n * α(n)) - Same as getCount()
     * SPACE COMPLEXITY: O(n) - Same as getCount()
     * 
     * @return true if all elements are connected
     */
    fun isConnected(): Boolean {
        return getCount() == 1
    }
    
    /**
     * Reset Union-Find to initial state
     * 
     * ALGORITHM:
     * 1. Reset parent array to identity mapping
     * 2. Reset rank array to zeros
     * 
     * TIME COMPLEXITY: O(n) - n elements to reset
     * SPACE COMPLEXITY: O(1) - No extra space needed
     */
    fun reset() {
        for (i in 0 until size) {
            parent[i] = i
            rank[i] = 0
        }
    }
    
    /**
     * Print current state
     * 
     * ALGORITHM:
     * 1. Display parent and rank arrays
     * 
     * TIME COMPLEXITY: O(n) - n elements to print
     * SPACE COMPLEXITY: O(1) - No extra space needed
     */
    fun printState() {
        println("Parent: ${parent.contentToString()}")
        println("Rank:   ${rank.contentToString()}")
    }
    
    /**
     * Get parent array (for debugging)
     * 
     * ALGORITHM:
     * 1. Return copy of parent array
     * 
     * TIME COMPLEXITY: O(n) - Copy array
     * SPACE COMPLEXITY: O(n) - New array
     * 
     * @return Copy of parent array
     */
    fun getParentArray(): IntArray = parent.copyOf()
    
    /**
     * Get rank array (for debugging)
     * 
     * ALGORITHM:
     * 1. Return copy of rank array
     * 
     * TIME COMPLEXITY: O(n) - Copy array
     * SPACE COMPLEXITY: O(n) - New array
     * 
     * @return Copy of rank array
     */
    fun getRankArray(): IntArray = rank.copyOf()
    
    /**
     * Set parent for an element (for creation functions)
     * 
     * ALGORITHM:
     * 1. Set parent value directly
     * 
     * TIME COMPLEXITY: O(1) - Direct assignment
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param index Element index
     * @param parentValue New parent value
     */
    fun setParent(index: Int, parentValue: Int) {
        require(index in 0 until size) { "Index out of bounds" }
        require(parentValue in 0 until size) { "Parent value out of bounds" }
        parent[index] = parentValue
    }
    
    /**
     * Set rank for an element (for creation functions)
     * 
     * ALGORITHM:
     * 1. Set rank value directly
     * 
     * TIME COMPLEXITY: O(1) - Direct assignment
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @param index Element index
     * @param rankValue New rank value
     */
    fun setRank(index: Int, rankValue: Int) {
        require(index in 0 until size) { "Index out of bounds" }
        require(rankValue >= 0) { "Rank must be non-negative" }
        rank[index] = rankValue
    }
}

// ===== WEIGHTED UNION-FIND =====
class WeightedUnionFind(private val size: Int) {
    private val parent = IntArray(size) { it }
    private val weight = DoubleArray(size) { 1.0 } // Weight of edge to parent
    
    /**
     * Find with path compression and weight tracking
     * 
     * ALGORITHM:
     * 1. Follow parent pointers with weight accumulation
     * 2. Compress path and update weights
     * 3. Return root and accumulated weight
     * 
     * TIME COMPLEXITY: O(α(n)) - Amortized time with path compression
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param x Element to find root for
     * @return Pair of root and weight from x to root
     */
    fun find(x: Int): Pair<Int, Double> {
        if (parent[x] != x) {
            val (root, weightToParent) = find(parent[x])
            parent[x] = root
            weight[x] *= weightToParent
        }
        return parent[x] to weight[x]
    }
    
    /**
     * Union with ratio
     * 
     * ALGORITHM:
     * 1. Find roots and weights of both elements
     * 2. If roots are same, no action needed
     * 3. Attach one tree to other with calculated weight
     * 
     * TIME COMPLEXITY: O(α(n)) - Amortized time with optimizations
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param x First element
     * @param y Second element
     * @param ratio Ratio of x to y
     */
    fun union(x: Int, y: Int, ratio: Double) {
        val (rootX, weightX) = find(x)
        val (rootY, weightY) = find(y)
        
        if (rootX == rootY) return
        
        parent[rootY] = rootX
        weight[rootY] = ratio * weightX / weightY
    }
    
    /**
     * Check if two elements are connected and get their ratio
     * 
     * ALGORITHM:
     * 1. Find roots and weights of both elements
     * 2. If roots are same, calculate ratio
     * 
     * TIME COMPLEXITY: O(α(n)) - Amortized time with path compression
     * SPACE COMPLEXITY: O(1) - Only uses constant extra space
     * 
     * @param x First element
     * @param y Second element
     * @return Ratio of y to x if connected, null otherwise
     */
    fun connected(x: Int, y: Int): Double? {
        val (rootX, weightX) = find(x)
        val (rootY, weightY) = find(y)
        
        return if (rootX == rootY) weightY / weightX else null
    }
    
    /**
     * Get size of the Weighted Union-Find
     * 
     * ALGORITHM:
     * 1. Return the size parameter
     * 
     * TIME COMPLEXITY: O(1) - Direct access
     * SPACE COMPLEXITY: O(1) - No extra space needed
     * 
     * @return Total number of elements
     */
    fun getSize(): Int = size
}

object UnionFindOperations {
    
    /**
     * Demonstrates standard Union-Find operations
     */
    fun demonstrateUnionFindOperations() {
        println("=== UNION-FIND OPERATIONS DEMONSTRATION ===\n")
        
        // Create a Union-Find for demonstration
        val uf = UnionFindCreation.createBasicUnionFind(5)
        
        // 1. Basic operations
        println("1. BASIC OPERATIONS")
        println("Initial count: ${uf.getCount()}")
        println("Size: ${uf.getSize()}")
        println("Is connected: ${uf.isConnected()}")
        println()
        
        // 2. Union operations
        println("2. UNION OPERATIONS")
        uf.union(0, 1)
        println("After union(0, 1):")
        uf.printState()
        println("Count: ${uf.getCount()}")
        println()
        
        uf.union(1, 2)
        println("After union(1, 2):")
        uf.printState()
        println("Count: ${uf.getCount()}")
        println()
        
        uf.union(3, 4)
        println("After union(3, 4):")
        uf.printState()
        println("Count: ${uf.getCount()}")
        println()
        
        // 3. Find operations
        println("3. FIND OPERATIONS")
        println("Find(0): ${uf.find(0)}")
        println("Find(1): ${uf.find(1)}")
        println("Find(2): ${uf.find(2)}")
        println("Find(3): ${uf.find(3)}")
        println("Find(4): ${uf.find(4)}")
        println()
        
        // 4. Connected operations
        println("4. CONNECTED OPERATIONS")
        println("0 and 2 connected: ${uf.connected(0, 2)}")
        println("0 and 4 connected: ${uf.connected(0, 4)}")
        println("3 and 4 connected: ${uf.connected(3, 4)}")
        println()
        
        // 5. Component operations
        println("5. COMPONENT OPERATIONS")
        val components = uf.getComponents()
        println("Components: $components")
        println("Component size of 0: ${uf.getComponentSize(0)}")
        println("Component size of 3: ${uf.getComponentSize(3)}")
        println()
        
        // 6. Final union
        println("6. FINAL UNION")
        uf.union(2, 3)
        println("After union(2, 3):")
        uf.printState()
        println("Count: ${uf.getCount()}")
        println("Is connected: ${uf.isConnected()}")
        println("0 and 4 connected: ${uf.connected(0, 4)}")
        println()
        
        // 7. Weighted Union-Find
        println("7. WEIGHTED UNION-FIND")
        val weightedUF = UnionFindCreation.createWeightedUnionFind(4)
        weightedUF.union(0, 1, 2.0) // 0 = 2 * 1
        weightedUF.union(1, 2, 3.0) // 1 = 3 * 2
        println("Ratio from 0 to 2: ${weightedUF.connected(0, 2)}")
        println("Ratio from 2 to 0: ${weightedUF.connected(2, 0)}")
        println()
        
        // 8. Reset operation
        println("8. RESET OPERATION")
        uf.reset()
        println("After reset:")
        uf.printState()
        println("Count: ${uf.getCount()}")
        println()
        
        println("=== UNION-FIND OPERATIONS DEMONSTRATION COMPLETE ===\n")
    }
} 