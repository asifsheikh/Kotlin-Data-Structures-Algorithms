package data_structure.arrays.algorithms.searching

/**
 * LINEAR SEARCH ALGORITHM
 * 
 * Problem: Find the first occurrence of a target element in an unsorted array.
 * 
 * Given an array of n elements and a target value, find the index of the first
 * occurrence of the target in the array. If the target is not found, return -1.
 * 
 * Example:
 * Array: [4, 2, 7, 1, 9, 3, 6]
 * Target: 7
 * Result: 2 (index where 7 is found)
 * 
 * Array: [1, 2, 3, 4, 5]
 * Target: 6
 * Result: -1 (not found)
 * 
 * Intuition:
 * - Since the array is unsorted, we need to check each element sequentially
 * - Start from the first element and compare with target
 * - If found, return the index immediately
 * - If not found after checking all elements, return -1
 * - This is the simplest search algorithm but not the most efficient
 * 
 * Use Cases:
 * - Small arrays or unsorted data
 * - When you need to find the first occurrence
 * - When data is rarely searched
 * - As a fallback when other search methods are not applicable
 */

object LinearSearch {
    
    /**
     * Basic Linear Search
     * 
     * Algorithm:
     * 1. Traverse the array from left to right
     * 2. Compare each element with the target
     * 3. If found, return the index immediately
     * 4. If not found after checking all elements, return -1
     * 
     * Time Complexity: O(n) - Worst case, we check all n elements
     * Space Complexity: O(1) - We only use a constant amount of extra space
     * 
     * Best Case: O(1) - Target found at first position
     * Average Case: O(n/2) - Target found in middle on average
     * Worst Case: O(n) - Target not found or at last position
     */
    fun search(arr: IntArray, target: Int): Int {
        for (i in arr.indices) {
            if (arr[i] == target) {
                return i
            }
        }
        return -1
    }
    
    /**
     * Linear Search with Early Exit for Sorted Array
     * 
     * If we know the array is sorted in ascending order, we can exit early
     * when we encounter an element greater than the target.
     * 
     * Algorithm:
     * 1. Traverse array from left to right
     * 2. If current element equals target, return index
     * 3. If current element > target, exit early (not found)
     * 4. If not found, return -1
     * 
     * Time Complexity: O(n) - Still O(n) in worst case
     * Space Complexity: O(1)
     */
    fun searchSorted(arr: IntArray, target: Int): Int {
        for (i in arr.indices) {
            when {
                arr[i] == target -> return i
                arr[i] > target -> return -1  // Early exit for sorted array
            }
        }
        return -1
    }
    
    /**
     * Linear Search for Last Occurrence
     * 
     * Find the last occurrence of the target element in the array.
     * 
     * Algorithm:
     * 1. Traverse array from left to right
     * 2. Keep track of the last occurrence found
     * 3. Update last occurrence when target is found
     * 4. Return the last occurrence index or -1
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun searchLastOccurrence(arr: IntArray, target: Int): Int {
        var lastIndex = -1
        for (i in arr.indices) {
            if (arr[i] == target) {
                lastIndex = i
            }
        }
        return lastIndex
    }
    
    /**
     * Linear Search for All Occurrences
     * 
     * Find all occurrences of the target element in the array.
     * 
     * Algorithm:
     * 1. Traverse array from left to right
     * 2. Collect all indices where target is found
     * 3. Return list of all occurrence indices
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(k) where k is the number of occurrences
     */
    fun searchAllOccurrences(arr: IntArray, target: Int): List<Int> {
        val occurrences = mutableListOf<Int>()
        for (i in arr.indices) {
            if (arr[i] == target) {
                occurrences.add(i)
            }
        }
        return occurrences
    }
    
    /**
     * Linear Search with Sentinel
     * 
     * Optimized version that reduces the number of comparisons
     * by adding the target as a sentinel at the end.
     * 
     * Algorithm:
     * 1. Store the original last element
     * 2. Place target at the end as sentinel
     * 3. Search until target is found (will always be found)
     * 4. Restore original last element
     * 5. Return index if it's not the sentinel position
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * 
     * Advantage: Reduces number of comparisons in the loop
     */
    fun searchWithSentinel(arr: IntArray, target: Int): Int {
        val n = arr.size
        if (n == 0) return -1
        
        // Store original last element
        val originalLast = arr[n - 1]
        
        // Place sentinel
        arr[n - 1] = target
        
        var i = 0
        while (arr[i] != target) {
            i++
        }
        
        // Restore original last element
        arr[n - 1] = originalLast
        
        // Check if target was found at sentinel position
        return if (i < n - 1 || originalLast == target) i else -1
    }
    
    /**
     * Linear Search with Recursion
     * 
     * Recursive implementation of linear search.
     * 
     * Algorithm:
     * 1. Base case: if array is empty, return -1
     * 2. If first element equals target, return 0
     * 3. Recursively search in the rest of the array
     * 4. Add 1 to the result if found in subarray
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) - Recursion stack space
     */
    fun searchRecursive(arr: IntArray, target: Int): Int {
        return searchRecursiveHelper(arr, target, 0)
    }
    
    private fun searchRecursiveHelper(arr: IntArray, target: Int, index: Int): Int {
        // Base case: reached end of array
        if (index >= arr.size) return -1
        
        // Found target
        if (arr[index] == target) return index
        
        // Recursively search rest of array
        return searchRecursiveHelper(arr, target, index + 1)
    }
    
    /**
     * Linear Search for Minimum Element
     * 
     * Find the minimum element in the array.
     * 
     * Algorithm:
     * 1. Assume first element is minimum
     * 2. Traverse array and update minimum if smaller element found
     * 3. Return the minimum value
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun findMinimum(arr: IntArray): Int? {
        if (arr.isEmpty()) return null
        
        var min = arr[0]
        for (i in 1 until arr.size) {
            if (arr[i] < min) {
                min = arr[i]
            }
        }
        return min
    }
    
    /**
     * Linear Search for Maximum Element
     * 
     * Find the maximum element in the array.
     * 
     * Algorithm:
     * 1. Assume first element is maximum
     * 2. Traverse array and update maximum if larger element found
     * 3. Return the maximum value
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun findMaximum(arr: IntArray): Int? {
        if (arr.isEmpty()) return null
        
        var max = arr[0]
        for (i in 1 until arr.size) {
            if (arr[i] > max) {
                max = arr[i]
            }
        }
        return max
    }
    
    /**
     * Linear Search for Second Largest Element
     * 
     * Find the second largest element in the array.
     * 
     * Algorithm:
     * 1. Keep track of largest and second largest
     * 2. Update both when a larger element is found
     * 3. Return second largest
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun findSecondLargest(arr: IntArray): Int? {
        if (arr.size < 2) return null
        
        var largest = maxOf(arr[0], arr[1])
        var secondLargest = minOf(arr[0], arr[1])
        
        for (i in 2 until arr.size) {
            when {
                arr[i] > largest -> {
                    secondLargest = largest
                    largest = arr[i]
                }
                arr[i] > secondLargest && arr[i] != largest -> {
                    secondLargest = arr[i]
                }
            }
        }
        
        return secondLargest
    }
} 