/* 
Find the smallest prime palindrome greater than or equal to N.

Recall that a number is prime if it's only divisors are 1 and itself, and it is greater than 1. 

For example, 2,3,5,7,11 and 13 are primes.

Recall that a number is a palindrome if it reads the same from left to right as it does from right to left. 

For example, 12321 is a palindrome.

 

Example 1:

Input: 6
Output: 7
Example 2:

Input: 8
Output: 11
Example 3:

Input: 13
Output: 101
 

Note:

1 <= N <= 10^8
The answer is guaranteed to exist and be less than 2 * 10^8.
 */

class Solution {
    public int primePalindrome(int N) {
        
    }
}



class Solution {
    public int primePalindrome(int N) {
        if (N <= 2) return 2;
        if (N > 7 && N <= 11) return 11;
        // ABCDE
        int digitsCount = (int) Math.floor(Math.log10(N)) + 1;
        if (digitsCount % 2 == 0) N = (int)Math.pow(10, digitsCount++);
        int n = N, count = 0, len = digitsCount / 2 + 1;
        int[] digits = new int[len];
        // ABC
        while (n > 0) {
            if (++count > len) digits[digitsCount - count] = n % 10;
            n /= 10;
        }
        
        while (true) {
            // ABCBA
            int number = 0;
            for (int i = 0; i < digitsCount; i++) {
                int index = i < digits.length ? i : digitsCount - i - 1;
                number = number * 10 + digits[index];
            }
            // if ABCBA >= ABCDE && ~
            if (number >= N && isPrime(number)) return number;
            
            // ABC++ -> increment
            for (int i = digits.length - 1; i >= 0; i--) {
                if (i == 0) {
                    if (digits[0] == 9) {
                        digitsCount += 2;
                        digits = new int[len + 1];
                        digits[0] = 1;
                    } else digits[0] += digits[0] % 2 + 1;
                } else if (digits[i] == 9) {
                    digits[i] = 0;
                } else {
                    digits[i]++;
                    break;
                }
            }
        }
    }
    
    private boolean isPrime(int n) {
        if (n == 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}