/* 
You are given a binary string binary consisting of only 0's or 1's. You can apply each of the following operations any number of times:

Operation 1: If the number contains the substring "00", you can replace it with "10".
For example, "00010" -> "10010"
Operation 2: If the number contains the substring "10", you can replace it with "01".
For example, "00010" -> "00001"
Return the maximum binary string you can obtain after any number of operations. Binary string x is greater than binary string y if x's decimal representation is greater than y's decimal representation.

 

Example 1:

Input: binary = "000110"
Output: "111011"
Explanation: A valid transformation sequence can be:
"000110" -> "000101" 
"000101" -> "100101" 
"100101" -> "110101" 
"110101" -> "110011" 
"110011" -> "111011"
Example 2:

Input: binary = "01"
Output: "01"
Explanation: "01" cannot be transformed any further.
 

Constraints:

1 <= binary.length <= 105
binary consist of '0' and '1'.
 */

/*
 * 作者：keylol
 * 链接：https://leetcode-cn.com/problems/maximum-binary-string-after-change/solution/shuang-bai-zhu-pei-ma-by-keylol-j7zi/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class Solution {
    public String maximumBinaryString(String binary) {
        int p = 0;
        int len = binary.length();
        char[] chb = binary.toCharArray();
        char[] chs = new char[len];
        Arrays.fill(chs, '1');
        for (int i = 0; i < len; i++) {
            if (chb[i] == '1') continue;
            while (i < len) {
                p += chb[i++] - '0';
            }
            chs[len - p - 1] = '0';
            break;
        }
        return new String(chs);
    }
}