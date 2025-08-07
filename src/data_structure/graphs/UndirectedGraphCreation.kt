package data_structure.graphs

/**
 * UNDIRECTED GRAPH CREATION - Quick Reference
 * All Kotlin undirected graph creation methods in one place
 */

object UndirectedGraphCreation {
    
    /**
     * All Undirected Graph Creation Methods
     * Complete reference for creating undirected graphs in Kotlin
     */
    fun allUndirectedGraphCreationMethods() {
        // === BASIC UNDIRECTED GRAPH CREATION ===
        val emptyUndirectedGraph = createUndirectedGraph()          // Empty undirected graph
        val undirectedGraph = createUndirectedGraph()               // Undirected graph for use
        
        // === FROM EDGES ===
        val edges = listOf(0 to 1, 1 to 2, 2 to 3, 3 to 0)
        val graphFromEdges = createFromEdges(edges)                 // Graph from edges
        
        // === FROM ARRAYS ===
        val edgeArrays = arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 3))
        val graphFromArrays = createFromArrays(edgeArrays)          // Graph from arrays
        
        // === FROM LISTS ===
        val edgeLists = listOf(listOf(0, 1), listOf(1, 2), listOf(2, 3))
        val graphFromLists = createFromLists(edgeLists)             // Graph from lists
        
        // === FROM PAIRS ===
        val edgePairs = listOf(0 to 1, 1 to 2, 2 to 3)
        val graphFromPairs = createFromPairs(edgePairs)             // Graph from pairs
        
        // === FROM RANGES ===
        val graphFromRange = createFromRange(0..4)                  // Graph with nodes 0-4
        
        // === FROM SEQUENCES ===
        val sequence = (0..4).asSequence()
        val graphFromSequence = createFromSequence(sequence)        // Graph from sequence
        
        // === WITH INITIAL VALUES ===
        val graphWithInitial = createWithInitialValue(5, 0)         // Graph with initial values
        
        // === WITH RANDOM VALUES ===
        val graphWithRandom = createWithRandomValues(5, 1..10)      // Graph with random weights
        
        // === SPECIAL PATTERNS ===
        val linearUndirectedGraph = createLinearUndirectedGraph(5)  // Linear undirected graph 0-1-2-3-4
        val circularUndirectedGraph = createCircularUndirectedGraph(5) // Circular undirected graph 0-1-2-3-4-0
        val starUndirectedGraph = createStarUndirectedGraph(5)      // Star undirected graph with center 0
        val completeUndirectedGraph = createCompleteUndirectedGraph(4) // Complete undirected graph
        
        // === FROM STRING REPRESENTATION ===
        val graphString = "0-1,1-2,2-3"
        val graphFromString = createFromString(graphString)         // Graph from string
        
        // === FROM MATRIX ===
        val matrixData = arrayOf(
            intArrayOf(0, 1, 0, 0),
            intArrayOf(1, 0, 1, 0),
            intArrayOf(0, 1, 0, 1),
            intArrayOf(0, 0, 1, 0)
        )
        val graphFromMatrix = createFromMatrix(matrixData)          // Graph from matrix
        
        // === WITH FILTERING ===
        val allEdges = listOf(0 to 1, 1 to 2, 2 to 3, 3 to 4)
        val graphFromFiltered = createFromFiltered(allEdges) { it.first < it.second } // Graph with filtered edges
        
        // === WITH TRANSFORMATION ===
        val originalEdges = listOf(0 to 1, 1 to 2, 2 to 3)
        val graphFromTransformed = createFromTransformed(originalEdges) { it.first * 2 to it.second * 2 } // Graph with transformed edges
        
        // === WITH SORTING ===
        val unsortedEdges = listOf(2 to 3, 0 to 1, 1 to 2)
        val graphFromSorted = createFromSorted(unsortedEdges)       // Graph with sorted edges
        
        // === WITH DEDUPLICATION ===
        val duplicateEdges = listOf(0 to 1, 1 to 2, 0 to 1, 2 to 3)
        val graphFromUnique = createFromUnique(duplicateEdges)      // Graph with unique edges
        
        // === FROM CHARACTER ARRAYS ===
        val charEdges = arrayOf(charArrayOf('A', 'B'), charArrayOf('B', 'C'))
        val graphFromChars = createFromChars(charEdges)             // Graph from character arrays
        
        // === WITH PREFIX SUMS ===
        val nodes = (0..4).toList()
        val graphFromPrefix = createFromPrefixSums(nodes)           // Graph from prefix sums
        
        // === WITH DIFFERENCE ARRAY ===
        val originalArray = intArrayOf(1, 2, 3, 4, 5)
        val graphFromDifference = createFromDifferenceArray(originalArray) // Graph from difference array
        
        // === SPECIAL UNDIRECTED GRAPHS ===
        val treeGraph = createUndirectedTree(5)                     // Undirected Tree
        val cycleGraph = createUndirectedCycle(5)                   // Undirected Cycle
        val pathGraph = createUndirectedPath(5)                     // Undirected Path
        val bipartiteGraph = createBipartiteUndirectedGraph(3, 3)   // Bipartite Undirected Graph
        val gridGraph = createGridUndirectedGraph(3, 3)             // Grid Undirected Graph
        val cubeGraph = createCubeUndirectedGraph(2)                // Cube Undirected Graph
        val wheelGraph = createWheelUndirectedGraph(5)              // Wheel Undirected Graph
        val ladderGraph = createLadderUndirectedGraph(5)            // Ladder Undirected Graph
    }
    
    /**
     * Creates empty undirected graph
     */
    fun createUndirectedGraph(): MutableMap<Int, MutableList<Int>> {
        return mutableMapOf()
    }
    
    /**
     * Creates undirected graph from edges
     */
    fun createFromEdges(edges: List<Pair<Int, Int>>): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        for ((u, v) in edges) {
            graph.computeIfAbsent(u) { mutableListOf() }.add(v)
            graph.computeIfAbsent(v) { mutableListOf() }.add(u)
        }
        return graph
    }
    
    /**
     * Creates undirected graph from arrays
     */
    fun createFromArrays(arrays: Array<IntArray>): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        for (array in arrays) {
            if (array.size >= 2) {
                val u = array[0]
                val v = array[1]
                graph.computeIfAbsent(u) { mutableListOf() }.add(v)
                graph.computeIfAbsent(v) { mutableListOf() }.add(u)
            }
        }
        return graph
    }
    
    /**
     * Creates undirected graph from lists
     */
    fun createFromLists(lists: List<List<Int>>): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        for (list in lists) {
            if (list.size >= 2) {
                val u = list[0]
                val v = list[1]
                graph.computeIfAbsent(u) { mutableListOf() }.add(v)
                graph.computeIfAbsent(v) { mutableListOf() }.add(u)
            }
        }
        return graph
    }
    
    /**
     * Creates undirected graph from pairs
     */
    fun createFromPairs(pairs: List<Pair<Int, Int>>): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        for ((u, v) in pairs) {
            graph.computeIfAbsent(u) { mutableListOf() }.add(v)
            graph.computeIfAbsent(v) { mutableListOf() }.add(u)
        }
        return graph
    }
    
    /**
     * Creates undirected graph from range
     */
    fun createFromRange(range: IntRange): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        for (node in range) {
            graph[node] = mutableListOf()
        }
        return graph
    }
    
    /**
     * Creates undirected graph from sequence
     */
    fun createFromSequence(sequence: Sequence<Int>): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        for (node in sequence) {
            graph[node] = mutableListOf()
        }
        return graph
    }
    
    /**
     * Creates undirected graph with initial values
     */
    fun createWithInitialValue(size: Int, initialValue: Int): Array<IntArray> {
        return Array(size) { IntArray(size) { initialValue } }
    }
    
    /**
     * Creates undirected graph with random values
     */
    fun createWithRandomValues(size: Int, range: IntRange): Array<IntArray> {
        return Array(size) { IntArray(size) { range.random() } }
    }
    
    /**
     * Creates linear undirected graph
     */
    fun createLinearUndirectedGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        for (i in 0 until size - 1) {
            graph.computeIfAbsent(i) { mutableListOf() }.add(i + 1)
            graph.computeIfAbsent(i + 1) { mutableListOf() }.add(i)
        }
        return graph
    }
    
    /**
     * Creates circular undirected graph
     */
    fun createCircularUndirectedGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createLinearUndirectedGraph(size)
        graph.computeIfAbsent(0) { mutableListOf() }.add(size - 1)
        graph.computeIfAbsent(size - 1) { mutableListOf() }.add(0)
        return graph
    }
    
    /**
     * Creates star undirected graph
     */
    fun createStarUndirectedGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        for (i in 1 until size) {
            graph.computeIfAbsent(0) { mutableListOf() }.add(i)
            graph.computeIfAbsent(i) { mutableListOf() }.add(0)
        }
        return graph
    }
    
    /**
     * Creates complete undirected graph
     */
    fun createCompleteUndirectedGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        for (i in 0 until size) {
            for (j in i + 1 until size) {
                graph.computeIfAbsent(i) { mutableListOf() }.add(j)
                graph.computeIfAbsent(j) { mutableListOf() }.add(i)
            }
        }
        return graph
    }
    
    /**
     * Creates undirected graph from string
     */
    fun createFromString(graphString: String): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        val edges = graphString.split(",")
        for (edge in edges) {
            val nodes = edge.split("-")
            if (nodes.size == 2) {
                val u = nodes[0].trim().toInt()
                val v = nodes[1].trim().toInt()
                graph.computeIfAbsent(u) { mutableListOf() }.add(v)
                graph.computeIfAbsent(v) { mutableListOf() }.add(u)
            }
        }
        return graph
    }
    
    /**
     * Creates undirected graph from matrix
     */
    fun createFromMatrix(matrix: Array<IntArray>): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        for (i in matrix.indices) {
            for (j in i until matrix[i].size) {
                if (matrix[i][j] == 1) {
                    graph.computeIfAbsent(i) { mutableListOf() }.add(j)
                    graph.computeIfAbsent(j) { mutableListOf() }.add(i)
                }
            }
        }
        return graph
    }
    
    /**
     * Creates undirected graph with filtered edges
     */
    fun createFromFiltered(edges: List<Pair<Int, Int>>, filter: (Pair<Int, Int>) -> Boolean): MutableMap<Int, MutableList<Int>> {
        val filteredEdges = edges.filter(filter)
        return createFromEdges(filteredEdges)
    }
    
    /**
     * Creates undirected graph with transformed edges
     */
    fun createFromTransformed(edges: List<Pair<Int, Int>>, transform: (Pair<Int, Int>) -> Pair<Int, Int>): MutableMap<Int, MutableList<Int>> {
        val transformedEdges = edges.map(transform)
        return createFromEdges(transformedEdges)
    }
    
    /**
     * Creates undirected graph with sorted edges
     */
    fun createFromSorted(edges: List<Pair<Int, Int>>): MutableMap<Int, MutableList<Int>> {
        val sortedEdges = edges.sortedWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })
        return createFromEdges(sortedEdges)
    }
    
    /**
     * Creates undirected graph with unique edges
     */
    fun createFromUnique(edges: List<Pair<Int, Int>>): MutableMap<Int, MutableList<Int>> {
        val uniqueEdges = edges.distinct()
        return createFromEdges(uniqueEdges)
    }
    
    /**
     * Creates undirected graph from character arrays
     */
    fun createFromChars(charEdges: Array<CharArray>): MutableMap<Char, MutableList<Char>> {
        val graph = mutableMapOf<Char, MutableList<Char>>()
        for (edge in charEdges) {
            if (edge.size >= 2) {
                graph.computeIfAbsent(edge[0]) { mutableListOf() }.add(edge[1])
                graph.computeIfAbsent(edge[1]) { mutableListOf() }.add(edge[0])
            }
        }
        return graph
    }
    
    /**
     * Creates undirected graph from prefix sums
     */
    fun createFromPrefixSums(nodes: List<Int>): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        val prefixSums = nodes.runningFold(0) { acc, num -> acc + num }.drop(1)
        for (i in nodes.indices) {
            graph.computeIfAbsent(nodes[i]) { mutableListOf() }.add(prefixSums[i])
            graph.computeIfAbsent(prefixSums[i]) { mutableListOf() }.add(nodes[i])
        }
        return graph
    }
    
    /**
     * Creates undirected graph from difference array
     */
    fun createFromDifferenceArray(arr: IntArray): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        for (i in 0 until arr.size - 1) {
            val diff = arr[i + 1] - arr[i]
            graph.computeIfAbsent(arr[i]) { mutableListOf() }.add(diff)
            graph.computeIfAbsent(diff) { mutableListOf() }.add(arr[i])
        }
        return graph
    }
    
    /**
     * Creates undirected tree
     */
    fun createUndirectedTree(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        for (i in 1 until size) {
            val parent = (i - 1) / 2
            graph.computeIfAbsent(parent) { mutableListOf() }.add(i)
            graph.computeIfAbsent(i) { mutableListOf() }.add(parent)
        }
        return graph
    }
    
    /**
     * Creates undirected cycle
     */
    fun createUndirectedCycle(size: Int): MutableMap<Int, MutableList<Int>> {
        return createCircularUndirectedGraph(size)
    }
    
    /**
     * Creates undirected path
     */
    fun createUndirectedPath(size: Int): MutableMap<Int, MutableList<Int>> {
        return createLinearUndirectedGraph(size)
    }
    
    /**
     * Creates bipartite undirected graph
     */
    fun createBipartiteUndirectedGraph(leftSize: Int, rightSize: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        for (i in 0 until leftSize) {
            for (j in leftSize until leftSize + rightSize) {
                graph.computeIfAbsent(i) { mutableListOf() }.add(j)
                graph.computeIfAbsent(j) { mutableListOf() }.add(i)
            }
        }
        return graph
    }
    
    /**
     * Creates grid undirected graph
     */
    fun createGridUndirectedGraph(rows: Int, cols: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                val node = i * cols + j
                // Add right neighbor
                if (j + 1 < cols) {
                    val right = i * cols + (j + 1)
                    graph.computeIfAbsent(node) { mutableListOf() }.add(right)
                    graph.computeIfAbsent(right) { mutableListOf() }.add(node)
                }
                // Add bottom neighbor
                if (i + 1 < rows) {
                    val bottom = (i + 1) * cols + j
                    graph.computeIfAbsent(node) { mutableListOf() }.add(bottom)
                    graph.computeIfAbsent(bottom) { mutableListOf() }.add(node)
                }
            }
        }
        return graph
    }
    
    /**
     * Creates cube undirected graph
     */
    fun createCubeUndirectedGraph(dimension: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        val size = 1 shl dimension
        for (i in 0 until size) {
            for (j in 0 until dimension) {
                val neighbor = i xor (1 shl j)
                if (neighbor < size) {
                    graph.computeIfAbsent(i) { mutableListOf() }.add(neighbor)
                    graph.computeIfAbsent(neighbor) { mutableListOf() }.add(i)
                }
            }
        }
        return graph
    }
    
    /**
     * Creates wheel undirected graph
     */
    fun createWheelUndirectedGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createCircularUndirectedGraph(size - 1)
        val center = size - 1
        for (i in 0 until size - 1) {
            graph.computeIfAbsent(center) { mutableListOf() }.add(i)
            graph.computeIfAbsent(i) { mutableListOf() }.add(center)
        }
        return graph
    }
    
    /**
     * Creates ladder undirected graph
     */
    fun createLadderUndirectedGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createUndirectedGraph()
        for (i in 0 until size - 1) {
            // Top rung
            graph.computeIfAbsent(i) { mutableListOf() }.add(i + 1)
            graph.computeIfAbsent(i + 1) { mutableListOf() }.add(i)
            // Bottom rung
            val bottomI = i + size
            val bottomNext = i + 1 + size
            graph.computeIfAbsent(bottomI) { mutableListOf() }.add(bottomNext)
            graph.computeIfAbsent(bottomNext) { mutableListOf() }.add(bottomI)
            // Vertical edges
            graph.computeIfAbsent(i) { mutableListOf() }.add(bottomI)
            graph.computeIfAbsent(bottomI) { mutableListOf() }.add(i)
        }
        return graph
    }
} 