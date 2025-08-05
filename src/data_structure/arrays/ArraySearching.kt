package data_structure.arrays

/**
 * ARRAY SEARCHING ALGORITHMS
 * 
 * Problem: Given an array and a target element, find the element or its position.
 * 
 * Different search scenarios:
 * 1. Linear Search: Find element in unsorted array
 * 2. Binary Search: Find element in sorted array
 * 3. Find first/last occurrence
 * 4. Find missing elements
 * 5. Find duplicates
 * 
 * Example:
 * Array: [4, 2, 7, 1, 9, 3, 6]
 * Target: 7
 * Linear search result: index 2
 * 
 * Sorted Array: [1, 2, 3, 4, 6, 7, 9]
 * Target: 6
 * Binary search result: index 4
 * 
 * Intuition:
 * - Linear search: Check each element sequentially
 * - Binary search: Divide and conquer on sorted array
 * - Use hash sets for duplicate detection
 * - Use mathematical formulas for missing elements
 */

object ArraySearching {
    
    /**
     * Linear Search
     * 
     * Problem: Find the first occurrence of target in an unsorted array
     * 
     * Algorithm:
     * 1. Traverse array from left to right
     * 2. Compare each element with target
     * 3. Return index when found, -1 if not found
     * 
     * Time Complexity: O(n) - Worst case, we check all elements
     * Space Complexity: O(1) - Constant extra space
     */
    fun linearSearch(arr: IntArray, target: Int): Int {
        for (i in arr.indices) {
            if (arr[i] == target) {
                return i
            }
        }
        return -1
    }
    
    /**
     * Binary Search (Iterative)
     * 
     * Problem: Find target in a sorted array
     * 
     * Algorithm:
     * 1. Set left = 0, right = n-1
     * 2. While left <= right:
     *    - Calculate mid = (left + right) / 2
     *    - If arr[mid] == target, return mid
     *    - If arr[mid] < target, left = mid + 1
     *    - If arr[mid] > target, right = mid - 1
     * 3. Return -1 if not found
     * 
     * Time Complexity: O(log n) - We halve the search space each iteration
     * Space Complexity: O(1) - Constant extra space
     */
    fun binarySearch(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            
            when {
                arr[mid] == target -> return mid
                arr[mid] < target -> left = mid + 1
                else -> right = mid - 1
            }
        }
        return -1
    }
    
    /**
     * Binary Search (Recursive)
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) - Recursion stack space
     */
    fun binarySearchRecursive(arr: IntArray, target: Int): Int {
        return binarySearchRecursiveHelper(arr, target, 0, arr.size - 1)
    }
    
    private fun binarySearchRecursiveHelper(arr: IntArray, target: Int, left: Int, right: Int): Int {
        if (left > right) return -1
        
        val mid = left + (right - left) / 2
        
        return when {
            arr[mid] == target -> mid
            arr[mid] < target -> binarySearchRecursiveHelper(arr, target, mid + 1, right)
            else -> binarySearchRecursiveHelper(arr, target, left, mid - 1)
        }
    }
    
    /**
     * Find First Occurrence
     * 
     * Problem: Find the first occurrence of target in sorted array with duplicates
     * 
     * Algorithm:
     * 1. Use binary search but continue searching left when found
     * 2. Keep track of the leftmost occurrence
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    fun findFirstOccurrence(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        var result = -1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            
            when {
                arr[mid] == target -> {
                    result = mid
                    right = mid - 1  // Continue searching left
                }
                arr[mid] < target -> left = mid + 1
                else -> right = mid - 1
            }
        }
        return result
    }
    
    /**
     * Find Last Occurrence
     * 
     * Problem: Find the last occurrence of target in sorted array with duplicates
     * 
     * Algorithm:
     * 1. Use binary search but continue searching right when found
     * 2. Keep track of the rightmost occurrence
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    fun findLastOccurrence(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        var result = -1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            
            when {
                arr[mid] == target -> {
                    result = mid
                    left = mid + 1  // Continue searching right
                }
                arr[mid] < target -> left = mid + 1
                else -> right = mid - 1
            }
        }
        return result
    }
    
    /**
     * Find Missing Number
     * 
     * Problem: Find the missing number in array containing 0 to n
     * 
     * Algorithm:
     * 1. Calculate expected sum: n * (n + 1) / 2
     * 2. Calculate actual sum of array
     * 3. Missing number = expected sum - actual sum
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun findMissingNumber(arr: IntArray): Int {
        val n = arr.size
        val expectedSum = n * (n + 1) / 2
        val actualSum = arr.sum()
        return expectedSum - actualSum
    }
    
    /**
     * Find Missing Number using XOR
     * 
     * Algorithm:
     * 1. XOR all numbers from 0 to n
     * 2. XOR with all numbers in array
     * 3. Result is the missing number
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun findMissingNumberXOR(arr: IntArray): Int {
        var xor = 0
        
        // XOR all numbers from 0 to n
        for (i in 0..arr.size) {
            xor = xor.xor(i)
        }
        
        // XOR with all numbers in array
        for (num in arr) {
            xor = xor.xor(num)
        }
        
        return xor
    }
    
    /**
     * Find Duplicate Number
     * 
     * Problem: Find duplicate number in array containing 1 to n-1 with one duplicate
     * 
     * Algorithm:
     * 1. Use Floyd's Cycle Detection (Tortoise and Hare)
     * 2. Find intersection point
     * 3. Find start of cycle
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun findDuplicateNumber(arr: IntArray): Int {
        var slow = arr[0]
        var fast = arr[0]
        
        // Find intersection point
        do {
            slow = arr[slow]
            fast = arr[arr[fast]]
        } while (slow != fast)
        
        // Find start of cycle
        slow = arr[0]
        while (slow != fast) {
            slow = arr[slow]
            fast = arr[fast]
        }
        
        return slow
    }
    
    /**
     * Find Duplicate Number using Sum
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun findDuplicateNumberSum(arr: IntArray): Int {
        val n = arr.size - 1
        val expectedSum = n * (n + 1) / 2
        val actualSum = arr.sum()
        return actualSum - expectedSum
    }
    
    /**
     * Find All Duplicates
     * 
     * Problem: Find all duplicate numbers in array
     * 
     * Algorithm:
     * 1. Use array as hash table (since numbers are 1 to n)
     * 2. Mark visited numbers as negative
     * 3. If already negative, it's a duplicate
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1) - In-place modification
     */
    fun findAllDuplicates(arr: IntArray): List<Int> {
        val duplicates = mutableListOf<Int>()
        
        for (num in arr) {
            val index = abs(num) - 1
            if (arr[index] < 0) {
                duplicates.add(abs(num))
            } else {
                arr[index] = -arr[index]
            }
        }
        
        return duplicates
    }
    
    /**
     * Find Peak Element
     * 
     * Problem: Find a peak element (greater than neighbors) in array
     * 
     * Algorithm:
     * 1. Use binary search
     * 2. If mid is peak, return it
     * 3. If left neighbor > mid, search left half
     * 4. Otherwise, search right half
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    fun findPeakElement(arr: IntArray): Int {
        var left = 0
        var right = arr.size - 1
        
        while (left < right) {
            val mid = left + (right - left) / 2
            
            if (arr[mid] > arr[mid + 1]) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        
        return left
    }
    
    /**
     * Find K-th Largest Element
     * 
     * Problem: Find the k-th largest element in unsorted array
     * 
     * Algorithm:
     * 1. Use QuickSelect algorithm
     * 2. Partition array around pivot
     * 3. Recursively search in appropriate partition
     * 
     * Time Complexity: O(n) average, O(n²) worst case
     * Space Complexity: O(1)
     */
    fun findKthLargest(arr: IntArray, k: Int): Int {
        val kthSmallest = arr.size - k
        return quickSelect(arr, 0, arr.size - 1, kthSmallest)
    }
    
    private fun quickSelect(arr: IntArray, left: Int, right: Int, k: Int): Int {
        if (left == right) return arr[left]
        
        val pivotIndex = partition(arr, left, right)
        
        return when {
            k == pivotIndex -> arr[k]
            k < pivotIndex -> quickSelect(arr, left, pivotIndex - 1, k)
            else -> quickSelect(arr, pivotIndex + 1, right, k)
        }
    }
    
    private fun partition(arr: IntArray, left: Int, right: Int): Int {
        val pivot = arr[right]
        var i = left - 1
        
        for (j in left until right) {
            if (arr[j] <= pivot) {
                i++
                arr[i] = arr[j].also { arr[j] = arr[i] }
            }
        }
        
        arr[i + 1] = arr[right].also { arr[right] = arr[i + 1] }
        return i + 1
    }
} 