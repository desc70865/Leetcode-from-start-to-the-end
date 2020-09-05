/* 
Given a non-empty array of unique positive integers A, consider the following graph:

There are A.length nodes, labelled A[0] to A[A.length - 1];
There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
Return the size of the largest connected component in the graph.

 

Example 1:

Input: [4,6,15,35]
Output: 4

Example 2:

Input: [20,50,9,63]
Output: 2

Example 3:

Input: [2,3,6,7,4,12,21,39]
Output: 8

Note:

1 <= A.length <= 20000
1 <= A[i] <= 100000
 */

class Solution {
    static final int[] primeFactor = new int[100001];
    static {
        for (int i = 3; i < primeFactor.length; i += 2) {
            if (primeFactor[i] == 0) primeFactor[i] = i;
            else continue;
            if (i >= 1000) continue;
            for (int j = i * i; j < primeFactor.length; j += i) {
                primeFactor[j] = i;
            }
        }
    }
    
    int maxCount = 0;
    int[] leaders;
    int[] counts;
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    
    public int largestComponentSize(int[] A) {
        maxCount = 1;
        int len = A.length;
        leaders = new int[len];
        counts = new int[len];
        for (int i = 0; i < len; i++) {
            leaders[i] = i;
            counts[i] = 1;
        }
        
        for (int i = 0; i < len; i++) {
            int cur = A[i];
            int commonDivisor;
            while (cur > 1) {
                if (cur % 2 == 0) {
                    commonDivisor = 2;
                    processCommonDivisor(commonDivisor, i);
                    while (cur % 2 == 0) cur >>= 1;
                }
                while (cur > 1) {
                    commonDivisor = primeFactor[cur];
                    processCommonDivisor(commonDivisor, i);
                    while (cur % commonDivisor == 0) cur /= commonDivisor;
                }
            }
        }
        return maxCount;
    }
    
    private void processCommonDivisor(int commonDivisor, int i) {
        List<Integer> list = map.get(commonDivisor);
        if (list == null) {
            list = new ArrayList<>();
            map.put(commonDivisor, list);
        }
        if (list.size() > 0) {
            MCDLeader(list.get(0), i);
        }
        
        list.add(i);
    }
    
    private void MCDLeader(int pre, int i) {
        int leaderPre = GCDLeader(leaders, pre);
        int leaderI = GCDLeader(leaders, i);
        if (leaderPre == leaderI) {
            return;
        }
        leaders[leaderI] = leaderPre;
        counts[leaderPre] += counts[leaderI];
        maxCount = Math.max(maxCount, counts[leaderPre]);
    }
    
    private int GCDLeader(int[] leaders, int i) {
        int leader = leaders[i];
        if (leaders[leader] == leader) {
            return leader;
        } else {
            return leaders[i] = GCDLeader(leaders, leader);
        }
    }
}