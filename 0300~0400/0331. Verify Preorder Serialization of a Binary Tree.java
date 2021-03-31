/* 
One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:

Input: "9,3,4,#,#,1,#,#,2,#,6,#,#"
Output: true
Example 2:

Input: "1,#"
Output: false
Example 3:

Input: "9,#,#,1"
Output: false
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree/solution/wu-ti-by-keylol/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public boolean isValidSerialization(String preorder) {
        String[] strs = preorder.split(",");
        int degree = 1;
        for (String str: strs) {
            if (degree == 0) return false;
            if (str.charAt(0) == '#') degree--;
            else degree++;
        }
        return degree == 0;
    }
}



class Solution {
    public boolean isValidSerialization(String preorder) {
        char[] chs = preorder.toCharArray();
        int len = chs.length;
        if (chs[0] == '#') return len == 1;
        int degree = 1;
        for (int i = 1; i < len; i++) {
            if (chs[i] == ',') {
                if (chs[i - 1] == '#') degree--;
                else if (degree++ == 0) return false;
            }
        }
        if (chs[len - 1] == '#') degree--;
        else degree++;
        return degree == 0;
    }
}