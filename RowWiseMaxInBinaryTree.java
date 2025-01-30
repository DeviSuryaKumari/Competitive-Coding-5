// Approach: Traverse the tree level by level using a BFS approach with a queue. At each level, track the maximum element. In each
// iteration, while the queue is not empty, the queue size determines the number of nodes at the current level being processed.
// Time Complexity: O(n) where n - node count
// Space Complexity: O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/
// https://leetcode.com/problems/find-largest-value-in-each-tree-row/solutions/6182943/beats-100-proof-concise-code-java-beginner-freindly/

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class RowWiseMaxInBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) {
            val = v;
            left = right = null;
        }
    }

    int[] BFS(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        Deque<TreeNode> Q = new ArrayDeque<>();
        Q.add(root);
        while (!Q.isEmpty()) {
            int levelSize = Q.size();
            int maxInCurrLevel = Integer.MIN_VALUE;
            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = Q.poll();
                maxInCurrLevel = Math.max(maxInCurrLevel, curr.val);
                if (curr.left != null) {
                    Q.add(curr.left);
                }
                if (curr.right != null) {
                    Q.add(curr.right);
                }
            }
            ans.add(maxInCurrLevel);
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    int[] DFS(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>(); // stores level to max at that level mapping
        preorder(root, map, 0);
        List<Integer> ans = new ArrayList<>(map.values());
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    void preorder(TreeNode root, Map<Integer, Integer> map, int level) {
        if (root == null) {
            return;
        }
        map.put(level, Math.max(map.getOrDefault(level, root.val), root.val));
        preorder(root.left, map, level + 1);
        preorder(root.right, map, level + 1);
    }

    public static void main(String[] args) {
        RowWiseMaxInBinaryTree rwmbt = new RowWiseMaxInBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
        System.out.println("Level-wise max in given binary tree: " + Arrays.toString(rwmbt.BFS(root))); // prints [1,3,9]
    }
}