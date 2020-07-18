/* 
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */

class Solution {
    List<Set<Integer>> list;
    private int[] re;
    public int maxPoints(int[][] points) {
        int LEN = points.length;
        if (LEN < 3) {
            return LEN;
        }
        int max = 0;

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < LEN; i++) {
            String str = points[i][0] + "#" + points[i][1];
            if (map.containsKey(str)) {
                map.compute(str, (key, oldVal) -> oldVal + 1);
                continue;
            }
            map.put(str, 1);
        }
        re = new int[LEN];
        for (int i = 0; i < LEN; i++) {
            String str = points[i][0] + "#" + points[i][1];
            re[i] = map.get(str);
            max = Math.max(re[i], max);
        }
        if (max < LEN) {
            max++;
        }

        list = new ArrayList<>();
        for (int i = 0; i < LEN; i++) {
            list.add(new HashSet<Integer>());
        }
        for (int i = 0; i < LEN; i++) {
            int add = re[i] - 1;
            int a = points[i][0], b = points[i][1];
            for (int j = i+1; j < LEN-1; j++) {
                if (check(i, j)) {
                    continue;
                }
                int sub = re[j] - 1;
                int c = points[j][0], d = points[j][1];
                max = Math.max(count(a, b, c, d, j+1, points) + add + sub, max);
            }
        }
        return max;
    }
    
    private int count(long x1, long y1, long x2, long y2, int start, int[][] points) {
        int res = 2;
        for (int i = start; i < points.length; i++) {
            if (re[i] > 1) {
                continue;
            }
            long x3 = points[i][0], y3 = points[i][1];
            if ((x2 - x1) * (y3 - y2) == (x3 - x2) * (y2 - y1)) {
                // System.out.println(x1 + " " + x2 + " " + x3);
                // System.out.println(y1 + " " + y2 + " " + y3);
                // System.out.println();
                res++;
            }
        }
        return res;
    }
    
    private boolean check(int i, int j) {
        for (Integer l : list.get(i)) {
            if (list.get(j).contains(l)) {
                return true;
            }
        }
        return false;
    }
}



class Solution {
    public static int maxPoints(int[][] points) {
        int n = points.length;
        if (n < 3) {
            return n;
        }

        int max = 2;
        for (int i = 0; i < n - 2; i++) {
            long x1 = points[i][0], y1 = points[i][1];
            int base = 1;
            for (int j = i + 1; j < n; j++) {
                int sameLine = 0;
                long x2 = points[j][0], y2 = points[j][1];
                if (x2 == x1 && y2 == y1) {
                    base++; // 记忆,仅回溯时; p3的计算中包含此
                } else {
                    sameLine = 1;
                    for (int k = j + 1; k < n; k++) {
                        int x3 = points[k][0], y3 = points[k][1];
                        if ((y2 - y1) * (x3 - x2) == (y3 - y2) * (x2 - x1)) {
                            sameLine++;
                        }
                    }
                }
                max = Math.max(max, base + sameLine);
            }
        }
        return max;
    }
}