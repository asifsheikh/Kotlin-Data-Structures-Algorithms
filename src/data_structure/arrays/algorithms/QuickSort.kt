package data_structure.arrays.algorithms

/**
 * QUICKSORT ALGORITHM
 * 
 * Problem: Sort an array of elements in ascending order efficiently.
 * 
 * Given an unsorted array of n elements, rearrange them so that they are
 * in ascending order (or any specified order).
 * 
 * Example:
 * Input: [64, 34, 25, 12, 22, 11, 90]
 * Output: [11, 12, 22, 25, 34, 64, 90]
 * 
 * Intuition:
 * - Use divide-and-conquer strategy
 * - Choose a pivot element and partition array around it
 * - Elements < pivot go to left, elements > pivot go to right
 * - Recursively sort left and right subarrays
 * - Pivot ends up in its final sorted position
 * - Combine sorted subarrays (already in place)
 * 
 * Key Features:
 * - In-place sorting algorithm
 * - Average case is very efficient
 * - Unstable sort (may change relative order of equal elements)
 * - Cache-friendly due to locality of reference
 */

object QuickSort {
    
    /**
     * Basic QuickSort Implementation
     * 
     * Algorithm:
     * 1. Choose a pivot element (usually last element)
     * 2. Partition array around pivot
     * 3. Recursively sort left and right subarrays
     * 4. Base case: arrays of size 0 or 1 are already sorted
     * 
     * Time Complexity: 
     * - Average Case: O(n log n) - Good pivot selection
     * - Worst Case: O(n²) - Bad pivot selection (already sorted/reverse sorted)
     * - Best Case: O(n log n) - Balanced partitions
     * 
     * Space Complexity: O(log n) - Recursion stack space
     */
    fun sort(arr: IntArray) {
        quickSort(arr, 0, arr.size - 1)
    }
    
    private fun quickSort(arr: IntArray, low: Int, high: Int) {
        if (low < high) {
            val pivotIndex = partition(arr, low, high)
            quickSort(arr, low, pivotIndex - 1)
            quickSort(arr, pivotIndex + 1, high)
        }
    }
    
    /**
     * Partition Function (Lomuto's Partition)
     * 
     * Algorithm:
     * 1. Choose last element as pivot
     * 2. Use two pointers: i (smaller elements) and j (current element)
     * 3. Move elements smaller than pivot to left side
     * 4. Place pivot in its final position
     * 5. Return pivot index
     * 
     * Time Complexity: O(n) - Single pass through array
     * Space Complexity: O(1) - In-place partitioning
     */
    private fun partition(arr: IntArray, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low - 1  // Index of smaller element
        
        for (j in low until high) {
            if (arr[j] <= pivot) {
                i++
                arr[i] = arr[j].also { arr[j] = arr[i] }
            }
        }
        
        // Place pivot in correct position
        arr[i + 1] = arr[high].also { arr[high] = arr[i + 1] }
        return i + 1
    }
    
    /**
     * QuickSort with Hoare's Partition
     * 
     * Alternative partitioning scheme that uses two pointers
     * from both ends of the array.
     * 
     * Algorithm:
     * 1. Choose first element as pivot
     * 2. Use two pointers: left and right
     * 3. Move left pointer until element > pivot
     * 4. Move right pointer until element < pivot
     * 5. Swap elements and continue until pointers meet
     * 
     * Time Complexity: O(n log n) average
     * Space Complexity: O(log n)
     */
    fun sortWithHoarePartition(arr: IntArray) {
        quickSortHoare(arr, 0, arr.size - 1)
    }
    
    private fun quickSortHoare(arr: IntArray, low: Int, high: Int) {
        if (low < high) {
            val pivotIndex = hoarePartition(arr, low, high)
            quickSortHoare(arr, low, pivotIndex)
            quickSortHoare(arr, pivotIndex + 1, high)
        }
    }
    
    private fun hoarePartition(arr: IntArray, low: Int, high: Int): Int {
        val pivot = arr[low]
        var left = low - 1
        var right = high + 1
        
        while (true) {
            // Find element >= pivot from left
            do {
                left++
            } while (arr[left] < pivot)
            
            // Find element <= pivot from right
            do {
                right--
            } while (arr[right] > pivot)
            
            // If pointers meet, return right pointer
            if (left >= right) return right
            
            // Swap elements
            arr[left] = arr[right].also { arr[right] = arr[left] }
        }
    }
    
    /**
     * QuickSort with Randomized Pivot Selection
     * 
     * Improves performance by choosing random pivot,
     * reducing chance of worst-case scenario.
     * 
     * Algorithm:
     * 1. Choose random element as pivot
     * 2. Swap with last element
     * 3. Use standard partition
     * 
     * Time Complexity: O(n log n) average, O(n²) worst case (rare)
     * Space Complexity: O(log n)
     */
    fun sortRandomized(arr: IntArray) {
        quickSortRandomized(arr, 0, arr.size - 1)
    }
    
    private fun quickSortRandomized(arr: IntArray, low: Int, high: Int) {
        if (low < high) {
            val pivotIndex = randomizedPartition(arr, low, high)
            quickSortRandomized(arr, low, pivotIndex - 1)
            quickSortRandomized(arr, pivotIndex + 1, high)
        }
    }
    
    private fun randomizedPartition(arr: IntArray, low: Int, high: Int): Int {
        val randomIndex = (low..high).random()
        arr[randomIndex] = arr[high].also { arr[high] = arr[randomIndex] }
        return partition(arr, low, high)
    }
    
    /**
     * QuickSort with Median-of-Three Pivot Selection
     * 
     * Choose pivot as median of first, middle, and last elements.
     * This provides better pivot selection than random choice.
     * 
     * Algorithm:
     * 1. Find median of first, middle, and last elements
     * 2. Use median as pivot
     * 3. Partition around median
     * 
     * Time Complexity: O(n log n) average
     * Space Complexity: O(log n)
     */
    fun sortWithMedianOfThree(arr: IntArray) {
        quickSortMedianOfThree(arr, 0, arr.size - 1)
    }
    
    private fun quickSortMedianOfThree(arr: IntArray, low: Int, high: Int) {
        if (low < high) {
            val pivotIndex = medianOfThreePartition(arr, low, high)
            quickSortMedianOfThree(arr, low, pivotIndex - 1)
            quickSortMedianOfThree(arr, pivotIndex + 1, high)
        }
    }
    
    private fun medianOfThreePartition(arr: IntArray, low: Int, high: Int): Int {
        val mid = (low + high) / 2
        
        // Sort first, middle, and last elements
        if (arr[low] > arr[mid]) {
            arr[low] = arr[mid].also { arr[mid] = arr[low] }
        }
        if (arr[low] > arr[high]) {
            arr[low] = arr[high].also { arr[high] = arr[low] }
        }
        if (arr[mid] > arr[high]) {
            arr[mid] = arr[high].also { arr[high] = arr[mid] }
        }
        
        // Place median at high-1 position
        arr[mid] = arr[high - 1].also { arr[high - 1] = arr[mid] }
        
        // Use standard partition with pivot at high-1
        return partition(arr, low, high - 1)
    }
    
    /**
     * QuickSort with Tail Call Optimization
     * 
     * Optimized version that reduces recursion stack space
     * by eliminating one recursive call.
     * 
     * Algorithm:
     * 1. Always sort the smaller partition recursively
     * 2. Use iteration for the larger partition
     * 3. This reduces stack space to O(log n) in worst case
     * 
     * Time Complexity: O(n log n) average
     * Space Complexity: O(log n) - Improved worst case
     */
    fun sortOptimized(arr: IntArray) {
        quickSortOptimized(arr, 0, arr.size - 1)
    }
    
    private fun quickSortOptimized(arr: IntArray, low: Int, high: Int) {
        var currentLow = low
        var currentHigh = high
        
        while (currentLow < currentHigh) {
            val pivotIndex = partition(arr, currentLow, currentHigh)
            
            // Sort smaller partition recursively
            if (pivotIndex - currentLow < currentHigh - pivotIndex) {
                quickSortOptimized(arr, currentLow, pivotIndex - 1)
                currentLow = pivotIndex + 1
            } else {
                quickSortOptimized(arr, pivotIndex + 1, currentHigh)
                currentHigh = pivotIndex - 1
            }
        }
    }
    
    /**
     * QuickSort for Strings
     * 
     * Adapt QuickSort to work with string arrays.
     * 
     * Time Complexity: O(n log n) average
     * Space Complexity: O(log n)
     */
    fun sortStrings(arr: Array<String>) {
        quickSortStrings(arr, 0, arr.size - 1)
    }
    
    private fun quickSortStrings(arr: Array<String>, low: Int, high: Int) {
        if (low < high) {
            val pivotIndex = partitionStrings(arr, low, high)
            quickSortStrings(arr, low, pivotIndex - 1)
            quickSortStrings(arr, pivotIndex + 1, high)
        }
    }
    
    private fun partitionStrings(arr: Array<String>, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low - 1
        
        for (j in low until high) {
            if (arr[j] <= pivot) {
                i++
                arr[i] = arr[j].also { arr[j] = arr[i] }
            }
        }
        
        arr[i + 1] = arr[high].also { arr[high] = arr[i + 1] }
        return i + 1
    }
    
    /**
     * QuickSelect Algorithm
     * 
     * Find the k-th smallest element in unsorted array.
     * Uses QuickSort partitioning but only recurses on relevant partition.
     * 
     * Algorithm:
     * 1. Partition array around pivot
     * 2. If pivot is k-th element, return it
     * 3. If pivot > k-th element, search left partition
     * 4. If pivot < k-th element, search right partition
     * 
     * Time Complexity: O(n) average, O(n²) worst case
     * Space Complexity: O(1) average, O(n) worst case
     */
    fun quickSelect(arr: IntArray, k: Int): Int {
        return quickSelectHelper(arr, 0, arr.size - 1, k - 1)
    }
    
    private fun quickSelectHelper(arr: IntArray, low: Int, high: Int, k: Int): Int {
        if (low == high) return arr[low]
        
        val pivotIndex = partition(arr, low, high)
        
        return when {
            k == pivotIndex -> arr[k]
            k < pivotIndex -> quickSelectHelper(arr, low, pivotIndex - 1, k)
            else -> quickSelectHelper(arr, pivotIndex + 1, high, k)
        }
    }
} 