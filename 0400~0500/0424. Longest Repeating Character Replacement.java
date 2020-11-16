/* 
Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.

In one operation, you can choose any character of the string and change it to any other uppercase English character.

Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
 

Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
 */

class Solution {
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int[] map = new int[26];
        char[] chs = s.toCharArray();
        int left = 0;
        int right = 0;
        int max = 0;
        for (right = 0; right < chs.length; right++) {
            max = Math.max(max, ++map[chs[right] - 'A']);
            // core
            if (right - left >= max + k) map[chs[left++] - 'A']--;
        }
        return chs.length - left;
    }
}



class Solution {
    int max;

    public int characterReplacement(String s, int k) {
        int len = s.length();
        if (len == 0) return 0;
        max = 1 + k;
        List[] list = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            list[i] = new ArrayList<>();
        }
        int idx = 0;
        for (char c: s.toCharArray()) {
            list[c - 'A'].add(idx++);
        }
        for (List<Integer> p: list) {
            search(p, k);
        }
        return Math.min(len, max);
    }

    private void search(List<Integer> list, int k) {
        int len = list.size();
        int i = 0;
        int j = 0;
        while (j < len - 1) {
            while (j < len - 1 && getLen(list, i, j + 1) <= k) {
                j++;
            }
            max = Math.max(max, j - i + 1 + k);
            i++;
        }
    }

    private int getLen(List<Integer> list, int i, int j) {
        int res = list.get(j) - list.get(i) - (j - i);
        return res;
    }
}