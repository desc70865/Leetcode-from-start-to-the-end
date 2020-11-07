/* 
We are given a list of (axis-aligned) rectangles.  Each rectangle[i] = [x1, y1, x2, y2] , where (x1, y1) are the coordinates of the bottom-left corner, and (x2, y2) are the coordinates of the top-right corner of the ith rectangle.

Find the total area covered by all rectangles in the plane.  Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
Output: 6
Explanation: As illustrated in the picture.
Example 2:

Input: [[0,0,1000000000,1000000000]]
Output: 49
Explanation: The answer is 10^18 modulo (10^9 + 7), which is (10^9)^2 = (-7)^2 = 49.
Note:

1 <= rectangles.length <= 200
rectanges[i].length = 4
0 <= rectangles[i][j] <= 10^9
The total area covered by all rectangles will never exceed 2^63 - 1 and thus will fit in a 64-bit signed integer.
 */

class Solution {
    public int rectangleArea(int[][] rectangles) {
        int OPEN = 1, CLOSE = -1;
        int[][] events = new int[rectangles.length * 2][];
        Set<Integer> Xvals = new HashSet();
        int t = 0;
        for (int[] rec: rectangles) {
            events[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
            events[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
            Xvals.add(rec[0]);
            Xvals.add(rec[2]);
        }

        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        Integer[] X = Xvals.toArray(new Integer[0]);
        Arrays.sort(X);
        Map<Integer, Integer> Xi = new HashMap();
        for (int i = 0; i < X.length; ++i)
            Xi.put(X[i], i);

        Node active = new Node(0, X.length - 1, X);
        long ans = 0;
        long cur_x_sum = 0;
        int cur_y = events[0][0];

        for (int[] event: events) {
            int y = event[0], typ = event[1], x1 = event[2], x2 = event[3];
            ans += cur_x_sum * (y - cur_y);
            cur_x_sum = active.update(Xi.get(x1), Xi.get(x2), typ);
            cur_y = y;
        }

        ans %= 1_000_000_007;
        return (int) ans;
    }
}

class Node {
    int start, end;
    Integer[] X;
    Node left, right;
    int count;
    long total;

    public Node(int start, int end, Integer[] X) {
        this.start = start;
        this.end = end;
        this.X = X;
        left = null;
        right = null;
        count = 0;
        total = 0;
    }

    public int getRangeMid() {
        return start + (end - start) / 2;
    }

    public Node getLeft() {
        if (left == null) left = new Node(start, getRangeMid(), X);
        return left;
    }

    public Node getRight() {
        if (right == null) right = new Node(getRangeMid(), end, X);
        return right;
    }

    public long update(int i, int j, int val) {
        if (i >= j) return 0;
        if (start == i && end == j) {
            count += val;
        } else {
            getLeft().update(i, Math.min(getRangeMid(), j), val);
            getRight().update(Math.max(getRangeMid(), i), j, val);
        }

        if (count > 0) total = X[end] - X[start];
        else total = getLeft().total + getRight().total;

        return total;
    }
}



class Solution {
    public int rectangleArea(int[][] rectangles) {
        List<int[]> list = new ArrayList<>();
        for (int[] rectangle: rectangles) {
            addRectangle(list, rectangle, 0);
        }
        long res = 0;
        for (int[] rec: list) {
            res += calcArea(rec);
        }
        return (int) (res % 1_000_000_007);
    }

    private void addRectangle(List<int[]> list, int[] cur, int start) {
        if (start >= list.size()) {
            list.add(cur);
            return;
        }

        int[] rec = list.get(start);
        if (cur[2] <= rec[0] || cur[3] <= rec[1] || cur[0] >= rec[2] || cur[1] >= rec[3]) {
            addRectangle(list, cur, start + 1);
            return;
        }

        if (cur[0] < rec[0]) {
            addRectangle(list, new int[]{cur[0], cur[1], rec[0], cur[3]}, start + 1);
        }
        if (cur[2] > rec[2]) {
            addRectangle(list, new int[]{rec[2], cur[1], cur[2], cur[3]}, start + 1);
        }
        if (cur[1] < rec[1]) {
            addRectangle(list, new int[]{Math.max(rec[0], cur[0]), cur[1], Math.min(rec[2], cur[2]), rec[1]}, start + 1);
        }
        if (cur[3] > rec[3]) {
            addRectangle(list, new int[]{Math.max(rec[0], cur[0]), rec[3], Math.min(rec[2], cur[2]), cur[3]}, start + 1);
        }
    }

    private long calcArea(int[] rec) {
        return ((long) rec[2] - rec[0]) * (rec[3] - rec[1]);
    }
}