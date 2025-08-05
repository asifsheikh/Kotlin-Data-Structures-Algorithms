# Contributing to Kotlin Data Structures & Algorithms

🎉 **Thank you for your interest in contributing!** 

This document provides guidelines and information for contributors to help make the contribution process smooth and effective.

## 🤝 How to Contribute

### 1. **Fork the Repository**
- Click the "Fork" button on the top right of the repository page
- This creates a copy of the repository in your GitHub account

### 2. **Clone Your Fork**
```bash
git clone https://github.com/yourusername/Kotlin-Data-Structures-Algorithms.git
cd Kotlin-Data-Structures-Algorithms
```

### 3. **Create a Feature Branch**
```bash
git checkout -b feature/amazing-algorithm
# or
git checkout -b fix/bug-description
```

### 4. **Make Your Changes**
- Add your implementation following the existing patterns
- Ensure comprehensive documentation
- Test your code thoroughly
- Follow the coding standards below

### 5. **Commit Your Changes**
```bash
git add .
git commit -m "Add amazing algorithm with comprehensive documentation"
```

### 6. **Push to Your Fork**
```bash
git push origin feature/amazing-algorithm
```

### 7. **Create a Pull Request**
- Go to your fork on GitHub
- Click "New Pull Request"
- Select the main branch as the base
- Fill out the PR template
- Submit the PR

## 📝 Coding Standards

### **File Structure**
Follow the existing organization pattern:
```
src/data_structure/[data_structure]/algorithms/[category]/[AlgorithmName].kt
```

### **Documentation Template**
Each algorithm file should follow this structure:

```kotlin
/**
 * ALGORITHM NAME
 * 
 * Problem: Clear description of what the algorithm solves
 * 
 * Example:
 * Input: [example input]
 * Output: [example output]
 * 
 * Intuition:
 * - Explain the approach conceptually
 * - Why this approach works
 * - Key insights
 * 
 * Use Cases:
 * - When to use this algorithm
 * - Real-world applications
 */

object AlgorithmName {
    
    /**
     * Method Name
     * 
     * Problem: Specific problem this method solves
     * 
     * Algorithm:
     * 1. Step-by-step algorithm description
     * 2. Clear and concise steps
     * 3. Important details
     * 
     * Time Complexity: O(complexity) - Explanation
     * Space Complexity: O(complexity) - Explanation
     */
    fun methodName(params): ReturnType {
        // Implementation
    }
}
```

### **Code Style**
- Use **Kotlin coding conventions**
- **Meaningful variable names**
- **Clear and concise comments**
- **Proper error handling**
- **Edge case consideration**

### **Testing**
- Include **usage examples** in documentation
- **Test edge cases** (empty arrays, single elements, etc.)
- **Verify correctness** with multiple test cases

## 🎯 What We're Looking For

### **High Priority**
- **New algorithms** with comprehensive documentation
- **Optimizations** to existing algorithms
- **Bug fixes** and improvements
- **Additional data structures** (Linked Lists, Trees, Graphs, etc.)

### **Medium Priority**
- **Better examples** and use cases
- **Performance improvements**
- **Code refactoring** for better readability
- **Additional algorithm variations**

### **Low Priority**
- **Documentation improvements**
- **Minor formatting fixes**
- **README updates**

## 🚫 What We're NOT Looking For

- **Duplicate implementations** without significant improvements
- **Incomplete implementations** without proper documentation
- **Code that doesn't follow the established patterns**
- **Changes that break existing functionality**

## 📋 Pull Request Guidelines

### **PR Title Format**
```
Add [Algorithm Name] with [Key Feature]
```
Examples:
- `Add Binary Search with recursive implementation`
- `Add QuickSort with randomized pivot selection`
- `Fix ArrayRotation edge case handling`

### **PR Description Template**
```markdown
## Description
Brief description of what this PR adds/fixes

## Changes Made
- [ ] Added new algorithm: [Algorithm Name]
- [ ] Fixed bug in: [File/Function]
- [ ] Improved documentation for: [Topic]
- [ ] Added tests for: [Functionality]

## Algorithm Details
- **Problem**: What problem does this solve?
- **Approach**: How does this algorithm work?
- **Complexity**: Time and space complexity
- **Use Cases**: When to use this algorithm?

## Testing
- [ ] Added unit tests
- [ ] Tested with edge cases
- [ ] Verified correctness with examples

## Documentation
- [ ] Added comprehensive documentation
- [ ] Included usage examples
- [ ] Added complexity analysis
- [ ] Followed existing patterns

## Checklist
- [ ] Code follows Kotlin conventions
- [ ] Documentation is complete and clear
- [ ] Tests are included and passing
- [ ] No breaking changes to existing functionality
```

## 🐛 Reporting Issues

### **Bug Report Template**
```markdown
## Bug Description
Clear description of the bug

## Steps to Reproduce
1. Step 1
2. Step 2
3. Step 3

## Expected Behavior
What should happen

## Actual Behavior
What actually happens

## Environment
- Kotlin version: [version]
- JDK version: [version]
- IDE: [name and version]

## Additional Information
Any other relevant information
```

## 📚 Learning Resources

### **For Contributors**
- [Kotlin Official Documentation](https://kotlinlang.org/docs/home.html)
- [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- [Data Structures and Algorithms](https://en.wikipedia.org/wiki/Data_structure)

### **Algorithm Resources**
- [LeetCode](https://leetcode.com/) - Practice problems
- [GeeksforGeeks](https://www.geeksforgeeks.org/) - Algorithm explanations
- [CLRS Book](https://en.wikipedia.org/wiki/Introduction_to_Algorithms) - Classic reference

## 🤝 Community Guidelines

### **Be Respectful**
- **Respect all contributors** regardless of experience level
- **Provide constructive feedback** in a helpful manner
- **Be patient** with new contributors

### **Be Helpful**
- **Answer questions** in issues and discussions
- **Review pull requests** thoroughly and constructively
- **Share knowledge** and learning resources

### **Be Professional**
- **Follow the established patterns** and conventions
- **Write clear and maintainable code**
- **Document your work** thoroughly

## 🏆 Recognition

### **Contributor Levels**
- **🌱 New Contributor**: First contribution
- **🌿 Regular Contributor**: Multiple contributions
- **🌳 Core Contributor**: Significant contributions
- **⭐ Maintainer**: Consistent high-quality contributions

### **Hall of Fame**
Contributors will be recognized in the README for:
- **Major algorithm implementations**
- **Significant bug fixes**
- **Documentation improvements**
- **Community support**

## 📞 Getting Help

### **Questions?**
- **GitHub Issues**: For bug reports and feature requests
- **GitHub Discussions**: For questions and general discussion
- **Pull Request Comments**: For specific code-related questions

### **Need Guidance?**
- **Check existing issues** for similar questions
- **Review the documentation** thoroughly
- **Look at existing implementations** for patterns

---

**Thank you for contributing to the Kotlin community! 🚀**

*Your contributions help make this repository a valuable resource for developers worldwide.* 