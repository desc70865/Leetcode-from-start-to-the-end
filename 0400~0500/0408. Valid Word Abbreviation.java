/* 
Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.
Example 2:
Given s = "apple", abbr = "a2e":

Return false.
 */

class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        char[] chars = abbr.toCharArray();
        int num = 0;
        int next = 0;
        for (char c: chars) {
            if (c >= '0' && c <= '9') {
                if (num == 0 && c == '0') return false ;
                num = num* 10 + (c - '0');
                continue;
            }
            
            next = next + num;
            if (next >= word.length() || (word.charAt(next) != c)) {
                return false;
            }
            next++;
            num = 0;
        }
        return next + num == word.length();
    }
}