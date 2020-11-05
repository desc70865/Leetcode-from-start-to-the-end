/* 
Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.

 

Example 1:

Input: s = "eleetminicoworoep"
Output: 13
Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.
Example 2:

Input: s = "leetcodeisgreat"
Output: 5
Explanation: The longest substring is "leetc" which contains two e's.
Example 3:

Input: s = "bcbcbc"
Output: 6
Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.
 

Constraints:

1 <= s.length <= 5 x 10^5
s contains only lowercase English letters.
 */

class Solution {
    public int findTheLongestSubstring(String s) {
        int[] shift = new int[26];
        shift['a' - 97] = 1;
        shift['e' - 97] = 2;
        shift['i' - 97] = 4;
        shift['o' - 97] = 8;
        shift['u' - 97] = 16;

        int[] start = new int[32];
        Arrays.fill(start, -2);
        start[0] = -1;
        int[] end = new int[32];
        Arrays.fill(end, -1);

        char[] str = s.toCharArray();
        int len = str.length;
        int x = 0;
        for (int i = 0; i < len; i++) {
            x ^= shift[str[i] - 97];
            if (start[x] == -2) start[x] = i;
            else end[x] = i;
        }

        int max = 0;
        for (int i = 0; i < 32; i++) {
            if (end[i] == -1) continue;
            max = Math.max(max, end[i] - start[i]);
        }
        return max;
    }
}



class Solution {
    public int findTheLongestSubstring(String s) {
        char[] vowel = new char[] {'a', 'e', 'i', 'o', 'u'};
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        char[] str = s.toCharArray();
        int len = str.length;
        int x = 0;
        int max = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < 5; j++) {
                if (str[i] == vowel[j]) {
                    x ^= 1 << j;
                    map.putIfAbsent(x, i);
                    break;
                }
            }
            max = Math.max(max, i - map.get(x));
        }
        return max;
    }
}