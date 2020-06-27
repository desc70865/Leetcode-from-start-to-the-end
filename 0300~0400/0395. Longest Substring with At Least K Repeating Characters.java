/* 
Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */

class Solution {
    private int res = 0, k;
    private String s;
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() < k) {
            return 0;
        }
        if (k <= 1) {
            return s.length();
        }
        
        this.k = k;
        this.s = s;
        
        helper(0, s.length());
        
        return res;
    }
    
    public void helper(int low, int high) {
        int[] count = new int[26];
        for (int i = low; i < high; i++) {
            count[s.charAt(i) - 'a']++;
        }
        
        boolean flag = true;
        for (int x : count) {
            if ((0 - x & x - k) < 0) { // x ∈ (0, k)
                flag = false;
                break;
            }
        }
        if (flag) {
            res = high - low;
            return;
        }
        int start = low;
        for (int end = low; end < high; end++) {
            if (count[s.charAt(end) - 'a'] < k) {
            	if (end - start > res) {
            		helper(start, end);
            	}
                start = end + 1;
            }
        }
        if (high - start > res) {
            helper(start, high);
        }
    }
}



class Solution {
    public int longestSubstring(String s, int k) {  
        if (s == null || s.length() < k) 
            return 0;
        if (k <= 1) 
            return s.length();
        return longestSubstring(s, k, 0, s.length()-1);
    }
    
    public int longestSubstring(String s, int k, int low, int high) {
        if (low > high)
            return 0;
        int[] count = new int[26];
        for (int i = low; i <= high; i++)
            count[s.charAt(i) - 'a']++;
        boolean flag = true;
        for (int i = 0; i < 26; i++) {
            if ((-count[i] & count[i] - k) < 0) { // 0 < count[i] && count[i] < k
                flag = false;
                break;
            }
        }
        if (flag)
            return high - low + 1;
        int start = low;
        int res = 0;
        for (int i = low; i <= high; i++) {
            if (count[s.charAt(i) - 'a'] < k) {
                res = Math.max(longestSubstring(s, k, start, i-1), res);
                start = i+1;
            }
        }
        res = Math.max(longestSubstring(s, k, start, high), res);
        return res;
    }
}



class Solution {
    public int longestSubstring(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] arr = s.toCharArray();
        for (char c : arr) map.put(c, map.getOrDefault(c, 0) + 1);;
        List<Integer> split = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.get(arr[i]) < k) split.add(i);
        }
        if (split.size() == 0) return s.length();
        int ans = 0, left = 0;
        split.add(s.length());
        for (int i = 0; i < split.size(); i++) {
            int right = split.get(i);
            if (right - left > ans) ans = Math.max(ans, longestSubstring(s.substring(left, right), k));
            left = split.get(i) + 1;
        }
        return ans;
    }
}



class Solution {
	private static int res;
    public static int longestSubstring(String s, int k) {
        if (s == null || s.length() < k) {
            return 0;
        }
        if (k <= 1) {
            return s.length();
        }
        
        helper(s, k, 0, s.length());
        
        return res;
    }
    
    public static void helper(String s, int k, int low, int high) {
        int[] count = new int[26];
        for (int i = low; i < high; i++) {
            count[s.charAt(i) - 'a']++;
        }
        
        boolean flag = true;
        for (int x : count) {
            if ((0 - x & x - k) < 0) { // x ∈ (0, k)
                flag = false;
                break;
            }
        }
        if (flag) {
            res = high - low;
            System.out.println(res);
            return;
        }
        int start = low, end;
        for (end = low; end < high; end++) {
            if (count[s.charAt(end) - 'a'] < k) {
            	if (end - start > res) {
            		helper(s, k, start, end);
            	}
                start = end + 1;
            }
        }
        if (high - start > res) {
        	helper(s, k, start, high);
        }
    }
}