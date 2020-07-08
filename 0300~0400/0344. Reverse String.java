/* 
Write a function that reverses a string. The input string is given as an array of characters char[].

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

You may assume all the characters consist of printable ascii characters.

 

Example 1:

Input: ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
 */

class Solution {
    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            swap(s, l++, r--);
        }
    }
    
    private void swap(char[] nums, int i, int j) {
        if (nums[i] == nums[j]) {
            return;
        }
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}



class Solution {
    public void reverseString(char[] s) {
        int i = -1, j = s.length;
        while(++i < --j) {
            if (s[i] != s[j]) {
                char temp = s[i] ;
                s[i] = s[j] ;
                s[j] = temp ;
            }
        }
    }
}



class Solution {
    public void reverseString(char[] s) {
        int i = -1, j = s.length;
        while(++i < --j) {
            char temp = s[i] ;
            s[i] = s[j] ;
            s[j] = temp ;
        }
    }
}