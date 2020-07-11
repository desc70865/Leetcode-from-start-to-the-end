/* 
Given an array A of 0s and 1s, we may change up to K values from 0 to 1.

Return the length of the longest (contiguous) subarray that contains only 1s. 

 

Example 1:

Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation: 
[1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
Example 2:

Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
Output: 10
Explanation: 
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 

Note:

1 <= A.length <= 20000
0 <= K <= A.length
A[i] is 0 or 1 
 */

class Solution {
    public int longestOnes(int[] A, int K) {
        int len = A.length+1;
        int[] zeros = new int[1+len/2], ones = new int[len/2];
        int lenZero = 0, lenOne = 0, indexZero = 0, indexOne = 0;
        if (A[0] == 0) {
            zeros[indexZero++] = 0;
        }
        for (int num: A) {
            if (num == 0) {
                if (lenOne != 0) {
                    ones[indexOne++] = lenOne;
                    lenOne = 0;
                }
                lenZero++;
            } else {
                if (lenZero != 0) {
                    zeros[indexZero++] = lenZero;
                    lenZero = 0;
                }
                lenOne++;
            }
        }
        if (lenOne != 0) {
            ones[indexOne] = lenOne;
        } else {
            zeros[indexZero] = lenZero;
        }
        
        for (int num: zeros) {
            System.out.print(num + " ");
        }
        System.out.println();
        for (int num: ones) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        int sumZero = K, curLen = 0, pre = 0, max = 0;
        for (int i = 0; i < lenZero; i++) {
            int res = sumZero - zeros[i];
            if (res >= 0) {
                curLen += zeros[i] + ones[i] + res;
            } else {
                max = Math.max(max, curLen);
                while (sumZero - zeros[i] < 0) {
                    curLen -= zeros[pre] + ones[pre];
                    sumZero += zeros[pre++];
                }
            }
        }
        max = Math.max(max, curLen);
        
        return max;
    }
}

// ERROR

class Solution {
    public int longestOnes(int[] A, int K) {
        int  n = A.length, i = 0, j = 0;
        
        for (; j < n; j++) {
            K -= 1 - A[j];
            if (K < 0) {
                K += 1 - A[i++];
            }
        }
        
        return j - i;
    }
}



class Solution {
    public int longestOnes(int[] A, int K) {
        int N = A.length, i = 0, j = 0;
        
        while (j < N) {
            if (A[j++] == 0) {
                K--;
            }
            // K >= 0 的限制维持了最大滑窗
            if (K < 0 && A[i++] == 0) {
                K++;
            }
        }
        
        return j - i;
    }
}



class Solution {
    public int longestOnes(int[] A, int K) {
        int  i = 0, res = 0;
        
        while (j < A.length) {
            if ((K -= 1 - A[j++]) < 0) {
                K += 1 - A[i++];
            }
        }
        
        return j - i;
    }
}



class Solution {
    public int longestOnes(int[] A, int K) {
        int  i = 0, j = 0;
        
        while (j < A.length) {
            if ((K -= 1 ^ A[j++]) < 0) {
                K += 1 ^ A[i++];
            }
        }
        
        return j - i;
    }
}



class Solution {
    public int longestOnes(int[] A, int K) {
        int  i = 0, j = 0;
        
        while (j < A.length) {
            if ((K -= 1 ^ A[j++]) < 0) {
                K += 1 ^ A[i++];
            }
        }
        
        return j - i;
    }
}