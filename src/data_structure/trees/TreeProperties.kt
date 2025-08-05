package data_structure.trees

/**
 * TREE PROPERTIES AND ANALYSIS
 * 
 * This file contains tree property analysis:
 * - Tree symmetry and mirroring
 * - Path sum problems
 * - Tree diameter and height
 * - Tree balance and properties
 * - Lowest Common Ancestor (LCA)
 */

object TreeProperties {
    
    // ===== TREE SYMMETRY =====
    fun isSymmetric(root: TreeNode.BinaryTreeNode?): Boolean {
        return root == null || isSymmetricHelper(root.left, root.right)
    }
    
    private fun isSymmetricHelper(left: TreeNode.BinaryTreeNode?, right: TreeNode.BinaryTreeNode?): Boolean {
        if (left == null && right == null) return true
        if (left == null || right == null) return false
        
        return left.value == right.value &&
               isSymmetricHelper(left.left, right.right) &&
               isSymmetricHelper(left.right, right.left)
    }
    
    // ===== PATH SUM PROBLEMS =====
    fun hasPathSum(root: TreeNode.BinaryTreeNode?, targetSum: Int): Boolean {
        if (root == null) return false
        
        if (root.left == null && root.right == null) {
            return targetSum == root.value
        }
        
        return hasPathSum(root.left, targetSum - root.value) ||
               hasPathSum(root.right, targetSum - root.value)
    }
    
    fun pathSum(root: TreeNode.BinaryTreeNode?, targetSum: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val path = mutableListOf<Int>()
        pathSumHelper(root, targetSum, path, result)
        return result
    }
    
    private fun pathSumHelper(
        node: TreeNode.BinaryTreeNode?,
        targetSum: Int,
        path: MutableList<Int>,
        result: MutableList<List<Int>>
    ) {
        if (node == null) return
        
        path.add(node.value)
        
        if (node.left == null && node.right == null && targetSum == node.value) {
            result.add(path.toList())
        }
        
        pathSumHelper(node.left, targetSum - node.value, path, result)
        pathSumHelper(node.right, targetSum - node.value, path, result)
        
        path.removeAt(path.lastIndex)
    }
    
    // ===== TREE DIAMETER =====
    fun diameterOfBinaryTree(root: TreeNode.BinaryTreeNode?): Int {
        val diameter = intArrayOf(0)
        diameterHelper(root, diameter)
        return diameter[0]
    }
    
    private fun diameterHelper(node: TreeNode.BinaryTreeNode?, diameter: IntArray): Int {
        if (node == null) return 0
        
        val leftHeight = diameterHelper(node.left, diameter)
        val rightHeight = diameterHelper(node.right, diameter)
        
        diameter[0] = maxOf(diameter[0], leftHeight + rightHeight)
        
        return maxOf(leftHeight, rightHeight) + 1
    }
    
    // ===== MAXIMUM PATH SUM =====
    fun maxPathSum(root: TreeNode.BinaryTreeNode?): Int {
        val maxSum = intArrayOf(Int.MIN_VALUE)
        maxPathSumHelper(root, maxSum)
        return maxSum[0]
    }
    
    private fun maxPathSumHelper(node: TreeNode.BinaryTreeNode?, maxSum: IntArray): Int {
        if (node == null) return 0
        
        val leftSum = maxOf(0, maxPathSumHelper(node.left, maxSum))
        val rightSum = maxOf(0, maxPathSumHelper(node.right, maxSum))
        
        maxSum[0] = maxOf(maxSum[0], leftSum + rightSum + node.value)
        
        return maxOf(leftSum, rightSum) + node.value
    }
    
    // ===== LOWEST COMMON ANCESTOR (LCA) =====
    fun lowestCommonAncestor(
        root: TreeNode.BinaryTreeNode?,
        p: TreeNode.BinaryTreeNode,
        q: TreeNode.BinaryTreeNode
    ): TreeNode.BinaryTreeNode? {
        if (root == null || root == p || root == q) return root
        
        val left = lowestCommonAncestor(root.left, p, q)
        val right = lowestCommonAncestor(root.right, p, q)
        
        return when {
            left != null && right != null -> root
            left != null -> left
            right != null -> right
            else -> null
        }
    }
    
    // ===== TREE HEIGHT AND DEPTH =====
    fun getTreeHeight(root: TreeNode.BinaryTreeNode?): Int {
        if (root == null) return 0
        return 1 + maxOf(getTreeHeight(root.left), getTreeHeight(root.right))
    }
    
    fun getNodeDepth(root: TreeNode.BinaryTreeNode?, target: TreeNode.BinaryTreeNode): Int {
        return getNodeDepthHelper(root, target, 0)
    }
    
    private fun getNodeDepthHelper(
        node: TreeNode.BinaryTreeNode?,
        target: TreeNode.BinaryTreeNode,
        depth: Int
    ): Int {
        if (node == null) return -1
        if (node == target) return depth
        
        val leftDepth = getNodeDepthHelper(node.left, target, depth + 1)
        if (leftDepth != -1) return leftDepth
        
        return getNodeDepthHelper(node.right, target, depth + 1)
    }
    
    // ===== TREE BALANCE =====
    fun isBalanced(root: TreeNode.BinaryTreeNode?): Boolean {
        return checkBalance(root) != -1
    }
    
    private fun checkBalance(node: TreeNode.BinaryTreeNode?): Int {
        if (node == null) return 0
        
        val leftHeight = checkBalance(node.left)
        if (leftHeight == -1) return -1
        
        val rightHeight = checkBalance(node.right)
        if (rightHeight == -1) return -1
        
        if (Math.abs(leftHeight - rightHeight) > 1) return -1
        
        return maxOf(leftHeight, rightHeight) + 1
    }
    
    // ===== TREE COMPLETENESS =====
    fun isCompleteTree(root: TreeNode.BinaryTreeNode?): Boolean {
        if (root == null) return true
        
        val queue = ArrayDeque<TreeNode.BinaryTreeNode?>()
        queue.add(root)
        var foundNull = false
        
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            
            if (node == null) {
                foundNull = true
            } else {
                if (foundNull) return false
                queue.add(node.left)
                queue.add(node.right)
            }
        }
        
        return true
    }
    
    // ===== TREE PERFECTNESS =====
    fun isPerfectTree(root: TreeNode.BinaryTreeNode?): Boolean {
        val height = getTreeHeight(root)
        val expectedNodes = (1 shl height) - 1
        return countNodes(root) == expectedNodes
    }
    
    private fun countNodes(root: TreeNode.BinaryTreeNode?): Int {
        if (root == null) return 0
        return 1 + countNodes(root.left) + countNodes(root.right)
    }
    
    // ===== TREE FULLNESS =====
    fun isFullTree(root: TreeNode.BinaryTreeNode?): Boolean {
        if (root == null) return true
        
        if (root.left == null && root.right == null) return true
        
        if (root.left != null && root.right != null) {
            return isFullTree(root.left) && isFullTree(root.right)
        }
        
        return false
    }
    
    // ===== TREE INVERTING =====
    fun invertTree(root: TreeNode.BinaryTreeNode?): TreeNode.BinaryTreeNode? {
        if (root == null) return null
        
        val left = invertTree(root.left)
        val right = invertTree(root.right)
        
        root.left = right
        root.right = left
        
        return root
    }
    
    // ===== TREE FLATTENING =====
    fun flattenTree(root: TreeNode.BinaryTreeNode?) {
        if (root == null) return
        
        flattenTree(root.left)
        flattenTree(root.right)
        
        if (root.left != null) {
            val right = root.right
            root.right = root.left
            root.left = null
            
            var current = root.right
            while (current?.right != null) {
                current = current.right
            }
            current?.right = right
        }
    }
    
    // ===== TREE SERIALIZATION =====
    fun serializeTree(root: TreeNode.BinaryTreeNode?): String {
        if (root == null) return "null"
        return "${root.value},${serializeTree(root.left)},${serializeTree(root.right)}"
    }
    
    fun deserializeTree(data: String): TreeNode.BinaryTreeNode? {
        val values = data.split(",")
        val iterator = values.iterator()
        
        fun deserializeHelper(): TreeNode.BinaryTreeNode? {
            if (!iterator.hasNext()) return null
            
            val value = iterator.next()
            if (value == "null") return null
            
            val node = TreeNode.BinaryTreeNode(value.toInt())
            node.left = deserializeHelper()
            node.right = deserializeHelper()
            return node
        }
        
        return deserializeHelper()
    }
    
    // ===== TREE SUBTREE CHECK =====
    fun isSubtree(root: TreeNode.BinaryTreeNode?, subRoot: TreeNode.BinaryTreeNode?): Boolean {
        if (subRoot == null) return true
        if (root == null) return false
        
        return isSameTree(root, subRoot) ||
               isSubtree(root.left, subRoot) ||
               isSubtree(root.right, subRoot)
    }
    
    private fun isSameTree(p: TreeNode.BinaryTreeNode?, q: TreeNode.BinaryTreeNode?): Boolean {
        if (p == null && q == null) return true
        if (p == null || q == null) return false
        
        return p.value == q.value &&
               isSameTree(p.left, q.left) &&
               isSameTree(p.right, q.right)
    }
    
    // ===== TREE LEVEL ANALYSIS =====
    fun getLevelOrder(root: TreeNode.BinaryTreeNode?): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (root == null) return result
        
        val queue = ArrayDeque<TreeNode.BinaryTreeNode>()
        queue.add(root)
        
        while (queue.isNotEmpty()) {
            val levelSize = queue.size
            val level = mutableListOf<Int>()
            
            repeat(levelSize) {
                val node = queue.removeFirst()
                level.add(node.value)
                
                if (node.left != null) queue.add(node.left!!)
                if (node.right != null) queue.add(node.right!!)
            }
            
            result.add(level)
        }
        
        return result
    }
    
    // ===== TREE BOUNDARY TRAVERSAL =====
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
} 