package data_structure.lists.algorithms

/**
 * LIST SEARCHING ALGORITHMS
 * 
 * Problem: Find elements in lists using various search strategies.
 * 
 * Examples:
 * Input: [4, 2, 7, 1, 9, 3, 6], target = 7 → Output: 2 (index)
 * Input: [1, 2, 3, 4, 5, 6, 7], target = 5 → Output: 4 (index)
 * Input: [1, 2, 2, 3, 3, 3, 4], target = 3 → Output: 3 (first occurrence)
 * 
 * Intuition: Use different search strategies based on list properties
 * 
 * Time Complexity: O(n) for linear search, O(log n) for binary search
 * Space Complexity: O(1) for iterative, O(log n) for recursive
 */

object ListSearching {
    
    /**
     * Linear search in unsorted list
     */
    fun linearSearch(nums: List<Int>, target: Int): Int {
        for (i in nums.indices) {
            if (nums[i] == target) {
                return i
            }
        }
        return -1 // Not found
    }
    
    /**
     * Linear search with early exit for sorted list
     */
    fun linearSearchSorted(nums: List<Int>, target: Int): Int {
        for (i in nums.indices) {
            if (nums[i] == target) {
                return i
            }
            if (nums[i] > target) {
                break // Early exit for sorted list
            }
        }
        return -1
    }
    
    /**
     * Find last occurrence of element
     */
    fun findLastOccurrence(nums: List<Int>, target: Int): Int {
        for (i in nums.indices.reversed()) {
            if (nums[i] == target) {
                return i
            }
        }
        return -1
    }
    
    /**
     * Find all occurrences of element
     */
    fun findAllOccurrences(nums: List<Int>, target: Int): List<Int> {
        val result = mutableListOf<Int>()
        for (i in nums.indices) {
            if (nums[i] == target) {
                result.add(i)
            }
        }
        return result
    }
    
    /**
     * Binary search in sorted list
     */
    fun binarySearch(nums: List<Int>, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            when {
                nums[mid] == target -> return mid
                nums[mid] < target -> left = mid + 1
                else -> right = mid - 1
            }
        }
        return -1
    }
    
    /**
     * Binary search recursive implementation
     */
    fun binarySearchRecursive(nums: List<Int>, target: Int): Int {
        return binarySearchHelper(nums, target, 0, nums.size - 1)
    }
    
    private fun binarySearchHelper(nums: List<Int>, target: Int, left: Int, right: Int): Int {
        if (left > right) return -1
        
        val mid = left + (right - left) / 2
        return when {
            nums[mid] == target -> mid
            nums[mid] < target -> binarySearchHelper(nums, target, mid + 1, right)
            else -> binarySearchHelper(nums, target, left, mid - 1)
        }
    }
    
    /**
     * Find first occurrence in sorted list with duplicates
     */
    fun findFirstOccurrence(nums: List<Int>, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        var result = -1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (nums[mid] == target) {
                result = mid
                right = mid - 1 // Continue searching left half
            } else if (nums[mid] < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return result
    }
    
    /**
     * Find last occurrence in sorted list with duplicates
     */
    fun findLastOccurrenceBinary(nums: List<Int>, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        var result = -1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (nums[mid] == target) {
                result = mid
                left = mid + 1 // Continue searching right half
            } else if (nums[mid] < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return result
    }
    
    /**
     * Find insertion position for target in sorted list
     */
    fun findInsertionPosition(nums: List<Int>, target: Int): Int {
        var left = 0
        var right = nums.size
        
        while (left < right) {
            val mid = left + (right - left) / 2
            if (nums[mid] < target) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
    
    /**
     * Find floor value (largest element <= target)
     */
    fun findFloor(nums: List<Int>, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        var result = -1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (nums[mid] <= target) {
                result = nums[mid]
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return result
    }
    
    /**
     * Find ceiling value (smallest element >= target)
     */
    fun findCeiling(nums: List<Int>, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        var result = -1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            if (nums[mid] >= target) {
                result = nums[mid]
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return result
    }
    
    /**
     * Search in rotated sorted array
     */
    fun searchInRotatedArray(nums: List<Int>, target: Int): Int {
        var left = 0
        var right = nums.size - 1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            
            if (nums[mid] == target) return mid
            
            // Check if left half is sorted
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            } else {
                // Right half is sorted
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }
        }
        return -1
    }
    
    /**
     * Find minimum element in rotated sorted array
     */
    fun findMinInRotatedArray(nums: List<Int>): Int {
        var left = 0
        var right = nums.size - 1
        
        while (left < right) {
            val mid = left + (right - left) / 2
            
            if (nums[mid] > nums[right]) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return nums[left]
    }
    
    /**
     * Find peak element in array
     */
    fun findPeakElement(nums: List<Int>): Int {
        var left = 0
        var right = nums.size - 1
        
        while (left < right) {
            val mid = left + (right - left) / 2
            
            if (nums[mid] > nums[mid + 1]) {
                right = mid
            } else {
                left = mid + 1
            }
        }
        return left
    }
    
    /**
     * Search in 2D sorted matrix
     */
    fun searchMatrix(matrix: List<List<Int>>, target: Int): Boolean {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return false
        
        val rows = matrix.size
        val cols = matrix[0].size
        var left = 0
        var right = rows * cols - 1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            val row = mid / cols
            val col = mid % cols
            val value = matrix[row][col]
            
            when {
                value == target -> return true
                value < target -> left = mid + 1
                else -> right = mid - 1
            }
        }
        return false
    }
    
    /**
     * Find k closest elements to target
     */
    fun findKClosestElements(nums: List<Int>, k: Int, target: Int): List<Int> {
        val result = mutableListOf<Int>()
        val distances = nums.mapIndexed { index, num -> 
            Triple(index, num, kotlin.math.abs(num - target))
        }.sortedBy { it.third }
        
        for (i in 0 until minOf(k, distances.size)) {
            result.add(distances[i].second)
        }
        
        return result.sorted()
    }
    
    /**
     * Jump search for sorted list
     */
    fun jumpSearch(nums: List<Int>, target: Int): Int {
        val n = nums.size
        val step = kotlin.math.sqrt(n.toDouble()).toInt()
        var prev = 0
        
        // Finding the block where element is present
        while (prev < n && nums[minOf(step, n) - 1] < target) {
            prev = step
            step += kotlin.math.sqrt(n.toDouble()).toInt()
            if (prev >= n) return -1
        }
        
        // Linear search in the identified block
        while (prev < minOf(step, n)) {
            if (nums[prev] == target) return prev
            prev++
        }
        
        return -1
    }
} 