package data_structure.strings

/**
 * STRING ALGORITHMS
 * 
 * This file contains algorithms that use strings including
 * string matching, palindrome problems, string manipulation,
 * and other string-based algorithms.
 * 
 * COMMON STRING ALGORITHMS:
 * - String Matching: Find patterns in strings
 * - Palindrome Problems: Check and manipulate palindromes
 * - String Manipulation: Transform and process strings
 * - Anagrams: Check and find anagrams
 * - String Compression: Compress repeated characters
 * - Longest Common Subsequence: Find LCS between strings
 */

object StringAlgorithms {
    
    /**
     * PROBLEM: Valid Palindrome
     * 
     * Given a string, determine if it is a palindrome, considering only alphanumeric
     * characters and ignoring cases.
     * 
     * INPUT: A string
     * OUTPUT: true if palindrome, false otherwise
     * 
     * EXAMPLES:
     * Input: "A man, a plan, a canal: Panama"
     * Output: true (ignoring non-alphanumeric: "amanaplanacanalpanama")
     * 
     * Input: "race a car"
     * Output: false (ignoring spaces: "raceacar" is not palindrome)
     * 
     * Input: "racecar"
     * Output: true
     * 
     * Input: "hello"
     * Output: false
     * 
     * INTUITION:
     * - Use two pointers starting from both ends
     * - Skip non-alphanumeric characters
     * - Compare characters (case-insensitive)
     * - Move pointers inward until they meet
     * 
     * TIME COMPLEXITY: O(n) - Single pass through string
     * SPACE COMPLEXITY: O(1) - Only constant extra space
     * 
     * @param s Input string
     * @return true if palindrome, false otherwise
     */
    fun isPalindrome(s: String): Boolean {
        var left = 0
        var right = s.length - 1
        
        while (left < right) {
            // Skip non-alphanumeric characters from left
            while (left < right && !s[left].isLetterOrDigit()) {
                left++
            }
            
            // Skip non-alphanumeric characters from right
            while (left < right && !s[right].isLetterOrDigit()) {
                right--
            }
            
            // Compare characters (case-insensitive)
            if (s[left].lowercase() != s[right].lowercase()) {
                return false
            }
            
            left++
            right--
        }
        
        return true
    }
    
    /**
     * PROBLEM: Longest Palindromic Substring
     * 
     * Given a string, find the longest palindromic substring.
     * 
     * INPUT: A string
     * OUTPUT: The longest palindromic substring
     * 
     * EXAMPLES:
     * Input: "babad"
     * Output: "bab" or "aba" (both are valid)
     * 
     * Input: "cbbd"
     * Output: "bb"
     * 
     * Input: "a"
     * Output: "a"
     * 
     * Input: "ac"
     * Output: "a" or "c"
     * 
     * INTUITION:
     * - Use expand around center approach
     * - For each character, expand to find palindrome with odd length
     * - For each pair of characters, expand to find palindrome with even length
     * - Track the longest palindrome found
     * 
     * TIME COMPLEXITY: O(n²) - n centers, each expansion takes O(n)
     * SPACE COMPLEXITY: O(1) - Only constant extra space
     * 
     * @param s Input string
     * @return Longest palindromic substring
     */
    fun longestPalindrome(s: String): String {
        if (s.length < 2) return s
        
        var start = 0
        var maxLength = 1
        
        for (i in s.indices) {
            // Expand around single character (odd length)
            val len1 = expandAroundCenter(s, i, i)
            
            // Expand around two characters (even length)
            val len2 = expandAroundCenter(s, i, i + 1)
            
            val currentMax = maxOf(len1, len2)
            
            if (currentMax > maxLength) {
                start = i - (currentMax - 1) / 2
                maxLength = currentMax
            }
        }
        
        return s.substring(start, start + maxLength)
    }
    
    private fun expandAroundCenter(s: String, left: Int, right: Int): Int {
        var l = left
        var r = right
        
        while (l >= 0 && r < s.length && s[l] == s[r]) {
            l--
            r++
        }
        
        return r - l - 1
    }
    
    /**
     * PROBLEM: Valid Anagram
     * 
     * Given two strings, determine if they are anagrams.
     * 
     * INPUT: Two strings
     * OUTPUT: true if anagrams, false otherwise
     * 
     * EXAMPLES:
     * Input: s = "anagram", t = "nagaram"
     * Output: true
     * 
     * Input: s = "rat", t = "car"
     * Output: false
     * 
     * Input: s = "", t = ""
     * Output: true
     * 
     * Input: s = "hello", t = "world"
     * Output: false
     * 
     * INTUITION:
     * - Use character frequency counting
     * - Count characters in first string
     * - Decrement counts for second string
     * - All counts should be zero for anagrams
     * 
     * TIME COMPLEXITY: O(n) - n is length of strings
     * SPACE COMPLEXITY: O(k) - k is size of character set (26 for lowercase)
     * 
     * @param s First string
     * @param t Second string
     * @return true if anagrams, false otherwise
     */
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        
        val charCount = IntArray(26)
        
        for (char in s) {
            charCount[char - 'a']++
        }
        
        for (char in t) {
            charCount[char - 'a']--
            if (charCount[char - 'a'] < 0) {
                return false
            }
        }
        
        return true
    }
    
    /**
     * PROBLEM: Group Anagrams
     * 
     * Given an array of strings, group the anagrams together.
     * 
     * INPUT: Array of strings
     * OUTPUT: List of groups, where each group contains anagrams
     * 
     * EXAMPLES:
     * Input: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * Output: [["bat"], ["nat", "tan"], ["ate", "eat", "tea"]]
     * 
     * Input: [""]
     * Output: [[""]]
     * 
     * Input: ["a"]
     * Output: [["a"]]
     * 
     * INTUITION:
     * - Use sorted string as key for grouping
     * - For each string, sort its characters to create a key
     * - Group strings with the same key together
     * - Return all groups
     * 
     * TIME COMPLEXITY: O(n * k log k) - n strings, k is max string length
     * SPACE COMPLEXITY: O(n * k) - Store all strings
     * 
     * @param strs Array of strings
     * @return List of anagram groups
     */
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val groups = mutableMapOf<String, MutableList<String>>()
        
        for (str in strs) {
            val key = str.toCharArray().sorted().joinToString("")
            groups.getOrPut(key) { mutableListOf() }.add(str)
        }
        
        return groups.values.toList()
    }
    
    /**
     * PROBLEM: String Compression
     * 
     * Given a string, compress it by replacing consecutive repeated characters
     * with the character followed by its count.
     * 
     * INPUT: A string
     * OUTPUT: Compressed string
     * 
     * EXAMPLES:
     * Input: "aabcccccaaa"
     * Output: "a2b1c5a3"
     * 
     * Input: "abcd"
     * Output: "abcd" (no compression needed)
     * 
     * Input: "aa"
     * Output: "a2"
     * 
     * Input: "a"
     * Output: "a"
     * 
     * INTUITION:
     * - Use two pointers to track current character and count
     * - When character changes, append previous character and count
     * - Handle edge cases (single character, no repeats)
     * - Build result string efficiently
     * 
     * TIME COMPLEXITY: O(n) - Single pass through string
     * SPACE COMPLEXITY: O(n) - Output string
     * 
     * @param chars Input string as character array
     * @return Length of compressed string
     */
    fun compress(chars: CharArray): Int {
        if (chars.isEmpty()) return 0
        
        var writeIndex = 0
        var currentChar = chars[0]
        var count = 1
        
        for (i in 1 until chars.size) {
            if (chars[i] == currentChar) {
                count++
            } else {
                chars[writeIndex++] = currentChar
                if (count > 1) {
                    val countStr = count.toString()
                    for (digit in countStr) {
                        chars[writeIndex++] = digit
                    }
                }
                currentChar = chars[i]
                count = 1
            }
        }
        
        // Handle last group
        chars[writeIndex++] = currentChar
        if (count > 1) {
            val countStr = count.toString()
            for (digit in countStr) {
                chars[writeIndex++] = digit
            }
        }
        
        return writeIndex
    }
    
    /**
     * PROBLEM: Longest Common Subsequence
     * 
     * Given two strings, find the length of their longest common subsequence.
     * 
     * INPUT: Two strings
     * OUTPUT: Length of longest common subsequence
     * 
     * EXAMPLES:
     * Input: text1 = "abcde", text2 = "ace"
     * Output: 3 (LCS is "ace")
     * 
     * Input: text1 = "abc", text2 = "abc"
     * Output: 3 (LCS is "abc")
     * 
     * Input: text1 = "abc", text2 = "def"
     * Output: 0 (no common subsequence)
     * 
     * INTUITION:
     * - Use dynamic programming with 2D table
     * - dp[i][j] = LCS length for text1[0..i-1] and text2[0..j-1]
     * - If characters match: dp[i][j] = dp[i-1][j-1] + 1
     * - If characters don't match: dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     * 
     * TIME COMPLEXITY: O(m * n) - m and n are string lengths
     * SPACE COMPLEXITY: O(m * n) - DP table
     * 
     * @param text1 First string
     * @param text2 Second string
     * @return Length of longest common subsequence
     */
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        val m = text1.length
        val n = text2.length
        val dp = Array(m + 1) { IntArray(n + 1) }
        
        for (i in 1..m) {
            for (j in 1..n) {
                if (text1[i - 1] == text2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1
                } else {
                    dp[i][j] = maxOf(dp[i - 1][j], dp[i][j - 1])
                }
            }
        }
        
        return dp[m][n]
    }
    
    /**
     * PROBLEM: String to Integer (atoi)
     * 
     * Convert a string to a 32-bit signed integer following specific rules.
     * 
     * INPUT: A string
     * OUTPUT: Converted integer
     * 
     * EXAMPLES:
     * Input: "42"
     * Output: 42
     * 
     * Input: "   -42"
     * Output: -42
     * 
     * Input: "4193 with words"
     * Output: 4193
     * 
     * Input: "words and 987"
     * Output: 0
     * 
     * Input: "-91283472332"
     * Output: -2147483648 (clamped to INT_MIN)
     * 
     * INTUITION:
     * - Skip leading whitespace
     * - Handle sign (+ or -)
     * - Read digits until non-digit character
     * - Handle overflow by clamping to INT_MIN/INT_MAX
     * - Return 0 if no valid conversion
     * 
     * TIME COMPLEXITY: O(n) - n is string length
     * SPACE COMPLEXITY: O(1) - Only constant extra space
     * 
     * @param s Input string
     * @return Converted integer
     */
    fun myAtoi(s: String): Int {
        var i = 0
        val n = s.length
        
        // Skip leading whitespace
        while (i < n && s[i] == ' ') {
            i++
        }
        
        if (i == n) return 0
        
        // Handle sign
        var sign = 1
        if (s[i] == '+' || s[i] == '-') {
            sign = if (s[i] == '-') -1 else 1
            i++
        }
        
        // Read digits
        var result = 0
        while (i < n && s[i].isDigit()) {
            val digit = s[i] - '0'
            
            // Check for overflow
            if (result > Int.MAX_VALUE / 10 || 
                (result == Int.MAX_VALUE / 10 && digit > Int.MAX_VALUE % 10)) {
                return if (sign == 1) Int.MAX_VALUE else Int.MIN_VALUE
            }
            
            result = result * 10 + digit
            i++
        }
        
        return sign * result
    }
    
    /**
     * PROBLEM: Longest Substring Without Repeating Characters
     * 
     * Given a string, find the length of the longest substring without repeating characters.
     * 
     * INPUT: A string
     * OUTPUT: Length of longest substring without repeating characters
     * 
     * EXAMPLES:
     * Input: "abcabcbb"
     * Output: 3 (substring "abc")
     * 
     * Input: "bbbbb"
     * Output: 1 (substring "b")
     * 
     * Input: "pwwkew"
     * Output: 3 (substring "wke")
     * 
     * Input: ""
     * Output: 0
     * 
     * INTUITION:
     * - Use sliding window with two pointers
     * - Use set to track characters in current window
     * - Expand window by moving right pointer
     * - Shrink window by moving left pointer when duplicate found
     * - Track maximum window size
     * 
     * TIME COMPLEXITY: O(n) - Each character visited at most twice
     * SPACE COMPLEXITY: O(min(m, n)) - m is character set size, n is string length
     * 
     * @param s Input string
     * @return Length of longest substring without repeating characters
     */
    fun lengthOfLongestSubstring(s: String): Int {
        val charSet = mutableSetOf<Char>()
        var left = 0
        var maxLength = 0
        
        for (right in s.indices) {
            while (s[right] in charSet) {
                charSet.remove(s[left])
                left++
            }
            
            charSet.add(s[right])
            maxLength = maxOf(maxLength, right - left + 1)
        }
        
        return maxLength
    }
    
    /**
     * PROBLEM: Minimum Window Substring
     * 
     * Given two strings s and t, find the minimum window substring of s such that
     * every character in t is included in the window.
     * 
     * INPUT: Two strings s and t
     * OUTPUT: Minimum window substring, or empty string if not found
     * 
     * EXAMPLES:
     * Input: s = "ADOBECODEBANC", t = "ABC"
     * Output: "BANC"
     * 
     * Input: s = "a", t = "a"
     * Output: "a"
     * 
     * Input: s = "a", t = "aa"
     * Output: "" (not found)
     * 
     * INTUITION:
     * - Use sliding window with two pointers
     * - Use hash map to track required character counts
     * - Expand window until all characters are included
     * - Shrink window to find minimum valid window
     * - Track minimum window found
     * 
     * TIME COMPLEXITY: O(n) - n is length of string s
     * SPACE COMPLEXITY: O(k) - k is size of character set
     * 
     * @param s Source string
     * @param t Target string
     * @return Minimum window substring
     */
    fun minWindow(s: String, t: String): String {
        if (s.isEmpty() || t.isEmpty()) return ""
        
        val charCount = mutableMapOf<Char, Int>()
        for (char in t) {
            charCount[char] = charCount.getOrDefault(char, 0) + 1
        }
        
        var left = 0
        var minStart = 0
        var minLength = Int.MAX_VALUE
        var required = charCount.size
        var formed = 0
        
        for (right in s.indices) {
            val char = s[right]
            
            if (char in charCount) {
                charCount[char] = charCount[char]!! - 1
                if (charCount[char] == 0) {
                    formed++
                }
            }
            
            while (formed == required) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1
                    minStart = left
                }
                
                val leftChar = s[left]
                if (leftChar in charCount) {
                    charCount[leftChar] = charCount[leftChar]!! + 1
                    if (charCount[leftChar]!! > 0) {
                        formed--
                    }
                }
                left++
            }
        }
        
        return if (minLength == Int.MAX_VALUE) "" else s.substring(minStart, minStart + minLength)
    }
    
    /**
     * Demonstrates string algorithms
     */
    fun demonstrateStringAlgorithms() {
        println("=== STRING ALGORITHMS DEMONSTRATION ===\n")
        
        // 1. Valid Palindrome
        println("1. VALID PALINDROME")
        val palindromeTests = listOf(
            "A man, a plan, a canal: Panama",
            "race a car",
            "racecar",
            "hello"
        )
        for (test in palindromeTests) {
            println("'$test': ${isPalindrome(test)}")
        }
        println()
        
        // 2. Longest Palindromic Substring
        println("2. LONGEST PALINDROMIC SUBSTRING")
        val lpsTests = listOf("babad", "cbbd", "a", "ac")
        for (test in lpsTests) {
            println("'$test': '${longestPalindrome(test)}'")
        }
        println()
        
        // 3. Valid Anagram
        println("3. VALID ANAGRAM")
        val anagramPairs = listOf(
            Pair("anagram", "nagaram"),
            Pair("rat", "car"),
            Pair("", ""),
            Pair("hello", "world")
        )
        for ((s, t) in anagramPairs) {
            println("'$s' and '$t': ${isAnagram(s, t)}")
        }
        println()
        
        // 4. Group Anagrams
        println("4. GROUP ANAGRAMS")
        val groupAnagramsInput = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
        val groups = groupAnagrams(groupAnagramsInput)
        println("Input: ${groupAnagramsInput.contentToString()}")
        println("Groups: $groups")
        println()
        
        // 5. String Compression
        println("5. STRING COMPRESSION")
        val compressionTests = listOf("aabcccccaaa", "abcd", "aa", "a")
        for (test in compressionTests) {
            val chars = test.toCharArray()
            val length = compress(chars)
            println("'$test': '${chars.take(length).joinToString("")}' (length: $length)")
        }
        println()
        
        // 6. Longest Common Subsequence
        println("6. LONGEST COMMON SUBSEQUENCE")
        val lcsPairs = listOf(
            Pair("abcde", "ace"),
            Pair("abc", "abc"),
            Pair("abc", "def")
        )
        for ((s, t) in lcsPairs) {
            println("'$s' and '$t': ${longestCommonSubsequence(s, t)}")
        }
        println()
        
        // 7. String to Integer
        println("7. STRING TO INTEGER")
        val atoiTests = listOf("42", "   -42", "4193 with words", "words and 987", "-91283472332")
        for (test in atoiTests) {
            println("'$test': ${myAtoi(test)}")
        }
        println()
        
        // 8. Longest Substring Without Repeating Characters
        println("8. LONGEST SUBSTRING WITHOUT REPEATING CHARACTERS")
        val lswrcTests = listOf("abcabcbb", "bbbbb", "pwwkew", "")
        for (test in lswrcTests) {
            println("'$test': ${lengthOfLongestSubstring(test)}")
        }
        println()
        
        // 9. Minimum Window Substring
        println("9. MINIMUM WINDOW SUBSTRING")
        val mwsPairs = listOf(
            Pair("ADOBECODEBANC", "ABC"),
            Pair("a", "a"),
            Pair("a", "aa")
        )
        for ((s, t) in mwsPairs) {
            println("'$s' and '$t': '${minWindow(s, t)}'")
        }
        println()
        
        println("=== STRING ALGORITHMS DEMONSTRATION COMPLETE ===\n")
    }
} 