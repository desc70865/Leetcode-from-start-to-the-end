/* 
Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */

class Solution {
    static int SIZE = 1500000;
    static int[] countPrime = new int[SIZE + 1];
    
    static {
        for (int i = 2; i * i <= SIZE; i++) {
            for (int j = i * i; j <= SIZE; j += i) {
                countPrime[j] = -1;
            }
        }

        for (int i = 2; i <= SIZE; i++) {
            countPrime[i] += countPrime[i - 1] + 1;
        }
    }

    public int countPrimes(int n) {
        return n == 0 ? 0 : countPrime[n - 1];
    }
}