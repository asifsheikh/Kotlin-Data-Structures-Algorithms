package data_structure.graphs

/**
 * BIPARTITE CHECK AND MAXIMUM BIPARTITE MATCHING (Hopcroft–Karp)
 *
 * - Bipartite test via BFS coloring
 * - Maximum matching in bipartite graph via Hopcroft–Karp
 *
 * Time Complexity (Hopcroft–Karp): O(E * sqrt(V))
 */
object BipartiteAndMatching {

    // ===== BIPARTITE CHECK (BFS coloring) =====
    /**
     * Time Complexity: O(V + E)
     * Space Complexity: O(V)
     */
    fun isBipartite(graph: Map<Int, List<Int>>): Boolean {
        val color = mutableMapOf<Int, Int>() // 0 or 1
        for (start in graph.keys) {
            if (start !in color) {
                val queue = ArrayDeque<Int>()
                queue.add(start)
                color[start] = 0
                while (queue.isNotEmpty()) {
                    val u = queue.removeFirst()
                    for (v in graph[u] ?: emptyList()) {
                        if (v !in color) {
                            color[v] = 1 - color[u]!!
                            queue.add(v)
                        } else if (color[v] == color[u]) {
                            return false
                        }
                    }
                }
            }
        }
        return true
    }

    // ===== HOPCROFT–KARP MAXIMUM MATCHING =====
    // Left partition U = 0..(nLeft-1); Right partition V = 0..(nRight-1)
    // edges: adjacency from U to V
    /**
     * Time Complexity: O(E * sqrt(V))
     * Space Complexity: O(V + E)
     */
    fun hopcroftKarp(nLeft: Int, nRight: Int, edges: Map<Int, List<Int>>): Int {
        val dist = IntArray(nLeft) { -1 }
        val matchU = IntArray(nLeft) { -1 }
        val matchV = IntArray(nRight) { -1 }

        fun bfs(): Boolean {
            val queue = ArrayDeque<Int>()
            for (u in 0 until nLeft) {
                if (matchU[u] == -1) {
                    dist[u] = 0
                    queue.add(u)
                } else dist[u] = -1
            }
            var foundAugPath = false
            while (queue.isNotEmpty()) {
                val u = queue.removeFirst()
                for (v in edges[u] ?: emptyList()) {
                    val mu = matchV[v]
                    if (mu == -1) {
                        foundAugPath = true
                    } else if (dist[mu] == -1) {
                        dist[mu] = dist[u] + 1
                        queue.add(mu)
                    }
                }
            }
            return foundAugPath
        }

        fun dfs(u: Int): Boolean {
            for (v in edges[u] ?: emptyList()) {
                val mu = matchV[v]
                if (mu == -1 || (dist[mu] == dist[u] + 1 && dfs(mu))) {
                    matchU[u] = v
                    matchV[v] = u
                    return true
                }
            }
            dist[u] = -1
            return false
        }

        var matching = 0
        while (bfs()) {
            for (u in 0 until nLeft) {
                if (matchU[u] == -1 && dfs(u)) matching++
            }
        }
        return matching
    }
}


