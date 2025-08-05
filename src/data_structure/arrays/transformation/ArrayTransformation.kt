package data_structure.arrays.transformation

/**
 * ARRAY TRANSFORMATION ALGORITHMS
 *
 * Problem: Transform arrays using various operations like mapping, filtering, reshaping, and flattening.
 *
 * Different transformations:
 * 1. Map elements using a transformation function
 * 2. Filter elements based on conditions
 * 3. Flatten 2D arrays to 1D
 * 4. Reshape arrays to different dimensions
 * 5. Concatenate multiple arrays
 * 6. Split arrays into parts
 *
 * Example:
 * Array: [1, 2, 3, 4, 5]
 * Map (x * 2): [2, 4, 6, 8, 10]
 * Filter (x > 3): [4, 5]
 * 2D Array: [[1, 2], [3, 4], [5, 6]]
 * Flatten: [1, 2, 3, 4, 5, 6]
 *
 * Intuition:
 * - Use functional programming concepts
 * - Handle edge cases (empty arrays, invalid dimensions)
 * - Optimize for space and time complexity
 * - Consider in-place vs new array creation
 */

object ArrayTransformation {
    
    /**
     * Map Array Elements
     *
     * Problem: Apply a transformation function to each element of an array.
     *
     * Algorithm:
     * 1. Create new array of same size
     * 2. Apply transform function to each element
     * 3. Store result in new array
     *
     * Time Complexity: O(n) - We process each element once
     * Space Complexity: O(n) - New array for result
     */
    fun mapArray(arr: IntArray, transform: (Int) -> Int): IntArray {
        return IntArray(arr.size) { transform(arr[it]) }
    }
    
    /**
     * Map Array with Index
     *
     * Problem: Apply transformation function that has access to element index.
     *
     * Algorithm:
     * 1. Create new array of same size
     * 2. Apply transform function with element and index
     * 3. Store result in new array
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun mapArrayWithIndex(arr: IntArray, transform: (Int, Int) -> Int): IntArray {
        return IntArray(arr.size) { transform(arr[it], it) }
    }
    
    /**
     * Filter Array Elements
     *
     * Problem: Create new array containing only elements that satisfy a condition.
     *
     * Algorithm:
     * 1. Traverse array and check each element
     * 2. Collect elements that satisfy predicate
     * 3. Return filtered array
     *
     * Time Complexity: O(n) - We check each element once
     * Space Complexity: O(k) - k elements satisfy condition
     */
    fun filterArray(arr: IntArray, predicate: (Int) -> Boolean): IntArray {
        return arr.filter(predicate).toIntArray()
    }
    
    /**
     * Filter Array with Index
     *
     * Problem: Filter elements based on condition that has access to index.
     *
     * Algorithm:
     * 1. Traverse array with index
     * 2. Check predicate with element and index
     * 3. Collect matching elements
     *
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     */
    fun filterArrayWithIndex(arr: IntArray, predicate: (Int, Int) -> Boolean): IntArray {
        return arr.filterIndexed(predicate).toIntArray()
    }
    
    /**
     * Flatten 2D Array
     *
     * Problem: Convert 2D array (matrix) to 1D array.
     *
     * Algorithm:
     * 1. Calculate total size from all rows
     * 2. Traverse each row and copy elements
     * 3. Return flattened array
     *
     * Time Complexity: O(rows * cols) - We process each element once
     * Space Complexity: O(rows * cols) - New array for result
     */
    fun flatten2DArray(matrix: Array<IntArray>): IntArray {
        return matrix.flatMap { it.toList() }.toIntArray()
    }
    
    /**
     * Flatten 2D Array (Row-wise)
     *
     * Problem: Flatten 2D array by concatenating rows.
     *
     * Algorithm:
     * 1. Calculate total size
     * 2. Copy elements row by row
     * 3. Handle rows of different lengths
     *
     * Time Complexity: O(rows * cols)
     * Space Complexity: O(rows * cols)
     */
    fun flatten2DArrayRowWise(matrix: Array<IntArray>): IntArray {
        val totalSize = matrix.sumOf { it.size }
        val result = IntArray(totalSize)
        var index = 0
        
        for (row in matrix) {
            row.copyInto(result, index)
            index += row.size
        }
        
        return result
    }
    
    /**
     * Flatten 2D Array (Column-wise)
     *
     * Problem: Flatten 2D array by concatenating columns.
     *
     * Algorithm:
     * 1. Find maximum column length
     * 2. Copy elements column by column
     * 3. Handle rows of different lengths
     *
     * Time Complexity: O(rows * cols)
     * Space Complexity: O(rows * cols)
     */
    fun flatten2DArrayColumnWise(matrix: Array<IntArray>): IntArray {
        if (matrix.isEmpty()) return intArrayOf()
        
        val maxCols = matrix.maxOfOrNull { it.size } ?: 0
        val result = mutableListOf<Int>()
        
        for (col in 0 until maxCols) {
            for (row in matrix) {
                if (col < row.size) {
                    result.add(row[col])
                }
            }
        }
        
        return result.toIntArray()
    }
    
    /**
     * Reshape Array
     *
     * Problem: Reshape 1D array to 2D array with specified dimensions.
     *
     * Algorithm:
     * 1. Check if total elements match (rows * cols)
     * 2. Create 2D array with specified dimensions
     * 3. Copy elements row by row
     *
     * Time Complexity: O(n) - We copy each element once
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
     * Reshape Array (Column-wise)
     *
     * Problem: Reshape array by filling columns first.
     *
     * Algorithm:
     * 1. Check if dimensions are valid
     * 2. Fill matrix column by column
     * 3. Handle remaining elements
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
    
    /**
     * Concatenate Arrays
     *
     * Problem: Combine multiple arrays into a single array.
     *
     * Algorithm:
     * 1. Calculate total size from all arrays
     * 2. Create result array of total size
     * 3. Copy elements from each array sequentially
     *
     * Time Complexity: O(total size) - We copy each element once
     * Space Complexity: O(total size) - New array for result
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
     * Problem: Combine multiple 2D arrays into a single 2D array.
     *
     * Algorithm:
     * 1. Calculate total rows and maximum columns
     * 2. Create result matrix
     * 3. Copy rows from each matrix
     *
     * Time Complexity: O(total rows * max cols)
     * Space Complexity: O(total rows * max cols)
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
     * Problem: Split array into two parts at a specific index.
     *
     * Algorithm:
     * 1. Validate split index
     * 2. Create two arrays: left and right
     * 3. Copy elements to respective arrays
     *
     * Time Complexity: O(n) - We copy each element once
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
     * Problem: Split array into multiple parts using a delimiter value.
     *
     * Algorithm:
     * 1. Traverse array and collect elements
     * 2. When delimiter is found, create new part
     * 3. Handle consecutive delimiters
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
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
     * Split Array into Chunks
     *
     * Problem: Split array into chunks of specified size.
     *
     * Algorithm:
     * 1. Process array in chunks of chunk size
     * 2. Create subarray for each chunk
     * 3. Handle last chunk if smaller than chunk size
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    fun splitArrayIntoChunks(arr: IntArray, chunkSize: Int): List<IntArray> {
        val result = mutableListOf<IntArray>()
        
        for (i in 0 until arr.size step chunkSize) {
            val end = minOf(i + chunkSize, arr.size)
            result.add(arr.slice(i until end).toIntArray())
        }
        
        return result
    }
    
    /**
     * Copy Array Range
     *
     * Problem: Create a copy of array elements within a specific range.
     *
     * Algorithm:
     * 1. Validate and adjust start and end indices
     * 2. Create new array for the range
     * 3. Copy elements from start to end
     *
     * Time Complexity: O(end - start)
     * Space Complexity: O(end - start)
     */
    fun copyArrayRange(arr: IntArray, start: Int, end: Int): IntArray {
        val startIndex = maxOf(0, start)
        val endIndex = minOf(arr.size, end + 1)
        return if (startIndex < endIndex) arr.slice(startIndex until endIndex).toIntArray() else intArrayOf()
    }
    
    /**
     * Copy Array with Padding
     *
     * Problem: Create a copy of array with padding to reach specified size.
     *
     * Algorithm:
     * 1. Create new array of specified size
     * 2. Fill with padding value
     * 3. Copy original array elements
     *
     * Time Complexity: O(newSize)
     * Space Complexity: O(newSize)
     */
    fun copyArrayWithPadding(arr: IntArray, newSize: Int, paddingValue: Int = 0): IntArray {
        val result = IntArray(newSize) { paddingValue }
        arr.copyInto(result, 0, 0, minOf(arr.size, newSize))
        return result
    }
} 