/* 
On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.

Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).

Examples:
Input: N = 1, K = 1
Output: 0

Input: N = 2, K = 1
Output: 0

Input: N = 2, K = 2
Output: 1

Input: N = 4, K = 5
Output: 1

Explanation:
row 1: 0
row 2: 01
row 3: 0110
row 4: 01101001
Note:

N will be an integer in the range [1, 30].
K will be an integer in the range [1, 2^(N-1)].
 */

class Solution {
    public int kthGrammar(int N, int K) {
        if (N <= 2) {
            return K - 1;
        }
        return K > (1 << (N - 2)) ? 1 - kthGrammar(N - 1, K - (1 << (N - 2))) : kthGrammar(N - 1, K);
    }
}

// forEach

class Solution {
    public int kthGrammar(int N, int K) {
        return bitCount(--K) % 2;
    }
    
    private int bitCount(int K) {
        int cnt = 0;
        while (K > 0) {
            cnt += K & 1;
            K >>= 1;
        }
        return cnt;
    }
}

// forEach

class Solution {
    public int kthGrammar(int N, int K) {
        return bitCount(--K) % 2;
    }
    
    private int bitCount(int K) {
        int cnt = 0;
        while (K > 0) {
            K &= K - 1;
            cnt++;
        }
        return cnt;
    }
}

// Hamming Weight

class Solution {
    public int kthGrammar(int N, int K) {
        return bitCount(--K) % 2;
    }
    
    private int bitCount(int K) {
        K = (K & 0x55555555) + ((K >>> 1) & 0x55555555);
        K = (K & 0x33333333) + ((K >>> 2) & 0x33333333);
        K = (K & 0x0F0F0F0F) + ((K >>> 4) & 0x0F0F0F0F);
        K = (K & 0x00FF00FF) + ((K >>> 8) & 0x00FF00FF);
        K = (K & 0x0000FFFF) + ((K >>> 16) & 0x0000FFFF);
        return K;
    }
}

// optimization

class Solution {
    public int kthGrammar(int N, int K) {
        return bitCount(--K) % 2;
    }
    
    private int bitCount(int K) {
        K = K - ((K >>> 1) & 0x55555555);
        K = (K & 0x33333333) + ((K >>> 2) & 0x33333333);
        K = (K + (K >>> 4)) & 0x0F0F0F0F;
        K = K + (K >>> 8);
        K = K + (K >>> 16);
        return K & 0x3F;
    }
}

// multiplication

class Solution {
    public int kthGrammar(int N, int K) {
        return bitCount(--K) % 2;
    }
    
    private int bitCount(int K) {
        K = K - ((K >>> 1) & 0x55555555);
        K = (K & 0x33333333) + ((K >>> 2) & 0x33333333);
        K = (K + (K >>> 4)) & 0x0F0F0F0F;
        return (K * 0x01010101) >>> 24;
    }
}

// 

class Solution {
    public int kthGrammar(int N, int K) {
        return bitCount(--K) % 2;
    }
    
    private int bitCount(int K) {
        K = K - (((K >>> 1) & 0xdb6db6db) + ((K >>> 2) & 0x49249249));
        K = (K + (K >>> 3)) & 0xc71c71c7;
        return K < 0 ? ((K >>> 30) + ((K << 2) >>> 2) % 63) : K % 63;
    }
}

// 0xdb6db6db = 11011011011011011011011011011011
// 0x49249249 = 01001001001001001001001001001001
// 0xc71c71c7 = 11000111000111000111000111000111

