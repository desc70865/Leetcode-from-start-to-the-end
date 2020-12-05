/* 
Given an array equations of strings that represent relationships between variables, each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.

Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.

 

Example 1:

Input: ["a==b","b!=a"]
Output: false
Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
Example 2:

Input: ["b==a","a==b"]
Output: true
Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
Example 3:

Input: ["a==b","b==c","a==c"]
Output: true
Example 4:

Input: ["a==b","b!=c","c==a"]
Output: false
Example 5:

Input: ["c==c","b==d","x!=z"]
Output: true
 

Note:

1 <= equations.length <= 500
equations[i].length == 4
equations[i][0] and equations[i][3] are lowercase letters
equations[i][1] is either '=' or '!'
equations[i][2] is '='
 */

class Solution {
    int[] p = new int[26];

    public boolean equationsPossible(String[] equations) {
        for (int i = 1; i < 26; i++) {
            p[i] = i;
        }
        int len = equations.length;
        char[][] chs = new char[len][4];
        for (int i = 0; i < len; i++) {
            chs[i] = equations[i].toCharArray();
        }
        for (char[] arr: chs) {
            if (arr[1] == '=') {
                union(arr[0] - 97, arr[3] - 97);
            }
        }
        for (char[] arr: chs) {
            if (arr[1] == '!') {
                if (find(arr[0] - 97) == find(arr[3] - 97))
                    return false;
            }
        }
        return true;
    }

    private int find(int x) {
        return x == p[x] ? x : (p[x] = find(p[x]));
    }

    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        if (a < b) p[b] = a;
        else p[a] = b;
    }
}