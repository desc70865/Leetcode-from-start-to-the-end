/* 
Given a positive integer N, return the number of positive integers less than or equal to N that have at least 1 repeated digit.

 

Example 1:

Input: 20
Output: 1
Explanation: The only positive number (<= 20) with at least 1 repeated digit is 11.
Example 2:

Input: 100
Output: 10
Explanation: The positive numbers (<= 100) with atleast 1 repeated digit are 11, 22, 33, 44, 55, 66, 77, 88, 99, and 100.
Example 3:

Input: 1000
Output: 262
 

Note:

1 <= N <= 10^9
 */

class Solution {
    static int[] factorial;

    static {
        factorial = new int[10];
        factorial[0] = factorial[1] = 1;
        for (int i = 2; i < 10; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }

    public int numDupDigitsAtMostN(int N) {
        return N - dp(N);
    }

    public int dp(int n) {
        List<Integer> digits = new ArrayList<>();
        while (n > 0) {
            digits.add(n % 10);
            n /= 10;
        }
        int size = digits.size();

        int[] used = new int[10];
        int total = 0;

        for (int i = 1; i < size; i++) {
            total += 9 * arrange(9, i - 1);
        }

        for (int i = size - 1; i >= 0; i--) {
            int num = digits.get(i);
            for (int j = i == size - 1 ? 1 : 0; j < num; j++) {
                if (used[j] != 0) continue;
                total += arrange(10 - (size - i), i);
            }
            if (used[num]++ > 0) break;
            if (i == 0) total++;
        }
        return total;
    }

    public int arrange(int m, int n) {
        return factorial[m] / factorial[m - n];
    }
}