/* 
We are given some website visits: the user with name username[i] visited the website website[i] at time timestamp[i].

A 3-sequence is a list of websites of length 3 sorted in ascending order by the time of their visits.  (The websites in a 3-sequence are not necessarily distinct.)

Find the 3-sequence visited by the largest number of users. If there is more than one solution, return the lexicographically smallest such 3-sequence.

 

Example 1:

Input: username = ["joe","joe","joe","james","james","james","james","mary","mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","career","home","cart","maps","home","home","about","career"]
Output: ["home","about","career"]
Explanation: 
The tuples in this example are:
["joe", 1, "home"]
["joe", 2, "about"]
["joe", 3, "career"]
["james", 4, "home"]
["james", 5, "cart"]
["james", 6, "maps"]
["james", 7, "home"]
["mary", 8, "home"]
["mary", 9, "about"]
["mary", 10, "career"]
The 3-sequence ("home", "about", "career") was visited at least once by 2 users.
The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
 

Note:

3 <= N = username.length = timestamp.length = website.length <= 50
1 <= username[i].length <= 10
0 <= timestamp[i] <= 10^9
1 <= website[i].length <= 10
Both username[i] and website[i] contain only lowercase characters.
It is guaranteed that there is at least one user who visited at least 3 websites.
No user visits two websites at the same time.
 */

class Solution {
    /**
        inner class
    */
    class Log {
        String username;
        int timestamp;
        String website;
        
        public Log(String _username, int _timestamp, String _website) {
            username = _username;
            timestamp = _timestamp;
            website = _website;
        }
    }
    
    /**
        inner class
        Comparator for Log
    */
    class LogComparator implements Comparator<Log> {
        @Override
        public int compare(Log log1, Log log2) {
            if (log1.timestamp < log2.timestamp) {
                return -1;
            }
            else if (log1.timestamp > log2.timestamp) {
                return 1;
            }
            return 0;
        }
    }
    
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        
        // Create log
        int len = timestamp.length;
        List<Log> logs = new ArrayList<Log>();
        for (int i = 0; i < len; i++) {
            logs.add(new Log(username[i], timestamp[i], website[i]));
        }
        
        // Use comparator to sort the log
        Collections.sort(logs, new LogComparator());
        
        // printLogs(logs);
        
        // HashMap<Username, List<String>> map1
        HashMap<String, List<String>> userVisitMap = new HashMap<String, List<String>>();
        
        // HashMap<3-Sequence, Integer> map2
        HashMap<List<String>, Integer> patternTimesMap = new HashMap<List<String>, Integer>();
        
        // memory: O(N)
        
        // iterate timestamp and build map1
        for (int i = 0; i < logs.size(); i++) {
            List<String> visitPattern = userVisitMap.getOrDefault(logs.get(i).username, new ArrayList<String>());
            visitPattern.add(logs.get(i).website);
            userVisitMap.put(logs.get(i).username, visitPattern);
        }
        
        List<String> mostVisitPattern = new ArrayList<String>();
        int maxFrequency = Integer.MIN_VALUE;
        // iterate userVisitMap and generate all 3-sequence pattern
        // N
        for (String user : userVisitMap.keySet()) {
            List<String> visitPattern = userVisitMap.get(user);
            // get all 3-sequence pattern of a user
            // < N
            // C(n, 3) = n(n-1)(n-2)
            int size = visitPattern.size();
            // Make sure a certain for one user can only count for once
            HashSet<List<String>> set = new HashSet<List<String>>();
            for (int i = 0; i <= size - 3; i++) {
                for (int j = i + 1; j <= size - 2; j++) {
                    for (int k = j + 1; k <= size - 1; k++) {
                        List<String> threeSequence = new ArrayList<String>();
                        threeSequence.add(visitPattern.get(i));
                        threeSequence.add(visitPattern.get(j));
                        threeSequence.add(visitPattern.get(k));
                        if (set.contains(threeSequence)) {
                            continue;
                        }
                        set.add(threeSequence);
                        int times = patternTimesMap.getOrDefault(threeSequence, 0);
                        times++;
                        patternTimesMap.put(threeSequence, times);
                        if (times > maxFrequency) {
                            maxFrequency = times;
                            mostVisitPattern = new ArrayList<String>(threeSequence);
                        }
                        else if (times == maxFrequency) {
                            // If there is more than one solution, 
                            // return the lexicographically smallest such 3-sequence.
                            mostVisitPattern = new ArrayList<String>(getAlphabetSequence(mostVisitPattern, threeSequence));
                        }
                    }
                }
            }
            
        }
        return mostVisitPattern;      
    }
        
    /**
        return the lexicographically smallest such 3-sequence.
    */
    private List<String> getAlphabetSequence(List<String> mostVisitPattern, List<String> threeSequence) {
        for (int i = 0; i < 3; i++) {
            // Compare every website
            int index = 0;
            String word1 = mostVisitPattern.get(i);
            String word2 = threeSequence.get(i);
            while (index < word1.length() && index < word2.length()) {
                if (word1.charAt(index) < word2.charAt(index)) {
                    return mostVisitPattern;
                }
                else if (word1.charAt(index) > word2.charAt(index)) {
                    return threeSequence;
                }
                
                // word1.charAt(index) == word2.charAt(index)
                index++;         
            }
            if (index == word1.length() && index < word2.length()) {
                return mostVisitPattern;
            }
            if (index == word2.length() && index < word1.length()) {
                return threeSequence;
            }
        }
        return mostVisitPattern;      
    }
    
    /**
        print all logs
    */
    private void printLogs (List<Log> logs) {
        for (int i = 0; i < logs.size(); i++) {
            Log log = logs.get(i);
            System.out.println(log.username +" " + log.timestamp + " " + log.website);
        }
    }
}