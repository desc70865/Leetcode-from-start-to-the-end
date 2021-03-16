/* 
You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

Example 1:

Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.

Example 2:

Input:
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
Output: []
 */

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int size = words[0].length();
        int len = s.length();
        if (size * words.length > len) return res;
        Map<String, Integer> dict = new HashMap<>();
        for (String word: words) {
            dict.merge(word, 1, Integer::sum);
        }
        for (int i = 0; i < size; i++) {
            Map<String, Integer> cur = new HashMap<>();
            int cnt = 0, left = i, right = i;
            while (right <= len - size) {
                String sub = s.substring(right, right + size);
                if (dict.containsKey(sub)) {
                    cur.merge(sub, 1, Integer::sum);
                    cnt++;
                    while (dict.get(sub) < cur.get(sub)) {
                        cur.merge(s.substring(left, left + size), -1, Integer::sum);
                        cnt--;
                        left += size;
                    }
                    if (cnt == words.length) {
                        res.add(left);
                        cur.merge(s.substring(left, left + size), -1, Integer::sum);
                        cnt--;
                        left += size;
                    }
                } else {
                    cur.clear();
                    cnt = 0;
                    left = right + size;
                }
                right += size;
            }
        }
        return res;
    }
}