/* 
Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.

If there isn't any rectangle, return 0.

 

Example 1:

Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:

Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2
 

Note:

1 <= points.length <= 500
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.
 */

class Solution {
    public int minAreaRect(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        Map<Integer, Node> col = new HashMap<>();
        Map<Integer, Node> row = new HashMap<>();
        int N = points.length;

        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            int x = points[i][0];
            int y = points[i][1];
            Node cur = new Node(x, y);
            nodes[i] = cur;
            if (col.containsKey(x)) col.get(x).nextc = cur;
            col.put(x, cur);
            if (row.containsKey(y)) row.get(y).nextr = cur;
            row.put(y, cur);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            Node cur = nodes[i];
            int area = Integer.MAX_VALUE;
            for (Node b = cur.nextc; b != null; b = b.nextc) {
                Node c = cur.nextr;
                Node d = b.nextr;
                while (c != null && d != null) {
                    if (c.x == d.x) {
                        area = (b.y - cur.y) * (c.x - cur.x);
                        min = Math.min(min, area);
                        break;
                    } else if (c.x < d.x) {
                        c = c.nextr;
                    } else /* d.x < c.x */ {
                        d = d.nextr;
                    }
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}

class Node {
    int x;
    int y;
    Node nextc;
    Node nextr;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}