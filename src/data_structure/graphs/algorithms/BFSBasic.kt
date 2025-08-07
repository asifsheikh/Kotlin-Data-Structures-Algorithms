package data_structure.graphs.algorithms

/**
 * BFS BASIC ALGORITHMS
 * 
 * Problem: Implement basic Breadth-First Search algorithms for graph traversal.
 * 
 * Examples:
 * Input: Graph with edges [(0,1), (0,2), (1,3), (2,3)], start = 0
 * Output: [0, 1, 2, 3] (BFS traversal order)
 * 
 * Intuition: Use queue to explore all neighbors at current level before moving to next level
 * 
 * Time Complexity: O(V + E) - V = vertices, E = edges
 * Space Complexity: O(V) - for queue and visited set
 */

object BFSBasic {
    
    /**
     * Standard BFS traversal
     */
    fun bfs(graph: Map<Int, List<Int>>, start: Int): List<Int> {
        val visited = mutableSetOf<Int>()
        val queue = ArrayDeque<Int>()
        val result = mutableListOf<Int>()
        
        queue.add(start)
        visited.add(start)
        
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            result.add(current)
            
            for (neighbor in graph[current] ?: emptyList()) {
                if (neighbor !in visited) {
                    visited.add(neighbor)
                    queue.add(neighbor)
                }
            }
        }
        
        return result
    }
    
    /**
     * BFS with level information
     */
    fun bfsWithLevels(graph: Map<Int, List<Int>>, start: Int): List<List<Int>> {
        val visited = mutableSetOf<Int>()
        val queue = ArrayDeque<Int>()
        val result = mutableListOf<List<Int>>()
        
        queue.add(start)
        visited.add(start)
        
        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            val level = mutableListOf<Int>()
            
            repeat(levelSize) {
                val current = queue.removeFirst()
                level.add(current)
                
                for (neighbor in graph[current] ?: emptyList()) {
                    if (neighbor !in visited) {
                        visited.add(neighbor)
                        queue.add(neighbor)
                    }
                }
            }
            
            result.add(level)
        }
        
        return result
    }
    
    /**
     * BFS for all nodes (handles disconnected graphs)
     */
    fun bfsAllNodes(graph: Map<Int, List<Int>>): List<Int> {
        val visited = mutableSetOf<Int>()
        val result = mutableListOf<Int>()
        
        for (node in graph.keys) {
            if (node !in visited) {
                val component = bfs(graph, node)
                result.addAll(component)
                visited.addAll(component)
            }
        }
        
        return result
    }
    
    /**
     * BFS with custom visitor function
     */
    fun bfsWithVisitor(
        graph: Map<Int, List<Int>>, 
        start: Int, 
        visitor: (Int, Int) -> Unit // (node, level)
    ) {
        val visited = mutableSetOf<Int>()
        val queue = ArrayDeque<Pair<Int, Int>>() // (node, level)
        
        queue.add(start to 0)
        visited.add(start)
        
        while (queue.isNotEmpty()) {
            val (current, level) = queue.removeFirst()
            visitor(current, level)
            
            for (neighbor in graph[current] ?: emptyList()) {
                if (neighbor !in visited) {
                    visited.add(neighbor)
                    queue.add(neighbor to (level + 1))
                }
            }
        }
    }
    
    /**
     * BFS with early termination
     */
    fun bfsWithTermination(
        graph: Map<Int, List<Int>>, 
        start: Int, 
        shouldTerminate: (Int) -> Boolean
    ): List<Int> {
        val visited = mutableSetOf<Int>()
        val queue = ArrayDeque<Int>()
        val result = mutableListOf<Int>()
        
        queue.add(start)
        visited.add(start)
        
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            result.add(current)
            
            if (shouldTerminate(current)) {
                break
            }
            
            for (neighbor in graph[current] ?: emptyList()) {
                if (neighbor !in visited) {
                    visited.add(neighbor)
                    queue.add(neighbor)
                }
            }
        }
        
        return result
    }
    
    /**
     * BFS with maximum depth limit
     */
    fun bfsWithMaxDepth(
        graph: Map<Int, List<Int>>, 
        start: Int, 
        maxDepth: Int
    ): List<Int> {
        val visited = mutableSetOf<Int>()
        val queue = ArrayDeque<Pair<Int, Int>>() // (node, depth)
        val result = mutableListOf<Int>()
        
        queue.add(start to 0)
        visited.add(start)
        
        while (queue.isNotEmpty()) {
            val (current, depth) = queue.removeFirst()
            result.add(current)
            
            if (depth < maxDepth) {
                for (neighbor in graph[current] ?: emptyList()) {
                    if (neighbor !in visited) {
                        visited.add(neighbor)
                        queue.add(neighbor to (depth + 1))
                    }
                }
            }
        }
        
        return result
    }
} 