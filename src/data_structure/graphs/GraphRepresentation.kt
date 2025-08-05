package data_structure.graphs

/**
 * GRAPH REPRESENTATION
 * 
 * This file contains different ways to represent graphs:
 * - Adjacency List (most common for interviews)
 * - Adjacency Matrix (for dense graphs)
 * - Edge List (for specific algorithms)
 */

object GraphRepresentation {
    
    // ===== ADJACENCY LIST REPRESENTATION =====
    // Most common and efficient for sparse graphs
    fun createAdjacencyList(): MutableMap<Int, MutableList<Int>> {
        return mutableMapOf()
    }
    
    fun addEdgeAdjacencyList(graph: MutableMap<Int, MutableList<Int>>, u: Int, v: Int, directed: Boolean = true) {
        graph.computeIfAbsent(u) { mutableListOf() }.add(v)
        if (!directed) {
            graph.computeIfAbsent(v) { mutableListOf() }.add(u)
        }
    }
    
    fun printAdjacencyList(graph: Map<Int, List<Int>>) {
        println("Adjacency List:")
        for ((node, edges) in graph) {
            println("  $node -> $edges")
        }
    }
    
    // ===== ADJACENCY MATRIX REPRESENTATION =====
    // Good for dense graphs, O(1) edge lookup
    fun createAdjacencyMatrix(size: Int): Array<IntArray> {
        return Array(size) { IntArray(size) { 0 } }
    }
    
    fun addEdgeAdjacencyMatrix(matrix: Array<IntArray>, u: Int, v: Int, directed: Boolean = true) {
        matrix[u][v] = 1
        if (!directed) {
            matrix[v][u] = 1
        }
    }
    
    fun printAdjacencyMatrix(matrix: Array<IntArray>) {
        println("Adjacency Matrix:")
        for (row in matrix) {
            println("  ${row.contentToString()}")
        }
    }
    
    // ===== EDGE LIST REPRESENTATION =====
    // Useful for algorithms like Kruskal's MST
    data class Edge(val u: Int, val v: Int, val weight: Int = 1)
    
    fun createEdgeList(): MutableList<Edge> {
        return mutableListOf()
    }
    
    fun addEdgeEdgeList(edges: MutableList<Edge>, u: Int, v: Int, weight: Int = 1) {
        edges.add(Edge(u, v, weight))
    }
    
    fun printEdgeList(edges: List<Edge>) {
        println("Edge List:")
        for (edge in edges) {
            println("  ${edge.u} -> ${edge.v} (weight: ${edge.weight})")
        }
    }
    
    // ===== SAMPLE GRAPH CREATION =====
    fun createSampleGraph(): MutableMap<Int, MutableList<Int>> {
        val graph = createAdjacencyList()
        
        // Create sample directed graph
        addEdgeAdjacencyList(graph, 0, 1)
        addEdgeAdjacencyList(graph, 0, 2)
        addEdgeAdjacencyList(graph, 1, 2)
        addEdgeAdjacencyList(graph, 2, 0)
        addEdgeAdjacencyList(graph, 2, 3)
        addEdgeAdjacencyList(graph, 3, 3)
        
        return graph
    }
    
    fun createSampleUndirectedGraph(): MutableMap<Int, MutableList<Int>> {
        val graph = createAdjacencyList()
        
        // Create sample undirected graph
        addEdgeAdjacencyList(graph, 0, 1, false)
        addEdgeAdjacencyList(graph, 0, 2, false)
        addEdgeAdjacencyList(graph, 1, 2, false)
        addEdgeAdjacencyList(graph, 1, 3, false)
        addEdgeAdjacencyList(graph, 2, 3, false)
        
        return graph
    }
    
    // ===== GRAPH PROPERTIES =====
    fun getDegree(graph: Map<Int, List<Int>>, node: Int): Int {
        return graph[node]?.size ?: 0
    }
    
    fun getInDegree(graph: Map<Int, List<Int>>, node: Int): Int {
        return graph.values.count { it.contains(node) }
    }
    
    fun getOutDegree(graph: Map<Int, List<Int>>, node: Int): Int {
        return getDegree(graph, node)
    }
    
    fun isDirected(graph: Map<Int, List<Int>>): Boolean {
        // Simple heuristic: if we find any asymmetric edges, it's directed
        for ((node, neighbors) in graph) {
            for (neighbor in neighbors) {
                if (graph[neighbor]?.contains(node) != true) {
                    return true
                }
            }
        }
        return false
    }
    
    // ===== CONVERSION BETWEEN REPRESENTATIONS =====
    fun adjacencyListToMatrix(graph: Map<Int, List<Int>>, size: Int): Array<IntArray> {
        val matrix = createAdjacencyMatrix(size)
        for ((node, neighbors) in graph) {
            for (neighbor in neighbors) {
                matrix[node][neighbor] = 1
            }
        }
        return matrix
    }
    
    fun adjacencyMatrixToList(matrix: Array<IntArray>): MutableMap<Int, MutableList<Int>> {
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
} 