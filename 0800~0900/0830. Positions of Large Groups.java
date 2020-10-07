/* 
In a string s of lowercase letters, these letters form consecutive groups of the same character.

For example, a string like s = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z", and "yy".

A group is identified by an interval [start, end], where start and end denote the start and end indices (inclusive) of the group. In the above example, "xxxx" has the interval [3,6].

A group is considered large if it has 3 or more characters.

Return the intervals of every large group sorted in increasing order by start index.

 

Example 1:

Input: s = "abbxxxxzzy"
Output: [[3,6]]
Explanation: "xxxx" is the only large group with start index 3 and end index 6.
Example 2:

Input: s = "abc"
Output: []
Explanation: We have groups "a", "b", and "c", none of which are large groups.
Example 3:

Input: s = "abcdddeeeeaabbbcd"
Output: [[3,5],[6,9],[12,14]]
Explanation: The large groups are "ddd", "eeee", and "bbb".
Example 4:

Input: s = "aba"
Output: []
 

Constraints:

1 <= s.length <= 1000
s contains lower-case English letters only.
 */

class Solution {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        char[] str = s.toCharArray();
        int N = str.length;
        int n = 0;
        char c = str[0];
        for (int i = 0; i < N; i++) {
            if (str[i] == c) n++;
            else {
                if (n >= 3) {
                    List<Integer> p = new ArrayList<>();
                    p.add(i - n);
                    p.add(i - 1);
                    res.add(p);
                }
                n = 1;
                c = str[i];
            }
        }
        if (n >= 3) {
            List<Integer> p = new ArrayList<>();
            p.add(N - n);
            p.add(N - 1);
            res.add(p);
        }
        return res;
    }
}