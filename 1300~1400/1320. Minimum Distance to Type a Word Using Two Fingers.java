/* 
You have a keyboard layout as shown above in the XY plane, where each English uppercase letter is located at some coordinate, for example, the letter A is located at coordinate (0,0), the letter B is located at coordinate (0,1), the letter P is located at coordinate (2,3) and the letter Z is located at coordinate (4,1).

Given the string word, return the minimum total distance to type such string using only two fingers. The distance between coordinates (x1,y1) and (x2,y2) is |x1 - x2| + |y1 - y2|. 

Note that the initial positions of your two fingers are considered free so don't count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.

 

Example 1:

Input: word = "CAKE"
Output: 3
Explanation: 
Using two fingers, one optimal way to type "CAKE" is: 
Finger 1 on letter 'C' -> cost = 0 
Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2 
Finger 2 on letter 'K' -> cost = 0 
Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1 
Total distance = 3
Example 2:

Input: word = "HAPPY"
Output: 6
Explanation: 
Using two fingers, one optimal way to type "HAPPY" is:
Finger 1 on letter 'H' -> cost = 0
Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
Finger 2 on letter 'P' -> cost = 0
Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
Total distance = 6
Example 3:

Input: word = "NEW"
Output: 3
Example 4:

Input: word = "YEAR"
Output: 7
 

Constraints:

2 <= word.length <= 300
Each word[i] is an English uppercase letter.
 */

// One important observation is that, one finger will always stay at A[i - 1] after the last move.
// left[i] : so far the optimal result if previous char tapped by the left finger, and the last char tapped by the right finger is 'A' + i
// At each position, there are two options either keep using the left finger, or switch from right finger to left finger
// Due to symmetry, if right[] is created as left[], we have right[i] = left[i] for all i. We may only consider left[], and ignore right[]

class Solution {
    Integer[][] dis = new Integer[26][26];

    public int minimumDistance(String word) {
        int[] dp = new int[26];
        char[] chs = word.toCharArray();
        int len = chs.length;

        for (int i = 1; i < len; i++) {
            int preIdx = chs[i - 1] - 'A';
            int curIdx = chs[i] - 'A';
            if (curIdx == preIdx) continue;
            int minCostShift = Integer.MAX_VALUE;
            for (int j = 0; j < 26; j++) {
                minCostShift = Math.min(minCostShift, dp[j] + getKeyDistance(j, curIdx));
                dp[j] += getKeyDistance(preIdx, curIdx);
            }
            dp[preIdx] = Math.min(dp[preIdx], minCostShift);
        }
        // System.out.println(Arrays.toString(dp));
        int count = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++) {
            count = Math.min(count, dp[i]);
        }
        return count;
    }

    private int getKeyDistance(int i, int j) {
        if (dis[i][j] != null) return dis[i][j];
        return dis[j][i] = dis[i][j] = Math.abs(i / 6 - j / 6) + Math.abs(i % 6 - j % 6);
    }
}