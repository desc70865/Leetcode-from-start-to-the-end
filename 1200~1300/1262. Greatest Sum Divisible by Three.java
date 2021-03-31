/* 
Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.

 

Example 1:

Input: nums = [3,6,5,1,8]
Output: 18
Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
Example 2:

Input: nums = [4]
Output: 0
Explanation: Since 4 is not divisible by 3, do not pick any number.
Example 3:

Input: nums = [1,2,3,4,4]
Output: 12
Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
 

Constraints:

1 <= nums.length <= 4 * 10^4
1 <= nums[i] <= 10^4
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/greatest-sum-divisible-by-three/solution/jing-tai-gui-hua-by-keylol/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public int maxSumDivThree(int[] nums) {
        int[][] min = new int[3][2];
        Arrays.fill(min[1], 10001);
        Arrays.fill(min[2], 10001);
        int sum = 0;
        for (int num: nums) {
            sum += num;
            update(min[num % 3], num);
        }
        int[] res = new int[3];
        res[1] = Math.min(min[1][0], min[2][0] + min[2][1]);
        res[2] = Math.min(min[2][0], min[1][0] + min[1][1]);
        return sum - res[sum % 3];
    }

    private void update(int[] min, int num) {
        if (num >= min[1]) ;
        else if (num < min[0]) {
            min[1] = min[0];
            min[0] = num;
        } else {
            min[1] = num;
        }
    }
}



class Solution {
    public int maxSumDivThree(int[] nums) {
        int INF = Integer.MIN_VALUE;
        int[] dp = new int[] {0, INF, INF};
        for (int num: nums) dp = update(dp, num);
        return dp[0];
    }

    private int[] update(int[] dp, int num) {
        int[] tmp = new int[3];
        for (int i = 0; i < 3; i++) {
            int cur = (i + num) % 3;
            tmp[cur] = Math.max(dp[i] + num, dp[cur]);
        }
        return tmp;
    }
}



class Solution {
    public int maxSumDivThree(int[] nums) {
        int INF = 10001;
        PriorityQueue<Integer>[] min = new PriorityQueue[3];
        for (int i = 0; i < 3; i++) {
            min[i] = new PriorityQueue<Integer>();
            min[i].offer(i * INF);
            min[i].offer(i * INF);
        }
        int sum = 0;
        for (int num: nums) {
            sum += num;
            min[num % 3].offer(num);
        }
        int[] res = new int[] {0, min[1].peek(), min[2].peek()};
        res[1] = Math.min(res[1], min[2].poll() + min[2].poll());
        res[2] = Math.min(res[2], min[1].poll() + min[1].poll());
        return sum - res[sum % 3];
    }
}