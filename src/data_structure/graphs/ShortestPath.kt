package data_structure.graphs

/**
 * SHORTEST PATH ALGORITHMS
 * 
 * This file contains shortest path implementations:
 * - Dijkstra's Algorithm (single source, non-negative weights)
 * - Bellman-Ford Algorithm (single source, negative weights allowed)
 * - Floyd-Warshall Algorithm (all pairs shortest path)
 * - BFS for unweighted graphs
 */

object ShortestPath {
    
    // ===== DIJKSTRA'S ALGORITHM =====
    fun dijkstra(graph: Map<Int, List<Pair<Int, Int>>>, start: Int): Map<Int, Int> {
        val distances = mutableMapOf<Int, Int>()
        val pq = java.util.PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        
        // Initialize distances
        for (node in graph.keys) {
            distances[node] = Int.MAX_VALUE
        }
        distances[start] = 0
        
        pq.add(start to 0)
        
        while (pq.isNotEmpty()) {
            val (current, dist) = pq.poll()
            
            // Skip if we found a shorter path already
            if (dist > distances[current]!!) continue
            
            for ((neighbor, weight) in graph[current] ?: emptyList()) {
                val newDist = dist + weight
                if (newDist < distances.getOrDefault(neighbor, Int.MAX_VALUE)) {
                    distances[neighbor] = newDist
                    pq.add(neighbor to newDist)
                }
            }
        }
        
        return distances
    }
    
    // ===== DIJKSTRA WITH PATH TRACKING =====
    fun dijkstraWithPath(graph: Map<Int, List<Pair<Int, Int>>>, start: Int): Pair<Map<Int, Int>, Map<Int, Int>> {
        val distances = mutableMapOf<Int, Int>()
        val previous = mutableMapOf<Int, Int>()
        val pq = java.util.PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        
        // Initialize
        for (node in graph.keys) {
            distances[node] = Int.MAX_VALUE
        }
        distances[start] = 0
        
        pq.add(start to 0)
        
        while (pq.isNotEmpty()) {
            val (current, dist) = pq.poll()
            
            if (dist > distances[current]!!) continue
            
            for ((neighbor, weight) in graph[current] ?: emptyList()) {
                val newDist = dist + weight
                if (newDist < distances.getOrDefault(neighbor, Int.MAX_VALUE)) {
                    distances[neighbor] = newDist
                    previous[neighbor] = current
                    pq.add(neighbor to newDist)
                }
            }
        }
        
        return Pair(distances, previous)
    }
    
    fun reconstructPath(previous: Map<Int, Int>, start: Int, end: Int): List<Int>? {
        if (end !in previous && end != start) return null
        
        val path = mutableListOf<Int>()
        var current = end
        
        while (current != start) {
            path.add(0, current)
            current = previous[current] ?: return null
        }
        path.add(0, start)
        
        return path
    }
    
    // ===== BELLMAN-FORD ALGORITHM =====
    fun bellmanFord(graph: Map<Int, List<Pair<Int, Int>>>, start: Int): Pair<Map<Int, Int>, Boolean> {
        val distances = mutableMapOf<Int, Int>()
        val edges = mutableListOf<Triple<Int, Int, Int>>()
        
        // Initialize distances and collect edges
        for (node in graph.keys) {
            distances[node] = Int.MAX_VALUE
            for ((neighbor, weight) in graph[node] ?: emptyList()) {
                edges.add(Triple(node, neighbor, weight))
            }
        }
        distances[start] = 0
        
        // Relax edges V-1 times
        for (i in 0 until graph.size - 1) {
            for ((u, v, weight) in edges) {
                if (distances[u] != Int.MAX_VALUE && distances[u]!! + weight < distances.getOrDefault(v, Int.MAX_VALUE)) {
                    distances[v] = distances[u]!! + weight
                }
            }
        }
        
        // Check for negative cycles
        for ((u, v, weight) in edges) {
            if (distances[u] != Int.MAX_VALUE && distances[u]!! + weight < distances.getOrDefault(v, Int.MAX_VALUE)) {
                return Pair(distances, true) // Negative cycle detected
            }
        }
        
        return Pair(distances, false)
    }
    
    // ===== FLOYD-WARSHALL ALGORITHM =====
    fun floydWarshall(graph: Map<Int, List<Pair<Int, Int>>>): Array<IntArray> {
        val n = graph.keys.maxOrNull()!! + 1
        val dist = Array(n) { IntArray(n) { Int.MAX_VALUE } }
        
        // Initialize distance matrix
        for (i in 0 until n) {
            dist[i][i] = 0
        }
        
        for ((node, neighbors) in graph) {
            for ((neighbor, weight) in neighbors) {
                dist[node][neighbor] = weight
            }
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
    
    // ===== BFS FOR UNWEIGHTED GRAPH SHORTEST PATH =====
    fun shortestPathBFS(graph: Map<Int, List<Int>>, start: Int, end: Int): Int {
        val visited = mutableSetOf<Int>()
        val queue = ArrayDeque<Pair<Int, Int>>() // (node, distance)
        
        queue.add(start to 0)
        visited.add(start)
        
        while (queue.isNotEmpty()) {
            val (current, distance) = queue.removeFirst()
            
            if (current == end) return distance
            
            for (neighbor in graph[current] ?: emptyList()) {
                if (neighbor !in visited) {
                    visited.add(neighbor)
                    queue.add(neighbor to (distance + 1))
                }
            }
        }
        
        return -1 // No path found
    }
    
    // ===== BFS SHORTEST PATH WITH PATH TRACKING =====
    fun shortestPathBFSWithPath(graph: Map<Int, List<Int>>, start: Int, end: Int): List<Int>? {
        val visited = mutableSetOf<Int>()
        val queue = ArrayDeque<Triple<Int, Int, List<Int>>>() // (node, distance, path)
        
        queue.add(Triple(start, 0, listOf(start)))
        visited.add(start)
        
        while (queue.isNotEmpty()) {
            val (current, distance, path) = queue.removeFirst()
            
            if (current == end) return path
            
            for (neighbor in graph[current] ?: emptyList()) {
                if (neighbor !in visited) {
                    visited.add(neighbor)
                    queue.add(Triple(neighbor, distance + 1, path + neighbor))
                }
            }
        }
        
        return null
    }
    
    // ===== MULTI-SOURCE BFS =====
    fun multiSourceBFS(graph: Map<Int, List<Int>>, sources: List<Int>): Map<Int, Int> {
        val distances = mutableMapOf<Int, Int>()
        val queue = ArrayDeque<Int>()
        
        // Initialize distances
        for (node in graph.keys) {
            distances[node] = Int.MAX_VALUE
        }
        
        // Add all sources to queue
        for (source in sources) {
            queue.add(source)
            distances[source] = 0
        }
        
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            
            for (neighbor in graph[current] ?: emptyList()) {
                if (distances[neighbor] == Int.MAX_VALUE) {
                    distances[neighbor] = distances[current]!! + 1
                    queue.add(neighbor)
                }
            }
        }
        
        return distances
    }
    
    // ===== 0-1 BFS (FOR GRAPHS WITH 0/1 WEIGHTS) =====
    fun zeroOneBFS(graph: Map<Int, List<Pair<Int, Int>>>, start: Int): Map<Int, Int> {
        val distances = mutableMapOf<Int, Int>()
        val deque = ArrayDeque<Int>()
        
        // Initialize distances
        for (node in graph.keys) {
            distances[node] = Int.MAX_VALUE
        }
        distances[start] = 0
        
        deque.add(start)
        
        while (deque.isNotEmpty()) {
            val current = deque.removeFirst()
            
            for ((neighbor, weight) in graph[current] ?: emptyList()) {
                if (distances[current]!! + weight < distances.getOrDefault(neighbor, Int.MAX_VALUE)) {
                    distances[neighbor] = distances[current]!! + weight
                    
                    if (weight == 0) {
                        deque.addFirst(neighbor) // Add to front for 0 weight
                    } else {
                        deque.addLast(neighbor) // Add to back for 1 weight
                    }
                }
            }
        }
        
        return distances
    }
    
    // ===== SHORTEST PATH IN 2D GRID =====
    fun shortestPathInGrid(grid: Array<IntArray>, start: Pair<Int, Int>, end: Pair<Int, Int>): Int {
        if (grid.isEmpty()) return -1
        
        val rows = grid.size
        val cols = grid[0].size
        val visited = Array(rows) { BooleanArray(cols) }
        val queue = ArrayDeque<Triple<Int, Int, Int>>() // (row, col, distance)
        
        val directions = arrayOf(
            intArrayOf(-1, 0), // up
            intArrayOf(1, 0),  // down
            intArrayOf(0, -1), // left
            intArrayOf(0, 1)   // right
        )
        
        queue.add(Triple(start.first, start.second, 0))
        visited[start.first][start.second] = true
        
        while (queue.isNotEmpty()) {
            val (row, col, distance) = queue.removeFirst()
            
            if (row == end.first && col == end.second) return distance
            
            for ((dr, dc) in directions) {
                val nr = row + dr
                val nc = col + dc
                
                if (nr in 0 until rows && nc in 0 until cols && 
                    grid[nr][nc] == 0 && !visited[nr][nc]) {
                    visited[nr][nc] = true
                    queue.add(Triple(nr, nc, distance + 1))
                }
            }
        }
        
        return -1
    }
    
    // ===== SHORTEST PATH WITH OBSTACLES =====
    fun shortestPathWithObstacles(grid: Array<IntArray>, k: Int): Int {
        if (grid.isEmpty()) return -1
        
        val rows = grid.size
        val cols = grid[0].size
        val visited = Array(rows) { Array(cols) { BooleanArray(k + 1) } }
        val queue = ArrayDeque<Quadruple<Int, Int, Int, Int>>() // (row, col, obstacles, distance)
        
        val directions = arrayOf(
            intArrayOf(-1, 0), // up
            intArrayOf(1, 0),  // down
            intArrayOf(0, -1), // left
            intArrayOf(0, 1)   // right
        )
        
        queue.add(Quadruple(0, 0, 0, 0))
        visited[0][0][0] = true
        
        while (queue.isNotEmpty()) {
            val (row, col, obstacles, distance) = queue.removeFirst()
            
            if (row == rows - 1 && col == cols - 1) return distance
            
            for ((dr, dc) in directions) {
                val nr = row + dr
                val nc = col + dc
                
                if (nr in 0 until rows && nc in 0 until cols) {
                    val newObstacles = obstacles + grid[nr][nc]
                    
                    if (newObstacles <= k && !visited[nr][nc][newObstacles]) {
                        visited[nr][nc][newObstacles] = true
                        queue.add(Quadruple(nr, nc, newObstacles, distance + 1))
                    }
                }
            }
        }
        
        return -1
    }
    
    // Helper data class for 4-tuple
    data class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)
} 