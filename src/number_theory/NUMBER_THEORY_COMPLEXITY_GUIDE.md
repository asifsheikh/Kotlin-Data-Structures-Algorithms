# NUMBER THEORY COMPLEXITY GUIDE

## Overview
This guide provides detailed time and space complexity analysis for number theory operations and algorithms. Understanding these complexities is crucial for optimizing performance in competitive programming and mathematical problem solving.

## Prime Number Operations

### **Prime Checking**

| Algorithm | Time | Space | Description |
|-----------|------|-------|-------------|
| Trial division | O(√n) | O(1) | Check each divisor up to √n |
| Trial division (optimized) | O(√n) | O(1) | Check only prime divisors |
| Miller-Rabin (deterministic) | O(log⁴n) | O(1) | For numbers < 2^64 |
| Miller-Rabin (probabilistic) | O(k log³n) | O(1) | k = number of bases |
| AKS primality test | O(log¹²n) | O(log⁶n) | Deterministic polynomial time |

**Trial Division**:
```kotlin
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (i in 3..kotlin.math.sqrt(n.toDouble()).toInt() step 2) {
        if (n % i == 0) return false
    }
    return true
}
```

**Miller-Rabin (Probabilistic)**:
```kotlin
fun millerRabin(n: Long, k: Int = 5): Boolean {
    if (n < 2) return false
    if (n == 2L || n == 3L) return true
    if (n % 2 == 0L) return false
    
    var d = n - 1
    var r = 0
    while (d % 2 == 0L) {
        d /= 2
        r++
    }
    
    repeat(k) {
        val a = (2..n-2).random().toLong()
        var x = modPow(a, d, n)
        if (x == 1L || x == n - 1) continue
        
        repeat(r - 1) {
            x = (x * x) % n
            if (x == n - 1L) return@repeat
        }
        return false
    }
    return true
}
```

### **Prime Factorization**

| Algorithm | Time | Space | Description |
|-----------|------|-------|-------------|
| Trial division | O(√n) | O(log n) | Check each prime up to √n |
| Pollard's rho | O(n^(1/4)) | O(1) | Probabilistic factorization |
| Quadratic sieve | O(e^(√(log n log log n))) | O(n^(1/2)) | For large numbers |
| General number field sieve | O(e^((log n)^(1/3) (log log n)^(2/3))) | O(n^(1/3)) | Fastest known |

**Trial Division Factorization**:
```kotlin
fun getPrimeFactors(n: Long): List<Long> {
    val factors = mutableListOf<Long>()
    var num = n
    
    // Check for 2
    while (num % 2 == 0L) {
        factors.add(2)
        num /= 2
    }
    
    // Check odd numbers
    for (i in 3..kotlin.math.sqrt(num.toDouble()).toInt() step 2) {
        while (num % i == 0L) {
            factors.add(i.toLong())
            num /= i
        }
    }
    
    if (num > 2) factors.add(num)
    return factors
}
```

### **Prime Generation**

| Algorithm | Time | Space | Description |
|-----------|------|-------|-------------|
| Sieve of Eratosthenes | O(n log log n) | O(n) | Generate all primes ≤ n |
| Segmented sieve | O(n log log n) | O(√n) | For large ranges |
| Wheel factorization | O(n log log n) | O(n) | Optimized with wheel |

**Sieve of Eratosthenes**:
```kotlin
fun generatePrimes(n: Int): List<Int> {
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
    
    return (2..n).filter { isPrime[it] }
}
```

## GCD and LCM Operations

### **Greatest Common Divisor (GCD)**

| Algorithm | Time | Space | Description |
|-----------|------|-------|-------------|
| Euclidean algorithm | O(log min(a,b)) | O(1) | Standard GCD algorithm |
| Binary GCD | O(log min(a,b)) | O(1) | Faster for large numbers |
| Extended Euclidean | O(log min(a,b)) | O(1) | Find Bézout coefficients |

**Euclidean Algorithm**:
```kotlin
fun gcd(a: Long, b: Long): Long {
    return if (b == 0L) a else gcd(b, a % b)
}
```

**Binary GCD (Stein's Algorithm)**:
```kotlin
fun binaryGCD(a: Long, b: Long): Long {
    if (a == 0L) return b
    if (b == 0L) return a
    
    var u = a
    var v = b
    var shift = 0
    
    while (((u or v) and 1L) == 0L) {
        u = u shr 1
        v = v shr 1
        shift++
    }
    
    while ((u and 1L) == 0L) u = u shr 1
    while ((v and 1L) == 0L) v = v shr 1
    
    while (u != v) {
        if (u > v) {
            u -= v
            while ((u and 1L) == 0L) u = u shr 1
        } else {
            v -= u
            while ((v and 1L) == 0L) v = v shr 1
        }
    }
    
    return u shl shift
}
```

**Extended Euclidean Algorithm**:
```kotlin
data class BezoutCoefficients(val x: Long, val y: Long, val gcd: Long)

fun extendedGCD(a: Long, b: Long): BezoutCoefficients {
    if (b == 0L) return BezoutCoefficients(1, 0, a)
    
    val result = extendedGCD(b, a % b)
    return BezoutCoefficients(
        result.y,
        result.x - (a / b) * result.y,
        result.gcd
    )
}
```

### **Least Common Multiple (LCM)**

| Operation | Time | Space | Description |
|-----------|------|-------|-------------|
| LCM calculation | O(log min(a,b)) | O(1) | Using GCD: LCM(a,b) = (a×b)/GCD(a,b) |

```kotlin
fun lcm(a: Long, b: Long): Long {
    return (a / gcd(a, b)) * b  // Avoid overflow
}
```

## Modular Arithmetic

### **Modular Operations**

| Operation | Time | Space | Description |
|-----------|------|-------|-------------|
| Modular addition | O(1) | O(1) | (a + b) mod m |
| Modular subtraction | O(1) | O(1) | (a - b) mod m |
| Modular multiplication | O(1) | O(1) | (a × b) mod m |
| Modular division | O(log m) | O(1) | Using modular inverse |

**Modular Operations**:
```kotlin
fun modAdd(a: Long, b: Long, m: Long): Long {
    return ((a % m) + (b % m)) % m
}

fun modSub(a: Long, b: Long, m: Long): Long {
    return ((a % m) - (b % m) + m) % m
}

fun modMul(a: Long, b: Long, m: Long): Long {
    return ((a % m) * (b % m)) % m
}
```

### **Modular Exponentiation**

| Algorithm | Time | Space | Description |
|-----------|------|-------|-------------|
| Fast power (binary) | O(log b) | O(1) | Calculate a^b mod m |
| Fast power (recursive) | O(log b) | O(log b) | Recursive implementation |

**Fast Modular Exponentiation**:
```kotlin
fun modPow(base: Long, exp: Long, mod: Long): Long {
    if (mod == 1L) return 0L
    
    var result = 1L
    var b = base % mod
    var e = exp
    
    while (e > 0) {
        if (e and 1L == 1L) {
            result = (result * b) % mod
        }
        b = (b * b) % mod
        e = e shr 1
    }
    
    return result
}
```

### **Modular Inverse**

| Algorithm | Time | Space | Description |
|-----------|------|-------|-------------|
| Extended Euclidean | O(log m) | O(1) | General case |
| Fermat's Little Theorem | O(log m) | O(1) | When m is prime |

**Modular Inverse using Extended Euclidean**:
```kotlin
fun modInverse(a: Long, m: Long): Long {
    val bezout = extendedGCD(a, m)
    if (bezout.gcd != 1L) return -1L  // No inverse exists
    
    return (bezout.x % m + m) % m
}
```

**Modular Inverse using Fermat's Little Theorem**:
```kotlin
fun modInverseFermat(a: Long, m: Long): Long {
    return modPow(a, m - 2, m)  // Only when m is prime
}
```

## Combinatorics

### **Factorial and Related**

| Operation | Time | Space | Description |
|-----------|------|-------|-------------|
| Factorial | O(n) | O(1) | Calculate n! |
| Factorial (modular) | O(n) | O(1) | Calculate n! mod m |
| Precomputed factorial | O(n) | O(n) | Precompute for range |

**Factorial**:
```kotlin
fun factorial(n: Int): Long {
    if (n < 0) return 0L
    if (n <= 1) return 1L
    
    var result = 1L
    for (i in 2..n) {
        result *= i
    }
    return result
}

fun factorialMod(n: Int, mod: Int): Int {
    if (n < 0) return 0
    if (n <= 1) return 1
    
    var result = 1
    for (i in 2..n) {
        result = (result * i) % mod
    }
    return result
}
```

### **Permutations and Combinations**

| Operation | Time | Space | Description |
|-----------|------|-------|-------------|
| Permutations P(n,r) | O(n) | O(1) | n! / (n-r)! |
| Combinations C(n,r) | O(n) | O(1) | n! / (r! × (n-r)!) |
| Combinations (modular) | O(n) | O(n) | With precomputed factorials |

**Permutations and Combinations**:
```kotlin
fun permutations(n: Int, r: Int): Long {
    if (r > n) return 0L
    var result = 1L
    for (i in 0 until r) {
        result *= (n - i)
    }
    return result
}

fun combinations(n: Int, r: Int): Long {
    if (r > n) return 0L
    if (r > n - r) return combinations(n, n - r)
    
    var result = 1L
    for (i in 0 until r) {
        result = result * (n - i) / (i + 1)
    }
    return result
}
```

### **Catalan Numbers**

| Algorithm | Time | Space | Description |
|-----------|------|-------|-------------|
| Direct formula | O(n) | O(1) | C(n) = (1/(n+1)) × C(2n,n) |
| Dynamic programming | O(n²) | O(n) | Using recurrence relation |

**Catalan Numbers**:
```kotlin
fun catalanNumber(n: Int): Long {
    if (n <= 1) return 1L
    return combinations(2 * n, n) / (n + 1)
}

fun catalanNumbers(n: Int): List<Long> {
    val catalan = LongArray(n + 1)
    catalan[0] = 1L
    catalan[1] = 1L
    
    for (i in 2..n) {
        for (j in 0 until i) {
            catalan[i] += catalan[j] * catalan[i - 1 - j]
        }
    }
    
    return catalan.toList()
}
```

## Divisors and Multiples

### **Divisors**

| Operation | Time | Space | Description |
|-----------|------|-------|-------------|
| Count divisors | O(√n) | O(1) | Count all divisors |
| Sum of divisors | O(√n) | O(1) | Sum all divisors |
| Generate divisors | O(√n) | O(√n) | List all divisors |

**Divisors**:
```kotlin
fun countDivisors(n: Long): Int {
    var count = 0
    for (i in 1..kotlin.math.sqrt(n.toDouble()).toLong()) {
        if (n % i == 0L) {
            count += if (i * i == n) 1 else 2
        }
    }
    return count
}

fun sumOfDivisors(n: Long): Long {
    var sum = 0L
    for (i in 1..kotlin.math.sqrt(n.toDouble()).toLong()) {
        if (n % i == 0L) {
            sum += i
            if (i * i != n) sum += n / i
        }
    }
    return sum
}
```

## Mathematical Sequences

### **Fibonacci Numbers**

| Algorithm | Time | Space | Description |
|-----------|------|-------|-------------|
| Recursive | O(2^n) | O(n) | Naive recursive |
| Dynamic programming | O(n) | O(n) | Memoization |
| Matrix exponentiation | O(log n) | O(1) | Fast calculation |
| Binet's formula | O(1) | O(1) | Closed form (approximate) |

**Fast Fibonacci using Matrix Exponentiation**:
```kotlin
fun fibonacci(n: Long): Long {
    if (n <= 1) return n
    
    val matrix = arrayOf(longArrayOf(1, 1), longArrayOf(1, 0))
    val result = matrixPower(matrix, n - 1)
    return result[0][0]
}

fun matrixPower(matrix: Array<LongArray>, n: Long): Array<LongArray> {
    if (n == 0L) return arrayOf(longArrayOf(1, 0), longArrayOf(0, 1))
    if (n == 1L) return matrix
    
    val half = matrixPower(matrix, n / 2)
    val squared = matrixMultiply(half, half)
    
    return if (n % 2 == 0L) squared else matrixMultiply(squared, matrix)
}
```

## Performance Optimization Tips

### **1. Use Efficient Algorithms**
- Use Euclidean algorithm for GCD (O(log n))
- Use fast modular exponentiation for large powers
- Use sieve for prime generation
- Use precomputed factorials for combinatorics

### **2. Handle Large Numbers**
- Use Long for intermediate calculations
- Use modular arithmetic to prevent overflow
- Use binary GCD for very large numbers

### **3. Precompute When Possible**
- Precompute primes using sieve
- Precompute factorials for combinatorics
- Precompute modular inverses

### **4. Choose Right Algorithm**
- Use trial division for small numbers
- Use Miller-Rabin for large numbers
- Use segmented sieve for large ranges

This complexity guide helps in choosing the most efficient number theory approach for any given problem.
