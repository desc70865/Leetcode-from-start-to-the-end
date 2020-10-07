/* 
Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:

Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Example 2:

Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
 */

public class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = digits.length - 1; i >= 0; --i) {
            if (digits[i] < 9) {
                ++digits[i];
                return digits;
            }
            digits[i] = 0;
        }
        int[] res = new int[n + 1];
        res[0] = 1;
        return res;
    }
}

// ...

public class Solution {
    public int[] plusOne(int[] digits) {
        if (digits.length == 0) return digits;
        int carry = 1, n = digits.length;
        for (int i = digits.length - 1; i >= 0; --i) {
            if (carry == 0) return digits;
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
        }
        int[] res = new int[n + 1];
        res[0] = 1;
        return carry == 0 ? digits : res;
    }
}



class Solution {
    public int[] plusOne(int[] digits) {
        List<Integer> list = new ArrayList<>();
        int c = 1;
        int sum = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            sum = c + digits[i];
            list.add(sum % 10);
            c = sum / 10;
        }
        if (c > 0) list.add(c);
        Collections.reverse(list);
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
}