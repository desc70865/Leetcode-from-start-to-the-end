/* 
We are given hours, a list of the number of hours worked per day for a given employee.

A day is considered to be a tiring day if and only if the number of hours worked is (strictly) greater than 8.

A well-performing interval is an interval of days for which the number of tiring days is strictly larger than the number of non-tiring days.

Return the length of the longest well-performing interval.

 

Example 1:

Input: hours = [9,9,6,0,6,6,9]
Output: 3
Explanation: The longest well-performing interval is [9,9,6].
 

Constraints:

1 <= hours.length <= 10000
0 <= hours[i] <= 16
 */

class Solution {
    public int longestWPI(int[] hours) {
        int N = hours.length;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] += hours[i] / 9 + sum[i];
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int k = sum[j + 1] - sum[i];
                int len = j + 1 - i;
                if (k > len - k) max = Math.max(max, len);
            }
        }
        return max;
    }
}



class Solution {
    public int longestWPI(int[] hours) {
        int N = hours.length;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] += sum[i] + (hours[i] > 8 ? 1 : -1);
        }
        // System.out.println(Arrays.toString(sum));
        Deque<Integer> p = new LinkedList<>();
        p.push(0);
        for (int i = 1; i <= N; i++) {
            if (sum[i] < sum[p.peek()]) p.push(i);
        }
        int max = 0;
        int idx = N;
        while (! p.isEmpty() && idx > 0) {
            if (sum[idx] <= sum[p.peek()]) idx--;
            else max = Math.max(idx - p.pop(), max);
        }
        return max;
    }
}



class Solution {
    public int longestWPI(int[] hours) {
        int N = hours.length;
        int cur = 0;
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            if (hours[i] > 8) cur++;
            else cur--;
            if (cur > 0) max = i + 1;
            else {
                map.putIfAbsent(cur, i);
                max = Math.max(max, i - map.getOrDefault(cur - 1, i));
            }
        }
        return max;
    }
}



class Solution {
    public int longestWPI(int[] hours) {
        int N = hours.length;
        int[] sum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
        }
        int[] stack = new int[N + 1];
        int idx = 1;
        for (int i = 1; i <= N; i++) {
            if (sum[i] < sum[stack[idx - 1]]) stack[idx++] = i;
        }
        int max = 0;
        for (int i = N; i >= 0; i--) {
            while (idx > 0 && sum[i] > sum[stack[idx - 1]]) {
                max = Math.max(max, i - stack[--idx]);
            }
        }
        return max;
    }
}