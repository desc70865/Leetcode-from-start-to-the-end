/* 
If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.

For each integer in this list:

The hundreds digit represents the depth D of this node, 1 <= D <= 4.
The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
The units digit represents the value V of this node, 0 <= V <= 9.
Given a list of ascending three-digits integers representing a binary tree with the depth smaller than 5, you need to return the sum of all paths from the root towards the leaves.

It's guaranteed that the given list represents a valid connected binary tree.

Example 1:

Input: [113, 215, 221]
Output: 12
Explanation: 
The tree that the list represents is:
    3
   / \
  5   1

The path sum is (3 + 5) + (3 + 1) = 12.
 

Example 2:

Input: [113, 221]
Output: 4
Explanation: 
The tree that the list represents is: 
    3
     \
      1

The path sum is (3 + 1) = 4.
 */

class Solution {
    public int pathSum(int[] nums) {
        return dfs(build(nums), 0);
    }

    private TreeNode build(int[] nums) {
        TreeNode root = new TreeNode(nums[0]);
        int idx = 1;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (idx < nums.length) {
            TreeNode cur = queue.poll();
            int level = cur.val / 100;
            if (nums[idx] / 100 != level + 1) {
                continue;
            }
            int offset = offset(cur.val);
            if (offset(nums[idx]) == 2 * offset - 1) {
                cur.left = new TreeNode(nums[idx++]);
                queue.offer(cur.left);
            }
            if (idx >= nums.length || nums[idx] / 100 != level + 1) {
                continue;
            }
            if (offset(nums[idx]) == 2 * offset) {
                cur.right = new TreeNode(nums[idx++]);
                queue.offer(cur.right);
            }
        }
        return root;
    }

    private int offset(int x) {
        return (x % 100) / 10;
    }

    private int dfs(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        sum += node.val % 10;
        if (node.left == null && node.right == null) {
            return sum;
        }
        return dfs(node.left, sum) + dfs(node.right, sum);
    }
}