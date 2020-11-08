/* 
Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.

Example 1:
Input: [1,1,2,2,2]
Output: true

Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
Example 2:
Input: [3,3,3,3,4]
Output: false

Explanation: You cannot find a way to form a square with all the matchsticks.
Note:
The length sum of the given matchsticks is in the range of 0 to 10^9.
The length of the given matchstick array will not exceed 15.
 */

class Solution {
    public boolean makesquare(int[] nums) {
        int sum = 0;
        for (int num: nums) sum += num;
        if (sum == 0 || sum % 4 != 0) return false;
        return dfs(nums, sum / 4, 1, sum / 4, 0, new boolean[nums.length]);
    }

    private boolean dfs(int[] nums, int side, int cnt, int rem, int idx, boolean[] used) {
        if (rem == 0) {
            if (cnt == 4) return true;
            return dfs(nums, side, cnt + 1, side, 0, used);
        }
        for (int i = idx; i < nums.length; i++) {
            if (used[i]) continue;
            if (nums[i] > rem) continue;
            used[i] = true;
            if (dfs(nums, side, cnt, rem - nums[i], i + 1, used)) return true;
            used[i] = false;
        }
        return false;
    }
}