/* 
Given a list of phrases, generate a list of Before and After puzzles.

A phrase is a string that consists of lowercase English letters and spaces only. No space appears in the start or the end of a phrase. There are no consecutive spaces in a phrase.

Before and After puzzles are phrases that are formed by merging two phrases where the last word of the first phrase is the same as the first word of the second phrase.

Return the Before and After puzzles that can be formed by every two phrases phrases[i] and phrases[j] where i != j. Note that the order of matching two phrases matters, we want to consider both orders.

You should return a list of distinct strings sorted lexicographically.

 

Example 1:

Input: phrases = ["writing code","code rocks"]
Output: ["writing code rocks"]
Example 2:

Input: phrases = ["mission statement",
                  "a quick bite to eat",
                  "a chip off the old block",
                  "chocolate bar",
                  "mission impossible",
                  "a man on a mission",
                  "block party",
                  "eat my words",
                  "bar of soap"]
Output: ["a chip off the old block party",
         "a man on a mission impossible",
         "a man on a mission statement",
         "a quick bite to eat my words",
         "chocolate bar of soap"]
Example 3:

Input: phrases = ["a","b","a"]
Output: ["a"]
 

Constraints:

1 <= phrases.length <= 100
1 <= phrases[i].length <= 100
 */

class Solution {
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        Map<String, List<Integer>> pre = new HashMap<>();
        Map<String, List<Integer>> tail = new HashMap<>();
        int len = phrases.length;
        for (int i = 0; i < len; i++) {
            String[] cur = phrases[i].split(" ");
            pre.computeIfAbsent(cur[0], z -> new ArrayList<>()).add(i);
            tail.computeIfAbsent(cur[cur.length - 1], z -> new ArrayList<>()).add(i);
        }
        Set<String> res = new HashSet<>();
        for (String s: pre.keySet()) {
            if (! tail.containsKey(s)) continue;
            int curLen = s.length();
            for (int i: pre.get(s)) {
                for (int j: tail.get(s)) {
                    if (i == j) continue;
                    String a = phrases[j];
                    res.add(a.substring(0, a.length() - curLen) + phrases[i]);
                }
            }
        }
        List<String> list = new ArrayList<>(res);
        Collections.sort(list);
        return list;
    }
}