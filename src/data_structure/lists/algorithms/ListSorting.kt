package data_structure.lists.algorithms

/**
 * LIST SORTING ALGORITHMS
 * 
 * Problem: Sort a list of elements in ascending or descending order.
 * 
 * Examples:
 * Input: [64, 34, 25, 12, 22, 11, 90] → Output: [11, 12, 22, 25, 34, 64, 90]
 * Input: ["banana", "apple", "cherry"] → Output: ["apple", "banana", "cherry"]
 * Input: [3, 1, 4, 1, 5, 9, 2, 6] → Output: [1, 1, 2, 3, 4, 5, 6, 9]
 * 
 * Intuition: Use efficient sorting algorithms with different trade-offs
 * 
 * Time Complexity: O(n log n) for comparison-based sorts
 * Space Complexity: O(1) to O(n) depending on algorithm
 */

object ListSorting {
    
    /**
     * QuickSort implementation for lists
     */
    fun quickSort(nums: MutableList<Int>, low: Int = 0, high: Int = nums.size - 1) {
        if (low < high) {
            val pi = partition(nums, low, high)
            quickSort(nums, low, pi - 1)
            quickSort(nums, pi + 1, high)
        }
    }
    
    private fun partition(nums: MutableList<Int>, low: Int, high: Int): Int {
        val pivot = nums[high]
        var i = low - 1
        
        for (j in low until high) {
            if (nums[j] <= pivot) {
                i++
                nums[i] = nums[j].also { nums[j] = nums[i] }
            }
        }
        
        nums[i + 1] = nums[high].also { nums[high] = nums[i + 1] }
        return i + 1
    }
    
    /**
     * MergeSort implementation for lists
     */
    fun mergeSort(nums: List<Int>): List<Int> {
        if (nums.size <= 1) return nums
        
        val mid = nums.size / 2
        val left = mergeSort(nums.take(mid))
        val right = mergeSort(nums.drop(mid))
        
        return merge(left, right)
    }
    
    private fun merge(left: List<Int>, right: List<Int>): List<Int> {
        val result = mutableListOf<Int>()
        var i = 0
        var j = 0
        
        while (i < left.size && j < right.size) {
            if (left[i] <= right[j]) {
                result.add(left[i])
                i++
            } else {
                result.add(right[j])
                j++
            }
        }
        
        result.addAll(left.drop(i))
        result.addAll(right.drop(j))
        
        return result
    }
    
    /**
     * BubbleSort implementation
     */
    fun bubbleSort(nums: MutableList<Int>) {
        val n = nums.size
        for (i in 0 until n - 1) {
            for (j in 0 until n - i - 1) {
                if (nums[j] > nums[j + 1]) {
                    nums[j] = nums[j + 1].also { nums[j + 1] = nums[j] }
                }
            }
        }
    }
    
    /**
     * SelectionSort implementation
     */
    fun selectionSort(nums: MutableList<Int>) {
        val n = nums.size
        for (i in 0 until n - 1) {
            var minIndex = i
            for (j in i + 1 until n) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j
                }
            }
            if (minIndex != i) {
                nums[i] = nums[minIndex].also { nums[minIndex] = nums[i] }
            }
        }
    }
    
    /**
     * InsertionSort implementation
     */
    fun insertionSort(nums: MutableList<Int>) {
        for (i in 1 until nums.size) {
            val key = nums[i]
            var j = i - 1
            
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j]
                j--
            }
            nums[j + 1] = key
        }
    }
    
    /**
     * HeapSort implementation
     */
    fun heapSort(nums: MutableList<Int>) {
        val n = nums.size
        
        // Build max heap
        for (i in n / 2 - 1 downTo 0) {
            heapify(nums, n, i)
        }
        
        // Extract elements from heap one by one
        for (i in n - 1 downTo 0) {
            nums[0] = nums[i].also { nums[i] = nums[0] }
            heapify(nums, i, 0)
        }
    }
    
    private fun heapify(nums: MutableList<Int>, n: Int, i: Int) {
        var largest = i
        val left = 2 * i + 1
        val right = 2 * i + 2
        
        if (left < n && nums[left] > nums[largest]) {
            largest = left
        }
        
        if (right < n && nums[right] > nums[largest]) {
            largest = right
        }
        
        if (largest != i) {
            nums[i] = nums[largest].also { nums[largest] = nums[i] }
            heapify(nums, n, largest)
        }
    }
    
    /**
     * CountingSort for integers with known range
     */
    fun countingSort(nums: List<Int>, range: Int): List<Int> {
        val count = IntArray(range + 1)
        val output = MutableList(nums.size) { 0 }
        
        // Count occurrences
        for (num in nums) {
            count[num]++
        }
        
        // Calculate positions
        for (i in 1..range) {
            count[i] += count[i - 1]
        }
        
        // Build output array
        for (i in nums.size - 1 downTo 0) {
            output[count[nums[i]] - 1] = nums[i]
            count[nums[i]]--
        }
        
        return output
    }
    
    /**
     * RadixSort for integers
     */
    fun radixSort(nums: MutableList<Int>) {
        val max = nums.maxOrNull() ?: return
        
        var exp = 1
        while (max / exp > 0) {
            countingSortByDigit(nums, exp)
            exp *= 10
        }
    }
    
    private fun countingSortByDigit(nums: MutableList<Int>, exp: Int) {
        val n = nums.size
        val output = MutableList(n) { 0 }
        val count = IntArray(10)
        
        // Count occurrences
        for (num in nums) {
            count[(num / exp) % 10]++
        }
        
        // Calculate positions
        for (i in 1..9) {
            count[i] += count[i - 1]
        }
        
        // Build output array
        for (i in n - 1 downTo 0) {
            val digit = (nums[i] / exp) % 10
            output[count[digit] - 1] = nums[i]
            count[digit]--
        }
        
        // Copy back to original array
        for (i in 0 until n) {
            nums[i] = output[i]
        }
    }
    
    /**
     * Sort with custom comparator
     */
    fun sortWithComparator(nums: MutableList<Int>, comparator: (Int, Int) -> Int) {
        nums.sortWith { a, b -> comparator(a, b) }
    }
    
    /**
     * Sort by multiple criteria
     */
    fun sortByMultipleCriteria(nums: MutableList<Pair<Int, String>>) {
        nums.sortWith(compareBy<Pair<Int, String>> { it.first }.thenBy { it.second })
    }
    
    /**
     * Partial sort - sort only first k elements
     */
    fun partialSort(nums: MutableList<Int>, k: Int) {
        for (i in 0 until minOf(k, nums.size - 1)) {
            var minIndex = i
            for (j in i + 1 until nums.size) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j
                }
            }
            if (minIndex != i) {
                nums[i] = nums[minIndex].also { nums[minIndex] = nums[i] }
            }
        }
    }
} 