/* 
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 */

// 全排列更新逻辑: 逆次+1 & 重置

class Solution {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (k <= 0 || n < k) return result; // 可删
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i=1; i <= k; i++) nums.add(i);
        result.add(new ArrayList<>(nums.subList(0, k)));
        if (n == k) return result; // 规避循环风险
        nums.add(n+1); // 补上辅助位
        int index = k, end = n-k+1;
        while (index >= 0) {
            if (index == 0) {
            	if (nums.get(0) == end) return result; // [n-k+1,...,n] at last
                // 重置
            	while (index < k && nums.get(index+1) == nums.get(index)+1) nums.set(index, index++ +1);
            } else index--; // 逆序
            nums.set(index, nums.get(index)+1); // +1
            result.add(new ArrayList<>(nums.subList(0, k)));
        }
        return result;
    }
}



class Solution {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (k <= 0 || n < k) return result; // 可删
        int[] nums = new int[k];
        for (int i=0; i < k; i++) nums[i] = i+1;
        result.add(arrayToList(nums));
        if (n == k) return result; // 规避循环风险
        int index = k, end = n-k+1;
        while (index >= 0) {
            if (index == 0) {
            	if (nums[0] == end) return result; // [n-k+1,...,n] at last
                // 重置
            	while (index < k-1 && nums[index+1] == nums[index]+1) nums[index] = ++index;
            } else index--; // 逆序
            nums[index]++; // +1
            result.add(arrayToList(nums));
        }
        return result;
    }

    private static List<Integer> arrayToList(int[] array) {
    	List<Integer> list = new ArrayList<Integer>(array.length);
    	for (int i : array) list.add(i);
    	return list;
    }
}

// ...傻逼代码の傻逼注释
// 使用前请刷新

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        if (k <= 0 || n < k) return result;
        findCombinations(n, k, 1, new Stack<>());
        return result;
    }
    
    private void findCombinations(int n, int k, int index, Stack<Integer> p) {
        if (p.size() == k) {
            result.add(new ArrayList<>(p));
            return;
        }
        for (int i = index; i <= n - (k - p.size()) + 1; i++) {
            p.push(i);
            findCombinations(n, k, i + 1, p);
            p.pop();
        }
    }
}