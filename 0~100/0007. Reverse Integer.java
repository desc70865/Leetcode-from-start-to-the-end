/* 
Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321

Example 2:

Input: -123
Output: -321

Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
 
 class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10; // 在 java 中负数取余仍是负数: -17 % 10 = -7
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}

// pop & push
// 溢出检测

/* 
const char* convert(char buf[], int value)
{
  static char digits[19] =
    { '9', '8', '7', '6', '5', '4', '3', '2', '1',
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
  static const char* zero = digits + 9;  // zero 指向 '0'
  // works for -2147483648 .. 2147483647
  int i = value;
  char* p = buf;
  do {
    int lsd = i % 10;  // lsd 可能小于 0
    i /= 10;           // 是向下取整还是向零取整？
    *p++ = zero[lsd];  // 下标可能为负
  } while (i != 0);
  if (value < 0) {
    *p++ = '-';
  }
  *p = '/0';
  std::reverse(buf, p);
  return p; // p - buf 即为整数长度
}
 */
 
 // 32-bit 整数 => 数组