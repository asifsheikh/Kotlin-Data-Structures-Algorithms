package data_structure.graphs

/**
 * SPECIAL GRAPH CREATION - Quick Reference
 * All Kotlin special graph creation methods in one place
 */

object SpecialGraphCreation {
    
    /**
     * All Special Graph Creation Methods
     * Complete reference for creating special graphs in Kotlin
     */
    fun allSpecialGraphCreationMethods() {
        // === BIPARTITE GRAPHS ===
        val bipartiteGraph = createBipartiteGraph(3, 3)             // Bipartite graph with 3+3 nodes
        val completeBipartiteGraph = createCompleteBipartiteGraph(2, 3) // Complete bipartite graph K2,3
        
        // === TREE GRAPHS ===
        val binaryTree = createBinaryTree(7)                         // Binary tree with 7 nodes
        val balancedTree = createBalancedTree(7)                     // Balanced tree with 7 nodes
        val skewedTree = createSkewedTree(5)                         // Skewed tree with 5 nodes
        val starTree = createStarTree(5)                             // Star tree with 5 nodes
        
        // === CYCLE GRAPHS ===
        val cycleGraph = createCycleGraph(5)                         // Cycle graph C5
        val evenCycle = createEvenCycle(6)                           // Even cycle C6
        val oddCycle = createOddCycle(5)                             // Odd cycle C5
        
        // === PATH GRAPHS ===
        val pathGraph = createPathGraph(5)                           // Path graph P5
        val linearPath = createLinearPath(5)                         // Linear path with 5 nodes
        
        // === COMPLETE GRAPHS ===
        val completeGraph = createCompleteGraph(4)                   // Complete graph K4
        val completeDigraph = createCompleteDigraph(4)               // Complete directed graph
        
        // === GRID GRAPHS ===
        val gridGraph = createGridGraph(3, 3)                        // 3x3 grid graph
        val rectangularGrid = createRectangularGrid(2, 4)            // 2x4 rectangular grid
        val squareGrid = createSquareGrid(4)                         // 4x4 square grid
        
        // === CUBE GRAPHS ===
        val cubeGraph = createCubeGraph(3)                           // 3D cube graph
        val hypercube = createHypercube(4)                           // 4D hypercube
        
        // === WHEEL GRAPHS ===
        val wheelGraph = createWheelGraph(5)                         // Wheel graph W5
        val fanGraph = createFanGraph(5)                             // Fan graph F5
        
        // === LADDER GRAPHS ===
        val ladderGraph = createLadderGraph(5)                       // Ladder graph L5
        val circularLadder = createCircularLadder(5)                 // Circular ladder
        
        // === TOURNAMENT GRAPHS ===
        val tournamentGraph = createTournamentGraph(5)               // Tournament graph T5
        val transitiveTournament = createTransitiveTournament(5)     // Transitive tournament
        
        // === PLANAR GRAPHS ===
        val planarGraph = createPlanarGraph(5)                       // Planar graph
        val outerplanarGraph = createOuterplanarGraph(5)             // Outerplanar graph
        
        // === REGULAR GRAPHS ===
        val regularGraph = createRegularGraph(6, 3)                  // 3-regular graph with 6 nodes
        val cubicGraph = createCubicGraph(6)                         // Cubic graph (3-regular)
        
        // === EULERIAN GRAPHS ===
        val eulerianGraph = createEulerianGraph(5)                   // Eulerian graph
        val semiEulerianGraph = createSemiEulerianGraph(5)           // Semi-Eulerian graph
        
        // === HAMILTONIAN GRAPHS ===
        val hamiltonianGraph = createHamiltonianGraph(5)             // Hamiltonian graph
        val semiHamiltonianGraph = createSemiHamiltonianGraph(5)     // Semi-Hamiltonian graph
        
        // === FOREST GRAPHS ===
        val forestGraph = createForestGraph(3, 2)                    // Forest with 3 trees, 2 nodes each
        val disjointUnion = createDisjointUnion(listOf(3, 2, 4))     // Disjoint union of graphs
        
        // === MULTIGRAPHS ===
        val multigraph = createMultigraph(5)                         // Multigraph with parallel edges
        val weightedMultigraph = createWeightedMultigraph(5)         // Weighted multigraph
        
        // === PSEUDOGRAPHS ===
        val pseudograph = createPseudograph(5)                       // Pseudograph with self-loops
        val mixedGraph = createMixedGraph(5)                         // Mixed graph (directed + undirected)
        
        // === RANDOM GRAPHS ===
        val randomGraph = createRandomGraph(10, 0.3)                 // Random graph with 30% edge probability
        val erdosRenyiGraph = createErdosRenyiGraph(10, 15)          // Erdős-Rényi graph with 15 edges
        
        // === SCALE-FREE GRAPHS ===
        val scaleFreeGraph = createScaleFreeGraph(10, 3)             // Scale-free graph with 10 nodes, 3 initial
        val barabasiAlbertGraph = createBarabasiAlbertGraph(10, 2)   // Barabási-Albert graph
        
        // === SMALL WORLD GRAPHS ===
        val smallWorldGraph = createSmallWorldGraph(10, 4, 0.1)      // Small-world graph
        val wattsStrogatzGraph = createWattsStrogatzGraph(10, 4, 0.1) // Watts-Strogatz graph
    }
    
    /**
     * Creates bipartite graph
     */
    fun createBipartiteGraph(leftSize: Int, rightSize: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until leftSize) {
            for (j in leftSize until leftSize + rightSize) {
                graph.computeIfAbsent(i) { mutableListOf() }.add(j)
                graph.computeIfAbsent(j) { mutableListOf() }.add(i)
            }
        }
        return graph
    }
    
    /**
     * Creates complete bipartite graph
     */
    fun createCompleteBipartiteGraph(leftSize: Int, rightSize: Int): MutableMap<Int, MutableList<Int>> {
        return createBipartiteGraph(leftSize, rightSize)
    }
    
    /**
     * Creates binary tree
     */
    fun createBinaryTree(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until size) {
            val leftChild = 2 * i + 1
            val rightChild = 2 * i + 2
            if (leftChild < size) {
                graph.computeIfAbsent(i) { mutableListOf() }.add(leftChild)
                graph.computeIfAbsent(leftChild) { mutableListOf() }.add(i)
            }
            if (rightChild < size) {
                graph.computeIfAbsent(i) { mutableListOf() }.add(rightChild)
                graph.computeIfAbsent(rightChild) { mutableListOf() }.add(i)
            }
        }
        return graph
    }
    
    /**
     * Creates balanced tree
     */
    fun createBalancedTree(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 1 until size) {
            val parent = (i - 1) / 2
            graph.computeIfAbsent(parent) { mutableListOf() }.add(i)
            graph.computeIfAbsent(i) { mutableListOf() }.add(parent)
        }
        return graph
    }
    
    /**
     * Creates skewed tree
     */
    fun createSkewedTree(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until size - 1) {
            graph.computeIfAbsent(i) { mutableListOf() }.add(i + 1)
            graph.computeIfAbsent(i + 1) { mutableListOf() }.add(i)
        }
        return graph
    }
    
    /**
     * Creates star tree
     */
    fun createStarTree(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 1 until size) {
            graph.computeIfAbsent(0) { mutableListOf() }.add(i)
            graph.computeIfAbsent(i) { mutableListOf() }.add(0)
        }
        return graph
    }
    
    /**
     * Creates cycle graph
     */
    fun createCycleGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until size) {
            val next = (i + 1) % size
            graph.computeIfAbsent(i) { mutableListOf() }.add(next)
            graph.computeIfAbsent(next) { mutableListOf() }.add(i)
        }
        return graph
    }
    
    /**
     * Creates even cycle
     */
    fun createEvenCycle(size: Int): MutableMap<Int, MutableList<Int>> {
        require(size % 2 == 0) { "Size must be even" }
        return createCycleGraph(size)
    }
    
    /**
     * Creates odd cycle
     */
    fun createOddCycle(size: Int): MutableMap<Int, MutableList<Int>> {
        require(size % 2 == 1) { "Size must be odd" }
        return createCycleGraph(size)
    }
    
    /**
     * Creates path graph
     */
    fun createPathGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until size - 1) {
            graph.computeIfAbsent(i) { mutableListOf() }.add(i + 1)
            graph.computeIfAbsent(i + 1) { mutableListOf() }.add(i)
        }
        return graph
    }
    
    /**
     * Creates linear path
     */
    fun createLinearPath(size: Int): MutableMap<Int, MutableList<Int>> {
        return createPathGraph(size)
    }
    
    /**
     * Creates complete graph
     */
    fun createCompleteGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until size) {
            for (j in i + 1 until size) {
                graph.computeIfAbsent(i) { mutableListOf() }.add(j)
                graph.computeIfAbsent(j) { mutableListOf() }.add(i)
            }
        }
        return graph
    }
    
    /**
     * Creates complete directed graph
     */
    fun createCompleteDigraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until size) {
            for (j in 0 until size) {
                if (i != j) {
                    graph.computeIfAbsent(i) { mutableListOf() }.add(j)
                }
            }
        }
        return graph
    }
    
    /**
     * Creates grid graph
     */
    fun createGridGraph(rows: Int, cols: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                val node = i * cols + j
                // Add right neighbor
                if (j + 1 < cols) {
                    val right = i * cols + (j + 1)
                    graph.computeIfAbsent(node) { mutableListOf() }.add(right)
                    graph.computeIfAbsent(right) { mutableListOf() }.add(node)
                }
                // Add bottom neighbor
                if (i + 1 < rows) {
                    val bottom = (i + 1) * cols + j
                    graph.computeIfAbsent(node) { mutableListOf() }.add(bottom)
                    graph.computeIfAbsent(bottom) { mutableListOf() }.add(node)
                }
            }
        }
        return graph
    }
    
    /**
     * Creates rectangular grid
     */
    fun createRectangularGrid(rows: Int, cols: Int): MutableMap<Int, MutableList<Int>> {
        return createGridGraph(rows, cols)
    }
    
    /**
     * Creates square grid
     */
    fun createSquareGrid(size: Int): MutableMap<Int, MutableList<Int>> {
        return createGridGraph(size, size)
    }
    
    /**
     * Creates cube graph
     */
    fun createCubeGraph(dimension: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        val size = 1 shl dimension
        for (i in 0 until size) {
            for (j in 0 until dimension) {
                val neighbor = i xor (1 shl j)
                if (neighbor < size) {
                    graph.computeIfAbsent(i) { mutableListOf() }.add(neighbor)
                    graph.computeIfAbsent(neighbor) { mutableListOf() }.add(i)
                }
            }
        }
        return graph
    }
    
    /**
     * Creates hypercube
     */
    fun createHypercube(dimension: Int): MutableMap<Int, MutableList<Int>> {
        return createCubeGraph(dimension)
    }
    
    /**
     * Creates wheel graph
     */
    fun createWheelGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createCycleGraph(size - 1)
        val center = size - 1
        for (i in 0 until size - 1) {
            graph.computeIfAbsent(center) { mutableListOf() }.add(i)
            graph.computeIfAbsent(i) { mutableListOf() }.add(center)
        }
        return graph
    }
    
    /**
     * Creates fan graph
     */
    fun createFanGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createPathGraph(size - 1)
        val center = size - 1
        for (i in 0 until size - 1) {
            graph.computeIfAbsent(center) { mutableListOf() }.add(i)
            graph.computeIfAbsent(i) { mutableListOf() }.add(center)
        }
        return graph
    }
    
    /**
     * Creates ladder graph
     */
    fun createLadderGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until size - 1) {
            // Top rung
            graph.computeIfAbsent(i) { mutableListOf() }.add(i + 1)
            graph.computeIfAbsent(i + 1) { mutableListOf() }.add(i)
            // Bottom rung
            val bottomI = i + size
            val bottomNext = i + 1 + size
            graph.computeIfAbsent(bottomI) { mutableListOf() }.add(bottomNext)
            graph.computeIfAbsent(bottomNext) { mutableListOf() }.add(bottomI)
            // Vertical edges
            graph.computeIfAbsent(i) { mutableListOf() }.add(bottomI)
            graph.computeIfAbsent(bottomI) { mutableListOf() }.add(i)
        }
        return graph
    }
    
    /**
     * Creates circular ladder
     */
    fun createCircularLadder(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = createLadderGraph(size)
        // Connect ends
        graph.computeIfAbsent(0) { mutableListOf() }.add(size - 1)
        graph.computeIfAbsent(size - 1) { mutableListOf() }.add(0)
        graph.computeIfAbsent(size) { mutableListOf() }.add(2 * size - 1)
        graph.computeIfAbsent(2 * size - 1) { mutableListOf() }.add(size)
        return graph
    }
    
    /**
     * Creates tournament graph
     */
    fun createTournamentGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until size) {
            for (j in i + 1 until size) {
                if (i < j) {
                    graph.computeIfAbsent(i) { mutableListOf() }.add(j)
                }
            }
        }
        return graph
    }
    
    /**
     * Creates transitive tournament
     */
    fun createTransitiveTournament(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until size) {
            for (j in i + 1 until size) {
                graph.computeIfAbsent(i) { mutableListOf() }.add(j)
            }
        }
        return graph
    }
    
    /**
     * Creates planar graph
     */
    fun createPlanarGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        // Simple planar graph (cycle with chords)
        val graph = createCycleGraph(size)
        if (size >= 4) {
            graph.computeIfAbsent(0) { mutableListOf() }.add(2)
            graph.computeIfAbsent(2) { mutableListOf() }.add(0)
        }
        return graph
    }
    
    /**
     * Creates outerplanar graph
     */
    fun createOuterplanarGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        // Outerplanar graph (cycle with non-crossing chords)
        val graph = createCycleGraph(size)
        if (size >= 4) {
            graph.computeIfAbsent(0) { mutableListOf() }.add(2)
            graph.computeIfAbsent(2) { mutableListOf() }.add(0)
        }
        return graph
    }
    
    /**
     * Creates regular graph
     */
    fun createRegularGraph(size: Int, degree: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until size) {
            for (j in 1..degree) {
                val neighbor = (i + j) % size
                graph.computeIfAbsent(i) { mutableListOf() }.add(neighbor)
                graph.computeIfAbsent(neighbor) { mutableListOf() }.add(i)
            }
        }
        return graph
    }
    
    /**
     * Creates cubic graph
     */
    fun createCubicGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        return createRegularGraph(size, 3)
    }
    
    /**
     * Creates Eulerian graph
     */
    fun createEulerianGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        // Even degree for all vertices
        return createCycleGraph(size)
    }
    
    /**
     * Creates semi-Eulerian graph
     */
    fun createSemiEulerianGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        // Exactly two vertices with odd degree
        val graph = createPathGraph(size)
        if (size >= 3) {
            graph.computeIfAbsent(0) { mutableListOf() }.add(size - 1)
            graph.computeIfAbsent(size - 1) { mutableListOf() }.add(0)
        }
        return graph
    }
    
    /**
     * Creates Hamiltonian graph
     */
    fun createHamiltonianGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        // Contains Hamiltonian cycle
        return createCycleGraph(size)
    }
    
    /**
     * Creates semi-Hamiltonian graph
     */
    fun createSemiHamiltonianGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        // Contains Hamiltonian path but not cycle
        return createPathGraph(size)
    }
    
    /**
     * Creates forest graph
     */
    fun createForestGraph(numTrees: Int, treeSize: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (tree in 0 until numTrees) {
            val startNode = tree * treeSize
            for (i in 0 until treeSize - 1) {
                val node = startNode + i
                val next = startNode + i + 1
                graph.computeIfAbsent(node) { mutableListOf() }.add(next)
                graph.computeIfAbsent(next) { mutableListOf() }.add(node)
            }
        }
        return graph
    }
    
    /**
     * Creates disjoint union of graphs
     */
    fun createDisjointUnion(sizes: List<Int>): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        var offset = 0
        for (size in sizes) {
            for (i in 0 until size - 1) {
                val node = offset + i
                val next = offset + i + 1
                graph.computeIfAbsent(node) { mutableListOf() }.add(next)
                graph.computeIfAbsent(next) { mutableListOf() }.add(node)
            }
            offset += size
        }
        return graph
    }
    
    /**
     * Creates multigraph
     */
    fun createMultigraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until size - 1) {
            // Add multiple edges between consecutive nodes
            graph.computeIfAbsent(i) { mutableListOf() }.add(i + 1)
            graph.computeIfAbsent(i) { mutableListOf() }.add(i + 1)
            graph.computeIfAbsent(i + 1) { mutableListOf() }.add(i)
            graph.computeIfAbsent(i + 1) { mutableListOf() }.add(i)
        }
        return graph
    }
    
    /**
     * Creates weighted multigraph
     */
    fun createWeightedMultigraph(size: Int): MutableMap<Int, MutableList<Pair<Int, Int>>> {
        val graph = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
        for (i in 0 until size - 1) {
            // Add multiple weighted edges
            graph.computeIfAbsent(i) { mutableListOf() }.add(i + 1 to 1)
            graph.computeIfAbsent(i) { mutableListOf() }.add(i + 1 to 2)
            graph.computeIfAbsent(i + 1) { mutableListOf() }.add(i to 1)
            graph.computeIfAbsent(i + 1) { mutableListOf() }.add(i to 2)
        }
        return graph
    }
    
    /**
     * Creates pseudograph
     */
    fun createPseudograph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until size) {
            // Add self-loops
            graph.computeIfAbsent(i) { mutableListOf() }.add(i)
            // Add edges to next node
            if (i < size - 1) {
                graph.computeIfAbsent(i) { mutableListOf() }.add(i + 1)
                graph.computeIfAbsent(i + 1) { mutableListOf() }.add(i)
            }
        }
        return graph
    }
    
    /**
     * Creates mixed graph
     */
    fun createMixedGraph(size: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until size - 1) {
            // Undirected edge
            graph.computeIfAbsent(i) { mutableListOf() }.add(i + 1)
            graph.computeIfAbsent(i + 1) { mutableListOf() }.add(i)
            // Directed edge (if not last)
            if (i < size - 2) {
                graph.computeIfAbsent(i) { mutableListOf() }.add(i + 2)
            }
        }
        return graph
    }
    
    /**
     * Creates random graph
     */
    fun createRandomGraph(size: Int, probability: Double): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        for (i in 0 until size) {
            for (j in i + 1 until size) {
                if (Math.random() < probability) {
                    graph.computeIfAbsent(i) { mutableListOf() }.add(j)
                    graph.computeIfAbsent(j) { mutableListOf() }.add(i)
                }
            }
        }
        return graph
    }
    
    /**
     * Creates Erdős-Rényi graph
     */
    fun createErdosRenyiGraph(size: Int, numEdges: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        val maxEdges = size * (size - 1) / 2
        val edges = (0 until maxEdges).toMutableList()
        edges.shuffle()
        
        for (i in 0 until minOf(numEdges, maxEdges)) {
            val edge = edges[i]
            val u = edge / (size - 1)
            val v = edge % (size - 1) + (if (edge % (size - 1) >= u) 1 else 0)
            graph.computeIfAbsent(u) { mutableListOf() }.add(v)
            graph.computeIfAbsent(v) { mutableListOf() }.add(u)
        }
        return graph
    }
    
    /**
     * Creates scale-free graph
     */
    fun createScaleFreeGraph(size: Int, initialNodes: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        
        // Start with initial nodes
        for (i in 0 until initialNodes) {
            for (j in i + 1 until initialNodes) {
                graph.computeIfAbsent(i) { mutableListOf() }.add(j)
                graph.computeIfAbsent(j) { mutableListOf() }.add(i)
            }
        }
        
        // Add remaining nodes with preferential attachment
        for (i in initialNodes until size) {
            val degrees = mutableListOf<Int>()
            for (j in 0 until i) {
                val degree = graph[j]?.size ?: 0
                repeat(degree + 1) { degrees.add(j) }
            }
            
            if (degrees.isNotEmpty()) {
                val target = degrees.random()
                graph.computeIfAbsent(i) { mutableListOf() }.add(target)
                graph.computeIfAbsent(target) { mutableListOf() }.add(i)
            }
        }
        
        return graph
    }
    
    /**
     * Creates Barabási-Albert graph
     */
    fun createBarabasiAlbertGraph(size: Int, m: Int): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        
        // Start with m nodes
        for (i in 0 until m) {
            for (j in i + 1 until m) {
                graph.computeIfAbsent(i) { mutableListOf() }.add(j)
                graph.computeIfAbsent(j) { mutableListOf() }.add(i)
            }
        }
        
        // Add remaining nodes
        for (i in m until size) {
            val degrees = mutableListOf<Int>()
            for (j in 0 until i) {
                val degree = graph[j]?.size ?: 0
                repeat(degree + 1) { degrees.add(j) }
            }
            
            repeat(m) {
                if (degrees.isNotEmpty()) {
                    val target = degrees.random()
                    graph.computeIfAbsent(i) { mutableListOf() }.add(target)
                    graph.computeIfAbsent(target) { mutableListOf() }.add(i)
                    degrees.removeAll { it == target }
                }
            }
        }
        
        return graph
    }
    
    /**
     * Creates small-world graph
     */
    fun createSmallWorldGraph(size: Int, k: Int, p: Double): MutableMap<Int, MutableList<Int>> {
        val graph = mutableMapOf<Int, MutableList<Int>>()
        
        // Create regular ring lattice
        for (i in 0 until size) {
            for (j in 1..k / 2) {
                val neighbor = (i + j) % size
                graph.computeIfAbsent(i) { mutableListOf() }.add(neighbor)
                graph.computeIfAbsent(neighbor) { mutableListOf() }.add(i)
            }
        }
        
        // Rewire edges with probability p
        for (i in 0 until size) {
            val neighbors = graph[i]?.toMutableList() ?: mutableListOf()
            for (neighbor in neighbors) {
                if (Math.random() < p) {
                    graph[i]?.remove(neighbor)
                    graph[neighbor]?.remove(i)
                    
                    val newNeighbor = (0 until size).filter { it != i && it != neighbor }.random()
                    graph.computeIfAbsent(i) { mutableListOf() }.add(newNeighbor)
                    graph.computeIfAbsent(newNeighbor) { mutableListOf() }.add(i)
                }
            }
        }
        
        return graph
    }
    
    /**
     * Creates Watts-Strogatz graph
     */
    fun createWattsStrogatzGraph(size: Int, k: Int, p: Double): MutableMap<Int, MutableList<Int>> {
        return createSmallWorldGraph(size, k, p)
    }
} 