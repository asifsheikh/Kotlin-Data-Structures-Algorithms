package algos.sorting

/**
 * COMPREHENSIVE SORTING ALGORITHMS REFERENCE
 * 
 * This file contains all essential sorting algorithms commonly asked in interviews:
 * - Comparison-based sorts (Bubble, Selection, Insertion, Merge, Quick, Heap)
 * - Non-comparison sorts (Counting, Radix, Bucket)
 * - Hybrid sorts (TimSort, IntroSort)
 * - Time/Space complexity analysis
 * - Stability and in-place properties
 */

fun sortingAlgorithms() {
    println("=== SORTING ALGORITHMS ===\n")
    
    // ===== COMPARISON-BASED SORTS =====
    println("1. COMPARISON-BASED SORTS")
    println("-------------------------")
    
    // Bubble Sort - O(n²) time, O(1) space, stable
    fun bubbleSort(arr: IntArray) {
        val n = arr.size
        for (i in 0 until n - 1) {
            var swapped = false
            for (j in 0 until n - 1 - i) {
                if (arr[j] > arr[j + 1]) {
                    arr[j] = arr[j + 1].also { arr[j + 1] = arr[j] }
                    swapped = true
                }
            }
            if (!swapped) break // Optimized: exit if no swaps
        }
    }
    
    // Selection Sort - O(n²) time, O(1) space, not stable
    fun selectionSort(arr: IntArray) {
        val n = arr.size
        for (i in 0 until n - 1) {
            var minIdx = i
            for (j in i + 1 until n) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j
                }
            }
            if (minIdx != i) {
                arr[i] = arr[minIdx].also { arr[minIdx] = arr[i] }
            }
        }
    }
    
    // Insertion Sort - O(n²) time, O(1) space, stable
    fun insertionSort(arr: IntArray) {
        for (i in 1 until arr.size) {
            val key = arr[i]
            var j = i - 1
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]
                j--
            }
            arr[j + 1] = key
        }
    }
    
    // Merge Sort - O(n log n) time, O(n) space, stable
    fun mergeSort(arr: IntArray) {
        fun merge(left: IntArray, right: IntArray): IntArray {
            val result = IntArray(left.size + right.size)
            var i = 0
            var j = 0
            var k = 0
            
            while (i < left.size && j < right.size) {
                if (left[i] <= right[j]) {
                    result[k++] = left[i++]
                } else {
                    result[k++] = right[j++]
                }
            }
            
            while (i < left.size) result[k++] = left[i++]
            while (j < right.size) result[k++] = right[j++]
            
            return result
        }
        
        fun sort(arr: IntArray): IntArray {
            if (arr.size <= 1) return arr
            
            val mid = arr.size / 2
            val left = sort(arr.sliceArray(0 until mid))
            val right = sort(arr.sliceArray(mid until arr.size))
            
            return merge(left, right)
        }
        
        val sorted = sort(arr)
        for (i in arr.indices) {
            arr[i] = sorted[i]
        }
    }
    
    // Quick Sort - O(n log n) average, O(n²) worst, O(log n) space, not stable
    fun quickSort(arr: IntArray) {
        fun partition(low: Int, high: Int): Int {
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
        
        fun sort(low: Int, high: Int) {
            if (low < high) {
                val pi = partition(low, high)
                sort(low, pi - 1)
                sort(pi + 1, high)
            }
        }
        
        sort(0, arr.size - 1)
    }
    
    // Heap Sort - O(n log n) time, O(1) space, not stable
    fun heapSort(arr: IntArray) {
        fun heapify(n: Int, i: Int) {
            var largest = i
            val left = 2 * i + 1
            val right = 2 * i + 2
            
            if (left < n && arr[left] > arr[largest]) {
                largest = left
            }
            
            if (right < n && arr[right] > arr[largest]) {
                largest = right
            }
            
            if (largest != i) {
                arr[i] = arr[largest].also { arr[largest] = arr[i] }
                heapify(n, largest)
            }
        }
        
        // Build max heap
        for (i in arr.size / 2 - 1 downTo 0) {
            heapify(arr.size, i)
        }
        
        // Extract elements from heap
        for (i in arr.size - 1 downTo 0) {
            arr[0] = arr[i].also { arr[i] = arr[0] }
            heapify(i, 0)
        }
    }
    
    // ===== NON-COMPARISON SORTS =====
    println("\n2. NON-COMPARISON SORTS")
    println("------------------------")
    
    // Counting Sort - O(n + k) time, O(k) space, stable
    fun countingSort(arr: IntArray) {
        if (arr.isEmpty()) return
        
        val max = arr.maxOrNull()!!
        val min = arr.minOrNull()!!
        val range = max - min + 1
        
        val count = IntArray(range)
        val output = IntArray(arr.size)
        
        // Count occurrences
        for (num in arr) {
            count[num - min]++
        }
        
        // Modify count to store positions
        for (i in 1 until range) {
            count[i] += count[i - 1]
        }
        
        // Build output array
        for (i in arr.size - 1 downTo 0) {
            output[count[arr[i] - min] - 1] = arr[i]
            count[arr[i] - min]--
        }
        
        // Copy back to original array
        for (i in arr.indices) {
            arr[i] = output[i]
        }
    }
    
    // Radix Sort - O(d * (n + k)) time, O(n + k) space, stable
    fun radixSort(arr: IntArray) {
        fun countingSortByDigit(arr: IntArray, exp: Int) {
            val n = arr.size
            val output = IntArray(n)
            val count = IntArray(10)
            
            // Count occurrences
            for (i in 0 until n) {
                count[(arr[i] / exp) % 10]++
            }
            
            // Modify count to store positions
            for (i in 1..9) {
                count[i] += count[i - 1]
            }
            
            // Build output array
            for (i in n - 1 downTo 0) {
                val digit = (arr[i] / exp) % 10
                output[count[digit] - 1] = arr[i]
                count[digit]--
            }
            
            // Copy back
            for (i in 0 until n) {
                arr[i] = output[i]
            }
        }
        
        val max = arr.maxOrNull() ?: return
        
        var exp = 1
        while (max / exp > 0) {
            countingSortByDigit(arr, exp)
            exp *= 10
        }
    }
    
    // ===== HYBRID SORTS =====
    println("\n3. HYBRID SORTS")
    println("---------------")
    
    // Helper function for TimSort
    fun mergeRuns(arr: IntArray, left: Int, mid: Int, right: Int) {
        val leftArr = arr.sliceArray(left until mid)
        val rightArr = arr.sliceArray(mid until right)
        
        var i = 0
        var j = 0
        var k = left
        
        while (i < leftArr.size && j < rightArr.size) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++]
            } else {
                arr[k++] = rightArr[j++]
            }
        }
        
        while (i < leftArr.size) arr[k++] = leftArr[i++]
        while (j < rightArr.size) arr[k++] = rightArr[j++]
    }
    
    // TimSort-inspired (simplified version)
    fun timSort(arr: IntArray) {
        val minRun = 32
        
        // Sort individual runs using insertion sort
        for (i in 0 until arr.size step minRun) {
            val end = minOf(i + minRun, arr.size)
            insertionSort(arr.sliceArray(i until end))
        }
        
        // Merge runs
        var size = minRun
        while (size < arr.size) {
            for (left in 0 until arr.size step 2 * size) {
                val mid = minOf(left + size, arr.size)
                val right = minOf(left + 2 * size, arr.size)
                
                if (mid < right) {
                    mergeRuns(arr, left, mid, right)
                }
            }
            size *= 2
        }
    }
    
    // ===== SORTING ALGORITHMS COMPARISON =====
    println("\n4. ALGORITHM COMPARISON")
    println("----------------------")
    
    val testArray = intArrayOf(64, 34, 25, 12, 22, 11, 90)
    println("Original array: ${testArray.contentToString()}")
    
    // Test different sorting algorithms
    val bubbleArray = testArray.clone()
    bubbleSort(bubbleArray)
    println("Bubble Sort: ${bubbleArray.contentToString()}")
    
    val selectionArray = testArray.clone()
    selectionSort(selectionArray)
    println("Selection Sort: ${selectionArray.contentToString()}")
    
    val insertionArray = testArray.clone()
    insertionSort(insertionArray)
    println("Insertion Sort: ${insertionArray.contentToString()}")
    
    val mergeArray = testArray.clone()
    mergeSort(mergeArray)
    println("Merge Sort: ${mergeArray.contentToString()}")
    
    val quickArray = testArray.clone()
    quickSort(quickArray)
    println("Quick Sort: ${quickArray.contentToString()}")
    
    val heapArray = testArray.clone()
    heapSort(heapArray)
    println("Heap Sort: ${heapArray.contentToString()}")
    
    val countingArray = testArray.clone()
    countingSort(countingArray)
    println("Counting Sort: ${countingArray.contentToString()}")
    
    val radixArray = testArray.clone()
    radixSort(radixArray)
    println("Radix Sort: ${radixArray.contentToString()}")
    
    val timArray = testArray.clone()
    timSort(timArray)
    println("Tim Sort: ${timArray.contentToString()}")
    
    // ===== SORTING PROBLEMS =====
    println("\n5. COMMON SORTING PROBLEMS")
    println("--------------------------")
    
    // Helper function for quick select
    fun partitionArray(nums: IntArray, left: Int, right: Int): Int {
        val pivot = nums[right]
        var i = left
        
        for (j in left until right) {
            if (nums[j] <= pivot) {
                nums[i] = nums[j].also { nums[j] = nums[i] }
                i++
            }
        }
        nums[i] = nums[right].also { nums[right] = nums[i] }
        return i
    }
    
    // Kth Largest Element
    fun findKthLargest(nums: IntArray, k: Int): Int {
        fun quickSelect(left: Int, right: Int, kSmallest: Int): Int {
            if (left == right) return nums[left]
            
            val pivotIndex = partitionArray(nums, left, right)
            
            return when {
                kSmallest == pivotIndex -> nums[kSmallest]
                kSmallest < pivotIndex -> quickSelect(left, pivotIndex - 1, kSmallest)
                else -> quickSelect(pivotIndex + 1, right, kSmallest)
            }
        }
        
        return quickSelect(0, nums.size - 1, nums.size - k)
    }
    
    val kthArray = intArrayOf(3, 2, 1, 5, 6, 4)
    println("2nd largest in ${kthArray.contentToString()}: ${findKthLargest(kthArray, 2)}")
    
    // Merge K Sorted Arrays
    fun mergeKSortedArrays(arrays: List<IntArray>): IntArray {
        val minHeap = java.util.PriorityQueue<Triple<Int, Int, Int>> { a, b -> a.first.compareTo(b.first) }
        
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
            
            if (elementIndex + 1 < arrays[arrayIndex].size) {
                minHeap.add(Triple(arrays[arrayIndex][elementIndex + 1], arrayIndex, elementIndex + 1))
            }
        }
        
        return result.toIntArray()
    }
    
    val arrays = listOf(
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(3, 6, 9)
    )
    println("Merged K sorted arrays: ${mergeKSortedArrays(arrays).contentToString()}")
    
    // ===== QUICK REFERENCE SUMMARY =====
    println("\n6. QUICK REFERENCE")
    println("------------------")
    println("• Bubble Sort: O(n²) time, O(1) space, stable")
    println("• Selection Sort: O(n²) time, O(1) space, not stable")
    println("• Insertion Sort: O(n²) time, O(1) space, stable")
    println("• Merge Sort: O(n log n) time, O(n) space, stable")
    println("• Quick Sort: O(n log n) avg, O(n²) worst, O(log n) space, not stable")
    println("• Heap Sort: O(n log n) time, O(1) space, not stable")
    println("• Counting Sort: O(n + k) time, O(k) space, stable")
    println("• Radix Sort: O(d * (n + k)) time, O(n + k) space, stable")
    println("• Tim Sort: O(n log n) time, O(n) space, stable")
    println("• In-place: Bubble, Selection, Insertion, Quick, Heap")
    println("• Stable: Bubble, Insertion, Merge, Counting, Radix, Tim")
    println("• Best for small arrays: Insertion Sort")
    println("• Best for large arrays: Quick Sort, Merge Sort")
    println("• Best for integers with known range: Counting/Radix Sort")
}

// ===== CUSTOM COMPARATOR EXAMPLES =====
class CustomSortingExamples {
    // Sort by multiple criteria
    data class Person(val name: String, val age: Int, val city: String)
    
    fun sortPeople(people: List<Person>): List<Person> {
        return people.sortedWith(compareBy<Person> { it.city }
            .thenBy { it.age }
            .thenBy { it.name })
    }
    
    // Sort strings by length, then lexicographically
    fun sortStringsByLength(strings: List<String>): List<String> {
        return strings.sortedWith(compareBy<String> { it.length }.thenBy { it })
    }
    
    // Sort array in custom order
    fun sortByCustomOrder(arr: IntArray, order: List<Int>): IntArray {
        val orderMap = order.withIndex().associate { it.value to it.index }
        return arr.sortedBy { orderMap[it] ?: Int.MAX_VALUE }.toIntArray()
    }
} 