/* 
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2

Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
 */

class Solution {
    public static int mySqrt(int x) {
        if (x == 1)
            return 1;
        double _max = x; //此处一定为浮点数，不要用整数
        double _min = 0.0;
        double p = 1e-5; //此处为精度，当满足该精度时，返回该近似值
        double  mid = (_max + _min) / 2.0;
        while(Math.abs(mid * mid - x) > p)//此处是浮点数之差的绝对值与精度进行比较
        {
            if(mid * mid < x)
                _min = mid;
            else if(mid * mid > x)
                _max = mid;
            else
                return (int)mid;
            mid = (_max + _min) / 2.0;
        }
        return (int)mid;
    }
}

class Solution {
    public static int mySqrt(int x) {
        double n = x/2 + 1; // 设置初值
        double p = 0.9; // 设置精度
        while(Math.abs(n*n - x) > p)
            n = (n + x / n) / 2.0;
        return (int)n;
    }
}

// Hessian Matrix
// 平方根倒数算法精确设定初始值

class Solution {
    public static int mySqrt(int x) {
        long i = Double.doubleToLongBits((double)x);
        i = 0x5fe6ec85e7de30daL - (i >> 1);
        double n = 1/Double.longBitsToDouble(i)+1;
        while(Math.abs(n*n - x) > 0.1)
            n = (n + x / n) / 2.0;
        return (int)n;
    }
}

// test

public class Solution {
    public static int mySqrt(int x) {
        long i = Double.doubleToLongBits((double)x);
        i = 0x5fe6ec85e7de30daL - (i >> 1);
        double n = 1/Double.longBitsToDouble(i)+1;
        System.out.println(n);
        while(Math.abs(n*n - x) >= 1) {
            n = (n + x / n) / 2.0;
        	System.out.println(n);
        }
        return (int)n;
    }
	
    public static void main(String[] args) {
    	System.out.println(mySqrt(375920568));
    }
}