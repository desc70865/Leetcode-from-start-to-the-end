/* 
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/reorganize-string/solution/zhe-ti-bu-nan-by-keylol/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public String reorganizeString(String S) {
        int[] map = count(S);
        int N = S.length();
        int idx = 0;
        for (int i = 0; i < 26; i++) {
            if (map[i] > map[idx]) idx = i;
        }
        
        if (map[idx] > (N + 1) / 2) return "";
        
        char[] res = new char[N];
        for (int i = 0; i < N; i += 2) {
            while (map[idx]-- == 0) idx = next(idx);
            res[i] = (char) (idx + 97);
        }
        for (int i = 1; i < N; i += 2) {
            while (map[idx]-- == 0) idx = next(idx);
            res[i] = (char) (idx + 97);
        }
        
        return new String(res);
    }
    
    private int next(int x) {
        return (x + 1) % 26;
    }
    
    private int[] count(String s) {
        int[] map = new int[26];
        for (char c: s.toCharArray()) map[c - 97]++;
        return map;
    }
}