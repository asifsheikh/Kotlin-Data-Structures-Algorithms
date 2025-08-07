package data_structure.graphs

/**
 * DIRECTED GRAPH CREATION - Quick Reference
 * All Kotlin directed graph creation methods in one place
 */

object DirectedGraphCreation {
    
    /**
     * All Directed Graph Creation Methods
     * Complete reference for creating directed graphs in Kotlin
     */
    fun allDirectedGraphCreationMethods() {
        // === BASIC DIRECTED GRAPH CREATION ===
        val emptyDirectedGraph = createDirectedGraph()              // Empty directed graph
        val directedGraph = createDirectedGraph()                   // Directed graph for use
        
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
        val linearDirectedGraph = createLinearDirectedGraph(5)      // Linear directed graph 0->1->2->3->4
        val circularDirectedGraph = createCircularDirectedGraph(5)  // Circular directed graph 0->1->2->3->4->0
        val starDirectedGraph = createStarDirectedGraph(5)          // Star directed graph with center 0
        val completeDirectedGraph = createCompleteDirectedGraph(4)  // Complete directed graph
        
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
        
        // === SPECIAL DIRECTED GRAPHS ===
        val dagGraph = createDAG(5)                                 // Directed Acyclic Graph
        val treeGraph = createDirectedTree(5)                       // Directed Tree
        val cycleGraph = createDirectedCycle(5)                     // Directed Cycle
        val pathGraph = createDirectedPath(5)                       // Directed Path
        val tournamentGraph = createTournamentGraph(5)              // Tournament Graph
        val bipartiteDirectedGraph = createBipartiteDirectedGraph(3, 3) // Bipartite Directed Graph
    }
    
    /**
     * Creates empty directed graph
     */
    fun createDirectedGraph(): MutableMap<Int, MutableList<Int>> {
        return mutableMapOf()
    }
    
    /**
     * Creates directed graph from edges
     */
    fun createFromEdges(edges: List<Pair<Int, Int>>): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
        for ((u, v) in edges) {
            graph.computeIfAbsent(u) { mutableListOf() }.add(v)
        }
        return graph
    }
    
    /**
     * Creates directed graph from arrays
     */
    fun createFromArrays(arrays: Array<IntArray>): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
        for (array in arrays) {
            if (array.size >= 2) {
                graph.computeIfAbsent(array[0]) { mutableListOf() }.add(array[1])
            }
        }
        return graph
    }
    
    /**
     * Creates directed graph from lists
     */
    fun createFromLists(lists: List<List<Int>>): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
        for (list in lists) {
            if (list.size >= 2) {
                graph.computeIfAbsent(list[0]) { mutableListOf() }.add(list[1])
            }
        }
        return graph
    }
    
    /**
     * Creates directed graph from pairs
     */
    fun createFromPairs(pairs: List<Pair<Int, Int>>): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
        for ((u, v) in pairs) {
            graph.computeIfAbsent(u) { mutableListOf() }.add(v)
        }
        return graph
    }
    
    /**
     * Creates directed graph from range
     */
    fun createFromRange(range: IntRange): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
        for (node in range) {
            graph[node] = mutableListOf()
        }
        return graph
    }
    
    /**
     * Creates directed graph from sequence
     */
    fun createFromSequence(sequence: Sequence<Int>): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
        for (node in sequence) {
            graph[node] = mutableListOf()
        }
        return graph
    }
    
    /**
     * Creates directed graph with initial values
     */
    fun createWithInitialValue(size: Int, initialValue: Int): Array<IntArray> {
        return Array(size) { IntArray(size) { initialValue } }
    }
    
    /**
     * Creates directed graph with random values
     */
    fun createWithRandomValues(size: Int, range: IntRange): Array<IntArray> {
        return Array(size) { IntArray(size) { range.random() } }
    }
    
    /**
     * Creates linear directed graph
     */
    fun createLinearDirectedGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
        for (i in 0 until size - 1) {
            graph.computeIfAbsent(i) { mutableListOf() }.add(i + 1)
        }
        return graph
    }
    
    /**
     * Creates circular directed graph
     */
    fun createCircularDirectedGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createLinearDirectedGraph(size)
        graph.computeIfAbsent(size - 1) { mutableListOf() }.add(0)
        return graph
    }
    
    /**
     * Creates star directed graph
     */
    fun createStarDirectedGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
        for (i in 1 until size) {
            graph.computeIfAbsent(0) { mutableListOf() }.add(i)
        }
        return graph
    }
    
    /**
     * Creates complete directed graph
     */
    fun createCompleteDirectedGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
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
     * Creates directed graph from string
     */
    fun createFromString(graphString: String): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
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
     * Creates directed graph from matrix
     */
    fun createFromMatrix(matrix: Array<IntArray>): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
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
     * Creates directed graph with filtered edges
     */
    fun createFromFiltered(edges: List<Pair<Int, Int>>, filter: (Pair<Int, Int>) -> Boolean): MutableMap<Int, MutableList<Int>> {
        val filteredEdges = edges.filter(filter)
        return createFromEdges(filteredEdges)
    }
    
    /**
     * Creates directed graph with transformed edges
     */
    fun createFromTransformed(edges: List<Pair<Int, Int>>, transform: (Pair<Int, Int>) -> Pair<Int, Int>): MutableMap<Int, MutableList<Int>> {
        val transformedEdges = edges.map(transform)
        return createFromEdges(transformedEdges)
    }
    
    /**
     * Creates directed graph with sorted edges
     */
    fun createFromSorted(edges: List<Pair<Int, Int>>): MutableMap<Int, MutableList<Int>> {
        val sortedEdges = edges.sortedWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })
        return createFromEdges(sortedEdges)
    }
    
    /**
     * Creates directed graph with unique edges
     */
    fun createFromUnique(edges: List<Pair<Int, Int>>): MutableMap<Int, MutableList<Int>> {
        val uniqueEdges = edges.distinct()
        return createFromEdges(uniqueEdges)
    }
    
    /**
     * Creates directed graph from character arrays
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
     * Creates directed graph from prefix sums
     */
    fun createFromPrefixSums(nodes: List<Int>): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
        val prefixSums = nodes.runningFold(0) { acc, num -> acc + num }.drop(1)
        for (i in nodes.indices) {
            graph.computeIfAbsent(nodes[i]) { mutableListOf() }.add(prefixSums[i])
        }
        return graph
    }
    
    /**
     * Creates directed graph from difference array
     */
    fun createFromDifferenceArray(arr: IntArray): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
        for (i in 0 until arr.size - 1) {
            val diff = arr[i + 1] - arr[i]
            graph.computeIfAbsent(arr[i]) { mutableListOf() }.add(diff)
        }
        return graph
    }
    
    /**
     * Creates Directed Acyclic Graph (DAG)
     */
    fun createDAG(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
        for (i in 0 until size - 1) {
            graph.computeIfAbsent(i) { mutableListOf() }.add(i + 1)
        }
        return graph
    }
    
    /**
     * Creates directed tree
     */
    fun createDirectedTree(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
        for (i in 1 until size) {
            val parent = (i - 1) / 2
            graph.computeIfAbsent(parent) { mutableListOf() }.add(i)
        }
        return graph
    }
    
    /**
     * Creates directed cycle
     */
    fun createDirectedCycle(size: Int): MutableMap<Int, MutableList<Int>> {
        return createCircularDirectedGraph(size)
    }
    
    /**
     * Creates directed path
     */
    fun createDirectedPath(size: Int): MutableMap<Int, MutableList<Int>> {
        return createLinearDirectedGraph(size)
    }
    
    /**
     * Creates tournament graph
     */
    fun createTournamentGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
        for (i in 0 until size) {
            for (j in i + 1 until size) {
                if (i < j) {
                    graph.computeIfAbsent(i) { mutableListOf() }.add(j)
                }
            }
        }
        return graph
    }
    
    /**
     * Creates bipartite directed graph
     */
    fun createBipartiteDirectedGraph(leftSize: Int, rightSize: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createDirectedGraph()
        for (i in 0 until leftSize) {
            for (j in leftSize until leftSize + rightSize) {
                graph.computeIfAbsent(i) { mutableListOf() }.add(j)
            }
        }
        return graph
    }
} 