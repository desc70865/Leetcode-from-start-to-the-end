/* 
Given a number N, return a string consisting of "0"s and "1"s that represents its value in base -2 (negative two).

The returned string must have no leading zeroes, unless the string is "0".

 

Example 1:

Input: 2
Output: "110"
Explantion: (-2) ^ 2 + (-2) ^ 1 = 2
Example 2:

Input: 3
Output: "111"
Explantion: (-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3
Example 3:

Input: 4
Output: "100"
Explantion: (-2) ^ 2 = 4
 

Note:

0 <= N <= 10^9
 */

class Solution {
    public String baseNeg2(int N) {
        
    }
}



class Solution {
    public String baseNeg2(int N) {
        StringBuilder res = new StringBuilder();
        while (N != 0) {
            res.append(N & 1);
            N = -(N >> 1);
        }
        return res.length() > 0 ? res.reverse().toString() : "0";
    }
}



/* class Solution {
public:
    // 无论K是正数还是负数都支持（只支持-10～10进制，因为更高进制需要引入字母）
    vector<int> baseK(int N, int K) {
        if (N == 0) return {0};
        vector<int> res;
        while (N != 0) {
            int r = ((N % K) + abs(K)) % abs(K); // 此处为关键
            res.push_back(r);
            N -= r;
            N /= K;
        }
        reverse(res.begin(), res.end());
        return res;
    }
    string baseNeg2(int N) {
        vector<int> nums = baseK(N, -2);
        string res;
        for (auto x : nums) res += to_string(x);
        return res;
    }
}; */