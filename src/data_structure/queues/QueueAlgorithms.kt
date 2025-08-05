package data_structure.queues

import data_structure.stacks.StackCreation
import java.util.PriorityQueue

/**
 * QUEUE ALGORITHMS
 * 
 * This file contains algorithms that use queues including
 * breadth-first search, task scheduling, sliding window,
 * and other queue-based algorithms.
 * 
 * COMMON QUEUE ALGORITHMS:
 * - Breadth-First Search: Level-order traversal using queue
 * - Task Scheduling: Process tasks in order
 * - Sliding Window: Process elements in windows
 * - Level Order Traversal: Tree traversal by levels
 * - Print Binary Tree: Print tree in level order
 */

object QueueAlgorithms {
    
    /**
     * Breadth-First Search on a graph
     * 
     * ALGORITHM:
     * 1. Start from source node and add to queue
     * 2. While queue is not empty:
     *    - Dequeue a node
     *    - Process the node
     *    - Add all unvisited neighbors to queue
     * 3. Mark nodes as visited to avoid cycles
     * 
     * TIME COMPLEXITY: O(V + E) - V vertices, E edges
     * SPACE COMPLEXITY: O(V) - queue stores vertices
     * 
     * @param graph Adjacency list representation of graph
     * @param start Starting vertex
     * @return List of vertices in BFS order
     */
    fun bfs(graph: Map<Int, List<Int>>, start: Int): List<Int> {
        val queue = QueueCreation.createBasicQueue<Int>()
        val visited = mutableSetOf<Int>()
        val result = mutableListOf<Int>()
        
        queue.addLast(start)
        visited.add(start)
        
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()!!
            result.add(current)
            
            for (neighbor in graph[current] ?: emptyList()) {
                if (neighbor !in visited) {
                    queue.addLast(neighbor)
                    visited.add(neighbor)
                }
            }
        }
        
        return result
    }
    
    /**
     * Level-order traversal of a binary tree
     * 
     * ALGORITHM:
     * 1. Start from root and add to queue
     * 2. While queue is not empty:
     *    - Dequeue a node
     *    - Process the node
     *    - Add left and right children to queue
     * 3. Process nodes level by level
     * 
     * TIME COMPLEXITY: O(n) - n nodes in tree
     * SPACE COMPLEXITY: O(w) - w is maximum width of tree
     * 
     * @param root Root of the binary tree
     * @return List of nodes in level-order
     */
    fun levelOrderTraversal(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()
        
        val queue = QueueCreation.createBasicQueue<TreeNode>()
        val result = mutableListOf<List<Int>>()
        
        queue.addLast(root)
        
        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            val level = mutableListOf<Int>()
            
            for (i in 0 until levelSize) {
                val current = queue.removeFirst()!!
                level.add(current.value)
                
                current.left?.let { queue.addLast(it) }
                current.right?.let { queue.addLast(it) }
            }
            
            result.add(level)
        }
        
        return result
    }
    
    /**
     * Task scheduler with cooldown period
     * 
     * ALGORITHM:
     * 1. Count frequency of each task
     * 2. Use priority queue to process most frequent tasks first
     * 3. Maintain cooldown period between same tasks
     * 4. Calculate minimum time needed
     * 
     * TIME COMPLEXITY: O(n log 26) = O(n) - n tasks, 26 task types
     * SPACE COMPLEXITY: O(1) - fixed size for task frequencies
     * 
     * @param tasks Array of task types
     * @param n Cooldown period between same tasks
     * @return Minimum time to complete all tasks
     */
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val frequency = IntArray(26)
        for (task in tasks) {
            frequency[task - 'A']++
        }
        
        val maxHeap = QueueCreation.createMaxPriorityQueue<Int>()
        for (freq in frequency) {
            if (freq > 0) {
                maxHeap.add(freq)
            }
        }
        
        var time = 0
        val queue = QueueCreation.createBasicQueue<Pair<Int, Int>>() // (frequency, available time)
        
        while (maxHeap.isNotEmpty() || queue.isNotEmpty()) {
            time++
            
            if (maxHeap.isNotEmpty()) {
                val freq = maxHeap.poll()!!
                if (freq > 1) {
                    queue.addLast(Pair(freq - 1, time + n))
                }
            }
            
            if (queue.isNotEmpty() && queue.first().second <= time) {
                maxHeap.add(queue.removeFirst().first)
            }
        }
        
        return time
    }
    
    /**
     * Sliding window maximum
     * 
     * ALGORITHM:
     * 1. Use deque to maintain indices of potential maximums
     * 2. Remove elements smaller than current element from back
     * 3. Remove elements outside window from front
     * 4. Front of deque always contains maximum for current window
     * 
     * TIME COMPLEXITY: O(n) - each element pushed/popped at most once
     * SPACE COMPLEXITY: O(k) - deque stores at most k elements
     * 
     * @param nums Array of numbers
     * @param k Window size
     * @return Array of maximum values for each window
     */
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        if (nums.isEmpty() || k == 0) return IntArray(0)
        
        val deque = QueueCreation.createBasicQueue<Int>()
        val result = IntArray(nums.size - k + 1)
        var resultIndex = 0
        
        for (i in nums.indices) {
            // Remove elements outside window
            while (deque.isNotEmpty() && deque.first() < i - k + 1) {
                deque.removeFirst()
            }
            
            // Remove elements smaller than current
            while (deque.isNotEmpty() && nums[deque.last()] < nums[i]) {
                deque.removeLast()
            }
            
            deque.addLast(i)
            
            // Add maximum to result
            if (i >= k - 1) {
                result[resultIndex++] = nums[deque.first()]
            }
        }
        
        return result
    }
    
    /**
     * Print binary tree in level order
     * 
     * ALGORITHM:
     * 1. Use queue for level-order traversal
     * 2. Track level information
     * 3. Format output with proper spacing
     * 
     * TIME COMPLEXITY: O(n) - n nodes in tree
     * SPACE COMPLEXITY: O(w) - w is maximum width of tree
     * 
     * @param root Root of the binary tree
     * @return String representation of tree in level order
     */
    fun printBinaryTree(root: TreeNode?): String {
        if (root == null) return "Empty Tree"
        
        val queue = QueueCreation.createBasicQueue<TreeNode>()
        val result = StringBuilder()
        
        queue.addLast(root)
        
        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            
            for (i in 0 until levelSize) {
                val current = queue.removeFirst()!!
                result.append("${current.value} ")
                
                current.left?.let { queue.addLast(it) }
                current.right?.let { queue.addLast(it) }
            }
            
            result.append("\n")
        }
        
        return result.toString()
    }
    
    /**
     * Circular queue implementation
     * 
     * ALGORITHM:
     * 1. Use array with front and rear pointers
     * 2. Handle wrap-around when pointers reach end
     * 3. Track size to distinguish full from empty
     * 
     * TIME COMPLEXITY: O(1) - constant time operations
     * SPACE COMPLEXITY: O(k) - k is capacity
     */
    class CircularQueue<T>(private val capacity: Int) {
        private val array = Array<Any?>(capacity) { null }
        private var front = 0
        private var rear = -1
        private var size = 0
        
        fun enqueue(element: T): Boolean {
            if (isFull()) return false
            
            rear = (rear + 1) % capacity
            array[rear] = element
            size++
            return true
        }
        
        fun dequeue(): T? {
            if (isEmpty()) return null
            
            val element = array[front] as T
            front = (front + 1) % capacity
            size--
            return element
        }
        
        fun peek(): T? {
            return if (isEmpty()) null else array[front] as T
        }
        
        fun isEmpty(): Boolean = size == 0
        fun isFull(): Boolean = size == capacity
        fun getSize(): Int = size
    }
    
    /**
     * Task scheduler with priorities
     * 
     * ALGORITHM:
     * 1. Use priority queue to process high-priority tasks first
     * 2. Track task execution time
     * 3. Handle task dependencies if any
     * 
     * TIME COMPLEXITY: O(n log n) - n tasks, log n for priority queue operations
     * SPACE COMPLEXITY: O(n) - priority queue stores all tasks
     */
    class TaskScheduler {
        private val priorityQueue = QueueCreation.createPriorityQueue<Task>()
        
        fun addTask(task: Task) {
            priorityQueue.add(task)
        }
        
        fun executeNextTask(): Task? {
            return if (priorityQueue.isNotEmpty()) {
                priorityQueue.poll()
            } else null
        }
        
        fun hasTasks(): Boolean = priorityQueue.isNotEmpty()
    }
    
    /**
     * Task data class
     */
    data class Task(
        val id: String,
        val priority: Int,
        val description: String,
        val estimatedTime: Int
    ) : Comparable<Task> {
        override fun compareTo(other: Task): Int {
            return other.priority.compareTo(priority) // Higher priority first
        }
    }
    
    /**
     * Print queue contents
     * 
     * ALGORITHM:
     * 1. Create a copy of the queue
     * 2. Dequeue elements and print them
     * 3. Restore the original queue
     * 
     * TIME COMPLEXITY: O(n) - n elements in queue
     * SPACE COMPLEXITY: O(n) - temporary storage
     * 
     * @param queue Queue to print
     * @return String representation of queue contents
     */
    fun <T> printQueue(queue: ArrayDeque<T>): String {
        if (queue.isEmpty()) return "Empty Queue"
        
        val tempQueue = QueueCreation.createBasicQueue<T>()
        val result = StringBuilder()
        
        while (queue.isNotEmpty()) {
            val element = queue.removeFirst()!!
            result.append("$element ")
            tempQueue.addLast(element)
        }
        
        // Restore original queue
        while (tempQueue.isNotEmpty()) {
            queue.addLast(tempQueue.removeFirst()!!)
        }
        
        return result.toString().trim()
    }
    
    /**
     * Reverse a queue using a stack
     * 
     * ALGORITHM:
     * 1. Push all elements from queue to stack
     * 2. Pop all elements from stack back to queue
     * 3. Order is reversed due to LIFO nature of stack
     * 
     * TIME COMPLEXITY: O(n) - n elements to process
     * SPACE COMPLEXITY: O(n) - stack stores all elements
     * 
     * @param queue Queue to reverse
     */
    fun <T> reverseQueue(queue: ArrayDeque<T>) {
        val stack = StackCreation.createBasicStack<T>()
        
        // Push all elements to stack
        while (queue.isNotEmpty()) {
            stack.addLast(queue.removeFirst()!!)
        }
        
        // Pop all elements back to queue
        while (stack.isNotEmpty()) {
            queue.addLast(stack.removeLast()!!)
        }
    }
    
    /**
     * Find first non-repeating character in stream
     * 
     * ALGORITHM:
     * 1. Use queue to maintain order of characters
     * 2. Use frequency array to track character counts
     * 3. Remove characters from queue when they repeat
     * 
     * TIME COMPLEXITY: O(n) - n characters in stream
     * SPACE COMPLEXITY: O(1) - fixed size for character frequencies
     */
    class FirstNonRepeatingStream {
        private val queue = QueueCreation.createBasicQueue<Char>()
        private val frequency = IntArray(26)
        
        fun addCharacter(ch: Char): Char? {
            val index = ch - 'a'
            frequency[index]++
            
            if (frequency[index] == 1) {
                queue.addLast(ch)
            }
            
            // Remove characters that have repeated
            while (queue.isNotEmpty() && frequency[queue.first() - 'a'] > 1) {
                queue.removeFirst()
            }
            
            return if (queue.isNotEmpty()) queue.first() else null
        }
    }
    
    /**
     * Demonstrates queue algorithms
     */
    fun demonstrateQueueAlgorithms() {
        println("=== QUEUE ALGORITHMS DEMONSTRATION ===\n")
        
        // 1. BFS on graph
        println("1. BREADTH-FIRST SEARCH")
        val graph = mapOf(
            0 to listOf(1, 2),
            1 to listOf(3, 4),
            2 to listOf(5),
            3 to emptyList(),
            4 to emptyList(),
            5 to emptyList()
        )
        val bfsResult = bfs(graph, 0)
        println("BFS starting from 0: $bfsResult")
        println()
        
        // 2. Level order traversal
        println("2. LEVEL ORDER TRAVERSAL")
        val root = TreeNode(1)
        root.left = TreeNode(2)
        root.right = TreeNode(3)
        root.left?.left = TreeNode(4)
        root.left?.right = TreeNode(5)
        root.right?.left = TreeNode(6)
        root.right?.right = TreeNode(7)
        
        val levelOrder = levelOrderTraversal(root)
        println("Level order traversal:")
        for (level in levelOrder) {
            println(level)
        }
        println()
        
        // 3. Task scheduler
        println("3. TASK SCHEDULER")
        val tasks = charArrayOf('A', 'A', 'A', 'B', 'B', 'B')
        val interval = leastInterval(tasks, 2)
        println("Tasks: ${tasks.contentToString()}")
        println("Minimum interval: $interval")
        println()
        
        // 4. Sliding window maximum
        println("4. SLIDING WINDOW MAXIMUM")
        val nums = intArrayOf(1, 3, -1, -3, 5, 3, 6, 7)
        val k = 3
        val maxWindow = maxSlidingWindow(nums, k)
        println("Array: ${nums.contentToString()}")
        println("Window size: $k")
        println("Max sliding window: ${maxWindow.contentToString()}")
        println()
        
        // 5. Print binary tree
        println("5. PRINT BINARY TREE")
        val treeString = printBinaryTree(root)
        println("Tree in level order:")
        println(treeString)
        
        // 6. Circular queue
        println("6. CIRCULAR QUEUE")
        val circularQueue = CircularQueue<Int>(3)
        circularQueue.enqueue(1)
        circularQueue.enqueue(2)
        circularQueue.enqueue(3)
        println("Circular queue operations:")
        println("Dequeue: ${circularQueue.dequeue()}")
        println("Peek: ${circularQueue.peek()}")
        println("Size: ${circularQueue.getSize()}")
        println()
        
        // 7. Task scheduler with priorities
        println("7. TASK SCHEDULER WITH PRIORITIES")
        val taskScheduler = TaskScheduler()
        taskScheduler.addTask(Task("T1", 3, "High priority task", 10))
        taskScheduler.addTask(Task("T2", 1, "Low priority task", 5))
        taskScheduler.addTask(Task("T3", 5, "Critical task", 15))
        
        println("Executing tasks in priority order:")
        while (taskScheduler.hasTasks()) {
            val task = taskScheduler.executeNextTask()!!
            println("Executing: ${task.id} (Priority: ${task.priority})")
        }
        println()
        
        // 8. Queue operations
        println("8. QUEUE OPERATIONS")
        val demoQueue = QueueCreation.createQueueFromList(listOf(1, 2, 3, 4, 5))
        println("Original queue: ${printQueue(demoQueue)}")
        reverseQueue(demoQueue)
        println("Reversed queue: ${printQueue(demoQueue)}")
        println()
        
        // 9. First non-repeating character stream
        println("9. FIRST NON-REPEATING CHARACTER STREAM")
        val stream = FirstNonRepeatingStream()
        val streamChars = "aabc"
        println("Processing stream: '$streamChars'")
        for (ch in streamChars) {
            val firstNonRepeating = stream.addCharacter(ch)
            println("After '$ch': First non-repeating = '$firstNonRepeating'")
        }
        println()
        
        println("=== QUEUE ALGORITHMS DEMONSTRATION COMPLETE ===\n")
    }
    
    /**
     * Simple TreeNode class for demonstrations
     */
    data class TreeNode(
        val value: Int,
        var left: TreeNode? = null,
        var right: TreeNode? = null
    )
} 