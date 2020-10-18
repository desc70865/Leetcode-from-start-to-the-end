/* 
All DNA is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T', for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

 

Example 1:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]
Example 2:

Input: s = "AAAAAAAAAAAAA"
Output: ["AAAAAAAAAA"]
 

Constraints:

0 <= s.length <= 105
s[i] is 'A', 'C', 'G', or 'T'.
 */

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        Set<String> set = new HashSet<>();
        Set<String> res = new HashSet<>();
        for (int i = 0; i < N - 9; i++) {
            String tmp = new String(Arrays.copyOfRange(str, i, i + 10));
            if (! set.add(tmp)) res.add(tmp);
        }
        return new ArrayList<>(res);
    }
}



class Solution {
    int mask;
    int cur;
    public List<String> findRepeatedDnaSequences(String s) {
        int N = s.length();
        Set<String> res = new HashSet<>();
        if (N < 11) return new ArrayList<>(res);
        
        cur = 0;
        int sum = 1 << 20;
        mask = sum - 1;
        
        char[] str = s.toCharArray();
        boolean[] v = new boolean[sum];
        Set<Integer> set = new HashSet<>();
        
        for (int i = 0; i < 9; i++) {
            next(str[i]);
        }
        for (int i = 9; i < N; i++) {
            next(str[i]);
            if (v[cur]) res.add(new String(Arrays.copyOfRange(str, i - 9, i + 1)));
            else v[cur] = true;
        }
        
        return new ArrayList<>(res);
    }
    
    private void next(char c) {
        cur <<= 2;
        cur &= mask;
        switch (c) {
            case 'A': break;
            case 'C': cur += 1; break;
            case 'G': cur += 2; break;
            case 'T': cur += 3; break;
        }
    }
}