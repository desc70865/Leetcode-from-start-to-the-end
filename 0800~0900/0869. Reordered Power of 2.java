/* 
Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.

Return true if and only if we can do this in a way such that the resulting number is a power of 2.

 

Example 1:

Input: 1
Output: true
Example 2:

Input: 10
Output: false
Example 3:

Input: 16
Output: true
Example 4:

Input: 24
Output: false
Example 5:

Input: 46
Output: true
 

Note:

1 <= N <= 10^9
 */

class Solution {
    private Set<String> set;
    
    public boolean reorderedPowerOf2(int N) {
        initial();
        char[] arr = String.valueOf(N).toCharArray();
        Arrays.sort(arr);
        // System.out.println(String.valueOf(arr));
        return set.contains(String.valueOf(arr));
    }
    
    private void initial() {
        set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("4");
        set.add("8");
        set.add("16");
        set.add("23");
        set.add("46");
        set.add("128");
        set.add("256");
        set.add("125");
        set.add("0124");
        set.add("0248");
        set.add("0469");
        set.add("1289");
        set.add("13468");
        set.add("23678");
        set.add("35566");
        set.add("011237");
        set.add("122446");
        set.add("224588");
        set.add("0145678");
        set.add("0122579");
        set.add("0134449");
        set.add("0368888");
        set.add("11266777");
        set.add("23334455");
        set.add("01466788");
        set.add("112234778");
        set.add("234455668");
        set.add("012356789");
    }
}



class Solution {
    public boolean reorderedPowerOf2(int N) {
        if ((N & (N - 1)) == 0) return true;
        
        int[] arr = build(N);
        int digits = (int) Math.log10(N), ex = digits % 3 == 0 ? 1 : 0;
        int start = 4 + digits % 3 * 3 + digits / 3 * 10, end = start - 3 - ex;
        
        for (int i = start; i >= end; i--) {
            int[] p = build(1 << i);
            boolean flag = true;
            for (int j = 0; j < 10; j++) {
                if (arr[j] == p[j]) continue;
                flag = false;
                break;
            }
            if (flag) return true;
        }
        return false;
    }
    
    private int[] build(int N) {
        int[] arr = new int[10];
        do arr[N % 10]++;
        while ((N /= 10) > 0);
        return arr;
    }
}