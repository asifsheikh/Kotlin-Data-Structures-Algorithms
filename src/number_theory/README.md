# NUMBER THEORY - Quick Reference

## Overview
Number theory is a branch of mathematics that deals with properties and relationships of numbers, particularly integers. It's essential for:
- **Competitive programming**: Many problems require number theory concepts
- **Cryptography**: Prime numbers, modular arithmetic, etc.
- **Algorithm optimization**: GCD, LCM, prime factorization
- **Mathematical problem solving**: Permutations, combinations, sequences

## Basic Number Theory Concepts

### **Prime Numbers**
- **Prime**: Number > 1 with no positive divisors other than 1 and itself
- **Composite**: Number > 1 that is not prime
- **Prime factorization**: Breaking a number into product of primes
- **Sieve of Eratosthenes**: Efficient way to find all primes up to n

### **Divisors and Multiples**
- **Divisors**: Numbers that divide another number evenly
- **Multiples**: Numbers that are products of another number
- **Perfect numbers**: Numbers equal to sum of their proper divisors
- **Abundant/Deficient**: Numbers greater/less than sum of proper divisors

### **GCD and LCM**
- **GCD (Greatest Common Divisor)**: Largest number that divides both numbers
- **LCM (Least Common Multiple)**: Smallest number divisible by both numbers
- **Euclidean Algorithm**: Efficient way to find GCD
- **Relationship**: GCD(a,b) × LCM(a,b) = a × b

### **Modular Arithmetic**
- **Modulo operation**: Remainder after division
- **Congruence**: a ≡ b (mod m) means a and b have same remainder when divided by m
- **Modular inverse**: Number that when multiplied gives remainder 1
- **Fermat's Little Theorem**: a^(p-1) ≡ 1 (mod p) for prime p

## Common Operations

### **Prime Operations**
- **Check if prime**: Trial division, Miller-Rabin test
- **Prime factorization**: Trial division, Pollard's rho
- **Generate primes**: Sieve of Eratosthenes, segmented sieve
- **Count primes**: Prime counting function π(n)

### **GCD/LCM Operations**
- **Euclidean algorithm**: GCD(a,b) = GCD(b, a mod b)
- **Extended Euclidean**: Find x,y such that ax + by = GCD(a,b)
- **Binary GCD**: Faster GCD using bit operations
- **LCM calculation**: LCM(a,b) = (a × b) / GCD(a,b)

### **Modular Operations**
- **Modular addition**: (a + b) mod m
- **Modular multiplication**: (a × b) mod m
- **Modular exponentiation**: a^b mod m (fast power)
- **Modular inverse**: Extended Euclidean or Fermat's theorem

### **Combinatorics**
- **Factorial**: n! = n × (n-1) × ... × 1
- **Permutations**: P(n,r) = n! / (n-r)!
- **Combinations**: C(n,r) = n! / (r! × (n-r)!)
- **Catalan numbers**: C(n) = (1/(n+1)) × C(2n,n)

## Time Complexity Guide

| Operation | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| Prime check (trial) | O(√n) | O(1) | Check if number is prime |
| Prime check (Miller-Rabin) | O(k log³n) | O(1) | Probabilistic prime test |
| Prime factorization | O(√n) | O(log n) | Factorize into primes |
| Sieve of Eratosthenes | O(n log log n) | O(n) | Generate primes up to n |
| GCD (Euclidean) | O(log min(a,b)) | O(1) | Find greatest common divisor |
| LCM | O(log min(a,b)) | O(1) | Find least common multiple |
| Modular exponentiation | O(log b) | O(1) | Calculate a^b mod m |
| Factorial | O(n) | O(1) | Calculate n! |
| Permutations | O(n) | O(1) | Calculate P(n,r) |
| Combinations | O(n) | O(1) | Calculate C(n,r) |

## Common Use Cases

### **1. Prime Number Problems**
```kotlin
// Check if number is prime
val isPrime = isPrime(17)  // true

// Get prime factors
val factors = getPrimeFactors(84)  // [2, 2, 3, 7]

// Generate primes up to n
val primes = generatePrimes(100)  // [2, 3, 5, 7, 11, ...]
```

### **2. GCD/LCM Problems**
```kotlin
// Find GCD
val gcd = findGCD(48, 18)  // 6

// Find LCM
val lcm = findLCM(12, 18)  // 36

// Extended Euclidean
val (x, y) = extendedGCD(48, 18)  // x, y such that 48x + 18y = 6
```

### **3. Modular Arithmetic**
```kotlin
// Fast modular exponentiation
val result = modPow(2, 10, 1000)  // 2^10 mod 1000 = 24

// Modular inverse
val inverse = modInverse(3, 7)  // 5 (3 * 5 ≡ 1 mod 7)

// Chinese remainder theorem
val solution = chineseRemainder(listOf(2, 3, 2), listOf(3, 5, 7))
```

### **4. Combinatorics**
```kotlin
// Factorial
val fact = factorial(5)  // 120

// Permutations
val perms = permutations(5, 3)  // 60

// Combinations
val combs = combinations(5, 3)  // 10

// Catalan numbers
val catalan = catalanNumber(4)  // 14
```

## Best Practices

### **1. Use Efficient Algorithms**
```kotlin
// Good: Euclidean algorithm for GCD
fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

// Avoid: Trial division for large numbers
// Use Miller-Rabin for probabilistic primality testing
```

### **2. Handle Edge Cases**
```kotlin
// Always check for zero and negative numbers
fun safeGCD(a: Int, b: Int): Int {
    if (a == 0) return b
    if (b == 0) return a
    return gcd(kotlin.math.abs(a), kotlin.math.abs(b))
}
```

### **3. Use Modular Arithmetic for Large Numbers**
```kotlin
// For large calculations, use modular arithmetic
fun largeFactorial(n: Int, mod: Int): Int {
    var result = 1
    for (i in 2..n) {
        result = (result * i) % mod
    }
    return result
}
```

### **4. Precompute When Possible**
```kotlin
// Precompute factorials for combinatorics
val factorial = IntArray(100001) { 1 }
for (i in 2..100000) {
    factorial[i] = (factorial[i-1] * i) % MOD
}
```

## Common Pitfalls

### **1. Integer Overflow**
```kotlin
// Wrong: May overflow
val result = a * b / gcd(a, b)  // LCM

// Correct: Use Long or modular arithmetic
val result = (a.toLong() * b / gcd(a, b)).toInt()
```

### **2. Precision Issues**
```kotlin
// Wrong: Floating point precision
val result = n / 2.0 * (n + 1)  // Sum of first n numbers

// Correct: Integer arithmetic
val result = n * (n + 1) / 2
```

### **3. Not Handling Edge Cases**
```kotlin
// Always check for edge cases
fun safeModInverse(a: Int, m: Int): Int {
    if (m <= 1) return 0
    if (a == 0) return 0
    // ... rest of implementation
}
```

## File Structure

```
src/number_theory/
├── README.md                           # This file
├── NUMBER_THEORY_COMPLEXITY_GUIDE.md   # Detailed complexity analysis
├── NumberTheoryBasics.kt              # Basic concepts and operations
├── NumberTheoryOperations.kt          # Common operations
├── NumberTheoryUtilities.kt           # Utility functions
└── algorithms/                        # Algorithm implementations
    ├── PrimeNumbers.kt                # Prime operations
    ├── GCD_LCM.kt                     # GCD and LCM algorithms
    ├── ModularArithmetic.kt           # Modular operations
    ├── Combinatorics.kt               # Permutations and combinations
    ├── Divisors.kt                    # Divisors and related
    └── MathematicalSequences.kt       # Mathematical sequences
```

## Quick Reference

### **Basic Operations**
- Prime check: `isPrime(n)`
- GCD: `gcd(a, b)`
- LCM: `lcm(a, b)`
- Modular power: `modPow(base, exp, mod)`
- Factorial: `factorial(n)`

### **Common Patterns**
- Prime factorization: `getPrimeFactors(n)`
- Sieve of Eratosthenes: `generatePrimes(n)`
- Extended Euclidean: `extendedGCD(a, b)`
- Modular inverse: `modInverse(a, m)`
- Combinations: `combinations(n, r)`

### **Mathematical Formulas**
- Sum of first n numbers: `n * (n + 1) / 2`
- Sum of squares: `n * (n + 1) * (2n + 1) / 6`
- Sum of cubes: `(n * (n + 1) / 2)²`
- Catalan numbers: `C(n) = (1/(n+1)) * C(2n,n)`

This comprehensive guide covers all essential number theory concepts for competitive programming and mathematical problem solving.
