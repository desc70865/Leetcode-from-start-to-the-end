/* 
Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.

Note:

You may assume the interval's end point is always bigger than its start point.
You may assume none of these intervals have the same start point.
 

Example 1:

Input: [ [1,2] ]

Output: [-1]

Explanation: There is only one interval in the collection, so it outputs -1.
 

Example 2:

Input: [ [3,4], [2,3], [1,2] ]

Output: [-1, 0, 1]

Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.
 

Example 3:

Input: [ [1,4], [2,3], [3,4] ]

Output: [-1, 2, -1]

Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */

class Solution {
    public int[] findRightInterval(int[][] intervals) {
        
    }
}



class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int len = intervals.length;
        int[] ans = new int[len];
        TreeSet<Node> set = new TreeSet<>((x, y) -> x.start - y.start);
        for (int i = 0; i < len; i++) set.add(new Node(intervals[i][0], i));
        for (int i = 0; i < len; i++) {
            Node temp = set.ceiling(new Node(intervals[i][1], -1));
            if (temp == null) ans[i] = -1;
            else ans[i] = temp.pos;
        }
        return ans;
    }
}

class Node {
    int start;
    int pos;
    public Node(int s,int p) {
        this.start = s;
        this.pos = p;
    }
}



class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int[] interval: intervals) {
            min = Math.min(min, interval[0]);
            max = Math.max(max, interval[1]);
        }
        
        int len = max - min + 1;
        int[] buckets = new int[len];
        Arrays.fill(buckets, -1);
        
        for (int i = 0; i < intervals.length; i++) {
            buckets[intervals[i][0] - min] = i;
        }
        
        for (int i = len - 2; i >= 0; i--) {
            if (buckets[i] == -1) {
                buckets[i] = buckets[i + 1];
            }
        }
        
        int[] res = new int[intervals.length];
        
        for (int i = 0; i < intervals.length; i++) {
            res[i] = buckets[intervals[i][1] - min];
        }
        return res;
    }
}