/* 
Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */

class Solution {
    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new LinkedList<>();
        for (char digit: num.toCharArray()) {
            while (deque.size() > 0 && k > 0 && deque.peekLast() > digit) {
                deque.removeLast();
                k--;
            }
            deque.offerLast(digit);
        }
        for (int i = 0; i < k; ++i) {
            deque.removeLast();
        }
        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        for (char digit: deque) {
            if (leadingZero && digit == '0') continue;
            leadingZero = false;
            ret.append(digit);
        }
        if (ret.length() == 0) return "0";
        return ret.toString();
    }
}



class Solution {
    public String removeKdigits(String num, int k) {
        int len = num.length();
        if (len == k) return "0";
        char[] str = num.toCharArray();
        int left = 0;
        int right = 1;
        
        // if '0' exist in [0, k + 1], remove un-'0' element(s)
        for (; right < len && k > 0; right++) {
            if (right - left > k) break;
            if (str[right] == '0') {
                k -= right - left;
                left = right + 1;
                while (left < len && str[left] == '0') left++;
                right = left ;
            }
        }
        
        if (left == len || right - left == 1 && right >= len) return "0";
        if (k == 0) {
            return new String(Arrays.copyOfRange(str, left, len));
        }

        char[] stack = new char[len];
        int end = 0;
        stack[end++] = str[left++];
        for (; left < len && k > 0; left++) {
            while (k > 0 && end > 0 && stack[end - 1] > str[left]) {
                k--;
                end--;
            }
            stack[end++] = str[left];
        }
        end -= k;
        while (left < len) {
            stack[end++] = str[left++];
        }
        return new String(Arrays.copyOfRange(stack, 0, end));
    }
}