package data_structure.graphs

/**
 * DEPTH-FIRST SEARCH (DFS)
 * 
 * This file contains DFS implementations for graphs:
 * - Standard DFS traversal (recursive and iterative)
 * - DFS for cycle detection
 * - DFS for topological sorting
 * - DFS for connected components
 * - DFS for path finding
 */

object DFS {
    
    // ===== STANDARD DFS TRAVERSAL (RECURSIVE) =====
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
    
    // ===== STANDARD DFS TRAVERSAL (ITERATIVE) =====
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
    
    // ===== DFS FOR CYCLE DETECTION IN DIRECTED GRAPH =====
    fun hasCycleDirected(graph: Map<Int, List<Int>>): Boolean {
        val visited = mutableSetOf<Int>()
        val recursionStack = mutableSetOf<Int>()
        
        fun dfsCycle(node: Int): Boolean {
            if (node !in visited) {
                visited.add(node)
                recursionStack.add(node)
                
                for (neighbor in graph[node] ?: emptyList()) {
                    if (neighbor !in visited && dfsCycle(neighbor)) {
                        return true
                    } else if (neighbor in recursionStack) {
                        return true // Back edge found
                    }
                }
            }
            recursionStack.remove(node)
            return false
        }
        
        for (node in graph.keys) {
            if (dfsCycle(node)) return true
        }
        return false
    }
    
    // ===== DFS FOR CYCLE DETECTION IN UNDIRECTED GRAPH =====
    fun hasCycleUndirected(graph: Map<Int, List<Int>>): Boolean {
        val visited = mutableSetOf<Int>()
        
        fun dfsUndirected(node: Int, parent: Int): Boolean {
            visited.add(node)
            
            for (neighbor in graph[node] ?: emptyList()) {
                if (neighbor !in visited) {
                    if (dfsUndirected(neighbor, node)) return true
                } else if (neighbor != parent) {
                    return true // Back edge to non-parent
                }
            }
            return false
        }
        
        for (node in graph.keys) {
            if (node !in visited) {
                if (dfsUndirected(node, -1)) return true
            }
        }
        return false
    }
    
    // ===== DFS FOR TOPOLOGICAL SORTING =====
    fun topologicalSortDFS(graph: Map<Int, List<Int>>): List<Int>? {
        val visited = mutableSetOf<Int>()
        val stack = mutableListOf<Int>()
        val onPath = mutableSetOf<Int>()
        var cycleFound = false
        
        fun dfsTopo(node: Int) {
            if (cycleFound) return
            visited.add(node)
            onPath.add(node)
            
            for (neighbor in graph[node] ?: emptyList()) {
                if (neighbor !in visited) {
                    dfsTopo(neighbor)
                } else if (neighbor in onPath) {
                    cycleFound = true
                    return
                }
            }
            onPath.remove(node)
            stack.add(node)
        }
        
        for (node in graph.keys) {
            if (node !in visited) dfsTopo(node)
            if (cycleFound) return null
        }
        
        return stack.reversed()
    }
    
    // ===== DFS FOR CONNECTED COMPONENTS =====
    fun findConnectedComponentsDFS(graph: Map<Int, List<Int>>): List<List<Int>> {
        val visited = mutableSetOf<Int>()
        val components = mutableListOf<List<Int>>()
        
        fun dfsComponent(node: Int, component: MutableList<Int>) {
            visited.add(node)
            component.add(node)
            
            for (neighbor in graph[node] ?: emptyList()) {
                if (neighbor !in visited) {
                    dfsComponent(neighbor, component)
                }
            }
        }
        
        for (node in graph.keys) {
            if (node !in visited) {
                val component = mutableListOf<Int>()
                dfsComponent(node, component)
                components.add(component)
            }
        }
        
        return components
    }
    
    // ===== DFS FOR PATH FINDING =====
    fun findPathDFS(graph: Map<Int, List<Int>>, start: Int, end: Int): List<Int>? {
        val visited = mutableSetOf<Int>()
        val path = mutableListOf<Int>()
        
        fun dfsPath(node: Int): Boolean {
            visited.add(node)
            path.add(node)
            
            if (node == end) return true
            
            for (neighbor in graph[node] ?: emptyList()) {
                if (neighbor !in visited) {
                    if (dfsPath(neighbor)) return true
                }
            }
            
            path.removeAt(path.lastIndex)
            return false
        }
        
        return if (dfsPath(start)) path else null
    }
    
    // ===== DFS FOR BIPARTITE GRAPH CHECKING =====
    fun isBipartiteDFS(graph: Map<Int, List<Int>>): Boolean {
        val colors = mutableMapOf<Int, Int>()
        
        fun dfsColor(node: Int, color: Int): Boolean {
            if (node in colors) {
                return colors[node] == color
            }
            
            colors[node] = color
            
            for (neighbor in graph[node] ?: emptyList()) {
                if (!dfsColor(neighbor, 1 - color)) {
                    return false
                }
            }
            return true
        }
        
        for (node in graph.keys) {
            if (node !in colors) {
                if (!dfsColor(node, 0)) return false
            }
        }
        
        return true
    }
    
    // ===== DFS FOR STRONGLY CONNECTED COMPONENTS (TARJAN'S ALGORITHM) =====
    fun findStronglyConnectedComponents(graph: Map<Int, List<Int>>): List<List<Int>> {
        val visited = mutableSetOf<Int>()
        val stack = mutableListOf<Int>()
        val low = mutableMapOf<Int, Int>()
        val disc = mutableMapOf<Int, Int>()
        val components = mutableListOf<List<Int>>()
        var time = 0
        
        fun tarjan(node: Int) {
            visited.add(node)
            stack.add(node)
            disc[node] = time
            low[node] = time
            time++
            
            for (neighbor in graph[node] ?: emptyList()) {
                if (neighbor !in visited) {
                    tarjan(neighbor)
                    low[node] = minOf(low[node]!!, low[neighbor]!!)
                } else if (neighbor in stack) {
                    low[node] = minOf(low[node]!!, disc[neighbor]!!)
                }
            }
            
            if (low[node] == disc[node]) {
                val component = mutableListOf<Int>()
                while (stack.isNotEmpty()) {
                    val top = stack.removeAt(stack.lastIndex)
                    component.add(top)
                    if (top == node) break
                }
                components.add(component)
            }
        }
        
        for (node in graph.keys) {
            if (node !in visited) {
                tarjan(node)
            }
        }
        
        return components
    }
    
    // ===== DFS FOR ARTICULATION POINTS =====
    fun findArticulationPoints(graph: Map<Int, List<Int>>): Set<Int> {
        val visited = mutableSetOf<Int>()
        val disc = mutableMapOf<Int, Int>()
        val low = mutableMapOf<Int, Int>()
        val parent = mutableMapOf<Int, Int>()
        val articulationPoints = mutableSetOf<Int>()
        var time = 0
        
        fun dfsArticulation(node: Int) {
            visited.add(node)
            disc[node] = time
            low[node] = time
            time++
            
            var children = 0
            
            for (neighbor in graph[node] ?: emptyList()) {
                if (neighbor !in visited) {
                    children++
                    parent[neighbor] = node
                    dfsArticulation(neighbor)
                    
                    low[node] = minOf(low[node]!!, low[neighbor]!!)
                    
                    // Check if node is articulation point
                    if (parent[node] == null && children > 1) {
                        articulationPoints.add(node)
                    }
                    if (parent[node] != null && low[neighbor]!! >= disc[node]!!) {
                        articulationPoints.add(node)
                    }
                } else if (neighbor != parent[node]) {
                    low[node] = minOf(low[node]!!, disc[neighbor]!!)
                }
            }
        }
        
        for (node in graph.keys) {
            if (node !in visited) {
                dfsArticulation(node)
            }
        }
        
        return articulationPoints
    }
    
    // ===== DFS FOR BRIDGES =====
    fun findBridges(graph: Map<Int, List<Int>>): List<Pair<Int, Int>> {
        val visited = mutableSetOf<Int>()
        val disc = mutableMapOf<Int, Int>()
        val low = mutableMapOf<Int, Int>()
        val parent = mutableMapOf<Int, Int>()
        val bridges = mutableListOf<Pair<Int, Int>>()
        var time = 0
        
        fun dfsBridge(node: Int) {
            visited.add(node)
            disc[node] = time
            low[node] = time
            time++
            
            for (neighbor in graph[node] ?: emptyList()) {
                if (neighbor !in visited) {
                    parent[neighbor] = node
                    dfsBridge(neighbor)
                    
                    low[node] = minOf(low[node]!!, low[neighbor]!!)
                    
                    // Check if edge is bridge
                    if (low[neighbor]!! > disc[node]!!) {
                        bridges.add(node to neighbor)
                    }
                } else if (neighbor != parent[node]) {
                    low[node] = minOf(low[node]!!, disc[neighbor]!!)
                }
            }
        }
        
        for (node in graph.keys) {
            if (node !in visited) {
                dfsBridge(node)
            }
        }
        
        return bridges
    }
    
    // ===== DFS FOR BACKTRACKING (ALL PATHS) =====
    fun findAllPaths(graph: Map<Int, List<Int>>, start: Int, end: Int): List<List<Int>> {
        val visited = mutableSetOf<Int>()
        val paths = mutableListOf<List<Int>>()
        
        fun dfsAllPaths(node: Int, path: MutableList<Int>) {
            visited.add(node)
            path.add(node)
            
            if (node == end) {
                paths.add(path.toList())
            } else {
                for (neighbor in graph[node] ?: emptyList()) {
                    if (neighbor !in visited) {
                        dfsAllPaths(neighbor, path)
                    }
                }
            }
            
            visited.remove(node)
            path.removeAt(path.lastIndex)
        }
        
        dfsAllPaths(start, mutableListOf())
        return paths
    }
} 