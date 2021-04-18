/* 
Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3, t = 0
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1, t = 2
Output: true
Example 3:

Input: nums = [1,5,9,1,5,9], k = 2, t = 3
Output: false
 */

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            Long ceiling = set.ceiling((long) nums[i] - t);
            if (ceiling != null && ceiling <= (long) nums[i] + t) return true;
            set.add((long) nums[i]);
            if (i >= k) {
                // if (j -> [i - k, i]): nums[i - k] == nums[j]
                // such j doesn't exist
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}



class Solution {
    static final long BASE = 1L << 31;

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        Map<Long, Long> bucket = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long id = getId(nums[i], t + 1);
            if (bucket.containsKey(id)) return true;
            if (bucket.containsKey(id - 1) && Math.abs(nums[i] - bucket.get(id - 1)) <= t) return true;
            if (bucket.containsKey(id + 1) && Math.abs(nums[i] - bucket.get(id + 1)) <= t) return true;
            bucket.put(id, (long) nums[i]);
            if (i >= k) bucket.remove(getId(nums[i - k], t + 1));
        }
        return false;
    }

    private Long getId(int n, int size) {
        return (BASE + n) / size;
    }
}

// 下面是一种错误的解法

class Solution {
    static final long BASE = 1L << 31;
    static final int MOD = 1 << 16;

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length > 100) {
            return containsNearbyAlmostDuplicateHelper(nums, k, t);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + k && j < nums.length; j++) {
                if (Math.abs((long) nums[i] - nums[j]) <= t) return true;
            }
        }
        return false;
    }

    private boolean containsNearbyAlmostDuplicateHelper(int[] nums, int k, int t) {
        int len = nums.length;
        long[] arr = new long[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (BASE + nums[i] << 16) + i;
        }
        Arrays.sort(arr);
        for (int i = 1; i < len; i++) {
            if (near(arr[i - 1], arr[i], k, t)) {
                return true;
            }
        }
        return false;
    }

    private boolean near(long a, long b, int k, int t) {
        return Math.abs((a >> 16) - (b >> 16)) <= t && Math.abs(a % MOD - b % MOD) <= k;
    }
}