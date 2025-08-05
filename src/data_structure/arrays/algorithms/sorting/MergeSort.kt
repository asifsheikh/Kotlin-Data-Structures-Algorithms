package data_structure.arrays.algorithms.sorting

/**
 * MERGESORT ALGORITHM
 * 
 * Problem: Sort an array of elements using divide-and-conquer strategy.
 * 
 * Given an unsorted array of n elements, rearrange them so that they are
 * in ascending order using the merge sort algorithm.
 * 
 * Example:
 * Input: [64, 34, 25, 12, 22, 11, 90]
 * Output: [11, 12, 22, 25, 34, 64, 90]
 * 
 * Intuition:
 * - Use divide-and-conquer strategy
 * - Divide array into two halves recursively
 * - Sort each half independently
 * - Merge the sorted halves back together
 * - Base case: arrays of size 0 or 1 are already sorted
 * 
 * Key Features:
 * - Stable sorting algorithm (preserves relative order of equal elements)
 * - Predictable performance (always O(n log n))
 * - Good for linked lists and external sorting
 * - Not in-place (requires extra space)
 */

object MergeSort {
    
    /**
     * Basic MergeSort Implementation
     * 
     * Algorithm:
     * 1. Divide array into two halves
     * 2. Recursively sort left and right halves
     * 3. Merge the sorted halves
     * 4. Base case: arrays of size 0 or 1
     * 
     * Time Complexity: O(n log n) - Always the same
     * Space Complexity: O(n) - Extra array for merging
     * 
     * Best Case: O(n log n)
     * Average Case: O(n log n)
     * Worst Case: O(n log n)
     */
    fun sort(arr: IntArray) {
        val temp = IntArray(arr.size)
        mergeSort(arr, temp, 0, arr.size - 1)
    }
    
    private fun mergeSort(arr: IntArray, temp: IntArray, left: Int, right: Int) {
        if (left < right) {
            val mid = (left + right) / 2
            
            // Sort left and right halves
            mergeSort(arr, temp, left, mid)
            mergeSort(arr, temp, mid + 1, right)
            
            // Merge the sorted halves
            merge(arr, temp, left, mid, right)
        }
    }
    
    /**
     * Merge Function
     * 
     * Algorithm:
     * 1. Copy elements to temporary array
     * 2. Use three pointers: i (left), j (right), k (original array)
     * 3. Compare elements from both halves and merge in sorted order
     * 4. Copy remaining elements from either half
     * 
     * Time Complexity: O(n) - Single pass through both halves
     * Space Complexity: O(n) - Temporary array
     */
    private fun merge(arr: IntArray, temp: IntArray, left: Int, mid: Int, right: Int) {
        var i = left      // Index for left subarray
        var j = mid + 1   // Index for right subarray
        var k = left      // Index for merged array
        
        // Copy elements to temporary array
        for (x in left..right) {
            temp[x] = arr[x]
        }
        
        // Merge the two halves
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i]
                i++
            } else {
                arr[k] = temp[j]
                j++
            }
            k++
        }
        
        // Copy remaining elements from left half
        while (i <= mid) {
            arr[k] = temp[i]
            i++
            k++
        }
        
        // Copy remaining elements from right half
        while (j <= right) {
            arr[k] = temp[j]
            j++
            k++
        }
    }
    
    /**
     * MergeSort with In-Place Merge
     * 
     * Attempt to reduce space complexity by merging in-place.
     * Note: This is more complex and may not be as efficient.
     * 
     * Time Complexity: O(n log n)
     * Space Complexity: O(1) - In-place but with higher constant factors
     */
    fun sortInPlace(arr: IntArray) {
        mergeSortInPlace(arr, 0, arr.size - 1)
    }
    
    private fun mergeSortInPlace(arr: IntArray, left: Int, right: Int) {
        if (left < right) {
            val mid = (left + right) / 2
            
            mergeSortInPlace(arr, left, mid)
            mergeSortInPlace(arr, mid + 1, right)
            
            mergeInPlace(arr, left, mid, right)
        }
    }
    
    private fun mergeInPlace(arr: IntArray, left: Int, mid: Int, right: Int) {
        var i = left
        var j = mid + 1
        
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                i++
            } else {
                // Rotate the right element to correct position
                val value = arr[j]
                for (k in j downTo i + 1) {
                    arr[k] = arr[k - 1]
                }
                arr[i] = value
                i++
                mid++
                j++
            }
        }
    }
    
    /**
     * Bottom-Up MergeSort (Iterative)
     * 
     * Iterative implementation that avoids recursion.
     * 
     * Algorithm:
     * 1. Start with subarrays of size 1 (already sorted)
     * 2. Merge adjacent subarrays of size 1 to get size 2
     * 3. Merge adjacent subarrays of size 2 to get size 4
     * 4. Continue until entire array is sorted
     * 
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     */
    fun sortBottomUp(arr: IntArray) {
        val n = arr.size
        val temp = IntArray(n)
        
        // Start with subarrays of size 1, then 2, 4, 8, ...
        var size = 1
        while (size < n) {
            var left = 0
            while (left < n - size) {
                val mid = left + size - 1
                val right = minOf(left + 2 * size - 1, n - 1)
                
                merge(arr, temp, left, mid, right)
                left += 2 * size
            }
            size *= 2
        }
    }
    
    /**
     * MergeSort for Linked Lists
     * 
     * MergeSort is particularly efficient for linked lists
     * because it doesn't require random access.
     * 
     * Time Complexity: O(n log n)
     * Space Complexity: O(log n) - Recursion stack only
     */
    fun sortLinkedList(head: ListNode?): ListNode? {
        if (head?.next == null) return head
        
        // Find middle using slow/fast pointer
        var slow = head
        var fast = head.next
        
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        
        val mid = slow?.next
        slow?.next = null
        
        // Recursively sort both halves
        val left = sortLinkedList(head)
        val right = sortLinkedList(mid)
        
        // Merge sorted lists
        return mergeLinkedLists(left, right)
    }
    
    private fun mergeLinkedLists(left: ListNode?, right: ListNode?): ListNode? {
        val dummy = ListNode(0)
        var current = dummy
        var l = left
        var r = right
        
        while (l != null && r != null) {
            if (l.value <= r.value) {
                current.next = l
                l = l.next
            } else {
                current.next = r
                r = r.next
            }
            current = current.next!!
        }
        
        // Attach remaining elements
        current.next = l ?: r
        
        return dummy.next
    }
    
    /**
     * MergeSort with Custom Comparator
     * 
     * Sort array using custom comparison function.
     * 
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     */
    fun sortWithComparator(arr: IntArray, comparator: (Int, Int) -> Int) {
        val temp = IntArray(arr.size)
        mergeSortWithComparator(arr, temp, 0, arr.size - 1, comparator)
    }
    
    private fun mergeSortWithComparator(
        arr: IntArray, 
        temp: IntArray, 
        left: Int, 
        right: Int, 
        comparator: (Int, Int) -> Int
    ) {
        if (left < right) {
            val mid = (left + right) / 2
            
            mergeSortWithComparator(arr, temp, left, mid, comparator)
            mergeSortWithComparator(arr, temp, mid + 1, right, comparator)
            
            mergeWithComparator(arr, temp, left, mid, right, comparator)
        }
    }
    
    private fun mergeWithComparator(
        arr: IntArray, 
        temp: IntArray, 
        left: Int, 
        mid: Int, 
        right: Int, 
        comparator: (Int, Int) -> Int
    ) {
        var i = left
        var j = mid + 1
        var k = left
        
        // Copy to temp array
        for (x in left..right) {
            temp[x] = arr[x]
        }
        
        // Merge using comparator
        while (i <= mid && j <= right) {
            if (comparator(temp[i], temp[j]) <= 0) {
                arr[k] = temp[i]
                i++
            } else {
                arr[k] = temp[j]
                j++
            }
            k++
        }
        
        // Copy remaining elements
        while (i <= mid) {
            arr[k] = temp[i]
            i++
            k++
        }
        
        while (j <= right) {
            arr[k] = temp[j]
            j++
            k++
        }
    }
    
    /**
     * Count Inversions using MergeSort
     * 
     * Count the number of inversions in an array.
     * An inversion is a pair (i, j) where i < j and arr[i] > arr[j].
     * 
     * Algorithm:
     * 1. Use MergeSort but count inversions during merge
     * 2. When merging, if element from right half is smaller,
     *    it forms inversions with all remaining elements in left half
     * 
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     */
    fun countInversions(arr: IntArray): Long {
        val temp = IntArray(arr.size)
        return mergeSortWithInversionCount(arr, temp, 0, arr.size - 1)
    }
    
    private fun mergeSortWithInversionCount(arr: IntArray, temp: IntArray, left: Int, right: Int): Long {
        var inversionCount = 0L
        
        if (left < right) {
            val mid = (left + right) / 2
            
            inversionCount += mergeSortWithInversionCount(arr, temp, left, mid)
            inversionCount += mergeSortWithInversionCount(arr, temp, mid + 1, right)
            inversionCount += mergeWithInversionCount(arr, temp, left, mid, right)
        }
        
        return inversionCount
    }
    
    private fun mergeWithInversionCount(arr: IntArray, temp: IntArray, left: Int, mid: Int, right: Int): Long {
        var i = left
        var j = mid + 1
        var k = left
        var inversionCount = 0L
        
        // Copy to temp array
        for (x in left..right) {
            temp[x] = arr[x]
        }
        
        // Merge and count inversions
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i]
                i++
            } else {
                arr[k] = temp[j]
                // Count inversions: all remaining elements in left half
                inversionCount += (mid - i + 1).toLong()
                j++
            }
            k++
        }
        
        // Copy remaining elements
        while (i <= mid) {
            arr[k] = temp[i]
            i++
            k++
        }
        
        while (j <= right) {
            arr[k] = temp[j]
            j++
            k++
        }
        
        return inversionCount
    }
}

// Helper class for linked list implementation
data class ListNode(val value: Int, var next: ListNode? = null) 