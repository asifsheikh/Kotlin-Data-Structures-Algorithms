package data_structure.graphs.algorithms

/**
 * DFS BASIC ALGORITHMS
 * 
 * Problem: Implement basic Depth-First Search algorithms for graph traversal.
 * 
 * Examples:
 * Input: Graph with edges [(0,1), (0,2), (1,3), (2,3)], start = 0
 * Output: [0, 1, 3, 2] (DFS traversal order)
 * 
 * Intuition: Use stack (recursive or iterative) to explore as deep as possible before backtracking
 * 
 * Time Complexity: O(V + E) - V = vertices, E = edges
 * Space Complexity: O(V) - for stack and visited set
 */

object DFSBasic {
    
    /**
     * Standard DFS traversal (recursive)
     */
    fun dfsRecursive(graph: Map<Int, List<Int>>, start: Int): List<Int> {
        val visited = mutableSetOf<Int>()
        val result = mutableListOf<Int>()
        
        fun dfsUtil(node: Int) {
            visited.add(node)
            result.add(node)
            
            for (neighbor in graph[node] ?: emptyList()) {
                if (neighbor !in visited) {
                    dfsUtil(neighbor)
                }
            }
        }
        
        dfsUtil(start)
        return result
    }
    
    /**
     * Standard DFS traversal (iterative)
     */
    fun dfsIterative(graph: Map<Int, List<Int>>, start: Int): List<Int> {
        val visited = mutableSetOf<Int>()
        val stack = ArrayDeque<Int>()
        val result = mutableListOf<Int>()
        
        stack.addLast(start)
        
        while (stack.isNotEmpty()) {
            val current = stack.removeLast()
            
            if (current !in visited) {
                visited.add(current)
                result.add(current)
                
                // Add neighbors in reverse order to maintain DFS order
                for (neighbor in (graph[current] ?: emptyList()).reversed()) {
                    if (neighbor !in visited) {
                        stack.addLast(neighbor)
                    }
                }
            }
        }
        
        return result
    }
    
    /**
     * DFS for all nodes (handles disconnected graphs)
     */
    fun dfsAllNodes(graph: Map<Int, List<Int>>): List<Int> {
        val visited = mutableSetOf<Int>()
        val result = mutableListOf<Int>()
        
        fun dfsUtil(node: Int) {
            visited.add(node)
            result.add(node)
            
            for (neighbor in graph[node] ?: emptyList()) {
                if (neighbor !in visited) {
                    dfsUtil(neighbor)
                }
            }
        }
        
        for (node in graph.keys) {
            if (node !in visited) {
                dfsUtil(node)
            }
        }
        
        return result
    }
    
    /**
     * DFS with custom visitor function
     */
    fun dfsWithVisitor(
        graph: Map<Int, List<Int>>, 
        start: Int, 
        visitor: (Int, Int) -> Unit // (node, depth)
    ) {
        val visited = mutableSetOf<Int>()
        
        fun dfsUtil(node: Int, depth: Int) {
            visited.add(node)
            visitor(node, depth)
            
            for (neighbor in graph[node] ?: emptyList()) {
                if (neighbor !in visited) {
                    dfsUtil(neighbor, depth + 1)
                }
            }
        }
        
        dfsUtil(start, 0)
    }
    
    /**
     * DFS with early termination
     */
    fun dfsWithTermination(
        graph: Map<Int, List<Int>>, 
        start: Int, 
        shouldTerminate: (Int) -> Boolean
    ): List<Int> {
        val visited = mutableSetOf<Int>()
        val result = mutableListOf<Int>()
        var terminated = false
        
        fun dfsUtil(node: Int) {
            if (terminated) return
            
            visited.add(node)
            result.add(node)
            
            if (shouldTerminate(node)) {
                terminated = true
                return
            }
            
            for (neighbor in graph[node] ?: emptyList()) {
                if (neighbor !in visited && !terminated) {
                    dfsUtil(neighbor)
                }
            }
        }
        
        dfsUtil(start)
        return result
    }
    
    /**
     * DFS with maximum depth limit
     */
    fun dfsWithMaxDepth(
        graph: Map<Int, List<Int>>, 
        start: Int, 
        maxDepth: Int
    ): List<Int> {
        val visited = mutableSetOf<Int>()
        val result = mutableListOf<Int>()
        
        fun dfsUtil(node: Int, depth: Int) {
            if (depth > maxDepth) return
            
            visited.add(node)
            result.add(node)
            
            if (depth < maxDepth) {
                for (neighbor in graph[node] ?: emptyList()) {
                    if (neighbor !in visited) {
                        dfsUtil(neighbor, depth + 1)
                    }
                }
            }
        }
        
        dfsUtil(start, 0)
        return result
    }
    
    /**
     * DFS with post-order traversal
     */
    fun dfsPostOrder(graph: Map<Int, List<Int>>, start: Int): List<Int> {
        val visited = mutableSetOf<Int>()
        val result = mutableListOf<Int>()
        
        fun dfsUtil(node: Int) {
            visited.add(node)
            
            for (neighbor in graph[node] ?: emptyList()) {
                if (neighbor !in visited) {
                    dfsUtil(neighbor)
                }
            }
            
            result.add(node) // Add after visiting all children
        }
        
        dfsUtil(start)
        return result
    }
    
    /**
     * DFS with in-order traversal (for binary trees)
     */
    fun dfsInOrder(graph: Map<Int, List<Int>>, start: Int): List<Int> {
        val visited = mutableSetOf<Int>()
        val result = mutableListOf<Int>()
        
        fun dfsUtil(node: Int) {
            visited.add(node)
            
            val neighbors = graph[node] ?: emptyList()
            if (neighbors.isNotEmpty()) {
                // Visit left child first (if exists)
                if (neighbors[0] !in visited) {
                    dfsUtil(neighbors[0])
                }
            }
            
            result.add(node) // Add after visiting left child
            
            if (neighbors.size > 1) {
                // Visit right child (if exists)
                if (neighbors[1] !in visited) {
                    dfsUtil(neighbors[1])
                }
            }
        }
        
        dfsUtil(start)
        return result
    }
    
    /**
     * DFS with pre-order traversal
     */
    fun dfsPreOrder(graph: Map<Int, List<Int>>, start: Int): List<Int> {
        return dfsRecursive(graph, start) // Pre-order is the same as standard DFS
    }
    
    /**
     * DFS with iterative post-order traversal
     */
    fun dfsIterativePostOrder(graph: Map<Int, List<Int>>, start: Int): List<Int> {
        val visited = mutableSetOf<Int>()
        val stack = ArrayDeque<Int>()
        val result = mutableListOf<Int>()
        val processed = mutableSetOf<Int>()
        
        stack.addLast(start)
        
        while (stack.isNotEmpty()) {
            val current = stack.last()
            
            if (current in processed) {
                stack.removeLast()
                result.add(current)
                continue
            }
            
            if (current !in visited) {
                visited.add(current)
                processed.add(current)
                
                // Add children in reverse order
                for (neighbor in (graph[current] ?: emptyList()).reversed()) {
                    if (neighbor !in visited) {
                        stack.addLast(neighbor)
                    }
                }
            }
        }
        
        return result
    }
    
    /**
     * DFS with iterative in-order traversal
     */
    fun dfsIterativeInOrder(graph: Map<Int, List<Int>>, start: Int): List<Int> {
        val visited = mutableSetOf<Int>()
        val stack = ArrayDeque<Int>()
        val result = mutableListOf<Int>()
        var current = start
        
        while (current != -1 || stack.isNotEmpty()) {
            // Reach the leftmost node
            while (current != -1) {
                stack.addLast(current)
                val neighbors = graph[current] ?: emptyList()
                current = if (neighbors.isNotEmpty()) neighbors[0] else -1
            }
            
            current = stack.removeLast()
            result.add(current)
            
            // Move to right child
            val neighbors = graph[current] ?: emptyList()
            current = if (neighbors.size > 1) neighbors[1] else -1
        }
        
        return result
    }
} 