package data_structure.graphs.algorithms

/**
 * BFS PATH FINDING ALGORITHMS
 * 
 * Problem: Implement BFS-based path finding algorithms for unweighted graphs.
 * 
 * Examples:
 * Input: Graph with edges [(0,1), (0,2), (1,3), (2,3)], start = 0, end = 3
 * Output: 2 (shortest path length) or [0, 1, 3] (shortest path)
 * 
 * Intuition: BFS guarantees shortest path in unweighted graphs by exploring level by level
 * 
 * Time Complexity: O(V + E) - V = vertices, E = edges
 * Space Complexity: O(V) - for queue and visited set
 */

object BFSPathFinding {
    
    /**
     * BFS for shortest path length (unweighted graph)
     */
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
    
    /**
     * BFS for shortest path with path tracking
     */
    fun shortestPathWithPath(graph: Map<Int, List<Int>>, start: Int, end: Int): List<Int>? {
        val visited = mutableSetOf<Int>()
        val queue = ArrayDeque<Triple<Int, Int, List<Int>>>() // (node, distance, path)
        val parent = mutableMapOf<Int, Int>()
        
        queue.add(Triple(start, 0, listOf(start)))
        visited.add(start)
        
        while (queue.isNotEmpty()) {
            val (current, distance, path) = queue.removeFirst()
            
            if (current == end) return path
            
            for (neighbor in graph[current] ?: emptyList()) {
                if (neighbor !in visited) {
                    visited.add(neighbor)
                    parent[neighbor] = current
                    queue.add(Triple(neighbor, distance + 1, path + neighbor))
                }
            }
        }
        
        return null // No path found
    }
    
    /**
     * BFS for shortest path using parent tracking
     */
    fun shortestPathWithParentTracking(graph: Map<Int, List<Int>>, start: Int, end: Int): List<Int>? {
        val visited = mutableSetOf<Int>()
        val queue = ArrayDeque<Int>()
        val parent = mutableMapOf<Int, Int>()
        
        queue.add(start)
        visited.add(start)
        
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            
            if (current == end) {
                return reconstructPath(parent, start, end)
            }
            
            for (neighbor in graph[current] ?: emptyList()) {
                if (neighbor !in visited) {
                    visited.add(neighbor)
                    parent[neighbor] = current
                    queue.add(neighbor)
                }
            }
        }
        
        return null // No path found
    }
    
    /**
     * Reconstruct path from parent map
     */
    private fun reconstructPath(parent: Map<Int, Int>, start: Int, end: Int): List<Int> {
        val path = mutableListOf<Int>()
        var current = end
        
        while (current != start) {
            path.add(0, current)
            current = parent[current] ?: return emptyList()
        }
        path.add(0, start)
        
        return path
    }
    
    /**
     * BFS for all shortest paths from start
     */
    fun allShortestPaths(graph: Map<Int, List<Int>>, start: Int): Map<Int, Int> {
        val visited = mutableSetOf<Int>()
        val queue = ArrayDeque<Pair<Int, Int>>() // (node, distance)
        val distances = mutableMapOf<Int, Int>()
        
        queue.add(start to 0)
        visited.add(start)
        distances[start] = 0
        
        while (queue.isNotEmpty()) {
            val (current, distance) = queue.removeFirst()
            
            for (neighbor in graph[current] ?: emptyList()) {
                if (neighbor !in visited) {
                    visited.add(neighbor)
                    distances[neighbor] = distance + 1
                    queue.add(neighbor to (distance + 1))
                }
            }
        }
        
        return distances
    }
    
    /**
     * BFS for shortest path with multiple targets
     */
    fun shortestPathToMultipleTargets(
        graph: Map<Int, List<Int>>, 
        start: Int, 
        targets: Set<Int>
    ): Map<Int, Int> {
        val visited = mutableSetOf<Int>()
        val queue = ArrayDeque<Pair<Int, Int>>() // (node, distance)
        val distances = mutableMapOf<Int, Int>()
        val remainingTargets = targets.toMutableSet()
        
        queue.add(start to 0)
        visited.add(start)
        
        while (queue.isNotEmpty() && remainingTargets.isNotEmpty()) {
            val (current, distance) = queue.removeFirst()
            
            if (current in remainingTargets) {
                distances[current] = distance
                remainingTargets.remove(current)
            }
            
            for (neighbor in graph[current] ?: emptyList()) {
                if (neighbor !in visited) {
                    visited.add(neighbor)
                    queue.add(neighbor to (distance + 1))
                }
            }
        }
        
        return distances
    }
    
    /**
     * BFS for shortest path with obstacles
     */
    fun shortestPathWithObstacles(
        graph: Map<Int, List<Int>>, 
        start: Int, 
        end: Int, 
        obstacles: Set<Int>
    ): List<Int>? {
        val visited = mutableSetOf<Int>()
        val queue = ArrayDeque<Pair<Int, List<Int>>>() // (node, path)
        
        queue.add(start to listOf(start))
        visited.add(start)
        
        while (queue.isNotEmpty()) {
            val (current, path) = queue.removeFirst()
            
            if (current == end) return path
            
            for (neighbor in graph[current] ?: emptyList()) {
                if (neighbor !in visited && neighbor !in obstacles) {
                    visited.add(neighbor)
                    queue.add(neighbor to (path + neighbor))
                }
            }
        }
        
        return null // No path found
    }
    
    /**
     * BFS for shortest path with weighted edges (treating as unweighted)
     */
    fun shortestPathUnweighted(
        graph: Map<Int, List<Pair<Int, Int>>>, // (neighbor, weight)
        start: Int, 
        end: Int
    ): Int {
        val visited = mutableSetOf<Int>()
        val queue = ArrayDeque<Pair<Int, Int>>() // (node, distance)
        
        queue.add(start to 0)
        visited.add(start)
        
        while (queue.isNotEmpty()) {
            val (current, distance) = queue.removeFirst()
            
            if (current == end) return distance
            
            for ((neighbor, _) in graph[current] ?: emptyList()) {
                if (neighbor !in visited) {
                    visited.add(neighbor)
                    queue.add(neighbor to (distance + 1))
                }
            }
        }
        
        return -1 // No path found
    }
    
    /**
     * BFS for shortest path with custom distance function
     */
    fun shortestPathWithCustomDistance(
        graph: Map<Int, List<Int>>, 
        start: Int, 
        end: Int, 
        distanceFunction: (Int, Int) -> Int
    ): Int {
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
                    val newDistance = distance + distanceFunction(current, neighbor)
                    queue.add(neighbor to newDistance)
                }
            }
        }
        
        return -1 // No path found
    }
} 