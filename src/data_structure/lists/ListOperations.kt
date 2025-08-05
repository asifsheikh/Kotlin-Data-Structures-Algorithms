package data_structure.lists

// Demonstrates creation, access, modification, filtering, sorting, and other operations on Lists in Kotlin
fun exploreListOperations() {
    // --- Creation ---
    val immutableList = listOf(1, 2, 3, 4, 5)
    val mutableList = mutableListOf(10, 20, 30)
    val emptyList = emptyList<String>()
    val initializedList = List(5) { it * 2 }  // [0, 2, 4, 6, 8]

    // --- Accessing Elements ---
    val first = immutableList[0]
    val last = immutableList[immutableList.size - 1]
    val getOrDefault = immutableList.getOrElse(10) { -1 }  // -1

    // --- Modifying (Mutable Only) ---
    mutableList.add(40)
    mutableList.add(1, 15)          // insert at index
    mutableList[0] = 5              // update value
    mutableList.remove(20)          // remove by value
    mutableList.removeAt(2)         // remove by index
    mutableList.clear()             // remove all elements

    // --- Size and Emptiness ---
    val size = immutableList.size
    val isEmpty = immutableList.isEmpty()
    val isNotEmpty = mutableList.isNotEmpty()

    // --- Iteration ---
    for (item in immutableList) println(item)
    immutableList.forEach { println(it) }
    immutableList.forEachIndexed { index, value -> println("[$index] = $value") }

    // --- Searching ---
    val contains3 = 3 in immutableList
    val indexOf2 = immutableList.indexOf(2)
    val lastIndex = immutableList.lastIndexOf(3)
    val findEven = immutableList.find { it % 2 == 0 }

    // --- Filtering ---
    val evenList = immutableList.filter { it % 2 == 0 }
    val mapped = immutableList.map { it * 10 }

    // --- Sorting ---
    val unsortedList = mutableListOf(5, 1, 4, 2, 3)
    val sorted = unsortedList.sorted()            // returns new list
    val sortedDesc = unsortedList.sortedDescending()
    unsortedList.sort()                           // in-place
    unsortedList.sortDescending()                 // in-place descending

    // --- Distinct & Set-like Operations ---
    val withDuplicates = listOf(1, 2, 2, 3, 3, 3)
    val noDuplicates = withDuplicates.distinct()  // [1, 2, 3]

    // --- Zipping & Pairing ---
    val letters = listOf("A", "B", "C")
    val numbers = listOf(1, 2, 3)
    val zipped = letters.zip(numbers)            // [("A",1),("B",2),("C",3)]
    val unzipped = zipped.unzip()                // Pair<List<String>, List<Int>>

    // --- Chunking & Windowing ---
    val chunked = (1..10).toList().chunked(3)     // [[1,2,3],[4,5,6],[7,8,9],[10]]
    val windowed = (1..5).toList().windowed(3)    // [[1,2,3],[2,3,4],[3,4,5]]

    // --- Reversing and Shuffling ---
    val reversed = immutableList.reversed()
    val shuffled = immutableList.shuffled()

    // --- Join and String Operations ---
    val joined = immutableList.joinToString(separator = "-") // "1-2-3-4-5"

    // --- SubLists ---
    val subList = immutableList.subList(1, 4)     // [2, 3, 4]

    // --- Conversion ---
    val toSet = immutableList.toSet()
    val toMutableList = immutableList.toMutableList()
    val toTypedArray = immutableList.toTypedArray()
}