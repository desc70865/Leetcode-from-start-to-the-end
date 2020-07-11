/* 
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */

class Solution {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int max = 1 << nums.length;
        for (int k = 0; k < max; ++k) {
            List<Integer> temp = convertIntToSet(nums, k);
            res.add(temp);
        }
        return res;
    }
    private static List<Integer> convertIntToSet(int[] nums, int k) {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        int idx = 0;
        for (int i = k; i > 0; i >>= 1) {
            if ((i & 1) == 1) temp.add(nums[idx]);
            ++idx;
        }
        return temp;
    }
}

class Solution {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> fuck = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, 0, new Stack<Integer>(), fuck);
        return fuck;
    }
    private static void helper(int[] nums, int i, Stack<Integer> p, List<List<Integer>> fuck) {
        if (i == nums.length) {
            fuck.add(new ArrayList<>(p));
            return;
        } else {
            helper(nums, i+1, p, fuck);
            p.push(nums[i]);
            helper(nums, i+1, p, fuck);
            p.pop();
        }
    }
}

class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        helper(nums, 0, new ArrayList<Integer>());
        return res;
    }
    private void helper(int[] nums, int i, ArrayList<Integer> p) {
        if (i == nums.length) {
            res.add(new ArrayList<>(p));
            return;
        } else {
            helper(nums, i+1, p);
            p.add(nums[i]);
            helper(nums, i+1, p);
            p.remove(p.size() - 1);
        }
    }
}



class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        // Arrays.sort(nums);
        helper(nums, 0, new ArrayList<Integer>());
        return res;
    }
    private void helper(int[] nums, int index, ArrayList<Integer> p) {
        res.add(new ArrayList<>(p));
        for (int i = index; i < nums.length; i++) {
            p.add(nums[i]);
            helper(nums, i+1, p);
            p.remove(p.size() - 1);
        }
    }
}