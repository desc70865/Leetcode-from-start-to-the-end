/* 
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Example 1:

Input: pattern = "abba", str = "dog cat cat dog"
Output: true
Example 2:

Input:pattern = "abba", str = "dog cat cat fish"
Output: false
Example 3:

Input: pattern = "aaaa", str = "dog cat cat dog"
Output: false
Example 4:

Input: pattern = "abba", str = "dog dog dog dog"
Output: false
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.
 */

class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] arr = str.split(" ");
        char[] index = pattern.toCharArray();
        if (arr.length != index.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            char key = index[i];
            if (map.containsKey(key)) {
                if (! map.get(key).equals(arr[i])) {
                    return false;
                }
            } else {
                map.put(key, arr[i]);
                if (! set.add(arr[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}



class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        Map index = new HashMap();
        for (Integer i = 0; i < words.length; ++i) {
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i)) {
                // compare old i
                return false;
            }
        }
        return true;
    }
}