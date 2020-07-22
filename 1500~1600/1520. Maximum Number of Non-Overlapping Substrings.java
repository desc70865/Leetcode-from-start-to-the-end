/* 
Given a string s of lowercase letters, you need to find the maximum number of non-empty substrings of s that meet the following conditions:

The substrings do not overlap, that is for any two substrings s[i..j] and s[k..l], either j < k or i > l is true.
A substring that contains a certain character c must also contain all occurrences of c.
Find the maximum number of substrings that meet the above conditions. If there are multiple solutions with the same number of substrings, return the one with minimum total length. It can be shown that there exists a unique solution of minimum total length.

Notice that you can return the substrings in any order.

 

Example 1:

Input: s = "adefaddaccc"
Output: ["e","f","ccc"]
Explanation: The following are all the possible substrings that meet the conditions:
[
  "adefaddaccc"
  "adefadda",
  "ef",
  "e",
  "f",
  "ccc",
]
If we choose the first string, we cannot choose anything else and we'd get only 1. If we choose "adefadda", we are left with "ccc" which is the only one that doesn't overlap, thus obtaining 2 substrings. Notice also, that it's not optimal to choose "ef" since it can be split into two. Therefore, the optimal way is to choose ["e","f","ccc"] which gives us 3 substrings. No other solution of the same number of substrings exist.
Example 2:

Input: s = "abbaccd"
Output: ["d","bb","cc"]
Explanation: Notice that while the set of substrings ["d","abba","cc"] also has length 3, it's considered incorrect since it has larger total length.
 

Constraints:

1 <= s.length <= 10^5
s contains only lowercase English letters.
 */

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        char[] t = s.toCharArray();
        
    }
}



class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        List<String> res = new LinkedList<>();
        char[] str = s.toCharArray();
        int[][] index = new int[26][3];
        for (int[] l: index)
            Arrays.fill(l, -1);
        
        // initialize
        for (int i = 0; i < str.length; i++) {
            int j = str[i] - 'a';
            if (-1 == index[j][0]) index[j][0] = i;
            index[j][2] = i;
        }
        
        
        // correctify -> remove intersection
        for (int i = 0; i < 26; i++) {
            if (-1 == index[i][0]) continue;
            index[i][1] = index[i][2];
            for (int j = index[i][0]+1; j < index[i][1]; j++) {
                int k = str[j] - 'a';
                if (index[k][0] < index[i][0]) {
                    index[i][1] = -1;
                    break;
                }
                index[i][1] = Math.max(index[i][1], index[k][2]);
            }
        }
        
        // simple order
        Arrays.sort(index, (a, b) -> (Math.abs(a[0]) * sig(a[1]) - Math.abs(b[0]) * sig(b[1])));
        
        // for (int[] arr: index)
        //     System.out.println(Arrays.toString(arr));
        
        // while does not contains behind
        for (int i = 0; i < 26; i++) {
            if (index[i][1] < 0 || i < 25 && index[i][1] > index[i+1][0]) continue;
            res.add(s.substring(index[i][0], index[i][1]+1));
        }
        
        return res;
    }
    
    private int sig(int x) {
        return x < 0 ? -1 : 1;
    }
}



class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        char[] cs = s.toCharArray();
        int[] lo = new int[26], hi = new int[26], trueHi = new int[26];
        Arrays.fill(lo, -1);
        for (int i = 0; i < cs.length; i++) {
            int idx = cs[i] - 'a';
            if (lo[idx] == -1) lo[idx] = i;
            hi[idx] = i;
        }
        for (int i = 0; i < 26; i++) {
            if (lo[i] == -1) continue;
            trueHi[i] = hi[i];
            for (int d = lo[i]+1; d < trueHi[i]; d++) {
                if (lo[cs[d]-'a'] < lo[i]) {
                    trueHi[i] = -1;
                    break;
                }
                trueHi[i] = Math.max(trueHi[i], hi[cs[d]-'a']);
            }
        }
        List<int[]> list = new ArrayList(26);
        for (int i = 0; i < 26; i++) {
            if (lo[i] != -1 && trueHi[i] != -1) list.add(new int[] {lo[i], trueHi[i]});
        }
        Collections.sort(list, (a, b) -> (a[0] - b[0]));
        List<String> ret = new LinkedList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)[1] < 0 
            || i < list.size()-1 && list.get(i)[1] > list.get(i+1)[0]) continue;
            ret.add(s.substring(list.get(i)[0],list.get(i)[1] + 1));
        }
        return ret;
    }
}