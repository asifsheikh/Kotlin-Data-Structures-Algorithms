package data_structure.arrays

/**
 * ARRAY VALIDATION ALGORITHMS
 * 
 * Problem: Validate various properties of arrays like sorting, duplicates, palindromes, etc.
 * 
 * Different validations:
 * 1. Check if array is sorted (ascending/descending)
 * 2. Check for duplicates
 * 3. Check if array is palindrome
 * 4. Check if array contains specific patterns
 * 5. Validate array properties
 * 
 * Example:
 * Array: [1, 2, 3, 4, 5]
 * Is sorted ascending: true
 * Is sorted descending: false
 * Has duplicates: false
 * Is palindrome: false
 * 
 * Array: [1, 2, 2, 1]
 * Is palindrome: true
 * Has duplicates: true
 * 
 * Intuition:
 * - For sorting: Compare adjacent elements
 * - For duplicates: Use hash set or array as hash table
 * - For palindrome: Use two pointers from ends
 * - For patterns: Use specific algorithms based on pattern
 */

object ArrayValidation {
    
    /**
     * Check if Array is Sorted (Ascending)
     * 
     * Problem: Determine if array is sorted in non-decreasing order
     * 
     * Algorithm:
     * 1. Traverse array from left to right
     * 2. Compare each element with next element
     * 3. If any element > next element, return false
     * 4. Return true if all comparisons pass
     * 
     * Time Complexity: O(n) - We check each element once
     * Space Complexity: O(1) - Constant extra space
     */
    fun isArraySorted(arr: IntArray): Boolean {
        for (i in 1 until arr.size) {
            if (arr[i] < arr[i - 1]) return false
        }
        return true
    }
    
    /**
     * Check if Array is Sorted (Descending)
     * 
     * Problem: Determine if array is sorted in non-increasing order
     * 
     * Algorithm:
     * 1. Traverse array from left to right
     * 2. Compare each element with next element
     * 3. If any element < next element, return false
     * 4. Return true if all comparisons pass
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun isArraySortedDescending(arr: IntArray): Boolean {
        for (i in 1 until arr.size) {
            if (arr[i] > arr[i - 1]) return false
        }
        return true
    }
    
    /**
     * Check if Array has Duplicates
     * 
     * Problem: Determine if array contains duplicate elements
     * 
     * Algorithm:
     * 1. Use hash set to track seen elements
     * 2. Traverse array and add elements to set
     * 3. If element already in set, return true
     * 4. Return false if no duplicates found
     * 
     * Time Complexity: O(n) - We check each element once
     * Space Complexity: O(n) - Hash set to store elements
     */
    fun hasDuplicates(arr: IntArray): Boolean {
        val seen = mutableSetOf<Int>()
        for (element in arr) {
            if (!seen.add(element)) return true
        }
        return false
    }
    
    /**
     * Check if Array has Duplicates (In-place for 1 to n)
     * 
     * Problem: Check for duplicates when array contains numbers 1 to n
     * 
     * Algorithm:
     * 1. Use array as hash table
     * 2. Mark visited numbers as negative
     * 3. If already negative, it's a duplicate
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1) - In-place modification
     */
    fun hasDuplicatesInPlace(arr: IntArray): Boolean {
        for (num in arr) {
            val index = abs(num) - 1
            if (arr[index] < 0) return true
            arr[index] = -arr[index]
        }
        return false
    }
    
    /**
     * Check if Array is Palindrome
     * 
     * Problem: Determine if array reads same forward and backward
     * 
     * Algorithm:
     * 1. Use two pointers: left (start) and right (end)
     * 2. Compare elements at both pointers
     * 3. Move left pointer right, right pointer left
     * 4. Return false if any mismatch, true if all match
     * 
     * Time Complexity: O(n) - We check half the array
     * Space Complexity: O(1) - Constant extra space
     */
    fun isArrayPalindrome(arr: IntArray): Boolean {
        var left = 0
        var right = arr.size - 1
        
        while (left < right) {
            if (arr[left] != arr[right]) return false
            left++
            right--
        }
        return true
    }
    
    /**
     * Check if Array is Monotonic
     * 
     * Problem: Determine if array is monotonic (either increasing or decreasing)
     * 
     * Algorithm:
     * 1. Determine direction by comparing first two different elements
     * 2. Check if rest of array follows that direction
     * 3. Handle edge cases (all same elements)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun isArrayMonotonic(arr: IntArray): Boolean {
        if (arr.size <= 2) return true
        
        var direction = 0 // 0: undetermined, 1: increasing, -1: decreasing
        
        for (i in 1 until arr.size) {
            when {
                arr[i] > arr[i - 1] -> {
                    if (direction == -1) return false
                    direction = 1
                }
                arr[i] < arr[i - 1] -> {
                    if (direction == 1) return false
                    direction = -1
                }
                // arr[i] == arr[i-1]: continue
            }
        }
        
        return true
    }
    
    /**
     * Check if Array Contains Consecutive Numbers
     * 
     * Problem: Determine if array contains consecutive integers
     * 
     * Algorithm:
     * 1. Find min and max values
     * 2. Check if max - min + 1 equals array size
     * 3. Check if all elements are unique
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) - For duplicate checking
     */
    fun isArrayConsecutive(arr: IntArray): Boolean {
        if (arr.isEmpty()) return true
        
        val min = arr.minOrNull()!!
        val max = arr.maxOrNull()!!
        
        // Check if range matches size
        if (max - min + 1 != arr.size) return false
        
        // Check for duplicates
        val seen = mutableSetOf<Int>()
        for (element in arr) {
            if (!seen.add(element)) return false
        }
        
        return true
    }
    
    /**
     * Check if Array is Mountain Array
     * 
     * Problem: Determine if array forms a mountain (increasing then decreasing)
     * 
     * Algorithm:
     * 1. Find peak (element greater than neighbors)
     * 2. Check if array is increasing before peak
     * 3. Check if array is decreasing after peak
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun isMountainArray(arr: IntArray): Boolean {
        if (arr.size < 3) return false
        
        var i = 0
        
        // Find peak (increasing part)
        while (i < arr.size - 1 && arr[i] < arr[i + 1]) {
            i++
        }
        
        // Check if peak exists and not at ends
        if (i == 0 || i == arr.size - 1) return false
        
        // Check decreasing part
        while (i < arr.size - 1 && arr[i] > arr[i + 1]) {
            i++
        }
        
        return i == arr.size - 1
    }
    
    /**
     * Check if Array Contains Valid Parentheses Pattern
     * 
     * Problem: Check if array represents valid parentheses (1 for '(', -1 for ')')
     * 
     * Algorithm:
     * 1. Use counter to track balance
     * 2. Increment for opening, decrement for closing
     * 3. Return false if counter goes negative
     * 4. Return true if counter is zero at end
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun isValidParenthesesPattern(arr: IntArray): Boolean {
        var balance = 0
        
        for (num in arr) {
            balance += num
            if (balance < 0) return false
        }
        
        return balance == 0
    }
    
    /**
     * Check if Array is Alternating
     * 
     * Problem: Check if array alternates between positive and negative numbers
     * 
     * Algorithm:
     * 1. Check if adjacent elements have opposite signs
     * 2. Handle zero as special case
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun isArrayAlternating(arr: IntArray): Boolean {
        for (i in 1 until arr.size) {
            val current = arr[i]
            val previous = arr[i - 1]
            
            // Check if signs are opposite (one positive, one negative)
            if ((current >= 0 && previous >= 0) || (current < 0 && previous < 0)) {
                return false
            }
        }
        return true
    }
    
    /**
     * Check if Array Contains Arithmetic Sequence
     * 
     * Problem: Check if array forms arithmetic sequence (constant difference)
     * 
     * Algorithm:
     * 1. Calculate common difference from first two elements
     * 2. Check if all adjacent pairs have same difference
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun isArithmeticSequence(arr: IntArray): Boolean {
        if (arr.size < 2) return true
        
        val difference = arr[1] - arr[0]
        
        for (i in 2 until arr.size) {
            if (arr[i] - arr[i - 1] != difference) {
                return false
            }
        }
        
        return true
    }
    
    /**
     * Check if Array Contains Geometric Sequence
     * 
     * Problem: Check if array forms geometric sequence (constant ratio)
     * 
     * Algorithm:
     * 1. Calculate common ratio from first two elements
     * 2. Check if all adjacent pairs have same ratio
     * 3. Handle zero division
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun isGeometricSequence(arr: IntArray): Boolean {
        if (arr.size < 2) return true
        
        if (arr[0] == 0) return false
        
        val ratio = arr[1].toDouble() / arr[0]
        
        for (i in 2 until arr.size) {
            if (arr[i - 1] == 0) return false
            if (arr[i].toDouble() / arr[i - 1] != ratio) {
                return false
            }
        }
        
        return true
    }
    
    /**
     * Check if Array is Valid for Binary Search
     * 
     * Problem: Check if array could be result of binary search traversal
     * 
     * Algorithm:
     * 1. Check if array is sorted
     * 2. For binary search, array must be sorted
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    fun isValidForBinarySearch(arr: IntArray): Boolean {
        return isArraySorted(arr)
    }
} 