/* 
Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.
 */

class Solution {
    public String largestNumber(int[] nums) {
        int N = nums.length;
        String[] str = new String[N];
        for (int i = 0; i < N; i++) {
            str[i] = String.valueOf(nums[i]);
        }
        
        // Arrays.sort(str, Collections.reverseOrder());
        Arrays.sort(str, (a, b) -> (b+a).compareTo(a+b));
        
        if (str[0].charAt(0) == '0') {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < N; j++) {
            sb.append(str[j]);
        }
        return sb.toString();
    }
}

// 1 == 2

class Solution {
    private class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
           return order2.compareTo(order1);
        }
    }

    public String largestNumber(int[] nums) {
        // Get input integers as strings.
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }

        // Sort strings according to custom comparator.
        Arrays.sort(asStrs, new LargerNumberComparator());

        // If, after being sorted, the largest number is `0`, the entire number
        // is zero.
        if (asStrs[0].equals("0")) {
            return "0";
        }

        // Build largest number from sorted array.
        StringBuilder sb = new StringBuilder();
        for (String numAsStr : asStrs) {
            sb.append(numAsStr);
        }
        return sb.toString();
    }
}



class Solution {
    public String largestNumber(int[] nums) {
        StringBuilder sb = new StringBuilder();

        for (String s :
            Arrays.stream(nums)
                    .boxed()
                    .map(Object::toString)
                    .sorted((o1, o2) -> (o2 + o1).compareTo(o1 + o2))
                    .collect(Collectors.toList())) {
            sb.append(s);
        }

        String result = sb.toString();

        return result.startsWith("0") ? "0" : result;
    }
}



class Solution {
    private boolean cmp(int a, int b) {
        long A = a * 10l, B = b * 10l;
        int a_ = a, b_ = b;
        while ((a_ /= 10) > 0) {
            B *= 10;
        }
        while ((b_ /= 10) > 0) {
            A *= 10;
        }
        return (A + b) > (B + a);
    }

    private void sort(int[] nums, int l, int r) {
        int LEN = r - l;
        if (LEN < 2) {
            return;
        }
        int m = l + (LEN) / 2;
        sort(nums, l, m);
        sort(nums, m, r);
        int[] aux = new int[LEN];
        for (int i = l, j = m, k = 0; k < LEN; k++) {
            if (i < m && (j == r || cmp(nums[i],nums[j]))) {
                aux[k] = nums[i++];
            } else { // if (j < r) 
                aux[k] = nums[j++];
            }
        }
        for (int i = 0; l < r;) {
            nums[l++] = aux[i++];
        }
    }
    
    public String largestNumber(int[] nums) {
        sort(nums, 0, nums.length);
        if (nums[0] == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            res.append(nums[i]);
        }
        return res.toString();
    }
}

// @WTF

class Solution {
    public String largestNumber(int[] nums) {
        int n = sentence.length();
        
        Trie root = new Trie();
        for (int num: nums) {
            root.insert(String.valueOf(num));
        }
        for (int i = 1; i <= n; ++i) {
            dp[i] = dp[i - 1] + 1;
            
            Trie curPos = root;
            for (int j = i; j >= 1; --j) {
                int t = sentence.charAt(j - 1) - '0';
                if (curPos.next[t] == null) {
                    break;
                } else if (curPos.next[t].isEnd) {
                    dp[i] = Math.min(dp[i], dp[j - 1]);
                }
                if (dp[i] == 0) {
                    break;
                }
                curPos = curPos.next[t];
            }
        }
        return dp[n];
    }
}

class Trie {
    public Trie[] next;
    public boolean isEnd;
    
    public Trie() {
        next = new Trie[10];
        isEnd = false;
    }

    public void insert(String s) {
        Trie curPos = this;
        
        for (int i = 0; i < s.length(); i++) {
            int t = s.charAt(i) - '0';
            if (curPos.next[t] == null) {
                curPos.next[t] = new Trie();
            }
            curPos = curPos.next[t];
        }
        curPos.isEnd = true;
    }
}



