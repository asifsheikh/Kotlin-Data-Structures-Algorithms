package algos.searching

/**
 * COMPREHENSIVE SEARCHING ALGORITHMS REFERENCE
 * 
 * This file contains all essential searching algorithms commonly asked in interviews:
 * - Linear Search
 * - Binary Search (iterative and recursive)
 * - Binary Search variations (first/last occurrence, ceiling/floor)
 * - Search in rotated sorted arrays
 * - Search in 2D matrices
 * - Advanced search techniques
 */

fun searchingAlgorithms() {
    println("=== SEARCHING ALGORITHMS ===\n")
    
    // ===== LINEAR SEARCH =====
    println("1. LINEAR SEARCH")
    println("-----------------")
    
    fun linearSearch(arr: IntArray, target: Int): Int {
        for (i in arr.indices) {
            if (arr[i] == target) {
                return i
            }
        }
        return -1
    }
    
    val linearArray = intArrayOf(10, 20, 30, 40, 50)
    println("Linear search for 30: ${linearSearch(linearArray, 30)}")
    println("Linear search for 60: ${linearSearch(linearArray, 60)}")
    
    // ===== BINARY SEARCH =====
    println("\n2. BINARY SEARCH")
    println("----------------")
    
    // Standard binary search (iterative)
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
    
    // Binary search (recursive)
    fun binarySearchRecursive(arr: IntArray, target: Int): Int {
        fun search(left: Int, right: Int): Int {
            if (left > right) return -1
            
            val mid = left + (right - left) / 2
            
            return when {
                arr[mid] == target -> mid
                arr[mid] < target -> search(mid + 1, right)
                else -> search(left, mid - 1)
            }
        }
        return search(0, arr.size - 1)
    }
    
    val sortedArray = intArrayOf(1, 3, 5, 7, 9, 11, 13, 15)
    println("Binary search for 7: ${binarySearch(sortedArray, 7)}")
    println("Binary search recursive for 7: ${binarySearchRecursive(sortedArray, 7)}")
    println("Binary search for 6: ${binarySearch(sortedArray, 6)}")
    
    // ===== BINARY SEARCH VARIATIONS =====
    println("\n3. BINARY SEARCH VARIATIONS")
    println("---------------------------")
    
    // Find first occurrence
    fun findFirstOccurrence(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        var result = -1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            
            if (arr[mid] == target) {
                result = mid
                right = mid - 1 // Continue searching left
            } else if (arr[mid] < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return result
    }
    
    // Find last occurrence
    fun findLastOccurrence(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        var result = -1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            
            if (arr[mid] == target) {
                result = mid
                left = mid + 1 // Continue searching right
            } else if (arr[mid] < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return result
    }
    
    val duplicateArray = intArrayOf(1, 2, 2, 2, 3, 4, 5)
    println("First occurrence of 2: ${findFirstOccurrence(duplicateArray, 2)}")
    println("Last occurrence of 2: ${findLastOccurrence(duplicateArray, 2)}")
    
    // Find ceiling (smallest element >= target)
    fun findCeiling(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            
            if (arr[mid] == target) return mid
            if (arr[mid] < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return if (left < arr.size) left else -1
    }
    
    // Find floor (largest element <= target)
    fun findFloor(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            
            if (arr[mid] == target) return mid
            if (arr[mid] < target) {
                left = mid + 1
            } else {
                right = mid - 1
            }
        }
        return right
    }
    
    println("Ceiling of 6: ${findCeiling(sortedArray, 6)}")
    println("Floor of 6: ${findFloor(sortedArray, 6)}")
    
    // ===== SEARCH IN ROTATED SORTED ARRAY =====
    println("\n4. SEARCH IN ROTATED SORTED ARRAY")
    println("--------------------------------")
    
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
    
    val rotatedArray = intArrayOf(4, 5, 6, 7, 0, 1, 2)
    println("Search 0 in rotated array: ${searchInRotatedArray(rotatedArray, 0)}")
    println("Search 3 in rotated array: ${searchInRotatedArray(rotatedArray, 3)}")
    
    // Find minimum in rotated sorted array
    fun findMinInRotatedArray(arr: IntArray): Int {
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
    
    println("Minimum in rotated array: ${findMinInRotatedArray(rotatedArray)}")
    
    // ===== SEARCH IN 2D MATRIX =====
    println("\n5. SEARCH IN 2D MATRIX")
    println("----------------------")
    
    // Search in sorted 2D matrix (each row and column is sorted)
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return false
        
        val rows = matrix.size
        val cols = matrix[0].size
        var row = 0
        var col = cols - 1
        
        while (row < rows && col >= 0) {
            when {
                matrix[row][col] == target -> return true
                matrix[row][col] > target -> col--
                else -> row++
            }
        }
        return false
    }
    
    val matrix = arrayOf(
        intArrayOf(1, 4, 7, 11, 15),
        intArrayOf(2, 5, 8, 12, 19),
        intArrayOf(3, 6, 9, 16, 22),
        intArrayOf(10, 13, 14, 17, 24),
        intArrayOf(18, 21, 23, 26, 30)
    )
    println("Search 5 in matrix: ${searchMatrix(matrix, 5)}")
    println("Search 20 in matrix: ${searchMatrix(matrix, 20)}")
    
    // ===== ADVANCED SEARCH TECHNIQUES =====
    println("\n6. ADVANCED SEARCH TECHNIQUES")
    println("----------------------------")
    
    // Peak finding in 1D array
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
    
    val peakArray = intArrayOf(1, 2, 3, 1)
    println("Peak element index: ${findPeakElement(peakArray)}")
    
    // Search in infinite sorted array
    fun searchInfiniteArray(arr: IntArray, target: Int): Int {
        var low = 0
        var high = 1
        
        // Find the bounds
        while (arr[high] < target) {
            low = high
            high *= 2
        }
        
        // Binary search in the found range
        while (low <= high) {
            val mid = low + (high - low) / 2
            
            when {
                arr[mid] == target -> return mid
                arr[mid] < target -> low = mid + 1
                else -> high = mid - 1
            }
        }
        return -1
    }
    
    // Find k closest elements
    fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
        var left = 0
        var right = arr.size - k
        
        while (left < right) {
            val mid = left + (right - left) / 2
            
            // Compare distances
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        
        return arr.slice(left until left + k)
    }
    
    val closestArray = intArrayOf(1, 2, 3, 4, 5)
    println("3 closest to 3: ${findClosestElements(closestArray, 3, 3)}")
    
    // ===== SEARCH WITH CUSTOM COMPARATOR =====
    println("\n7. SEARCH WITH CUSTOM COMPARATOR")
    println("--------------------------------")
    
    // Binary search with custom condition
    fun binarySearchCustom(arr: IntArray, condition: (Int) -> Boolean): Int {
        var left = 0
        var right = arr.size - 1
        var result = -1
        
        while (left <= right) {
            val mid = left + (right - left) / 2
            
            if (condition(arr[mid])) {
                result = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
        return result
    }
    
    // Find first element >= 5
    val firstGE5 = binarySearchCustom(sortedArray) { it >= 5 }
    println("First element >= 5: ${if (firstGE5 != -1) sortedArray[firstGE5] else "Not found"}")
    
    // ===== QUICK REFERENCE SUMMARY =====
    println("\n8. QUICK REFERENCE")
    println("------------------")
    println("• Linear Search: O(n) time, O(1) space")
    println("• Binary Search: O(log n) time, O(1) space")
    println("• Variations:")
    println("  - First/Last occurrence")
    println("  - Ceiling/Floor")
    println("  - Peak finding")
    println("  - Rotated array search")
    println("• 2D Matrix Search: O(m + n) time")
    println("• Infinite Array: O(log n) time")
    println("• K Closest Elements: O(log n) time")
    println("• Key Points:")
    println("  - Always use left + (right - left) / 2 to avoid overflow")
    println("  - Handle edge cases (empty array, single element)")
    println("  - Consider duplicates for first/last occurrence")
    println("  - Use custom conditions for advanced problems")
}

// ===== BINARY SEARCH TEMPLATES =====
class BinarySearchTemplates {
    // Template 1: Standard binary search
    fun template1(arr: IntArray, target: Int): Int {
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
    
    // Template 2: Find first occurrence
    fun template2(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        
        while (left < right) {
            val mid = left + (right - left) / 2
            
            if (arr[mid] < target) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return if (arr[left] == target) left else -1
    }
    
    // Template 3: Find last occurrence
    fun template3(arr: IntArray, target: Int): Int {
        var left = 0
        var right = arr.size - 1
        
        while (left < right) {
            val mid = left + (right - left + 1) / 2
            
            if (arr[mid] > target) {
                right = mid - 1
            } else {
                left = mid
            }
        }
        return if (arr[left] == target) left else -1
    }
} 