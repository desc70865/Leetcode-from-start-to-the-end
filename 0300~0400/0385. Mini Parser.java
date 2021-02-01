/* 
Given a nested list of integers represented as a string, implement a parser to deserialize it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Note: You may assume that the string is well-formed:

String is non-empty.
String does not contain white spaces.
String contains only digits 0-9, [, - ,, ].
 

Example 1:

Given s = "324",

You should return a NestedInteger object which contains a single integer 324.
 

Example 2:

Given s = "[123,[456,[789]]]",

Return a NestedInteger object containing a nested list with 2 elements:

1. An integer containing value 123.
2. A nested list containing two elements:
    i.  An integer containing value 456.
    ii. A nested list with one element:
         a. An integer containing value 789.
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    char[] chs;
    int idx = 0;

    public NestedInteger deserialize(String s) {
        chs = s.toCharArray();
        if (chs[0] != '[') {
            return new NestedInteger(Integer.valueOf(s));
        }
        return getNest();
    }

    public NestedInteger getNest() {
        NestedInteger nest = new NestedInteger();
        int sum = 0;
        int sign = 1;
        while (idx++ < chs.length - 1) {
            if (chs[idx] == ',') continue;
            if (chs[idx] == '[') nest.add(getNest());
            else if (chs[idx] == ']') return nest;
            else if (chs[idx] == '-') sign = -1;
            else {
                sum *= 10;
                sum += sign * (chs[idx] - '0');
                if (chs[idx + 1] == ',' || chs[idx + 1] == ']') {
                    nest.add(new NestedInteger(sum));
                    sum = 0;
                    sign = 1;
                }
            }
        }
        return null;
    }
}