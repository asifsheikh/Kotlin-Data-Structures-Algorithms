package data_structure.strings

fun exploreStringOperations() {
    // --- Immutable String Creation ---
    val str1 = "Hello, Kotlin!"                               // "Hello, Kotlin!"
    val str2 = String(charArrayOf('K', 'o', 't', 'l', 'i', 'n'))  // "Kotlin"
    val multiline = """
        This is
        a multiline
        string
    """.trimIndent()                                          // "This is\na multiline\nstring"

    // --- Accessing Characters ---
    val firstChar = str1[0]                                   // 'H'
    val lastChar = str1[str1.lastIndex]                       // '!'
    val getOrElseChar = str1.getOrElse(100) { '?' }           // '?' fallback, safe access

    // --- String Properties ---
    val length = str1.length                                   // 14
    val isEmpty = str1.isEmpty()                               // false
    val isBlank = "   ".isBlank()                              // true (only whitespace)
    val isNotEmpty = str1.isNotEmpty()                         // true

    // --- String Comparison ---
    val isEqual = str1 == "Hello, Kotlin!"                     // true (value equality)
    val equalsIgnoreCase = str1.equals("hello, kotlin!", ignoreCase = true) // true
    val compareResult = str1.compareTo("hello, kotlin!", ignoreCase = true) // 0 if equal

    // --- String Concatenation ---
    val concat = str1 + " Welcome!"                            // "Hello, Kotlin! Welcome!"
    val concatUsingTemplate = "$str1 Welcome!"                 // Same as above

    // --- Substrings ---
    val sub = str1.substring(7, 13)                            // "Kotlin" (exclusive end)
    // safe substring avoiding exceptions
    val safeSub = if (str1.length >= 13) str1.substring(7, 13) else ""

    val startsWithHello = str1.startsWith("Hello")             // true
    val endsWithExclamation = str1.endsWith("!")               // true

    // --- Searching ---
    val containsKotlin = str1.contains("Kotlin")               // true
    val indexOfKotlin = str1.indexOf("Kotlin")                  // 7 (first occurrence)
    val lastIndexOfO = str1.lastIndexOf('o')                    // 9 (last occurrence)
    val indexOrNull = str1.indexOf("World").takeIf { it >= 0 }  // null if not found

    // --- Case Conversion ---
    val upper = str1.uppercase()                                // "HELLO, KOTLIN!"
    val lower = str1.lowercase()                                // "hello, kotlin!"

    // --- Splitting ---
    val words = str1.split(", ", " ")                           // ["Hello", "Kotlin!"]
    val charList = str1.toList()                                // List<Char> ['H', 'e', 'l', ...]

    // splitting by regex (e.g. whitespace)
    val splitByWhitespace = "Kotlin is fun".split("\\s+".toRegex())  // ["Kotlin", "is", "fun"]

    // splitting into fixed length chunks (extension function created below)
    val chunks = str1.chunked(3)                                // ["Hel", "lo,", " Ko", "tli", "n!"]

    // --- Iteration ---
    for (char in str1) {
        print("$char ")                                         // H e l l o ,   K o t l i n !
    }
    println()

    // --- String Templates & Raw Strings ---
    val name = "Asif"
    val greeting = "Hello, $name!"                              // "Hello, Asif!"
    val expression = "Sum: ${3 + 7}"                            // "Sum: 10"
    val rawString = """
        |First Line
        |Second Line
    """.trimMargin()                                           // "First Line\nSecond Line"

    // --- StringBuilder (mutable string) ---
    val sb = StringBuilder()
    sb.append("Hello")                                          // "Hello"
    sb.append(", World!")                                       // "Hello, World!"
    sb.insert(5, " Kotlin")                                     // "Hello Kotlin, World!"
    sb.replace(6, 12, "Java")                                   // "Hello Java, World!"
    sb.delete(5, 10)                                            // "Hello, World!"
    sb.reverse()                                                // "!dlroW ,olleH"
    val sbLength = sb.length                                     // 13
    val sbString = sb.toString()                                // "!dlroW ,olleH"

    sb.clear()                                                  // sb reset empty
    val capacity = sb.capacity()                                // current buffer size, default usually 16

    // --- Useful String Functions (extension functions) ---
    val trimmed = "   Kotlin  ".trim()                          // "Kotlin"
    val trimmedIndent = """
        |Hello
        |Kotlin
    """.trimMargin()                                           // "Hello\nKotlin"

    val repeated = "Hi".repeat(3)                               // "HiHiHi"

    // --- Checking prefixes/suffixes with ignore case ---
    val startsWithKotlinIgnoreCase = str1.startsWith("kotlin", ignoreCase = true)  // false in this example

    // --- Unicode-aware String length and code points ---
    val codePointCount = str1.codePointCount(0, str1.length)    // may differ for some Unicode chars
    val isAsciiOnly = str1.all { it.code < 128 }                // check ASCII-only

    // --- Regex matching ---
    val isNumber = "12345".matches(Regex("\\d+"))               // true
    val regexSplit = "apple;banana;carrot".split(";")           // ["apple","banana","carrot"]

    // --- Reversing string ---
    val reversedStr = str1.reversed()                           // "!niltok ,olleH"

    // --- Check if all characters satisfy condition ---
    val allLetters = str1.all { it.isLetter() }                  // false due to punctuation
    val anyWhitespace = str1.any { it.isWhitespace() }           // true if contains spaces

    // --- Replace ---
    val replaced = str1.replace("Kotlin", "Java")                // "Hello, Java!"

    // --- Map to transformed chars and join ---
    val rotated = str1.map { c -> (c.code + 13).toChar() }.joinToString("")  // ROT13 like

    // --- Convert String to ByteArray and back (with charset) ---
    val utf8Bytes = str1.toByteArray(Charsets.UTF_8)
    val strFromBytes = String(utf8Bytes, Charsets.UTF_8)

    // --- Check for palindrome (ignore case and spaces) ---
    val s2 = "A man a plan a canal Panama"
    val isPalindrome = s2.filter { it.isLetterOrDigit() }
        .lowercase() == s2.filter { it.isLetterOrDigit() }
        .lowercase()
        .reversed()

    println("Is '$s2' palindrome? $isPalindrome")  // true
    
    // --- Panagram Check ---
    val panagramSentence = "The quick brown fox jumps over the lazy dog"
    val isPanagramResult = isPanagram(panagramSentence)
    println("Is '$panagramSentence' a panagram? $isPanagramResult")  // true
    
    val nonPanagramSentence = "Hello world"
    val isNotPanagram = isPanagram(nonPanagramSentence)
    println("Is '$nonPanagramSentence' a panagram? $isNotPanagram")  // false
}

// --- Helper extension to split string into fixed size chunks ---
fun String.chunked(size: Int): List<String> {
    require(size > 0) { "Size must be positive" }
    val result = mutableListOf<String>()
    var i = 0
    while (i < length) {
        val end = minOf(i + size, length)
        result.add(substring(i, end))
        i += size
    }
    return result
}

// --- Panagram Check ---
fun isPanagram(sentence: String): Boolean {
    val seen = mutableSetOf<Char>()
    for (char in sentence.lowercase()) {
        if (char.isLetter()) {
            seen.add(char)
        }
    }
    return seen.size == 26
}
