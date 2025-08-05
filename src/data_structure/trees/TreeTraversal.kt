package data_structure.trees

/**
 * TREE TRAVERSAL ALGORITHMS
 * 
 * This file contains tree traversal implementations:
 * - Inorder Traversal (Left -> Root -> Right)
 * - Preorder Traversal (Root -> Left -> Right)
 * - Postorder Traversal (Left -> Right -> Root)
 * - Level Order Traversal (Breadth-First)
 * - Morris Traversal (Inorder without recursion/stack)
 */

object TreeTraversal {
    
    // ===== INORDER TRAVERSAL =====
    fun inorderTraversal(root: TreeNode.BinaryTreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        
        fun inorderHelper(node: TreeNode.BinaryTreeNode?) {
            if (node == null) return
            inorderHelper(node.left)
            result.add(node.value)
            inorderHelper(node.right)
        }
        
        inorderHelper(root)
        return result
    }
    
    fun inorderTraversalIterative(root: TreeNode.BinaryTreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        val stack = ArrayDeque<TreeNode.BinaryTreeNode>()
        var current = root
        
        while (current != null || stack.isNotEmpty()) {
            while (current != null) {
                stack.addLast(current)
                current = current.left
            }
            
            current = stack.removeLast()
            result.add(current.value)
            current = current.right
        }
        
        return result
    }
    
    // ===== PREORDER TRAVERSAL =====
    fun preorderTraversal(root: TreeNode.BinaryTreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        
        fun preorderHelper(node: TreeNode.BinaryTreeNode?) {
            if (node == null) return
            result.add(node.value)
            preorderHelper(node.left)
            preorderHelper(node.right)
        }
        
        preorderHelper(root)
        return result
    }
    
    fun preorderTraversalIterative(root: TreeNode.BinaryTreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        val stack = ArrayDeque<TreeNode.BinaryTreeNode>()
        
        if (root != null) {
            stack.addLast(root)
        }
        
        while (stack.isNotEmpty()) {
            val current = stack.removeLast()
            result.add(current.value)
            
            // Push right child first (so left is processed first)
            if (current.right != null) {
                stack.addLast(current.right!!)
            }
            if (current.left != null) {
                stack.addLast(current.left!!)
            }
        }
        
        return result
    }
    
    // ===== POSTORDER TRAVERSAL =====
    fun postorderTraversal(root: TreeNode.BinaryTreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        
        fun postorderHelper(node: TreeNode.BinaryTreeNode?) {
            if (node == null) return
            postorderHelper(node.left)
            postorderHelper(node.right)
            result.add(node.value)
        }
        
        postorderHelper(root)
        return result
    }
    
    fun postorderTraversalIterative(root: TreeNode.BinaryTreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        val stack = ArrayDeque<TreeNode.BinaryTreeNode>()
        var current = root
        var lastVisited: TreeNode.BinaryTreeNode? = null
        
        while (current != null || stack.isNotEmpty()) {
            while (current != null) {
                stack.addLast(current)
                current = current.left
            }
            
            current = stack.last()
            
            if (current.right == null || current.right == lastVisited) {
                result.add(current.value)
                lastVisited = current
                stack.removeLast()
                current = null
            } else {
                current = current.right
            }
        }
        
        return result
    }
    
    // ===== LEVEL ORDER TRAVERSAL =====
    fun levelOrderTraversal(root: TreeNode.BinaryTreeNode?): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (root == null) return result
        
        val queue = ArrayDeque<TreeNode.BinaryTreeNode>()
        queue.add(root)
        
        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            val level = mutableListOf<Int>()
            
            repeat(levelSize) {
                val current = queue.removeFirst()
                level.add(current.value)
                
                if (current.left != null) {
                    queue.add(current.left!!)
                }
                if (current.right != null) {
                    queue.add(current.right!!)
                }
            }
            
            result.add(level)
        }
        
        return result
    }
    
    fun levelOrderTraversalSingleList(root: TreeNode.BinaryTreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        if (root == null) return result
        
        val queue = ArrayDeque<TreeNode.BinaryTreeNode>()
        queue.add(root)
        
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            result.add(current.value)
            
            if (current.left != null) {
                queue.add(current.left!!)
            }
            if (current.right != null) {
                queue.add(current.right!!)
            }
        }
        
        return result
    }
    
    // ===== ZIGZAG LEVEL ORDER TRAVERSAL =====
    fun zigzagLevelOrder(root: TreeNode.BinaryTreeNode?): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (root == null) return result
        
        val queue = ArrayDeque<TreeNode.BinaryTreeNode>()
        queue.add(root)
        var leftToRight = true
        
        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            val level = mutableListOf<Int>()
            
            repeat(levelSize) {
                val current = queue.removeFirst()
                
                if (leftToRight) {
                    level.add(current.value)
                } else {
                    level.add(0, current.value)
                }
                
                if (current.left != null) {
                    queue.add(current.left!!)
                }
                if (current.right != null) {
                    queue.add(current.right!!)
                }
            }
            
            result.add(level)
            leftToRight = !leftToRight
        }
        
        return result
    }
    
    // ===== MORRIS INORDER TRAVERSAL =====
    fun morrisInorderTraversal(root: TreeNode.BinaryTreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        var current = root
        
        while (current != null) {
            if (current.left == null) {
                result.add(current.value)
                current = current.right
            } else {
                // Find inorder predecessor
                var predecessor = current.left
                while (predecessor!!.right != null && predecessor.right != current) {
                    predecessor = predecessor.right
                }
                
                if (predecessor.right == null) {
                    predecessor.right = current
                    current = current.left
                } else {
                    predecessor.right = null
                    result.add(current.value)
                    current = current.right
                }
            }
        }
        
        return result
    }
    
    // ===== MORRIS PREORDER TRAVERSAL =====
    fun morrisPreorderTraversal(root: TreeNode.BinaryTreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        var current = root
        
        while (current != null) {
            if (current.left == null) {
                result.add(current.value)
                current = current.right
            } else {
                // Find inorder predecessor
                var predecessor = current.left
                while (predecessor!!.right != null && predecessor.right != current) {
                    predecessor = predecessor.right
                }
                
                if (predecessor.right == null) {
                    result.add(current.value)
                    predecessor.right = current
                    current = current.left
                } else {
                    predecessor.right = null
                    current = current.right
                }
            }
        }
        
        return result
    }
    
    // ===== VERTICAL ORDER TRAVERSAL =====
    fun verticalOrderTraversal(root: TreeNode.BinaryTreeNode?): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (root == null) return result
        
        val columnMap = mutableMapOf<Int, MutableList<Int>>()
        val queue = ArrayDeque<Pair<TreeNode.BinaryTreeNode, Int>>()
        queue.add(root to 0)
        
        while (queue.isNotEmpty()) {
            val (node, column) = queue.removeFirst()
            
            columnMap.computeIfAbsent(column) { mutableListOf() }.add(node.value)
            
            if (node.left != null) {
                queue.add(node.left!! to (column - 1))
            }
            if (node.right != null) {
                queue.add(node.right!! to (column + 1))
            }
        }
        
        return columnMap.entries.sortedBy { it.key }.map { it.value }
    }
    
    // ===== BOUNDARY TRAVERSAL =====
    fun boundaryTraversal(root: TreeNode.BinaryTreeNode?): List<Int> {
        val result = mutableListOf<Int>()
        if (root == null) return result
        
        result.add(root.value)
        
        // Add left boundary (excluding leaf)
        addLeftBoundary(root.left, result)
        
        // Add leaves
        addLeaves(root, result)
        
        // Add right boundary (excluding leaf, in reverse)
        addRightBoundary(root.right, result)
        
        return result
    }
    
    private fun addLeftBoundary(node: TreeNode.BinaryTreeNode?, result: MutableList<Int>) {
        if (node == null || (node.left == null && node.right == null)) return
        
        result.add(node.value)
        
        if (node.left != null) {
            addLeftBoundary(node.left, result)
        } else {
            addLeftBoundary(node.right, result)
        }
    }
    
    private fun addLeaves(node: TreeNode.BinaryTreeNode?, result: MutableList<Int>) {
        if (node == null) return
        
        if (node.left == null && node.right == null) {
            result.add(node.value)
            return
        }
        
        addLeaves(node.left, result)
        addLeaves(node.right, result)
    }
    
    private fun addRightBoundary(node: TreeNode.BinaryTreeNode?, result: MutableList<Int>) {
        if (node == null || (node.left == null && node.right == null)) return
        
        if (node.right != null) {
            addRightBoundary(node.right, result)
        } else {
            addRightBoundary(node.left, result)
        }
        
        result.add(node.value)
    }
    
    // ===== DIAGONAL TRAVERSAL =====
    fun diagonalTraversal(root: TreeNode.BinaryTreeNode?): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (root == null) return result
        
        val diagonalMap = mutableMapOf<Int, MutableList<Int>>()
        val queue = ArrayDeque<Pair<TreeNode.BinaryTreeNode, Int>>()
        queue.add(root to 0)
        
        while (queue.isNotEmpty()) {
            val (node, diagonal) = queue.removeFirst()
            
            diagonalMap.computeIfAbsent(diagonal) { mutableListOf() }.add(node.value)
            
            if (node.left != null) {
                queue.add(node.left!! to (diagonal + 1))
            }
            if (node.right != null) {
                queue.add(node.right!! to diagonal)
            }
        }
        
        return diagonalMap.entries.sortedBy { it.key }.map { it.value }
    }
    
    // ===== REVERSE LEVEL ORDER TRAVERSAL =====
    fun reverseLevelOrderTraversal(root: TreeNode.BinaryTreeNode?): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (root == null) return result
        
        val queue = ArrayDeque<TreeNode.BinaryTreeNode>()
        val stack = ArrayDeque<List<Int>>()
        queue.add(root)
        
        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            val level = mutableListOf<Int>()
            
            repeat(levelSize) {
                val current = queue.removeFirst()
                level.add(current.value)
                
                if (current.left != null) {
                    queue.add(current.left!!)
                }
                if (current.right != null) {
                    queue.add(current.right!!)
                }
            }
            
            stack.addLast(level)
        }
        
        while (stack.isNotEmpty()) {
            result.add(stack.removeLast())
        }
        
        return result
    }
    
    // ===== BOTTOM-UP LEVEL ORDER TRAVERSAL =====
    fun bottomUpLevelOrderTraversal(root: TreeNode.BinaryTreeNode?): List<List<Int>> {
        return reverseLevelOrderTraversal(root)
    }
} 