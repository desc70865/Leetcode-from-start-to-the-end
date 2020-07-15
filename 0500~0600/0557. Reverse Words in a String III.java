/* 
Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.
 */

class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String[] str = s.split(" ");
        int LEN = str.length;
        if (LEN == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LEN - 1; i++) {
            sb.append(reverse(str[i]));
            sb.append(" ");
        }
        sb.append(reverse(str[LEN-1]));
        return sb.toString();
    }

    private String reverse(String s) {
        char[] str = s.toCharArray();
        StringBuffer sb = new StringBuffer();
        int LEN = str.length;
        for (int i = LEN - 1; i >= 0; i--) {
            sb.append(str[i]);
        }
        return sb.toString();
    }
}



class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] str = s.toCharArray();
        int LEN = str.length, i = 0, j = 0;
        while (i < LEN) {
            while (++i < LEN && str[i] != ' ') ;
            swap(str, i-1, j);
            j = ++i;
        }
        return new String(str);
    }

    private void swap(char[] nums, int i, int j) {
        do {
            char temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        } while (--i > ++j);
    }
}