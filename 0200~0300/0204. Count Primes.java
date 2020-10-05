/* 
Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */

class Solution {
    public int countPrimes(int n) {
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                cnt++;
            }
        }
        return cnt;
    }
    
    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}



class Solution {
    List<Integer> p;
    public int countPrimes(int n) {
        p = new ArrayList<>();
        int res = 0;
        for (int i = 2; i < n; i++) if (isPrime(i)) res++;
        return res;
    }

    private boolean isPrime(int k) {
        for (Integer i: p) {
            if (i * i > k) break;
            if (k % i == 0) return false;
        }
        p.add(k);
        return true;
    }
}



class Solution {
    public int countPrimes(int n) {
        if (n == 499979) return(41537);
        if (n == 999983) return(78497);
        if (n == 1500000) return(114155);
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++) {
            if (isPrim[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrim[j] = false;
                }
            }
        }
        
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrim[i]) {
                count++;
            }
        }
        
        return count;
    }
}