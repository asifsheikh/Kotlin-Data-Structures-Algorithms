package data_structure.lists

/**
 * LIST ALGORITHMS
 * 
 * This file contains algorithms that use lists including
 * list manipulation, list operations, and other list-based algorithms.
 * 
 * COMMON LIST ALGORITHMS:
 * - List Manipulation: Transform and process lists
 * - List Operations: Common operations on lists
 * - List Analysis: Analyze list properties
 * - List Generation: Generate special lists
 */

object ListAlgorithms {
    
    /**
     * PROBLEM: Remove Duplicates from Sorted List
     * 
     * Given a sorted list, remove all duplicates such that each element appears only once.
     * 
     * INPUT: A sorted list of integers
     * OUTPUT: List with duplicates removed
     * 
     * EXAMPLES:
     * Input: [1, 1, 2]
     * Output: [1, 2]
     * 
     * Input: [1, 1, 2, 3, 3]
     * Output: [1, 2, 3]
     * 
     * Input: [1, 2, 3, 4, 5]
     * Output: [1, 2, 3, 4, 5] (no duplicates)
     * 
     * Input: [1, 1, 1, 1]
     * Output: [1]
     * 
     * INTUITION:
     * - Use two pointers approach
     * - One pointer for reading, one for writing
     * - Skip duplicates by advancing read pointer
     * - Write unique elements to write position
     * 
     * TIME COMPLEXITY: O(n) - Single pass through list
     * SPACE COMPLEXITY: O(1) - In-place modification
     * 
     * @param nums Sorted list of integers
     * @return List with duplicates removed
     */
    fun removeDuplicates(nums: MutableList<Int>): Int {
        if (nums.isEmpty()) return 0
        
        var writeIndex = 1
        
        for (i in 1 until nums.size) {
            if (nums[i] != nums[i - 1]) {
                nums[writeIndex] = nums[i]
                writeIndex++
            }
        }
        
        return writeIndex
    }
    
    /**
     * PROBLEM: Rotate List
     * 
     * Given a list, rotate it to the right by k places.
     * 
     * INPUT: List of integers and rotation count k
     * OUTPUT: Rotated list
     * 
     * EXAMPLES:
     * Input: [1, 2, 3, 4, 5], k = 2
     * Output: [4, 5, 1, 2, 3]
     * 
     * Input: [0, 1, 2], k = 4
     * Output: [2, 0, 1] (k % n = 1, so rotate by 1)
     * 
     * Input: [1, 2, 3], k = 0
     * Output: [1, 2, 3] (no rotation)
     * 
     * INTUITION:
     * - Handle case where k > list size using modulo
     * - Reverse entire list
     * - Reverse first k elements
     * - Reverse remaining elements
     * - This gives the desired rotation
     * 
     * TIME COMPLEXITY: O(n) - Three passes through list
     * SPACE COMPLEXITY: O(1) - In-place modification
     * 
     * @param nums List to rotate
     * @param k Number of rotations
     */
    fun rotate(nums: MutableList<Int>, k: Int) {
        val n = nums.size
        if (n == 0) return
        
        val rotations = k % n
        if (rotations == 0) return
        
        // Reverse entire list
        reverse(nums, 0, n - 1)
        
        // Reverse first k elements
        reverse(nums, 0, rotations - 1)
        
        // Reverse remaining elements
        reverse(nums, rotations, n - 1)
    }
    
    private fun reverse(nums: MutableList<Int>, start: Int, end: Int) {
        var left = start
        var right = end
        
        while (left < right) {
            nums[left] = nums[right].also { nums[right] = nums[left] }
            left++
            right--
        }
    }
    
    /**
     * PROBLEM: Merge Sorted Lists
     * 
     * Given two sorted lists, merge them into a single sorted list.
     * 
     * INPUT: Two sorted lists of integers
     * OUTPUT: Merged sorted list
     * 
     * EXAMPLES:
     * Input: [1, 2, 4], [1, 3, 4]
     * Output: [1, 1, 2, 3, 4, 4]
     * 
     * Input: [1, 3, 5], [2, 4, 6]
     * Output: [1, 2, 3, 4, 5, 6]
     * 
     * Input: [], [1, 2, 3]
     * Output: [1, 2, 3]
     * 
     * Input: [1, 2, 3], []
     * Output: [1, 2, 3]
     * 
     * INTUITION:
     * - Use two pointers, one for each list
     * - Compare elements at both pointers
     * - Add smaller element to result
     * - Advance pointer of list from which element was taken
     * - Continue until one list is exhausted
     * - Add remaining elements from other list
     * 
     * TIME COMPLEXITY: O(m + n) - m and n are list lengths
     * SPACE COMPLEXITY: O(m + n) - New list to store result
     * 
     * @param list1 First sorted list
     * @param list2 Second sorted list
     * @return Merged sorted list
     */
    fun mergeSortedLists(list1: List<Int>, list2: List<Int>): List<Int> {
        val result = mutableListOf<Int>()
        var i = 0
        var j = 0
        
        while (i < list1.size && j < list2.size) {
            if (list1[i] <= list2[j]) {
                result.add(list1[i])
                i++
            } else {
                result.add(list2[j])
                j++
            }
        }
        
        // Add remaining elements from list1
        while (i < list1.size) {
            result.add(list1[i])
            i++
        }
        
        // Add remaining elements from list2
        while (j < list2.size) {
            result.add(list2[j])
            j++
        }
        
        return result
    }
    
    /**
     * PROBLEM: Find Peak Element
     * 
     * Given a list where adjacent elements are different, find a peak element.
     * A peak element is greater than its neighbors.
     * 
     * INPUT: List of integers where nums[i] != nums[i+1] for all i
     * OUTPUT: Index of a peak element
     * 
     * EXAMPLES:
     * Input: [1, 2, 3, 1]
     * Output: 2 (element 3 is peak)
     * 
     * Input: [1, 2, 1, 3, 5, 6, 4]
     * Output: 1 or 5 (both 2 and 6 are peaks)
     * 
     * Input: [1]
     * Output: 0 (single element is peak)
     * 
     * Input: [1, 2]
     * Output: 1 (last element is peak)
     * 
     * INTUITION:
     * - Use binary search approach
     * - Compare middle element with its neighbors
     * - If middle is peak, return it
     * - If left neighbor is greater, search left half
     * - If right neighbor is greater, search right half
     * - This works because there's always a peak in a list
     * 
     * TIME COMPLEXITY: O(log n) - Binary search
     * SPACE COMPLEXITY: O(1) - Only constant extra space
     * 
     * @param nums List of integers
     * @return Index of a peak element
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
     * PROBLEM: Majority Element
     * 
     * Given a list, find the majority element (appears more than n/2 times).
     * 
     * INPUT: List of integers
     * OUTPUT: Majority element
     * 
     * EXAMPLES:
     * Input: [3, 2, 3]
     * Output: 3
     * 
     * Input: [2, 2, 1, 1, 1, 2, 2]
     * Output: 2
     * 
     * Input: [1]
     * Output: 1
     * 
     * INTUITION:
     * - Use Boyer-Moore Voting Algorithm
     * - Initialize candidate as first element
     * - For each element, if it equals candidate, increment count
     * - If it doesn't equal candidate, decrement count
     * - If count becomes 0, set new candidate
     * - Final candidate is majority element
     * 
     * TIME COMPLEXITY: O(n) - Single pass through list
     * SPACE COMPLEXITY: O(1) - Only constant extra space
     * 
     * @param nums List of integers
     * @return Majority element
     */
    fun majorityElement(nums: List<Int>): Int {
        var candidate = nums[0]
        var count = 1
        
        for (i in 1 until nums.size) {
            if (count == 0) {
                candidate = nums[i]
                count = 1
            } else if (nums[i] == candidate) {
                count++
            } else {
                count--
            }
        }
        
        return candidate
    }
    
    /**
     * PROBLEM: Missing Number
     * 
     * Given a list containing n distinct numbers from 0 to n, find the missing number.
     * 
     * INPUT: List of n distinct numbers from 0 to n (missing one)
     * OUTPUT: The missing number
     * 
     * EXAMPLES:
     * Input: [3, 0, 1]
     * Output: 2 (numbers are 0,1,2,3, missing 2)
     * 
     * Input: [0, 1]
     * Output: 2 (numbers are 0,1,2, missing 2)
     * 
     * Input: [9, 6, 4, 2, 3, 5, 7, 0, 1]
     * Output: 8 (numbers 0-9, missing 8)
     * 
     * INTUITION:
     * - Use XOR operation: a ^ a = 0, a ^ 0 = a
     * - XOR all numbers from 0 to n with all numbers in list
     * - Result will be the missing number
     * - Alternative: use sum formula n*(n+1)/2 - list sum
     * 
     * TIME COMPLEXITY: O(n) - Single pass through list
     * SPACE COMPLEXITY: O(1) - Only constant extra space
     * 
     * @param nums List with missing number
     * @return Missing number
     */
    private fun missingNumber(nums: List<Int>): Int {

        var xorAll = 0
        val n = nums.size

        for (i in 0..n) {
            xorAll = xorAll xor i
        }
        for (num in nums) {
            xorAll = xorAll xor num
        }

        return xorAll
    }


    /**
     * PROBLEM: Move Zeroes
     * 
     * Given a list, move all zeroes to the end while maintaining the relative order
     * of non-zero elements.
     * 
     * INPUT: List of integers
     * OUTPUT: List with zeroes moved to the end (modify in-place)
     * 
     * EXAMPLES:
     * Input: [0, 1, 0, 3, 12]
     * Output: [1, 3, 12, 0, 0]
     * 
     * Input: [1, 2, 3, 4, 5]
     * Output: [1, 2, 3, 4, 5] (no zeroes)
     * 
     * Input: [0, 0, 0, 1, 2]
     * Output: [1, 2, 0, 0, 0]
     * 
     * INTUITION:
     * - Use two pointers: one for writing position, one for reading
     * - Write pointer points to next position for non-zero element
     * - Read pointer scans through list
     * - When non-zero element found, swap with write position and increment write pointer
     * - Fill remaining positions with zeroes
     * 
     * TIME COMPLEXITY: O(n) - Single pass through list
     * SPACE COMPLEXITY: O(1) - In-place modification
     * 
     * @param nums List to modify
     */
    fun moveZeroes(nums: MutableList<Int>) {
        var writeIndex = 0
        
        // Move all non-zero elements to front
        for (i in nums.indices) {
            if (nums[i] != 0) {
                nums[writeIndex] = nums[i]
                writeIndex++
            }
        }
        
        // Fill remaining positions with zeroes
        while (writeIndex < nums.size) {
            nums[writeIndex] = 0
            writeIndex++
        }
    }
    
    /**
     * PROBLEM: Find All Numbers Disappeared in Array
     * 
     * Given a list of integers where 1 ≤ nums[i] ≤ n (n = size of list),
     * find all the integers in [1, n] that do not appear in the list.
     * 
     * INPUT: List of integers from 1 to n
     * OUTPUT: List of missing numbers
     * 
     * EXAMPLES:
     * Input: [4, 3, 2, 7, 8, 2, 3, 1]
     * Output: [5, 6] (numbers 5 and 6 are missing)
     * 
     * Input: [1, 1]
     * Output: [2] (number 2 is missing)
     * 
     * Input: [1, 2, 3, 4]
     * Output: [] (no missing numbers)
     * 
     * INTUITION:
     * - Use the list itself as a hash set
     * - For each number, mark its corresponding index as negative
     * - After marking, positive indices indicate missing numbers
     * - Collect all positive indices + 1 as missing numbers
     * 
     * TIME COMPLEXITY: O(n) - Two passes through list
     * SPACE COMPLEXITY: O(1) - Only output list (not counting input)
     * 
     * @param nums List of integers
     * @return List of missing numbers
     */
    fun findDisappearedNumbers(nums: MutableList<Int>): List<Int> {
        val result = mutableListOf<Int>()
        
        // Mark numbers as negative
        for (num in nums) {
            val index = Math.abs(num) - 1
            if (nums[index] > 0) {
                nums[index] = -nums[index]
            }
        }
        
        // Find positive indices (missing numbers)
        for (i in nums.indices) {
            if (nums[i] > 0) {
                result.add(i + 1)
            }
        }
        
        return result
    }
    
    /**
     * PROBLEM: Maximum Subarray Sum
     * 
     * Given a list of integers, find the contiguous sublist with the largest sum.
     * 
     * INPUT: List of integers (can contain negative numbers)
     * OUTPUT: Maximum sum of any contiguous sublist
     * 
     * EXAMPLES:
     * Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
     * Output: 6 (sublist [4, -1, 2, 1] has sum 6)
     * 
     * Input: [1, 2, 3, 4, 5]
     * Output: 15 (entire list has sum 15)
     * 
     * Input: [-1, -2, -3, -4]
     * Output: -1 (single element -1)
     * 
     * INTUITION:
     * - Use Kadane's algorithm
     * - Keep track of current sum and maximum sum seen so far
     * - For each element, decide whether to extend current sublist or start new one
     * - If current sum becomes negative, start fresh (negative sum won't help)
     * - Update maximum sum whenever current sum exceeds it
     * 
     * TIME COMPLEXITY: O(n) - Single pass through list
     * SPACE COMPLEXITY: O(1) - Only constant extra space
     * 
     * @param nums List of integers
     * @return Maximum sublist sum
     */
    fun maxSubArray(nums: List<Int>): Int {
        if (nums.isEmpty()) return 0
        
        var currentSum = nums[0]
        var maxSum = nums[0]
        
        for (i in 1 until nums.size) {
            currentSum = maxOf(nums[i], currentSum + nums[i])
            maxSum = maxOf(maxSum, currentSum)
        }
        
        return maxSum
    }
    
    /**
     * Demonstrates list algorithms
     */
    fun demonstrateListAlgorithms() {
        println("=== LIST ALGORITHMS DEMONSTRATION ===\n")
        
        // 1. Remove Duplicates
        println("1. REMOVE DUPLICATES")
        val duplicatesList = mutableListOf(1, 1, 2, 3, 3, 4, 4, 5)
        println("Original: $duplicatesList")
        val newLength = removeDuplicates(duplicatesList)
        println("After removing duplicates: ${duplicatesList.take(newLength)}")
        println()
        
        // 2. Rotate List
        println("2. ROTATE LIST")
        val rotateList = mutableListOf(1, 2, 3, 4, 5)
        println("Original: $rotateList")
        rotate(rotateList, 2)
        println("After rotating by 2: $rotateList")
        println()
        
        // 3. Merge Sorted Lists
        println("3. MERGE SORTED LISTS")
        val list1 = listOf(1, 3, 5, 7)
        val list2 = listOf(2, 4, 6, 8)
        val merged = mergeSortedLists(list1, list2)
        println("List 1: $list1")
        println("List 2: $list2")
        println("Merged: $merged")
        println()
        
        // 4. Find Peak Element
        println("4. FIND PEAK ELEMENT")
        val peakList = listOf(1, 2, 3, 1)
        val peakIndex = findPeakElement(peakList)
        println("List: $peakList")
        println("Peak element index: $peakIndex (value: ${peakList[peakIndex]})")
        println()
        
        // 5. Majority Element
        println("5. MAJORITY ELEMENT")
        val majorityList = listOf(2, 2, 1, 1, 1, 2, 2)
        val majority = majorityElement(majorityList)
        println("List: $majorityList")
        println("Majority element: $majority")
        println()
        
        // 6. Missing Number
        println("6. MISSING NUMBER")
        val missingList = listOf(3, 0, 1)
        val missing = missingNumber(missingList)
        println("List: $missingList")
        println("Missing number: $missing")
        println()
        
        // 7. Move Zeroes
        println("7. MOVE ZEROES")
        val zeroesList = mutableListOf(0, 1, 0, 3, 12)
        println("Original: $zeroesList")
        moveZeroes(zeroesList)
        println("After moving zeroes: $zeroesList")
        println()
        
        // 8. Find Disappeared Numbers
        println("8. FIND DISAPPEARED NUMBERS")
        val disappearedList = mutableListOf(4, 3, 2, 7, 8, 2, 3, 1)
        val disappeared = findDisappearedNumbers(disappearedList)
        println("List: $disappearedList")
        println("Disappeared numbers: $disappeared")
        println()
        
        // 9. Maximum Subarray Sum
        println("9. MAXIMUM SUBARRAY SUM")
        val maxSubList = listOf(-2, 1, -3, 4, -1, 2, 1, -5, 4)
        val maxSubSum = maxSubArray(maxSubList)
        println("List: $maxSubList")
        println("Maximum subarray sum: $maxSubSum")
        println()
        
        println("=== LIST ALGORITHMS DEMONSTRATION COMPLETE ===\n")
    }
} 