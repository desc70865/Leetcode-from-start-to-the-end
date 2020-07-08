/* 
Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Note:

Each element in the result must be unique.
The result can be in any order.
 */

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer, Boolean> map = new HashMap<>();
        
        for (int num : nums1) {
            if (! map.containsKey(num)) {
                map.put(num, false);
            }
        }
        for (int num : nums2) {
            if (map.containsKey(num)) {
                map.put(num, true);
            }
        }
        
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {
                list.add(entry.getKey());
            }
        }
        
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
}



class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        
        for (int num : nums1) {
            set.add(num);
        }
        for (int num : nums2) {
            res.add(num);
        }
        
        res.retainAll(set);
        int[] ans = new int[res.size()];
        int index = 0;
        for (int num : res) {
            ans[index++] = num;
        }
        return ans;
    }
}



class Solution {
    public int[] intersection(int[] a, int[] b) {
        boolean[] t = new boolean[1000];
        for (int v : a) {
            t[v] = true;
        }
        
        int i = 0;
        for (int v : b) {
            if (t[v]) {
                a[i++] = v;
                t[v] = false;
            }
        }
        return Arrays.copyOfRange(a, 0, i);
    }
}



class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        int j = 0;
        int freq[] = new int[1000];
        for (int i = 0; i < nums1.length; i++) {
            freq[nums1[i]]++;
        }
        
        int res[] = new int[nums1.length];
        for (int i = 0; i < nums2.length; i++) {
            if (freq[nums2[i]] > 0) {
               res[j++] = nums2[i];
                freq[nums2[i]] = 0;
            }
        }
        return Arrays.copyOf(res, j);
    }
}