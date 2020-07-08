/* 
Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Note:

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        
    }
}



class Solution {
    private final int MAGIC = 3251;
    public int[] intersect(int[] nums1, int[] nums2) {
        int j = 0;
        int freq[] = new int[MAGIC];
        for (int i = 0; i < nums1.length; i++) {
            freq[Math.abs(nums1[i] % MAGIC)]++;
        }
        
        int res[] = new int[nums1.length];
        for (int i = 0; i < nums2.length; i++) {
            if (freq[Math.abs(nums2[i] % MAGIC)]-- > 0) {
               res[j++] = nums2[i];
            }
        }
        return Arrays.copyOf(res, j);
    }
}



class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        int[] map = new int[1000];
        HashMap<Integer, Integer> mapL = new HashMap<>();
        for (int num : nums1) {
            if (num >= 0 && num < 1000) {
                map[num]++;
            } else {
                if (!mapL.containsKey(num)) {
                    mapL.put(num, 1);
                } else {
                    mapL.replace(num, mapL.get(num) + 1);
                }
            }
        }
        
        int i = 0;
        for (int num : nums2) {
            if (num >= 0 && num < 1000) {
                if (map[num] > 0) {
                    res[i++] = num;
                    map[num]--;
                }
            } else {
                if (mapL.containsKey(num)) {
                    int v = mapL.get(num);
                    if (v > 0) {
                        res[i++] = num;
                        mapL.put(num, v - 1);
                    }
                }
            }
        }
        return Arrays.copyOfRange(res, 0, i);
    }
}