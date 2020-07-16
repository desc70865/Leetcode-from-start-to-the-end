/* 
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

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
    private int index = 0;
    
    public String serialize(TreeNode root) {
        return serialize(root, new StringBuilder()).substring(1);
    }
    
    private StringBuilder serialize(TreeNode node, StringBuilder builder) {
        builder.append(',');

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
        
        while (data[index] != ',') {
            value = value * 10 + data[index++] - '0';
        }
        
        ++index;
        TreeNode node = new TreeNode(flag * value, deserialize(data), deserialize(data));
        return node;
    }
}