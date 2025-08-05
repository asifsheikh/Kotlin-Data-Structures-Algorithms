package data_structure.lists

/**
 * LIST CREATION - Quick Reference
 * All Kotlin list creation methods in one place
 */

object ListCreation {
    
    /**
     * All List Creation Methods
     * Complete reference for creating lists in Kotlin
     */
    fun allListCreationMethods() {
        // === IMMUTABLE LISTS ===
        val emptyList = emptyList<Int>()                      // []
        val listWithValues = listOf(1, 2, 3, 4, 5)           // [1, 2, 3, 4, 5]
        val listWithSize = List(5) { it * 2 }                // [0, 2, 4, 6, 8]
        val listWithDefault = List(3) { 42 }                 // [42, 42, 42]
        
        // === MUTABLE LISTS ===
        val mutableEmpty = mutableListOf<Int>()              // []
        val mutableWithValues = mutableListOf(1, 2, 3, 4, 5) // [1, 2, 3, 4, 5]
        val mutableWithSize = MutableList(5) { it * 2 }      // [0, 2, 4, 6, 8]
        val mutableWithDefault = MutableList(3) { 42 }       // [42, 42, 42]
        
        // === ARRAYLIST ===
        val arrayList = ArrayList<Int>()                      // []
        val arrayListWithValues = ArrayList(listOf(1, 2, 3)) // [1, 2, 3]
        val arrayListWithCapacity = ArrayList<Int>(10)        // [] with capacity 10
        
        // === FROM OTHER COLLECTIONS ===
        val array = intArrayOf(1, 2, 3, 4, 5)
        val listFromArray = array.toList()                    // [1, 2, 3, 4, 5]
        val mutableListFromArray = array.toMutableList()      // [1, 2, 3, 4, 5]
        val set = setOf(1, 2, 3, 4, 5)
        val listFromSet = set.toList()                        // [1, 2, 3, 4, 5]
        val map = mapOf("a" to 1, "b" to 2, "c" to 3)
        val listFromMapKeys = map.keys.toList()               // ["a", "b", "c"]
        val listFromMapValues = map.values.toList()           // [1, 2, 3]
        val string = "hello"
        val listFromString = string.toList()                  // ['h', 'e', 'l', 'l', 'o']
        
        // === FROM RANGES AND SEQUENCES ===
        val rangeList = (1..5).toList()                       // [1, 2, 3, 4, 5]
        val rangeStepList = (0..10 step 2).toList()           // [0, 2, 4, 6, 8, 10]
        val reverseRangeList = (5 downTo 1).toList()          // [5, 4, 3, 2, 1]
        val sequenceList = sequenceOf(1, 2, 3, 4, 5).toList() // [1, 2, 3, 4, 5]
        val infiniteSequence = generateSequence(1) { it * 2 }.take(5).toList() // [1, 2, 4, 8, 16]
        
        // === SPECIAL PATTERNS ===
        val repeatedList = List(5) { 42 }                     // [42, 42, 42, 42, 42]
        val alternatingList = List(6) { if (it % 2 == 0) 0 else 1 } // [0, 1, 0, 1, 0, 1]
        val randomList = List(5) { (1..100).random() }        // Random numbers
        val shuffledList = (1..10).toList().shuffled()        // Shuffled [1..10]
        
        // === WITH FUNCTIONS ===
        val mappedList = (1..5).map { it * 2 }               // [2, 4, 6, 8, 10]
        val mappedWithIndex = (1..5).mapIndexed { index, value -> "$index:$value" } // ["0:1", "1:2", "2:3", "3:4", "4:5"]
        val filteredList = (1..10).filter { it % 2 == 0 }     // [2, 4, 6, 8, 10]
        val flatMappedList = (1..3).flatMap { listOf(it, it * 10) } // [1, 10, 2, 20, 3, 30]
        
        // === ZIP OPERATIONS ===
        val list1 = listOf(1, 2, 3)
        val list2 = listOf("a", "b", "c")
        val zippedList = list1.zip(list2)                     // [(1, "a"), (2, "b"), (3, "c")]
        val zippedWithTransform = list1.zip(list2) { num, str -> "$num$str" } // ["1a", "2b", "3c"]
        
        // === MATRIX AS LIST OF LISTS ===
        val matrix = List(3) { row -> List(3) { col -> row * 3 + col } } // [[0, 1, 2], [3, 4, 5], [6, 7, 8]]
    }
} 