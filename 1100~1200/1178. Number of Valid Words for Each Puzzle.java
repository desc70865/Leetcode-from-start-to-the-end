/* 
With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
word contains the first letter of puzzle.
For each letter in word, that letter is in puzzle.
For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage"; while invalid words are "beefed" (doesn't include "a") and "based" (includes "s" which isn't in the puzzle).
Return an array answer, where answer[i] is the number of words in the given word list words that are valid with respect to the puzzle puzzles[i].
 

Example :

Input: 
words = ["aaaa","asas","able","ability","actt","actor","access"], 
puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
Output: [1,1,3,2,4,0]
Explanation:
1 valid word for "aboveyz" : "aaaa" 
1 valid word for "abrodyz" : "aaaa"
3 valid words for "abslute" : "aaaa", "asas", "able"
2 valid words for "absoryz" : "aaaa", "asas"
4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
There're no valid words for "gaswxyz" cause none of the words in the list contains letter 'g'.
 

Constraints:

1 <= words.length <= 10^5
4 <= words[i].length <= 50
1 <= puzzles.length <= 10^4
puzzles[i].length == 7
words[i][j], puzzles[i][j] are English lowercase letters.
Each puzzles[i] doesn't contain repeated characters.
 */

class Solution {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> map = new HashMap<>();
        for (String word: words) map.merge(encode(word), 1, Integer::sum);
        List<Integer> res = new ArrayList<>();
        for (String puzzle: puzzles) {
            int mask = encode(puzzle);
            int head = bitmap(puzzle.charAt(0));
            int cur = mask;
            int cnt = 0;
            while (true) {
                if ((head & cur) == head && map.containsKey(cur)) cnt += map.get(cur);
                if (cur == 0) break;
                cur = (cur - 1) & mask;
            }
            res.add(cnt);
        }
        return res;
    }

    private int encode(String s) {
        int res = 0;
        for (char c: s.toCharArray()) res |= bitmap(c);
        return res;
    }

    private int bitmap(char c) {
        return 1 << (c - 97);
    }
}