package data_structure.unionfind

/**
 * UNION-FIND ALGORITHMS
 * 
 * This file contains algorithms that use Union-Find including
 * number of islands, cycle detection, redundant connection,
 * and other Union-Find-based algorithms.
 * 
 * COMMON UNION-FIND ALGORITHMS:
 * - Number of Islands: Count connected land regions
 * - Cycle Detection: Detect cycles in undirected graphs
 * - Redundant Connection: Find redundant edges
 * - Minimum Spanning Tree: Kruskal's algorithm
 * - Permutation Validation: Check if array is valid permutation
 */

object UnionFindAlgorithms {
    
    /**
     * PROBLEM: Number of Islands
     * 
     * Given a 2D grid map of '1's (land) and '0's (water), count the number of islands.
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
     * 
     * INPUT: 2D grid with '1's (land) and '0's (water)
     * OUTPUT: Number of islands
     * 
     * EXAMPLES:
     * Input: [
     *   ['1','1','1','1','0'],
     *   ['1','1','0','1','0'],
     *   ['1','1','0','0','0'],
     *   ['0','0','0','0','0']
     * ]
     * Output: 1 (one large island)
     * 
     * Input: [
     *   ['1','1','0','0','0'],
     *   ['1','1','0','0','0'],
     *   ['0','0','1','0','0'],
     *   ['0','0','0','1','1']
     * ]
     * Output: 3 (three separate islands)
     * 
     * Input: [
     *   ['1','1','1'],
     *   ['0','1','0'],
     *   ['1','1','1']
     * ]
     * Output: 1 (one island with a lake)
     * 
     * INTUITION:
     * - Use Union-Find to track connected components
     * - Initialize with all land cells as separate components
     * - Union adjacent land cells in all 4 directions
     * - Count decreases for each successful union
     * - Final count is number of islands
     * 
     * TIME COMPLEXITY: O(rows * cols * α(rows * cols)) - Grid traversal with Union-Find
     * SPACE COMPLEXITY: O(rows * cols) - Union-Find storage
     * 
     * @param grid 2D grid with '1's (land) and '0's (water)
     * @return Number of islands
     */
    fun numIslands(grid: Array<CharArray>): Int {
        if (grid.isEmpty()) return 0
        
        val rows = grid.size
        val cols = grid[0].size
        val uf = UnionFindCreation.createBasicUnionFind(rows * cols)
        var count = 0
        
        // Count initial islands
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (grid[i][j] == '1') {
                    count++
                }
            }
        }
        
        // Union adjacent islands
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (grid[i][j] == '1') {
                    val current = i * cols + j
                    
                    // Check all 4 directions
                    val directions = arrayOf(
                        intArrayOf(-1, 0), // up
                        intArrayOf(1, 0),  // down
                        intArrayOf(0, -1), // left
                        intArrayOf(0, 1)   // right
                    )
                    
                    for ((di, dj) in directions) {
                        val ni = i + di
                        val nj = j + dj
                        
                        if (ni in 0 until rows && nj in 0 until cols && grid[ni][nj] == '1') {
                            val neighbor = ni * cols + nj
                            if (!uf.connected(current, neighbor)) {
                                uf.union(current, neighbor)
                                count--
                            }
                        }
                    }
                }
            }
        }
        
        return count
    }
    
    /**
     * PROBLEM: Cycle Detection in Undirected Graph
     * 
     * Given an undirected graph represented as edges, determine if the graph contains a cycle.
     * 
     * INPUT: Array of edges as [u, v] pairs and number of vertices
     * OUTPUT: true if cycle exists, false otherwise
     * 
     * EXAMPLES:
     * Input: edges = [[0,1], [1,2], [2,3], [3,4]], vertices = 5
     * Output: false (no cycle, tree structure)
     * 
     * Input: edges = [[0,1], [1,2], [2,3], [3,0]], vertices = 4
     * Output: true (cycle: 0->1->2->3->0)
     * 
     * Input: edges = [[0,1], [1,2], [2,0]], vertices = 3
     * Output: true (triangle cycle)
     * 
     * Input: edges = [[0,1], [1,2]], vertices = 3
     * Output: false (no cycle)
     * 
     * INTUITION:
     * - Use Union-Find to track connected components
     * - For each edge, check if vertices are already connected
     * - If vertices are already connected, adding this edge creates a cycle
     * - If not connected, union the vertices
     * - If no cycle found after processing all edges, graph is acyclic
     * 
     * TIME COMPLEXITY: O(E * α(V)) - E edges, V vertices
     * SPACE COMPLEXITY: O(V) - Union-Find storage
     * 
     * @param edges Array of edges as [u, v] pairs
     * @param vertices Number of vertices
     * @return true if cycle exists, false otherwise
     */
    fun hasCycleUndirected(edges: Array<IntArray>, vertices: Int): Boolean {
        val uf = UnionFindCreation.createBasicUnionFind(vertices)
        
        for ((u, v) in edges) {
            if (uf.connected(u, v)) {
                return true // Cycle found
            }
            uf.union(u, v)
        }
        
        return false
    }
    
    /**
     * PROBLEM: Find Redundant Connection
     * 
     * Given an undirected graph that started as a tree with N nodes, an additional edge was added.
     * Find the edge that can be removed to make the graph a tree again.
     * 
     * INPUT: Array of edges as [u, v] pairs and number of vertices
     * OUTPUT: Redundant edge or null if none found
     * 
     * EXAMPLES:
     * Input: edges = [[1,2], [1,3], [2,3]], vertices = 3
     * Output: [2,3] (edge [2,3] is redundant)
     * 
     * Input: edges = [[1,2], [2,3], [3,4], [1,4], [1,5]], vertices = 5
     * Output: [1,4] (edge [1,4] creates cycle)
     * 
     * Input: edges = [[1,2], [2,3], [3,4]], vertices = 4
     * Output: null (no redundant edges, valid tree)
     * 
     * Input: edges = [[1,2], [1,3], [2,3], [3,4]], vertices = 4
     * Output: [2,3] (edge [2,3] is redundant)
     * 
     * INTUITION:
     * - Use Union-Find to track connected components
     * - Process edges in order they appear
     * - For each edge, check if vertices are already connected
     * - If connected, this edge is redundant (creates cycle)
     * - If not connected, union the vertices
     * - Return the first redundant edge found
     * 
     * TIME COMPLEXITY: O(E * α(V)) - E edges, V vertices
     * SPACE COMPLEXITY: O(V) - Union-Find storage
     * 
     * @param edges Array of edges as [u, v] pairs
     * @param vertices Number of vertices
     * @return Redundant edge or null if none found
     */
    fun findRedundantConnection(edges: Array<IntArray>, vertices: Int): IntArray? {
        val uf = UnionFindCreation.createBasicUnionFind(vertices)
        
        for (edge in edges) {
            val (u, v) = edge
            if (uf.connected(u, v)) {
                return edge // Redundant edge found
            }
            uf.union(u, v)
        }
        
        return null
    }
    
    /**
     * PROBLEM: Validate Permutation
     * 
     * Given an array, determine if it represents a valid permutation of [0, 1, 2, ..., n-1].
     * A valid permutation contains all numbers from 0 to n-1 exactly once.
     * 
     * INPUT: Array of integers
     * OUTPUT: true if valid permutation, false otherwise
     * 
     * EXAMPLES:
     * Input: [0, 1, 2, 3]
     * Output: true (valid permutation)
     * 
     * Input: [1, 0, 2, 3]
     * Output: true (valid permutation, different order)
     * 
     * Input: [0, 1, 2, 2]
     * Output: false (duplicate element)
     * 
     * Input: [0, 1, 2, 4]
     * Output: false (missing 3, has 4)
     * 
     * Input: [0, 1, 2, -1]
     * Output: false (negative number)
     * 
     * INTUITION:
     * - Check if all values are within valid range [0, n-1]
     * - Use Union-Find to connect positions with their values
     * - If array is valid permutation, all elements form single connected component
     * - Check if Union-Find has exactly one component
     * - Alternative: use set to check for duplicates and range
     * 
     * TIME COMPLEXITY: O(n * α(n)) - n elements with Union-Find
     * SPACE COMPLEXITY: O(n) - Union-Find storage
     * 
     * @param perm Array to validate
     * @return true if valid permutation, false otherwise
     */
    fun validatePermutation(perm: IntArray): Boolean {
        // Check if all values are within bounds
        for (value in perm) {
            if (value < 0 || value >= perm.size) return false
        }
        
        val uf = UnionFindCreation.createBasicUnionFind(perm.size)
        
        for (i in perm.indices) {
            uf.union(i, perm[i])
        }
        
        return uf.getCount() == 1
    }
    
    /**
     * PROBLEM: Kruskal's Minimum Spanning Tree
     * 
     * Given a connected, undirected graph with weighted edges, find the minimum spanning tree (MST).
     * An MST is a subset of edges that connects all vertices with minimum total weight.
     * 
     * INPUT: Array of edges with [u, v, weight] and number of vertices
     * OUTPUT: List of edges in the minimum spanning tree
     * 
     * EXAMPLES:
     * Input: edges = [[0,1,4], [0,2,3], [1,2,1], [1,3,2], [2,3,4]], vertices = 4
     * Output: [[1,2,1], [1,3,2], [0,2,3]] (total weight = 6)
     * 
     * Input: edges = [[0,1,10], [0,2,6], [0,3,5], [1,3,15], [2,3,4]], vertices = 4
     * Output: [[2,3,4], [0,3,5], [0,1,10]] (total weight = 19)
     * 
     * Input: edges = [[0,1,1], [1,2,1], [2,0,1]], vertices = 3
     * Output: [[0,1,1], [1,2,1]] (any two edges form MST)
     * 
     * INTUITION:
     * - Sort all edges by weight in ascending order
     * - Use Union-Find to track connected components
     * - Process edges in order of increasing weight
     * - Add edge if it doesn't create a cycle (vertices not connected)
     * - Stop when n-1 edges are added (MST complete)
     * - Greedy approach: always choose minimum weight edge that doesn't create cycle
     * 
     * TIME COMPLEXITY: O(E log E + E * α(V)) - Sort edges + Union-Find operations
     * SPACE COMPLEXITY: O(V) - Union-Find storage
     * 
     * @param edges Array of edges with [u, v, weight]
     * @param vertices Number of vertices
     * @return List of edges in MST
     */
    fun kruskalMST(edges: Array<IntArray>, vertices: Int): List<IntArray> {
        // Sort edges by weight
        val sortedEdges = edges.sortedBy { it[2] }
        val uf = UnionFindCreation.createBasicUnionFind(vertices)
        val mst = mutableListOf<IntArray>()
        
        for (edge in sortedEdges) {
            val (u, v, weight) = edge
            if (!uf.connected(u, v)) {
                uf.union(u, v)
                mst.add(edge)
                
                if (mst.size == vertices - 1) {
                    break // MST complete
                }
            }
        }
        
        return mst
    }
    
    /**
     * PROBLEM: Count Connected Components
     * 
     * Given an undirected graph represented as edges, find the number of connected components.
     * A connected component is a subset of vertices where every pair of vertices is connected by a path.
     * 
     * INPUT: Array of edges as [u, v] pairs and number of vertices
     * OUTPUT: Number of connected components
     * 
     * EXAMPLES:
     * Input: edges = [[0,1], [1,2], [3,4]], vertices = 5
     * Output: 2 (components: {0,1,2} and {3,4})
     * 
     * Input: edges = [[0,1], [1,2], [2,3], [3,4]], vertices = 5
     * Output: 1 (single connected component)
     * 
     * Input: edges = [], vertices = 5
     * Output: 5 (each vertex is its own component)
     * 
     * Input: edges = [[0,1], [2,3], [4,5]], vertices = 6
     * Output: 3 (three separate components)
     * 
     * INTUITION:
     * - Use Union-Find to track connected components
     * - Initialize with each vertex as separate component
     * - Union vertices connected by edges
     * - Count decreases for each successful union
     * - Final count is number of connected components
     * 
     * TIME COMPLEXITY: O(E * α(V)) - E edges, V vertices
     * SPACE COMPLEXITY: O(V) - Union-Find storage
     * 
     * @param edges Array of edges as [u, v] pairs
     * @param vertices Number of vertices
     * @return Number of connected components
     */
    fun countConnectedComponents(edges: Array<IntArray>, vertices: Int): Int {
        val uf = UnionFindCreation.createBasicUnionFind(vertices)
        
        for ((u, v) in edges) {
            uf.union(u, v)
        }
        
        return uf.getCount()
    }
    
    /**
     * PROBLEM: Largest Connected Component
     * 
     * Given an undirected graph represented as edges, find the size of the largest connected component.
     * 
     * INPUT: Array of edges as [u, v] pairs and number of vertices
     * OUTPUT: Size of largest connected component
     * 
     * EXAMPLES:
     * Input: edges = [[0,1], [1,2], [3,4]], vertices = 5
     * Output: 3 (largest component: {0,1,2} has size 3)
     * 
     * Input: edges = [[0,1], [1,2], [2,3], [3,4]], vertices = 5
     * Output: 5 (single component with all vertices)
     * 
     * Input: edges = [], vertices = 5
     * Output: 1 (each vertex is its own component, max size = 1)
     * 
     * Input: edges = [[0,1], [2,3], [4,5], [6,7], [7,8]], vertices = 9
     * Output: 3 (largest component: {6,7,8} has size 3)
     * 
     * INTUITION:
     * - Use Union-Find to track connected components
     * - Union all edges to form components
     * - Check size of each component using getComponentSize()
     * - Track maximum component size found
     * - Return the largest size
     * 
     * TIME COMPLEXITY: O(E * α(V) + V * α(V)) - Union operations + component size calculation
     * SPACE COMPLEXITY: O(V) - Union-Find storage
     * 
     * @param edges Array of edges as [u, v] pairs
     * @param vertices Number of vertices
     * @return Size of largest connected component
     */
    fun largestConnectedComponent(edges: Array<IntArray>, vertices: Int): Int {
        val uf = UnionFindCreation.createBasicUnionFind(vertices)
        
        for ((u, v) in edges) {
            uf.union(u, v)
        }
        
        var maxSize = 0
        for (i in 0 until vertices) {
            maxSize = maxOf(maxSize, uf.getComponentSize(i))
        }
        
        return maxSize
    }
    
    /**
     * PROBLEM: Check if Graph is Bipartite
     * 
     * Given an undirected graph, determine if it is bipartite.
     * A graph is bipartite if its vertices can be divided into two disjoint sets such that
     * every edge connects a vertex from one set to a vertex from the other set.
     * 
     * INPUT: Array of edges as [u, v] pairs and number of vertices
     * OUTPUT: true if graph is bipartite, false otherwise
     * 
     * EXAMPLES:
     * Input: edges = [[0,1], [1,2], [2,3], [3,0]], vertices = 4
     * Output: false (odd cycle makes it non-bipartite)
     * 
     * Input: edges = [[0,1], [1,2], [2,3]], vertices = 4
     * Output: true (can be colored with 2 colors)
     * 
     * Input: edges = [[0,1], [1,2], [2,0]], vertices = 3
     * Output: false (triangle is not bipartite)
     * 
     * Input: edges = [[0,1], [1,2], [2,3], [3,4]], vertices = 5
     * Output: true (path is bipartite)
     * 
     * INTUITION:
     * - Use Union-Find with 2 * vertices to represent two sets
     * - For each edge (u,v), union u with v+n and v with u+n
     * - This ensures u and v are in opposite sets
     * - If u and u+n end up in same set, graph is not bipartite
     * - Check for conflicts after processing all edges
     * 
     * TIME COMPLEXITY: O(E * α(V)) - E edges, V vertices
     * SPACE COMPLEXITY: O(V) - Union-Find storage
     * 
     * @param edges Array of edges as [u, v] pairs
     * @param vertices Number of vertices
     * @return true if graph is bipartite, false otherwise
     */
    fun isBipartite(edges: Array<IntArray>, vertices: Int): Boolean {
        val uf = UnionFindCreation.createBasicUnionFind(2 * vertices)
        
        for ((u, v) in edges) {
            // Union u with v+n and v with u+n
            uf.union(u, v + vertices)
            uf.union(v, u + vertices)
            
            // Check for conflict
            if (uf.connected(u, u + vertices)) {
                return false
            }
        }
        
        return true
    }
    
    /**
     * Find critical connections (bridges) in graph
     * 
     * ALGORITHM:
     * 1. For each edge, temporarily remove it
     * 2. Check if graph becomes disconnected
     * 3. If disconnected, edge is critical
     * 
     * TIME COMPLEXITY: O(E * (E * α(V))) - For each edge, check connectivity
     * SPACE COMPLEXITY: O(V) - Union-Find storage
     * 
     * @param edges Array of edges as [u, v] pairs
     * @param vertices Number of vertices
     * @return List of critical edges
     */
    fun findCriticalConnections(edges: Array<IntArray>, vertices: Int): List<IntArray> {
        val critical = mutableListOf<IntArray>()
        
        for (i in edges.indices) {
            val uf = UnionFindCreation.createBasicUnionFind(vertices)
            
            // Add all edges except the current one
            for (j in edges.indices) {
                if (i != j) {
                    val (u, v) = edges[j]
                    uf.union(u, v)
                }
            }
            
            // Check if graph is disconnected
            if (uf.getCount() > 1) {
                critical.add(edges[i])
            }
        }
        
        return critical
    }
    
    /**
     * Find number of provinces (strongly connected components)
     * 
     * ALGORITHM:
     * 1. Initialize Union-Find with number of cities
     * 2. Union directly connected cities
     * 3. Return count of connected components
     * 
     * TIME COMPLEXITY: O(n² * α(n)) - n cities, adjacency matrix
     * SPACE COMPLEXITY: O(n) - Union-Find storage
     * 
     * @param isConnected Adjacency matrix of cities
     * @return Number of provinces
     */
    fun findProvinces(isConnected: Array<IntArray>): Int {
        val n = isConnected.size
        val uf = UnionFindCreation.createBasicUnionFind(n)
        
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j)
                }
            }
        }
        
        return uf.getCount()
    }
    
    /**
     * Check if equations are satisfiable using weighted Union-Find
     * 
     * ALGORITHM:
     * 1. Initialize weighted Union-Find
     * 2. Process equations with ratios
     * 3. Check for conflicts in equations
     * 
     * TIME COMPLEXITY: O(E * α(V)) - E equations, V variables
     * SPACE COMPLEXITY: O(V) - Union-Find storage
     * 
     * @param equations Array of equations ["a==b", "b!=c"]
     * @return true if equations are satisfiable, false otherwise
     */
    fun equationsPossible(equations: Array<String>): Boolean {
        val uf = UnionFindCreation.createWeightedUnionFind(26) // 26 letters
        
        // Process equality equations first
        for (equation in equations) {
            if (equation[1] == '=') {
                val a = equation[0] - 'a'
                val b = equation[3] - 'a'
                uf.union(a, b, 1.0)
            }
        }
        
        // Check inequality equations
        for (equation in equations) {
            if (equation[1] == '!') {
                val a = equation[0] - 'a'
                val b = equation[3] - 'a'
                if (uf.connected(a, b) != null) {
                    return false // Conflict found
                }
            }
        }
        
        return true
    }
    
    /**
     * Demonstrates Union-Find algorithms
     */
    fun demonstrateUnionFindAlgorithms() {
        println("=== UNION-FIND ALGORITHMS DEMONSTRATION ===\n")
        
        // 1. Number of Islands
        println("1. NUMBER OF ISLANDS")
        val grid = arrayOf(
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('1', '1', '0', '0', '0'),
            charArrayOf('0', '0', '1', '0', '0'),
            charArrayOf('0', '0', '0', '1', '1')
        )
        val islands = numIslands(grid)
        println("Grid:")
        for (row in grid) {
            println(row.contentToString())
        }
        println("Number of islands: $islands")
        println()
        
        // 2. Cycle Detection
        println("2. CYCLE DETECTION")
        val cycleEdges = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 2),
            intArrayOf(2, 3),
            intArrayOf(3, 0) // This creates a cycle
        )
        val hasCycle = hasCycleUndirected(cycleEdges, 4)
        println("Edges: ${cycleEdges.contentDeepToString()}")
        println("Has cycle: $hasCycle")
        println()
        
        // 3. Redundant Connection
        println("3. REDUNDANT CONNECTION")
        val redundantEdges = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 3) // This is redundant
        )
        val redundant = findRedundantConnection(redundantEdges, 3)
        println("Edges: ${redundantEdges.contentDeepToString()}")
        println("Redundant edge: ${redundant?.contentToString()}")
        println()
        
        // 4. Permutation Validation
        println("4. PERMUTATION VALIDATION")
        val validPerm = intArrayOf(1, 2, 0, 3) // Valid permutation
        val invalidPerm = intArrayOf(1, 2, 0, 4) // Invalid (4 is out of bounds)
        println("Valid permutation ${validPerm.contentToString()}: ${validatePermutation(validPerm)}")
        println("Invalid permutation ${invalidPerm.contentToString()}: ${validatePermutation(invalidPerm)}")
        println()
        
        // 5. Kruskal's MST
        println("5. KRUSKAL'S MST")
        val mstEdges = arrayOf(
            intArrayOf(0, 1, 4),
            intArrayOf(0, 2, 3),
            intArrayOf(1, 2, 1),
            intArrayOf(1, 3, 2),
            intArrayOf(2, 3, 4)
        )
        val mst = kruskalMST(mstEdges, 4)
        println("Edges: ${mstEdges.contentDeepToString()}")
        println("MST edges: ${mst.map { it.contentToString() }}")
        println()
        
        // 6. Connected Components
        println("6. CONNECTED COMPONENTS")
        val componentEdges = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 2),
            intArrayOf(3, 4)
        )
        val componentCount = countConnectedComponents(componentEdges, 5)
        val largestComponent = largestConnectedComponent(componentEdges, 5)
        println("Edges: ${componentEdges.contentDeepToString()}")
        println("Number of components: $componentCount")
        println("Largest component size: $largestComponent")
        println()
        
        // 7. Bipartite Check
        println("7. BIPARTITE CHECK")
        val bipartiteEdges = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 2),
            intArrayOf(2, 3)
        )
        val isBipartite = isBipartite(bipartiteEdges, 4)
        println("Edges: ${bipartiteEdges.contentDeepToString()}")
        println("Is bipartite: $isBipartite")
        println()
        
        // 8. Critical Connections
        println("8. CRITICAL CONNECTIONS")
        val criticalEdges = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 2),
            intArrayOf(2, 0),
            intArrayOf(1, 3)
        )
        val critical = findCriticalConnections(criticalEdges, 4)
        println("Edges: ${criticalEdges.contentDeepToString()}")
        println("Critical edges: ${critical.map { it.contentToString() }}")
        println()
        
        // 9. Find Provinces
        println("9. FIND PROVINCES")
        val provinces = arrayOf(
            intArrayOf(1, 1, 0),
            intArrayOf(1, 1, 0),
            intArrayOf(0, 0, 1)
        )
        val provinceCount = findProvinces(provinces)
        println("Adjacency matrix:")
        for (row in provinces) {
            println(row.contentToString())
        }
        println("Number of provinces: $provinceCount")
        println()
        
        // 10. Equations Possible
        println("10. EQUATIONS POSSIBLE")
        val equations = arrayOf("a==b", "b!=a")
        val equationsPossible = equationsPossible(equations)
        println("Equations: ${equations.contentToString()}")
        println("Equations possible: $equationsPossible")
        println()
        
        println("=== UNION-FIND ALGORITHMS DEMONSTRATION COMPLETE ===\n")
    }
} 