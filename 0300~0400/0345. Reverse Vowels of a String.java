/* 
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:

Input: "hello"
Output: "holle"
Example 2:

Input: "leetcode"
Output: "leotcede"
Note:
The vowels does not include the letter "y".
 */

class Solution {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] arr = s.toCharArray();
        int l = 0, r = arr.length-1;
        while (l < r) {
            while (l < r && ! isVowel(arr[l])) {
                l++;
            }
            while (r > l && ! isVowel(arr[r])) {
                r--;
            }
            if (l >= r) {
                break;
            }
            char tmp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = tmp;
        }
        
        return new String(arr);
    }
    
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
            || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}

// a,e,i,o,u
// 1,5,9,15,21



class Solution {
    private Map<Character, Boolean> map;
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        buildMap();
        char[] arr = s.toCharArray();
        int l = 0, r = arr.length-1;
        while (l < r) {
            while (l < r && ! map.getOrDefault(arr[l], false)) {
                l++;
            }
            while (r > l && ! map.getOrDefault(arr[r], false)) {
                r--;
            }
            if (l >= r) {
                break;
            }
            char tmp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = tmp;
        }
        
        return new String(arr);
    }
    
    private void buildMap() {
        map = new HashMap<>();
        char[] v = new char[]{'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        for (char ch : v) {
            map.put(ch, true);
        }
    }
}



class Solution {
    public String reverseVowels(String s) {
        char[] v = new char[]{'a','e', 'i', 'o' , 'u','A','E','I','O','U'};
 
        boolean[] map = new boolean[256];
        for (char ch : v) {
            map[ch] = true;
        }
        
        char[] chars = s.toCharArray();
        int l = 0;
        int r = s.length()-1;
        
        while (l < r) {
            while (l < r && !map[chars[l]]) {
                l++;
            }
            while (l < r && !map[chars[r]]) {
                r--;
            }
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            l++;
            r--;
        }
        return new String(chars);
    }
}