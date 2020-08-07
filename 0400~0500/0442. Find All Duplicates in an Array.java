/* 
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
 */

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        int[] fra = new int[nums.length+1];
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int n: nums) fra[n]++;
        for (int i=1; i < fra.length; i++) if (fra[i] == 2) res.add(i);
        return res;
    }
}



class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        Arrays.sort(nums);
        List<Integer> res = new LinkedList<>();
        for (int i = 1; i < nums.length; i++) if (nums[i] == nums[i-1]) res.add(nums[i]);
        return res;
    }
}