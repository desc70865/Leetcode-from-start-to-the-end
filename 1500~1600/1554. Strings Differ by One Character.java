/* 
Given a list of strings dict where all the strings are of the same length.

Return True if there are 2 strings that only differ by 1 character in the same index, otherwise return False.

Follow up: Could you solve this problem in O(n*m) where n is the length of dict and m is the length of each string.

 

Example 1:

Input: dict = ["abcd","acbd", "aacd"]
Output: true
Explanation: Strings "abcd" and "aacd" differ only by one character in the index 1.
Example 2:

Input: dict = ["ab","cd","yz"]
Output: false
Example 3:

Input: dict = ["abcd","cccc","abyd","abab"]
Output: true
 

Constraints:

Number of characters in dict <= 10^5
dict[i].length == dict[j].length
dict[i] should be unique.
dict[i] contains only lowercase English letters.
 */

class Solution {
    public boolean differByOne(String[] dict) {
        Set<String> set = new HashSet<>();
        for (String s: dict) {
            char[] chs = s.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                char c = chs[i];
                chs[i] = '*';
                if (! set.add(new String(chs))) {
                    return true;
                }
                chs[i] = c;
            }
        }
        return false;
    }
}



class Solution {
    public boolean differByOne(String[] dict) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < dict[0].length(); i++) {
            for (int j = 0; j < dict.length; j++) {
                String s = dict[j];
                if (! set.add(s.substring(0, i) + s.substring(i + 1))) {
                    return true;
                }
            }
            set.clear();
        }
        return false;
    }
}