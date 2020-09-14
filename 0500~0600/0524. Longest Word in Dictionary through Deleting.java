/* 
Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output: 
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output: 
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.
 */

class Solution {
    public String findLongestWord(String s, List<String> d) {
        Collections.sort(d, new MyComparator());
        for (String p: d) if (check(s.toCharArray(), p.toCharArray())) return p;
        return "";
    }

    private boolean check(char[] s, char[] p) {
        int N = p.length, j = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == p[j]) j++;
            if (j == N) break;
        }
        return j == N;
    }

    public class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            if (o1.length() != o2.length()) return o2.length() - o1.length();
            return (o1 + o2).compareTo(o2 + o1);
        }
    }
}