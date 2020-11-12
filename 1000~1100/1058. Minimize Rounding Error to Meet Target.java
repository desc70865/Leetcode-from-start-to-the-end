/* 
Given an array of prices [p1,p2...,pn] and a target, round each price pi to Roundi(pi) so that the rounded array [Round1(p1),Round2(p2)...,Roundn(pn)] sums to the given target. Each operation Roundi(pi) could be either Floor(pi) or Ceil(pi).

Return the string "-1" if the rounded array is impossible to sum to target. Otherwise, return the smallest rounding error, which is defined as Σ |Roundi(pi) - (pi)| for i from 1 to n, as a string with three places after the decimal.

 

Example 1:

Input: prices = ["0.700","2.800","4.900"], target = 8
Output: "1.000"
Explanation:
Use Floor, Ceil and Ceil operations to get (0.7 - 0) + (3 - 2.8) + (5 - 4.9) = 0.7 + 0.2 + 0.1 = 1.0 .
Example 2:

Input: prices = ["1.500","2.500","3.500"], target = 10
Output: "-1"
Explanation: It is impossible to meet the target.
Example 3:

Input: prices = ["1.500","2.500","3.500"], target = 9
Output: "1.500"
 

Constraints:

1 <= prices.length <= 500
Each string prices[i] represents a real number in the range [0.0, 1000.0] and has exactly 3 decimal places.
0 <= target <= 106
 */

class Solution {
    public String minimizeError(String[] prices, int target) {
        int min = 0;
        int max = 0;
        int len = prices.length;
        int[] rem = new int[len];
        int idx = 0;
        for (String price: prices) {
            int k = price.length() - 4;
            int integer = Integer.valueOf(price.substring(0, k));
            int decimal = Integer.valueOf(price.substring(k + 1, k + 4));
            min += integer;
            max += decimal == 0 ? 0 : 1;
            rem[idx++] = decimal;
        }
        if (target < min || target > min + max) return "-1";
        Arrays.sort(rem);
        int k = min + len - target;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (i < k) sum += rem[i];
            else sum += 1000 - rem[i];
        }
        return String.format("%.3f", (double) sum / 1000);
    }
}



import java.math.*;
class Solution {
    public String minimizeError(String[] prices, int target) {
        int min = 0;
        int max = 0;
        List<BigDecimal> list = new ArrayList<>();
        for (String price: prices) {
            min += Integer.parseInt(price.substring(0, price.length() - 4));
            if (! price.endsWith(".000")) {
                max++;
                list.add(new BigDecimal(price.substring(price.length() - 4, price.length())));
            }
        }
        Collections.sort(list);
        BigDecimal sum = BigDecimal.ZERO;
        if (target < min || target > min + max) return "-1";
        int k = min + max - target;
        for (int i = 0; i < max; i++) {
            if (i < k) {
                sum = sum.add(list.get(i));
            } else {
                sum = sum.add(BigDecimal.ONE).subtract(list.get(i));
            }
        }
        return sum.setScale(3, RoundingMode.HALF_UP).toString();
    }
}