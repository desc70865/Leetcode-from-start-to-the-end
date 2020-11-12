/* 
Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.

If there is no common time slot that satisfies the requirements, return an empty array.

The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.  

It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.

 

Example 1:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]
Example 2:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []
 

Constraints:

1 <= slots1.length, slots2.length <= 10^4
slots1[i].length, slots2[i].length == 2
slots1[i][0] < slots1[i][1]
slots2[i][0] < slots2[i][1]
0 <= slots1[i][j], slots2[i][j] <= 10^9
1 <= duration <= 10^6 
 */

class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
        int len1 = slots1.length;
        int len2 = slots2.length;
        int i = 0;
        int j = 0;
        while (i < len1 && j < len2) {
            int start = Math.max(slots1[i][0], slots2[j][0]);
            int e1 = slots1[i][1];
            int e2 = slots2[j][1];
            if (e1 > e2) {
                if (e2 - start >= duration) {
                    return Arrays.asList(start, start + duration);
                }
                j++;
            } else {
                if (e1 - start >= duration) {
                    return Arrays.asList(start, start + duration);
                }
                i++;
            }
        }
        return Arrays.asList();
    }
}



class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        for (int[] s: slots1) {
            if (s[1] - s[0] >= duration) {
                pq.offer(s);
            }
        }
        for (int[] s: slots2) {
            if (s[1] - s[0] >= duration) {
                pq.offer(s);
            }
        }
        while (pq.size() > 1) {
            if (pq.poll()[1] >= pq.peek()[0] + duration) {
                return Arrays.asList(pq.peek()[0], pq.peek()[0] + duration);
            }
        }
        return new ArrayList<>();
    }
}