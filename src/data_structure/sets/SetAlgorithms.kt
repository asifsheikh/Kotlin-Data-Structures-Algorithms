package data_structure.sets

/**
 * SET ALGORITHMS
 * 
 * This file contains algorithms that use Sets for efficient problem solving
 * including duplicate removal, set operations, and other set-based algorithms.
 * 
 * COMMON SET ALGORITHMS:
 * - Duplicate Removal: Remove duplicates from collections
 * - Set Operations: Union, intersection, difference
 * - Membership Testing: Efficient element checking
 * - Set Partitioning: Divide sets based on conditions
 * - Set Generation: Generate special sets
 */

object SetAlgorithms {
    
    /**
     * PROBLEM: Remove Duplicates from List
     * 
     * Given a list that may contain duplicate elements, return a new list with all duplicates removed.
     * 
     * INPUT: A list with potential duplicates
     * OUTPUT: A new list with duplicates removed, preserving order
     * 
     * EXAMPLES:
     * Input: [1, 2, 2, 3, 3, 4, 5, 5]
     * Output: [1, 2, 3, 4, 5]
     * 
     * Input: ["apple", "banana", "apple", "cherry", "banana"]
     * Output: ["apple", "banana", "cherry"]
     * 
     * Input: [1, 1, 1, 1, 1]
     * Output: [1]
     * 
     * Input: []
     * Output: []
     * 
     * INTUITION:
     * - Convert list to set to automatically remove duplicates
     * - Convert back to list to preserve list interface
     * - Sets only store unique elements, so duplicates are automatically handled
     * - Note: This may not preserve original order in all implementations
     * 
     * TIME COMPLEXITY: O(n) - n elements to process
     * SPACE COMPLEXITY: O(k) - k unique elements
     * 
     * @param list List with potential duplicates
     * @return List with duplicates removed
     */
    fun <T> removeDuplicates(list: List<T>): List<T> {
        return list.toSet().toList()
    }
    
    /**
     * PROBLEM: Find Common Elements Between Two Lists
     * 
     * Given two lists, find all elements that appear in both lists.
     * 
     * INPUT: Two lists of elements
     * OUTPUT: List of elements common to both lists
     * 
     * EXAMPLES:
     * Input: [1, 2, 3, 4, 5], [4, 5, 6, 7, 8]
     * Output: [4, 5]
     * 
     * Input: ["apple", "banana", "cherry"], ["banana", "date", "elderberry"]
     * Output: ["banana"]
     * 
     * Input: [1, 2, 3], [4, 5, 6]
     * Output: [] (no common elements)
     * 
     * Input: [1, 2, 3], [1, 2, 3]
     * Output: [1, 2, 3] (all elements common)
     * 
     * INTUITION:
     * - Convert both lists to sets for efficient membership testing
     * - Use set intersection operation to find common elements
     * - Intersection returns elements present in both sets
     * - Convert result back to list
     * 
     * TIME COMPLEXITY: O(n + m) - n and m elements in lists
     * SPACE COMPLEXITY: O(min(n, m)) - size of intersection
     * 
     * @param list1 First list
     * @param list2 Second list
     * @return List of common elements
     */
    fun <T> findCommonElements(list1: List<T>, list2: List<T>): List<T> {
        val set1 = list1.toSet()
        val set2 = list2.toSet()
        return (set1 intersect set2).toList()
    }
    
    /**
     * PROBLEM: Find Unique Elements in First List
     * 
     * Given two lists, find elements that appear in the first list but not in the second list.
     * 
     * INPUT: Two lists of elements
     * OUTPUT: List of elements unique to first list
     * 
     * EXAMPLES:
     * Input: [1, 2, 3, 4, 5], [4, 5, 6, 7, 8]
     * Output: [1, 2, 3]
     * 
     * Input: ["apple", "banana", "cherry"], ["banana", "date"]
     * Output: ["apple", "cherry"]
     * 
     * Input: [1, 2, 3], [1, 2, 3]
     * Output: [] (no unique elements)
     * 
     * Input: [1, 2, 3], [4, 5, 6]
     * Output: [1, 2, 3] (all elements unique)
     * 
     * INTUITION:
     * - Convert both lists to sets for efficient operations
     * - Use set difference operation (first set - second set)
     * - Difference returns elements in first set but not in second
     * - Convert result back to list
     * 
     * TIME COMPLEXITY: O(n + m) - n and m elements in lists
     * SPACE COMPLEXITY: O(n) - elements unique to first list
     * 
     * @param list1 First list
     * @param list2 Second list
     * @return List of elements unique to first list
     */
    fun <T> findUniqueElements(list1: List<T>, list2: List<T>): List<T> {
        val set1 = list1.toSet()
        val set2 = list2.toSet()
        return (set1 subtract set2).toList()
    }
    
    /**
     * PROBLEM: Check if Lists Have Common Elements
     * 
     * Given two lists, determine if they have any elements in common.
     * 
     * INPUT: Two lists of elements
     * OUTPUT: true if lists have common elements, false otherwise
     * 
     * EXAMPLES:
     * Input: [1, 2, 3, 4], [4, 5, 6, 7]
     * Output: true (4 is common)
     * 
     * Input: ["apple", "banana"], ["cherry", "date"]
     * Output: false (no common elements)
     * 
     * Input: [1, 2, 3], [1, 2, 3]
     * Output: true (all elements common)
     * 
     * Input: [], [1, 2, 3]
     * Output: false (empty list has no elements)
     * 
     * INTUITION:
     * - Convert both lists to sets
     * - Find intersection of the sets
     * - Check if intersection is non-empty
     * - If intersection has elements, lists have common elements
     * 
     * TIME COMPLEXITY: O(n + m) - n and m elements in lists
     * SPACE COMPLEXITY: O(min(n, m)) - size of smaller set
     * 
     * @param list1 First list
     * @param list2 Second list
     * @return true if lists have common elements
     */
    fun <T> hasCommonElements(list1: List<T>, list2: List<T>): Boolean {
        val set1 = list1.toSet()
        val set2 = list2.toSet()
        return (set1 intersect set2).isNotEmpty()
    }
    
    /**
     * PROBLEM: Check if One List is Subset of Another
     * 
     * Given two lists, determine if the first list is a subset of the second list.
     * A subset means all elements of the first list appear in the second list.
     * 
     * INPUT: Two lists of elements
     * OUTPUT: true if first list is subset of second list
     * 
     * EXAMPLES:
     * Input: [1, 2, 3], [1, 2, 3, 4, 5, 6]
     * Output: true (all elements of first list are in second)
     * 
     * Input: ["apple", "banana"], ["apple", "cherry", "date"]
     * Output: false ("banana" not in second list)
     * 
     * Input: [1, 2, 3], [1, 2, 3]
     * Output: true (equal lists, first is subset of second)
     * 
     * Input: [], [1, 2, 3]
     * Output: true (empty set is subset of any set)
     * 
     * Input: [1, 2, 3], []
     * Output: false (non-empty set cannot be subset of empty set)
     * 
     * INTUITION:
     * - Convert both lists to sets
     * - Check if all elements of first set are in second set
     * - Use set operation to verify subset relationship
     * - If first set is subset of second set, return true
     * 
     * TIME COMPLEXITY: O(n + m) - n and m elements in lists
     * SPACE COMPLEXITY: O(n) - size of first set
     * 
     * @param subset Candidate subset list
     * @param superset Candidate superset list
     * @return true if first list is subset of second
     */
    fun <T> isSubset(subset: List<T>, superset: List<T>): Boolean {
        val subsetSet = subset.toSet()
        val supersetSet = superset.toSet()
        return subsetSet.all { it in supersetSet }
    }
    
    /**
     * PROBLEM: Find All Unique Elements from Multiple Lists
     * 
     * Given multiple lists, find all unique elements that appear in any of the lists.
     * 
     * INPUT: List of lists
     * OUTPUT: List of all unique elements from all lists
     * 
     * EXAMPLES:
     * Input: [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
     * Output: [1, 2, 3, 4, 5, 6, 7, 8, 9]
     * 
     * Input: [["apple", "banana"], ["banana", "cherry"], ["cherry", "date"]]
     * Output: ["apple", "banana", "cherry", "date"]
     * 
     * Input: [[1, 2, 3], [1, 2, 3], [1, 2, 3]]
     * Output: [1, 2, 3] (duplicates removed)
     * 
     * Input: [[], [1, 2], [3, 4]]
     * Output: [1, 2, 3, 4]
     * 
     * INTUITION:
     * - Convert all lists to sets to remove duplicates within each list
     * - Use set union operation to combine all sets
     * - Union returns all unique elements from all sets
     * - Convert result back to list
     * 
     * TIME COMPLEXITY: O(n) - n total elements across all lists
     * SPACE COMPLEXITY: O(n) - n unique elements
     * 
     * @param lists List of lists
     * @return List of all unique elements
     */
    fun <T> findAllUniqueElements(lists: List<List<T>>): List<T> {
        return lists.flatMap { it.toSet() }.toSet().toList()
    }
    
    /**
     * PROBLEM: Find Elements Common to All Lists
     * 
     * Given multiple lists, find elements that appear in all of the lists.
     * 
     * INPUT: List of lists
     * OUTPUT: List of elements common to all lists
     * 
     * EXAMPLES:
     * Input: [[1, 2, 3, 4], [2, 3, 4, 5], [3, 4, 5, 6]]
     * Output: [3, 4]
     * 
     * Input: [["apple", "banana"], ["banana", "cherry"], ["banana", "date"]]
     * Output: ["banana"]
     * 
     * Input: [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
     * Output: [] (no common elements)
     * 
     * Input: [[1, 2, 3], [1, 2, 3], [1, 2, 3]]
     * Output: [1, 2, 3] (all elements common)
     * 
     * INTUITION:
     * - Convert all lists to sets
     * - Use set intersection operation to find common elements
     * - Intersection of multiple sets returns elements present in all sets
     * - Convert result back to list
     * 
     * TIME COMPLEXITY: O(n) - n total elements across all lists
     * SPACE COMPLEXITY: O(k) - k common elements
     * 
     * @param lists List of lists
     * @return List of elements common to all lists
     */
    fun <T> findCommonToAll(lists: List<List<T>>): List<T> {
        if (lists.isEmpty()) return emptyList()
        return lists.map { it.toSet() }.reduce { acc, set -> acc intersect set }.toList()
    }
    
    /**
     * PROBLEM: Partition Elements Based on Condition
     * 
     * Given a list of elements and a condition, partition the elements into two groups:
     * elements that satisfy the condition and elements that don't.
     * 
     * INPUT: List of elements and a partitioning condition (predicate)
     * OUTPUT: Map with two keys (true/false) and corresponding sets of elements
     * 
     * EXAMPLES:
     * Input: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], condition = even numbers
     * Output: {true: [2, 4, 6, 8, 10], false: [1, 3, 5, 7, 9]}
     * 
     * Input: ["apple", "banana", "cherry", "date"], condition = starts with 'a'
     * Output: {true: ["apple"], false: ["banana", "cherry", "date"]}
     * 
     * Input: [1, 2, 3], condition = greater than 5
     * Output: {true: [], false: [1, 2, 3]}
     * 
     * Input: [1, 2, 3], condition = greater than 0
     * Output: {true: [1, 2, 3], false: []}
     * 
     * INTUITION:
     * - Use groupBy() function to partition elements by condition
     * - Convert groups to sets to ensure uniqueness
     * - Result is a map with boolean keys and set values
     * - True key contains elements satisfying condition
     * - False key contains elements not satisfying condition
     * 
     * TIME COMPLEXITY: O(n) - n elements to partition
     * SPACE COMPLEXITY: O(n) - n elements
     * 
     * @param elements List of elements to partition
     * @param predicate Partitioning condition
     * @return Map of condition to set of elements
     */
    fun <T> partitionElements(elements: List<T>, predicate: (T) -> Boolean): Map<Boolean, Set<T>> {
        return elements.groupBy(predicate).mapValues { it.value.toSet() }
    }
    
    /**
     * PROBLEM: Find Elements Appearing Exactly Once
     * 
     * Given a list of elements, find all elements that appear exactly once.
     * 
     * INPUT: List of elements
     * OUTPUT: List of elements appearing exactly once
     * 
     * EXAMPLES:
     * Input: [1, 2, 2, 3, 4, 4, 5, 6, 6]
     * Output: [1, 3, 5]
     * 
     * Input: ["apple", "banana", "apple", "cherry", "banana"]
     * Output: ["cherry"]
     * 
     * Input: [1, 1, 2, 2, 3, 3]
     * Output: [] (no element appears exactly once)
     * 
     * Input: [1, 2, 3, 4, 5]
     * Output: [1, 2, 3, 4, 5] (all appear exactly once)
     * 
     * INTUITION:
     * - Count frequency of all elements
     * - Filter elements with frequency = 1
     * - Return list of qualifying elements
     * - Use map to track element counts efficiently
     * 
     * TIME COMPLEXITY: O(n) - n elements to process
     * SPACE COMPLEXITY: O(k) - k unique elements
     * 
     * @param elements List of elements
     * @return List of elements appearing exactly once
     */
    fun <T> findUniqueOccurrences(elements: List<T>): List<T> {
        val frequency = elements.groupingBy { it }.eachCount()
        return frequency.filter { it.value == 1 }.keys.toList()
    }
    
    /**
     * PROBLEM: Find Elements Appearing More Than Once
     * 
     * Given a list of elements, find all elements that appear more than once.
     * 
     * INPUT: List of elements
     * OUTPUT: List of elements appearing more than once
     * 
     * EXAMPLES:
     * Input: [1, 2, 2, 3, 4, 4, 5, 6, 6]
     * Output: [2, 4, 6]
     * 
     * Input: ["apple", "banana", "apple", "cherry", "banana"]
     * Output: ["apple", "banana"]
     * 
     * Input: [1, 2, 3, 4, 5]
     * Output: [] (no element appears more than once)
     * 
     * Input: [1, 1, 1, 1, 1]
     * Output: [1] (1 appears 5 times)
     * 
     * INTUITION:
     * - Count frequency of all elements
     * - Filter elements with frequency > 1
     * - Return list of qualifying elements
     * - Use map to track element counts efficiently
     * 
     * TIME COMPLEXITY: O(n) - n elements to process
     * SPACE COMPLEXITY: O(k) - k unique elements
     * 
     * @param elements List of elements
     * @return List of elements appearing more than once
     */
    fun <T> findDuplicateOccurrences(elements: List<T>): List<T> {
        val frequency = elements.groupingBy { it }.eachCount()
        return frequency.filter { it.value > 1 }.keys.toList()
    }
    
    /**
     * PROBLEM: Check if String Has All Unique Characters
     * 
     * Given a string, determine if all characters in the string are unique.
     * 
     * INPUT: A string
     * OUTPUT: true if all characters are unique, false otherwise
     * 
     * EXAMPLES:
     * Input: "hello"
     * Output: false ('l' appears twice)
     * 
     * Input: "world"
     * Output: true (all characters are unique)
     * 
     * Input: "abcdef"
     * Output: true (all characters are unique)
     * 
     * Input: "aabbcc"
     * Output: false (multiple duplicates)
     * 
     * Input: ""
     * Output: true (empty string has no duplicates)
     * 
     * INTUITION:
     * - Convert string to set of characters
     * - Compare set size with string length
     * - If sizes are equal, all characters are unique
     * - If set size is smaller, there are duplicates
     * - Sets automatically remove duplicates
     * 
     * TIME COMPLEXITY: O(n) - n characters in string
     * SPACE COMPLEXITY: O(k) - k unique characters
     * 
     * @param str Input string
     * @return true if all characters are unique
     */
    fun hasUniqueCharacters(str: String): Boolean {
        return str.toSet().size == str.length
    }
    
    /**
     * PROBLEM: Find Missing Numbers in Range
     * 
     * Given a list of numbers and a range [start, end], find all numbers in the range
     * that are missing from the list.
     * 
     * INPUT: List of numbers and range [start, end]
     * OUTPUT: List of missing numbers in the range
     * 
     * EXAMPLES:
     * Input: [1, 3, 5, 7, 9], start = 1, end = 10
     * Output: [2, 4, 6, 8, 10]
     * 
     * Input: [1, 2, 3, 4, 5], start = 1, end = 5
     * Output: [] (no missing numbers)
     * 
     * Input: [2, 4, 6, 8], start = 1, end = 10
     * Output: [1, 3, 5, 7, 9, 10]
     * 
     * Input: [], start = 1, end = 5
     * Output: [1, 2, 3, 4, 5] (all numbers missing)
     * 
     * INTUITION:
     * - Create set of expected numbers in range [start, end]
     * - Create set of given numbers
     * - Find difference: expected - given
     * - Return sorted list of missing numbers
     * - Use set operations for efficient difference calculation
     * 
     * TIME COMPLEXITY: O(n) - n numbers in range
     * SPACE COMPLEXITY: O(n) - n numbers
     * 
     * @param numbers List of numbers
     * @param start Start of range
     * @param end End of range
     * @return List of missing numbers
     */
    fun findMissingNumbers(numbers: List<Int>, start: Int, end: Int): List<Int> {
        val expected = (start..end).toSet()
        val given = numbers.toSet()
        return (expected subtract given).toList().sorted()
    }
    
    /**
     * PROBLEM: Find Intersection of Multiple Sets
     * 
     * Given multiple sets, find elements that appear in all of them.
     * 
     * INPUT: List of sets
     * OUTPUT: Set of elements common to all sets
     * 
     * EXAMPLES:
     * Input: [{1, 2, 3, 4}, {2, 3, 4, 5}, {3, 4, 5, 6}]
     * Output: {3, 4}
     * 
     * Input: [{"apple", "banana"}, {"banana", "cherry"}, {"banana", "date"}]
     * Output: {"banana"}
     * 
     * Input: [{1, 2, 3}, {4, 5, 6}, {7, 8, 9}]
     * Output: {} (no common elements)
     * 
     * Input: [{1, 2, 3}, {1, 2, 3}, {1, 2, 3}]
     * Output: {1, 2, 3} (all elements common)
     * 
     * INTUITION:
     * - Use reduce() with intersect operation
     * - Start with first set, then intersect with each subsequent set
     * - Result is set of elements present in all sets
     * - Handle empty list case
     * 
     * TIME COMPLEXITY: O(n) - n total elements across all sets
     * SPACE COMPLEXITY: O(k) - k common elements
     * 
     * @param sets List of sets
     * @return Set of elements common to all sets
     */
    fun <T> findIntersection(sets: List<Set<T>>): Set<T> {
        if (sets.isEmpty()) return emptySet()
        return sets.reduce { acc, set -> acc intersect set }
    }
    
    /**
     * PROBLEM: Find Union of Multiple Sets
     * 
     * Given multiple sets, find all unique elements from all sets.
     * 
     * INPUT: List of sets
     * OUTPUT: Set of all unique elements from all sets
     * 
     * EXAMPLES:
     * Input: [{1, 2, 3}, {4, 5, 6}, {7, 8, 9}]
     * Output: {1, 2, 3, 4, 5, 6, 7, 8, 9}
     * 
     * Input: [{"apple", "banana"}, {"banana", "cherry"}, {"cherry", "date"}]
     * Output: {"apple", "banana", "cherry", "date"}
     * 
     * Input: [{1, 2, 3}, {1, 2, 3}, {1, 2, 3}]
     * Output: {1, 2, 3} (duplicates removed)
     * 
     * Input: [{}, {1, 2}, {3, 4}]
     * Output: {1, 2, 3, 4}
     * 
     * INTUITION:
     * - Use reduce() with union operation
     * - Start with first set, then union with each subsequent set
     * - Result is set of all unique elements from all sets
     * - Handle empty list case
     * 
     * TIME COMPLEXITY: O(n) - n total elements across all sets
     * SPACE COMPLEXITY: O(n) - n unique elements
     * 
     * @param sets List of sets
     * @return Set of all unique elements
     */
    fun <T> findUnion(sets: List<Set<T>>): Set<T> {
        if (sets.isEmpty()) return emptySet()
        return sets.reduce { acc, set -> acc union set }
    }
    
    /**
     * PROBLEM: Check if Sets are Disjoint
     * 
     * Given multiple sets, determine if they are disjoint (have no common elements).
     * 
     * INPUT: List of sets
     * OUTPUT: true if all sets are disjoint, false otherwise
     * 
     * EXAMPLES:
     * Input: [{1, 2, 3}, {4, 5, 6}, {7, 8, 9}]
     * Output: true (no common elements)
     * 
     * Input: [{1, 2, 3}, {3, 4, 5}, {5, 6, 7}]
     * Output: false (3 and 5 are common)
     * 
     * Input: [{1, 2}, {2, 3}]
     * Output: false (2 is common)
     * 
     * Input: [{1, 2, 3}]
     * Output: true (single set is always disjoint)
     * 
     * Input: []
     * Output: true (empty list is considered disjoint)
     * 
     * INTUITION:
     * - Find intersection of all sets
     * - If intersection is empty, sets are disjoint
     * - If intersection has elements, sets are not disjoint
     * - Handle edge cases (empty list, single set)
     * 
     * TIME COMPLEXITY: O(n) - n total elements across all sets
     * SPACE COMPLEXITY: O(k) - k common elements
     * 
     * @param sets List of sets
     * @return true if all sets are disjoint
     */
    fun <T> areDisjoint(sets: List<Set<T>>): Boolean {
        if (sets.size < 2) return true
        return findIntersection(sets).isEmpty()
    }
    
    /**
     * PROBLEM: Generate Power Set
     * 
     * Given a set, generate all possible subsets (power set).
     * 
     * INPUT: A set of elements
     * OUTPUT: Set of all possible subsets
     * 
     * EXAMPLES:
     * Input: {1, 2, 3}
     * Output: {{}, {1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, {1, 2, 3}}
     * 
     * Input: {"a", "b"}
     * Output: {{}, {"a"}, {"b"}, {"a", "b"}}
     * 
     * Input: {1}
     * Output: {{}, {1}}
     * 
     * Input: {}
     * Output: {{}}
     * 
     * INTUITION:
     * - Use bit manipulation to generate all possible combinations
     * - For n elements, there are 2^n possible subsets
     * - Each bit pattern represents a subset
     * - For each bit pattern, check which bits are set
     * - Add corresponding elements to subset
     * 
     * TIME COMPLEXITY: O(2^n) - 2^n subsets for n elements
     * SPACE COMPLEXITY: O(2^n) - 2^n subsets
     * 
     * @param set Input set
     * @return Set of all possible subsets
     */
    fun <T> generatePowerSet(set: Set<T>): Set<Set<T>> {
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
     * PROBLEM: Find Symmetric Difference of Two Sets
     * 
     * Given two sets, find elements that appear in exactly one of the sets.
     * 
     * INPUT: Two sets
     * OUTPUT: Set of elements in exactly one set
     * 
     * EXAMPLES:
     * Input: {1, 2, 3, 4}, {3, 4, 5, 6}
     * Output: {1, 2, 5, 6}
     * 
     * Input: {"apple", "banana"}, {"banana", "cherry"}
     * Output: {"apple", "cherry"}
     * 
     * Input: {1, 2, 3}, {1, 2, 3}
     * Output: {} (no symmetric difference)
     * 
     * Input: {1, 2, 3}, {4, 5, 6}
     * Output: {1, 2, 3, 4, 5, 6} (all elements)
     * 
     * INTUITION:
     * - Find union of both sets
     * - Find intersection of both sets
     * - Subtract intersection from union
     * - Result is elements in exactly one set
     * - Symmetric difference = (A ∪ B) - (A ∩ B)
     * 
     * TIME COMPLEXITY: O(n + m) - n and m elements in sets
     * SPACE COMPLEXITY: O(n + m) - elements in symmetric difference
     * 
     * @param set1 First set
     * @param set2 Second set
     * @return Set of elements in exactly one set
     */
    fun <T> findSymmetricDifference(set1: Set<T>, set2: Set<T>): Set<T> {
        return (set1 union set2) subtract (set1 intersect set2)
    }
    
    /**
     * PROBLEM: Check if Set is Proper Subset
     * 
     * Given two sets, determine if the first set is a proper subset of the second set.
     * A proper subset means all elements of first set are in second set, but sets are not equal.
     * 
     * INPUT: Two sets
     * OUTPUT: true if first set is proper subset of second set
     * 
     * EXAMPLES:
     * Input: {1, 2}, {1, 2, 3, 4}
     * Output: true (all elements of first set are in second, but sets are not equal)
     * 
     * Input: {1, 2, 3}, {1, 2, 3}
     * Output: false (sets are equal, not proper subset)
     * 
     * Input: {1, 2, 3}, {1, 2}
     * Output: false (first set has elements not in second)
     * 
     * Input: {}, {1, 2, 3}
     * Output: true (empty set is proper subset of non-empty set)
     * 
     * Input: {1, 2, 3}, {}
     * Output: false (non-empty set cannot be subset of empty set)
     * 
     * INTUITION:
     * - Check if all elements of first set are in second set
     * - Check if sets are not equal
     * - Both conditions must be true for proper subset
     * - Use set operations for efficient checking
     * 
     * TIME COMPLEXITY: O(n) - n elements in first set
     * SPACE COMPLEXITY: O(1) - constant space
     * 
     * @param subset Candidate subset
     * @param superset Candidate superset
     * @return true if first set is proper subset of second
     */
    fun <T> isProperSubset(subset: Set<T>, superset: Set<T>): Boolean {
        return subset.all { it in superset } && subset != superset
    }
    
    /**
     * Demonstrates set algorithms
     */
    fun demonstrateSetAlgorithms() {
        println("=== SET ALGORITHMS DEMONSTRATION ===\n")
        
        // 1. Duplicate removal
        println("1. DUPLICATE REMOVAL")
        val listWithDuplicates = listOf(1, 2, 2, 3, 3, 4, 5, 5)
        val uniqueList = removeDuplicates(listWithDuplicates)
        println("List with duplicates: $listWithDuplicates")
        println("After removing duplicates: $uniqueList")
        println()
        
        // 2. Common elements
        println("2. COMMON ELEMENTS")
        val list1 = listOf(1, 2, 3, 4, 5)
        val list2 = listOf(4, 5, 6, 7, 8)
        val common = findCommonElements(list1, list2)
        println("List1: $list1")
        println("List2: $list2")
        println("Common elements: $common")
        println()
        
        // 3. Unique elements
        println("3. UNIQUE ELEMENTS")
        val uniqueToFirst = findUniqueElements(list1, list2)
        val uniqueToSecond = findUniqueElements(list2, list1)
        println("Elements unique to List1: $uniqueToFirst")
        println("Elements unique to List2: $uniqueToSecond")
        println()
        
        // 4. Has common elements
        println("4. HAS COMMON ELEMENTS")
        val hasCommon = hasCommonElements(list1, list2)
        println("Lists have common elements: $hasCommon")
        println()
        
        // 5. Subset checking
        println("5. SUBSET CHECKING")
        val subset = listOf(1, 2, 3)
        val superset = listOf(1, 2, 3, 4, 5, 6)
        val isSubset = isSubset(subset, superset)
        println("Subset: $subset")
        println("Superset: $superset")
        println("Is subset: $isSubset")
        println()
        
        // 6. All unique elements
        println("6. ALL UNIQUE ELEMENTS")
        val lists = listOf(list1, list2, listOf(9, 10))
        val allUnique = findAllUniqueElements(lists)
        println("Lists: $lists")
        println("All unique elements: $allUnique")
        println()
        
        // 7. Common to all
        println("7. COMMON TO ALL")
        val commonToAll = findCommonToAll(lists)
        println("Elements common to all lists: $commonToAll")
        println()
        
        // 8. Partitioning
        println("8. PARTITIONING")
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val partitioned = partitionElements(numbers) { it % 2 == 0 }
        println("Numbers: $numbers")
        println("Partitioned by even/odd: $partitioned")
        println()
        
        // 9. Unique occurrences
        println("9. UNIQUE OCCURRENCES")
        val elements = listOf(1, 2, 2, 3, 4, 4, 5, 6, 6)
        val uniqueOccurrences = findUniqueOccurrences(elements)
        val duplicateOccurrences = findDuplicateOccurrences(elements)
        println("Elements: $elements")
        println("Elements appearing once: $uniqueOccurrences")
        println("Elements appearing multiple times: $duplicateOccurrences")
        println()
        
        // 10. Unique characters
        println("10. UNIQUE CHARACTERS")
        val str1 = "hello"
        val str2 = "world"
        val hasUnique1 = hasUniqueCharacters(str1)
        val hasUnique2 = hasUniqueCharacters(str2)
        println("'$str1' has unique characters: $hasUnique1")
        println("'$str2' has unique characters: $hasUnique2")
        println()
        
        // 11. Missing numbers
        println("11. MISSING NUMBERS")
        val givenNumbers = listOf(1, 3, 5, 7, 9)
        val missing = findMissingNumbers(givenNumbers, 1, 10)
        println("Given numbers: $givenNumbers")
        println("Missing numbers in range 1-10: $missing")
        println()
        
        // 12. Power set
        println("12. POWER SET")
        val smallSet = setOf(1, 2, 3)
        val powerSet = generatePowerSet(smallSet)
        println("Set: $smallSet")
        println("Power set: $powerSet")
        println()
        
        // 13. Symmetric difference
        println("13. SYMMETRIC DIFFERENCE")
        val set1 = setOf(1, 2, 3, 4)
        val set2 = setOf(3, 4, 5, 6)
        val symmetricDiff = findSymmetricDifference(set1, set2)
        println("Set1: $set1")
        println("Set2: $set2")
        println("Symmetric difference: $symmetricDiff")
        println()
        
        // 14. Proper subset
        println("14. PROPER SUBSET")
        val properSubset = setOf(1, 2)
        val isProper = isProperSubset(properSubset, set1)
        println("Subset: $properSubset")
        println("Superset: $set1")
        println("Is proper subset: $isProper")
        println()
        
        println("=== SET ALGORITHMS DEMONSTRATION COMPLETE ===\n")
    }
} 