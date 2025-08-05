package data_structure.sets

/**
 * SET CREATION
 * 
 * This file contains functions for creating different types of Sets
 * and various creation patterns.
 * 
 * SET PROPERTIES:
 * - Collection of unique elements
 * - No duplicate values allowed
 * - Efficient membership testing
 * - Supports both mutable and immutable variants
 * 
 * IMPLEMENTATION:
 * - Uses Kotlin's built-in Set implementations
 * - Supports different set types (HashSet, LinkedHashSet, TreeSet)
 * - Provides utility functions for common creation patterns
 */

object SetCreation {
    
    /**
     * Creates a basic immutable set
     * 
     * ALGORITHM:
     * 1. Use setOf() to create immutable set
     * 2. Returns read-only set with provided elements
     * 
     * TIME COMPLEXITY: O(1) - Constant time creation
     * SPACE COMPLEXITY: O(n) - n elements
     * 
     * @param elements Elements to initialize the set
     * @return Immutable set with provided elements
     */
    fun <T> createImmutableSet(vararg elements: T): Set<T> {
        return setOf(*elements)
    }
    
    /**
     * Creates a basic mutable set
     * 
     * ALGORITHM:
     * 1. Use mutableSetOf() to create mutable set
     * 2. Returns mutable set with provided elements
     * 
     * TIME COMPLEXITY: O(1) - Constant time creation
     * SPACE COMPLEXITY: O(n) - n elements
     * 
     * @param elements Elements to initialize the set
     * @return Mutable set with provided elements
     */
    fun <T> createMutableSet(vararg elements: T): MutableSet<T> {
        return mutableSetOf(*elements)
    }
    
    /**
     * Creates a HashSet for optimal performance
     * 
     * ALGORITHM:
     * 1. Use HashSet constructor for hash-based implementation
     * 2. Provides O(1) average case for operations
     * 
     * TIME COMPLEXITY: O(1) - Constant time creation
     * SPACE COMPLEXITY: O(n) - n elements
     * 
     * @param elements Elements to initialize the set
     * @return HashSet with provided elements
     */
    fun <T> createHashSet(vararg elements: T): HashSet<T> {
        return HashSet<T>().apply {
            addAll(elements.toList())
        }
    }
    
    /**
     * Creates a LinkedHashSet to preserve insertion order
     * 
     * ALGORITHM:
     * 1. Use LinkedHashSet constructor for ordered implementation
     * 2. Maintains insertion order of elements
     * 
     * TIME COMPLEXITY: O(1) - Constant time creation
     * SPACE COMPLEXITY: O(n) - n elements
     * 
     * @param elements Elements to initialize the set
     * @return LinkedHashSet with provided elements
     */
    fun <T> createLinkedHashSet(vararg elements: T): LinkedHashSet<T> {
        return LinkedHashSet<T>().apply {
            addAll(elements.toList())
        }
    }
    
    /**
     * Creates a set from a list
     * 
     * ALGORITHM:
     * 1. Convert list to set using toSet()
     * 2. Automatically removes duplicates
     * 
     * TIME COMPLEXITY: O(n) - n elements to process
     * SPACE COMPLEXITY: O(n) - n unique elements
     * 
     * @param list List of elements
     * @return Set created from list
     */
    fun <T> createSetFromList(list: List<T>): Set<T> {
        return list.toSet()
    }
    
    /**
     * Creates a set from an array
     * 
     * ALGORITHM:
     * 1. Convert array to set using toSet()
     * 2. Automatically removes duplicates
     * 
     * TIME COMPLEXITY: O(n) - n elements to process
     * SPACE COMPLEXITY: O(n) - n unique elements
     * 
     * @param array Array of elements
     * @return Set created from array
     */
    fun <T> createSetFromArray(array: Array<T>): Set<T> {
        return array.toSet()
    }
    
    /**
     * Creates a set from a sequence
     * 
     * ALGORITHM:
     * 1. Convert sequence to set using toSet()
     * 2. Efficient for large datasets
     * 
     * TIME COMPLEXITY: O(n) - n elements to process
     * SPACE COMPLEXITY: O(n) - n unique elements
     * 
     * @param sequence Sequence of elements
     * @return Set created from sequence
     */
    fun <T> createSetFromSequence(sequence: Sequence<T>): Set<T> {
        return sequence.toSet()
    }
    
    /**
     * Creates a set with initial capacity for performance
     * 
     * ALGORITHM:
     * 1. Create HashSet with specified initial capacity
     * 2. Add provided elements
     * 
     * TIME COMPLEXITY: O(1) - Constant time creation
     * SPACE COMPLEXITY: O(capacity) - Initial capacity
     * 
     * @param initialCapacity Initial capacity of the set
     * @param elements Elements to initialize the set
     * @return HashSet with specified capacity
     */
    fun <T> createSetWithCapacity(initialCapacity: Int, vararg elements: T): HashSet<T> {
        return HashSet<T>(initialCapacity).apply {
            addAll(elements.toList())
        }
    }
    
    /**
     * Creates a set from a range of integers
     * 
     * ALGORITHM:
     * 1. Convert range to set using toSet()
     * 2. Creates set of integers in range
     * 
     * TIME COMPLEXITY: O(n) - n elements in range
     * SPACE COMPLEXITY: O(n) - n elements
     * 
     * @param range Range of integers
     * @return Set of integers in range
     */
    fun createSetFromRange(range: IntRange): Set<Int> {
        return range.toSet()
    }
    
    /**
     * Creates a set by filtering elements
     * 
     * ALGORITHM:
     * 1. Filter elements based on predicate
     * 2. Convert filtered result to set
     * 
     * TIME COMPLEXITY: O(n) - n elements to filter
     * SPACE COMPLEXITY: O(k) - k elements that pass filter
     * 
     * @param elements List of elements to filter
     * @param predicate Filter condition
     * @return Set of filtered elements
     */
    fun <T> createFilteredSet(elements: List<T>, predicate: (T) -> Boolean): Set<T> {
        return elements.filter(predicate).toSet()
    }
    
    /**
     * Creates a set by transforming elements
     * 
     * ALGORITHM:
     * 1. Transform elements using function
     * 2. Convert transformed result to set
     * 
     * TIME COMPLEXITY: O(n) - n elements to transform
     * SPACE COMPLEXITY: O(n) - n transformed elements
     * 
     * @param elements List of elements to transform
     * @param transform Transformation function
     * @return Set of transformed elements
     */
    fun <T, R> createTransformedSet(elements: List<T>, transform: (T) -> R): Set<R> {
        return elements.map(transform).toSet()
    }
    
    /**
     * Creates a set of unique characters from a string
     * 
     * ALGORITHM:
     * 1. Convert string to list of characters
     * 2. Convert to set to remove duplicates
     * 
     * TIME COMPLEXITY: O(n) - n characters in string
     * SPACE COMPLEXITY: O(k) - k unique characters
     * 
     * @param str Input string
     * @return Set of unique characters
     */
    fun createCharacterSet(str: String): Set<Char> {
        return str.toSet()
    }
    
    /**
     * Creates a set of unique words from a string
     * 
     * ALGORITHM:
     * 1. Split string by whitespace
     * 2. Convert to set to remove duplicates
     * 
     * TIME COMPLEXITY: O(n) - n characters in string
     * SPACE COMPLEXITY: O(k) - k unique words
     * 
     * @param str Input string
     * @return Set of unique words
     */
    fun createWordSet(str: String): Set<String> {
        return str.split("\\s+".toRegex()).toSet()
    }
    
    /**
     * Creates a set by combining multiple sets
     * 
     * ALGORITHM:
     * 1. Use union operation to combine sets
     * 2. Returns set with all unique elements
     * 
     * TIME COMPLEXITY: O(n) - n total elements across all sets
     * SPACE COMPLEXITY: O(n) - n unique elements
     * 
     * @param sets List of sets to combine
     * @return Combined set with all unique elements
     */
    fun <T> createCombinedSet(sets: List<Set<T>>): Set<T> {
        return sets.reduce { acc, set -> acc union set }
    }
    
    /**
     * Creates a set by finding intersection of multiple sets
     * 
     * ALGORITHM:
     * 1. Use intersection operation to find common elements
     * 2. Returns set with elements present in all sets
     * 
     * TIME COMPLEXITY: O(n) - n total elements across all sets
     * SPACE COMPLEXITY: O(k) - k common elements
     * 
     * @param sets List of sets to intersect
     * @return Set with elements common to all sets
     */
    fun <T> createIntersectionSet(sets: List<Set<T>>): Set<T> {
        return sets.reduce { acc, set -> acc intersect set }
    }
    
    /**
     * Creates a set by finding difference of sets
     * 
     * ALGORITHM:
     * 1. Use subtract operation to find elements in first set but not in others
     * 2. Returns set with elements unique to first set
     * 
     * TIME COMPLEXITY: O(n) - n total elements across all sets
     * SPACE COMPLEXITY: O(k) - k unique elements
     * 
     * @param baseSet Base set
     * @param otherSets Sets to subtract from base set
     * @return Set with elements unique to base set
     */
    fun <T> createDifferenceSet(baseSet: Set<T>, otherSets: List<Set<T>>): Set<T> {
        return otherSets.fold(baseSet) { acc, set -> acc subtract set }
    }
    
    /**
     * Creates a set of prime numbers up to n
     * 
     * ALGORITHM:
     * 1. Use Sieve of Eratosthenes to find primes
     * 2. Convert result to set
     * 
     * TIME COMPLEXITY: O(n log log n) - Sieve of Eratosthenes
     * SPACE COMPLEXITY: O(n) - n numbers
     * 
     * @param n Upper limit
     * @return Set of prime numbers up to n
     */
    fun createPrimeSet(n: Int): Set<Int> {
        if (n < 2) return emptySet()
        
        val isPrime = BooleanArray(n + 1) { true }
        isPrime[0] = false
        isPrime[1] = false
        
        for (i in 2..kotlin.math.sqrt(n.toDouble()).toInt()) {
            if (isPrime[i]) {
                for (j in i * i..n step i) {
                    isPrime[j] = false
                }
            }
        }
        
        return (2..n).filter { isPrime[it] }.toSet()
    }
    
    /**
     * Creates a set of Fibonacci numbers up to n
     * 
     * ALGORITHM:
     * 1. Generate Fibonacci numbers iteratively
     * 2. Stop when exceeding n
     * 
     * TIME COMPLEXITY: O(log n) - Fibonacci grows exponentially
     * SPACE COMPLEXITY: O(log n) - Number of Fibonacci numbers up to n
     * 
     * @param n Upper limit
     * @return Set of Fibonacci numbers up to n
     */
    fun createFibonacciSet(n: Int): Set<Int> {
        if (n < 0) return emptySet()
        
        val fibonacci = mutableSetOf<Int>()
        var a = 0
        var b = 1
        
        while (a <= n) {
            fibonacci.add(a)
            val temp = a + b
            a = b
            b = temp
        }
        
        return fibonacci
    }
    
    /**
     * Demonstrates set creation patterns
     */
    fun demonstrateSetCreation() {
        println("=== SET CREATION DEMONSTRATION ===\n")
        
        // 1. Basic set creation
        println("1. BASIC SET CREATION")
        val basicSet = createImmutableSet(1, 2, 3, 4, 5)
        val mutableSet = createMutableSet("A", "B", "C")
        println("Immutable set: $basicSet")
        println("Mutable set: $mutableSet")
        println()
        
        // 2. Different set types
        println("2. DIFFERENT SET TYPES")
        val hashSet = createHashSet(1, 2, 3, 4, 5)
        val linkedHashSet = createLinkedHashSet("A", "B", "C", "D")
        println("HashSet: $hashSet")
        println("LinkedHashSet: $linkedHashSet")
        println()
        
        // 3. From collections
        println("3. FROM COLLECTIONS")
        val list = listOf(1, 2, 2, 3, 3, 4, 5)
        val array = arrayOf("A", "B", "A", "C", "B")
        val listSet = createSetFromList(list)
        val arraySet = createSetFromArray(array)
        println("List: $list")
        println("Set from list: $listSet")
        println("Array: ${array.contentToString()}")
        println("Set from array: $arraySet")
        println()
        
        // 4. From range
        println("4. FROM RANGE")
        val rangeSet = createSetFromRange(1..10)
        println("Range 1..10: $rangeSet")
        println()
        
        // 5. Filtered set
        println("5. FILTERED SET")
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val evenSet = createFilteredSet(numbers) { it % 2 == 0 }
        println("Numbers: $numbers")
        println("Even numbers: $evenSet")
        println()
        
        // 6. Transformed set
        println("6. TRANSFORMED SET")
        val words = listOf("apple", "banana", "cherry")
        val lengthSet = createTransformedSet(words) { it.length }
        println("Words: $words")
        println("Word lengths: $lengthSet")
        println()
        
        // 7. Character set
        println("7. CHARACTER SET")
        val charSet = createCharacterSet("hello world")
        println("String: 'hello world'")
        println("Unique characters: $charSet")
        println()
        
        // 8. Word set
        println("8. WORD SET")
        val wordSet = createWordSet("the quick brown fox jumps over the lazy dog")
        println("Text: 'the quick brown fox jumps over the lazy dog'")
        println("Unique words: $wordSet")
        println()
        
        // 9. Set operations
        println("9. SET OPERATIONS")
        val set1 = setOf(1, 2, 3, 4)
        val set2 = setOf(3, 4, 5, 6)
        val set3 = setOf(5, 6, 7, 8)
        
        val combined = createCombinedSet(listOf(set1, set2, set3))
        val intersection = createIntersectionSet(listOf(set1, set2))
        val difference = createDifferenceSet(set1, listOf(set2))
        
        println("Set1: $set1")
        println("Set2: $set2")
        println("Set3: $set3")
        println("Combined: $combined")
        println("Intersection of Set1 and Set2: $intersection")
        println("Difference (Set1 - Set2): $difference")
        println()
        
        // 10. Special sets
        println("10. SPECIAL SETS")
        val primeSet = createPrimeSet(20)
        val fibonacciSet = createFibonacciSet(20)
        println("Prime numbers up to 20: $primeSet")
        println("Fibonacci numbers up to 20: $fibonacciSet")
        println()
        
        println("=== SET CREATION DEMONSTRATION COMPLETE ===\n")
    }
} 