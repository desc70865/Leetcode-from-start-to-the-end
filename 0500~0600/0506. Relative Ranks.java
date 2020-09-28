/* 
Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".

Example 1:
Input: [5, 4, 3, 2, 1]
Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal". 
For the left two athletes, you just need to output their relative ranks according to their scores.
Note:
N is a positive integer and won't exceed 10,000.
All the scores of athletes are guaranteed to be unique.
 */

class Solution {
    public String[] findRelativeRanks(int[] nums) {
        int N = nums.length;
        int[][] aux = new int[N][2];
        String[] res = new String[N];
        for (int i = 0; i < N; i++) {
            aux[i][0] = i;
            aux[i][1] = nums[i];
        }
        Arrays.sort(aux, (a, b) -> (b[1] - a[1]));
        for (int i = 0; i < N; i++) {
            if (i == 0) res[aux[i][0]] = "Gold Medal";
            else if (i == 1) res[aux[i][0]] = "Silver Medal";
            else if (i == 2) res[aux[i][0]] = "Bronze Medal";
            else res[aux[i][0]] = "" + (i + 1);
        }
        return res;
    }
}