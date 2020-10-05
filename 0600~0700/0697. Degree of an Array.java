/* 
Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

 

Example 1:

Input: nums = [1,2,2,3,1]
Output: 2
Explanation: 
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:

Input: nums = [1,2,2,3,1,4,2]
Output: 6
Explanation: 
The degree is 3 because the element 2 is repeated 3 times.
So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.
 

Constraints:

nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.
 */

class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            List<Integer> list;
            if (! map.containsKey(nums[i])) list = new ArrayList<>();
            else list = map.get(nums[i]);
            list.add(i);
            map.put(nums[i], list);
        }
        int max = 0;
        int min = N;
        for (Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
            int num = entry.getKey();
            List<Integer> list = entry.getValue();
            if (list.size() > max) {
                max = list.size();
                min = N;
            }
            if (list.size() == max) {
                min = Math.min(min, list.get(max - 1) - list.get(0) + 1);
            }
        }
        return min;
    }
}



class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int N = nums.length;
        int[] p;
        // [l, r, cnt]
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (! map.containsKey(nums[i])) p = new int[3];
            else p = map.get(nums[i]);
            if (p[0] == 0) p[0] = i + 1;
            else p[1] = i + 1;
            max = Math.max(max, ++p[2]);
            map.put(nums[i], p);
        }
        if (max == 1) return 1;
        int min = N;
        for (Map.Entry<Integer, int[]> entry: map.entrySet()) {
            p = entry.getValue();
            if (p[2] == max) min = Math.min(min, p[1] - p[0] + 1);
        }
        return min;
    }
}