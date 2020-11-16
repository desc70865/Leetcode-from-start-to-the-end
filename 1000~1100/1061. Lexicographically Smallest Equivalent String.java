/* 
Given strings A and B of the same length, we say A[i] and B[i] are equivalent characters. For example, if A = "abc" and B = "cde", then we have 'a' == 'c', 'b' == 'd', 'c' == 'e'.

Equivalent characters follow the usual rules of any equivalence relation:

Reflexivity: 'a' == 'a'
Symmetry: 'a' == 'b' implies 'b' == 'a'
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'
For example, given the equivalency information from A and B above, S = "eed", "acd", and "aab" are equivalent strings, and "aab" is the lexicographically smallest equivalent string of S.

Return the lexicographically smallest equivalent string of S by using the equivalency information from A and B.

 

Example 1:

Input: A = "parker", B = "morris", S = "parser"
Output: "makkek"
Explanation: Based on the equivalency information in A and B, we can group their characters as [m,p], [a,o], [k,r,s], [e,i]. The characters in each group are equivalent and sorted in lexicographical order. So the answer is "makkek".
Example 2:

Input: A = "hello", B = "world", S = "hold"
Output: "hdld"
Explanation:  Based on the equivalency information in A and B, we can group their characters as [h,w], [d,e,o], [l,r]. So only the second letter 'o' in S is changed to 'd', the answer is "hdld".
Example 3:

Input: A = "leetcode", B = "programs", S = "sourcecode"
Output: "aauaaaaada"
Explanation:  We group the equivalent characters in A and B as [a,o,e,r,s,c], [l,p], [g,t] and [d,m], thus all letters in S except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".
 

Note:

String A, B and S consist of only lowercase English letters from 'a' - 'z'.
The lengths of string A, B and S are between 1 and 1000.
String A and B are of the same length.
 */

class Solution {
    int[] map;

    public String smallestEquivalentString(String A, String B, String S) {
        getMap(A, B);
        char[] res = new char[S.length()];
        int idx = 0;
        for (char c: S.toCharArray()) {
            res[idx++] = (char) (map[c - 97] + 97);
        }
        return new String(res);
    }

    private void getMap(String a, String b) {
        char[] ch1 = a.toCharArray();
        char[] ch2 = b.toCharArray();
        int len = ch1.length;
        map = new int[26];
        for (int i = 1; i < 26; i++) {
            map[i] = i;
        }
        for (int i = 0; i < len; i++) {
            if (ch1[i] == ch2[i]) continue;
            union(ch1[i] - 97, ch2[i] - 97);
        }
        for (int i = 0; i < 26; i++) {
            find(i);
        }
    }

    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        if (a > b) map[a] = b;
        else map[b] = a;
    }

    private int find(int x) {
        return map[x] == x ? x : (map[x] = find(map[x]));
    }
}