/* 
Given a date string in the form Day Month Year, where:

Day is in the set {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"}.
Month is in the set {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}.
Year is in the range [1900, 2100].
Convert the date string to the format YYYY-MM-DD, where:

YYYY denotes the 4 digit year.
MM denotes the 2 digit month.
DD denotes the 2 digit day.
 

Example 1:

Input: date = "20th Oct 2052"
Output: "2052-10-20"
Example 2:

Input: date = "6th Jun 1933"
Output: "1933-06-06"
Example 3:

Input: date = "26th May 1960"
Output: "1960-05-26"
 

Constraints:

The given dates are guaranteed to be valid, so no error handling is necessary.
 */

class Solution {
    public String reformatDate(String date) {
        String[] ss = date.split(" ");
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        StringBuilder re = new StringBuilder();
        re.append(ss[2]);
        re.append("-");
        
        for (int i = 0; i < months.length; i++) {
            if (months[i].equals(ss[1])) {
                re.append((char) ('0' + (i + 1) / 10));
                re.append((char) ('0' + (i + 1) % 10));
                break;
            }
        }
        re.append("-");

        int i = 0;
        for (; i < 4; i++) {
            if (ss[0].charAt(i) < '0' || ss[0].charAt(i) > '9') break;
        }
        if (i == 1) {
            re.append('0');
            re.append(ss[0].charAt(0));
        } else {
            re.append(ss[0].charAt(0));
            re.append(ss[0].charAt(1));
        }
        return re.toString();
    }
}