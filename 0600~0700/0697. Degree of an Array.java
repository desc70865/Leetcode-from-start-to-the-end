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
        int min = nums.length;
        for (int i = 0; i < min; i++) {
            map.computeIfAbsent(nums[i], z -> new ArrayList<>()).add(i);
        }
        int max = 0;
        for (List<Integer> list: map.values()) {
            if (max < list.size()) {
                max = list.size();
                min = nums.length; // reset
            }
            if (max == list.size()) {
                min = Math.min(min, list.get(max - 1) - list.get(0) + 1);
            }
        }
        return min;
    }
}



class Solution {
    public int findShortestSubArray(int[] nums) {
        int up = -1, low = 50001;
        for (int num: nums) {
            up = Math.max(up, num);
            low = Math.min(low, num);
        }
        int[][] map = new int[up - low + 1][3];
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int idx = nums[i] - low;
            if (map[idx][2] > 0) {
                map[idx][1] = i;
                map[idx][2]++;
            } else {
                map[idx][0] = map[idx][1] = i;
                map[idx][2] = 1;
            }
        }
        int max = 0;
        int min = len;
        for (int[] arr: map) {
            if (max < arr[2]) {
                max = arr[2];
                min = len;
            }
            if (max == arr[2]) {
                min = Math.min(min, arr[1] - arr[0] + 1);
            }
        }
        return min;
    }
}