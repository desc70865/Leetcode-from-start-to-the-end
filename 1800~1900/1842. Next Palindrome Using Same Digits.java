/* 
You are given a numeric string num, representing a very large palindrome.

Return the smallest palindrome larger than num that can be created by rearranging its digits. If no such palindrome exists, return an empty string "".

A palindrome is a number that reads the same backward as forward.

 

Example 1:

Input: num = "1221"
Output: "2112"
Explanation: The next palindrome larger than "1221" is "2112".
Example 2:

Input: num = "32123"
Output: ""
Explanation: No palindromes larger than "32123" can be made by rearranging the digits.
Example 3:

Input: num = "45544554"
Output: "54455445"
Explanation: The next palindrome larger than "45544554" is "54455445".
 

Constraints:

1 <= num.length <= 105
num is a palindrome.
 */

class Solution {
    public String nextPalindrome(String num) {
        int n = num.length() / 2;
        char[] nums = num.substring(0, n).toCharArray();
        if (! nextPermutation(nums)) {
            return "";
        }
        String head = new String(nums);
        reverse(nums, 0, n - 1);
        String tail = new String(nums);
        return head + (n * 2 == num.length() ? "" : num.charAt(n)) + tail;
    }

    public boolean nextPermutation(char[] nums) {
        int n = nums.length;
        int i = n - 1;
        for (; i > 0 && nums[i - 1] >= nums[i]; --i);
        if (i == 0) {
            // reverse(nums, i, n - 1);
            return false;
        }
        int j = n - 1;
        for (; j >= 0 && nums[i - 1] >= nums[j]; --j);
        swap(nums, i - 1, j);
        reverse(nums, i, n - 1);
        return true;
    }

    private void reverse(char[] nums, int l, int r) {
        for (; l < r; swap(nums, l++, r--));
    }

    private void swap(char[] nums, int i, int j) {
        char tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}