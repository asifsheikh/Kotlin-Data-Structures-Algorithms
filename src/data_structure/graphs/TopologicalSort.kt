package data_structure.graphs

/**
 * TOPOLOGICAL SORTING
 * 
 * This file contains topological sorting implementations:
 * - Kahn's Algorithm (BFS-based)
 * - DFS-based topological sort
 * - Cycle detection during topological sort
 * - Applications and variations
 */

object TopologicalSort {
    
    // ===== KAHN'S ALGORITHM (BFS-BASED) =====
    /**
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    fun kahnAlgorithm(graph: Map<Int, List<Int>>): List<Int>? {
        val inDegree = mutableMapOf<Int, Int>()
        val queue = ArrayDeque<Int>()
        val result = mutableListOf<Int>()
        
        // Initialize in-degrees
        for (node in graph.keys) {
            inDegree[node] = 0
        }
        
        // Calculate in-degrees
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
            
            // Reduce in-degree of neighbors
            for (neighbor in graph[current] ?: emptyList()) {
                inDegree[neighbor] = inDegree[neighbor]!! - 1
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor)
                }
            }
        }
        
        // Check if all nodes were processed (no cycle)
        return if (result.size == graph.size) result else null
    }
    
    // ===== DFS-BASED TOPOLOGICAL SORT =====
    /**
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    fun dfsTopologicalSort(graph: Map<Int, List<Int>>): List<Int>? {
        val visited = mutableSetOf<Int>()
        val stack = mutableListOf<Int>()
        val onPath = mutableSetOf<Int>()
        var cycleFound = false
        
        fun dfs(node: Int) {
            if (cycleFound) return
            
            visited.add(node)
            onPath.add(node)
            
            for (neighbor in graph[node] ?: emptyList()) {
                if (neighbor !in visited) {
                    dfs(neighbor)
                } else if (neighbor in onPath) {
                    cycleFound = true
                    return
                }
            }
            
            onPath.remove(node)
            stack.add(node)
        }
        
        for (node in graph.keys) {
            if (node !in visited) {
                dfs(node)
                if (cycleFound) return null
            }
        }
        
        return stack.reversed()
    }
    
    // ===== TOPOLOGICAL SORT WITH CYCLE DETECTION =====
    /**
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    fun topologicalSortWithCycleDetection(graph: Map<Int, List<Int>>): Pair<List<Int>?, Boolean> {
        val visited = mutableSetOf<Int>()
        val stack = mutableListOf<Int>()
        val onPath = mutableSetOf<Int>()
        var cycleFound = false
        
        fun dfs(node: Int) {
            if (cycleFound) return
            
            visited.add(node)
            onPath.add(node)
            
            for (neighbor in graph[node] ?: emptyList()) {
                if (neighbor !in visited) {
                    dfs(neighbor)
                } else if (neighbor in onPath) {
                    cycleFound = true
                    return
                }
            }
            
            onPath.remove(node)
            stack.add(node)
        }
        
        for (node in graph.keys) {
            if (node !in visited) {
                dfs(node)
                if (cycleFound) break
            }
        }
        
        return if (cycleFound) {
            Pair(null, true) // Has cycle
        } else {
            Pair(stack.reversed(), false) // No cycle
        }
    }
    
    // ===== ALL POSSIBLE TOPOLOGICAL SORTS =====
    /**
     * Time Complexity: O(K * (V + E)) where K is the number of valid topological orders
     *   (worst-case exponential; up to O(V!))
     * Space Complexity: O(V)
     */
    fun allTopologicalSorts(graph: Map<Int, List<Int>>): List<List<Int>> {
        val inDegree = mutableMapOf<Int, Int>()
        val result = mutableListOf<List<Int>>()
        
        // Calculate in-degrees
        for (node in graph.keys) {
            inDegree[node] = 0
        }
        
        for (neighbors in graph.values) {
            for (neighbor in neighbors) {
                inDegree[neighbor] = inDegree.getOrDefault(neighbor, 0) + 1
            }
        }
        
        fun backtrack(visited: MutableSet<Int>, path: MutableList<Int>, tempInDegree: MutableMap<Int, Int>) {
            if (path.size == graph.size) {
                result.add(path.toList())
                return
            }
            
            for (node in graph.keys) {
                if (node !in visited && tempInDegree[node] == 0) {
                    visited.add(node)
                    path.add(node)
                    
                    // Update in-degrees
                    for (neighbor in graph[node] ?: emptyList()) {
                        tempInDegree[neighbor] = tempInDegree[neighbor]!! - 1
                    }
                    
                    backtrack(visited, path, tempInDegree)
                    
                    // Backtrack
                    visited.remove(node)
                    path.removeAt(path.lastIndex)
                    
                    // Restore in-degrees
                    for (neighbor in graph[node] ?: emptyList()) {
                        tempInDegree[neighbor] = tempInDegree[neighbor]!! + 1
                    }
                }
            }
        }
        
        backtrack(mutableSetOf(), mutableListOf(), inDegree.toMutableMap())
        return result
    }
    
    // ===== TOPOLOGICAL SORT FOR WEIGHTED GRAPH =====
    /**
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    fun topologicalSortWeighted(graph: Map<Int, List<Pair<Int, Int>>>): List<Int>? {
        val inDegree = mutableMapOf<Int, Int>()
        val queue = ArrayDeque<Int>()
        val result = mutableListOf<Int>()
        
        // Initialize in-degrees
        for (node in graph.keys) {
            inDegree[node] = 0
        }
        
        // Calculate in-degrees
        for (neighbors in graph.values) {
            for ((neighbor, _) in neighbors) {
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
            
            for ((neighbor, _) in graph[current] ?: emptyList()) {
                inDegree[neighbor] = inDegree[neighbor]!! - 1
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor)
                }
            }
        }
        
        return if (result.size == graph.size) result else null
    }
    
    // ===== LONGEST PATH IN DAG (USING TOPOLOGICAL SORT) =====
    /**
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    fun longestPathInDAG(graph: Map<Int, List<Pair<Int, Int>>>): Int {
        val topoOrder = topologicalSortWeighted(graph) ?: return -1
        val distances = mutableMapOf<Int, Int>()
        
        // Initialize distances
        for (node in graph.keys) {
            distances[node] = Int.MIN_VALUE
        }
        distances[topoOrder[0]] = 0
        
        // Process nodes in topological order
        for (node in topoOrder) {
            if (distances[node] != Int.MIN_VALUE) {
                for ((neighbor, weight) in graph[node] ?: emptyList()) {
                    if (distances[neighbor]!! < distances[node]!! + weight) {
                        distances[neighbor] = distances[node]!! + weight
                    }
                }
            }
        }
        
        return distances.values.maxOrNull() ?: 0
    }
    
    // ===== SHORTEST PATH IN DAG (USING TOPOLOGICAL SORT) =====
    /**
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    fun shortestPathInDAG(graph: Map<Int, List<Pair<Int, Int>>>, start: Int): Map<Int, Int> {
        val topoOrder = topologicalSortWeighted(graph) ?: return emptyMap()
        val distances = mutableMapOf<Int, Int>()
        
        // Initialize distances
        for (node in graph.keys) {
            distances[node] = Int.MAX_VALUE
        }
        distances[start] = 0
        
        // Process nodes in topological order
        for (node in topoOrder) {
            if (distances[node] != Int.MAX_VALUE) {
                for ((neighbor, weight) in graph[node] ?: emptyList()) {
                    if (distances[neighbor]!! > distances[node]!! + weight) {
                        distances[neighbor] = distances[node]!! + weight
                    }
                }
            }
        }
        
        return distances
    }
    
    // ===== COURSE SCHEDULE PROBLEM =====
    /**
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     */
    fun canFinishCourses(numCourses: Int, prerequisites: List<List<Int>>): Boolean {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        
        // Build graph
        for (i in 0 until numCourses) {
            graph[i] = mutableListOf()
        }
        
        for ((course, prereq) in prerequisites) {
            graph[prereq]?.add(course)
        }
        
        return kahnAlgorithm(graph) != null
    }
    
    /**
     * Time Complexity: O(V + E)
     * Space Complexity: O(V + E)
     */
    fun findOrder(numCourses: Int, prerequisites: List<List<Int>>): IntArray {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        
        // Build graph
        for (i in 0 until numCourses) {
            graph[i] = mutableListOf()
        }
        
        for ((course, prereq) in prerequisites) {
            graph[prereq]?.add(course)
        }
        
        val result = kahnAlgorithm(graph)
        return result?.toIntArray() ?: intArrayOf()
    }
    
    // ===== ALIEN DICTIONARY PROBLEM =====
    /**
     * Let T be total characters across all words, U the number of unique characters, and E the number of precedence edges.
     * Time Complexity: O(T + U + E)
     * Space Complexity: O(U + E)
     */
    fun alienOrder(words: List<String>): String {
        val graph = mutableMapOf<Char, MutableList<Char>>()
        val inDegree = mutableMapOf<Char, Int>()
        
        // Initialize graph and in-degree
        for (word in words) {
            for (char in word) {
                if (char !in graph) {
                    graph[char] = mutableListOf()
                    inDegree[char] = 0
                }
            }
        }
        
        // Build graph from word comparisons
        for (i in 0 until words.size - 1) {
            val word1 = words[i]
            val word2 = words[i + 1]
            
            val minLen = minOf(word1.length, word2.length)
            for (j in 0 until minLen) {
                if (word1[j] != word2[j]) {
                    graph[word1[j]]?.add(word2[j])
                    inDegree[word2[j]] = inDegree[word2[j]]!! + 1
                    break
                }
            }
        }
        
        // Kahn's algorithm
        val queue = ArrayDeque<Char>()
        val result = mutableListOf<Char>()
        
        for ((char, degree) in inDegree) {
            if (degree == 0) {
                queue.add(char)
            }
        }
        
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
        
        return if (result.size == graph.size) result.joinToString("") else ""
    }
} 