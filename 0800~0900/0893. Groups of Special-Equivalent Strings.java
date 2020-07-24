/* 
You are given an array A of strings.

A move onto S consists of swapping any two even indexed characters of S, or any two odd indexed characters of S.

Two strings S and T are special-equivalent if after any number of moves onto S, S == T.

For example, S = "zzxy" and T = "xyzz" are special-equivalent because we may make the moves "zzxy" -> "xzzy" -> "xyzz" that swap S[0] and S[2], then S[1] and S[3].

Now, a group of special-equivalent strings from A is a non-empty subset of A such that:

Every pair of strings in the group are special equivalent, and;
The group is the largest size possible (ie., there isn't a string S not in the group such that S is special equivalent to every string in the group)
Return the number of groups of special-equivalent strings from A.

 
Example 1:

Input: ["abcd","cdab","cbad","xyzz","zzxy","zzyx"]
Output: 3
Explanation: 
One group is ["abcd", "cdab", "cbad"], since they are all pairwise special equivalent, and none of the other strings are all pairwise special equivalent to these.

The other two groups are ["xyzz", "zzxy"] and ["zzyx"].  Note that in particular, "zzxy" is not special equivalent to "zzyx".
Example 2:

Input: ["abc","acb","bac","bca","cab","cba"]
Output: 3
 

Note:

1 <= A.length <= 1000
1 <= A[i].length <= 20
All A[i] have the same length.
All A[i] consist of only lowercase letters.
 */

class Solution {
    public int numSpecialEquivGroups(String[] A) {
        
    }
}

// 去重: HashSet
// 特征: 奇偶统计/排序

class Solution {
    public int numSpecialEquivGroups(String[] A) {
        Set<String> set = new HashSet<>();
        for (String a: A) {
            
            int[] odd = new int[26];
            int[] even = new int[26];
            
            for (int i = 0; i < a.length(); i++) {
                if ((i & 1) == 0) even[a.charAt(i) - 'a']++;
                else odd[a.charAt(i) - 'a']++;
            }
            set.add(Arrays.toString(odd) + Arrays.toString(even));
        }
        return set.size();
    }
}



class Solution {
    public int numSpecialEquivGroups(String[] A) {
        HashSet<String> set = new HashSet<String>();
        int answer = 0;
        for (String s: A) {
            if (set.add(getSignature(s.toCharArray()))) answer++;
        }
        return answer;
    }
    
    public String getSignature(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            for (int j = i+2; j < chars.length; j += 2) {
                if (chars[i] > chars[j]) {
                    swap(chars, i, j);
                }
            }
        }
        return String.valueOf(chars);
    }
    
    public void swap(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }
}



class Solution {
    private HashSet<String> set;
    
    public int numSpecialEquivGroups(String[] A) {
        set = new HashSet<String>();
        for (String s: A) count(s.toCharArray());
        return set.size();
    }
    
    private boolean count(char[] s) {
        int LEN = s.length, parity = LEN % 2, even = LEN - 1, odd = LEN - 1;
        if (parity == 0) even--;
        else odd--;
        paritySort(s, 0, even);
        paritySort(s, 1, odd);
        return set.add(String.valueOf(s));
    }
    
    private void paritySort(char[] s, int l, int r) {
        if (l >= r) return;
        int p = partition(s, l, r);
        paritySort(s, l, p-2);
        paritySort(s, p+2, r);
    }
    
    private int partition(char[] arr, int l, int r) {
        char temp = arr[l];
        while (l < r) {
            while (l < r) {
                if (arr[r] < temp) {
                    arr[l] = arr[r];
                    break;
                }
                r -= 2;
            }
            while (l < r) {
                if (arr[l] > temp) {
                    arr[r] = arr[l];
                    break;
                }
                l += 2;
            }
        }
        arr[l] = temp;
        return l;
    }
}