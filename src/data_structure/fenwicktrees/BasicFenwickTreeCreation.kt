package data_structure.fenwicktrees

/**
 * BASIC FENWICK TREE CREATION - Quick Reference
 * All Kotlin basic Fenwick Tree creation methods in one place
 */

object BasicFenwickTreeCreation {
    
    /**
     * Basic Fenwick Tree class for efficient range queries and point updates
     */
    class FenwickTree(private val size: Int) {
        private val tree = LongArray(size + 1) { 0L }
        
        /**
         * Update value at index i by adding delta
         */
        fun update(i: Int, delta: Long) {
            var index = i + 1 // 1-indexed
            while (index <= size) {
                tree[index] += delta
                index += index and -index // Add least significant bit
            }
        }
        
        /**
         * Get prefix sum from index 0 to i (inclusive)
         */
        fun query(i: Int): Long {
            var sum = 0L
            var index = i + 1 // 1-indexed
            while (index > 0) {
                sum += tree[index]
                index -= index and -index // Remove least significant bit
            }
            return sum
        }
        
        /**
         * Get range sum from index left to right (inclusive)
         */
        fun rangeQuery(left: Int, right: Int): Long {
            return query(right) - query(left - 1)
        }
        
        /**
         * Get current size of the tree
         */
        fun getSize(): Int = size
    }
} 