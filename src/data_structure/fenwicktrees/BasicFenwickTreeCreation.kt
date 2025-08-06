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
    
    /**
     * All Basic Fenwick Tree Creation Methods
     * Complete reference for creating basic Fenwick Trees in Kotlin
     */
    fun allBasicFenwickTreeCreationMethods() {
        // === BASIC FENWICK TREE CREATION ===
        val emptyFenwick = FenwickTree(10)                        // Empty Fenwick tree with size 10
        val largeFenwick = FenwickTree(1000000)                   // Large Fenwick tree for big datasets
        val smallFenwick = FenwickTree(1)                         // Smallest possible Fenwick tree
        
        // === FROM ARRAYS ===
        val array = longArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val fenwickFromArray = createFromArray(array)             // Fenwick tree from array
        
        // === FROM LISTS ===
        val list = listOf(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L)
        val fenwickFromList = createFromList(list)                // Fenwick tree from list
        
        // === FROM SEQUENCES ===
        val sequence = (1..10).asSequence().map { it.toLong() }
        val fenwickFromSequence = createFromSequence(sequence)    // Fenwick tree from sequence
        
        // === FROM RANGES ===
        val fenwickFromRange = createFromRange(1..10)             // Fenwick tree from range
        val fenwickFromRangeStep = createFromRange(1..20 step 2)  // Fenwick tree from range with step
        
        // === FROM FUNCTIONS ===
        val fenwickFromFunction = createFromFunction(10) { it * it.toLong() } // Fenwick tree from function
        val fenwickFromIndexFunction = createFromIndexFunction(10) { index -> index * 2L } // Fenwick tree from index function
        
        // === WITH INITIAL VALUES ===
        val fenwickWithZeros = createWithInitialValue(10, 0L)     // Fenwick tree filled with zeros
        val fenwickWithOnes = createWithInitialValue(10, 1L)      // Fenwick tree filled with ones
        val fenwickWithValue = createWithInitialValue(10, 42L)    // Fenwick tree filled with specific value
        
        // === WITH RANDOM VALUES ===
        val fenwickWithRandom = createWithRandomValues(10, 1L..100L) // Fenwick tree with random values
        val fenwickWithRandomInt = createWithRandomValues(10, 1..100) // Fenwick tree with random int values
        
        // === SPECIAL PATTERNS ===
        val fenwickAlternating = createAlternating(10)            // Fenwick tree with alternating 1, -1, 1, -1...
        val fenwickFibonacci = createFibonacci(10)                // Fenwick tree with Fibonacci sequence
        val fenwickPowers = createPowers(10, 2)                  // Fenwick tree with powers of 2
        val fenwickFactorial = createFactorial(10)                // Fenwick tree with factorial sequence
        
        // === FROM ITERABLES ===
        val iterable = (1..10).asIterable()
        val fenwickFromIterable = createFromIterable(iterable)    // Fenwick tree from iterable
        
        // === FROM COLLECTIONS ===
        val collection = listOf(1L, 2L, 3L, 4L, 5L)
        val fenwickFromCollection = createFromCollection(collection) // Fenwick tree from collection
        
        // === WITH FILTERING ===
        val allNumbers = (1..20).toList()
        val filteredNumbers = allNumbers.filter { it % 2 == 0 }
        val fenwickFromFiltered = createFromFiltered(allNumbers) { it % 2 == 0 } // Fenwick tree with filtering
        
        // === WITH TRANSFORMATION ===
        val originalNumbers = (1..10).toList()
        val transformedNumbers = originalNumbers.map { it * it }
        val fenwickFromTransformed = createFromTransformed(originalNumbers) { it * it } // Fenwick tree with transformation
        
        // === WITH SORTING ===
        val unsortedNumbers = listOf(5, 2, 8, 1, 9, 3)
        val sortedNumbers = unsortedNumbers.sorted()
        val fenwickFromSorted = createFromSorted(unsortedNumbers) // Fenwick tree with sorted values
        
        // === WITH DEDUPLICATION ===
        val duplicateNumbers = listOf(1, 2, 2, 3, 3, 3, 4, 4, 4, 4)
        val uniqueNumbers = duplicateNumbers.distinct()
        val fenwickFromUnique = createFromUnique(duplicateNumbers) // Fenwick tree with unique values
        
        // === FROM STRING SPLITTING ===
        val numberString = "1,2,3,4,5,6,7,8,9,10"
        val splitNumbers = numberString.split(",").map { it.toLong() }
        val fenwickFromSplit = createFromSplit(numberString) { it.split(",").map { num -> num.toLong() } } // Fenwick tree from split string
        
        // === WITH PREFIX SUMS ===
        val numbers = (1..10).toList()
        val prefixSums = numbers.runningFold(0L) { acc, num -> acc + num }.drop(1)
        val fenwickFromPrefixSums = createFromPrefixSums(numbers) // Fenwick tree from prefix sums
        
        // === WITH DIFFERENCE ARRAY ===
        val originalArray = longArrayOf(1, 2, 3, 4, 5)
        val differenceArray = createDifferenceArray(originalArray)
        val fenwickFromDifference = createFromDifferenceArray(originalArray) // Fenwick tree from difference array
        
        // === WITH CUMULATIVE SUMS ===
        val cumulativeNumbers = (1..10).toList()
        val fenwickFromCumulative = createFromCumulative(cumulativeNumbers) // Fenwick tree from cumulative sums
        
        // === FROM CHARACTER ARRAYS ===
        val charArray = charArrayOf('1', '2', '3', '4', '5')
        val charNumbers = charArray.map { it.toString().toLong() }
        val fenwickFromChars = createFromChars(charArray) // Fenwick tree from character array
    }
    
    /**
     * Creates Fenwick tree from array
     */
    fun createFromArray(arr: LongArray): FenwickTree {
        val fenwick = FenwickTree(arr.size)
        for (i in arr.indices) {
            fenwick.update(i, arr[i])
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree from list
     */
    fun createFromList(list: List<Long>): FenwickTree {
        val fenwick = FenwickTree(list.size)
        for (i in list.indices) {
            fenwick.update(i, list[i])
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree from sequence
     */
    fun createFromSequence(sequence: Sequence<Long>): FenwickTree {
        val list = sequence.toList()
        return createFromList(list)
    }
    
    /**
     * Creates Fenwick tree from range
     */
    fun createFromRange(range: IntRange): FenwickTree {
        val fenwick = FenwickTree(range.count())
        for ((index, value) in range.withIndex()) {
            fenwick.update(index, value.toLong())
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree from function
     */
    fun createFromFunction(size: Int, function: (Int) -> Long): FenwickTree {
        val fenwick = FenwickTree(size)
        for (i in 0 until size) {
            fenwick.update(i, function(i))
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree from index function
     */
    fun createFromIndexFunction(size: Int, function: (Int) -> Long): FenwickTree {
        return createFromFunction(size, function)
    }
    
    /**
     * Creates Fenwick tree with initial value
     */
    fun createWithInitialValue(size: Int, value: Long): FenwickTree {
        val fenwick = FenwickTree(size)
        for (i in 0 until size) {
            fenwick.update(i, value)
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree with random values
     */
    fun createWithRandomValues(size: Int, range: LongRange): FenwickTree {
        val fenwick = FenwickTree(size)
        for (i in 0 until size) {
            fenwick.update(i, range.random())
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree with random int values
     */
    fun createWithRandomValues(size: Int, range: IntRange): FenwickTree {
        val fenwick = FenwickTree(size)
        for (i in 0 until size) {
            fenwick.update(i, range.random().toLong())
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree with alternating values
     */
    fun createAlternating(size: Int): FenwickTree {
        val fenwick = FenwickTree(size)
        for (i in 0 until size) {
            fenwick.update(i, if (i % 2 == 0) 1L else -1L)
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree with Fibonacci sequence
     */
    fun createFibonacci(size: Int): FenwickTree {
        val fenwick = FenwickTree(size)
        if (size > 0) fenwick.update(0, 1L)
        if (size > 1) fenwick.update(1, 1L)
        for (i in 2 until size) {
            val fib = fenwick.query(i - 1) - fenwick.query(i - 2)
            fenwick.update(i, fib)
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree with powers
     */
    fun createPowers(size: Int, base: Int): FenwickTree {
        val fenwick = FenwickTree(size)
        for (i in 0 until size) {
            fenwick.update(i, base.toLong().pow(i))
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree with factorial sequence
     */
    fun createFactorial(size: Int): FenwickTree {
        val fenwick = FenwickTree(size)
        var factorial = 1L
        for (i in 0 until size) {
            if (i > 0) factorial *= i
            fenwick.update(i, factorial)
        }
        return fenwick
    }
    
    /**
     * Creates Fenwick tree from iterable
     */
    fun createFromIterable(iterable: Iterable<Int>): FenwickTree {
        val list = iterable.map { it.toLong() }
        return createFromList(list)
    }
    
    /**
     * Creates Fenwick tree from collection
     */
    fun createFromCollection(collection: Collection<Long>): FenwickTree {
        return createFromList(collection.toList())
    }
    
    /**
     * Creates Fenwick tree with filtering
     */
    fun createFromFiltered(numbers: List<Int>, filter: (Int) -> Boolean): FenwickTree {
        val filtered = numbers.filter(filter).map { it.toLong() }
        return createFromList(filtered)
    }
    
    /**
     * Creates Fenwick tree with transformation
     */
    fun createFromTransformed(numbers: List<Int>, transform: (Int) -> Long): FenwickTree {
        val transformed = numbers.map(transform)
        return createFromList(transformed)
    }
    
    /**
     * Creates Fenwick tree with sorted values
     */
    fun createFromSorted(numbers: List<Int>): FenwickTree {
        val sorted = numbers.sorted().map { it.toLong() }
        return createFromList(sorted)
    }
    
    /**
     * Creates Fenwick tree with unique values
     */
    fun createFromUnique(numbers: List<Int>): FenwickTree {
        val unique = numbers.distinct().map { it.toLong() }
        return createFromList(unique)
    }
    
    /**
     * Creates Fenwick tree from split string
     */
    fun createFromSplit(input: String, splitFunction: (String) -> List<Long>): FenwickTree {
        val numbers = splitFunction(input)
        return createFromList(numbers)
    }
    
    /**
     * Creates Fenwick tree from prefix sums
     */
    fun createFromPrefixSums(numbers: List<Int>): FenwickTree {
        val prefixSums = numbers.runningFold(0L) { acc, num -> acc + num }.drop(1)
        return createFromList(prefixSums)
    }
    
    /**
     * Creates difference array
     */
    private fun createDifferenceArray(arr: LongArray): LongArray {
        val diff = LongArray(arr.size)
        diff[0] = arr[0]
        for (i in 1 until arr.size) {
            diff[i] = arr[i] - arr[i - 1]
        }
        return diff
    }
    
    /**
     * Creates Fenwick tree from difference array
     */
    fun createFromDifferenceArray(arr: LongArray): FenwickTree {
        val diff = createDifferenceArray(arr)
        return createFromArray(diff)
    }
    
    /**
     * Creates Fenwick tree from cumulative sums
     */
    fun createFromCumulative(numbers: List<Int>): FenwickTree {
        val cumulative = numbers.runningFold(0L) { acc, num -> acc + num }.drop(1)
        return createFromList(cumulative)
    }
    
    /**
     * Creates Fenwick tree from character array
     */
    fun createFromChars(chars: CharArray): FenwickTree {
        val numbers = chars.map { it.toString().toLong() }
        return createFromList(numbers)
    }
    
    /**
     * Extension function for Long power
     */
    private fun Long.pow(exponent: Int): Long {
        var result = 1L
        repeat(exponent) { result *= this }
        return result
    }
} 