/* 
Given an integer n, return a list of all simplified fractions between 0 and 1 (exclusive) such that the denominator is less-than-or-equal-to n. The fractions can be in any order.

 

Example 1:

Input: n = 2
Output: ["1/2"]
Explanation: "1/2" is the only unique fraction with a denominator less-than-or-equal-to 2.
Example 2:

Input: n = 3
Output: ["1/2","1/3","2/3"]
Example 3:

Input: n = 4
Output: ["1/2","1/3","1/4","2/3","3/4"]
Explanation: "2/4" is not a simplified fraction because it can be simplified to "1/2".
Example 4:

Input: n = 1
Output: []
 

Constraints:

1 <= n <= 100
 */

class Solution {
    public List<String> simplifiedFractions(int n) {
        
    }
}



import java.util.AbstractList;
import java.util.List;

class Solution extends AbstractList<String> {
    int[] as;
	int[] bs;
	int size = 0;
	
	@Override
	public String get(int index) {
		return as[index] + "/" + bs[index];
	}

	@Override
	public int size() {
		return size;
	}

	public List<String> simplifiedFractions(int n) {
        // 首项+末项的和乘项数除以2
        int len = n * (n-1) >>> 1;
		this.as = new int[len];
		this.bs = new int[len];
        // a1 = 0, a2 = 1, b1 = 1, b2 = 1
		walk(n, 0, 1, 1, 1);
        // 直接返回这个继承了抽象链表的Solution类
		return this;
	}

    //把分子和分母放进as和bs
    //用walk方法决定size
	void walk(int max, int a1, int b1, int a2, int b2) {
        //a1+a2变成下一个分子
        //？？？
        //1，2，3，5，8，13，21
		int am = a1 + a2;
        //b1+b2变成下一个分母
        //？？？
        //2，3，5，8，13，21，34
		int bm = b1 + b2;
		if (bm <= max) {
            //递归递归递归递归递归递归递归递归递归递归
            //把a2,b2换成了am,bm
            //进去之后新的am=a1+am，新的bm=b1+bm
            //b1+bm比max小的时候吧bm就一直加
            //分母不变，分子递归
            //a1=0,b1=1
            //第一轮，分子a不变，分母递增1
			walk(max, a1, b1, am, bm);
            //am放进as，bm放进bs
            //size用作放进去的下标，也是总的分数的个数
			as[size] = am;
			bs[size] = bm;
			size ++;
            //把a1,b1换成了am,bm
            //进去之后新的am=am+a2，新的bm=bm+b2
            //bm+b2比max小的时候吧bm就一直加
            //分母变了，递归进if的第一个walk
            //a2=1,b2=1
			walk(max, am, bm, a2, b2);
		}
	}
}