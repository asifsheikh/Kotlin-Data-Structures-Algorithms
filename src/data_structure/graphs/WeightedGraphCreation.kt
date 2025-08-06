package data_structure.graphs

/**
 * WEIGHTED GRAPH CREATION - Quick Reference
 * All Kotlin weighted graph creation methods in one place
 */

object WeightedGraphCreation {
    
    /**
     * Weighted edge data class
     */
    data class WeightedEdge(val u: Int, val v: Int, val weight: Int) : Comparable<WeightedEdge> {
        override fun compareTo(other: WeightedEdge): Int {
            return this.weight.compareTo(other.weight)
        }
    }
    
    /**
     * Weighted graph class
     */
    class WeightedGraph {
        private val graph = mutableMapOf<Int, MutableList<Pair<Int, Int>>>() // (node, weight)
        
        fun addEdge(u: Int, v: Int, weight: Int, directed: Boolean = true) {
            graph.computeIfAbsent(u) { mutableListOf() }.add(v to weight)
            if (!directed) {
                graph.computeIfAbsent(v) { mutableListOf() }.add(u to weight)
            }
        }
        
        fun getNeighbors(node: Int): List<Pair<Int, Int>> {
            return graph[node] ?: emptyList()
        }
        
        fun getAllEdges(): List<WeightedEdge> {
            val edges = mutableListOf<WeightedEdge>()
            for ((u, neighbors) in graph) {
                for ((v, weight) in neighbors) {
                    edges.add(WeightedEdge(u, v, weight))
                }
            }
            return edges
        }
        
        fun getNodes(): Set<Int> {
            return graph.keys
        }
    }
    
    /**
     * All Weighted Graph Creation Methods
     */
    fun allWeightedGraphCreationMethods() {
        // === BASIC WEIGHTED GRAPH CREATION ===
        val emptyWeightedGraph = WeightedGraph()                    // Empty weighted graph
        val weightedGraph = WeightedGraph()                         // Weighted graph for use
        
        // === FROM EDGES WITH WEIGHTS ===
        val weightedEdges = listOf(
            WeightedEdge(0, 1, 5),
            WeightedEdge(1, 2, 3),
            WeightedEdge(2, 3, 7)
        )
        val graphFromWeightedEdges = createFromWeightedEdges(weightedEdges) // Graph from weighted edges
        
        // === FROM TRIPLES ===
        val edgeTriples = listOf(Triple(0, 1, 5), Triple(1, 2, 3), Triple(2, 3, 7))
        val graphFromTriples = createFromTriples(edgeTriples)       // Graph from triples
        
        // === FROM ARRAYS ===
        val edgeArrays = arrayOf(intArrayOf(0, 1, 5), intArrayOf(1, 2, 3), intArrayOf(2, 3, 7))
        val graphFromArrays = createFromArrays(edgeArrays)          // Graph from arrays
        
        // === WITH UNIFORM WEIGHTS ===
        val uniformWeightGraph = createWithUniformWeight(5, 1)      // Graph with uniform weight 1
        val uniformWeightGraph2 = createWithUniformWeight(5, 10)    // Graph with uniform weight 10
        
        // === WITH RANDOM WEIGHTS ===
        val randomWeightGraph = createWithRandomWeights(5, 1..10)   // Graph with random weights 1-10
        val randomWeightGraph2 = createWithRandomWeights(5, 100..1000) // Graph with random weights 100-1000
        
        // === WITH SEQUENTIAL WEIGHTS ===
        val sequentialWeightGraph = createWithSequentialWeights(5)  // Graph with weights 1, 2, 3, ...
        
        // === WITH FIBONACCI WEIGHTS ===
        val fibonacciWeightGraph = createWithFibonacciWeights(5)    // Graph with Fibonacci weights
        
        // === WITH POWER WEIGHTS ===
        val powerWeightGraph = createWithPowerWeights(5, 2)         // Graph with power weights (2^n)
        
        // === WITH ALTERNATING WEIGHTS ===
        val alternatingWeightGraph = createWithAlternatingWeights(5) // Graph with alternating weights
        
        // === WITH NEGATIVE WEIGHTS ===
        val negativeWeightGraph = createWithNegativeWeights(5)      // Graph with negative weights
        
        // === WITH ZERO WEIGHTS ===
        val zeroWeightGraph = createWithZeroWeights(5)              // Graph with zero weights
        
        // === WITH CUSTOM WEIGHT FUNCTION ===
        val customWeightGraph = createWithCustomWeight(5) { u, v -> u * v } // Graph with custom weight function
    }
    
    /**
     * Creates weighted graph from weighted edges
     */
    fun createFromWeightedEdges(edges: List<WeightedEdge>): WeightedGraph {
        val graph = WeightedGraph()
        for (edge in edges) {
            graph.addEdge(edge.u, edge.v, edge.weight)
        }
        return graph
    }
    
    /**
     * Creates weighted graph from triples
     */
    fun createFromTriples(triples: List<Triple<Int, Int, Int>>): WeightedGraph {
        val graph = WeightedGraph()
        for ((u, v, weight) in triples) {
            graph.addEdge(u, v, weight)
        }
        return graph
    }
    
    /**
     * Creates weighted graph from arrays
     */
    fun createFromArrays(arrays: Array<IntArray>): WeightedGraph {
        val graph = WeightedGraph()
        for (array in arrays) {
            if (array.size >= 3) {
                graph.addEdge(array[0], array[1], array[2])
            }
        }
        return graph
    }
    
    /**
     * Creates weighted graph with uniform weight
     */
    fun createWithUniformWeight(size: Int, weight: Int): WeightedGraph {
        val graph = WeightedGraph()
        for (i in 0 until size - 1) {
            graph.addEdge(i, i + 1, weight)
        }
        return graph
    }
    
    /**
     * Creates weighted graph with random weights
     */
    fun createWithRandomWeights(size: Int, range: IntRange): WeightedGraph {
        val graph = WeightedGraph()
        for (i in 0 until size - 1) {
            graph.addEdge(i, i + 1, range.random())
        }
        return graph
    }
    
    /**
     * Creates weighted graph with sequential weights
     */
    fun createWithSequentialWeights(size: Int): WeightedGraph {
        val graph = WeightedGraph()
        for (i in 0 until size - 1) {
            graph.addEdge(i, i + 1, i + 1)
        }
        return graph
    }
    
    /**
     * Creates weighted graph with Fibonacci weights
     */
    fun createWithFibonacciWeights(size: Int): WeightedGraph {
        val graph = WeightedGraph()
        var a = 1
        var b = 1
        for (i in 0 until size - 1) {
            graph.addEdge(i, i + 1, a)
            val temp = a + b
            a = b
            b = temp
        }
        return graph
    }
    
    /**
     * Creates weighted graph with power weights
     */
    fun createWithPowerWeights(size: Int, base: Int): WeightedGraph {
        val graph = WeightedGraph()
        for (i in 0 until size - 1) {
            graph.addEdge(i, i + 1, base.pow(i + 1))
        }
        return graph
    }
    
    /**
     * Creates weighted graph with alternating weights
     */
    fun createWithAlternatingWeights(size: Int): WeightedGraph {
        val graph = WeightedGraph()
        for (i in 0 until size - 1) {
            val weight = if (i % 2 == 0) 1 else -1
            graph.addEdge(i, i + 1, weight)
        }
        return graph
    }
    
    /**
     * Creates weighted graph with negative weights
     */
    fun createWithNegativeWeights(size: Int): WeightedGraph {
        val graph = WeightedGraph()
        for (i in 0 until size - 1) {
            graph.addEdge(i, i + 1, -(i + 1))
        }
        return graph
    }
    
    /**
     * Creates weighted graph with zero weights
     */
    fun createWithZeroWeights(size: Int): WeightedGraph {
        val graph = WeightedGraph()
        for (i in 0 until size - 1) {
            graph.addEdge(i, i + 1, 0)
        }
        return graph
    }
    
    /**
     * Creates weighted graph with custom weight function
     */
    fun createWithCustomWeight(size: Int, weightFunction: (Int, Int) -> Int): WeightedGraph {
        val graph = WeightedGraph()
        for (i in 0 until size - 1) {
            graph.addEdge(i, i + 1, weightFunction(i, i + 1))
        }
        return graph
    }
    
    /**
     * Extension function for Int power
     */
    private fun Int.pow(exponent: Int): Int {
        var result = 1
        repeat(exponent) { result *= this }
        return result
    }
} 