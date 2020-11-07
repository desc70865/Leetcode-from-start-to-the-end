/* 
You are given an integer num. You will apply the following steps exactly two times:

Pick a digit x (0 <= x <= 9).
Pick another digit y (0 <= y <= 9). The digit y can be equal to x.
Replace all the occurrences of x in the decimal representation of num by y.
The new integer cannot have any leading zeros, also the new integer cannot be 0.
Let a and b be the results of applying the operations to num the first and second times, respectively.

Return the max difference between a and b.

 

Example 1:

Input: num = 555
Output: 888
Explanation: The first time pick x = 5 and y = 9 and store the new integer in a.
The second time pick x = 5 and y = 1 and store the new integer in b.
We have now a = 999 and b = 111 and max difference = 888
Example 2:

Input: num = 9
Output: 8
Explanation: The first time pick x = 9 and y = 9 and store the new integer in a.
The second time pick x = 9 and y = 1 and store the new integer in b.
We have now a = 9 and b = 1 and max difference = 8
Example 3:

Input: num = 123456
Output: 820000
Example 4:

Input: num = 10000
Output: 80000
Example 5:

Input: num = 9288
Output: 8700
 

Constraints:

1 <= num <= 10^8
 */

class Solution {
    public int maxDiff(int num) {
        String s1 = String.valueOf(num);
        String s2 = s1;
        int len = s1.length();
        int i = 0, j = 0;
        while (i < len && s1.charAt(i) == '9') i++;
        if (i < len) s1 = s1.replace(s1.charAt(i), '9');
        if (s2.charAt(0) == '1') {
            while (j < len && (s2.charAt(j) == '1' || s2.charAt(j) == '0')) j++;
            if (j < len) s2 = s2.replace(s2.charAt(j), '0');
        } else s2 = s2.replace(s2.charAt(j), '1');
        return Integer.parseInt(s1) - Integer.parseInt(s2);
    }
}