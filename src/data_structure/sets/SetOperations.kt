package data_structure.sets

/**
 * SET OPERATIONS
 * 
 * This file contains comprehensive operations on Sets including
 * membership testing, modification, iteration, filtering, and utility operations.
 * 
 * COMMON SET OPERATIONS:
 * - Membership: Check if element exists in set
 * - Modification: Add, remove elements
 * - Iteration: Traverse all elements
 * - Set Operations: Union, intersection, difference
 * - Filtering: Filter based on conditions
 * - Transformation: Transform elements
 * - Utility: Size, emptiness, containment checks
 */

object SetOperations {
    
    /**
     * Demonstrates comprehensive set operations
     */
    fun demonstrateSetOperations() {
        println("=== SET OPERATIONS DEMONSTRATION ===\n")
        
        // Create sets for demonstration
        val immutableSet = setOf(1, 2, 3, 4, 5)
        val mutableSet = mutableSetOf("A", "B", "C", "D")
        
        // 1. Basic membership operations
        demonstrateMembershipOperations(immutableSet)
        
        // 2. Modification operations
        demonstrateModificationOperations(mutableSet)
        
        // 3. Iteration operations
        demonstrateIterationOperations(immutableSet)
        
        // 4. Set operations
        demonstrateSetOperations(immutableSet)
        
        // 5. Filtering operations
        demonstrateFilteringOperations(immutableSet)
        
        // 6. Transformation operations
        demonstrateTransformationOperations(immutableSet)
        
        // 7. Utility operations
        demonstrateUtilityOperations(immutableSet)
        
        // 8. Advanced operations
        demonstrateAdvancedOperations(immutableSet)
        
        println("=== SET OPERATIONS DEMONSTRATION COMPLETE ===\n")
    }
    
    /**
     * Demonstrates membership operations on sets
     */
    private fun demonstrateMembershipOperations(set: Set<Int>) {
        println("1. MEMBERSHIP OPERATIONS")
        println("Set: $set")
        
        // Basic membership testing
        println("   contains(3): ${set.contains(3)}")
        println("   contains(10): ${set.contains(10)}")
        println("   3 in set: ${3 in set}")
        println("   10 in set: ${10 in set}")
        
        // Multiple element testing
        val testElements = listOf(1, 3, 6, 10)
        val contained = testElements.filter { it in set }
        val notContained = testElements.filter { it !in set }
        println("   Test elements: $testElements")
        println("   Contained: $contained")
        println("   Not contained: $notContained")
        
        // All/Any operations
        println("   All elements > 0: ${set.all { it > 0 }}")
        println("   Any element > 4: ${set.any { it > 4 }}")
        println("   None element < 0: ${set.none { it < 0 }}")
        println()
    }
    
    /**
     * Demonstrates modification operations on mutable sets
     */
    private fun demonstrateModificationOperations(set: MutableSet<String>) {
        println("2. MODIFICATION OPERATIONS")
        println("Initial set: $set")
        
        // Add operations
        set.add("E")
        println("   After add(\"E\"): $set")
        
        set.addAll(listOf("F", "G"))
        println("   After addAll([\"F\", \"G\"]): $set")
        
        // Remove operations
        set.remove("A")
        println("   After remove(\"A\"): $set")
        
        set.removeAll(listOf("B", "C"))
        println("   After removeAll([\"B\", \"C\"]): $set")
        
        // Retain operations
        set.retainAll(listOf("D", "E", "F"))
        println("   After retainAll([\"D\", \"E\", \"F\"]): $set")
        
        // Clear
        set.clear()
        println("   After clear(): $set")
        println()
    }
    
    /**
     * Demonstrates iteration operations on sets
     */
    private fun demonstrateIterationOperations(set: Set<Int>) {
        println("3. ITERATION OPERATIONS")
        println("Set: $set")
        
        // Basic iteration
        println("   Iterating over elements:")
        for (element in set) {
            println("     $element")
        }
        
        // Iteration with index
        println("   Iterating with index:")
        set.forEachIndexed { index, element ->
            println("     $index: $element")
        }
        
        // Functional iteration
        println("   Functional iteration:")
        set.forEach { element ->
            println("     $element")
        }
        
        // Iterator usage
        println("   Using iterator:")
        val iterator = set.iterator()
        while (iterator.hasNext()) {
            println("     ${iterator.next()}")
        }
        println()
    }
    
    /**
     * Demonstrates set operations (union, intersection, difference)
     */
    private fun demonstrateSetOperations(set: Set<Int>) {
        println("4. SET OPERATIONS")
        println("Set: $set")
        
        val otherSet = setOf(3, 4, 5, 6, 7)
        println("Other set: $otherSet")
        
        // Union
        val union = set union otherSet
        println("   Union: $union")
        
        // Intersection
        val intersection = set intersect otherSet
        println("   Intersection: $intersection")
        
        // Difference
        val difference = set subtract otherSet
        println("   Difference (set - otherSet): $difference")
        
        // Symmetric difference
        val symmetricDifference = (set union otherSet) subtract (set intersect otherSet)
        println("   Symmetric difference: $symmetricDifference")
        
        // Subset and superset
        println("   set is subset of union: ${set.all { it in union }}")
        println("   union is superset of set: ${union.containsAll(set)}")
        println()
    }
    
    /**
     * Demonstrates filtering operations on sets
     */
    private fun demonstrateFilteringOperations(set: Set<Int>) {
        println("5. FILTERING OPERATIONS")
        println("Set: $set")
        
        // Filter by condition
        val evenNumbers = set.filter { it % 2 == 0 }
        println("   Even numbers: $evenNumbers")
        
        val greaterThan3 = set.filter { it > 3 }
        println("   Numbers > 3: $greaterThan3")
        
        // Take and drop
        val firstThree = set.take(3)
        val lastTwo = set.drop(3)
        println("   First 3 elements: $firstThree")
        println("   Last 2 elements: $lastTwo")
        
        // Take while and drop while
        val takeWhileLessThan4 = set.takeWhile { it < 4 }
        val dropWhileLessThan3 = set.dropWhile { it < 3 }
        println("   Take while < 4: $takeWhileLessThan4")
        println("   Drop while < 3: $dropWhileLessThan3")
        println()
    }
    
    /**
     * Demonstrates transformation operations on sets
     */
    private fun demonstrateTransformationOperations(set: Set<Int>) {
        println("6. TRANSFORMATION OPERATIONS")
        println("Set: $set")
        
        // Map transformation
        val doubled = set.map { it * 2 }
        println("   Doubled: $doubled")
        
        val asString = set.map { it.toString() }
        println("   As strings: $asString")
        
        // Flat map transformation
        val flatMapped = set.flatMap { listOf(it, it * 10) }
        println("   Flat mapped: $flatMapped")
        
        // Associate operations
        val associated = set.associateWith { it * it }
        println("   Associated with squares: $associated")
        
        val associatedBy = set.associateBy { it % 2 == 0 }
        println("   Associated by even/odd: $associatedBy")
        
        // Group by
        val grouped = set.groupBy { it % 2 == 0 }
        println("   Grouped by even/odd: $grouped")
        println()
    }
    
    /**
     * Demonstrates utility operations on sets
     */
    private fun demonstrateUtilityOperations(set: Set<Int>) {
        println("7. UTILITY OPERATIONS")
        println("Set: $set")
        
        // Size and emptiness
        println("   Size: ${set.size}")
        println("   Is empty: ${set.isEmpty()}")
        println("   Is not empty: ${set.isNotEmpty()}")
        
        // Min/Max operations
        println("   Min: ${set.minOrNull()}")
        println("   Max: ${set.maxOrNull()}")
        
        // Sum and average
        println("   Sum: ${set.sum()}")
        println("   Average: ${set.average()}")
        
        // Count operations
        println("   Count of even numbers: ${set.count { it % 2 == 0 }}")
        println("   Count of numbers > 3: ${set.count { it > 3 }}")
        
        // Find operations
        println("   First element > 3: ${set.find { it > 3 }}")
        println("   Last element < 4: ${set.findLast { it < 4 }}")
        
        // Random element
        println("   Random element: ${set.random()}")
        println()
    }
    
    /**
     * Demonstrates advanced operations on sets
     */
    private fun demonstrateAdvancedOperations(set: Set<Int>) {
        println("8. ADVANCED OPERATIONS")
        println("Set: $set")
        
        // Partition
        val (even, odd) = set.partition { it % 2 == 0 }
        println("   Even numbers: $even")
        println("   Odd numbers: $odd")
        
        // Chunked
        val chunked = set.chunked(2)
        println("   Chunked by 2: $chunked")
        
        // Windowed
        val windowed = set.windowed(3, 1, true)
        println("   Windowed by 3: $windowed")
        
        // Zip operations
        val otherSet = setOf("A", "B", "C", "D", "E")
        val zipped = set.zip(otherSet)
        println("   Zipped with $otherSet: $zipped")
        
        // Join operations
        val joined = set.joinToString(", ", "[", "]") { it.toString() }
        println("   Joined as string: $joined")
        
        // To other collections
        val asList = set.toList()
        val asArray = set.toTypedArray()
        println("   As list: $asList")
        println("   As array: ${asArray.contentToString()}")
        println()
    }
    
    /**
     * Demonstrates set algorithms and patterns
     */
    fun demonstrateSetAlgorithms() {
        println("=== SET ALGORITHMS DEMONSTRATION ===\n")
        
        // 1. Duplicate removal
        println("1. DUPLICATE REMOVAL")
        val listWithDuplicates = listOf(1, 2, 2, 3, 3, 4, 5, 5)
        val uniqueElements = listWithDuplicates.toSet()
        println("   List with duplicates: $listWithDuplicates")
        println("   Unique elements: $uniqueElements")
        println()
        
        // 2. Set intersection for common elements
        println("2. COMMON ELEMENTS")
        val set1 = setOf(1, 2, 3, 4, 5)
        val set2 = setOf(4, 5, 6, 7, 8)
        val common = set1 intersect set2
        println("   Set1: $set1")
        println("   Set2: $set2")
        println("   Common elements: $common")
        println()
        
        // 3. Set difference for unique elements
        println("3. UNIQUE ELEMENTS")
        val uniqueToSet1 = set1 subtract set2
        val uniqueToSet2 = set2 subtract set1
        println("   Elements unique to Set1: $uniqueToSet1")
        println("   Elements unique to Set2: $uniqueToSet2")
        println()
        
        // 4. Set union for all elements
        println("4. ALL ELEMENTS")
        val allElements = set1 union set2
        println("   All elements: $allElements")
        println()
        
        // 5. Subset and superset checking
        println("5. SUBSET/SUPERSET CHECKING")
        val subset = setOf(1, 2, 3)
        val superset = setOf(1, 2, 3, 4, 5, 6)
        println("   Subset: $subset")
        println("   Superset: $superset")
        println("   Is subset: ${subset.all { it in superset }}")
        println("   Is superset: ${superset.containsAll(subset)}")
        println()
        
        // 6. Disjoint sets
        println("6. DISJOINT SETS")
        val disjoint1 = setOf(1, 2, 3)
        val disjoint2 = setOf(4, 5, 6)
        val hasIntersection = (disjoint1 intersect disjoint2).isNotEmpty()
        println("   Set1: $disjoint1")
        println("   Set2: $disjoint2")
        println("   Has intersection: $hasIntersection")
        println("   Are disjoint: ${!hasIntersection}")
        println()
        
        // 7. Set complement
        println("7. SET COMPLEMENT")
        val universal = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val subset2 = setOf(2, 4, 6, 8)
        val complement = universal subtract subset2
        println("   Universal set: $universal")
        println("   Subset: $subset2")
        println("   Complement: $complement")
        println()
        
        // 8. Power set generation
        println("8. POWER SET GENERATION")
        val smallSet = setOf(1, 2, 3)
        val powerSet = generatePowerSet(smallSet)
        println("   Set: $smallSet")
        println("   Power set: $powerSet")
        println()
        
        // 9. Set partitioning
        println("9. SET PARTITIONING")
        val numbers = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val partitioned = partitionSet(numbers) { it % 2 == 0 }
        println("   Numbers: $numbers")
        println("   Partitioned by even/odd: $partitioned")
        println()
        
        // 10. Set cartesian product
        println("10. CARTESIAN PRODUCT")
        val setA = setOf(1, 2)
        val setB = setOf("A", "B")
        val cartesian = cartesianProduct(setA, setB)
        println("   Set A: $setA")
        println("   Set B: $setB")
        println("   Cartesian product: $cartesian")
        println()
        
        println("=== SET ALGORITHMS DEMONSTRATION COMPLETE ===\n")
    }
    
    /**
     * Generates the power set of a given set
     */
    private fun <T> generatePowerSet(set: Set<T>): Set<Set<T>> {
        val elements = set.toList()
        val powerSet = mutableSetOf<Set<T>>()
        
        for (i in 0 until (1 shl elements.size)) {
            val subset = mutableSetOf<T>()
            for (j in elements.indices) {
                if ((i and (1 shl j)) != 0) {
                    subset.add(elements[j])
                }
            }
            powerSet.add(subset)
        }
        
        return powerSet
    }
    
    /**
     * Partitions a set based on a predicate
     */
    private fun <T> partitionSet(set: Set<T>, predicate: (T) -> Boolean): Map<Boolean, Set<T>> {
        return set.groupBy(predicate).mapValues { it.value.toSet() }
    }
    
    /**
     * Computes the cartesian product of two sets
     */
    private fun <T, U> cartesianProduct(setA: Set<T>, setB: Set<U>): Set<Pair<T, U>> {
        return setA.flatMap { a -> setB.map { b -> a to b } }.toSet()
    }
}
