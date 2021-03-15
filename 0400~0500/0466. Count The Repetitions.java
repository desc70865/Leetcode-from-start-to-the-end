/* 
Define S = [s,n] as the string S which consists of n connected strings s. For example, ["abc", 3] ="abcabcabc".

On the other hand, we define that string s1 can be obtained from string s2 if we can remove some characters from s2 such that it becomes s1. For example, “abc” can be obtained from “abdbec” based on our definition, but it can not be obtained from “acbbe”.

You are given two non-empty strings s1 and s2 (each at most 100 characters long) and two integers 0 ≤ n1 ≤ 106 and 1 ≤ n2 ≤ 106. Now consider the strings S1 and S2, where S1=[s1,n1] and S2=[s2,n2]. Find the maximum integer M such that [S2,M] can be obtained from S1.

Example:

Input:
s1="acb", n1=4
s2="ab", n2=2

Return:
2
 */

class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (n1 == 0) return 0;
        char[] chs1 = s1.toCharArray(), chs2 = s2.toCharArray();
        int cnt2 = 0;
        Map<Integer, int[]> map = new HashMap<>();
        int[] prev;
        for (int cnt1 = 1, p2 = 0; cnt1 <= n1; cnt1++) {
            for (int p1 = 0; p1 < chs1.length; p1++) {
                if (chs1[p1] == chs2[p2] && p2++ == chs2.length - 1) {
                    p2 = 0;
                    cnt2++;
                }
            }
            if ((prev = map.putIfAbsent(p2, new int[] {cnt1, cnt2})) != null) {
                cnt2 += (n1 - cnt1) / (cnt1 - prev[0]) * (cnt2 - prev[1]);
                cnt1 += (n1 - cnt1) / (cnt1 - prev[0]) * (cnt1 - prev[0]);
            }
        }
        return cnt2 / n2;
    }
}