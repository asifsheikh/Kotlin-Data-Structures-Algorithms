package data_structure.arrays

/**
 * ARRAY ROTATION ALGORITHMS
 * 
 * Problem: Given an array and a number k, rotate the array by k positions.
 * 
 * There are two types of rotations:
 * 1. Left Rotation: Elements move to the left by k positions
 * 2. Right Rotation: Elements move to the right by k positions
 * 
 * Example:
 * Array: [1, 2, 3, 4, 5, 6, 7]
 * Left rotation by 2: [3, 4, 5, 6, 7, 1, 2]
 * Right rotation by 2: [6, 7, 1, 2, 3, 4, 5]
 * 
 * Intuition:
 * - We can solve this using the "Juggling Algorithm" or "Reversal Algorithm"
 * - The Reversal Algorithm is more efficient and easier to understand
 * - For left rotation: Reverse first k elements, reverse remaining elements, reverse entire array
 * - For right rotation: Reverse entire array, reverse first k elements, reverse remaining elements
 * - This approach works because reversing twice cancels out, and we get the desired rotation
 */

object ArrayRotation {
    
    /**
     * Left Rotation using Reversal Algorithm
     * 
     * Algorithm:
     * 1. Reverse the first k elements
     * 2. Reverse the remaining n-k elements  
     * 3. Reverse the entire array
     * 
     * Time Complexity: O(n) - We traverse the array at most 3 times
     * Space Complexity: O(1) - We only use a constant amount of extra space
     */
    fun rotateLeft(arr: IntArray, k: Int) {
        val n = arr.size
        if (n == 0) return
        
        val rotations = k % n
        if (rotations == 0) return
        
        // Reverse first k elements
        reverseRange(arr, 0, rotations - 1)
        // Reverse remaining elements
        reverseRange(arr, rotations, n - 1)
        // Reverse entire array
        reverseRange(arr, 0, n - 1)
    }
    
    /**
     * Right Rotation using Reversal Algorithm
     * 
     * Algorithm:
     * 1. Reverse the entire array
     * 2. Reverse the first k elements
     * 3. Reverse the remaining n-k elements
     * 
     * Time Complexity: O(n) - We traverse the array at most 3 times
     * Space Complexity: O(1) - We only use a constant amount of extra space
     */
    fun rotateRight(arr: IntArray, k: Int) {
        val n = arr.size
        if (n == 0) return
        
        val rotations = k % n
        if (rotations == 0) return
        
        // Reverse entire array
        reverseRange(arr, 0, n - 1)
        // Reverse first k elements
        reverseRange(arr, 0, rotations - 1)
        // Reverse remaining elements
        reverseRange(arr, rotations, n - 1)
    }
    
    /**
     * Left Rotation using Juggling Algorithm
     * 
     * Algorithm:
     * 1. Find GCD of array length and rotation count
     * 2. Move elements in sets equal to GCD
     * 3. Each set is moved by rotation count positions
     * 
     * Time Complexity: O(n) - Each element is moved exactly once
     * Space Complexity: O(1) - Constant extra space
     */
    fun rotateLeftJuggling(arr: IntArray, k: Int) {
        val n = arr.size
        if (n == 0) return
        
        val rotations = k % n
        if (rotations == 0) return
        
        val gcd = findGCD(n, rotations)
        
        for (i in 0 until gcd) {
            val temp = arr[i]
            var j = i
            
            while (true) {
                val next = (j + rotations) % n
                if (next == i) break
                arr[j] = arr[next]
                j = next
            }
            arr[j] = temp
        }
    }
    
    /**
     * Helper function to reverse array elements in a given range
     */
    private fun reverseRange(arr: IntArray, start: Int, end: Int) {
        var left = start
        var right = end
        while (left < right) {
            arr[left] = arr[right].also { arr[right] = arr[left] }
            left++
            right--
        }
    }
    
    /**
     * Helper function to find GCD of two numbers
     */
    private fun findGCD(a: Int, b: Int): Int {
        return if (b == 0) a else findGCD(b, a % b)
    }
    
    /**
     * Alternative approach: Using extra array
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n) - Requires extra array
     */
    fun rotateLeftWithExtraSpace(arr: IntArray, k: Int): IntArray {
        val n = arr.size
        if (n == 0) return arr
        
        val rotations = k % n
        if (rotations == 0) return arr.copyOf()
        
        val result = IntArray(n)
        for (i in 0 until n) {
            val newIndex = (i - rotations + n) % n
            result[newIndex] = arr[i]
        }
        return result
    }
    
    fun rotateRightWithExtraSpace(arr: IntArray, k: Int): IntArray {
        val n = arr.size
        if (n == 0) return arr
        
        val rotations = k % n
        if (rotations == 0) return arr.copyOf()
        
        val result = IntArray(n)
        for (i in 0 until n) {
            val newIndex = (i + rotations) % n
            result[newIndex] = arr[i]
        }
        return result
    }
} 