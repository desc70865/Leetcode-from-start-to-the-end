/* 
Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

Begin with the first character and then the number of characters abbreviated, which followed by the last character.
If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
If the abbreviation doesn't make the word shorter, then keep it as original.
Example:
Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
Note:
Both n and the length of each word will not exceed 400.
The length of each word is greater than 1.
The words consist of lowercase English letters only.
The return answers should be in the same order as the original array.
 */

class Solution {
    public List<String> wordsAbbreviation(List<String> dict) {
        if (dict.isEmpty()) return new ArrayList<>();
        int len = dict.size();
        String[] ans = new String[len];
        Map<String, Integer> map = new HashMap<>();
        int size = 1;
        for (int i = 0; i < len; i++) {
            map.merge(ans[i] = getAbbr(dict.get(i), size), 1, Integer::sum);
        }
        while (true) {
            boolean TBC = true;
            size++;
            for (int i = 0; i < len; i++) {
                if (map.get(ans[i]) > 1) {
                    TBC = false;
                    map.merge(ans[i] = getAbbr(dict.get(i), size), 1, Integer::sum);
                }
            }
            if (TBC) break;
        }
        return Arrays.asList(ans);
    }

    private String getAbbr(String s, int size) {
        int len = s.length();
        if (size + 2 >= len) {
            return s;
        }
        return s.substring(0, size) + (len - size - 1) + s.charAt(len - 1);
    }
}