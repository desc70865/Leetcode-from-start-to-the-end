/* 
In a project, you have a list of required skills req_skills, and a list of people.  The i-th person people[i] contains a list of skills that person has.

Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least one person in the team who has that skill.  We can represent these teams by the index of each person: for example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].

Return any sufficient team of the smallest possible size, represented by the index of each person.

You may return the answer in any order.  It is guaranteed an answer exists.

 

Example 1:

Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
Output: [0,2]
Example 2:

Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
Output: [1,2]
 

Constraints:

1 <= req_skills.length <= 16
1 <= people.length <= 60
1 <= people[i].length, req_skills[i].length, people[i][j].length <= 16
Elements of req_skills and people[i] are (respectively) distinct.
req_skills[i][j], people[i][j][k] are lowercase English letters.
Every skill in people[i] is a skill in req_skills.
It is guaranteed a sufficient team exists.
 */

class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> map = new HashMap<>();
        int N = req_skills.length;
        for (int i = 0; i < N; i++) map.put(req_skills[i], 1 << i);
        int M = people.size();
        int[] p = new int[M];
        for (int i = 0; i < M; i++) {
            int cur = 0;
            for (String s: people.get(i)) cur |= map.getOrDefault(s, 0);
            p[i] = cur;
        }
        int target = 1 << N;
        int[][] dp = new int[target][];
        dp[0] = new int[0];
        int max = 0;
        for (int i = 0; i < M; i++) {
            if (p[i] == 0) continue;
            for (int j = 0; j <= max; j++) {
                if (dp[j] == null) continue;
                int cur = j | p[i];
                if (dp[cur] == null || dp[j].length + 1 < dp[cur].length) {
                    dp[cur] = Arrays.copyOf(dp[j], dp[j].length + 1);
                    dp[cur][dp[j].length] = i;
                }
            }
            max |= p[i];
        }
        return dp[target - 1];
    }
}