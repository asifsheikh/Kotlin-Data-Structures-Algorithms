package data_structure.graphs

/**
 * BASIC GRAPH CREATION - Quick Reference
 * All Kotlin basic graph creation methods in one place
 */

object BasicGraphCreation {
    
    /**
     * All Basic Graph Creation Methods
     * Complete reference for creating basic graphs in Kotlin
     */
    fun allBasicGraphCreationMethods() {
        // === ADJACENCY LIST CREATION ===
        val emptyAdjList = createAdjacencyList()                    // Empty adjacency list
        val adjList = createAdjacencyList()                         // Adjacency list for graph
        
        // === ADJACENCY MATRIX CREATION ===
        val emptyMatrix = createAdjacencyMatrix(5)                  // Empty 5x5 matrix
        val matrix = createAdjacencyMatrix(10)                      // Empty 10x10 matrix
        
        // === EDGE LIST CREATION ===
        val emptyEdgeList = createEdgeList()                        // Empty edge list
        val edgeList = createEdgeList()                             // Edge list for graph
        
        // === FROM ARRAYS ===
        val edgesArray = arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 3))
        val graphFromArray = createFromEdgesArray(edgesArray)       // Graph from edges array
        
        // === FROM LISTS ===
        val edgesList = listOf(listOf(0, 1), listOf(1, 2), listOf(2, 3))
        val graphFromList = createFromEdgesList(edgesList)          // Graph from edges list
        
        // === FROM PAIRS ===
        val edgesPairs = listOf(0 to 1, 1 to 2, 2 to 3)
        val graphFromPairs = createFromEdgesPairs(edgesPairs)       // Graph from pairs
        
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
        val linearGraph = createLinearGraph(5)                      // Linear graph 0->1->2->3->4
        val circularGraph = createCircularGraph(5)                  // Circular graph 0->1->2->3->4->0
        val starGraph = createStarGraph(5)                          // Star graph with center 0
        val completeGraph = createCompleteGraph(4)                  // Complete graph K4
        
        // === FROM STRING REPRESENTATION ===
        val graphString = "0->1,1->2,2->3"
        val graphFromString = createFromString(graphString)         // Graph from string
        
        // === FROM MATRIX ===
        val matrixData = arrayOf(
            intArrayOf(0, 1, 0, 0),
            intArrayOf(0, 0, 1, 0),
            intArrayOf(0, 0, 0, 1),
            intArrayOf(0, 0, 0, 0)
        )
        val graphFromMatrix = createFromMatrix(matrixData)          // Graph from matrix
        
        // === WITH FILTERING ===
        val allEdges = listOf(0 to 1, 1 to 2, 2 to 3, 3 to 4)
        val filteredEdges = allEdges.filter { it.first < it.second }
        val graphFromFiltered = createFromFiltered(allEdges) { it.first < it.second } // Graph with filtered edges
        
        // === WITH TRANSFORMATION ===
        val originalEdges = listOf(0 to 1, 1 to 2, 2 to 3)
        val transformedEdges = originalEdges.map { it.first * 2 to it.second * 2 }
        val graphFromTransformed = createFromTransformed(originalEdges) { it.first * 2 to it.second * 2 } // Graph with transformed edges
        
        // === WITH SORTING ===
        val unsortedEdges = listOf(2 to 3, 0 to 1, 1 to 2)
        val sortedEdges = unsortedEdges.sortedWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })
        val graphFromSorted = createFromSorted(unsortedEdges)       // Graph with sorted edges
        
        // === WITH DEDUPLICATION ===
        val duplicateEdges = listOf(0 to 1, 1 to 2, 0 to 1, 2 to 3)
        val uniqueEdges = duplicateEdges.distinct()
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
    }
    
    /**
     * Creates empty adjacency list
     */
    fun createAdjacencyList(): MutableMap<Int, MutableList<Int>> {
        return mutableMapOf()
    }
    
    /**
     * Creates empty adjacency matrix
     */
    fun createAdjacencyMatrix(size: Int): Array<IntArray> {
        return Array(size) { IntArray(size) { 0 } }
    }
    
    /**
     * Creates empty edge list
     */
    fun createEdgeList(): MutableList<Pair<Int, Int>> {
        return mutableListOf()
    }
    
    /**
     * Creates graph from edges array
     */
    fun createFromEdgesArray(edges: Array<IntArray>): MutableMap<Int, MutableList<Int>> {
        val graph = createAdjacencyList()
        for (edge in edges) {
            if (edge.size >= 2) {
                graph.computeIfAbsent(edge[0]) { mutableListOf() }.add(edge[1])
            }
        }
        return graph
    }
    
    /**
     * Creates graph from edges list
     */
    fun createFromEdgesList(edges: List<List<Int>>): MutableMap<Int, MutableList<Int>> {
        val graph = createAdjacencyList()
        for (edge in edges) {
            if (edge.size >= 2) {
                graph.computeIfAbsent(edge[0]) { mutableListOf() }.add(edge[1])
            }
        }
        return graph
    }
    
    /**
     * Creates graph from edges pairs
     */
    fun createFromEdgesPairs(edges: List<Pair<Int, Int>>): MutableMap<Int, MutableList<Int>> {
        val graph = createAdjacencyList()
        for ((u, v) in edges) {
            graph.computeIfAbsent(u) { mutableListOf() }.add(v)
        }
        return graph
    }
    
    /**
     * Creates graph from range
     */
    fun createFromRange(range: IntRange): MutableMap<Int, MutableList<Int>> {
        val graph = createAdjacencyList()
        for (node in range) {
            graph[node] = mutableListOf()
        }
        return graph
    }
    
    /**
     * Creates graph from sequence
     */
    fun createFromSequence(sequence: Sequence<Int>): MutableMap<Int, MutableList<Int>> {
        val graph = createAdjacencyList()
        for (node in sequence) {
            graph[node] = mutableListOf()
        }
        return graph
    }
    
    /**
     * Creates graph with initial values
     */
    fun createWithInitialValue(size: Int, initialValue: Int): Array<IntArray> {
        return Array(size) { IntArray(size) { initialValue } }
    }
    
    /**
     * Creates graph with random values
     */
    fun createWithRandomValues(size: Int, range: IntRange): Array<IntArray> {
        return Array(size) { IntArray(size) { range.random() } }
    }
    
    /**
     * Creates linear graph
     */
    fun createLinearGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createAdjacencyList()
        for (i in 0 until size - 1) {
            graph.computeIfAbsent(i) { mutableListOf() }.add(i + 1)
        }
        return graph
    }
    
    /**
     * Creates circular graph
     */
    fun createCircularGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createLinearGraph(size)
        graph.computeIfAbsent(size - 1) { mutableListOf() }.add(0)
        return graph
    }
    
    /**
     * Creates star graph
     */
    fun createStarGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createAdjacencyList()
        for (i in 1 until size) {
            graph.computeIfAbsent(0) { mutableListOf() }.add(i)
        }
        return graph
    }
    
    /**
     * Creates complete graph
     */
    fun createCompleteGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createAdjacencyList()
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (i != j) {
                    graph.computeIfAbsent(i) { mutableListOf() }.add(j)
                }
            }
        }
        return graph
    }
    
    /**
     * Creates graph from string
     */
    fun createFromString(graphString: String): MutableMap<Int, MutableList<Int>> {
        val graph = createAdjacencyList()
        val edges = graphString.split(",")
        for (edge in edges) {
            val nodes = edge.split("->")
            if (nodes.size == 2) {
                val u = nodes[0].trim().toInt()
                val v = nodes[1].trim().toInt()
                graph.computeIfAbsent(u) { mutableListOf() }.add(v)
            }
        }
        return graph
    }
    
    /**
     * Creates graph from matrix
     */
    fun createFromMatrix(matrix: Array<IntArray>): MutableMap<Int, MutableList<Int>> {
        val graph = createAdjacencyList()
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == 1) {
                    graph.computeIfAbsent(i) { mutableListOf() }.add(j)
                }
            }
        }
        return graph
    }
    
    /**
     * Creates graph with filtered edges
     */
    fun createFromFiltered(edges: List<Pair<Int, Int>>, filter: (Pair<Int, Int>) -> Boolean): MutableMap<Int, MutableList<Int>> {
        val filteredEdges = edges.filter(filter)
        return createFromEdgesPairs(filteredEdges)
    }
    
    /**
     * Creates graph with transformed edges
     */
    fun createFromTransformed(edges: List<Pair<Int, Int>>, transform: (Pair<Int, Int>) -> Pair<Int, Int>): MutableMap<Int, MutableList<Int>> {
        val transformedEdges = edges.map(transform)
        return createFromEdgesPairs(transformedEdges)
    }
    
    /**
     * Creates graph with sorted edges
     */
    fun createFromSorted(edges: List<Pair<Int, Int>>): MutableMap<Int, MutableList<Int>> {
        val sortedEdges = edges.sortedWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })
        return createFromEdgesPairs(sortedEdges)
    }
    
    /**
     * Creates graph with unique edges
     */
    fun createFromUnique(edges: List<Pair<Int, Int>>): MutableMap<Int, MutableList<Int>> {
        val uniqueEdges = edges.distinct()
        return createFromEdgesPairs(uniqueEdges)
    }
    
    /**
     * Creates graph from character arrays
     */
    fun createFromChars(charEdges: Array<CharArray>): MutableMap<Char, MutableList<Char>> {
        val graph = mutableMapOf<Char, MutableList<Char>>()
        for (edge in charEdges) {
            if (edge.size >= 2) {
                graph.computeIfAbsent(edge[0]) { mutableListOf() }.add(edge[1])
            }
        }
        return graph
    }
    
    /**
     * Creates graph from prefix sums
     */
    fun createFromPrefixSums(nodes: List<Int>): MutableMap<Int, MutableList<Int>> {
        val graph = createAdjacencyList()
        val prefixSums = nodes.runningFold(0) { acc, num -> acc + num }.drop(1)
        for (i in nodes.indices) {
            graph.computeIfAbsent(nodes[i]) { mutableListOf() }.add(prefixSums[i])
        }
        return graph
    }
    
    /**
     * Creates graph from difference array
     */
    fun createFromDifferenceArray(arr: IntArray): MutableMap<Int, MutableList<Int>> {
        val graph = createAdjacencyList()
        for (i in 0 until arr.size - 1) {
            val diff = arr[i + 1] - arr[i]
            graph.computeIfAbsent(arr[i]) { mutableListOf() }.add(diff)
        }
        return graph
    }
} 