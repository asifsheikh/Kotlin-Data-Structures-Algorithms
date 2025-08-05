package data_structure.arrays

/**
 * ARRAY CREATION AND BASIC OPERATIONS
 * 
 * This file contains array creation methods and basic operations:
 * - Different ways to create arrays
 * - Basic array operations
 * - Array initialization patterns
 */

object ArrayCreation {
    
    // ===== ARRAY CREATION METHODS =====
    fun createArrayWithSize(size: Int): IntArray {
        return IntArray(size)
    }
    
    fun createArrayWithValues(vararg values: Int): IntArray {
        return values
    }
    
    fun createArrayWithDefault(size: Int, defaultValue: Int): IntArray {
        return IntArray(size) { defaultValue }
    }
    
    fun createArrayWithFunction(size: Int, function: (Int) -> Int): IntArray {
        return IntArray(size) { function(it) }
    }
    
    fun createArrayWithRange(start: Int, end: Int): IntArray {
        return (start..end).toList().toIntArray()
    }
    
    fun createArrayWithStep(start: Int, end: Int, step: Int): IntArray {
        return (start..end step step).toList().toIntArray()
    }
    
    // ===== 2D ARRAY CREATION =====
    fun create2DArray(rows: Int, cols: Int): Array<IntArray> {
        return Array(rows) { IntArray(cols) }
    }
    
    fun create2DArrayWithDefault(rows: Int, cols: Int, defaultValue: Int): Array<IntArray> {
        return Array(rows) { IntArray(cols) { defaultValue } }
    }
    
    fun create2DArrayWithValues(vararg rows: IntArray): Array<IntArray> {
        return Array(rows.size) { rows[it] }
    }
    
    // ===== ARRAY INITIALIZATION PATTERNS =====
    fun createIdentityMatrix(size: Int): Array<IntArray> {
        return Array(size) { row ->
            IntArray(size) { col ->
                if (row == col) 1 else 0
            }
        }
    }
    
    fun createSpiralMatrix(rows: Int, cols: Int): Array<IntArray> {
        val matrix = Array(rows) { IntArray(cols) }
        var value = 1
        var top = 0
        var bottom = rows - 1
        var left = 0
        var right = cols - 1
        
        while (top <= bottom && left <= right) {
            // Fill top row
            for (i in left..right) {
                matrix[top][i] = value++
            }
            top++
            
            // Fill right column
            for (i in top..bottom) {
                matrix[i][right] = value++
            }
            right--
            
            // Fill bottom row
            if (top <= bottom) {
                for (i in right downTo left) {
                    matrix[bottom][i] = value++
                }
                bottom--
            }
            
            // Fill left column
            if (left <= right) {
                for (i in bottom downTo top) {
                    matrix[i][left] = value++
                }
                left++
            }
        }
        
        return matrix
    }
    
    fun createRandomArray(size: Int, min: Int, max: Int): IntArray {
        return IntArray(size) { (min..max).random() }
    }
    
    fun createSortedArray(size: Int, start: Int = 1): IntArray {
        return IntArray(size) { start + it }
    }
    
    fun createReverseSortedArray(size: Int, start: Int = size): IntArray {
        return IntArray(size) { start - it }
    }
    
    // ===== ARRAY UTILITIES =====
    fun printArray(arr: IntArray) {
        println(arr.contentToString())
    }
    
    fun print2DArray(matrix: Array<IntArray>) {
        for (row in matrix) {
            println(row.contentToString())
        }
    }
    
    fun copyArray(arr: IntArray): IntArray {
        return arr.copyOf()
    }
    
    fun copy2DArray(matrix: Array<IntArray>): Array<IntArray> {
        return Array(matrix.size) { matrix[it].copyOf() }
    }
    
    fun resizeArray(arr: IntArray, newSize: Int): IntArray {
        return arr.copyOf(newSize)
    }
    
    // ===== ARRAY VALIDATION =====
    fun isValidIndex(arr: IntArray, index: Int): Boolean {
        return index in arr.indices
    }
    
    fun isValid2DIndex(matrix: Array<IntArray>, row: Int, col: Int): Boolean {
        return row in matrix.indices && col in matrix[row].indices
    }
    
    fun isEmpty(arr: IntArray): Boolean {
        return arr.isEmpty()
    }
    
    fun isNotEmpty(arr: IntArray): Boolean {
        return arr.isNotEmpty()
    }
    
    // ===== ARRAY CONVERSION =====
    fun arrayToList(arr: IntArray): List<Int> {
        return arr.toList()
    }
    
    fun listToArray(list: List<Int>): IntArray {
        return list.toIntArray()
    }
    
    fun arrayToSet(arr: IntArray): Set<Int> {
        return arr.toSet()
    }
    
    fun setToArray(set: Set<Int>): IntArray {
        return set.toIntArray()
    }
    
    // ===== ARRAY COMPARISON =====
    fun areArraysEqual(arr1: IntArray, arr2: IntArray): Boolean {
        return arr1.contentEquals(arr2)
    }
    
    fun are2DArraysEqual(matrix1: Array<IntArray>, matrix2: Array<IntArray>): Boolean {
        if (matrix1.size != matrix2.size) return false
        for (i in matrix1.indices) {
            if (!matrix1[i].contentEquals(matrix2[i])) return false
        }
        return true
    }
    
    // ===== ARRAY STATISTICS =====
    fun getArrayStats(arr: IntArray): Map<String, Comparable<*>?> {
        if (arr.isEmpty()) {
            return mapOf(
                "size" to 0,
                "min" to null,
                "max" to null,
                "sum" to 0,
                "average" to 0.0
            )
        }
        
        return mapOf(
            "size" to arr.size,
            "min" to arr.minOrNull(),
            "max" to arr.maxOrNull(),
            "sum" to arr.sum(),
            "average" to arr.average()
        )
    }
    
    // ===== SAMPLE ARRAYS =====
    fun createSampleArray(): IntArray {
        return intArrayOf(1, 2, 3, 4, 5)
    }
    
    fun createSample2DArray(): Array<IntArray> {
        return arrayOf(
            intArrayOf(1, 2, 3),
            intArrayOf(4, 5, 6),
            intArrayOf(7, 8, 9)
        )
    }
    
    fun createSampleMatrix(): Array<IntArray> {
        return arrayOf(
            intArrayOf(1, 2, 3, 4),
            intArrayOf(5, 6, 7, 8),
            intArrayOf(9, 10, 11, 12),
            intArrayOf(13, 14, 15, 16)
        )
    }
} 