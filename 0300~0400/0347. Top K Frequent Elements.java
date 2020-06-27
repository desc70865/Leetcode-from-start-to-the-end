/* 
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
It's guaranteed that the answer is unique, in other words the set of the top k frequent elements is unique.
You can return the answer in any order.
 */

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        List<Integer>[] fre = new ArrayList[nums.length + 1];
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        
        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (i >= nums.length-1 || nums[i] != nums[i+1]) {
                if (fre[count] == null) {
                    fre[count] = new ArrayList<>();
                }
                fre[count].add(nums[i]);
                count = 1;
            } else {
                count++;
            }
        }
        
        int[] res = new int[k];
        int cnt = 0, index = fre.length;
        while (index > 0) {
            if (fre[--index] != null) {
                for (int item : fre[index]) {
                    if (cnt >= k) {
                        return res;
                    }
                    res[cnt++] = item;
                }
            }
        }
        
        return res;
    }
}