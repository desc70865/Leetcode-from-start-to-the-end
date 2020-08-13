/* 
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"

Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */

class Solution {
	public String multiply(String num1, String num2) {
		if (num1.equals("0") || num2.equals("0"))
			return "0";
		int n1 = num1.length();
		int n2 = num2.length();
		int[] products = new int[n1 + n2];
		for (int i = n1 - 1; i >= 0; i--) {
			for (int j = n2 - 1; j >= 0; j--) {
				products[i + j + 1] += ((int) num1.charAt(i) - '0') * ((int) num2.charAt(j) - '0');
			}
		}
		int carry = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = n1 + n2 - 1; i >= 0; i--) {
			int tmp = products[i] + carry;
			sb.append(tmp % 10);
			carry = tmp / 10;
		}
		sb.reverse();
		if (sb.charAt(0) == '0')
			sb.deleteCharAt(0);
		return sb.toString();
	}
}

// 大数乘法

class Solution {
	public String multiply(String num1, String num2) {
		int m = num1.length(), n = num2.length();
		int[] pos = new int[m + n];
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				int mul = (num1.charAt(i) - '0')
						* (num2.charAt(j) - '0');
				int p1 = i + j, p2 = i + j + 1;
				int sum = mul + pos[p2];
				pos[p1] += sum / 10;
				pos[p2] = (sum) % 10;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int p : pos)
			if (!(sb.length() == 0 && p == 0))
				sb.append(p);
		return sb.length() == 0 ? "0" : sb.toString();
	}
}

// 垂直遍历加速
// 从后往前,累加 num1[j] * num2[k] 并进位
// j 从 min(m,i) 开始循环
// 显然 j + k = i; 同时满足 k ∈ [0,n]

class Solution {
	public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0") || num1.length() == 0 || num2.length() == 0) // equals(), ==
            return "0";
		int m = num1.length() - 1, n = num2.length() - 1;
        int length = m + n, carry = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = length; i >= 0; i--) {
            int j = (i > m ? m : i); // k = i - j
            int sum = 0;
			while (i - j <= n && j >= 0) {
                sum += (num1.charAt(j) - '0')
					* (num2.charAt(i - j) - '0'); // num1[j] * num2[k]
                j--;
            }
            sb.append((sum + carry) % 10);
            carry = (sum + carry) / 10;
		}
		if (carry != 0)
            sb.append(carry);
		return sb.reverse().toString();
	}
}



class Solution {
    public String multiply(String num1, String num2) {
        return multiply(num1.toCharArray(), num2.toCharArray());
    }

    public String multiply(char[] a, char[] b) {
        if (a[0] == '0' || b[0] == '0') return "0";
        int carry = 0, m = a.length-1, n = b.length-1;
        StringBuilder sb = new StringBuilder();
        for (int i = m + n; i >= 0; i--) {
            int j = Math.min(i, m), sum = carry;
            while (i - j <= n && j >= 0) {
                sum += (a[j] - '0') * (b[i - j--] - '0');
            }
            sb.append(sum % 10);
            carry = sum / 10;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }
}