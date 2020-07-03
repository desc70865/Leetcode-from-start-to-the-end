/* 
Given an integer n, return 1 - n in lexicographical order.

For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
 */

class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<Integer> tree = new Stack<Integer>();
        if (n < 10) {
            for (int i = n; i > 0; i--) {
                tree.push(i);
            }
        } else {
            for (int i = 9; i > 0; i--) {
                tree.push(i);
            }
        }
        int t, m;
        while (! tree.empty()) {
            t = tree.peek();
            tree.pop();
            res.add(t);
            if (t * 10 > n) {
                continue;
            } else {
                m = n - t * 10;
                if (m > 9) {
                    m = 9;
                }
            }
            for (int i = m; i >= 0; i--) {
                tree.push(t * 10 + i);
            }
        }
        return res;
    }
}



class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        dfs(n, 0, res);
        return res;
    }

    public void dfs(int n, int tmp, List<Integer> res) {
        for (int i = 0; i <= 9; i++) {
            if (tmp > n) {
                return;
            }
            if (tmp == 0) {
                tmp++;
                continue;
            }
            res.add(tmp);
            dfs(n, tmp++ * 10, res);
        }
    }
}