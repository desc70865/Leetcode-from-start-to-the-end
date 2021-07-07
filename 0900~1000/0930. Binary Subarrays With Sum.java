/* 
In an array A of 0s and 1s, how many non-empty subarrays have sum S?

 

Example 1:

Input: A = [1,0,1,0,1], S = 2
Output: 4
Explanation: 
The 4 subarrays are bolded below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
 

Note:

A.length <= 30000
0 <= S <= A.length
A[i] is either 0 or 1.
 */

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int[] map = new int[30001];
        map[0] = 1;
        int sum = 0;
        int ans = 0;
        for (int num: nums) {
            sum += num;
            if (sum >= goal) {
                ans += map[sum - goal];
            }
            ++map[sum];
        }
        return ans;
    }
}