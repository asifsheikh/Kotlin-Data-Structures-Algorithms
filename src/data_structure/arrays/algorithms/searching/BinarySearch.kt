package data_structure.arrays.algorithms.searching

/**
 * BINARY SEARCH ALGORITHM
 * 
 * Problem: Find a target element in a sorted array efficiently.
 * 
 * Given a sorted array of n elements and a target value, find the index of the
 * target in the array. If the target is not found, return -1.
 * 
 * Example:
 * Array: [1, 2, 3, 4, 5, 6, 7, 8, 9]
 * Target: 6
 * Result: 5 (index where 6 is found)
 * 
 * Array: [1, 2, 3, 4, 5]
 * Target: 6
 * Result: -1 (not found)
 * 
 * Intuition:
 * - Since the array is sorted, we can eliminate half the elements in each step
 * - Compare target with middle element
 * - If target < middle, search left half
 * - If target > middle, search right half
 * - If target == middle, found!
 * - Continue until target found or search space is empty
 * 
 * Use Cases:
 * - Large sorted arrays
 * - When you need fast search performance
 * - Database queries
 * - Finding insertion points
 * - Range queries
 */

object BinarySearch {
    
    /**
     * Basic Binary Search (Iterative)
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
     * Space Complexity: O(1) - We only use a constant amount of extra space
     * 
     * Best Case: O(1) - Target found at middle
     * Average Case: O(log n)
     * Worst Case: O(log n) - Target not found
     */
    fun search(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        
        while (left <= right) {
            val mid = left + (right - left) / 2  // Avoid overflow
            
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
     * Recursive implementation of binary search.
     * 
     * Algorithm:
     * 1. Base case: if left > right, return -1
     * 2. Calculate mid = (left + right) / 2
     * 3. If found at mid, return mid
     * 4. Recursively search left or right half
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(log n) - Recursion stack space
     */
    fun searchRecursive(arr: IntArray, target: Int): Int {
        return searchRecursiveHelper(arr, target, 0, arr.size - 1)
    }
    
    private fun searchRecursiveHelper(arr: IntArray, target: Int, left: Int, right: Int): Int {
        if (left > right) return -1
        
        val mid = left + (right - left) / 2
        
        return when {
            arr[mid] == target -> mid
            arr[mid] < target -> searchRecursiveHelper(arr, target, mid + 1, right)
            else -> searchRecursiveHelper(arr, target, left, mid - 1)
        }
    }
    
    /**
     * Find First Occurrence
     * 
     * Find the first occurrence of target in sorted array with duplicates.
     * 
     * Algorithm:
     * 1. Use binary search but continue searching left when found
     * 2. Keep track of the leftmost occurrence
     * 3. Return the leftmost occurrence
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
     * Find the last occurrence of target in sorted array with duplicates.
     * 
     * Algorithm:
     * 1. Use binary search but continue searching right when found
     * 2. Keep track of the rightmost occurrence
     * 3. Return the rightmost occurrence
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
     * Find Insertion Position
     * 
     * Find the position where target should be inserted to maintain sorted order.
     * 
     * Algorithm:
     * 1. Use binary search to find the rightmost element <= target
     * 2. Return the position after that element
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    fun findInsertionPosition(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size
        
        while (left < right) {
            val mid = left + (right - left) / 2
            
            if (arr[mid] < target) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
    
    /**
     * Find Floor Value
     * 
     * Find the largest element <= target.
     * 
     * Algorithm:
     * 1. Use binary search to find the rightmost element <= target
     * 2. Return that element or -1 if not found
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    fun findFloor(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        var result = -1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            
            if (arr[mid] <= target) {
                result = arr[mid]
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return result
    }
    
    /**
     * Find Ceiling Value
     * 
     * Find the smallest element >= target.
     * 
     * Algorithm:
     * 1. Use binary search to find the leftmost element >= target
     * 2. Return that element or -1 if not found
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    fun findCeiling(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        var result = -1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            
            if (arr[mid] >= target) {
                result = arr[mid]
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return result
    }
    
    /**
     * Find Peak Element
     * 
     * Find a peak element (greater than neighbors) in array.
     * 
     * Algorithm:
     * 1. Use binary search on the array
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
     * Search in Rotated Sorted Array
     * 
     * Find target in array that has been rotated at some pivot.
     * 
     * Algorithm:
     * 1. Find the pivot point (where rotation occurred)
     * 2. Determine which half contains the target
     * 3. Apply binary search on the appropriate half
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    fun searchInRotatedArray(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            
            if (arr[mid] == target) return mid
            
            // Check if left half is sorted
            if (arr[left] <= arr[mid]) {
                if (target >= arr[left] && target < arr[mid]) {
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            } else {
                // Right half is sorted
                if (target > arr[mid] && target <= arr[right]) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }
        }
        return -1
    }
    
    /**
     * Find Minimum in Rotated Sorted Array
     * 
     * Find the minimum element in a rotated sorted array.
     * 
     * Algorithm:
     * 1. Use binary search to find the pivot point
     * 2. The minimum is at the pivot point
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    fun findMinimumInRotatedArray(arr: IntArray): Int {
        var left = 0
        var right = arr.size - 1
        
        while (left < right) {
            val mid = left + (right - left) / 2
            
            if (arr[mid] > arr[right]) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return arr[left]
    }
    
    /**
     * Count Occurrences
     * 
     * Count the number of occurrences of target in sorted array.
     * 
     * Algorithm:
     * 1. Find first and last occurrence
     * 2. Return last - first + 1
     * 
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    fun countOccurrences(arr: IntArray, target: Int): Int {
        val first = findFirstOccurrence(arr, target)
        if (first == -1) return 0
        
        val last = findLastOccurrence(arr, target)
        return last - first + 1
    }
} 