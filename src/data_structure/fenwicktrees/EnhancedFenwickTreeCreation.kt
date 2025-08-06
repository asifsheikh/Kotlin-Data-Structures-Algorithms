package data_structure.fenwicktrees

/**
 * ENHANCED FENWICK TREE CREATION - Quick Reference
 * All Kotlin enhanced Fenwick Tree creation methods in one place
 */

object EnhancedFenwickTreeCreation {
    
    /**
     * Enhanced Fenwick Tree with additional features
     */
    class EnhancedFenwickTree(private val size: Int) {
        private val tree = LongArray(size + 1) { 0L }
        private val original = LongArray(size) { 0L }
        
        /**
         * Set value at index i
         */
        fun set(i: Int, value: Long) {
            val delta = value - original[i]
            update(i, delta)
        }
        
        /**
         * Update value at index i by adding delta
         */
        fun update(i: Int, delta: Long) {
            original[i] += delta
            var index = i + 1
            while (index <= size) {
                tree[index] += delta
                index += index and -index
            }
        }
        
        /**
         * Get prefix sum from index 0 to i (inclusive)
         */
        fun query(i: Int): Long {
            var sum = 0L
            var index = i + 1
            while (index > 0) {
                sum += tree[index]
                index -= index and -index
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
         * Get value at specific index
         */
        fun get(i: Int): Long = original[i]
        
        /**
         * Get current size of the tree
         */
        fun getSize(): Int = size
    }
} 