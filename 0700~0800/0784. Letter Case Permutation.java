/* 
Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.

Return a list of all possible strings we could create. You can return the output in any order.

 

Example 1:

Input: S = "a1b2"
Output: ["a1b2","a1B2","A1b2","A1B2"]
Example 2:

Input: S = "3z4"
Output: ["3z4","3Z4"]
Example 3:

Input: S = "12345"
Output: ["12345"]
Example 4:

Input: S = "0"
Output: ["0"]
 

Constraints:

S will be a string with length between 1 and 12.
S will consist only of letters or digits.
 */

class Solution {
    List<String> res;
    public List<String> letterCasePermutation(String S) {
        res = new ArrayList<>();
        dfs(S.toCharArray(), S.length() - 1);
        return res;
    }

    private void dfs(char[] s, int idx) {
        if (idx < 0) {
            res.add(new String(s));
            return;
        }
        dfs(s, idx - 1);
        if (s[idx] > '9') {
            if (s[idx] < 'a') {
                s[idx] += 32;
                dfs(s, idx - 1);
                s[idx] -= 32;
            } else {
                s[idx] -= 32;
                dfs(s, idx - 1);
                s[idx] += 32;
            }
        }
    }
}