/* 
Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,1,2,1,1].
Example 2:

Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,4,6].
 */


/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    private ArrayList<Integer> l = new ArrayList<Integer>();
    private int pos = 0;
    public NestedIterator(List<NestedInteger> nestedList) {
        // l = new List<Integer>();
        for (NestedInteger i : nestedList) {
            helper(i);
        }
    }
    private void helper(NestedInteger i) {
        if (i.isInteger()) {
            // System.out.print(i.getInteger());
            l.add(i.getInteger());
            return;
        }
        for (NestedInteger j : i.getList()) {
            helper(j);
        }
        return;
    }
    
    @Override
    public Integer next() {
        return l.get(pos++);
    }

    @Override
    public boolean hasNext() {
        return pos < l.size();
    }
}

// 重写, 递归, 

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */



public class NestedIterator implements Iterator<Integer> {

    private List<Integer> list;
    private int index = -1;

    public NestedIterator(List<NestedInteger> nestedList) {
        list = integerIterator(nestedList);
    }

    @Override
    public Integer next() {
        return list.get(++index);
    }

    @Override
    public boolean hasNext() {
        return index + 1 < list.size();
    }

    private static List<Integer> integerIterator(List<NestedInteger> nestedIntegerList) {
        ArrayList<Integer> list = new ArrayList<>(nestedIntegerList.size());
        for (NestedInteger tmp : nestedIntegerList) {
            if (tmp.isInteger()) 
                list.add(tmp.getInteger());
            else 
                list.addAll(integerIterator(tmp.getList()));
        }
        return list;
    }
}