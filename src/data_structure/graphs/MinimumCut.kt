package data_structure.graphs

/**
 * MINIMUM CUT via Max-Flow (s-t cut)
 *
 * Provides a method to retrieve the min cut after running max-flow.
 */
object MinimumCut {

    data class CutResult(val maxFlow: Int, val minCutSetS: Set<Int>, val minCutSetT: Set<Int>)

    // capacity is adjacency matrix; returns min-cut partition after max flow
    /**
     * Time Complexity: O(V * E^2) using Edmonds–Karp for max-flow; plus O(V + E) to extract the cut
     * Space Complexity: O(V^2) for residual capacities
     */
    fun minCut(capacity: Array<IntArray>, source: Int, sink: Int): CutResult {
        val n = capacity.size
        val residual = Array(n) { IntArray(n) }
        for (i in 0 until n) for (j in 0 until n) residual[i][j] = capacity[i][j]

        var maxFlow = 0
        val parent = IntArray(n) { -1 }

        fun bfs(): Int {
            java.util.Arrays.fill(parent, -1)
            parent[source] = -2
            val queue = ArrayDeque<Pair<Int, Int>>()
            queue.add(source to Int.MAX_VALUE)
            while (queue.isNotEmpty()) {
                val (u, flow) = queue.removeFirst()
                for (v in 0 until n) {
                    if (parent[v] == -1 && residual[u][v] > 0) {
                        parent[v] = u
                        val newFlow = minOf(flow, residual[u][v])
                        if (v == sink) return newFlow
                        queue.add(v to newFlow)
                    }
                }
            }
            return 0
        }

        while (true) {
            val flow = bfs()
            if (flow == 0) break
            maxFlow += flow
            var cur = sink
            while (cur != source) {
                val prev = parent[cur]
                residual[prev][cur] -= flow
                residual[cur][prev] += flow
                cur = prev
            }
        }

        // After max flow, run DFS/BFS from source in residual graph to find reachable set S
        val visited = BooleanArray(n)
        val stack = ArrayDeque<Int>()
        stack.add(source)
        while (stack.isNotEmpty()) {
            val u = stack.removeLast()
            if (visited[u]) continue
            visited[u] = true
            for (v in 0 until n) {
                if (!visited[v] && residual[u][v] > 0) stack.add(v)
            }
        }

        val setS = mutableSetOf<Int>()
        val setT = mutableSetOf<Int>()
        for (i in 0 until n) if (visited[i]) setS.add(i) else setT.add(i)

        return CutResult(maxFlow, setS, setT)
    }
}


