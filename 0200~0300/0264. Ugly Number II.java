/* 
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

Example:

Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
Note:  

1 is typically treated as an ugly number.
n does not exceed 1690.
 */

class Solution {
    public int nthUglyNumber(int n) {
        
    }
}



class Solution {
    public int nthUglyNumber(int n) {
        int i = 1;
        while (n > 0) if (check(i++)) n--;
        return i-1;
    }

    private boolean check(int n) {
        while (n % 2 == 0) n /= 2;
        while (n % 3 == 0) n /= 3;
        while (n % 5 == 0) n /= 5;
        return n == 1;
    }
}



class Initial {
    private static int LEN = 1690;
    public int[] num;
    
    public Initial() {
        num = new int[LEN];
        num[0] = 1;
        int a = 0, b = 0, c = 0;
        for (int i = 1; i < LEN; i++) {
            num[i] = min(num[a] * 2, num[b] * 3, num[c] * 5);
            if (num[i] == num[a] * 2) a++;
            if (num[i] == num[b] * 3) b++;
            if (num[i] == num[c] * 5) c++;
        }
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}

class Solution {
    // static 加速???
    public static Initial set = new Initial();
    public int nthUglyNumber(int n) {
        return set.num[n-1];
    }
}