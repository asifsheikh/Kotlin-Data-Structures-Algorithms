package data_structure.arrays

/**
 * ARRAY OPERATIONS
 * 
 * This file contains array operations and manipulations:
 * - Array access and modification
 * - Array manipulation operations
 * - Array transformation utilities
 */

object ArrayOperations {
    
    // ===== ARRAY ACCESS OPERATIONS =====
    fun getElement(arr: IntArray, index: Int): Int? {
        return if (index in arr.indices) arr[index] else null
    }
    
    fun getFirstElement(arr: IntArray): Int? {
        return arr.firstOrNull()
    }
    
    fun getLastElement(arr: IntArray): Int? {
        return arr.lastOrNull()
    }
    
    fun getElementsInRange(arr: IntArray, start: Int, end: Int): IntArray {
        val startIndex = maxOf(0, start)
        val endIndex = minOf(arr.size, end + 1)
        return if (startIndex < endIndex) arr.slice(startIndex until endIndex).toIntArray() else intArrayOf()
    }
    
    // ===== ARRAY MODIFICATION OPERATIONS =====
    fun setElement(arr: IntArray, index: Int, value: Int): Boolean {
        return if (index in arr.indices) {
            arr[index] = value
            true
        } else false
    }
    
    fun swapElements(arr: IntArray, i: Int, j: Int): Boolean {
        return if (i in arr.indices && j in arr.indices) {
            arr[i] = arr[j].also { arr[j] = arr[i] }
            true
        } else false
    }
    
    fun reverseArray(arr: IntArray) {
        var left = 0
        var right = arr.size - 1
        while (left < right) {
            swapElements(arr, left, right)
            left++
            right--
        }
    }
    
    fun reverseArrayInRange(arr: IntArray, start: Int, end: Int) {
        var left = maxOf(0, start)
        var right = minOf(arr.size - 1, end)
        while (left < right) {
            swapElements(arr, left, right)
            left++
            right--
        }
    }
    
    // ===== ARRAY MANIPULATION =====
    fun rotateArrayLeft(arr: IntArray, k: Int) {
        val n = arr.size
        if (n == 0) return
        
        val rotations = k % n
        if (rotations == 0) return
        
        reverseArrayInRange(arr, 0, rotations - 1)
        reverseArrayInRange(arr, rotations, n - 1)
        reverseArray(arr)
    }
    
    fun rotateArrayRight(arr: IntArray, k: Int) {
        val n = arr.size
        if (n == 0) return
        
        val rotations = k % n
        if (rotations == 0) return
        
        reverseArray(arr)
        reverseArrayInRange(arr, 0, rotations - 1)
        reverseArrayInRange(arr, rotations, n - 1)
    }
    
    fun shiftArrayLeft(arr: IntArray, k: Int): IntArray {
        val n = arr.size
        if (n == 0) return arr
        
        val shift = k % n
        if (shift == 0) return arr.copyOf()
        
        val result = IntArray(n)
        for (i in 0 until n) {
            val newIndex = (i - shift + n) % n
            result[newIndex] = arr[i]
        }
        return result
    }
    
    fun shiftArrayRight(arr: IntArray, k: Int): IntArray {
        val n = arr.size
        if (n == 0) return arr
        
        val shift = k % n
        if (shift == 0) return arr.copyOf()
        
        val result = IntArray(n)
        for (i in 0 until n) {
            val newIndex = (i + shift) % n
            result[newIndex] = arr[i]
        }
        return result
    }
    
    // ===== ARRAY TRANSFORMATION =====
    fun mapArray(arr: IntArray, transform: (Int) -> Int): IntArray {
        return IntArray(arr.size) { transform(arr[it]) }
    }
    
    fun filterArray(arr: IntArray, predicate: (Int) -> Boolean): IntArray {
        return arr.filter(predicate).toIntArray()
    }
    
    fun flatten2DArray(matrix: Array<IntArray>): IntArray {
        return matrix.flatMap { it.toList() }.toIntArray()
    }
    
    fun reshapeArray(arr: IntArray, rows: Int, cols: Int): Array<IntArray>? {
        if (arr.size != rows * cols) return null
        
        val matrix = Array(rows) { IntArray(cols) }
        for (i in arr.indices) {
            val row = i / cols
            val col = i % cols
            matrix[row][col] = arr[i]
        }
        return matrix
    }
    
    // ===== ARRAY CONCATENATION =====
    fun concatenateArrays(vararg arrays: IntArray): IntArray {
        val totalSize = arrays.sumOf { it.size }
        val result = IntArray(totalSize)
        var index = 0
        
        for (array in arrays) {
            array.copyInto(result, index)
            index += array.size
        }
        
        return result
    }
    
    fun concatenate2DArrays(vararg matrices: Array<IntArray>): Array<IntArray> {
        val totalRows = matrices.sumOf { it.size }
        val maxCols = matrices.maxOfOrNull { it.maxOfOrNull { row -> row.size } ?: 0 } ?: 0
        
        val result = Array(totalRows) { IntArray(maxCols) }
        var rowIndex = 0
        
        for (matrix in matrices) {
            for (row in matrix) {
                row.copyInto(result[rowIndex], 0, 0, minOf(row.size, maxCols))
                rowIndex++
            }
        }
        
        return result
    }
    
    // ===== ARRAY SPLITTING =====
    fun splitArray(arr: IntArray, index: Int): Pair<IntArray, IntArray> {
        val left = arr.take(index).toIntArray()
        val right = arr.drop(index).toIntArray()
        return Pair(left, right)
    }
    
    fun splitArrayByValue(arr: IntArray, value: Int): List<IntArray> {
        val result = mutableListOf<IntArray>()
        var current = mutableListOf<Int>()
        
        for (element in arr) {
            if (element == value) {
                if (current.isNotEmpty()) {
                    result.add(current.toIntArray())
                    current.clear()
                }
            } else {
                current.add(element)
            }
        }
        
        if (current.isNotEmpty()) {
            result.add(current.toIntArray())
        }
        
        return result
    }
    
    // ===== ARRAY FILLING =====
    fun fillArray(arr: IntArray, value: Int) {
        arr.fill(value)
    }
    
    fun fillArrayInRange(arr: IntArray, start: Int, end: Int, value: Int) {
        val startIndex = maxOf(0, start)
        val endIndex = minOf(arr.size, end + 1)
        for (i in startIndex until endIndex) {
            arr[i] = value
        }
    }
    
    fun fillArrayWithFunction(arr: IntArray, function: (Int) -> Int) {
        for (i in arr.indices) {
            arr[i] = function(i)
        }
    }
    
    // ===== ARRAY COPYING =====
    fun copyArrayRange(arr: IntArray, start: Int, end: Int): IntArray {
        val startIndex = maxOf(0, start)
        val endIndex = minOf(arr.size, end + 1)
        return if (startIndex < endIndex) arr.slice(startIndex until endIndex).toIntArray() else intArrayOf()
    }
    
    fun copyArrayWithPadding(arr: IntArray, newSize: Int, paddingValue: Int = 0): IntArray {
        val result = IntArray(newSize) { paddingValue }
        arr.copyInto(result, 0, 0, minOf(arr.size, newSize))
        return result
    }
    
    // ===== ARRAY VALIDATION =====
    fun isArraySorted(arr: IntArray): Boolean {
        for (i in 1 until arr.size) {
            if (arr[i] < arr[i - 1]) return false
        }
        return true
    }
    
    fun isArraySortedDescending(arr: IntArray): Boolean {
        for (i in 1 until arr.size) {
            if (arr[i] > arr[i - 1]) return false
        }
        return true
    }
    
    fun hasDuplicates(arr: IntArray): Boolean {
        val seen = mutableSetOf<Int>()
        for (element in arr) {
            if (!seen.add(element)) return true
        }
        return false
    }
    
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
    
    // ===== ARRAY UTILITIES =====
    fun removeElement(arr: IntArray, value: Int): IntArray {
        return arr.filter { it != value }.toIntArray()
    }
    
    fun removeElementAt(arr: IntArray, index: Int): IntArray {
        return if (index in arr.indices) {
            arr.take(index).plus(arr.drop(index + 1)).toIntArray()
        } else {
            arr.copyOf()
        }
    }
    
    fun insertElement(arr: IntArray, index: Int, value: Int): IntArray {
        return if (index in 0..arr.size) {
            arr.take(index).plus(value).plus(arr.drop(index)).toIntArray()
        } else {
            arr.copyOf()
        }
    }
    
    fun replaceElement(arr: IntArray, oldValue: Int, newValue: Int): IntArray {
        return arr.map { if (it == oldValue) newValue else it }.toIntArray()
    }
    
    // ===== ARRAY STATISTICS =====
    fun countOccurrences(arr: IntArray, value: Int): Int {
        return arr.count { it == value }
    }
    
    fun findMostFrequent(arr: IntArray): Int? {
        if (arr.isEmpty()) return null
        
        val frequency = mutableMapOf<Int, Int>()
        for (element in arr) {
            frequency[element] = frequency.getOrDefault(element, 0) + 1
        }
        
        return frequency.maxByOrNull { it.value }?.key
    }
    
    fun findUniqueElements(arr: IntArray): IntArray {
        return arr.toSet().toIntArray()
    }
    
    fun findMissingNumbers(arr: IntArray, min: Int, max: Int): IntArray {
        val present = arr.toSet()
        return (min..max).filter { it !in present }.toIntArray()
    }
}

