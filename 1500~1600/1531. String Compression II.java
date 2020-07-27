/* 
Run-length encoding is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".

Notice that in this problem, we are not adding '1' after single characters.

Given a string s and an integer k. You need to delete at most k characters from s such that the run-length encoded version of s has minimum length.

Find the minimum length of the run-length encoded version of s after deleting at most k characters.

 

Example 1:

Input: s = "aaabcccd", k = 2
Output: 4
Explanation: Compressing s without deleting anything will give us "a3bc3d" of length 6. Deleting any of the characters 'a' or 'c' would at most decrease the length of the compressed string to 5, for instance delete 2 'a' then we will have s = "abcccd" which compressed is abc3d. Therefore, the optimal way is to delete 'b' and 'd', then the compressed version of s will be "a3c3" of length 4.
Example 2:

Input: s = "aabbaa", k = 2
Output: 2
Explanation: If we delete both 'b' characters, the resulting compressed string would be "a4" of length 2.
Example 3:

Input: s = "aaaaaaaaaaa", k = 0
Output: 3
Explanation: Since k is zero, we cannot delete anything. The compressed string is "a11" of length 3.
 

Constraints:

1 <= s.length <= 100
0 <= k <= s.length
s contains only lowercase English letters.
 */

class Solution {
    public int getLengthOfOptimalCompression(String s_, int K) {
        char[] s = s_.toCharArray();
        int n = s.length;
        
        int[][][] dp = new int[26][1][K+1];
        for(int i = 0;i < 26;i++){
            for(int j = 0;j <= 0;j++){
                Arrays.fill(dp[i][j], 99999999);
            }
        }
        dp[0][0][0] = 0;
        int le = 1;
        for(char c : s){
            int[][][] ndp = new int[26][le+1][K+1];
            for(int i = 0;i < 26;i++){
                for(int j = 0;j <= le;j++){
                    Arrays.fill(ndp[i][j], 99999999);
                }
            }
            int cc = c-'a';
            for(int i = 0;i < 26;i++){
                for(int j = 0;j < le;j++){
                    for(int k = 0;k <= K;k++){
                        if(dp[i][j][k] >= 99999999)continue;
                        // not erase
                        if(i != cc){
                            int len = j == 0 ? 0 : j == 1 ? 1 : j <= 9 ? 2 : j <= 99 ? 3 : 4;
                            ndp[cc][1][k] = Math.min(ndp[cc][1][k], dp[i][j][k] + len);
                        }else{
                            ndp[i][j+1][k] = Math.min(ndp[i][j+1][k], dp[i][j][k]);
                        }
                        
                        // erase
                        if(k+1 <= K)ndp[i][j][k+1] = Math.min(ndp[i][j][k+1], dp[i][j][k]);
                    }
                }
            }
            le++;
            dp = ndp;
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0;i < 26;i++){
            for(int j = 0;j <= n;j++){
                for(int k = 0;k <= K;k++){
                    int len = j == 0 ? 0 : j == 1 ? 1 : j <= 9 ? 2 : j <= 99 ? 3 : 4;
                    ans = Math.min(ans, dp[i][j][k] + len);
                }
            }
        }
        return ans;
    }
}	