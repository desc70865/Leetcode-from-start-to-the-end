/* 
Given a number N, return true if and only if it is a confusing number, which satisfies the following condition:

We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid. A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.

 

Example 1:



Input: 6
Output: true
Explanation: 
We get 9 after rotating 6, 9 is a valid number and 9!=6.
Example 2:



Input: 89
Output: true
Explanation: 
We get 68 after rotating 89, 86 is a valid number and 86!=89.
Example 3:



Input: 11
Output: false
Explanation: 
We get 11 after rotating 11, 11 is a valid number but the value remains the same, thus 11 is not a confusing number.
Example 4:



Input: 25
Output: false
Explanation: 
We get an invalid number after rotating 25.
 

Note:

0 <= N <= 10^9
After the rotation we can ignore leading zeros, for example if after rotation we have 0008 then this number is considered as just 8.
 */

class Solution {
    char[] rotate = new char[] {'0', '1', '@', '@', '@', '@', '9', '@', '8', '6'};

    public boolean confusingNumber(int N) {
        StringBuilder sb = new StringBuilder();
        for (char c: String.valueOf(N).toCharArray()) {
            if (rotate[c - '0'] == '@') return false;
            sb.append(rotate[c - '0']);
        }
        return Integer.parseInt(sb.reverse().toString()) != N;
    }
}



class Solution {
    char[] rotate = {'0', '1', '#', '#', '#', '#', '9', '#', '8', '6'};

    public boolean confusingNumber(int N) {
        char[] chs = String.valueOf(N).toCharArray();
        boolean ans = false;
        for (int l = 0, r = chs.length - 1; l <= r; l++, r--) {
            if (rotate[chs[l] - '0'] == '#' || rotate[chs[r] - '0'] == '#') return false;
            ans |= chs[l] != rotate[chs[r] - '0'];
        }
        return ans;
    }
}



class Solution {
    int[] rotate = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};

    public boolean confusingNumber(int N) {
        long confusing = 0;
        int intput = N;
        while (N != 0) {
            int p = rotate[N % 10];
            if (p < 0) return false;
            confusing *= 10;
            confusing += p;
            N /= 10;
        }
        return confusing != intput;
    }
}