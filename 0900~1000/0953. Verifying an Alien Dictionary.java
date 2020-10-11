/* 
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

 

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
 */

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] dict = new int[26];
        for (int i = 0; i < 26; i++) dict[order.charAt(i) - 97] = i;
        // System.out.println(Arrays.toString(dict));
        int N = words.length;
        for (int i = 1; i < N; i++) if (! check(words[i-1].toCharArray(), words[i].toCharArray(), dict)) return false;
        return true;
    }

    private boolean check(char[] l, char[] r, int[] dict) {
        int m = l.length, n = r.length;
        int i = 0, j = 0;
        while (i < m) {
            if (j == n) return false;
            if (l[i] == r[j]) {
                i++;
                j++;
            } else if (dict[l[i] - 97] < dict[r[j] - 97]) return true;
            else return false;
        }
        return true;
    }
}