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
        int[][] cnt = new int[N][26];
        int idx = 0;
        for (String s: A) {
            for (char c: s.toCharArray()) cnt[idx][c - 97]++;
            idx++;
        }
        List<String> res = new ArrayList<>();
        for (int j = 0; j < 26; j++) {
            int min = cnt[0][j];
            for (int i = 0; i < N && min > 0; i++) {
                min = Math.min(min, cnt[i][j]);
            }
            for (int k = 0; k < min; k++) res.add((char) (j + 97) + "");
        }
        return res;
    }
}