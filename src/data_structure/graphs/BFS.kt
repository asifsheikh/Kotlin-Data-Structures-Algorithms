package data_structure.graphs

/**
 * BREADTH-FIRST SEARCH (BFS)
 * 
 * This file contains BFS implementations for graphs:
 * - Standard BFS traversal
 * - BFS for shortest path in unweighted graphs
 * - BFS for level-by-level traversal
 * - BFS for finding connected components
 */

object BFS {
    
    // ===== STANDARD BFS TRAVERSAL =====
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
    
    // ===== BFS WITH LEVEL INFORMATION =====
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
    
    // ===== BFS FOR SHORTEST PATH (UNWEIGHTED GRAPH) =====
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
    
    // ===== BFS FOR SHORTEST PATH WITH PATH TRACKING =====
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
    
    // ===== BFS FOR CONNECTED COMPONENTS =====
    fun findConnectedComponentsBFS(graph: Map<Int, List<Int>>): List<List<Int>> {
        val visited = mutableSetOf<Int>()
        val components = mutableListOf<List<Int>>()
        
        for (node in graph.keys) {
            if (node !in visited) {
                val component = bfs(graph, node)
                components.add(component)
                visited.addAll(component)
            }
        }
        
        return components
    }
    
    // ===== BFS FOR BIPARTITE GRAPH CHECKING =====
    fun isBipartiteBFS(graph: Map<Int, List<Int>>): Boolean {
        val colors = mutableMapOf<Int, Int>()
        val queue = ArrayDeque<Int>()
        
        for (start in graph.keys) {
            if (start !in colors) {
                queue.add(start)
                colors[start] = 0
                
                while (queue.isNotEmpty()) {
                    val current = queue.removeFirst()
                    
                    for (neighbor in graph[current] ?: emptyList()) {
                        if (neighbor !in colors) {
                            colors[neighbor] = 1 - colors[current]!!
                            queue.add(neighbor)
                        } else if (colors[neighbor] == colors[current]) {
                            return false // Not bipartite
                        }
                    }
                }
            }
        }
        
        return true
    }
    
    // ===== BFS FOR TOPOLOGICAL SORT (KAHN'S ALGORITHM) =====
    fun topologicalSortBFS(graph: Map<Int, List<Int>>): List<Int>? {
        val inDegree = mutableMapOf<Int, Int>()
        val queue = ArrayDeque<Int>()
        val result = mutableListOf<Int>()
        
        // Calculate in-degrees
        for (node in graph.keys) {
            inDegree[node] = 0
        }
        
        for (neighbors in graph.values) {
            for (neighbor in neighbors) {
                inDegree[neighbor] = inDegree.getOrDefault(neighbor, 0) + 1
            }
        }
        
        // Add nodes with in-degree 0 to queue
        for ((node, degree) in inDegree) {
            if (degree == 0) {
                queue.add(node)
            }
        }
        
        // Process queue
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            result.add(current)
            
            for (neighbor in graph[current] ?: emptyList()) {
                inDegree[neighbor] = inDegree[neighbor]!! - 1
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor)
                }
            }
        }
        
        // Check if all nodes were processed
        return if (result.size == graph.size) result else null
    }
    
    // ===== BFS FOR WORD LADDER =====
    fun wordLadderBFS(beginWord: String, endWord: String, wordList: List<String>): Int {
        val wordSet = wordList.toSet()
        if (endWord !in wordSet) return 0
        
        val queue = ArrayDeque<Pair<String, Int>>()
        val visited = mutableSetOf<String>()
        
        queue.add(beginWord to 1)
        visited.add(beginWord)
        
        while (queue.isNotEmpty()) {
            val (current, level) = queue.removeFirst()
            
            if (current == endWord) return level
            
            // Generate all possible transformations
            for (i in current.indices) {
                for (c in 'a'..'z') {
                    val newWord = current.substring(0, i) + c + current.substring(i + 1)
                    
                    if (newWord in wordSet && newWord !in visited) {
                        visited.add(newWord)
                        queue.add(newWord to (level + 1))
                    }
                }
            }
        }
        
        return 0
    }
    
    // ===== BFS FOR 2D GRID (NUMBER OF ISLANDS) =====
    fun numIslandsBFS(grid: Array<CharArray>): Int {
        if (grid.isEmpty()) return 0
        
        val rows = grid.size
        val cols = grid[0].size
        var count = 0
        
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (grid[i][j] == '1') {
                    count++
                    bfsGrid(grid, i, j, rows, cols)
                }
            }
        }
        
        return count
    }
    
    private fun bfsGrid(grid: Array<CharArray>, row: Int, col: Int, rows: Int, cols: Int) {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(row to col)
        grid[row][col] = '0'
        
        val directions = arrayOf(
            intArrayOf(-1, 0), // up
            intArrayOf(1, 0),  // down
            intArrayOf(0, -1), // left
            intArrayOf(0, 1)   // right
        )
        
        while (queue.isNotEmpty()) {
            val (r, c) = queue.removeFirst()
            
            for ((dr, dc) in directions) {
                val nr = r + dr
                val nc = c + dc
                
                if (nr in 0 until rows && nc in 0 until cols && grid[nr][nc] == '1') {
                    grid[nr][nc] = '0'
                    queue.add(nr to nc)
                }
            }
        }
    }
} 