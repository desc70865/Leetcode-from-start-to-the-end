/* 
Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.

You may return the answer in any order.

 

Example 1:

Input: ["bella","label","roller"]
Output: ["e","l","l"]
Example 2:

Input: ["cool","lock","cook"]
Output: ["c","o"]
 

Note:

1 <= A.length <= 100
1 <= A[i].length <= 100
A[i][j] is a lowercase letter
 */

class Solution {
    public List<String> commonChars(String[] A) {
        int N = A.length;
        int[] map = count(A[0]);
        for (int i = 1; i < N; i++) merge(map, count(A[i]));
        List<String> res = new ArrayList<>();
        for (int j = 0; j < 26; j++) {
            for (int k = 0; k < map[j]; k++) {
                res.add(Character.toString(j + 97));
            }
        }
        return res;
    }

    private void merge(int[] s1, int[] s2) {
        for (int i = 0; i < 26; i++) {
            if (s1[i] == 0) continue;
            s1[i] = Math.min(s1[i], s2[i]);
        }
    }

    private int[] count(String s) {
        int[] cnt = new int[26];
        for (char c: s.toCharArray()) cnt[c - 97]++;
        return cnt;
    }
}