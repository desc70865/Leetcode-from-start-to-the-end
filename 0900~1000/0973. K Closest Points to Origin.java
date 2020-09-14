/* 
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)

 

Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation: 
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)
 

Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
 */

class Solution {
    public int[][] kClosest(int[][] points, int K) {
        List<int[]> list = new ArrayList<>();
        for (int[] p: points) list.add(p);
        Collections.sort(list, (a, b) -> dis(a[0], a[1]) - dis(b[0], b[1]));
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) res[i] = list.get(i);
        return res;
    }

    private int dis(int x, int y) {
        return x * x + y * y;
    }
}



class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int n = points.length;
        int l = 0, r = n-1;
        while (true) {
            if (l >= r) break;
            
            int mid = partition(points, l, r);            
            if (mid < K) l = mid + 1;
            else if (mid  > K) r = mid - 1;
            else break;
        }
        return Arrays.copyOfRange(points, 0, K);
    }
    
    private int partition(int[][] arr, int l, int r) {
        int lp = l;
        int rp = r + 1;
        
        int pivot =  distance(arr[l]);
        
        while (true) {
            while (distance(arr[++lp]) < pivot) if (lp == r) break;
            while (distance(arr[--rp]) > pivot) if (rp == l) break;
            if (lp >= rp) break;
            swap(arr,lp, rp);
        }
        
        swap(arr, rp, l);
        
        return rp;
    }
    
    private int distance(int[] p) {
        return dis(p[0], p[1]);
    }

    private int dis(int x, int y) {
        return x * x + y * y;
    }
    
    private void swap(int[][] arr, int l, int r) {
        int[] tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }
}