/* 
Given a string s, we make queries on substrings of s.

For each query queries[i] = [left, right, k], we may rearrange the substring s[left], ..., s[right], and then choose up to k of them to replace with any lowercase English letter. 

If the substring is possible to be a palindrome string after the operations above, the result of the query is true. Otherwise, the result is false.

Return an array answer[], where answer[i] is the result of the i-th query queries[i].

Note that: Each letter is counted individually for replacement so if for example s[left..right] = "aaa", and k = 2, we can only replace two of the letters.  (Also, note that the initial string s is never modified by any query.)

 

Example :

Input: s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
Output: [true,false,false,true,true]
Explanation:
queries[0] : substring = "d", is palidrome.
queries[1] : substring = "bc", is not palidrome.
queries[2] : substring = "abcd", is not palidrome after replacing only 1 character.
queries[3] : substring = "abcd", could be changed to "abba" which is palidrome. Also this can be changed to "baab" first rearrange it "bacd" then replace "cd" with "ab".
queries[4] : substring = "abcda", could be changed to "abcba" which is palidrome.
 

Constraints:

1 <= s.length, queries.length <= 10^5
0 <= queries[i][0] <= queries[i][1] < s.length
0 <= queries[i][2] <= s.length
s only contains lowercase English letters.
 */

class Solution {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int[] map = getMap(s);
        List<Boolean> ans = new ArrayList<>();
        for (int[] query: queries) {
            ans.add(check(map, query[0], query[1]) <= query[2]);
        }
        return ans;
    }

    private int check(int[] map, int l, int r) {
        int bitmap = map[l] ^ map[r + 1];
        return count(bitmap) / 2;
    }

    private int count(int x) {
        int sum = 0;
        while (x != 0) {
            sum++;
            x &= x - 1;
        }
        return sum;
    }

    private int[] getMap(String s) {
        int len = s.length();
        int[] map = new int[len + 1];
        char[] chs = s.toCharArray();
        for (int i = 1; i <= len; i++) {
            map[i] = map[i - 1] ^ bit(chs[i - 1]);
        }
        return map;
    }

    private int bit(char c) {
        return 1 << (c - 'a');
    }
}