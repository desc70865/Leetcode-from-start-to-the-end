/* 
Given a string IP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address or "Neither" if IP is not a correct IP of any type.

A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros. For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses but "192.168.01.1", while "192.168.1.00" and "192.168@1.1" are invalid IPv4 addresses.

A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:

1 <= xi.length <= 4
xi is a hexadecimal string which may contain digits, lower-case English letter ('a' to 'f') and upper-case English letters ('A' to 'F').
Leading zeros are allowed in xi.
For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses, while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.

 

Example 1:

Input: IP = "172.16.254.1"
Output: "IPv4"
Explanation: This is a valid IPv4 address, return "IPv4".
Example 2:

Input: IP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
Output: "IPv6"
Explanation: This is a valid IPv6 address, return "IPv6".
Example 3:

Input: IP = "256.256.256.256"
Output: "Neither"
Explanation: This is neither a IPv4 address nor a IPv6 address.
Example 4:

Input: IP = "2001:0db8:85a3:0:0:8A2E:0370:7334:"
Output: "Neither"
Example 5:

Input: IP = "1e1.4.5.6"
Output: "Neither"
 

Constraints:

IP consists only of English letters, digits and the characters '.' and ':'.
 */

class Solution {
    public String validIPAddress(String IP) {
        int N = IP.length();
        if (N == 0 || N > 39 || IP.charAt(N - 1) == ':' || IP.charAt(N - 1) == '.') return "Neither";
        String[] s4 = IP.split("\\.");
        if (s4.length == 4 && isIPV4(s4)) return "IPv4";
        String[] s6 = IP.split(":");
        if (s6.length == 8 && isIPV6(s6)) return "IPv6";
        return "Neither";
    }

    private boolean isIPV4(String[] s) {
        for (String p: s) {
            if (p.length() == 0 || p.length() > 1 && p.charAt(0) == '0') return false;
            for (char c: p.toCharArray()) {
                if (! isNum(c)) return false;
            }
            int k = Integer.valueOf(p);
            if (k > 255) return false;
        }
        return true;
    }

    private boolean isIPV6(String[] s) {
        for (String p: s) {
            if (p.length() > 4 || p.length() == 0) return false;
            for (char c: p.toCharArray()) {
                if (! isValid(c)) return false;
            }
        }
        return true;
    }

    private boolean isNum(char c) {
        return '0' <= c && c <= '9';
    }

    private boolean isValid(char c) {
        return '0' <= c && c <= '9' || 'a' <= c && c <= 'f' || 'A' <= c && c <= 'F';
    }
}