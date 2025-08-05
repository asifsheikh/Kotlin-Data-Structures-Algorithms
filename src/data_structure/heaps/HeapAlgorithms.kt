package data_structure.heaps

import java.util.*

/**
 * HEAP ALGORITHMS
 * 
 * This file contains standard algorithms that use heaps including
 * heap sort, top k elements, median finder, and other heap-based algorithms.
 * 
 * COMMON HEAP ALGORITHM PATTERNS:
 * - Top K Elements: Use min-heap for k largest, max-heap for k smallest
 * - Median Finding: Use two heaps (max-heap + min-heap)
 * - Sorting: Build heap and extract elements
 * - Priority Scheduling: Use heap for task prioritization
 * 
 * KEY INSIGHTS:
 * - Min-heap root = smallest element
 * - Max-heap root = largest element
 * - Heap operations (add/poll) = O(log n)
 * - Building heap from array = O(n)
 */

object HeapAlgorithms {
    
    /**
     * Heap Sort algorithm using max-heap (descending order)
     * 
     * ALGORITHM:
     * 1. Build a max-heap from the input array
     *    - Add each element to the max-heap
     *    - Each addition triggers heapify to maintain heap property
     * 2. Extract elements from the heap in descending order
     *    - Repeatedly poll the root (largest element)
     *    - Place each polled element in the result array
     * 
     * TIME COMPLEXITY: O(n log n)
     * - Building heap: O(n log n) - n additions, each taking O(log n)
     * - Extracting elements: O(n log n) - n polls, each taking O(log n)
     * SPACE COMPLEXITY: O(n) - Additional space for the heap
     * 
     * @param arr Input array to sort
     * @return Sorted array in descending order
     */
    fun heapSort(arr: IntArray): IntArray {
        val maxHeap = PriorityQueue<Int>(compareByDescending { it })
        
        // Build max-heap
        for (element in arr) {
            maxHeap.add(element)
        }
        
        // Extract elements in descending order
        val sortedArray = IntArray(arr.size)
        for (i in arr.indices) {
            sortedArray[i] = maxHeap.poll()
        }
        
        return sortedArray
    }
    
    /**
     * Heap Sort algorithm using min-heap (ascending order)
     * 
     * ALGORITHM:
     * 1. Build a min-heap from the input array
     *    - Add each element to the min-heap
     *    - Each addition triggers heapify to maintain heap property
     * 2. Extract elements from the heap in ascending order
     *    - Repeatedly poll the root (smallest element)
     *    - Place each polled element in the result array
     * 
     * TIME COMPLEXITY: O(n log n)
     * - Building heap: O(n log n) - n additions, each taking O(log n)
     * - Extracting elements: O(n log n) - n polls, each taking O(log n)
     * SPACE COMPLEXITY: O(n) - Additional space for the heap
     * 
     * @param arr Input array to sort
     * @return Sorted array in ascending order
     */
    fun heapSortAscending(arr: IntArray): IntArray {
        val minHeap = PriorityQueue<Int>()
        
        // Build min-heap
        for (element in arr) {
            minHeap.add(element)
        }
        
        // Extract elements in ascending order
        val sortedArray = IntArray(arr.size)
        for (i in arr.indices) {
            sortedArray[i] = minHeap.poll()
        }
        
        return sortedArray
    }
    
    /**
     * Find the kth largest element using min-heap
     * 
     * ALGORITHM:
     * 1. Maintain a min-heap of size k
     * 2. For each element in the array:
     *    - Add the element to the min-heap
     *    - If heap size exceeds k, remove the smallest element (root)
     * 3. After processing all elements, the root contains the kth largest
     * 
     * INTUITION:
     * - Min-heap of size k keeps the k largest elements
     * - Root of this heap is the kth largest element
     * - Smaller elements are automatically removed
     * 
     * TIME COMPLEXITY: O(n log k)
     * - n elements processed
     * - Each operation (add/poll) takes O(log k) on heap of size k
     * SPACE COMPLEXITY: O(k) - Heap size is limited to k
     * 
     * @param nums Input array
     * @param k Position of largest element to find (1-indexed)
     * @return The kth largest element
     */
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val minHeap = PriorityQueue<Int>()
        
        for (num in nums) {
            minHeap.add(num)
            if (minHeap.size > k) {
                minHeap.poll()
            }
        }
        
        return minHeap.peek()
    }
    
    /**
     * Find the kth smallest element using max-heap
     * 
     * ALGORITHM:
     * 1. Maintain a max-heap of size k
     * 2. For each element in the array:
     *    - Add the element to the max-heap
     *    - If heap size exceeds k, remove the largest element (root)
     * 3. After processing all elements, the root contains the kth smallest
     * 
     * INTUITION:
     * - Max-heap of size k keeps the k smallest elements
     * - Root of this heap is the kth smallest element
     * - Larger elements are automatically removed
     * 
     * TIME COMPLEXITY: O(n log k)
     * - n elements processed
     * - Each operation (add/poll) takes O(log k) on heap of size k
     * SPACE COMPLEXITY: O(k) - Heap size is limited to k
     * 
     * @param nums Input array
     * @param k Position of smallest element to find (1-indexed)
     * @return The kth smallest element
     */
    fun findKthSmallest(nums: IntArray, k: Int): Int {
        val maxHeap = PriorityQueue<Int>(compareByDescending { it })
        
        for (num in nums) {
            maxHeap.add(num)
            if (maxHeap.size > k) {
                maxHeap.poll()
            }
        }
        
        return maxHeap.peek()
    }
    
    /**
     * Find top k largest elements
     * 
     * ALGORITHM:
     * 1. Maintain a min-heap of size k
     * 2. For each element in the array:
     *    - Add the element to the min-heap
     *    - If heap size exceeds k, remove the smallest element (root)
     * 3. After processing all elements, return all elements in the heap
     * 4. Sort the result for consistent ordering
     * 
     * INTUITION:
     * - Min-heap of size k keeps the k largest elements
     * - All elements in the heap are the top k largest
     * - Smaller elements are automatically removed
     * 
     * TIME COMPLEXITY: O(n log k + k log k)
     * - Building heap: O(n log k)
     * - Sorting result: O(k log k)
     * SPACE COMPLEXITY: O(k) - Heap size is limited to k
     * 
     * @param nums Input array
     * @param k Number of largest elements to find
     * @return List of k largest elements in ascending order
     */
    fun findTopKLargest(nums: IntArray, k: Int): List<Int> {
        val minHeap = PriorityQueue<Int>()
        
        for (num in nums) {
            minHeap.add(num)
            if (minHeap.size > k) {
                minHeap.poll()
            }
        }
        
        return minHeap.toList().sorted()
    }
    
    /**
     * Find top k smallest elements
     * 
     * ALGORITHM:
     * 1. Maintain a max-heap of size k
     * 2. For each element in the array:
     *    - Add the element to the max-heap
     *    - If heap size exceeds k, remove the largest element (root)
     * 3. After processing all elements, return all elements in the heap
     * 4. Sort the result for consistent ordering
     * 
     * INTUITION:
     * - Max-heap of size k keeps the k smallest elements
     * - All elements in the heap are the top k smallest
     * - Larger elements are automatically removed
     * 
     * TIME COMPLEXITY: O(n log k + k log k)
     * - Building heap: O(n log k)
     * - Sorting result: O(k log k)
     * SPACE COMPLEXITY: O(k) - Heap size is limited to k
     * 
     * @param nums Input array
     * @param k Number of smallest elements to find
     * @return List of k smallest elements in ascending order
     */
    fun findTopKSmallest(nums: IntArray, k: Int): List<Int> {
        val maxHeap = PriorityQueue<Int>(compareByDescending { it })
        
        for (num in nums) {
            maxHeap.add(num)
            if (maxHeap.size > k) {
                maxHeap.poll()
            }
        }
        
        return maxHeap.toList().sorted()
    }
    
    /**
     * Median Finder using two heaps
     * 
     * ALGORITHM:
     * Uses two heaps to maintain the median efficiently:
     * - maxHeap: stores the left half (smaller elements)
     * - minHeap: stores the right half (larger elements)
     * 
     * ADD OPERATION:
     * 1. Add new element to maxHeap (left half)
     * 2. Move the largest element from maxHeap to minHeap (right half)
     * 3. If minHeap becomes larger, move smallest element back to maxHeap
     * 4. This ensures maxHeap.size >= minHeap.size
     * 
     * FIND MEDIAN:
     * - If maxHeap.size > minHeap.size: median = maxHeap.peek()
     * - If sizes are equal: median = (maxHeap.peek() + minHeap.peek()) / 2
     * 
     * INTUITION:
     * - maxHeap contains the smaller half of numbers
     * - minHeap contains the larger half of numbers
     * - Root of maxHeap is the largest of smaller half
     * - Root of minHeap is the smallest of larger half
     * 
     * TIME COMPLEXITY:
     * - addNum: O(log n) - heap operations
     * - findMedian: O(1) - direct access to roots
     * SPACE COMPLEXITY: O(n) - stores all elements across two heaps
     */
    class MedianFinder {
        private val maxHeap = PriorityQueue<Int>(compareByDescending { it }) // left half
        private val minHeap = PriorityQueue<Int>() // right half
        
        fun addNum(num: Int) {
            maxHeap.add(num)
            minHeap.add(maxHeap.poll())
            
            if (maxHeap.size < minHeap.size) {
                maxHeap.add(minHeap.poll())
            }
        }
        
        fun findMedian(): Double {
            return if (maxHeap.size > minHeap.size) {
                maxHeap.peek().toDouble()
            } else {
                (maxHeap.peek() + minHeap.peek()) / 2.0
            }
        }
    }
    
    /**
     * Sliding Window Maximum using deque (not heap, but related)
     * 
     * ALGORITHM:
     * Uses a monotonic deque to maintain the maximum in each sliding window:
     * 1. For each element, maintain a decreasing deque (largest to smallest)
     * 2. Remove elements outside the current window from front
     * 3. Remove smaller elements from back (they can't be maximum)
     * 4. Add current element to back
     * 5. Front of deque always contains the maximum for current window
     * 
     * INTUITION:
     * - Deque maintains indices of potential maximums
     * - Front always has the maximum for current window
     * - Smaller elements are removed as they can't be maximum
     * - Elements outside window are removed from front
     * 
     * TIME COMPLEXITY: O(n)
     * - Each element is added and removed at most once
     * - Amortized O(1) per element
     * SPACE COMPLEXITY: O(k) - deque size is at most k
     * 
     * @param nums Input array
     * @param k Window size
     * @return Array of maximum values for each sliding window
     */
    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
        if (nums.isEmpty() || k == 0) return IntArray(0)
        
        val result = IntArray(nums.size - k + 1)
        val deque = ArrayDeque<Int>()
        
        for (i in nums.indices) {
            // Remove elements outside the window
            while (deque.isNotEmpty() && deque.first() < i - k + 1) {
                deque.removeFirst()
            }
            
            // Remove smaller elements from the back
            while (deque.isNotEmpty() && nums[deque.last()] < nums[i]) {
                deque.removeLast()
            }
            
            deque.addLast(i)
            
            // Add maximum to result
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.first()]
            }
        }
        
        return result
    }
    
    /**
     * Merge k sorted arrays using min-heap
     * 
     * ALGORITHM:
     * 1. Create a min-heap to store (value, arrayIndex, elementIndex) tuples
     * 2. Add the first element from each non-empty array to the heap
     * 3. Repeatedly:
     *    - Poll the smallest element from the heap
     *    - Add it to the result
     *    - Add the next element from the same array to the heap
     * 4. Continue until heap is empty
     * 
     * INTUITION:
     * - Heap always contains the smallest available element from each array
     * - Polling gives us the next smallest element overall
     * - Adding next element from same array maintains sorted order
     * 
     * TIME COMPLEXITY: O(N log k)
     * - N = total number of elements across all arrays
     * - k = number of arrays
     * - Each element is added and polled once
     * - Heap operations take O(log k)
     * SPACE COMPLEXITY: O(k) - heap size is at most k
     * 
     * @param arrays List of sorted arrays to merge
     * @return Merged sorted array
     */
    fun mergeKSortedArrays(arrays: List<IntArray>): IntArray {
        val minHeap = PriorityQueue<Triple<Int, Int, Int>> { a, b -> a.first.compareTo(b.first) }
        
        // Add first element from each array
        for (i in arrays.indices) {
            if (arrays[i].isNotEmpty()) {
                minHeap.add(Triple(arrays[i][0], i, 0))
            }
        }
        
        val result = mutableListOf<Int>()
        
        while (minHeap.isNotEmpty()) {
            val (value, arrayIndex, elementIndex) = minHeap.poll()
            result.add(value)
            
            // Add next element from the same array
            if (elementIndex + 1 < arrays[arrayIndex].size) {
                minHeap.add(Triple(arrays[arrayIndex][elementIndex + 1], arrayIndex, elementIndex + 1))
            }
        }
        
        return result.toIntArray()
    }
    
    /**
     * Find k closest elements to a target
     * 
     * ALGORITHM:
     * 1. Maintain a max-heap of size k based on distance from target
     * 2. For each element in the array:
     *    - Calculate distance from target: |element - target|
     *    - Add (distance, element) pair to max-heap
     *    - If heap size exceeds k, remove the element with largest distance
     * 3. After processing all elements, return all elements in the heap
     * 4. Sort the result for consistent ordering
     * 
     * INTUITION:
     * - Max-heap of size k keeps the k elements with smallest distances
     * - Elements with larger distances are automatically removed
     * - Root always contains the element with largest distance among k closest
     * 
     * TIME COMPLEXITY: O(n log k + k log k)
     * - Building heap: O(n log k)
     * - Sorting result: O(k log k)
     * SPACE COMPLEXITY: O(k) - heap size is limited to k
     * 
     * @param arr Input array
     * @param k Number of closest elements to find
     * @param x Target value
     * @return List of k elements closest to target in ascending order
     */
    fun findKClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
        val maxHeap = PriorityQueue<Pair<Int, Int>> { a, b -> 
            b.first.compareTo(a.first) // max-heap based on distance
        }
        
        for (num in arr) {
            val distance = Math.abs(num - x)
            maxHeap.add(Pair(distance, num))
            
            if (maxHeap.size > k) {
                maxHeap.poll()
            }
        }
        
        return maxHeap.map { it.second }.sorted()
    }
    
    /**
     * Task Scheduler with cooldown
     * 
     * ALGORITHM:
     * 1. Count frequency of each task type
     * 2. Use max-heap to prioritize tasks with highest frequency
     * 3. Use queue to track tasks that are cooling down
     * 4. At each time step:
     *    - Execute highest frequency task if available
     *    - Put task back in queue with cooldown time
     *    - Move cooled-down tasks back to heap
     * 
     * INTUITION:
     * - Always execute the most frequent task when possible
     * - Use cooldown queue to track when tasks become available again
     * - This minimizes idle time and maximizes throughput
     * 
     * TIME COMPLEXITY: O(n log 26) = O(n)
     * - n = total number of tasks
     * - Heap operations are O(log 26) since there are at most 26 task types
     * SPACE COMPLEXITY: O(26) = O(1)
     * - Fixed space for frequency array and heaps
     * 
     * @param tasks Array of task types (A-Z)
     * @param n Cooldown period between same task types
     * @return Minimum time to complete all tasks
     */
    fun leastInterval(tasks: CharArray, n: Int): Int {
        val frequency = IntArray(26)
        for (task in tasks) {
            frequency[task - 'A']++
        }
        
        val maxHeap = PriorityQueue<Int>(compareByDescending { it })
        for (freq in frequency) {
            if (freq > 0) {
                maxHeap.add(freq)
            }
        }
        
        var time = 0
        val queue = ArrayDeque<Pair<Int, Int>>() // (frequency, available time)
        
        while (maxHeap.isNotEmpty() || queue.isNotEmpty()) {
            time++
            
            if (maxHeap.isNotEmpty()) {
                val freq = maxHeap.poll()
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
     * Connect ropes with minimum cost
     * 
     * ALGORITHM:
     * 1. Build a min-heap from all rope lengths
     * 2. Repeatedly:
     *    - Extract two shortest ropes from heap
     *    - Connect them (cost = sum of lengths)
     *    - Add the connected rope back to heap
     * 3. Continue until only one rope remains
     * 
     * INTUITION:
     * - Always connect the two shortest ropes first
     * - This minimizes the cost of each connection
     * - Greedy approach: local optimal choice leads to global optimal
     * 
     * TIME COMPLEXITY: O(n log n)
     * - Building heap: O(n log n)
     * - n-1 connections, each taking O(log n)
     * SPACE COMPLEXITY: O(n) - heap size
     * 
     * @param ropes Array of rope lengths
     * @return Minimum total cost to connect all ropes
     */
    fun connectRopes(ropes: IntArray): Int {
        val minHeap = PriorityQueue<Int>()
        
        for (rope in ropes) {
            minHeap.add(rope)
        }
        
        var totalCost = 0
        
        while (minHeap.size > 1) {
            val first = minHeap.poll()
            val second = minHeap.poll()
            val cost = first + second
            totalCost += cost
            minHeap.add(cost)
        }
        
        return totalCost
    }
    
    /**
     * Demonstrates heap algorithms
     */
    fun demonstrateHeapAlgorithms() {
        println("=== HEAP ALGORITHMS DEMONSTRATION ===\n")
        
        // 1. Heap Sort
        println("1. HEAP SORT")
        val arr1 = intArrayOf(64, 34, 25, 12, 22, 11, 90)
        val sorted1 = heapSort(arr1)
        val sorted2 = heapSortAscending(arr1)
        println("Original array: ${arr1.contentToString()}")
        println("Heap sort (descending): ${sorted1.contentToString()}")
        println("Heap sort (ascending): ${sorted2.contentToString()}")
        println()
        
        // 2. Kth largest/smallest
        println("2. KTH LARGEST/SMALLEST")
        val arr2 = intArrayOf(3, 2, 1, 5, 6, 4)
        val kthLargest = findKthLargest(arr2, 2)
        val kthSmallest = findKthSmallest(arr2, 3)
        println("Array: ${arr2.contentToString()}")
        println("2nd largest: $kthLargest")
        println("3rd smallest: $kthSmallest")
        println()
        
        // 3. Top K elements
        println("3. TOP K ELEMENTS")
        val topKLargest = findTopKLargest(arr2, 3)
        val topKSmallest = findTopKSmallest(arr2, 3)
        println("Top 3 largest: $topKLargest")
        println("Top 3 smallest: $topKSmallest")
        println()
        
        // 4. Median Finder
        println("4. MEDIAN FINDER")
        val medianFinder = MedianFinder()
        val numbers = intArrayOf(1, 2, 3, 4, 5)
        for (num in numbers) {
            medianFinder.addNum(num)
            println("After adding $num, median: ${medianFinder.findMedian()}")
        }
        println()
        
        // 5. Sliding Window Maximum
        println("5. SLIDING WINDOW MAXIMUM")
        val arr3 = intArrayOf(1, 3, -1, -3, 5, 3, 6, 7)
        val windowMax = maxSlidingWindow(arr3, 3)
        println("Array: ${arr3.contentToString()}")
        println("Sliding window max (k=3): ${windowMax.contentToString()}")
        println()
        
        // 6. Merge K Sorted Arrays
        println("6. MERGE K SORTED ARRAYS")
        val arrays = listOf(
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(3, 6, 9)
        )
        val merged = mergeKSortedArrays(arrays)
        println("Arrays: ${arrays.map { it.contentToString() }}")
        println("Merged: ${merged.contentToString()}")
        println()
        
        // 7. K Closest Elements
        println("7. K CLOSEST ELEMENTS")
        val arr4 = intArrayOf(1, 2, 3, 4, 5)
        val closest = findKClosestElements(arr4, 3, 3)
        println("Array: ${arr4.contentToString()}")
        println("3 closest to 3: $closest")
        println()
        
        // 8. Task Scheduler
        println("8. TASK SCHEDULER")
        val tasks = charArrayOf('A', 'A', 'A', 'B', 'B', 'B')
        val intervals = leastInterval(tasks, 2)
        println("Tasks: ${tasks.contentToString()}")
        println("Minimum intervals needed: $intervals")
        println()
        
        // 9. Connect Ropes
        println("9. CONNECT ROPES")
        val ropes = intArrayOf(4, 2, 7, 6, 9)
        val minCost = connectRopes(ropes)
        println("Rope lengths: ${ropes.contentToString()}")
        println("Minimum cost to connect: $minCost")
        println()
        
        println("=== HEAP ALGORITHMS DEMONSTRATION COMPLETE ===\n")
    }
} 