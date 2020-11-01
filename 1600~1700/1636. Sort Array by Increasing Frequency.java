/* 
Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.

Return the sorted array.

 

Example 1:

Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
Example 2:

Input: nums = [2,3,1,3,2]
Output: [1,3,3,2,2]
Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
Example 3:

Input: nums = [-1,1,-6,4,5,-6,1,4,1]
Output: [5,-1,4,4,-6,-6,1,1,1]
 

Constraints:

1 <= nums.length <= 100
-100 <= nums[i] <= 100
 */

class Solution {
    public int[] frequencySort(int[] nums) {
        int[][] map = new int[201][2];
        for (int i = 0; i <= 200; i++) map[i][0] = i;
        for (int num: nums) map[num + 100][1]++;
        Arrays.sort(map, (x, y) -> x[1] == y[1] ? y[0] - x[0] : x[1] - y[1]);
        int idx = 0;
        for (int i = 0; i <= 200; i++) {
            while (map[i][1]-- > 0) nums[idx++] = map[i][0] - 100;
        }
        return nums;
    }
}



class Solution {
    static final int B = 100;
    public int[] frequencySort(int[] nums) {
        int N = nums.length;
        int[] map = new int[201];
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++) {
            arr[i] = nums[i];
            map[arr[i] + B]++;
        }
        Arrays.sort(arr, (a, b) -> map[a + B] == map[b + B] ? b - a : map[a + B] - map[b + B]);
        return Arrays.stream(arr).mapToInt(Integer::valueOf).toArray();
    }
}