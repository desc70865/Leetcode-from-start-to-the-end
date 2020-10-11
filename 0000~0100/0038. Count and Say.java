/* 
The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence. You can do so recursively, in other words from the previous member read off the digits, counting the number of digits in groups of the same digit.

Note: Each term of the sequence of integers will be represented as a string.

 

Example 1:

Input: 1
Output: "1"
Explanation: This is the base case.

Example 2:

Input: 4
Output: "1211"
Explanation: For n = 3 the term was "21" in which we have two groups "2" and "1", "2" can be read as "12" which means frequency = 1 and value = 2, the same way "1" is read as "11", so the answer is the concatenation of "12" and "11" which is "1211".
 */

public class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        // 递归调用，然后对字符串处理
        String str = countAndSay(n-1) + "*"; // 简化判断
        char[] c = str.toCharArray();
        int count = 1;
        String s = "";
        for (int i = 0; i < c.length - 1;i++) {
        	if (c[i] == c[i+1]) {
        		count++; // 计数
        	} else {
        		s = s + count + c[i]; // *
        		count = 1; // 重置
        	}
        }
        return s;
    }
}

// https://www.cnblogs.com/TenosDoIt/p/3776356.html
// 读数结果只包含123
// 上面这个...
// 应该还有其他方法

public class Solution {
    public String countAndSay(int n) {
        //special case
        if (n <= 0) return "";
        if (n == 1) return "1";
        //base case
        StringBuilder s = new StringBuilder("1");
        //general case
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int j = 1; j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(j - 1)) {
                    count++;
                } else {
                    sb.append(count);
                    sb.append(s.charAt(j - 1));
                    count = 1;
                }
            }
            sb.append(count);
            sb.append(s.charAt(s.length() - 1));
            s = sb;
        }           
        return s.toString();
    }
}

// 感觉好多了,直接对字符串操作去掉数组转化
// 用循环代替递归

class Solution {
    public String countAndSay(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        while (n-- > 1) {
            sb = update(sb);
        }
        return sb.toString();
    }

    private StringBuilder update(StringBuilder sb) {
        char[] str = sb.toString().toCharArray();
        sb = new StringBuilder();
        int N = str.length;
        char c = str[0];
        int t = 0;
        for (int i = 0; i < N; i++) {
            if (str[i] == c) t++;
            else {
                sb.append(t).append(c);
                c = str[i];
                t = 1;
            }
        }
        sb.append(t).append(c);
        return sb;
    }
}