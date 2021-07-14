/* 
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

Buildings Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x,y1], [y, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */

class Solution {
    static final int INF = Integer.MAX_VALUE;

    static class Node {
        int left = 0, right = 0, height = 0;
        Node next = null;
        
        public Node(int _left, int _right, int _height) {
            this.left = _left;
            this.right = _right;
            this.height = _height;
        }
        
        public Node(int _left, int _right, int _height, Node _next) {
            this.left = _left;
            this.right = _right;
            this.height = _height;
            this.next = _next;
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        if (buildings.length == 0) {
            return result;
        }
        Node dummy = new Node(-1, INF, 0, new Node(INF, INF, 0));
        Node node = dummy, cache = null, next = null, prev = node;
        int left = 0, right = 0, height = 0;
        for (int[] building: buildings) {
            left = building[0];
            right = building[1];
            height = building[2];
            while (node.right < left) {
                prev = node;
                node = node.next;
            }
            cache = node;
            while (right > left) {
                next = cache;
                if (height > cache.height) {
                    if (right < cache.right) {
                        next = new Node(left, right, height, new Node(right, cache.right, cache.height));
                        next.next.next = cache.next;
                        cache.next = next;
                    } else {
                        next = new Node(left, cache.right, height, cache.next);
                        cache.next = next;
                    }
                    if (left == cache.left) {
                        prev.next = next;
                    } else {
                        cache.right = left;
                    }
                    prev = next;
                } else {
                    prev = cache;
                }
                if (right > next.right) {
                    left = next.right;
                    cache = next.next;
                } else {
                    left = right;
                }
            }
        }
        node = dummy.next;
        height = -1;
        right = 0;
        while (node != null) {
            if (node.height != height && node.left < node.right) {
                result.add(Arrays.asList(node.left, node.height));
                height = node.height;
                right = node.right;
            }
            node = node.next;
        }
        if (height > 0) {
            result.add(Arrays.asList(right, 0));
        }
        return result;
    }
}