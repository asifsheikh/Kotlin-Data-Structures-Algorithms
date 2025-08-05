package data_structure.arrays

/**
 * ARRAY TRANSFORMATION ALGORITHMS
 * 
 * Problem: Transform arrays in various ways like mapping, filtering, reshaping, etc.
 * 
 * Different transformations:
 * 1. Map: Apply function to each element
 * 2. Filter: Keep elements that satisfy condition
 * 3. Flatten: Convert 2D array to 1D
 * 4. Reshape: Change dimensions of array
 * 5. Concatenate: Join multiple arrays
 * 6. Split: Divide array into parts
 * 
 * Example:
 * Array: [1, 2, 3, 4, 5]
 * Map (square): [1, 4, 9, 16, 25]
 * Filter (even): [2, 4]
 * 
 * 2D Array: [[1, 2], [3, 4], [5, 6]]
 * Flatten: [1, 2, 3, 4, 5, 6]
 * Reshape (2x3): [[1, 2, 3], [4, 5, 6]]
 * 
 * Intuition:
 * - Map: Create new array with transformed values
 * - Filter: Create new array with selected values
 * - Flatten: Concatenate all sub-arrays
 * - Reshape: Reorganize elements in new dimensions
 * - Use mathematical formulas for index mapping
 */

object ArrayTransformation {
    
    /**
     * Map Array
     * 
     * Problem: Apply a transformation function to each element of array
     * 
     * Algorithm:
     * 1. Create new array of same size
     * 2. Apply transform function to each element
     * 3. Store result in new array
     * 
     * Time Complexity: O(n) - We process each element once
     * Space Complexity: O(n) - We create new array
     */
    fun mapArray(arr: IntArray, transform: (Int) -> Int): IntArray {
        return IntArray(arr.size) { transform(arr[it]) }
    }
    
    /**
     * Filter Array
     * 
     * Problem: Keep only elements that satisfy given condition
     * 
     * Algorithm:
     * 1. Create list to store filtered elements
     * 2. Check each element with predicate
     * 3. Add element if condition is true
     * 4. Convert list to array
     * 
     * Time Complexity: O(n) - We check each element once
     * Space Complexity: O(n) - Worst case, all elements pass filter
     */
    fun filterArray(arr: IntArray, predicate: (Int) -> Boolean): IntArray {
        return arr.filter(predicate).toIntArray()
    }
    
    /**
     * Flatten 2D Array
     * 
     * Problem: Convert 2D array (matrix) to 1D array
     * 
     * Algorithm:
     * 1. Calculate total size from all sub-arrays
     * 2. Create 1D array of that size
     * 3. Copy elements from each sub-array sequentially
     * 
     * Time Complexity: O(n*m) - n rows, m columns
     * Space Complexity: O(n*m) - New flattened array
     */
    fun flatten2DArray(matrix: Array<IntArray>): IntArray {
        return matrix.flatMap { it.toList() }.toIntArray()
    }
    
    /**
     * Flatten 2D Array (Manual)
     * 
     * Time Complexity: O(n*m)
     * Space Complexity: O(n*m)
     */
    fun flatten2DArrayManual(matrix: Array<IntArray>): IntArray {
        val totalSize = matrix.sumOf { it.size }
        val result = IntArray(totalSize)
        var index = 0
        
        for (row in matrix) {
            for (element in row) {
                result[index++] = element
            }
        }
        
        return result
    }
    
    /**
     * Reshape Array
     * 
     * Problem: Change dimensions of array from 1D to 2D
     * 
     * Algorithm:
     * 1. Check if reshape is possible (size must match)
     * 2. Create 2D array with new dimensions
     * 3. Map 1D indices to 2D indices using formulas:
     *    - row = index / cols
     *    - col = index % cols
     * 
     * Time Complexity: O(n) - We process each element once
     * Space Complexity: O(n) - New 2D array
     */
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
    
    /**
     * Concatenate Arrays
     * 
     * Problem: Join multiple arrays into single array
     * 
     * Algorithm:
     * 1. Calculate total size of all arrays
     * 2. Create result array of that size
     * 3. Copy elements from each array sequentially
     * 
     * Time Complexity: O(total size of all arrays)
     * Space Complexity: O(total size of all arrays)
     */
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
    
    /**
     * Concatenate 2D Arrays
     * 
     * Problem: Join multiple 2D arrays vertically
     * 
     * Algorithm:
     * 1. Calculate total rows and max columns
     * 2. Create result matrix with new dimensions
     * 3. Copy each row from input matrices
     * 
     * Time Complexity: O(total elements in all matrices)
     * Space Complexity: O(total elements in result matrix)
     */
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
    
    /**
     * Split Array
     * 
     * Problem: Divide array into two parts at given index
     * 
     * Algorithm:
     * 1. Create two arrays: left (0 to index-1) and right (index to end)
     * 2. Copy elements to respective arrays
     * 
     * Time Complexity: O(n) - We copy all elements
     * Space Complexity: O(n) - Two new arrays
     */
    fun splitArray(arr: IntArray, index: Int): Pair<IntArray, IntArray> {
        val left = arr.take(index).toIntArray()
        val right = arr.drop(index).toIntArray()
        return Pair(left, right)
    }
    
    /**
     * Split Array by Value
     * 
     * Problem: Split array into multiple parts at occurrences of given value
     * 
     * Algorithm:
     * 1. Traverse array and collect elements
     * 2. When delimiter is found, create new sub-array
     * 3. Continue until end of array
     * 
     * Time Complexity: O(n) - We process each element once
     * Space Complexity: O(n) - List of sub-arrays
     */
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
    
    /**
     * Transpose Matrix
     * 
     * Problem: Convert rows to columns and columns to rows
     * 
     * Algorithm:
     * 1. Create new matrix with swapped dimensions
     * 2. Copy elements with swapped indices: result[i][j] = matrix[j][i]
     * 
     * Time Complexity: O(n*m) - n rows, m columns
     * Space Complexity: O(n*m) - New transposed matrix
     */
    fun transposeMatrix(matrix: Array<IntArray>): Array<IntArray> {
        val rows = matrix.size
        val cols = matrix[0].size
        
        val result = Array(cols) { IntArray(rows) }
        
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                result[j][i] = matrix[i][j]
            }
        }
        
        return result
    }
    
    /**
     * Rotate Matrix 90 Degrees Clockwise
     * 
     * Problem: Rotate matrix by 90 degrees clockwise
     * 
     * Algorithm:
     * 1. Transpose the matrix
     * 2. Reverse each row
     * 
     * Time Complexity: O(n²) - n x n matrix
     * Space Complexity: O(n²) - New rotated matrix
     */
    fun rotateMatrix90Clockwise(matrix: Array<IntArray>): Array<IntArray> {
        val transposed = transposeMatrix(matrix)
        
        // Reverse each row
        for (row in transposed) {
            var left = 0
            var right = row.size - 1
            while (left < right) {
                row[left] = row[right].also { row[right] = row[left] }
                left++
                right--
            }
        }
        
        return transposed
    }
    
    /**
     * Rotate Matrix 90 Degrees Counter-clockwise
     * 
     * Algorithm:
     * 1. Transpose the matrix
     * 2. Reverse each column (or reverse the entire matrix)
     * 
     * Time Complexity: O(n²)
     * Space Complexity: O(n²)
     */
    fun rotateMatrix90CounterClockwise(matrix: Array<IntArray>): Array<IntArray> {
        val transposed = transposeMatrix(matrix)
        
        // Reverse the entire matrix (equivalent to reversing each column)
        val rows = transposed.size
        val cols = transposed[0].size
        
        for (i in 0 until rows / 2) {
            for (j in 0 until cols) {
                transposed[i][j] = transposed[rows - 1 - i][j].also { 
                    transposed[rows - 1 - i][j] = transposed[i][j] 
                }
            }
        }
        
        return transposed
    }
    
    /**
     * Convert 1D Array to 2D Array (Column-wise)
     * 
     * Problem: Convert 1D array to 2D array filling column by column
     * 
     * Algorithm:
     * 1. Create 2D array with given dimensions
     * 2. Map 1D index to 2D indices: row = index % rows, col = index / rows
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun reshapeArrayColumnWise(arr: IntArray, rows: Int, cols: Int): Array<IntArray>? {
        if (arr.size != rows * cols) return null
        
        val matrix = Array(rows) { IntArray(cols) }
        for (i in arr.indices) {
            val row = i % rows
            val col = i / rows
            matrix[row][col] = arr[i]
        }
        return matrix
    }
} 