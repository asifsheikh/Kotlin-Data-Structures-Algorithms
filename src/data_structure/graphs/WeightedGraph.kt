package data_structure.graphs

/**
 * WEIGHTED GRAPH ALGORITHMS
 * 
 * This file contains weighted graph implementations:
 * - Weighted graph representation
 * - Minimum Spanning Tree (Kruskal's, Prim's)
 * - Weighted graph utilities
 */

object WeightedGraph {
    
    // ===== WEIGHTED GRAPH REPRESENTATION =====
    data class WeightedEdge(val u: Int, val v: Int, val weight: Int) : Comparable<WeightedEdge> {
        override fun compareTo(other: WeightedEdge): Int {
            return this.weight.compareTo(other.weight)
        }
    }
    
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
        
        fun printGraph() {
            println("Weighted Graph:")
            for ((node, neighbors) in graph) {
                println("  $node -> ${neighbors.map { "${it.first}(${it.second})" }}")
            }
        }
    }
    
    // ===== KRUSKAL'S ALGORITHM (MINIMUM SPANNING TREE) =====
    fun kruskalMST(graph: WeightedGraph): List<WeightedEdge> {
        val edges = graph.getAllEdges().sorted()
        val unionFind = UnionFind(graph.getNodes().size)
        val mst = mutableListOf<WeightedEdge>()
        
        for (edge in edges) {
            if (!unionFind.connected(edge.u, edge.v)) {
                unionFind.union(edge.u, edge.v)
                mst.add(edge)
            }
        }
        
        return mst
    }
    
    // ===== PRIM'S ALGORITHM (MINIMUM SPANNING TREE) =====
    fun primMST(graph: WeightedGraph, start: Int): List<WeightedEdge> {
        val visited = mutableSetOf<Int>()
        val pq = java.util.PriorityQueue<WeightedEdge>()
        val mst = mutableListOf<WeightedEdge>()
        
        visited.add(start)
        
        // Add all edges from start node
        for ((neighbor, weight) in graph.getNeighbors(start)) {
            pq.add(WeightedEdge(start, neighbor, weight))
        }
        
        while (pq.isNotEmpty() && visited.size < graph.getNodes().size) {
            val edge = pq.poll()
            
            if (edge.v !in visited) {
                visited.add(edge.v)
                mst.add(edge)
                
                // Add edges from newly visited node
                for ((neighbor, weight) in graph.getNeighbors(edge.v)) {
                    if (neighbor !in visited) {
                        pq.add(WeightedEdge(edge.v, neighbor, weight))
                    }
                }
            }
        }
        
        return mst
    }
    
    // ===== MINIMUM SPANNING TREE WEIGHT =====
    fun mstWeight(edges: List<WeightedEdge>): Int {
        return edges.sumOf { it.weight }
    }
    
    // ===== SECOND MINIMUM SPANNING TREE =====
    fun secondMST(graph: WeightedGraph): List<WeightedEdge>? {
        val mst = kruskalMST(graph)
        var secondMst: List<WeightedEdge>? = null
        var minWeight = Int.MAX_VALUE
        
        // Try removing each edge from MST and find new MST
        for (excludedEdge in mst) {
            val tempGraph = WeightedGraph()
            
            // Rebuild graph without excluded edge
            for (edge in graph.getAllEdges()) {
                if (edge != excludedEdge) {
                    tempGraph.addEdge(edge.u, edge.v, edge.weight, false)
                }
            }
            
            val newMst = kruskalMST(tempGraph)
            if (newMst.size == mst.size) {
                val weight = mstWeight(newMst)
                if (weight < minWeight) {
                    minWeight = weight
                    secondMst = newMst
                }
            }
        }
        
        return secondMst
    }
    
    // ===== MAXIMUM SPANNING TREE =====
    fun maxSpanningTree(graph: WeightedGraph): List<WeightedEdge> {
        val edges = graph.getAllEdges().sortedDescending()
        val unionFind = UnionFind(graph.getNodes().size)
        val mst = mutableListOf<WeightedEdge>()
        
        for (edge in edges) {
            if (!unionFind.connected(edge.u, edge.v)) {
                unionFind.union(edge.u, edge.v)
                mst.add(edge)
            }
        }
        
        return mst
    }
    
    // ===== SHORTEST PATH IN WEIGHTED GRAPH =====
    fun shortestPathWeighted(graph: WeightedGraph, start: Int, end: Int): Int {
        val distances = mutableMapOf<Int, Int>()
        val pq = java.util.PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        
        // Initialize distances
        for (node in graph.getNodes()) {
            distances[node] = Int.MAX_VALUE
        }
        distances[start] = 0
        
        pq.add(start to 0)
        
        while (pq.isNotEmpty()) {
            val (current, dist) = pq.poll()
            
            if (current == end) return dist
            if (dist > distances[current]!!) continue
            
            for ((neighbor, weight) in graph.getNeighbors(current)) {
                val newDist = dist + weight
                if (newDist < distances.getOrDefault(neighbor, Int.MAX_VALUE)) {
                    distances[neighbor] = newDist
                    pq.add(neighbor to newDist)
                }
            }
        }
        
        return -1 // No path found
    }
    
    // ===== ALL PAIRS SHORTEST PATH (FLOYD-WARSHALL) =====
    fun allPairsShortestPath(graph: WeightedGraph): Array<IntArray> {
        val nodes = graph.getNodes().toList()
        val n = nodes.size
        val dist = Array(n) { IntArray(n) { Int.MAX_VALUE } }
        
        // Initialize distance matrix
        for (i in 0 until n) {
            dist[i][i] = 0
        }
        
        for (edge in graph.getAllEdges()) {
            val u = nodes.indexOf(edge.u)
            val v = nodes.indexOf(edge.v)
            dist[u][v] = edge.weight
        }
        
        // Floyd-Warshall algorithm
        for (k in 0 until n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (dist[i][k] != Int.MAX_VALUE && dist[k][j] != Int.MAX_VALUE) {
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j]
                        }
                    }
                }
            }
        }
        
        return dist
    }
    
    // ===== NEGATIVE CYCLE DETECTION =====
    fun hasNegativeCycle(graph: WeightedGraph): Boolean {
        val nodes = graph.getNodes().toList()
        val n = nodes.size
        val dist = Array(n) { IntArray(n) { Int.MAX_VALUE } }
        
        // Initialize distance matrix
        for (i in 0 until n) {
            dist[i][i] = 0
        }
        
        for (edge in graph.getAllEdges()) {
            val u = nodes.indexOf(edge.u)
            val v = nodes.indexOf(edge.v)
            dist[u][v] = edge.weight
        }
        
        // Floyd-Warshall algorithm
        for (k in 0 until n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (dist[i][k] != Int.MAX_VALUE && dist[k][j] != Int.MAX_VALUE) {
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j]
                        }
                    }
                }
            }
        }
        
        // Check for negative cycles
        for (i in 0 until n) {
            if (dist[i][i] < 0) return true
        }
        
        return false
    }
    
    // ===== MINIMUM COST TO CONNECT ALL POINTS =====
    fun minCostConnectPoints(points: Array<IntArray>): Int {
        val n = points.size
        val edges = mutableListOf<WeightedEdge>()
        
        // Create edges between all pairs of points
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                val distance = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])
                edges.add(WeightedEdge(i, j, distance))
            }
        }
        
        // Sort edges by weight
        edges.sort()
        
        val unionFind = UnionFind(n)
        var totalCost = 0
        
        for (edge in edges) {
            if (!unionFind.connected(edge.u, edge.v)) {
                unionFind.union(edge.u, edge.v)
                totalCost += edge.weight
            }
        }
        
        return totalCost
    }
    
    // ===== NETWORK DELAY TIME =====
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
        val graph = WeightedGraph()
        
        // Build graph
        for ((u, v, w) in times) {
            graph.addEdge(u - 1, v - 1, w) // Convert to 0-based indexing
        }
        
        val distances = ShortestPath.dijkstra(graph.getNodes().associateWith { graph.getNeighbors(it) }, k - 1)
        
        val maxDistance = distances.values.maxOrNull() ?: 0
        return if (maxDistance == Int.MAX_VALUE) -1 else maxDistance
    }
    
    // ===== CHEAPEST FLIGHTS WITHIN K STOPS =====
    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        val graph = WeightedGraph()
        
        // Build graph
        for ((from, to, price) in flights) {
            graph.addEdge(from, to, price)
        }
        
        val distances = Array(n) { IntArray(k + 2) { Int.MAX_VALUE } }
        val pq = java.util.PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third }) // (node, stops, cost)
        
        distances[src][0] = 0
        pq.add(Triple(src, 0, 0))
        
        while (pq.isNotEmpty()) {
            val (current, stops, cost) = pq.poll()
            
            if (current == dst) return cost
            if (stops > k) continue
            if (cost > distances[current][stops]) continue
            
            for ((neighbor, weight) in graph.getNeighbors(current)) {
                val newCost = cost + weight
                if (newCost < distances[neighbor][stops + 1]) {
                    distances[neighbor][stops + 1] = newCost
                    pq.add(Triple(neighbor, stops + 1, newCost))
                }
            }
        }
        
        return -1
    }
    
    // Helper class for Union-Find (simplified version)
    private class UnionFind(size: Int) {
        private val parent = IntArray(size) { it }
        private val rank = IntArray(size) { 0 }
        
        fun find(x: Int): Int {
            if (parent[x] != x) {
                parent[x] = find(parent[x])
            }
            return parent[x]
        }
        
        fun union(x: Int, y: Int) {
            val rootX = find(x)
            val rootY = find(y)
            
            if (rootX == rootY) return
            
            when {
                rank[rootX] < rank[rootY] -> parent[rootX] = rootY
                rank[rootX] > rank[rootY] -> parent[rootY] = rootX
                else -> {
                    parent[rootY] = rootX
                    rank[rootX]++
                }
            }
        }
        
        fun connected(x: Int, y: Int): Boolean {
            return find(x) == find(y)
        }
    }
} 