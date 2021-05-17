/* 
Return the number of distinct non-empty substrings of text that can be written as the concatenation of some string with itself (i.e. it can be written as a + a where a is some string).

 

Example 1:

Input: text = "abcabcabc"
Output: 3
Explanation: The 3 substrings are "abcabc", "bcabca" and "cabcab".
Example 2:

Input: text = "leetcodeleetcode"
Output: 2
Explanation: The 2 substrings are "ee" and "leetcodeleetcode".
 

Constraints:

1 <= text.length <= 2000
text has only lowercase English letters.
 */

class Solution {
    static final int BASE = 37;
    static final int MOD = 1_000_000_007;

    public int distinctEchoSubstrings(String text) {
        char[] s = text.toCharArray();
        int n = s.length;
        long[] preSum = new long[n + 1];
        long[] pow = new long[n + 1];
        pow[0] = 1L;
        for (int i = 1; i <= n; ++i) {
            preSum[i] = (preSum[i - 1] * BASE + s[i - 1] - 96) % MOD;
            pow[i] = (pow[i - 1] * BASE) % MOD;
        }
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            for (int k = 2; i + k <= n; k += 2) {
                int m = i + k / 2;
                long head = (preSum[m] - preSum[i] * pow[k / 2] % MOD + MOD) % MOD;
                long tail = (preSum[i + k] - preSum[m] * pow[k / 2] % MOD + MOD) % MOD;
                if (head == tail) {
                    set.add(head);
                }
            }
        }
        return set.size();
    }
}