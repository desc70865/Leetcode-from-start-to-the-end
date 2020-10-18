/* 
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));



public class Codec {
    public String serialize(TreeNode root) {
        if (root == null) {
            return "X";
        }
        String l = "(" + serialize(root.left) + ")";
        String r = "(" + serialize(root.right) + ")";
        return  l + root.val + r;
    }

    public TreeNode deserialize(String data) {
        int[] ptr = {0};
        return parse(data, ptr);
    }

    public TreeNode parse(String data, int[] ptr) {
        if (data.charAt(ptr[0]) == 'X') {
            ++ptr[0];
            return null;
        }
        TreeNode cur = new TreeNode(0);
        cur.left = parseSubtree(data, ptr);
        cur.val = parseInt(data, ptr);
        cur.right = parseSubtree(data, ptr);
        return cur;
    }

    public TreeNode parseSubtree(String data, int[] ptr) {
        ++ptr[0]; // 跳过左括号
        TreeNode subtree = parse(data, ptr);
        ++ptr[0]; // 跳过右括号
        return subtree;
    }

    public int parseInt(String data, int[] ptr) {
        int x = 0, sgn = 1;
        if (!Character.isDigit(data.charAt(ptr[0]))) {
            sgn = -1;
            ++ptr[0];
        }
        while (Character.isDigit(data.charAt(ptr[0]))) {
            x = x * 10 + data.charAt(ptr[0]++) - '0';
        }
        return x * sgn;
    }
}



public class Codec {
    private int index = 0;
    
    public String serialize(TreeNode root) {
        return serialize(root, new StringBuilder()).substring(1);
    }
    
    private StringBuilder serialize(TreeNode node, StringBuilder builder) {
        builder.append(' ');

        if (node != null) {
            builder.append(node.val);
            serialize(node.left, builder);
            serialize(node.right, builder);
        } else {
            builder.append('#');
        }
        
        return builder;
    }

    public TreeNode deserialize(String data) {
        return deserialize(data.toCharArray());
    }
    
    private TreeNode deserialize(char[] data) {
        if (data[index] == '#') {
            index += 2;
            return null;
        }
        
        int flag = 1;
        int value = 0;
        
        if (data[index] == '-') {
            flag = -1;
            ++index;
        }
        
        while (data[index] != ' ') {
            value = value * 10 + data[index++] - '0';
        }
        
        ++index;
        TreeNode node = new TreeNode(flag * value, deserialize(data), deserialize(data));
        return node;
    }
}